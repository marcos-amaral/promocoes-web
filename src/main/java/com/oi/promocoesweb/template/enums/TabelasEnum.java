/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.enums;

/**
 *
 * @author mpma0
 */
public enum TabelasEnum {
    
    CONFIG_OFERTASPRE(
            TabelaConfigOfertasPre.SELECT,
            TabelaConfigOfertasPre.INSERT,
            TabelaConfigOfertasPre.UPDATE,
            TabelaConfigOfertasPre.UPDATEINSERT,
            TabelaConfigOfertasPre.DELETE,
            TabelaConfigOfertasPre.APROVAR),
    
    PROMPTS_OFERTASPRE(
            TabelaPromptsOfertasPre.SELECT,
            TabelaPromptsOfertasPre.INSERT,
            TabelaPromptsOfertasPre.UPDATE,
            TabelaPromptsOfertasPre.UPDATEINSERT,
            TabelaPromptsOfertasPre.DELETE,
            TabelaPromptsOfertasPre.APROVAR),
    
    FRASEOLOGIAS_OFERTASPRE(
            TabelaFraseologiasOfertasPre.SELECT,
            TabelaFraseologiasOfertasPre.INSERT,
            TabelaFraseologiasOfertasPre.UPDATE,
            TabelaFraseologiasOfertasPre.UPDATEINSERT,
            TabelaFraseologiasOfertasPre.DELETE,
            TabelaFraseologiasOfertasPre.APROVAR),
    
    INCENTIVO_OFERTASPRE(
            TabelaIncentivoOfertasPre.SELECT,
            TabelaIncentivoOfertasPre.INSERT,
            TabelaIncentivoOfertasPre.UPDATE,
            TabelaIncentivoOfertasPre.UPDATEINSERT,
            TabelaIncentivoOfertasPre.DELETE,
            TabelaIncentivoOfertasPre.APROVAR),
    
    INCENTIVO_OFERTASPRE_S2S(
            TabelaIncentivoOfertasPreS2S.SELECT,
            TabelaIncentivoOfertasPreS2S.INSERT,
            TabelaIncentivoOfertasPreS2S.UPDATE,
            TabelaIncentivoOfertasPreS2S.UPDATEINSERT,
            TabelaIncentivoOfertasPreS2S.DELETE,
            TabelaIncentivoOfertasPreS2S.APROVAR),
    
    INCENTIVOS_PORTABILIDADE(
            TabelaIncentivosPortabilidade.SELECT,
            TabelaIncentivosPortabilidade.INSERT,
            TabelaIncentivosPortabilidade.UPDATE,
            TabelaIncentivosPortabilidade.UPDATEINSERT,
            TabelaIncentivosPortabilidade.DELETE,
            TabelaIncentivosPortabilidade.APROVAR),
    
    INCENTIVOS_PORTABILIDADE_OCS(
            TabelaIncentivosPortabilidadeOcs.SELECT,
            TabelaIncentivosPortabilidadeOcs.INSERT,
            TabelaIncentivosPortabilidadeOcs.UPDATE,
            TabelaIncentivosPortabilidadeOcs.UPDATEINSERT,
            TabelaIncentivosPortabilidadeOcs.DELETE,
            TabelaIncentivosPortabilidadeOcs.APROVAR),
    
    FRASES_PORTABILIDADE(
            TabelaFrasesPortabilidade.SELECT,
            TabelaFrasesPortabilidade.INSERT,
            TabelaFrasesPortabilidade.UPDATE,
            TabelaFrasesPortabilidade.UPDATEINSERT,
            TabelaFrasesPortabilidade.DELETE,
            TabelaFrasesPortabilidade.APROVAR),
    
    FRASES_PORTABILIDADE_OCS(
            TabelaFrasesPortabilidadeOcs.SELECT,
            TabelaFrasesPortabilidadeOcs.INSERT,
            TabelaFrasesPortabilidadeOcs.UPDATE,
            TabelaFrasesPortabilidadeOcs.UPDATEINSERT,
            TabelaFrasesPortabilidadeOcs.DELETE,
            TabelaFrasesPortabilidadeOcs.APROVAR),
    
    OFERTAS_PORTABILIDADE(
            TabelaOfertasPortabilidade.SELECT,
            TabelaOfertasPortabilidade.INSERT,
            TabelaOfertasPortabilidade.UPDATE,
            TabelaOfertasPortabilidade.UPDATEINSERT,
            TabelaOfertasPortabilidade.DELETE,
            TabelaOfertasPortabilidade.APROVAR),
    
