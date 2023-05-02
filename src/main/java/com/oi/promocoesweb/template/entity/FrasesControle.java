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
 * @author jpereirc
 */
public class FrasesControle extends Template {

    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9?9")
    private String idbeneficio;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9?9")
    private String idprograma;
    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    @Id
    @FieldInput(name = InputType.STRING, mask = ",99")
    private String ddd;

    @FieldInput(name = InputType.FRASE, maxLength = 130)
    private String jacliente_promopadrao;
    @FieldInput(name = InputType.FRASE, maxLength = 130)
    private String jacliente_regras;
    @FieldInput(name = InputType.FRASE, maxLength = 130)
    private String jacliente_validade;
    @FieldInput(name = InputType.FRASE, maxLength = 130)
    private String jacliente_saldo;
    @FieldInput(name = InputType.FRASE, maxLength = 130)
    private String jacliente_conting;
    @FieldInput(name = InputType.FRASE, maxLength = 118)
    private String jacliente_sva;
    @FieldInput(name = InputType.FRASE, maxLength = 125)
    private String naocliente_informapromo;
    @FieldInput(name = InputType.FRASE, maxLength = 130)
    @Deprecated
    private String naocliente_regras;
    @FieldInput(name = InputType.FRASE, maxLength = 130)
    private String naocliente_validade;
    @FieldInput(name = InputType.FRASE, maxLength = 159, label = "CONECTADO_INCENTIVO_CREDITO_BOLETO")
    private String conectado_incentivo;
    @FieldInput(name = InputType.FRASE, maxLength = 45, label = "CONECTADO_INCENTIVO_BOLETO")
    private String incentivo_comboleto;
    @FieldInput(name = InputType.FRASE, maxLength = 45)
    @Deprecated
    private String conectado_incentivopromocao;
    @FieldInput(name = InputType.FRASE, maxLength = 130)
    private String detalhes_ofertaretencao;
    @FieldInput(name = InputType.FRASE, maxLength = 159)
    private String fallback;
    @FieldInput(name = InputType.FRASE, maxLength = 130)
    private String internetemdobro_jacliente;
    @FieldInput(name = InputType.FRASE, maxLength = 130)
    private String internetemdobro_naocliente;
    @FieldInput(name = InputType.FRASE, maxLength = 130)
    private String beneficiointernet_anteriordtvendaura;
    @FieldInput(name = InputType.FRASE, maxLength = 130)
    private String beneficiointernet_aposdtvendaura;
    
    @FieldInput(name = InputType.FRASE)
    private String jacliente_promopadrao_144;
    @FieldInput(name = InputType.FRASE)
    private String jacliente_regras_144;
    @FieldInput(name = InputType.FRASE)
    private String jacliente_validade_144;
    @FieldInput(name = InputType.FRASE)
    private String jacliente_saldo_144;
    @FieldInput(name = InputType.FRASE)
    private String jacliente_conting_144;
    @FieldInput(name = InputType.FRASE)
    private String jacliente_sva_144;
    @FieldInput(name = InputType.FRASE)
    private String naocliente_informapromo_144;
    @FieldInput(name = InputType.FRASE)
    @Deprecated
    private String naocliente_regras_144;
    @FieldInput(name = InputType.FRASE)
    private String naocliente_validade_144;
    @FieldInput(name = InputType.FRASE, label = "CONECTADO_INCENTIVO_CREDITO_BOLETO")
    private String conectado_incentivo_144;
    @FieldInput(name = InputType.FRASE, label = "CONECTADO_INCENTIVO_BOLETO")
    private String incentivo_comboleto_144;
    @FieldInput(name = InputType.FRASE)
    @Deprecated
    private String conectado_incentivopromocao_144;
    @FieldInput(name = InputType.FRASE)
    private String detalhes_ofertaretencao_144;
    @FieldInput(name = InputType.FRASE)
    private String fallback_144;
    @FieldInput(name = InputType.FRASE)
    private String internetemdobro_jacliente_144;
    @FieldInput(name = InputType.FRASE)
    private String internetemdobro_naocliente_144;
    @FieldInput(name = InputType.FRASE)
    private String beneficiointernet_anteriordtvendaura_144;
    @FieldInput(name = InputType.FRASE)
    private String beneficiointernet_aposdtvendaura_144;
    
