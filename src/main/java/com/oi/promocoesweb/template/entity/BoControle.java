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
public class BoControle extends Template {

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
    @FieldInput(name = InputType.WAV, label = "CLIENTE_PROMOPADRAO")
    private String oicjac_promopadrao;
    @FieldInput(name = InputType.WAV, label = "CLIENTE_REGRAS")
    private String oicjac_regras;
    @FieldInput(name = InputType.WAV, label = "CLIENTE_VALIDADE")
    private String oicjac_validade;
    @FieldInput(name = InputType.WAV, label = "CLIENTE_SALDO")
    private String oicjac_saldo;
    @FieldInput(name = InputType.WAV, label = "CLIENTE_CONTINGENCIA")
    private String oicjac_conting;
    @FieldInput(name = InputType.WAV, label = "NAOCLIENTE_INFORMAPROMO")
    private String oicnaoc_informapromo;
    @FieldInput(name = InputType.WAV, label = "NAOCLIENTE_REGRAS")
    private String oicnaoc_regras;
    @FieldInput(name = InputType.WAV, label = "NAOCLIENTE_VALIDADE")
    private String oicnaoc_validade;
    @FieldInput(name = InputType.WAV, label = "CLIENTE_INCENTIVO")
    private String oicconectado_incentivo;
    @FieldInput(name = InputType.WAV, label = "CLIENTE_INCENTIVOPROMO")
    private String oicconectado_incentivopromo;
    @FieldInput(name = InputType.WAV, label = "CLIENTE_SVA")
    private String jacliente_sva;
    @FieldInput(name = InputType.WAV, label = "INCENTIVO_COMBOLETO")
    private String oicc_incentivo_comboleto;
    @FieldInput(name = InputType.WAV, label = "DETALHES_OFERTARETENCAO")
    private String detalhes_ofertaretencao;
    @FieldInput(name = InputType.WAV, label = "CLIENTE_INTERNETEMDOBRO")
    private String intemdobro_jacliente;
    @FieldInput(name = InputType.WAV, label = "NAOCLIENTE_INTERNETEMDOBRO")
    private String intemdobro_naocliente;
    @FieldInput(name = InputType.WAV, label = "BENEFICIOINTERNET_ANTERIOR")
    private String benint_anteriordtvendaura;
    @FieldInput(name = InputType.WAV, label = "BENEFICIOINTERNET_APOS")
    private String benint_aposdtvendaura;
    @FieldInput(name = InputType.WAV, label = "FALLBACK")
    private String fallback_controle;

    public BoControle() {
        super();
    }

