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
public class ConfigOfertasPre extends Template {

    private static final Logger logger = LogManager.getLogger();

    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9999999")
    private String id_ofertaocs;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9-99999999999")
    private String id_campanhasiebel;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9-99999999999")
    private String id_ofertasiebel;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9?9")
    private String id_beneficio;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9?9")
    private String id_programa;

    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    @Id
    @FieldInput(name = InputType.STRING, mask = ",99")
    private String ddd;
    private Date data_inic;
    private Date data_fim;
    private String tipo_oferta;
    private boolean elegivelconversao;
    private String freeunitvozelegivel;
    private String freeunitdadoselegivel;
    @Deprecated
    private String limmaxconvmin;
    @Deprecated
    private String limmaxconvmb;
    @FieldInput(name = InputType.INTEGER)
    private String saldomindadosconvmb;
    @FieldInput(name = InputType.MONEY)
    private String valor_taxaadesao;
    private boolean existefallback;
    private boolean existerollover;
    @Deprecated
    private String short_code;
    private boolean ussd_convertido = true;
    private String id_oferta_rentcycle;

    public ConfigOfertasPre() {
        super();
    }

    public ConfigOfertasPre(ConfigOfertasPre selectedConfigOfertasPre) {
        this.id_beneficio = selectedConfigOfertasPre.getId_beneficio();
        this.id_programa = selectedConfigOfertasPre.getId_programa();
        this.id_ofertaocs = selectedConfigOfertasPre.getId_ofertaocs();
        this.id_campanhasiebel = selectedConfigOfertasPre.getId_campanhasiebel();
        this.id_ofertasiebel = selectedConfigOfertasPre.getId_ofertasiebel();
        this.ddd = selectedConfigOfertasPre.getDdd();
        this.data_inic = selectedConfigOfertasPre.getData_inic();
        this.data_fim = selectedConfigOfertasPre.getData_fim();
        this.tipo_oferta = selectedConfigOfertasPre.getTipo_oferta();
        this.elegivelconversao = selectedConfigOfertasPre.getElegivelconversao();
        this.freeunitvozelegivel = selectedConfigOfertasPre.getFreeunitvozelegivel();
        this.freeunitdadoselegivel = selectedConfigOfertasPre.getFreeunitdadoselegivel();
        this.limmaxconvmin = selectedConfigOfertasPre.getLimmaxconvmin();
        this.limmaxconvmb = selectedConfigOfertasPre.getLimmaxconvmb();
        this.saldomindadosconvmb = selectedConfigOfertasPre.getSaldomindadosconvmb();
        this.valor_taxaadesao = selectedConfigOfertasPre.getValor_taxaadesao();
        this.existefallback = selectedConfigOfertasPre.getExistefallback();
        this.existerollover = selectedConfigOfertasPre.getExisterollover();
        this.short_code = selectedConfigOfertasPre.getShort_code();
        this.ussd_convertido = selectedConfigOfertasPre.getUssd_convertido();
        this.id_oferta_rentcycle = selectedConfigOfertasPre.getId_oferta_rentcycle();
    }

    @JsonIgnore
    public String getIdDdd() {
        StringBuilder sb = new StringBuilder();
        if (ddd != null && ddd.length() >= 2) {
            return sb.append("ID_OFERTAOCS: ").append(id_ofertaocs).append(" | DDD: ").append(ddd.substring(0, 2)).append("...").toString();
        } else {
            return sb.append("ID_OFERTAOCS: ").append(id_ofertaocs).append(" | DDD: null").toString();
        }
    }

    public String getHistory(String field) {
        JsonObject jsonObject = null;

        return jsonObject + "";
    }
    
    @JsonIgnore
    public boolean isEditId_beneficio() {
        return super.getEditMap().getOrDefault("ID_BENEFICIO", false);
    }

    public void setEditId_beneficio(boolean checked) {
        super.getEditMap().put("ID_BENEFICIO", checked);
    }
    
    @JsonIgnore
    public boolean isEditId_programa() {
        return super.getEditMap().getOrDefault("ID_PROGRAMA", false);
    }

    public void setEditId_programa(boolean checked) {
        super.getEditMap().put("ID_PROGRAMA", checked);
    }
    
    @JsonIgnore
    public boolean isEditId_campanhasiebel() {
        return super.getEditMap().getOrDefault("ID_CAMPANHASIEBEL", false);
    }

    public void setEditId_campanhasiebel(boolean checked) {
        super.getEditMap().put("ID_CAMPANHASIEBEL", checked);
    }
    
    @JsonIgnore
    public boolean isEditId_ofertasiebel() {
        return super.getEditMap().getOrDefault("ID_OFERTASIEBEL", false);
    }

