/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.importar.enumerate;

/**
 *
 * @author mmouraam
 */
public enum AbaTemplate {
    CONFIG_OFERTASPRE("CONFIG_OFERTASPRE"),
    PROMPTS_OFERTASPRE("PROMPTS_OFERTASPRE"),
    FRASEOLOGIAS_OFERTASPRE("FRASEOLOGIAS_OFERTASPRE"),
    INCENTIVO_OFERTASPRE("INCENTIVO_OFERTASPRE"),
    FRASES_CARTAO("FRASES_CARTAO"),
    VO_CARTAO("VO_CARTAO"),
    BO_CARTAO("BO_CARTAO"),
    VO_CONTROLE("VO_CONTROLE"),
    BO_CONTROLE("BO_CONTROLE"),
    FRASES_CONTROLE("FRASES_CONTROLE"),
    PROMPT_CARTAOMENU("PROMPT_CARTAOMENU"),
    PROMPT_CONTROLEMENU("PROMPT_CONTROLEMENU"),
    TB_CARTPROMOCOES("TB_CartPROMOCOES"),
    TB_CARTAOMENU("TB_CartaoMENU"),
    FRASES_PORTABILIDADE("FRASES_PORTABILIDADE"),
    IO_CARTAO_CARTAO("IO_CARTAO_CARTAO"),
    IO_CARTAO_CONTROLE("IO_CARTAO_CONTROLE"),
    IO_CONTROLE_CONTROLE("IO_CONTROLE_CONTROLE"),
    IO_CONTROLE_CONTROLE_S2S("IO_CONTROLE_CONTROLE_S2S"),
    IO_CARTAO_CARTAO_S2S("IO_CARTAO_CARTAO_S2S"),
    TB_OICPROMOCOES("TB_OICPromocoes"),
    TB_OICMENU("TB_OiCMENU");

    private String tabela = "";
    
    private AbaTemplate(String tabela) {
        this.tabela = tabela;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }
        
}
