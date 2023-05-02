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
public class PromptControleMenu extends Template {

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
    private String promopadrao_mensageminicial;
    private String promopadrao_regratorpedo;
    private String promopadrao_regraligacao;
    private String promopadrao_comousar;
    private String promopadrao_dados;
    private String informapromo_mensageminicial;
    private String informapromo_regratorpedo;
    private String informapromo_regraligacao;
    private String informapromo_comousar;
    private String informapromo_validadepromo;
    private String informapromo_dados;
    private String outrosplanos_informapromooicontrole;

    //construtores
    public PromptControleMenu() {
        super();
    }

    public PromptControleMenu(PromptControleMenu selectedConfigOfertasPre) {
        this.tb_id_beneficio = selectedConfigOfertasPre.getTb_id_beneficio();
        this.tb_id_programa = selectedConfigOfertasPre.getTb_id_programa();
        this.tb_ddd = selectedConfigOfertasPre.getTb_ddd();
        this.promopadrao_mensageminicial = selectedConfigOfertasPre.getPromopadrao_mensageminicial();
        this.promopadrao_regratorpedo = selectedConfigOfertasPre.getPromopadrao_regratorpedo();
        this.promopadrao_regraligacao = selectedConfigOfertasPre.getPromopadrao_regraligacao();
        this.promopadrao_comousar = selectedConfigOfertasPre.getPromopadrao_comousar();
        this.promopadrao_dados = selectedConfigOfertasPre.getPromopadrao_dados();
        this.informapromo_mensageminicial = selectedConfigOfertasPre.getInformapromo_mensageminicial();
        this.informapromo_regratorpedo = selectedConfigOfertasPre.getInformapromo_regratorpedo();
        this.informapromo_regraligacao = selectedConfigOfertasPre.getInformapromo_regraligacao();
        this.informapromo_comousar = selectedConfigOfertasPre.getInformapromo_comousar();
        this.informapromo_validadepromo = selectedConfigOfertasPre.getInformapromo_validadepromo();
        this.informapromo_dados = selectedConfigOfertasPre.getInformapromo_dados();
        this.outrosplanos_informapromooicontrole = selectedConfigOfertasPre.getOutrosplanos_informapromooicontrole();

    }

    @JsonIgnore
    public String getIdDdd() {
        StringBuilder sb = new StringBuilder();
        if (tb_ddd != null && tb_ddd.length() >= 2) {
            return sb.append("ID_Beneficio: ").append(tb_id_beneficio).append(" | ID_PROGRAMA: ").append(tb_id_programa).append(" | DDD: ").append(tb_ddd.substring(0, 2)).append("...").toString();
        } else {
            return sb.append("ID_Beneficio: ").append(tb_id_beneficio).append(" | ID_PROGRAMA: ").append(tb_id_programa).append(" | DDD: null").toString();
        }
    }

    public String getHistory(String field) {
        JsonObject jsonObject = null;

        return jsonObject + "";
    }

    //IsEdit
    @JsonIgnore
    public boolean isEditTb_id_beneficio() {
        return super.getEditMap().getOrDefault("TB_ID_BENEFICIO", false);
    }

    @JsonIgnore
    public boolean isEditTb_id_programa() {
        return super.getEditMap().getOrDefault("TB_ID_PROGRAMA", false);
    }

    @JsonIgnore
    public boolean isEditTb_ddd() {
        return super.getEditMap().getOrDefault("TB_DDD", false);
    }

    @JsonIgnore
    public boolean isEditPromopadrao_mensageminicial() {
        return super.getEditMap().getOrDefault("PROMOPADRAO_MENSAGEMINICIAL", false);
    }

    @JsonIgnore
    public boolean isEditPromopadrao_regratorpedo() {
        return super.getEditMap().getOrDefault("PROMOPADRAO_REGRATORPEDO", false);
    }

    @JsonIgnore
    public boolean isEditPromopadrao_regraligacao() {
        return super.getEditMap().getOrDefault("PROMOPADRAO_REGRALIGACAO", false);
    }

    @JsonIgnore
    public boolean isEditPromopadrao_comousar() {
        return super.getEditMap().getOrDefault("PROMOPADRAO_COMOUSAR", false);
    }

    @JsonIgnore
    public boolean isEditPromopadrao_dados() {
        return super.getEditMap().getOrDefault("PROMOPADRAO_DADOS", false);
    }

    @JsonIgnore
    public boolean isEditInformapromo_mensageminicial() {
        return super.getEditMap().getOrDefault("INFORMAPROMO_MENSAGEMINICIAL", false);
    }

    @JsonIgnore
    public boolean isEditInformapromo_regratorpedo() {
        return super.getEditMap().getOrDefault("INFORMAPROMO_REGRATORPEDO", false);
    }

    @JsonIgnore
    public boolean isEditInformapromo_regraligacao() {
        return super.getEditMap().getOrDefault("INFORMAPROMO_REGRALIGACAO", false);
    }

    @JsonIgnore
    public boolean isEditInformapromo_comousar() {
        return super.getEditMap().getOrDefault("INFORMAPROMO_COMOUSAR", false);
    }

    @JsonIgnore
    public boolean isEditInformapromo_validadepromo() {
        return super.getEditMap().getOrDefault("INFORMAPROMO_VALIDADEPROMO", false);
    }

    @JsonIgnore
    public boolean isEditInformapromo_dados() {
        return super.getEditMap().getOrDefault("INFORMAPROMO_DADOS", false);
    }

    @JsonIgnore
    public boolean isEditOutrosplanos_informapromooicontrole() {
        return super.getEditMap().getOrDefault("OUTROSPLANOS_INFORMAPROMOOICONTROLE", false);
    }

