/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.importar.managedbean;

import com.oi.promocoesweb.dbms.control.ImportarControl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.oi.promocoesweb.importar.entity.ui.Campo;
import com.oi.promocoesweb.importar.entity.ui.Column;
import com.oi.promocoesweb.importar.entity.ui.Prompt;
import com.oi.promocoesweb.importar.entity.ui.Registro;
import com.oi.promocoesweb.importar.enumerate.AbaTemplate;
import com.oi.promocoesweb.importar.enumerate.TipoCampo;
import com.oi.promocoesweb.validation.enums.ValidationEnum;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author mmouraam
 */
@Named
@SessionScoped
public class ImportarBean implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private UploadedFile file;
    private XSSFWorkbook workbook;
    private List<Campo> campos;
    private List<AbaTemplate> abas;
    private AbaTemplate selectedAba;
    private List<String> substringValues;
    private List<Registro> tabelaList;
    private List<String> colunasChavesList;
    private List<String> colunasValuesList;
    private List<String> script;
    private boolean canSave = false;
    private boolean skip;
    private boolean scriptInsert;
    private StreamedContent scriptFile;

    private void init() {
        campos = new ArrayList<>();
        abas = new ArrayList<>();
        selectedAba = null;
        substringValues = new ArrayList<>();
        tabelaList = new ArrayList<>();
        colunasChavesList = new ArrayList<>();
        colunasValuesList = new ArrayList<>();
        script = new ArrayList<>();
        canSave = false;
        scriptInsert = true;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload(FileUploadEvent event) {
        file = event.getFile();

        logger.trace("UPLOAD XLSX! {}", file);

        if (file != null) {
            try {
                init();

                workbook = new XSSFWorkbook(file.getInputStream());

                try {
                    abas = new ArrayList<>();
                    int i = 0;
                    while (true) {
                        XSSFSheet sheet = workbook.getSheetAt(i);
                        try {
                            logger.trace("add {}", sheet.getSheetName().toUpperCase());
                            abas.add(AbaTemplate.valueOf(sheet.getSheetName().toUpperCase()));
                        } catch (Exception e) {
                        }
                        i++;
                    }
                } catch (Exception e) {
                }

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", file.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } catch (IOException e) {
                logger.error("", e);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    public void save() {
        try {
            ImportarControl.save(tabelaList, selectedAba);
        } catch (SQLException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void popularCampos() {
        logger.trace("POPULAR CAMPOS!");
        campos = new ArrayList<>();

        if (selectedAba != null && !"".equals(selectedAba)) {
            XSSFRow row = workbook.getSheetAt(abas.indexOf(selectedAba)).getRow(0);

            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("../PromocoesWeb/config/templates/" + selectedAba + ".json");
            List<Campo> camposCache;
            try {
                camposCache = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Campo.class));
                campos = camposCache;

            } catch (IOException ex) {
                logger.warn("cache error!", ex);
                for (Iterator iterator = row.cellIterator(); iterator.hasNext();) {
                    Cell next = (Cell) iterator.next();

                    campos.add(new Campo(next.getStringCellValue()));
                }
            }

        }
    }

    public void visualizar() {
        logger.trace("------------------------------------------------------------");
        script.clear();

        XSSFSheet sheet = workbook.getSheetAt(abas.indexOf(selectedAba));

        Map<String, Registro> tabela = new HashMap<>();
        Map<String, String> chaveTemp = new HashMap<>();

        logger.trace("Processando {}|{}", selectedAba, sheet.getLastRowNum());

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);

            if(row != null){
                Registro registro = new Registro(row, campos);

                if (registro.getChaves().isEmpty()) {
                    registro.setChaves(chaveTemp);
                }

                logger.trace("{}|chave: {}|{}", row.getRowNum(), registro.getChaveStr(), registro.getColunasKeysList());
                if (tabela.containsKey(registro.getChaveStr())) {
                    tabela.get(registro.getChaveStr()).merge(registro);
                } else {
                    tabela.put(registro.getChaveStr(), registro);
                }

                chaveTemp = new HashMap<>(registro.getChaves());
            }

        }

        tabelaList = tabela.values().stream().collect(Collectors.toList());
        Collections.sort(tabelaList);
        Collections.reverse(tabelaList);
        
        colunasChavesList.clear();
        colunasValuesList.clear();
        boolean existeCampoColuna = false;
        for (Campo campo : campos) {
            switch (campo.getTipo()) {
                case CHAVE:
                    colunasChavesList.add(campo.getNomeColuna()!=null&&!"".equals(campo.getNomeColuna())?campo.getNomeColuna().toUpperCase():campo.getNome().toUpperCase());
                    break;
                case COLUNA:
                    List<String> colunas = new ArrayList<>();
                    campo.getColumns().forEach((c) -> {
                        if(!colunasValuesList.contains(c.getColumnName()))
                            colunasValuesList.add(c.getColumnName());
                    });
                    existeCampoColuna = true;
                    break;

                case VALOR:
                    if (!existeCampoColuna) {
                        colunasValuesList.add(campo.getNomeColuna()!=null&&!"".equals(campo.getNomeColuna())?campo.getNomeColuna().toUpperCase():campo.getNome().toUpperCase());
                    }
                    break;
            }
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            StringWriter stringWriter = new StringWriter();
            objectMapper.writeValue(stringWriter, campos);

            File filePath = new File("../PromocoesWeb/config/templates/");
            filePath.mkdirs();
            File file = new File(filePath.getAbsolutePath() + "/" + selectedAba + ".json");
            file.createNewFile();
            Path path = Paths.get(file.getAbsolutePath());

            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                writer.write(stringWriter.getBuffer().toString());
                writer.close();
            }
            logger.trace("Path: " + path);

        } catch (IOException e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        canSave = true;
    }

    public void loadScript() {
        logger.trace("------------------------------------------------------------");
        script.clear();

        if (scriptInsert) {
            for (Registro registro : tabelaList) {
                StringBuilder sb = new StringBuilder();

                sb.append("INSERT INTO ").append(selectedAba).append(" (");
                for (String colunaChave : colunasChavesList) {
                    sb.append(colunaChave).append(",");
                }
                for (String colunaValue : colunasValuesList) {
                    sb.append(colunaValue).append(",");
                }
                sb.append(") VALUES(");
                for (String colunaChave : colunasChavesList) {
                    sb.append("'").append(registro.getChaves().get(colunaChave)).append("',");
                }
                for (String colunaValue : colunasValuesList) {
                    sb.append("'").append(registro.getColunas().get(colunaValue) != null ? registro.getColunas().get(colunaValue).replaceAll("'", "") : null).append("',");
                }
                sb.append(");");

                script.add(sb.toString().replace(",)", ")").replace("'null'", "null").replace("''", "null"));
            }
        } else {
            for (Registro registro : tabelaList) {
                StringBuilder sb = new StringBuilder();

                sb.append("UPDATE ").append(selectedAba).append(" SET ");
                for (String colunaValue : colunasValuesList) {
                    sb.append(colunaValue).append(" = ");
                    sb.append("'").append(registro.getColunas().get(colunaValue) != null ? registro.getColunas().get(colunaValue).replaceAll("'", "") : null).append("', ");
                }
                sb.append("WHERE ");
                for (String colunaChave : colunasChavesList) {
                    sb.append(colunaChave).append(" = ");
                    sb.append("'").append(registro.getChaves().get(colunaChave)).append("' AND ");
                }
                sb.append(";");

                script.add(sb.toString().replace(", WHERE", " WHERE").replace("AND ;", ";").replace("'null'", "null").replace("''", "null"));
            }
        }

    }
    
    public void generateScriptFile() {
        StringBuilder script = new StringBuilder();

        if (tabelaList != null) {
            for (Registro registro : tabelaList) {
                for (Prompt prompt : registro.getPrompts()) {
                    script.append(prompt.getWav()).append("|").append(prompt.getConteudo().replace("\n", "")).append("\n");
                }
            }

            InputStream inputStream = new ByteArrayInputStream(script.toString().getBytes(Charset.forName("UTF-8")));

            DefaultStreamedContent defaultStreamedContent = new DefaultStreamedContent();
            defaultStreamedContent.setStream(inputStream);
            defaultStreamedContent.setName("prompts.txt");
            defaultStreamedContent.setContentType("text/plain");

            scriptFile = defaultStreamedContent;

        }
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            logger.trace("PhaseId: " + event.getPhaseId().getName());
            logger.trace("Ordinal: " + event.getPhaseId().getOrdinal());
            logger.trace("NewStep: " + event.getNewStep());
            logger.trace("OldStep: " + event.getOldStep());

            switch (event.getNewStep()) {
                case "VISUALIZAR":
                    visualizar();
                    break;
                case "SCRIPT":
                    loadScript();
                    break;
            }
            return event.getNewStep();
        }
    }

    public void onAddNew(Campo campo) {
        campo.addColumn();
    }
    public void onRemove(Campo campo, Column column) {
        campo.getColumns().remove(column);
    }
    
    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public List<AbaTemplate> getAbas() {
        return abas;
    }

    public AbaTemplate getSelectedAba() {
        return selectedAba;
    }

    public void setSelectedAba(AbaTemplate selectedAba) {
        this.selectedAba = selectedAba;
    }

    public List<String> getSubstringValues() {
        return substringValues;
    }

    public List<Registro> getTabelaList() {
        return tabelaList;
    }

    public List<String> getColunasChavesList() {
        return colunasChavesList;
    }

    public void setColunasChavesList(List<String> colunasChavesList) {
        this.colunasChavesList = colunasChavesList;
    }

    public List<String> getColunasValuesList() {
        return colunasValuesList;
    }

    public void setColunasValuesList(List<String> colunasValuesList) {
        this.colunasValuesList = colunasValuesList;
    }

    public TipoCampo[] getTipoCampos() {
        return TipoCampo.values();
    }

    public ValidationEnum[] getTipoDados() {
        return ValidationEnum.values();
    }

    public boolean isCanSave() {
        return canSave;
    }

    public List<String> getScript() {
        return script;
    }

    public boolean isScriptInsert() {
        return scriptInsert;
    }

    public void setScriptInsert(boolean scriptInsert) {
        this.scriptInsert = scriptInsert;
    }

    public StreamedContent getScriptFile() {
        return scriptFile;
    }

}
