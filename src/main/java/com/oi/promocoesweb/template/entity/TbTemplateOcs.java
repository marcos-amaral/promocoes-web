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
public class TbTemplateOcs extends Template {
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    private String id_campanha_siebel1;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    private String id_oferta_siebel1;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    private String id_campanha_siebel2;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    private String id_oferta_siebel2;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    private String id_campanha_siebel3;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    private String id_oferta_siebel3;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    private String id_campanha_default;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    private String id_oferta_default;
    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    private String ddd;
    
    private String msg_ocs_sva;
    private String tipo_bonus;

    public TbTemplateOcs(){
        super();
    }
    public TbTemplateOcs(TbTemplateOcs selectedConfigOfertasPre){
        
        this.ddd = selectedConfigOfertasPre.getDdd();
        this.id_campanha_siebel1 = selectedConfigOfertasPre.getId_campanha_siebel1();
        this.id_oferta_siebel1 = selectedConfigOfertasPre.getId_oferta_siebel1();
        this.id_campanha_siebel2 = selectedConfigOfertasPre.getId_campanha_siebel2();
        this.id_oferta_siebel2 = selectedConfigOfertasPre.getId_oferta_siebel2();
        this.id_campanha_siebel3 = selectedConfigOfertasPre.getId_campanha_siebel3();
        this.id_oferta_siebel3 = selectedConfigOfertasPre.getId_oferta_siebel3();
        this.id_campanha_default = selectedConfigOfertasPre.getId_campanha_default();
        this.id_oferta_default = selectedConfigOfertasPre.getId_oferta_default();
        this.msg_ocs_sva = selectedConfigOfertasPre.getMsg_ocs_sva();
        this.tipo_bonus = selectedConfigOfertasPre.getTipo_bonus();
        
    }
    @JsonIgnore
    public String getIdDdd() {
        StringBuilder sb = new StringBuilder();
        if (ddd != null && ddd.length() >= 2) {
            return sb.append("ID_BFPG: ").append(" | DDD: ").append(ddd.substring(0, 2)).append("...").toString();
        } else {
            return sb.append("ID_BFPG: ").append(" | DDD: null").toString();
        }
    }
    
     public String getHistory(String field) {
        JsonObject jsonObject = null;

        return jsonObject + "";
    }
    //IsEdit
    @JsonIgnore public boolean isEditDdd() {return super.getEditMap().getOrDefault("DDD", false);}
    @JsonIgnore public boolean isEditId_campanha_siebel1() {return super.getEditMap().getOrDefault("ID_CAMPANHA_SIEBEL1", false);}
    @JsonIgnore public boolean isEditId_oferta_siebel1() {return super.getEditMap().getOrDefault("ID_OFERTA_SIEBEL1", false);}
    @JsonIgnore public boolean isEditId_campanha_siebel2() {return super.getEditMap().getOrDefault("ID_CAMPANHA_SIEBEL2", false);}
    @JsonIgnore public boolean isEditId_oferta_siebel2() {return super.getEditMap().getOrDefault("ID_OFERTA_SIEBEL2", false);}
    @JsonIgnore public boolean isEditId_campanha_siebel3() {return super.getEditMap().getOrDefault("ID_CAMPANHA_SIEBEL3", false);}
    @JsonIgnore public boolean isEditId_oferta_siebel3() {return super.getEditMap().getOrDefault("ID_OFERTA_SIEBEL3", false);}
    @JsonIgnore public boolean isEditId_campanha_default() {return super.getEditMap().getOrDefault("ID_CAMPANHA_DEFAULT", false);}
    @JsonIgnore public boolean isEditId_oferta_default() {return super.getEditMap().getOrDefault("ID_OFERTA_DEFAULT", false);}
    @JsonIgnore public boolean isEditMsg_ocs_sva() {return super.getEditMap().getOrDefault("MSG_OCS_SVA", false);}
    @JsonIgnore public boolean isEditTipo_bonus() {return super.getEditMap().getOrDefault("TIPO_BONUS", false);}
     
