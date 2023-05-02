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
 * @author jpereirc
 */
public class OiCMenu extends Template{
    
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9?9")
    private String tb_id_beneficio;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9?9")
    private String tb_id_programa;
    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    @Id
    @FieldInput(name = InputType.STRING, mask = ",99")
    private String tb_ddd;
    
    private String tb_regiao;
    private String upr1_oicpp_mensageminicial;
    private String upr1_oicpp_regtorpedo;
    private String upr1_oicpp_regligacao;
    private String upr1_oicpp_comousar;
    private String upr1_oicinfp_mensageminicial;
    private String upr1_oicinfp_regtorpedo;
    private String upr1_oicinfop_regligacao;
    private String upr1_oicinfp_comousar;
    private String upr1_oicinfp_validadepromo;
    private String upr1_oiccpoplanos_promooicontrole;
    private String upr1_oicinfp_regdados;
    private String upr1_oicpp_regdados;
    private String jacliente_sva;

    
       
    
    
    //Construtores
    public OiCMenu(){
        
        
    }
    public OiCMenu(OiCMenu selectedConfigOfertasPre){
        this.tb_id_beneficio = selectedConfigOfertasPre.getTb_id_beneficio();
        this.tb_id_programa = selectedConfigOfertasPre.getTb_id_programa();
        this.tb_ddd = selectedConfigOfertasPre.getTb_ddd();
        this.tb_regiao = selectedConfigOfertasPre.getTb_regiao();
        this.upr1_oicpp_mensageminicial = selectedConfigOfertasPre.getUpr1_oicpp_mensageminicial();
        this.upr1_oicpp_regtorpedo = selectedConfigOfertasPre.getUpr1_oicpp_regtorpedo();
        this.upr1_oicpp_regligacao = selectedConfigOfertasPre.getUpr1_oicpp_regligacao();
        this.upr1_oicpp_comousar = selectedConfigOfertasPre.getUpr1_oicpp_comousar();
        this.upr1_oicinfp_mensageminicial = selectedConfigOfertasPre.getUpr1_oicinfp_mensageminicial();
        this.upr1_oicinfp_regtorpedo = selectedConfigOfertasPre.getUpr1_oicinfp_regtorpedo();
        this.upr1_oicinfop_regligacao = selectedConfigOfertasPre.getUpr1_oicinfop_regligacao();
        this.upr1_oicinfp_comousar = selectedConfigOfertasPre.getUpr1_oicinfp_comousar();
        this.upr1_oicinfp_validadepromo = selectedConfigOfertasPre.getUpr1_oicinfp_validadepromo();
        this.upr1_oiccpoplanos_promooicontrole = selectedConfigOfertasPre.getUpr1_oiccpoplanos_promooicontrole();
        this.upr1_oicinfp_regdados = selectedConfigOfertasPre.getUpr1_oicinfp_regdados();
        this.upr1_oicpp_regdados = selectedConfigOfertasPre.getUpr1_oicpp_regdados();
        this.jacliente_sva = selectedConfigOfertasPre.getJacliente_sva();
    }
    //getHistory
    public String getHistory(String field) {
        JsonObject jsonObject = null;

        return jsonObject + "";
    }
    @JsonIgnore
    public String getIdDdd() {
        StringBuilder sb = new StringBuilder();
        if (tb_ddd != null && tb_ddd.length() >= 2) {
            return sb.append("ID_BENEFICIO: ").append(tb_id_beneficio).append("| ID_PROGRAMA: ").append(tb_id_programa).append(" | DDD: ").append(tb_ddd.substring(0, 2)).append("...").toString();
        } else {
            return sb.append("ID_BENEFICIO: ").append(tb_id_beneficio).append("| ID_PROGRAMA: ").append(tb_id_programa).append(" | DDD: null").toString();
        }
    }
    //IsEdit
    @JsonIgnore public boolean isEditTb_id_beneficio() {return super.getEditMap().getOrDefault("TB_ID_BENEFICIO", false);}
    @JsonIgnore public boolean isEditTb_id_programa() {return super.getEditMap().getOrDefault("TB_ID_PROGRAMA", false);}
    @JsonIgnore public boolean isEditTb_ddd() {return super.getEditMap().getOrDefault("TB_DDD", false);}
    @JsonIgnore public boolean isEditTb_regiao() {return super.getEditMap().getOrDefault("TB_REGIAO", false);}
    @JsonIgnore public boolean isEditUpr1_oicpp_mensageminicial() {return super.getEditMap().getOrDefault("UPR1_OICPP_MENSAGEMINICIAL", false);}
    @JsonIgnore public boolean isEditUpr1_oicpp_regtorpedo() {return super.getEditMap().getOrDefault("UPR1_OICPP_REGTORPEDO", false);}
    @JsonIgnore public boolean isEditUpr1_oicpp_regligacao() {return super.getEditMap().getOrDefault("UPR1_OICPP_REGLIGACAO", false);}
    @JsonIgnore public boolean isEditUpr1_oicpp_comousar() {return super.getEditMap().getOrDefault("UPR1_OICPP_COMOUSAR", false);}
    @JsonIgnore public boolean isEditUpr1_oicinfp_mensageminicial() {return super.getEditMap().getOrDefault("UPR1_OICINFP_MENSAGEMINICIAL", false);}
    @JsonIgnore public boolean isEditUpr1_oicinfp_regtorpedo() {return super.getEditMap().getOrDefault("UPR1_OICINFP_REGTORPEDO", false);}
    @JsonIgnore public boolean isEditUpr1_oicinfop_regligacao() {return super.getEditMap().getOrDefault("UPR1_OICINFOP_REGLIGACAO", false);}
    @JsonIgnore public boolean isEditUpr1_oicinfp_comousar() {return super.getEditMap().getOrDefault("UPR1_OICINFP_COMOUSAR", false);}
    @JsonIgnore public boolean isEditUpr1_oicinfp_validadepromo() {return super.getEditMap().getOrDefault("UPR1_OICINFP_VALIDADEPROMO", false);}
    @JsonIgnore public boolean isEditUpr1_oiccpoplanos_promooicontrole() {return super.getEditMap().getOrDefault("UPR1_OICCPOPLANOS_PROMOOICONTROLE", false);}
    @JsonIgnore public boolean isEditUpr1_oicinfp_regdados() {return super.getEditMap().getOrDefault("UPR1_OICINFP_REGDADOS", false);}
    @JsonIgnore public boolean isEditUpr1_oicpp_regdados() {return super.getEditMap().getOrDefault("UPR1_OICPP_REGDADOS", false);}
    @JsonIgnore public boolean isEditJacliente_sva() {return super.getEditMap().getOrDefault("JACLIENTE_SVA", false);}
    
