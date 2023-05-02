/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oi.promocoesweb.dbms.entity.User;
import com.oi.promocoesweb.template.utils.DateUtils;
import com.oi.promocoesweb.validation.annotation.FieldValidation;
import com.oi.promocoesweb.validation.enums.ValidationEnum;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Jpereirc
 */
public class OfertasPortabilidadeOcs extends Template {
    
    private static final Logger logger = LogManager.getLogger();

    private int id;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    private String idbeneficio;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    private String idprograma;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    private String idofertaocs;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    private String id_campanhasiebel;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    private String id_ofertasiebel;
    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    private String ddd;
    private Date data_inic;
    private Date data_fim;
    private String informapromo;
    private String incentivo_portabilidade;
    private String naocliente_sva;
    private String tb_tipo_bonus;

    
    

    public OfertasPortabilidadeOcs() {
        super();
    }

    public OfertasPortabilidadeOcs(OfertasPortabilidadeOcs selectedConfigOfertasPre) {
        this.idbeneficio = selectedConfigOfertasPre.getIdbeneficio();
        this.idprograma = selectedConfigOfertasPre.getIdprograma();
        this.idofertaocs = selectedConfigOfertasPre.getIdofertaocs();
        this.id_campanhasiebel = selectedConfigOfertasPre.getId_campanhasiebel();
        this.id_ofertasiebel = selectedConfigOfertasPre.getId_ofertasiebel();
        this.ddd = selectedConfigOfertasPre.getDdd();
        this.data_inic = selectedConfigOfertasPre.getData_inic();
        this.data_fim = selectedConfigOfertasPre.getData_fim();
        this.informapromo = selectedConfigOfertasPre.getInformapromo();
        this.incentivo_portabilidade = selectedConfigOfertasPre.getIncentivo_portabilidade();
        this.naocliente_sva = selectedConfigOfertasPre.getNaocliente_sva();
        this.tb_tipo_bonus = selectedConfigOfertasPre.getTb_tipo_bonus();

        
  
        
    }

    public String getHistory(String field) {
        JsonObject jsonObject = null;

        return jsonObject + "";
    }
    
    @JsonIgnore
    public String getIdDdd() {
        StringBuilder sb = new StringBuilder();
        if (ddd != null && ddd.length() >= 2) {
            return sb.append("IDBENEFICIO: ").append(idbeneficio).append("| IDPROGRAMA: ").append(idprograma).append(" | DDD: ").append(ddd.substring(0, 2)).append("...").toString();
        } else {
            return sb.append("IDBENEFICIO: ").append(idbeneficio).append("| IDPROGRAMA: ").append(idprograma).append(" | DDD: null").toString();
        }
    }


    @JsonIgnore
    public boolean isEditIdBeneficio() {
        return super.getEditMap().getOrDefault("IDBENEFICIO", false);
    }

    public void setEditIdBeneficio(boolean checked) {
        super.getEditMap().put("IDBENEFICIO", checked);
    }

    @JsonIgnore
    public boolean isEditIdPrograma() {
        return super.getEditMap().getOrDefault("IDPROGRAMA", false);
    }

    public void setEditIdPrograma(boolean checked) {
        super.getEditMap().put("IDPROGRAMA", checked);
    }
    @JsonIgnore
    public boolean isEditIdOfertaOcs() {
        return super.getEditMap().getOrDefault("IDOFERTAOCS", false);
    }

    public void setEditIdOfertaOcs(boolean checked) {
        super.getEditMap().put("IDOFERTAOCS", checked);
    }
    @JsonIgnore
    public boolean isEditId_campanhasiebel() {
        return super.getEditMap().getOrDefault("ID_CAMPANHASIEBEL", false);
    }

    public void setEditId_campanhasiebel(boolean checked) {
        super.getEditMap().put("ID_CAMPANHASIEBEL", checked);
    }
    @JsonIgnore
    public boolean isEditId_ofertasiebel() {
        return super.getEditMap().getOrDefault("ID_OFERTASIEBEL", false);
    }

    public void setEditId_ofertasiebel(boolean checked) {
        super.getEditMap().put("ID_OFERTASIEBEL", checked);
    }
    @JsonIgnore
    public boolean isEditInformapromo() {
        return super.getEditMap().getOrDefault("INFORMAPROMO", false);
    }

    public void setEditInformapromo(boolean checked) {
        super.getEditMap().put("INFORMAPROMO", checked);
    }
    @JsonIgnore
    public boolean isEditIncentivo_portabilidade() {
        return super.getEditMap().getOrDefault("INCENTIVO_PORTABILIDADE", false);
    }