    public FrasesControle() {
        super();
    }

    public FrasesControle(FrasesControle selectedConfigOfertasPre) {

        this.idbeneficio = selectedConfigOfertasPre.getIdbeneficio();
        this.idprograma = selectedConfigOfertasPre.getIdprograma();
        this.ddd = selectedConfigOfertasPre.getDdd();
        this.jacliente_promopadrao = selectedConfigOfertasPre.getJacliente_promopadrao();
        this.jacliente_regras = selectedConfigOfertasPre.getJacliente_regras();
        this.jacliente_validade = selectedConfigOfertasPre.getJacliente_validade();
        this.jacliente_saldo = selectedConfigOfertasPre.getJacliente_saldo();
        this.jacliente_conting = selectedConfigOfertasPre.getJacliente_conting();
        this.naocliente_informapromo = selectedConfigOfertasPre.getNaocliente_informapromo();
        this.naocliente_regras = selectedConfigOfertasPre.getNaocliente_regras();
        this.naocliente_validade = selectedConfigOfertasPre.getNaocliente_validade();
        this.conectado_incentivo = selectedConfigOfertasPre.getConectado_incentivo();
        this.conectado_incentivopromocao = selectedConfigOfertasPre.getConectado_incentivopromocao();
        this.jacliente_sva = selectedConfigOfertasPre.getJacliente_sva();
        this.incentivo_comboleto = selectedConfigOfertasPre.getIncentivo_comboleto();
        this.detalhes_ofertaretencao = selectedConfigOfertasPre.getDetalhes_ofertaretencao();
        this.internetemdobro_jacliente = selectedConfigOfertasPre.getInternetemdobro_jacliente();
        this.internetemdobro_naocliente = selectedConfigOfertasPre.getInternetemdobro_naocliente();
        this.beneficiointernet_anteriordtvendaura = selectedConfigOfertasPre.getBeneficiointernet_anteriordtvendaura();
        this.beneficiointernet_aposdtvendaura = selectedConfigOfertasPre.getBeneficiointernet_aposdtvendaura();
        this.fallback = selectedConfigOfertasPre.getFallback();
        this.jacliente_promopadrao_144 = selectedConfigOfertasPre.getJacliente_promopadrao_144();
        this.jacliente_regras_144 = selectedConfigOfertasPre.getJacliente_regras_144();
        this.jacliente_validade_144 = selectedConfigOfertasPre.getJacliente_validade_144();
        this.jacliente_saldo_144 = selectedConfigOfertasPre.getJacliente_saldo_144();
        this.jacliente_conting_144 = selectedConfigOfertasPre.getJacliente_conting_144();
        this.naocliente_informapromo_144 = selectedConfigOfertasPre.getNaocliente_informapromo_144();
        this.naocliente_regras_144 = selectedConfigOfertasPre.getNaocliente_regras_144();
        this.naocliente_validade_144 = selectedConfigOfertasPre.getNaocliente_validade_144();
        this.conectado_incentivo_144 = selectedConfigOfertasPre.getConectado_incentivo_144();
        this.conectado_incentivopromocao_144 = selectedConfigOfertasPre.getConectado_incentivopromocao_144();
        this.jacliente_sva_144 = selectedConfigOfertasPre.getJacliente_sva_144();
        this.incentivo_comboleto_144 = selectedConfigOfertasPre.getIncentivo_comboleto_144();
        this.detalhes_ofertaretencao_144 = selectedConfigOfertasPre.getDetalhes_ofertaretencao_144();
        this.internetemdobro_jacliente_144 = selectedConfigOfertasPre.getInternetemdobro_jacliente_144();
        this.internetemdobro_naocliente_144 = selectedConfigOfertasPre.getInternetemdobro_naocliente_144();
        this.beneficiointernet_anteriordtvendaura_144 = selectedConfigOfertasPre.getBeneficiointernet_anteriordtvendaura_144();
        this.beneficiointernet_aposdtvendaura_144 = selectedConfigOfertasPre.getBeneficiointernet_aposdtvendaura_144();
        this.fallback_144 = selectedConfigOfertasPre.getFallback_144();
    }

