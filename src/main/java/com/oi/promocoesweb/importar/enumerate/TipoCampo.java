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
public enum TipoCampo {
    CHAVE("CHAVE"),
    COLUNA("COLUNA"),
    VALOR("VALOR"),
    LIMITE("LIMITE"),
    IGNORAR("IGNORAR");
    
    private String tipo;

    private TipoCampo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
