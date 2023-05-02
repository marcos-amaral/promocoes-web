/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oi.promocoesweb.dbms.entity.User;
import com.oi.promocoesweb.template.entity.ui.InputType;
import com.oi.promocoesweb.validation.annotation.FieldInput;
import com.oi.promocoesweb.validation.annotation.FieldValidation;
import com.oi.promocoesweb.validation.annotation.Id;
import com.oi.promocoesweb.validation.enums.ValidationEnum;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author mmouraam
 */
public class PromptCartaoMenu extends Template {

    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9?9")
    private String tb_id_beneficio;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9?9")
    private String tb_id_programa;
    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    @Id
    @FieldInput(name = InputType.STRING, mask = ",99")
    private String tb_ddd;

    private String cliente_mensageminicial_ussd;
    private String cliente_regratorpedo_ussd;
    private String cliente_regraligacao_ussd;
    private String cliente_regradados_ussd;
    private String cliente_sva_ussd;
    private String cliente_comousar_ussd;
    private String regraligacaooimovel_ussd;
    private String regraligacaooimovelfixo_ussd;
    private String regraligacaomix_ussd;
    private String recebebonusiniciopromodegrau_ussd;
    private String recebebonusmax_ussd;
    private String recebebonusmindegrau2_ussd;
    private String recebebonusmindegrau3_ussd;
    private String recebebonusmindegrau2e3_ussd;
    private String recebebonusmax3_ussd;
    private String naocliente_mensageminicial_ussd;
    private String naocliente_regratorpedo_ussd;
    private String naocliente_regraligacao_ussd;
    private String naocliente_comousar_ussd;
    private String naocliente_validadepromo_ussd;
    private String naocliente_regradados_ussd;
    private String promooicartao_ussd;
    private String oferta_pre_ussd;
    private String cliente_mensageminicial_144;
    private String cliente_regratorpedo_144;
    private String cliente_regraligacao_144;
    private String cliente_regradados_144;
    private String cliente_sva_144;
    private String cliente_comousar_144;
    private String regraligacaooimovel_144;
    private String regraligacaooimovelfixo_144;
    private String regraligacaomix_144;
    private String recebebonusiniciopromodegrau_144;
    private String recebebonusmax_144;
    private String recebebonusmindegrau2_144;
    private String recebebonusmindegrau3_144;
    private String recebebonusmindegrau2e3_144;
    private String recebebonusmax3_144;
    private String naocliente_mensageminicial_144;
    private String naocliente_regratorpedo_144;
    private String naocliente_regraligacao_144;
    private String naocliente_comousar_144;
    private String naocliente_validadepromo_144;
    private String naocliente_regradados_144;
    private String promooicartao_144;
    private String oferta_pre_144;

    public PromptCartaoMenu() {
        super();
    }

