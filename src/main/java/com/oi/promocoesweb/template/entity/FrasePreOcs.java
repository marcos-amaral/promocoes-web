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
public class FrasePreOcs extends Template{
    
   @FieldValidation(name = ValidationEnum.VALOR_UNICO)
   private String idcampanha;
   @FieldValidation(name = ValidationEnum.VALOR_UNICO)
   private String idoferta;
   @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
   private String ddd;
   
   private String msg_oferta_1;
   private String msg_oferta_2;
   private String msg_oferta_3;
   private String msg_oferta_default;
   private String msg_ocs_sva;
    
    public FrasePreOcs(){
        super();
    }
    public FrasePreOcs(FrasePreOcs selectedConfigOfertasPre){
        this.idcampanha = selectedConfigOfertasPre.getIdcampanha();
        this.idoferta = selectedConfigOfertasPre.getIdoferta();
        this.ddd = selectedConfigOfertasPre.getDdd();
        this.msg_oferta_1 = selectedConfigOfertasPre.getMsg_oferta_1();
        this.msg_oferta_2 = selectedConfigOfertasPre.getMsg_oferta_2();
        this.msg_oferta_3 = selectedConfigOfertasPre.getMsg_oferta_3();
        this.msg_oferta_default = selectedConfigOfertasPre.getMsg_oferta_default();
        this.msg_ocs_sva = selectedConfigOfertasPre.getMsg_ocs_sva();
    }
    
    @JsonIgnore
    public String getIdDdd() {
        StringBuilder sb = new StringBuilder();
        if (ddd != null && ddd.length() >= 2) {
            return sb.append("ID_CAMPANHA: ").append(idcampanha).append(" | ID_OFERTA: ").append(idoferta).append(" | DDD: ").append(ddd.substring(0, 2)).append("...").toString();
        } else {
            return sb.append("ID_CAMPANHA: ").append(idcampanha).append(" | ID_OFERTA: ").append(idoferta).append(" | DDD: null").toString();
        }
    }
    
    public String getHistory(String field) {
        JsonObject jsonObject = null;

        return jsonObject + "";
    }
    //
    @JsonIgnore
    public boolean isEditIdcampanha(){
        return super.getEditMap().getOrDefault("IDCAMPANHA", false);
    }
    
    public void setEditIdcampanha(boolean checked){
        super.getEditMap().put("IDCAMPANHA", checked);
    }
    
    @JsonIgnore 
    public boolean isEditIdoferta(){
        return super.getEditMap().getOrDefault("IDOFERTA", false);
    }
    
    public void setEditIdoferta(boolean checked){
        super.getEditMap().put("IDOFERTA", checked);
    }
    
    @JsonIgnore 
    public boolean isEditDdd(){
        return super.getEditMap().getOrDefault("DDD", false);
    }
    
    public void setEditDdd(boolean checked){
        super.getEditMap().put("DDD", checked);
    }
    
    @JsonIgnore
    public boolean isEditMsg_oferta_1(){
        return super.getEditMap().getOrDefault("MSG_OFERTA_1", false);
    }
    
    public void setEditMsg_oferta_1(boolean checked){
        super.getEditMap().put("MSG_OFERTA_1", checked);
    }
    
    @JsonIgnore
    public boolean isEditMsg_oferta_2(){
        return super.getEditMap().getOrDefault("MSG_OFERTA_2", false);
    }
    
    public void setEditMsg_oferta_2(boolean checked){
        super.getEditMap().put("MSG_OFERTA_2", checked);
    }
    
    @JsonIgnore
    public boolean isEditMsg_oferta_3(){
        return super.getEditMap().getOrDefault("MSG_OFERTA_3", false);
    }
    
    public void setEditMsg_oferta_3(boolean checked){
        super.getEditMap().put("MSG_OFERTA_3", checked);
    }
    
    @JsonIgnore
    public boolean isEditMsg_oferta_default(){
        return super.getEditMap().getOrDefault("MSG_OFERTA_DEFAULT", false);
    }
    
    public void setEditMsg_oferta_default(boolean checked){
        super.getEditMap().put("MSG_OFERTA_DEFAULT", checked);
    }
    
    @JsonIgnore
    public boolean isEditMsg_ocs_sva(){
        return super.getEditMap().getOrDefault("MSG_OCS_SVA", false);
    }
    
    public void setEditMsg_ocs_sva(boolean checked){
        super.getEditMap().put("MSG_OCS_SVA", checked);
    }

    
    
    //GettsAndSetters

    public String getIdcampanha() {
        return idcampanha;
    }

    public void setIdcampanha(String idcampanha) {
        this.idcampanha = idcampanha;
    }

    public String getIdoferta() {
        return idoferta;
    }

    public void setIdoferta(String idoferta) {
        this.idoferta = idoferta;
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
    
    public String getMsg_oferta_1() {
        return msg_oferta_1;
    }

    public void setMsg_oferta_1(String msg_oferta_1) {
        this.msg_oferta_1 = msg_oferta_1;
    }
    
    public String getMsg_oferta_2() {
        return msg_oferta_2;
    }

    public void setMsg_oferta_2(String msg_oferta_2) {
        this.msg_oferta_2 = msg_oferta_2;
    }

    public String getMsg_oferta_3() {
        return msg_oferta_3;
    }

    public void setMsg_oferta_3(String msg_oferta_3) {
        this.msg_oferta_3 = msg_oferta_3;
    }

    public String getMsg_oferta_default() {
        return msg_oferta_default;
    }

    public void setMsg_oferta_default(String msg_oferta_default) {
        this.msg_oferta_default = msg_oferta_default;
    }

    public String getMsg_ocs_sva() {
        return msg_ocs_sva;
    }

    public void setMsg_ocs_sva(String msg_ocs_sva) {
        this.msg_ocs_sva = msg_ocs_sva;
    }
    
    
    
    
    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                FrasePreOcs[] pp1 = mapper.readValue(history, FrasePreOcs[].class);
                setHistory(Arrays.asList(mapper.readValue(history, FrasePreOcs[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (FrasePreOcs template : pp1) {
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
