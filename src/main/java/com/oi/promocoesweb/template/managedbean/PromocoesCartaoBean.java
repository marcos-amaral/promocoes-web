/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.managedbean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oi.promocoesweb.dbms.entity.User;
import static com.oi.promocoesweb.managedbean.ControleBean.AUTH_USER;
import com.oi.promocoesweb.template.entity.PromocoesCartao;
import com.oi.promocoesweb.template.entity.Template;
import com.oi.promocoesweb.template.entity.aprovacao.Aprovacao;
import com.oi.promocoesweb.template.enums.StatusEnum;
import com.oi.promocoesweb.template.enums.TabelasEnum;
import com.oi.promocoesweb.template.utils.JsonUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author mmouraam
 */
@Named
@ViewScoped
public class PromocoesCartaoBean extends TemplateBean<PromocoesCartao> implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private final TabelasEnum tabelasEnum = TabelasEnum.TB_CARTPROMOCOES;

    @PostConstruct
    public void init() {
        logger.trace("============= TemplateBean init =============");
        try {
            super.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new PromocoesCartao());
            super.clearVarsAdd(new PromocoesCartao());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void reloadTabela() {
        try {
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new PromocoesCartao());
            super.clearVarsAdd(new PromocoesCartao());

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void adicionar() {
        super.adicionar(tabelasEnum);
        super.clearVarsAdd(new PromocoesCartao());

        if (!isAdicionarOutra()) {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('sidebarAdd').hide();");
        }
    }

    public void editar() {
        try {
            Map<String, Boolean> collect = getEditConfigOfertasPre().getEditMap().entrySet().stream().filter(x -> x.getValue().equals(Boolean.TRUE)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            if (collect == null || collect.isEmpty()) {
                throw new Exception("Selecione pelo menos um checkbox para edição!");
            }

            getEditConfigOfertasPre().normalize();
            for (Template configOfertasPre : getSelectedConfigOfertasPreList()) {
                configOfertasPre.setHistory(JsonUtils.addToJsonArray(configOfertasPre.getDt_gmud() == null ? null : configOfertasPre.getHistory(), getEditConfigOfertasPre()));
                logger.trace(JsonUtils.toJson(configOfertasPre.getHistory()));
            }

            super.editar(tabelasEnum);
            
            super.clearVarsEdit(new PromocoesCartao());

        } catch (JsonProcessingException e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('sidebarEdit').hide();");
    }

    public void deletar() {
        super.deletar(tabelasEnum);

        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('sidebarDelete').hide();");
    }

    public void adiar() {
        super.adiar(tabelasEnum);
    }

    public void aprovar() {
        super.implantar(tabelasEnum);
    }

    public void aprovarGmud() {
        super.aprovarGmud(tabelasEnum);
    }

    public void iniciarGmud() {
        super.iniciarGmud(tabelasEnum);
    }

    public void validarGmud() {
        super.validarGmud(tabelasEnum);
    }

    public void uatGmud() {
        super.uatGmud(tabelasEnum);
    }

    public void implantarGmud() {
        super.implantarGmud(tabelasEnum);
    }

    public void preLoadEditBean() {
        if (getSelectedConfigOfertasPreList().size() == 1) {
            setEditTemplate(new PromocoesCartao(getSelectedConfigOfertasPreList().get(0)));
            getEditConfigOfertasPre().setUser(super.getUser());
            setNewTemplate(new PromocoesCartao(getSelectedConfigOfertasPreList().get(0)));
            getNewConfigOfertasPre().setUser(super.getUser());
        } else {
            setEditTemplate(new PromocoesCartao());
            getEditConfigOfertasPre().setUser(super.getUser());
            setNewTemplate(new PromocoesCartao());
            getNewConfigOfertasPre().setUser(super.getUser());
        }
        if (getSelectedConfigOfertasPreList().size() > 0) super.setGmudToDelete(getSelectedConfigOfertasPreList().get(0).getDt_gmud());
        else super.setGmudToDelete(null);
    }

    public void generateScriptFile(String aplicacao) {
        logger.trace("SCRIPT gmud:{}|{}", super.getDefaultFilterValue(), aplicacao);
        StringBuilder script = new StringBuilder();

        List<PromocoesCartao> list = null;

        list = getConfigOfertasPreList().stream().filter(c -> super.getDefaultFilterValue().equals(c.getDtGmudStr())).collect(Collectors.toList());

        if (list != null) {
            StringBuilder sb;

            InputStream inputStream = null;
            int i;
            for (PromocoesCartao registro : list) {
                sb = new StringBuilder();

                switch (aplicacao) {
                    case "USSD":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM ").append(tabelasEnum.name()).append(";\n");
                            script.append(sb.toString());

                        } else {
                            for (PromocoesCartao registroDel : list) {
                                sb.append("DELETE FROM ").append(tabelasEnum.name());

                                sb.append(" WHERE ");
                                sb.append("TB_ID_BENEFICIO = ").append(registroDel.getTb_id_beneficio()).append(" AND ");
                                sb.append("TB_ID_PROGRAMA = ").append(registroDel.getTb_id_programa()).append(" AND ");
                                sb.append("TB_DDD = '").append(registroDel.getTb_ddd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }

                        sb.append("INSERT INTO ").append(tabelasEnum.name());
                        sb.append(" (TB_ID_BENEFICIO,TB_ID_PROGRAMA,TB_ID_OFERTA,TB_DDD,TB_REGIAO,INCENTIVO1,INCENTIVO2,OUTROSPLANOS,OFERTAPROMO1,OFERTAPROMO2,TB_NOME_PROMOCAO,TB_TIPO_PROMO,TB_SN_ELEGIVEL_NOVA_PROMO,TB_TAXAADESAO,TB_SN_ELEGIVEL_ESTENDIDA,TB_DT_INICIO_PROMO,TB_DT_FIM_PROMO,TB_TIPO_BONUS,TB_RECARGA_MIN,TB_RECARGA_MAX,TB_BONUS_MIN,TB_BONUS_MAX,TB_MULTIPLICADOR,TB_SN_BONUS_TORPEDO,TB_QTD_TORP,TB_BONUS_TORP,TB_PRORROGACAO_6_MESES,TB_PRORROGACAO_12_MESES,TB_VALORTAXA_6_MESES,TB_VALORTAXA_12_MESES,TB_ID_BENEFICIO_6_MESES,TB_ID_PROGRAMA_6_MESES,TB_ID_BENEFICIO_12_MESES,TB_ID_PROGRAMA_12_MESES,TB_BONUSBOLSODIFERENCIADO,TB_RECARGABOLSODEGRAU2,TB_BOLSOBONUSDEGRAU2,TB_RECARGABOLSODEGRAU3,TB_BOLSOBONUSDEGRAU3,TB_BONUSLIGACAOOIMOVEL,TB_BONUSLIGACAOOIMOVELFIXO,TB_BONUSLIGACAOMIX,TB_LIGACAOOIMOVELFIXO_DADOS,TB_BONUSLIGACAOMIX_DADOS,TB_SEMCARENCIA,TEM_BCE,ELEGIVEL_PROMO_SECUNDARIA,PRIORIDADE,OFERTAR_SEMPROMO,PRE_CONTRATACAO,COBRARTAXA,TB_USSD_CONVERTIDO,TB_CATEGORIA) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16,VAR17,VAR18,VAR19,VAR20,VAR21,VAR22,VAR23,VAR24,VAR25,VAR26,VAR27,VAR28,VAR29,VAR30,VAR31,VAR32,VAR33,VAR34,VAR35,VAR36,VAR37,VAR38,VAR39,VAR40,VAR41,VAR42,VAR43,VAR44,VAR45,VAR46,VAR47,VAR48,VAR49,VAR50,VAR51,VAR52,VAR53);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_beneficio() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_programa() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_oferta() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_ddd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_regiao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIncentivo1() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIncentivo2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOutrosplanos() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertapromo1() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertapromo2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_nome_promocao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_tipo_promo() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_sn_elegivel_nova_promoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_taxaadesao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_sn_elegivel_estendidaStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_dt_inicio_promoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_dt_fim_promoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_tipo_bonus() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_recarga_min() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_recarga_max() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonus_min() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonus_max() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_multiplicador() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_sn_bonus_torpedoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_qtd_torp() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonus_torp() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_prorrogacao_6_mesesStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_prorrogacao_12_mesesStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_valortaxa_6_meses() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_valortaxa_12_meses() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_beneficio_6_meses() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_programa_6_meses() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_beneficio_12_meses() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_programa_12_meses() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonusbolsodiferenciadoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_recargabolsodegrau2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bolsobonusdegrau2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_recargabolsodegrau3() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bolsobonusdegrau3() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonusligacaooimovelStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonusligacaooimovelfixoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonusligacaomixStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_ligacaooimovelfixo_dadosStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonusligacaomix_dadosStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_semcarenciaStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTem_bceStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getElegivel_promo_secundariaStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getPrioridade() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertar_sempromo() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getPre_contratacaoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCobrartaxa() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_ussd_convertidoStr() + "',")
                                .replace("VAR" + (i++), "'" + registro.getTb_categoria() + "'")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .replace("TB_PRORROGACAO_6_MESES", "TB_PRORROGACAO_6MESES")
                                        .replace("TB_PRORROGACAO_12_MESES", "TB_PRORROGACAO_12MESES")
                                        .replace("TB_VALORTAXA_6_MESES", "TB_VALORTAXA6MESES")
                                        .replace("TB_VALORTAXA_12_MESES", "TB_VALORTAXA12MESES")
                                        .replace("PRE_CONTRATACAO", "PRE_CONTRATACO")
                                        .getBytes(Charset.forName("UTF-8")));

                        break;

                    case "144":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM TB_PROMOCOES_CARTAO;\n");
                            script.append(sb.toString());

                        } else {
                            for (PromocoesCartao registroDel : list) {
                                sb.append("DELETE FROM TB_PROMOCOES_CARTAO");

                                sb.append(" WHERE ");
                                sb.append("TB_ID_BENEFICIO = ").append(registroDel.getTb_id_beneficio()).append(" AND ");
                                sb.append("TB_ID_PROGRAMA = ").append(registroDel.getTb_id_programa()).append(" AND ");
                                sb.append("TB_DDD = '").append(registroDel.getTb_ddd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }

                        sb.append("INSERT INTO TB_PROMOCOES_CARTAO");
                        sb.append(" (TB_ID_BENEFICIO,TB_ID_PROGRAMA,TB_ID_OFERTA,TB_DDD,INCENTIVO1,INCENTIVO2,OUTROSPLANOS,OFERTAPROMO1,OFERTAPROMO2,TB_NOME_PROMOCAO,TB_TIPO_PROMO,TB_SN_ELEGIVEL_NOVA_PROMO,TB_TAXAADESAO,TB_SN_ELEGIVEL_ESTENDIDA,TB_DT_INICIO_PROMO,TB_DT_FIM_PROMO,TB_TIPO_BONUS,TB_RECARGA_MIN,TB_RECARGA_MAX,TB_BONUS_MIN,TB_BONUS_MAX,TB_MULTIPLICADOR,TB_SN_BONUS_TORPEDO,TB_QTD_TORP,TB_BONUS_TORP,TB_PRORROGACAO_6_MESES,TB_PRORROGACAO_12_MESES,TB_VALORTAXA_6_MESES,TB_VALORTAXA_12_MESES,TB_ID_BENEFICIO_6_MESES,TB_ID_PROGRAMA_6_MESES,TB_ID_BENEFICIO_12_MESES,TB_ID_PROGRAMA_12_MESES,TB_BONUSBOLSODIFERENCIADO,TB_RECARGABOLSODEGRAU2,TB_BOLSOBONUSDEGRAU2,TB_RECARGABOLSODEGRAU3,TB_BOLSOBONUSDEGRAU3,TB_BONUSLIGACAOOIMOVEL,TB_BONUSLIGACAOOIMOVELFIXO,TB_BONUSLIGACAOMIX,TB_LIGACAOOIMOVELFIXO_DADOS,TB_BONUSLIGACAOMIX_DADOS,TB_SEMCARENCIA,TEM_BCE,PRIORIDADE,OFERTAR_SEMPROMO,COBRARTAXA,TB_CATEGORIA) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16,VAR17,VAR18,VAR19,VAR20,VAR21,VAR22,VAR23,VAR24,VAR25,VAR26,VAR27,VAR28,VAR29,VAR30,VAR31,VAR32,VAR33,VAR34,VAR35,VAR36,VAR37,VAR38,VAR39,VAR40,VAR41,VAR42,VAR43,VAR44,VAR45,VAR46,VAR47,VAR48,VAR49);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_beneficio() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_programa() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_oferta() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_ddd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIncentivo1() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIncentivo2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOutrosplanos() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertapromo1() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertapromo2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_nome_promocao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_tipo_promo() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_sn_elegivel_nova_promoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_taxaadesao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_sn_elegivel_estendidaStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_dt_inicio_promoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_dt_fim_promoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_tipo_bonus() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_recarga_min() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_recarga_max() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonus_min() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonus_max() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_multiplicador() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_sn_bonus_torpedoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_qtd_torp() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonus_torp() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_prorrogacao_6_mesesStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_prorrogacao_12_mesesStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_valortaxa_6_meses() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_valortaxa_12_meses() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_beneficio_6_meses() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_programa_6_meses() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_beneficio_12_meses() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_programa_12_meses() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonusbolsodiferenciadoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_recargabolsodegrau2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bolsobonusdegrau2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_recargabolsodegrau3() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bolsobonusdegrau3() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonusligacaooimovelStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonusligacaooimovelfixoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonusligacaomixStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_ligacaooimovelfixo_dadosStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonusligacaomix_dadosStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_semcarenciaStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTem_bceStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getPrioridade() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertar_sempromo() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCobrartaxa() + "',")
                                .replace("VAR" + (i++), "'" + registro.getTb_categoria() + "'")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .replace("TB_ID_BENEFICIO", "IDBENEFICIO")
                                        .replace("TB_ID_PROGRAMA", "IDPROGRAMA")
                                        .replace("TB_ID_OFERTA", "IDOFERTA")
                                        .replace("TB_DDD", "DDD")
                                        .replace("TB_NOME_PROMOCAO", "NOME_PROMOCAO")
                                        .replace("TB_TIPO_PROMO", "TIPO_PROMO")
                                        .replace("TB_SN_ELEGIVEL_NOVA_PROMO", "SN_ELEGIVEL_NOVA_PROMO")
                                        .replace("TB_TAXAADESAO", "TAXAADESAO")
                                        .replace("TB_SN_ELEGIVEL_ESTENDIDA", "SN_ELEGIVEL_ESTENDIDA")
                                        .replace("TB_DT_INICIO_PROMO", "DT_INICIO_PROMO")
                                        .replace("TB_DT_FIM_PROMO", "DT_FIM_PROMO")
                                        .replace("TB_TIPO_BONUS", "TIPO_BONUS")
                                        .replace("TB_RECARGA_MIN", "RECARGA_MIN")
                                        .replace("TB_RECARGA_MAX", "RECARGA_MAX")
                                        .replace("TB_BONUS_MIN", "BONUS_MIN")
                                        .replace("TB_BONUS_MAX", "BONUS_MAX")
                                        .replace("TB_MULTIPLICADOR", "MULTIPLICADOR")
                                        .replace("TB_SN_BONUS_TORPEDO", "SN_BONUS_TORPEDO")
                                        .replace("TB_QTD_TORP", "QTD_TORP")
                                        .replace("TB_BONUS_TORP", "BONUS_TORP")
                                        .replace("TB_PRORROGACAO_6_MESES", "PRORROGACAO_6MESES")
                                        .replace("TB_PRORROGACAO_12_MESES", "PRORROGACAO_12MESES")
                                        .replace("TB_VALORTAXA_6_MESES", "VALORTAXA6MESES")
                                        .replace("TB_VALORTAXA_12_MESES", "VALORTAXA12MESES")
                                        .replace("TB_ID_BENEFICIO_6_MESES", "ID_BENEFICIO_6MESES")
                                        .replace("TB_ID_PROGRAMA_6_MESES", "ID_PROGRAMA_6MESES")
                                        .replace("TB_ID_BENEFICIO_12_MESES", "ID_BENEFICIO_12MESES")
                                        .replace("TB_ID_PROGRAMA_12_MESES", "ID_PROGRAMA_12MESES")
                                        .replace("TB_BONUSBOLSODIFERENCIADO", "BONUSBOLSODIFERENCIADO")
                                        .replace("TB_RECARGABOLSODEGRAU2", "RECARGABOLSODEGRAU2")
                                        .replace("TB_BOLSOBONUSDEGRAU2", "BOLSOBONUSDEGRAU2")
                                        .replace("TB_RECARGABOLSODEGRAU3", "RECARGABOLSODEGRAU3")
                                        .replace("TB_BOLSOBONUSDEGRAU3", "BOLSOBONUSDEGRAU3")
                                        .replace("TB_BONUSLIGACAOOIMOVEL", "BONUSLIGACAOOIMOVEL")
                                        .replace("TB_BONUSLIGACAOOIMOVELFIXO", "BONUSLIGACAOOIMOVELFIXO")
                                        .replace("TB_BONUSLIGACAOMIX", "BONUSLIGACAOMIX")
                                        .replace("TB_LIGACAOOIMOVELFIXO_DADOS", "LIGACAOOIMOVELFIXO_DADOS")
                                        .replace("TB_BONUSLIGACAOMIX_DADOS", "BONUSLIGACAOMIX_DADOS")
                                        .replace("TB_SEMCARENCIA", "SEMCARENCIA")
                                        .replace("TEM_BCE", "BOLSOBCE")
                                        .replace("TB_CATEGORIA", "CATEGORIA")
                                        .getBytes(Charset.forName("UTF-8")));

                        break;

                    case "880":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM PROMOCOES_CARTAO;\n");
                            script.append(sb.toString());

                        } else {
                            for (PromocoesCartao registroDel : list) {
                                sb.append("DELETE FROM PROMOCOES_CARTAO");

                                sb.append(" WHERE ");
                                sb.append("TB_ID_BENEFICIO = ").append(registroDel.getTb_id_beneficio()).append(" AND ");
                                sb.append("TB_ID_PROGRAMA = ").append(registroDel.getTb_id_programa()).append(" AND ");
                                sb.append("TB_DDD = '").append(registroDel.getTb_ddd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }

                        sb.append("INSERT INTO PROMOCOES_CARTAO");
                        sb.append(" (TB_ID_BENEFICIO,TB_ID_PROGRAMA,TB_ID_OFERTA,TB_DDD,INCENTIVO1,INCENTIVO2,OUTROSPLANOS,OFERTAPROMO1,OFERTAPROMO2,TB_NOME_PROMOCAO,TB_TIPO_PROMO,TB_SN_ELEGIVEL_NOVA_PROMO,TB_TAXAADESAO,TB_SN_ELEGIVEL_ESTENDIDA,TB_DT_INICIO_PROMO,TB_DT_FIM_PROMO,TB_TIPO_BONUS,TB_RECARGA_MIN,TB_RECARGA_MAX,TB_BONUS_MIN,TB_BONUS_MAX,TB_MULTIPLICADOR,TB_SN_BONUS_TORPEDO,TB_QTD_TORP,TB_BONUS_TORP,TB_PRORROGACAO_6_MESES,TB_PRORROGACAO_12_MESES,TB_VALORTAXA_6_MESES,TB_VALORTAXA_12_MESES,TB_ID_BENEFICIO_6_MESES,TB_ID_PROGRAMA_6_MESES,TB_ID_BENEFICIO_12_MESES,TB_ID_PROGRAMA_12_MESES,TB_BONUSBOLSODIFERENCIADO,TB_RECARGABOLSODEGRAU2,TB_BOLSOBONUSDEGRAU2,TB_RECARGABOLSODEGRAU3,TB_BOLSOBONUSDEGRAU3,TB_BONUSLIGACAOOIMOVEL,TB_BONUSLIGACAOOIMOVELFIXO,TB_BONUSLIGACAOMIX,TB_LIGACAOOIMOVELFIXO_DADOS,TB_BONUSLIGACAOMIX_DADOS,TB_SEMCARENCIA,TEM_BCE,ELEGIVEL_PROMO_SECUNDARIA,PRIORIDADE,OFERTAR_SEMPROMO,PRE_CONTRATACAO,COBRARTAXA,TB_CATEGORIA) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16,VAR17,VAR18,VAR19,VAR20,VAR21,VAR22,VAR23,VAR24,VAR25,VAR26,VAR27,VAR28,VAR29,VAR30,VAR31,VAR32,VAR33,VAR34,VAR35,VAR36,VAR37,VAR38,VAR39,VAR40,VAR41,VAR42,VAR43,VAR44,VAR45,VAR46,VAR47,VAR48,VAR49,VAR50,VAR51);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_beneficio() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_programa() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_oferta() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_ddd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIncentivo1() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIncentivo2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOutrosplanos() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertapromo1() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertapromo2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_nome_promocao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_tipo_promo() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_sn_elegivel_nova_promoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_taxaadesao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_sn_elegivel_estendidaStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_dt_inicio_promoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_dt_fim_promoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_tipo_bonus() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_recarga_min() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_recarga_max() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonus_min() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonus_max() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_multiplicador() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_sn_bonus_torpedoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_qtd_torp() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonus_torp() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_prorrogacao_6_mesesStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_prorrogacao_12_mesesStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_valortaxa_6_meses() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_valortaxa_12_meses() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_beneficio_6_meses() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_programa_6_meses() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_beneficio_12_meses() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_id_programa_12_meses() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonusbolsodiferenciadoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_recargabolsodegrau2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bolsobonusdegrau2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_recargabolsodegrau3() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bolsobonusdegrau3() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonusligacaooimovelStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonusligacaooimovelfixoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonusligacaomixStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_ligacaooimovelfixo_dadosStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_bonusligacaomix_dadosStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_semcarenciaStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTem_bceStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getElegivel_promo_secundariaStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getPrioridade() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertar_sempromo() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getPre_contratacaoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCobrartaxa() + "',")
                                .replace("VAR" + (i++), "'" + registro.getTb_categoria() + "'")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .replace("TB_ID_BENEFICIO", "IDBENEFICIO")
                                        .replace("TB_ID_PROGRAMA", "IDPROGRAMA")
                                        .replace("TB_ID_OFERTA", "IDOFERTA")
                                        .replace("TB_DDD", "DDD")
                                        .replace("TB_NOME_PROMOCAO", "NOME_PROMOCAO")
                                        .replace("TB_TIPO_PROMO", "TIPO_PROMO")
                                        .replace("TB_SN_ELEGIVEL_NOVA_PROMO", "SN_ELEGIVEL_NOVA_PROMO")
                                        .replace("TB_TAXAADESAO", "TAXAADESAO")
                                        .replace("TB_SN_ELEGIVEL_ESTENDIDA", "SN_ELEGIVEL_ESTENDIDA")
                                        .replace("TB_DT_INICIO_PROMO", "DT_INICIO_PROMO")
                                        .replace("TB_DT_FIM_PROMO", "DT_FIM_PROMO")
                                        .replace("TB_TIPO_BONUS", "TIPO_BONUS")
                                        .replace("TB_RECARGA_MIN", "RECARGA_MIN")
                                        .replace("TB_RECARGA_MAX", "RECARGA_MAX")
                                        .replace("TB_BONUS_MIN", "BONUS_MIN")
                                        .replace("TB_BONUS_MAX", "BONUS_MAX")
                                        .replace("TB_MULTIPLICADOR", "MULTIPLICADOR")
                                        .replace("TB_SN_BONUS_TORPEDO", "SN_BONUS_TORPEDO")
                                        .replace("TB_QTD_TORP", "QTD_TORP")
                                        .replace("TB_BONUS_TORP", "BONUS_TORP")
                                        .replace("TB_PRORROGACAO_6_MESES", "PRORROGACAO_6MESES")
                                        .replace("TB_PRORROGACAO_12_MESES", "PRORROGACAO_12MESES")
                                        .replace("TB_VALORTAXA_6_MESES", "VALORTAXA6MESES")
                                        .replace("TB_VALORTAXA_12_MESES", "VALORTAXA12MESES")
                                        .replace("TB_ID_BENEFICIO_6_MESES", "ID_BENEFICIO_6MESES")
                                        .replace("TB_ID_PROGRAMA_6_MESES", "ID_PROGRAMA_6MESES")
                                        .replace("TB_ID_BENEFICIO_12_MESES", "ID_BENEFICIO_12MESES")
                                        .replace("TB_ID_PROGRAMA_12_MESES", "ID_PROGRAMA_12MESES")
                                        .replace("TB_BONUSBOLSODIFERENCIADO", "BONUSBOLSODIFERENCIADO")
                                        .replace("TB_RECARGABOLSODEGRAU2", "RECARGABOLSODEGRAU2")
                                        .replace("TB_BOLSOBONUSDEGRAU2", "BOLSOBONUSDEGRAU2")
                                        .replace("TB_RECARGABOLSODEGRAU3", "RECARGABOLSODEGRAU3")
                                        .replace("TB_BOLSOBONUSDEGRAU3", "BOLSOBONUSDEGRAU3")
                                        .replace("TB_BONUSLIGACAOOIMOVEL", "BONUSLIGACAOOIMOVEL")
                                        .replace("TB_BONUSLIGACAOOIMOVELFIXO", "BONUSLIGACAOOIMOVELFIXO")
                                        .replace("TB_BONUSLIGACAOMIX", "BONUSLIGACAOMIX")
                                        .replace("TB_LIGACAOOIMOVELFIXO_DADOS", "LIGACAOOIMOVELFIXO_DADOS")
                                        .replace("TB_BONUSLIGACAOMIX_DADOS", "BONUSLIGACAOMIX_DADOS")
                                        .replace("TB_SEMCARENCIA", "SEMCARENCIA")
                                        .replace("TEM_BCE", "BONUS_BCE")
                                        .replace("ELEGIVEL_PROMO_SECUNDARIA", "ELEGIVEL_PROMO_TEMPLATE_SECUNDARIA")
                                        .replace("PRE_CONTRATACAO", "PRECONTRATACAO")
                                        .getBytes(Charset.forName("UTF-8")));

                        break;
                }
            }

            DefaultStreamedContent defaultStreamedContent = new DefaultStreamedContent();
            defaultStreamedContent.setStream(inputStream);
            defaultStreamedContent.setName("promocoes_cartao.sql");
            defaultStreamedContent.setContentType("text/plain");

            super.setScriptFile(defaultStreamedContent);

        }
    }
    
    @Override
    public List<PromocoesCartao> getTemplateList() {
        return (List<PromocoesCartao>) (Object) super.getTemplateList();
    }

    public List<PromocoesCartao> getConfigOfertasPreList() {
        return super.getTemplateList();
    }

    public List<PromocoesCartao> getSelectedConfigOfertasPreList() {
        return super.getSelectedTemplateList();
    }

    public PromocoesCartao getNewConfigOfertasPre() {
        return super.getNewTemplate();
    }

    public PromocoesCartao getEditConfigOfertasPre() {
        return super.getEditTemplate();
    }

    public void setConfigOfertasPreList(List<PromocoesCartao> configOfertasPreList) {
        super.setTemplateList(configOfertasPreList);
    }

    public void setSelectedConfigOfertasPreList(List<PromocoesCartao> selectedConfigOfertasPreList) {
        super.setSelectedTemplateList(selectedConfigOfertasPreList);
    }

    public void setNewConfigOfertasPre(PromocoesCartao newConfigOfertasPre) {
        super.setNewTemplate(newConfigOfertasPre);
    }

    public void setEditConfigOfertasPre(PromocoesCartao editConfigOfertasPre) {
        super.setEditTemplate(editConfigOfertasPre);
    }

    public List<PromocoesCartao> getSelectedConfigOfertasPreHistoryList() {
        if (super.getSelectedTemplateList() != null && super.getSelectedTemplateList().size() > 0) {
            List<PromocoesCartao> history = (List<PromocoesCartao>) (Object) super.getSelectedTemplateList().get(0).getHistory();

            for (PromocoesCartao bean : history) {
                List<Aprovacao> collect = getAprovacaoList().stream().filter(x -> getDefaultFilterValue().equals(x.getDt_gmudStr())).collect(Collectors.toList());
                    if (collect != null && !collect.isEmpty()) {
                        StatusEnum statusTemp = StatusEnum.INICIADO;
                        for (Aprovacao aprovacao : collect) {
                            if(aprovacao.getDt_aprovacao().after(bean.getModified())){
                                switch(statusTemp){
                                    case EM_IMPLANTACAO:
                                    case IMPLANTAR:
                                        break;
                                    case EM_UAT:
                                        if(aprovacao.getStatus()==StatusEnum.EM_IMPLANTACAO || aprovacao.getStatus()==StatusEnum.IMPLANTAR){
                                            statusTemp = aprovacao.getStatus();
                                        }
                                        break;
                                    case APROVADO:
                                        if(aprovacao.getStatus()==StatusEnum.EM_IMPLANTACAO || aprovacao.getStatus()==StatusEnum.IMPLANTAR
                                                || aprovacao.getStatus()==StatusEnum.EM_UAT){
                                            statusTemp = aprovacao.getStatus();
                                        }
                                        break;
                                    case VALIDADO:
                                        if(aprovacao.getStatus()==StatusEnum.EM_IMPLANTACAO || aprovacao.getStatus()==StatusEnum.IMPLANTAR
                                                || aprovacao.getStatus()==StatusEnum.EM_UAT || aprovacao.getStatus()==StatusEnum.APROVADO){
                                            statusTemp = aprovacao.getStatus();
                                        }
                                        break;
                                    case INICIADO:
                                        statusTemp = aprovacao.getStatus();
                                        break;
                                }
                            }
                        }
                        bean.setLastStatus(statusTemp);
                    }
            }
            
            return history;
        } else {
            return new ArrayList<>();
        }
    }

    public LazyDataModel<PromocoesCartao> getDataModel() {
        return (LazyDataModel<PromocoesCartao>) (Object) super.getDataModel();
    }
}
