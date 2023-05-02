/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.managedbean.wizard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oi.promocoesweb.dbms.control.TemplateControl;
import com.oi.promocoesweb.dbms.entity.User;
import static com.oi.promocoesweb.managedbean.ControleBean.AUTH_USER;
import com.oi.promocoesweb.template.entity.Template;
import com.oi.promocoesweb.template.enums.TabelasEnum;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author mmouraam
 */
@Named
@ViewScoped
public class AddPromoPortabilidadeBean implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private String[] TODOS_DDDs = {"11", "12", "13", "14", "15", "16", "17", "18", "19", "21", "22", "24", "27", "28", "31", "32", "33", "34", "35", "37", "38", "41", "42", "43", "44", "45", "46", "47", "48", "49", "51", "53", "54", "55", "61", "62", "63", "64", "65", "66", "67", "68", "69", "71", "73", "74", "75", "77", "79", "81", "82", "83", "84", "85", "86", "87", "88", "89", "91", "92", "93", "94", "95", "96", "97", "98", "99"};

    private Date dt_gmud;
    private String id_ofertaocs;
    private String id_beneficio;
    private String id_programa;
    private List<List<String>> ddds;
    private List<List<String>> dddsFrases;
    private DualListModel<String> allDdds;
    private DualListModel<String> allDddsFrases;
    private String tipoPlano;
    private String tarifador;

    private List<String> currentDddsFrases;

    private int currentDddGroupIndex = -1;
    private boolean finalizado = false;

    @PostConstruct
    public void init() {
        logger.trace("============= AddPromoBean init =============");

        initialize();
    }
    
    private void initialize(){
        ddds = new ArrayList<>();
        dddsFrases = new ArrayList<>();

        List<String> dddsSource = new ArrayList<String>();
        List<String> dddsTarget = new ArrayList<String>();

        dddsSource.addAll(Arrays.asList(TODOS_DDDs));

        allDdds = new DualListModel<>(dddsSource, dddsTarget);
        allDddsFrases = new DualListModel<>(dddsSource, dddsTarget);
    }
    
    public void reset(){
        initialize();
        
        dt_gmud = null;
        id_ofertaocs = null;
        id_beneficio = null;
        id_programa = null;
        
        finalizado = false;
    }

    public String onFlowProcess(FlowEvent event) {
        if(finalizado){
            reset();
            finalizado = false;
            return "tabIds";
        }
        
        switch(event.getNewStep()){
            case "tabDados":
                if(ddds.isEmpty()){
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "Você não adicionou nenhum grupo de DDD");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    
                    return "tabDDDs";
                }
                break;
                
        }
        
        return event.getNewStep();
    }

    public void addGrupoDdd() {
        if (allDdds != null && allDdds.getTarget() != null) {
            ddds.add(allDdds.getTarget());
            allDdds.setTarget(new ArrayList<String>());
        }
        dddsFrases.clear();
        dddsFrases.addAll(ddds);
    }

    public void addDdd(SelectEvent evt) {
        if (evt.getObject() != null && allDdds.getSource() != null && allDdds.getSource().contains(String.valueOf(evt.getObject()))) {
            ddds.get(currentDddGroupIndex).add(String.valueOf(evt.getObject()));
            allDdds.getSource().remove(String.valueOf(evt.getObject()));
        }
        dddsFrases.clear();
        dddsFrases.addAll(ddds);
    }

    public void removeGrupoDdd(int index) {
        allDdds.getSource().addAll(ddds.get(index));
        Collections.sort(allDdds.getSource());
        ddds.remove(index);

        dddsFrases.clear();
        dddsFrases.addAll(ddds);
    }

    public void removeDdd(UnselectEvent evt) {
        for (int i = 0; i < ddds.size(); i++) {
            if (ddds.get(i).contains(String.valueOf(evt.getObject()))) {
                ddds.get(i).remove(String.valueOf(evt.getObject()));

                if (ddds.get(i).isEmpty()) {
                    ddds.remove(i);
                    break;
                }
            }
        }
        allDdds.getSource().add(String.valueOf(evt.getObject()));
        Collections.sort(allDdds.getSource());

        dddsFrases.clear();
        dddsFrases.addAll(ddds);
    }

    public void loadPickListDddFrases(List<String> list) {
        allDddsFrases.getSource().clear();
        for (List<String> ddd : ddds) {
            for (String string : ddd) {
                allDddsFrases.getSource().add(string);
            }
        }
        allDddsFrases.getTarget().clear();

        if (list != null) {
            currentDddsFrases = new ArrayList<>(list);

            for (String string : list) {
                allDddsFrases.getTarget().add(string);
            }
        } else {
            currentDddsFrases = null;
        }

        for (List<String> dddsFrase : dddsFrases) {
            for (String string : dddsFrase) {
                allDddsFrases.getSource().remove(string);
            }
        }
    }

    public void deleteDddsFrases(List<String> list) {
        dddsFrases.remove(list);
    }

    public void confirmDddsFrases() {
        if (currentDddsFrases == null || currentDddsFrases.isEmpty()) {
            dddsFrases.add(new ArrayList<>(allDddsFrases.getTarget()));
            dddsFrases.get(dddsFrases.size() - 1).sort(null);
        } else {
            dddsFrases.set(dddsFrases.indexOf(currentDddsFrases), new ArrayList<>(allDddsFrases.getTarget()));
            dddsFrases.get(dddsFrases.indexOf(allDddsFrases.getTarget())).sort(null);
        }

    }

    public void onTabClose(TabCloseEvent event) {
        dddsFrases.remove(event.getData());
    }
    
    public void adicionar(TabelasEnum tabelasEnumDados, List<Template> listDados, TabelasEnum tabelasEnumFrases, List<Template> listFrases) {
        try {
            String msg ="";
            
            for (Template t : listDados) {
                t.setModified(new Date());
                t.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
                t.normalize();
            }
            msg+=tabelasEnumDados+"\n";
            
            for (Template t : listFrases) {
                t.setModified(new Date());
                t.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
                t.normalize();
            }
            msg+=tabelasEnumFrases+"\n";

            Map<TabelasEnum,List<Template>> map = new HashMap<>();
            map.put(tabelasEnumDados, listDados);
            map.put(tabelasEnumFrases, listFrases);
            TemplateControl.insert(map);

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Promoção adicionada!", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);

        } catch (JsonProcessingException e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
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

    public Date getDt_gmud() {
        return dt_gmud;
    }

    public void setDt_gmud(Date dt_gmud) {
        this.dt_gmud = dt_gmud;
    }

    public String getId_ofertaocs() {
        return id_ofertaocs;
    }

    public void setId_ofertaocs(String id_ofertaocs) {
        this.id_ofertaocs = id_ofertaocs;
    }

    public String getId_beneficio() {
        return id_beneficio;
    }

    public void setId_beneficio(String id_beneficio) {
        this.id_beneficio = id_beneficio;
    }

    public String getId_programa() {
        return id_programa;
    }

    public void setId_programa(String id_programa) {
        this.id_programa = id_programa;
    }

    public List<List<String>> getDdds() {
        return ddds;
    }

    public String getDddsString(int index) {
        return ddds.get(index).toString().replace("[", "").replace("]", "").replace(" ", "");
    }

    public void setDdds(List<List<String>> ddds) {
        this.ddds = ddds;
    }

    public String getTipoPlano() {
        return tipoPlano;
    }

    public void setTipoPlano(String tipoPlano) {
        this.tipoPlano = tipoPlano;
        
        if("CONTROLE".equals(tipoPlano)){
            tarifador = "IN";
            id_ofertaocs = null;
        }
    }

    public String getTarifador() {
        return tarifador;
    }

    public void setTarifador(String tarifador) {
        this.tarifador = tarifador;
    }

    public DualListModel<String> getAllDdds() {
        return allDdds;
    }

    public void setAllDdds(DualListModel<String> allDdds) {
        this.allDdds = allDdds;
    }

    public int getCurrentDddGroupIndex() {
        return currentDddGroupIndex;
    }

    public void setCurrentDddGroupIndex(int currentDddGroupIndex) {
        this.currentDddGroupIndex = currentDddGroupIndex;
    }

    public List<List<String>> getDddsFrases() {
        return dddsFrases;
    }

    public String getDddsFrasesString(int index) {
        return dddsFrases.get(index).toString().replace("[", "").replace("]", "").replace(" ", "");
    }

    public void setDddsFrases(List<List<String>> dddsFrases) {
        this.dddsFrases = dddsFrases;
    }

    public DualListModel<String> getAllDddsFrases() {
        return allDddsFrases;
    }

    public void setAllDddsFrases(DualListModel<String> allDddsFrases) {
        this.allDddsFrases = allDddsFrases;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

}
