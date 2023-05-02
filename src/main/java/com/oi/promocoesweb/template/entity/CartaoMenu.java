/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oi.promocoesweb.dbms.entity.User;
import com.oi.promocoesweb.validation.annotation.FieldValidation;
import com.oi.promocoesweb.validation.enums.ValidationEnum;
import java.util.Arrays;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mmouraam
 */
public class CartaoMenu extends Template {

    private static final Logger logger = LogManager.getLogger();

    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    private String tb_id_beneficio;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    private String tb_id_programa;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    private String tb_id_oferta;
    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    private String tb_ddd;
    private String tb_regiao;
    private String upr1_oi_cpp_minicial;
    private String upr1_oi_cpp_regtorpedo;
    private String upr1_oi_cpp_regligacao;
    private String upr1_oi_cppcont_regligacaooimovel;
    private String upr1_oi_cppcont_regligacaooimovelfixo;
    private String upr1_oi_cppcont_regligacaomix;
    private String upr1_oi_cpp_comousar;
    private String upr1_oi_crbdad5arbi_promodegrau_init2;
    private String upr1_oi_crbdad5d_recbonusmax_init2;
    private String upr1_oi_crbdad5d_recbonusmindegrau2_init2;
    private String upr1_oi_crbdad5d_recebonusmindegrau3_init2;
    private String upr1_oi_crbdad5d_recebebonusmindegrau2e3_init2;
    private String upr1_oi_crbdad5d_recebebonusmax3_init2;
    private String upr1_oi_cinfpromo_mensageminicial;
    private String upr1_oi_cinfpromo_regtorpedo;
    private String upr1_oi_cinfpromo_regligacao;
    private String upr1_oi_cinfpromo_comousar;
    private String upr1_oi_cinfpromo_validadepromo;
    private String upr1_oi_ccpop_promooicartao;
    private String upr1_oi_cinfp_regdados;
    private String upr1_oi_cpp_regdados;
    private String incentivo_oferta_pre;
    private String jacliente_sva;

    public CartaoMenu() {
        super();
    }

