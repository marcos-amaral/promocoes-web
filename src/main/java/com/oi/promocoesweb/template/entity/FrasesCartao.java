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
import javax.json.JsonObject;

/**
 *
 * @author mmouraam
 */
public class FrasesCartao extends Template {

    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    @Id
    @FieldInput(name = InputType.STRING, mask = ",99")
    private String ddd;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9?9")
    private String idbeneficio;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9?9")
    private String idprograma;
    @FieldInput(name = InputType.FRASE, maxLength = 159)
    private String promopadrao_ussd;
    @FieldInput(name = InputType.FRASE, maxLength = 159)
    private String semrecarga_ussd;
    @FieldInput(name = InputType.FRASE, maxLength = 159)
    private String chamada_ussd;
    @FieldInput(name = InputType.FRASE, maxLength = 159)
    private String detalhes_ussd;
    @FieldInput(name = InputType.FRASE, maxLength = 159)
    private String recarga_ussd;
    @Deprecated
    private String informavalidade_ussd;
    @FieldInput(name = InputType.FRASE, maxLength = 123)
    private String validade_ussd;
    @Deprecated
    private String contingencia_ussd;
    @FieldInput(name = InputType.FRASE, maxLength = 116)
    private String naocliente_informapromo_ussd;
    @Deprecated
    private String naocliente_validade_ussd;
    @Deprecated
    private String regras_ussd;
    @Deprecated
    private String naocliente_recarga_ussd;
    @FieldInput(name = InputType.FRASE, maxLength = 50)
    private String incentivo_oferta_pre_ussd;
    @FieldInput(name = InputType.FRASE, maxLength = 159)
    private String fallback_ussd;
    @FieldInput(name = InputType.FRASE, maxLength = 118)
    private String sva_ussd;
    @FieldInput(name = InputType.FRASE, maxLength = 159)
    private String informasaldopromo_ussd;
    @Deprecated
    private String ofertamigrados_ussd;
    @Deprecated
    private String informapromo_ussd_ussd;
    @FieldInput(name = InputType.FRASE)
    private String promopadrao_144;
    @FieldInput(name = InputType.FRASE)
    private String semrecarga_144;
    @FieldInput(name = InputType.FRASE)
    private String chamada_144;
    @FieldInput(name = InputType.FRASE)
    private String detalhes_144;
    @FieldInput(name = InputType.FRASE)
    private String recarga_144;
    @Deprecated
    private String informavalidade_144;
    @FieldInput(name = InputType.FRASE)
    private String validade_144;
    @FieldInput(name = InputType.FRASE)
    private String contingencia_144;
    @FieldInput(name = InputType.FRASE)
    private String naocliente_informapromo_144;
    @FieldInput(name = InputType.FRASE)
    private String naocliente_validade_144;
    @FieldInput(name = InputType.FRASE)
    private String regras_144;
    @FieldInput(name = InputType.FRASE)
    private String naocliente_recarga_144;
    @FieldInput(name = InputType.FRASE)
    private String incentivo_oferta_pre_144;
    @FieldInput(name = InputType.FRASE)
    private String fallback_144;
    @FieldInput(name = InputType.FRASE)
    private String sva_144;
    @FieldInput(name = InputType.FRASE)
    private String informasaldopromo_144;
    @FieldInput(name = InputType.FRASE)
    private String ofertamigrados_144;
    @Deprecated
    private String informapromo_ussd_144;

    public FrasesCartao() {
        super();
    }