    public PromptCartaoMenu(PromptCartaoMenu selectedConfigOfertasPre) {
        this.tb_id_beneficio = selectedConfigOfertasPre.getTb_id_beneficio();
        this.tb_id_programa = selectedConfigOfertasPre.getTb_id_programa();
        this.tb_ddd = selectedConfigOfertasPre.getTb_ddd();
        this.cliente_mensageminicial_ussd = selectedConfigOfertasPre.getCliente_mensageminicial_ussd();
        this.cliente_regratorpedo_ussd = selectedConfigOfertasPre.getCliente_regratorpedo_ussd();
        this.cliente_regraligacao_ussd = selectedConfigOfertasPre.getCliente_regraligacao_ussd();
        this.cliente_regradados_ussd = selectedConfigOfertasPre.getCliente_regradados_ussd();
        this.cliente_sva_ussd = selectedConfigOfertasPre.getCliente_sva_ussd();
        this.cliente_comousar_ussd = selectedConfigOfertasPre.getCliente_comousar_ussd();
        this.regraligacaooimovel_ussd = selectedConfigOfertasPre.getRegraligacaooimovel_ussd();
        this.regraligacaooimovelfixo_ussd = selectedConfigOfertasPre.getRegraligacaooimovelfixo_ussd();
        this.regraligacaomix_ussd = selectedConfigOfertasPre.getRegraligacaomix_ussd();
        this.recebebonusiniciopromodegrau_ussd = selectedConfigOfertasPre.getRecebebonusiniciopromodegrau_ussd();
        this.recebebonusmax_ussd = selectedConfigOfertasPre.getRecebebonusmax_ussd();
        this.recebebonusmindegrau2_ussd = selectedConfigOfertasPre.getRecebebonusmindegrau2_ussd();
        this.recebebonusmindegrau3_ussd = selectedConfigOfertasPre.getRecebebonusmindegrau3_ussd();
        this.recebebonusmindegrau2e3_ussd = selectedConfigOfertasPre.getRecebebonusmindegrau2e3_ussd();
        this.recebebonusmax3_ussd = selectedConfigOfertasPre.getRecebebonusmax3_ussd();
        this.naocliente_mensageminicial_ussd = selectedConfigOfertasPre.getNaocliente_mensageminicial_ussd();
        this.naocliente_regratorpedo_ussd = selectedConfigOfertasPre.getNaocliente_regratorpedo_ussd();
        this.naocliente_regraligacao_ussd = selectedConfigOfertasPre.getNaocliente_regraligacao_ussd();
        this.naocliente_comousar_ussd = selectedConfigOfertasPre.getNaocliente_comousar_ussd();
        this.naocliente_validadepromo_ussd = selectedConfigOfertasPre.getNaocliente_validadepromo_ussd();
        this.naocliente_regradados_ussd = selectedConfigOfertasPre.getNaocliente_regradados_ussd();
        this.promooicartao_ussd = selectedConfigOfertasPre.getPromooicartao_ussd();
        this.oferta_pre_ussd = selectedConfigOfertasPre.getOferta_pre_ussd();
        this.cliente_mensageminicial_144 = selectedConfigOfertasPre.getCliente_mensageminicial_144();
        this.cliente_regratorpedo_144 = selectedConfigOfertasPre.getCliente_regratorpedo_144();
        this.cliente_regraligacao_144 = selectedConfigOfertasPre.getCliente_regraligacao_144();
        this.cliente_regradados_144 = selectedConfigOfertasPre.getCliente_regradados_144();
        this.cliente_sva_144 = selectedConfigOfertasPre.getCliente_sva_144();
        this.cliente_comousar_144 = selectedConfigOfertasPre.getCliente_comousar_144();
        this.regraligacaooimovel_144 = selectedConfigOfertasPre.getRegraligacaooimovel_144();
        this.regraligacaooimovelfixo_144 = selectedConfigOfertasPre.getRegraligacaooimovelfixo_144();
        this.regraligacaomix_144 = selectedConfigOfertasPre.getRegraligacaomix_144();
        this.recebebonusiniciopromodegrau_144 = selectedConfigOfertasPre.getRecebebonusiniciopromodegrau_144();
        this.recebebonusmax_144 = selectedConfigOfertasPre.getRecebebonusmax_144();
        this.recebebonusmindegrau2_144 = selectedConfigOfertasPre.getRecebebonusmindegrau2_144();
        this.recebebonusmindegrau3_144 = selectedConfigOfertasPre.getRecebebonusmindegrau3_144();
        this.recebebonusmindegrau2e3_144 = selectedConfigOfertasPre.getRecebebonusmindegrau2e3_144();
        this.recebebonusmax3_144 = selectedConfigOfertasPre.getRecebebonusmax3_144();
        this.naocliente_mensageminicial_144 = selectedConfigOfertasPre.getNaocliente_mensageminicial_144();
        this.naocliente_regratorpedo_144 = selectedConfigOfertasPre.getNaocliente_regratorpedo_144();
        this.naocliente_regraligacao_144 = selectedConfigOfertasPre.getNaocliente_regraligacao_144();
        this.naocliente_comousar_144 = selectedConfigOfertasPre.getNaocliente_comousar_144();
        this.naocliente_validadepromo_144 = selectedConfigOfertasPre.getNaocliente_validadepromo_144();
        this.naocliente_regradados_144 = selectedConfigOfertasPre.getNaocliente_regradados_144();
        this.promooicartao_144 = selectedConfigOfertasPre.getPromooicartao_144();
        this.oferta_pre_144 = selectedConfigOfertasPre.getOferta_pre_144();

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
    public boolean isEditCliente_mensageminicial_ussd() {
        return super.getEditMap().getOrDefault("CLIENTE_MENSAGEMINICIAL_USSD", false);
    }

    @JsonIgnore
    public boolean isEditCliente_regratorpedo_ussd() {
        return super.getEditMap().getOrDefault("CLIENTE_REGRATORPEDO_USSD", false);
    }

    @JsonIgnore
    public boolean isEditCliente_regraligacao_ussd() {
        return super.getEditMap().getOrDefault("CLIENTE_REGRALIGACAO_USSD", false);
    }

    @JsonIgnore
    public boolean isEditCliente_regradados_ussd() {
        return super.getEditMap().getOrDefault("CLIENTE_REGRADADOS_USSD", false);
    }

    @JsonIgnore
    public boolean isEditCliente_sva_ussd() {
        return super.getEditMap().getOrDefault("CLIENTE_SVA_USSD", false);
    }

    @JsonIgnore
    public boolean isEditCliente_comousar_ussd() {
        return super.getEditMap().getOrDefault("CLIENTE_COMOUSAR_USSD", false);
    }

    @JsonIgnore
    public boolean isEditRegraligacaooimovel_ussd() {
        return super.getEditMap().getOrDefault("REGRALIGACAOOIMOVEL_USSD", false);
    }

    @JsonIgnore
    public boolean isEditRegraligacaooimovelfixo_ussd() {
        return super.getEditMap().getOrDefault("REGRALIGACAOOIMOVELFIXO_USSD", false);
    }

    @JsonIgnore
    public boolean isEditRegraligacaomix_ussd() {
        return super.getEditMap().getOrDefault("REGRALIGACAOMIX_USSD", false);
    }

    @JsonIgnore
    public boolean isEditRecebebonusiniciopromodegrau_ussd() {
        return super.getEditMap().getOrDefault("RECEBEBONUSINICIOPROMODEGRAU_USSD", false);
    }

    @JsonIgnore
    public boolean isEditRecebebonusmax_ussd() {
        return super.getEditMap().getOrDefault("RECEBEBONUSMAX_USSD", false);
    }

    @JsonIgnore
    public boolean isEditRecebebonusmindegrau2_ussd() {
        return super.getEditMap().getOrDefault("RECEBEBONUSMINDEGRAU2_USSD", false);
    }

    @JsonIgnore
    public boolean isEditRecebebonusmindegrau3_ussd() {
        return super.getEditMap().getOrDefault("RECEBEBONUSMINDEGRAU3_USSD", false);
    }

    @JsonIgnore
    public boolean isEditRecebebonusmindegrau2e3_ussd() {
        return super.getEditMap().getOrDefault("RECEBEBONUSMINDEGRAU2E3_USSD", false);
    }

    @JsonIgnore
    public boolean isEditRecebebonusmax3_ussd() {
        return super.getEditMap().getOrDefault("RECEBEBONUSMAX3_USSD", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_mensageminicial_ussd() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_MENSAGEMINICIAL_USSD", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_regratorpedo_ussd() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_REGRATORPEDO_USSD", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_regraligacao_ussd() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_REGRALIGACAO_USSD", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_comousar_ussd() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_COMOUSAR_USSD", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_validadepromo_ussd() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_VALIDADEPROMO_USSD", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_regradados_ussd() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_REGRADADOS_USSD", false);
    }

    @JsonIgnore
    public boolean isEditPromooicartao_ussd() {
        return super.getEditMap().getOrDefault("PROMOOICARTAO_USSD", false);
    }

    @JsonIgnore
    public boolean isEditOferta_pre_ussd() {
        return super.getEditMap().getOrDefault("OFERTA_PRE_USSD", false);
    }

    public void setEditCliente_mensageminicial_ussd(boolean checked) {
        super.getEditMap().put("CLIENTE_MENSAGEMINICIAL_USSD", checked);
    }

    public void setEditCliente_regratorpedo_ussd(boolean checked) {
        super.getEditMap().put("CLIENTE_REGRATORPEDO_USSD", checked);
    }

    public void setEditCliente_regraligacao_ussd(boolean checked) {
        super.getEditMap().put("CLIENTE_REGRALIGACAO_USSD", checked);
    }

    public void setEditCliente_regradados_ussd(boolean checked) {
        super.getEditMap().put("CLIENTE_REGRADADOS_USSD", checked);
    }

    public void setEditCliente_sva_ussd(boolean checked) {
        super.getEditMap().put("CLIENTE_SVA_USSD", checked);
    }

    public void setEditCliente_comousar_ussd(boolean checked) {
        super.getEditMap().put("CLIENTE_COMOUSAR_USSD", checked);
    }

    public void setEditRegraligacaooimovel_ussd(boolean checked) {
        super.getEditMap().put("REGRALIGACAOOIMOVEL_USSD", checked);
    }

    public void setEditRegraligacaooimovelfixo_ussd(boolean checked) {
        super.getEditMap().put("REGRALIGACAOOIMOVELFIXO_USSD", checked);
    }

    public void setEditRegraligacaomix_ussd(boolean checked) {
        super.getEditMap().put("REGRALIGACAOMIX_USSD", checked);
    }

    public void setEditRecebebonusiniciopromodegrau_ussd(boolean checked) {
        super.getEditMap().put("RECEBEBONUSINICIOPROMODEGRAU_USSD", checked);
    }

    public void setEditRecebebonusmax_ussd(boolean checked) {
        super.getEditMap().put("RECEBEBONUSMAX_USSD", checked);
    }

    public void setEditRecebebonusmindegrau2_ussd(boolean checked) {
        super.getEditMap().put("RECEBEBONUSMINDEGRAU2_USSD", checked);
    }

    public void setEditRecebebonusmindegrau3_ussd(boolean checked) {
        super.getEditMap().put("RECEBEBONUSMINDEGRAU3_USSD", checked);
    }

    public void setEditRecebebonusmindegrau2e3_ussd(boolean checked) {
        super.getEditMap().put("RECEBEBONUSMINDEGRAU2E3_USSD", checked);
    }

    public void setEditRecebebonusmax3_ussd(boolean checked) {
        super.getEditMap().put("RECEBEBONUSMAX3_USSD", checked);
    }

    public void setEditNaocliente_mensageminicial_ussd(boolean checked) {
        super.getEditMap().put("NAOCLIENTE_MENSAGEMINICIAL_USSD", checked);
    }

    public void setEditNaocliente_regratorpedo_ussd(boolean checked) {
        super.getEditMap().put("NAOCLIENTE_REGRATORPEDO_USSD", checked);
    }

    public void setEditNaocliente_regraligacao_ussd(boolean checked) {
        super.getEditMap().put("NAOCLIENTE_REGRALIGACAO_USSD", checked);
    }

    public void setEditNaocliente_comousar_ussd(boolean checked) {
        super.getEditMap().put("NAOCLIENTE_COMOUSAR_USSD", checked);
    }

    public void setEditNaocliente_validadepromo_ussd(boolean checked) {
        super.getEditMap().put("NAOCLIENTE_VALIDADEPROMO_USSD", checked);
    }

    public void setEditNaocliente_regradados_ussd(boolean checked) {
        super.getEditMap().put("NAOCLIENTE_REGRADADOS_USSD", checked);
    }

    public void setEditPromooicartao_ussd(boolean checked) {
        super.getEditMap().put("PROMOOICARTAO_USSD", checked);
    }

    public void setEditOferta_pre_ussd(boolean checked) {
        super.getEditMap().put("OFERTA_PRE_USSD", checked);
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

    public String getTb_ddd() {
        return tb_ddd;
    }

    public void setTb_ddd(String tb_ddd) {
        this.tb_ddd = tb_ddd;
    }

    public String getCliente_mensageminicial_ussd() {
        return cliente_mensageminicial_ussd;
    }

    public void setCliente_mensageminicial_ussd(String cliente_mensageminicial_ussd) {
        this.cliente_mensageminicial_ussd = cliente_mensageminicial_ussd;
    }

    public String getCliente_regratorpedo_ussd() {
        return cliente_regratorpedo_ussd;
    }

    public void setCliente_regratorpedo_ussd(String cliente_regratorpedo_ussd) {
        this.cliente_regratorpedo_ussd = cliente_regratorpedo_ussd;
    }

    public String getCliente_regraligacao_ussd() {
        return cliente_regraligacao_ussd;
    }

    public void setCliente_regraligacao_ussd(String cliente_regraligacao_ussd) {
        this.cliente_regraligacao_ussd = cliente_regraligacao_ussd;
    }

    public String getCliente_regradados_ussd() {
        return cliente_regradados_ussd;
    }

    public void setCliente_regradados_ussd(String cliente_regradados_ussd) {
        this.cliente_regradados_ussd = cliente_regradados_ussd;
    }

    public String getCliente_sva_ussd() {
        return cliente_sva_ussd;
    }

    public void setCliente_sva_ussd(String cliente_sva_ussd) {
        this.cliente_sva_ussd = cliente_sva_ussd;
    }

    public String getCliente_comousar_ussd() {
        return cliente_comousar_ussd;
    }

    public void setCliente_comousar_ussd(String cliente_comousar_ussd) {
        this.cliente_comousar_ussd = cliente_comousar_ussd;
    }

    public String getRegraligacaooimovel_ussd() {
        return regraligacaooimovel_ussd;
    }

    public void setRegraligacaooimovel_ussd(String regraligacaooimovel_ussd) {
        this.regraligacaooimovel_ussd = regraligacaooimovel_ussd;
    }

    public String getRegraligacaooimovelfixo_ussd() {
        return regraligacaooimovelfixo_ussd;
    }

    public void setRegraligacaooimovelfixo_ussd(String regraligacaooimovelfixo_ussd) {
        this.regraligacaooimovelfixo_ussd = regraligacaooimovelfixo_ussd;
    }

    public String getRegraligacaomix_ussd() {
        return regraligacaomix_ussd;
    }

    public void setRegraligacaomix_ussd(String regraligacaomix_ussd) {
        this.regraligacaomix_ussd = regraligacaomix_ussd;
    }

    public String getRecebebonusiniciopromodegrau_ussd() {
        return recebebonusiniciopromodegrau_ussd;
    }

    public void setRecebebonusiniciopromodegrau_ussd(String recebebonusiniciopromodegrau_ussd) {
        this.recebebonusiniciopromodegrau_ussd = recebebonusiniciopromodegrau_ussd;
    }

    public String getRecebebonusmax_ussd() {
        return recebebonusmax_ussd;
    }

    public void setRecebebonusmax_ussd(String recebebonusmax_ussd) {
        this.recebebonusmax_ussd = recebebonusmax_ussd;
    }

    public String getRecebebonusmindegrau2_ussd() {
        return recebebonusmindegrau2_ussd;
    }

    public void setRecebebonusmindegrau2_ussd(String recebebonusmindegrau2_ussd) {
        this.recebebonusmindegrau2_ussd = recebebonusmindegrau2_ussd;
    }

    public String getRecebebonusmindegrau3_ussd() {
        return recebebonusmindegrau3_ussd;
    }

    public void setRecebebonusmindegrau3_ussd(String recebebonusmindegrau3_ussd) {
        this.recebebonusmindegrau3_ussd = recebebonusmindegrau3_ussd;
    }

    public String getRecebebonusmindegrau2e3_ussd() {
        return recebebonusmindegrau2e3_ussd;
    }

    public void setRecebebonusmindegrau2e3_ussd(String recebebonusmindegrau2e3_ussd) {
        this.recebebonusmindegrau2e3_ussd = recebebonusmindegrau2e3_ussd;
    }

    public String getRecebebonusmax3_ussd() {
        return recebebonusmax3_ussd;
    }

    public void setRecebebonusmax3_ussd(String recebebonusmax3_ussd) {
        this.recebebonusmax3_ussd = recebebonusmax3_ussd;
    }

    public String getNaocliente_mensageminicial_ussd() {
        return naocliente_mensageminicial_ussd;
    }

    public void setNaocliente_mensageminicial_ussd(String naocliente_mensageminicial_ussd) {
        this.naocliente_mensageminicial_ussd = naocliente_mensageminicial_ussd;
    }

    public String getNaocliente_regratorpedo_ussd() {
        return naocliente_regratorpedo_ussd;
    }

    public void setNaocliente_regratorpedo_ussd(String naocliente_regratorpedo_ussd) {
        this.naocliente_regratorpedo_ussd = naocliente_regratorpedo_ussd;
    }

    public String getNaocliente_regraligacao_ussd() {
        return naocliente_regraligacao_ussd;
    }

    public void setNaocliente_regraligacao_ussd(String naocliente_regraligacao_ussd) {
        this.naocliente_regraligacao_ussd = naocliente_regraligacao_ussd;
    }

    public String getNaocliente_comousar_ussd() {
        return naocliente_comousar_ussd;
    }

    public void setNaocliente_comousar_ussd(String naocliente_comousar_ussd) {
        this.naocliente_comousar_ussd = naocliente_comousar_ussd;
    }

    public String getNaocliente_validadepromo_ussd() {
        return naocliente_validadepromo_ussd;
    }

    public void setNaocliente_validadepromo_ussd(String naocliente_validadepromo_ussd) {
        this.naocliente_validadepromo_ussd = naocliente_validadepromo_ussd;
    }

    public String getNaocliente_regradados_ussd() {
        return naocliente_regradados_ussd;
    }

    public void setNaocliente_regradados_ussd(String naocliente_regradados_ussd) {
        this.naocliente_regradados_ussd = naocliente_regradados_ussd;
    }

    public String getPromooicartao_ussd() {
        return promooicartao_ussd;
    }

    public void setPromooicartao_ussd(String promooicartao_ussd) {
        this.promooicartao_ussd = promooicartao_ussd;
    }

    public String getOferta_pre_ussd() {
        return oferta_pre_ussd;
    }

    public void setOferta_pre_ussd(String oferta_pre_ussd) {
        this.oferta_pre_ussd = oferta_pre_ussd;
    }

    public String getCliente_mensageminicial_144() {
        return cliente_mensageminicial_144;
    }

    public void setCliente_mensageminicial_144(String cliente_mensageminicial_144) {
        this.cliente_mensageminicial_144 = cliente_mensageminicial_144;
    }

    public String getCliente_regratorpedo_144() {
        return cliente_regratorpedo_144;
    }

    public void setCliente_regratorpedo_144(String cliente_regratorpedo_144) {
        this.cliente_regratorpedo_144 = cliente_regratorpedo_144;
    }

    public String getCliente_regraligacao_144() {
        return cliente_regraligacao_144;
    }

    public void setCliente_regraligacao_144(String cliente_regraligacao_144) {
        this.cliente_regraligacao_144 = cliente_regraligacao_144;
    }

    public String getCliente_regradados_144() {
        return cliente_regradados_144;
    }

    public void setCliente_regradados_144(String cliente_regradados_144) {
        this.cliente_regradados_144 = cliente_regradados_144;
    }

    public String getCliente_sva_144() {
        return cliente_sva_144;
    }

    public void setCliente_sva_144(String cliente_sva_144) {
        this.cliente_sva_144 = cliente_sva_144;
    }

    public String getCliente_comousar_144() {
        return cliente_comousar_144;
    }

    public void setCliente_comousar_144(String cliente_comousar_144) {
        this.cliente_comousar_144 = cliente_comousar_144;
    }

    public String getRegraligacaooimovel_144() {
        return regraligacaooimovel_144;
    }

    public void setRegraligacaooimovel_144(String regraligacaooimovel_144) {
        this.regraligacaooimovel_144 = regraligacaooimovel_144;
    }

    public String getRegraligacaooimovelfixo_144() {
        return regraligacaooimovelfixo_144;
    }

    public void setRegraligacaooimovelfixo_144(String regraligacaooimovelfixo_144) {
        this.regraligacaooimovelfixo_144 = regraligacaooimovelfixo_144;
    }

    public String getRegraligacaomix_144() {
        return regraligacaomix_144;
    }

    public void setRegraligacaomix_144(String regraligacaomix_144) {
        this.regraligacaomix_144 = regraligacaomix_144;
    }

    public String getRecebebonusiniciopromodegrau_144() {
        return recebebonusiniciopromodegrau_144;
    }

    public void setRecebebonusiniciopromodegrau_144(String recebebonusiniciopromodegrau_144) {
        this.recebebonusiniciopromodegrau_144 = recebebonusiniciopromodegrau_144;
    }

    public String getRecebebonusmax_144() {
        return recebebonusmax_144;
    }

    public void setRecebebonusmax_144(String recebebonusmax_144) {
        this.recebebonusmax_144 = recebebonusmax_144;
    }

    public String getRecebebonusmindegrau2_144() {
        return recebebonusmindegrau2_144;
    }

    public void setRecebebonusmindegrau2_144(String recebebonusmindegrau2_144) {
        this.recebebonusmindegrau2_144 = recebebonusmindegrau2_144;
    }

    public String getRecebebonusmindegrau3_144() {
        return recebebonusmindegrau3_144;
    }

    public void setRecebebonusmindegrau3_144(String recebebonusmindegrau3_144) {
        this.recebebonusmindegrau3_144 = recebebonusmindegrau3_144;
    }

    public String getRecebebonusmindegrau2e3_144() {
        return recebebonusmindegrau2e3_144;
    }

    public void setRecebebonusmindegrau2e3_144(String recebebonusmindegrau2e3_144) {
        this.recebebonusmindegrau2e3_144 = recebebonusmindegrau2e3_144;
    }

    public String getRecebebonusmax3_144() {
        return recebebonusmax3_144;
    }

    public void setRecebebonusmax3_144(String recebebonusmax3_144) {
        this.recebebonusmax3_144 = recebebonusmax3_144;
    }

    public String getNaocliente_mensageminicial_144() {
        return naocliente_mensageminicial_144;
    }

    public void setNaocliente_mensageminicial_144(String naocliente_mensageminicial_144) {
        this.naocliente_mensageminicial_144 = naocliente_mensageminicial_144;
    }

    public String getNaocliente_regratorpedo_144() {
        return naocliente_regratorpedo_144;
    }

    public void setNaocliente_regratorpedo_144(String naocliente_regratorpedo_144) {
        this.naocliente_regratorpedo_144 = naocliente_regratorpedo_144;
    }

    public String getNaocliente_regraligacao_144() {
        return naocliente_regraligacao_144;
    }

    public void setNaocliente_regraligacao_144(String naocliente_regraligacao_144) {
        this.naocliente_regraligacao_144 = naocliente_regraligacao_144;
    }

    public String getNaocliente_comousar_144() {
        return naocliente_comousar_144;
    }

    public void setNaocliente_comousar_144(String naocliente_comousar_144) {
        this.naocliente_comousar_144 = naocliente_comousar_144;
    }

    public String getNaocliente_validadepromo_144() {
        return naocliente_validadepromo_144;
    }

    public void setNaocliente_validadepromo_144(String naocliente_validadepromo_144) {
        this.naocliente_validadepromo_144 = naocliente_validadepromo_144;
    }

    public String getNaocliente_regradados_144() {
        return naocliente_regradados_144;
    }

    public void setNaocliente_regradados_144(String naocliente_regradados_144) {
        this.naocliente_regradados_144 = naocliente_regradados_144;
    }

    public String getPromooicartao_144() {
        return promooicartao_144;
    }

    public void setPromooicartao_144(String promooicartao_144) {
        this.promooicartao_144 = promooicartao_144;
    }

    public String getOferta_pre_144() {
        return oferta_pre_144;
    }

    public void setOferta_pre_144(String oferta_pre_144) {
        this.oferta_pre_144 = oferta_pre_144;
    }

    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                PromptCartaoMenu[] pp1 = mapper.readValue(history, PromptCartaoMenu[].class);
                setHistory(Arrays.asList(mapper.readValue(history, PromptCartaoMenu[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (PromptCartaoMenu template : pp1) {
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
        if (cliente_mensageminicial_ussd != null && !"".equals(cliente_mensageminicial_ussd.trim())) {
            if (!cliente_mensageminicial_ussd.toUpperCase().endsWith(".WAV")) {
                cliente_mensageminicial_ussd = cliente_mensageminicial_ussd + ".wav";
            }
            cliente_mensageminicial_ussd = cliente_mensageminicial_ussd.replace(" ", "");
        }
        if (cliente_regratorpedo_ussd != null && !"".equals(cliente_regratorpedo_ussd.trim())) {
            if (!cliente_regratorpedo_ussd.toUpperCase().endsWith(".WAV")) {
                cliente_regratorpedo_ussd = cliente_regratorpedo_ussd + ".wav";
            }
            cliente_regratorpedo_ussd = cliente_regratorpedo_ussd.replace(" ", "");
        }
        if (cliente_regraligacao_ussd != null && !"".equals(cliente_regraligacao_ussd.trim())) {
            if (!cliente_regraligacao_ussd.toUpperCase().endsWith(".WAV")) {
                cliente_regraligacao_ussd = cliente_regraligacao_ussd + ".wav";
            }
            cliente_regraligacao_ussd = cliente_regraligacao_ussd.replace(" ", "");
        }
        if (cliente_regradados_ussd != null && !"".equals(cliente_regradados_ussd.trim())) {
            if (!cliente_regradados_ussd.toUpperCase().endsWith(".WAV")) {
                cliente_regradados_ussd = cliente_regradados_ussd + ".wav";
            }
            cliente_regradados_ussd = cliente_regradados_ussd.replace(" ", "");
        }
        if (cliente_sva_ussd != null && !"".equals(cliente_sva_ussd.trim())) {
            if (!cliente_sva_ussd.toUpperCase().endsWith(".WAV")) {
                cliente_sva_ussd = cliente_sva_ussd + ".wav";
            }
            cliente_sva_ussd = cliente_sva_ussd.replace(" ", "");
        }
        if (cliente_comousar_ussd != null && !"".equals(cliente_comousar_ussd.trim())) {
            if (!cliente_comousar_ussd.toUpperCase().endsWith(".WAV")) {
                cliente_comousar_ussd = cliente_comousar_ussd + ".wav";
            }
            cliente_comousar_ussd = cliente_comousar_ussd.replace(" ", "");
        }
        if (regraligacaooimovel_ussd != null && !"".equals(regraligacaooimovel_ussd.trim())) {
            if (!regraligacaooimovel_ussd.toUpperCase().endsWith(".WAV")) {
                regraligacaooimovel_ussd = regraligacaooimovel_ussd + ".wav";
            }
            regraligacaooimovel_ussd = regraligacaooimovel_ussd.replace(" ", "");
        }
        if (regraligacaooimovelfixo_ussd != null && !"".equals(regraligacaooimovelfixo_ussd.trim())) {
            if (!regraligacaooimovelfixo_ussd.toUpperCase().endsWith(".WAV")) {
                regraligacaooimovelfixo_ussd = regraligacaooimovelfixo_ussd + ".wav";
            }
            regraligacaooimovelfixo_ussd = regraligacaooimovelfixo_ussd.replace(" ", "");
        }
        if (regraligacaomix_ussd != null && !"".equals(regraligacaomix_ussd.trim())) {
            if (!regraligacaomix_ussd.toUpperCase().endsWith(".WAV")) {
                regraligacaomix_ussd = regraligacaomix_ussd + ".wav";
            }
            regraligacaomix_ussd = regraligacaomix_ussd.replace(" ", "");
        }
        if (recebebonusiniciopromodegrau_ussd != null && !"".equals(recebebonusiniciopromodegrau_ussd.trim())) {
            if (!recebebonusiniciopromodegrau_ussd.toUpperCase().endsWith(".WAV")) {
                recebebonusiniciopromodegrau_ussd = recebebonusiniciopromodegrau_ussd + ".wav";
            }
            recebebonusiniciopromodegrau_ussd = recebebonusiniciopromodegrau_ussd.replace(" ", "");
        }
        if (recebebonusmax_ussd != null && !"".equals(recebebonusmax_ussd.trim())) {
            if (!recebebonusmax_ussd.toUpperCase().endsWith(".WAV")) {
                recebebonusmax_ussd = recebebonusmax_ussd + ".wav";
            }
            recebebonusmax_ussd = recebebonusmax_ussd.replace(" ", "");
        }
        if (recebebonusmindegrau2_ussd != null && !"".equals(recebebonusmindegrau2_ussd.trim())) {
            if (!recebebonusmindegrau2_ussd.toUpperCase().endsWith(".WAV")) {
                recebebonusmindegrau2_ussd = recebebonusmindegrau2_ussd + ".wav";
            }
            recebebonusmindegrau2_ussd = recebebonusmindegrau2_ussd.replace(" ", "");
        }
        if (recebebonusmindegrau3_ussd != null && !"".equals(recebebonusmindegrau3_ussd.trim())) {
            if (!recebebonusmindegrau3_ussd.toUpperCase().endsWith(".WAV")) {
                recebebonusmindegrau3_ussd = recebebonusmindegrau3_ussd + ".wav";
            }
            recebebonusmindegrau3_ussd = recebebonusmindegrau3_ussd.replace(" ", "");
        }
        if (recebebonusmindegrau2e3_ussd != null && !"".equals(recebebonusmindegrau2e3_ussd.trim())) {
            if (!recebebonusmindegrau2e3_ussd.toUpperCase().endsWith(".WAV")) {
                recebebonusmindegrau2e3_ussd = recebebonusmindegrau2e3_ussd + ".wav";
            }
            recebebonusmindegrau2e3_ussd = recebebonusmindegrau2e3_ussd.replace(" ", "");
        }
        if (recebebonusmax3_ussd != null && !"".equals(recebebonusmax3_ussd.trim())) {
            if (!recebebonusmax3_ussd.toUpperCase().endsWith(".WAV")) {
                recebebonusmax3_ussd = recebebonusmax3_ussd + ".wav";
            }
            recebebonusmax3_ussd = recebebonusmax3_ussd.replace(" ", "");
        }
        if (naocliente_mensageminicial_ussd != null && !"".equals(naocliente_mensageminicial_ussd.trim())) {
            if (!naocliente_mensageminicial_ussd.toUpperCase().endsWith(".WAV")) {
                naocliente_mensageminicial_ussd = naocliente_mensageminicial_ussd + ".wav";
            }
            naocliente_mensageminicial_ussd = naocliente_mensageminicial_ussd.replace(" ", "");
        }
        if (naocliente_regratorpedo_ussd != null && !"".equals(naocliente_regratorpedo_ussd.trim())) {
            if (!naocliente_regratorpedo_ussd.toUpperCase().endsWith(".WAV")) {
                naocliente_regratorpedo_ussd = naocliente_regratorpedo_ussd + ".wav";
            }
            naocliente_regratorpedo_ussd = naocliente_regratorpedo_ussd.replace(" ", "");
        }
        if (naocliente_regraligacao_ussd != null && !"".equals(naocliente_regraligacao_ussd.trim())) {
            if (!naocliente_regraligacao_ussd.toUpperCase().endsWith(".WAV")) {
                naocliente_regraligacao_ussd = naocliente_regraligacao_ussd + ".wav";
            }
            naocliente_regraligacao_ussd = naocliente_regraligacao_ussd.replace(" ", "");
        }
        if (naocliente_comousar_ussd != null && !"".equals(naocliente_comousar_ussd.trim())) {
            if (!naocliente_comousar_ussd.toUpperCase().endsWith(".WAV")) {
                naocliente_comousar_ussd = naocliente_comousar_ussd + ".wav";
            }
            naocliente_comousar_ussd = naocliente_comousar_ussd.replace(" ", "");
        }
        if (naocliente_validadepromo_ussd != null && !"".equals(naocliente_validadepromo_ussd.trim())) {
            if (!naocliente_validadepromo_ussd.toUpperCase().endsWith(".WAV")) {
                naocliente_validadepromo_ussd = naocliente_validadepromo_ussd + ".wav";
            }
            naocliente_validadepromo_ussd = naocliente_validadepromo_ussd.replace(" ", "");
        }
        if (naocliente_regradados_ussd != null && !"".equals(naocliente_regradados_ussd.trim())) {
            if (!naocliente_regradados_ussd.toUpperCase().endsWith(".WAV")) {
                naocliente_regradados_ussd = naocliente_regradados_ussd + ".wav";
            }
            naocliente_regradados_ussd = naocliente_regradados_ussd.replace(" ", "");
        }
        if (promooicartao_ussd != null && !"".equals(promooicartao_ussd.trim())) {
            if (!promooicartao_ussd.toUpperCase().endsWith(".WAV")) {
                promooicartao_ussd = promooicartao_ussd + ".wav";
            }
            promooicartao_ussd = promooicartao_ussd.replace(" ", "");
        }
        if (oferta_pre_ussd != null && !"".equals(oferta_pre_ussd.trim())) {
            if (!oferta_pre_ussd.toUpperCase().endsWith(".WAV")) {
                oferta_pre_ussd = oferta_pre_ussd + ".wav";
            }
            oferta_pre_ussd = oferta_pre_ussd.replace(" ", "");
        }

    }
}
