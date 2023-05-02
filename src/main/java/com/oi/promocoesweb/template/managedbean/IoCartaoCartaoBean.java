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
public class IoCartaoCartaoBean extends TemplateBean<IoCartaoCartao> implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private final TabelasEnum tabelasEnum = TabelasEnum.IO_CARTAO_CARTAO;

    private List<String> validTipoBonusList;

    @PostConstruct
    public void init() {
        logger.trace("============= TemplateBean init =============");
        try {
            super.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new IoCartaoCartao());
            super.clearVarsAdd(new IoCartaoCartao());

            Date dt_gmud_incentivo = (Date) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("dt_gmud_incentivo");
            validTipoBonusList = TemplateControl.getValidTipoBonus(dt_gmud_incentivo);

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    @Override
    public void reloadGmud() {

        try {

            validTipoBonusList = TemplateControl.getValidTipoBonus(getNewTemplate().getDt_gmud());

        } catch (Exception e) {
            logger.error(" ", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void reloadEditGmud() {

        try {
            if (getEditTemplate().getDt_gmud() != null) {
                validTipoBonusList = TemplateControl.getValidTipoBonus(getEditTemplate().getDt_gmud());
            } else if (getDefaultFilterValue() != null && !"PRODUCAO".equals(getDefaultFilterValue())) {
                validTipoBonusList = TemplateControl.getValidTipoBonus(new SimpleDateFormat("dd/MM/yy").parse(getDefaultFilterValue()));
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
            super.clearVarsEdit(new IoCartaoCartao());
            super.clearVarsAdd(new IoCartaoCartao());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void adicionar() {
        if (super.adicionar(tabelasEnum)) {
            super.clearVarsAdd(new IoCartaoCartao());

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
                super.clearVarsEdit(new IoCartaoCartao());
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
            setEditTemplate(new IoCartaoCartao(getSelectedTemplateList().get(0)));
            getEditTemplate().setUser(super.getUser());
            setNewTemplate(new IoCartaoCartao(getSelectedTemplateList().get(0)));
            getNewTemplate().setUser(super.getUser());
        } else {
            setEditTemplate(new IoCartaoCartao());
            getEditTemplate().setUser(super.getUser());
            setNewTemplate(new IoCartaoCartao());
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
    }

    @Override
    public List<IoCartaoCartao> getTemplateList() {
        return (List<IoCartaoCartao>) (Object) super.getTemplateList();
    }

    @Override
    public List<IoCartaoCartao> getSelectedTemplateList() {
        return (List<IoCartaoCartao>) (Object) super.getSelectedTemplateList();
    }

    @Override
    public IoCartaoCartao getNewTemplate() {
        return (IoCartaoCartao) super.getNewTemplate();
    }

    @Override
    public List<IoCartaoCartao> getNewTemplateList() {
        return (List<IoCartaoCartao>) (Object) super.getNewTemplateList();
    }

    @Override
    public IoCartaoCartao getEditTemplate() {
        return (IoCartaoCartao) super.getEditTemplate();
    }

    @Override
    public void setTemplateList(List<IoCartaoCartao> configOfertasPreList) {
        super.setTemplateList(configOfertasPreList);
    }

    @Override
    public void setSelectedTemplateList(List<IoCartaoCartao> selectedTemplateList) {
        super.setSelectedTemplateList(selectedTemplateList);
    }

    @Override
    public void setNewTemplate(IoCartaoCartao newTemplate) {
        super.setNewTemplate(newTemplate);
    }

    @Override
    public void setEditTemplate(IoCartaoCartao editTemplate) {
        super.setEditTemplate(editTemplate);
    }

    public List<IoCartaoCartao> getSelectedHistoryList() {
        if (super.getSelectedTemplateList() != null && super.getSelectedTemplateList().size() > 0) {
            List<IoCartaoCartao> history = (List<IoCartaoCartao>) (Object) super.getSelectedTemplateList().get(0).getHistory();

            for (IoCartaoCartao bean : history) {
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

    @Override
    public LazyDataModel<IoCartaoCartao> getDataModel() {
        return (LazyDataModel<IoCartaoCartao>) (Object) super.getDataModel();
    }

    public TabelasEnum getTabelasEnum() {
        return tabelasEnum;
    }

    //CUSTOM METHODS////////////////////////////////////////////////////////
    public IoCartaoCartao getNewTemplate(Integer size, Integer index, String ddd, Date dt_gmud) {
        if (super.getNewTemplateList().size() <= index) {
            for (int i = 0; i < index; i++) {
                if (i == index) {
                    IoCartaoCartao ioCartaoCartao = new IoCartaoCartao();
                    ioCartaoCartao.setDdd(ddd);
                    ioCartaoCartao.setDt_gmud(dt_gmud);
                    ioCartaoCartao.setUser(getUser());
                    super.getNewTemplateList().add(ioCartaoCartao);

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
            IoCartaoCartao ioCartaoCartao = new IoCartaoCartao();
            ioCartaoCartao.setDdd(ddd);
            ioCartaoCartao.setDt_gmud(dt_gmud);
            ioCartaoCartao.setUser(getUser());
            super.getNewTemplateList().set(index, ioCartaoCartao);
        } else {
            ((IoCartaoCartao) super.getNewTemplate(index)).setDdd(ddd);
            ((IoCartaoCartao) super.getNewTemplate(index)).setDt_gmud(dt_gmud);
            ((IoCartaoCartao) super.getNewTemplate(index)).setUser(getUser());
        }

        return (IoCartaoCartao) super.getNewTemplate(index);
    }

    public List<String> getValidTipoBonusList() {
        return validTipoBonusList;
    }

    public boolean isValidTipoBonusValue(String valor, IoCartaoCartao ioCartaoCartao) {
        if (valor == null || "".equals(valor)) {
            return true;
        }

        if (valor.equals(ioCartaoCartao.getSelectedOfertapromo1_1())) {
            return false;
        }
        if (valor.equals(ioCartaoCartao.getSelectedOfertapromo1_2())) {
            return false;
        }
        if (valor.equals(ioCartaoCartao.getSelectedOfertapromo1_3())) {
            return false;
        }
        if (valor.equals(ioCartaoCartao.getSelectedOfertapromo2_1())) {
            return false;
        }
        if (valor.equals(ioCartaoCartao.getSelectedOfertapromo2_2())) {
            return false;
        }
        if (valor.equals(ioCartaoCartao.getSelectedOfertapromo2_3())) {
            return false;
        }

        return true;
    }

}
