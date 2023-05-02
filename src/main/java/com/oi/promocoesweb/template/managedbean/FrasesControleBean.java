/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.managedbean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oi.promocoesweb.dbms.entity.User;
import static com.oi.promocoesweb.managedbean.ControleBean.AUTH_USER;
import com.oi.promocoesweb.template.entity.FrasesControle;
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
public class FrasesControleBean extends TemplateBean<FrasesControle> implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private final TabelasEnum tabelasEnum = TabelasEnum.FRASES_CONTROLE;
    private String currentApplication = "USSD";

    @PostConstruct
    public void init() {
        logger.trace("============= TemplateBean init =============");
        try {
            super.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new FrasesControle());
            super.clearVarsAdd(new FrasesControle());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void reloadTabela() {
        try {
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new FrasesControle());
            super.clearVarsAdd(new FrasesControle());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void adicionar() {
        super.adicionar(tabelasEnum);
        super.clearVarsAdd(new FrasesControle());
        
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

            super.clearVarsEdit(new FrasesControle());

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
            this.setEditTemplate(new FrasesControle(this.getSelectedTemplateList().get(0)));
            this.getEditTemplate().setUser(super.getUser());
            this.setNewTemplate(new FrasesControle(this.getSelectedTemplateList().get(0)));
            this.getNewTemplate().setUser(super.getUser());
        } else {
            this.setEditTemplate(new FrasesControle());
            this.getEditTemplate().setUser(super.getUser());
            this.setNewTemplate(new FrasesControle());
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

        List<FrasesControle> list = null;

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
                        for (FrasesControle registroDel : list) {
                            sb.append("DELETE FROM BO_CONTROLE");

                            sb.append(" WHERE ");
                            sb.append("IDBENEFICIO = ").append(registroDel.getIdbeneficio()).append(" AND ");
                            sb.append("IDPROGRAMA = ").append(registroDel.getIdprograma()).append(" AND ");
                            sb.append("DDD = '").append(registroDel.getDdd()).append("';\n");
                            script.append(sb.toString());
                        }
                    }

                    for (FrasesControle registro : list) {
                        sb = new StringBuilder();

                        if (false) {

                            sb.append("INSERT INTO BO_CONTROLE");
                            sb.append("(IDBENEFICIO,IDPROGRAMA,DDD,OICTRLJACLIENTE_PROMOPADRAO,OICTRLJACLIENTE_REGRAS,OICTRLJACLIENTE_VALIDADE,OICTRLJACLIENTE_SALDO,OICTRLJACLIENTE_CONTING,OICTRLNAOCLIENTE_INFORMAPROMO,OICTRLNAOCLIENTE_REGRAS,OICTRLNAOCLIENTE_VALIDADE,OICTRLCNTD_INCNTIV,OICTRLCNTD_INCNTIVPROMO,JACLIENTE_SVA,OICTRLCNTD_INCNTIV_CBOLETO,DETALHES_OFERTARETENCAO,BENINT_ANTDTVENDAURA,BENINT_APOSDTVENDAURA,OICTRLJACLIENTE_FALLBACK,INTERNETEMDOBRO_NAOCLIENTE)");
                            sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16,VAR17,VAR18,VAR19,VAR20);");

                            i = 1;
                            script.append(sb.toString()
                                    .replace("VAR" + (i++) + ",", "'" + registro.getIdbeneficio() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getIdprograma() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getJacliente_promopadrao() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getJacliente_regras() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getJacliente_validade() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getJacliente_saldo() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getJacliente_conting() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getNaocliente_informapromo() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getNaocliente_regras() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getNaocliente_validade() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getConectado_incentivo() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getConectado_incentivopromocao() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getJacliente_sva() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getIncentivo_comboleto() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getDetalhes_ofertaretencao() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getBeneficiointernet_anteriordtvendaura() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getBeneficiointernet_aposdtvendaura() + "',")
                                    .replace("VAR" + (i++) + ",", "'" + registro.getFallback() + "',")
                                    .replace("VAR" + (i++), "'" + registro.getInternetemdobro_naocliente() + "'")
                            ).append("\n");

                        } else {

                            sb.append("UPDATE BO_CONTROLE ");
                            sb.append("SET JACLIENTE_SVA = VAR1 ");
                            sb.append("WHERE IDBENEFICIO = VAR2 AND IDPROGRAMA = VAR3 AND DDD = VAR4;");

                            i = 1;
                            script.append(sb.toString()
                                    .replace("VAR" + (i++), "'" + registro.getJacliente_sva() + "'")
                                    .replace("VAR" + (i++), "'" + registro.getIdbeneficio() + "'")
                                    .replace("VAR" + (i++), "'" + registro.getIdprograma() + "'")
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
    public List<FrasesControle> getNewTemplateList() {
        return (List<FrasesControle>) (Object) super.getNewTemplateList();
    }
    
    @Override
    public List<FrasesControle> getTemplateList() {
        return (List<FrasesControle>) (Object) super.getTemplateList();
    }

    public String getCurrentApplication() {
        return currentApplication;
    }

    public void setCurrentApplication(String currentApplication) {
        this.currentApplication = currentApplication;

    }

    @Override
    public List<FrasesControle> getSelectedTemplateList() {
        return (List<FrasesControle>) (Object) super.getSelectedTemplateList();
    }

    @Override
    public FrasesControle getNewTemplate() {
        return (FrasesControle) super.getNewTemplate();
    }
    
    public FrasesControle getNewTemplate(Integer size, Integer index, String ddd, String idBeneficio, String idPrograma, Date dt_gmud) {
        if (super.getNewTemplateList().size() <= index) {
            for (int i = 0; i < index; i++) {
                if (i == index) {
                    FrasesControle frasesControle = new FrasesControle();
                    frasesControle.setDdd(ddd);
                    frasesControle.setIdbeneficio(idBeneficio);
                    frasesControle.setIdprograma(idPrograma);
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
            FrasesControle frasesControle = new FrasesControle();
            frasesControle.setDdd(ddd);
            frasesControle.setIdbeneficio(idBeneficio);
            frasesControle.setIdprograma(idPrograma);
            frasesControle.setDt_gmud(dt_gmud);
            frasesControle.setUser(getUser());
            super.getNewTemplateList().set(index, frasesControle);
        } else {
            ((FrasesControle) super.getNewTemplate(index)).setDdd(ddd);
            ((FrasesControle) super.getNewTemplate(index)).setIdbeneficio(idBeneficio);
            ((FrasesControle) super.getNewTemplate(index)).setIdprograma(idPrograma);
            ((FrasesControle) super.getNewTemplate(index)).setDt_gmud(dt_gmud);
            ((FrasesControle) super.getNewTemplate(index)).setUser(getUser());
        }

        return (FrasesControle) super.getNewTemplate(index);
    }

    @Override
    public FrasesControle getEditTemplate() {
        return (FrasesControle) super.getEditTemplate();
    }

    public void setTemplatePreList(List<FrasesControle> configOfertasPreList) {
        super.setTemplateList(configOfertasPreList);
    }

    @Override
    public void setSelectedTemplateList(List<FrasesControle> selectedConfigOfertasPreList) {
        super.setSelectedTemplateList(selectedConfigOfertasPreList);
    }

    @Override
    public void setNewTemplate(FrasesControle newConfigOfertasPre) {
        super.setNewTemplate(newConfigOfertasPre);
    }

    @Override
    public void setEditTemplate(FrasesControle editConfigOfertasPre) {
        super.setEditTemplate(editConfigOfertasPre);
    }

    public List<FrasesControle> getSelectedHistoryList() {
        if (super.getSelectedTemplateList() != null && super.getSelectedTemplateList().size() > 0) {
            List<FrasesControle> history = (List<FrasesControle>) (Object) super.getSelectedTemplateList().get(0).getHistory();

            for (FrasesControle bean : history) {
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

    public LazyDataModel<FrasesControle> getDataModel() {
        return (LazyDataModel<FrasesControle>) (Object) super.getDataModel();
    }
}
