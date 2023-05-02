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
public class FraseologiasOfertasPre extends Template {

    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9999999")
    private String id_ofertaocs;
    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    @Id
    @FieldInput(name = InputType.STRING, mask = ",99")
    private String ddd;

    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9?9")
    private String id_beneficio;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9?9")
    private String id_programa;
    
    @FieldInput(name = InputType.FRASE, maxLength = 159)
    private String jacliente_oferta_ussd;
    @FieldInput(name = InputType.FRASE, maxLength = 118)
    private String jacliente_sva_ussd;
    @FieldInput(name = InputType.FRASE, maxLength = 159)
    private String detalhesoferta_ussd;
    @FieldInput(name = InputType.FRASE, maxLength = 159)
    private String regrarollover_ussd;
    @FieldInput(name = InputType.FRASE, maxLength = 159)
    private String regrafallback_ussd;
    @FieldInput(name = InputType.FRASE, maxLength = 159)
    private String ofertamigrados_ussd;
    @FieldInput(name = InputType.FRASE, maxLength = 116)
    private String naocliente_informaoferta_ussd;
    @FieldInput(name = InputType.FRASE, maxLength = 50)
    private String naocliente_incentivooferta_ussd;

    @FieldInput(name = InputType.FRASE)
    private String jacliente_oferta_144;
    @FieldInput(name = InputType.FRASE)
    private String jacliente_sva_144;
    @FieldInput(name = InputType.FRASE)
    private String detalhesoferta_144;
    @FieldInput(name = InputType.FRASE)
    private String regrarollover_144;
    @FieldInput(name = InputType.FRASE)
    private String regrafallback_144;
    @FieldInput(name = InputType.FRASE)
    private String ofertamigrados_144;
    @FieldInput(name = InputType.FRASE)
    private String naocliente_informaoferta_144;
    @FieldInput(name = InputType.FRASE)
    private String naocliente_incentivooferta_144;

    public FraseologiasOfertasPre() {
        super();
    }

