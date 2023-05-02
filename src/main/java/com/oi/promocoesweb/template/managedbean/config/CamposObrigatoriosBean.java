/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.managedbean.config;

import com.oi.promocoesweb.dbms.control.TemplateControl;
import com.oi.promocoesweb.template.enums.TabelasEnum;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mmouraam
 */
@Named
@ViewScoped
public class CamposObrigatoriosBean implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private Map<String, Boolean> map;
    
    @PostConstruct
    public void init() {
        logger.trace("============= CamposObrigatoriosBean init =============");
        map = new HashMap<>();
    }

    public void updateMap(String tabela){
        try {
            Map<String, Boolean> map = TemplateControl.getCamposObrigatorios(TabelasEnum.valueOf(tabela));
            
        } catch (SQLException e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public boolean isObrigatorio(String campo){
        return map.getOrDefault(campo, Boolean.FALSE);
    }
}