    public FrasesCartao(FrasesCartao selectedConfigOfertasPre) {
        this.ddd = selectedConfigOfertasPre.getDdd();
        this.idbeneficio = selectedConfigOfertasPre.getIdbeneficio();
        this.idprograma = selectedConfigOfertasPre.getIdprograma();
        this.promopadrao_ussd = selectedConfigOfertasPre.getPromopadrao_ussd();
        this.semrecarga_ussd = selectedConfigOfertasPre.getSemrecarga_ussd();
        this.chamada_ussd = selectedConfigOfertasPre.getChamada_ussd();
        this.detalhes_ussd = selectedConfigOfertasPre.getDetalhes_ussd();
        this.recarga_ussd = selectedConfigOfertasPre.getRecarga_ussd();
        this.informavalidade_ussd = selectedConfigOfertasPre.getInformavalidade_ussd();
        this.validade_ussd = selectedConfigOfertasPre.getValidade_ussd();
        this.contingencia_ussd = selectedConfigOfertasPre.getContingencia_ussd();
        this.naocliente_informapromo_ussd = selectedConfigOfertasPre.getNaocliente_informapromo_ussd();
        this.naocliente_validade_ussd = selectedConfigOfertasPre.getNaocliente_validade_ussd();
        this.regras_ussd = selectedConfigOfertasPre.getRegras_ussd();
        this.naocliente_recarga_ussd = selectedConfigOfertasPre.getNaocliente_recarga_ussd();
        this.incentivo_oferta_pre_ussd = selectedConfigOfertasPre.getIncentivo_oferta_pre_ussd();
        this.fallback_ussd = selectedConfigOfertasPre.getFallback_ussd();
        this.sva_ussd = selectedConfigOfertasPre.getSva_ussd();
        this.informasaldopromo_ussd = selectedConfigOfertasPre.getInformasaldopromo_ussd();
        this.ofertamigrados_ussd = selectedConfigOfertasPre.getOfertamigrados_ussd();
        this.informapromo_ussd_ussd = selectedConfigOfertasPre.getInformapromo_ussd_ussd();
        this.promopadrao_144 = selectedConfigOfertasPre.getPromopadrao_144();
        this.semrecarga_144 = selectedConfigOfertasPre.getSemrecarga_144();
        this.chamada_144 = selectedConfigOfertasPre.getChamada_144();
        this.detalhes_144 = selectedConfigOfertasPre.getDetalhes_144();
        this.recarga_144 = selectedConfigOfertasPre.getRecarga_144();
        this.informavalidade_144 = selectedConfigOfertasPre.getInformavalidade_144();
        this.validade_144 = selectedConfigOfertasPre.getValidade_144();
        this.contingencia_144 = selectedConfigOfertasPre.getContingencia_144();
        this.naocliente_informapromo_144 = selectedConfigOfertasPre.getNaocliente_informapromo_144();
        this.naocliente_validade_144 = selectedConfigOfertasPre.getNaocliente_validade_144();
        this.regras_144 = selectedConfigOfertasPre.getRegras_144();
        this.naocliente_recarga_144 = selectedConfigOfertasPre.getNaocliente_recarga_144();
        this.incentivo_oferta_pre_144 = selectedConfigOfertasPre.getIncentivo_oferta_pre_144();
        this.fallback_144 = selectedConfigOfertasPre.getFallback_144();
        this.sva_144 = selectedConfigOfertasPre.getSva_144();
        this.informasaldopromo_144 = selectedConfigOfertasPre.getInformasaldopromo_144();
        this.ofertamigrados_144 = selectedConfigOfertasPre.getOfertamigrados_144();
        this.informapromo_ussd_144 = selectedConfigOfertasPre.getInformapromo_ussd_144();
    }

    @JsonIgnore
    public String getIdDdd() {
        StringBuilder sb = new StringBuilder();
        if (ddd != null && ddd.length() >= 2) {
            return sb.append(idbeneficio).append(".").append(idprograma).append(" | DDD: ").append(ddd.substring(0, 2)).append("...").toString();
        } else {
            return sb.append(idbeneficio).append(".").append(idprograma).append(" | DDD: null").toString();
        }
    }

    public String getHistory(String field) {
        JsonObject jsonObject = null;

        return jsonObject + "";
    }

    @JsonIgnore
    public boolean isEditPromopadrao_ussd() {
        return super.getEditMap().getOrDefault("PROMOPADRAO_USSD", false);
    }

    @JsonIgnore
    public boolean isEditSemrecarga_ussd() {
        return super.getEditMap().getOrDefault("SEMRECARGA_USSD", false);
    }

    @JsonIgnore
    public boolean isEditChamada_ussd() {
        return super.getEditMap().getOrDefault("CHAMADA_USSD", false);
    }

    @JsonIgnore
    public boolean isEditDetalhes_ussd() {
        return super.getEditMap().getOrDefault("DETALHES_USSD", false);
    }

    @JsonIgnore
    public boolean isEditRecarga_ussd() {
        return super.getEditMap().getOrDefault("RECARGA_USSD", false);
    }

    @JsonIgnore
    public boolean isEditInformavalidade_ussd() {
        return super.getEditMap().getOrDefault("INFORMAVALIDADE_USSD", false);
    }

    @JsonIgnore
    public boolean isEditValidade_ussd() {
        return super.getEditMap().getOrDefault("VALIDADE_USSD", false);
    }

