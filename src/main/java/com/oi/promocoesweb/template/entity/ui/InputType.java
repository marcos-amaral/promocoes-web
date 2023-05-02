/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.entity.ui;

/**
 *
 * @author mmouraam
 */
public enum InputType {
    STRING,
    BOOLEAN,
    DATE,
    MONEY,
    INTEGER,
    FRASE,
    WAV,
    AUTOCOMPLETE,
    DROPDOWN;

    @Override
    public String toString() {
        return this.name();
    }
    
    
}
