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
 * @author jpereirc
 */
public class OiCPromocoes extends Template{
    
    private static final Logger logger = LogManager.getLogger();
    
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
    private String incentivo1;
    private String incentivo2;
    private String outrosplanos;
    private String offercode;
    private String tb_plano;
    private String tb_nome_promocao;
    private String tb_tipo_promo;
    private boolean tb_sn_elegivel_nova_promo;
    @FieldInput(name = InputType.MONEY)
    private String tb_taxa_adesao;
    private Date tb_dt_inicio_promo;
    private Date tb_dt_fim_promo;
    private String tb_tipo_bonus;
    @FieldInput(name = InputType.MONEY)
    private String tb_franquia;
    @FieldInput(name = InputType.MONEY)
    private String tb_bonus;
    private boolean tb_sn_bonus_torpedo;
    @FieldInput(name = InputType.INTEGER)
    private String tb_qtd_torp;
    @FieldInput(name = InputType.INTEGER)
    private String tb_bonus_torp;
    private boolean sn_oferta_sem_franquia;
    private boolean tb_ligacaooimovelfixo_dados;
    private boolean tb_bonusligacaomix_dados;
    private boolean tb_ussd_convertido = true;
    
    public OiCPromocoes(){
        super();
    }
    public OiCPromocoes(OiCPromocoes selectedConfigOfertasPre){
        this.tb_id_beneficio = selectedConfigOfertasPre.getTb_id_beneficio();
        this.tb_id_programa= selectedConfigOfertasPre.getTb_id_programa();
        this.tb_ddd= selectedConfigOfertasPre.getTb_ddd();
        this.tb_regiao= selectedConfigOfertasPre.getTb_regiao();
        this.incentivo1= selectedConfigOfertasPre.getIncentivo1();
        this.incentivo2= selectedConfigOfertasPre.getIncentivo2();
        this.outrosplanos = selectedConfigOfertasPre.getOutrosplanos();
        this.offercode= selectedConfigOfertasPre.getOffercode();
        this.tb_plano = selectedConfigOfertasPre.getTb_plano();
        this.tb_nome_promocao= selectedConfigOfertasPre.getTb_nome_promocao();
        this.tb_tipo_promo= selectedConfigOfertasPre.getTb_tipo_promo();
        this.tb_sn_elegivel_nova_promo = selectedConfigOfertasPre.getTb_sn_elegivel_nova_promo();
        this.tb_taxa_adesao= selectedConfigOfertasPre.getTb_taxa_adesao();
        this.tb_dt_inicio_promo= selectedConfigOfertasPre.getTb_dt_inicio_promo();
        this.tb_dt_fim_promo= selectedConfigOfertasPre.getTb_dt_fim_promo();
        this.tb_tipo_bonus= selectedConfigOfertasPre.getTb_tipo_bonus();
        this.tb_franquia= selectedConfigOfertasPre.getTb_franquia();
        this.tb_bonus= selectedConfigOfertasPre.getTb_bonus();
        this.tb_sn_bonus_torpedo= selectedConfigOfertasPre.getTb_sn_bonus_torpedo();
        this.tb_qtd_torp= selectedConfigOfertasPre.getTb_qtd_torp();
        this.tb_bonus_torp= selectedConfigOfertasPre.getTb_bonus_torp();
        this.sn_oferta_sem_franquia= selectedConfigOfertasPre.getSn_oferta_sem_franquia();
        this.tb_ligacaooimovelfixo_dados = selectedConfigOfertasPre.getTb_ligacaooimovelfixo_dados();
        this.tb_bonusligacaomix_dados = selectedConfigOfertasPre.getTb_bonusligacaomix_dados();
        this.tb_ussd_convertido = selectedConfigOfertasPre.getTb_ussd_convertido();
    }
   
    
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
    @JsonIgnore public boolean isEditIncentivo1() {return super.getEditMap().getOrDefault("INCENTIVO1", false);}
    @JsonIgnore public boolean isEditIncentivo2() {return super.getEditMap().getOrDefault("INCENTIVO2", false);}
    @JsonIgnore public boolean isEditOutrosplanos() {return super.getEditMap().getOrDefault("OUTROSPLANOS", false);}
    @JsonIgnore public boolean isEditOffercode() {return super.getEditMap().getOrDefault("OFFERCODE", false);}
    @JsonIgnore public boolean isEditTb_plano() {return super.getEditMap().getOrDefault("TB_PLANO", false);}
    @JsonIgnore public boolean isEditTb_nome_promocao() {return super.getEditMap().getOrDefault("TB_NOME_PROMOCAO", false);}
    @JsonIgnore public boolean isEditTb_tipo_promo() {return super.getEditMap().getOrDefault("TB_TIPO_PROMO", false);}
    @JsonIgnore public boolean isEditTb_sn_elegivel_nova_promo() {return super.getEditMap().getOrDefault("TB_SN_ELEGIVEL_NOVA_PROMO", false);}
    @JsonIgnore public boolean isEditTb_taxa_adesao() {return super.getEditMap().getOrDefault("TB_TAXA_ADESAO", false);}
    @JsonIgnore public boolean isEditTb_dt_inicio_promo() {return super.getEditMap().getOrDefault("TB_DT_INICIO_PROMO", false);}
    @JsonIgnore public boolean isEditTb_dt_fim_promo() {return super.getEditMap().getOrDefault("TB_DT_FIM_PROMO", false);}
    @JsonIgnore public boolean isEditTb_tipo_bonus() {return super.getEditMap().getOrDefault("TB_TIPO_BONUS", false);}
    @JsonIgnore public boolean isEditTb_franquia() {return super.getEditMap().getOrDefault("TB_FRANQUIA", false);}
    @JsonIgnore public boolean isEditTb_bonus() {return super.getEditMap().getOrDefault("TB_BONUS", false);}
    @JsonIgnore public boolean isEditTb_sn_bonus_torpedo() {return super.getEditMap().getOrDefault("TB_SN_BONUS_TORPEDO", false);}
    @JsonIgnore public boolean isEditTb_qtd_torp() {return super.getEditMap().getOrDefault("TB_QTD_TORP", false);}
    @JsonIgnore public boolean isEditTb_bonus_torp() {return super.getEditMap().getOrDefault("TB_BONUS_TORP", false);}
    @JsonIgnore public boolean isEditSn_oferta_sem_franquia() {return super.getEditMap().getOrDefault("SN_OFERTA_SEM_FRANQUIA", false);}
    @JsonIgnore public boolean isEditTb_ligacaooimovelfixo_dados() {return super.getEditMap().getOrDefault("TB_LIGACAOOIMOVELFIXO_DADOS", false);}
    @JsonIgnore public boolean isEditTb_bonusligacaomix_dados() {return super.getEditMap().getOrDefault("TB_BONUSLIGACAOMIX_DADOS", false);}
    @JsonIgnore public boolean isEditTb_ussd_convertido() {return super.getEditMap().getOrDefault("TB_USSD_CONVERTIDO", false);}
     