    OFERTAS_PORTABILIDADE_OCS(
            TabelaOfertasPortabilidadeOcs.SELECT,
            TabelaOfertasPortabilidadeOcs.INSERT,
            TabelaOfertasPortabilidadeOcs.UPDATE,
            TabelaOfertasPortabilidadeOcs.UPDATEINSERT,
            TabelaOfertasPortabilidadeOcs.DELETE,
            TabelaOfertasPortabilidadeOcs.APROVAR),
    
    
    VO_CARTAO(
            TabelaVoCartao.SELECT,
            TabelaVoCartao.INSERT,
            TabelaVoCartao.UPDATE,
            TabelaVoCartao.UPDATEINSERT,
            TabelaVoCartao.DELETE,
            TabelaVoCartao.APROVAR),
    
    BO_CARTAO(
            TabelaBoCartao.SELECT,
            TabelaBoCartao.INSERT,
            TabelaBoCartao.UPDATE,
            TabelaBoCartao.UPDATEINSERT,
            TabelaBoCartao.DELETE,
            TabelaBoCartao.APROVAR),
    
    FRASES_CARTAO(
            TabelaFrasesCartao.SELECT,
            TabelaFrasesCartao.INSERT,
            TabelaFrasesCartao.UPDATE,
            TabelaFrasesCartao.UPDATEINSERT,
            TabelaFrasesCartao.DELETE,
            TabelaFrasesCartao.APROVAR),
    
    IO_CARTAO_CARTAO(
            TabelaIoCartaoCartao.SELECT,
            TabelaIoCartaoCartao.INSERT,
            TabelaIoCartaoCartao.UPDATE,
            TabelaIoCartaoCartao.UPDATEINSERT,
            TabelaIoCartaoCartao.DELETE,
            TabelaIoCartaoCartao.APROVAR),
    IO_CARTAO_CARTAO_S2S(
            TabelaIoCartaoCartaoS2S.SELECT,
            TabelaIoCartaoCartaoS2S.INSERT,
            TabelaIoCartaoCartaoS2S.UPDATE,
            TabelaIoCartaoCartaoS2S.UPDATEINSERT,
            TabelaIoCartaoCartaoS2S.DELETE,
            TabelaIoCartaoCartaoS2S.APROVAR),
    
    IO_CARTAO_CONTROLE(
            TabelaIoCartaoControle.SELECT,
            TabelaIoCartaoControle.INSERT,
            TabelaIoCartaoControle.UPDATE,
            TabelaIoCartaoControle.UPDATEINSERT,
            TabelaIoCartaoControle.DELETE,
            TabelaIoCartaoControle.APROVAR),
    
    IO_CONTROLE_CONTROLE(
            TabelaIoControleControle.SELECT,
            TabelaIoControleControle.INSERT,
            TabelaIoControleControle.UPDATE,
            TabelaIoControleControle.UPDATEINSERT,
            TabelaIoControleControle.DELETE,
            TabelaIoControleControle.APROVAR),
    IO_CONTROLE_CONTROLE_S2S(
            TabelaIoControleControleS2S.SELECT,
            TabelaIoControleControleS2S.INSERT,
            TabelaIoControleControleS2S.UPDATE,
            TabelaIoControleControleS2S.UPDATEINSERT,
            TabelaIoControleControleS2S.DELETE,
            TabelaIoControleControleS2S.APROVAR),
    
    TB_OICMENU(
            TabelaOiCMenu.SELECT,
            TabelaOiCMenu.INSERT,
            TabelaOiCMenu.UPDATE,
            TabelaOiCMenu.UPDATEINSERT,
            TabelaOiCMenu.DELETE,
            TabelaOiCMenu.APROVAR),
    TB_OICPROMOCOES(
            TabelaOiCPromocoes.SELECT,
            TabelaOiCPromocoes.INSERT,
            TabelaOiCPromocoes.UPDATE,
            TabelaOiCPromocoes.UPDATEINSERT,
            TabelaOiCPromocoes.DELETE,
            TabelaOiCPromocoes.APROVAR),
    
    TB_TEMPLATE(
            TabelaTemplatePre.SELECT,
            TabelaTemplatePre.INSERT,
            TabelaTemplatePre.UPDATE,
            TabelaTemplatePre.UPDATEINSERT,
            TabelaTemplatePre.DELETE,
            TabelaTemplatePre.APROVAR),
    
    TB_TEMPLATE_CONTROLE(
            TabelaTemplateControle.SELECT,
            TabelaTemplateControle.INSERT,
            TabelaTemplateControle.UPDATE,
            TabelaTemplateControle.UPDATEINSERT,
            TabelaTemplateControle.DELETE,
            TabelaTemplateControle.APROVAR),
    
