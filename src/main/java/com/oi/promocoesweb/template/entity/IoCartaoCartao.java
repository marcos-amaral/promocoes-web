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
import java.util.HashSet;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;
import javax.json.JsonObject;

/**
 *
 * @author mmouraam
 */
public class IoCartaoCartao extends Template {

    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    @Id
    @FieldInput(name = InputType.STRING, mask = ",99")
    private String ddd;
    @FieldInput(name = InputType.MONEY)
    private String recacumuladainicial;
    @FieldInput(name = InputType.MONEY)
    private String recacumuladafinal;
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

    public IoCartaoCartao() {
        super();
    }

    public IoCartaoCartao(IoCartaoCartao selectedConfigOfertasPre) {
        this.ddd = selectedConfigOfertasPre.getDdd();
        this.ofertapromo1 = selectedConfigOfertasPre.getOfertapromo1();
        this.ofertapromo2 = selectedConfigOfertasPre.getOfertapromo2();
        this.recacumuladainicial = selectedConfigOfertasPre.getRecacumuladainicial();
        this.recacumuladafinal = selectedConfigOfertasPre.getRecacumuladafinal();
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
    public boolean isEditRecacumuladainicial() {
        return super.getEditMap().getOrDefault("RECACUMULADAINICIAL", false);
    }

    public void setEditRecacumuladainicial(boolean checked) {
        super.getEditMap().put("RECACUMULADAINICIAL", checked);
    }

    @JsonIgnore
    public boolean isEditRecacumuladafinal() {
        return super.getEditMap().getOrDefault("RECACUMULADAFINAL", false);
    }

    public void setEditRecacumuladafinal(boolean checked) {
        super.getEditMap().put("RECACUMULADAFINAL", checked);
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

    public void setOfertapromo1(String id_ofertaocs) {

        this.ofertapromo1 = id_ofertaocs;
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

    public void setOfertapromo2(String id_ofertaocs) {
        this.ofertapromo2 = id_ofertaocs;
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

    public String getRecacumuladainicial() {
        return recacumuladainicial;
    }

    public void setRecacumuladainicial(String recacumuladainicial) {
        this.recacumuladainicial = recacumuladainicial;
    }

    public String getRecacumuladafinal() {
        return recacumuladafinal;
    }

    public void setRecacumuladafinal(String recacumuladafinal) {
        this.recacumuladafinal = recacumuladafinal;
    }

    @JsonIgnore
    public String getSelectedOfertapromo1_1() {
        return selectedOfertapromo1_1;
    }

    public void setSelectedOfertapromo1_1(String selectedOfertapromo1_1) {
        this.selectedOfertapromo1_1 = selectedOfertapromo1_1;

        ofertapromo1 = "";
        if (selectedOfertapromo1_1 != null && !"".equals(selectedOfertapromo1_1)) {
            ofertapromo1 += selectedOfertapromo1_1;
        } else {
            selectedOfertapromo1_2 = null;
            selectedOfertapromo1_3 = null;
        }

        if (selectedOfertapromo1_2 != null && !"".equals(selectedOfertapromo1_2)) {
            ofertapromo1 += "," + selectedOfertapromo1_2;
        } else {
            selectedOfertapromo1_3 = null;
        }

        if (selectedOfertapromo1_3 != null && !"".equals(selectedOfertapromo1_3)) {
            ofertapromo1 += "," + selectedOfertapromo1_3;
        }

        if (ofertapromo1.startsWith(",")) {
            ofertapromo1 = ofertapromo1.substring(1);
        }
    }

    @JsonIgnore
    public String getSelectedOfertapromo1_2() {
        return selectedOfertapromo1_2;
    }

    public void setSelectedOfertapromo1_2(String selectedOfertapromo1_2) {
        this.selectedOfertapromo1_2 = selectedOfertapromo1_2;

        ofertapromo1 = "";
        if (selectedOfertapromo1_1 != null && !"".equals(selectedOfertapromo1_1)) {
            ofertapromo1 += selectedOfertapromo1_1;
        } else {
            selectedOfertapromo1_2 = null;
            selectedOfertapromo1_3 = null;
        }

        if (selectedOfertapromo1_2 != null && !"".equals(selectedOfertapromo1_2)) {
            ofertapromo1 += "," + selectedOfertapromo1_2;
        } else {
            selectedOfertapromo1_3 = null;
        }
        if (selectedOfertapromo1_3 != null && !"".equals(selectedOfertapromo1_3)) {
            ofertapromo1 += "," + selectedOfertapromo1_3;
        }

        if (ofertapromo1.startsWith(",")) {
            ofertapromo1 = ofertapromo1.substring(1);
        }
    }

    @JsonIgnore
    public String getSelectedOfertapromo1_3() {
        return selectedOfertapromo1_3;
    }

    public void setSelectedOfertapromo1_3(String selectedOfertapromo1_3) {
        this.selectedOfertapromo1_3 = selectedOfertapromo1_3;

        ofertapromo1 = "";
        if (selectedOfertapromo1_1 != null && !"".equals(selectedOfertapromo1_1)) {
            ofertapromo1 += selectedOfertapromo1_1;
        }
        if (selectedOfertapromo1_2 != null && !"".equals(selectedOfertapromo1_2)) {
            ofertapromo1 += "," + selectedOfertapromo1_2;
        }
        if (selectedOfertapromo1_3 != null && !"".equals(selectedOfertapromo1_3)) {
            ofertapromo1 += "," + selectedOfertapromo1_3;
        }

        if (ofertapromo1.startsWith(",")) {
            ofertapromo1 = ofertapromo1.substring(1);
        }
    }

    @JsonIgnore
    public String getSelectedOfertapromo2_1() {
        return selectedOfertapromo2_1;
    }

    public void setSelectedOfertapromo2_1(String selectedOfertapromo2_1) {
        this.selectedOfertapromo2_1 = selectedOfertapromo2_1;

        ofertapromo2 = "";
        if (selectedOfertapromo2_1 != null && !"".equals(selectedOfertapromo2_1)) {
            ofertapromo2 += selectedOfertapromo2_1;
        } else {
            selectedOfertapromo2_2 = null;
            selectedOfertapromo2_3 = null;
        }
        if (selectedOfertapromo2_2 != null && !"".equals(selectedOfertapromo2_2)) {
            ofertapromo2 += "," + selectedOfertapromo2_2;
        }
        if (selectedOfertapromo2_3 != null && !"".equals(selectedOfertapromo2_3)) {
            ofertapromo2 += "," + selectedOfertapromo2_3;
        }

        if (ofertapromo2.startsWith(",")) {
            ofertapromo2 = ofertapromo2.substring(1);
        }
    }

    @JsonIgnore
    public String getSelectedOfertapromo2_2() {
        return selectedOfertapromo2_2;
    }

    public void setSelectedOfertapromo2_2(String selectedOfertapromo2_2) {
        this.selectedOfertapromo2_2 = selectedOfertapromo2_2;

        ofertapromo2 = "";
        if (selectedOfertapromo2_1 != null && !"".equals(selectedOfertapromo2_1)) {
            ofertapromo2 += selectedOfertapromo2_1;
        } else {
            selectedOfertapromo2_3 = null;
        }

        if (selectedOfertapromo2_2 != null && !"".equals(selectedOfertapromo2_2)) {
            ofertapromo2 += "," + selectedOfertapromo2_2;
        }
        if (selectedOfertapromo2_3 != null && !"".equals(selectedOfertapromo2_3)) {
            ofertapromo2 += "," + selectedOfertapromo2_3;
        }

        if (ofertapromo2.startsWith(",")) {
            ofertapromo2 = ofertapromo2.substring(1);
        }
    }

    @JsonIgnore
    public String getSelectedOfertapromo2_3() {
        return selectedOfertapromo2_3;
    }

    public void setSelectedOfertapromo2_3(String selectedOfertapromo2_3) {
        this.selectedOfertapromo2_3 = selectedOfertapromo2_3;

        ofertapromo2 = "";
        if (selectedOfertapromo2_1 != null && !"".equals(selectedOfertapromo2_1)) {
            ofertapromo2 += selectedOfertapromo2_1;
        }
        if (selectedOfertapromo2_2 != null && !"".equals(selectedOfertapromo2_2)) {
            ofertapromo2 += "," + selectedOfertapromo2_2;
        }
        if (selectedOfertapromo2_3 != null && !"".equals(selectedOfertapromo2_3)) {
            ofertapromo2 += "," + selectedOfertapromo2_3;
        }

        if (ofertapromo2.startsWith(",")) {
            ofertapromo2 = ofertapromo2.substring(1);
        }
    }

    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                IoCartaoCartao[] pp1 = mapper.readValue(history, IoCartaoCartao[].class);
                setHistory(Arrays.asList(mapper.readValue(history, IoCartaoCartao[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (IoCartaoCartao template : pp1) {
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

    @Override
    public boolean validate() throws ValidatorException {
        Set<String> set = new HashSet<>();
        Set<String> repeatedSet = new HashSet<>();

        if (ofertapromo1 != null && !"".equals(ofertapromo1)) {
            String[] oferta1 = ofertapromo1.split(",");
            set.add(oferta1[0]);

            if (oferta1.length > 1 && oferta1[1] != null && !"".equals(oferta1[1]) && set.add(oferta1[1]) == false) {
                repeatedSet.add(oferta1[1]);
            }
            if (oferta1.length > 2 && oferta1[2] != null && !"".equals(oferta1[2]) && set.add(oferta1[2]) == false) {
                repeatedSet.add(oferta1[2]);
            }

            if (ofertapromo2 != null && !"".equals(ofertapromo2)) {
                String[] oferta2 = ofertapromo2.split(",");

                if (oferta2.length > 0 && oferta2[0] != null && !"".equals(oferta2[0]) && set.add(oferta2[0]) == false) {
                    repeatedSet.add(oferta2[0]);
                }
                if (oferta2.length > 1 && oferta2[1] != null && !"".equals(oferta2[1]) && set.add(oferta2[1]) == false) {
                    repeatedSet.add(oferta2[1]);
                }
                if (oferta2.length > 2 && oferta2[2] != null && !"".equals(oferta2[2]) && set.add(oferta2[2]) == false) {
                    repeatedSet.add(oferta2[2]);
                }
            }

            if (!repeatedSet.isEmpty()) {
                FacesMessage msg = new FacesMessage("Incentivos: Valores repetidos: " + repeatedSet.toString());
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        } else {
            FacesMessage msg = new FacesMessage("OfertaPromo1 não preenchida!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        if (recacumuladainicial != null && !"".equals(recacumuladainicial)
                && recacumuladafinal != null && !"".equals(recacumuladafinal)) {

            if (Float.compare(Float.parseFloat(recacumuladainicial), Float.parseFloat(recacumuladafinal)) > 0) {
                FacesMessage msg = new FacesMessage("Cartão > Cartão\nRecarga: Valor inicial maior que valor final");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }

        return true;
    }
}
