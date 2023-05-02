/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.enums;

public enum StatusEnum {
    INICIADO("INICIADO"),
    VALIDADO("VALIDADO"),
    APROVADO("APROVADO"),
    EM_UAT("EM UAT"),
    EM_IMPLANTACAO("EM IMPLANTACAO"),
    IMPLANTAR("IMPLANTAR");
    
    private String tipo;

    private StatusEnum(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
