/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.managedbean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oi.promocoesweb.dbms.entity.User;
import static com.oi.promocoesweb.managedbean.ControleBean.AUTH_USER;
import com.oi.promocoesweb.template.entity.PromptsOfertasPre;
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
public class PromptsOfertasPreBean extends TemplateBean<PromptsOfertasPre> implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private final TabelasEnum tabelasEnum = TabelasEnum.PROMPTS_OFERTASPRE;

    @PostConstruct
    public void init() {
        logger.trace("============= TemplateBean init =============");
        try {
            super.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new PromptsOfertasPre());
            super.clearVarsAdd(new PromptsOfertasPre());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void reloadTabela() {
        try {
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new PromptsOfertasPre());
            super.clearVarsAdd(new PromptsOfertasPre());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void adicionar() {
        super.adicionar(tabelasEnum);
         super.clearVarsAdd(new PromptsOfertasPre());

        if (!isAdicionarOutra()) {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('sidebarAdd').hide();");
        }
    }

    public void editar() {
        try {
            logger.trace("EDIT {} linha(s)!",getSelectedTemplateList().size());
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
            
            super.clearVarsEdit(new PromptsOfertasPre());
            
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
            setEditTemplate(new PromptsOfertasPre(getSelectedTemplateList().get(0)));
            getEditTemplate().setUser(super.getUser());
            setNewTemplate(new PromptsOfertasPre(getSelectedTemplateList().get(0)));
            getNewTemplate().setUser(super.getUser());
        } else {
            setEditTemplate(new PromptsOfertasPre());
            getEditTemplate().setUser(super.getUser());
            setNewTemplate(new PromptsOfertasPre());
            getNewTemplate().setUser(super.getUser());
        }
        if (getSelectedTemplateList().size() > 0) super.setGmudToDelete(getSelectedTemplateList().get(0).getDt_gmud());
        else super.setGmudToDelete(null);
    }

    public void generateScriptFile(String aplicacao) {
        logger.trace("SCRIPT gmud:{}|{}", super.getDefaultFilterValue(), aplicacao);
        StringBuilder script = new StringBuilder();

        List<PromptsOfertasPre> list = null;

        list = getTemplateList().stream().filter(c -> super.getDefaultFilterValue().equals(c.getDtGmudStr())).collect(Collectors.toList());

        if (list != null) {
            StringBuilder sb;

            InputStream inputStream = null;
            int i;
            for (PromptsOfertasPre registro : list) {
                sb = new StringBuilder();

                switch (aplicacao) {
                    case "144":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM TB_PROMPTS_OFERTAOCSPRE;\n");
                            script.append(sb.toString());

                        } else {
                            for (PromptsOfertasPre registroDel : list) {
                                sb.append("DELETE FROM TB_PROMPTS_OFERTAOCSPRE");

                                sb.append(" WHERE ");
                                sb.append("ID_OFERTAOCS = ").append(registroDel.getId_ofertaocs()).append(" AND ");
                                sb.append("DDD = '").append(registroDel.getDdd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }
                        
                        sb.append("INSERT INTO TB_PROMPTS_OFERTAOCSPRE ");
                        sb.append("(ID_OFERTAOCS,DDD,JACLIENTE_OFERTA,JACLIENTE_SVA,DETALHESOFERTA,REGRAROLLOVER,REGRAFALLBACK,OFERTAMIGRADOS,NAOCLIENTE_INFORMAOFERTA,NAOCLIENTE_INCENTIVOOFERTA,INCENTIVOOFERTA) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getId_ofertaocs() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getJacliente_oferta() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getJacliente_sva() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getDetalhesoferta() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRegrarollover() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRegrafallback() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertamigrados() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getNaocliente_informaoferta() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getNaocliente_incentivooferta() + "',")
                                .replace("VAR" + (i++), "'" + registro.getIncentivooferta() + "'")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .replace("INCENTIVOOFERTA", "INCENTIVOOFERTA_SC")
                                        .getBytes(Charset.forName("UTF-8")));

                        break;

                    case "880":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM OCS_PROMPTS_OFERTASPRE;\n");
                            script.append(sb.toString());

                        } else {
                            for (PromptsOfertasPre registroDel : list) {
                                sb.append("DELETE FROM OCS_PROMPTS_OFERTASPRE");

                                sb.append(" WHERE ");
                                sb.append("ID_OFERTAOCS = ").append(registroDel.getId_ofertaocs()).append(" AND ");
                                sb.append("DDD = '").append(registroDel.getDdd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }
                        
                        sb.append("INSERT INTO OCS_PROMPTS_OFERTASPRE ");
                        sb.append("(ID_OFERTAOCS,DDD,JACLIENTE_OFERTA,JACLIENTE_SVA,DETALHESOFERTA,REGRAROLLOVER,REGRAFALLBACK,NAOCLIENTE_INFORMAOFERTA,NAOCLIENTE_INCENTIVOOFERTA,INCENTIVOOFERTA) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getId_ofertaocs() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getJacliente_oferta() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getJacliente_sva() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getDetalhesoferta() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRegrarollover() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRegrafallback() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getNaocliente_informaoferta() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getNaocliente_incentivooferta() + "',")
                                .replace("VAR" + (i++), "'" + registro.getIncentivooferta() + "'")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .replace("INCENTIVOOFERTA", "INCENTIVOOFERTA_SC")
                                        .getBytes(Charset.forName("UTF-8")));

                        break;
                }

            }

            DefaultStreamedContent defaultStreamedContent = new DefaultStreamedContent();
            defaultStreamedContent.setStream(inputStream);
            defaultStreamedContent.setName("prompts_ofertaspre.sql");
            defaultStreamedContent.setContentType("text/plain");

            super.setScriptFile(defaultStreamedContent);

        }
    }
    
    @Override
    public List<PromptsOfertasPre> getTemplateList() {
        return (List<PromptsOfertasPre>) (Object) super.getTemplateList();
    }

    @Override
    public List<PromptsOfertasPre> getSelectedTemplateList() {
        return super.getSelectedTemplateList();
    }

    @Override
    public PromptsOfertasPre getNewTemplate() {
        return super.getNewTemplate();
    }

    @Override
    public PromptsOfertasPre getEditTemplate() {
        return super.getEditTemplate();
    }

    @Override
    public void setTemplateList(List<PromptsOfertasPre> configOfertasPreList) {
        super.setTemplateList(configOfertasPreList);
    }

    @Override
    public void setSelectedTemplateList(List<PromptsOfertasPre> selectedTemplateList) {
        super.setSelectedTemplateList(selectedTemplateList);
    }

    @Override
    public void setNewTemplate(PromptsOfertasPre newTemplate) {
        super.setNewTemplate(newTemplate);
    }

    @Override
    public void setEditTemplate(PromptsOfertasPre editTemplate) {
        super.setEditTemplate(editTemplate);
    }

    public List<PromptsOfertasPre> getSelectedHistoryList() {
        if (super.getSelectedTemplateList() != null && super.getSelectedTemplateList().size() > 0) {
            List<PromptsOfertasPre> history = (List<PromptsOfertasPre>) (Object) super.getSelectedTemplateList().get(0).getHistory();

            for (PromptsOfertasPre bean : history) {
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
    public LazyDataModel<PromptsOfertasPre> getDataModel() {
        return (LazyDataModel<PromptsOfertasPre>) (Object) super.getDataModel();
    }

}
