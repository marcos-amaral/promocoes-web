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
public class BoCartao extends Template {

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
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV, label = "CLIENTE_PROMOPADRAO")
    private String cpfxc_promopadrao;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV, label = "CLIENTE_SEMRECARGA")
    private String cpfxc_semrecarga;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV, label = "CLIENTE_CHAMADA")
    private String cpfxc_chamada;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV, label = "CLIENTE_DETALHES")
    private String cpfxc_detalhes;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV, label = "CLIENTE_RECARGA")
    private String cpfxc_recarga;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV, label = "CLIENTE_INFORMAVALIDADE")
    private String cpfxc_informavalidade;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV, label = "CLIENTE_VALIDADE")
    private String cpfxc_validade;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV, label = "CLIENTE_CONTINGENCIA")
    private String cpfxc_contingencia;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV, label = "NAOCLIENTE_INFORMAPROMO")
    private String naocpfxc_informapromo;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV, label = "NAOCLIENTE_VALIDADE")
    private String naocpfxc_validade;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV, label = "NAOCLIENTE_REGRAS")
    private String naocpfxc_regras;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV, label = "NAOCLIENTE_RECARGA")
    private String naocpfxc_recarga;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV, label = "INCENTIVO_OFERTA_PRE")
    private String incentivo_oferta_pre;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV, label = "FALLBACK")
    private String fallback;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV, label = "CLIENTE_SVA")
    private String jacliente_sva;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV, label = "INFORMASALDOPROMO")
    private String informasaldopromo;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV, label = "OFERTAMIGRADOS")
    private String ofertamigrados;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @Deprecated
    private String naocps_infop_ussd;

    public BoCartao() {
        super();
    }

    public BoCartao(BoCartao selectedConfigOfertasPre) {
        this.ddd = selectedConfigOfertasPre.getDdd();
        this.idbeneficio = selectedConfigOfertasPre.getIdbeneficio();
        this.idprograma = selectedConfigOfertasPre.getIdprograma();
        this.cpfxc_promopadrao = selectedConfigOfertasPre.getCpfxc_promopadrao();
        this.cpfxc_semrecarga = selectedConfigOfertasPre.getCpfxc_semrecarga();
        this.cpfxc_chamada = selectedConfigOfertasPre.getCpfxc_chamada();
        this.cpfxc_detalhes = selectedConfigOfertasPre.getCpfxc_detalhes();
        this.cpfxc_recarga = selectedConfigOfertasPre.getCpfxc_recarga();
        this.cpfxc_informavalidade = selectedConfigOfertasPre.getCpfxc_informavalidade();
        this.cpfxc_validade = selectedConfigOfertasPre.getCpfxc_validade();
        this.cpfxc_contingencia = selectedConfigOfertasPre.getCpfxc_contingencia();
        this.naocpfxc_informapromo = selectedConfigOfertasPre.getNaocpfxc_informapromo();
        this.naocpfxc_validade = selectedConfigOfertasPre.getNaocpfxc_validade();
        this.naocpfxc_regras = selectedConfigOfertasPre.getNaocpfxc_regras();
        this.naocpfxc_recarga = selectedConfigOfertasPre.getNaocpfxc_recarga();
        this.incentivo_oferta_pre = selectedConfigOfertasPre.getIncentivo_oferta_pre();
        this.fallback = selectedConfigOfertasPre.getFallback();
        this.jacliente_sva = selectedConfigOfertasPre.getJacliente_sva();
        this.informasaldopromo = selectedConfigOfertasPre.getInformasaldopromo();
        this.ofertamigrados = selectedConfigOfertasPre.getOfertamigrados();
        this.naocps_infop_ussd = selectedConfigOfertasPre.getNaocps_infop_ussd();
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
    public boolean isEditCpfxc_promopadrao() {
        return super.getEditMap().getOrDefault("CPFXC_PROMOPADRAO", false);
    }

    @JsonIgnore
    public boolean isEditCpfxc_semrecarga() {
        return super.getEditMap().getOrDefault("CPFXC_SEMRECARGA", false);
    }

    @JsonIgnore
    public boolean isEditCpfxc_chamada() {
        return super.getEditMap().getOrDefault("CPFXC_CHAMADA", false);
    }

    @JsonIgnore
    public boolean isEditCpfxc_detalhes() {
        return super.getEditMap().getOrDefault("CPFXC_DETALHES", false);
    }

    @JsonIgnore
    public boolean isEditCpfxc_recarga() {
        return super.getEditMap().getOrDefault("CPFXC_RECARGA", false);
    }

    @JsonIgnore
    public boolean isEditCpfxc_informavalidade() {
        return super.getEditMap().getOrDefault("CPFXC_INFORMAVALIDADE", false);
    }

    @JsonIgnore
    public boolean isEditCpfxc_validade() {
        return super.getEditMap().getOrDefault("CPFXC_VALIDADE", false);
    }

    @JsonIgnore
    public boolean isEditCpfxc_contingencia() {
        return super.getEditMap().getOrDefault("CPFXC_CONTINGENCIA", false);
    }

    @JsonIgnore
    public boolean isEditNaocpfxc_informapromo() {
        return super.getEditMap().getOrDefault("NAOCPFXC_INFORMAPROMO", false);
    }

    @JsonIgnore
    public boolean isEditNaocpfxc_validade() {
        return super.getEditMap().getOrDefault("NAOCPFXC_VALIDADE", false);
    }

    @JsonIgnore
    public boolean isEditNaocpfxc_regras() {
        return super.getEditMap().getOrDefault("NAOCPFXC_REGRAS", false);
    }

    @JsonIgnore
    public boolean isEditNaocpfxc_recarga() {
        return super.getEditMap().getOrDefault("NAOCPFXC_RECARGA", false);
    }

    @JsonIgnore
    public boolean isEditIncentivo_oferta_pre() {
        return super.getEditMap().getOrDefault("INCENTIVO_OFERTA_PRE", false);
    }

    @JsonIgnore
    public boolean isEditFallback() {
        return super.getEditMap().getOrDefault("FALLBACK", false);
    }

    @JsonIgnore
    public boolean isEditJacliente_sva() {
        return super.getEditMap().getOrDefault("JACLIENTE_SVA", false);
    }

    @JsonIgnore
    public boolean isEditInformasaldopromo() {
        return super.getEditMap().getOrDefault("INFORMASALDOPROMO", false);
    }

    @JsonIgnore
    public boolean isEditOfertamigrados() {
        return super.getEditMap().getOrDefault("OFERTAMIGRADOS", false);
    }

    @JsonIgnore
    public boolean isEditNaocps_infop_ussd() {
        return super.getEditMap().getOrDefault("NAOCPS_INFOP_USSD", false);
    }

    public void setEditCpfxc_promopadrao(boolean bool) {
        super.getEditMap().put("CPFXC_PROMOPADRAO", bool);
    }

    public void setEditCpfxc_semrecarga(boolean bool) {
        super.getEditMap().put("CPFXC_SEMRECARGA", bool);
    }

    public void setEditCpfxc_chamada(boolean bool) {
        super.getEditMap().put("CPFXC_CHAMADA", bool);
    }

    public void setEditCpfxc_detalhes(boolean bool) {
        super.getEditMap().put("CPFXC_DETALHES", bool);
    }

    public void setEditCpfxc_recarga(boolean bool) {
        super.getEditMap().put("CPFXC_RECARGA", bool);
    }

    public void setEditCpfxc_informavalidade(boolean bool) {
        super.getEditMap().put("CPFXC_INFORMAVALIDADE", bool);
    }

    public void setEditCpfxc_validade(boolean bool) {
        super.getEditMap().put("CPFXC_VALIDADE", bool);
    }

    public void setEditCpfxc_contingencia(boolean bool) {
        super.getEditMap().put("CPFXC_CONTINGENCIA", bool);
    }

    public void setEditNaocpfxc_informapromo(boolean bool) {
        super.getEditMap().put("NAOCPFXC_INFORMAPROMO", bool);
    }

    public void setEditNaocpfxc_validade(boolean bool) {
        super.getEditMap().put("NAOCPFXC_VALIDADE", bool);
    }

    public void setEditNaocpfxc_regras(boolean bool) {
        super.getEditMap().put("NAOCPFXC_REGRAS", bool);
    }

    public void setEditNaocpfxc_recarga(boolean bool) {
        super.getEditMap().put("NAOCPFXC_RECARGA", bool);
    }

    public void setEditIncentivo_oferta_pre(boolean bool) {
        super.getEditMap().put("INCENTIVO_OFERTA_PRE", bool);
    }

    public void setEditFallback(boolean bool) {
        super.getEditMap().put("FALLBACK", bool);
    }

    public void setEditJacliente_sva(boolean bool) {
        super.getEditMap().put("JACLIENTE_SVA", bool);
    }

    public void setEditInformasaldopromo(boolean bool) {
        super.getEditMap().put("INFORMASALDOPROMO", bool);
    }

    public void setEditOfertamigrados(boolean bool) {
        super.getEditMap().put("OFERTAMIGRADOS", bool);
    }

    public void setEditNaocps_infop_ussd(boolean bool) {
        super.getEditMap().put("NAOCPS_INFOP_USSD", bool);
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

    public String getCpfxc_promopadrao() {
        return cpfxc_promopadrao;
    }

    public void setCpfxc_promopadrao(String cpfxc_promopadrao) {
        this.cpfxc_promopadrao = cpfxc_promopadrao;
    }

    public String getCpfxc_semrecarga() {
        return cpfxc_semrecarga;
    }

    public void setCpfxc_semrecarga(String cpfxc_semrecarga) {
        this.cpfxc_semrecarga = cpfxc_semrecarga;
    }

    public String getCpfxc_chamada() {
        return cpfxc_chamada;
    }

    public void setCpfxc_chamada(String cpfxc_chamada) {
        this.cpfxc_chamada = cpfxc_chamada;
    }

    public String getCpfxc_detalhes() {
        return cpfxc_detalhes;
    }

    public void setCpfxc_detalhes(String cpfxc_detalhes) {
        this.cpfxc_detalhes = cpfxc_detalhes;
    }

    public String getCpfxc_recarga() {
        return cpfxc_recarga;
    }

    public void setCpfxc_recarga(String cpfxc_recarga) {
        this.cpfxc_recarga = cpfxc_recarga;
    }

    public String getCpfxc_informavalidade() {
        return cpfxc_informavalidade;
    }

    public void setCpfxc_informavalidade(String cpfxc_informavalidade) {
        this.cpfxc_informavalidade = cpfxc_informavalidade;
    }

    public String getCpfxc_validade() {
        return cpfxc_validade;
    }

    public void setCpfxc_validade(String cpfxc_validade) {
        this.cpfxc_validade = cpfxc_validade;
    }

    public String getCpfxc_contingencia() {
        return cpfxc_contingencia;
    }

    public void setCpfxc_contingencia(String cpfxc_contingencia) {
        this.cpfxc_contingencia = cpfxc_contingencia;
    }

    public String getNaocpfxc_informapromo() {
        return naocpfxc_informapromo;
    }

    public void setNaocpfxc_informapromo(String naocpfxc_informapromo) {
        this.naocpfxc_informapromo = naocpfxc_informapromo;
    }

    public String getNaocpfxc_validade() {
        return naocpfxc_validade;
    }

    public void setNaocpfxc_validade(String naocpfxc_validade) {
        this.naocpfxc_validade = naocpfxc_validade;
    }

    public String getNaocpfxc_regras() {
        return naocpfxc_regras;
    }

    public void setNaocpfxc_regras(String naocpfxc_regras) {
        this.naocpfxc_regras = naocpfxc_regras;
    }

    public String getNaocpfxc_recarga() {
        return naocpfxc_recarga;
    }

    public void setNaocpfxc_recarga(String naocpfxc_recarga) {
        this.naocpfxc_recarga = naocpfxc_recarga;
    }

    public String getIncentivo_oferta_pre() {
        return incentivo_oferta_pre;
    }

    public void setIncentivo_oferta_pre(String incentivo_oferta_pre) {
        this.incentivo_oferta_pre = incentivo_oferta_pre;
    }

    public String getFallback() {
        return fallback;
    }

    public void setFallback(String fallback) {
        this.fallback = fallback;
    }

    public String getJacliente_sva() {
        return jacliente_sva;
    }

    public void setJacliente_sva(String jacliente_sva) {
        this.jacliente_sva = jacliente_sva;
    }

    public String getInformasaldopromo() {
        return informasaldopromo;
    }

    public void setInformasaldopromo(String informasaldopromo) {
        this.informasaldopromo = informasaldopromo;
    }

    public String getOfertamigrados() {
        return ofertamigrados;
    }

    public void setOfertamigrados(String ofertamigrados) {
        this.ofertamigrados = ofertamigrados;
    }

    public String getNaocps_infop_ussd() {
        return naocps_infop_ussd;
    }

    public void setNaocps_infop_ussd(String naocps_infop_ussd) {
        this.naocps_infop_ussd = naocps_infop_ussd;
    }

    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                BoCartao[] pp1 = mapper.readValue(history, BoCartao[].class);
                setHistory(Arrays.asList(mapper.readValue(history, BoCartao[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (BoCartao template : pp1) {
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
        if (cpfxc_promopadrao != null && !"".equals(cpfxc_promopadrao.trim())) {
            if (!cpfxc_promopadrao.toUpperCase().endsWith(".WAV")) {
                cpfxc_promopadrao = cpfxc_promopadrao + ".wav";
            }
            cpfxc_promopadrao = cpfxc_promopadrao.replace(" ", "");
        }
        if (cpfxc_semrecarga != null && !"".equals(cpfxc_semrecarga.trim())) {
            if (!cpfxc_semrecarga.toUpperCase().endsWith(".WAV")) {
                cpfxc_semrecarga = cpfxc_semrecarga + ".wav";
            }
            cpfxc_semrecarga = cpfxc_semrecarga.replace(" ", "");
        }
        if (cpfxc_chamada != null && !"".equals(cpfxc_chamada.trim())) {
            if (!cpfxc_chamada.toUpperCase().endsWith(".WAV")) {
                cpfxc_chamada = cpfxc_chamada + ".wav";
            }
            cpfxc_chamada = cpfxc_chamada.replace(" ", "");
        }
        if (cpfxc_detalhes != null && !"".equals(cpfxc_detalhes.trim())) {
            if (!cpfxc_detalhes.toUpperCase().endsWith(".WAV")) {
                cpfxc_detalhes = cpfxc_detalhes + ".wav";
            }
            cpfxc_detalhes = cpfxc_detalhes.replace(" ", "");
        }
        if (cpfxc_recarga != null && !"".equals(cpfxc_recarga.trim())) {
            if (!cpfxc_recarga.toUpperCase().endsWith(".WAV")) {
                cpfxc_recarga = cpfxc_recarga + ".wav";
            }
            cpfxc_recarga = cpfxc_recarga.replace(" ", "");
        }
        if (cpfxc_informavalidade != null && !"".equals(cpfxc_informavalidade.trim())) {
            if (!cpfxc_informavalidade.toUpperCase().endsWith(".WAV")) {
                cpfxc_informavalidade = cpfxc_informavalidade + ".wav";
            }
            cpfxc_informavalidade = cpfxc_informavalidade.replace(" ", "");
        }
        if (cpfxc_validade != null && !"".equals(cpfxc_validade.trim())) {
            if (!cpfxc_validade.toUpperCase().endsWith(".WAV")) {
                cpfxc_validade = cpfxc_validade + ".wav";
            }
            cpfxc_validade = cpfxc_validade.replace(" ", "");
        }
        if (cpfxc_contingencia != null && !"".equals(cpfxc_contingencia.trim())) {
            if (!cpfxc_contingencia.toUpperCase().endsWith(".WAV")) {
                cpfxc_contingencia = cpfxc_contingencia + ".wav";
            }
            cpfxc_contingencia = cpfxc_contingencia.replace(" ", "");
        }
        if (naocpfxc_informapromo != null && !"".equals(naocpfxc_informapromo.trim())) {
            if (!naocpfxc_informapromo.toUpperCase().endsWith(".WAV")) {
                naocpfxc_informapromo = naocpfxc_informapromo + ".wav";
            }
            naocpfxc_informapromo = naocpfxc_informapromo.replace(" ", "");
        }
        if (naocpfxc_validade != null && !"".equals(naocpfxc_validade.trim())) {
            if (!naocpfxc_validade.toUpperCase().endsWith(".WAV")) {
                naocpfxc_validade = naocpfxc_validade + ".wav";
            }
            naocpfxc_validade = naocpfxc_validade.replace(" ", "");
        }
        if (naocpfxc_regras != null && !"".equals(naocpfxc_regras.trim())) {
            if (!naocpfxc_regras.toUpperCase().endsWith(".WAV")) {
                naocpfxc_regras = naocpfxc_regras + ".wav";
            }
            naocpfxc_regras = naocpfxc_regras.replace(" ", "");
        }
        if (naocpfxc_recarga != null && !"".equals(naocpfxc_recarga.trim())) {
            if (!naocpfxc_recarga.toUpperCase().endsWith(".WAV")) {
                naocpfxc_recarga = naocpfxc_recarga + ".wav";
            }
            naocpfxc_recarga = naocpfxc_recarga.replace(" ", "");
        }
        if (incentivo_oferta_pre != null && !"".equals(incentivo_oferta_pre.trim())) {
            if (!incentivo_oferta_pre.toUpperCase().endsWith(".WAV")) {
                incentivo_oferta_pre = incentivo_oferta_pre + ".wav";
            }
            incentivo_oferta_pre = incentivo_oferta_pre.replace(" ", "");
        }
        if (fallback != null && !"".equals(fallback.trim())) {
            if (!fallback.toUpperCase().endsWith(".WAV")) {
                fallback = fallback + ".wav";
            }
            fallback = fallback.replace(" ", "");
        }
        if (jacliente_sva != null && !"".equals(jacliente_sva.trim())) {
            if (!jacliente_sva.toUpperCase().endsWith(".WAV")) {
                jacliente_sva = jacliente_sva + ".wav";
            }
            jacliente_sva = jacliente_sva.replace(" ", "");
        }
        if (informasaldopromo != null && !"".equals(informasaldopromo.trim())) {
            if (!informasaldopromo.toUpperCase().endsWith(".WAV")) {
                informasaldopromo = informasaldopromo + ".wav";
            }
            informasaldopromo = informasaldopromo.replace(" ", "");
        }
        if (ofertamigrados != null && !"".equals(ofertamigrados.trim())) {
            if (!ofertamigrados.toUpperCase().endsWith(".WAV")) {
                ofertamigrados = ofertamigrados + ".wav";
            }
            ofertamigrados = ofertamigrados.replace(" ", "");
        }
        if (naocps_infop_ussd != null && !"".equals(naocps_infop_ussd.trim())) {
            if (!naocps_infop_ussd.toUpperCase().endsWith(".WAV")) {
                naocps_infop_ussd = naocps_infop_ussd + ".wav";
            }
            naocps_infop_ussd = naocps_infop_ussd.replace(" ", "");
        }

    }
}
