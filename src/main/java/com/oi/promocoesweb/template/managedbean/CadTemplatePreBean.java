/////*
//// * To change this license header, choose License Headers in Project Properties.
//// * To change this template file, choose Tools | Templates
//// * and open the template in the editor.
//// */
////package com.oi.promocoesweb.template.managedbean;
////
////import com.fasterxml.jackson.core.JsonProcessingException;
////import com.oi.promocoesweb.dbms.entity.User;
////import static com.oi.promocoesweb.managedbean.ControleBean.AUTH_USER;
////import com.oi.promocoesweb.template.entity.CadTemplatePre;
////import com.oi.promocoesweb.template.entity.Template;
////import com.oi.promocoesweb.template.enums.TabelasEnum;
////import com.oi.promocoesweb.template.utils.JsonUtils;
////import java.io.ByteArrayInputStream;
////import java.io.InputStream;
////import java.io.Serializable;
////import java.nio.charset.Charset;
////import java.util.ArrayList;
////import java.util.List;
////import java.util.Map;
////import java.util.stream.Collectors;
////import javax.annotation.PostConstruct;
////import javax.faces.application.FacesMessage;
////import javax.faces.context.FacesContext;
////import javax.faces.view.ViewScoped;
////import javax.inject.Named;
////import org.apache.logging.log4j.LogManager;
////import org.apache.logging.log4j.Logger;
////import org.primefaces.PrimeFaces;
////import org.primefaces.model.DefaultStreamedContent;
////
/////**
//// *
//// * @author mmouraam
//// */
////@Named
////@ViewScoped
////public class CadTemplatePreBean extends TemplateBean<CadTemplatePre> implements Serializable {
////
////    private static final Logger logger = LogManager.getLogger();
////
////    private final TabelasEnum tabelasEnum = TabelasEnum.TB_TEMPLATEPRE;
////    
////    @PostConstruct
////    public void init() {
////        logger.trace("============= TemplateBean init =============");
////        try {
////            super.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
////            super.loadTabela(tabelasEnum);
////            super.clearVarsEdit(new CadTemplatePre());
////            super.clearVarsAdd(new CadTemplatePre());
////
////        } catch (Exception e) {
////            logger.error("", e);
////            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
////            FacesContext.getCurrentInstance().addMessage(null, message);
////        }
////    }
////    
////    public void adicionar() {
////        super.adicionar(tabelasEnum);
////        
////        if (!isAdicionarOutra()) {
////            PrimeFaces current = PrimeFaces.current();
////            current.executeScript("PF('sidebarAdd').hide();");
////        }
////    }
////    
////    public void editar() {
////        try {
////            Map<String, Boolean> collect = getEditConfigOfertasPre().getEditMap().entrySet().stream().filter(x -> x.getValue().equals(Boolean.TRUE)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
////            if (collect == null || collect.isEmpty()) {
////                throw new Exception("Selecione pelo menos um checkbox para edição!");
////            }
////
////            getEditConfigOfertasPre().normalize();
////            for (Template configOfertasPre : getSelectedConfigOfertasPreList()) {
////                configOfertasPre.setHistory(JsonUtils.addToJsonArray(configOfertasPre.getDt_gmud() == null ? null : configOfertasPre.getHistory(), getEditConfigOfertasPre()));
////                logger.trace(JsonUtils.toJson(configOfertasPre.getHistory()));
////            }
////            
////            super.editar(tabelasEnum);
////            
////            super.clearVarsEdit(new CadTemplatePre());
////
////        } catch (JsonProcessingException e) {
////            logger.error("", e);
////            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
////            FacesContext.getCurrentInstance().addMessage(null, message);
////        } catch (Exception e) {
////            logger.error("", e);
////            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
////            FacesContext.getCurrentInstance().addMessage(null, message);
////        }
////
////        PrimeFaces current = PrimeFaces.current();
////        current.executeScript("PF('sidebarEdit').hide();");
////    }
////    
////    public void deletar() {
////        super.deletar(tabelasEnum);
////        
////        PrimeFaces current = PrimeFaces.current();
////        current.executeScript("PF('sidebarDelete').hide();");
////    }
////    
////    public void adiar() {
////        super.adiar(tabelasEnum);
////    }
////    
////    public void aprovar() {
////        super.implantar(tabelasEnum);
////    }
////    
////    public void preLoadEditBean() {
////        if (getSelectedConfigOfertasPreList().size() == 1) {
////            setEditTemplate(new CadTemplatePre(getSelectedConfigOfertasPreList().get(0)));
////            getEditConfigOfertasPre().setUser(super.getUser());
////            setNewTemplate(new CadTemplatePre(getSelectedConfigOfertasPreList().get(0)));
////            getNewConfigOfertasPre().setUser(super.getUser());
////        } else {
////            setEditTemplate(new CadTemplatePre());
////            getEditConfigOfertasPre().setUser(super.getUser());
////            setNewTemplate(new CadTemplatePre());
////            getNewConfigOfertasPre().setUser(super.getUser());
////        }
////        if (getSelectedConfigOfertasPreList().size() > 0) super.setGmudToDelete(getSelectedConfigOfertasPreList().get(0).getDt_gmud());
////    }
////
////    public void generateScriptFile(String aplicacao) {
////        logger.trace("SCRIPT gmud:{}|{}", super.getDefaultFilterValue(), aplicacao);
////        StringBuilder script = new StringBuilder();
////
////        List<CadTemplatePre> list = null;
////
////        list = getConfigOfertasPreList().stream().filter(c -> super.getDefaultFilterValue().equals(c.getDtGmudStr())).collect(Collectors.toList());
////
////        if (list != null) {
////            StringBuilder sb = new StringBuilder();
////
////            if (super.getDefaultFilterValue() == null) {
////                sb.append("DELETE FROM ").append(tabelasEnum.name()).append(";\n");
////                script.append(sb.toString());
////
////            } else {
////                for (CadTemplatePre registro : list) {
////                    sb.append("DELETE FROM ").append(tabelasEnum.name());
////
////                    sb.append(" WHERE ");
////                    sb.append("DDD = '").append(registro.getDdd()).append("';\n");
////                    script.append(sb.toString());
////                }
////            }
////
////            for (CadTemplatePre registro : list) {
////                sb = new StringBuilder();
////
////                sb.append("INSERT INTO TEMPLATE_PRE ");
////                sb.append("(DDD,OFERTA1,OFERTA2,OFERTA3,OFERTA_ERRO_IN,OFERTA_ERRO_SIEBEL,SEGPROMPT,TIPO_BONUS) ");
////                sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8);");
////
////                int i = 1;
////                script.append(sb.toString()
////                        .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
////                        .replace("VAR" + (i++) + ",", "'" + registro.getOferta1()+ "',")
////                        .replace("VAR" + (i++) + ",", "'" + registro.getOferta2()+ "',")
////                        .replace("VAR" + (i++) + ",", "'" + registro.getOferta3()+ "',")
////                        .replace("VAR" + (i++) + ",", "'" + registro.getOferta_erro_in()+ "',")
////                        .replace("VAR" + (i++) + ",", "'" + registro.getOferta_erro_siebel()+ "',")
////                        .replace("VAR" + (i++) + ",", "'" + registro.getSegprompt()+ "',")
////                        .replace("VAR" + (i++) + ",", "'" + registro.getTipo_bonus() + "'")
////                ).append("\n");
////
////            }
////
////            InputStream inputStream = new ByteArrayInputStream(script.toString().getBytes(Charset.forName("UTF-8")));
////
////            DefaultStreamedContent defaultStreamedContent = new DefaultStreamedContent();
////            defaultStreamedContent.setStream(inputStream);
////            defaultStreamedContent.setName("config_ofertaspre.sql");
////            defaultStreamedContent.setContentType("text/plain");
////
////            super.setScriptFile(defaultStreamedContent);
////
////        }
////    }
////    @Override
////    public List<CadTemplatePre> getTemplateList() {
////        return (List<CadTemplatePre>) (Object) super.getTemplateList();
////    }
////    
////    public List<CadTemplatePre> getConfigOfertasPreList() {
////        return (List<CadTemplatePre>) (Object) super.getTemplateList();
////    }
////
////    public List<CadTemplatePre> getSelectedConfigOfertasPreList() {
////        return (List<CadTemplatePre>) (Object) super.getSelectedTemplateList();
////    }
////
////    public CadTemplatePre getNewConfigOfertasPre() {
////        return (CadTemplatePre) super.getNewTemplate();
////    }
////
////    public CadTemplatePre getEditConfigOfertasPre() {
////        return (CadTemplatePre) super.getEditTemplate();
////    }
////    
////    public void setConfigOfertasPreList(List<CadTemplatePre> configOfertasPreList) {
////        super.setTemplateList(configOfertasPreList);
////    }
////
////    public void setSelectedConfigOfertasPreList(List<CadTemplatePre> selectedConfigOfertasPreList) {
////        super.setSelectedTemplateList(selectedConfigOfertasPreList);
////    }
////
////    public void setNewConfigOfertasPre(CadTemplatePre newConfigOfertasPre) {
////        super.setNewTemplate(newConfigOfertasPre);
////    }
////
////    public void setEditConfigOfertasPre(CadTemplatePre editConfigOfertasPre) {
////        super.setEditTemplate(editConfigOfertasPre);
////    }
////
////    public List<CadTemplatePre> getSelectedConfigOfertasPreHistoryList() {
////        if (super.getSelectedTemplateList() != null && super.getSelectedTemplateList().size() > 0) {
////            List<CadTemplatePre> history = (List<CadTemplatePre>) (Object) super.getSelectedTemplateList().get(0).getHistory();
////
////            return history;
////        } else {
////            return new ArrayList<>();
////        }
////    }
////
////}
//;