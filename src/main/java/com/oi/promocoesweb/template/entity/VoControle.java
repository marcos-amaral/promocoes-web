/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oi.promocoesweb.dbms.entity.User;
import com.oi.promocoesweb.template.entity.ui.InputType;
import com.oi.promocoesweb.template.utils.DateUtils;
import com.oi.promocoesweb.validation.annotation.FieldInput;
import com.oi.promocoesweb.validation.annotation.FieldValidation;
import com.oi.promocoesweb.validation.annotation.Id;
import com.oi.promocoesweb.validation.enums.ValidationEnum;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mmouraam
 */
public class VoControle extends Template {
    private static final Logger logger = LogManager.getLogger();

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
    private String tb_tipobonus;
    private String tb_tipoplano;
    private String incentivo1;
    private String incentivo2;
    private String outrosplanos;
    private String offercode;
    private Date data_inic;
    private Date data_fim;
    @FieldInput(name = InputType.MONEY)
    private String taxaadesao;
    @FieldInput(name = InputType.MONEY)
    private String tarifasmsdadoswifi;
    @FieldInput(name = InputType.MONEY)
    private String tarifasms;
    @FieldInput(name = InputType.MONEY)
    private String tarifadados;
    @FieldInput(name = InputType.MONEY)
    private String tarifavoz;
    @FieldInput(name = InputType.MONEY)
    private String franquia;
    @FieldInput(name = InputType.MONEY)
    private String creditos; 
    @FieldInput(name = InputType.MONEY)
    private String tb_taxa_adesaoboleto;
    @FieldInput(name = InputType.MONEY)
    private String franquia_boleto;
    @FieldInput(name = InputType.INTEGER)
    private String bolso_ilimitado;
    @FieldInput(name = InputType.INTEGER)
    private String bolso_allnet;
    
    @FieldInput(name = InputType.AUTOCOMPLETE, autoComplete = {"Credito","Boleto","Credito,Boleto"})
    private String tipo_pagamento;
    private boolean tb_ussd_convertido;
    private boolean elegivel_nova_promo;

    public VoControle() {
        super();
    }

    public VoControle(VoControle selectedConfigOfertasPre) {
        this.idbeneficio = selectedConfigOfertasPre.getIdbeneficio();
        this.idprograma = selectedConfigOfertasPre.getIdprograma();
        this.incentivo1 = selectedConfigOfertasPre.getIncentivo1();
        this.incentivo2 = selectedConfigOfertasPre.getIncentivo2();
        this.ddd = selectedConfigOfertasPre.getDdd();
        this.data_inic = selectedConfigOfertasPre.getData_inic();
        this.data_fim = selectedConfigOfertasPre.getData_fim();
        this.tb_tipoplano = selectedConfigOfertasPre.getTb_tipoplano();
        this.outrosplanos = selectedConfigOfertasPre.getOutrosplanos();
        this.offercode = selectedConfigOfertasPre.getOffercode();
        this.tb_tipobonus = selectedConfigOfertasPre.getTb_tipobonus();
        this.tarifasmsdadoswifi = selectedConfigOfertasPre.getTarifasmsdadoswifi();
        this.tarifasms = selectedConfigOfertasPre.getTarifasms();
        this.taxaadesao = selectedConfigOfertasPre.getTaxaadesao();
        this.tarifadados = selectedConfigOfertasPre.getTarifadados();
        this.tarifavoz = selectedConfigOfertasPre.getTarifavoz();
        this.franquia = selectedConfigOfertasPre.getFranquia();
        this.creditos = selectedConfigOfertasPre.getCreditos();
        this.elegivel_nova_promo = selectedConfigOfertasPre.getElegivel_nova_promo();
        this.tb_taxa_adesaoboleto = selectedConfigOfertasPre.getTb_taxa_adesaoboleto();
        this.bolso_ilimitado = selectedConfigOfertasPre.getBolso_ilimitado();
        this.bolso_allnet = selectedConfigOfertasPre.getBolso_allnet();
        this.franquia_boleto = selectedConfigOfertasPre.getFranquia_boleto();
        this.tipo_pagamento = selectedConfigOfertasPre.getTipo_pagamento();
        this.tb_ussd_convertido = selectedConfigOfertasPre.getTb_ussd_convertido();
    }

