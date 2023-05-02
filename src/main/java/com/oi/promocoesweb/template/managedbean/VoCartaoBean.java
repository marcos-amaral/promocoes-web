/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.managedbean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oi.promocoesweb.dbms.entity.User;
import static com.oi.promocoesweb.managedbean.ControleBean.AUTH_USER;
import com.oi.promocoesweb.template.entity.VoCartao;
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
public class VoCartaoBean extends TemplateBean<VoCartao> implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    private final TabelasEnum tabelasEnum = TabelasEnum.VO_CARTAO;

    @PostConstruct
    public void init() {
        logger.trace("============= TemplateBean init =============");
        try {
            super.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER));
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new VoCartao());
            VoCartao voCartao = new VoCartao();
            voCartao.setTb_ussd_convertido(true);
            super.clearVarsAdd(voCartao);

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void reloadTabela() {
        try {
            super.loadTabela(tabelasEnum);
            super.clearVarsEdit(new VoCartao());
            VoCartao voCartao = new VoCartao();
            voCartao.setTb_ussd_convertido(true);
            super.clearVarsAdd(voCartao);

        } catch (Exception e) {
            logger.error("", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void adicionar() {
        super.adicionar(tabelasEnum);
        VoCartao voCartao = new VoCartao();
        voCartao.setTb_ussd_convertido(true);
        super.clearVarsAdd(voCartao);

        if (!isAdicionarOutra()) {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('sidebarAdd').hide();");
        }
    }

    public void editar() {
        try {
            Map<String, Boolean> collect = getEditTemplate().getEditMap().entrySet().stream().filter(x -> x.getValue().equals(Boolean.TRUE)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            if (collect == null || collect.isEmpty()) {
                throw new Exception("Selecione pelo menos um checkbox para edição!");
            }

            getEditTemplate().normalize();
            for (Template configOfertasPre : getSelectedTemplateList()) {
                configOfertasPre.setHistory(JsonUtils.addToJsonArray(configOfertasPre.getDt_gmud() == null ? null : configOfertasPre.getHistory(), getEditTemplate()));
                logger.trace(JsonUtils.toJson(configOfertasPre.getHistory()));
            }

            super.editar(tabelasEnum);

            super.clearVarsEdit(new VoCartao());

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
        if (getSelectedTemplateList().size() == 1) {
            setEditTemplate(new VoCartao(getSelectedTemplateList().get(0)));
            getEditTemplate().setUser(super.getUser());
            setNewTemplate(new VoCartao(getSelectedTemplateList().get(0)));
            getNewTemplate().setUser(super.getUser());
        } else {
            setEditTemplate(new VoCartao());
            getEditTemplate().setUser(super.getUser());
            setNewTemplate(new VoCartao());
            getNewTemplate().setUser(super.getUser());
        }
        if (getSelectedTemplateList().size() > 0) {
            super.setGmudToDelete(getSelectedTemplateList().get(0).getDt_gmud());
        } else {
            super.setGmudToDelete(null);
        }
    }

    public void generateScriptFile(String aplicacao) {
        logger.trace("SCRIPT gmud:{}|{}", super.getDefaultFilterValue(), aplicacao);
        StringBuilder script = new StringBuilder();

        List<VoCartao> list = null;

        list = getTemplateList().stream().filter(c -> super.getDefaultFilterValue().equals(c.getDtGmudStr())).collect(Collectors.toList());

        if (list != null) {
            StringBuilder sb;

            InputStream inputStream = null;
            int i;
            for (VoCartao registro : list) {
                sb = new StringBuilder();

                switch (aplicacao) {
                    case "USSD":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM ").append(tabelasEnum.name()).append(";\n");
                            script.append(sb.toString());

                        } else {
                            for (VoCartao registroDel : list) {
                                sb.append("DELETE FROM ").append(tabelasEnum.name());

                                sb.append(" WHERE ");
                                sb.append("IDBENEFICIO = ").append(registroDel.getIdbeneficio()).append(" AND ");
                                sb.append("IDPROGRAMA = ").append(registroDel.getIdprograma()).append(" AND ");
                                sb.append("DDD = '").append(registroDel.getDdd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }

                        sb.append("INSERT INTO VO_CARTAO ");
                        sb.append("(IDBENEFICIO,IDPROGRAMA,DDD,INCENTIVO1,INCENTIVO2,OUTROSPLANOS,OFERTAPROMO1,OFERTAPROMO2,TB_TIPO_BONUS,DATA_INIC,DATA_FIM,TAXAADESAO,TARIFASMSDADOSWIFI,TARIFASMS,TARIFADADOS,TARIFAVOZ,TARIFAVOZFIXO,RECARGAMIN,ELEGIVEL_NOVA_PROMO,ELEGIVEL_PROMO_TEMPLATE_PRIMARIA,PRIORIDADE,OFERTAR_SEMPROMO,PRECONTRATACAO,COBRARTAXA,TB_USSD_CONVERTIDO) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16,VAR17,VAR18,VAR19,VAR20,VAR21,VAR22,VAR23,VAR24,VAR25);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getIdbeneficio() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIdprograma() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIncentivo1() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIncentivo2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOutrosplanos() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertapromo1() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertapromo2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_tipo_bonus() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getData_inic() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getData_fim() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTaxaadesao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTarifasmsdadoswifi() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTarifasms() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTarifadados() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTarifavoz() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTarifavozfixo() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecargamin() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getElegivel_nova_promoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getElegivel_promo_template_primariaStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getPrioridade() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertar_sempromoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getPrecontratacaoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCobrartaxa() + "',")
                                .replace("VAR" + (i++), "'" + registro.getTb_ussd_convertidoStr() + "'")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .replace("ELEGIVEL_PROMO_TEMPLATE_PRIMARIA", "ELEGIVEL_PROMO_TEMPLATE_PRIM")
                                        .getBytes(Charset.forName("UTF-8")));

                        break;

                    case "144":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM TB_PROMO_FALEXCENT_CARTAO;\n");
                            script.append(sb.toString());

                        } else {
                            for (VoCartao registroDel : list) {
                                sb.append("DELETE FROM TB_PROMO_FALEXCENT_CARTAO");

                                sb.append(" WHERE ");
                                sb.append("IDBENEFICIO = ").append(registroDel.getIdbeneficio()).append(" AND ");
                                sb.append("IDPROGRAMA = ").append(registroDel.getIdprograma()).append(" AND ");
                                sb.append("DDD = '").append(registroDel.getDdd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }

                        sb.append("INSERT INTO TB_PROMO_FALEXCENT_CARTAO ");
                        sb.append("(IDBENEFICIO,IDPROGRAMA,DDD,INCENTIVO1,INCENTIVO2,OUTROSPLANOS,OFERTAPROMO1,OFERTAPROMO2,TB_TIPO_BONUS,DATA_INIC,DATA_FIM,TAXAADESAO,TARIFASMSDADOSWIFI,TARIFASMS,TARIFADADOS,TARIFAVOZ,TARIFAVOZFIXO,RECARGAMIN,ELEGIVEL_NOVA_PROMO,ELEGIVEL_PROMO_TEMPLATE_PRIMARIA,PRIORIDADE,OFERTAR_SEMPROMO,PRECONTRATACAO,COBRARTAXA,TB_GATILHOOFERTASFLEX) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16,VAR17,VAR18,VAR19,VAR20,VAR21,VAR22,VAR23,VAR24,VAR25);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getIdbeneficio() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIdprograma() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIncentivo1() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIncentivo2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOutrosplanos() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertapromo1() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertapromo2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_tipo_bonus() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getData_inic() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getData_fim() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTaxaadesao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTarifasmsdadoswifi() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTarifasms() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTarifadados() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTarifavoz() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTarifavozfixo() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecargamin() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getElegivel_nova_promoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getElegivel_promo_template_primariaStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getPrioridade() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertar_sempromoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getPrecontratacaoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCobrartaxa() + "',")
                                .replace("VAR" + (i++), "'" + registro.getTb_gatilhoofertasflex() + "'")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .replace("TB_TIPO_BONUS", "TIPO_BONUS")
                                        .replace("DATA_INIC", "DT_INICIO_PROMO")
                                        .replace("DATA_FIM", "DT_FIM_PROMO")
                                        .replace("TARIFASMSDADOSWIFI", "TARIFA_SMS_DADOS_WIFI")
                                        .replace("ELEGIVEL_PROMO_TEMPLATE_PRIMARIA", "ELEGIVEL_TEMPLATE_PRIMARIA")
                                        .replace("TB_GATILHOOFERTASFLEX", "GATILHO_OFERTAS_FLEX")
                                        .getBytes(Charset.forName("UTF-8")));

                        break;

                    case "880":
                        if (super.getDefaultFilterValue() == null) {
                            sb.append("DELETE FROM TPT_SEC_CARTAO_VO;\n");
                            script.append(sb.toString());

                        } else {
                            for (VoCartao registroDel : list) {
                                sb.append("DELETE FROM TPT_SEC_CARTAO_VO");

                                sb.append(" WHERE ");
                                sb.append("IDBENEFICIO = ").append(registroDel.getIdbeneficio()).append(" AND ");
                                sb.append("IDPROGRAMA = ").append(registroDel.getIdprograma()).append(" AND ");
                                sb.append("DDD = '").append(registroDel.getDdd()).append("';\n");
                                script.append(sb.toString());
                            }
                        }

                        sb.append("INSERT INTO TPT_SEC_CARTAO_VO ");
                        sb.append("(IDBENEFICIO,IDPROGRAMA,DDD,INCENTIVO1,INCENTIVO2,OUTROSPLANOS,OFERTAPROMO1,OFERTAPROMO2,TB_TIPO_BONUS,DATA_INIC,DATA_FIM,TAXAADESAO,TARIFASMSDADOSWIFI,TARIFASMS,TARIFADADOS,TARIFAVOZ,TARIFAVOZFIXO,RECARGAMIN,ELEGIVEL_NOVA_PROMO,ELEGIVEL_PROMO_TEMPLATE_PRIMARIA,PRIORIDADE,OFERTAR_SEMPROMO,PRECONTRATACAO,COBRARTAXA,TB_GATILHOOFERTASFLEX) ");
                        sb.append("VALUES (VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16,VAR17,VAR18,VAR19,VAR20,VAR21,VAR22,VAR23,VAR24,VAR25);");

                        i = 1;
                        script.append(sb.toString()
                                .replace("VAR" + (i++) + ",", "'" + registro.getIdbeneficio() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIdprograma() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getDdd() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIncentivo1() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getIncentivo2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOutrosplanos() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertapromo1() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertapromo2() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTb_tipo_bonus() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getData_inic() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getData_fim() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTaxaadesao() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTarifasmsdadoswifi() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTarifasms() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTarifadados() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTarifavoz() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getTarifavozfixo() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getRecargamin() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getElegivel_nova_promoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getElegivel_promo_template_primariaStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getPrioridade() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getOfertar_sempromoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getPrecontratacaoStr() + "',")
                                .replace("VAR" + (i++) + ",", "'" + registro.getCobrartaxa() + "',")
                                .replace("VAR" + (i++), "'" + registro.getTb_gatilhoofertasflex() + "'")
                        ).append("\n");

                        inputStream = new ByteArrayInputStream(
                                script.toString()
                                        .replace("IDBENEFICIO", "ID_BENEFICIO")
                                        .replace("IDPROGRAMA", "ID_PROGRAMA")
                                        .replace("TB_TIPO_BONUS", "TIPO_BONUS")
                                        .replace("DATA_INIC", "DT_INICIO_PROMO")
                                        .replace("DATA_FIM", "DT_FIM_PROMO")
                                        .replace("TB_GATILHOOFERTASFLEX", "GATILHOOFERTASFLEX")
                                        .getBytes(Charset.forName("UTF-8")));

                        break;
                }

            }

            DefaultStreamedContent defaultStreamedContent = new DefaultStreamedContent();
            defaultStreamedContent.setStream(inputStream);
            defaultStreamedContent.setName("vo_cartao.sql");
            defaultStreamedContent.setContentType("text/plain");

            super.setScriptFile(defaultStreamedContent);

        }
    }

    @Override
    public List<VoCartao> getNewTemplateList() {
        return (List<VoCartao>) (Object) super.getNewTemplateList();
    }

    @Override
    public List<VoCartao> getTemplateList() {
        return (List<VoCartao>) (Object) super.getTemplateList();
    }

    @Override
    public List<VoCartao> getSelectedTemplateList() {
        return (List<VoCartao>) (Object) super.getSelectedTemplateList();
    }

    @Override
    public VoCartao getNewTemplate() {
        return (VoCartao) super.getNewTemplate();
    }

    public VoCartao getNewTemplate(Integer size, Integer index, String ddd, String idBeneficio, String idPrograma, Date dt_gmud) {
        if (super.getNewTemplateList().size() <= index) {
            for (int i = 0; i < index; i++) {
                if (i == index) {
                    VoCartao voCartao = new VoCartao();
                    voCartao.setDdd(ddd);
                    voCartao.setIdbeneficio(idBeneficio);
                    voCartao.setIdprograma(idPrograma);
                    voCartao.setDt_gmud(dt_gmud);
                    voCartao.setUser(getUser());
                    super.getNewTemplateList().add(voCartao);

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
            VoCartao voCartao = new VoCartao();
            voCartao.setDdd(ddd);
            voCartao.setIdbeneficio(idBeneficio);
            voCartao.setIdprograma(idPrograma);
            voCartao.setDt_gmud(dt_gmud);
            voCartao.setUser(getUser());
            super.getNewTemplateList().set(index, voCartao);
        } else {
            ((VoCartao) super.getNewTemplate(index)).setDdd(ddd);
            ((VoCartao) super.getNewTemplate(index)).setIdbeneficio(idBeneficio);
            ((VoCartao) super.getNewTemplate(index)).setIdprograma(idPrograma);
            ((VoCartao) super.getNewTemplate(index)).setDt_gmud(dt_gmud);
            ((VoCartao) super.getNewTemplate(index)).setUser(getUser());
        }

        return (VoCartao) super.getNewTemplate(index);
    }

    @Override
    public VoCartao getEditTemplate() {
        return (VoCartao) super.getEditTemplate();
    }

    @Override
    public void setTemplateList(List<VoCartao> configOfertasPreList) {
        super.setTemplateList(configOfertasPreList);
    }

    @Override
    public void setSelectedTemplateList(List<VoCartao> selectedTemplateList) {
        super.setSelectedTemplateList(selectedTemplateList);
    }

    @Override
    public void setNewTemplate(VoCartao newTemplate) {
        super.setNewTemplate(newTemplate);
    }

    @Override
    public void setEditTemplate(VoCartao editTemplate) {
        super.setEditTemplate(editTemplate);
    }

    public List<VoCartao> getSelectedHistoryList() {
        if (super.getSelectedTemplateList() != null && super.getSelectedTemplateList().size() > 0) {
            List<VoCartao> history = (List<VoCartao>) (Object) super.getSelectedTemplateList().get(0).getHistory();

            for (VoCartao bean : history) {
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

    public LazyDataModel<VoCartao> getDataModel() {
        return (LazyDataModel<VoCartao>) (Object) super.getDataModel();
    }

}