    //SetEdit
    public void setEditTb_id_beneficio(boolean checked) {super.getEditMap().put("TB_ID_BENEFICIO", checked);}
    public void setEditTb_id_programa(boolean checked) {super.getEditMap().put("TB_ID_PROGRAMA", checked);}
    public void setEditTb_ddd(boolean checked) {super.getEditMap().put("TB_DDD", checked);}
    public void setEditTb_regiao(boolean checked) {super.getEditMap().put("TB_REGIAO", checked);}
    public void setEditUpr1_oicpp_mensageminicial(boolean checked) {super.getEditMap().put("UPR1_OICPP_MENSAGEMINICIAL", checked);}
    public void setEditUpr1_oicpp_regtorpedo(boolean checked) {super.getEditMap().put("UPR1_OICPP_REGTORPEDO", checked);}
    public void setEditUpr1_oicpp_regligacao(boolean checked) {super.getEditMap().put("UPR1_OICPP_REGLIGACAO", checked);}
    public void setEditUpr1_oicpp_comousar(boolean checked) {super.getEditMap().put("UPR1_OICPP_COMOUSAR", checked);}
    public void setEditUpr1_oicinfp_mensageminicial(boolean checked) {super.getEditMap().put("UPR1_OICINFP_MENSAGEMINICIAL", checked);}
    public void setEditUpr1_oicinfp_regtorpedo(boolean checked) {super.getEditMap().put("UPR1_OICINFP_REGTORPEDO", checked);}
    public void setEditUpr1_oicinfop_regligacao(boolean checked) {super.getEditMap().put("UPR1_OICINFOP_REGLIGACAO", checked);}
    public void setEditUpr1_oicinfp_comousar(boolean checked) {super.getEditMap().put("UPR1_OICINFP_COMOUSAR", checked);}
    public void setEditUpr1_oicinfp_validadepromo(boolean checked) {super.getEditMap().put("UPR1_OICINFP_VALIDADEPROMO", checked);}
    public void setEditUpr1_oiccpoplanos_promooicontrole(boolean checked) {super.getEditMap().put("UPR1_OICCPOPLANOS_PROMOOICONTROLE", checked);}
    public void setEditUpr1_oicinfp_regdados(boolean checked) {super.getEditMap().put("UPR1_OICINFP_REGDADOS", checked);}
    public void setEditUpr1_oicpp_regdados(boolean checked) {super.getEditMap().put("UPR1_OICPP_REGDADOS", checked);}
    public void setEditJacliente_sva(boolean checked) {super.getEditMap().put("JACLIENTE_SVA", checked);}
    
    
    //Getters and Setters

    public String getTb_id_beneficio() {
        return tb_id_beneficio;
    }

    public void setTb_id_beneficio(String tb_id_beneficio) {
        this.tb_id_beneficio = tb_id_beneficio;
    }

    public String getTb_id_programa() {
        return tb_id_programa;
    }

    public void setTb_id_programa(String tb_id_programa) {
        this.tb_id_programa = tb_id_programa;
    }