    //SetEdit
    public void setEditDdd(boolean checked) {super.getEditMap().put("DDD", checked);}
    public void setEditId_campanha_siebel1(boolean checked) {super.getEditMap().put("ID_CAMPANHA_SIEBEL1", checked);}
    public void setEditId_oferta_siebel1(boolean checked) {super.getEditMap().put("ID_OFERTA_SIEBEL1", checked);}
    public void setEditId_campanha_siebel2(boolean checked) {super.getEditMap().put("ID_CAMPANHA_SIEBEL2", checked);}
    public void setEditId_oferta_siebel2(boolean checked) {super.getEditMap().put("ID_OFERTA_SIEBEL2", checked);}
    public void setEditId_campanha_siebel3(boolean checked) {super.getEditMap().put("ID_CAMPANHA_SIEBEL3", checked);}
    public void setEditId_oferta_siebel3(boolean checked) {super.getEditMap().put("ID_OFERTA_SIEBEL3", checked);}
    public void setEditId_campanha_default(boolean checked) {super.getEditMap().put("ID_CAMPANHA_DEFAULT", checked);}
    public void setEditId_oferta_default(boolean checked) {super.getEditMap().put("ID_OFERTA_DEFAULT", checked);}
    public void setEditMsg_ocs_sva(boolean checked) {super.getEditMap().put("MSG_OCS_SVA", checked);}
    public void setEditTipo_bonus(boolean checked) {super.getEditMap().put("TIPO_BONUS", checked);}
    
    //gettes and Setters
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

    public String getId_campanha_siebel1() {
        return id_campanha_siebel1;
    }

    public void setId_campanha_siebel1(String id_campanha_siebel1) {
        this.id_campanha_siebel1 = id_campanha_siebel1;
    }

    public String getId_oferta_siebel1() {
        return id_oferta_siebel1;
    }

    public void setId_oferta_siebel1(String id_oferta_siebel1) {
        this.id_oferta_siebel1 = id_oferta_siebel1;
    }

    public String getId_campanha_siebel2() {
        return id_campanha_siebel2;
    }

    public void setId_campanha_siebel2(String id_campanha_siebel2) {
        this.id_campanha_siebel2 = id_campanha_siebel2;
    }

    public String getId_oferta_siebel2() {
        return id_oferta_siebel2;
    }

    public void setId_oferta_siebel2(String id_oferta_siebel2) {
        this.id_oferta_siebel2 = id_oferta_siebel2;
    }

    public String getId_campanha_siebel3() {
        return id_campanha_siebel3;
    }

    public void setId_campanha_siebel3(String id_campanha_siebel3) {
        this.id_campanha_siebel3 = id_campanha_siebel3;
    }

    public String getId_oferta_siebel3() {
        return id_oferta_siebel3;
    }

    public void setId_oferta_siebel3(String id_oferta_siebel3) {
        this.id_oferta_siebel3 = id_oferta_siebel3;
    }

    public String getId_campanha_default() {
        return id_campanha_default;
    }

    public void setId_campanha_default(String id_campanha_default) {
        this.id_campanha_default = id_campanha_default;
    }

    public String getId_oferta_default() {
        return id_oferta_default;
    }

    public void setId_oferta_default(String id_oferta_default) {
        this.id_oferta_default = id_oferta_default;
    }

    public String getMsg_ocs_sva() {
        return msg_ocs_sva;
    }

    public void setMsg_ocs_sva(String msg_ocs_sva) {
        this.msg_ocs_sva = msg_ocs_sva;
    }

    public String getTipo_bonus() {
        return tipo_bonus;
    }

    public void setTipo_bonus(String tipo_bonus) {
        this.tipo_bonus = tipo_bonus;
    }
    
    
    //Final
    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                TbTemplateOcs[] pp1 = mapper.readValue(history, TbTemplateOcs[].class);
                setHistory(Arrays.asList(mapper.readValue(history, TbTemplateOcs[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (TbTemplateOcs template : pp1) {
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
