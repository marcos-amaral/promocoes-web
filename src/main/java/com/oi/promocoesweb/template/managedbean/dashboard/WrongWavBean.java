/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.managedbean.dashboard;

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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
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
@ViewScoped
public class WrongWavBean implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private List<PromptsOfertasPre> promptsOfertasPreList;
    private List<BoCartao> boCartaoList;
    private StreamedContent scriptFile;
    private Map<String, List<ValidationWarnHolder>> map;
    private List<String> tabelasWav;

    @PostConstruct
    public void init() {
        logger.trace("============= Welcome init =============");
        map = new HashMap<>();
        map.put(Validation.WAV_WARN, new ArrayList<>());
        tabelasWav = new ArrayList<>();

        try {
            loadTabela();

            Map<String, String> mapDdd = new HashMap<>();
            String tabela = "PROMPTS_OFERTASPRE";
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
            
        } catch (SQLException e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    private void loadTabela() throws SQLException, Exception {
        promptsOfertasPreList = (List<PromptsOfertasPre>)(Object)TemplateControl.getAll(TabelasEnum.PROMPTS_OFERTASPRE,-1);
        boCartaoList = (List<BoCartao>)(Object)TemplateControl.getAll(TabelasEnum.BO_CARTAO,-1);
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
        if (Validation.WAV_WARN.equals(warnMessage)) {
            if (!tabelasWav.contains(tabela)) {
                tabelasWav.add(tabela);
            }
        }
    }

    public StreamedContent getScriptFile() {
        return scriptFile;
    }

    public Map<String, List<ValidationWarnHolder>> getMap() {
        return map;
    }

    public List<String> getTabelasWav() {
        return tabelasWav;
    }

}