    public String getTb_ddd() {
        return tb_ddd;
    }
    @JsonIgnore
    public String getDddSub() {
        if (tb_ddd != null && tb_ddd.length() > 59) {
            String dddSub = tb_ddd.substring(0, 59);
            dddSub = dddSub.substring(0, dddSub.lastIndexOf(",")) + "...";
            return dddSub;
        }
        return tb_ddd;
    }

    public void setTb_ddd(String tb_ddd) {
        this.tb_ddd = tb_ddd;
    }

    public String getTb_regiao() {
        return tb_regiao;
    }

    public void setTb_regiao(String tb_regiao) {
        this.tb_regiao = tb_regiao;
    }

    public String getUpr1_oicpp_mensageminicial() {
        return upr1_oicpp_mensageminicial;
    }

    public void setUpr1_oicpp_mensageminicial(String upr1_oicpp_mensageminicial) {
        this.upr1_oicpp_mensageminicial = upr1_oicpp_mensageminicial;
    }

    public String getUpr1_oicpp_regtorpedo() {
        return upr1_oicpp_regtorpedo;
    }

    public void setUpr1_oicpp_regtorpedo(String upr1_oicpp_regtorpedo) {
        this.upr1_oicpp_regtorpedo = upr1_oicpp_regtorpedo;
    }

    public String getUpr1_oicpp_regligacao() {
        return upr1_oicpp_regligacao;
    }

    public void setUpr1_oicpp_regligacao(String upr1_oicpp_regligacao) {
        this.upr1_oicpp_regligacao = upr1_oicpp_regligacao;
    }

    public String getUpr1_oicpp_comousar() {
        return upr1_oicpp_comousar;
    }

    public void setUpr1_oicpp_comousar(String upr1_oicpp_comousar) {
        this.upr1_oicpp_comousar = upr1_oicpp_comousar;
    }

    public String getUpr1_oicinfp_mensageminicial() {
        return upr1_oicinfp_mensageminicial;
    }

    public void setUpr1_oicinfp_mensageminicial(String upr1_oicinfp_mensageminicial) {
        this.upr1_oicinfp_mensageminicial = upr1_oicinfp_mensageminicial;
    }

    public String getUpr1_oicinfp_regtorpedo() {
        return upr1_oicinfp_regtorpedo;
    }

    public void setUpr1_oicinfp_regtorpedo(String upr1_oicinfp_regtorpedo) {
        this.upr1_oicinfp_regtorpedo = upr1_oicinfp_regtorpedo;
    }

    public String getUpr1_oicinfop_regligacao() {
        return upr1_oicinfop_regligacao;
    }

    public void setUpr1_oicinfop_regligacao(String upr1_oicinfop_regligacao) {
        this.upr1_oicinfop_regligacao = upr1_oicinfop_regligacao;
    }

    public String getUpr1_oicinfp_comousar() {
        return upr1_oicinfp_comousar;
    }

    public void setUpr1_oicinfp_comousar(String upr1_oicinfp_comousar) {
        this.upr1_oicinfp_comousar = upr1_oicinfp_comousar;
    }

    public String getUpr1_oicinfp_validadepromo() {
        return upr1_oicinfp_validadepromo;
    }

    public void setUpr1_oicinfp_validadepromo(String upr1_oicinfp_validadepromo) {
        this.upr1_oicinfp_validadepromo = upr1_oicinfp_validadepromo;
    }

    public String getUpr1_oiccpoplanos_promooicontrole() {
        return upr1_oiccpoplanos_promooicontrole;
    }

    public void setUpr1_oiccpoplanos_promooicontrole(String upr1_oiccpoplanos_promooicontrole) {
        this.upr1_oiccpoplanos_promooicontrole = upr1_oiccpoplanos_promooicontrole;
    }

    public String getUpr1_oicinfp_regdados() {
        return upr1_oicinfp_regdados;
    }

    public void setUpr1_oicinfp_regdados(String upr1_oicinfp_regdados) {
        this.upr1_oicinfp_regdados = upr1_oicinfp_regdados;
    }

    public String getUpr1_oicpp_regdados() {
        return upr1_oicpp_regdados;
    }

    public void setUpr1_oicpp_regdados(String upr1_oicpp_regdados) {
        this.upr1_oicpp_regdados = upr1_oicpp_regdados;
    }

    public String getJacliente_sva() {
        return jacliente_sva;
    }

    public void setJacliente_sva(String jacliente_sva) {
        this.jacliente_sva = jacliente_sva;
    }
    
    
    
    //Final
    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                OiCMenu[] pp1 = mapper.readValue(history, OiCMenu[].class);
                setHistory(Arrays.asList(mapper.readValue(history, OiCMenu[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (OiCMenu template : pp1) {
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
        tb_ddd = tb_ddd != null ? tb_ddd.replace(" ", "").replace(";", ",") : tb_ddd;
    }
}
