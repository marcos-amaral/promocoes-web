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
public class FrasesControleCad extends Template {

    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    private String ddd;

    private String oferta_cadastro;
    private String oferta_cadastro_conteudo;
    private String oferta_escolhida;
    private String oferta_escolhida_conteudo;
    
    public FrasesControleCad() {
        super();
    }

    public FrasesControleCad(FrasesControleCad selectedConfigOfertasPre) {

        this.ddd = selectedConfigOfertasPre.getDdd();
        this.oferta_cadastro = selectedConfigOfertasPre.getOferta_cadastro();
        this.oferta_cadastro_conteudo = selectedConfigOfertasPre.getOferta_cadastro_conteudo();
        this.oferta_escolhida = selectedConfigOfertasPre.getOferta_escolhida();
        this.oferta_escolhida_conteudo = selectedConfigOfertasPre.getOferta_escolhida_conteudo();
    }

    @JsonIgnore
    public String getIdDdd() {
        StringBuilder sb = new StringBuilder();
        if (ddd != null && ddd.length() >= 2) {
            return sb.append("DDD: ").append(ddd.substring(0, 2)).append("...").toString();
        } else {
            return sb.append("DDD: null").toString();
        }
    }

    public String getHistory(String field) {
        JsonObject jsonObject = null;

        return jsonObject + "";
    }

    @JsonIgnore
    public boolean isEditOferta_cadastro() {
        return super.getEditMap().getOrDefault("OFERTA_CADASTRO", false);
    }

    @JsonIgnore
    public boolean isEditOferta_cadastro_conteudo() {
        return super.getEditMap().getOrDefault("OFERTA_CADASTRO_CONTEUDO", false);
    }

    @JsonIgnore
    public boolean isEditOferta_escolhida() {
        return super.getEditMap().getOrDefault("OFERTA_ESCOLHIDA", false);
    }

    @JsonIgnore
    public boolean isEditOferta_escolhida_conteudo() {
        return super.getEditMap().getOrDefault("OFERTA_ESCOLHIDA_CONTEUDO", false);
    }

    public void setEditOferta_cadastro(boolean checked) {
        super.getEditMap().put("OFERTA_CADASTRO", checked);
    }

    public void setEditOferta_cadastro_conteudo(boolean checked) {
        super.getEditMap().put("OFERTA_CADASTRO_CONTEUDO", checked);
    }

    public void setEditOferta_escolhida(boolean checked) {
        super.getEditMap().put("OFERTA_ESCOLHIDA", checked);
    }

    public void setEditOferta_escolhida_conteudo(boolean checked) {
        super.getEditMap().put("OFERTA_ESCOLHIDA_CONTEUDO", checked);
    }

    //Getters and setters
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

    public String getOferta_cadastro() {
        return oferta_cadastro;
    }

    public void setOferta_cadastro(String oferta_cadastro) {
        this.oferta_cadastro = oferta_cadastro;
    }

    public String getOferta_cadastro_conteudo() {
        return oferta_cadastro_conteudo;
    }

    public void setOferta_cadastro_conteudo(String oferta_cadastro_conteudo) {
        this.oferta_cadastro_conteudo = oferta_cadastro_conteudo;
    }

    public String getOferta_escolhida() {
        return oferta_escolhida;
    }

    public void setOferta_escolhida(String oferta_escolhida) {
        this.oferta_escolhida = oferta_escolhida;
    }

    public String getOferta_escolhida_conteudo() {
        return oferta_escolhida_conteudo;
    }

    public void setOferta_escolhida_conteudo(String oferta_escolhida_conteudo) {
        this.oferta_escolhida_conteudo = oferta_escolhida_conteudo;
    }

    //
    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                FrasesControleCad[] pp1 = mapper.readValue(history, FrasesControleCad[].class);
                setHistory(Arrays.asList(mapper.readValue(history, FrasesControleCad[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (FrasesControleCad template : pp1) {
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
