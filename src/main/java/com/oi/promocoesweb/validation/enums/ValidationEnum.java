/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.validation.enums;

/**
 *
 * @author mpma0
 */
public enum ValidationEnum {
    VALOR_UNICO("VALOR ÚNICO"),
    SEQUENCIA_VALORES("SEQUÊNCIA DE VALORES"),
    CONTEUDO("CONTEÚDO MSG"),
    NOME_WAV("NOME WAV"),
    DATA("DATA"),
    MONETARIO("VALOR R$"),
    BOOLEANO("BOOLEANO");
    
    private String tipo;

    private ValidationEnum(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
