/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.managedbean;

import com.oi.promocoesweb.dbms.control.TemplateControl;
import com.oi.promocoesweb.template.entity.BoCartao;
import com.oi.promocoesweb.template.entity.ConfigOfertasPre;
import com.oi.promocoesweb.template.entity.FraseologiasOfertasPre;
import com.oi.promocoesweb.template.entity.FrasesCartao;
import com.oi.promocoesweb.template.entity.IncentivoOfertasPre;
import com.oi.promocoesweb.template.entity.IoCartaoCartao;
import com.oi.promocoesweb.template.entity.IoCartaoControle;
import com.oi.promocoesweb.template.entity.PromptsOfertasPre;
import com.oi.promocoesweb.template.entity.VoCartao;
import com.oi.promocoesweb.template.enums.TabelasEnum;
import com.oi.promocoesweb.validation.annotation.FieldValidation;
import com.oi.promocoesweb.validation.bean.ValidationWarnHolder;
import com.oi.promocoesweb.validation.utils.Validation;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author mmouraam
 */
@Named
@ApplicationScoped
public class WelcomeBean implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private List<ConfigOfertasPre> configOfertasPreList;
    private List<PromptsOfertasPre> promptsOfertasPreList;
    private List<FraseologiasOfertasPre> fraseologiasOfertasPreList;
    private List<IncentivoOfertasPre> incentivoOfertasPreList;
    private List<VoCartao> voCartaoList;
    private List<BoCartao> boCartaoList;
    private List<FrasesCartao> frasesCartaoList;
    private List<IoCartaoCartao> ioCartaoCartaoList;
    private List<IoCartaoControle> ioCartaoControleList;
    private StreamedContent scriptFile;
    private Map<String, List<ValidationWarnHolder>> map;
    private List<String> tabelasDdd;
    private List<String> tabelasDdd2x;
    private List<String> tabelasWav;
    private List<String> tabelasKey;

    @PostConstruct
    public void init() {
        logger.trace("============= Welcome init =============");
        map = new HashMap<>();
        map.put(Validation.DDD_WARN, new ArrayList<>());
        map.put(Validation.DDD_2X_WARN, new ArrayList<>());
        map.put(Validation.LIST_WARN, new ArrayList<>());
        map.put(Validation.WAV_WARN, new ArrayList<>());
        map.put(Validation.QTD_WAV_WARN, new ArrayList<>());
        map.put(Validation.KEY_WARN, new ArrayList<>());
        tabelasDdd = new ArrayList<>();
        tabelasDdd2x = new ArrayList<>();
        tabelasWav = new ArrayList<>();
        tabelasKey = new ArrayList<>();
/*
        try {
            loadTabela();

            Map<String, String> mapDdd = new HashMap<>();
            String tabela = "CONFIG_OFERTASPRE";
            for (ConfigOfertasPre configOfertasPre : configOfertasPreList) {
                for (Field declaredField : configOfertasPre.getClass().getDeclaredFields()) {
                    declaredField.setAccessible(true);
                    proccessWarn(declaredField, tabela, configOfertasPre);
                }

                String ddd = configOfertasPre.getDdd();
                if (ddd != null) {
                    String[] dddArray = ddd.split(",");
                    for (String s : dddArray) {
                        String key = "Id_ofertaocs: " + configOfertasPre.getId_ofertaocs() + "|ddd: " + s;
                        if (mapDdd.containsKey(key)) {
                            proccessWarn(key, tabela, Validation.DDD_2X_WARN);
                        } else {
                            mapDdd.put(key, "");
                        }
                    }
                }

            }
            mapDdd.clear();
            tabela = "PROMPTS_OFERTASPRE";
            for (PromptsOfertasPre configOfertasPre : promptsOfertasPreList) {
                for (Field declaredField : configOfertasPre.getClass().getDeclaredFields()) {
                    declaredField.setAccessible(true);
                    proccessWarn(declaredField, tabela, configOfertasPre);
                }

                String ddd = configOfertasPre.getDdd();
                if (ddd != null) {
                    String[] dddArray = ddd.split(",");
                    for (String s : dddArray) {
                        String key = "Id_ofertaocs: " + configOfertasPre.getId_ofertaocs() + "|ddd: " + s;
                        if (mapDdd.containsKey(key)) {
                            proccessWarn(key, tabela, Validation.DDD_2X_WARN);
                        } else {
                            mapDdd.put(key, "");
                        }
                    }
                }
            }
            mapDdd.clear();
            tabela = "FRASEOLOGIAS_OFERTASPRE";
            for (FraseologiasOfertasPre configOfertasPre : fraseologiasOfertasPreList) {
                for (Field declaredField : configOfertasPre.getClass().getDeclaredFields()) {
                    declaredField.setAccessible(true);
                    proccessWarn(declaredField, tabela, configOfertasPre);
                }

                String ddd = configOfertasPre.getDdd();
                if (ddd != null) {
                    String[] dddArray = ddd.split(",");
                    for (String s : dddArray) {
                        String key = "Id_ofertaocs: " + configOfertasPre.getId_ofertaocs() + "|ddd: " + s;
                        if (mapDdd.containsKey(key)) {
                            proccessWarn(key, tabela, Validation.DDD_2X_WARN);
                        } else {
                            mapDdd.put(key, "");
                        }
                    }
                }
            }
            mapDdd.clear();
            tabela = "INCENTIVO_OFERTASPRE";
            for (IncentivoOfertasPre configOfertasPre : incentivoOfertasPreList) {
                for (Field declaredField : configOfertasPre.getClass().getDeclaredFields()) {
                    declaredField.setAccessible(true);
                    proccessWarn(declaredField, tabela, configOfertasPre);
                }

                String ddd = configOfertasPre.getDdd();
                if (ddd != null) {
                    String[] dddArray = ddd.split(",");
                    for (String s : dddArray) {
                        String key = "lista ddd: " + configOfertasPre.getDdd() + "|ddd: " + s;
                        if (mapDdd.containsKey(key)) {
                            proccessWarn(key, tabela, Validation.DDD_2X_WARN);
                        } else {
                            mapDdd.put(key, "");
                        }
                    }
                }
            }
            mapDdd.clear();
            tabela = "VO_CARTAO";
            for (VoCartao configOfertasPre : voCartaoList) {
                for (Field declaredField : configOfertasPre.getClass().getDeclaredFields()) {
                    declaredField.setAccessible(true);
                    proccessWarn(declaredField, tabela, configOfertasPre);
                }
            }
            mapDdd.clear();
            tabela = "BO_CARTAO";
            for (BoCartao configOfertasPre : boCartaoList) {
                for (Field declaredField : configOfertasPre.getClass().getDeclaredFields()) {
                    declaredField.setAccessible(true);
                    proccessWarn(declaredField, tabela, configOfertasPre);
                }

                String ddd = configOfertasPre.getDdd();
                if (ddd != null) {
                    String[] dddArray = ddd.split(",");
                    for (String s : dddArray) {
                        String key = "Id_bfpg: " + configOfertasPre.getIdbeneficio() + "." + configOfertasPre.getIdprograma() + "|ddd: " + s;
                        if (mapDdd.containsKey(key)) {
                            proccessWarn(key, tabela, Validation.DDD_2X_WARN);
                        } else {
                            mapDdd.put(key, "");
                        }
                    }
                }
            }
            mapDdd.clear();
            tabela = "FRASES_CARTAO";
            for (FrasesCartao configOfertasPre : frasesCartaoList) {
                for (Field declaredField : configOfertasPre.getClass().getDeclaredFields()) {
                    declaredField.setAccessible(true);
                    proccessWarn(declaredField, tabela, configOfertasPre);
                }

                String ddd = configOfertasPre.getDdd();
                if (ddd != null) {
                    String[] dddArray = ddd.split(",");
                    for (String s : dddArray) {
                        String key = "Id_bfpg: " + configOfertasPre.getIdbeneficio() + "." + configOfertasPre.getIdprograma() + "|ddd: " + s;
                        if (mapDdd.containsKey(key)) {
                            proccessWarn(key, tabela, Validation.DDD_2X_WARN);
                        } else {
                            mapDdd.put(key, "");
                        }
                    }
                }
            }
            mapDdd.clear();
            tabela = "IO_CARTAO_CARTAO";
            for (IoCartaoCartao configOfertasPre : ioCartaoCartaoList) {
                for (Field declaredField : configOfertasPre.getClass().getDeclaredFields()) {
                    declaredField.setAccessible(true);
                    proccessWarn(declaredField, tabela, configOfertasPre);
                }

                String ddd = configOfertasPre.getDdd();
                if (ddd != null) {
                    String[] dddArray = ddd.split(",");
                    for (String s : dddArray) {
                        String key = "lista ddd: " + configOfertasPre.getDdd() + "|ddd: " + s;
                        if (mapDdd.containsKey(key)) {
                            proccessWarn(key, tabela, Validation.DDD_2X_WARN);
                        } else {
                            mapDdd.put(key, "");
                        }
                    }
                }
            }
            mapDdd.clear();
            tabela = "IO_CARTAO_CONTROLE";
            if(ioCartaoControleList!=null){
                for (IoCartaoControle configOfertasPre : ioCartaoControleList) {
                    for (Field declaredField : configOfertasPre.getClass().getDeclaredFields()) {
                        declaredField.setAccessible(true);
                        proccessWarn(declaredField, tabela, configOfertasPre);
                    }

                    String ddd = configOfertasPre.getDdd();
                    if (ddd != null) {
                        String[] dddArray = ddd.split(",");
                        for (String s : dddArray) {
                            String key = "lista ddd: " + configOfertasPre.getDdd() + "|ddd: " + s;
                            if (mapDdd.containsKey(key)) {
                                proccessWarn(key, tabela, Validation.DDD_2X_WARN);
                            } else {
                                mapDdd.put(key, "");
                            }
                        }
                    }
                }
            }
            
            Map<String,String> mapPrompts = new HashMap<>();
            for(Prompt prompt : TemplateControl.getAllPrompts(-1)){
                if(mapPrompts.containsKey(prompt.getWav())){
                    proccessWarn(prompt.getWav()+";"+prompt.getConteudo()+";"+mapPrompts.get(prompt.getWav()), "LISTA_PROMPTS", Validation.QTD_WAV_WARN);
                }
                else{
                    mapPrompts.put(prompt.getWav(), prompt.getConteudo());
                }
            }
            
        } catch (SQLException e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
*/
    }

    private void loadTabela() throws SQLException, Exception {
        configOfertasPreList = (List<ConfigOfertasPre>)(Object)TemplateControl.getAll(TabelasEnum.CONFIG_OFERTASPRE,-1);
        promptsOfertasPreList = (List<PromptsOfertasPre>)(Object)TemplateControl.getAll(TabelasEnum.PROMPTS_OFERTASPRE,-1);
        fraseologiasOfertasPreList = (List<FraseologiasOfertasPre>)(Object)TemplateControl.getAll(TabelasEnum.FRASEOLOGIAS_OFERTASPRE,-1);
        incentivoOfertasPreList = (List<IncentivoOfertasPre>)(Object)TemplateControl.getAll(TabelasEnum.INCENTIVO_OFERTASPRE,-1);
        voCartaoList = (List<VoCartao>)(Object)TemplateControl.getAll(TabelasEnum.VO_CARTAO,-1);
        boCartaoList = (List<BoCartao>)(Object)TemplateControl.getAll(TabelasEnum.BO_CARTAO,-1);
        frasesCartaoList = (List<FrasesCartao>)(Object)TemplateControl.getAll(TabelasEnum.FRASES_CARTAO,-1);
        ioCartaoCartaoList = (List<IoCartaoCartao>)(Object)TemplateControl.getAll(TabelasEnum.IO_CARTAO_CARTAO,-1);
        //ioCartaoControleList = (List<>)(Object)TemplateControl.getAll();
    }

    public void generateScriptFile(String mapKey) {
        StringBuilder script = new StringBuilder();

        if (map != null) {
            for (ValidationWarnHolder registro : map.get(mapKey)) {
                logger.trace(registro.getMessage());
                script.append(registro.getHeader()).append(";").append(registro.getMessage()).append("\n");
            }

            InputStream inputStream = new ByteArrayInputStream(script.toString().getBytes(Charset.forName("ISO-8859-1")));

            DefaultStreamedContent defaultStreamedContent = new DefaultStreamedContent();
            defaultStreamedContent.setName(mapKey + "_warns.csv");
            defaultStreamedContent.setContentType("text/plain");
            defaultStreamedContent.setContentEncoding("ISO-8859-1");
            defaultStreamedContent.setStream(inputStream);

            scriptFile = defaultStreamedContent;

        }
    }

    private void proccessWarn(Field field, String tabela, Object obj) throws IllegalArgumentException, IllegalAccessException {
        if (field.isAnnotationPresent(FieldValidation.class)) {
            String warnMessage = Validation.getWarnMessage(field.getAnnotation(FieldValidation.class).name(), field.getName(), field.get(obj));

            if (!Validation.NORMAL.equals(warnMessage)) {
                ValidationWarnHolder validationWarnHolder = new ValidationWarnHolder();

                validationWarnHolder.setHeader("tabela: \"" + tabela + "\"");
                validationWarnHolder.setMessage(field.getName() + ": \"" + ((String) field.get(obj) + "\""));

                logger.trace("warnMessage:{}",warnMessage);
                map.get(warnMessage).add(validationWarnHolder);

                countTabela(tabela,warnMessage);
            }
        }
    }

    private void proccessWarn(String message, String tabela, String warnMessage) throws IllegalArgumentException, IllegalAccessException {

        ValidationWarnHolder validationWarnHolder = new ValidationWarnHolder();

        validationWarnHolder.setHeader("tabela: \"" + tabela + "\"");
        validationWarnHolder.setMessage(message);

        map.get(warnMessage).add(validationWarnHolder);
        countTabela(tabela,warnMessage);

    }
    
    private void countTabela(String tabela, String warnMessage){
        if (Validation.DDD_WARN.equals(warnMessage)) {
            if (!tabelasDdd.contains(tabela)) {
                tabelasDdd.add(tabela);
            }
        } else if (Validation.WAV_WARN.equals(warnMessage)) {
            if (!tabelasWav.contains(tabela)) {
                tabelasWav.add(tabela);
            }
        } else if (Validation.KEY_WARN.equals(warnMessage)) {
            if (!tabelasKey.contains(tabela)) {
                tabelasKey.add(tabela);
            }
        } else if (Validation.DDD_2X_WARN.equals(warnMessage)) {
            if (!tabelasDdd2x.contains(tabela)) {
                tabelasDdd2x.add(tabela);
            }
        }
    }

    public List<ConfigOfertasPre> getConfigOfertasPreList() {
        return configOfertasPreList;
    }

    public void setConfigOfertasPreList(List<ConfigOfertasPre> configOfertasPreList) {
        this.configOfertasPreList = configOfertasPreList;
    }

    public StreamedContent getScriptFile() {
        return scriptFile;
    }

    public Map<String, List<ValidationWarnHolder>> getMap() {
        return map;
    }

    public List<String> getTabelasDdd() {
        return tabelasDdd;
    }

    public List<String> getTabelasWav() {
        return tabelasWav;
    }

    public List<String> getTabelasKey() {
        return tabelasKey;
    }

    public List<String> getTabelasDdd2x() {
        return tabelasDdd2x;
    }

}
