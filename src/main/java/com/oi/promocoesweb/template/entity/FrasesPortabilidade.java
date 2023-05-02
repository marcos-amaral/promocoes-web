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
import javax.json.JsonObject;

/**
 *
 * @author Jpereirc
 */
public class FrasesPortabilidade extends Template {

    private int id;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    private String idbeneficio;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    private String idprograma;
    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    private String ddd;
    private String informapromo;
    private String incentivo_portabilidade;
    private String naocliente_sva;
    
    

    public FrasesPortabilidade() {
        super();
    }

    public FrasesPortabilidade(FrasesPortabilidade selectedConfigOfertasPre) {
        this.idbeneficio = selectedConfigOfertasPre.getIdbeneficio();
        this.idprograma = selectedConfigOfertasPre.getIdprograma();
        this.ddd = selectedConfigOfertasPre.getDdd();
        this.informapromo = selectedConfigOfertasPre.getInformapromo();
        this.incentivo_portabilidade = selectedConfigOfertasPre.getIncentivo_portabilidade();
        this.naocliente_sva = selectedConfigOfertasPre.getNaocliente_sva();
        
  
        
    }

    public String getHistory(String field) {
        JsonObject jsonObject = null;

        return jsonObject + "";
    }
    @JsonIgnore
    public String getIdDdd() {
        StringBuilder sb = new StringBuilder();
        if (ddd != null && ddd.length() >= 2) {
            return sb.append("IDBENEFICIO: ").append(idbeneficio).append("| IDPROGRAMA: ").append(idprograma).append(" | DDD: ").append(ddd.substring(0, 2)).append("...").toString();
        } else {
            return sb.append("IDBENEFICIO: ").append(idbeneficio).append("| IDPROGRAMA: ").append(idprograma).append(" | DDD: null").toString();
        }
    }

    @JsonIgnore
    public boolean isEditIdBeneficio() {
        return super.getEditMap().getOrDefault("IDBENEFICIO", false);
    }

    public void setEditIdBeneficio(boolean checked) {
        super.getEditMap().put("IDBENEFICIO", checked);
    }

    @JsonIgnore
    public boolean isEditIdPrograma() {
        return super.getEditMap().getOrDefault("IDPROGRAMA", false);
    }

    public void setEditIdPrograma(boolean checked) {
        super.getEditMap().put("IDPROGRAMA", checked);
    }
    @JsonIgnore
    public boolean isEditInformapromo() {
        return super.getEditMap().getOrDefault("INFORMAPROMO", false);
    }

    public void setEditInformapromo(boolean checked) {
        super.getEditMap().put("INFORMAPROMO", checked);
    }
    @JsonIgnore
    public boolean isEditIncentivo_portabilidade() {
        return super.getEditMap().getOrDefault("INCENTIVO_PORTABILIDADE", false);
    }

    public void setEditIncentivo_portabilidade(boolean checked) {
        super.getEditMap().put("INCENTIVO_PORTABILIDADE", checked);
    }
    @JsonIgnore
    public boolean isEditNaocliente_sva() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_SVA", false);
    }

    public void setEditNaocliente_sva(boolean checked) {
        super.getEditMap().put("NAOCLIENTE_SVA", checked);
    }

    @JsonIgnore
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getInformapromo() {
        return informapromo;
    }

    public void setInformapromo(String informapromo) {
        this.informapromo = informapromo;
    }

    public String getIncentivo_portabilidade() {
        return incentivo_portabilidade;
    }

    public void setIncentivo_portabilidade(String incentivo_portabilidade) {
        this.incentivo_portabilidade = incentivo_portabilidade;
    }

    public String getNaocliente_sva() {
        return naocliente_sva;
    }

    public void setNaocliente_sva(String naocliente_sva) {
        this.naocliente_sva = naocliente_sva;
    }
  

    
    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                FrasesPortabilidade[] pp1 = mapper.readValue(history, FrasesPortabilidade[].class);
                setHistory(Arrays.asList(mapper.readValue(history, FrasesPortabilidade[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (FrasesPortabilidade template : pp1) {
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
