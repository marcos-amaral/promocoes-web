/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.importar.entity.ui;

/**
 *
 * @author mpma0
 */
public class Prompt {
    
    private String wav = "";
    private String conteudo = "";

    public Prompt(String wav, String conteudo) {
        this.wav = wav;
        this.conteudo = conteudo;
    }

    public String getWav() {
        return wav;
    }

    public void setWav(String wav) {
        this.wav = wav;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    
}