    TB_TEMPLATE_OCS(
            TabelaTemplatePreOcs.SELECT,
            TabelaTemplatePreOcs.INSERT,
            TabelaTemplatePreOcs.UPDATE,
            TabelaTemplatePreOcs.UPDATEINSERT,
            TabelaTemplatePreOcs.DELETE,
            TabelaTemplatePreOcs.APROVAR),
    
    FRASE_PRE_OCS(
            TabelaFrasePreOcs.SELECT,
            TabelaFrasePreOcs.INSERT,
            TabelaFrasePreOcs.UPDATE,
            TabelaFrasePreOcs.UPDATEINSERT,
            TabelaFrasePreOcs.DELETE,
            TabelaFrasePreOcs.APROVAR),
    
    FRASE_PRE(
            TabelaFrasePre.SELECT,
            TabelaFrasePre.INSERT,
            TabelaFrasePre.UPDATE,
            TabelaFrasePre.UPDATEINSERT,
            TabelaFrasePre.DELETE,
            TabelaFrasePre.APROVAR),
    
    FRASES_CONTROLE(
            TabelaFrasesControle.SELECT,
            TabelaFrasesControle.INSERT,
            TabelaFrasesControle.UPDATE,
            TabelaFrasesControle.UPDATEINSERT,
            TabelaFrasesControle.DELETE,
            TabelaFrasesControle.APROVAR),
    
    TB_CARTPROMOCOES(
            TabelaPromocoesCartao.SELECT,
            TabelaPromocoesCartao.INSERT,
            TabelaPromocoesCartao.UPDATE,
            TabelaPromocoesCartao.UPDATEINSERT,
            TabelaPromocoesCartao.DELETE,
            TabelaPromocoesCartao.APROVAR),
    
    TB_CARTAOMENU(
            TabelaCartaoMenu.SELECT,
            TabelaCartaoMenu.INSERT,
            TabelaCartaoMenu.UPDATE,
            TabelaCartaoMenu.UPDATEINSERT,
            TabelaCartaoMenu.DELETE,
            TabelaCartaoMenu.APROVAR),
    
    PROMPT_CARTAOMENU(
            TabelaPromptCartaoMenu.SELECT,
            TabelaPromptCartaoMenu.INSERT,
            TabelaPromptCartaoMenu.UPDATE,
            TabelaPromptCartaoMenu.UPDATEINSERT,
            TabelaPromptCartaoMenu.DELETE,
            TabelaPromptCartaoMenu.APROVAR),
    
    PROMPT_CONTROLEMENU(
            TabelaPromptControleMenu.SELECT,
            TabelaPromptControleMenu.INSERT,
            TabelaPromptControleMenu.UPDATE,
            TabelaPromptControleMenu.UPDATEINSERT,
            TabelaPromptControleMenu.DELETE,
            TabelaPromptControleMenu.APROVAR),
    
    VO_CONTROLE(
            TabelaVoControle.SELECT,
            TabelaVoControle.INSERT,
            TabelaVoControle.UPDATE,
            TabelaVoControle.UPDATEINSERT,
            TabelaVoControle.DELETE,
            TabelaVoControle.APROVAR),
    
    BO_CONTROLE(
            TabelaBoControle.SELECT,
            TabelaBoControle.INSERT,
            TabelaBoControle.UPDATE,
            TabelaBoControle.UPDATEINSERT,
            TabelaBoControle.DELETE,
            TabelaBoControle.APROVAR),
    
    FRASES_CONTROLE_CAD(
            TabelaFrasesControleCad.SELECT,
            TabelaFrasesControleCad.INSERT,
            TabelaFrasesControleCad.UPDATE,
            TabelaFrasesControleCad.UPDATEINSERT,
            TabelaFrasesControleCad.DELETE,
            TabelaFrasesControleCad.APROVAR),
    
    LISTA_PROMPTS(
            TabelaListaPrompts.SELECT,
            TabelaListaPrompts.INSERT,
            TabelaListaPrompts.UPDATE,
            TabelaListaPrompts.UPDATEINSERT,
            TabelaListaPrompts.DELETE,
            TabelaListaPrompts.APROVAR);
    
    private final String select;
    private final String insert;
    private final String update;
    private final String updateInsert;
    private final String delete;
    private final String aprovar;

    private TabelasEnum(String select, String insert, String update, String updateInsert, String delete, String aprovar) {
        this.select = select;
        this.insert = insert;
        this.update = update;
        this.updateInsert = updateInsert;
        this.delete = delete;
        this.aprovar = aprovar;
    }

    public String getSelect() {
        return select;
    }

    public String getInsert() {
        return insert;
    }

    public String getUpdate() {
        return update;
    }

    public String getUpdateInsert() {
        return updateInsert;
    }

    public String getDelete() {
        return delete;
    }

    public String getAprovar() {
        return aprovar;
    }
    
}
