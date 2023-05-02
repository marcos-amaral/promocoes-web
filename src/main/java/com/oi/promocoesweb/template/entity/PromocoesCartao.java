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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mmouraam
 */
public class PromocoesCartao extends Template {

    private static final Logger logger = LogManager.getLogger();

    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9?9")
    private String tb_id_beneficio;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    @Id
    @FieldInput(name = InputType.STRING, mask = "9?9")
    private String tb_id_programa;
    @FieldValidation(name = ValidationEnum.VALOR_UNICO)
    private String tb_id_oferta;
    @FieldValidation(name = ValidationEnum.SEQUENCIA_VALORES)
    @Id
    @FieldInput(name = InputType.STRING, mask = ",99")
    private String tb_ddd;
    private String tb_regiao;
    private String incentivo1;
    private String incentivo2;
    private String outrosplanos;
    private String ofertapromo1;
    private String ofertapromo2;
    private String tb_nome_promocao;
    private String tb_tipo_promo;
    private boolean tb_sn_elegivel_nova_promo;
    @FieldInput(name = InputType.MONEY)
    private String tb_taxaadesao;
    private boolean tb_sn_elegivel_estendida;
    private Date tb_dt_inicio_promo;
    private Date tb_dt_fim_promo;
    private String tb_tipo_bonus;
    @FieldInput(name = InputType.MONEY)
    private String tb_recarga_min;
    @FieldInput(name = InputType.MONEY)
    private String tb_recarga_max;
    @FieldInput(name = InputType.MONEY)
    private String tb_bonus_min;
    @FieldInput(name = InputType.MONEY)
    private String tb_bonus_max;
    @FieldInput(name = InputType.INTEGER)
    private String tb_multiplicador;
    private boolean tb_sn_bonus_torpedo;
    @FieldInput(name = InputType.INTEGER)
    private String tb_qtd_torp;
    @FieldInput(name = InputType.INTEGER)
    private String tb_bonus_torp;
    private boolean tb_prorrogacao_6_meses;
    private boolean tb_prorrogacao_12_meses;
    @FieldInput(name = InputType.MONEY)
    private String tb_valortaxa_6_meses;
    @FieldInput(name = InputType.MONEY)
    private String tb_valortaxa_12_meses;
    @FieldInput(name = InputType.STRING, mask = "9?9")
    private String tb_id_beneficio_6_meses;
    @FieldInput(name = InputType.STRING, mask = "9?9")
    private String tb_id_programa_6_meses;
    @FieldInput(name = InputType.STRING, mask = "9?9")
    private String tb_id_beneficio_12_meses;
    @FieldInput(name = InputType.STRING, mask = "9?9")
    private String tb_id_programa_12_meses;
    private boolean tb_bonusbolsodiferenciado;
    @FieldInput(name = InputType.MONEY)
    private String tb_recargabolsodegrau2;
    @FieldInput(name = InputType.MONEY)
    private String tb_bolsobonusdegrau2;
    @FieldInput(name = InputType.MONEY)
    private String tb_recargabolsodegrau3;
    @FieldInput(name = InputType.MONEY)
    private String tb_bolsobonusdegrau3;
    private boolean tb_bonusligacaooimovel;
    private boolean tb_bonusligacaooimovelfixo;
    private boolean tb_bonusligacaomix;
    private boolean tb_ligacaooimovelfixo_dados;
    private boolean tb_bonusligacaomix_dados;
    private boolean tb_semcarencia;
    private boolean tem_bce;
    private boolean elegivel_promo_secundaria;
    private String prioridade;
    private String ofertar_sempromo;
    private boolean pre_contratacao;
    private String cobrartaxa;
    private boolean tb_ussd_convertido = true;
    private String tb_categoria;

    public PromocoesCartao() {
        super();
    }