    public BoControle(BoControle selectedConfigOfertasPre) {
        this.idbeneficio = selectedConfigOfertasPre.getIdbeneficio();
        this.idprograma = selectedConfigOfertasPre.getIdprograma();
        this.ddd = selectedConfigOfertasPre.getDdd();
        this.oicjac_promopadrao= selectedConfigOfertasPre.getOicjac_promopadrao();
        this.oicjac_regras= selectedConfigOfertasPre.getOicjac_regras();
        this.oicjac_validade= selectedConfigOfertasPre.getOicjac_validade();
        this.oicjac_saldo= selectedConfigOfertasPre.getOicjac_saldo();
        this.oicjac_conting= selectedConfigOfertasPre.getOicjac_conting();
        this.oicnaoc_informapromo= selectedConfigOfertasPre.getOicnaoc_informapromo();
        this.oicnaoc_regras= selectedConfigOfertasPre.getOicnaoc_regras();
        this.oicnaoc_validade= selectedConfigOfertasPre.getOicnaoc_validade();
        this.oicconectado_incentivo= selectedConfigOfertasPre.getOicconectado_incentivo();
        this.oicconectado_incentivopromo= selectedConfigOfertasPre.getOicconectado_incentivopromo();
        this.jacliente_sva= selectedConfigOfertasPre.getJacliente_sva();
        this.oicc_incentivo_comboleto= selectedConfigOfertasPre.getOicc_incentivo_comboleto();
        this.detalhes_ofertaretencao= selectedConfigOfertasPre.getDetalhes_ofertaretencao();
        this.intemdobro_jacliente= selectedConfigOfertasPre.getIntemdobro_jacliente();
        this.intemdobro_naocliente= selectedConfigOfertasPre.getIntemdobro_naocliente();
        this.benint_anteriordtvendaura= selectedConfigOfertasPre.getBenint_anteriordtvendaura();
        this.benint_aposdtvendaura= selectedConfigOfertasPre.getBenint_aposdtvendaura();
        this.fallback_controle= selectedConfigOfertasPre.getFallback_controle();

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
    public boolean isEditOicjac_promopadrao() {
        return super.getEditMap().getOrDefault("OICJAC_PROMOPADRAO", false);
    }

    @JsonIgnore
    public boolean isEditOicjac_regras() {
        return super.getEditMap().getOrDefault("OICJAC_REGRAS", false);
    }

    @JsonIgnore
    public boolean isEditOicjac_validade() {
        return super.getEditMap().getOrDefault("OICJAC_VALIDADE", false);
    }

    @JsonIgnore
    public boolean isEditOicjac_saldo() {
        return super.getEditMap().getOrDefault("OICJAC_SALDO", false);
    }

    @JsonIgnore
    public boolean isEditOicjac_conting() {
        return super.getEditMap().getOrDefault("OICJAC_CONTING", false);
    }

    @JsonIgnore
    public boolean isEditOicnaoc_informapromo() {
        return super.getEditMap().getOrDefault("OICNAOC_INFORMAPROMO", false);
    }

    @JsonIgnore
    public boolean isEditOicnaoc_regras() {
        return super.getEditMap().getOrDefault("OICNAOC_REGRAS", false);
    }

    @JsonIgnore
    public boolean isEditOicnaoc_validade() {
        return super.getEditMap().getOrDefault("OICNAOC_VALIDADE", false);
    }

    @JsonIgnore
    public boolean isEditOicconectado_incentivo() {
        return super.getEditMap().getOrDefault("OICCONECTADO_INCENTIVO", false);
    }

    @JsonIgnore
    public boolean isEditOicconectado_incentivopromo() {
        return super.getEditMap().getOrDefault("OICCONECTADO_INCENTIVOPROMO", false);
    }

    @JsonIgnore
    public boolean isEditJacliente_sva() {
        return super.getEditMap().getOrDefault("JACLIENTE_SVA", false);
    }

    @JsonIgnore
    public boolean isEditOicc_incentivo_comboleto() {
        return super.getEditMap().getOrDefault("OICC_INCENTIVO_COMBOLETO", false);
    }

    @JsonIgnore
    public boolean isEditDetalhes_ofertaretencao() {
        return super.getEditMap().getOrDefault("DETALHES_OFERTARETENCAO", false);
    }

    @JsonIgnore
    public boolean isEditIntemdobro_jacliente() {
        return super.getEditMap().getOrDefault("INTEMDOBRO_JACLIENTE", false);
    }

    @JsonIgnore
    public boolean isEditIntemdobro_naocliente() {
        return super.getEditMap().getOrDefault("INTEMDOBRO_NAOCLIENTE", false);
    }

    @JsonIgnore
    public boolean isEditBenint_anteriordtvendaura() {
        return super.getEditMap().getOrDefault("BENINT_ANTERIORDTVENDAURA", false);
    }

    @JsonIgnore
    public boolean isEditBenint_aposdtvendaura() {
        return super.getEditMap().getOrDefault("BENINT_APOSDTVENDAURA", false);
    }

    @JsonIgnore
    public boolean isEditFallback_controle() {
        return super.getEditMap().getOrDefault("FALLBACK_CONTROLE", false);
    }

    public void setEditOicjac_promopadrao(boolean checked) {
        super.getEditMap().put("OICJAC_PROMOPADRAO", checked);
    }

    public void setEditOicjac_regras(boolean checked) {
        super.getEditMap().put("OICJAC_REGRAS", checked);
    }

    public void setEditOicjac_validade(boolean checked) {
        super.getEditMap().put("OICJAC_VALIDADE", checked);
    }

    public void setEditOicjac_saldo(boolean checked) {
        super.getEditMap().put("OICJAC_SALDO", checked);
    }

    public void setEditOicjac_conting(boolean checked) {
        super.getEditMap().put("OICJAC_CONTING", checked);
    }

    public void setEditOicnaoc_informapromo(boolean checked) {
        super.getEditMap().put("OICNAOC_INFORMAPROMO", checked);
    }

    public void setEditOicnaoc_regras(boolean checked) {
        super.getEditMap().put("OICNAOC_REGRAS", checked);
    }

    public void setEditOicnaoc_validade(boolean checked) {
        super.getEditMap().put("OICNAOC_VALIDADE", checked);
    }

    public void setEditOicconectado_incentivo(boolean checked) {
        super.getEditMap().put("OICCONECTADO_INCENTIVO", checked);
    }

    public void setEditOicconectado_incentivopromo(boolean checked) {
        super.getEditMap().put("OICCONECTADO_INCENTIVOPROMO", checked);
    }

    public void setEditJacliente_sva(boolean checked) {
        super.getEditMap().put("JACLIENTE_SVA", checked);
    }

    public void setEditOicc_incentivo_comboleto(boolean checked) {
        super.getEditMap().put("OICC_INCENTIVO_COMBOLETO", checked);
    }

    public void setEditDetalhes_ofertaretencao(boolean checked) {
        super.getEditMap().put("DETALHES_OFERTARETENCAO", checked);
    }

    public void setEditIntemdobro_jacliente(boolean checked) {
        super.getEditMap().put("INTEMDOBRO_JACLIENTE", checked);
    }

    public void setEditIntemdobro_naocliente(boolean checked) {
        super.getEditMap().put("INTEMDOBRO_NAOCLIENTE", checked);
    }

    public void setEditBenint_anteriordtvendaura(boolean checked) {
        super.getEditMap().put("BENINT_ANTERIORDTVENDAURA", checked);
    }

    public void setEditBenint_aposdtvendaura(boolean checked) {
        super.getEditMap().put("BENINT_APOSDTVENDAURA", checked);
    }

    public void setEditFallback_controle(boolean checked) {
        super.getEditMap().put("FALLBACK_CONTROLE", checked);
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

    public String getOicjac_promopadrao() {
        return oicjac_promopadrao;
    }

    public void setOicjac_promopadrao(String oicjac_promopadrao) {
        this.oicjac_promopadrao = oicjac_promopadrao;
    }

    public String getOicjac_regras() {
        return oicjac_regras;
    }

    public void setOicjac_regras(String oicjac_regras) {
        this.oicjac_regras = oicjac_regras;
    }

    public String getOicjac_validade() {
        return oicjac_validade;
    }

    public void setOicjac_validade(String oicjac_validade) {
        this.oicjac_validade = oicjac_validade;
    }

    public String getOicjac_saldo() {
        return oicjac_saldo;
    }

    public void setOicjac_saldo(String oicjac_saldo) {
        this.oicjac_saldo = oicjac_saldo;
    }

    public String getOicjac_conting() {
        return oicjac_conting;
    }

    public void setOicjac_conting(String oicjac_conting) {
        this.oicjac_conting = oicjac_conting;
    }

    public String getOicnaoc_informapromo() {
        return oicnaoc_informapromo;
    }

    public void setOicnaoc_informapromo(String oicnaoc_informapromo) {
        this.oicnaoc_informapromo = oicnaoc_informapromo;
    }

    public String getOicnaoc_regras() {
        return oicnaoc_regras;
    }

    public void setOicnaoc_regras(String oicnaoc_regras) {
        this.oicnaoc_regras = oicnaoc_regras;
    }

    public String getOicnaoc_validade() {
        return oicnaoc_validade;
    }

    public void setOicnaoc_validade(String oicnaoc_validade) {
        this.oicnaoc_validade = oicnaoc_validade;
    }

    public String getOicconectado_incentivo() {
        return oicconectado_incentivo;
    }

    public void setOicconectado_incentivo(String oicconectado_incentivo) {
        this.oicconectado_incentivo = oicconectado_incentivo;
    }

    public String getOicconectado_incentivopromo() {
        return oicconectado_incentivopromo;
    }

    public void setOicconectado_incentivopromo(String oicconectado_incentivopromo) {
        this.oicconectado_incentivopromo = oicconectado_incentivopromo;
    }

    public String getJacliente_sva() {
        return jacliente_sva;
    }

    public void setJacliente_sva(String jacliente_sva) {
        this.jacliente_sva = jacliente_sva;
    }

    public String getOicc_incentivo_comboleto() {
        return oicc_incentivo_comboleto;
    }

    public void setOicc_incentivo_comboleto(String oicc_incentivo_comboleto) {
        this.oicc_incentivo_comboleto = oicc_incentivo_comboleto;
    }

    public String getDetalhes_ofertaretencao() {
        return detalhes_ofertaretencao;
    }

    public void setDetalhes_ofertaretencao(String detalhes_ofertaretencao) {
        this.detalhes_ofertaretencao = detalhes_ofertaretencao;
    }

    public String getIntemdobro_jacliente() {
        return intemdobro_jacliente;
    }

    public void setIntemdobro_jacliente(String intemdobro_jacliente) {
        this.intemdobro_jacliente = intemdobro_jacliente;
    }

    public String getIntemdobro_naocliente() {
        return intemdobro_naocliente;
    }

    public void setIntemdobro_naocliente(String intemdobro_naocliente) {
        this.intemdobro_naocliente = intemdobro_naocliente;
    }

    public String getBenint_anteriordtvendaura() {
        return benint_anteriordtvendaura;
    }

    public void setBenint_anteriordtvendaura(String benint_anteriordtvendaura) {
        this.benint_anteriordtvendaura = benint_anteriordtvendaura;
    }

    public String getBenint_aposdtvendaura() {
        return benint_aposdtvendaura;
    }

    public void setBenint_aposdtvendaura(String benint_aposdtvendaura) {
        this.benint_aposdtvendaura = benint_aposdtvendaura;
    }

    public String getFallback_controle() {
        return fallback_controle;
    }

    public void setFallback_controle(String fallback_controle) {
        this.fallback_controle = fallback_controle;
    }

    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                BoControle[] pp1 = mapper.readValue(history, BoControle[].class);
                setHistory(Arrays.asList(mapper.readValue(history, BoControle[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (BoControle template : pp1) {
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
