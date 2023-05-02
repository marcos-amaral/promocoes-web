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
import com.oi.promocoesweb.template.entity.ui.ColumnModel;
import com.oi.promocoesweb.template.enums.TabelasEnum;
import com.oi.promocoesweb.template.managedbean.IncentivoOfertasPreBean;
import com.oi.promocoesweb.template.managedbean.IoCartaoCartaoBean;
import com.oi.promocoesweb.template.managedbean.IoCartaoControleBean;
import com.oi.promocoesweb.template.managedbean.IoControleControleBean;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author mmouraam
 */
@Named
@ViewScoped
public class AddIncentivoBean implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private String[] TODOS_DDDs = {"11", "12", "13", "14", "15", "16", "17", "18", "19", "21", "22", "24", "27", "28", "31", "32", "33", "34", "35", "37", "38", "41", "42", "43", "44", "45", "46", "47", "48", "49", "51", "53", "54", "55", "61", "62", "63", "64", "65", "66", "67", "68", "69", "71", "73", "74", "75", "77", "79", "81", "82", "83", "84", "85", "86", "87", "88", "89", "91", "92", "93", "94", "95", "96", "97", "98", "99"};

    private Date dt_gmud;
    private List<List<String>> ddds144;
    private List<List<String>> ddds144PreControle;
    private List<List<String>> dddsCadastro;
    private List<List<String>> dddsFrases;
    private DualListModel<String> allDdds;
    private DualListModel<String> allDddsPreControle;
    private DualListModel<String> allDddsFrases;
    private Map<String, String> templateGrupoDdd;
    private String tipoPlano;
    private String tarifador;

    private List<String> currentDddsFrases;
    private List<String> currentDddsPreControle;

    private int currentDddGroupIndex = -1;
    private boolean finalizado = false;
    private int indexTabDados144 = 0;
    private int indexGrupoDados144 = 0;
    private int indexGrupoDados144PreControle = 0;
    private int indexGrupoDadosCadastro = 0;

    private List<? extends Template> listaNewTemplateDados;
    private List<? extends Template> listaNewTemplateDadosCartaoControle;
    private List<ColumnModel> colunasTemplateDados;
    private List<ColumnModel> colunasTemplateDadosPreControle;

    @PostConstruct
    public void init() {
        logger.trace("============= AddPromoBean init =============");

        initialize();
    }

    private void initialize() {
        ddds144 = new ArrayList<>();
        ddds144PreControle = new ArrayList<>();
        dddsCadastro = new ArrayList<>();
        dddsFrases = new ArrayList<>();
        
        List<String> dddsSource = new ArrayList<>();
        List<String> dddsTarget = new ArrayList<>();

        dddsSource.addAll(Arrays.asList(TODOS_DDDs));

        allDdds = new DualListModel<>(dddsSource, dddsTarget);
        allDddsFrases = new DualListModel<>(Arrays.asList(TODOS_DDDs), new ArrayList<>());
        allDddsPreControle = new DualListModel<>(Arrays.asList(TODOS_DDDs), new ArrayList<>());

        templateGrupoDdd = new HashMap<>();
    }

    public void reset() {
        initialize();

        dt_gmud = null;

        finalizado = false;
    }

    public String onFlowProcess(FlowEvent event) {
        if (finalizado) {
            reset();
            finalizado = false;
            return "tabIds";
        }

        String nextStep = event.getNewStep();
        try {
            switch (event.getNewStep()) {
                case "tabDados":
                    if (ddds144.isEmpty() && dddsCadastro.isEmpty()) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "Você não adicionou nenhum grupo de DDD");
                        FacesContext.getCurrentInstance().addMessage(null, message);

                        return "tabDDDs";
                    }
                    if (!ddds144.isEmpty()) {
                        for (int i = 0; i < ddds144.size(); i++) {
                            if (ddds144.get(i).isEmpty()) {
                                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "Grupo" + (i + 1) + " vazio");
                                FacesContext.getCurrentInstance().addMessage(null, message);

                                return "tabDDDs";
                            }
                        }
                    }
                    if (!dddsCadastro.isEmpty()) {
                        for (int i = 0; i < dddsCadastro.size(); i++) {
                            if (dddsCadastro.get(i).isEmpty()) {
                                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "Grupo" + (i + 1) + " vazio");
                                FacesContext.getCurrentInstance().addMessage(null, message);

                                return "tabDDDs";
                            }
                        }
                    }
                    break;
                case "tabConfirm":
                    if ("OCS".equals(tarifador) && "PRE".equals(tipoPlano)) {
                        FacesContext context = FacesContext.getCurrentInstance();
                        Application application = context.getApplication();

                        IncentivoOfertasPreBean incentivoOfertasPreBean = application.evaluateExpressionGet(context, "#{incentivoOfertasPreBean}", IncentivoOfertasPreBean.class);
                        listaNewTemplateDados = incentivoOfertasPreBean.getNewTemplateList();
                        try {
                            for (Template listaNewTemplateDado : listaNewTemplateDados) {
                                listaNewTemplateDado.validate();
                            }
                        } catch (ValidatorException e) {
                            nextStep = "tabDados";
                            throw e;
                        }

                    } else if ("IN".equals(tarifador) && "PRE".equals(tipoPlano)) {
                        FacesContext context = FacesContext.getCurrentInstance();
                        Application application = context.getApplication();

                        IoCartaoCartaoBean ioCartaoCartaoBean = application.evaluateExpressionGet(context, "#{ioCartaoCartaoBean}", IoCartaoCartaoBean.class);
                        listaNewTemplateDados = ioCartaoCartaoBean.getNewTemplateList();
                        try {
                            for (Template listaNewTemplateDado : listaNewTemplateDados) {
                                listaNewTemplateDado.validate();
                            }
                        } catch (ValidatorException e) {
                            nextStep = "tabDados";
                            throw e;
                        }

                    } else if ("IN".equals(tarifador) && "CONTROLE".equals(tipoPlano)) {
                        FacesContext context = FacesContext.getCurrentInstance();
                        Application application = context.getApplication();

                        IoControleControleBean ioControleControleBean = application.evaluateExpressionGet(context, "#{ioControleControleBean}", IoControleControleBean.class);
                        listaNewTemplateDados = ioControleControleBean.getNewTemplateList();
                        try {
                            for (Template listaNewTemplateDado : listaNewTemplateDados) {
                                listaNewTemplateDado.validate();
                            }
                        } catch (ValidatorException e) {
                            nextStep = "tabDados";
                            throw e;
                        }

                        IoCartaoControleBean ioCartaoControleBean = application.evaluateExpressionGet(context, "#{ioCartaoControleBean}", IoCartaoControleBean.class);
                        listaNewTemplateDadosCartaoControle = ioCartaoControleBean.getNewTemplateList();
                        try {
                            for (Template listaNewTemplateDado : listaNewTemplateDadosCartaoControle) {
                                listaNewTemplateDado.validate();
                            }
                        } catch (ValidatorException e) {
                            nextStep = "tabDados";
                            throw e;
                        }

                        updateColunasTemplateDadosPreControle(listaNewTemplateDadosCartaoControle.get(0));

                    }
                    updateColunasTemplateDados(listaNewTemplateDados.get(0));

                    break;
            }
        } catch (ValidatorException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        return nextStep;
    }

    public void addGrupoDdd() {
        if (allDdds != null && allDdds.getTarget() != null) {
            for (List<String> list : ddds144) {
                for (String string : allDdds.getTarget()) {
                    if (list.contains(string)) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "DDD já existe em algum grupo!", null);
                        FacesContext.getCurrentInstance().addMessage(null, message);

                        return;
                    }
                }
            }

            ddds144.add(allDdds.getTarget());
            allDdds.setTarget(new ArrayList<>());
            List<String> dddsSource = new ArrayList<>();
            dddsSource.addAll(Arrays.asList(TODOS_DDDs));
            allDdds.setSource(dddsSource);
        }
        ddds144PreControle.clear();
        ddds144PreControle.addAll(ddds144);
    }

    public void addDdd(SelectEvent evt) {
        if (evt.getObject() != null && allDdds.getSource() != null && String.valueOf(evt.getObject()) != null) {
            String[] valueOf = String.valueOf(evt.getObject()).replace(" ", "").split("[\\W]");

            for (String value : valueOf) {
                if (ddds144.get(currentDddGroupIndex).contains(value)) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "DDD "+value+" já existe em algum grupo!", null);
                    FacesContext.getCurrentInstance().addMessage(null, message);

                    return;
                }
                
                if (allDdds.getSource().contains(value)) {
                    ddds144.get(currentDddGroupIndex).add(value);
                    allDdds.getSource().remove(value);
                }
            }
        }
        
        ddds144PreControle.clear();
        ddds144PreControle.addAll(ddds144);
    }

    public void removeGrupoDdd(int index) {
        Collections.sort(allDdds.getSource());
        ddds144.remove(index);

        ddds144PreControle.clear();
        ddds144PreControle.addAll(ddds144);
    }

    public void removeDdd(UnselectEvent evt) {
        for (int i = 0; i < ddds144.size(); i++) {
            if (ddds144.get(i).contains(String.valueOf(evt.getObject()))) {
                ddds144.get(i).remove(String.valueOf(evt.getObject()));

                if (ddds144.get(i).isEmpty()) {
                    ddds144.remove(i);
                    break;
                }
            }
        }
        Collections.sort(allDdds.getSource());

        ddds144PreControle.clear();
        ddds144PreControle.addAll(ddds144);
    }

    public void addGrupoDddCadastro() {
        if (allDdds != null && allDdds.getTarget() != null) {
            for (List<String> list : dddsCadastro) {
                for (String string : allDdds.getTarget()) {
                    if (list.contains(string)) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "DDD já existe em algum grupo!", null);
                        FacesContext.getCurrentInstance().addMessage(null, message);

                        return;
                    }
                }
            }

            dddsCadastro.add(allDdds.getTarget());
            allDdds.setTarget(new ArrayList<>());
            
            List<String> dddsSource = new ArrayList<>();
            dddsSource.addAll(Arrays.asList(TODOS_DDDs));
            allDdds.setSource(dddsSource);
        }
        dddsFrases.clear();
        dddsFrases.addAll(dddsCadastro);
    }

    public void addDddCadastro(SelectEvent evt) {
        if (evt.getObject() != null && allDdds.getSource() != null && allDdds.getSource().contains(String.valueOf(evt.getObject()))) {

            if (dddsCadastro.get(currentDddGroupIndex).contains(String.valueOf(evt.getObject()))) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "DDD já existe em algum grupo!", null);
                FacesContext.getCurrentInstance().addMessage(null, message);

                return;
            }

            dddsCadastro.get(currentDddGroupIndex).add(String.valueOf(evt.getObject()));
            allDdds.getSource().remove(String.valueOf(evt.getObject()));
        }
        dddsFrases.clear();
        dddsFrases.addAll(dddsCadastro);
    }

    public void removeGrupoDddCadastro(int index) {
        Collections.sort(allDdds.getSource());
        dddsCadastro.remove(index);

        dddsFrases.clear();
        dddsFrases.addAll(dddsCadastro);
    }

    public void removeDddCadastro(UnselectEvent evt) {
        for (int i = 0; i < dddsCadastro.size(); i++) {
            if (dddsCadastro.get(i).contains(String.valueOf(evt.getObject()))) {
                dddsCadastro.get(i).remove(String.valueOf(evt.getObject()));

                if (dddsCadastro.get(i).isEmpty()) {
                    dddsCadastro.remove(i);
                    break;
                }
            }
        }
        Collections.sort(allDdds.getSource());

        dddsFrases.clear();
        dddsFrases.addAll(dddsCadastro);
    }

    public void loadPickListDddFrases(List<String> list) {
        allDddsFrases.setSource(new ArrayList<>());
        for (List<String> ddd : dddsCadastro) {
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

    public void loadPickListDddPreControle(List<String> list) {
        allDddsPreControle.setSource(new ArrayList<>());
        for (String ddd : TODOS_DDDs) {
            allDddsPreControle.getSource().add(ddd);
        }
        allDddsPreControle.getTarget().clear();

        if (list != null) {
            currentDddsPreControle = new ArrayList<>(list);

            for (String string : list) {
                allDddsPreControle.getTarget().add(string);
            }
        } else {
            currentDddsPreControle = null;
        }

        for (List<String> ddds : ddds144PreControle) {
            for (String string : ddds) {
                allDddsPreControle.getSource().remove(string);
            }
        }
    }

    public void deleteDddsFrases(List<String> list) {
        dddsFrases.remove(list);
    }

    public void deleteDddsPreControle(List<String> list) {
        ddds144PreControle.remove(list);
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

    public void confirmDddsPreControle() {
        if (currentDddsPreControle == null || currentDddsPreControle.isEmpty()) {
            ddds144PreControle.add(new ArrayList<>(allDddsPreControle.getTarget()));
            ddds144PreControle.get(ddds144PreControle.size() - 1).sort(null);
        } else {
            ddds144PreControle.set(ddds144PreControle.indexOf(currentDddsPreControle), new ArrayList<>(allDddsPreControle.getTarget()));
            ddds144PreControle.get(ddds144PreControle.indexOf(allDddsPreControle.getTarget())).sort(null);
        }

    }

    public void onTabClose(TabCloseEvent event) {
        dddsFrases.remove(event.getData());
    }

    public void onTabClosePreControle(TabCloseEvent event) {
        ddds144PreControle.remove(event.getData());
    }

    public void onTabChange144(TabChangeEvent event) {
        indexGrupoDados144 = Integer.valueOf(event.getTab().getTitle().substring(event.getTab().getTitle().length() - 1)) - 1;
    }

    public void onTabChangeTemplate144(TabChangeEvent event) {
        switch (event.getTab().getTitle()) {
            case "*144 / *880 / USSD (Controle > Controle)":
                indexTabDados144 = 0;
                break;
            case "*144 / *880 / USSD (Pre > Controle)":
                indexTabDados144 = 1;
                break;
            case "URA Cadastro":
                int idx = 0;
                if (ddds144 != null && !ddds144.isEmpty()) {
                    idx++;
                }
                if (ddds144PreControle != null && !ddds144PreControle.isEmpty()) {
                    idx++;
                }
                indexTabDados144 = idx;
                break;
            default:
                indexTabDados144 = 0;
                break;
        }
    }

    public void onTabChange144PreControle(TabChangeEvent event) {
        indexGrupoDados144PreControle = Integer.valueOf(event.getTab().getTitle().substring(event.getTab().getTitle().length() - 1)) - 1;
    }

    public void onTabChangeCadastro(TabChangeEvent event) {
        indexGrupoDadosCadastro = Integer.valueOf(event.getTab().getTitle().substring(event.getTab().getTitle().length() - 1)) - 1;
    }

    public void adicionar(TabelasEnum tabelasEnumDados144, List<Template> listDados144, TabelasEnum tabelasEnumDados144PreControle, List<Template> listDados144PreControle, TabelasEnum tabelasEnumDadosCad, List<Template> listDadosCad, TabelasEnum tabelasEnumFrases, List<Template> listFrases) {
        try {
            Map<TabelasEnum, List<Template>> map = new HashMap<>();
            String msg = "";

            if (listDados144 != null && !listDados144.isEmpty() && ddds144 != null && !ddds144.isEmpty()) {

                for (Template t : listDados144) {
                    t.setModified(new Date());
                    t.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
                    t.normalize();
                }
                map.put(tabelasEnumDados144, listDados144);
                msg += tabelasEnumDados144 + "\n";
            }

            if (listDados144PreControle != null && !listDados144PreControle.isEmpty() && ddds144PreControle != null && !ddds144PreControle.isEmpty()) {

                for (Template t : listDados144PreControle) {
                    t.setModified(new Date());
                    t.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
                    t.normalize();
                }
                map.put(tabelasEnumDados144PreControle, listDados144PreControle);
                msg += tabelasEnumDados144PreControle + "\n";
            }

//            if (listDadosCad != null && !listDadosCad.isEmpty() && dddsCadastro != null && !dddsCadastro.isEmpty()) {
//
//                for (Template t : listDadosCad) {
//                    t.setModified(new Date());
//                    t.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
//                    t.normalize();
//                }
//                map.put(tabelasEnumDadosCad, listDadosCad);
//                msg += tabelasEnumDadosCad + "\n";
//            }
//
//            if (listFrases != null && !listFrases.isEmpty() && dddsFrases != null && !dddsFrases.isEmpty()) {
//
//                for (Template t : listFrases) {
//                    t.setModified(new Date());
//                    t.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
//                    t.normalize();
//                }
//                map.put(tabelasEnumFrases, listFrases);
//                msg += tabelasEnumFrases + "\n";
//            }

            for (Map.Entry<TabelasEnum, List<Template>> entry : map.entrySet()) {
                Object key = entry.getKey();
                List<Template> value = entry.getValue();

                System.out.println(key + "|" + value);
            }

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
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("dt_gmud_incentivo", dt_gmud);
        this.dt_gmud = dt_gmud;
    }

    public List<List<String>> getDdds144() {
        return ddds144;
    }

    public List<List<String>> getDddsCadastro() {
        return dddsCadastro;
    }

    public String getDddsString(int index) {
        return ddds144.get(index).toString().replace("[", "").replace("]", "").replace(" ", "");
    }

    public String getDddsStringCadastro(int index) {
        return dddsCadastro.get(index).toString().replace("[", "").replace("]", "").replace(" ", "");
    }

    public void setDdds144(List<List<String>> ddds144) {
        this.ddds144 = ddds144;
    }

    public String getTipoPlano() {
        return tipoPlano;
    }

    public void setTipoPlano(String tipoPlano) {
        this.tipoPlano = tipoPlano;

        if ("CONTROLE".equals(tipoPlano)) {
            tarifador = "IN";
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

    public List<List<String>> getDdds144PreControle() {
        return ddds144PreControle;
    }

    public String getDdds144PreControleString(int index) {
        return ddds144PreControle.get(index).toString().replace("[", "").replace("]", "").replace(" ", "");
    }

    public void setDdds144PreControle(List<List<String>> ddds144PreControle) {
        this.ddds144PreControle = ddds144PreControle;
    }

    public DualListModel<String> getAllDddsPreControle() {
        return allDddsPreControle;
    }

    public void setAllDddsPreControle(DualListModel<String> allDddsPreControle) {
        this.allDddsPreControle = allDddsPreControle;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Map<String, String> getTemplateGrupoDdd() {
        return templateGrupoDdd;
    }

    public void setTemplateGrupoDdd(Map<String, String> templateGrupoDdd) {
        this.templateGrupoDdd = templateGrupoDdd;
    }

    public int getIndexGrupoDados144() {
        return indexGrupoDados144;
    }

    public void setIndexGrupoDados144(int indexGrupoDados144) {
        this.indexGrupoDados144 = indexGrupoDados144;
    }

    public int getIndexTabDados144() {
        return indexTabDados144;
    }

    public void setIndexTabDados144(int indexTabDados144) {
        this.indexTabDados144 = indexTabDados144;
    }

    public int getIndexGrupoDados144PreControle() {
        return indexGrupoDados144PreControle;
    }

    public void setIndexGrupoDados144PreControle(int indexGrupoDados144PreControle) {
        this.indexGrupoDados144PreControle = indexGrupoDados144PreControle;
    }

    public int getIndexGrupoDadosCadastro() {
        return indexGrupoDadosCadastro;
    }

    public void setIndexGrupoDadosCadastro(int indexGrupoDadosCadastro) {
        this.indexGrupoDadosCadastro = indexGrupoDadosCadastro;
    }

    public Date getToday() {
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

    public List<? extends Template> getListaNewTemplateDados() {
        return listaNewTemplateDados;
    }

    public List<? extends Template> getListaNewTemplateDadosCartaoControle() {
        return listaNewTemplateDadosCartaoControle;
    }

    public List<ColumnModel> getColunasTemplateDados() {
        return colunasTemplateDados;
    }

    public List<ColumnModel> getColunasTemplateDadosPreControle() {
        return colunasTemplateDadosPreControle;
    }

    private void updateColunasTemplateDados(Template template) {
        colunasTemplateDados = new ArrayList<>();
        if (template != null) {
            for (Field field : template.getClass().getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    if (ColumnModel.isValidColumn(field)) {
                        colunasTemplateDados.add(new ColumnModel(field));
                    }
                } catch (IllegalArgumentException ex) {
                }
            }
        }
    }

    private void updateColunasTemplateDadosPreControle(Template template) {
        colunasTemplateDadosPreControle = new ArrayList<>();
        if (template != null) {
            for (Field field : template.getClass().getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    if (ColumnModel.isValidColumn(field)) {
                        colunasTemplateDadosPreControle.add(new ColumnModel(field));
                    }
                } catch (IllegalArgumentException ex) {
                }
            }
        }
    }
}
