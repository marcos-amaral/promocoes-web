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
 * @author Jpereirc
 */
public class IncentivoOfertasPreS2S extends Template {

    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    @Id
    @FieldInput(name = InputType.STRING, mask = ",99")
    private String ddd;
    @FieldInput(name = InputType.MONEY)
    private String recacumuladamesanteriorinicial;
    @FieldInput(name = InputType.MONEY)
    private String recacumuladamesanteriorfinal;
    private String ofertapromo1;
    private String ofertapromo2;
    
    @JsonIgnore
    private String selectedOfertapromo1_1 = "";
    @JsonIgnore
    private String selectedOfertapromo1_2 = "";
    @JsonIgnore
    private String selectedOfertapromo1_3 = "";
    @JsonIgnore
    private String selectedOfertapromo2_1 = "";
    @JsonIgnore
    private String selectedOfertapromo2_2 = "";
    @JsonIgnore
    private String selectedOfertapromo2_3 = "";

    public IncentivoOfertasPreS2S() {
        super();
    }

    public IncentivoOfertasPreS2S(IncentivoOfertasPreS2S selectedConfigOfertasPre) {
        this.ddd = selectedConfigOfertasPre.getDdd();
        this.ofertapromo1 = selectedConfigOfertasPre.getOfertapromo1();
        this.ofertapromo2 = selectedConfigOfertasPre.getOfertapromo2();
        this.recacumuladamesanteriorinicial = selectedConfigOfertasPre.getRecacumuladamesanteriorinicial();
        this.recacumuladamesanteriorfinal = selectedConfigOfertasPre.getRecacumuladamesanteriorfinal();
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
    public boolean isEditRecacumuladamesanteriorinicial() {
        return super.getEditMap().getOrDefault("RECACUMULADAMESANTERIORINICIAL", false);
    }

    public void setEditRecacumuladamesanteriorinicial(boolean checked) {
        super.getEditMap().put("RECACUMULADAMESANTERIORINICIAL", checked);
    }

    @JsonIgnore
    public boolean isEditRecacumuladamesanteriorfinal() {
        return super.getEditMap().getOrDefault("RECACUMULADAMESANTERIORFINAL", false);
    }

    public void setEditRecacumuladamesanteriorfinal(boolean checked) {
        super.getEditMap().put("RECACUMULADAMESANTERIORFINAL", checked);
    }

    @JsonIgnore
    public boolean isEditOfertapromo1() {
        return super.getEditMap().getOrDefault("OFERTAPROMO1", false);
    }

    public void setEditOfertapromo1(boolean checked) {
        super.getEditMap().put("OFERTAPROMO1", checked);
    }

    @JsonIgnore
    public boolean isEditOfertapromo2() {
        return super.getEditMap().getOrDefault("OFERTAPROMO2", false);
    }

    public void setEditOfertapromo2(boolean checked) {
        super.getEditMap().put("OFERTAPROMO2", checked);
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

    public String getOfertapromo1() {
        return ofertapromo1;
    }

    public void setOfertapromo1(String ofertapromo1) {
        this.ofertapromo1 = ofertapromo1;
        if (ofertapromo1 != null && !"".equals(ofertapromo1)) {
            String[] split = ofertapromo1.split(",");

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

    public String getOfertapromo2() {
        return ofertapromo2;
    }

    public void setOfertapromo2(String ofertapromo2) {
        this.ofertapromo2 = ofertapromo2;
        if (ofertapromo2 != null && !"".equals(ofertapromo2)) {
            String[] split = ofertapromo2.split(",");

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

    public String getRecacumuladamesanteriorinicial() {
        return recacumuladamesanteriorinicial;
    }

    public void setRecacumuladamesanteriorinicial(String recacumuladamesanteriorinicial) {
        this.recacumuladamesanteriorinicial = recacumuladamesanteriorinicial;
    }

    public String getRecacumuladamesanteriorfinal() {
        return recacumuladamesanteriorfinal;
    }

    public void setRecacumuladamesanteriorfinal(String recacumuladamesanteriorfinal) {
        this.recacumuladamesanteriorfinal = recacumuladamesanteriorfinal;
    }

    @JsonIgnore
    public String getSelectedOfertapromo1_1() {
        return selectedOfertapromo1_1;
    }

    public void setSelectedOfertapromo1_1(String selectedOfertapromo1_1) {
        this.selectedOfertapromo1_1 = selectedOfertapromo1_1;
        
        ofertapromo1 = "";
        if(selectedOfertapromo1_1 !=null && !"".equals(selectedOfertapromo1_1)){
            ofertapromo1+=selectedOfertapromo1_1;
        }
        else{
            selectedOfertapromo1_2 = null;
            selectedOfertapromo1_3 = null;
        }
        
        if(selectedOfertapromo1_2 !=null && !"".equals(selectedOfertapromo1_2)){
            ofertapromo1+=","+selectedOfertapromo1_2;
        }
        if(selectedOfertapromo1_3 !=null && !"".equals(selectedOfertapromo1_3)){
            ofertapromo1+=","+selectedOfertapromo1_3;
        }
        
        if(ofertapromo1.startsWith(",")) ofertapromo1 = ofertapromo1.substring(1);
    }

    @JsonIgnore
    public String getSelectedOfertapromo1_2() {
        return selectedOfertapromo1_2;
    }

    public void setSelectedOfertapromo1_2(String selectedOfertapromo1_2) {
        this.selectedOfertapromo1_2 = selectedOfertapromo1_2;
        
        ofertapromo1 = "";
        if(selectedOfertapromo1_1 !=null && !"".equals(selectedOfertapromo1_1)){
            ofertapromo1+=selectedOfertapromo1_1;
        }
        else{
            selectedOfertapromo1_3 = null;
        }
        
        if(selectedOfertapromo1_2 !=null && !"".equals(selectedOfertapromo1_2)){
            ofertapromo1+=","+selectedOfertapromo1_2;
        }
        if(selectedOfertapromo1_3 !=null && !"".equals(selectedOfertapromo1_3)){
            ofertapromo1+=","+selectedOfertapromo1_3;
        }
        
        if(ofertapromo1.startsWith(",")) ofertapromo1 = ofertapromo1.substring(1);
    }

    @JsonIgnore
    public String getSelectedOfertapromo1_3() {
        return selectedOfertapromo1_3;
    }

    public void setSelectedOfertapromo1_3(String selectedOfertapromo1_3) {
        this.selectedOfertapromo1_3 = selectedOfertapromo1_3;
        
        ofertapromo1 = "";
        if(selectedOfertapromo1_1 !=null && !"".equals(selectedOfertapromo1_1)){
            ofertapromo1+=selectedOfertapromo1_1;
        }
        if(selectedOfertapromo1_2 !=null && !"".equals(selectedOfertapromo1_2)){
            ofertapromo1+=","+selectedOfertapromo1_2;
        }
        if(selectedOfertapromo1_3 !=null && !"".equals(selectedOfertapromo1_3)){
            ofertapromo1+=","+selectedOfertapromo1_3;
        }
        
        if(ofertapromo1.startsWith(",")) ofertapromo1 = ofertapromo1.substring(1);
    }

    @JsonIgnore
    public String getSelectedOfertapromo2_1() {
        return selectedOfertapromo2_1;
    }

    public void setSelectedOfertapromo2_1(String selectedOfertapromo2_1) {
        this.selectedOfertapromo2_1 = selectedOfertapromo2_1;
        
        ofertapromo2 = "";
        if(selectedOfertapromo2_1 !=null && !"".equals(selectedOfertapromo2_1)){
            ofertapromo2+=selectedOfertapromo2_1;
        }
        else{
            selectedOfertapromo2_2 = null;
            selectedOfertapromo2_3 = null;
        }
        if(selectedOfertapromo2_2 !=null && !"".equals(selectedOfertapromo2_2)){
            ofertapromo2+=","+selectedOfertapromo2_2;
        }
        if(selectedOfertapromo2_3 !=null && !"".equals(selectedOfertapromo2_3)){
            ofertapromo2+=","+selectedOfertapromo2_3;
        }
        
        if(ofertapromo2.startsWith(",")) ofertapromo2 = ofertapromo2.substring(1);
    }

    @JsonIgnore
    public String getSelectedOfertapromo2_2() {
        return selectedOfertapromo2_2;
    }

    public void setSelectedOfertapromo2_2(String selectedOfertapromo2_2) {
        this.selectedOfertapromo2_2 = selectedOfertapromo2_2;
        
        ofertapromo2 = "";
        if(selectedOfertapromo2_1!=null && !"".equals(selectedOfertapromo2_1)){
            ofertapromo2+=selectedOfertapromo2_1;
        }
        else{
            selectedOfertapromo2_3 = null;
        }
        
        if(selectedOfertapromo2_2!=null && !"".equals(selectedOfertapromo2_2)){
            ofertapromo2+=","+selectedOfertapromo2_2;
        }
        if(selectedOfertapromo2_3!=null && !"".equals(selectedOfertapromo2_3)){
            ofertapromo2+=","+selectedOfertapromo2_3;
        }
        
        if(ofertapromo2.startsWith(",")) ofertapromo2 = ofertapromo2.substring(1);
    }

    @JsonIgnore
    public String getSelectedOfertapromo2_3() {
        return selectedOfertapromo2_3;
    }

    public void setSelectedOfertapromo2_3(String selectedOfertapromo2_3) {
        this.selectedOfertapromo2_3 = selectedOfertapromo2_3;
        
        ofertapromo2 = "";
        if(selectedOfertapromo2_1!=null && !"".equals(selectedOfertapromo2_1)){
            ofertapromo2+=selectedOfertapromo2_1;
        }
        if(selectedOfertapromo2_2!=null && !"".equals(selectedOfertapromo2_2)){
            ofertapromo2+=","+selectedOfertapromo2_2;
        }
        if(selectedOfertapromo2_3!=null && !"".equals(selectedOfertapromo2_3)){
            ofertapromo2+=","+selectedOfertapromo2_3;
        }
        
        if(ofertapromo2.startsWith(",")) ofertapromo2 = ofertapromo2.substring(1);
    }
    
    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                IncentivoOfertasPreS2S[] pp1 = mapper.readValue(history, IncentivoOfertasPreS2S[].class);
                setHistory(Arrays.asList(mapper.readValue(history, IncentivoOfertasPreS2S[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (IncentivoOfertasPreS2S template : pp1) {
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