    //SetEdit
    public void setEditTb_id_beneficio(boolean checked) {super.getEditMap().put("TB_ID_BENEFICIO", checked);}
    public void setEditTb_id_programa(boolean checked) {super.getEditMap().put("TB_ID_PROGRAMA", checked);}
    public void setEditTb_ddd(boolean checked) {super.getEditMap().put("TB_DDD", checked);}
    public void setEditTb_regiao(boolean checked) {super.getEditMap().put("TB_REGIAO", checked);}
    public void setEditIncentivo1(boolean checked) {super.getEditMap().put("INCENTIVO1", checked);}
    public void setEditIncentivo2(boolean checked) {super.getEditMap().put("INCENTIVO2", checked);}
    public void setEditOutrosplanos(boolean checked) {super.getEditMap().put("OUTROSPLANOS", checked);}
    public void setEditOffercode(boolean checked) {super.getEditMap().put("OFFERCODE", checked);}
    public void setEditTb_plano(boolean checked) {super.getEditMap().put("TB_PLANO", checked);}
    public void setEditTb_nome_promocao(boolean checked) {super.getEditMap().put("TB_NOME_PROMOCAO", checked);}
    public void setEditTb_tipo_promo(boolean checked) {super.getEditMap().put("TB_TIPO_PROMO", checked);}
    public void setEditTb_sn_elegivel_nova_promo(boolean checked) {super.getEditMap().put("TB_SN_ELEGIVEL_NOVA_PROMO", checked);}
    public void setEditTb_taxa_adesao(boolean checked) {super.getEditMap().put("TB_TAXA_ADESAO", checked);}
    public void setEditTb_dt_inicio_promo(boolean checked) {super.getEditMap().put("TB_DT_INICIO_PROMO", checked);}
    public void setEditTb_dt_fim_promo(boolean checked) {super.getEditMap().put("TB_DT_FIM_PROMO", checked);}
    public void setEditTb_tipo_bonus(boolean checked) {super.getEditMap().put("TB_TIPO_BONUS", checked);}
    public void setEditTb_franquia(boolean checked) {super.getEditMap().put("TB_FRANQUIA", checked);}
    public void setEditTb_bonus(boolean checked) {super.getEditMap().put("TB_BONUS", checked);}
    public void setEditTb_sn_bonus_torpedo(boolean checked) {super.getEditMap().put("TB_SN_BONUS_TORPEDO", checked);}
    public void setEditTb_qtd_torp(boolean checked) {super.getEditMap().put("TB_QTD_TORP", checked);}
    public void setEditTb_bonus_torp(boolean checked) {super.getEditMap().put("TB_BONUS_TORP", checked);}
    public void setEditSn_oferta_sem_franquia(boolean checked) {super.getEditMap().put("SN_OFERTA_SEM_FRANQUIA", checked);}
    public void setEditTb_ligacaooimovelfixo_dados(boolean checked) {super.getEditMap().put("TB_LIGACAOOIMOVELFIXO_DADOS", checked);}
    public void setEditTb_bonusligacaomix_dados(boolean checked) {super.getEditMap().put("TB_BONUSLIGACAOMIX_DADOS", checked);}
    public void setEditTb_ussd_convertido(boolean checked) {super.getEditMap().put("TB_USSD_CONVERTIDO", checked);}
    
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