    //SetEdit
    public void setEditTb_id_beneficio(boolean checked) {
        super.getEditMap().put("TB_ID_BENEFICIO", checked);
    }

    public void setEditTb_id_programa(boolean checked) {
        super.getEditMap().put("TB_ID_PROGRAMA", checked);
    }

    public void setEditTb_ddd(boolean checked) {
        super.getEditMap().put("TB_DDD", checked);
    }

    public void setEditPromopadrao_mensageminicial(boolean checked) {
        super.getEditMap().put("PROMOPADRAO_MENSAGEMINICIAL", checked);
    }

    public void setEditPromopadrao_regratorpedo(boolean checked) {
        super.getEditMap().put("PROMOPADRAO_REGRATORPEDO", checked);
    }

    public void setEditPromopadrao_regraligacao(boolean checked) {
        super.getEditMap().put("PROMOPADRAO_REGRALIGACAO", checked);
    }

    public void setEditPromopadrao_comousar(boolean checked) {
        super.getEditMap().put("PROMOPADRAO_COMOUSAR", checked);
    }

    public void setEditPromopadrao_dados(boolean checked) {
        super.getEditMap().put("PROMOPADRAO_DADOS", checked);
    }

    public void setEditInformapromo_mensageminicial(boolean checked) {
        super.getEditMap().put("INFORMAPROMO_MENSAGEMINICIAL", checked);
    }

    public void setEditInformapromo_regratorpedo(boolean checked) {
        super.getEditMap().put("INFORMAPROMO_REGRATORPEDO", checked);
    }

    public void setEditInformapromo_regraligacao(boolean checked) {
        super.getEditMap().put("INFORMAPROMO_REGRALIGACAO", checked);
    }

    public void setEditInformapromo_comousar(boolean checked) {
        super.getEditMap().put("INFORMAPROMO_COMOUSAR", checked);
    }

    public void setEditInformapromo_validadepromo(boolean checked) {
        super.getEditMap().put("INFORMAPROMO_VALIDADEPROMO", checked);
    }

    public void setEditInformapromo_dados(boolean checked) {
        super.getEditMap().put("INFORMAPROMO_DADOS", checked);
    }

    public void setEditOutrosplanos_informapromooicontrole(boolean checked) {
        super.getEditMap().put("OUTROSPLANOS_INFORMAPROMOOICONTROLE", checked);
    }

    //getters and setters
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

    public String getPromopadrao_mensageminicial() {
        return promopadrao_mensageminicial;
    }

    public void setPromopadrao_mensageminicial(String promopadrao_mensageminicial) {
        this.promopadrao_mensageminicial = promopadrao_mensageminicial;
    }

    public String getPromopadrao_regratorpedo() {
        return promopadrao_regratorpedo;
    }

    public void setPromopadrao_regratorpedo(String promopadrao_regratorpedo) {
        this.promopadrao_regratorpedo = promopadrao_regratorpedo;
    }

    public String getPromopadrao_regraligacao() {
        return promopadrao_regraligacao;
    }

    public void setPromopadrao_regraligacao(String promopadrao_regraligacao) {
        this.promopadrao_regraligacao = promopadrao_regraligacao;
    }

    public String getPromopadrao_comousar() {
        return promopadrao_comousar;
    }

    public void setPromopadrao_comousar(String promopadrao_comousar) {
        this.promopadrao_comousar = promopadrao_comousar;
    }

    public String getPromopadrao_dados() {
        return promopadrao_dados;
    }

    public void setPromopadrao_dados(String promopadrao_dados) {
        this.promopadrao_dados = promopadrao_dados;
    }

    public String getInformapromo_mensageminicial() {
        return informapromo_mensageminicial;
    }

    public void setInformapromo_mensageminicial(String informapromo_mensageminicial) {
        this.informapromo_mensageminicial = informapromo_mensageminicial;
    }

    public String getInformapromo_regratorpedo() {
        return informapromo_regratorpedo;
    }

    public void setInformapromo_regratorpedo(String informapromo_regratorpedo) {
        this.informapromo_regratorpedo = informapromo_regratorpedo;
    }

    public String getInformapromo_regraligacao() {
        return informapromo_regraligacao;
    }

    public void setInformapromo_regraligacao(String informapromo_regraligacao) {
        this.informapromo_regraligacao = informapromo_regraligacao;
    }

    public String getInformapromo_comousar() {
        return informapromo_comousar;
    }

    public void setInformapromo_comousar(String informapromo_comousar) {
        this.informapromo_comousar = informapromo_comousar;
    }

    public String getInformapromo_validadepromo() {
        return informapromo_validadepromo;
    }

    public void setInformapromo_validadepromo(String informapromo_validadepromo) {
        this.informapromo_validadepromo = informapromo_validadepromo;
    }

    public String getInformapromo_dados() {
        return informapromo_dados;
    }

    public void setInformapromo_dados(String informapromo_dados) {
        this.informapromo_dados = informapromo_dados;
    }

    public String getOutrosplanos_informapromooicontrole() {
        return outrosplanos_informapromooicontrole;
    }

    public void setOutrosplanos_informapromooicontrole(String outrosplanos_informapromooicontrole) {
        this.outrosplanos_informapromooicontrole = outrosplanos_informapromooicontrole;
    }

    //final
    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                PromptControleMenu[] pp1 = mapper.readValue(history, PromptControleMenu[].class);
                setHistory(Arrays.asList(mapper.readValue(history, PromptControleMenu[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (PromptControleMenu template : pp1) {
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
