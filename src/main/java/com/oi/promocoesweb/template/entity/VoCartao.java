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
import com.oi.promocoesweb.template.entity.ui.InputType;
import com.oi.promocoesweb.template.utils.DateUtils;
import com.oi.promocoesweb.validation.annotation.FieldInput;
import com.oi.promocoesweb.validation.annotation.FieldValidation;
import com.oi.promocoesweb.validation.annotation.Id;
import com.oi.promocoesweb.validation.enums.ValidationEnum;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mmouraam
 */
public class VoCartao extends Template {

    private static final Logger logger = LogManager.getLogger();

    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9?9")
    private String idbeneficio;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9?9")
    private String idprograma;
    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    @Id
    @FieldInput(name = InputType.STRING, mask = ",99")
    private String ddd;
    
    private String incentivo1;
    private String incentivo2;
    private String outrosplanos;
    private String ofertapromo1;
    private String ofertapromo2;
    private String tb_tipo_bonus;
    private Date data_inic;
    private Date data_fim;
    @FieldInput(name = InputType.MONEY)
    private String taxaadesao;
    @FieldInput(name = InputType.MONEY)
    private String tarifasmsdadoswifi;
    @FieldInput(name = InputType.MONEY)
    private String tarifasms;
    @FieldInput(name = InputType.MONEY)
    private String tarifadados;
    @FieldInput(name = InputType.MONEY)
    private String tarifavoz;
    @FieldInput(name = InputType.MONEY)
    private String tarifavozfixo;
    @FieldInput(name = InputType.MONEY)
    private String recargamin;
    private boolean elegivel_nova_promo;
    private boolean elegivel_promo_template_primaria;
    private String prioridade;
    private boolean ofertar_sempromo;
    private boolean precontratacao;
    private String cobrartaxa;
    private boolean tb_ussd_convertido = true;
    @FieldInput(name = InputType.MONEY)
    private String tb_gatilhoofertasflex;

    public VoCartao() {
        super();
    }

    public VoCartao(VoCartao selectedConfigOfertasPre) {
        this.idbeneficio = selectedConfigOfertasPre.getIdbeneficio();
        this.idprograma = selectedConfigOfertasPre.getIdprograma();
        this.incentivo1 = selectedConfigOfertasPre.getIncentivo1();
        this.incentivo2 = selectedConfigOfertasPre.getIncentivo2();
        this.ddd = selectedConfigOfertasPre.getDdd();
        this.data_inic = selectedConfigOfertasPre.getData_inic();
        this.data_fim = selectedConfigOfertasPre.getData_fim();
        this.ofertapromo1 = selectedConfigOfertasPre.getOfertapromo1();
        this.outrosplanos = selectedConfigOfertasPre.getOutrosplanos();
        this.ofertapromo2 = selectedConfigOfertasPre.getOfertapromo2();
        this.tb_tipo_bonus = selectedConfigOfertasPre.getTb_tipo_bonus();
        this.tarifasmsdadoswifi = selectedConfigOfertasPre.getTarifasmsdadoswifi();
        this.tarifasms = selectedConfigOfertasPre.getTarifasms();
        this.taxaadesao = selectedConfigOfertasPre.getTaxaadesao();
        this.tarifadados = selectedConfigOfertasPre.getTarifadados();
        this.tarifavoz = selectedConfigOfertasPre.getTarifavoz();
        this.tarifavozfixo = selectedConfigOfertasPre.getTarifavozfixo();
        this.recargamin = selectedConfigOfertasPre.getRecargamin();
        this.elegivel_nova_promo = selectedConfigOfertasPre.getElegivel_nova_promo();
        this.elegivel_promo_template_primaria = selectedConfigOfertasPre.getElegivel_promo_template_primaria();
        this.prioridade = selectedConfigOfertasPre.getPrioridade();
        this.ofertar_sempromo = selectedConfigOfertasPre.getOfertar_sempromo();
        this.precontratacao = selectedConfigOfertasPre.getPrecontratacao();
        this.cobrartaxa = selectedConfigOfertasPre.getCobrartaxa();
        this.tb_ussd_convertido = selectedConfigOfertasPre.getTb_ussd_convertido();
        this.tb_gatilhoofertasflex = selectedConfigOfertasPre.getTb_gatilhoofertasflex();
    }

