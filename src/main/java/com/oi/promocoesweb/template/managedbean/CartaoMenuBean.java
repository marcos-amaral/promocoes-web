/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.managedbean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oi.promocoesweb.dbms.entity.User;
import static com.oi.promocoesweb.managedbean.ControleBean.AUTH_USER;
import com.oi.promocoesweb.template.entity.CartaoMenu;
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
public class CartaoMenuBean extends TemplateBean<CartaoMenu> implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private final TabelasEnum tabelasEnum = TabelasEnum.TB_CARTAOMENU;

    @PostConstruct
    public void init() {
        logger.trace("============= TemplateBean init =============");
        try {
            super.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new CartaoMenu());
            super.clearVarsAdd(new CartaoMenu());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void reloadTabela() {
        try {
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new CartaoMenu());
            super.clearVarsAdd(new CartaoMenu());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void adicionar() {
        super.adicionar(tabelasEnum);
        super.clearVarsAdd(new CartaoMenu());

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
            
            super.clearVarsEdit(new CartaoMenu());

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
            setEditTemplate(new CartaoMenu(getSelectedConfigOfertasPreList().get(0)));
            getEditConfigOfertasPre().setUser(super.getUser());
            setNewTemplate(new CartaoMenu(getSelectedConfigOfertasPreList().get(0)));
            getNewConfigOfertasPre().setUser(super.getUser());
        } else {
            setEditTemplate(new CartaoMenu());
            getEditConfigOfertasPre().setUser(super.getUser());
            setNewTemplate(new CartaoMenu());
            getNewConfigOfertasPre().setUser(super.getUser());
        }
        if (getSelectedConfigOfertasPreList().size() > 0) super.setGmudToDelete(getSelectedConfigOfertasPreList().get(0).getDt_gmud());
        else super.setGmudToDelete(null);
    }

    public void generateScriptFile(String aplicacao) {
        logger.trace("SCRIPT gmud:{}|{}", super.getDefaultFilterValue(), aplicacao);
        StringBuilder script = new StringBuilder();

        List<CartaoMenu> list = null;

        list = getConfigOfertasPreList().stream().filter(c -> super.getDefaultFilterValue().equals(c.getDtGmudStr())).collect(Collectors.toList());

        if (list != null) {
            StringBuilder sb;

            InputStream inputStream = null;
            int i;
            for (CartaoMenu registro : list) {
                sb = new StringBuilder();

                switch (aplicacao) {
                    case "144":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM TB_MSG_PROMO_CARTAO;\n");
                            script.append(sb.toString());

                        } else {
                            for (CartaoMenu registroDel : list) {
                                sb.append("DELETE FROM TB_MSG_PROMO_CARTAO");

                                sb.append(" WHERE ");
                                sb.append("TB_ID_BENEFICIO = ").append(registroDel.getTb_id_beneficio()).append(" AND ");
                                sb.append("TB_ID_PROGRAMA = ").append(registroDel.getTb_id_programa()).append(" AND ");
                                sb.append("TB_DDD = '").append(registroDel.getTb_ddd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }

                        sb.append("INSERT INTO TB_MSG_PROMO_CARTAO");
                        sb.append(" (TB_ID_BENEFICIO,TB_ID_PROGRAMA,TB_DDD,UPR1_OI_CPP_MINICIAL,UPR1_OI_CPP_REGTORPEDO,UPR1_OI_CPP_REGLIGACAO,UPR1_OI_CPPCONT_REGLIGACAOOIMOVEL,UPR1_OI_CPPCONT_REGLIGACAOOIMOVELFIXO,UPR1_OI_CPPCONT_REGLIGACAOMIX,UPR1_OI_CPP_COMOUSAR,UPR1_OI_CRBDAD5ARBI_PROMODEGRAU_INIT2,UPR1_OI_CRBDAD5D_RECBONUSMAX_INIT2,UPR1_OI_CRBDAD5D_RECBONUSMINDEGRAU2_INIT2,UPR1_OI_CRBDAD5D_RECEBONUSMINDEGRAU3_INIT2,UPR1_OI_CRBDAD5D_RECEBEBONUSMINDEGRAU2E3_INIT2,UPR1_OI_CRBDAD5D_RECEBEBONUSMAX3_INIT2,UPR1_OI_CINFPROMO_MENSAGEMINICIAL,UPR1_OI_CINFPROMO_REGTORPEDO,UPR1_OI_CINFPROMO_REGLIGACAO,UPR1_OI_CINFPROMO_COMOUSAR,UPR1_OI_CINFPROMO_VALIDADEPROMO,UPR1_OI_CCPOP_PROMOOICARTAO,UPR1_OI_CINFP_REGDADOS,UPR1_OI_CPP_REGDADOS,INCENTIVO_OFERTA_PRE,JACLIENTE_SVA) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16,VAR17,VAR18,VAR19,VAR20,VAR21,VAR22,VAR23,VAR24,VAR25,VAR26);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_beneficio() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_programa() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_ddd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cpp_minicial() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cpp_regtorpedo() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cpp_regligacao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cppcont_regligacaooimovel() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cppcont_regligacaooimovelfixo() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cppcont_regligacaomix() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cpp_comousar() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_crbdad5arbi_promodegrau_init2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_crbdad5d_recbonusmax_init2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_crbdad5d_recbonusmindegrau2_init2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_crbdad5d_recebonusmindegrau3_init2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_crbdad5d_recebebonusmindegrau2e3_init2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_crbdad5d_recebebonusmax3_init2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cinfpromo_mensageminicial() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cinfpromo_regtorpedo() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cinfpromo_regligacao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cinfpromo_comousar() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cinfpromo_validadepromo() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_ccpop_promooicartao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cinfp_regdados() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cpp_regdados() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIncentivo_oferta_pre() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getJacliente_sva() + "',")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .replace("TB_ID_BENEFICIO", "IDBENEFICIO")
                                        .replace("TB_ID_PROGRAMA", "IDPROGRAMA")
                                        .replace("TB_DDD", "DDD")
                                        .replace("UPR1_OI_CPP_MINICIAL", "OIC_PROMO_STD_MSG_INIC")
                                        .replace("UPR1_OI_CPP_REGTORPEDO", "OICARTAOPROMOPADRAO_REGRATORPE")
                                        .replace("UPR1_OI_CPP_REGLIGACAO", "OIC_PROMO_STD_REGRA_LIGACAO")
                                        .replace("UPR1_OI_CPPCONT_REGLIGACAOOIMOVEL", "OICARTAOPROMOPADRAOCONT_REGR_3")
                                        .replace("UPR1_OI_CPPCONT_REGLIGACAOOIMOVELFIXO", "OICARTAOPROMOPADRAOCONT_REGR_4")
                                        .replace("UPR1_OI_CPPCONT_REGLIGACAOMIX", "OICARTAOPROMOPADRAOCONT_REGRAL")
                                        .replace("UPR1_OI_CPP_COMOUSAR", "OICARTAOPROMOPADRAO_COMOUSAR")
                                        .replace("UPR1_OI_CRBDAD5ARBI_PROMODEGRAU_INIT2", "BONUSDAY_DIA5_INICIO_PROMODEG")
                                        .replace("UPR1_OI_CRBDAD5D_RECBONUSMAX_INIT2", "BONUSDAY_DIA5_REC_MAX")
                                        .replace("UPR1_OI_CRBDAD5D_RECBONUSMINDEGRAU2_INIT2", "BONUSDAY_DIA5_REC_MIN_DEG2")
                                        .replace("UPR1_OI_CRBDAD5D_RECEBONUSMINDEGRAU3_INIT2", "BONUSDAY_DIA5_REC_MIN_DEG3")
                                        .replace("UPR1_OI_CRBDAD5D_RECEBEBONUSMINDEGRAU2E3_INIT2", "BONUSDAY_DIA5_REC_MIN_DEG2E3")
                                        .replace("UPR1_OI_CRBDAD5D_RECEBEBONUSMAX3_INIT2", "BONUSDAY_DIA5_REC_MAX3")
                                        .replace("UPR1_OI_CINFPROMO_MENSAGEMINICIAL", "OIC_INFO_PROMO_MSG_INIC")
                                        .replace("UPR1_OI_CINFPROMO_REGTORPEDO", "OICARTAOINFORMAPROMO_REGRATORP")
                                        .replace("UPR1_OI_CINFPROMO_REGLIGACAO", "OICARTAOINFORMAPROMO_REGRALIGA")
                                        .replace("UPR1_OI_CINFPROMO_COMOUSAR", "OICARTAOINFORMAPROMO_COMOUSAR")
                                        .replace("UPR1_OI_CINFPROMO_VALIDADEPROMO", "OICARTAOINFORMAPROMO_VALIDADEP")
                                        .replace("UPR1_OI_CCPOP_PROMOOICARTAO", "OICARTAOCONTROLEPROMOOUTROSPLA")
                                        .replace("UPR1_OI_CINFP_REGDADOS", "OICARTAOINFORMAPROMO_REGRADADO")
                                        .replace("UPR1_OI_CPP_REGDADOS", "OICARTAOPROMOPADRAO_REGRADADOS")
                                        .getBytes(Charset.forName("UTF-8")));

                        break;

                    case "880":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM CARTAO_MENU;\n");
                            script.append(sb.toString());

                        } else {
                            for (CartaoMenu registroDel : list) {
                                sb.append("DELETE FROM CARTAO_MENU");

                                sb.append(" WHERE ");
                                sb.append("TB_ID_BENEFICIO = ").append(registroDel.getTb_id_beneficio()).append(" AND ");
                                sb.append("TB_ID_PROGRAMA = ").append(registroDel.getTb_id_programa()).append(" AND ");
                                sb.append("TB_DDD = '").append(registroDel.getTb_ddd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }

                        sb.append("INSERT INTO CARTAO_MENU");
                        sb.append(" (TB_ID_BENEFICIO,TB_ID_PROGRAMA,TB_DDD,TB_REGIAO,UPR1_OI_CPP_MINICIAL,UPR1_OI_CPP_REGTORPEDO,UPR1_OI_CPP_REGLIGACAO,UPR1_OI_CPPCONT_REGLIGACAOOIMOVEL,UPR1_OI_CPPCONT_REGLIGACAOOIMOVELFIXO,UPR1_OI_CPPCONT_REGLIGACAOMIX,UPR1_OI_CPP_COMOUSAR,UPR1_OI_CRBDAD5ARBI_PROMODEGRAU_INIT2,UPR1_OI_CRBDAD5D_RECBONUSMAX_INIT2,UPR1_OI_CRBDAD5D_RECBONUSMINDEGRAU2_INIT2,UPR1_OI_CRBDAD5D_RECEBONUSMINDEGRAU3_INIT2,UPR1_OI_CRBDAD5D_RECEBEBONUSMINDEGRAU2E3_INIT2,UPR1_OI_CRBDAD5D_RECEBEBONUSMAX3_INIT2,UPR1_OI_CINFPROMO_MENSAGEMINICIAL,UPR1_OI_CINFPROMO_REGTORPEDO,UPR1_OI_CINFPROMO_REGLIGACAO,UPR1_OI_CINFPROMO_COMOUSAR,UPR1_OI_CINFPROMO_VALIDADEPROMO,UPR1_OI_CCPOP_PROMOOICARTAO,UPR1_OI_CINFP_REGDADOS,UPR1_OI_CPP_REGDADOS,INCENTIVO_OFERTA_PRE,JACLIENTE_SVA) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16,VAR17,VAR18,VAR19,VAR20,VAR21,VAR22,VAR23,VAR24,VAR25,VAR26);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_beneficio() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_programa() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_ddd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cpp_minicial() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cpp_regtorpedo() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cpp_regligacao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cppcont_regligacaooimovel() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cppcont_regligacaooimovelfixo() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cppcont_regligacaomix() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cpp_comousar() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_crbdad5arbi_promodegrau_init2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_crbdad5d_recbonusmax_init2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_crbdad5d_recbonusmindegrau2_init2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_crbdad5d_recebonusmindegrau3_init2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_crbdad5d_recebebonusmindegrau2e3_init2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_crbdad5d_recebebonusmax3_init2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cinfpromo_mensageminicial() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cinfpromo_regtorpedo() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cinfpromo_regligacao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cinfpromo_comousar() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cinfpromo_validadepromo() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_ccpop_promooicartao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cinfp_regdados() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getUpr1_oi_cpp_regdados() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIncentivo_oferta_pre() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getJacliente_sva() + "',")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .replace("TB_ID_BENEFICIO", "IDBENEFICIO")
                                        .replace("TB_ID_PROGRAMA", "IDPROGRAMA")
                                        .replace("TB_DDD", "DDD")
                                        .replace("UPR1_OI_CPP_MINICIAL", "OICARTAOPROMOPADRAO_MENSAGEMINICIAL")
                                        .replace("UPR1_OI_CPP_REGTORPEDO", "OICARTAOPROMOPADRAO_REGRATORPEDO")
                                        .replace("UPR1_OI_CPP_REGLIGACAO", "OICARTAOPROMOPADRAO_REGRALIGACAO")
                                        .replace("UPR1_OI_CPPCONT_REGLIGACAOOIMOVEL", "OICARTAOPROMOPADRAOCONT_REGRALIGACAOOIMOVEL")
                                        .replace("UPR1_OI_CPPCONT_REGLIGACAOOIMOVELFIXO", "OICARTAOPROMOPADRAOCONT_REGRALIGACAOOIMOVELFIXO")
                                        .replace("UPR1_OI_CPPCONT_REGLIGACAOMIX", "OICARTAOPROMOPADRAOCONT_REGRALIGACAOMIX")
                                        .replace("UPR1_OI_CPP_COMOUSAR", "OICARTAOPROMOPADRAO_COMOUSAR")
                                        .replace("UPR1_OI_CRBDAD5ARBI_PROMODEGRAU_INIT2", "OICRECBONUSDIARIOATEDIA5ADESAO_RECEBEBONUSINICIOPROMODEGRAU")
                                        .replace("UPR1_OI_CRBDAD5D_RECBONUSMAX_INIT2", "OICRECEBIMENTOBONUSDIARIOATEDIA5DEGRAU_RECEBEBONUSMAX")
                                        .replace("UPR1_OI_CRBDAD5D_RECBONUSMINDEGRAU2_INIT2", "OICRECEBIMENTOBONUSDIARIOATEDIA5DEGRAU_RECEBEBONUSMINDEGRAU2")
                                        .replace("UPR1_OI_CRBDAD5D_RECEBONUSMINDEGRAU3_INIT2", "OICRECEBIMENTOBONUSDIARIOATEDIA5DEGRAU_RECEBEBONUSMINDEGRAU3")
                                        .replace("UPR1_OI_CRBDAD5D_RECEBEBONUSMINDEGRAU2E3_INIT2", "OICRECEBIMENTOBONUSDIARIOATEDIA5DEGRAU_RECEBEBONUSMINDEGRAU2E3")
                                        .replace("UPR1_OI_CRBDAD5D_RECEBEBONUSMAX3_INIT2", "OICARTAORECEBIMENTOBONUSDIARIOATEDIA5DEGRAU_RECEBEBONUSMAX3")
                                        .replace("UPR1_OI_CINFPROMO_MENSAGEMINICIAL", "OICARTAOINFORMAPROMO_MENSAGEMINICIAL")
                                        .replace("UPR1_OI_CINFPROMO_REGTORPEDO", "OICARTAOINFORMAPROMO_REGRATORPEDO")
                                        .replace("UPR1_OI_CINFPROMO_REGLIGACAO", "OICARTAOINFORMAPROMO_REGRALIGACAO")
                                        .replace("UPR1_OI_CINFPROMO_COMOUSAR", "OICARTAOINFORMAPROMO_COMOUSAR")
                                        .replace("UPR1_OI_CINFPROMO_VALIDADEPROMO", "OICARTAOINFORMAPROMO_VALIDADEPROMO")
                                        .replace("UPR1_OI_CCPOP_PROMOOICARTAO", "OICARTAOCONTROLEPROMOOUTROSPLANOS_PROMOOICARTAO")
                                        .replace("UPR1_OI_CINFP_REGDADOS", "OICARTAOINFORMAPROMO_REGRADADOS")
                                        .replace("UPR1_OI_CPP_REGDADOS", "OICARTAOPROMOPADRAO_REGRADADOS")
                                        .getBytes(Charset.forName("UTF-8")));

                        break;
                }
            }

            DefaultStreamedContent defaultStreamedContent = new DefaultStreamedContent();
            defaultStreamedContent.setStream(inputStream);
            defaultStreamedContent.setName("promocoes_cartao.sql");
            defaultStreamedContent.setContentType("text/plain");

            super.setScriptFile(defaultStreamedContent);

        }
    }
    @Override
    public List<CartaoMenu> getTemplateList() {
        return (List<CartaoMenu>) (Object) super.getTemplateList();
    }

    public List<CartaoMenu> getConfigOfertasPreList() {
        return (List<CartaoMenu>) (Object) super.getTemplateList();
    }

    public List<CartaoMenu> getSelectedConfigOfertasPreList() {
        return (List<CartaoMenu>) (Object) super.getSelectedTemplateList();
    }

    public CartaoMenu getNewConfigOfertasPre() {
        return (CartaoMenu) super.getNewTemplate();
    }

    public CartaoMenu getEditConfigOfertasPre() {
        return (CartaoMenu) super.getEditTemplate();
    }

    public void setConfigOfertasPreList(List<CartaoMenu> configOfertasPreList) {
        super.setTemplateList(configOfertasPreList);
    }

    public void setSelectedConfigOfertasPreList(List<CartaoMenu> selectedConfigOfertasPreList) {
        super.setSelectedTemplateList(selectedConfigOfertasPreList);
    }

    public void setNewConfigOfertasPre(CartaoMenu newConfigOfertasPre) {
        super.setNewTemplate(newConfigOfertasPre);
    }

    public void setEditConfigOfertasPre(CartaoMenu editConfigOfertasPre) {
        super.setEditTemplate(editConfigOfertasPre);
    }

    public List<CartaoMenu> getSelectedConfigOfertasPreHistoryList() {
        if (super.getSelectedTemplateList() != null && super.getSelectedTemplateList().size() > 0) {
            List<CartaoMenu> history = (List<CartaoMenu>) (Object) super.getSelectedTemplateList().get(0).getHistory();

            for (CartaoMenu bean : history) {
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
    
    public LazyDataModel<CartaoMenu> getDataModel() {
        return (LazyDataModel<CartaoMenu>) (Object) super.getDataModel();
    }

}
