/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.managedbean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oi.promocoesweb.dbms.entity.User;
import static com.oi.promocoesweb.managedbean.ControleBean.AUTH_USER;
import com.oi.promocoesweb.template.entity.OfertasPortabilidade;
import com.oi.promocoesweb.template.entity.Template;
import com.oi.promocoesweb.template.entity.aprovacao.Aprovacao;
import com.oi.promocoesweb.template.enums.StatusEnum;
import com.oi.promocoesweb.template.enums.TabelasEnum;
import com.oi.promocoesweb.template.utils.JsonUtils;
import java.io.InputStream;
import java.io.Serializable;
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
public class OfertasPortabilidadeBean  extends TemplateBean<OfertasPortabilidade> implements Serializable {
    
    private static final Logger logger = LogManager.getLogger();

    private final TabelasEnum tabelasEnum = TabelasEnum.OFERTAS_PORTABILIDADE;

    @PostConstruct
    public void init() {
        logger.trace("============= TemplateBean init =============");
        try {
            super.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new OfertasPortabilidade());
            super.clearVarsAdd(new OfertasPortabilidade());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void reloadTabela() {
        try {
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new OfertasPortabilidade());
            super.clearVarsAdd(new OfertasPortabilidade());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void adicionar() {
        super.adicionar(tabelasEnum);
        super.clearVarsAdd(new OfertasPortabilidade());

        if (!isAdicionarOutra()) {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('sidebarAdd').hide();");
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

            super.editar(tabelasEnum);
            
            super.clearVarsEdit(new OfertasPortabilidade());

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
        if (this.getSelectedTemplateList().size() == 1) {
            this.setEditTemplate(new OfertasPortabilidade(this.getSelectedTemplateList().get(0)));
            this.getEditTemplate().setUser(super.getUser());
            this.setNewTemplate(new OfertasPortabilidade(this.getSelectedTemplateList().get(0)));
            this.getNewTemplate().setUser(super.getUser());
        } else {
            this.setEditTemplate(new OfertasPortabilidade());
            this.getEditTemplate().setUser(super.getUser());
            this.setNewTemplate(new OfertasPortabilidade());
            this.getNewTemplate().setUser(super.getUser());
        }
        if (this.getSelectedTemplateList().size() > 0) super.setGmudToDelete(this.getSelectedTemplateList().get(0).getDt_gmud());
        else super.setGmudToDelete(null);
    }

    public void generateScriptFile(String aplicacao) {
        logger.trace("SCRIPT gmud:{}|{}", super.getDefaultFilterValue(), aplicacao);
        StringBuilder script = new StringBuilder();

        List<OfertasPortabilidade> list = null;

        list = this.getTemplateList().stream().filter(c -> super.getDefaultFilterValue().equals(c.getDtGmudStr())).collect(Collectors.toList());

        if (list != null) {
            StringBuilder sb;

            InputStream inputStream = null;
            int i;
            for (OfertasPortabilidade registro : list) {
                sb = new StringBuilder();

       }    }
    }


    @Override
    public List<OfertasPortabilidade> getTemplateList() {
        return (List<OfertasPortabilidade>) (Object) super.getTemplateList();
    }

    @Override
    public List<OfertasPortabilidade> getSelectedTemplateList() {
        return (List<OfertasPortabilidade>) (Object) super.getSelectedTemplateList();
    }

    @Override
    public OfertasPortabilidade getNewTemplate() {
        return (OfertasPortabilidade) super.getNewTemplate();
    }

    @Override
    public List<OfertasPortabilidade> getNewTemplateList() {
        return (List<OfertasPortabilidade>) (Object) super.getNewTemplateList();
    }

    @Override
    public OfertasPortabilidade getEditTemplate() {
        return (OfertasPortabilidade) super.getEditTemplate();
    }

    public void setTemplatePreList(List<OfertasPortabilidade> configOfertasPreList) {
        super.setTemplateList(configOfertasPreList);
    }

    @Override
    public void setSelectedTemplateList(List<OfertasPortabilidade> selectedConfigOfertasPreList) {
        super.setSelectedTemplateList(selectedConfigOfertasPreList);
    }

    @Override
    public void setNewTemplate(OfertasPortabilidade newConfigOfertasPre) {
        super.setNewTemplate(newConfigOfertasPre);
    }

    @Override
    public void setEditTemplate(OfertasPortabilidade editConfigOfertasPre) {
        super.setEditTemplate(editConfigOfertasPre);
    }

    public List<OfertasPortabilidade> getSelectedTemplateHistoryList() {
        if (super.getSelectedTemplateList() != null && super.getSelectedTemplateList().size() > 0) {
            List<OfertasPortabilidade> history = (List<OfertasPortabilidade>) (Object) super.getSelectedTemplateList().get(0).getHistory();

            for (OfertasPortabilidade bean : history) {
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
    
    public LazyDataModel<OfertasPortabilidade> getDataModel() {
        return (LazyDataModel<OfertasPortabilidade>) (Object) super.getDataModel();
    }
    
    public TabelasEnum getTabelasEnum() {
        return tabelasEnum;
    }
    
    //CUSTOM METHODS///////////////////////////////////////////////////////
    public OfertasPortabilidade getNewTemplate(Integer size, Integer index, String ddd, String idBeneficio, String idPrograma, Date dt_gmud) {
        if (super.getNewTemplateList().size() <= index) {
            for (int i = 0; i < index; i++) {
                if (i == index) {
                    OfertasPortabilidade ofertasPortabilidade = new OfertasPortabilidade();
                    ofertasPortabilidade.setDdd(ddd);
                    ofertasPortabilidade.setIdbeneficio(idBeneficio);
                    ofertasPortabilidade.setIdprograma(idPrograma);
                    ofertasPortabilidade.setDt_gmud(dt_gmud);
                    ofertasPortabilidade.setUser(getUser());
                    super.getNewTemplateList().add(ofertasPortabilidade);

                } else {
                    super.getNewTemplateList().add(null);
                }
            }
        } else if(super.getNewTemplateList().size() > size){
            do {
                super.getNewTemplateList().remove(super.getNewTemplateList().size()-1);
            } while (super.getNewTemplateList().size() > size);
        }
        
        if (super.getNewTemplate(index) == null) {
            OfertasPortabilidade ofertasPortabilidade = new OfertasPortabilidade();
            ofertasPortabilidade.setDdd(ddd);
            ofertasPortabilidade.setIdbeneficio(idBeneficio);
            ofertasPortabilidade.setIdprograma(idPrograma);
            ofertasPortabilidade.setDt_gmud(dt_gmud);
            ofertasPortabilidade.setUser(getUser());
            super.getNewTemplateList().set(index, ofertasPortabilidade);
        } else {
            ((OfertasPortabilidade) super.getNewTemplate(index)).setDdd(ddd);
            ((OfertasPortabilidade) super.getNewTemplate(index)).setIdbeneficio(idBeneficio);
            ((OfertasPortabilidade) super.getNewTemplate(index)).setIdprograma(idPrograma);
            ((OfertasPortabilidade) super.getNewTemplate(index)).setDt_gmud(dt_gmud);
            ((OfertasPortabilidade) super.getNewTemplate(index)).setUser(getUser());
        }

        return (OfertasPortabilidade) super.getNewTemplate(index);
    }
}