    @JsonIgnore
    public String getIdDdd() {
        StringBuilder sb = new StringBuilder();
        if (ddd != null && ddd.length() >= 2) {
            return sb.append("ID_BENEFICIO: ").append(idbeneficio).append(" | ID_PROGRAMA: ").append(idprograma).append(" | DDD: ").append(ddd.substring(0, 2)).append("...").toString();
        } else {
            return sb.append("ID_BENEFICIO: ").append(idbeneficio).append(" | ID_PROGRAMA: ").append(idprograma).append(" | DDD: null").toString();
        }
    }

    public String getHistory(String field) {
        JsonObject jsonObject = null;

        return jsonObject + "";
    }

    @JsonIgnore
    public boolean isEditTb_tipobonus() {
        return super.getEditMap().getOrDefault("TB_TIPOBONUS", false);
    }

    public void setEditTb_tipobonus(boolean checked) {
        super.getEditMap().put("TB_TIPOBONUS", checked);
    }

    @JsonIgnore
    public boolean isEditTb_tipoplano() {
        return super.getEditMap().getOrDefault("TB_TIPOPLANO", false);
    }

    public void setEditTb_tipoplano(boolean checked) {
        super.getEditMap().put("TB_TIPOPLANO", checked);
    }

    @JsonIgnore
    public boolean isEditIncentivo1() {
        return super.getEditMap().getOrDefault("INCENTIVO1", false);
    }

    public void setEditIncentivo1(boolean checked) {
        super.getEditMap().put("INCENTIVO1", checked);
    }

    @JsonIgnore
    public boolean isEditIncentivo2() {
        return super.getEditMap().getOrDefault("INCENTIVO2", false);
    }

    public void setEditIncentivo2(boolean checked) {
        super.getEditMap().put("INCENTIVO2", checked);
    }

    @JsonIgnore
    public boolean isEditOutrosplanos() {
        return super.getEditMap().getOrDefault("OUTROSPLANOS", false);
    }

    public void setEditOutrosplanos(boolean checked) {
        super.getEditMap().put("OUTROSPLANOS", checked);
    }

    @JsonIgnore
    public boolean isEditOffercode() {
        return super.getEditMap().getOrDefault("OFFERCODE", false);
    }

    public void setEditOffercode(boolean checked) {
        super.getEditMap().put("OFFERCODE", checked);
    }

    @JsonIgnore
    public boolean isEditData_inic() {
        return super.getEditMap().getOrDefault("DATA_INIC", false);
    }

    public void setEditData_inic(boolean checked) {
        super.getEditMap().put("DATA_INIC", checked);
    }

    @JsonIgnore
    public boolean isEditData_fim() {
        return super.getEditMap().getOrDefault("DATA_FIM", false);
    }

    public void setEditData_fim(boolean checked) {
        super.getEditMap().put("DATA_FIM", checked);
    }

    @JsonIgnore
    public boolean isEditTaxaadesao() {
        return super.getEditMap().getOrDefault("TAXAADESAO", false);
    }

    public void setEditTaxaadesao(boolean checked) {
        super.getEditMap().put("TAXAADESAO", checked);
    }

    @JsonIgnore
    public boolean isEditTarifasmsdadoswifi() {
        return super.getEditMap().getOrDefault("TARIFASMSDADOSWIFI", false);
    }

    public void setEditTarifasmsdadoswifi(boolean checked) {
        super.getEditMap().put("TARIFASMSDADOSWIFI", checked);
    }
    
    @JsonIgnore
    public boolean isEditTarifasms() {
        return super.getEditMap().getOrDefault("TARIFASMS", false);
    }

    public void setEditTarifasms(boolean checked) {
        super.getEditMap().put("TARIFASMS", checked);
    }

    @JsonIgnore
    public boolean isEditTarifadados() {
        return super.getEditMap().getOrDefault("TARIFADADOS", false);
    }

    public void setEditTarifadados(boolean checked) {
        super.getEditMap().put("TARIFADADOS", checked);
    }

    @JsonIgnore
    public boolean isEditTarifavoz() {
        return super.getEditMap().getOrDefault("TARIFAVOZ", false);
    }

    public void setEditTarifavoz(boolean checked) {
        super.getEditMap().put("TARIFAVOZ", checked);
    }
    
    @JsonIgnore
    public boolean isEditFranquia() {
        return super.getEditMap().getOrDefault("FRANQUIA", false);
    }

