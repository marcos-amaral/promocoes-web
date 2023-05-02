/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.managedbean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oi.promocoesweb.dbms.entity.User;
import static com.oi.promocoesweb.managedbean.ControleBean.AUTH_USER;
import com.oi.promocoesweb.template.entity.FrasesControleCad;
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
 * @author jpereirc
 */
@Named
@ViewScoped
public class FrasesControleCadBean extends TemplateBean<FrasesControleCad> implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private final TabelasEnum tabelasEnum = TabelasEnum.FRASES_CONTROLE_CAD;
    private String currentApplication = "USSD";

    @PostConstruct
    public void init() {
        logger.trace("============= TemplateBean init =============");
        try {
            super.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new FrasesControleCad());
            super.clearVarsAdd(new FrasesControleCad());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void reloadTabela() {
        try {
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new FrasesControleCad());
            super.clearVarsAdd(new FrasesControleCad());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void adicionar() {
        super.adicionar(tabelasEnum);
        super.clearVarsAdd(new FrasesControleCad());

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

            super.clearVarsEdit(new FrasesControleCad());

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
            this.setEditTemplate(new FrasesControleCad(this.getSelectedTemplateList().get(0)));
            this.getEditTemplate().setUser(super.getUser());
            this.setNewTemplate(new FrasesControleCad(this.getSelectedTemplateList().get(0)));
            this.getNewTemplate().setUser(super.getUser());
        } else {
            this.setEditTemplate(new FrasesControleCad());
            this.getEditTemplate().setUser(super.getUser());
            this.setNewTemplate(new FrasesControleCad());
            this.getNewTemplate().setUser(super.getUser());
        }
        if (this.getSelectedTemplateList().size() > 0) {
            super.setGmudToDelete(this.getSelectedTemplateList().get(0).getDt_gmud());
        }
        else super.setGmudToDelete(null);
    }

    public void generateScriptFile(String aplicacao) {
        logger.trace("SCRIPT gmud:{}|{}", super.getDefaultFilterValue(), aplicacao);
        StringBuilder script = new StringBuilder();

        List<FrasesControleCad> list = null;

        list = this.getTemplateList().stream().filter(c -> super.getDefaultFilterValue().equals(c.getDtGmudStr())).collect(Collectors.toList());

        if (list != null) {
            StringBuilder sb;

            InputStream inputStream = null;
            int i;

            sb = new StringBuilder();

            switch (aplicacao) {
                case "USSD":
                    if (super.getDefaultFilterValue() == null) {
                        sb.append("DELETE FROM BO_CONTROLE;\n");
                        script.append(sb.toString());

                    } else {
                        for (FrasesControleCad registroDel : list) {
                            sb.append("DELETE FROM BO_CONTROLE");

                            sb.append(" WHERE ");
                            sb.append("DDD = '").append(registroDel.getDdd()).append("';\n");
                            script.append(sb.toString());
                        }
                    }

                    for (FrasesControleCad registro : list) {
                        sb = new StringBuilder();

                        if (false) {

                            sb.append("INSERT INTO BO_CONTROLE");
                            sb.append("(IDBENEFICIO,IDPROGRAMA,DDD,OICTRLJACLIENTE_PROMOPADRAO,OICTRLJACLIENTE_REGRAS,OICTRLJACLIENTE_VALIDADE,OICTRLJACLIENTE_SALDO,OICTRLJACLIENTE_CONTING,OICTRLNAOCLIENTE_INFORMAPROMO,OICTRLNAOCLIENTE_REGRAS,OICTRLNAOCLIENTE_VALIDADE,OICTRLCNTD_INCNTIV,OICTRLCNTD_INCNTIVPROMO,JACLIENTE_SVA,OICTRLCNTD_INCNTIV_CBOLETO,DETALHES_OFERTARETENCAO,BENINT_ANTDTVENDAURA,BENINT_APOSDTVENDAURA,OICTRLJACLIENTE_FALLBACK,INTERNETEMDOBRO_NAOCLIENTE)");
                            sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16,VAR17,VAR18,VAR19,VAR20);");

                            i = 1;
                            script.append(sb.toString()
                                    .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getOferta_cadastro() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getOferta_cadastro_conteudo() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getOferta_escolhida() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getOferta_escolhida_conteudo() + "',")
                            ).append("\n");

                        } else {

                            sb.append("UPDATE BO_CONTROLE ");
                            sb.append("SET JACLIENTE_SVA = VAR1 ");
                            sb.append("WHERE DDD = VAR4;");

                            i = 1;
                            script.append(sb.toString()
                                    .replace("VAR" + (i++), "'" + registro.getDdd() + "'")
                            ).append("\n");
                        }
                    }

                    inputStream = new ByteArrayInputStream(
                            script.toString()
                                    .replace("'null'", "NULL")
                                    .getBytes(Charset.forName("UTF-8")));

                    break;
            }

            DefaultStreamedContent defaultStreamedContent = new DefaultStreamedContent();
            defaultStreamedContent.setStream(inputStream);
            defaultStreamedContent.setName("frases_controle.sql");
            defaultStreamedContent.setContentType("text/plain");

            super.setScriptFile(defaultStreamedContent);

        }
    }

    @Override
    public List<FrasesControleCad> getNewTemplateList() {
        return (List<FrasesControleCad>) (Object) super.getNewTemplateList();
    }
    
    @Override
    public List<FrasesControleCad> getTemplateList() {
        return (List<FrasesControleCad>) (Object) super.getTemplateList();
    }

    public String getCurrentApplication() {
        return currentApplication;
    }

    public void setCurrentApplication(String currentApplication) {
        this.currentApplication = currentApplication;

    }

    @Override
    public List<FrasesControleCad> getSelectedTemplateList() {
        return (List<FrasesControleCad>) (Object) super.getSelectedTemplateList();
    }

    @Override
    public FrasesControleCad getNewTemplate() {
        return (FrasesControleCad) super.getNewTemplate();
    }
    
    @Override
    public FrasesControleCad getEditTemplate() {
        return (FrasesControleCad) super.getEditTemplate();
    }

    public void setTemplatePreList(List<FrasesControleCad> configOfertasPreList) {
        super.setTemplateList(configOfertasPreList);
    }

    @Override
    public void setSelectedTemplateList(List<FrasesControleCad> selectedConfigOfertasPreList) {
        super.setSelectedTemplateList(selectedConfigOfertasPreList);
    }

    @Override
    public void setNewTemplate(FrasesControleCad newConfigOfertasPre) {
        super.setNewTemplate(newConfigOfertasPre);
    }

    @Override
    public void setEditTemplate(FrasesControleCad editConfigOfertasPre) {
        super.setEditTemplate(editConfigOfertasPre);
    }

    public List<FrasesControleCad> getSelectedTemplateHistoryList() {
        if (super.getSelectedTemplateList() != null && super.getSelectedTemplateList().size() > 0) {
            List<FrasesControleCad> history = (List<FrasesControleCad>) (Object) super.getSelectedTemplateList().get(0).getHistory();

            for (FrasesControleCad bean : history) {
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

    public TabelasEnum getTabelasEnum() {
        return tabelasEnum;
    }

    public LazyDataModel<FrasesControleCad> getDataModel() {
        return (LazyDataModel<FrasesControleCad>) (Object) super.getDataModel();
    }
    
    //CUSTOM METHODS//////////////////////////////////////////////////////
    public FrasesControleCad getNewTemplate(Integer size, Integer index, String ddd, Date dt_gmud) {
        if (super.getNewTemplateList().size() <= index) {
            for (int i = 0; i < index; i++) {
                if (i == index) {
                    FrasesControleCad frasesControle = new FrasesControleCad();
                    frasesControle.setDdd(ddd);
                    frasesControle.setDt_gmud(dt_gmud);
                    frasesControle.setUser(getUser());
                    super.getNewTemplateList().add(frasesControle);

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
            FrasesControleCad frasesControle = new FrasesControleCad();
            frasesControle.setDdd(ddd);
            frasesControle.setDt_gmud(dt_gmud);
            frasesControle.setUser(getUser());
            super.getNewTemplateList().set(index, frasesControle);
        } else {
            ((FrasesControleCad) super.getNewTemplate(index)).setDdd(ddd);
            ((FrasesControleCad) super.getNewTemplate(index)).setDt_gmud(dt_gmud);
            ((FrasesControleCad) super.getNewTemplate(index)).setUser(getUser());
        }

        return (FrasesControleCad) super.getNewTemplate(index);
    }
}
