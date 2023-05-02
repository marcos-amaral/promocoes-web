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
import com.oi.promocoesweb.template.entity.IoControleControle;
import com.oi.promocoesweb.template.entity.Template;
import com.oi.promocoesweb.template.entity.aprovacao.Aprovacao;
import com.oi.promocoesweb.template.enums.StatusEnum;
import com.oi.promocoesweb.template.enums.TabelasEnum;
import com.oi.promocoesweb.template.utils.JsonUtils;
import java.io.InputStream;
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
 * @author jpereirc
 */
@Named
@ViewScoped
public class IoControleControleBean extends TemplateBean<IoControleControle> implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private final TabelasEnum tabelasEnum = TabelasEnum.IO_CONTROLE_CONTROLE;

    private List<String> validTipoPlanoList;

    @PostConstruct
    public void init() {
        logger.trace("============= TemplateBean init =============");
        try {
            super.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new IoControleControle());
            super.clearVarsAdd(new IoControleControle());

            Date dt_gmud_incentivo = (Date) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("dt_gmud_incentivo");
            validTipoPlanoList = TemplateControl.getValidTipoPlano(dt_gmud_incentivo);

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    @Override
    public void reloadGmud() {

        try {
            validTipoPlanoList = TemplateControl.getValidTipoPlano(getNewTemplate().getDt_gmud());

        } catch (Exception e) {
            logger.error(" ", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void reloadEditGmud() {

        try {
            if (getEditTemplate().getDt_gmud() != null) {
                validTipoPlanoList = TemplateControl.getValidTipoPlano(getEditTemplate().getDt_gmud());
            } else if (getDefaultFilterValue() != null && !"PRODUCAO".equals(getDefaultFilterValue())) {
                validTipoPlanoList = TemplateControl.getValidTipoPlano(new SimpleDateFormat("dd/MM/yy").parse(getDefaultFilterValue()));
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
            super.clearVarsEdit(new IoControleControle());
            super.clearVarsAdd(new IoControleControle());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void adicionar() {
        if (super.adicionar(tabelasEnum)) {
            super.clearVarsAdd(new IoControleControle());

            if (!isAdicionarOutra()) {
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('sidebarAdd').hide();");
            }
        }
    }

    public void editar() {

        try {
            Map<String, Boolean> collect = this.getEditTemplate().getEditMap().entrySet().stream().filter(x -> x.getValue().equals(Boolean.TRUE)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            if (collect == null || collect.isEmpty()) {
                throw new Exception("Selecione pelo menos um checkbox para edição!");
            }

            this.getEditTemplate().normalize();
            for (Template configOfertasPre : this.getSelectedTemplateList()) {
                configOfertasPre.setHistory(JsonUtils.addToJsonArray(configOfertasPre.getDt_gmud() == null ? null : configOfertasPre.getHistory(), this.getEditTemplate()));
                logger.trace(JsonUtils.toJson(configOfertasPre.getHistory()));
            }

            if (super.editar(tabelasEnum)) {
                super.clearVarsEdit(new IoControleControle());
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
        if (this.getSelectedTemplateList().size() == 1) {
            this.setEditTemplate(new IoControleControle(this.getSelectedTemplateList().get(0)));
            this.getEditTemplate().setUser(super.getUser());
            this.setNewTemplate(new IoControleControle(this.getSelectedTemplateList().get(0)));
            this.getNewTemplate().setUser(super.getUser());
        } else {
            this.setEditTemplate(new IoControleControle());
            this.getEditTemplate().setUser(super.getUser());
            this.setNewTemplate(new IoControleControle());
            this.getNewTemplate().setUser(super.getUser());
        }
        if (this.getSelectedTemplateList().size() > 0) {
            super.setGmudToDelete(this.getSelectedTemplateList().get(0).getDt_gmud());
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

        List<IoControleControle> list = null;

        list = this.getTemplateList().stream().filter(c -> super.getDefaultFilterValue().equals(c.getDtGmudStr())).collect(Collectors.toList());

        if (list != null) {
            StringBuilder sb;

            InputStream inputStream = null;
            int i;
            for (IoControleControle registro : list) {
                sb = new StringBuilder();

            }
        }
    }

    @Override
    public List<IoControleControle> getTemplateList() {
        return (List<IoControleControle>) (Object) super.getTemplateList();
    }

    @Override
    public List<IoControleControle> getSelectedTemplateList() {
        return (List<IoControleControle>) (Object) super.getSelectedTemplateList();
    }

    @Override
    public IoControleControle getNewTemplate() {
        return (IoControleControle) super.getNewTemplate();
    }

    @Override
    public List<IoControleControle> getNewTemplateList() {
        return (List<IoControleControle>) (Object) super.getNewTemplateList();
    }

    @Override
    public IoControleControle getEditTemplate() {
        return (IoControleControle) super.getEditTemplate();
    }

    public void setTemplatePreList(List<IoControleControle> configOfertasPreList) {
        super.setTemplateList(configOfertasPreList);
    }

    @Override
    public void setSelectedTemplateList(List<IoControleControle> selectedConfigOfertasPreList) {
        super.setSelectedTemplateList(selectedConfigOfertasPreList);
    }

    @Override
    public void setNewTemplate(IoControleControle newConfigOfertasPre) {
        super.setNewTemplate(newConfigOfertasPre);
    }

    @Override
    public void setEditTemplate(IoControleControle editConfigOfertasPre) {
        super.setEditTemplate(editConfigOfertasPre);
    }

    public List<IoControleControle> getSelectedHistoryList() {
        if (super.getSelectedTemplateList() != null && super.getSelectedTemplateList().size() > 0) {
            List<IoControleControle> history = (List<IoControleControle>) (Object) super.getSelectedTemplateList().get(0).getHistory();

            for (IoControleControle bean : history) {
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

    public LazyDataModel<IoControleControle> getDataModel() {
        return (LazyDataModel<IoControleControle>) (Object) super.getDataModel();
    }

    public TabelasEnum getTabelasEnum() {
        return tabelasEnum;
    }

    //CUSTOM METHODS////////////////////////////////////////////////////////
    public IoControleControle getNewTemplate(Integer size, Integer index, String ddd, Date dt_gmud) {
        if (super.getNewTemplateList().size() <= index) {
            for (int i = 0; i < index; i++) {
                if (i == index) {
                    IoControleControle ioControleControle = new IoControleControle();
                    ioControleControle.setDdd(ddd);
                    ioControleControle.setDt_gmud(dt_gmud);
                    ioControleControle.setUser(getUser());
                    super.getNewTemplateList().add(ioControleControle);

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
            IoControleControle ioControleControle = new IoControleControle();
            ioControleControle.setDdd(ddd);
            ioControleControle.setDt_gmud(dt_gmud);
            ioControleControle.setUser(getUser());
            super.getNewTemplateList().set(index, ioControleControle);
        } else {
            ((IoControleControle) super.getNewTemplate(index)).setDdd(ddd);
            ((IoControleControle) super.getNewTemplate(index)).setDt_gmud(dt_gmud);
            ((IoControleControle) super.getNewTemplate(index)).setUser(getUser());
        }

        return (IoControleControle) super.getNewTemplate(index);
    }

    public List<String> getValidTipoPlanoList() {
        return validTipoPlanoList;
    }

    public boolean isValidTipoPlanoValue(String valor, IoControleControle ioControleControle) {

        if (valor == null || "".equals(valor)) {
            return true;
        }

        if (valor.equals(ioControleControle.getTb_incentivo1())) {
            return false;
        }
        if (valor.equals(ioControleControle.getTb_incentivo2())) {
            return false;
        }
        if (valor.equals(ioControleControle.getTb_incentivo3())) {
            return false;
        }
        if (valor.equals(ioControleControle.getTb_incentivo4())) {
            return false;
        }

        return true;
    }

}