    public void setEditId_ofertasiebel(boolean checked) {
        super.getEditMap().put("ID_OFERTASIEBEL", checked);
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
    public boolean isEditValor_TaxAdesao() {
        return super.getEditMap().getOrDefault("VALOR_TAXAADESAO", false);
    }

    public void setEditValor_TaxAdesao(boolean checked) {
        super.getEditMap().put("VALOR_TAXAADESAO", checked);
    }

    @JsonIgnore
    public boolean isEditLimmaxconvmin() {
        return super.getEditMap().getOrDefault("LIMMAXCONVMIN", false);
    }

    public void setEditLimmaxconvmin(boolean checked) {
        super.getEditMap().put("LIMMAXCONVMIN", checked);
    }

    @JsonIgnore
    public boolean isEditLimmaxconvmb() {
        return super.getEditMap().getOrDefault("LIMMAXCONVMB", false);
    }

    public void setEditLimmaxconvmb(boolean checked) {
        super.getEditMap().put("LIMMAXCONVMB", checked);
    }

    @JsonIgnore
    public boolean isEditSaldomindadosconvmb() {
        return super.getEditMap().getOrDefault("SALDOMINDADOSCONVMB", false);
    }

    public void setEditSaldomindadosconvmb(boolean checked) {
        super.getEditMap().put("SALDOMINDADOSCONVMB", checked);
    }

    @JsonIgnore
    public boolean isEditTipo_oferta() {
        return super.getEditMap().getOrDefault("TIPO_OFERTA", false);
    }

    public void setEditTipo_oferta(boolean checked) {
        super.getEditMap().put("TIPO_OFERTA", checked);
    }

    @JsonIgnore
    public boolean isEditFreeunitvozelegivel() {
        return super.getEditMap().getOrDefault("FREEUNITVOZELEGIVEL", false);
    }

    public void setEditFreeunitvozelegivel(boolean checked) {
        super.getEditMap().put("FREEUNITVOZELEGIVEL", checked);
    }

    @JsonIgnore
    public boolean isEditFreeunitdadoselegivel() {
        return super.getEditMap().getOrDefault("FREEUNITDADOSELEGIVEL", false);
    }

    public void setEditFreeunitdadoselegivel(boolean checked) {
        super.getEditMap().put("FREEUNITDADOSELEGIVEL", checked);
    }

    @JsonIgnore
    public boolean isEditShort_code() {
        return super.getEditMap().getOrDefault("SHORT_CODE", false);
    }

    public void setEditShort_code(boolean checked) {
        super.getEditMap().put("SHORT_CODE", checked);
    }

    @JsonIgnore
    public boolean isEditElegivelconversao() {
        return super.getEditMap().getOrDefault("ELEGIVELCONVERSAO", false);
    }

    public void setEditElegivelconversao(boolean checked) {
        super.getEditMap().put("ELEGIVELCONVERSAO", checked);
    }

    @JsonIgnore
    public boolean isEditExistefallback() {
        return super.getEditMap().getOrDefault("EXISTEFALLBACK", false);
    }

    public void setEditExistefallback(boolean checked) {
        super.getEditMap().put("EXISTEFALLBACK", checked);
    }

    @JsonIgnore
    public boolean isEditExisterollover() {
        return super.getEditMap().getOrDefault("EXISTEROLLOVER", false);
    }

    public void setEditExisterollover(boolean checked) {
        super.getEditMap().put("EXISTEROLLOVER", checked);
    }
    
    @JsonIgnore
    public boolean isEditUssd_convertido() {
        return super.getEditMap().getOrDefault("USSD_CONVERTIDO", false);
    }

    public void setEditUssd_convertido(boolean checked) {
        super.getEditMap().put("USSD_CONVERTIDO", checked);
    }

    @JsonIgnore
    public boolean isEditIdOfertaRentCycle() {
        return super.getEditMap().getOrDefault("ID_OFERTA_RENTCYCLE", false);
    }

    public void setEditIdOfertaRentCycle(boolean checked) {
        super.getEditMap().put("ID_OFERTA_RENTCYCLE", checked);
    }

    public String getId_ofertaocs() {
        return id_ofertaocs;
    }

    public void setId_ofertaocs(String id_ofertaocs) {
        this.id_ofertaocs = id_ofertaocs;
    }

    public String getId_campanhasiebel() {
        return id_campanhasiebel;
    }

    public void setId_campanhasiebel(String id_campanhasiebel) {
        this.id_campanhasiebel = id_campanhasiebel;
    }

    public String getId_ofertasiebel() {
        return id_ofertasiebel;
    }

    public void setId_ofertasiebel(String id_ofertasiebel) {
        this.id_ofertasiebel = id_ofertasiebel;
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

    public String getTipo_oferta() {
        return tipo_oferta;
    }

    public void setTipo_oferta(String tipo_oferta) {
        this.tipo_oferta = tipo_oferta;
    }

    public boolean getElegivelconversao() {
        return elegivelconversao;
    }

    @JsonGetter("elegivelconversao")
    public String getElegivelconversaoStr() {
        return elegivelconversao ? "S" : "N";
    }

    @JsonSetter("elegivelconversao")
    public void setElegivelconversao(String elegivelconversao) {
        this.elegivelconversao = "S".equals(elegivelconversao);
    }

    public void setElegivelconversao(boolean elegivelconversao) {
        this.elegivelconversao = elegivelconversao;
    }

    public String getFreeunitvozelegivel() {
        return freeunitvozelegivel;
    }

    public void setFreeunitvozelegivel(String freeunitvozelegivel) {
        this.freeunitvozelegivel = freeunitvozelegivel;
    }

    public String getFreeunitdadoselegivel() {
        return freeunitdadoselegivel;
    }

    public void setFreeunitdadoselegivel(String freeunitdadoselegivel) {
        this.freeunitdadoselegivel = freeunitdadoselegivel;
    }

    public String getLimmaxconvmin() {
        return limmaxconvmin;
    }

    public void setLimmaxconvmin(String limmaxconvmin) {
        this.limmaxconvmin = limmaxconvmin;
    }

    public String getLimmaxconvmb() {
        return limmaxconvmb;
    }

    public void setLimmaxconvmb(String limmaxconvmb) {
        this.limmaxconvmb = limmaxconvmb;
    }

    public String getSaldomindadosconvmb() {
        return saldomindadosconvmb;
    }

    public void setSaldomindadosconvmb(String saldomindadosconvmb) {
        this.saldomindadosconvmb = saldomindadosconvmb;
    }

    public String getValor_taxaadesao() {
        return valor_taxaadesao;
    }

    public void setValor_taxaadesao(String valor_taxaadesao) {
        this.valor_taxaadesao = valor_taxaadesao;
    }

    public boolean getExistefallback() {
        return existefallback;
    }

    @JsonGetter("existefallback")
    public String getExistefallbackStr() {
        return existefallback ? "S" : "N";
    }

    @JsonSetter("existefallback")
    public void setExistefallback(String existefallback) {
        this.existefallback = "S".equals(existefallback);
    }

    public void setExistefallback(boolean existefallback) {
        this.existefallback = existefallback;
    }

    public boolean getExisterollover() {
        return existerollover;
    }

    @JsonGetter("existerollover")
    public String getExisterolloverStr() {
        return existerollover ? "S" : "N";
    }

    @JsonSetter("existerollover")
    public void setExisterollover(String existerollover) {
        this.existerollover = "S".equals(existerollover);
    }

    public void setExisterollover(boolean existerollover) {
        this.existerollover = existerollover;
    }

    public String getShort_code() {
        return short_code;
    }

    public void setShort_code(String short_code) {
        this.short_code = short_code;
    }
    
    public boolean getUssd_convertido() {
        return ussd_convertido;
    }

    @JsonGetter("ussd_convertido")
    public String getUssd_convertidoStr() {
        return ussd_convertido ? "S" : "N";
    }

    @JsonSetter("ussd_convertido")
    public void setUssd_convertido(String ussd_convertido) {
        this.ussd_convertido = "S".equals(ussd_convertido);
    }

    public void setUssd_convertido(boolean ussd_convertido) {
        this.ussd_convertido = ussd_convertido;
    }
    
    public String getId_oferta_rentcycle() {
        return id_oferta_rentcycle;
    }

    public void setId_oferta_rentcycle(String id_oferta_rentcycle) {
        this.id_oferta_rentcycle = id_oferta_rentcycle;
    }

    public String getId_beneficio() {
        return id_beneficio;
    }

    public void setId_beneficio(String id_beneficio) {
        this.id_beneficio = id_beneficio;
    }

    public String getId_programa() {
        return id_programa;
    }

    public void setId_programa(String id_programa) {
        this.id_programa = id_programa;
    }
    
    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                ConfigOfertasPre[] pp1 = mapper.readValue(history, ConfigOfertasPre[].class);
                setHistory(Arrays.asList(mapper.readValue(history, ConfigOfertasPre[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (ConfigOfertasPre template : pp1) {
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
        ddd = ddd!=null?ddd.replace(" " , "").replace(";", ","):ddd;

    }
}
