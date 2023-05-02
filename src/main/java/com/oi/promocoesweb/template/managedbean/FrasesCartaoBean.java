/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.managedbean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oi.promocoesweb.dbms.entity.User;
import static com.oi.promocoesweb.managedbean.ControleBean.AUTH_USER;
import com.oi.promocoesweb.template.entity.FrasesCartao;
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
public class FrasesCartaoBean extends TemplateBean<FrasesCartao> implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private final TabelasEnum tabelasEnum = TabelasEnum.FRASES_CARTAO;
    private String currentApplication = "USSD";

    @PostConstruct
    public void init() {
        logger.trace("============= TemplateBean init =============");
        try {
            super.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new FrasesCartao());
            super.clearVarsAdd(new FrasesCartao());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void reloadTabela() {
        try {
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new FrasesCartao());
            super.clearVarsAdd(new FrasesCartao());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void adicionar() {
        super.adicionar(tabelasEnum);
        super.clearVarsAdd(new FrasesCartao());

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

            super.clearVarsEdit(new FrasesCartao());

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
            setEditTemplate(new FrasesCartao(getSelectedTemplateList().get(0)));
            getEditTemplate().setUser(super.getUser());
            setNewTemplate(new FrasesCartao(getSelectedTemplateList().get(0)));
            getNewTemplate().setUser(super.getUser());
        } else {
            setEditTemplate(new FrasesCartao());
            getEditTemplate().setUser(super.getUser());
            setNewTemplate(new FrasesCartao());
            getNewTemplate().setUser(super.getUser());
        }
        if (getSelectedTemplateList().size() > 0) {
            super.setGmudToDelete(getSelectedTemplateList().get(0).getDt_gmud());
        }
        else super.setGmudToDelete(null);
    }

    public void generateScriptFile(String aplicacao) {
        logger.trace("SCRIPT gmud:{}|{}", super.getDefaultFilterValue(), aplicacao);
        StringBuilder script = new StringBuilder();

        List<FrasesCartao> list = null;

        list = getTemplateList().stream().filter(c -> super.getDefaultFilterValue().equals(c.getDtGmudStr())).collect(Collectors.toList());

        if (list != null) {
            StringBuilder sb;

            InputStream inputStream = null;
            int i;

            sb = new StringBuilder();

            switch (aplicacao) {
                case "USSD":
                    if (super.getDefaultFilterValue() == null) {
                        sb.append("DELETE FROM BO_CARTAO;\n");
                        script.append(sb.toString());

                    } else {
                        for (FrasesCartao registroDel : list) {
                            sb.append("DELETE FROM BO_CARTAO");

                            sb.append(" WHERE ");
                            sb.append("IDBENEFICIO = ").append(registroDel.getIdbeneficio()).append(" AND ");
                            sb.append("IDPROGRAMA = ").append(registroDel.getIdprograma()).append(" AND ");
                            sb.append("DDD = '").append(registroDel.getDdd()).append("';\n");
                            script.append(sb.toString());
                        }
                    }
                    for (FrasesCartao registro : list) {
                        sb = new StringBuilder();
                        
                        if (false) {
                            sb.append("INSERT INTO BO_CARTAO ");
                            sb.append("(IDBENEFICIO,IDPROGRAMA,DDD,PROMOPADRAO_USSD,SEMRECARGA_USSD,CHAMADA_USSD,DETALHES_USSD,RECARGA_USSD,INFORMAVALIDADE_USSD,VALIDADE_USSD,CONTINGENCIA_USSD,NAOCLIENTE_INFORMAPROMO_USSD,NAOCLIENTE_VALIDADE_USSD,REGRAS_USSD,NAOCLIENTE_RECARGA_USSD,INCENTIVO_OFERTA_PRE_USSD,FALLBACK_USSD,SVA_USSD,INFORMASALDOPROMO_USSD,OFERTAMIGRADOS_USSD,INFORMAPROMO_USSD_USSD) ");
                            sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16,VAR17,VAR18,VAR19,VAR20,VAR21);");

                            i = 1;
                            script.append(sb.toString()
                                    .replace("VAR" + (i++) + ",", "'" + registro.getIdbeneficio() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getIdprograma() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getPromopadrao_ussd() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getSemrecarga_ussd() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getChamada_ussd() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getDetalhes_ussd() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getRecarga_ussd() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getInformavalidade_ussd() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getValidade_ussd() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getContingencia_ussd() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getNaocliente_informapromo_ussd() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getNaocliente_validade_ussd() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getRegras_ussd() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getNaocliente_recarga_ussd() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getIncentivo_oferta_pre_ussd() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getFallback_ussd() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getSva_ussd() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getInformasaldopromo_ussd() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getOfertamigrados_ussd() + "',")
                                    .replace("VAR" + (i++), "'" + registro.getInformapromo_ussd_ussd() + "'")
                            ).append("\n");

                        } else {
                            
                            sb.append("UPDATE BO_CARTAO ");
                            sb.append("SET SVA_USSD = VAR1 ");
                            sb.append("WHERE IDBENEFICIO = VAR2 AND IDPROGRAMA = VAR3 AND DDD = VAR4;");

                            i = 1;
                            script.append(sb.toString()
                                    .replace("VAR" + (i++), "'" + registro.getSva_ussd() + "'")
                                    .replace("VAR" + (i++), "'" + registro.getIdbeneficio() + "'")
                                    .replace("VAR" + (i++), "'" + registro.getIdprograma() + "'")
                                    .replace("VAR" + (i++), "'" + registro.getDdd() + "'")
                            ).append("\n");
                        }
                    }
                    
                    inputStream = new ByteArrayInputStream(
                            script.toString()
                                    .replace("PROMOPADRAO_USSD", "CLTPROFALEXCENTS_PROMOPADRAO")
                                    .replace("SEMRECARGA_USSD", "CLTPROFALEXCENTS_SEMRECARGA")
                                    .replace("CHAMADA_USSD", "CLTPROFALEXCENTS_CHAMADA")
                                    .replace("DETALHES_USSD", "CLTPROFALEXCENTS_DETALHES")
                                    .replace("RECARGA_USSD", "CLTPROFALEXCENTS_RECARGA")
                                    .replace("INFORMAVALIDADE_USSD", "CLTPROFALEXCENTS_INFVALIDADE")
                                    .replace("VALIDADE_USSD", "CLTPROFALEXCENTS_VALIDADE")
                                    .replace("CONTINGENCIA_USSD", "CLTPROFALEXCENTS_CONTINGENCIA")
                                    .replace("NAOCLIENTE_INFORMAPROMO_USSD", "NAOCLTPROFALEXCENTS_INFPROMO")
                                    .replace("NAOCLIENTE_VALIDADE_USSD", "NAOCLTPROFALEXCENTS_VALIDADE")
                                    .replace("REGRAS_USSD", "NAOCLTPROFALEXCENTS_REGRAS")
                                    .replace("NAOCLIENTE_RECARGA_USSD", "NAOCLTPROFALEXCENTS_RECARGA")
                                    .replace("INCENTIVO_OFERTA_PRE_USSD", "INCENTIVO_OFERTA_PRE")
                                    .replace("FALLBACK_USSD", "FALLBACK")
                                    .replace("SVA_USSD", "JACLIENTE_SVA")
                                    .replace("INFORMASALDOPROMO_USSD", "INFORMASALDOPROMO")
                                    .replace("OFERTAMIGRADOS_USSD", "OFERTAMIGRADOS")
                                    .replace("INFORMAPROMO_USSD_USSD", "NAOCLIENTEPROMSEM_INFPROM_USSD")
                                    .replace("'null'", "NULL")
                                    .getBytes(Charset.forName("UTF-8")));

                    break;

            }

            DefaultStreamedContent defaultStreamedContent = new DefaultStreamedContent();
            defaultStreamedContent.setStream(inputStream);
            defaultStreamedContent.setName("frases_cartao.sql");
            defaultStreamedContent.setContentType("text/plain");

            super.setScriptFile(defaultStreamedContent);

        }
    }
    
    @Override
    public List<FrasesCartao> getNewTemplateList() {
        return (List<FrasesCartao>) (Object) super.getNewTemplateList();
    }

    @Override
    public List<FrasesCartao> getTemplateList() {
        return (List<FrasesCartao>) (Object) super.getTemplateList();
    }

    public String getCurrentApplication() {
        return currentApplication;
    }

    public void setCurrentApplication(String currentApplication) {
        this.currentApplication = currentApplication;

    }

    @Override
    public LazyDataModel<FrasesCartao> getDataModel() {
        return (LazyDataModel<FrasesCartao>) (Object) super.getDataModel();
    }

    @Override
    public List<FrasesCartao> getSelectedTemplateList() {
        return (List<FrasesCartao>) (Object) super.getSelectedTemplateList();
    }

    @Override
    public FrasesCartao getNewTemplate() {
        return (FrasesCartao) super.getNewTemplate();
    }
    
    public FrasesCartao getNewTemplate(Integer size, Integer index, String ddd, String idBeneficio, String idPrograma, Date dt_gmud) {
        if (super.getNewTemplateList().size() <= index) {
            for (int i = 0; i < index; i++) {
                if (i == index) {
                    FrasesCartao frasesCartao = new FrasesCartao();
                    frasesCartao.setDdd(ddd);
                    frasesCartao.setIdbeneficio(idBeneficio);
                    frasesCartao.setIdprograma(idPrograma);
                    frasesCartao.setDt_gmud(dt_gmud);
                    frasesCartao.setUser(getUser());
                    super.getNewTemplateList().add(frasesCartao);

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
            FrasesCartao frasesCartao = new FrasesCartao();
            frasesCartao.setDdd(ddd);
            frasesCartao.setIdbeneficio(idBeneficio);
            frasesCartao.setIdprograma(idPrograma);
            frasesCartao.setDt_gmud(dt_gmud);
            frasesCartao.setUser(getUser());
            super.getNewTemplateList().set(index, frasesCartao);
        } else {
            ((FrasesCartao) super.getNewTemplate(index)).setDdd(ddd);
            ((FrasesCartao) super.getNewTemplate(index)).setIdbeneficio(idBeneficio);
            ((FrasesCartao) super.getNewTemplate(index)).setIdprograma(idPrograma);
            ((FrasesCartao) super.getNewTemplate(index)).setDt_gmud(dt_gmud);
            ((FrasesCartao) super.getNewTemplate(index)).setUser(getUser());
        }

        return (FrasesCartao) super.getNewTemplate(index);
    }

    @Override
    public FrasesCartao getEditTemplate() {
        return (FrasesCartao) super.getEditTemplate();
    }

    @Override
    public void setTemplateList(List<FrasesCartao> configOfertasPreList) {
        super.setTemplateList(configOfertasPreList);
    }

    @Override
    public void setSelectedTemplateList(List<FrasesCartao> selectedTemplateList) {
        super.setSelectedTemplateList(selectedTemplateList);
    }

    @Override
    public void setNewTemplate(FrasesCartao newTemplate) {
        super.setNewTemplate(newTemplate);
    }

    @Override
    public void setEditTemplate(FrasesCartao editTemplate) {
        super.setEditTemplate(editTemplate);
    }

    public List<FrasesCartao> getSelectedHistoryList() {
        if (super.getSelectedTemplateList() != null && super.getSelectedTemplateList().size() > 0) {
            List<FrasesCartao> history = (List<FrasesCartao>) (Object) super.getSelectedTemplateList().get(0).getHistory();

            for (FrasesCartao bean : history) {
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

}
