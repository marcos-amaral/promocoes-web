/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.managedbean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oi.promocoesweb.dbms.entity.User;
import static com.oi.promocoesweb.managedbean.ControleBean.AUTH_USER;
import com.oi.promocoesweb.template.entity.BoCartao;
import com.oi.promocoesweb.template.entity.Template;
import com.oi.promocoesweb.template.entity.VoCartao;
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
public class BoCartaoBean extends TemplateBean<BoCartao> implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private final TabelasEnum tabelasEnum = TabelasEnum.BO_CARTAO;

    @PostConstruct
    public void init() {
        logger.trace("============= TemplateBean init =============");
        try {
            super.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new BoCartao());
            super.clearVarsAdd(new BoCartao());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void reloadTabela() {
        try {
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new BoCartao());
            super.clearVarsAdd(new BoCartao());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void adicionar() {
        super.adicionar(tabelasEnum);
        super.clearVarsAdd(new BoCartao());

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
            
            super.clearVarsEdit(new BoCartao());

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
            setEditTemplate(new BoCartao(getSelectedTemplateList().get(0)));
            getEditTemplate().setUser(super.getUser());
            setNewTemplate(new BoCartao(getSelectedTemplateList().get(0)));
            getNewTemplate().setUser(super.getUser());
        } else {
            setEditTemplate(new BoCartao());
            getEditTemplate().setUser(super.getUser());
            setNewTemplate(new BoCartao());
            getNewTemplate().setUser(super.getUser());
        }
        if (getSelectedTemplateList().size() > 0) super.setGmudToDelete(getSelectedTemplateList().get(0).getDt_gmud());
        else super.setGmudToDelete(null);
        
    }

    public void generateScriptFile(String aplicacao) {
        logger.trace("SCRIPT gmud:{}|{}", super.getDefaultFilterValue(), aplicacao);
        StringBuilder script = new StringBuilder();

        List<BoCartao> list = null;

        list = getTemplateList().stream().filter(c -> super.getDefaultFilterValue().equals(c.getDtGmudStr())).collect(Collectors.toList());

        if (list != null) {
            StringBuilder sb;

            InputStream inputStream = null;
            int i;
            for (BoCartao registro : list) {
                sb = new StringBuilder();

                switch (aplicacao) {
                    case "144":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM TB_MSG_PROMO_FALEXCENT_CARTAO;\n");
                            script.append(sb.toString());

                        } else {
                            for (BoCartao registroDel : list) {
                                sb.append("DELETE FROM TB_MSG_PROMO_FALEXCENT_CARTAO");

                                sb.append(" WHERE ");
                                sb.append("IDBENEFICIO = ").append(registroDel.getIdbeneficio()).append(" AND ");
                                sb.append("IDPROGRAMA = ").append(registroDel.getIdprograma()).append(" AND ");
                                sb.append("DDD = '").append(registroDel.getDdd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }

                        sb.append("INSERT INTO TB_MSG_PROMO_FALEXCENT_CARTAO");
                        sb.append(" (IDBENEFICIO,IDPROGRAMA,DDD,CPFXC_PROMOPADRAO,CPFXC_SEMRECARGA,CPFXC_CHAMADA,CPFXC_DETALHES,CPFXC_RECARGA,CPFXC_INFORMAVALIDADE,CPFXC_VALIDADE,CPFXC_CONTINGENCIA,NAOCPFXC_INFORMAPROMO,NAOCPFXC_VALIDADE,NAOCPFXC_REGRAS,NAOCPFXC_RECARGA,INCENTIVO_OFERTA_PRE,FALLBACK,JACLIENTE_SVA,INFORMASALDOPROMO,OFERTAMIGRADOS) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16,VAR17,VAR18,VAR19,VAR20);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getIdbeneficio() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIdprograma() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCpfxc_promopadrao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCpfxc_semrecarga() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCpfxc_chamada() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCpfxc_detalhes() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCpfxc_recarga() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCpfxc_informavalidade() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCpfxc_validade() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCpfxc_contingencia() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getNaocpfxc_informapromo() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getNaocpfxc_validade() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getNaocpfxc_regras() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getNaocpfxc_recarga() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIncentivo_oferta_pre() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getFallback() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getJacliente_sva() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getInformasaldopromo() + "',")
                                .replace("VAR" + (i++), "'" + registro.getOfertamigrados() + "'")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .replace("CPFXC_PROMOPADRAO", "CLIENTE_PROMOPADRAO")
                                        .replace("CPFXC_SEMRECARGA", "CLIENTE_SEMRECARGA")
                                        .replace("CPFXC_CHAMADA", "CLIENTE_CHAMADA")
                                        .replace("CPFXC_DETALHES", "CLIENTE_DETALHES")
                                        .replace("CPFXC_RECARGA", "CLIENTE_RECARGA")
                                        .replace("CPFXC_INFORMAVALIDADE", "CLIENTE_INFORMAVALIDADE")
                                        .replace("CPFXC_VALIDADE", "CLIENTE_VALIDADE")
                                        .replace("CPFXC_CONTINGENCIA", "CLIENTE_CONTINGENCIA")
                                        .replace("NAOCPFXC_INFORMAPROMO", "NAOCLIENTE_INFORMAPROMO")
                                        .replace("NAOCPFXC_VALIDADE", "NAOCLIENTE_VALIDADE")
                                        .replace("NAOCPFXC_REGRAS", "NAOCLIENTE_REGRAS")
                                        .replace("NAOCPFXC_RECARGA", "NAOCLIENTE_RECARGA")
                                        .getBytes(Charset.forName("UTF-8")));

                        break;

                    case "880":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM TPT_SEC_CARTAO_BO;\n");
                            script.append(sb.toString());

                        } else {
                            for (BoCartao registroDel : list) {
                                sb.append("DELETE FROM TPT_SEC_CARTAO_BO");

                                sb.append(" WHERE ");
                                sb.append("IDBENEFICIO = ").append(registroDel.getIdbeneficio()).append(" AND ");
                                sb.append("IDPROGRAMA = ").append(registroDel.getIdprograma()).append(" AND ");
                                sb.append("DDD = '").append(registroDel.getDdd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }

                        sb.append("INSERT INTO TPT_SEC_CARTAO_BO");
                        sb.append(" (IDBENEFICIO,IDPROGRAMA,DDD,CPFXC_PROMOPADRAO,CPFXC_SEMRECARGA,CPFXC_CHAMADA,CPFXC_DETALHES,CPFXC_RECARGA,CPFXC_INFORMAVALIDADE,CPFXC_VALIDADE,CPFXC_CONTINGENCIA,NAOCPFXC_INFORMAPROMO,NAOCPFXC_VALIDADE,NAOCPFXC_REGRAS,NAOCPFXC_RECARGA,INCENTIVO_OFERTA_PRE,FALLBACK,JACLIENTE_SVA,INFORMASALDOPROMO,OFERTAMIGRADOS) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16,VAR17,VAR18,VAR19,VAR20);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getIdbeneficio() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIdprograma() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCpfxc_promopadrao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCpfxc_semrecarga() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCpfxc_chamada() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCpfxc_detalhes() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCpfxc_recarga() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCpfxc_informavalidade() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCpfxc_validade() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCpfxc_contingencia() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getNaocpfxc_informapromo() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getNaocpfxc_validade() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getNaocpfxc_regras() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getNaocpfxc_recarga() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIncentivo_oferta_pre() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getFallback() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getJacliente_sva() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getInformasaldopromo() + "',")
                                .replace("VAR" + (i++), "'" + registro.getOfertamigrados() + "'")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .replace("IDBENEFICIO", "ID_BENEFICIO")
                                        .replace("IDPROGRAMA", "ID_PROGRAMA")
                                        .replace("CPFXC_PROMOPADRAO", "TPT_SEC_CARTAO_CLIENTEPROMOFALEXCENTAVOS_PROMOPADRAO")
                                        .replace("CPFXC_SEMRECARGA", "TPT_SEC_CARTAO_CLIENTEPROMOFALEXCENTAVOS_SEMRECARGA")
                                        .replace("CPFXC_CHAMADA", "TPT_SEC_CARTAO_CLIENTEPROMOFALEXCENTAVOS_CHAMADA")
                                        .replace("CPFXC_DETALHES", "TPT_SEC_CARTAO_CLIENTEPROMOFALEXCENTAVOS_DETALHES")
                                        .replace("CPFXC_RECARGA", "TPT_SEC_CARTAO_CLIENTEPROMOFALEXCENTAVOS_RECARGA")
                                        .replace("CPFXC_INFORMAVALIDADE", "TPT_SEC_CARTAO_CLIENTEPROMOFALEXCENTAVOS_INFORMAVALIDADE")
                                        .replace("CPFXC_VALIDADE", "TPT_SEC_CARTAO_CLIENTEPROMOFALEXCENTAVOS_VALIDADE")
                                        .replace("CPFXC_CONTINGENCIA", "TPT_SEC_CARTAO_CLIENTEPROMOFALEXCENTAVOS_CONTINGENCIA")
                                        .replace("NAOCPFXC_INFORMAPROMO", "TPT_SEC_CARTAO_NAOCLIENTEPROMOFALEXCENTAVOS_INFORMAPROMO")
                                        .replace("NAOCPFXC_VALIDADE", "TPT_SEC_CARTAO_NAOCLIENTEPROMOFALEXCENTAVOS_VALIDADE")
                                        .replace("NAOCPFXC_REGRAS", "TPT_SEC_CARTAO_NAOCLIENTEPROMOFALEXCENTAVOS_REGRAS")
                                        .replace("NAOCPFXC_RECARGA", "TPT_SEC_CARTAO_NAOCLIENTEPROMOFALEXCENTAVOS_RECARGA")
                                        .replace("INCENTIVO_OFERTA_PRE", "TPT_SEC_CARTAO_INCENTIVO_OFERTA_PRE")
                                        .replace("FALLBACK", "TPT_SEC_CARTAO_FALLBACK")
                                        .replace("JACLIENTE_SVA", "TPT_SEC_CARTAO_JACLIENTE_SVA")
                                        .replace("INFORMASALDOPROMO", "TPT_SEC_CARTAO_INFORMASALDOPROMO")
                                        .getBytes(Charset.forName("UTF-8")));

                        break;
                }
            }

            DefaultStreamedContent defaultStreamedContent = new DefaultStreamedContent();
            defaultStreamedContent.setStream(inputStream);
            defaultStreamedContent.setName("config_ofertaspre.sql");
            defaultStreamedContent.setContentType("text/plain");

            super.setScriptFile(defaultStreamedContent);

        }
    }
    
    @Override
    public List<BoCartao> getTemplateList() {
        return (List<BoCartao>) (Object) super.getTemplateList();
    }
    
    @Override
    public List<BoCartao> getSelectedTemplateList() {
        return (List<BoCartao>) (Object) super.getSelectedTemplateList();
    }

    @Override
    public BoCartao getNewTemplate() {
        return (BoCartao) super.getNewTemplate();
    }

    @Override
    public BoCartao getEditTemplate() {
        return (BoCartao) super.getEditTemplate();
    }

    @Override
    public void setTemplateList(List<BoCartao> configOfertasPreList) {
        super.setTemplateList(configOfertasPreList);
    }

    @Override
    public void setSelectedTemplateList(List<BoCartao> selectedTemplateList) {
        super.setSelectedTemplateList(selectedTemplateList);
    }

    @Override
    public void setNewTemplate(BoCartao newTemplate) {
        super.setNewTemplate(newTemplate);
    }

    @Override
    public void setEditTemplate(BoCartao editTemplate) {
        super.setEditTemplate(editTemplate);
    }

    public List<BoCartao> getSelectedHistoryList() {
        if (super.getSelectedTemplateList() != null && super.getSelectedTemplateList().size() > 0) {
            List<BoCartao> history = (List<BoCartao>) (Object) super.getSelectedTemplateList().get(0).getHistory();

            for (BoCartao bean : history) {
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

    @Override
    public LazyDataModel<BoCartao> getDataModel() {
        return (LazyDataModel<BoCartao>) (Object) super.getDataModel();
    }
}
