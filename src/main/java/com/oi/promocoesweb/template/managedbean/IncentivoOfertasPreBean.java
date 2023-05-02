/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.managedbean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oi.promocoesweb.dbms.control.TemplateControl;
import com.oi.promocoesweb.dbms.entity.User;
import static com.oi.promocoesweb.managedbean.ControleBean.AUTH_USER;
import com.oi.promocoesweb.template.entity.IncentivoOfertasPre;
import com.oi.promocoesweb.template.entity.Template;
import com.oi.promocoesweb.template.entity.aprovacao.Aprovacao;
import com.oi.promocoesweb.template.enums.StatusEnum;
import com.oi.promocoesweb.template.enums.TabelasEnum;
import com.oi.promocoesweb.template.utils.JsonUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author mmouraam
 */
@Named
@ViewScoped
public class IncentivoOfertasPreBean extends TemplateBean<IncentivoOfertasPre> implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private final TabelasEnum tabelasEnum = TabelasEnum.INCENTIVO_OFERTASPRE;

    private List<String> validIdOfertaOcsList;

    @PostConstruct
    public void init() {
        logger.trace("============= TemplateBean init =============");
        try {
            super.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new IncentivoOfertasPre());
            super.clearVarsAdd(new IncentivoOfertasPre());

            Date dt_gmud_incentivo = (Date) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("dt_gmud_incentivo");
            if (dt_gmud_incentivo != null) {
                validIdOfertaOcsList = TemplateControl.getValidIdOfertaOcs(dt_gmud_incentivo);
            }

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    @Override
    public void reloadGmud() {

        try {

            validIdOfertaOcsList = TemplateControl.getValidTipoPlano(getNewTemplate().getDt_gmud());

        } catch (Exception e) {
            logger.error(" ", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void reloadEditGmud() {

        try {
            if (getEditTemplate().getDt_gmud() != null) {
                validIdOfertaOcsList = TemplateControl.getValidTipoPlano(getEditTemplate().getDt_gmud());
            } else if (getDefaultFilterValue() != null && !"PRODUCAO".equals(getDefaultFilterValue())) {
                validIdOfertaOcsList = TemplateControl.getValidTipoPlano(new SimpleDateFormat("dd/MM/yy").parse(getDefaultFilterValue()));
            }

        } catch (Exception e) {
            logger.error(" ", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void reloadTabela() {
        try {
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new IncentivoOfertasPre());
            super.clearVarsAdd(new IncentivoOfertasPre());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void adicionar() {
   
        super.adicionar(tabelasEnum);
        super.clearVarsAdd(new IncentivoOfertasPre());

        if (!isAdicionarOutra()) {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('sidebarAdd').hide();");
        }
    }

    public void editar() {
        try {
            Map<String, Boolean> collect = getEditTemplate().getEditMap().entrySet().stream().filter(x -> x.getValue().equals(Boolean.TRUE)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            if (collect == null || collect.isEmpty()) {
                throw new Exception("Selecione pelo menos um checkbox para edição!");
            }

            getEditTemplate().normalize();
            for (Template configOfertasPre : getSelectedTemplateList()) {
                configOfertasPre.setHistory(JsonUtils.addToJsonArray(configOfertasPre.getDt_gmud() == null ? null : configOfertasPre.getHistory(), getEditTemplate()));
                logger.trace(JsonUtils.toJson(configOfertasPre.getHistory()));
            }

            super.editar(tabelasEnum);

            super.clearVarsEdit(new IncentivoOfertasPre());

        } catch (JsonProcessingException e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('sidebarEdit').hide();");
    }

    public void deletar() {
        super.deletar(tabelasEnum);

        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('sidebarDelete').hide();");
    }

    public void adiar() {
        super.adiar(tabelasEnum);
    }

    public void aprovar() {
        super.implantar(tabelasEnum);
    }

    public void aprovarGmud() {
        super.aprovarGmud(tabelasEnum);
    }

    public void iniciarGmud() {
        super.iniciarGmud(tabelasEnum);
    }

    public void validarGmud() {
        super.validarGmud(tabelasEnum);
    }

    public void uatGmud() {
        super.uatGmud(tabelasEnum);
    }

    public void implantarGmud() {
        super.implantarGmud(tabelasEnum);
    }

    public void preLoadEditBean(Boolean reloadEditGmud) {
        if (getSelectedTemplateList().size() == 1) {
            setEditTemplate(new IncentivoOfertasPre(getSelectedTemplateList().get(0)));
            getEditTemplate().setUser(super.getUser());
            setNewTemplate(new IncentivoOfertasPre(getSelectedTemplateList().get(0)));
            getNewTemplate().setUser(super.getUser());
        } else {
            setEditTemplate(new IncentivoOfertasPre());
            getEditTemplate().setUser(super.getUser());
            setNewTemplate(new IncentivoOfertasPre());
            getNewTemplate().setUser(super.getUser());
        }
        if (getSelectedTemplateList().size() > 0) {
            super.setGmudToDelete(getSelectedTemplateList().get(0).getDt_gmud());
        }
        if (reloadEditGmud) {
            reloadEditGmud();
        }
    }

    public void preLoadEditBean() {
        preLoadEditBean(false);
    }

    public void generateScriptFile(String aplicacao) {
        logger.trace("SCRIPT gmud:{}|{}", super.getDefaultFilterValue(), aplicacao);
        StringBuilder script = new StringBuilder();

        List<IncentivoOfertasPre> list = null;

        list = getTemplateList().stream().filter(c -> super.getDefaultFilterValue().equals(c.getDtGmudStr())).collect(Collectors.toList());

        InputStream inputStream = null;
        int i;
        if (list != null) {
            StringBuilder sb;

            for (IncentivoOfertasPre registro : list) {
                sb = new StringBuilder();

                switch (aplicacao) {
                    case "USSD":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM ").append(tabelasEnum.name()).append(";\n");
                            script.append(sb.toString());

                        } else {
                            for (IncentivoOfertasPre registroDel : list) {
                                sb.append("DELETE FROM ").append(tabelasEnum.name());

                                sb.append(" WHERE ");
                                sb.append("DDD = '").append(registroDel.getDdd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }

                        sb.append("INSERT INTO ").append(tabelasEnum.name());
                        sb.append(" (DDD,RECACUMULADAINICIAL,RECACUMULADAFINAL,OFERTAPROMO1_ID_OFERTAOCS,OFERTAPROMO2_ID_OFERTAOCS) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecacumuladainicial() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecacumuladafinal() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertapromo1_id_ofertaocs() + "',")
                                .replace("VAR" + (i++), "'" + registro.getOfertapromo2_id_ofertaocs() + "'")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .replace("OFERTAPROMO1_ID_OFERTAOCS", "OFERTAPROMO1")
                                        .replace("OFERTAPROMO2_ID_OFERTAOCS", "OFERTAPROMO2")
                                        .getBytes(Charset.forName("UTF-8")));

                        break;

                    case "144":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM TB_INCENTIVO_OFERTASOCSPRE;\n");
                            script.append(sb.toString());

                        } else {
                            for (IncentivoOfertasPre registroDel : list) {
                                sb.append("DELETE FROM TB_INCENTIVO_OFERTASOCSPRE");

                                sb.append(" WHERE ");
                                sb.append("DDD = '").append(registroDel.getDdd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }

                        sb.append("INSERT INTO TB_INCENTIVO_OFERTASOCSPRE");
                        sb.append(" (DDD,RECACUMULADAINICIAL,RECACUMULADAFINAL,OFERTAPROMO1_ID_OFERTAOCS,OFERTAPROMO2_ID_OFERTAOCS) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecacumuladainicial() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecacumuladafinal() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertapromo1_id_ofertaocs() + "',")
                                .replace("VAR" + (i++), "'" + registro.getOfertapromo2_id_ofertaocs() + "'")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .replace("RECACUMULADAINICIAL", "REC_ACUMULADA_INICIAL")
                                        .replace("RECACUMULADAFINAL", "REC_ACUMULADA_FINAL")
                                        .replace("OFERTAPROMO1_ID_OFERTAOCS", "OFERTA_PROMO1")
                                        .replace("OFERTAPROMO2_ID_OFERTAOCS", "OFERTA_PROMO2")
                                        .getBytes(Charset.forName("UTF-8")));

                        break;

                    case "880":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM OCS_INCENTIVO_OFERTASPRE;\n");
                            script.append(sb.toString());

                        } else {
                            for (IncentivoOfertasPre registroDel : list) {
                                sb.append("DELETE FROM OCS_INCENTIVO_OFERTASPRE");

                                sb.append(" WHERE ");
                                sb.append("DDD = '").append(registroDel.getDdd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }

                        sb.append("INSERT INTO OCS_INCENTIVO_OFERTASPRE");
                        sb.append(" (DDD,RECACUMULADAINICIAL,RECACUMULADAFINAL,OFERTAPROMO1_ID_OFERTAOCS,OFERTAPROMO2_ID_OFERTAOCS) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecacumuladainicial() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecacumuladafinal() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertapromo1_id_ofertaocs() + "',")
                                .replace("VAR" + (i++), "'" + registro.getOfertapromo2_id_ofertaocs() + "'")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .replace("RECACUMULADAINICIAL", "IN_RECACUMULADAINICIAL")
                                        .replace("RECACUMULADAFINAL", "IN_RECACUMULADAFINAL")
                                        .replace("OFERTAPROMO1_ID_OFERTAOCS", "OFERTAPROMO1")
                                        .replace("OFERTAPROMO2_ID_OFERTAOCS", "OFERTAPROMO2")
                                        .getBytes(Charset.forName("UTF-8")));

                        break;
                }

            }

            DefaultStreamedContent defaultStreamedContent = new DefaultStreamedContent();
            defaultStreamedContent.setStream(inputStream);
            defaultStreamedContent.setName("incentivo_ofertaspre.sql");
            defaultStreamedContent.setContentType("text/plain");

            super.setScriptFile(defaultStreamedContent);

        }
    }

    @Override
    public List<IncentivoOfertasPre> getTemplateList() {
        return (List<IncentivoOfertasPre>) (Object) super.getTemplateList();
    }

    @Override
    public List<IncentivoOfertasPre> getSelectedTemplateList() {
        return (List<IncentivoOfertasPre>) (Object) super.getSelectedTemplateList();
    }

    @Override
    public IncentivoOfertasPre getNewTemplate() {
        return (IncentivoOfertasPre) super.getNewTemplate();
    }

    @Override
    public List<IncentivoOfertasPre> getNewTemplateList() {
        return (List<IncentivoOfertasPre>) (Object) super.getNewTemplateList();
    }

    @Override
    public IncentivoOfertasPre getEditTemplate() {
        return (IncentivoOfertasPre) super.getEditTemplate();
    }

    @Override
    public void setTemplateList(List<IncentivoOfertasPre> configOfertasPreList) {
        super.setTemplateList(configOfertasPreList);
    }

    @Override
    public void setSelectedTemplateList(List<IncentivoOfertasPre> selectedTemplateList) {
        super.setSelectedTemplateList(selectedTemplateList);
    }

    @Override
    public void setNewTemplate(IncentivoOfertasPre newTemplate) {
        super.setNewTemplate(newTemplate);
    }

    @Override
    public void setEditTemplate(IncentivoOfertasPre editTemplate) {
        super.setEditTemplate(editTemplate);
    }

    public List<IncentivoOfertasPre> getSelectedHistoryList() {
        if (super.getSelectedTemplateList() != null && super.getSelectedTemplateList().size() > 0) {
            List<IncentivoOfertasPre> history = (List<IncentivoOfertasPre>) (Object) super.getSelectedTemplateList().get(0).getHistory();

            for (IncentivoOfertasPre bean : history) {
                List<Aprovacao> collect = getAprovacaoList().stream().filter(x -> getDefaultFilterValue().equals(x.getDt_gmudStr())).collect(Collectors.toList());
                    if (collect != null && !collect.isEmpty()) {
                        StatusEnum statusTemp = StatusEnum.INICIADO;
                        for (Aprovacao aprovacao : collect) {
                            if(aprovacao.getDt_aprovacao().after(bean.getModified())){
                                switch(statusTemp){
                                    case EM_IMPLANTACAO:
                                    case IMPLANTAR:
                                        break;
                                    case EM_UAT:
                                        if(aprovacao.getStatus()==StatusEnum.EM_IMPLANTACAO || aprovacao.getStatus()==StatusEnum.IMPLANTAR){
                                            statusTemp = aprovacao.getStatus();
                                        }
                                        break;
                                    case APROVADO:
                                        if(aprovacao.getStatus()==StatusEnum.EM_IMPLANTACAO || aprovacao.getStatus()==StatusEnum.IMPLANTAR
                                                || aprovacao.getStatus()==StatusEnum.EM_UAT){
                                            statusTemp = aprovacao.getStatus();
                                        }
                                        break;
                                    case VALIDADO:
                                        if(aprovacao.getStatus()==StatusEnum.EM_IMPLANTACAO || aprovacao.getStatus()==StatusEnum.IMPLANTAR
                                                || aprovacao.getStatus()==StatusEnum.EM_UAT || aprovacao.getStatus()==StatusEnum.APROVADO){
                                            statusTemp = aprovacao.getStatus();
                                        }
                                        break;
                                    case INICIADO:
                                        statusTemp = aprovacao.getStatus();
                                        break;
                                }
                            }
                        }
                        bean.setLastStatus(statusTemp);
                    }
            }
            
            return history;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public LazyDataModel<IncentivoOfertasPre> getDataModel() {
        return (LazyDataModel<IncentivoOfertasPre>) (Object) super.getDataModel();
    }

    public TabelasEnum getTabelasEnum() {
        return tabelasEnum;
    }

    //CUSTOM METHODS///////////////////////////////////////////////////////
    public IncentivoOfertasPre getNewTemplate(Integer size, Integer index, String ddd, Date dt_gmud) {
        if (super.getNewTemplateList().size() <= index) {
            for (int i = 0; i < index; i++) {
                if (i == index) {
                    IncentivoOfertasPre incentivoOfertasPre = new IncentivoOfertasPre();
                    incentivoOfertasPre.setDdd(ddd);
                    incentivoOfertasPre.setDt_gmud(dt_gmud);
                    incentivoOfertasPre.setUser(getUser());
                    super.getNewTemplateList().add(incentivoOfertasPre);

                } else {
                    super.getNewTemplateList().add(null);
                }
            }
        } else if (super.getNewTemplateList().size() > size) {
            do {
                super.getNewTemplateList().remove(super.getNewTemplateList().size() - 1);
            } while (super.getNewTemplateList().size() > size);
        }

        if (super.getNewTemplate(index) == null) {
            IncentivoOfertasPre incentivoOfertasPre = new IncentivoOfertasPre();
            incentivoOfertasPre.setDdd(ddd);
            incentivoOfertasPre.setDt_gmud(dt_gmud);
            incentivoOfertasPre.setUser(getUser());
            super.getNewTemplateList().set(index, incentivoOfertasPre);
        } else {
            ((IncentivoOfertasPre) super.getNewTemplate(index)).setDdd(ddd);
            ((IncentivoOfertasPre) super.getNewTemplate(index)).setDt_gmud(dt_gmud);
            ((IncentivoOfertasPre) super.getNewTemplate(index)).setUser(getUser());
        }

        return (IncentivoOfertasPre) super.getNewTemplate(index);
    }

    public List<String> getValidIdOfertaOcsList() {
        return validIdOfertaOcsList;
    }

    public boolean isValidIdOfertaOcsValue(String valor, IncentivoOfertasPre incentivoOfertasPre) {
        if (valor.equals(incentivoOfertasPre.getSelectedOfertapromo1_1())) {
            return false;
        }
        if (valor.equals(incentivoOfertasPre.getSelectedOfertapromo1_2())) {
            return false;
        }
        if (valor.equals(incentivoOfertasPre.getSelectedOfertapromo1_3())) {
            return false;
        }
        if (valor.equals(incentivoOfertasPre.getSelectedOfertapromo2_1())) {
            return false;
        }
        if (valor.equals(incentivoOfertasPre.getSelectedOfertapromo2_2())) {
            return false;
        }
        if (valor.equals(incentivoOfertasPre.getSelectedOfertapromo2_3())) {
            return false;
        }

        return true;
    }
}