    public void setEditFranquia(boolean checked) {
        super.getEditMap().put("FRANQUIA", checked);
    }

    @JsonIgnore
    public boolean isEditCreditos() {
        return super.getEditMap().getOrDefault("CREDITOS", false);
    }

    public void setEditCreditos(boolean checked) {
        super.getEditMap().put("CREDITOS", checked);
    }

    @JsonIgnore
    public boolean isEditElegivel_nova_promo() {
        return super.getEditMap().getOrDefault("ELEGIVEL_NOVA_PROMO", false);
    }

    public void setEditElegivel_nova_promo(boolean checked) {
        super.getEditMap().put("ELEGIVEL_NOVA_PROMO", checked);
    }

    @JsonIgnore
    public boolean isEditTb_taxa_adesaoboleto() {
        return super.getEditMap().getOrDefault("TB_TAXA_ADESAOBOLETO", false);
    }

    public void setEditTb_taxa_adesaoboleto(boolean checked) {
        super.getEditMap().put("TB_TAXA_ADESAOBOLETO", checked);
    }

    @JsonIgnore
    public boolean isEditBolso_ilimitado() {
        return super.getEditMap().getOrDefault("BOLSO_ILIMITADO", false);
    }

    public void setEditBolso_ilimitado(boolean checked) {
        super.getEditMap().put("BOLSO_ILIMITADO", checked);
    }

    @JsonIgnore
    public boolean isEditBolso_allnet() {
        return super.getEditMap().getOrDefault("BOLSO_ALLNET", false);
    }

    public void setEditBolso_allnet(boolean checked) {
        super.getEditMap().put("BOLSO_ALLNET", checked);
    }

    @JsonIgnore
    public boolean isEditFranquia_boleto() {
        return super.getEditMap().getOrDefault("FRANQUIA_BOLETO", false);
    }

    public void setEditFranquia_boleto(boolean checked) {
        super.getEditMap().put("FRANQUIA_BOLETO", checked);
    }

    @JsonIgnore
    public boolean isEditTipo_pagamento() {
        return super.getEditMap().getOrDefault("TIPO_PAGAMENTO", false);
    }

    public void setEditTipo_pagamento(boolean checked) {
        super.getEditMap().put("TIPO_PAGAMENTO", checked);
    }

    @JsonIgnore
    public boolean isEditTb_ussd_convertido() {
        return super.getEditMap().getOrDefault("TB_USSD_CONVERTIDO", false);
    }

