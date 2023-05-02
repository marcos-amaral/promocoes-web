/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.entity.wizard;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mmouraam
 */
public class GrupoDdd {
    private List<String> ddd = new ArrayList<>();
    private String[] aplicacao;

    public GrupoDdd() {
    }

    public GrupoDdd(List<String> ddd) {
        this.ddd = ddd;
    }
    
    public List<String> getDdd() {
        return ddd;
    }

    public void setDdd(List<String> ddd) {
        this.ddd = ddd;
    }

    public String[] getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(String[] aplicacao) {
        this.aplicacao = aplicacao;
    }
 
    public boolean is144(){
        if(aplicacao!=null){
            for (String app : aplicacao) {
                if("144".equals(app)) 
                    return true;
            }
        }
        return false;
    }
    
    public boolean isCadastro(){
        if(aplicacao!=null){
            for (String app : aplicacao) {
                if("CADASTRO".equals(app)) 
                    return true;
            }
        }
        return false;
    }
}
