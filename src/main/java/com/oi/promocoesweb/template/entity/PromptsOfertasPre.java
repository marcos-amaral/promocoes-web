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
 * @author mmouraam
 */
public class PromptsOfertasPre extends Template {

    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9?9.9?9")
    private String id_bfpg;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9999999")
    private String id_ofertaocs;
    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    @Id
    @FieldInput(name = InputType.STRING, mask = ",99")
    private String ddd;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV)
    private String jacliente_oferta;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV)
    private String jacliente_sva;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV)
    private String detalhesoferta;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV)
    private String regrarollover;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV)
    private String regrafallback;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV)
    private String ofertamigrados;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV)
    private String naocliente_informaoferta;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV)
    private String naocliente_incentivooferta;
    @FieldValidation(name = ValidationEnum.NOME_WAV)
    @FieldInput(name = InputType.WAV)
    private String incentivooferta;

    public PromptsOfertasPre() {
        super();
    }

    public PromptsOfertasPre(PromptsOfertasPre selectedConfigOfertasPre) {
        this.id_bfpg = selectedConfigOfertasPre.getId_bfpg();
        this.id_ofertaocs = selectedConfigOfertasPre.getId_ofertaocs();
        this.ddd = selectedConfigOfertasPre.getDdd();
        this.jacliente_oferta = selectedConfigOfertasPre.getJacliente_oferta();
        this.jacliente_sva = selectedConfigOfertasPre.getJacliente_sva();
        this.detalhesoferta = selectedConfigOfertasPre.getDetalhesoferta();
        this.regrarollover = selectedConfigOfertasPre.getRegrarollover();
        this.regrafallback = selectedConfigOfertasPre.getRegrafallback();
        this.ofertamigrados = selectedConfigOfertasPre.getOfertamigrados();
        this.naocliente_informaoferta = selectedConfigOfertasPre.getNaocliente_informaoferta();
        this.naocliente_incentivooferta = selectedConfigOfertasPre.getNaocliente_incentivooferta();
        this.incentivooferta = selectedConfigOfertasPre.getIncentivooferta();

    }

    @JsonIgnore
    public String getIdDdd() {
        StringBuilder sb = new StringBuilder();
        if (ddd != null && ddd.length() >= 2) {
            return sb.append("ID_OFERTAOCS: ").append(id_ofertaocs).append(" | DDD: ").append(ddd.substring(0, 2)).append("...").toString();
        } else {
            return sb.append("ID_OFERTAOCS: ").append(id_ofertaocs).append(" | DDD: null").toString();
        }
    }

    public String getHistory(String field) {
        JsonObject jsonObject = null;

        return jsonObject + "";
    }

    @JsonIgnore
    public boolean isEditJacliente_oferta() {
        return super.getEditMap().getOrDefault("JACLIENTE_OFERTA", false);
    }

    @JsonIgnore
    public boolean isEditJacliente_sva() {
        return super.getEditMap().getOrDefault("JACLIENTE_SVA", false);
    }

    @JsonIgnore
    public boolean isEditDetalhesoferta() {
        return super.getEditMap().getOrDefault("DETALHESOFERTA", false);
    }

    @JsonIgnore
    public boolean isEditRegrarollover() {
        return super.getEditMap().getOrDefault("REGRAROLLOVER", false);
    }

    @JsonIgnore
    public boolean isEditRegrafallback() {
        return super.getEditMap().getOrDefault("REGRAFALLBACK", false);
    }

    @JsonIgnore
    public boolean isEditOfertamigrados() {
        return super.getEditMap().getOrDefault("OFERTAMIGRADOS", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_informaoferta() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_INFORMAOFERTA", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_incentivooferta() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_INCENTIVOOFERTA", false);
    }

    @JsonIgnore
    public boolean isEditIncentivooferta() {
        return super.getEditMap().getOrDefault("INCENTIVOOFERTA", false);
    }

    public void setEditJacliente_oferta(boolean bool) {
        super.getEditMap().put("JACLIENTE_OFERTA", bool);
    }

    public void setEditJacliente_sva(boolean bool) {
        super.getEditMap().put("JACLIENTE_SVA", bool);
    }

    public void setEditDetalhesoferta(boolean bool) {
        super.getEditMap().put("DETALHESOFERTA", bool);
    }

    public void setEditRegrarollover(boolean bool) {
        super.getEditMap().put("REGRAROLLOVER", bool);
    }

    public void setEditRegrafallback(boolean bool) {
        super.getEditMap().put("REGRAFALLBACK", bool);
    }

    public void setEditOfertamigrados(boolean bool) {
        super.getEditMap().put("OFERTAMIGRADOS", bool);
    }

    public void setEditNaocliente_informaoferta(boolean bool) {
        super.getEditMap().put("NAOCLIENTE_INFORMAOFERTA", bool);
    }

    public void setEditNaocliente_incentivooferta(boolean bool) {
        super.getEditMap().put("NAOCLIENTE_INCENTIVOOFERTA", bool);
    }

    public void setEditIncentivooferta(boolean bool) {
        super.getEditMap().put("INCENTIVOOFERTA", bool);
    }

    public String getId_bfpg() {
        return id_bfpg;
    }

    public void setId_bfpg(String id_bfpg) {
        this.id_bfpg = id_bfpg;
    }

    public String getId_ofertaocs() {
        return id_ofertaocs;
    }

    public void setId_ofertaocs(String id_ofertaocs) {
        this.id_ofertaocs = id_ofertaocs;
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

    public String getJacliente_oferta() {
        return jacliente_oferta;
    }

    public void setJacliente_oferta(String jacliente_oferta) {
        this.jacliente_oferta = jacliente_oferta;
    }

    public String getJacliente_sva() {
        return jacliente_sva;
    }

    public void setJacliente_sva(String jacliente_sva) {
        this.jacliente_sva = jacliente_sva;
    }

    public String getDetalhesoferta() {
        return detalhesoferta;
    }

    public void setDetalhesoferta(String detalhesoferta) {
        this.detalhesoferta = detalhesoferta;
    }

    public String getRegrarollover() {
        return regrarollover;
    }

    public void setRegrarollover(String regrarollover) {
        this.regrarollover = regrarollover;
    }

    public String getRegrafallback() {
        return regrafallback;
    }

    public void setRegrafallback(String regrafallback) {
        this.regrafallback = regrafallback;
    }

    public String getOfertamigrados() {
        return ofertamigrados;
    }

    public void setOfertamigrados(String ofertamigrados) {
        this.ofertamigrados = ofertamigrados;
    }

    public String getNaocliente_informaoferta() {
        return naocliente_informaoferta;
    }

    public void setNaocliente_informaoferta(String naocliente_informaoferta) {
        this.naocliente_informaoferta = naocliente_informaoferta;
    }

    public String getNaocliente_incentivooferta() {
        return naocliente_incentivooferta;
    }

    public void setNaocliente_incentivooferta(String naocliente_incentivooferta) {
        this.naocliente_incentivooferta = naocliente_incentivooferta;
    }

    public String getIncentivooferta() {
        return incentivooferta;
    }

    public void setIncentivooferta(String incentivooferta) {
        this.incentivooferta = incentivooferta;
    }

    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                PromptsOfertasPre[] pp1 = mapper.readValue(history, PromptsOfertasPre[].class);
                setHistory(Arrays.asList(mapper.readValue(history, PromptsOfertasPre[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (PromptsOfertasPre template : pp1) {
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
        if(jacliente_oferta!=null && !"".equals(jacliente_oferta.trim())){
            if(!jacliente_oferta.toUpperCase().endsWith(".WAV"))
                jacliente_oferta = jacliente_oferta+".wav";
            
            jacliente_oferta = jacliente_oferta.replace(" ", "");
        }
        if(jacliente_sva!=null && !"".equals(jacliente_sva.trim())){
            if(!jacliente_sva.toUpperCase().endsWith(".WAV"))
                jacliente_sva = jacliente_sva+".wav";
            
            jacliente_sva = jacliente_sva.replace(" ", "");
        }
        if(detalhesoferta!=null && !"".equals(detalhesoferta.trim())){
            if(!detalhesoferta.toUpperCase().endsWith(".WAV"))
                detalhesoferta = detalhesoferta+".wav";
            
            detalhesoferta = detalhesoferta.replace(" ", "");
        }
        if(regrarollover!=null && !"".equals(regrarollover.trim())){
            if(!regrarollover.toUpperCase().endsWith(".WAV"))
                regrarollover = regrarollover+".wav";
            
            regrarollover = regrarollover.replace(" ", "");
        }
        if(regrafallback!=null && !"".equals(regrafallback.trim())){
            if(!regrafallback.toUpperCase().endsWith(".WAV"))
                regrafallback = regrafallback+".wav";
            
            regrafallback = regrafallback.replace(" ", "");
        }
        if(ofertamigrados!=null && !"".equals(ofertamigrados.trim())){
            if(!ofertamigrados.toUpperCase().endsWith(".WAV"))
                ofertamigrados = ofertamigrados+".wav";
            
            ofertamigrados = ofertamigrados.replace(" ", "");
        }
        if(naocliente_informaoferta!=null && !"".equals(naocliente_informaoferta.trim())){
            if(!naocliente_informaoferta.toUpperCase().endsWith(".WAV"))
                naocliente_informaoferta = naocliente_informaoferta+".wav";
            
            naocliente_informaoferta = naocliente_informaoferta.replace(" ", "");
        }
        if(naocliente_incentivooferta!=null && !"".equals(naocliente_incentivooferta.trim())){
            if(!naocliente_incentivooferta.toUpperCase().endsWith(".WAV"))
                naocliente_incentivooferta = naocliente_incentivooferta+".wav";
            
            naocliente_incentivooferta = naocliente_incentivooferta.replace(" ", "");
        }
        if(incentivooferta!=null && !"".equals(incentivooferta.trim())){
            if(!incentivooferta.toUpperCase().endsWith(".WAV"))
                incentivooferta = incentivooferta+".wav";
            
            incentivooferta = incentivooferta.replace(" ", "");
        }

    }
    
    

}
