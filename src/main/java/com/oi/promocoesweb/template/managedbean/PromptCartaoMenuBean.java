/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.managedbean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oi.promocoesweb.dbms.entity.User;
import static com.oi.promocoesweb.managedbean.ControleBean.AUTH_USER;
import com.oi.promocoesweb.template.entity.PromptCartaoMenu;
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
public class PromptCartaoMenuBean extends TemplateBean<PromptCartaoMenu> implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private final TabelasEnum tabelasEnum = TabelasEnum.PROMPT_CARTAOMENU;
    private String currentApplication = "USSD";

    @PostConstruct
    public void init() {
        logger.trace("============= TemplateBean init =============");
        try {
            super.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new PromptCartaoMenu());
            super.clearVarsAdd(new PromptCartaoMenu());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void reloadTabela() {
        try {
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new PromptCartaoMenu());
            super.clearVarsAdd(new PromptCartaoMenu());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void adicionar() {
        super.adicionar(tabelasEnum);
        super.clearVarsAdd(new PromptCartaoMenu());

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
            
            super.clearVarsEdit(new PromptCartaoMenu());

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
        if (getSelectedConfigOfertasPreList().size() == 1) {
            setEditTemplate(new PromptCartaoMenu(getSelectedConfigOfertasPreList().get(0)));
            getEditConfigOfertasPre().setUser(super.getUser());
            setNewTemplate(new PromptCartaoMenu(getSelectedConfigOfertasPreList().get(0)));
            getNewConfigOfertasPre().setUser(super.getUser());
        } else {
            setEditTemplate(new PromptCartaoMenu());
            getEditConfigOfertasPre().setUser(super.getUser());
            setNewTemplate(new PromptCartaoMenu());
            getNewConfigOfertasPre().setUser(super.getUser());
        }
        if (getSelectedConfigOfertasPreList().size() > 0) super.setGmudToDelete(getSelectedConfigOfertasPreList().get(0).getDt_gmud());
        else super.setGmudToDelete(null);
    }

    public void generateScriptFile(String aplicacao) {
        logger.trace("SCRIPT gmud:{}|{}", super.getDefaultFilterValue(), aplicacao);
        StringBuilder script = new StringBuilder();

        List<PromptCartaoMenu> list = null;

        list = getConfigOfertasPreList().stream().filter(c -> super.getDefaultFilterValue().equals(c.getDtGmudStr())).collect(Collectors.toList());

        if (list != null) {
            StringBuilder sb;

            InputStream inputStream = null;
            int i;
            for (PromptCartaoMenu registro : list) {
                sb = new StringBuilder();

                switch (aplicacao) {
                    case "USSD":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM TB_CARTMENU;\n");
                            script.append(sb.toString());

                        } else {
                            for (PromptCartaoMenu registroDel : list) {
                                sb.append("DELETE FROM TB_CARTMENU");

                                sb.append(" WHERE ");
                                sb.append("TB_ID_BENEFICIO = ").append(registroDel.getTb_id_beneficio()).append(" AND ");
                                sb.append("TB_ID_PROGRAMA = ").append(registroDel.getTb_id_programa()).append(" AND ");
                                sb.append("TB_DDD = '").append(registroDel.getTb_ddd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }

                        sb.append("INSERT INTO TB_CARTMENU");
                        sb.append(" (TB_ID_BENEFICIO,TB_ID_PROGRAMA,TB_DDD,CLIENTE_MENSAGEMINICIAL_USSD,CLIENTE_REGRATORPEDO_USSD,CLIENTE_REGRALIGACAO_USSD,CLIENTE_REGRADADOS_USSD,CLIENTE_SVA_USSD,CLIENTE_COMOUSAR_USSD,REGRALIGACAOOIMOVEL_USSD,REGRALIGACAOOIMOVELFIXO_USSD,REGRALIGACAOMIX_USSD,RECEBEBONUSINICIOPROMODEGRAU_USSD,RECEBEBONUSMAX_USSD,RECEBEBONUSMINDEGRAU2_USSD,RECEBEBONUSMINDEGRAU3_USSD,RECEBEBONUSMINDEGRAU2E3_USSD,RECEBEBONUSMAX3_USSD,NAOCLIENTE_MENSAGEMINICIAL_USSD,NAOCLIENTE_REGRATORPEDO_USSD,NAOCLIENTE_REGRALIGACAO_USSD,NAOCLIENTE_COMOUSAR_USSD,NAOCLIENTE_VALIDADEPROMO_USSD,NAOCLIENTE_REGRADADOS_USSD,PROMOOICARTAO_USSD,OFERTA_PRE_USSD) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16,VAR17,VAR18,VAR19,VAR20,VAR21,VAR22,VAR23,VAR24,VAR25,VAR26);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_beneficio() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_programa() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_ddd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCliente_mensageminicial_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCliente_regratorpedo_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCliente_regraligacao_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCliente_regradados_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCliente_sva_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCliente_comousar_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRegraligacaooimovel_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRegraligacaooimovelfixo_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRegraligacaomix_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecebebonusiniciopromodegrau_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecebebonusmax_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecebebonusmindegrau2_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecebebonusmindegrau3_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecebebonusmindegrau2e3_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecebebonusmax3_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getNaocliente_mensageminicial_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getNaocliente_regratorpedo_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getNaocliente_regraligacao_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getNaocliente_comousar_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getNaocliente_validadepromo_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getNaocliente_regradados_ussd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getPromooicartao_ussd() + "',")
                                .replace("VAR" + (i++)      , "'" + registro.getOferta_pre_ussd() + "'")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .replace("TB_ID_BENEFICIO", "TBIDBENEFICIO")
                                        .replace("TB_ID_PROGRAMA", "TBIDPROGRAMA")
                                        .replace("TB_DDD", "TBDDD")
                                        .replace("CLIENTE_MENSAGEMINICIAL_USSD", "OICRTPRMPDRMSGINI")
                                        .replace("CLIENTE_REGRATORPEDO_USSD", "OICRTPRMPDRREGRATORPEDO")
                                        .replace("CLIENTE_REGRALIGACAO_USSD", "OICRTPRMPDRREGRALGAO")
                                        .replace("CLIENTE_REGRADADOS_USSD", "OICRTPROMOPDRREGRADADOS")
                                        .replace("CLIENTE_SVA_USSD", "JACLIENTESVA")
                                        .replace("CLIENTE_COMOUSAR_USSD", "OICRTPRMPDRCOMOUSAR")
                                        .replace("REGRALIGACAOOIMOVEL_USSD", "OICRTPRMPDRCONTRGRLGAOOIMVL")
                                        .replace("REGRALIGACAOOIMOVELFIXO_USSD", "OICRTPRMPDRCONTRGRLGAOOIMVLFX")
                                        .replace("REGRALIGACAOMIX_USSD", "OICRTPRMPDRCONTRGRLGAOMIX")
                                        .replace("RECEBEBONUSINICIOPROMODEGRAU_USSD", "OICRTRCMTBNSDTD5ADDGRINIT2")
                                        .replace("RECEBEBONUSMAX_USSD", "OICRTRCMTBNSDTD5DGRMXINIT2")
                                        .replace("RECEBEBONUSMINDEGRAU2_USSD", "OICRTRCMTBNSDTD5DGRMNDGR2I2")
                                        .replace("RECEBEBONUSMINDEGRAU3_USSD", "OICRTRCMTBNSDTD5DGRMNDGR3I2")
                                        .replace("RECEBEBONUSMINDEGRAU2E3_USSD", "OICRTRCMTBNSDTD5DGRMNDGR2E3I2")
                                        .replace("RECEBEBONUSMAX3_USSD", "OICRTRCMTBNSDTD5DGRMX3I2")
                                        .replace("NAOCLIENTE_MENSAGEMINICIAL_USSD", "OICRTINFORMAPROMMSGINI")
                                        .replace("NAOCLIENTE_REGRATORPEDO_USSD", "OICRTINFORMAPROMREGRATORPEDO")
                                        .replace("NAOCLIENTE_REGRALIGACAO_USSD", "OICRTINFORMAPROMREGRALGAO")
                                        .replace("NAOCLIENTE_COMOUSAR_USSD", "OICRTINFORMAPROMCOMOUSAR")
                                        .replace("NAOCLIENTE_VALIDADEPROMO_USSD", "OICRTINFORMAPROMVALIDADEPROM")
                                        .replace("NAOCLIENTE_REGRADADOS_USSD", "OICRTINFORMAPROMOREGRADADOS")
                                        .replace("PROMOOICARTAO_USSD", "OICRTCTRLPROMOTRPLNPROMOICRT")
                                        .replace("OFERTA_PRE_USSD", "INCENTIVOOFERTAPRE")
                                        .getBytes(Charset.forName("UTF-8")));

                        break;
                }
            }

            DefaultStreamedContent defaultStreamedContent = new DefaultStreamedContent();
            defaultStreamedContent.setStream(inputStream);
            defaultStreamedContent.setName("prompt_cartaomenu.sql");
            defaultStreamedContent.setContentType("text/plain");

            super.setScriptFile(defaultStreamedContent);

        }
    }
    
    @Override
    public List<PromptCartaoMenu> getTemplateList() {
        return (List<PromptCartaoMenu>) (Object) super.getTemplateList();
    }

    
    public String getCurrentApplication() {
        return currentApplication;
    }

    public void setCurrentApplication(String currentApplication) {
        this.currentApplication = currentApplication;

    }

    public List<PromptCartaoMenu> getConfigOfertasPreList() {
        return (List<PromptCartaoMenu>) (Object) super.getTemplateList();
    }

    public List<PromptCartaoMenu> getSelectedConfigOfertasPreList() {
        return (List<PromptCartaoMenu>) (Object) super.getSelectedTemplateList();
    }

    public PromptCartaoMenu getNewConfigOfertasPre() {
        return (PromptCartaoMenu) super.getNewTemplate();
    }

    public PromptCartaoMenu getEditConfigOfertasPre() {
        return (PromptCartaoMenu) super.getEditTemplate();
    }

    public void setConfigOfertasPreList(List<PromptCartaoMenu> configOfertasPreList) {
        super.setTemplateList(configOfertasPreList);
    }

    public void setSelectedConfigOfertasPreList(List<PromptCartaoMenu> selectedConfigOfertasPreList) {
        super.setSelectedTemplateList(selectedConfigOfertasPreList);
    }

    public void setNewConfigOfertasPre(PromptCartaoMenu newConfigOfertasPre) {
        super.setNewTemplate(newConfigOfertasPre);
    }

    public void setEditConfigOfertasPre(PromptCartaoMenu editConfigOfertasPre) {
        super.setEditTemplate(editConfigOfertasPre);
    }

    public List<PromptCartaoMenu> getSelectedConfigOfertasPreHistoryList() {
        if (super.getSelectedTemplateList() != null && super.getSelectedTemplateList().size() > 0) {
            List<PromptCartaoMenu> history = (List<PromptCartaoMenu>) (Object) super.getSelectedTemplateList().get(0).getHistory();

            for (PromptCartaoMenu bean : history) {
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

    public LazyDataModel<PromptCartaoMenu> getDataModel() {
        return (LazyDataModel<PromptCartaoMenu>) (Object) super.getDataModel();
    }
    
}
