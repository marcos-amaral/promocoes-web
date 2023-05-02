/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.entity.aprovacao;

import com.oi.promocoesweb.dbms.entity.User;
import com.oi.promocoesweb.template.enums.StatusEnum;
import com.oi.promocoesweb.template.enums.TabelasEnum;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author mpma0
 */
public class Aprovacao {
    private int id;
    private User user;
    private Date dt_gmud;
    private Date dt_aprovacao;
    private TabelasEnum tabela;
    private StatusEnum status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDt_gmud() {
        return dt_gmud;
    }
    
    public String getDt_gmudStr() {
        if(dt_gmud!=null){
            return new SimpleDateFormat("dd/MM/yy").format(dt_gmud);
        }
        return "";
    }

    public void setDt_gmud(Date dt_gmud) {
        this.dt_gmud = dt_gmud;
    }

    public Date getDt_aprovacao() {
        return dt_aprovacao;
    }

    public void setDt_aprovacao(Date dt_aprovacao) {
        this.dt_aprovacao = dt_aprovacao;
    }

    public TabelasEnum getTabela() {
        return tabela;
    }

    public void setTabela(TabelasEnum tabela) {
        this.tabela = tabela;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Aprovacao{" + "id=" + id + ", user=" + user + ", dt_gmud=" + dt_gmud + ", dt_aprovacao=" + dt_aprovacao + ", tabela=" + tabela + ", status=" + status + '}';
    }
    
    
}
