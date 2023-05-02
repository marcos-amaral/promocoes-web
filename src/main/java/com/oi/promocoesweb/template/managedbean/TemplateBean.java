/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.managedbean;

import com.oi.promocoesweb.dbms.control.TemplateControl;
import com.oi.promocoesweb.dbms.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.oi.promocoesweb.datamodel.GenericDataModel;
import com.oi.promocoesweb.template.entity.Template;
import com.oi.promocoesweb.template.entity.aprovacao.Aprovacao;
import com.oi.promocoesweb.template.entity.ui.ColumnModel;
import com.oi.promocoesweb.template.enums.StatusEnum;
import com.oi.promocoesweb.template.enums.TabelasEnum;
import com.oi.promocoesweb.template.utils.DateUtils;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author mmouraam
 * @param <T>
 */
public abstract class TemplateBean<T extends Template> implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private List<String> gmudList;
    private String defaultFilterValue;
    private boolean adicionarOutra;
    private User user;
    private StreamedContent scriptFile;
    private Date gmudToDelete;
    private Date expiredGmud;
    private Date newDtGmud;
    private int limit = -1;

    private List<Aprovacao> aprovacaoList;
    private Aprovacao aprovacaoFiltered;
    private List<T> configOfertasPreList;
    private LazyDataModel<T> dataModel;
    private List<T> selectedConfigOfertasPreList;
    private List<T> newConfigOfertasPre;
    private T editConfigOfertasPre;
    private int currentAddIndex = 0;

    private Map<String, Boolean> map;

    private List<ColumnModel> colunasTemplate;
    private List<ColumnModel> colunasTemplateNotId;
    private List<ColumnModel> colunasTemplateId;
    private List<ColumnModel> colunasTemplateFrases144;
    private List<ColumnModel> colunasTemplateFrasesUSSD;

    protected void loadTabela(TabelasEnum tabelasEnum) {
        try {
            newConfigOfertasPre = new ArrayList<>();

            map = new HashMap<>();
            gmudList = new ArrayList<>();
            aprovacaoList = TemplateControl.getAprovacoes(tabelasEnum);
            configOfertasPreList = (List<T>) (Object) TemplateControl.getAll(tabelasEnum, limit);
            dataModel = new GenericDataModel<>(configOfertasPreList);

            configOfertasPreList.forEach(configOfertasPre -> gmudList.add(configOfertasPre.getDtGmudStr()));
            gmudList = gmudList.stream().distinct().collect(Collectors.toList());
            gmudList.remove("");

            gmudList.stream().forEach(dt -> {
                try {
                    Date data = new SimpleDateFormat("dd/MM/yy").parse(dt);
                    String dt1 = new SimpleDateFormat("dd/MM/yy").format(new Date());
                    Date hoje = DateUtils.parse(dt1);
                    if (data.compareTo(hoje) < 0) {
                        expiredGmud = DateUtils.parse(dt);
                    }
                } catch (Exception ex) {
                    logger.warn("gmudList_warn: ", ex);
                }
            });

            if (expiredGmud != null) {
                defaultFilterValue = new SimpleDateFormat("dd/MM/yy").format(expiredGmud);

                List<Aprovacao> collect = aprovacaoList.stream().filter(x -> defaultFilterValue.equals(x.getDt_gmudStr())).collect(Collectors.toList());

                if (collect != null && !collect.isEmpty()) {
                    aprovacaoFiltered = collect.get(0);
                } else {
                    aprovacaoFiltered = null;
                }

            }

            updateColunasTemplate(configOfertasPreList.get(0));

            map = TemplateControl.getCamposObrigatorios(tabelasEnum);

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro atualizando visualização", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    protected void clearVarsEdit(T temp) {
        clearSelected();
        editConfigOfertasPre = temp;
        editConfigOfertasPre.setUser(user);
    }

    protected void clearSelected() {
        selectedConfigOfertasPreList = new ArrayList<>();
    }

    protected void clearVarsAdd(T temp) {
        newConfigOfertasPre.clear();
        temp.setUser(user);
        newConfigOfertasPre.add(temp);
    }

    protected boolean adicionar(TabelasEnum tabelasEnum) {
        try {
            for (T t : newConfigOfertasPre) {
                try {
                    t.validate();

                } catch (ValidatorException e) {
                    throw e;
                }

                t.setModified(new Date());
                t.setUser(user);
                t.normalize();
            }

            Map<TabelasEnum, List<Template>> map = new HashMap<>();
            map.put(tabelasEnum, (List<Template>) (Object) newConfigOfertasPre);
            TemplateControl.insert(map);

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Promoção adicionada!");
            FacesContext.getCurrentInstance().addMessage(null, message);

            loadTabela(tabelasEnum);

            clearSelected();

            PrimeFaces.current().ajax().update("formTable:tabelaId");
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('tabelaCell').filter();");
            
            return true;

        } catch (ValidatorException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
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

        return false;
    }

    protected boolean editar(TabelasEnum tabelasEnum) {
        try {
            if (selectedConfigOfertasPreList != null && !selectedConfigOfertasPreList.isEmpty()) {
                try {
                    editConfigOfertasPre.validate();

                } catch (ValidatorException e) {
                    throw e;
                }

                editConfigOfertasPre.normalize();
                TemplateControl.update(tabelasEnum, (List<Template>) (Object) selectedConfigOfertasPreList, editConfigOfertasPre);

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Tabela Atualizada!");
                FacesContext.getCurrentInstance().addMessage(null, message);

                loadTabela(tabelasEnum);

                clearSelected();

                PrimeFaces.current().ajax().update("formTable:tabelaId");
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('tabelaCell').filter();");
                
                return true;
                
            } else {
                throw new Exception("Nenhuma promoção selecionada para edição!");
            }

        } catch (ValidatorException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
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
        
        return false;
    }

    protected void deletar(TabelasEnum tabelasEnum) {
        try {
            logger.debug("DELETE!");
            Template templateDel = new Template();
            templateDel.setDt_gmud(gmudToDelete);
            templateDel.setUser(user);
            TemplateControl.delete(tabelasEnum, (List<Template>) (Object) selectedConfigOfertasPreList, templateDel);

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Promoções Deletadas!");
            FacesContext.getCurrentInstance().addMessage(null, message);

            loadTabela(tabelasEnum);

            clearSelected();

            PrimeFaces.current().ajax().update("formTable:tabelaId");
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('tabelaCell').filter();");

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

    public void adiar(TabelasEnum tabelasEnum) {
        try {
            logger.debug("ADIAR!");
            if (expiredGmud == null) {
                expiredGmud = new SimpleDateFormat("dd/MM/yy").parse(defaultFilterValue);
            }
            TemplateControl.adiar(tabelasEnum, expiredGmud, newDtGmud);

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Promoções Adiadas!");
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

        loadTabela(tabelasEnum);

        clearSelected();
    }

    protected void implantar(TabelasEnum tabelasEnum) {
        try {
            logger.debug("IMPLANTAR!");
            Date gmudToAprove;
            if (expiredGmud == null) {
                gmudToAprove = new SimpleDateFormat("dd/MM/yy").parse(defaultFilterValue);
            } else {
                gmudToAprove = expiredGmud;
            }
            if (gmudToAprove.compareTo(new Date()) <= 0) {
                TemplateControl.implantar(tabelasEnum, gmudToAprove);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Promoções em produção!");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "A implantação só poderá ser realizada na data da GMUD!");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
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

        loadTabela(tabelasEnum);
        clearSelected();
    }

    protected void aprovarGmud(TabelasEnum tabelasEnum) {
        try {
            logger.debug("APROVAR!");
            Date gmudToAprove;
            if (expiredGmud == null) {
                gmudToAprove = new SimpleDateFormat("dd/MM/yy").parse(defaultFilterValue);
            } else {
                gmudToAprove = expiredGmud;
            }
            TemplateControl.aprovar(tabelasEnum, gmudToAprove, user.getId(), StatusEnum.APROVADO);

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Promoções Aprovadas!");
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

        loadTabela(tabelasEnum);
        clearSelected();
    }

    protected void iniciarGmud(TabelasEnum tabelasEnum) {
        try {
            logger.debug("INICIAR!");
            Date gmudToAprove;
            if (expiredGmud == null) {
                gmudToAprove = new SimpleDateFormat("dd/MM/yy").parse(defaultFilterValue);
            } else {
                gmudToAprove = expiredGmud;
            }
            TemplateControl.aprovar(tabelasEnum, gmudToAprove, user.getId(), StatusEnum.INICIADO);

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Promoções Iniciadas!");
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

        loadTabela(tabelasEnum);
        clearSelected();
    }

    protected void validarGmud(TabelasEnum tabelasEnum) {
        try {
            logger.debug("VALIDAR!");
            Date gmudToAprove;
            if (expiredGmud == null) {
                gmudToAprove = new SimpleDateFormat("dd/MM/yy").parse(defaultFilterValue);
            } else {
                gmudToAprove = expiredGmud;
            }
            TemplateControl.aprovar(tabelasEnum, gmudToAprove, user.getId(), StatusEnum.VALIDADO);

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Promoções Validadas!");
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

        loadTabela(tabelasEnum);
        clearSelected();
    }

    protected void uatGmud(TabelasEnum tabelasEnum) {
        try {
            logger.debug("EM UAT!");
            Date gmudToAprove;
            if (expiredGmud == null) {
                gmudToAprove = new SimpleDateFormat("dd/MM/yy").parse(defaultFilterValue);
            } else {
                gmudToAprove = expiredGmud;
            }
            TemplateControl.aprovar(tabelasEnum, gmudToAprove, user.getId(), StatusEnum.EM_UAT);

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Promoções em UAT!");
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

        loadTabela(tabelasEnum);
        clearSelected();
    }

    protected void implantarGmud(TabelasEnum tabelasEnum) {
        try {
            logger.debug("EM IMPLANTAÇÃO!");
            Date gmudToAprove;
            if (expiredGmud == null) {
                gmudToAprove = new SimpleDateFormat("dd/MM/yy").parse(defaultFilterValue);
            } else {
                gmudToAprove = expiredGmud;
            }
            TemplateControl.aprovar(tabelasEnum, gmudToAprove, user.getId(), StatusEnum.EM_IMPLANTACAO);

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Promoções em Implantação!");
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

        loadTabela(tabelasEnum);
        clearSelected();
    }

    public boolean isEditProd() {
        return getSelectedTemplateList() != null && getSelectedTemplateList().size() > 0 && getSelectedTemplateList().get(0).getDt_gmud() == null;
    }

    public boolean canEdit() {
        return getSelectedTemplateList() != null && getSelectedTemplateList().size() > 0 && getSelectedTemplateList().stream().filter(x -> x.isDeleted()).collect(Collectors.toList()).isEmpty();
    }

    public void cleanSelected() {
        setSelectedTemplateList(new ArrayList<>());
    }

    public boolean isAdicionarOutra() {
        return adicionarOutra;
    }

    public void setAdicionarOutra(boolean adicionarOutra) {
        this.adicionarOutra = adicionarOutra;
    }

    public StreamedContent getScriptFile() {
        return scriptFile;
    }

    public void setScriptFile(StreamedContent scriptFile) {
        this.scriptFile = scriptFile;
    }

    public List<String> getGmudList() {
        return gmudList;
    }

    public String getDefaultFilterValue() {
        return defaultFilterValue;
    }

    public void setDefaultFilterValue(String defaultFilterValue) {
        this.defaultFilterValue = defaultFilterValue;
        List<Aprovacao> collect = aprovacaoList.stream().filter(x -> defaultFilterValue.equals(x.getDt_gmudStr())).collect(Collectors.toList());
        if (collect != null && !collect.isEmpty()) {
            aprovacaoFiltered = collect.get(0);
        } else {
            aprovacaoFiltered = null;
        }
    }

    public Date getGmudToDelete() {
        return gmudToDelete;
    }

    public void setGmudToDelete(Date gmudToDelete) {
        this.gmudToDelete = gmudToDelete;
    }

    public Date getExpiredGmud() {
        return expiredGmud;
    }

    public Date getNewDtGmud() {
        return newDtGmud;
    }

    public void setNewDtGmud(Date newDtGmud) {
        this.newDtGmud = newDtGmud;
    }

    protected User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    protected List<T> getTemplateList() {
        return configOfertasPreList;
    }

    protected LazyDataModel<T> getDataModel() {
        return dataModel;
    }

    protected void setTemplateList(List<T> configOfertasPreList) {
        this.configOfertasPreList = new ArrayList<>(configOfertasPreList);
    }

    protected List<T> getSelectedTemplateList() {
        return selectedConfigOfertasPreList;
    }

    protected void setSelectedTemplateList(List<T> selectedConfigOfertasPreList) {
        this.selectedConfigOfertasPreList = new ArrayList<>(selectedConfigOfertasPreList);
    }

    protected List<T> getNewTemplateList() {
        return newConfigOfertasPre;
    }

    protected T getNewTemplate() {
        return newConfigOfertasPre.get(currentAddIndex);
    }

    protected T getNewTemplate(Integer index) {
        return newConfigOfertasPre.get(index);
    }

    protected void setNewTemplate(T newConfigOfertasPre) {
        if (this.newConfigOfertasPre.isEmpty()) {
            this.newConfigOfertasPre.add(newConfigOfertasPre);
        } else {
            this.newConfigOfertasPre.set(currentAddIndex, newConfigOfertasPre);
        }
    }

    protected T getEditTemplate() {
        return editConfigOfertasPre;
    }

    protected void setEditTemplate(T editConfigOfertasPre) {
        this.editConfigOfertasPre = editConfigOfertasPre;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<Aprovacao> getAprovacaoList() {
        return aprovacaoList;
    }

    public Aprovacao getAprovacaoFiltered() {
        return aprovacaoFiltered;
    }

    public boolean isObrigatorio(String campo) {
        return map.getOrDefault(campo, Boolean.FALSE);
    }

    public int getCurrentAddIndex() {
        return currentAddIndex;
    }

    public void setCurrentAddIndex(int currentAddIndex) {
        this.currentAddIndex = currentAddIndex;
    }

    public Date getToday() {
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

    public List<ColumnModel> getColunasTemplate() {
        return colunasTemplate;
    }

    public List<ColumnModel> getColunasTemplateNotId() {
        return colunasTemplateNotId;
    }

    public List<ColumnModel> getColunasTemplateId() {
        return colunasTemplateId;
    }

    public List<ColumnModel> getColunasTemplateFrases144() {
        return colunasTemplateFrases144;
    }

    public List<ColumnModel> getColunasTemplateFrasesUSSD() {
        return colunasTemplateFrasesUSSD;
    }

    private void updateColunasTemplate(Template template) {
        colunasTemplate = new ArrayList<>();
        colunasTemplateNotId = new ArrayList<>();
        colunasTemplateId = new ArrayList<>();
        colunasTemplateFrasesUSSD = new ArrayList<>();
        colunasTemplateFrases144 = new ArrayList<>();

        if (template != null) {
            for (Field field : template.getClass().getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    if (ColumnModel.isValidColumn(field)) {
                        colunasTemplate.add(new ColumnModel(field));
                    }

                    if (ColumnModel.isValidColumnNotId(field)) {
                        colunasTemplateNotId.add(new ColumnModel(field));
                    } else if (ColumnModel.isValidColumnId(field)) {
                        colunasTemplateId.add(new ColumnModel(field));
                    }

                    if (ColumnModel.isValidColumnFrase144(field)) {
                        colunasTemplateFrases144.add(new ColumnModel(field));
                    } else if (ColumnModel.isValidColumnFraseUSSD(field)) {
                        colunasTemplateFrasesUSSD.add(new ColumnModel(field));
                    }
                } catch (IllegalArgumentException ex) {
                }
            }
        }
    }
    
    public void reloadGmud(){
        
    }
}