    public PromocoesCartao(PromocoesCartao selectedConfigOfertasPre) {
        this.tb_id_beneficio = selectedConfigOfertasPre.getTb_id_beneficio();
        this.tb_id_programa = selectedConfigOfertasPre.getTb_id_programa();
        this.tb_id_oferta = selectedConfigOfertasPre.getTb_id_oferta();
        this.tb_ddd = selectedConfigOfertasPre.getTb_ddd();
        this.tb_regiao = selectedConfigOfertasPre.getTb_regiao();
        this.incentivo1 = selectedConfigOfertasPre.getIncentivo1();
        this.incentivo2 = selectedConfigOfertasPre.getIncentivo2();
        this.outrosplanos = selectedConfigOfertasPre.getOutrosplanos();
        this.ofertapromo1 = selectedConfigOfertasPre.getOfertapromo1();
        this.ofertapromo2 = selectedConfigOfertasPre.getOfertapromo2();
        this.tb_nome_promocao = selectedConfigOfertasPre.getTb_nome_promocao();
        this.tb_tipo_promo = selectedConfigOfertasPre.getTb_tipo_promo();
        this.tb_sn_elegivel_nova_promo = selectedConfigOfertasPre.isTb_sn_elegivel_nova_promo();
        this.tb_taxaadesao = selectedConfigOfertasPre.getTb_taxaadesao();
        this.tb_sn_elegivel_estendida = selectedConfigOfertasPre.isTb_sn_elegivel_estendida();
        this.tb_dt_inicio_promo = selectedConfigOfertasPre.getTb_dt_inicio_promo();
        this.tb_dt_fim_promo = selectedConfigOfertasPre.getTb_dt_fim_promo();
        this.tb_tipo_bonus = selectedConfigOfertasPre.getTb_tipo_bonus();
        this.tb_recarga_min = selectedConfigOfertasPre.getTb_recarga_min();
        this.tb_recarga_max = selectedConfigOfertasPre.getTb_recarga_max();
        this.tb_bonus_min = selectedConfigOfertasPre.getTb_bonus_min();
        this.tb_bonus_max = selectedConfigOfertasPre.getTb_bonus_max();
        this.tb_multiplicador = selectedConfigOfertasPre.getTb_multiplicador();
        this.tb_sn_bonus_torpedo = selectedConfigOfertasPre.isTb_sn_bonus_torpedo();
        this.tb_qtd_torp = selectedConfigOfertasPre.getTb_qtd_torp();
        this.tb_bonus_torp = selectedConfigOfertasPre.getTb_bonus_torp();
        this.tb_prorrogacao_6_meses = selectedConfigOfertasPre.isTb_prorrogacao_6_meses();
        this.tb_prorrogacao_12_meses = selectedConfigOfertasPre.isTb_prorrogacao_12_meses();
        this.tb_valortaxa_6_meses = selectedConfigOfertasPre.getTb_valortaxa_6_meses();
        this.tb_valortaxa_12_meses = selectedConfigOfertasPre.getTb_valortaxa_12_meses();
        this.tb_id_beneficio_6_meses = selectedConfigOfertasPre.getTb_id_beneficio_6_meses();
        this.tb_id_programa_6_meses = selectedConfigOfertasPre.getTb_id_programa_6_meses();
        this.tb_id_beneficio_12_meses = selectedConfigOfertasPre.getTb_id_beneficio_12_meses();
        this.tb_id_programa_12_meses = selectedConfigOfertasPre.getTb_id_programa_12_meses();
        this.tb_bonusbolsodiferenciado = selectedConfigOfertasPre.isTb_bonusbolsodiferenciado();
        this.tb_recargabolsodegrau2 = selectedConfigOfertasPre.getTb_recargabolsodegrau2();
        this.tb_bolsobonusdegrau2 = selectedConfigOfertasPre.getTb_bolsobonusdegrau2();
        this.tb_recargabolsodegrau3 = selectedConfigOfertasPre.getTb_recargabolsodegrau3();
        this.tb_bolsobonusdegrau3 = selectedConfigOfertasPre.getTb_bolsobonusdegrau3();
        this.tb_bonusligacaooimovel = selectedConfigOfertasPre.isTb_bonusligacaooimovel();
        this.tb_bonusligacaooimovelfixo = selectedConfigOfertasPre.isTb_bonusligacaooimovelfixo();
        this.tb_bonusligacaomix = selectedConfigOfertasPre.isTb_bonusligacaomix();
        this.tb_ligacaooimovelfixo_dados = selectedConfigOfertasPre.isTb_ligacaooimovelfixo_dados();
        this.tb_bonusligacaomix_dados = selectedConfigOfertasPre.isTb_bonusligacaomix_dados();
        this.tb_semcarencia = selectedConfigOfertasPre.isTb_semcarencia();
        this.tem_bce = selectedConfigOfertasPre.isTem_bce();
        this.elegivel_promo_secundaria = selectedConfigOfertasPre.isElegivel_promo_secundaria();
        this.prioridade = selectedConfigOfertasPre.getPrioridade();
        this.ofertar_sempromo = selectedConfigOfertasPre.getOfertar_sempromo();
        this.pre_contratacao = selectedConfigOfertasPre.isPre_contratacao();
        this.cobrartaxa = selectedConfigOfertasPre.getCobrartaxa();
        this.tb_ussd_convertido = selectedConfigOfertasPre.isTb_ussd_convertido();
        this.tb_categoria = selectedConfigOfertasPre.getTb_categoria();

    }
    