    public CartaoMenu(CartaoMenu selectedConfigOfertasPre) {
        this.tb_id_beneficio = selectedConfigOfertasPre.getTb_id_beneficio();
        this.tb_id_programa = selectedConfigOfertasPre.getTb_id_programa();
        this.tb_id_oferta = selectedConfigOfertasPre.getTb_id_oferta();
        this.tb_ddd = selectedConfigOfertasPre.getTb_ddd();
        this.tb_regiao = selectedConfigOfertasPre.getTb_regiao();
        this.upr1_oi_cpp_minicial = selectedConfigOfertasPre.getUpr1_oi_cpp_minicial();
        this.upr1_oi_cpp_regtorpedo = selectedConfigOfertasPre.getUpr1_oi_cpp_regtorpedo();
        this.upr1_oi_cpp_regligacao = selectedConfigOfertasPre.getUpr1_oi_cpp_regligacao();
        this.upr1_oi_cppcont_regligacaooimovel = selectedConfigOfertasPre.getUpr1_oi_cppcont_regligacaooimovel();
        this.upr1_oi_cppcont_regligacaooimovelfixo = selectedConfigOfertasPre.getUpr1_oi_cppcont_regligacaooimovelfixo();
        this.upr1_oi_cppcont_regligacaomix = selectedConfigOfertasPre.getUpr1_oi_cppcont_regligacaomix();
        this.upr1_oi_cpp_comousar = selectedConfigOfertasPre.getUpr1_oi_cpp_comousar();
        this.upr1_oi_crbdad5arbi_promodegrau_init2 = selectedConfigOfertasPre.getUpr1_oi_crbdad5arbi_promodegrau_init2();
        this.upr1_oi_crbdad5d_recbonusmax_init2 = selectedConfigOfertasPre.getUpr1_oi_crbdad5d_recbonusmax_init2();
        this.upr1_oi_crbdad5d_recbonusmindegrau2_init2 = selectedConfigOfertasPre.getUpr1_oi_crbdad5d_recbonusmindegrau2_init2();
        this.upr1_oi_crbdad5d_recebonusmindegrau3_init2 = selectedConfigOfertasPre.getUpr1_oi_crbdad5d_recebonusmindegrau3_init2();
        this.upr1_oi_crbdad5d_recebebonusmindegrau2e3_init2 = selectedConfigOfertasPre.getUpr1_oi_crbdad5d_recebebonusmindegrau2e3_init2();
        this.upr1_oi_crbdad5d_recebebonusmax3_init2 = selectedConfigOfertasPre.getUpr1_oi_crbdad5d_recebebonusmax3_init2();
        this.upr1_oi_cinfpromo_mensageminicial = selectedConfigOfertasPre.getUpr1_oi_cinfpromo_mensageminicial();
        this.upr1_oi_cinfpromo_regtorpedo = selectedConfigOfertasPre.getUpr1_oi_cinfpromo_regtorpedo();
        this.upr1_oi_cinfpromo_regligacao = selectedConfigOfertasPre.getUpr1_oi_cinfpromo_regligacao();
        this.upr1_oi_cinfpromo_comousar = selectedConfigOfertasPre.getUpr1_oi_cinfpromo_comousar();
        this.upr1_oi_cinfpromo_validadepromo = selectedConfigOfertasPre.getUpr1_oi_cinfpromo_validadepromo();
        this.upr1_oi_ccpop_promooicartao = selectedConfigOfertasPre.getUpr1_oi_ccpop_promooicartao();
        this.upr1_oi_cinfp_regdados = selectedConfigOfertasPre.getUpr1_oi_cinfp_regdados();
        this.upr1_oi_cpp_regdados = selectedConfigOfertasPre.getUpr1_oi_cpp_regdados();
        this.incentivo_oferta_pre = selectedConfigOfertasPre.getIncentivo_oferta_pre();
        this.jacliente_sva = selectedConfigOfertasPre.getJacliente_sva();
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_cpp_minicial() {
        return super.getEditMap().getOrDefault("UPR1_OI_CPP_MINICIAL", false);
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_cpp_regtorpedo() {
        return super.getEditMap().getOrDefault("UPR1_OI_CPP_REGTORPEDO", false);
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_cpp_regligacao() {
        return super.getEditMap().getOrDefault("UPR1_OI_CPP_REGLIGACAO", false);
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_cppcont_regligacaooimovel() {
        return super.getEditMap().getOrDefault("UPR1_OI_CPPCONT_REGLIGACAOOIMOVEL", false);
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_cppcont_regligacaooimovelfixo() {
        return super.getEditMap().getOrDefault("UPR1_OI_CPPCONT_REGLIGACAOOIMOVELFIXO", false);
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_cppcont_regligacaomix() {
        return super.getEditMap().getOrDefault("UPR1_OI_CPPCONT_REGLIGACAOMIX", false);
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_cpp_comousar() {
        return super.getEditMap().getOrDefault("UPR1_OI_CPP_COMOUSAR", false);
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_crbdad5arbi_promodegrau_init2() {
        return super.getEditMap().getOrDefault("UPR1_OI_CRBDAD5ARBI_PROMODEGRAU_INIT2", false);
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_crbdad5d_recbonusmax_init2() {
        return super.getEditMap().getOrDefault("UPR1_OI_CRBDAD5D_RECBONUSMAX_INIT2", false);
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_crbdad5d_recbonusmindegrau2_init2() {
        return super.getEditMap().getOrDefault("UPR1_OI_CRBDAD5D_RECBONUSMINDEGRAU2_INIT2", false);
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_crbdad5d_recebonusmindegrau3_init2() {
        return super.getEditMap().getOrDefault("UPR1_OI_CRBDAD5D_RECEBONUSMINDEGRAU3_INIT2", false);
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_crbdad5d_recebebonusmindegrau2e3_init2() {
        return super.getEditMap().getOrDefault("UPR1_OI_CRBDAD5D_RECEBEBONUSMINDEGRAU2E3_INIT2", false);
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_crbdad5d_recebebonusmax3_init2() {
        return super.getEditMap().getOrDefault("UPR1_OI_CRBDAD5D_RECEBEBONUSMAX3_INIT2", false);
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_cinfpromo_mensageminicial() {
        return super.getEditMap().getOrDefault("UPR1_OI_CINFPROMO_MENSAGEMINICIAL", false);
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_cinfpromo_regtorpedo() {
        return super.getEditMap().getOrDefault("UPR1_OI_CINFPROMO_REGTORPEDO", false);
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_cinfpromo_regligacao() {
        return super.getEditMap().getOrDefault("UPR1_OI_CINFPROMO_REGLIGACAO", false);
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_cinfpromo_comousar() {
        return super.getEditMap().getOrDefault("UPR1_OI_CINFPROMO_COMOUSAR", false);
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_cinfpromo_validadepromo() {
        return super.getEditMap().getOrDefault("UPR1_OI_CINFPROMO_VALIDADEPROMO", false);
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_ccpop_promooicartao() {
        return super.getEditMap().getOrDefault("UPR1_OI_CCPOP_PROMOOICARTAO", false);
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_cinfp_regdados() {
        return super.getEditMap().getOrDefault("UPR1_OI_CINFP_REGDADOS", false);
    }

    @JsonIgnore
    public boolean isEditUpr1_oi_cpp_regdados() {
        return super.getEditMap().getOrDefault("UPR1_OI_CPP_REGDADOS", false);
    }

    @JsonIgnore
    public boolean isEditIncentivo_oferta_pre() {
        return super.getEditMap().getOrDefault("INCENTIVO_OFERTA_PRE", false);
    }

    @JsonIgnore
    public boolean isEditJacliente_sva() {
        return super.getEditMap().getOrDefault("JACLIENTE_SVA", false);
    }

    public void setEditUpr1_oi_cpp_minicial(boolean checked) {
        super.getEditMap().put("UPR1_OI_CPP_MINICIAL", checked);
    }

    public void setEditUpr1_oi_cpp_regtorpedo(boolean checked) {
        super.getEditMap().put("UPR1_OI_CPP_REGTORPEDO", checked);
    }

    public void setEditUpr1_oi_cpp_regligacao(boolean checked) {
        super.getEditMap().put("UPR1_OI_CPP_REGLIGACAO", checked);
    }

    public void setEditUpr1_oi_cppcont_regligacaooimovel(boolean checked) {
        super.getEditMap().put("UPR1_OI_CPPCONT_REGLIGACAOOIMOVEL", checked);
    }

    public void setEditUpr1_oi_cppcont_regligacaooimovelfixo(boolean checked) {
        super.getEditMap().put("UPR1_OI_CPPCONT_REGLIGACAOOIMOVELFIXO", checked);
    }

    public void setEditUpr1_oi_cppcont_regligacaomix(boolean checked) {
        super.getEditMap().put("UPR1_OI_CPPCONT_REGLIGACAOMIX", checked);
    }

    public void setEditUpr1_oi_cpp_comousar(boolean checked) {
        super.getEditMap().put("UPR1_OI_CPP_COMOUSAR", checked);
    }

    public void setEditUpr1_oi_crbdad5arbi_promodegrau_init2(boolean checked) {
        super.getEditMap().put("UPR1_OI_CRBDAD5ARBI_PROMODEGRAU_INIT2", checked);
    }

    public void setEditUpr1_oi_crbdad5d_recbonusmax_init2(boolean checked) {
        super.getEditMap().put("UPR1_OI_CRBDAD5D_RECBONUSMAX_INIT2", checked);
    }

    public void setEditUpr1_oi_crbdad5d_recbonusmindegrau2_init2(boolean checked) {
        super.getEditMap().put("UPR1_OI_CRBDAD5D_RECBONUSMINDEGRAU2_INIT2", checked);
    }

    public void setEditUpr1_oi_crbdad5d_recebonusmindegrau3_init2(boolean checked) {
        super.getEditMap().put("UPR1_OI_CRBDAD5D_RECEBONUSMINDEGRAU3_INIT2", checked);
    }

    public void setEditUpr1_oi_crbdad5d_recebebonusmindegrau2e3_init2(boolean checked) {
        super.getEditMap().put("UPR1_OI_CRBDAD5D_RECEBEBONUSMINDEGRAU2E3_INIT2", checked);
    }

    public void setEditUpr1_oi_crbdad5d_recebebonusmax3_init2(boolean checked) {
        super.getEditMap().put("UPR1_OI_CRBDAD5D_RECEBEBONUSMAX3_INIT2", checked);
    }

    public void setEditUpr1_oi_cinfpromo_mensageminicial(boolean checked) {
        super.getEditMap().put("UPR1_OI_CINFPROMO_MENSAGEMINICIAL", checked);
    }

    public void setEditUpr1_oi_cinfpromo_regtorpedo(boolean checked) {
        super.getEditMap().put("UPR1_OI_CINFPROMO_REGTORPEDO", checked);
    }

    public void setEditUpr1_oi_cinfpromo_regligacao(boolean checked) {
        super.getEditMap().put("UPR1_OI_CINFPROMO_REGLIGACAO", checked);
    }

    public void setEditUpr1_oi_cinfpromo_comousar(boolean checked) {
        super.getEditMap().put("UPR1_OI_CINFPROMO_COMOUSAR", checked);
    }

    public void setEditUpr1_oi_cinfpromo_validadepromo(boolean checked) {
        super.getEditMap().put("UPR1_OI_CINFPROMO_VALIDADEPROMO", checked);
    }

    public void setEditUpr1_oi_ccpop_promooicartao(boolean checked) {
        super.getEditMap().put("UPR1_OI_CCPOP_PROMOOICARTAO", checked);
    }

    public void setEditUpr1_oi_cinfp_regdados(boolean checked) {
        super.getEditMap().put("UPR1_OI_CINFP_REGDADOS", checked);
    }

    public void setEditUpr1_oi_cpp_regdados(boolean checked) {
        super.getEditMap().put("UPR1_OI_CPP_REGDADOS", checked);
    }

    public void setEditIncentivo_oferta_pre(boolean checked) {
        super.getEditMap().put("INCENTIVO_OFERTA_PRE", checked);
    }

    public void setEditJacliente_sva(boolean checked) {
        super.getEditMap().put("JACLIENTE_SVA", checked);
    }

    @JsonIgnore
    public String getIdDdd() {
        StringBuilder sb = new StringBuilder();
        if (tb_ddd != null && tb_ddd.length() >= 2) {
            return sb.append("ID_BFPG: ").append(tb_id_beneficio).append(tb_id_programa).append(" | DDD: ").append(tb_ddd.substring(0, 2)).append("...").toString();
        } else {
            return sb.append("ID_BFPG: ").append(tb_id_beneficio).append(tb_id_programa).append(" | DDD: null").toString();
        }
    }

    @JsonIgnore
    public String getDddSub() {
        if (tb_ddd != null && tb_ddd.length() > 59) {
            String dddSub = tb_ddd.substring(0, 59);
            dddSub = dddSub.substring(0, dddSub.lastIndexOf(",")) + "...";
            return dddSub;
        }
        return tb_ddd;
    }

    public String getTb_id_beneficio() {
        return tb_id_beneficio;
    }

    public void setTb_id_beneficio(String tb_id_beneficio) {
        this.tb_id_beneficio = tb_id_beneficio;
    }

    public String getTb_id_programa() {
        return tb_id_programa;
    }

    public void setTb_id_programa(String tb_id_programa) {
        this.tb_id_programa = tb_id_programa;
    }

    public String getTb_id_oferta() {
        return tb_id_oferta;
    }

    public void setTb_id_oferta(String tb_id_oferta) {
        this.tb_id_oferta = tb_id_oferta;
    }

    public String getTb_ddd() {
        return tb_ddd;
    }

    public void setTb_ddd(String tb_ddd) {
        this.tb_ddd = tb_ddd;
    }

    public String getTb_regiao() {
        return tb_regiao;
    }

    public void setTb_regiao(String tb_regiao) {
        this.tb_regiao = tb_regiao;
    }

    public String getUpr1_oi_cpp_minicial() {
        return upr1_oi_cpp_minicial;
    }

    public void setUpr1_oi_cpp_minicial(String upr1_oi_cpp_minicial) {
        this.upr1_oi_cpp_minicial = upr1_oi_cpp_minicial;
    }

    public String getUpr1_oi_cpp_regtorpedo() {
        return upr1_oi_cpp_regtorpedo;
    }

    public void setUpr1_oi_cpp_regtorpedo(String upr1_oi_cpp_regtorpedo) {
        this.upr1_oi_cpp_regtorpedo = upr1_oi_cpp_regtorpedo;
    }

    public String getUpr1_oi_cpp_regligacao() {
        return upr1_oi_cpp_regligacao;
    }

    public void setUpr1_oi_cpp_regligacao(String upr1_oi_cpp_regligacao) {
        this.upr1_oi_cpp_regligacao = upr1_oi_cpp_regligacao;
    }

    public String getUpr1_oi_cppcont_regligacaooimovel() {
        return upr1_oi_cppcont_regligacaooimovel;
    }

    public void setUpr1_oi_cppcont_regligacaooimovel(String upr1_oi_cppcont_regligacaooimovel) {
        this.upr1_oi_cppcont_regligacaooimovel = upr1_oi_cppcont_regligacaooimovel;
    }

    public String getUpr1_oi_cppcont_regligacaooimovelfixo() {
        return upr1_oi_cppcont_regligacaooimovelfixo;
    }

    public void setUpr1_oi_cppcont_regligacaooimovelfixo(String upr1_oi_cppcont_regligacaooimovelfixo) {
        this.upr1_oi_cppcont_regligacaooimovelfixo = upr1_oi_cppcont_regligacaooimovelfixo;
    }

    public String getUpr1_oi_cppcont_regligacaomix() {
        return upr1_oi_cppcont_regligacaomix;
    }

    public void setUpr1_oi_cppcont_regligacaomix(String upr1_oi_cppcont_regligacaomix) {
        this.upr1_oi_cppcont_regligacaomix = upr1_oi_cppcont_regligacaomix;
    }

    public String getUpr1_oi_cpp_comousar() {
        return upr1_oi_cpp_comousar;
    }

    public void setUpr1_oi_cpp_comousar(String upr1_oi_cpp_comousar) {
        this.upr1_oi_cpp_comousar = upr1_oi_cpp_comousar;
    }

    public String getUpr1_oi_crbdad5arbi_promodegrau_init2() {
        return upr1_oi_crbdad5arbi_promodegrau_init2;
    }

    public void setUpr1_oi_crbdad5arbi_promodegrau_init2(String upr1_oi_crbdad5arbi_promodegrau_init2) {
        this.upr1_oi_crbdad5arbi_promodegrau_init2 = upr1_oi_crbdad5arbi_promodegrau_init2;
    }

    public String getUpr1_oi_crbdad5d_recbonusmax_init2() {
        return upr1_oi_crbdad5d_recbonusmax_init2;
    }

    public void setUpr1_oi_crbdad5d_recbonusmax_init2(String upr1_oi_crbdad5d_recbonusmax_init2) {
        this.upr1_oi_crbdad5d_recbonusmax_init2 = upr1_oi_crbdad5d_recbonusmax_init2;
    }

    public String getUpr1_oi_crbdad5d_recbonusmindegrau2_init2() {
        return upr1_oi_crbdad5d_recbonusmindegrau2_init2;
    }

    public void setUpr1_oi_crbdad5d_recbonusmindegrau2_init2(String upr1_oi_crbdad5d_recbonusmindegrau2_init2) {
        this.upr1_oi_crbdad5d_recbonusmindegrau2_init2 = upr1_oi_crbdad5d_recbonusmindegrau2_init2;
    }

    public String getUpr1_oi_crbdad5d_recebonusmindegrau3_init2() {
        return upr1_oi_crbdad5d_recebonusmindegrau3_init2;
    }

    public void setUpr1_oi_crbdad5d_recebonusmindegrau3_init2(String upr1_oi_crbdad5d_recebonusmindegrau3_init2) {
        this.upr1_oi_crbdad5d_recebonusmindegrau3_init2 = upr1_oi_crbdad5d_recebonusmindegrau3_init2;
    }

    public String getUpr1_oi_crbdad5d_recebebonusmindegrau2e3_init2() {
        return upr1_oi_crbdad5d_recebebonusmindegrau2e3_init2;
    }

    public void setUpr1_oi_crbdad5d_recebebonusmindegrau2e3_init2(String upr1_oi_crbdad5d_recebebonusmindegrau2e3_init2) {
        this.upr1_oi_crbdad5d_recebebonusmindegrau2e3_init2 = upr1_oi_crbdad5d_recebebonusmindegrau2e3_init2;
    }

    public String getUpr1_oi_crbdad5d_recebebonusmax3_init2() {
        return upr1_oi_crbdad5d_recebebonusmax3_init2;
    }

    public void setUpr1_oi_crbdad5d_recebebonusmax3_init2(String upr1_oi_crbdad5d_recebebonusmax3_init2) {
        this.upr1_oi_crbdad5d_recebebonusmax3_init2 = upr1_oi_crbdad5d_recebebonusmax3_init2;
    }

    public String getUpr1_oi_cinfpromo_mensageminicial() {
        return upr1_oi_cinfpromo_mensageminicial;
    }

    public void setUpr1_oi_cinfpromo_mensageminicial(String upr1_oi_cinfpromo_mensageminicial) {
        this.upr1_oi_cinfpromo_mensageminicial = upr1_oi_cinfpromo_mensageminicial;
    }

    public String getUpr1_oi_cinfpromo_regtorpedo() {
        return upr1_oi_cinfpromo_regtorpedo;
    }

    public void setUpr1_oi_cinfpromo_regtorpedo(String upr1_oi_cinfpromo_regtorpedo) {
        this.upr1_oi_cinfpromo_regtorpedo = upr1_oi_cinfpromo_regtorpedo;
    }

    public String getUpr1_oi_cinfpromo_regligacao() {
        return upr1_oi_cinfpromo_regligacao;
    }

    public void setUpr1_oi_cinfpromo_regligacao(String upr1_oi_cinfpromo_regligacao) {
        this.upr1_oi_cinfpromo_regligacao = upr1_oi_cinfpromo_regligacao;
    }

    public String getUpr1_oi_cinfpromo_comousar() {
        return upr1_oi_cinfpromo_comousar;
    }

    public void setUpr1_oi_cinfpromo_comousar(String upr1_oi_cinfpromo_comousar) {
        this.upr1_oi_cinfpromo_comousar = upr1_oi_cinfpromo_comousar;
    }

    public String getUpr1_oi_cinfpromo_validadepromo() {
        return upr1_oi_cinfpromo_validadepromo;
    }

    public void setUpr1_oi_cinfpromo_validadepromo(String upr1_oi_cinfpromo_validadepromo) {
        this.upr1_oi_cinfpromo_validadepromo = upr1_oi_cinfpromo_validadepromo;
    }

    public String getUpr1_oi_ccpop_promooicartao() {
        return upr1_oi_ccpop_promooicartao;
    }

    public void setUpr1_oi_ccpop_promooicartao(String upr1_oi_ccpop_promooicartao) {
        this.upr1_oi_ccpop_promooicartao = upr1_oi_ccpop_promooicartao;
    }

    public String getUpr1_oi_cinfp_regdados() {
        return upr1_oi_cinfp_regdados;
    }

    public void setUpr1_oi_cinfp_regdados(String upr1_oi_cinfp_regdados) {
        this.upr1_oi_cinfp_regdados = upr1_oi_cinfp_regdados;
    }

    public String getUpr1_oi_cpp_regdados() {
        return upr1_oi_cpp_regdados;
    }

    public void setUpr1_oi_cpp_regdados(String upr1_oi_cpp_regdados) {
        this.upr1_oi_cpp_regdados = upr1_oi_cpp_regdados;
    }

    public String getIncentivo_oferta_pre() {
        return incentivo_oferta_pre;
    }

    public void setIncentivo_oferta_pre(String incentivo_oferta_pre) {
        this.incentivo_oferta_pre = incentivo_oferta_pre;
    }

    public String getJacliente_sva() {
        return jacliente_sva;
    }

    public void setJacliente_sva(String jacliente_sva) {
        this.jacliente_sva = jacliente_sva;
    }

    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                CartaoMenu[] pp1 = mapper.readValue(history, CartaoMenu[].class);
                setHistory(Arrays.asList(mapper.readValue(history, CartaoMenu[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (CartaoMenu template : pp1) {
                    if (template.getModified() != null && template.getModified().after(tempDate)) {
                        tempDate = template.getModified();
                        tempUser = template.getUser();
                    }

                    template.getEditMap().forEach((k, v) -> {
                        if (Boolean.TRUE.equals(v)) {
                            getEditMap().put(k, v);
                        }
                    });
                }
                setModified(tempDate);
                setUser(tempUser);

            } catch (Exception ex) {
                throw ex;
            }
        }
    }

    @Override
    public void normalize() {
        tb_ddd = tb_ddd != null ? tb_ddd.replace(" ", "").replace(";", ",") : tb_ddd;
        if (upr1_oi_cpp_minicial != null && !"".equals(upr1_oi_cpp_minicial.trim())) {
            if (!upr1_oi_cpp_minicial.toUpperCase().endsWith(".WAV")) {
                upr1_oi_cpp_minicial = upr1_oi_cpp_minicial + ".wav";
            }
            upr1_oi_cpp_minicial = upr1_oi_cpp_minicial.replace(" ", "");
        }
        if (upr1_oi_cpp_regtorpedo != null && !"".equals(upr1_oi_cpp_regtorpedo.trim())) {
            if (!upr1_oi_cpp_regtorpedo.toUpperCase().endsWith(".WAV")) {
                upr1_oi_cpp_regtorpedo = upr1_oi_cpp_regtorpedo + ".wav";
            }
            upr1_oi_cpp_regtorpedo = upr1_oi_cpp_regtorpedo.replace(" ", "");
        }
        if (upr1_oi_cpp_regligacao != null && !"".equals(upr1_oi_cpp_regligacao.trim())) {
            if (!upr1_oi_cpp_regligacao.toUpperCase().endsWith(".WAV")) {
                upr1_oi_cpp_regligacao = upr1_oi_cpp_regligacao + ".wav";
            }
            upr1_oi_cpp_regligacao = upr1_oi_cpp_regligacao.replace(" ", "");
        }
        if (upr1_oi_cppcont_regligacaooimovel != null && !"".equals(upr1_oi_cppcont_regligacaooimovel.trim())) {
            if (!upr1_oi_cppcont_regligacaooimovel.toUpperCase().endsWith(".WAV")) {
                upr1_oi_cppcont_regligacaooimovel = upr1_oi_cppcont_regligacaooimovel + ".wav";
            }
            upr1_oi_cppcont_regligacaooimovel = upr1_oi_cppcont_regligacaooimovel.replace(" ", "");
        }
        if (upr1_oi_cppcont_regligacaooimovelfixo != null && !"".equals(upr1_oi_cppcont_regligacaooimovelfixo.trim())) {
            if (!upr1_oi_cppcont_regligacaooimovelfixo.toUpperCase().endsWith(".WAV")) {
                upr1_oi_cppcont_regligacaooimovelfixo = upr1_oi_cppcont_regligacaooimovelfixo + ".wav";
            }
            upr1_oi_cppcont_regligacaooimovelfixo = upr1_oi_cppcont_regligacaooimovelfixo.replace(" ", "");
        }
        if (upr1_oi_cppcont_regligacaomix != null && !"".equals(upr1_oi_cppcont_regligacaomix.trim())) {
            if (!upr1_oi_cppcont_regligacaomix.toUpperCase().endsWith(".WAV")) {
                upr1_oi_cppcont_regligacaomix = upr1_oi_cppcont_regligacaomix + ".wav";
            }
            upr1_oi_cppcont_regligacaomix = upr1_oi_cppcont_regligacaomix.replace(" ", "");
        }
        if (upr1_oi_cpp_comousar != null && !"".equals(upr1_oi_cpp_comousar.trim())) {
            if (!upr1_oi_cpp_comousar.toUpperCase().endsWith(".WAV")) {
                upr1_oi_cpp_comousar = upr1_oi_cpp_comousar + ".wav";
            }
            upr1_oi_cpp_comousar = upr1_oi_cpp_comousar.replace(" ", "");
        }
        if (upr1_oi_crbdad5arbi_promodegrau_init2 != null && !"".equals(upr1_oi_crbdad5arbi_promodegrau_init2.trim())) {
            if (!upr1_oi_crbdad5arbi_promodegrau_init2.toUpperCase().endsWith(".WAV")) {
                upr1_oi_crbdad5arbi_promodegrau_init2 = upr1_oi_crbdad5arbi_promodegrau_init2 + ".wav";
            }
            upr1_oi_crbdad5arbi_promodegrau_init2 = upr1_oi_crbdad5arbi_promodegrau_init2.replace(" ", "");
        }
        if (upr1_oi_crbdad5d_recbonusmax_init2 != null && !"".equals(upr1_oi_crbdad5d_recbonusmax_init2.trim())) {
            if (!upr1_oi_crbdad5d_recbonusmax_init2.toUpperCase().endsWith(".WAV")) {
                upr1_oi_crbdad5d_recbonusmax_init2 = upr1_oi_crbdad5d_recbonusmax_init2 + ".wav";
            }
            upr1_oi_crbdad5d_recbonusmax_init2 = upr1_oi_crbdad5d_recbonusmax_init2.replace(" ", "");
        }
        if (upr1_oi_crbdad5d_recbonusmindegrau2_init2 != null && !"".equals(upr1_oi_crbdad5d_recbonusmindegrau2_init2.trim())) {
            if (!upr1_oi_crbdad5d_recbonusmindegrau2_init2.toUpperCase().endsWith(".WAV")) {
                upr1_oi_crbdad5d_recbonusmindegrau2_init2 = upr1_oi_crbdad5d_recbonusmindegrau2_init2 + ".wav";
            }
            upr1_oi_crbdad5d_recbonusmindegrau2_init2 = upr1_oi_crbdad5d_recbonusmindegrau2_init2.replace(" ", "");
        }
        if (upr1_oi_crbdad5d_recebonusmindegrau3_init2 != null && !"".equals(upr1_oi_crbdad5d_recebonusmindegrau3_init2.trim())) {
            if (!upr1_oi_crbdad5d_recebonusmindegrau3_init2.toUpperCase().endsWith(".WAV")) {
                upr1_oi_crbdad5d_recebonusmindegrau3_init2 = upr1_oi_crbdad5d_recebonusmindegrau3_init2 + ".wav";
            }
            upr1_oi_crbdad5d_recebonusmindegrau3_init2 = upr1_oi_crbdad5d_recebonusmindegrau3_init2.replace(" ", "");
        }
        if (upr1_oi_crbdad5d_recebebonusmindegrau2e3_init2 != null && !"".equals(upr1_oi_crbdad5d_recebebonusmindegrau2e3_init2.trim())) {
            if (!upr1_oi_crbdad5d_recebebonusmindegrau2e3_init2.toUpperCase().endsWith(".WAV")) {
                upr1_oi_crbdad5d_recebebonusmindegrau2e3_init2 = upr1_oi_crbdad5d_recebebonusmindegrau2e3_init2 + ".wav";
            }
            upr1_oi_crbdad5d_recebebonusmindegrau2e3_init2 = upr1_oi_crbdad5d_recebebonusmindegrau2e3_init2.replace(" ", "");
        }
        if (upr1_oi_crbdad5d_recebebonusmax3_init2 != null && !"".equals(upr1_oi_crbdad5d_recebebonusmax3_init2.trim())) {
            if (!upr1_oi_crbdad5d_recebebonusmax3_init2.toUpperCase().endsWith(".WAV")) {
                upr1_oi_crbdad5d_recebebonusmax3_init2 = upr1_oi_crbdad5d_recebebonusmax3_init2 + ".wav";
            }
            upr1_oi_crbdad5d_recebebonusmax3_init2 = upr1_oi_crbdad5d_recebebonusmax3_init2.replace(" ", "");
        }
        if (upr1_oi_cinfpromo_mensageminicial != null && !"".equals(upr1_oi_cinfpromo_mensageminicial.trim())) {
            if (!upr1_oi_cinfpromo_mensageminicial.toUpperCase().endsWith(".WAV")) {
                upr1_oi_cinfpromo_mensageminicial = upr1_oi_cinfpromo_mensageminicial + ".wav";
            }
            upr1_oi_cinfpromo_mensageminicial = upr1_oi_cinfpromo_mensageminicial.replace(" ", "");
        }
        if (upr1_oi_cinfpromo_regtorpedo != null && !"".equals(upr1_oi_cinfpromo_regtorpedo.trim())) {
            if (!upr1_oi_cinfpromo_regtorpedo.toUpperCase().endsWith(".WAV")) {
                upr1_oi_cinfpromo_regtorpedo = upr1_oi_cinfpromo_regtorpedo + ".wav";
            }
            upr1_oi_cinfpromo_regtorpedo = upr1_oi_cinfpromo_regtorpedo.replace(" ", "");
        }
        if (upr1_oi_cinfpromo_regligacao != null && !"".equals(upr1_oi_cinfpromo_regligacao.trim())) {
            if (!upr1_oi_cinfpromo_regligacao.toUpperCase().endsWith(".WAV")) {
                upr1_oi_cinfpromo_regligacao = upr1_oi_cinfpromo_regligacao + ".wav";
            }
            upr1_oi_cinfpromo_regligacao = upr1_oi_cinfpromo_regligacao.replace(" ", "");
        }
        if (upr1_oi_cinfpromo_comousar != null && !"".equals(upr1_oi_cinfpromo_comousar.trim())) {
            if (!upr1_oi_cinfpromo_comousar.toUpperCase().endsWith(".WAV")) {
                upr1_oi_cinfpromo_comousar = upr1_oi_cinfpromo_comousar + ".wav";
            }
            upr1_oi_cinfpromo_comousar = upr1_oi_cinfpromo_comousar.replace(" ", "");
        }
        if (upr1_oi_cinfpromo_validadepromo != null && !"".equals(upr1_oi_cinfpromo_validadepromo.trim())) {
            if (!upr1_oi_cinfpromo_validadepromo.toUpperCase().endsWith(".WAV")) {
                upr1_oi_cinfpromo_validadepromo = upr1_oi_cinfpromo_validadepromo + ".wav";
            }
            upr1_oi_cinfpromo_validadepromo = upr1_oi_cinfpromo_validadepromo.replace(" ", "");
        }
        if (upr1_oi_ccpop_promooicartao != null && !"".equals(upr1_oi_ccpop_promooicartao.trim())) {
            if (!upr1_oi_ccpop_promooicartao.toUpperCase().endsWith(".WAV")) {
                upr1_oi_ccpop_promooicartao = upr1_oi_ccpop_promooicartao + ".wav";
            }
            upr1_oi_ccpop_promooicartao = upr1_oi_ccpop_promooicartao.replace(" ", "");
        }
        if (upr1_oi_cinfp_regdados != null && !"".equals(upr1_oi_cinfp_regdados.trim())) {
            if (!upr1_oi_cinfp_regdados.toUpperCase().endsWith(".WAV")) {
                upr1_oi_cinfp_regdados = upr1_oi_cinfp_regdados + ".wav";
            }
            upr1_oi_cinfp_regdados = upr1_oi_cinfp_regdados.replace(" ", "");
        }
        if (upr1_oi_cpp_regdados != null && !"".equals(upr1_oi_cpp_regdados.trim())) {
            if (!upr1_oi_cpp_regdados.toUpperCase().endsWith(".WAV")) {
                upr1_oi_cpp_regdados = upr1_oi_cpp_regdados + ".wav";
            }
            upr1_oi_cpp_regdados = upr1_oi_cpp_regdados.replace(" ", "");
        }
        if (incentivo_oferta_pre != null && !"".equals(incentivo_oferta_pre.trim())) {
            if (!incentivo_oferta_pre.toUpperCase().endsWith(".WAV")) {
                incentivo_oferta_pre = incentivo_oferta_pre + ".wav";
            }
            incentivo_oferta_pre = incentivo_oferta_pre.replace(" ", "");
        }
        if (jacliente_sva != null && !"".equals(jacliente_sva.trim())) {
            if (!jacliente_sva.toUpperCase().endsWith(".WAV")) {
                jacliente_sva = jacliente_sva + ".wav";
            }
            jacliente_sva = jacliente_sva.replace(" ", "");
        }

    }
}
