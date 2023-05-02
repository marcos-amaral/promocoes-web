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
public class TbTemplateControle extends Template {
    
    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    private String ddd;
    
    private String offercode;
    private String oferta_cadastro;
    private String oferta_cadastro_escolhida;
    private String tx_adesao_boleto;
    private String tb_franquia_oferta;
    private String tipo_pagamento;
    
    public TbTemplateControle(){
        super();
    }
    public TbTemplateControle(TbTemplateControle selectedConfigOfertasPre ){
        this.ddd = selectedConfigOfertasPre.getDdd();
        this.offercode = selectedConfigOfertasPre.getOffercode();
        this.oferta_cadastro = selectedConfigOfertasPre.getOferta_cadastro();
        this.oferta_cadastro_escolhida = selectedConfigOfertasPre.getOferta_cadastro_escolhida();
        this.tx_adesao_boleto = selectedConfigOfertasPre.getTx_adesao_boleto();
        this.tb_franquia_oferta = selectedConfigOfertasPre.getTb_franquia_oferta();  
        this.tipo_pagamento = selectedConfigOfertasPre.getTipo_pagamento();
    }
    @JsonIgnore
    public String getIdDdd() {
        StringBuilder sb = new StringBuilder();
        if (ddd != null && ddd.length() >= 2) {
            return sb.append(" | DDD: ").append(ddd.substring(0, 2)).append("...").toString();
        } else {
            return sb.append(" | DDD: null").toString();
        }
    }
    
    public String getHistory(String field) {
        JsonObject jsonObject = null;

        return jsonObject + "";
    }
    //IsEdit
    @JsonIgnore public boolean isEditOffercode() {return super.getEditMap().getOrDefault("OFFERCODE", false);}
    @JsonIgnore public boolean isEditOferta_cadastro() {return super.getEditMap().getOrDefault("OFERTA_CADASTRO", false);}
    @JsonIgnore public boolean isEditOferta_cadastro_escolhida() {return super.getEditMap().getOrDefault("OFERTA_CADASTRO_ESCOLHIDA", false);}
    @JsonIgnore public boolean isEditTx_adesao_boleto() {return super.getEditMap().getOrDefault("TX_ADESAO_BOLETO", false);}
    @JsonIgnore public boolean isEditTb_franquia_oferta() {return super.getEditMap().getOrDefault("TB_FRANQUIA_OFERTA", false);}
    @JsonIgnore public boolean isEditDdd() {return super.getEditMap().getOrDefault("DDD", false);}
    @JsonIgnore public boolean isEditTipo_pagamento() {return super.getEditMap().getOrDefault("TIPO_PAGAMENTO", false);}
    
    //SetEdit
    public void setEditOffercode(boolean checked) {super.getEditMap().put("OFFERCODE", checked);}
    public void setEditOferta_cadastro(boolean checked) {super.getEditMap().put("OFERTA_CADASTRO", checked);}
    public void setEditOferta_cadastro_escolhida(boolean checked) {super.getEditMap().put("OFERTA_CADASTRO_ESCOLHIDA", checked);}
    public void setEditTx_adesao_boleto(boolean checked) {super.getEditMap().put("TX_ADESAO_BOLETO", checked);}
    public void setEditTb_franquia_oferta(boolean checked) {super.getEditMap().put("TB_FRANQUIA_OFERTA", checked);}
    public void setEditDdd(boolean checked) {super.getEditMap().put("DDD", checked);}
    public void setEditTipo_pagamento(boolean checked) {super.getEditMap().put("TIPO_PAGAMENTO", checked);}
    
    
    
    //gettes AND Setters
    public String getOffercode() {
        return offercode;
    }

    public void setOffercode(String offercode) {
        this.offercode = offercode;
    }

    public String getOferta_cadastro() {
        return oferta_cadastro;
    }

    public void setOferta_cadastro(String oferta_cadastro) {
        this.oferta_cadastro = oferta_cadastro;
    }

    public String getOferta_cadastro_escolhida() {
        return oferta_cadastro_escolhida;
    }

    public void setOferta_cadastro_escolhida(String oferta_cadastro_escolhida) {
        this.oferta_cadastro_escolhida = oferta_cadastro_escolhida;
    }

    public String getTx_adesao_boleto() {
        return tx_adesao_boleto;
    }

    public void setTx_adesao_boleto(String tx_adesao_boleto) {
        if(tx_adesao_boleto!=null)
            tx_adesao_boleto = tx_adesao_boleto.replace(".", ",");
        
        this.tx_adesao_boleto = tx_adesao_boleto;
    }

    public String getTb_franquia_oferta() {
        return tb_franquia_oferta;
    }

    public void setTb_franquia_oferta(String tb_franquia_oferta) {
        if(tb_franquia_oferta!=null)
            tb_franquia_oferta = tb_franquia_oferta.replace(".", ",");
        
        this.tb_franquia_oferta = tb_franquia_oferta;
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

    public String getTipo_pagamento() {
        return tipo_pagamento;
    }

    public void setTipo_pagamento(String tipo_pagamento) {
        this.tipo_pagamento = tipo_pagamento;
    }
    
    
    
    //Final
    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                TbTemplateControle[] pp1 = mapper.readValue(history, TbTemplateControle[].class);
                setHistory(Arrays.asList(mapper.readValue(history, TbTemplateControle[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (TbTemplateControle template : pp1) {
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
