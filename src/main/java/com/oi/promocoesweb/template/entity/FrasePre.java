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
 * @author jpereirc
 */
public class FrasePre extends Template {
    
    
    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    private String ddd;

    private String cod_oferta;
    private String oferta1;
    private String oferta2;
    private String oferta3;
    private String oferta_erro_in;
    private String oferta_siebel;
    private String segprompt;
    
    public FrasePre(){
        super();
    }
    
    public FrasePre(FrasePre selectedConfigOfertasPre){
      this.cod_oferta = selectedConfigOfertasPre.getCod_oferta();
      this.ddd = selectedConfigOfertasPre.getDdd();
      this.oferta1 = selectedConfigOfertasPre.getOferta1();
      this.oferta2 = selectedConfigOfertasPre.getOferta2();
      this.oferta3 = selectedConfigOfertasPre.getOferta3();
      this.oferta_erro_in = selectedConfigOfertasPre.getOferta_erro_in();
      this.oferta_siebel = selectedConfigOfertasPre.getOferta_siebel();
      this.segprompt = selectedConfigOfertasPre.getSegprompt();
    }
    @JsonIgnore
    public String getIdDdd() {
        StringBuilder sb = new StringBuilder();
        if (ddd != null && ddd.length() >= 2) {
            return sb.append(" | DDD: ").append(ddd.substring(0, 2)).append("...").toString();
        } else {
            return sb.append(" | DDD: null").toString();
        }
    }
    
     public String getHistory(String field) {
        JsonObject jsonObject = null;

        return jsonObject + "";
    }
     
    @JsonIgnore
    public boolean isEditCod_oferta() {
        return super.getEditMap().getOrDefault("COD_OFERTA", false);
    }
    
    public void setEditCod_oferta(boolean checked) {
        super.getEditMap().put("COD_OFERTA", checked);
    }
    
    @JsonIgnore
    public boolean isEditDdd() {
        return super.getEditMap().getOrDefault("DDD", false);
    }
    
    public void setEditDdd(boolean checked){
        super.getEditMap().put("DDD", checked);
    }
    
    @JsonIgnore
    public boolean isEditOferta1() {
        return super.getEditMap().getOrDefault("OFERTA1", false);
    }
    
    public void setEditOferta1(boolean checked){
	super.getEditMap().put("OFERTA1", checked);
    }      

    @JsonIgnore
    public boolean isEditOferta2() {
        return super.getEditMap().getOrDefault("OFERTA2", false);
    }
    
    public void setEditOferta2(boolean checked){
	super.getEditMap().put("OFERTA2", checked);
    }
    
    @JsonIgnore
    public boolean isEditOferta3() {
        return super.getEditMap().getOrDefault("OFERTA3", false);
    }
    
    public void setEditOferta3(boolean checked){
        super.getEditMap().put("OFERTA3", checked);
    }
    
    @JsonIgnore
    public boolean isEditOferta_erro_in() {
        return super.getEditMap().getOrDefault("OFERTA_ERRO_IN", false);
    }
    
    public void setEditOferta_erro_in(boolean checked){
        super.getEditMap().put("OFERTA_ERRO_IN", checked);
    }
    
    @JsonIgnore
    public boolean isEditOferta_siebel() {
        return super.getEditMap().getOrDefault("OFERTA_SIEBEL", false);
    }
    
    public void setEditOferta_siebel(boolean checked){
	super.getEditMap().put("OFERTA_SIEBEL",checked);
    }
    
    @JsonIgnore
    public boolean isEditSegprompt() {
        return super.getEditMap().getOrDefault("SEGPROMPT", false); 
    }
    
    public void setEditSegprompt(boolean checked){
        super.getEditMap().put("SEGPROMPT", checked);
    }
    
    
    
    //Getters and Setts

    public String getCod_oferta() {
        return cod_oferta;
    }

    public void setCod_oferta(String cod_oferta) {
        this.cod_oferta = cod_oferta;
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

    public String getOferta1() {
        return oferta1;
    }

    public void setOferta1(String oferta1) {
        this.oferta1 = oferta1;
    }

    public String getOferta2() {
        return oferta2;
    }

    public void setOferta2(String oferta2) {
        this.oferta2 = oferta2;
    }

    public String getOferta3() {
        return oferta3;
    }

    public void setOferta3(String oferta3) {
        this.oferta3 = oferta3;
    }

    public String getOferta_erro_in() {
        return oferta_erro_in;
    }

    public void setOferta_erro_in(String oferta_erro_in) {
        this.oferta_erro_in = oferta_erro_in;
    }

    public String getOferta_siebel() {
        return oferta_siebel;
    }

    public void setOferta_siebel(String oferta_erro_siebel) {
        this.oferta_siebel = oferta_erro_siebel;
    }

    public String getSegprompt() {
        return segprompt;
    }

    public void setSegprompt(String segprompt) {
        this.segprompt = segprompt;
    }
    
    
    
   //
    
    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                FrasePre[] pp1 = mapper.readValue(history, FrasePre[].class);
                setHistory(Arrays.asList(mapper.readValue(history, FrasePre[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (FrasePre template : pp1) {
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
    

