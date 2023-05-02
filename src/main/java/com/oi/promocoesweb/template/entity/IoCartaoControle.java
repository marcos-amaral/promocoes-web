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
import java.util.HashSet;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;
import javax.json.JsonObject;

/**
 *
 * @author mmouraam
 */
public class IoCartaoControle extends Template {

    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    @Id
    @FieldInput(name = InputType.STRING, mask = ",99")
    private String ddd;
    @FieldInput(name = InputType.DROPDOWN)
    private String tb_incentivo1;
    @FieldInput(name = InputType.DROPDOWN)
    private String tb_incentivo2;
    @FieldInput(name = InputType.DROPDOWN)
    private String tb_incentivo3;
    @FieldInput(name = InputType.DROPDOWN)
    private String tb_incentivo4;
    @FieldInput(name = InputType.DROPDOWN)
    private String tb_incentivo5;
    @FieldInput(name = InputType.MONEY)
    private String recacumuladamesanteriorinicial;
    @FieldInput(name = InputType.MONEY)
    private String recacumuladamesanteriorfinal;
    @FieldInput(name = InputType.AUTOCOMPLETE, autoComplete = {"Credito", "Boleto"})
    private String tipopagamentoincentivo;

    public IoCartaoControle() {
        super();
    }

    public IoCartaoControle(IoCartaoControle selectedConfigOfertasPre) {
        this.ddd = selectedConfigOfertasPre.getDdd();
        this.tb_incentivo1 = selectedConfigOfertasPre.getTb_incentivo1();
        this.tb_incentivo2 = selectedConfigOfertasPre.getTb_incentivo2();
        this.tb_incentivo3 = selectedConfigOfertasPre.getTb_incentivo3();
        this.tb_incentivo4 = selectedConfigOfertasPre.getTb_incentivo4();
        this.tb_incentivo5 = selectedConfigOfertasPre.getTb_incentivo5();
        this.recacumuladamesanteriorinicial = selectedConfigOfertasPre.getRecacumuladamesanteriorinicial();
        this.recacumuladamesanteriorfinal = selectedConfigOfertasPre.getRecacumuladamesanteriorfinal();
        this.tipopagamentoincentivo = selectedConfigOfertasPre.getTipopagamentoincentivo();
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

    @JsonIgnore
    public boolean isEditDdd() {
        return super.getEditMap().getOrDefault("DDD", false);
    }

    public void setEditDdd(boolean checked) {
        super.getEditMap().put("DDD", checked);
    }

    @JsonIgnore
    public boolean isEditTb_incentivo1() {
        return super.getEditMap().getOrDefault("TB_INCENTIVO1", false);
    }

    public void setEditTb_incentivo1(boolean checked) {
        super.getEditMap().put("TB_INCENTIVO1", checked);
    }

    @JsonIgnore
    public boolean isEditTb_incentivo2() {
        return super.getEditMap().getOrDefault("TB_INCENTIVO2", false);
    }

    public void setEditTb_incentivo2(boolean checked) {
        super.getEditMap().put("TB_INCENTIVO2", checked);
    }

    @JsonIgnore
    public boolean isEditTb_incentivo3() {
        return super.getEditMap().getOrDefault("TB_INCENTIVO3", false);
    }

    public void setEditTb_incentivo3(boolean checked) {
        super.getEditMap().put("TB_INCENTIVO3", checked);
    }

    @JsonIgnore
    public boolean isEditTb_incentivo4() {
        return super.getEditMap().getOrDefault("TB_INCENTIVO4", false);
    }

    public void setEditTb_incentivo4(boolean checked) {
        super.getEditMap().put("TB_INCENTIVO4", checked);
    }

    @JsonIgnore
    public boolean isEditTb_incentivo5() {
        return super.getEditMap().getOrDefault("TB_INCENTIVO5", false);
    }

    public void setEditTb_incentivo5(boolean checked) {
        super.getEditMap().put("TB_INCENTIVO5", checked);
    }

    @JsonIgnore
    public boolean isEditRecacumuladamesanteriorinicial() {
        return super.getEditMap().getOrDefault("RECACUMULADAMESANTERIORINICIAL", false);
    }

    public void setEditRecacumuladamesanteriorinicial(boolean checked) {
        super.getEditMap().put("RECACUMULADAMESANTERIORINICIAL", checked);
    }

    @JsonIgnore
    public boolean isEditRecacumuladamesanteriorfinal() {
        return super.getEditMap().getOrDefault("RECACUMULADAMESANTERIORFINAL", false);
    }

    public void setEditRecacumuladamesanteriorfinal(boolean checked) {
        super.getEditMap().put("RECACUMULADAMESANTERIORFINAL", checked);
    }

    @JsonIgnore
    public boolean isEditTipopagamentoincentivo() {
        return super.getEditMap().getOrDefault("TIPOPAGAMENTOINCENTIVO", false);
    }

    public void setEditTipopagamentoincentivo(boolean checked) {
        super.getEditMap().put("TIPOPAGAMENTOINCENTIVO", checked);
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

    public String getTb_incentivo3() {
        return tb_incentivo3;
    }

    public void setTb_incentivo3(String id_ofertaocs) {
        this.tb_incentivo3 = id_ofertaocs;
    }

    public String getTb_incentivo4() {
        return tb_incentivo4;
    }

    public void setTb_incentivo4(String id_ofertaocs) {
        this.tb_incentivo4 = id_ofertaocs;
    }

    public String getTb_incentivo5() {
        return tb_incentivo5;
    }

    public void setTb_incentivo5(String id_ofertaocs) {
        this.tb_incentivo5 = id_ofertaocs;
    }

    public String getTb_incentivo1() {
        return tb_incentivo1;
    }

    public void setTb_incentivo1(String tb_incentivo1) {
        this.tb_incentivo1 = tb_incentivo1;
    }

    public String getTb_incentivo2() {
        return tb_incentivo2;
    }

    public void setTb_incentivo2(String tb_incentivo2) {
        this.tb_incentivo2 = tb_incentivo2;
    }

    public String getRecacumuladamesanteriorinicial() {
        return recacumuladamesanteriorinicial;
    }

    public void setRecacumuladamesanteriorinicial(String recacumuladamesanteriorinicial) {
        this.recacumuladamesanteriorinicial = recacumuladamesanteriorinicial;
    }

    public String getRecacumuladamesanteriorfinal() {
        return recacumuladamesanteriorfinal;
    }

    public void setRecacumuladamesanteriorfinal(String recacumuladamesanteriorfinal) {
        this.recacumuladamesanteriorfinal = recacumuladamesanteriorfinal;
    }

    public String getTipopagamentoincentivo() {
        return tipopagamentoincentivo;
    }

    public void setTipopagamentoincentivo(String tipopagamentoincentivo) {
        this.tipopagamentoincentivo = tipopagamentoincentivo;
    }

    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                IoCartaoControle[] pp1 = mapper.readValue(history, IoCartaoControle[].class);
                setHistory(Arrays.asList(mapper.readValue(history, IoCartaoControle[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (IoCartaoControle template : pp1) {
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

    @Override
    public boolean validate() throws ValidatorException {
        Set<String> set = new HashSet<>();
        Set<String> repeatedSet = new HashSet<>();

        set.add(tb_incentivo1);
        if (tb_incentivo2 != null && !"".equals(tb_incentivo2) && set.add(tb_incentivo2) == false) {
            repeatedSet.add(tb_incentivo2);
        }
        if (tb_incentivo3 != null && !"".equals(tb_incentivo3) && set.add(tb_incentivo3) == false) {
            repeatedSet.add(tb_incentivo3);
        }
        if (tb_incentivo4 != null && !"".equals(tb_incentivo4) && set.add(tb_incentivo4) == false) {
            repeatedSet.add(tb_incentivo4);
        }
        if (tb_incentivo5 != null && !"".equals(tb_incentivo5) && set.add(tb_incentivo5) == false) {
            repeatedSet.add(tb_incentivo5);
        }

        if (!repeatedSet.isEmpty()) {
            FacesMessage msg = new FacesMessage("Cartao > Controle\nIncentivos: Valores repetidos: " + repeatedSet.toString());
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        if (tb_incentivo2 == null || "".equals(tb_incentivo2)) {
            if ((tb_incentivo3 != null && !"".equals(tb_incentivo3))
                    || (tb_incentivo4 != null && !"".equals(tb_incentivo4))
                    || (tb_incentivo5 != null && !"".equals(tb_incentivo5))) {
                FacesMessage msg = new FacesMessage("Cartao > Controle\nIncentivos: Incentivo2 null, Incentivo3,4 ou 5 preenchidos");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }

        if (tb_incentivo3 == null || "".equals(tb_incentivo3)) {
            if ((tb_incentivo4 != null && !"".equals(tb_incentivo4))
                    || (tb_incentivo5 != null && !"".equals(tb_incentivo5))) {
                FacesMessage msg = new FacesMessage("Cartao > Controle\nIncentivos: Incentivo3 null, Incentivo4 ou 5 preenchidos");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }

        if (tb_incentivo4 == null || "".equals(tb_incentivo4)) {
            if ((tb_incentivo5 != null && !"".equals(tb_incentivo5))) {
                FacesMessage msg = new FacesMessage("Cartao > Controle\nIncentivos: Incentivo4 null, Incentivo5 preenchido");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }

        if (recacumuladamesanteriorinicial != null && !"".equals(recacumuladamesanteriorinicial)
                && recacumuladamesanteriorfinal != null && !"".equals(recacumuladamesanteriorfinal)) {

            if (Float.compare(Float.parseFloat(recacumuladamesanteriorinicial), Float.parseFloat(recacumuladamesanteriorfinal)) > 0) {
                FacesMessage msg = new FacesMessage("Cartao > Controle\nRecargaAcumulada: Valor inicial maior que valor final");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }

        return true;
    }
}
