/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.managedbean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oi.promocoesweb.dbms.entity.User;
import static com.oi.promocoesweb.managedbean.ControleBean.AUTH_USER;
import com.oi.promocoesweb.template.entity.FraseologiasOfertasPre;
import com.oi.promocoesweb.template.entity.Template;
import com.oi.promocoesweb.template.entity.aprovacao.Aprovacao;
import com.oi.promocoesweb.template.enums.StatusEnum;
import com.oi.promocoesweb.template.enums.TabelasEnum;
import com.oi.promocoesweb.template.utils.JsonUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
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
public class FraseologiasOfertasPreBean extends TemplateBean<FraseologiasOfertasPre> implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private final TabelasEnum tabelasEnum = TabelasEnum.FRASEOLOGIAS_OFERTASPRE;
    private String currentApplication = "USSD";

    @PostConstruct
    public void init() {
        logger.trace("============= TemplateBean init =============");
        try {
            super.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new FraseologiasOfertasPre());
            super.clearVarsAdd(new FraseologiasOfertasPre());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void reloadTabela() {
        try {
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new FraseologiasOfertasPre());
            super.clearVarsAdd(new FraseologiasOfertasPre());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void adicionar() {
        super.adicionar(tabelasEnum);
        super.clearVarsAdd(new FraseologiasOfertasPre());

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

            super.clearVarsEdit(new FraseologiasOfertasPre());

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

    public void preLoadEditBean() {
        if (getSelectedTemplateList().size() == 1) {
            setEditTemplate(new FraseologiasOfertasPre(getSelectedTemplateList().get(0)));
            getEditTemplate().setUser(super.getUser());
            setNewTemplate(new FraseologiasOfertasPre(getSelectedTemplateList().get(0)));
            getNewTemplate().setUser(super.getUser());
        } else {
            setEditTemplate(new FraseologiasOfertasPre());
            getEditTemplate().setUser(super.getUser());
            setNewTemplate(new FraseologiasOfertasPre());
            getNewTemplate().setUser(super.getUser());
        }
        if (getSelectedTemplateList().size() > 0) {
            super.setGmudToDelete(getSelectedTemplateList().get(0).getDt_gmud());
        }
        else super.setGmudToDelete(null);
    }

    public void generateScriptFile(String aplicacao) {
        logger.trace("SCRIPT gmud:{}|{}", super.getDefaultFilterValue(), aplicacao);
        try {
            StringBuilder script = new StringBuilder();

            FraseologiasOfertasPre registro = getSelectedTemplateList().get(0);

            script.append(JsonUtils.toJson(registro));

            final InputStream inputStream = new ByteArrayInputStream(script.toString().getBytes(Charset.forName("UTF-8")));

            DefaultStreamedContent defaultStreamedContent = DefaultStreamedContent.builder()
                .name("fraseologias_ofertaspre.json")
                .contentType("application/json")
                .stream(() -> inputStream)
                .build();

            super.setScriptFile(defaultStreamedContent);
        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    @Override
    public List<FraseologiasOfertasPre> getTemplateList() {
        return (List<FraseologiasOfertasPre>) (Object) super.getTemplateList();
    }

    public String getCurrentApplication() {
        return currentApplication;
    }

    public void setCurrentApplication(String currentApplication) {
        this.currentApplication = currentApplication;

    }

    @Override
    public List<FraseologiasOfertasPre> getSelectedTemplateList() {
        return (List<FraseologiasOfertasPre>) (Object) super.getSelectedTemplateList();
    }
    
    public FraseologiasOfertasPre getNewTemplate() {
        return (FraseologiasOfertasPre) super.getNewTemplate();
    }

    public FraseologiasOfertasPre getNewTemplate(Integer size, Integer index, String ddd, String idOfertaOcs, String idBeneficio, String idPrograma, Date dt_gmud) {
        if (super.getNewTemplateList().size() <= index) {
            for (int i = 0; i < index; i++) {
                if (i == index) {
                    FraseologiasOfertasPre configOfertasPre = new FraseologiasOfertasPre();
                    configOfertasPre.setDdd(ddd);
                    configOfertasPre.setId_ofertaocs(idOfertaOcs);
                    configOfertasPre.setId_beneficio(idBeneficio);
                    configOfertasPre.setId_programa(idPrograma);
                    configOfertasPre.setDt_gmud(dt_gmud);
                    configOfertasPre.setUser(getUser());
                    super.getNewTemplateList().add(configOfertasPre);

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
            FraseologiasOfertasPre configOfertasPre = new FraseologiasOfertasPre();
            configOfertasPre.setDdd(ddd);
            configOfertasPre.setId_ofertaocs(idOfertaOcs);
            configOfertasPre.setId_beneficio(idBeneficio);
            configOfertasPre.setId_programa(idPrograma);
            configOfertasPre.setDt_gmud(dt_gmud);
            configOfertasPre.setUser(getUser());
            super.getNewTemplateList().set(index, configOfertasPre);
        } else {
            ((FraseologiasOfertasPre) super.getNewTemplate(index)).setDdd(ddd);
            ((FraseologiasOfertasPre) super.getNewTemplate(index)).setId_ofertaocs(idOfertaOcs);
            ((FraseologiasOfertasPre) super.getNewTemplate(index)).setId_beneficio(idBeneficio);
            ((FraseologiasOfertasPre) super.getNewTemplate(index)).setId_programa(idPrograma);
            ((FraseologiasOfertasPre) super.getNewTemplate(index)).setDt_gmud(dt_gmud);
            ((FraseologiasOfertasPre) super.getNewTemplate(index)).setUser(getUser());
        }

        return (FraseologiasOfertasPre) super.getNewTemplate(index);
    }

    @Override
    public List<FraseologiasOfertasPre> getNewTemplateList() {
        return (List<FraseologiasOfertasPre>) (Object) super.getNewTemplateList();
    }

    public FraseologiasOfertasPre getEditTemplate() {
        return (FraseologiasOfertasPre) super.getEditTemplate();
    }

    public void setTemplateList(List<FraseologiasOfertasPre> configOfertasPreList) {
        super.setTemplateList(configOfertasPreList);
    }

    public void setSelectedTemplateList(List<FraseologiasOfertasPre> selectedTemplateList) {
        super.setSelectedTemplateList(selectedTemplateList);
    }

    public void setNewTemplate(FraseologiasOfertasPre newTemplate) {
        super.setNewTemplate(newTemplate);
    }

    public void setEditTemplate(FraseologiasOfertasPre editTemplate) {
        super.setEditTemplate(editTemplate);
    }

    public List<FraseologiasOfertasPre> getSelectedHistoryList() {
        if (super.getSelectedTemplateList() != null && super.getSelectedTemplateList().size() > 0) {
            List<FraseologiasOfertasPre> history = (List<FraseologiasOfertasPre>) (Object) super.getSelectedTemplateList().get(0).getHistory();

            for (FraseologiasOfertasPre bean : history) {
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

    public TabelasEnum getTabelasEnum() {
        return tabelasEnum;
    }

    public LazyDataModel<FraseologiasOfertasPre> getDataModel() {
        return (LazyDataModel<FraseologiasOfertasPre>) (Object) super.getDataModel();
    }
}