    public String getOffercode() {
        return offercode;
    }

    public void setOffercode(String offercode) {
        this.offercode = offercode;
    }

    public String getTb_plano() {
        return tb_plano;
    }

    public void setTb_plano(String tb_plano) {
        this.tb_plano = tb_plano;
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

    public boolean getTb_sn_elegivel_nova_promo() {
        return tb_sn_elegivel_nova_promo;
    }
    @JsonGetter("tb_sn_elegivel_nova_promo")
    public String getTb_sn_elegivel_nova_promoStr() {
        return tb_sn_elegivel_nova_promo ? "S" : "N";
    }

    @JsonSetter("tb_sn_elegivel_nova_promo")
    public void setTb_sn_elegivel_nova_promo(String elegivelconversao) {
        this.tb_sn_elegivel_nova_promo = "S".equals(elegivelconversao);
    }

    public void setTb_sn_elegivel_nova_promo(boolean tb_sn_elegivel_nova_promo) {
        this.tb_sn_elegivel_nova_promo = tb_sn_elegivel_nova_promo;
    }

    public String getTb_taxa_adesao() {
        return tb_taxa_adesao;
    }

    public void setTb_taxa_adesao(String tb_taxa_adesao) {
        this.tb_taxa_adesao = tb_taxa_adesao;
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
    public void setTb_dt_inicio_promoString(String tb_dt_inicio_promo) {
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

    public String getTb_franquia() {
        return tb_franquia;
    }

    public void setTb_franquia(String tb_franquia) {
        this.tb_franquia = tb_franquia;
    }

    public String getTb_bonus() {
        return tb_bonus;
    }

    public void setTb_bonus(String tb_bonus) {
        this.tb_bonus = tb_bonus;
    }

    public boolean getTb_sn_bonus_torpedo() {
        return tb_sn_bonus_torpedo;
    }
    
    @JsonGetter("tb_sn_bonus_torpedo")
    public String getTb_sn_bonus_torpedoStr() {
        return tb_sn_bonus_torpedo ? "S" : "N";
    }

    @JsonSetter("tb_sn_bonus_torpedo")
    public void setTb_sn_bonus_torpedo(String Tb_sn_bonus_torpedo) {
        this.tb_sn_bonus_torpedo = "S".equals(Tb_sn_bonus_torpedo);
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

    public boolean getSn_oferta_sem_franquia() {
        return sn_oferta_sem_franquia;
    }
    @JsonGetter("sn_oferta_sem_franquia")
    public String getSn_oferta_sem_franquiaStr() {
        return sn_oferta_sem_franquia ? "S" : "N";
    }

    @JsonSetter("sn_oferta_sem_franquia")
    public void setSn_oferta_sem_franquia(String sn_oferta_sem_franquia) {
        this.sn_oferta_sem_franquia = "S".equals(sn_oferta_sem_franquia);
    }

    public void setSn_oferta_sem_franquia(boolean sn_oferta_sem_franquia) {
        this.sn_oferta_sem_franquia = sn_oferta_sem_franquia;
    }

    public boolean getTb_ligacaooimovelfixo_dados() {
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

    public boolean getTb_bonusligacaomix_dados() {
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
    
    
    //Final
    @Override
    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                OiCPromocoes[] pp1 = mapper.readValue(history, OiCPromocoes[].class);
                setHistory(Arrays.asList(mapper.readValue(history, OiCPromocoes[].class)));

                Date tempDate = new Date(0);
                User tempUser = null;
                for (OiCPromocoes template : pp1) {
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
