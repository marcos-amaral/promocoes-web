/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import javax.json.JsonObject;

/**
 *
 * @author mmouraam
 */
public class Prompt extends Template {

    private String idPromo;
    private String wav;
    private String conteudo;

    public Prompt() {
        super();
    }

    public Prompt(Prompt selectedConfigOfertasPre) {
        this.wav = selectedConfigOfertasPre.getWav();
        this.conteudo = selectedConfigOfertasPre.getConteudo();
    }

    public String getHistory(String field) {
        JsonObject jsonObject = null;

        return jsonObject + "";
    }

    @JsonIgnore
    public boolean isEditWav() {
        return super.getEditMap().getOrDefault("WAV", false);
    }

    public void setEditWav(boolean checked) {
        super.getEditMap().put("WAV", checked);
    }

    @JsonIgnore
    public boolean isEditConteudo() {
        return super.getEditMap().getOrDefault("CONTEUDO", false);
    }

    public void setEditConteudo(boolean checked) {
        super.getEditMap().put("CONTEUDO", checked);
    }

    @JsonIgnore
    public String getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(String id) {
        this.idPromo = id;
    }

    public String getWav() {
        return wav;
    }

    public void setWav(String wav) {
        this.wav = wav;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                Prompt[] pp1 = mapper.readValue(history, Prompt[].class);
                setHistory(Arrays.asList(mapper.readValue(history, Prompt[].class)));

                Date tempDate = new Date(0);
                for (Prompt template : pp1) {
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

            } catch (IOException ex) {
                throw ex;
            }
        }
    }

}