    public void setEditTb_ussd_convertido(boolean checked) {
        super.getEditMap().put("TB_USSD_CONVERTIDO", checked);
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

    public String getIncentivo1() {
        return incentivo1;
    }

    public void setIncentivo1(String incentivo1) {
        this.incentivo1 = incentivo1;
    }

    public String getIncentivo2() {
        return incentivo2;
    }

    public void setIncentivo2(String incentivo2) {
        this.incentivo2 = incentivo2;
    }

    public String getDdd() {
        return ddd;
    }
    
    @JsonIgnore
    public String getDddSub() {
        if(ddd != null && ddd.length()>59){
            String dddSub = ddd.substring(0, 59);
            dddSub = dddSub.substring(0, dddSub.lastIndexOf(","))+"...";
            return dddSub;
        }
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public Date getData_inic() {
        return data_inic;
    }

    @JsonGetter("data_inic")
    public String getData_inicStr() {
        if (data_inic != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            return sdf.format(data_inic);
        }

        return "";
    }
    @JsonSetter("data_inic")
    public void setData_inicAsString(String data_inic) {
        try {
            if (data_inic != null && !"".equals(data_inic)) {
                this.data_inic = DateUtils.parse(data_inic);
            }
        } catch (Exception e) {
            logger.warn("invalid data_inic: {}", data_inic);
        }
    }

    public void setData_inic(Date data_inic) {
        this.data_inic = data_inic;
    }

    public Date getData_fim() {
        return data_fim;
    }

    @JsonGetter("data_fim")
    public String getData_fimStr() {
        if (data_fim != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            return sdf.format(data_fim);
        }

        return "";
    }
    @JsonSetter("data_fim")
    public void setData_fimAsString(String data_fim) {
        try {
            if (data_fim != null && !"".equals(data_fim)) {
                this.data_fim = DateUtils.parse(data_fim);
            }
        } catch (Exception e) {
            logger.warn("invalid data_fim: {}", data_fim);
        }
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }

    public String getTb_tipoplano() {
        return tb_tipoplano;
    }

    public void setTb_tipoplano(String tb_tipoplano) {
        this.tb_tipoplano = tb_tipoplano;
    }

    public String getOutrosplanos() {
        return outrosplanos;
    }

    public void setOutrosplanos(String outrosplanos) {
        this.outrosplanos = outrosplanos;
    }

    public String getOffercode() {
        return offercode;
    }

    public void setOffercode(String offercode) {
        this.offercode = offercode;
    }

    public String getTb_tipobonus() {
        return tb_tipobonus;
    }

    public void setTb_tipobonus(String tb_tipobonus) {
        this.tb_tipobonus = tb_tipobonus;
    }

    public String getTarifasmsdadoswifi() {
        return tarifasmsdadoswifi;
    }

    public void setTarifasmsdadoswifi(String tarifasmsdadoswifi) {
        this.tarifasmsdadoswifi = tarifasmsdadoswifi;
    }

    public String getTarifasms() {
        return tarifasms;
    }

    public void setTarifasms(String tarifasms) {
        this.tarifasms = tarifasms;
    }

    public String getTaxaadesao() {
        return taxaadesao;
    }

    public void setTaxaadesao(String taxaadesao) {
        this.taxaadesao = taxaadesao;
    }

    public String getTarifadados() {
        return tarifadados;
    }

    public void setTarifadados(String tarifadados) {
        this.tarifadados = tarifadados;
    }

    public String getTarifavoz() {
        return tarifavoz;
    }

    public void setTarifavoz(String tarifavoz) {
        this.tarifavoz = tarifavoz;
    }

    public String getFranquia() {
        return franquia;
    }

    public void setFranquia(String franquia) {
        this.franquia = franquia;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public boolean getElegivel_nova_promo() {
        return elegivel_nova_promo;
    }
    
    @JsonGetter("elegivel_nova_promo")
    public String getElegivel_nova_promoStr() {
        return elegivel_nova_promo ? "S" : "N";
    }

    @JsonSetter("elegivel_nova_promo")
    public void setElegivel_nova_promo(String elegivel_nova_promo) {
        this.elegivel_nova_promo = "S".equals(elegivel_nova_promo);
    }

    public void setElegivel_nova_promo(boolean elegivel_nova_promo) {
        this.elegivel_nova_promo = elegivel_nova_promo;
    }
    
    public String getTb_taxa_adesaoboleto() {
        return tb_taxa_adesaoboleto;
    }
    
    public void setTb_taxa_adesaoboleto(String tb_taxa_adesaoboleto) {
        this.tb_taxa_adesaoboleto = tb_taxa_adesaoboleto;
    }
    
    public String getBolso_ilimitado() {
        return bolso_ilimitado;
    }

    public void setBolso_ilimitado(String bolso_ilimitado) {
        this.bolso_ilimitado = bolso_ilimitado;
    }

    public String getBolso_allnet() {
        return bolso_allnet;
    }

    public void setBolso_allnet(String bolso_allnet) {
        this.bolso_allnet = bolso_allnet;
    }

    public String getFranquia_boleto() {
        return franquia_boleto;
    }

    public void setFranquia_boleto(String franquia_boleto) {
        this.franquia_boleto = franquia_boleto;
    }

    public String getTipo_pagamento() {
        return tipo_pagamento;
    }

    public void setTipo_pagamento(String tipo_pagamento) {
        this.tipo_pagamento = tipo_pagamento;
    }

    public boolean getTb_ussd_convertido() {
        return tb_ussd_convertido;
    }
    
    @JsonGetter("tb_ussd_convertido")
    public String getTb_ussd_convertidoStr() {
        return tb_ussd_convertido ? "S" : "N";
    }

    @JsonSetter("tb_ussd_convertido")
    public void setTb_ussd_convertido(String tb_ussd_convertido) {
        this.tb_ussd_convertido = "S".equals(tb_ussd_convertido);
    }

    public void setTb_ussd_convertido(boolean tb_ussd_convertido) {
        this.tb_ussd_convertido = tb_ussd_convertido;
    }

    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                VoControle[] pp1 = mapper.readValue(history, VoControle[].class);
                setHistory(Arrays.asList(mapper.readValue(history, VoControle[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (VoControle template : pp1) {
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
}