    @JsonIgnore public boolean isEditIncentivo1() {return super.getEditMap().getOrDefault("INCENTIVO1", false);}
    @JsonIgnore public boolean isEditIncentivo2() {return super.getEditMap().getOrDefault("INCENTIVO2", false);}
    @JsonIgnore public boolean isEditOutrosplanos() {return super.getEditMap().getOrDefault("OUTROSPLANOS", false);}
    @JsonIgnore public boolean isEditOfertapromo1() {return super.getEditMap().getOrDefault("OFERTAPROMO1", false);}
    @JsonIgnore public boolean isEditOfertapromo2() {return super.getEditMap().getOrDefault("OFERTAPROMO2", false);}
    @JsonIgnore public boolean isEditTb_nome_promocao() {return super.getEditMap().getOrDefault("TB_NOME_PROMOCAO", false);}
    @JsonIgnore public boolean isEditTb_tipo_promo() {return super.getEditMap().getOrDefault("TB_TIPO_PROMO", false);}
    @JsonIgnore public boolean isEditTb_sn_elegivel_nova_promo() {return super.getEditMap().getOrDefault("TB_SN_ELEGIVEL_NOVA_PROMO", false);}
    @JsonIgnore public boolean isEditTb_taxaadesao() {return super.getEditMap().getOrDefault("TB_TAXAADESAO", false);}
    @JsonIgnore public boolean isEditTb_sn_elegivel_estendida() {return super.getEditMap().getOrDefault("TB_SN_ELEGIVEL_ESTENDIDA", false);}
    @JsonIgnore public boolean isEditTb_dt_inicio_promo() {return super.getEditMap().getOrDefault("TB_DT_INICIO_PROMO", false);}
    @JsonIgnore public boolean isEditTb_dt_fim_promo() {return super.getEditMap().getOrDefault("TB_DT_FIM_PROMO", false);}
    @JsonIgnore public boolean isEditTb_tipo_bonus() {return super.getEditMap().getOrDefault("TB_TIPO_BONUS", false);}
    @JsonIgnore public boolean isEditTb_recarga_min() {return super.getEditMap().getOrDefault("TB_RECARGA_MIN", false);}
    @JsonIgnore public boolean isEditTb_recarga_max() {return super.getEditMap().getOrDefault("TB_RECARGA_MAX", false);}
    @JsonIgnore public boolean isEditTb_bonus_min() {return super.getEditMap().getOrDefault("TB_BONUS_MIN", false);}
    @JsonIgnore public boolean isEditTb_bonus_max() {return super.getEditMap().getOrDefault("TB_BONUS_MAX", false);}
    @JsonIgnore public boolean isEditTb_multiplicador() {return super.getEditMap().getOrDefault("TB_MULTIPLICADOR", false);}
    @JsonIgnore public boolean isEditTb_sn_bonus_torpedo() {return super.getEditMap().getOrDefault("TB_SN_BONUS_TORPEDO", false);}
    @JsonIgnore public boolean isEditTb_qtd_torp() {return super.getEditMap().getOrDefault("TB_QTD_TORP", false);}
    @JsonIgnore public boolean isEditTb_bonus_torp() {return super.getEditMap().getOrDefault("TB_BONUS_TORP", false);}
    @JsonIgnore public boolean isEditTb_prorrogacao_6_meses() {return super.getEditMap().getOrDefault("TB_PRORROGACAO_6_MESES", false);}
    @JsonIgnore public boolean isEditTb_prorrogacao_12_meses() {return super.getEditMap().getOrDefault("TB_PRORROGACAO_12_MESES", false);}
    @JsonIgnore public boolean isEditTb_valortaxa_6_meses() {return super.getEditMap().getOrDefault("TB_VALORTAXA_6_MESES", false);}
    @JsonIgnore public boolean isEditTb_valortaxa_12_meses() {return super.getEditMap().getOrDefault("TB_VALORTAXA_12_MESES", false);}
    @JsonIgnore public boolean isEditTb_id_beneficio_6_meses() {return super.getEditMap().getOrDefault("TB_ID_BENEFICIO_6_MESES", false);}
    @JsonIgnore public boolean isEditTb_id_programa_6_meses() {return super.getEditMap().getOrDefault("TB_ID_PROGRAMA_6_MESES", false);}
    @JsonIgnore public boolean isEditTb_id_beneficio_12_meses() {return super.getEditMap().getOrDefault("TB_ID_BENEFICIO_12_MESES", false);}
    @JsonIgnore public boolean isEditTb_id_programa_12_meses() {return super.getEditMap().getOrDefault("TB_ID_PROGRAMA_12_MESES", false);}
    @JsonIgnore public boolean isEditTb_bonusbolsodiferenciado() {return super.getEditMap().getOrDefault("TB_BONUSBOLSODIFERENCIADO", false);}
    @JsonIgnore public boolean isEditTb_recargabolsodegrau2() {return super.getEditMap().getOrDefault("TB_RECARGABOLSODEGRAU2", false);}
    @JsonIgnore public boolean isEditTb_bolsobonusdegrau2() {return super.getEditMap().getOrDefault("TB_BOLSOBONUSDEGRAU2", false);}
    @JsonIgnore public boolean isEditTb_recargabolsodegrau3() {return super.getEditMap().getOrDefault("TB_RECARGABOLSODEGRAU3", false);}
    @JsonIgnore public boolean isEditTb_bolsobonusdegrau3() {return super.getEditMap().getOrDefault("TB_BOLSOBONUSDEGRAU3", false);}
    @JsonIgnore public boolean isEditTb_bonusligacaooimovel() {return super.getEditMap().getOrDefault("TB_BONUSLIGACAOOIMOVEL", false);}
    @JsonIgnore public boolean isEditTb_bonusligacaooimovelfixo() {return super.getEditMap().getOrDefault("TB_BONUSLIGACAOOIMOVELFIXO", false);}
    @JsonIgnore public boolean isEditTb_bonusligacaomix() {return super.getEditMap().getOrDefault("TB_BONUSLIGACAOMIX", false);}
    @JsonIgnore public boolean isEditTb_ligacaooimovelfixo_dados() {return super.getEditMap().getOrDefault("TB_LIGACAOOIMOVELFIXO_DADOS", false);}
    @JsonIgnore public boolean isEditTb_bonusligacaomix_dados() {return super.getEditMap().getOrDefault("TB_BONUSLIGACAOMIX_DADOS", false);}
    @JsonIgnore public boolean isEditTb_semcarencia() {return super.getEditMap().getOrDefault("TB_SEMCARENCIA", false);}
    @JsonIgnore public boolean isEditTem_bce() {return super.getEditMap().getOrDefault("TEM_BCE", false);}
    @JsonIgnore public boolean isEditElegivel_promo_secundaria() {return super.getEditMap().getOrDefault("ELEGIVEL_PROMO_SECUNDARIA", false);}
    @JsonIgnore public boolean isEditPrioridade() {return super.getEditMap().getOrDefault("PRIORIDADE", false);}
    @JsonIgnore public boolean isEditOfertar_sempromo() {return super.getEditMap().getOrDefault("OFERTAR_SEMPROMO", false);}
    @JsonIgnore public boolean isEditPre_contratacao() {return super.getEditMap().getOrDefault("PRE_CONTRATACAO", false);}
    @JsonIgnore public boolean isEditCobrartaxa() {return super.getEditMap().getOrDefault("COBRARTAXA", false);}
    @JsonIgnore public boolean isEditTb_ussd_convertido() {return super.getEditMap().getOrDefault("TB_USSD_CONVERTIDO", false);}
    @JsonIgnore public boolean isEditTb_categoria() {return super.getEditMap().getOrDefault("TB_CATEGORIA", false);}

