/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.dbms.entity;

public enum Perfil {
    MARKETING(1,"Marketing"), 
    CLIENTE(5,"Cliente"), 
    TESTER(9,"Funcional"), 
    ADMIN(13,"Desenvolvedor"), 
    SUPER(17,"Administração");
    
    private final int id;
    private final String label;

    private Perfil(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Perfil getProfile(int profile) {
        switch(profile){
            case 1:
                return MARKETING;
            case 5:
                return CLIENTE;
            case 9:
                return TESTER;
            case 13:
                return ADMIN;
            case 17:
                return SUPER;
            default:
                return MARKETING;
        }
    }
}