    @JsonIgnore
    public String getIdDdd() {
        StringBuilder sb = new StringBuilder();
        if (ddd != null && ddd.length() >= 2) {
            return sb.append("ID_Beneficio: ").append(idbeneficio).append(" | ID_Programa: ").append(idprograma).append(" | DDD: ").append(ddd.substring(0, 2)).append("...").toString();
        } else {
            return sb.append("ID_Beneficio: ").append(idbeneficio).append(" | ID_Programa: ").append(idprograma).append(" | DDD: null").toString();
        }
    }

    public String getHistory(String field) {
        JsonObject jsonObject = null;

        return jsonObject + "";
    }

    @JsonIgnore
    public boolean isEditIncentivo1() {
        return super.getEditMap().getOrDefault("INCENTIVO1", false);
    }

    public void setEditIncentivo1(boolean checked) {
        super.getEditMap().put("INCENTIVO1", checked);
    }

    @JsonIgnore
    public boolean isEditIncentivo2() {
        return super.getEditMap().getOrDefault("INCENTIVO2", false);
    }

    public void setEditIncentivo2(boolean checked) {
        super.getEditMap().put("INCENTIVO2", checked);
    }

    @JsonIgnore
    public boolean isEditOutrosplanos() {
        return super.getEditMap().getOrDefault("OUTROSPLANOS", false);
    }

    public void setEditOutrosplanos(boolean checked) {
        super.getEditMap().put("OUTROSPLANOS", checked);
    }

    @JsonIgnore
    public boolean isEditOfertapromo1() {
        return super.getEditMap().getOrDefault("OFERTAPROMO1", false);
    }

    public void setEditOfertapromo1(boolean checked) {
        super.getEditMap().put("OFERTAPROMO1", checked);
    }

    @JsonIgnore
    public boolean isEditOfertapromo2() {
        return super.getEditMap().getOrDefault("OFERTAPROMO2", false);
    }