    @JsonIgnore
    public boolean isEditContingencia_ussd() {
        return super.getEditMap().getOrDefault("CONTINGENCIA_USSD", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_informapromo_ussd() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_INFORMAPROMO_USSD", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_validade_ussd() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_VALIDADE_USSD", false);
    }

    @JsonIgnore
    public boolean isEditRegras_ussd() {
        return super.getEditMap().getOrDefault("REGRAS_USSD", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_recarga_ussd() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_RECARGA_USSD", false);
    }

    @JsonIgnore
    public boolean isEditIncentivo_oferta_pre_ussd() {
        return super.getEditMap().getOrDefault("INCENTIVO_OFERTA_PRE_USSD", false);
    }

    @JsonIgnore
    public boolean isEditFallback_ussd() {
        return super.getEditMap().getOrDefault("FALLBACK_USSD", false);
    }

    @JsonIgnore
    public boolean isEditSva_ussd() {
        return super.getEditMap().getOrDefault("SVA_USSD", false);
    }

    @JsonIgnore
    public boolean isEditInformasaldopromo_ussd() {
        return super.getEditMap().getOrDefault("INFORMASALDOPROMO_USSD", false);
    }

    @JsonIgnore
    public boolean isEditOfertamigrados_ussd() {
        return super.getEditMap().getOrDefault("OFERTAMIGRADOS_USSD", false);
    }

    @JsonIgnore
    public boolean isEditInformapromo_ussd_ussd() {
        return super.getEditMap().getOrDefault("INFORMAPROMO_USSD_USSD", false);
    }

    public void setEditPromopadrao_ussd(boolean bool) {
        super.getEditMap().put("PROMOPADRAO_USSD", bool);
    }

    public void setEditSemrecarga_ussd(boolean bool) {
        super.getEditMap().put("SEMRECARGA_USSD", bool);
    }

    public void setEditChamada_ussd(boolean bool) {
        super.getEditMap().put("CHAMADA_USSD", bool);
    }

    public void setEditDetalhes_ussd(boolean bool) {
        super.getEditMap().put("DETALHES_USSD", bool);
    }

    public void setEditRecarga_ussd(boolean bool) {
        super.getEditMap().put("RECARGA_USSD", bool);
    }

    public void setEditInformavalidade_ussd(boolean bool) {
        super.getEditMap().put("INFORMAVALIDADE_USSD", bool);
    }

    public void setEditValidade_ussd(boolean bool) {
        super.getEditMap().put("VALIDADE_USSD", bool);
    }

    public void setEditContingencia_ussd(boolean bool) {
        super.getEditMap().put("CONTINGENCIA_USSD", bool);
    }

    public void setEditNaocliente_informapromo_ussd(boolean bool) {
        super.getEditMap().put("NAOCLIENTE_INFORMAPROMO_USSD", bool);
    }

    public void setEditNaocliente_validade_ussd(boolean bool) {
        super.getEditMap().put("NAOCLIENTE_VALIDADE_USSD", bool);
    }

    public void setEditRegras_ussd(boolean bool) {
        super.getEditMap().put("REGRAS_USSD", bool);
    }

    public void setEditNaocliente_recarga_ussd(boolean bool) {
        super.getEditMap().put("NAOCLIENTE_RECARGA_USSD", bool);
    }

    public void setEditIncentivo_oferta_pre_ussd(boolean bool) {
        super.getEditMap().put("INCENTIVO_OFERTA_PRE_USSD", bool);
    }

    public void setEditFallback_ussd(boolean bool) {
        super.getEditMap().put("FALLBACK_USSD", bool);
    }

    public void setEditSva_ussd(boolean bool) {
        super.getEditMap().put("SVA_USSD", bool);
    }

    public void setEditInformasaldopromo_ussd(boolean bool) {
        super.getEditMap().put("INFORMASALDOPROMO_USSD", bool);
    }

    public void setEditOfertamigrados_ussd(boolean bool) {
        super.getEditMap().put("OFERTAMIGRADOS_USSD", bool);
    }

    public void setEditInformapromo_ussd_ussd(boolean bool) {
        super.getEditMap().put("INFORMAPROMO_USSD_USSD", bool);
    }

    @JsonIgnore
    public boolean isEditPromopadrao_144() {
        return super.getEditMap().getOrDefault("PROMOPADRAO_144", false);
    }

    @JsonIgnore
    public boolean isEditSemrecarga_144() {
        return super.getEditMap().getOrDefault("SEMRECARGA_144", false);
    }

    @JsonIgnore
    public boolean isEditChamada_144() {
        return super.getEditMap().getOrDefault("CHAMADA_144", false);
    }

    @JsonIgnore
    public boolean isEditDetalhes_144() {
        return super.getEditMap().getOrDefault("DETALHES_144", false);
    }

    @JsonIgnore
    public boolean isEditRecarga_144() {
        return super.getEditMap().getOrDefault("RECARGA_144", false);
    }

    @JsonIgnore
    public boolean isEditInformavalidade_144() {
        return super.getEditMap().getOrDefault("INFORMAVALIDADE_144", false);
    }

    @JsonIgnore
    public boolean isEditValidade_144() {
        return super.getEditMap().getOrDefault("VALIDADE_144", false);
    }

    @JsonIgnore
    public boolean isEditContingencia_144() {
        return super.getEditMap().getOrDefault("CONTINGENCIA_144", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_informapromo_144() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_INFORMAPROMO_144", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_validade_144() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_VALIDADE_144", false);
    }

    @JsonIgnore
    public boolean isEditRegras_144() {
        return super.getEditMap().getOrDefault("REGRAS_144", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_recarga_144() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_RECARGA_144", false);
    }

    @JsonIgnore
    public boolean isEditIncentivo_oferta_pre_144() {
        return super.getEditMap().getOrDefault("INCENTIVO_OFERTA_PRE_144", false);
    }

    @JsonIgnore
    public boolean isEditFallback_144() {
        return super.getEditMap().getOrDefault("FALLBACK_144", false);
    }

    @JsonIgnore
    public boolean isEditSva_144() {
        return super.getEditMap().getOrDefault("SVA_144", false);
    }

    @JsonIgnore
    public boolean isEditInformasaldopromo_144() {
        return super.getEditMap().getOrDefault("INFORMASALDOPROMO_144", false);
    }

    @JsonIgnore
    public boolean isEditOfertamigrados_144() {
        return super.getEditMap().getOrDefault("OFERTAMIGRADOS_144", false);
    }

    @JsonIgnore
    public boolean isEditInformapromo_ussd_144() {
        return super.getEditMap().getOrDefault("INFORMAPROMO_USSD_144", false);
    }

    public void setEditPromopadrao_144(boolean bool) {
        super.getEditMap().put("PROMOPADRAO_144", bool);
    }

    public void setEditSemrecarga_144(boolean bool) {
        super.getEditMap().put("SEMRECARGA_144", bool);
    }

    public void setEditChamada_144(boolean bool) {
        super.getEditMap().put("CHAMADA_144", bool);
    }

    public void setEditDetalhes_144(boolean bool) {
        super.getEditMap().put("DETALHES_144", bool);
    }

    public void setEditRecarga_144(boolean bool) {
        super.getEditMap().put("RECARGA_144", bool);
    }

    public void setEditInformavalidade_144(boolean bool) {
        super.getEditMap().put("INFORMAVALIDADE_144", bool);
    }

    public void setEditValidade_144(boolean bool) {
        super.getEditMap().put("VALIDADE_144", bool);
    }

    public void setEditContingencia_144(boolean bool) {
        super.getEditMap().put("CONTINGENCIA_144", bool);
    }

    public void setEditNaocliente_informapromo_144(boolean bool) {
        super.getEditMap().put("NAOCLIENTE_INFORMAPROMO_144", bool);
    }

    public void setEditNaocliente_validade_144(boolean bool) {
        super.getEditMap().put("NAOCLIENTE_VALIDADE_144", bool);
    }

    public void setEditRegras_144(boolean bool) {
        super.getEditMap().put("REGRAS_144", bool);
    }

    public void setEditNaocliente_recarga_144(boolean bool) {
        super.getEditMap().put("NAOCLIENTE_RECARGA_144", bool);
    }

    public void setEditIncentivo_oferta_pre_144(boolean bool) {
        super.getEditMap().put("INCENTIVO_OFERTA_PRE_144", bool);
    }

    public void setEditFallback_144(boolean bool) {
        super.getEditMap().put("FALLBACK_144", bool);
    }

    public void setEditSva_144(boolean bool) {
        super.getEditMap().put("SVA_144", bool);
    }

    public void setEditInformasaldopromo_144(boolean bool) {
        super.getEditMap().put("INFORMASALDOPROMO_144", bool);
    }

    public void setEditOfertamigrados_144(boolean bool) {
        super.getEditMap().put("OFERTAMIGRADOS_144", bool);
    }

    public void setEditInformapromo_ussd_144(boolean bool) {
        super.getEditMap().put("INFORMAPROMO_USSD_144", bool);
    }

    public String getDdd() {
        return ddd;
    }

    @JsonIgnore
    public String getDddSub() {
        if (ddd != null && ddd.length() > 59) {
            String dddSub = ddd.substring(0, 59);
            dddSub = dddSub.substring(0, dddSub.lastIndexOf(",")) + "...";
            return dddSub;
        }
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getIdbeneficio() {
        return idbeneficio;
    }

    public void setIdbeneficio(String idbeneficio) {
        this.idbeneficio = idbeneficio;
    }

    public String getIdprograma() {
        return idprograma;
    }

    public void setIdprograma(String idprograma) {
        this.idprograma = idprograma;
    }

    public String getPromopadrao_ussd() {
        return promopadrao_ussd;
    }

    public void setPromopadrao_ussd(String promopadrao_ussd) {
        this.promopadrao_ussd = promopadrao_ussd;
    }

    public String getSemrecarga_ussd() {
        return semrecarga_ussd;
    }

    public void setSemrecarga_ussd(String semrecarga_ussd) {
        this.semrecarga_ussd = semrecarga_ussd;
    }

    public String getChamada_ussd() {
        return chamada_ussd;
    }

    public void setChamada_ussd(String chamada_ussd) {
        this.chamada_ussd = chamada_ussd;
    }

    public String getDetalhes_ussd() {
        return detalhes_ussd;
    }

    public void setDetalhes_ussd(String detalhes_ussd) {
        this.detalhes_ussd = detalhes_ussd;
    }

    public String getRecarga_ussd() {
        return recarga_ussd;
    }

    public void setRecarga_ussd(String recarga_ussd) {
        this.recarga_ussd = recarga_ussd;
    }

    public String getInformavalidade_ussd() {
        return informavalidade_ussd;
    }

    public void setInformavalidade_ussd(String informavalidade_ussd) {
        this.informavalidade_ussd = informavalidade_ussd;
    }

    public String getValidade_ussd() {
        return validade_ussd;
    }

    public void setValidade_ussd(String validade_ussd) {
        this.validade_ussd = validade_ussd;
    }

    public String getContingencia_ussd() {
        return contingencia_ussd;
    }

    public void setContingencia_ussd(String contingencia_ussd) {
        this.contingencia_ussd = contingencia_ussd;
    }

    public String getNaocliente_informapromo_ussd() {
        return naocliente_informapromo_ussd;
    }

    public void setNaocliente_informapromo_ussd(String naocliente_informapromo_ussd) {
        this.naocliente_informapromo_ussd = naocliente_informapromo_ussd;
    }

    public String getNaocliente_validade_ussd() {
        return naocliente_validade_ussd;
    }

    public void setNaocliente_validade_ussd(String naocliente_validade_ussd) {
        this.naocliente_validade_ussd = naocliente_validade_ussd;
    }

    public String getRegras_ussd() {
        return regras_ussd;
    }

    public void setRegras_ussd(String regras_ussd) {
        this.regras_ussd = regras_ussd;
    }

    public String getNaocliente_recarga_ussd() {
        return naocliente_recarga_ussd;
    }

    public void setNaocliente_recarga_ussd(String naocliente_recarga_ussd) {
        this.naocliente_recarga_ussd = naocliente_recarga_ussd;
    }

    public String getIncentivo_oferta_pre_ussd() {
        return incentivo_oferta_pre_ussd;
    }

    public void setIncentivo_oferta_pre_ussd(String incentivo_oferta_pre_ussd) {
        this.incentivo_oferta_pre_ussd = incentivo_oferta_pre_ussd;
    }

    public String getFallback_ussd() {
        return fallback_ussd;
    }

    public void setFallback_ussd(String fallback_ussd) {
        this.fallback_ussd = fallback_ussd;
    }

    public String getSva_ussd() {
        return sva_ussd;
    }

    public void setSva_ussd(String sva_ussd) {
        this.sva_ussd = sva_ussd;
    }

    public String getInformasaldopromo_ussd() {
        return informasaldopromo_ussd;
    }

    public void setInformasaldopromo_ussd(String informasaldopromo_ussd) {
        this.informasaldopromo_ussd = informasaldopromo_ussd;
    }

    public String getOfertamigrados_ussd() {
        return ofertamigrados_ussd;
    }

    public void setOfertamigrados_ussd(String ofertamigrados_ussd) {
        this.ofertamigrados_ussd = ofertamigrados_ussd;
    }

    public String getInformapromo_ussd_ussd() {
        return informapromo_ussd_ussd;
    }

    public void setInformapromo_ussd_ussd(String informapromo_ussd_ussd) {
        this.informapromo_ussd_ussd = informapromo_ussd_ussd;
    }

    public String getPromopadrao_144() {
        return promopadrao_144;
    }

    public void setPromopadrao_144(String promopadrao_144) {
        this.promopadrao_144 = promopadrao_144;
    }

    public String getSemrecarga_144() {
        return semrecarga_144;
    }

    public void setSemrecarga_144(String semrecarga_144) {
        this.semrecarga_144 = semrecarga_144;
    }

    public String getChamada_144() {
        return chamada_144;
    }

    public void setChamada_144(String chamada_144) {
        this.chamada_144 = chamada_144;
    }

    public String getDetalhes_144() {
        return detalhes_144;
    }

    public void setDetalhes_144(String detalhes_144) {
        this.detalhes_144 = detalhes_144;
    }

    public String getRecarga_144() {
        return recarga_144;
    }

    public void setRecarga_144(String recarga_144) {
        this.recarga_144 = recarga_144;
    }

    public String getInformavalidade_144() {
        return informavalidade_144;
    }

    public void setInformavalidade_144(String informavalidade_144) {
        this.informavalidade_144 = informavalidade_144;
    }

    public String getValidade_144() {
        return validade_144;
    }

    public void setValidade_144(String validade_144) {
        this.validade_144 = validade_144;
    }

    public String getContingencia_144() {
        return contingencia_144;
    }

    public void setContingencia_144(String contingencia_144) {
        this.contingencia_144 = contingencia_144;
    }

    public String getNaocliente_informapromo_144() {
        return naocliente_informapromo_144;
    }

    public void setNaocliente_informapromo_144(String naocliente_informapromo_144) {
        this.naocliente_informapromo_144 = naocliente_informapromo_144;
    }

    public String getNaocliente_validade_144() {
        return naocliente_validade_144;
    }

    public void setNaocliente_validade_144(String naocliente_validade_144) {
        this.naocliente_validade_144 = naocliente_validade_144;
    }

    public String getRegras_144() {
        return regras_144;
    }

    public void setRegras_144(String regras_144) {
        this.regras_144 = regras_144;
    }

    public String getNaocliente_recarga_144() {
        return naocliente_recarga_144;
    }

    public void setNaocliente_recarga_144(String naocliente_recarga_144) {
        this.naocliente_recarga_144 = naocliente_recarga_144;
    }

    public String getIncentivo_oferta_pre_144() {
        return incentivo_oferta_pre_144;
    }

    public void setIncentivo_oferta_pre_144(String incentivo_oferta_pre_144) {
        this.incentivo_oferta_pre_144 = incentivo_oferta_pre_144;
    }

    public String getFallback_144() {
        return fallback_144;
    }

    public void setFallback_144(String fallback_144) {
        this.fallback_144 = fallback_144;
    }

    public String getSva_144() {
        return sva_144;
    }

    public void setSva_144(String sva_144) {
        this.sva_144 = sva_144;
    }

    public String getInformasaldopromo_144() {
        return informasaldopromo_144;
    }

    public void setInformasaldopromo_144(String informasaldopromo_144) {
        this.informasaldopromo_144 = informasaldopromo_144;
    }

    public String getOfertamigrados_144() {
        return ofertamigrados_144;
    }

    public void setOfertamigrados_144(String ofertamigrados_144) {
        this.ofertamigrados_144 = ofertamigrados_144;
    }

    public String getInformapromo_ussd_144() {
        return informapromo_ussd_144;
    }

    public void setInformapromo_ussd_144(String informapromo_ussd_144) {
        this.informapromo_ussd_144 = informapromo_ussd_144;
    }

    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                FrasesCartao[] pp1 = mapper.readValue(history, FrasesCartao[].class);
                setHistory(Arrays.asList(mapper.readValue(history, FrasesCartao[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (FrasesCartao template : pp1) {
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
        ddd = ddd != null ? ddd.replace(" ", "").replace(";", ",") : ddd;
  
    }
}