    public void setEditIncentivo_portabilidade(boolean checked) {
        super.getEditMap().put("INCENTIVO_PORTABILIDADE", checked);
    }
    @JsonIgnore
    public boolean isEditNaocliente_sva() {
        return super.getEditMap().getOrDefault("NAOCLIENTE_SVA", false);
    }

    public void setEditNaocliente_sva(boolean checked) {
        super.getEditMap().put("NAOCLIENTE_SVA", checked);
    }
    
    @JsonIgnore
    public boolean isEditTb_tipo_bonus() {
        return super.getEditMap().getOrDefault("TB_TIPO_BONUS", false);
    }

    public void setEditTb_tipo_bonus(boolean checked) {
        super.getEditMap().put("TB_TIPO_BONUS", checked);
    }

    @JsonIgnore
    public boolean isEditData_inic() {
        return super.getEditMap().getOrDefault("DATA_INIC", false);
    }

    public void setEditData_inic(boolean checked) {
        super.getEditMap().put("DATA_INIC", checked);
    }

    @JsonIgnore
    public boolean isEditData_fim() {
        return super.getEditMap().getOrDefault("DATA_FIM", false);
    }

    public void setEditData_fim(boolean checked) {
        super.getEditMap().put("DATA_FIM", checked);
    }

    @JsonIgnore
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getIdbeneficio() {
        return idbeneficio;
    }

    public void setIdbeneficio(String idbeneficio) {
        this.idbeneficio = idbeneficio;
    }

    public String getIdprograma() {
        return idprograma;
    }

    public void setIdprograma(String idprograma) {
        this.idprograma = idprograma;
    }

    public String getInformapromo() {
        return informapromo;
    }

    public void setInformapromo(String informapromo) {
        this.informapromo = informapromo;
    }

    public String getIncentivo_portabilidade() {
        return incentivo_portabilidade;
    }

    public void setIncentivo_portabilidade(String incentivo_portabilidade) {
        this.incentivo_portabilidade = incentivo_portabilidade;
    }

    public String getNaocliente_sva() {
        return naocliente_sva;
    }

    public void setNaocliente_sva(String naocliente_sva) {
        this.naocliente_sva = naocliente_sva;
    }

        public Date getData_inic() {
        return data_inic;
    }

    @JsonGetter("data_inic")
    public String getData_inicStr() {
        if (data_inic != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            return sdf.format(data_inic);
        }

        return "";
    }
    
    @JsonSetter("data_inic")
    public void setData_inicAsString(String data_inic) {
        try {
            if (data_inic != null && !"".equals(data_inic)) {
                this.data_inic = DateUtils.parse(data_inic);
            }
        } catch (Exception e) {
            logger.warn("invalid data_inic: {}", data_inic);
        }
    }

    public void setData_inic(Date data_inic) {
        this.data_inic = data_inic;
    }

    public Date getData_fim() {
        return data_fim;
    }

    @JsonGetter("data_fim")
    public String getData_fimStr() {
        if (data_fim != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            return sdf.format(data_fim);
        }

        return "";
    }
    
    @JsonSetter("data_fim")
    public void setData_fimAsString(String data_fim) {
        try {
            if (data_fim != null && !"".equals(data_fim)) {
                this.data_fim = DateUtils.parse(data_fim);
            }
        } catch (Exception e) {
            logger.warn("invalid data_fim: {}", data_fim);
        }
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }
    
    

    public String getTb_tipo_bonus() {
        return tb_tipo_bonus;
    }

    public void setTb_tipo_bonus(String tb_tipo_bonus) {
        this.tb_tipo_bonus = tb_tipo_bonus;
    }

    public String getIdofertaocs() {
        return idofertaocs;
    }

    public void setIdofertaocs(String idofertaocs) {
        this.idofertaocs = idofertaocs;
    }

    public String getId_campanhasiebel() {
        return id_campanhasiebel;
    }

    public void setId_campanhasiebel(String id_campanhasiebel) {
        this.id_campanhasiebel = id_campanhasiebel;
    }

    public String getId_ofertasiebel() {
        return id_ofertasiebel;
    }

    public void setId_ofertasiebel(String id_ofertasiebel) {
        this.id_ofertasiebel = id_ofertasiebel;
    }
    
    
    
    
    
  

    
    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                OfertasPortabilidadeOcs[] pp1 = mapper.readValue(history, OfertasPortabilidadeOcs[].class);
                setHistory(Arrays.asList(mapper.readValue(history, OfertasPortabilidadeOcs[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (OfertasPortabilidadeOcs template : pp1) {
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