    @JsonIgnore
    public String getIdDdd() {
        StringBuilder sb = new StringBuilder();
        if (ddd != null && ddd.length() >= 2) {
            return sb.append("ID_BENEFICIO |  ").append(idbeneficio).append(" | ID_PROGRAMA | ").append(idprograma).append(" | DDD: ").append(ddd.substring(0, 2)).append("...").toString();
        } else {
            return sb.append("ID_BENEFICIO | ").append(idbeneficio).append("| ID_PROGRAMA | ").append(idprograma).append(" | DDD: null").toString();
        }
    }

    public String getHistory(String field) {
        JsonObject jsonObject = null;

        return jsonObject + "";
    }

    @JsonIgnore
    public boolean isEditJacliente_promopadrao() {
        return super.getEditMap().getOrDefault("JACLIENTE_PROMOPADRAO", false);
    }

    @JsonIgnore
    public boolean isEditJacliente_regras() {
        return super.getEditMap().getOrDefault("JACLIENTE_REGRAS", false);
    }

    @JsonIgnore
    public boolean isEditJacliente_validade() {
        return super.getEditMap().getOrDefault("JACLIENTE_VALIDADE", false);
    }

    @JsonIgnore
    public boolean isEditJacliente_saldo() {
        return super.getEditMap().getOrDefault("JACLIENTE_SALDO", false);
    }