    public void setEditTb_id_beneficio(boolean checked) {super.getEditMap().put("TB_ID_BENEFICIO", checked);}
    public void setEditTb_id_programa(boolean checked) {super.getEditMap().put("TB_ID_PROGRAMA", checked);}
    public void setEditTb_id_oferta(boolean checked) {super.getEditMap().put("TB_ID_OFERTA", checked);}
    public void setEditTb_ddd(boolean checked) {super.getEditMap().put("TB_DDD", checked);}
    public void setEditTb_regiao(boolean checked) {super.getEditMap().put("TB_REGIAO", checked);}
    public void setEditIncentivo1(boolean checked) {super.getEditMap().put("INCENTIVO1", checked);}
    public void setEditIncentivo2(boolean checked) {super.getEditMap().put("INCENTIVO2", checked);}
    public void setEditOutrosplanos(boolean checked) {super.getEditMap().put("OUTROSPLANOS", checked);}
    public void setEditOfertapromo1(boolean checked) {super.getEditMap().put("OFERTAPROMO1", checked);}
    public void setEditOfertapromo2(boolean checked) {super.getEditMap().put("OFERTAPROMO2", checked);}
    public void setEditTb_nome_promocao(boolean checked) {super.getEditMap().put("TB_NOME_PROMOCAO", checked);}
    public void setEditTb_tipo_promo(boolean checked) {super.getEditMap().put("TB_TIPO_PROMO", checked);}
    public void setEditTb_sn_elegivel_nova_promo(boolean checked) {super.getEditMap().put("TB_SN_ELEGIVEL_NOVA_PROMO", checked);}
    public void setEditTb_taxaadesao(boolean checked) {super.getEditMap().put("TB_TAXAADESAO", checked);}
    public void setEditTb_sn_elegivel_estendida(boolean checked) {super.getEditMap().put("TB_SN_ELEGIVEL_ESTENDIDA", checked);}
    public void setEditTb_dt_inicio_promo(boolean checked) {super.getEditMap().put("TB_DT_INICIO_PROMO", checked);}
    public void setEditTb_dt_fim_promo(boolean checked) {super.getEditMap().put("TB_DT_FIM_PROMO", checked);}
    public void setEditTb_tipo_bonus(boolean checked) {super.getEditMap().put("TB_TIPO_BONUS", checked);}
    public void setEditTb_recarga_min(boolean checked) {super.getEditMap().put("TB_RECARGA_MIN", checked);}
    public void setEditTb_recarga_max(boolean checked) {super.getEditMap().put("TB_RECARGA_MAX", checked);}
    public void setEditTb_bonus_min(boolean checked) {super.getEditMap().put("TB_BONUS_MIN", checked);}
    public void setEditTb_bonus_max(boolean checked) {super.getEditMap().put("TB_BONUS_MAX", checked);}
    public void setEditTb_multiplicador(boolean checked) {super.getEditMap().put("TB_MULTIPLICADOR", checked);}
    public void setEditTb_sn_bonus_torpedo(boolean checked) {super.getEditMap().put("TB_SN_BONUS_TORPEDO", checked);}
    public void setEditTb_qtd_torp(boolean checked) {super.getEditMap().put("TB_QTD_TORP", checked);}
    public void setEditTb_bonus_torp(boolean checked) {super.getEditMap().put("TB_BONUS_TORP", checked);}
    public void setEditTb_prorrogacao_6_meses(boolean checked) {super.getEditMap().put("TB_PRORROGACAO_6_MESES", checked);}
    public void setEditTb_prorrogacao_12_meses(boolean checked) {super.getEditMap().put("TB_PRORROGACAO_12_MESES", checked);}
    public void setEditTb_valortaxa_6_meses(boolean checked) {super.getEditMap().put("TB_VALORTAXA_6_MESES", checked);}
    public void setEditTb_valortaxa_12_meses(boolean checked) {super.getEditMap().put("TB_VALORTAXA_12_MESES", checked);}
    public void setEditTb_id_beneficio_6_meses(boolean checked) {super.getEditMap().put("TB_ID_BENEFICIO_6_MESES", checked);}
    public void setEditTb_id_programa_6_meses(boolean checked) {super.getEditMap().put("TB_ID_PROGRAMA_6_MESES", checked);}
    public void setEditTb_id_beneficio_12_meses(boolean checked) {super.getEditMap().put("TB_ID_BENEFICIO_12_MESES", checked);}
    public void setEditTb_id_programa_12_meses(boolean checked) {super.getEditMap().put("TB_ID_PROGRAMA_12_MESES", checked);}
    public void setEditTb_bonusbolsodiferenciado(boolean checked) {super.getEditMap().put("TB_BONUSBOLSODIFERENCIADO", checked);}
    public void setEditTb_recargabolsodegrau2(boolean checked) {super.getEditMap().put("TB_RECARGABOLSODEGRAU2", checked);}
    public void setEditTb_bolsobonusdegrau2(boolean checked) {super.getEditMap().put("TB_BOLSOBONUSDEGRAU2", checked);}
    public void setEditTb_recargabolsodegrau3(boolean checked) {super.getEditMap().put("TB_RECARGABOLSODEGRAU3", checked);}
    public void setEditTb_bolsobonusdegrau3(boolean checked) {super.getEditMap().put("TB_BOLSOBONUSDEGRAU3", checked);}
    public void setEditTb_bonusligacaooimovel(boolean checked) {super.getEditMap().put("TB_BONUSLIGACAOOIMOVEL", checked);}
    public void setEditTb_bonusligacaooimovelfixo(boolean checked) {super.getEditMap().put("TB_BONUSLIGACAOOIMOVELFIXO", checked);}
    public void setEditTb_bonusligacaomix(boolean checked) {super.getEditMap().put("TB_BONUSLIGACAOMIX", checked);}
    public void setEditTb_ligacaooimovelfixo_dados(boolean checked) {super.getEditMap().put("TB_LIGACAOOIMOVELFIXO_DADOS", checked);}
    public void setEditTb_bonusligacaomix_dados(boolean checked) {super.getEditMap().put("TB_BONUSLIGACAOMIX_DADOS", checked);}
    public void setEditTb_semcarencia(boolean checked) {super.getEditMap().put("TB_SEMCARENCIA", checked);}
    public void setEditTem_bce(boolean checked) {super.getEditMap().put("TEM_BCE", checked);}
    public void setEditElegivel_promo_secundaria(boolean checked) {super.getEditMap().put("ELEGIVEL_PROMO_SECUNDARIA", checked);}
    public void setEditPrioridade(boolean checked) {super.getEditMap().put("PRIORIDADE", checked);}
    public void setEditOfertar_sempromo(boolean checked) {super.getEditMap().put("OFERTAR_SEMPROMO", checked);}
    public void setEditPre_contratacao(boolean checked) {super.getEditMap().put("PRE_CONTRATACAO", checked);}
    public void setEditCobrartaxa(boolean checked) {super.getEditMap().put("COBRARTAXA", checked);}
    public void setEditTb_ussd_convertido(boolean checked) {super.getEditMap().put("TB_USSD_CONVERTIDO", checked);}
    public void setEditTb_categoria(boolean checked) {super.getEditMap().put("TB_CATEGORIA", checked);}

