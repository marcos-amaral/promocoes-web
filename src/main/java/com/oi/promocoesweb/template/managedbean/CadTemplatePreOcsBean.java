/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.managedbean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oi.promocoesweb.dbms.entity.User;
import static com.oi.promocoesweb.managedbean.ControleBean.AUTH_USER;
import com.oi.promocoesweb.template.entity.CadTemplatePreOcs;
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
public class CadTemplatePreOcsBean extends TemplateBean<CadTemplatePreOcs> implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private final TabelasEnum tabelasEnum = TabelasEnum.TB_TEMPLATE_OCS;
    
    @PostConstruct
    public void init() {
        logger.trace("============= TemplateBean init =============");
        try {
            super.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new CadTemplatePreOcs());
            super.clearVarsAdd(new CadTemplatePreOcs());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void adicionar() {
        super.adicionar(tabelasEnum);
        super.clearVarsAdd(new CadTemplatePreOcs());
        
        if (!isAdicionarOutra()) {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('sidebarAdd').hide();");
        }
    }
    
    public void editar() {
        try {
            Map<String, Boolean> collect = getEditConfigOfertasPre().getEditMap().entrySet().stream().filter(x -> x.getValue().equals(Boolean.TRUE)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            if (collect == null || collect.isEmpty()) {
                throw new Exception("Selecione pelo menos um checkbox para edição!");
            }

            getEditConfigOfertasPre().normalize();
            for (Template configOfertasPre : getSelectedConfigOfertasPreList()) {
                configOfertasPre.setHistory(JsonUtils.addToJsonArray(configOfertasPre.getDt_gmud() == null ? null : configOfertasPre.getHistory(), getEditConfigOfertasPre()));
                logger.trace(JsonUtils.toJson(configOfertasPre.getHistory()));
            }
            
            super.editar(tabelasEnum);
            
            super.clearVarsEdit(new CadTemplatePreOcs());

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
    
    public void preLoadEditBean() {
        if (getSelectedConfigOfertasPreList().size() == 1) {
            setEditTemplate(new CadTemplatePreOcs(getSelectedConfigOfertasPreList().get(0)));
            getEditConfigOfertasPre().setUser(super.getUser());
            setNewTemplate(new CadTemplatePreOcs(getSelectedConfigOfertasPreList().get(0)));
            getNewConfigOfertasPre().setUser(super.getUser());
        } else {
            setEditTemplate(new CadTemplatePreOcs());
            getEditConfigOfertasPre().setUser(super.getUser());
            setNewTemplate(new CadTemplatePreOcs());
            getNewConfigOfertasPre().setUser(super.getUser());
        }
        if (getSelectedConfigOfertasPreList().size() > 0) super.setGmudToDelete(getSelectedConfigOfertasPreList().get(0).getDt_gmud());
        else super.setGmudToDelete(null);
    }

    public void generateScriptFile(String aplicacao) {
        logger.trace("SCRIPT gmud:{}|{}", super.getDefaultFilterValue(), aplicacao);
        StringBuilder script = new StringBuilder();

        List<CadTemplatePreOcs> list = null;

        list = getConfigOfertasPreList().stream().filter(c -> super.getDefaultFilterValue().equals(c.getDtGmudStr())).collect(Collectors.toList());

        if (list != null) {
            StringBuilder sb = new StringBuilder();

            if (super.getDefaultFilterValue() == null) {
                sb.append("DELETE FROM ").append(tabelasEnum.name()).append(";\n");
                script.append(sb.toString());

            } else {
                for (CadTemplatePreOcs registro : list) {
                    sb.append("DELETE FROM ").append(tabelasEnum.name());

                    sb.append(" WHERE ");
                    sb.append("DDD = '").append(registro.getDdd()).append("';\n");
                    script.append(sb.toString());
                }
            }

            for (CadTemplatePreOcs registro : list) {
                sb = new StringBuilder();

                sb.append("INSERT INTO TB_TEMPLATE_OCS ");
                sb.append("(DDD,ID_CAMPANHA_SIEBEL1,ID_OFERTA_SIEBEL1,ID_CAMPANHA_SIEBEL2,ID_OFERTA_SIEBEL2,ID_CAMPANHA_SIEBEL3,ID_OFERTA_SIEBEL3,ID_CAMPANHA_DEFAULT,ID_OFERTA_DEFAULT,MSG_OCS_SVA,TIPO_BONUS) ");
                sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11);");

                int i = 1;
                script.append(sb.toString()
                        .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
                        .replace("VAR" + (i++) + ",", "'" + registro.getId_campanha_siebel1() + "',")
                        .replace("VAR" + (i++) + ",", "'" + registro.getId_oferta_siebel1() + "',")
                        .replace("VAR" + (i++) + ",", "'" + registro.getId_campanha_siebel2() + "',")
                        .replace("VAR" + (i++) + ",", "'" + registro.getId_oferta_siebel2() + "',")
                        .replace("VAR" + (i++) + ",", "'" + registro.getId_campanha_siebel3() + "',")
                        .replace("VAR" + (i++) + ",", "'" + registro.getId_oferta_siebel3() + "',")
                        .replace("VAR" + (i++) + ",", "'" + registro.getId_campanha_default() + "',")
                        .replace("VAR" + (i++) + ",", "'" + registro.getId_oferta_default() + "',")
                        .replace("VAR" + (i++) + ",", "'" + registro.getMsg_ocs_sva() + "',")
                        .replace("VAR" + (i++) + ",", "'" + registro.getTipo_bonus() + "'")
                ).append("\n");

            }

            InputStream inputStream = new ByteArrayInputStream(script.toString().getBytes(Charset.forName("UTF-8")));

            DefaultStreamedContent defaultStreamedContent = new DefaultStreamedContent();
            defaultStreamedContent.setStream(inputStream);
            defaultStreamedContent.setName("config_ofertaspre.sql");
            defaultStreamedContent.setContentType("text/plain");

            super.setScriptFile(defaultStreamedContent);

        }
    }
    @Override
    public List<CadTemplatePreOcs> getTemplateList() {
        return (List<CadTemplatePreOcs>) (Object) super.getTemplateList();
    }
    
    public List<CadTemplatePreOcs> getConfigOfertasPreList() {
        return (List<CadTemplatePreOcs>) (Object) super.getTemplateList();
    }

    public List<CadTemplatePreOcs> getSelectedConfigOfertasPreList() {
        return (List<CadTemplatePreOcs>) (Object) super.getSelectedTemplateList();
    }

    public CadTemplatePreOcs getNewConfigOfertasPre() {
        return (CadTemplatePreOcs) super.getNewTemplate();
    }

    public CadTemplatePreOcs getEditConfigOfertasPre() {
        return (CadTemplatePreOcs) super.getEditTemplate();
    }
    
    public void setConfigOfertasPreList(List<CadTemplatePreOcs> configOfertasPreList) {
        super.setTemplateList(configOfertasPreList);
    }

    public void setSelectedConfigOfertasPreList(List<CadTemplatePreOcs> selectedConfigOfertasPreList) {
        super.setSelectedTemplateList(selectedConfigOfertasPreList);
    }

    public void setNewConfigOfertasPre(CadTemplatePreOcs newConfigOfertasPre) {
        super.setNewTemplate(newConfigOfertasPre);
    }

    public void setEditConfigOfertasPre(CadTemplatePreOcs editConfigOfertasPre) {
        super.setEditTemplate(editConfigOfertasPre);
    }

    public List<CadTemplatePreOcs> getSelectedConfigOfertasPreHistoryList() {
        if (super.getSelectedTemplateList() != null && super.getSelectedTemplateList().size() > 0) {
            List<CadTemplatePreOcs> history = (List<CadTemplatePreOcs>) (Object) super.getSelectedTemplateList().get(0).getHistory();

            for (CadTemplatePreOcs bean : history) {
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

    public LazyDataModel<CadTemplatePreOcs> getDataModel() {
        return (LazyDataModel<CadTemplatePreOcs>) (Object) super.getDataModel();
    }
}
