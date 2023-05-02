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
 * @author mmouraam
 */
public class CadTemplatePreOcs extends Template {

    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    private String ddd;
    private String id_campanha_siebel1;
    private String id_oferta_siebel1;
    private String id_campanha_siebel2;
    private String id_oferta_siebel2;
    private String id_campanha_siebel3;
    private String id_oferta_siebel3;
    private String id_campanha_default;
    private String id_oferta_default;
    private String msg_ocs_sva;
    private String tipo_bonus;

    public CadTemplatePreOcs() {
        super();
    }

    public CadTemplatePreOcs(CadTemplatePreOcs selectedConfigOfertasPre) {
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

    public String getHistory(String field) {
        JsonObject jsonObject = null;

        return jsonObject + "";
    }

    @JsonIgnore
    public boolean isEditId_campanha_siebel1() {
        return super.getEditMap().getOrDefault("ID_CAMPANHA_SIEBEL1", false);
    }

    public void setEditId_campanha_siebel1(boolean checked) {
        super.getEditMap().put("ID_CAMPANHA_SIEBEL1", checked);
    }

    @JsonIgnore
    public boolean isEditId_oferta_siebel1() {
        return super.getEditMap().getOrDefault("ID_OFERTA_SIEBEL1", false);
    }

    public void setEditId_oferta_siebel1(boolean checked) {
        super.getEditMap().put("ID_OFERTA_SIEBEL1", checked);
    }

    @JsonIgnore
    public boolean isEditId_campanha_siebel2() {
        return super.getEditMap().getOrDefault("ID_CAMPANHA_SIEBEL2", false);
    }

    public void setEditId_campanha_siebel2(boolean checked) {
        super.getEditMap().put("ID_CAMPANHA_SIEBEL2", checked);
    }

    @JsonIgnore
    public boolean isEditId_oferta_siebel2() {
        return super.getEditMap().getOrDefault("ID_OFERTA_SIEBEL2", false);
    }

    public void setEditId_oferta_siebel2(boolean checked) {
        super.getEditMap().put("ID_OFERTA_SIEBEL2", checked);
    }

    @JsonIgnore
    public boolean isEditId_campanha_siebel3() {
        return super.getEditMap().getOrDefault("ID_CAMPANHA_SIEBEL3", false);
    }

    public void setEditId_campanha_siebel3(boolean checked) {
        super.getEditMap().put("ID_CAMPANHA_SIEBEL3", checked);
    }

    @JsonIgnore
    public boolean isEditId_oferta_siebel3() {
        return super.getEditMap().getOrDefault("ID_OFERTA_SIEBEL3", false);
    }

    public void setEditId_oferta_siebel3(boolean checked) {
        super.getEditMap().put("ID_OFERTA_SIEBEL3", checked);
    }

    @JsonIgnore
    public boolean isEditId_campanha_default() {
        return super.getEditMap().getOrDefault("ID_CAMPANHA_DEFAULT", false);
    }

    public void setEditId_campanha_default(boolean checked) {
        super.getEditMap().put("ID_CAMPANHA_DEFAULT", checked);
    }

    @JsonIgnore
    public boolean isEditId_oferta_default() {
        return super.getEditMap().getOrDefault("ID_OFERTA_DEFAULT", false);
    }

    public void setEditId_oferta_default(boolean checked) {
        super.getEditMap().put("ID_OFERTA_DEFAULT", checked);
    }

    @JsonIgnore
    public boolean isEditMsg_ocs_sva() {
        return super.getEditMap().getOrDefault("MSG_OCS_SVA", false);
    }

    public void setEditMsg_ocs_sva(boolean checked) {
        super.getEditMap().put("MSG_OCS_SVA", checked);
    }

    @JsonIgnore
    public boolean isEditTipo_bonus() {
        return super.getEditMap().getOrDefault("TIPO_BONUS", false);
    }

    public void setEditTipo_bonus(boolean checked) {
        super.getEditMap().put("TIPO_BONUS", checked);
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

    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                CadTemplatePreOcs[] pp1 = mapper.readValue(history, CadTemplatePreOcs[].class);
                setHistory(Arrays.asList(mapper.readValue(history, CadTemplatePreOcs[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (CadTemplatePreOcs template : pp1) {
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
