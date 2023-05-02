/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Date;
import javax.json.JsonObject;

/**
 *
 * @author mmouraam
 */
public class Mensagem extends Template {

    private int id;
    private String idPromo;
    private Prompt prompt = new Prompt();
    private BoCartao boCartao = new BoCartao();
    private PromptsOfertasPre promptsOfertasPre = new PromptsOfertasPre();
    private String tipo_msg;

    public Mensagem() {
        super();
    }

    public Mensagem(Mensagem selectedConfigOfertasPre) {
        this.prompt = selectedConfigOfertasPre.getPrompt();
        this.boCartao = selectedConfigOfertasPre.getBoCartao();
        this.promptsOfertasPre = selectedConfigOfertasPre.getPromptsOfertasPre();
        this.tipo_msg = selectedConfigOfertasPre.getTipo_msg();
    }

    public String getHistory(String field) {
        JsonObject jsonObject = null;

        return jsonObject + "";
    }

    @JsonIgnore
    public boolean isEditPrompt() {
        return super.getEditMap().getOrDefault("PROMPT", false);
    }

    public void setEditPrompt(boolean checked) {
        super.getEditMap().put("PROMPT", checked);
    }

    @JsonIgnore
    public boolean isEditTipo_msg() {
        return super.getEditMap().getOrDefault("TIPO_MSG", false);
    }

    public void setEditTipo_msg(boolean checked) {
        super.getEditMap().put("TIPO_MSG", checked);
    }

    @JsonIgnore
    public boolean isEditBoCartao() {
        return super.getEditMap().getOrDefault("BO_CARTAO", false);
    }

    public void setEditBoCartao(boolean checked) {
        super.getEditMap().put("BO_CARTAO", checked);
    }

    @JsonIgnore
    public boolean isEditFrasesCartao() {
        return super.getEditMap().getOrDefault("FRASES_CARTAO", false);
    }

    public void setEditFrasesCartao(boolean checked) {
        super.getEditMap().put("FRASES_CARTAO", checked);
    }
    
    @JsonIgnore
    public String getDddSub() {
        String ddd = "";
        if(boCartao!=null)
            ddd = boCartao.getDdd();
        else if(promptsOfertasPre!=null)
            ddd = promptsOfertasPre.getDdd();
                    
        if (ddd != null && ddd.length() >= 2) {
            String dddSub = ddd.substring(0, 2) + "...";
            return dddSub;
        }
        return ddd;
    }

    public Prompt getPrompt() {
        return prompt;
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    public BoCartao getBoCartao() {
        return boCartao;
    }

    public void setBoCartao(BoCartao boCartao) {
        this.boCartao = boCartao;
    }

    public PromptsOfertasPre getPromptsOfertasPre() {
        return promptsOfertasPre;
    }

    public void setPromptsOfertasPre(PromptsOfertasPre promptsOfertasPre) {
        this.promptsOfertasPre = promptsOfertasPre;
    }

    public String getTipo_msg() {
        return tipo_msg;
    }

    public void setTipo_msg(String id_ofertaocs) {
        this.tipo_msg = id_ofertaocs;
    }

    public String getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(String idPromo) {
        this.idPromo = idPromo;
    }

    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                Mensagem[] pp1 = mapper.readValue(history, Mensagem[].class);
                setHistory(Arrays.asList(mapper.readValue(history, Mensagem[].class)));

                Date tempDate = new Date(0);
                for (Mensagem template : pp1) {
                    if (template.getModified() != null && template.getModified().after(tempDate)) {
                        tempDate = template.getModified();
                    }

                    template.getEditMap().forEach((k, v) -> {
                        if (Boolean.TRUE.equals(v)) {
                            getEditMap().put(k, v);
                        }
                    });
                }
                setModified(tempDate);

            } catch (Exception ex) {
                throw ex;
            }
        }
    }

}