    @JsonIgnore
    public boolean isEditJacliente_conting() {
        return super.getEditMap().getOrDefault("JACLIENTE_CONTING", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_informapromo() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_INFORMAPROMO", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_regras() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_REGRAS", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_validade() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_VALIDADE", false);
    }

    @JsonIgnore
    public boolean isEditConectado_incentivo() {
        return super.getEditMap().getOrDefault("CONECTADO_INCENTIVO", false);
    }

    @JsonIgnore
    public boolean isEditConectado_incentivopromocao() {
        return super.getEditMap().getOrDefault("CONECTADO_INCENTIVOPROMOCAO", false);
    }

    @JsonIgnore
    public boolean isEditJacliente_sva() {
        return super.getEditMap().getOrDefault("JACLIENTE_SVA", false);
    }

    @JsonIgnore
    public boolean isEditIncentivo_comboleto() {
        return super.getEditMap().getOrDefault("INCENTIVO_COMBOLETO", false);
    }

    @JsonIgnore
    public boolean isEditDetalhes_ofertaretencao() {
        return super.getEditMap().getOrDefault("DETALHES_OFERTARETENCAO", false);
    }

    @JsonIgnore
    public boolean isEditInternetemdobro_jacliente() {
        return super.getEditMap().getOrDefault("INTERNETEMDOBRO_JACLIENTE", false);
    }

    @JsonIgnore
    public boolean isEditInternetemdobro_naocliente() {
        return super.getEditMap().getOrDefault("INTERNETEMDOBRO_NAOCLIENTE", false);
    }

    @JsonIgnore
    public boolean isEditBeneficiointernet_anteriordtvendaura() {
        return super.getEditMap().getOrDefault("BENEFICIOINTERNET_ANTERIORDTVENDAURA", false);
    }

    @JsonIgnore
    public boolean isEditBeneficiointernet_aposdtvendaura() {
        return super.getEditMap().getOrDefault("BENEFICIOINTERNET_APOSDTVENDAURA", false);
    }

    @JsonIgnore
    public boolean isEditFallback() {
        return super.getEditMap().getOrDefault("FALLBACK", false);
    }
    
    @JsonIgnore
    public boolean isEditJacliente_promopadrao_144() {
        return super.getEditMap().getOrDefault("JACLIENTE_PROMOPADRAO_144", false);
    }

    @JsonIgnore
    public boolean isEditJacliente_regras_144() {
        return super.getEditMap().getOrDefault("JACLIENTE_REGRAS_144", false);
    }

    @JsonIgnore
    public boolean isEditJacliente_validade_144() {
        return super.getEditMap().getOrDefault("JACLIENTE_VALIDADE_144", false);
    }

    @JsonIgnore
    public boolean isEditJacliente_saldo_144() {
        return super.getEditMap().getOrDefault("JACLIENTE_SALDO_144", false);
    }

    @JsonIgnore
    public boolean isEditJacliente_conting_144() {
        return super.getEditMap().getOrDefault("JACLIENTE_CONTING_144", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_informapromo_144() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_INFORMAPROMO_144", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_regras_144() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_REGRAS_144", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_validade_144() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_VALIDADE_144", false);
    }

    @JsonIgnore
    public boolean isEditConectado_incentivo_144() {
        return super.getEditMap().getOrDefault("CONECTADO_INCENTIVO_144", false);
    }

    @JsonIgnore
    public boolean isEditConectado_incentivopromocao_144() {
        return super.getEditMap().getOrDefault("CONECTADO_INCENTIVOPROMOCAO_144", false);
    }

    @JsonIgnore
    public boolean isEditJacliente_sva_144() {
        return super.getEditMap().getOrDefault("JACLIENTE_SVA_144", false);
    }

    @JsonIgnore
    public boolean isEditIncentivo_comboleto_144() {
        return super.getEditMap().getOrDefault("INCENTIVO_COMBOLETO_144", false);
    }

    @JsonIgnore
    public boolean isEditDetalhes_ofertaretencao_144() {
        return super.getEditMap().getOrDefault("DETALHES_OFERTARETENCAO_144", false);
    }

    @JsonIgnore
    public boolean isEditInternetemdobro_jacliente_144() {
        return super.getEditMap().getOrDefault("INTERNETEMDOBRO_JACLIENTE_144", false);
    }

    @JsonIgnore
    public boolean isEditInternetemdobro_naocliente_144() {
        return super.getEditMap().getOrDefault("INTERNETEMDOBRO_NAOCLIENTE_144", false);
    }

    @JsonIgnore
    public boolean isEditBeneficiointernet_anteriordtvendaura_144() {
        return super.getEditMap().getOrDefault("BENEFICIOINTERNET_ANTERIORDTVENDAURA_144", false);
    }

    @JsonIgnore
    public boolean isEditBeneficiointernet_aposdtvendaura_144() {
        return super.getEditMap().getOrDefault("BENEFICIOINTERNET_APOSDTVENDAURA_144", false);
    }

    @JsonIgnore
    public boolean isEditFallback_144() {
        return super.getEditMap().getOrDefault("FALLBACK_144", false);
    }

    public void setEditJacliente_promopadrao(boolean checked) {
        super.getEditMap().put("JACLIENTE_PROMOPADRAO", checked);
    }

    public void setEditJacliente_regras(boolean checked) {
        super.getEditMap().put("JACLIENTE_REGRAS", checked);
    }

    public void setEditJacliente_validade(boolean checked) {
        super.getEditMap().put("JACLIENTE_VALIDADE", checked);
    }

    public void setEditJacliente_saldo(boolean checked) {
        super.getEditMap().put("JACLIENTE_SALDO", checked);
    }

    public void setEditJacliente_conting(boolean checked) {
        super.getEditMap().put("JACLIENTE_CONTING", checked);
    }

    public void setEditNaocliente_informapromo(boolean checked) {
        super.getEditMap().put("NAOCLIENTE_INFORMAPROMO", checked);
    }

    public void setEditNaocliente_regras(boolean checked) {
        super.getEditMap().put("NAOCLIENTE_REGRAS", checked);
    }

    public void setEditNaocliente_validade(boolean checked) {
        super.getEditMap().put("NAOCLIENTE_VALIDADE", checked);
    }

    public void setEditConectado_incentivo(boolean checked) {
        super.getEditMap().put("CONECTADO_INCENTIVO", checked);
    }

    public void setEditConectado_incentivopromocao(boolean checked) {
        super.getEditMap().put("CONECTADO_INCENTIVOPROMOCAO", checked);
    }

    public void setEditJacliente_sva(boolean checked) {
        super.getEditMap().put("JACLIENTE_SVA", checked);
    }

    public void setEditIncentivo_comboleto(boolean checked) {
        super.getEditMap().put("INCENTIVO_COMBOLETO", checked);
    }

    public void setEditDetalhes_ofertaretencao(boolean checked) {
        super.getEditMap().put("DETALHES_OFERTARETENCAO", checked);
    }

    public void setEditInternetemdobro_jacliente(boolean checked) {
        super.getEditMap().put("INTERNETEMDOBRO_JACLIENTE", checked);
    }

    public void setEditInternetemdobro_naocliente(boolean checked) {
        super.getEditMap().put("INTERNETEMDOBRO_NAOCLIENTE", checked);
    }

    public void setEditBeneficiointernet_anteriordtvendaura(boolean checked) {
        super.getEditMap().put("BENEFICIOINTERNET_ANTERIORDTVENDAURA", checked);
    }

    public void setEditBeneficiointernet_aposdtvendaura(boolean checked) {
        super.getEditMap().put("BENEFICIOINTERNET_APOSDTVENDAURA", checked);
    }

    public void setEditFallback(boolean checked) {
        super.getEditMap().put("FALLBACK", checked);
    }
    
    public void setEditJacliente_promopadrao_144(boolean checked) {
        super.getEditMap().put("JACLIENTE_PROMOPADRAO_144", checked);
    }

    public void setEditJacliente_regras_144(boolean checked) {
        super.getEditMap().put("JACLIENTE_REGRAS_144", checked);
    }

    public void setEditJacliente_validade_144(boolean checked) {
        super.getEditMap().put("JACLIENTE_VALIDADE_144", checked);
    }

    public void setEditJacliente_saldo_144(boolean checked) {
        super.getEditMap().put("JACLIENTE_SALDO_144", checked);
    }

    public void setEditJacliente_conting_144(boolean checked) {
        super.getEditMap().put("JACLIENTE_CONTING_144", checked);
    }

    public void setEditNaocliente_informapromo_144(boolean checked) {
        super.getEditMap().put("NAOCLIENTE_INFORMAPROMO_144", checked);
    }

    public void setEditNaocliente_regras_144(boolean checked) {
        super.getEditMap().put("NAOCLIENTE_REGRAS_144", checked);
    }

    public void setEditNaocliente_validade_144(boolean checked) {
        super.getEditMap().put("NAOCLIENTE_VALIDADE_144", checked);
    }

    public void setEditConectado_incentivo_144(boolean checked) {
        super.getEditMap().put("CONECTADO_INCENTIVO_144", checked);
    }

    public void setEditConectado_incentivopromocao_144(boolean checked) {
        super.getEditMap().put("CONECTADO_INCENTIVOPROMOCAO_144", checked);
    }

    public void setEditJacliente_sva_144(boolean checked) {
        super.getEditMap().put("JACLIENTE_SVA_144", checked);
    }

    public void setEditIncentivo_comboleto_144(boolean checked) {
        super.getEditMap().put("INCENTIVO_COMBOLETO_144", checked);
    }

    public void setEditDetalhes_ofertaretencao_144(boolean checked) {
        super.getEditMap().put("DETALHES_OFERTARETENCAO_144", checked);
    }

    public void setEditInternetemdobro_jacliente_144(boolean checked) {
        super.getEditMap().put("INTERNETEMDOBRO_JACLIENTE_144", checked);
    }

    public void setEditInternetemdobro_naocliente_144(boolean checked) {
        super.getEditMap().put("INTERNETEMDOBRO_NAOCLIENTE_144", checked);
    }

    public void setEditBeneficiointernet_anteriordtvendaura_144(boolean checked) {
        super.getEditMap().put("BENEFICIOINTERNET_ANTERIORDTVENDAURA_144", checked);
    }

    public void setEditBeneficiointernet_aposdtvendaura_144(boolean checked) {
        super.getEditMap().put("BENEFICIOINTERNET_APOSDTVENDAURA_144", checked);
    }

    public void setEditFallback_144(boolean checked) {
        super.getEditMap().put("FALLBACK_144", checked);
    }

    //Getters and setters
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

    public String getJacliente_promopadrao() {
        return jacliente_promopadrao;
    }

    public void setJacliente_promopadrao(String jacliente_promopadrao) {
        this.jacliente_promopadrao = jacliente_promopadrao;
    }

    public String getJacliente_regras() {
        return jacliente_regras;
    }

    public void setJacliente_regras(String jacliente_regras) {
        this.jacliente_regras = jacliente_regras;
    }

    public String getJacliente_validade() {
        return jacliente_validade;
    }

    public void setJacliente_validade(String jacliente_validade) {
        this.jacliente_validade = jacliente_validade;
    }

    public String getJacliente_saldo() {
        return jacliente_saldo;
    }

    public void setJacliente_saldo(String jacliente_saldo) {
        this.jacliente_saldo = jacliente_saldo;
    }

    public String getJacliente_conting() {
        return jacliente_conting;
    }

    public void setJacliente_conting(String jacliente_conting) {
        this.jacliente_conting = jacliente_conting;
    }

    public String getNaocliente_informapromo() {
        return naocliente_informapromo;
    }

    public void setNaocliente_informapromo(String naocliente_informapromo) {
        this.naocliente_informapromo = naocliente_informapromo;
    }

    public String getNaocliente_regras() {
        return naocliente_regras;
    }

    public void setNaocliente_regras(String naocliente_regras) {
        this.naocliente_regras = naocliente_regras;
    }

    public String getNaocliente_validade() {
        return naocliente_validade;
    }

    public void setNaocliente_validade(String naocliente_validade) {
        this.naocliente_validade = naocliente_validade;
    }

    public String getConectado_incentivo() {
        return conectado_incentivo;
    }

    public void setConectado_incentivo(String conectado_incentivo) {
        this.conectado_incentivo = conectado_incentivo;
    }

    public String getConectado_incentivopromocao() {
        return conectado_incentivopromocao;
    }

    public void setConectado_incentivopromocao(String conectado_incentivopromocao) {
        this.conectado_incentivopromocao = conectado_incentivopromocao;
    }

    public String getJacliente_sva() {
        return jacliente_sva;
    }

    public void setJacliente_sva(String jacliente_sva) {
        this.jacliente_sva = jacliente_sva;
    }

    public String getIncentivo_comboleto() {
        return incentivo_comboleto;
    }

    public void setIncentivo_comboleto(String incentivo_comboleto) {
        this.incentivo_comboleto = incentivo_comboleto;
    }

    public String getDetalhes_ofertaretencao() {
        return detalhes_ofertaretencao;
    }

    public void setDetalhes_ofertaretencao(String detalhes_ofertaretencao) {
        this.detalhes_ofertaretencao = detalhes_ofertaretencao;
    }

    public String getInternetemdobro_jacliente() {
        return internetemdobro_jacliente;
    }

    public void setInternetemdobro_jacliente(String internetemdobro_jacliente) {
        this.internetemdobro_jacliente = internetemdobro_jacliente;
    }

    public String getInternetemdobro_naocliente() {
        return internetemdobro_naocliente;
    }

    public void setInternetemdobro_naocliente(String internetemdobro_naocliente) {
        this.internetemdobro_naocliente = internetemdobro_naocliente;
    }

    public String getBeneficiointernet_anteriordtvendaura() {
        return beneficiointernet_anteriordtvendaura;
    }

    public void setBeneficiointernet_anteriordtvendaura(String beneficiointernet_anteriordtvendaura) {
        this.beneficiointernet_anteriordtvendaura = beneficiointernet_anteriordtvendaura;
    }

    public String getBeneficiointernet_aposdtvendaura() {
        return beneficiointernet_aposdtvendaura;
    }

    public void setBeneficiointernet_aposdtvendaura(String beneficiointernet_aposdtvendaura) {
        this.beneficiointernet_aposdtvendaura = beneficiointernet_aposdtvendaura;
    }

    public String getFallback() {
        return fallback;
    }

    public void setFallback(String fallback) {
        this.fallback = fallback;
    }
    
    public String getJacliente_promopadrao_144() {
        return jacliente_promopadrao_144;
    }

    public void setJacliente_promopadrao_144(String jacliente_promopadrao) {
        this.jacliente_promopadrao_144 = jacliente_promopadrao;
    }

    public String getJacliente_regras_144() {
        return jacliente_regras_144;
    }

    public void setJacliente_regras_144(String jacliente_regras) {
        this.jacliente_regras_144 = jacliente_regras;
    }

    public String getJacliente_validade_144() {
        return jacliente_validade_144;
    }

    public void setJacliente_validade_144(String jacliente_validade) {
        this.jacliente_validade_144 = jacliente_validade;
    }

    public String getJacliente_saldo_144() {
        return jacliente_saldo_144;
    }

    public void setJacliente_saldo_144(String jacliente_saldo) {
        this.jacliente_saldo_144 = jacliente_saldo;
    }

    public String getJacliente_conting_144() {
        return jacliente_conting_144;
    }

    public void setJacliente_conting_144(String jacliente_conting) {
        this.jacliente_conting_144 = jacliente_conting;
    }

    public String getNaocliente_informapromo_144() {
        return naocliente_informapromo_144;
    }

    public void setNaocliente_informapromo_144(String naocliente_informapromo) {
        this.naocliente_informapromo_144 = naocliente_informapromo;
    }

    public String getNaocliente_regras_144() {
        return naocliente_regras_144;
    }

    public void setNaocliente_regras_144(String naocliente_regras) {
        this.naocliente_regras_144 = naocliente_regras;
    }

    public String getNaocliente_validade_144() {
        return naocliente_validade_144;
    }

    public void setNaocliente_validade_144(String naocliente_validade) {
        this.naocliente_validade_144 = naocliente_validade;
    }

    public String getConectado_incentivo_144() {
        return conectado_incentivo_144;
    }

    public void setConectado_incentivo_144(String conectado_incentivo) {
        this.conectado_incentivo_144 = conectado_incentivo;
    }

    public String getConectado_incentivopromocao_144() {
        return conectado_incentivopromocao_144;
    }

    public void setConectado_incentivopromocao_144(String conectado_incentivopromocao) {
        this.conectado_incentivopromocao_144 = conectado_incentivopromocao;
    }

    public String getJacliente_sva_144() {
        return jacliente_sva_144;
    }

    public void setJacliente_sva_144(String jacliente_sva) {
        this.jacliente_sva_144 = jacliente_sva;
    }

    public String getIncentivo_comboleto_144() {
        return incentivo_comboleto_144;
    }

    public void setIncentivo_comboleto_144(String incentivo_comboleto) {
        this.incentivo_comboleto_144 = incentivo_comboleto;
    }

    public String getDetalhes_ofertaretencao_144() {
        return detalhes_ofertaretencao_144;
    }

    public void setDetalhes_ofertaretencao_144(String detalhes_ofertaretencao) {
        this.detalhes_ofertaretencao_144 = detalhes_ofertaretencao;
    }

    public String getInternetemdobro_jacliente_144() {
        return internetemdobro_jacliente_144;
    }

    public void setInternetemdobro_jacliente_144(String internetemdobro_jacliente) {
        this.internetemdobro_jacliente_144 = internetemdobro_jacliente;
    }

    public String getInternetemdobro_naocliente_144() {
        return internetemdobro_naocliente_144;
    }

    public void setInternetemdobro_naocliente_144(String internetemdobro_naocliente) {
        this.internetemdobro_naocliente_144 = internetemdobro_naocliente;
    }

    public String getBeneficiointernet_anteriordtvendaura_144() {
        return beneficiointernet_anteriordtvendaura_144;
    }

    public void setBeneficiointernet_anteriordtvendaura_144(String beneficiointernet_anteriordtvendaura) {
        this.beneficiointernet_anteriordtvendaura_144 = beneficiointernet_anteriordtvendaura;
    }

    public String getBeneficiointernet_aposdtvendaura_144() {
        return beneficiointernet_aposdtvendaura_144;
    }

    public void setBeneficiointernet_aposdtvendaura_144(String beneficiointernet_aposdtvendaura) {
        this.beneficiointernet_aposdtvendaura_144 = beneficiointernet_aposdtvendaura;
    }

    public String getFallback_144() {
        return fallback_144;
    }

    public void setFallback_144(String fallback) {
        this.fallback_144 = fallback;
    }

    //
    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                FrasesControle[] pp1 = mapper.readValue(history, FrasesControle[].class);
                setHistory(Arrays.asList(mapper.readValue(history, FrasesControle[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (FrasesControle template : pp1) {
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
