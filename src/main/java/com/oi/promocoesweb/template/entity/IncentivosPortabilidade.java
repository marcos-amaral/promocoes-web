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
public class IncentivosPortabilidade extends Template {

    private int id;
    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    private String ddd;
    private String oferta1;
    private String oferta2;
    
    private String selectedOfertapromo1_1 = "";
    private String selectedOfertapromo1_2 = "";
    private String selectedOfertapromo1_3 = "";
    private String selectedOfertapromo2_1 = "";
    private String selectedOfertapromo2_2 = "";
    private String selectedOfertapromo2_3 = "";

    public IncentivosPortabilidade() {
        super();
    }

    public IncentivosPortabilidade(IncentivosPortabilidade selectedConfigOfertasPre) {
        this.ddd = selectedConfigOfertasPre.getDdd();
        this.oferta1 = selectedConfigOfertasPre.getOferta1();
        this.oferta2 = selectedConfigOfertasPre.getOferta2();
        this.selectedOfertapromo1_1 = selectedConfigOfertasPre.getSelectedOfertapromo1_1();
        this.selectedOfertapromo1_2 = selectedConfigOfertasPre.getSelectedOfertapromo1_2();
        this.selectedOfertapromo1_3 = selectedConfigOfertasPre.getSelectedOfertapromo1_3();
        this.selectedOfertapromo2_1 = selectedConfigOfertasPre.getSelectedOfertapromo2_1();
        this.selectedOfertapromo2_2 = selectedConfigOfertasPre.getSelectedOfertapromo2_2();
        this.selectedOfertapromo2_3 = selectedConfigOfertasPre.getSelectedOfertapromo2_3();
    }

    public String getHistory(String field) {
        JsonObject jsonObject = null;

        return jsonObject + "";
    }


    @JsonIgnore
    public boolean isEditOferta1() {
        return super.getEditMap().getOrDefault("OFERTA1", false);
    }

    public void setEditOferta1(boolean checked) {
        super.getEditMap().put("OFERTA1", checked);
    }

    @JsonIgnore
    public boolean isEditOferta2() {
        return super.getEditMap().getOrDefault("OFERTA2", false);
    }

