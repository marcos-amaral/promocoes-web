/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.managedbean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oi.promocoesweb.dbms.entity.User;
import static com.oi.promocoesweb.managedbean.ControleBean.AUTH_USER;
import com.oi.promocoesweb.template.entity.ConfigOfertasPre;
import com.oi.promocoesweb.template.entity.Template;
import com.oi.promocoesweb.template.entity.aprovacao.Aprovacao;
import com.oi.promocoesweb.template.enums.StatusEnum;
import com.oi.promocoesweb.template.enums.TabelasEnum;
import com.oi.promocoesweb.template.utils.JsonUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author mmouraam
 */
@Named
@ViewScoped
public class ConfigOfertasPreBean extends TemplateBean<ConfigOfertasPre> implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private final TabelasEnum tabelasEnum = TabelasEnum.CONFIG_OFERTASPRE;

    @PostConstruct
    public void init() {
        logger.trace("============= TemplateBean init =============");
        try {
            super.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new ConfigOfertasPre());
            ConfigOfertasPre configOfertasPre = new ConfigOfertasPre();
            configOfertasPre.setUssd_convertido(true);
            super.clearVarsAdd(configOfertasPre);

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void reloadTabela() {
        try {
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new ConfigOfertasPre());
            ConfigOfertasPre configOfertasPre = new ConfigOfertasPre();
            configOfertasPre.setUssd_convertido(true);
            super.clearVarsAdd(configOfertasPre);

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void adicionar() {
        super.adicionar(tabelasEnum);
        ConfigOfertasPre configOfertasPre = new ConfigOfertasPre();
        configOfertasPre.setUssd_convertido(true);
        super.clearVarsAdd(configOfertasPre);

        if (!isAdicionarOutra()) {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('sidebarAdd').hide();");
        }
    }

    public void editar() {
        try {
            Map<String, Boolean> collect = this.getEditTemplate().getEditMap().entrySet().stream().filter(x -> x.getValue().equals(Boolean.TRUE)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            if (collect == null || collect.isEmpty()) {
                throw new Exception("Selecione pelo menos um checkbox para edição!");
            }

            this.getEditTemplate().normalize();
            for (Template configOfertasPre : this.getSelectedTemplateList()) {
                configOfertasPre.setHistory(JsonUtils.addToJsonArray(configOfertasPre.getDt_gmud() == null ? null : configOfertasPre.getHistory(), this.getEditTemplate()));
                logger.trace(JsonUtils.toJson(configOfertasPre.getHistory()));
            }

            super.editar(tabelasEnum);

            super.clearVarsEdit(new ConfigOfertasPre());

        } catch (JsonProcessingException e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('sidebarEdit').hide();");
    }

    public void deletar() {
        super.deletar(tabelasEnum);

        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('sidebarDelete').hide();");
    }

    public void adiar() {
        super.adiar(tabelasEnum);
    }

    public void aprovar() {
        super.implantar(tabelasEnum);
    }

    public void aprovarGmud() {
        super.aprovarGmud(tabelasEnum);
    }

    public void iniciarGmud() {
        super.iniciarGmud(tabelasEnum);
    }

    public void validarGmud() {
        super.validarGmud(tabelasEnum);
    }

    public void uatGmud() {
        super.uatGmud(tabelasEnum);
    }

    public void implantarGmud() {
        super.implantarGmud(tabelasEnum);
    }

    public void preLoadEditBean() {
        if (this.getSelectedTemplateList().size() == 1) {
            this.setEditTemplate(new ConfigOfertasPre(this.getSelectedTemplateList().get(0)));
            this.getEditTemplate().setUser(super.getUser());
            this.setNewTemplate(new ConfigOfertasPre(this.getSelectedTemplateList().get(0)));
            this.getNewTemplate().setUser(super.getUser());
        } else {
            this.setEditTemplate(new ConfigOfertasPre());
            this.getEditTemplate().setUser(super.getUser());
            this.setNewTemplate(new ConfigOfertasPre());
            this.getNewTemplate().setUser(super.getUser());
        }
        if (this.getSelectedTemplateList().size() > 0) {
            super.setGmudToDelete(this.getSelectedTemplateList().get(0).getDt_gmud());
        } else {
            super.setGmudToDelete(null);
        }
    }

    public void generateScriptFile(String aplicacao) {
        logger.trace("SCRIPT gmud:{}|{}", super.getDefaultFilterValue(), aplicacao);
        StringBuilder script = new StringBuilder();

        List<ConfigOfertasPre> list = null;

        list = this.getTemplateList().stream().filter(c -> super.getDefaultFilterValue().equals(c.getDtGmudStr())).collect(Collectors.toList());

        if (list != null) {
            StringBuilder sb;

            InputStream inputStream = null;
            int i;
            for (ConfigOfertasPre registro : list) {
                sb = new StringBuilder();

                switch (aplicacao) {
                    case "USSD":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM ").append(tabelasEnum.name()).append(";\n");
                            script.append(sb.toString());

                        } else {
                            for (ConfigOfertasPre registroDel : list) {
                                sb.append("DELETE FROM ").append(tabelasEnum.name());

                                sb.append(" WHERE ");
                                sb.append("ID_OFERTAOCS = ").append(registroDel.getId_ofertaocs()).append(" AND ");
                                sb.append("DDD = '").append(registroDel.getDdd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }

                        sb.append("INSERT INTO ").append(tabelasEnum.name());
                        sb.append(" (ID_OFERTAOCS,ID_CAMPANHASIEBEL,ID_OFERTASIEBEL,DDD,DATA_INIC,DATA_FIM,TIPO_OFERTA,ELEGIVELCONVERSAO,FREEUNITVOZELEGIVEL,FREEUNITDADOSELEGIVEL,LIMMAXCONVMIN,LIMMAXCONVMB,SALDOMINDADOSCONVMB,VALOR_TAXAADESAO,EXISTEFALLBACK,EXISTEROLLOVER,SHORT_CODE,USSD_CONVERTIDO) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16,VAR17,VAR18);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getId_ofertaocs() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getId_campanhasiebel() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getId_ofertasiebel() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getData_inicStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getData_fimStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTipo_oferta() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getElegivelconversaoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getFreeunitvozelegivel() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getFreeunitdadoselegivel() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getLimmaxconvmin() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getLimmaxconvmb() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getSaldomindadosconvmb() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getValor_taxaadesao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getExistefallbackStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getExisterolloverStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getShort_code() + "',")
                                .replace("VAR" + (i++), "'" + registro.getUssd_convertidoStr() + "'")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .replace("LIMMAXCONVMIN", "LIMITEMAXIMOCONVERSAOVOZ")
                                        .replace("LIMMAXCONVMB", "LIMITEMAXIMOCONVERSAO")
                                        .getBytes(Charset.forName("UTF-8")));

                        break;

                    case "144":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM TB_CONFIG_OFERTASOCSPRE;\n");
                            script.append(sb.toString());

                        } else {
                            for (ConfigOfertasPre registroDel : list) {
                                sb.append("DELETE FROM TB_CONFIG_OFERTASOCSPRE");

                                sb.append(" WHERE ");
                                sb.append("ID_OFERTAOCS = ").append(registroDel.getId_ofertaocs()).append(" AND ");
                                sb.append("DDD = '").append(registroDel.getDdd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }

                        sb.append("INSERT INTO TB_CONFIG_OFERTASOCSPRE");
                        sb.append(" (ID_OFERTAOCS,ID_CAMPANHASIEBEL,ID_OFERTASIEBEL,DDD,DATA_INIC,DATA_FIM,TIPO_OFERTA,VALOR_TAXAADESAO,EXISTEFALLBACK,EXISTEROLLOVER,SHORT_CODE) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getId_ofertaocs() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getId_campanhasiebel() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getId_ofertasiebel() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getData_inicStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getData_fimStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTipo_oferta() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getValor_taxaadesao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getExistefallbackStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getExisterolloverStr() + "',")
                                .replace("VAR" + (i++), "'" + registro.getShort_code() + "'")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .getBytes(Charset.forName("UTF-8")));

                        break;

                    case "880":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM OCS_CONFIG_OFERTASPRE;\n");
                            script.append(sb.toString());

                        } else {
                            for (ConfigOfertasPre registroDel : list) {
                                sb.append("DELETE FROM OCS_CONFIG_OFERTASPRE");

                                sb.append(" WHERE ");
                                sb.append("ID_OFERTAOCS = ").append(registroDel.getId_ofertaocs()).append(" AND ");
                                sb.append("DDD = '").append(registroDel.getDdd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }

                        sb.append("INSERT INTO OCS_CONFIG_OFERTASPRE");
                        sb.append(" (ID_OFERTAOCS,ID_CAMPANHASIEBEL,ID_OFERTASIEBEL,DDD,DATA_INIC,DATA_FIM,TIPO_OFERTA,VALOR_TAXAADESAO,EXISTEFALLBACK,EXISTEROLLOVER,SHORT_CODE) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getId_ofertaocs() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getId_campanhasiebel() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getId_ofertasiebel() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getData_inicStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getData_fimStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTipo_oferta() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getValor_taxaadesao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getExistefallbackStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getExisterolloverStr() + "',")
                                .replace("VAR" + (i++), "'" + registro.getShort_code() + "'")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .getBytes(Charset.forName("UTF-8")));

                        break;
                }
            }

            DefaultStreamedContent defaultStreamedContent = new DefaultStreamedContent();
            defaultStreamedContent.setStream(inputStream);
            defaultStreamedContent.setName("config_ofertaspre.sql");
            defaultStreamedContent.setContentType("text/plain");

            super.setScriptFile(defaultStreamedContent);

        }
    }

    @Override
    public List<ConfigOfertasPre> getTemplateList() {
        return (List<ConfigOfertasPre>) (Object) super.getTemplateList();
    }

    @Override
    public List<ConfigOfertasPre> getSelectedTemplateList() {
        return (List<ConfigOfertasPre>) (Object) super.getSelectedTemplateList();
    }

    @Override
    public ConfigOfertasPre getNewTemplate() {
        return (ConfigOfertasPre) super.getNewTemplate();
    }

    @Override
    public List<ConfigOfertasPre> getNewTemplateList() {
        return (List<ConfigOfertasPre>) (Object) super.getNewTemplateList();
    }

    @Override
    public ConfigOfertasPre getEditTemplate() {
        return (ConfigOfertasPre) super.getEditTemplate();
    }

    @Override
    public void setTemplateList(List<ConfigOfertasPre> configOfertasPreList) {
        super.setTemplateList(configOfertasPreList);
    }

    @Override
    public void setSelectedTemplateList(List<ConfigOfertasPre> selectedConfigOfertasPreList) {
        super.setSelectedTemplateList(selectedConfigOfertasPreList);
    }

    @Override
    public void setNewTemplate(ConfigOfertasPre newConfigOfertasPre) {
        super.setNewTemplate(newConfigOfertasPre);
    }

    @Override
    public void setEditTemplate(ConfigOfertasPre editConfigOfertasPre) {
        super.setEditTemplate(editConfigOfertasPre);
    }

    public List<ConfigOfertasPre> getSelectedHistoryList() {
        if (super.getSelectedTemplateList() != null && super.getSelectedTemplateList().size() > 0) {
            List<ConfigOfertasPre> history = (List<ConfigOfertasPre>) (Object) super.getSelectedTemplateList().get(0).getHistory();

            for (ConfigOfertasPre bean : history) {
                List<Aprovacao> collect = getAprovacaoList().stream().filter(x -> getDefaultFilterValue().equals(x.getDt_gmudStr())).collect(Collectors.toList());
                if (collect != null && !collect.isEmpty()) {
                    StatusEnum statusTemp = StatusEnum.INICIADO;
                    for (Aprovacao aprovacao : collect) {
                        if (aprovacao.getDt_aprovacao().after(bean.getModified())) {
                            switch (statusTemp) {
                                case EM_IMPLANTACAO:
                                case IMPLANTAR:
                                    break;
                                case EM_UAT:
                                    if (aprovacao.getStatus() == StatusEnum.EM_IMPLANTACAO || aprovacao.getStatus() == StatusEnum.IMPLANTAR) {
                                        statusTemp = aprovacao.getStatus();
                                    }
                                    break;
                                case APROVADO:
                                    if (aprovacao.getStatus() == StatusEnum.EM_IMPLANTACAO || aprovacao.getStatus() == StatusEnum.IMPLANTAR
                                            || aprovacao.getStatus() == StatusEnum.EM_UAT) {
                                        statusTemp = aprovacao.getStatus();
                                    }
                                    break;
                                case VALIDADO:
                                    if (aprovacao.getStatus() == StatusEnum.EM_IMPLANTACAO || aprovacao.getStatus() == StatusEnum.IMPLANTAR
                                            || aprovacao.getStatus() == StatusEnum.EM_UAT || aprovacao.getStatus() == StatusEnum.APROVADO) {
                                        statusTemp = aprovacao.getStatus();
                                    }
                                    break;
                                case INICIADO:
                                    statusTemp = aprovacao.getStatus();
                                    break;
                            }
                        }
                    }
                    bean.setLastStatus(statusTemp);
                }
            }

            return history;
        } else {
            return new ArrayList<>();
        }
    }

    public TabelasEnum getTabelasEnum() {
        return tabelasEnum;
    }

    public LazyDataModel<ConfigOfertasPre> getDataModel() {
        return (LazyDataModel<ConfigOfertasPre>) (Object) super.getDataModel();
    }

    //CUSTOM METHODS////////////////////////////////////////////////////////
    public ConfigOfertasPre getNewTemplate(Integer size, Integer index, String ddd, String idOfertaOcs, String idBeneficio, String idPrograma, String id_campanhasiebel, String id_ofertasiebel, Date dt_gmud) {
        if (super.getNewTemplateList().size() <= index) {
            for (int i = 0; i < index; i++) {
                if (i == index) {
                    ConfigOfertasPre configOfertasPre = new ConfigOfertasPre();
                    configOfertasPre.setDdd(ddd);
                    configOfertasPre.setId_ofertaocs(idOfertaOcs);
                    configOfertasPre.setId_beneficio(idBeneficio);
                    configOfertasPre.setId_programa(idPrograma);
                    configOfertasPre.setId_campanhasiebel(id_campanhasiebel);
                    configOfertasPre.setId_ofertasiebel(id_ofertasiebel);
                    configOfertasPre.setDt_gmud(dt_gmud);
                    configOfertasPre.setUser(getUser());
                    super.getNewTemplateList().add(configOfertasPre);

                } else {
                    super.getNewTemplateList().add(null);
                }
            }
        } else if (super.getNewTemplateList().size() > size) {
            do {
                super.getNewTemplateList().remove(super.getNewTemplateList().size() - 1);
            } while (super.getNewTemplateList().size() > size);
        }

        if (super.getNewTemplate(index) == null) {
            ConfigOfertasPre configOfertasPre = new ConfigOfertasPre();
            configOfertasPre.setDdd(ddd);
            configOfertasPre.setId_ofertaocs(idOfertaOcs);
            configOfertasPre.setId_beneficio(idBeneficio);
            configOfertasPre.setId_programa(idPrograma);
            configOfertasPre.setId_campanhasiebel(id_campanhasiebel);
            configOfertasPre.setId_ofertasiebel(id_ofertasiebel);
            configOfertasPre.setDt_gmud(dt_gmud);
            configOfertasPre.setUser(getUser());
            super.getNewTemplateList().set(index, configOfertasPre);
        } else {
            ((ConfigOfertasPre) super.getNewTemplate(index)).setDdd(ddd);
            ((ConfigOfertasPre) super.getNewTemplate(index)).setId_ofertaocs(idOfertaOcs);
            ((ConfigOfertasPre) super.getNewTemplate(index)).setId_beneficio(idBeneficio);
            ((ConfigOfertasPre) super.getNewTemplate(index)).setId_programa(idPrograma);
            ((ConfigOfertasPre) super.getNewTemplate(index)).setId_campanhasiebel(id_campanhasiebel);
            ((ConfigOfertasPre) super.getNewTemplate(index)).setId_ofertasiebel(id_ofertasiebel);
            ((ConfigOfertasPre) super.getNewTemplate(index)).setDt_gmud(dt_gmud);
            ((ConfigOfertasPre) super.getNewTemplate(index)).setUser(getUser());
        }

        return (ConfigOfertasPre) super.getNewTemplate(index);
    }
}
