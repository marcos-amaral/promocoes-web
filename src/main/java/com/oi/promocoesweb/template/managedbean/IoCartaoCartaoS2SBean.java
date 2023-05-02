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
import com.oi.promocoesweb.template.entity.IoCartaoCartao;
import com.oi.promocoesweb.template.entity.IoCartaoCartaoS2S;
import com.oi.promocoesweb.template.entity.Template;
import com.oi.promocoesweb.template.entity.aprovacao.Aprovacao;
import com.oi.promocoesweb.template.enums.StatusEnum;
import com.oi.promocoesweb.template.enums.TabelasEnum;
import com.oi.promocoesweb.template.utils.JsonUtils;
import java.io.Serializable;
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
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Jpereirc
 */
@Named
@ViewScoped
public class IoCartaoCartaoS2SBean extends TemplateBean<IoCartaoCartaoS2S> implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private final TabelasEnum tabelasEnum = TabelasEnum.IO_CARTAO_CARTAO_S2S;

    private List<String> validTipoBonusListS2S;

    @PostConstruct
    public void init() {
        logger.trace("============= TemplateBean init =============");
        try {
            super.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new IoCartaoCartaoS2S());
            super.clearVarsAdd(new IoCartaoCartaoS2S());

            Date dt_gmud_incentivo = (Date) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("dt_gmud_incentivo");
            validTipoBonusListS2S = TemplateControl.getValidTipoBonusS2S(dt_gmud_incentivo);

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    @Override
    public void reloadGmud() {

        try {

            validTipoBonusListS2S = TemplateControl.getValidTipoBonusS2S(getNewTemplate().getDt_gmud());

        } catch (Exception e) {
            logger.error(" ", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void reloadEditGmud() {

        try {
            if (getEditTemplate().getDt_gmud() != null) {
                validTipoBonusListS2S = TemplateControl.getValidTipoBonusS2S(getEditTemplate().getDt_gmud());
            } else if (getDefaultFilterValue() != null && !"PRODUCAO".equals(getDefaultFilterValue())) {
                validTipoBonusListS2S = TemplateControl.getValidTipoBonusS2S(new SimpleDateFormat("dd/MM/yy").parse(getDefaultFilterValue()));
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
            super.clearVarsEdit(new IoCartaoCartaoS2S());
            super.clearVarsAdd(new IoCartaoCartaoS2S());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void adicionar() {
        if (super.adicionar(tabelasEnum)) {
            super.clearVarsAdd(new IoCartaoCartaoS2S());

            if (!isAdicionarOutra()) {
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('sidebarAdd').hide();");
            }
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

            if (super.editar(tabelasEnum)) {
                super.clearVarsEdit(new IoCartaoCartaoS2S());
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('sidebarEdit').hide();");
            }

        } catch (JsonProcessingException e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);

            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('sidebarEdit').hide();");
        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);

            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('sidebarEdit').hide();");
        }

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
            setEditTemplate(new IoCartaoCartaoS2S(getSelectedTemplateList().get(0)));
            getEditTemplate().setUser(super.getUser());
            setNewTemplate(new IoCartaoCartaoS2S(getSelectedTemplateList().get(0)));
            getNewTemplate().setUser(super.getUser());
        } else {
            setEditTemplate(new IoCartaoCartaoS2S());
            getEditTemplate().setUser(super.getUser());
            setNewTemplate(new IoCartaoCartaoS2S());
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

        List<IoCartaoCartao> list = null;
        /*
        list = getTemplateList().stream().filter(c -> super.getDefaultFilterValue().equals(c.getDtGmudStr())).collect(Collectors.toList());
        
        if (list != null) {
            StringBuilder sb;

            InputStream inputStream = null;
            int i = 1;
            for (IoCartaoCartao registro : list) {
                sb = new StringBuilder();

                switch (aplicacao) {
                    case "USSD":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM ").append(tabelasEnum.name()).append(";\n");
                            script.append(sb.toString());

                        } else {
                            for (IoCartaoCartao registroDel : list) {
                                sb.append("DELETE FROM ").append(tabelasEnum.name());

                                sb.append(" WHERE ");
                                sb.append("DDD = '").append(registroDel.getDdd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }

                        sb.append("INSERT INTO ").append(tabelasEnum.name());
                        sb.append(" (DDD,RECACUMULADAINICIAL,RECACUMULADAFINAL,OFERTAPROMO1,OFERTAPROMO2) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecacumuladainicial() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecacumuladafinal() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertapromo1() + "',")
                                .replace("VAR" + (i++), "'" + registro.getOfertapromo2() + "'")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .replace("RECACUMULADAINICIAL", "IN_REC_ACM_INCIAL")
                                        .replace("RECACUMULADAFINAL", "IN_REC_ACM_FINAL")
                                        .replace("OFERTAPROMO1", "OFERTAPRM1")
                                        .replace("OFERTAPROMO2", "OFERTAPRM2")
                                        .getBytes(Charset.forName("UTF-8")));

                        break;

                    case "144":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM TB_INCENTIVO_CARTAO_CARTAO;\n");
                            script.append(sb.toString());

                        } else {
                            for (IoCartaoCartao registroDel : list) {
                                sb.append("DELETE FROM TB_INCENTIVO_CARTAO_CARTAO");

                                sb.append(" WHERE ");
                                sb.append("DDD = '").append(registroDel.getDdd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }

                        sb.append("INSERT INTO TB_INCENTIVO_CARTAO_CARTAO");
                        sb.append(" (DDD,RECACUMULADAINICIAL,RECACUMULADAFINAL,OFERTAPROMO1,OFERTAPROMO2) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecacumuladainicial() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecacumuladafinal() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertapromo1() + "',")
                                .replace("VAR" + (i++), "'" + registro.getOfertapromo2() + "'")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .replace("RECACUMULADAINICIAL", "IN_REC_ACUMULADA_INICIAL")
                                        .replace("RECACUMULADAFINAL", "IN_REC_ACUMULADA_FINAL")
                                        .replace("OFERTAPROMO1", "OFERTA_PROMO1")
                                        .replace("OFERTAPROMO2", "OFERTA_PROMO2")
                                        .getBytes(Charset.forName("UTF-8")));

                        break;

                    case "880":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM TPT_SEC_CARTAO_CARTAO_IO;\n");
                            script.append(sb.toString());

                        } else {
                            for (IoCartaoCartao registroDel : list) {
                                sb.append("DELETE FROM TPT_SEC_CARTAO_CARTAO_IO");

                                sb.append(" WHERE ");
                                sb.append("DDD = '").append(registroDel.getDdd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }

                        sb.append("INSERT INTO TPT_SEC_CARTAO_CARTAO_IO");
                        sb.append(" (DDD,RECACUMULADAINICIAL,RECACUMULADAFINAL,OFERTAPROMO1,OFERTAPROMO2) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecacumuladainicial() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecacumuladafinal() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertapromo1() + "',")
                                .replace("VAR" + (i++), "'" + registro.getOfertapromo2() + "'")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .replace("RECACUMULADAINICIAL", "IN_RECACUMULADAINICIAL")
                                        .replace("RECACUMULADAFINAL", "IN_RECACUMULADAFINAL")
                                        .replace("OFERTAPROMO1", "OFERTAPROMO1")
                                        .replace("OFERTAPROMO2", "OFERTAPROMO2")
                                        .getBytes(Charset.forName("UTF-8")));

                        break;
                }
            }

            DefaultStreamedContent defaultStreamedContent = new DefaultStreamedContent();
            defaultStreamedContent.setStream(inputStream);
            defaultStreamedContent.setName("io_cartao_cartao.sql");
            defaultStreamedContent.setContentType("text/plain");

            super.setScriptFile(defaultStreamedContent);

        }
         */
    }

    @Override
    public List<IoCartaoCartaoS2S> getTemplateList() {
        return (List<IoCartaoCartaoS2S>) (Object) super.getTemplateList();
    }

    @Override
    public List<IoCartaoCartaoS2S> getSelectedTemplateList() {
        return (List<IoCartaoCartaoS2S>) (Object) super.getSelectedTemplateList();
    }

    @Override
    public IoCartaoCartaoS2S getNewTemplate() {
        return (IoCartaoCartaoS2S) super.getNewTemplate();
    }

    @Override
    public List<IoCartaoCartaoS2S> getNewTemplateList() {
        return (List<IoCartaoCartaoS2S>) (Object) super.getNewTemplateList();
    }

    @Override
    public IoCartaoCartaoS2S getEditTemplate() {
        return (IoCartaoCartaoS2S) super.getEditTemplate();
    }

    @Override
    public void setTemplateList(List<IoCartaoCartaoS2S> configOfertasPreList) {
        super.setTemplateList(configOfertasPreList);
    }

    @Override
    public void setSelectedTemplateList(List<IoCartaoCartaoS2S> selectedTemplateList) {
        super.setSelectedTemplateList(selectedTemplateList);
    }

    @Override
    public void setNewTemplate(IoCartaoCartaoS2S newTemplate) {
        super.setNewTemplate(newTemplate);
    }

    @Override
    public void setEditTemplate(IoCartaoCartaoS2S editTemplate) {
        super.setEditTemplate(editTemplate);
    }

    public List<IoCartaoCartaoS2S> getSelectedHistoryList() {
        if (super.getSelectedTemplateList() != null && super.getSelectedTemplateList().size() > 0) {
            List<IoCartaoCartaoS2S> history = (List<IoCartaoCartaoS2S>) (Object) super.getSelectedTemplateList().get(0).getHistory();

            for (IoCartaoCartaoS2S bean : history) {
                List<Aprovacao> collect = getAprovacaoList().stream().filter(x -> getDefaultFilterValue().equals(x.getDt_gmudStr())).collect(Collectors.toList());
                if (collect != null && !collect.isEmpty()) {
                    StatusEnum statusTemp = StatusEnum.INICIADO;
                    for (Aprovacao aprovacao : collect) {
                        if (aprovacao.getDt_aprovacao().after(bean.getModified())) {
                            switch (statusTemp) {
                                case EM_IMPLANTACAO:
                                case IMPLANTAR:
                                    break;
                                case EM_UAT:
                                    if (aprovacao.getStatus() == StatusEnum.EM_IMPLANTACAO || aprovacao.getStatus() == StatusEnum.IMPLANTAR) {
                                        statusTemp = aprovacao.getStatus();
                                    }
                                    break;
                                case APROVADO:
                                    if (aprovacao.getStatus() == StatusEnum.EM_IMPLANTACAO || aprovacao.getStatus() == StatusEnum.IMPLANTAR
                                            || aprovacao.getStatus() == StatusEnum.EM_UAT) {
                                        statusTemp = aprovacao.getStatus();
                                    }
                                    break;
                                case VALIDADO:
                                    if (aprovacao.getStatus() == StatusEnum.EM_IMPLANTACAO || aprovacao.getStatus() == StatusEnum.IMPLANTAR
                                            || aprovacao.getStatus() == StatusEnum.EM_UAT || aprovacao.getStatus() == StatusEnum.APROVADO) {
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

    public LazyDataModel<IoCartaoCartaoS2S> getDataModel() {
        return (LazyDataModel<IoCartaoCartaoS2S>) (Object) super.getDataModel();
    }

    public TabelasEnum getTabelasEnum() {
        return tabelasEnum;
    }

    //CUSTOM METHODS////////////////////////////////////////////////////////
    public IoCartaoCartaoS2S getNewTemplate(Integer size, Integer index, String ddd, Date dt_gmud) {
        if (super.getNewTemplateList().size() <= index) {
            for (int i = 0; i < index; i++) {
                if (i == index) {
                    IoCartaoCartaoS2S ioCartaoCartaoS2s = new IoCartaoCartaoS2S();
                    ioCartaoCartaoS2s.setDdd(ddd);
                    ioCartaoCartaoS2s.setDt_gmud(dt_gmud);
                    ioCartaoCartaoS2s.setUser(getUser());
                    super.getNewTemplateList().add(ioCartaoCartaoS2s);

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
            IoCartaoCartaoS2S ioCartaoCartaoS2s = new IoCartaoCartaoS2S();
            ioCartaoCartaoS2s.setDdd(ddd);
            ioCartaoCartaoS2s.setDt_gmud(dt_gmud);
            ioCartaoCartaoS2s.setUser(getUser());
            super.getNewTemplateList().set(index, ioCartaoCartaoS2s);
        } else {
            ((IoCartaoCartaoS2S) super.getNewTemplate(index)).setDdd(ddd);
            ((IoCartaoCartaoS2S) super.getNewTemplate(index)).setDt_gmud(dt_gmud);
            ((IoCartaoCartaoS2S) super.getNewTemplate(index)).setUser(getUser());
        }

        return (IoCartaoCartaoS2S) super.getNewTemplate(index);
    }

    public List<String> getValidTipoBonusS2SList() {
        return validTipoBonusListS2S;
    }

    public boolean isValidTipoBonusValue(String valor, IoCartaoCartaoS2S ioCartaoCartaoS2s) {
        if (valor == null || "".equals(valor)) {
            return true;
        }

        if (valor.equals(ioCartaoCartaoS2s.getSelectedOfertapromo1_1())) {
            return false;
        }
        if (valor.equals(ioCartaoCartaoS2s.getSelectedOfertapromo1_2())) {
            return false;
        }
        if (valor.equals(ioCartaoCartaoS2s.getSelectedOfertapromo1_3())) {
            return false;
        }
        if (valor.equals(ioCartaoCartaoS2s.getSelectedOfertapromo2_1())) {
            return false;
        }
        if (valor.equals(ioCartaoCartaoS2s.getSelectedOfertapromo2_2())) {
            return false;
        }
        if (valor.equals(ioCartaoCartaoS2s.getSelectedOfertapromo2_3())) {
            return false;
        }

        return true;
    }

}