    public void setEditOferta2(boolean checked) {
        super.getEditMap().put("OFERTA2", checked);
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

    public String getOferta1() {
        return oferta1;
    }

    public void setOferta1(String id_ofertaocs) {
        this.oferta1 = id_ofertaocs;
        if (oferta1 != null && !"".equals(oferta1)) {
            String[] split = oferta1.split(",");

            if (split.length > 0) {
                selectedOfertapromo1_1 = split[0];
            }
            if (split.length > 1) {
                selectedOfertapromo1_2 = split[1];
            }
            if (split.length > 2) {
                selectedOfertapromo1_3 = split[2];
            }
        }
    }

    public String getOferta2() {
        return oferta2;
    }

    public void setOferta2(String id_ofertaocs) {
        this.oferta2 = id_ofertaocs;
        if (oferta2 != null && !"".equals(oferta2)) {
            String[] split = oferta2.split(",");

            if (split.length > 0) {
                selectedOfertapromo2_1 = split[0];
            }
            if (split.length > 1) {
                selectedOfertapromo2_2 = split[1];
            }
            if (split.length > 2) {
                selectedOfertapromo2_3 = split[2];
            }
        }
    }

    @JsonIgnore
    public String getSelectedOfertapromo1_1() {
        return selectedOfertapromo1_1;
    }

    public void setSelectedOfertapromo1_1(String selectedOfertapromo1_1) {
        this.selectedOfertapromo1_1 = selectedOfertapromo1_1;
        
        oferta1 = "";
        if(selectedOfertapromo1_1 !=null && !"".equals(selectedOfertapromo1_1)){
            oferta1+=selectedOfertapromo1_1;
        }
        else{
            selectedOfertapromo1_2 = null;
            selectedOfertapromo1_3 = null;
        }
        
        if(selectedOfertapromo1_2 !=null && !"".equals(selectedOfertapromo1_2)){
            oferta1+=","+selectedOfertapromo1_2;
        }
        if(selectedOfertapromo1_3 !=null && !"".equals(selectedOfertapromo1_3)){
            oferta1+=","+selectedOfertapromo1_3;
        }
        
        if(oferta1.startsWith(",")) oferta1 = oferta1.substring(1);
    }

    @JsonIgnore
    public String getSelectedOfertapromo1_2() {
        return selectedOfertapromo1_2;
    }

    public void setSelectedOfertapromo1_2(String selectedOfertapromo1_2) {
        this.selectedOfertapromo1_2 = selectedOfertapromo1_2;
        
        oferta1 = "";
        if(selectedOfertapromo1_1 !=null && !"".equals(selectedOfertapromo1_1)){
            oferta1+=selectedOfertapromo1_1;
        }
        else{
            selectedOfertapromo1_3 = null;
        }
        
        if(selectedOfertapromo1_2 !=null && !"".equals(selectedOfertapromo1_2)){
            oferta1+=","+selectedOfertapromo1_2;
        }
        if(selectedOfertapromo1_3 !=null && !"".equals(selectedOfertapromo1_3)){
            oferta1+=","+selectedOfertapromo1_3;
        }
        
        if(oferta1.startsWith(",")) oferta1 = oferta1.substring(1);
    }

    @JsonIgnore
    public String getSelectedOfertapromo1_3() {
        return selectedOfertapromo1_3;
    }

    public void setSelectedOfertapromo1_3(String selectedOfertapromo1_3) {
        this.selectedOfertapromo1_3 = selectedOfertapromo1_3;
        
        oferta1 = "";
        if(selectedOfertapromo1_1 !=null && !"".equals(selectedOfertapromo1_1)){
            oferta1+=selectedOfertapromo1_1;
        }
        if(selectedOfertapromo1_2 !=null && !"".equals(selectedOfertapromo1_2)){
            oferta1+=","+selectedOfertapromo1_2;
        }
        if(selectedOfertapromo1_3 !=null && !"".equals(selectedOfertapromo1_3)){
            oferta1+=","+selectedOfertapromo1_3;
        }
        
        if(oferta1.startsWith(",")) oferta1 = oferta1.substring(1);
    }

    @JsonIgnore
    public String getSelectedOfertapromo2_1() {
        return selectedOfertapromo2_1;
    }

    public void setSelectedOfertapromo2_1(String selectedOfertapromo2_1) {
        this.selectedOfertapromo2_1 = selectedOfertapromo2_1;
        
        oferta2 = "";
        if(selectedOfertapromo2_1 !=null && !"".equals(selectedOfertapromo2_1)){
            oferta2+=selectedOfertapromo2_1;
        }
        else{
            selectedOfertapromo2_2 = null;
            selectedOfertapromo2_3 = null;
        }
        if(selectedOfertapromo2_2 !=null && !"".equals(selectedOfertapromo2_2)){
            oferta2+=","+selectedOfertapromo2_2;
        }
        if(selectedOfertapromo2_3 !=null && !"".equals(selectedOfertapromo2_3)){
            oferta2+=","+selectedOfertapromo2_3;
        }
        
        if(oferta2.startsWith(",")) oferta2 = oferta2.substring(1);
    }

    @JsonIgnore
    public String getSelectedOfertapromo2_2() {
        return selectedOfertapromo2_2;
    }

    public void setSelectedOfertapromo2_2(String selectedOfertapromo2_2) {
        this.selectedOfertapromo2_2 = selectedOfertapromo2_2;
        
        oferta2 = "";
        if(selectedOfertapromo2_1!=null && !"".equals(selectedOfertapromo2_1)){
            oferta2+=selectedOfertapromo2_1;
        }
        else{
            selectedOfertapromo2_3 = null;
        }
        
        if(selectedOfertapromo2_2!=null && !"".equals(selectedOfertapromo2_2)){
            oferta2+=","+selectedOfertapromo2_2;
        }
        if(selectedOfertapromo2_3!=null && !"".equals(selectedOfertapromo2_3)){
            oferta2+=","+selectedOfertapromo2_3;
        }
        
        if(oferta2.startsWith(",")) oferta2 = oferta2.substring(1);
    }

    @JsonIgnore
    public String getSelectedOfertapromo2_3() {
        return selectedOfertapromo2_3;
    }

    public void setSelectedOfertapromo2_3(String selectedOfertapromo2_3) {
        this.selectedOfertapromo2_3 = selectedOfertapromo2_3;
        
        oferta2 = "";
        if(selectedOfertapromo2_1!=null && !"".equals(selectedOfertapromo2_1)){
            oferta2+=selectedOfertapromo2_1;
        }
        if(selectedOfertapromo2_2!=null && !"".equals(selectedOfertapromo2_2)){
            oferta2+=","+selectedOfertapromo2_2;
        }
        if(selectedOfertapromo2_3!=null && !"".equals(selectedOfertapromo2_3)){
            oferta2+=","+selectedOfertapromo2_3;
        }
        
        if(oferta2.startsWith(",")) oferta2 = oferta2.substring(1);
    }
    
    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                IncentivosPortabilidade[] pp1 = mapper.readValue(history, IncentivosPortabilidade[].class);
                setHistory(Arrays.asList(mapper.readValue(history, IncentivosPortabilidade[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (IncentivosPortabilidade template : pp1) {
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
