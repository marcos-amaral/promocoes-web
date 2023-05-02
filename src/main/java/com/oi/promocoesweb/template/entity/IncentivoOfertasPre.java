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
public class IncentivoOfertasPre extends Template {

    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    @Id
    @FieldInput(name = InputType.STRING, mask = ",99")
    private String ddd;
    @FieldInput(name = InputType.MONEY)
    private String recacumuladainicial;
    @FieldInput(name = InputType.MONEY)
    private String recacumuladafinal;
    private String ofertapromo1_id_ofertaocs;
    private String ofertapromo2_id_ofertaocs;

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

    public IncentivoOfertasPre() {
        super();
    }

    public IncentivoOfertasPre(IncentivoOfertasPre selectedConfigOfertasPre) {
        this.ddd = selectedConfigOfertasPre.getDdd();
        this.ofertapromo1_id_ofertaocs = selectedConfigOfertasPre.getOfertapromo1_id_ofertaocs();
        this.ofertapromo2_id_ofertaocs = selectedConfigOfertasPre.getOfertapromo2_id_ofertaocs();
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
    public boolean isEditId_ofertaocs1() {
        return super.getEditMap().getOrDefault("OFERTAPROMO1_ID_OFERTAOCS", false);
    }

    public void setEditId_ofertaocs1(boolean checked) {
        super.getEditMap().put("OFERTAPROMO1_ID_OFERTAOCS", checked);
    }

    @JsonIgnore
    public boolean isEditId_ofertaocs2() {
        return super.getEditMap().getOrDefault("OFERTAPROMO2_ID_OFERTAOCS", false);
    }

    public void setEditId_ofertaocs2(boolean checked) {
        super.getEditMap().put("OFERTAPROMO2_ID_OFERTAOCS", checked);
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

    public String getOfertapromo1_id_ofertaocs() {
        return ofertapromo1_id_ofertaocs;
    }

    public void setOfertapromo1_id_ofertaocs(String id_ofertaocs) {
        this.ofertapromo1_id_ofertaocs = id_ofertaocs;
        if (ofertapromo1_id_ofertaocs != null && !"".equals(ofertapromo1_id_ofertaocs)) {
            String[] split = ofertapromo1_id_ofertaocs.split(",");

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

    public String getOfertapromo2_id_ofertaocs() {
        return ofertapromo2_id_ofertaocs;
    }

    public void setOfertapromo2_id_ofertaocs(String id_ofertaocs) {
        this.ofertapromo2_id_ofertaocs = id_ofertaocs;
        if (ofertapromo2_id_ofertaocs != null && !"".equals(ofertapromo2_id_ofertaocs)) {
            String[] split = ofertapromo2_id_ofertaocs.split(",");

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

        ofertapromo1_id_ofertaocs = "";
        if (selectedOfertapromo1_1 != null && !"".equals(selectedOfertapromo1_1)) {
            ofertapromo1_id_ofertaocs += selectedOfertapromo1_1;
        } else {
            selectedOfertapromo1_2 = null;
            selectedOfertapromo1_3 = null;
        }

        if (selectedOfertapromo1_2 != null && !"".equals(selectedOfertapromo1_2)) {
            ofertapromo1_id_ofertaocs += "," + selectedOfertapromo1_2;
        }
        if (selectedOfertapromo1_3 != null && !"".equals(selectedOfertapromo1_3)) {
            ofertapromo1_id_ofertaocs += "," + selectedOfertapromo1_3;
        }

        if (ofertapromo1_id_ofertaocs.startsWith(",")) {
            ofertapromo1_id_ofertaocs = ofertapromo1_id_ofertaocs.substring(1);
        }
    }

    @JsonIgnore
    public String getSelectedOfertapromo1_2() {
        return selectedOfertapromo1_2;
    }

    public void setSelectedOfertapromo1_2(String selectedOfertapromo1_2) {
        this.selectedOfertapromo1_2 = selectedOfertapromo1_2;

        ofertapromo1_id_ofertaocs = "";
        if (selectedOfertapromo1_1 != null && !"".equals(selectedOfertapromo1_1)) {
            ofertapromo1_id_ofertaocs += selectedOfertapromo1_1;
        } else {
            selectedOfertapromo1_3 = null;
        }

        if (selectedOfertapromo1_2 != null && !"".equals(selectedOfertapromo1_2)) {
            ofertapromo1_id_ofertaocs += "," + selectedOfertapromo1_2;
        }
        if (selectedOfertapromo1_3 != null && !"".equals(selectedOfertapromo1_3)) {
            ofertapromo1_id_ofertaocs += "," + selectedOfertapromo1_3;
        }

        if (ofertapromo1_id_ofertaocs.startsWith(",")) {
            ofertapromo1_id_ofertaocs = ofertapromo1_id_ofertaocs.substring(1);
        }
    }

    @JsonIgnore
    public String getSelectedOfertapromo1_3() {
        return selectedOfertapromo1_3;
    }

    public void setSelectedOfertapromo1_3(String selectedOfertapromo1_3) {
        this.selectedOfertapromo1_3 = selectedOfertapromo1_3;

        ofertapromo1_id_ofertaocs = "";
        if (selectedOfertapromo1_1 != null && !"".equals(selectedOfertapromo1_1)) {
            ofertapromo1_id_ofertaocs += selectedOfertapromo1_1;
        }
        if (selectedOfertapromo1_2 != null && !"".equals(selectedOfertapromo1_2)) {
            ofertapromo1_id_ofertaocs += "," + selectedOfertapromo1_2;
        }
        if (selectedOfertapromo1_3 != null && !"".equals(selectedOfertapromo1_3)) {
            ofertapromo1_id_ofertaocs += "," + selectedOfertapromo1_3;
        }

        if (ofertapromo1_id_ofertaocs.startsWith(",")) {
            ofertapromo1_id_ofertaocs = ofertapromo1_id_ofertaocs.substring(1);
        }
    }

    @JsonIgnore
    public String getSelectedOfertapromo2_1() {
        return selectedOfertapromo2_1;
    }

    public void setSelectedOfertapromo2_1(String selectedOfertapromo2_1) {
        this.selectedOfertapromo2_1 = selectedOfertapromo2_1;

        ofertapromo2_id_ofertaocs = "";
        if (selectedOfertapromo2_1 != null && !"".equals(selectedOfertapromo2_1)) {
            ofertapromo2_id_ofertaocs += selectedOfertapromo2_1;
        } else {
            selectedOfertapromo2_2 = null;
            selectedOfertapromo2_3 = null;
        }
        if (selectedOfertapromo2_2 != null && !"".equals(selectedOfertapromo2_2)) {
            ofertapromo2_id_ofertaocs += "," + selectedOfertapromo2_2;
        }
        if (selectedOfertapromo2_3 != null && !"".equals(selectedOfertapromo2_3)) {
            ofertapromo2_id_ofertaocs += "," + selectedOfertapromo2_3;
        }

        if (ofertapromo2_id_ofertaocs.startsWith(",")) {
            ofertapromo2_id_ofertaocs = ofertapromo2_id_ofertaocs.substring(1);
        }
    }

    @JsonIgnore
    public String getSelectedOfertapromo2_2() {
        return selectedOfertapromo2_2;
    }

    public void setSelectedOfertapromo2_2(String selectedOfertapromo2_2) {
        this.selectedOfertapromo2_2 = selectedOfertapromo2_2;

        ofertapromo2_id_ofertaocs = "";
        if (selectedOfertapromo2_1 != null && !"".equals(selectedOfertapromo2_1)) {
            ofertapromo2_id_ofertaocs += selectedOfertapromo2_1;
        } else {
            selectedOfertapromo2_3 = null;
        }

        if (selectedOfertapromo2_2 != null && !"".equals(selectedOfertapromo2_2)) {
            ofertapromo2_id_ofertaocs += "," + selectedOfertapromo2_2;
        }
        if (selectedOfertapromo2_3 != null && !"".equals(selectedOfertapromo2_3)) {
            ofertapromo2_id_ofertaocs += "," + selectedOfertapromo2_3;
        }

        if (ofertapromo2_id_ofertaocs.startsWith(",")) {
            ofertapromo2_id_ofertaocs = ofertapromo2_id_ofertaocs.substring(1);
        }
    }

    @JsonIgnore
    public String getSelectedOfertapromo2_3() {
        return selectedOfertapromo2_3;
    }

    public void setSelectedOfertapromo2_3(String selectedOfertapromo2_3) {
        this.selectedOfertapromo2_3 = selectedOfertapromo2_3;

        ofertapromo2_id_ofertaocs = "";
        if (selectedOfertapromo2_1 != null && !"".equals(selectedOfertapromo2_1)) {
            ofertapromo2_id_ofertaocs += selectedOfertapromo2_1;
        }
        if (selectedOfertapromo2_2 != null && !"".equals(selectedOfertapromo2_2)) {
            ofertapromo2_id_ofertaocs += "," + selectedOfertapromo2_2;
        }
        if (selectedOfertapromo2_3 != null && !"".equals(selectedOfertapromo2_3)) {
            ofertapromo2_id_ofertaocs += "," + selectedOfertapromo2_3;
        }

        if (ofertapromo2_id_ofertaocs.startsWith(",")) {
            ofertapromo2_id_ofertaocs = ofertapromo2_id_ofertaocs.substring(1);
        }
    }

    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                IncentivoOfertasPre[] pp1 = mapper.readValue(history, IncentivoOfertasPre[].class);
                setHistory(Arrays.asList(mapper.readValue(history, IncentivoOfertasPre[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (IncentivoOfertasPre template : pp1) {
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

        if (ofertapromo1_id_ofertaocs != null && !"".equals(ofertapromo1_id_ofertaocs)) {
            String[] oferta1 = ofertapromo1_id_ofertaocs.split(",");
            set.add(oferta1[0]);

            if (oferta1.length > 1 && oferta1[1] != null && !"".equals(oferta1[1]) && set.add(oferta1[1]) == false) {
                repeatedSet.add(oferta1[1]);
            }
            if (oferta1.length > 2 && oferta1[2] != null && !"".equals(oferta1[2]) && set.add(oferta1[2]) == false) {
                repeatedSet.add(oferta1[2]);
            }

            if (ofertapromo2_id_ofertaocs != null && !"".equals(ofertapromo2_id_ofertaocs)) {
                String[] oferta2 = ofertapromo2_id_ofertaocs.split(",");

                if (oferta2.length > 1 && oferta2[0] != null && !"".equals(oferta2[0]) && set.add(oferta2[0]) == false) {
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
            FacesMessage msg = new FacesMessage("OfertaPromo1 não preechida!");
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