    public FraseologiasOfertasPre(FraseologiasOfertasPre selectedConfigOfertasPre) {
        this.id_ofertaocs = selectedConfigOfertasPre.getId_ofertaocs();
        this.ddd = selectedConfigOfertasPre.getDdd();
        
        this.id_beneficio = selectedConfigOfertasPre.getId_beneficio();
        this.id_programa = selectedConfigOfertasPre.getId_programa();

        this.jacliente_oferta_ussd = selectedConfigOfertasPre.getJacliente_oferta_ussd();
        this.jacliente_sva_ussd = selectedConfigOfertasPre.getJacliente_sva_ussd();
        this.detalhesoferta_ussd = selectedConfigOfertasPre.getDetalhesoferta_ussd();
        this.regrarollover_ussd = selectedConfigOfertasPre.getRegrarollover_ussd();
        this.regrafallback_ussd = selectedConfigOfertasPre.getRegrafallback_ussd();
        this.ofertamigrados_ussd = selectedConfigOfertasPre.getOfertamigrados_ussd();
        this.naocliente_informaoferta_ussd = selectedConfigOfertasPre.getNaocliente_informaoferta_ussd();
        this.naocliente_incentivooferta_ussd = selectedConfigOfertasPre.getNaocliente_incentivooferta_ussd();

        this.jacliente_oferta_144 = selectedConfigOfertasPre.getJacliente_oferta_144();
        this.jacliente_sva_144 = selectedConfigOfertasPre.getJacliente_sva_144();
        this.detalhesoferta_144 = selectedConfigOfertasPre.getDetalhesoferta_144();
        this.regrarollover_144 = selectedConfigOfertasPre.getRegrarollover_144();
        this.regrafallback_144 = selectedConfigOfertasPre.getRegrafallback_144();
        this.ofertamigrados_144 = selectedConfigOfertasPre.getOfertamigrados_144();
        this.naocliente_informaoferta_144 = selectedConfigOfertasPre.getNaocliente_informaoferta_144();
        this.naocliente_incentivooferta_144 = selectedConfigOfertasPre.getNaocliente_incentivooferta_144();

        

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
    public boolean isEditId_beneficio() {
        return super.getEditMap().getOrDefault("ID_BENEFICIO", false);
    }
    
    @JsonIgnore
    public boolean isEditId_programa() {
        return super.getEditMap().getOrDefault("ID_PROGRAMA", false);
    }

    @JsonIgnore
    public boolean isEditJacliente_oferta_ussd() {
        return super.getEditMap().getOrDefault("JACLIENTE_OFERTA_USSD", false);
    }

    @JsonIgnore
    public boolean isEditJacliente_sva_ussd() {
        return super.getEditMap().getOrDefault("JACLIENTE_SVA_USSD", false);
    }

    @JsonIgnore
    public boolean isEditDetalhesoferta_ussd() {
        return super.getEditMap().getOrDefault("DETALHESOFERTA_USSD", false);
    }

    @JsonIgnore
    public boolean isEditRegrarollover_ussd() {
        return super.getEditMap().getOrDefault("REGRAROLLOVER_USSD", false);
    }

    @JsonIgnore
    public boolean isEditRegrafallback_ussd() {
        return super.getEditMap().getOrDefault("REGRAFALLBACK_USSD", false);
    }

    @JsonIgnore
    public boolean isEditOfertamigrados_ussd() {
        return super.getEditMap().getOrDefault("OFERTAMIGRADOS_USSD", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_informaoferta_ussd() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_INFORMAOFERTA_USSD", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_incentivooferta_ussd() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_INCENTIVOOFERTA_USSD", false);
    }

    @JsonIgnore
    public boolean isEditIncentivooferta_ussd() {
        return super.getEditMap().getOrDefault("INCENTIVOOFERTA_SC_USSD", false);
    }
    
    public void setEditId_beneficio(boolean bool) {
        super.getEditMap().put("ID_BENEFICIO", bool);
    }
    
    public void setEditId_programa(boolean bool) {
        super.getEditMap().put("ID_PROGRAMA", bool);
    }

    public void setEditJacliente_oferta_ussd(boolean bool) {
        super.getEditMap().put("JACLIENTE_OFERTA_USSD", bool);
    }

    public void setEditJacliente_sva_ussd(boolean bool) {
        super.getEditMap().put("JACLIENTE_SVA_USSD", bool);
    }

    public void setEditDetalhesoferta_ussd(boolean bool) {
        super.getEditMap().put("DETALHESOFERTA_USSD", bool);
    }

    public void setEditRegrarollover_ussd(boolean bool) {
        super.getEditMap().put("REGRAROLLOVER_USSD", bool);
    }

    public void setEditRegrafallback_ussd(boolean bool) {
        super.getEditMap().put("REGRAFALLBACK_USSD", bool);
    }

    public void setEditOfertamigrados_ussd(boolean bool) {
        super.getEditMap().put("OFERTAMIGRADOS_USSD", bool);
    }

    public void setEditNaocliente_informaoferta_ussd(boolean bool) {
        super.getEditMap().put("NAOCLIENTE_INFORMAOFERTA_USSD", bool);
    }

    public void setEditNaocliente_incentivooferta_ussd(boolean bool) {
        super.getEditMap().put("NAOCLIENTE_INCENTIVOOFERTA_USSD", bool);
    }

    public void setEditIncentivooferta_ussd(boolean bool) {
        super.getEditMap().put("INCENTIVOOFERTA_SC_USSD", bool);
    }

    @JsonIgnore
    public boolean isEditJacliente_oferta_144() {
        return super.getEditMap().getOrDefault("JACLIENTE_OFERTA_144", false);
    }

    @JsonIgnore
    public boolean isEditJacliente_sva_144() {
        return super.getEditMap().getOrDefault("JACLIENTE_SVA_144", false);
    }

    @JsonIgnore
    public boolean isEditDetalhesoferta_144() {
        return super.getEditMap().getOrDefault("DETALHESOFERTA_144", false);
    }

    @JsonIgnore
    public boolean isEditRegrarollover_144() {
        return super.getEditMap().getOrDefault("REGRAROLLOVER_144", false);
    }

    @JsonIgnore
    public boolean isEditRegrafallback_144() {
        return super.getEditMap().getOrDefault("REGRAFALLBACK_144", false);
    }

    @JsonIgnore
    public boolean isEditOfertamigrados_144() {
        return super.getEditMap().getOrDefault("OFERTAMIGRADOS_144", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_informaoferta_144() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_INFORMAOFERTA_144", false);
    }

    @JsonIgnore
    public boolean isEditNaocliente_incentivooferta_144() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_INCENTIVOOFERTA_144", false);
    }

    @JsonIgnore
    public boolean isEditIncentivooferta_144() {
        return super.getEditMap().getOrDefault("INCENTIVOOFERTA_SC_144", false);
    }

    public void setEditJacliente_oferta_144(boolean bool) {
        super.getEditMap().put("JACLIENTE_OFERTA_144", bool);
    }

    public void setEditJacliente_sva_144(boolean bool) {
        super.getEditMap().put("JACLIENTE_SVA_144", bool);
    }

    public void setEditDetalhesoferta_144(boolean bool) {
        super.getEditMap().put("DETALHESOFERTA_144", bool);
    }

    public void setEditRegrarollover_144(boolean bool) {
        super.getEditMap().put("REGRAROLLOVER_144", bool);
    }

    public void setEditRegrafallback_144(boolean bool) {
        super.getEditMap().put("REGRAFALLBACK_144", bool);
    }

    public void setEditOfertamigrados_144(boolean bool) {
        super.getEditMap().put("OFERTAMIGRADOS_144", bool);
    }

    public void setEditNaocliente_informaoferta_144(boolean bool) {
        super.getEditMap().put("NAOCLIENTE_INFORMAOFERTA_144", bool);
    }

    public void setEditNaocliente_incentivooferta_144(boolean bool) {
        super.getEditMap().put("NAOCLIENTE_INCENTIVOOFERTA_144", bool);
    }

    public void setEditIncentivooferta_144(boolean bool) {
        super.getEditMap().put("INCENTIVOOFERTA_SC_144", bool);
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

    public String getId_beneficio() {
        return id_beneficio;
    }

    public void setId_beneficio(String id_beneficio) {
        this.id_beneficio = id_beneficio;
    }

    public String getId_programa() {
        return id_programa;
    }

    public void setId_programa(String id_programa) {
        this.id_programa = id_programa;
    }
    
    

    public String getJacliente_oferta_ussd() {
        return jacliente_oferta_ussd;
    }

    public void setJacliente_oferta_ussd(String jacliente_oferta_ussd) {
        this.jacliente_oferta_ussd = jacliente_oferta_ussd;
    }

    public String getJacliente_sva_ussd() {
        return jacliente_sva_ussd;
    }

    public void setJacliente_sva_ussd(String jacliente_sva_ussd) {
        this.jacliente_sva_ussd = jacliente_sva_ussd;
    }

    public String getDetalhesoferta_ussd() {
        return detalhesoferta_ussd;
    }

    public void setDetalhesoferta_ussd(String detalhesoferta_ussd) {
        this.detalhesoferta_ussd = detalhesoferta_ussd;
    }

    public String getRegrarollover_ussd() {
        return regrarollover_ussd;
    }

    public void setRegrarollover_ussd(String regrarollover_ussd) {
        this.regrarollover_ussd = regrarollover_ussd;
    }

    public String getRegrafallback_ussd() {
        return regrafallback_ussd;
    }

    public void setRegrafallback_ussd(String regrafallback_ussd) {
        this.regrafallback_ussd = regrafallback_ussd;
    }

    public String getOfertamigrados_ussd() {
        return ofertamigrados_ussd;
    }

    public void setOfertamigrados_ussd(String ofertamigrados_ussd) {
        this.ofertamigrados_ussd = ofertamigrados_ussd;
    }

    public String getNaocliente_informaoferta_ussd() {
        return naocliente_informaoferta_ussd;
    }

    public void setNaocliente_informaoferta_ussd(String naocliente_informaoferta_ussd) {
        this.naocliente_informaoferta_ussd = naocliente_informaoferta_ussd;
    }

    public String getNaocliente_incentivooferta_ussd() {
        return naocliente_incentivooferta_ussd;
    }

    public void setNaocliente_incentivooferta_ussd(String naocliente_incentivooferta_ussd) {
        this.naocliente_incentivooferta_ussd = naocliente_incentivooferta_ussd;
    }

    public String getJacliente_oferta_144() {
        return jacliente_oferta_144;
    }

    public void setJacliente_oferta_144(String jacliente_oferta_144) {
        this.jacliente_oferta_144 = jacliente_oferta_144;
    }

    public String getJacliente_sva_144() {
        return jacliente_sva_144;
    }

    public void setJacliente_sva_144(String jacliente_sva_144) {
        this.jacliente_sva_144 = jacliente_sva_144;
    }

    public String getDetalhesoferta_144() {
        return detalhesoferta_144;
    }

    public void setDetalhesoferta_144(String detalhesoferta_144) {
        this.detalhesoferta_144 = detalhesoferta_144;
    }

    public String getRegrarollover_144() {
        return regrarollover_144;
    }

    public void setRegrarollover_144(String regrarollover_144) {
        this.regrarollover_144 = regrarollover_144;
    }

    public String getRegrafallback_144() {
        return regrafallback_144;
    }

    public void setRegrafallback_144(String regrafallback_144) {
        this.regrafallback_144 = regrafallback_144;
    }

    public String getOfertamigrados_144() {
        return ofertamigrados_144;
    }

    public void setOfertamigrados_144(String ofertamigrados_144) {
        this.ofertamigrados_144 = ofertamigrados_144;
    }

    public String getNaocliente_informaoferta_144() {
        return naocliente_informaoferta_144;
    }

    public void setNaocliente_informaoferta_144(String naocliente_informaoferta_144) {
        this.naocliente_informaoferta_144 = naocliente_informaoferta_144;
    }

    public String getNaocliente_incentivooferta_144() {
        return naocliente_incentivooferta_144;
    }

    public void setNaocliente_incentivooferta_144(String naocliente_incentivooferta_144) {
        this.naocliente_incentivooferta_144 = naocliente_incentivooferta_144;
    }

    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                FraseologiasOfertasPre[] pp1 = mapper.readValue(history, FraseologiasOfertasPre[].class);
                setHistory(Arrays.asList(mapper.readValue(history, FraseologiasOfertasPre[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (FraseologiasOfertasPre template : pp1) {
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
