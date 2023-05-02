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
import com.oi.promocoesweb.template.entity.Prompt;
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
public class PromptsBean extends TemplateBean<Prompt> implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private final TabelasEnum tabelasEnum = TabelasEnum.LISTA_PROMPTS;

    @PostConstruct
    public void init() {
        logger.trace("============= TemplateBean init =============");
        try {
            super.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new Prompt());
            super.clearVarsAdd(new Prompt());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void reloadTabela() {
        try {
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new Prompt());
            super.clearVarsAdd(new Prompt());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void adicionar() {
        super.adicionar(tabelasEnum);
        super.clearVarsAdd(new Prompt());

        if (!isAdicionarOutra()) {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('sidebarAdd').hide();");
        }
    }

    public void editar() {
        try {
            Map<String, Boolean> collect = getEditPrompt().getEditMap().entrySet().stream().filter(x -> x.getValue().equals(Boolean.TRUE)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            if (collect == null || collect.isEmpty()) {
                throw new Exception("Selecione pelo menos um checkbox para edição!");
            }

            getEditPrompt().normalize();
            for (Template configOfertasPre : getSelectedPromptList()) {
                configOfertasPre.setHistory(JsonUtils.addToJsonArray(configOfertasPre.getDt_gmud() == null ? null : configOfertasPre.getHistory(), getEditPrompt()));
                logger.trace(JsonUtils.toJson(configOfertasPre.getHistory()));
            }

            super.editar(tabelasEnum);

            super.clearVarsEdit(new Prompt());

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
        if (getSelectedPromptList().size() == 1) {
            setEditTemplate(new Prompt(getSelectedPromptList().get(0)));
            getEditPrompt().setUser(super.getUser());
            setNewTemplate(new Prompt(getSelectedPromptList().get(0)));
            getNewPrompt().setUser(super.getUser());
        } else {
            setEditTemplate(new Prompt());
            getEditPrompt().setUser(super.getUser());
            setNewTemplate(new Prompt());
            getNewPrompt().setUser(super.getUser());
        }
        if (getSelectedPromptList().size() > 0) {
            super.setGmudToDelete(getSelectedPromptList().get(0).getDt_gmud());
        }
        else super.setGmudToDelete(null);
    }

    public void generateScriptFile(String aplicacao) {
        logger.trace("SCRIPT gmud:{}|{}", super.getDefaultFilterValue(), aplicacao);

        try {

            StringBuilder script = new StringBuilder();

            List<Prompt> list = null;

            if ("Todos".equals(aplicacao)) {
                list = TemplateControl.getAllPrompts(-1);
            } else {
                list = super.getSelectedTemplateList();
            }

            if (list != null) {
                InputStream inputStream = null;
                
                for (Prompt registro : list) {
                    script.append(registro.getWav()).append(";").append(registro.getConteudo()).append("\n");
                }
                
                inputStream = new ByteArrayInputStream(script.toString().getBytes(Charset.forName("UTF-8")));

                DefaultStreamedContent defaultStreamedContent = new DefaultStreamedContent();
                defaultStreamedContent.setStream(inputStream);
                defaultStreamedContent.setName("lista_prompts.txt");
                defaultStreamedContent.setContentType("text/plain");

                super.setScriptFile(defaultStreamedContent);

            }
        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    @Override
    public List<Prompt> getTemplateList() {
        return (List<Prompt>) (Object) super.getTemplateList();
    }

    public List<Prompt> getPromptList() {
        return (List<Prompt>) (Object) super.getTemplateList();
    }

    public List<Prompt> getSelectedPromptList() {
        return (List<Prompt>) (Object) super.getSelectedTemplateList();
    }

    public Prompt getNewPrompt() {
        return (Prompt) super.getNewTemplate();
    }

    public Prompt getEditPrompt() {
        return (Prompt) super.getEditTemplate();
    }

    public void setPromptList(List<Prompt> configOfertasPreList) {
        super.setTemplateList(configOfertasPreList);
    }

    public void setSelectedPromptList(List<Prompt> selectedPromptList) {
        super.setSelectedTemplateList(selectedPromptList);
    }

    public void setNewPrompt(Prompt newPrompt) {
        super.setNewTemplate(newPrompt);
    }

    public void setEditPrompt(Prompt editPrompt) {
        super.setEditTemplate(editPrompt);
    }

    public List<Prompt> getSelectedPromptHistoryList() {
        if (super.getSelectedTemplateList() != null && super.getSelectedTemplateList().size() > 0) {
            List<Prompt> history = (List<Prompt>) (Object) super.getSelectedTemplateList().get(0).getHistory();

            for (Prompt bean : history) {
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

    public LazyDataModel<Prompt> getDataModel() {
        return (LazyDataModel<Prompt>) (Object) super.getDataModel();
    }
}