    @JsonIgnore
    public String getIdDdd() {
        StringBuilder sb = new StringBuilder();
        if (tb_ddd != null && tb_ddd.length() >= 2) {
            return sb.append("ID_BFPG: ").append(tb_id_beneficio).append(tb_id_programa).append(" | DDD: ").append(tb_ddd.substring(0, 2)).append("...").toString();
        } else {
            return sb.append("ID_BFPG: ").append(tb_id_beneficio).append(tb_id_programa).append(" | DDD: null").toString();
        }
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

    public String getTb_id_oferta() {
        return tb_id_oferta;
    }

    public void setTb_id_oferta(String tb_id_oferta) {
        this.tb_id_oferta = tb_id_oferta;
    }

    public String getTb_ddd() {
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

    public String getOutrosplanos() {
        return outrosplanos;
    }

    public void setOutrosplanos(String outrosplanos) {
        this.outrosplanos = outrosplanos;
    }

    public String getOfertapromo1() {
        return ofertapromo1;
    }

    public void setOfertapromo1(String ofertapromo1) {
        this.ofertapromo1 = ofertapromo1;
    }

    public String getOfertapromo2() {
        return ofertapromo2;
    }

    public void setOfertapromo2(String ofertapromo2) {
        this.ofertapromo2 = ofertapromo2;
    }

    public String getTb_nome_promocao() {
        return tb_nome_promocao;
    }

    public void setTb_nome_promocao(String tb_nome_promocao) {
        this.tb_nome_promocao = tb_nome_promocao;
    }

    public String getTb_tipo_promo() {
        return tb_tipo_promo;
    }

    public void setTb_tipo_promo(String tb_tipo_promo) {
        this.tb_tipo_promo = tb_tipo_promo;
    }

    public boolean isTb_sn_elegivel_nova_promo() {
        return tb_sn_elegivel_nova_promo;
    }
    
    @JsonGetter("tb_sn_elegivel_nova_promo")
    public String getTb_sn_elegivel_nova_promoStr() {
        return tb_sn_elegivel_nova_promo ? "S" : "N";
    }

    @JsonSetter("tb_sn_elegivel_nova_promo")
    public void setTb_sn_elegivel_nova_promo(String tb_sn_elegivel_nova_promo) {
        this.tb_sn_elegivel_nova_promo = "S".equals(tb_sn_elegivel_nova_promo);
    }

    public void setTb_sn_elegivel_nova_promo(boolean tb_sn_elegivel_nova_promo) {
        this.tb_sn_elegivel_nova_promo = tb_sn_elegivel_nova_promo;
    }

    public String getTb_taxaadesao() {
        return tb_taxaadesao;
    }

    public void setTb_taxaadesao(String tb_taxaadesao) {
        this.tb_taxaadesao = tb_taxaadesao;
    }

    public boolean isTb_sn_elegivel_estendida() {
        return tb_sn_elegivel_estendida;
    }
    
    @JsonGetter("tb_sn_elegivel_estendida")
    public String getTb_sn_elegivel_estendidaStr() {
        return tb_sn_elegivel_estendida ? "S" : "N";
    }

    @JsonSetter("tb_sn_elegivel_estendida")
    public void setTb_sn_elegivel_estendida(String tb_sn_elegivel_estendida) {
        this.tb_sn_elegivel_estendida = "S".equals(tb_sn_elegivel_estendida);
    }

    public void setTb_sn_elegivel_estendida(boolean tb_sn_elegivel_estendida) {
        this.tb_sn_elegivel_estendida = tb_sn_elegivel_estendida;
    }

    public Date getTb_dt_inicio_promo() {
        return tb_dt_inicio_promo;
    }
    
    @JsonGetter("tb_dt_inicio_promo")
    public String getTb_dt_inicio_promoStr() {
        if (tb_dt_inicio_promo != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            return sdf.format(tb_dt_inicio_promo);
        }

        return "";
    }

    @JsonSetter("tb_dt_inicio_promo")
    public void setTb_dt_inicio_promoAsString(String tb_dt_inicio_promo) {
        try {
            if (tb_dt_inicio_promo != null && !"".equals(tb_dt_inicio_promo)) {
                this.tb_dt_inicio_promo = DateUtils.parse(tb_dt_inicio_promo);
            }
        } catch (Exception e) {
            logger.warn("invalid tb_dt_inicio_promo: {}", tb_dt_inicio_promo);
        }
    }

    public void setTb_dt_inicio_promo(Date tb_dt_inicio_promo) {
        this.tb_dt_inicio_promo = tb_dt_inicio_promo;
    }

    public Date getTb_dt_fim_promo() {
        return tb_dt_fim_promo;
    }
    
    @JsonGetter("tb_dt_fim_promo")
    public String getTb_dt_fim_promoStr() {
        if (tb_dt_fim_promo != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            return sdf.format(tb_dt_fim_promo);
        }

        return "";
    }

    @JsonSetter("tb_dt_fim_promo")
    public void setTb_dt_fim_promoAsString(String tb_dt_fim_promo) {
        try {
            if (tb_dt_fim_promo != null && !"".equals(tb_dt_fim_promo)) {
                this.tb_dt_fim_promo = DateUtils.parse(tb_dt_fim_promo);
            }
        } catch (Exception e) {
            logger.warn("invalid tb_dt_fim_promo: {}", tb_dt_fim_promo);
        }
    }

    public void setTb_dt_fim_promo(Date tb_dt_fim_promo) {
        this.tb_dt_fim_promo = tb_dt_fim_promo;
    }

    public String getTb_tipo_bonus() {
        return tb_tipo_bonus;
    }

    public void setTb_tipo_bonus(String tb_tipo_bonus) {
        this.tb_tipo_bonus = tb_tipo_bonus;
    }

    public String getTb_recarga_min() {
        return tb_recarga_min;
    }

    public void setTb_recarga_min(String tb_recarga_min) {
        this.tb_recarga_min = tb_recarga_min;
    }

    public String getTb_recarga_max() {
        return tb_recarga_max;
    }

    public void setTb_recarga_max(String tb_recarga_max) {
        this.tb_recarga_max = tb_recarga_max;
    }

    public String getTb_bonus_min() {
        return tb_bonus_min;
    }

    public void setTb_bonus_min(String tb_bonus_min) {
        this.tb_bonus_min = tb_bonus_min;
    }

    public String getTb_bonus_max() {
        return tb_bonus_max;
    }

    public void setTb_bonus_max(String tb_bonus_max) {
        this.tb_bonus_max = tb_bonus_max;
    }

    public String getTb_multiplicador() {
        return tb_multiplicador;
    }

    public void setTb_multiplicador(String tb_multiplicador) {
        this.tb_multiplicador = tb_multiplicador;
    }

    public boolean isTb_sn_bonus_torpedo() {
        return tb_sn_bonus_torpedo;
    }
    
    @JsonGetter("tb_sn_bonus_torpedo")
    public String getTb_sn_bonus_torpedoStr() {
        return tb_sn_bonus_torpedo ? "S" : "N";
    }

    @JsonSetter("tb_sn_bonus_torpedo")
    public void setTb_sn_bonus_torpedo(String tb_sn_bonus_torpedo) {
        this.tb_sn_bonus_torpedo = "S".equals(tb_sn_bonus_torpedo);
    }

    public void setTb_sn_bonus_torpedo(boolean tb_sn_bonus_torpedo) {
        this.tb_sn_bonus_torpedo = tb_sn_bonus_torpedo;
    }

    public String getTb_qtd_torp() {
        return tb_qtd_torp;
    }

    public void setTb_qtd_torp(String tb_qtd_torp) {
        this.tb_qtd_torp = tb_qtd_torp;
    }

    public String getTb_bonus_torp() {
        return tb_bonus_torp;
    }

    public void setTb_bonus_torp(String tb_bonus_torp) {
        this.tb_bonus_torp = tb_bonus_torp;
    }

    public boolean isTb_prorrogacao_6_meses() {
        return tb_prorrogacao_6_meses;
    }
    
    @JsonGetter("tb_prorrogacao_6_meses")
    public String getTb_prorrogacao_6_mesesStr() {
        return tb_prorrogacao_6_meses ? "S" : "N";
    }

    @JsonSetter("tb_prorrogacao_6_meses")
    public void setTb_prorrogacao_6_meses(String tb_prorrogacao_6_meses) {
        this.tb_prorrogacao_6_meses = "S".equals(tb_prorrogacao_6_meses);
    }

    public void setTb_prorrogacao_6_meses(boolean tb_prorrogacao_6_meses) {
        this.tb_prorrogacao_6_meses = tb_prorrogacao_6_meses;
    }

    public boolean isTb_prorrogacao_12_meses() {
        return tb_prorrogacao_12_meses;
    }
    
    @JsonGetter("tb_prorrogacao_12_meses")
    public String getTb_prorrogacao_12_mesesStr() {
        return tb_prorrogacao_12_meses ? "S" : "N";
    }

    @JsonSetter("tb_prorrogacao_12_meses")
    public void setTb_prorrogacao_12_meses(String tb_prorrogacao_12_meses) {
        this.tb_prorrogacao_12_meses = "S".equals(tb_prorrogacao_12_meses);
    }

    public void setTb_prorrogacao_12_meses(boolean tb_prorrogacao_12_meses) {
        this.tb_prorrogacao_12_meses = tb_prorrogacao_12_meses;
    }

    public String getTb_valortaxa_6_meses() {
        return tb_valortaxa_6_meses;
    }

    public void setTb_valortaxa_6_meses(String tb_valortaxa_6_meses) {
        this.tb_valortaxa_6_meses = tb_valortaxa_6_meses;
    }

    public String getTb_valortaxa_12_meses() {
        return tb_valortaxa_12_meses;
    }

    public void setTb_valortaxa_12_meses(String tb_valortaxa_12_meses) {
        this.tb_valortaxa_12_meses = tb_valortaxa_12_meses;
    }

    public String getTb_id_beneficio_6_meses() {
        return tb_id_beneficio_6_meses;
    }

    public void setTb_id_beneficio_6_meses(String tb_id_beneficio_6_meses) {
        this.tb_id_beneficio_6_meses = tb_id_beneficio_6_meses;
    }

    public String getTb_id_programa_6_meses() {
        return tb_id_programa_6_meses;
    }

    public void setTb_id_programa_6_meses(String tb_id_programa_6_meses) {
        this.tb_id_programa_6_meses = tb_id_programa_6_meses;
    }

    public String getTb_id_beneficio_12_meses() {
        return tb_id_beneficio_12_meses;
    }

    public void setTb_id_beneficio_12_meses(String tb_id_beneficio_12_meses) {
        this.tb_id_beneficio_12_meses = tb_id_beneficio_12_meses;
    }

    public String getTb_id_programa_12_meses() {
        return tb_id_programa_12_meses;
    }

    public void setTb_id_programa_12_meses(String tb_id_programa_12_meses) {
        this.tb_id_programa_12_meses = tb_id_programa_12_meses;
    }

    public boolean isTb_bonusbolsodiferenciado() {
        return tb_bonusbolsodiferenciado;
    }
    
    @JsonGetter("tb_bonusbolsodiferenciado")
    public String getTb_bonusbolsodiferenciadoStr() {
        return tb_bonusbolsodiferenciado ? "S" : "N";
    }

    @JsonSetter("tb_bonusbolsodiferenciado")
    public void setTb_bonusbolsodiferenciado(String tb_bonusbolsodiferenciado) {
        this.tb_bonusbolsodiferenciado = "S".equals(tb_bonusbolsodiferenciado);
    }

    public void setTb_bonusbolsodiferenciado(boolean tb_bonusbolsodiferenciado) {
        this.tb_bonusbolsodiferenciado = tb_bonusbolsodiferenciado;
    }

    public String getTb_recargabolsodegrau2() {
        return tb_recargabolsodegrau2;
    }

    public void setTb_recargabolsodegrau2(String tb_recargabolsodegrau2) {
        this.tb_recargabolsodegrau2 = tb_recargabolsodegrau2;
    }

    public String getTb_bolsobonusdegrau2() {
        return tb_bolsobonusdegrau2;
    }

    public void setTb_bolsobonusdegrau2(String tb_bolsobonusdegrau2) {
        this.tb_bolsobonusdegrau2 = tb_bolsobonusdegrau2;
    }

    public String getTb_recargabolsodegrau3() {
        return tb_recargabolsodegrau3;
    }

    public void setTb_recargabolsodegrau3(String tb_recargabolsodegrau3) {
        this.tb_recargabolsodegrau3 = tb_recargabolsodegrau3;
    }

    public String getTb_bolsobonusdegrau3() {
        return tb_bolsobonusdegrau3;
    }

    public void setTb_bolsobonusdegrau3(String tb_bolsobonusdegrau3) {
        this.tb_bolsobonusdegrau3 = tb_bolsobonusdegrau3;
    }

    public boolean isTb_bonusligacaooimovel() {
        return tb_bonusligacaooimovel;
    }
    
    @JsonGetter("tb_bonusligacaooimovel")
    public String getTb_bonusligacaooimovelStr() {
        return tb_bonusligacaooimovel ? "S" : "N";
    }

    @JsonSetter("tb_bonusligacaooimovel")
    public void setTb_bonusligacaooimovel(String tb_bonusligacaooimovel) {
        this.tb_bonusligacaooimovel = "S".equals(tb_bonusligacaooimovel);
    }

    public void setTb_bonusligacaooimovel(boolean tb_bonusligacaooimovel) {
        this.tb_bonusligacaooimovel = tb_bonusligacaooimovel;
    }

    public boolean isTb_bonusligacaooimovelfixo() {
        return tb_bonusligacaooimovelfixo;
    }
    
    @JsonGetter("tb_bonusligacaooimovelfixo")
    public String getTb_bonusligacaooimovelfixoStr() {
        return tb_bonusligacaooimovelfixo ? "S" : "N";
    }

    @JsonSetter("tb_bonusligacaooimovelfixo")
    public void setTb_bonusligacaooimovelfixo(String tb_bonusligacaooimovelfixo) {
        this.tb_bonusligacaooimovelfixo = "S".equals(tb_bonusligacaooimovelfixo);
    }

    public void setTb_bonusligacaooimovelfixo(boolean tb_bonusligacaooimovelfixo) {
        this.tb_bonusligacaooimovelfixo = tb_bonusligacaooimovelfixo;
    }

    public boolean isTb_bonusligacaomix() {
        return tb_bonusligacaomix;
    }
    
    @JsonGetter("tb_bonusligacaomix")
    public String getTb_bonusligacaomixStr() {
        return tb_bonusligacaomix ? "S" : "N";
    }

    @JsonSetter("tb_bonusligacaomix")
    public void setTb_bonusligacaomix(String tb_bonusligacaomix) {
        this.tb_bonusligacaomix = "S".equals(tb_bonusligacaomix);
    }

    public void setTb_bonusligacaomix(boolean tb_bonusligacaomix) {
        this.tb_bonusligacaomix = tb_bonusligacaomix;
    }

    public boolean isTb_ligacaooimovelfixo_dados() {
        return tb_ligacaooimovelfixo_dados;
    }
    
    @JsonGetter("tb_ligacaooimovelfixo_dados")
    public String getTb_ligacaooimovelfixo_dadosStr() {
        return tb_ligacaooimovelfixo_dados ? "S" : "N";
    }

    @JsonSetter("tb_ligacaooimovelfixo_dados")
    public void setTb_ligacaooimovelfixo_dados(String tb_ligacaooimovelfixo_dados) {
        this.tb_ligacaooimovelfixo_dados = "S".equals(tb_ligacaooimovelfixo_dados);
    }

    public void setTb_ligacaooimovelfixo_dados(boolean tb_ligacaooimovelfixo_dados) {
        this.tb_ligacaooimovelfixo_dados = tb_ligacaooimovelfixo_dados;
    }

    public boolean isTb_bonusligacaomix_dados() {
        return tb_bonusligacaomix_dados;
    }
    
    @JsonGetter("tb_bonusligacaomix_dados")
    public String getTb_bonusligacaomix_dadosStr() {
        return tb_bonusligacaomix_dados ? "S" : "N";
    }

    @JsonSetter("tb_bonusligacaomix_dados")
    public void setTb_bonusligacaomix_dados(String tb_bonusligacaomix_dados) {
        this.tb_bonusligacaomix_dados = "S".equals(tb_bonusligacaomix_dados);
    }

    public void setTb_bonusligacaomix_dados(boolean tb_bonusligacaomix_dados) {
        this.tb_bonusligacaomix_dados = tb_bonusligacaomix_dados;
    }

    public boolean isTb_semcarencia() {
        return tb_semcarencia;
    }
    
    @JsonGetter("tb_semcarencia")
    public String getTb_semcarenciaStr() {
        return tb_semcarencia ? "S" : "N";
    }

    @JsonSetter("tb_semcarencia")
    public void setTb_semcarencia(String tb_semcarencia) {
        this.tb_semcarencia = "S".equals(tb_semcarencia);
    }

    public void setTb_semcarencia(boolean tb_semcarencia) {
        this.tb_semcarencia = tb_semcarencia;
    }

    public boolean isTem_bce() {
        return tem_bce;
    }
    
    @JsonGetter("tem_bce")
    public String getTem_bceStr() {
        return tem_bce ? "S" : "N";
    }

    @JsonSetter("tem_bce")
    public void setTem_bce(String tem_bce) {
        this.tem_bce = "S".equals(tem_bce);
    }

    public void setTem_bce(boolean tem_bce) {
        this.tem_bce = tem_bce;
    }

    public boolean isElegivel_promo_secundaria() {
        return elegivel_promo_secundaria;
    }
    
    @JsonGetter("elegivel_promo_secundaria")
    public String getElegivel_promo_secundariaStr() {
        return elegivel_promo_secundaria ? "S" : "N";
    }

    @JsonSetter("elegivel_promo_secundaria")
    public void setElegivel_promo_secundaria(String elegivel_promo_secundaria) {
        this.elegivel_promo_secundaria = "S".equals(elegivel_promo_secundaria);
    }

    public void setElegivel_promo_secundaria(boolean elegivel_promo_secundaria) {
        this.elegivel_promo_secundaria = elegivel_promo_secundaria;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getOfertar_sempromo() {
        return ofertar_sempromo;
    }

    public void setOfertar_sempromo(String ofertar_sempromo) {
        this.ofertar_sempromo = ofertar_sempromo;
    }

    public boolean isPre_contratacao() {
        return pre_contratacao;
    }
    
    @JsonGetter("pre_contratacao")
    public String getPre_contratacaoStr() {
        return pre_contratacao ? "S" : "N";
    }

    @JsonSetter("pre_contratacao")
    public void setPre_contratacao(String pre_contratacao) {
        this.pre_contratacao = "S".equals(pre_contratacao);
    }

    public void setPre_contratacao(boolean pre_contratacao) {
        this.pre_contratacao = pre_contratacao;
    }

    public String getCobrartaxa() {
        return cobrartaxa;
    }

    public void setCobrartaxa(String cobrartaxa) {
        this.cobrartaxa = cobrartaxa;
    }

    public boolean isTb_ussd_convertido() {
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

    public String getTb_categoria() {
        return tb_categoria;
    }

    public void setTb_categoria(String tb_categoria) {
        this.tb_categoria = tb_categoria;
    }

    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                PromocoesCartao[] pp1 = mapper.readValue(history, PromocoesCartao[].class);
                setHistory(Arrays.asList(mapper.readValue(history, PromocoesCartao[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (PromocoesCartao template : pp1) {
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