    public void setEditOfertapromo2(boolean checked) {
        super.getEditMap().put("OFERTAPROMO2", checked);
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
    public boolean isEditTaxaadesao() {
        return super.getEditMap().getOrDefault("TAXAADESAO", false);
    }

    public void setEditTaxaadesao(boolean checked) {
        super.getEditMap().put("TAXAADESAO", checked);
    }

    @JsonIgnore
    public boolean isEditTarifasmsdadoswifi() {
        return super.getEditMap().getOrDefault("TARIFASMSDADOSWIFI", false);
    }

    public void setEditTarifasmsdadoswifi(boolean checked) {
        super.getEditMap().put("TARIFASMSDADOSWIFI", checked);
    }
    
    @JsonIgnore
    public boolean isEditTarifasms() {
        return super.getEditMap().getOrDefault("TARIFASMS", false);
    }

    public void setEditTarifasms(boolean checked) {
        super.getEditMap().put("TARIFASMS", checked);
    }

    @JsonIgnore
    public boolean isEditTarifadados() {
        return super.getEditMap().getOrDefault("TARIFADADOS", false);
    }

    public void setEditTarifadados(boolean checked) {
        super.getEditMap().put("TARIFADADOS", checked);
    }

    @JsonIgnore
    public boolean isEditTarifavoz() {
        return super.getEditMap().getOrDefault("TARIFAVOZ", false);
    }

    public void setEditTarifavoz(boolean checked) {
        super.getEditMap().put("TARIFAVOZ", checked);
    }
    
    @JsonIgnore
    public boolean isEditTarifavozfixo() {
        return super.getEditMap().getOrDefault("TARIFAVOZFIXO", false);
    }

    public void setEditTarifavozfixo(boolean checked) {
        super.getEditMap().put("TARIFAVOZFIXO", checked);
    }

    @JsonIgnore
    public boolean isEditRecargamin() {
        return super.getEditMap().getOrDefault("RECARGAMIN", false);
    }

    public void setEditRecargamin(boolean checked) {
        super.getEditMap().put("RECARGAMIN", checked);
    }

    @JsonIgnore
    public boolean isEditElegivel_nova_promo() {
        return super.getEditMap().getOrDefault("ELEGIVEL_NOVA_PROMO", false);
    }

    public void setEditElegivel_nova_promo(boolean checked) {
        super.getEditMap().put("ELEGIVEL_NOVA_PROMO", checked);
    }

    @JsonIgnore
    public boolean isEditElegivel_promo_template_primaria() {
        return super.getEditMap().getOrDefault("ELEGIVEL_PROMO_TEMPLATE_PRIMARIA", false);
    }

    public void setEditElegivel_promo_template_primaria(boolean checked) {
        super.getEditMap().put("ELEGIVEL_PROMO_TEMPLATE_PRIMARIA", checked);
    }

    @JsonIgnore
    public boolean isEditPrioridade() {
        return super.getEditMap().getOrDefault("PRIORIDADE", false);
    }

    public void setEditPrioridade(boolean checked) {
        super.getEditMap().put("PRIORIDADE", checked);
    }

    @JsonIgnore
    public boolean isEditOfertar_sempromo() {
        return super.getEditMap().getOrDefault("OFERTAR_SEMPROMO", false);
    }

    public void setEditOfertar_sempromo(boolean checked) {
        super.getEditMap().put("OFERTAR_SEMPROMO", checked);
    }

    @JsonIgnore
    public boolean isEditPrecontratacao() {
        return super.getEditMap().getOrDefault("PRECONTRATACAO", false);
    }

    public void setEditPrecontratacao(boolean checked) {
        super.getEditMap().put("PRECONTRATACAO", checked);
    }

    @JsonIgnore
    public boolean isEditCobrartaxa() {
        return super.getEditMap().getOrDefault("COBRARTAXA", false);
    }

    public void setEditCobrartaxa(boolean checked) {
        super.getEditMap().put("COBRARTAXA", checked);
    }

    @JsonIgnore
    public boolean isEditTb_ussd_convertido() {
        return super.getEditMap().getOrDefault("TB_USSD_CONVERTIDO", false);
    }

    public void setEditTb_ussd_convertido(boolean checked) {
        super.getEditMap().put("TB_USSD_CONVERTIDO", checked);
    }

    @JsonIgnore
    public boolean isEditTb_gatilhoofertasflex() {
        return super.getEditMap().getOrDefault("TB_GATILHOOFERTASFLEX", false);
    }

    public void setEditTb_gatilhoofertasflex(boolean checked) {
        super.getEditMap().put("TB_GATILHOOFERTASFLEX", checked);
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

    public String getIncentivo1() {
        return incentivo1;
    }

    public void setIncentivo1(String incentivo1) {
        this.incentivo1 = incentivo1;
    }

    public String getIncentivo2() {
        return incentivo2;
    }

    public void setIncentivo2(String incentivo2) {
        this.incentivo2 = incentivo2;
    }

    public String getDdd() {
        return ddd;
    }
    
    @JsonIgnore
    public String getDddSub() {
        if(ddd != null && ddd.length()>59){
            String dddSub = ddd.substring(0, 59);
            dddSub = dddSub.substring(0, dddSub.lastIndexOf(","))+"...";
            return dddSub;
        }
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
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

    public String getOfertapromo1() {
        return ofertapromo1;
    }

    public void setOfertapromo1(String ofertapromo1) {
        this.ofertapromo1 = ofertapromo1;
    }

    public String getOutrosplanos() {
        return outrosplanos;
    }

    public void setOutrosplanos(String outrosplanos) {
        this.outrosplanos = outrosplanos;
    }

    public String getOfertapromo2() {
        return ofertapromo2;
    }

    public void setOfertapromo2(String ofertapromo2) {
        this.ofertapromo2 = ofertapromo2;
    }

    public String getTb_tipo_bonus() {
        return tb_tipo_bonus;
    }

    public void setTb_tipo_bonus(String tb_tipo_bonus) {
        this.tb_tipo_bonus = tb_tipo_bonus;
    }

    public String getTarifasmsdadoswifi() {
        return tarifasmsdadoswifi;
    }

    public void setTarifasmsdadoswifi(String tarifasmsdadoswifi) {
        this.tarifasmsdadoswifi = tarifasmsdadoswifi;
    }

    public String getTarifasms() {
        return tarifasms;
    }

    public void setTarifasms(String tarifasms) {
        this.tarifasms = tarifasms;
    }

    public String getTaxaadesao() {
        return taxaadesao;
    }

    public void setTaxaadesao(String taxaadesao) {
        this.taxaadesao = taxaadesao;
    }

    public String getTarifadados() {
        return tarifadados;
    }

    public void setTarifadados(String tarifadados) {
        this.tarifadados = tarifadados;
    }

    public String getTarifavoz() {
        return tarifavoz;
    }

    public void setTarifavoz(String tarifavoz) {
        this.tarifavoz = tarifavoz;
    }

    public String getTarifavozfixo() {
        return tarifavozfixo;
    }

    public void setTarifavozfixo(String tarifavozfixo) {
        this.tarifavozfixo = tarifavozfixo;
    }

    public String getRecargamin() {
        return recargamin;
    }

    public void setRecargamin(String recargamin) {
        this.recargamin = recargamin;
    }

    public boolean getElegivel_nova_promo() {
        return elegivel_nova_promo;
    }
    
    @JsonGetter("elegivel_nova_promo")
    public String getElegivel_nova_promoStr() {
        return elegivel_nova_promo ? "S" : "N";
    }

    @JsonSetter("elegivel_nova_promo")
    public void setElegivel_nova_promo(String elegivel_nova_promo) {
        this.elegivel_nova_promo = "S".equals(elegivel_nova_promo);
    }
    
    public void setElegivel_nova_promo(boolean elegivel_nova_promo) {
        this.elegivel_nova_promo = elegivel_nova_promo;
    }

    public boolean getElegivel_promo_template_primaria() {
        return elegivel_promo_template_primaria;
    }
    
    @JsonGetter("elegivel_promo_template_primaria")
    public String getElegivel_promo_template_primariaStr() {
        return elegivel_promo_template_primaria ? "S" : "N";
    }
    
    @JsonSetter("elegivel_promo_template_primaria")
    public void setElegivel_promo_template_primaria(String elegivel_promo_template_primaria) {
        this.elegivel_promo_template_primaria = "S".equals(elegivel_promo_template_primaria);
    }

    public void setElegivel_promo_template_primaria(boolean elegivel_promo_template_primaria) {
        this.elegivel_promo_template_primaria = elegivel_promo_template_primaria;
    }
    
    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public boolean getOfertar_sempromo() {
        return ofertar_sempromo;
    }
    
    @JsonGetter("ofertar_sempromo")
    public String getOfertar_sempromoStr() {
        return ofertar_sempromo ? "S" : "N";
    }

    @JsonSetter("ofertar_sempromo")
    public void setOfertar_sempromo(String ofertar_sempromo) {
        this.ofertar_sempromo = "S".equals(ofertar_sempromo);
    }

    public void setOfertar_sempromo(boolean ofertar_sempromo) {
        this.ofertar_sempromo = ofertar_sempromo;
    }
    
    public boolean getPrecontratacao() {
        return precontratacao;
    }
    
    @JsonGetter("precontratacao")
    public String getPrecontratacaoStr() {
        return precontratacao ? "S" : "N";
    }
    
    @JsonSetter("precontratacao")
    public void setPrecontratacao(String precontratacao) {
        this.precontratacao = "S".equals(precontratacao);
    }
    
    public void setPrecontratacao(boolean precontratacao) {
        this.precontratacao = precontratacao;
    }
    
    

    public String getCobrartaxa() {
        return cobrartaxa;
    }

    public void setCobrartaxa(String cobrartaxa) {
        this.cobrartaxa = cobrartaxa;
    }

    public boolean getTb_ussd_convertido() {
        return tb_ussd_convertido;
    }
    
    @JsonGetter("tb_ussd_convertido")
    public String getTb_ussd_convertidoStr() {
        return tb_ussd_convertido ? "S" : "N";
    }

    @JsonSetter("tb_ussd_convertido")
    public void setTb_ussd_convertido(String tb_ussd_convertido) {
        this.tb_ussd_convertido = "S".equals(tb_ussd_convertido);
    }

    public void setTb_ussd_convertido(boolean tb_ussd_convertido) {
        this.tb_ussd_convertido = tb_ussd_convertido;
    }

    public String getTb_gatilhoofertasflex() {
        return tb_gatilhoofertasflex;
    }

    public void setTb_gatilhoofertasflex(String tb_gatilhoofertasflex) {
        this.tb_gatilhoofertasflex = tb_gatilhoofertasflex;
    }

    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                VoCartao[] pp1 = mapper.readValue(history, VoCartao[].class);
                setHistory(Arrays.asList(mapper.readValue(history, VoCartao[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (VoCartao template : pp1) {
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
