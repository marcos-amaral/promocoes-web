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
 * @author jpereirc
 */
public class IoControleControleS2S extends Template {
    
    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    @Id
    private String oferta;
   
    @FieldInput(name = InputType.DROPDOWN)
    private String tb_incentivo1;
    @FieldInput(name = InputType.DROPDOWN)
    private String tb_incentivo2;
    @FieldInput(name = InputType.DROPDOWN)
    private String tb_incentivo3;
    @FieldInput(name = InputType.DROPDOWN)
    private String tb_incentivo4;
    
    public IoControleControleS2S(){
        super();
    }
    public IoControleControleS2S(IoControleControleS2S selectedConfigOfertasPre){
        this.oferta = selectedConfigOfertasPre.getOferta();
        this.tb_incentivo1 = selectedConfigOfertasPre.getTb_incentivo1();
        this.tb_incentivo2 = selectedConfigOfertasPre.getTb_incentivo2();
        this.tb_incentivo3 = selectedConfigOfertasPre.getTb_incentivo3();
        this.tb_incentivo4 = selectedConfigOfertasPre.getTb_incentivo4();
    }
    
    @JsonIgnore
    public String getIdDdd() {
        return getIdOferta();
    }
    
    @JsonIgnore
    public String getIdOferta() {
        StringBuilder sb = new StringBuilder();
        if (oferta != null && oferta.length() >= 2) {
            return sb.append(" | Oferta: ").append(oferta.substring(0, 7)).append("...").toString();
        } else {
            return sb.append(" | Oferta: null").toString();
        }
    }
     //getHistory
    public String getHistory(String field) {
        JsonObject jsonObject = null;

        return jsonObject + "";
    }
    
    
    
    //IsEdit
    @JsonIgnore public boolean isEditOferta() {return super.getEditMap().getOrDefault("OFERTA", false);}
    @JsonIgnore public boolean isEditTb_incentivo1() {return super.getEditMap().getOrDefault("TB_INCENTIVO1", false);}
    @JsonIgnore public boolean isEditTb_incentivo2() {return super.getEditMap().getOrDefault("TB_INCENTIVO2", false);}
    @JsonIgnore public boolean isEditTb_incentivo3() {return super.getEditMap().getOrDefault("TB_INCENTIVO3", false);}
    @JsonIgnore public boolean isEditTb_incentivo4() {return super.getEditMap().getOrDefault("TB_INCENTIVO4", false);}
    
    //SetEdits
    
    
    public void setEditOferta(boolean checked) {super.getEditMap().put("OFERTA", checked);}
    public void setEditTb_incentivo1(boolean checked) {super.getEditMap().put("TB_INCENTIVO1", checked);}
    public void setEditTb_incentivo2(boolean checked) {super.getEditMap().put("TB_INCENTIVO2", checked);}
    public void setEditTb_incentivo3(boolean checked) {super.getEditMap().put("TB_INCENTIVO3", checked);}
    public void setEditTb_incentivo4(boolean checked) {super.getEditMap().put("TB_INCENTIVO4", checked);}
    
    
    
    
    //Getters and Setters
    
    public String getOferta() {
        return oferta;
    }
    @JsonIgnore
    public String getOfertaSub() {
        if (oferta != null && oferta.length() > 59) {
            String ofertaSub = oferta.substring(0, 59);
            ofertaSub = ofertaSub.substring(0, ofertaSub.lastIndexOf(",")) + "...";
            return ofertaSub;
        }
        return oferta;
    }
    
    public void setOferta(String oferta) {
        this.oferta = oferta;
    }


    public String getTb_incentivo1() {
        return tb_incentivo1;
    }

    public void setTb_incentivo1(String tb_incentivo1) {
        this.tb_incentivo1 = tb_incentivo1;
    }

    public String getTb_incentivo2() {
        return tb_incentivo2;
    }

    public void setTb_incentivo2(String tb_incentivo2) {
        this.tb_incentivo2 = tb_incentivo2;
    }

    public String getTb_incentivo3() {
        return tb_incentivo3;
    }

    public void setTb_incentivo3(String tb_incentivo3) {
        this.tb_incentivo3 = tb_incentivo3;
    }

    public String getTb_incentivo4() {
        return tb_incentivo4;
    }

    public void setTb_incentivo4(String tb_incentivo4) {
        this.tb_incentivo4 = tb_incentivo4;
    }

    //Final
    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                IoControleControleS2S[] pp1 = mapper.readValue(history, IoControleControleS2S[].class);
                setHistory(Arrays.asList(mapper.readValue(history, IoControleControleS2S[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (IoControleControleS2S template : pp1) {
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
        oferta = oferta!=null?oferta.replace(" " , "").replace(";", ","):oferta;

    }
    
    @Override
    public boolean validate() throws ValidatorException {
        Set<String> set = new HashSet<>();
        Set<String> repeatedSet = new HashSet<>();

        set.add(tb_incentivo1);
        if (tb_incentivo2 != null && !"".equals(tb_incentivo2) && set.add(tb_incentivo2) == false) {
            repeatedSet.add(tb_incentivo2);
        }
        if (tb_incentivo3 != null && !"".equals(tb_incentivo3) && set.add(tb_incentivo3) == false) {
            repeatedSet.add(tb_incentivo3);
        }
        if (tb_incentivo4 != null && !"".equals(tb_incentivo4) && set.add(tb_incentivo4) == false) {
            repeatedSet.add(tb_incentivo4);
        }
        
        if (!repeatedSet.isEmpty()) {
            FacesMessage msg = new FacesMessage("Incentivos: Valores repetidos: " + repeatedSet.toString());
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        
        if (tb_incentivo2 == null || "".equals(tb_incentivo2)) {
            if ((tb_incentivo3 != null && !"".equals(tb_incentivo3))
                    || (tb_incentivo4 != null && !"".equals(tb_incentivo4))) {
                FacesMessage msg = new FacesMessage("Controle > Controle\nIncentivos: Incentivo2 null, Incentivo3 ou 4 preenchidos");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }

        if (tb_incentivo3 == null || "".equals(tb_incentivo3)) {
            if ((tb_incentivo4 != null && !"".equals(tb_incentivo4))) {
                FacesMessage msg = new FacesMessage("Controle > Controle\nIncentivos: Incentivo3 null, Incentivo4 preenchido");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }

        return true;
    }
}
