/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.oi.promocoesweb.template.entity.BoCartao;
import com.oi.promocoesweb.template.entity.BoControle;
import com.oi.promocoesweb.template.entity.CadFrasePre;
import com.oi.promocoesweb.template.entity.CadTemplatePre;
import com.oi.promocoesweb.template.entity.CadTemplatePreOcs;
import com.oi.promocoesweb.template.entity.CartaoMenu;
import com.oi.promocoesweb.template.entity.ConfigOfertasPre;
import com.oi.promocoesweb.template.entity.FrasePre;
import com.oi.promocoesweb.template.entity.FrasePreOcs;
import com.oi.promocoesweb.template.entity.FraseologiasOfertasPre;
import com.oi.promocoesweb.template.entity.FrasesCartao;
import com.oi.promocoesweb.template.entity.FrasesControle;
import com.oi.promocoesweb.template.entity.FrasesControleCad;
import com.oi.promocoesweb.template.entity.FrasesPortabilidade;
import com.oi.promocoesweb.template.entity.FrasesPortabilidadeOcs;
import com.oi.promocoesweb.template.entity.IncentivoOfertasPre;
import com.oi.promocoesweb.template.entity.IncentivoOfertasPreS2S;
import com.oi.promocoesweb.template.entity.IncentivosPortabilidade;
import com.oi.promocoesweb.template.entity.IncentivosPortabilidadeOcs;
import com.oi.promocoesweb.template.entity.IoCartaoCartao;
import com.oi.promocoesweb.template.entity.IoCartaoCartaoS2S;
import com.oi.promocoesweb.template.entity.IoCartaoControle;
import com.oi.promocoesweb.template.entity.IoControleControle;
import com.oi.promocoesweb.template.entity.IoControleControleS2S;
import com.oi.promocoesweb.template.entity.OfertasPortabilidade;
import com.oi.promocoesweb.template.entity.OfertasPortabilidadeOcs;
import com.oi.promocoesweb.template.entity.OiCMenu;
import com.oi.promocoesweb.template.entity.OiCPromocoes;
import com.oi.promocoesweb.template.entity.PromocoesCartao;
import com.oi.promocoesweb.template.entity.Prompt;
import com.oi.promocoesweb.template.entity.PromptCartaoMenu;
import com.oi.promocoesweb.template.entity.PromptControleMenu;
import com.oi.promocoesweb.template.entity.PromptsOfertasPre;
import com.oi.promocoesweb.template.entity.TbTemplate;
import com.oi.promocoesweb.template.entity.TbTemplateControle;
import com.oi.promocoesweb.template.entity.TbTemplateOcs;
import com.oi.promocoesweb.template.entity.Template;
import com.oi.promocoesweb.template.entity.VoCartao;
import com.oi.promocoesweb.template.entity.VoControle;
import com.oi.promocoesweb.template.enums.TabelasEnum;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mpma0
 */
public class JsonUtils {
    
    private static final Logger logger = LogManager.getLogger();

    public static String toJson(List<Object> list) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        objectMapper.writeValue(stringWriter, list);

        return stringWriter.getBuffer().toString();
    }

    public static String toJson(Object obj) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        objectMapper.writeValue(stringWriter, obj);

        return stringWriter.getBuffer().toString();
    }

    public static <T> List<T> fromJsonList(String json, Class<T> typeClass) throws IOException {
        if (json == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();

        List<T> ppl2 = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, typeClass));

        return ppl2;
    }

    public static Object fromJson(String json, Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Object pp1 = mapper.readValue(json, obj.getClass());

        return pp1;
    }

    public static List<BoCartao> fromJsonBoCartao(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        BoCartao[] pp1 = mapper.readValue(json, BoCartao[].class);

        List<BoCartao> ppl2 = Arrays.asList(mapper.readValue(json, BoCartao[].class));

        return ppl2;
    }

    public static String addToJsonArray(List<? extends Template> list, BoCartao bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());
        objectNode.put("idbeneficio", bean.getIdbeneficio());
        objectNode.put("idprograma", bean.getIdprograma());

        if (bean.isEditCpfxc_promopadrao()) {
            objectNode.put("cpfxc_promopadrao", bean.getCpfxc_promopadrao());
        }
        if (bean.isEditCpfxc_semrecarga()) {
            objectNode.put("cpfxc_semrecarga", bean.getCpfxc_semrecarga());
        }
        if (bean.isEditCpfxc_chamada()) {
            objectNode.put("cpfxc_chamada", bean.getCpfxc_chamada());
        }
        if (bean.isEditCpfxc_detalhes()) {
            objectNode.put("cpfxc_detalhes", bean.getCpfxc_detalhes());
        }
        if (bean.isEditCpfxc_recarga()) {
            objectNode.put("cpfxc_recarga", bean.getCpfxc_recarga());
        }
        if (bean.isEditCpfxc_informavalidade()) {
            objectNode.put("cpfxc_informavalidade", bean.getCpfxc_informavalidade());
        }
        if (bean.isEditCpfxc_validade()) {
            objectNode.put("cpfxc_validade", bean.getCpfxc_validade());
        }
        if (bean.isEditCpfxc_contingencia()) {
            objectNode.put("cpfxc_contingencia", bean.getCpfxc_contingencia());
        }
        if (bean.isEditNaocpfxc_informapromo()) {
            objectNode.put("naocpfxc_informapromo", bean.getNaocpfxc_informapromo());
        }
        if (bean.isEditNaocpfxc_validade()) {
            objectNode.put("naocpfxc_validade", bean.getNaocpfxc_validade());
        }
        if (bean.isEditNaocpfxc_regras()) {
            objectNode.put("naocpfxc_regras", bean.getNaocpfxc_regras());
        }
        if (bean.isEditNaocpfxc_recarga()) {
            objectNode.put("naocpfxc_recarga", bean.getNaocpfxc_recarga());
        }
        if (bean.isEditIncentivo_oferta_pre()) {
            objectNode.put("incentivo_oferta_pre", bean.getIncentivo_oferta_pre());
        }
        if (bean.isEditFallback()) {
            objectNode.put("fallback", bean.getFallback());
        }
        if (bean.isEditJacliente_sva()) {
            objectNode.put("jacliente_sva", bean.getJacliente_sva());
        }
        if (bean.isEditInformasaldopromo()) {
            objectNode.put("informasaldopromo", bean.getInformasaldopromo());
        }
        if (bean.isEditOfertamigrados()) {
            objectNode.put("ofertamigrados", bean.getOfertamigrados());
        }
        if (bean.isEditNaocps_infop_ussd()) {
            objectNode.put("naocps_infop_ussd", bean.getNaocps_infop_ussd());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, ConfigOfertasPre bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode nodeUser = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", nodeUser);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());
        objectNode.put("id_ofertaocs", bean.getId_ofertaocs());

        if (bean.isEditId_beneficio()) {
            objectNode.put("id_beneficio", bean.getId_beneficio());
        }
        if (bean.isEditId_programa()) {
            objectNode.put("id_programa", bean.getId_programa());
        }
        if (bean.isEditId_campanhasiebel()) {
            objectNode.put("id_campanhasiebel", bean.getId_campanhasiebel());
        }
        if (bean.isEditId_ofertasiebel()) {
            objectNode.put("id_ofertasiebel", bean.getId_ofertasiebel());
        }
        if (bean.isEditData_inic()) {
            objectNode.put("data_inic", bean.getData_inic().getTime());
        }
        if (bean.isEditData_fim()) {
            objectNode.put("data_fim", bean.getData_fim().getTime());
        }
        if (bean.isEditTipo_oferta()) {
            objectNode.put("tipo_oferta", bean.getTipo_oferta());
        }
        if (bean.isEditElegivelconversao()) {
            objectNode.put("elegivelconversao", bean.getElegivelconversaoStr());
        }
        if (bean.isEditFreeunitvozelegivel()) {
            objectNode.put("freeunitvozelegivel", bean.getFreeunitvozelegivel());
        }
        if (bean.isEditFreeunitdadoselegivel()) {
            objectNode.put("freeunitdadoselegivel", bean.getFreeunitdadoselegivel());
        }
        if (bean.isEditLimmaxconvmin()) {
            objectNode.put("limmaxconvmin", bean.getLimmaxconvmin());
        }
        if (bean.isEditLimmaxconvmb()) {
            objectNode.put("limmaxconvmb", bean.getLimmaxconvmb());
        }
        if (bean.isEditSaldomindadosconvmb()) {
            objectNode.put("saldomindadosconvmb", bean.getSaldomindadosconvmb());
        }
        if (bean.isEditValor_TaxAdesao()) {
            objectNode.put("valor_taxaadesao", bean.getValor_taxaadesao());
        }
        if (bean.isEditExistefallback()) {
            objectNode.put("existefallback", bean.getExistefallbackStr());
        }
        if (bean.isEditExisterollover()) {
            objectNode.put("existerollover", bean.getExisterolloverStr());
        }
        if (bean.isEditShort_code()) {
            objectNode.put("short_code", bean.getShort_code());
        }
        if (bean.isEditUssd_convertido()) {
            objectNode.put("ussd_convertido", bean.getUssd_convertidoStr());
        }
        if (bean.isEditIdOfertaRentCycle()) {
            objectNode.put("id_oferta_rentcycle", bean.getId_oferta_rentcycle());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, PromptsOfertasPre bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());
        objectNode.put("id_bfpg", bean.getId_bfpg());
        objectNode.put("id_ofertaocs", bean.getId_ofertaocs());

        if (bean.isEditJacliente_oferta()) {
            objectNode.put("jacliente_oferta", bean.getJacliente_oferta());
        }
        if (bean.isEditJacliente_sva()) {
            objectNode.put("jacliente_sva", bean.getJacliente_sva());
        }
        if (bean.isEditDetalhesoferta()) {
            objectNode.put("detalhesoferta", bean.getDetalhesoferta());
        }
        if (bean.isEditRegrarollover()) {
            objectNode.put("regrarollover", bean.getRegrarollover());
        }
        if (bean.isEditRegrafallback()) {
            objectNode.put("regrafallback", bean.getRegrafallback());
        }
        if (bean.isEditOfertamigrados()) {
            objectNode.put("ofertamigrados", bean.getOfertamigrados());
        }
        if (bean.isEditNaocliente_informaoferta()) {
            objectNode.put("naocliente_informaoferta", bean.getNaocliente_informaoferta());
        }
        if (bean.isEditNaocliente_incentivooferta()) {
            objectNode.put("naocliente_incentivooferta", bean.getNaocliente_incentivooferta());
        }
        if (bean.isEditIncentivooferta()) {
            objectNode.put("incentivooferta", bean.getIncentivooferta());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, FraseologiasOfertasPre bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());
        objectNode.put("id_ofertaocs", bean.getId_ofertaocs());

        if (bean.isEditId_beneficio()) {
            objectNode.put("id_beneficio", bean.getId_beneficio());
        }
        if (bean.isEditId_programa()) {
            objectNode.put("id_programa", bean.getId_programa());
        }
        if (bean.isEditJacliente_oferta_ussd()) {
            objectNode.put("jacliente_oferta_ussd", bean.getJacliente_oferta_ussd());
        }
        if (bean.isEditJacliente_sva_ussd()) {
            objectNode.put("jacliente_sva_ussd", bean.getJacliente_sva_ussd());
        }
        if (bean.isEditDetalhesoferta_ussd()) {
            objectNode.put("detalhesoferta_ussd", bean.getDetalhesoferta_ussd());
        }
        if (bean.isEditRegrafallback_ussd()) {
            objectNode.put("regrafallback_ussd", bean.getRegrafallback_ussd());
        }
        if (bean.isEditRegrarollover_ussd()) {
            objectNode.put("regrarollover_ussd", bean.getRegrarollover_ussd());
        }
        if (bean.isEditNaocliente_informaoferta_ussd()) {
            objectNode.put("naocliente_informaoferta_ussd", bean.getNaocliente_informaoferta_ussd());
        }
        if (bean.isEditNaocliente_incentivooferta_ussd()) {
            objectNode.put("naocliente_incentivooferta_ussd", bean.getNaocliente_incentivooferta_ussd());
        }
        if (bean.isEditOfertamigrados_ussd()) {
            objectNode.put("ofertamigrados_ussd", bean.getOfertamigrados_ussd());
        }
        if (bean.isEditJacliente_oferta_144()) {
            objectNode.put("jacliente_oferta_144", bean.getJacliente_oferta_144());
        }
        if (bean.isEditJacliente_sva_144()) {
            objectNode.put("jacliente_sva_144", bean.getJacliente_sva_144());
        }
        if (bean.isEditDetalhesoferta_144()) {
            objectNode.put("detalhesoferta_144", bean.getDetalhesoferta_144());
        }
        if (bean.isEditRegrafallback_144()) {
            objectNode.put("regrafallback_144", bean.getRegrafallback_144());
        }
        if (bean.isEditRegrarollover_144()) {
            objectNode.put("regrarollover_144", bean.getRegrarollover_144());
        }
        if (bean.isEditNaocliente_informaoferta_144()) {
            objectNode.put("naocliente_informaoferta_144", bean.getNaocliente_informaoferta_144());
        }
        if (bean.isEditNaocliente_incentivooferta_144()) {
            objectNode.put("naocliente_incentivooferta_144", bean.getNaocliente_incentivooferta_144());
        }
        if (bean.isEditOfertamigrados_144()) {
            objectNode.put("ofertamigrados_144", bean.getOfertamigrados_144());
        }
        
        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, IncentivoOfertasPre bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());

        if (bean.isEditRecacumuladainicial()) {
            objectNode.put("recacumuladainicial", bean.getRecacumuladainicial());
        }
        if (bean.isEditRecacumuladafinal()) {
            objectNode.put("recacumuladafinal", bean.getRecacumuladafinal());
        }
        if (bean.isEditId_ofertaocs1()) {
            objectNode.put("ofertapromo1_id_ofertaocs", bean.getOfertapromo1_id_ofertaocs());
        }
        if (bean.isEditId_ofertaocs2()) {
            objectNode.put("ofertapromo2_id_ofertaocs", bean.getOfertapromo2_id_ofertaocs());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, VoCartao bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());
        objectNode.put("idbeneficio", bean.getIdbeneficio());
        objectNode.put("idprograma", bean.getIdprograma());

        if (bean.isEditIncentivo1()) {
            objectNode.put("incentivo1", bean.getIncentivo1());
        }
        if (bean.isEditIncentivo2()) {
            objectNode.put("incentivo2", bean.getIncentivo2());
        }
        if (bean.isEditOutrosplanos()) {
            objectNode.put("outrosplanos", bean.getOutrosplanos());
        }
        if (bean.isEditOfertapromo1()) {
            objectNode.put("ofertapromo1", bean.getOfertapromo1());
        }
        if (bean.isEditOfertapromo2()) {
            objectNode.put("ofertapromo2", bean.getOfertapromo2());
        }
        if (bean.isEditTb_tipo_bonus()) {
            objectNode.put("tb_tipo_bonus", bean.getTb_tipo_bonus());
        }
        if (bean.isEditData_inic()) {
            objectNode.put("data_inic", bean.getData_inic().getTime());
        }
        if (bean.isEditData_fim()) {
            objectNode.put("data_fim", bean.getData_fim().getTime());
        }
        if (bean.isEditTaxaadesao()) {
            objectNode.put("taxaadesao", bean.getTaxaadesao());
        }
        if (bean.isEditTarifasmsdadoswifi()) {
            objectNode.put("tarifasmsdadoswifi", bean.getTarifasmsdadoswifi());
        }
        if (bean.isEditTarifasms()) {
            objectNode.put("tarifasms", bean.getTarifasms());
        }
        if (bean.isEditTarifadados()) {
            objectNode.put("tarifadados", bean.getTarifadados());
        }
        if (bean.isEditTarifavoz()) {
            objectNode.put("tarifavoz", bean.getTarifavoz());
        }
        if (bean.isEditTarifavozfixo()) {
            objectNode.put("tarifavozfixo", bean.getTarifavozfixo());
        }
        if (bean.isEditRecargamin()) {
            objectNode.put("recargamin", bean.getRecargamin());
        }
        if (bean.isEditElegivel_nova_promo()) {
            objectNode.put("elegivel_nova_promo", bean.getElegivel_nova_promoStr());
        }
        if (bean.isEditElegivel_promo_template_primaria()) {
            objectNode.put("elegivel_promo_template_primaria", bean.getElegivel_promo_template_primariaStr());
        }
        if (bean.isEditPrioridade()) {
            objectNode.put("prioridade", bean.getPrioridade());
        }
        if (bean.isEditOfertar_sempromo()) {
            objectNode.put("ofertar_sempromo", bean.getOfertar_sempromoStr());
        }
        if (bean.isEditPrecontratacao()) {
            objectNode.put("precontratacao", bean.getPrecontratacao());
        }
        if (bean.isEditCobrartaxa()) {
            objectNode.put("cobrartaxa", bean.getCobrartaxa());
        }
        if (bean.isEditTb_ussd_convertido()) {
            objectNode.put("tb_ussd_convertido", bean.getTb_ussd_convertidoStr());
        }
        if (bean.isEditTb_gatilhoofertasflex()) {
            objectNode.put("tb_gatilhoofertasflex", bean.getTb_gatilhoofertasflex());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, FrasesCartao bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());
        objectNode.put("idbeneficio", bean.getIdbeneficio());
        objectNode.put("idprograma", bean.getIdprograma());

        if (bean.isEditPromopadrao_ussd()) {
            objectNode.put("promopadrao_ussd", bean.getPromopadrao_ussd());
        }
        if (bean.isEditSemrecarga_ussd()) {
            objectNode.put("semrecarga_ussd", bean.getSemrecarga_ussd());
        }
        if (bean.isEditChamada_ussd()) {
            objectNode.put("chamada_ussd", bean.getChamada_ussd());
        }
        if (bean.isEditDetalhes_ussd()) {
            objectNode.put("detalhes_ussd", bean.getDetalhes_ussd());
        }
        if (bean.isEditRecarga_ussd()) {
            objectNode.put("recarga_ussd", bean.getRecarga_ussd());
        }
        if (bean.isEditInformavalidade_ussd()) {
            objectNode.put("informavalidade_ussd", bean.getInformavalidade_ussd());
        }
        if (bean.isEditValidade_ussd()) {
            objectNode.put("validade_ussd", bean.getValidade_ussd());
        }
        if (bean.isEditContingencia_ussd()) {
            objectNode.put("contingencia_ussd", bean.getContingencia_ussd());
        }
        if (bean.isEditNaocliente_informapromo_ussd()) {
            objectNode.put("naocliente_informapromo_ussd", bean.getNaocliente_informapromo_ussd());
        }
        if (bean.isEditNaocliente_validade_ussd()) {
            objectNode.put("naocliente_validade_ussd", bean.getNaocliente_validade_ussd());
        }
        if (bean.isEditRegras_ussd()) {
            objectNode.put("regras_ussd", bean.getRegras_ussd());
        }
        if (bean.isEditNaocliente_recarga_ussd()) {
            objectNode.put("naocliente_recarga_ussd", bean.getNaocliente_recarga_ussd());
        }
        if (bean.isEditIncentivo_oferta_pre_ussd()) {
            objectNode.put("incentivo_oferta_pre_ussd", bean.getIncentivo_oferta_pre_ussd());
        }
        if (bean.isEditFallback_ussd()) {
            objectNode.put("fallback_ussd", bean.getFallback_ussd());
        }
        if (bean.isEditSva_ussd()) {
            objectNode.put("sva_ussd", bean.getSva_ussd());
        }
        if (bean.isEditInformasaldopromo_ussd()) {
            objectNode.put("informasaldopromo_ussd", bean.getInformasaldopromo_ussd());
        }
        if (bean.isEditOfertamigrados_ussd()) {
            objectNode.put("ofertamigrados_ussd", bean.getOfertamigrados_ussd());
        }
        if (bean.isEditInformapromo_ussd_ussd()) {
            objectNode.put("informapromo_ussd_ussd", bean.getInformapromo_ussd_ussd());
        }
        if (bean.isEditPromopadrao_144()) {
            objectNode.put("promopadrao_144", bean.getPromopadrao_144());
        }
        if (bean.isEditSemrecarga_144()) {
            objectNode.put("semrecarga_144", bean.getSemrecarga_144());
        }
        if (bean.isEditChamada_144()) {
            objectNode.put("chamada_144", bean.getChamada_144());
        }
        if (bean.isEditDetalhes_144()) {
            objectNode.put("detalhes_144", bean.getDetalhes_144());
        }
        if (bean.isEditRecarga_144()) {
            objectNode.put("recarga_144", bean.getRecarga_144());
        }
        if (bean.isEditInformavalidade_144()) {
            objectNode.put("informavalidade_144", bean.getInformavalidade_144());
        }
        if (bean.isEditValidade_144()) {
            objectNode.put("validade_144", bean.getValidade_144());
        }
        if (bean.isEditContingencia_144()) {
            objectNode.put("contingencia_144", bean.getContingencia_144());
        }
        if (bean.isEditNaocliente_informapromo_144()) {
            objectNode.put("naocliente_informapromo_144", bean.getNaocliente_informapromo_144());
        }
        if (bean.isEditNaocliente_validade_144()) {
            objectNode.put("naocliente_validade_144", bean.getNaocliente_validade_144());
        }
        if (bean.isEditRegras_144()) {
            objectNode.put("regras_144", bean.getRegras_144());
        }
        if (bean.isEditNaocliente_recarga_144()) {
            objectNode.put("naocliente_recarga_144", bean.getNaocliente_recarga_144());
        }
        if (bean.isEditIncentivo_oferta_pre_144()) {
            objectNode.put("incentivo_oferta_pre_144", bean.getIncentivo_oferta_pre_144());
        }
        if (bean.isEditFallback_144()) {
            objectNode.put("fallback_144", bean.getFallback_144());
        }
        if (bean.isEditSva_144()) {
            objectNode.put("sva_144", bean.getSva_144());
        }
        if (bean.isEditInformasaldopromo_144()) {
            objectNode.put("informasaldopromo_144", bean.getInformasaldopromo_144());
        }
        if (bean.isEditOfertamigrados_144()) {
            objectNode.put("ofertamigrados_144", bean.getOfertamigrados_144());
        }
        if (bean.isEditInformapromo_ussd_144()) {
            objectNode.put("informapromo_ussd_144", bean.getInformapromo_ussd_144());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, IoCartaoCartao bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());

        if (bean.isEditRecacumuladainicial()) {
            objectNode.put("recacumuladainicial", bean.getRecacumuladainicial());
        }
        if (bean.isEditRecacumuladafinal()) {
            objectNode.put("recacumuladafinal", bean.getRecacumuladafinal());
        }
        if (bean.isEditOfertapromo1()) {
            objectNode.put("ofertapromo1", bean.getOfertapromo1());
        }
        if (bean.isEditOfertapromo2()) {
            objectNode.put("ofertapromo2", bean.getOfertapromo2());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, IoCartaoControle bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());

        if (bean.isEditTb_incentivo1()) {
            objectNode.put("tb_incentivo1", bean.getTb_incentivo1());
        }
        if (bean.isEditTb_incentivo2()) {
            objectNode.put("tb_incentivo2", bean.getTb_incentivo2());
        }
        if (bean.isEditTb_incentivo3()) {
            objectNode.put("tb_incentivo3", bean.getTb_incentivo3());
        }
        if (bean.isEditTb_incentivo4()) {
            objectNode.put("tb_incentivo4", bean.getTb_incentivo4());
        }
        if (bean.isEditTb_incentivo5()) {
            objectNode.put("tb_incentivo5", bean.getTb_incentivo5());
        }
        if (bean.isEditRecacumuladamesanteriorinicial()) {
            objectNode.put("recacumuladamesanteriorinicial", bean.getRecacumuladamesanteriorinicial());
        }
        if (bean.isEditRecacumuladamesanteriorfinal()) {
            objectNode.put("recacumuladamesanteriorfinal", bean.getRecacumuladamesanteriorfinal());
        }
        if (bean.isEditTipopagamentoincentivo()) {
            objectNode.put("tipopagamentoincentivo", bean.getTipopagamentoincentivo());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, CadTemplatePreOcs bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());

        if (bean.isEditId_campanha_siebel1()) {
            objectNode.put("id_campanha_siebel1", bean.getId_campanha_siebel1());
        }
        if (bean.isEditId_oferta_siebel1()) {
            objectNode.put("id_oferta_siebel1", bean.getId_oferta_siebel1());
        }
        if (bean.isEditId_campanha_siebel2()) {
            objectNode.put("id_campanha_siebel2", bean.getId_campanha_siebel2());
        }
        if (bean.isEditId_oferta_siebel2()) {
            objectNode.put("id_oferta_siebel2", bean.getId_oferta_siebel2());
        }
        if (bean.isEditId_campanha_siebel3()) {
            objectNode.put("id_campanha_siebel3", bean.getId_campanha_siebel3());
        }
        if (bean.isEditId_oferta_siebel3()) {
            objectNode.put("id_oferta_siebel3", bean.getId_oferta_siebel3());
        }
        if (bean.isEditId_campanha_default()) {
            objectNode.put("id_campanha_default", bean.getId_campanha_default());
        }
        if (bean.isEditId_oferta_default()) {
            objectNode.put("id_oferta_default", bean.getId_oferta_default());
        }
        if (bean.isEditMsg_ocs_sva()) {
            objectNode.put("msg_ocs_sva", bean.getMsg_ocs_sva());
        }
        if (bean.isEditTipo_bonus()) {
            objectNode.put("tipo_bonus", bean.getTipo_bonus());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, CadTemplatePre bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());

        if (bean.isEditOferta1()) {
            objectNode.put("oferta1", bean.getOferta1());
        }
        if (bean.isEditOferta2()) {
            objectNode.put("oferta2", bean.getOferta2());
        }
        if (bean.isEditOferta3()) {
            objectNode.put("oferta3", bean.getOferta3());
        }
        if (bean.isEditOferta_erro_in()) {
            objectNode.put("oferta_erro_in", bean.getOferta_erro_in());
        }
        if (bean.isEditOferta_erro_siebel()) {
            objectNode.put("oferta_erro_siebel", bean.getOferta_erro_siebel());
        }
        if (bean.isEditSegprompt()) {
            objectNode.put("segprompt", bean.getSegprompt());
        }
        if (bean.isEditTipo_bonus()) {
            objectNode.put("tipo_bonus", bean.getTipo_bonus());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, FrasePreOcs bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());

        if (bean.isEditIdcampanha()) {
            objectNode.put("idcampanha", bean.getIdcampanha());
        }
        if (bean.isEditIdoferta()) {
            objectNode.put("idoferta", bean.getIdoferta());
        }
        if (bean.isEditMsg_oferta_1()) {
            objectNode.put("msg_oferta_1", bean.getMsg_oferta_1());
        }
        if (bean.isEditMsg_oferta_2()) {
            objectNode.put("msg_oferta_2", bean.getMsg_oferta_2());
        }
        if (bean.isEditMsg_oferta_3()) {
            objectNode.put("msg_oferta_3", bean.getMsg_oferta_3());
        }
        if (bean.isEditMsg_oferta_default()) {
            objectNode.put("msg_oferta_default", bean.getMsg_oferta_default());
        }
        if (bean.isEditMsg_ocs_sva()) {
            objectNode.put("msg_ocs_sva", bean.getMsg_ocs_sva());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, CadFrasePre bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());

        if (bean.isEditCod_oferta()) {
            objectNode.put("cod_oferta", bean.getCod_oferta());
        }
        if (bean.isEditOferta1()) {
            objectNode.put("oferta1", bean.getOferta1());
        }
        if (bean.isEditOferta2()) {
            objectNode.put("oferta2", bean.getOferta2());
        }
        if (bean.isEditOferta3()) {
            objectNode.put("oferta3", bean.getOferta3());
        }
        if (bean.isEditOferta_erro_in()) {
            objectNode.put("oferta_erro_in", bean.getOferta_erro_in());
        }
        if (bean.isEditOferta_erro_siebel()) {
            objectNode.put("oferta_erro_siebel", bean.getOferta_erro_siebel());
        }
        if (bean.isEditSegprompt()) {
            objectNode.put("segprompt", bean.getSegprompt());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }

    }

    public static String addToJsonArray(List<? extends Template> list, VoControle bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());
        objectNode.put("idbeneficio", bean.getIdbeneficio());
        objectNode.put("idprograma", bean.getIdprograma());

        if (bean.isEditTb_tipobonus()) {
            objectNode.put("tb_tipobonus", bean.getTb_tipobonus());
        }
        if (bean.isEditTb_tipoplano()) {
            objectNode.put("tb_tipoplano", bean.getTb_tipoplano());
        }
        if (bean.isEditIncentivo1()) {
            objectNode.put("incentivo1", bean.getIncentivo1());
        }
        if (bean.isEditIncentivo2()) {
            objectNode.put("incentivo2", bean.getIncentivo2());
        }
        if (bean.isEditOutrosplanos()) {
            objectNode.put("outrosplanos", bean.getOutrosplanos());
        }
        if (bean.isEditOffercode()) {
            objectNode.put("offercode", bean.getOffercode());
        }
        if (bean.isEditData_inic()) {
            objectNode.put("data_inic", bean.getData_inic().getTime());
        }
        if (bean.isEditData_fim()) {
            objectNode.put("data_fim", bean.getData_fim().getTime());
        }
        if (bean.isEditTaxaadesao()) {
            objectNode.put("taxaadesao", bean.getTaxaadesao());
        }
        if (bean.isEditTarifasmsdadoswifi()) {
            objectNode.put("tarifasmsdadoswifi", bean.getTarifasmsdadoswifi());
        }
        if (bean.isEditTarifasms()) {
            objectNode.put("tarifasms", bean.getTarifasms());
        }
        if (bean.isEditTarifadados()) {
            objectNode.put("tarifadados", bean.getTarifadados());
        }
        if (bean.isEditTarifavoz()) {
            objectNode.put("tarifavoz", bean.getTarifavoz());
        }
        if (bean.isEditFranquia()) {
            objectNode.put("franquia", bean.getFranquia());
        }
        if (bean.isEditCreditos()) {
            objectNode.put("creditos", bean.getCreditos());
        }
        if (bean.isEditElegivel_nova_promo()) {
            objectNode.put("elegivel_nova_promo", bean.getElegivel_nova_promoStr());
        }
        if (bean.isEditTb_taxa_adesaoboleto()) {
            objectNode.put("tb_taxa_adesaoboleto", bean.getTb_taxa_adesaoboleto());
        }
        if (bean.isEditBolso_ilimitado()) {
            objectNode.put("bolso_ilimitado", bean.getBolso_ilimitado());
        }
        if (bean.isEditBolso_allnet()) {
            objectNode.put("bolso_allnet", bean.getBolso_allnet());
        }
        if (bean.isEditFranquia_boleto()) {
            objectNode.put("franquia_boleto", bean.getFranquia_boleto());
        }
        if (bean.isEditTipo_pagamento()) {
            objectNode.put("tipo_pagamento", bean.getTipo_pagamento());
        }
        if (bean.isEditTb_ussd_convertido()) {
            objectNode.put("tb_ussd_convertido", bean.getTb_ussd_convertidoStr());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, BoControle bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());
        objectNode.put("idbeneficio", bean.getIdbeneficio());
        objectNode.put("idprograma", bean.getIdprograma());

        if (bean.isEditOicjac_promopadrao()) {
            objectNode.put("oicjac_promopadrao", bean.getOicjac_promopadrao());
        }
        if (bean.isEditOicjac_regras()) {
            objectNode.put("oicjac_regras", bean.getOicjac_regras());
        }
        if (bean.isEditOicjac_validade()) {
            objectNode.put("oicjac_validade", bean.getOicjac_validade());
        }
        if (bean.isEditOicjac_saldo()) {
            objectNode.put("oicjac_saldo", bean.getOicjac_saldo());
        }
        if (bean.isEditOicjac_conting()) {
            objectNode.put("oicjac_conting", bean.getOicjac_conting());
        }
        if (bean.isEditOicnaoc_informapromo()) {
            objectNode.put("oicnaoc_informapromo", bean.getOicnaoc_informapromo());
        }
        if (bean.isEditOicnaoc_regras()) {
            objectNode.put("oicnaoc_regras", bean.getOicnaoc_regras());
        }
        if (bean.isEditOicnaoc_validade()) {
            objectNode.put("oicnaoc_validade", bean.getOicnaoc_validade());
        }
        if (bean.isEditOicconectado_incentivo()) {
            objectNode.put("oicconectado_incentivo", bean.getOicconectado_incentivo());
        }
        if (bean.isEditOicconectado_incentivopromo()) {
            objectNode.put("oicconectado_incentivopromo", bean.getOicconectado_incentivopromo());
        }
        if (bean.isEditJacliente_sva()) {
            objectNode.put("jacliente_sva", bean.getJacliente_sva());
        }
        if (bean.isEditOicc_incentivo_comboleto()) {
            objectNode.put("oicc_incentivo_comboleto", bean.getOicc_incentivo_comboleto());
        }
        if (bean.isEditDetalhes_ofertaretencao()) {
            objectNode.put("detalhes_ofertaretencao", bean.getDetalhes_ofertaretencao());
        }
        if (bean.isEditIntemdobro_jacliente()) {
            objectNode.put("intemdobro_jacliente", bean.getIntemdobro_jacliente());
        }
        if (bean.isEditIntemdobro_naocliente()) {
            objectNode.put("intemdobro_naocliente", bean.getIntemdobro_naocliente());
        }
        if (bean.isEditBenint_anteriordtvendaura()) {
            objectNode.put("benint_anteriordtvendaura", bean.getBenint_anteriordtvendaura());
        }
        if (bean.isEditBenint_aposdtvendaura()) {
            objectNode.put("benint_aposdtvendaura", bean.getBenint_aposdtvendaura());
        }
        if (bean.isEditFallback_controle()) {
            objectNode.put("fallback_controle", bean.getFallback_controle());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, PromocoesCartao bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("tb_ddd", bean.getTb_ddd());
        objectNode.put("tb_id_beneficio", bean.getTb_id_beneficio());
        objectNode.put("tb_id_programa", bean.getTb_id_programa());
        objectNode.put("tb_regiao", bean.getTb_regiao());

        if (bean.isEditIncentivo1()) {
            objectNode.put("incentivo1", bean.getIncentivo1());
        }
        if (bean.isEditIncentivo2()) {
            objectNode.put("incentivo2", bean.getIncentivo2());
        }
        if (bean.isEditOutrosplanos()) {
            objectNode.put("outrosplanos", bean.getOutrosplanos());
        }
        if (bean.isEditOfertapromo1()) {
            objectNode.put("ofertapromo1", bean.getOfertapromo1());
        }
        if (bean.isEditOfertapromo2()) {
            objectNode.put("ofertapromo2", bean.getOfertapromo2());
        }
        if (bean.isEditTb_nome_promocao()) {
            objectNode.put("tb_nome_promocao", bean.getTb_nome_promocao());
        }
        if (bean.isEditTb_tipo_promo()) {
            objectNode.put("tb_tipo_promo", bean.getTb_tipo_promo());
        }
        if (bean.isEditTb_sn_elegivel_nova_promo()) {
            objectNode.put("tb_sn_elegivel_nova_promo", bean.getTb_sn_elegivel_nova_promoStr());
        }
        if (bean.isEditTb_taxaadesao()) {
            objectNode.put("tb_taxaadesao", bean.getTb_taxaadesao());
        }
        if (bean.isEditTb_sn_elegivel_estendida()) {
            objectNode.put("tb_sn_elegivel_estendida", bean.getTb_sn_elegivel_estendidaStr());
        }
        if (bean.isEditTb_dt_inicio_promo()) {
            objectNode.put("tb_dt_inicio_promo", bean.getTb_dt_inicio_promoStr());
        }
        if (bean.isEditTb_dt_fim_promo()) {
            objectNode.put("tb_dt_fim_promo", bean.getTb_dt_fim_promoStr());
        }
        if (bean.isEditTb_tipo_bonus()) {
            objectNode.put("tb_tipo_bonus", bean.getTb_tipo_bonus());
        }
        if (bean.isEditTb_recarga_min()) {
            objectNode.put("tb_recarga_min", bean.getTb_recarga_min());
        }
        if (bean.isEditTb_recarga_max()) {
            objectNode.put("tb_recarga_max", bean.getTb_recarga_max());
        }
        if (bean.isEditTb_bonus_min()) {
            objectNode.put("tb_bonus_min", bean.getTb_bonus_min());
        }
        if (bean.isEditTb_bonus_max()) {
            objectNode.put("tb_bonus_max", bean.getTb_bonus_max());
        }
        if (bean.isEditTb_multiplicador()) {
            objectNode.put("tb_multiplicador", bean.getTb_multiplicador());
        }
        if (bean.isEditTb_sn_bonus_torpedo()) {
            objectNode.put("tb_sn_bonus_torpedo", bean.getTb_sn_bonus_torpedoStr());
        }
        if (bean.isEditTb_qtd_torp()) {
            objectNode.put("tb_qtd_torp", bean.getTb_qtd_torp());
        }
        if (bean.isEditTb_bonus_torp()) {
            objectNode.put("tb_bonus_torp", bean.getTb_bonus_torp());
        }
        if (bean.isEditTb_prorrogacao_6_meses()) {
            objectNode.put("tb_prorrogacao_6_meses", bean.getTb_prorrogacao_6_mesesStr());
        }
        if (bean.isEditTb_prorrogacao_12_meses()) {
            objectNode.put("tb_prorrogacao_12_meses", bean.getTb_prorrogacao_12_mesesStr());
        }
        if (bean.isEditTb_valortaxa_6_meses()) {
            objectNode.put("tb_valortaxa_6_meses", bean.getTb_valortaxa_6_meses());
        }
        if (bean.isEditTb_valortaxa_12_meses()) {
            objectNode.put("tb_valortaxa_12_meses", bean.getTb_valortaxa_12_meses());
        }
        if (bean.isEditTb_id_beneficio_6_meses()) {
            objectNode.put("tb_id_beneficio_6_meses", bean.getTb_id_beneficio_6_meses());
        }
        if (bean.isEditTb_id_programa_6_meses()) {
            objectNode.put("tb_id_programa_6_meses", bean.getTb_id_programa_6_meses());
        }
        if (bean.isEditTb_id_beneficio_12_meses()) {
            objectNode.put("tb_id_beneficio_12_meses", bean.getTb_id_beneficio_12_meses());
        }
        if (bean.isEditTb_id_programa_12_meses()) {
            objectNode.put("tb_id_programa_12_meses", bean.getTb_id_programa_12_meses());
        }
        if (bean.isEditTb_bonusbolsodiferenciado()) {
            objectNode.put("tb_bonusbolsodiferenciado", bean.getTb_bonusbolsodiferenciadoStr());
        }
        if (bean.isEditTb_recargabolsodegrau2()) {
            objectNode.put("tb_recargabolsodegrau2", bean.getTb_recargabolsodegrau2());
        }
        if (bean.isEditTb_bolsobonusdegrau2()) {
            objectNode.put("tb_bolsobonusdegrau2", bean.getTb_bolsobonusdegrau2());
        }
        if (bean.isEditTb_recargabolsodegrau3()) {
            objectNode.put("tb_recargabolsodegrau3", bean.getTb_recargabolsodegrau3());
        }
        if (bean.isEditTb_bolsobonusdegrau3()) {
            objectNode.put("tb_bolsobonusdegrau3", bean.getTb_bolsobonusdegrau3());
        }
        if (bean.isEditTb_bonusligacaooimovel()) {
            objectNode.put("tb_bonusligacaooimovel", bean.getTb_bonusligacaooimovelStr());
        }
        if (bean.isEditTb_bonusligacaooimovelfixo()) {
            objectNode.put("tb_bonusligacaooimovelfixo", bean.getTb_bonusligacaooimovelfixoStr());
        }
        if (bean.isEditTb_bonusligacaomix()) {
            objectNode.put("tb_bonusligacaomix", bean.getTb_bonusligacaomixStr());
        }
        if (bean.isEditTb_ligacaooimovelfixo_dados()) {
            objectNode.put("tb_ligacaooimovelfixo_dados", bean.getTb_ligacaooimovelfixo_dadosStr());
        }
        if (bean.isEditTb_bonusligacaomix_dados()) {
            objectNode.put("tb_bonusligacaomix_dados", bean.getTb_bonusligacaomix_dadosStr());
        }
        if (bean.isEditTb_semcarencia()) {
            objectNode.put("tb_semcarencia", bean.getTb_semcarenciaStr());
        }
        if (bean.isEditTem_bce()) {
            objectNode.put("tem_bce", bean.getTem_bceStr());
        }
        if (bean.isEditElegivel_promo_secundaria()) {
            objectNode.put("elegivel_promo_secundaria", bean.getElegivel_promo_secundariaStr());
        }
        if (bean.isEditPrioridade()) {
            objectNode.put("prioridade", bean.getPrioridade());
        }
        if (bean.isEditOfertar_sempromo()) {
            objectNode.put("ofertar_sempromo", bean.getOfertar_sempromo());
        }
        if (bean.isEditPre_contratacao()) {
            objectNode.put("pre_contratacao", bean.getPre_contratacaoStr());
        }
        if (bean.isEditCobrartaxa()) {
            objectNode.put("cobrartaxa", bean.getCobrartaxa());
        }
        if (bean.isEditTb_ussd_convertido()) {
            objectNode.put("tb_ussd_convertido", bean.getTb_ussd_convertidoStr());
        }
        if (bean.isEditTb_categoria()) {
            objectNode.put("tb_categoria", bean.getTb_categoria());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, CartaoMenu bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("tb_ddd", bean.getTb_ddd());
        objectNode.put("tb_id_beneficio", bean.getTb_id_beneficio());
        objectNode.put("tb_id_programa", bean.getTb_id_programa());
        objectNode.put("tb_regiao", bean.getTb_regiao());

        if (bean.isEditUpr1_oi_cpp_minicial()) {
            objectNode.put("upr1_oi_cpp_minicial", bean.getUpr1_oi_cpp_minicial());
        }
        if (bean.isEditUpr1_oi_cpp_regtorpedo()) {
            objectNode.put("upr1_oi_cpp_regtorpedo", bean.getUpr1_oi_cpp_regtorpedo());
        }
        if (bean.isEditUpr1_oi_cpp_regligacao()) {
            objectNode.put("upr1_oi_cpp_regligacao", bean.getUpr1_oi_cpp_regligacao());
        }
        if (bean.isEditUpr1_oi_cppcont_regligacaooimovel()) {
            objectNode.put("upr1_oi_cppcont_regligacaooimovel", bean.getUpr1_oi_cppcont_regligacaooimovel());
        }
        if (bean.isEditUpr1_oi_cppcont_regligacaooimovelfixo()) {
            objectNode.put("upr1_oi_cppcont_regligacaooimovelfixo", bean.getUpr1_oi_cppcont_regligacaooimovelfixo());
        }
        if (bean.isEditUpr1_oi_cppcont_regligacaomix()) {
            objectNode.put("upr1_oi_cppcont_regligacaomix", bean.getUpr1_oi_cppcont_regligacaomix());
        }
        if (bean.isEditUpr1_oi_cpp_comousar()) {
            objectNode.put("upr1_oi_cpp_comousar", bean.getUpr1_oi_cpp_comousar());
        }
        if (bean.isEditUpr1_oi_crbdad5arbi_promodegrau_init2()) {
            objectNode.put("upr1_oi_crbdad5arbi_promodegrau_init2", bean.getUpr1_oi_crbdad5arbi_promodegrau_init2());
        }
        if (bean.isEditUpr1_oi_crbdad5d_recbonusmax_init2()) {
            objectNode.put("upr1_oi_crbdad5d_recbonusmax_init2", bean.getUpr1_oi_crbdad5d_recbonusmax_init2());
        }
        if (bean.isEditUpr1_oi_crbdad5d_recbonusmindegrau2_init2()) {
            objectNode.put("upr1_oi_crbdad5d_recbonusmindegrau2_init2", bean.getUpr1_oi_crbdad5d_recbonusmindegrau2_init2());
        }
        if (bean.isEditUpr1_oi_crbdad5d_recebonusmindegrau3_init2()) {
            objectNode.put("upr1_oi_crbdad5d_recebonusmindegrau3_init2", bean.getUpr1_oi_crbdad5d_recebonusmindegrau3_init2());
        }
        if (bean.isEditUpr1_oi_crbdad5d_recebebonusmindegrau2e3_init2()) {
            objectNode.put("upr1_oi_crbdad5d_recebebonusmindegrau2e3_init2", bean.getUpr1_oi_crbdad5d_recebebonusmindegrau2e3_init2());
        }
        if (bean.isEditUpr1_oi_crbdad5d_recebebonusmax3_init2()) {
            objectNode.put("upr1_oi_crbdad5d_recebebonusmax3_init2", bean.getUpr1_oi_crbdad5d_recebebonusmax3_init2());
        }
        if (bean.isEditUpr1_oi_cinfpromo_mensageminicial()) {
            objectNode.put("upr1_oi_cinfpromo_mensageminicial", bean.getUpr1_oi_cinfpromo_mensageminicial());
        }
        if (bean.isEditUpr1_oi_cinfpromo_regtorpedo()) {
            objectNode.put("upr1_oi_cinfpromo_regtorpedo", bean.getUpr1_oi_cinfpromo_regtorpedo());
        }
        if (bean.isEditUpr1_oi_cinfpromo_regligacao()) {
            objectNode.put("upr1_oi_cinfpromo_regligacao", bean.getUpr1_oi_cinfpromo_regligacao());
        }
        if (bean.isEditUpr1_oi_cinfpromo_comousar()) {
            objectNode.put("upr1_oi_cinfpromo_comousar", bean.getUpr1_oi_cinfpromo_comousar());
        }
        if (bean.isEditUpr1_oi_cinfpromo_validadepromo()) {
            objectNode.put("upr1_oi_cinfpromo_validadepromo", bean.getUpr1_oi_cinfpromo_validadepromo());
        }
        if (bean.isEditUpr1_oi_ccpop_promooicartao()) {
            objectNode.put("upr1_oi_ccpop_promooicartao", bean.getUpr1_oi_ccpop_promooicartao());
        }
        if (bean.isEditUpr1_oi_cinfp_regdados()) {
            objectNode.put("upr1_oi_cinfp_regdados", bean.getUpr1_oi_cinfp_regdados());
        }
        if (bean.isEditUpr1_oi_cpp_regdados()) {
            objectNode.put("upr1_oi_cpp_regdados", bean.getUpr1_oi_cpp_regdados());
        }
        if (bean.isEditIncentivo_oferta_pre()) {
            objectNode.put("incentivo_oferta_pre", bean.getIncentivo_oferta_pre());
        }
        if (bean.isEditJacliente_sva()) {
            objectNode.put("jacliente_sva", bean.getJacliente_sva());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, PromptCartaoMenu bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("tb_ddd", bean.getTb_ddd());
        objectNode.put("tb_id_beneficio", bean.getTb_id_beneficio());
        objectNode.put("tb_id_programa", bean.getTb_id_programa());

        if (bean.isEditCliente_mensageminicial_ussd()) {
            objectNode.put("cliente_mensageminicial_ussd", bean.getCliente_mensageminicial_ussd());
        }
        if (bean.isEditCliente_regratorpedo_ussd()) {
            objectNode.put("cliente_regratorpedo_ussd", bean.getCliente_regratorpedo_ussd());
        }
        if (bean.isEditCliente_regraligacao_ussd()) {
            objectNode.put("cliente_regraligacao_ussd", bean.getCliente_regraligacao_ussd());
        }
        if (bean.isEditCliente_regradados_ussd()) {
            objectNode.put("cliente_regradados_ussd", bean.getCliente_regradados_ussd());
        }
        if (bean.isEditCliente_sva_ussd()) {
            objectNode.put("cliente_sva_ussd", bean.getCliente_sva_ussd());
        }
        if (bean.isEditCliente_comousar_ussd()) {
            objectNode.put("cliente_comousar_ussd", bean.getCliente_comousar_ussd());
        }
        if (bean.isEditRegraligacaooimovel_ussd()) {
            objectNode.put("regraligacaooimovel_ussd", bean.getRegraligacaooimovel_ussd());
        }
        if (bean.isEditRegraligacaooimovelfixo_ussd()) {
            objectNode.put("regraligacaooimovelfixo_ussd", bean.getRegraligacaooimovelfixo_ussd());
        }
        if (bean.isEditRegraligacaomix_ussd()) {
            objectNode.put("regraligacaomix_ussd", bean.getRegraligacaomix_ussd());
        }
        if (bean.isEditRecebebonusiniciopromodegrau_ussd()) {
            objectNode.put("recebebonusiniciopromodegrau_ussd", bean.getRecebebonusiniciopromodegrau_ussd());
        }
        if (bean.isEditRecebebonusmax_ussd()) {
            objectNode.put("recebebonusmax_ussd", bean.getRecebebonusmax_ussd());
        }
        if (bean.isEditRecebebonusmindegrau2_ussd()) {
            objectNode.put("recebebonusmindegrau2_ussd", bean.getRecebebonusmindegrau2_ussd());
        }
        if (bean.isEditRecebebonusmindegrau3_ussd()) {
            objectNode.put("recebebonusmindegrau3_ussd", bean.getRecebebonusmindegrau3_ussd());
        }
        if (bean.isEditRecebebonusmindegrau2e3_ussd()) {
            objectNode.put("recebebonusmindegrau2e3_ussd", bean.getRecebebonusmindegrau2e3_ussd());
        }
        if (bean.isEditRecebebonusmax3_ussd()) {
            objectNode.put("recebebonusmax3_ussd", bean.getRecebebonusmax3_ussd());
        }
        if (bean.isEditNaocliente_mensageminicial_ussd()) {
            objectNode.put("naocliente_mensageminicial_ussd", bean.getNaocliente_mensageminicial_ussd());
        }
        if (bean.isEditNaocliente_regratorpedo_ussd()) {
            objectNode.put("naocliente_regratorpedo_ussd", bean.getNaocliente_regratorpedo_ussd());
        }
        if (bean.isEditNaocliente_regraligacao_ussd()) {
            objectNode.put("naocliente_regraligacao_ussd", bean.getNaocliente_regraligacao_ussd());
        }
        if (bean.isEditNaocliente_comousar_ussd()) {
            objectNode.put("naocliente_comousar_ussd", bean.getNaocliente_comousar_ussd());
        }
        if (bean.isEditNaocliente_validadepromo_ussd()) {
            objectNode.put("naocliente_validadepromo_ussd", bean.getNaocliente_validadepromo_ussd());
        }
        if (bean.isEditNaocliente_regradados_ussd()) {
            objectNode.put("naocliente_regradados_ussd", bean.getNaocliente_regradados_ussd());
        }
        if (bean.isEditPromooicartao_ussd()) {
            objectNode.put("promooicartao_ussd", bean.getPromooicartao_ussd());
        }
        if (bean.isEditOferta_pre_ussd()) {
            objectNode.put("oferta_pre_ussd", bean.getOferta_pre_ussd());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, Prompt bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());

        if (bean.isEditWav()) {
            objectNode.put("wav", bean.getWav());
        }

        if (bean.isEditConteudo()) {
            objectNode.put("conteudo", bean.getConteudo());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }
    ///@jpereirc

    public static String addToJsonArray(List<? extends Template> list, FrasesControle bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode nodeUser = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", nodeUser);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());
        objectNode.put("idprograma", bean.getIdprograma());
        objectNode.put("idbeneficio", bean.getIdbeneficio());

        //
        if (bean.isEditJacliente_promopadrao()) {
            objectNode.put("jacliente_promopadrao", bean.getJacliente_promopadrao());
        }
        if (bean.isEditJacliente_regras()) {
            objectNode.put("jacliente_regras", bean.getJacliente_regras());
        }
        if (bean.isEditJacliente_validade()) {
            objectNode.put("jacliente_validade", bean.getJacliente_validade());
        }
        if (bean.isEditJacliente_saldo()) {
            objectNode.put("jacliente_saldo", bean.getJacliente_saldo());
        }
        if (bean.isEditJacliente_conting()) {
            objectNode.put("jacliente_conting", bean.getJacliente_conting());
        }
        if (bean.isEditNaocliente_informapromo()) {
            objectNode.put("naocliente_informapromo", bean.getNaocliente_informapromo());
        }
        if (bean.isEditNaocliente_regras()) {
            objectNode.put("naocliente_regras", bean.getNaocliente_regras());
        }
        if (bean.isEditNaocliente_validade()) {
            objectNode.put("naocliente_validade", bean.getNaocliente_validade());
        }
        if (bean.isEditConectado_incentivo()) {
            objectNode.put("conectado_incentivo", bean.getConectado_incentivo());
        }
        if (bean.isEditConectado_incentivopromocao()) {
            objectNode.put("conectado_incentivopromocao", bean.getConectado_incentivopromocao());
        }
        if (bean.isEditJacliente_sva()) {
            objectNode.put("jacliente_sva", bean.getJacliente_sva());
        }
        if (bean.isEditIncentivo_comboleto()) {
            objectNode.put("incentivo_comboleto", bean.getIncentivo_comboleto());
        }
        if (bean.isEditDetalhes_ofertaretencao()) {
            objectNode.put("detalhes_ofertaretencao", bean.getDetalhes_ofertaretencao());
        }
        if (bean.isEditInternetemdobro_jacliente()) {
            objectNode.put("internetemdobro_jacliente", bean.getInternetemdobro_jacliente());
        }
        if (bean.isEditInternetemdobro_naocliente()) {
            objectNode.put("internetemdobro_naocliente", bean.getInternetemdobro_naocliente());
        }
        if (bean.isEditBeneficiointernet_anteriordtvendaura()) {
            objectNode.put("beneficiointernet_anteriordtvendaura", bean.getBeneficiointernet_anteriordtvendaura());
        }
        if (bean.isEditBeneficiointernet_aposdtvendaura()) {
            objectNode.put("beneficiointernet_aposdtvendaura", bean.getBeneficiointernet_aposdtvendaura());
        }
        if (bean.isEditFallback()) {
            objectNode.put("fallback", bean.getFallback());
        }

        if (bean.isEditJacliente_promopadrao_144()) {
            objectNode.put("jacliente_promopadrao_144", bean.getJacliente_promopadrao_144());
        }
        if (bean.isEditJacliente_regras_144()) {
            objectNode.put("jacliente_regras_144", bean.getJacliente_regras_144());
        }
        if (bean.isEditJacliente_validade_144()) {
            objectNode.put("jacliente_validade_144", bean.getJacliente_validade_144());
        }
        if (bean.isEditJacliente_saldo_144()) {
            objectNode.put("jacliente_saldo_144", bean.getJacliente_saldo_144());
        }
        if (bean.isEditJacliente_conting_144()) {
            objectNode.put("jacliente_conting_144", bean.getJacliente_conting_144());
        }
        if (bean.isEditNaocliente_informapromo_144()) {
            objectNode.put("naocliente_informapromo_144", bean.getNaocliente_informapromo_144());
        }
        if (bean.isEditNaocliente_regras_144()) {
            objectNode.put("naocliente_regras_144", bean.getNaocliente_regras_144());
        }
        if (bean.isEditNaocliente_validade_144()) {
            objectNode.put("naocliente_validade_144", bean.getNaocliente_validade_144());
        }
        if (bean.isEditConectado_incentivo_144()) {
            objectNode.put("conectado_incentivo_144", bean.getConectado_incentivo_144());
        }
        if (bean.isEditConectado_incentivopromocao_144()) {
            objectNode.put("conectado_incentivopromocao_144", bean.getConectado_incentivopromocao_144());
        }
        if (bean.isEditJacliente_sva_144()) {
            objectNode.put("jacliente_sva_144", bean.getJacliente_sva_144());
        }
        if (bean.isEditIncentivo_comboleto_144()) {
            objectNode.put("incentivo_comboleto_144", bean.getIncentivo_comboleto_144());
        }
        if (bean.isEditDetalhes_ofertaretencao_144()) {
            objectNode.put("detalhes_ofertaretencao_144", bean.getDetalhes_ofertaretencao_144());
        }
        if (bean.isEditInternetemdobro_jacliente_144()) {
            objectNode.put("internetemdobro_jacliente_144", bean.getInternetemdobro_jacliente_144());
        }
        if (bean.isEditInternetemdobro_naocliente_144()) {
            objectNode.put("internetemdobro_naocliente_144", bean.getInternetemdobro_naocliente_144());
        }
        if (bean.isEditBeneficiointernet_anteriordtvendaura_144()) {
            objectNode.put("beneficiointernet_anteriordtvendaura_144", bean.getBeneficiointernet_anteriordtvendaura_144());
        }
        if (bean.isEditBeneficiointernet_aposdtvendaura_144()) {
            objectNode.put("beneficiointernet_aposdtvendaura_144", bean.getBeneficiointernet_aposdtvendaura_144());
        }
        if (bean.isEditFallback_144()) {
            objectNode.put("fallback_144", bean.getFallback_144());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, FrasesControleCad bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode nodeUser = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", nodeUser);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());

        //
        if (bean.isEditOferta_cadastro()) {
            objectNode.put("oferta_cadastro", bean.getOferta_cadastro());
        }
        if (bean.isEditOferta_cadastro_conteudo()) {
            objectNode.put("oferta_cadastro_conteudo", bean.getOferta_cadastro_conteudo());
        }
        if (bean.isEditOferta_escolhida()) {
            objectNode.put("oferta_escolhida", bean.getOferta_escolhida());
        }
        if (bean.isEditOferta_escolhida_conteudo()) {
            objectNode.put("oferta_escolhida_conteudo", bean.getOferta_escolhida_conteudo());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, IoControleControle bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode nodeUser = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", nodeUser);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());

        if (bean.isEditTb_franquiainicial()) {
            objectNode.put("tb_franquiainicial", bean.getTb_franquiainicial());
        }
        if (bean.isEditTb_franquiafinal()) {
            objectNode.put("tb_franquiafinal", bean.getTb_franquiafinal());
        }
        if (bean.isEditTb_incentivo1()) {
            objectNode.put("tb_incentivo1", bean.getTb_incentivo1());
        }
        if (bean.isEditTb_incentivo2()) {
            objectNode.put("tb_incentivo2", bean.getTb_incentivo2());
        }
        if (bean.isEditTb_incentivo3()) {
            objectNode.put("tb_incentivo3", bean.getTb_incentivo3());
        }
        if (bean.isEditTb_incentivo4()) {
            objectNode.put("tb_incentivo4", bean.getTb_incentivo4());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, PromptControleMenu bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode nodeUser = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", nodeUser);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("tb_ddd", bean.getTb_ddd());
        objectNode.put("tb_id_programa", bean.getTb_id_programa());
        objectNode.put("tb_id_beneficio", bean.getTb_id_beneficio());

        if (bean.isEditPromopadrao_mensageminicial()) {
            objectNode.put("promopadrao_mensageminicial", bean.getPromopadrao_mensageminicial());
        }
        if (bean.isEditPromopadrao_regratorpedo()) {
            objectNode.put("promopadrao_regratorpedo", bean.getPromopadrao_regratorpedo());
        }
        if (bean.isEditPromopadrao_regraligacao()) {
            objectNode.put("promopadrao_regraligacao", bean.getPromopadrao_regraligacao());
        }
        if (bean.isEditPromopadrao_comousar()) {
            objectNode.put("promopadrao_comousar", bean.getPromopadrao_comousar());
        }
        if (bean.isEditPromopadrao_dados()) {
            objectNode.put("promopadrao_dados", bean.getPromopadrao_dados());
        }
        if (bean.isEditInformapromo_mensageminicial()) {
            objectNode.put("informapromo_mensageminicial", bean.getInformapromo_mensageminicial());
        }
        if (bean.isEditInformapromo_regratorpedo()) {
            objectNode.put("informapromo_regratorpedo", bean.getInformapromo_regratorpedo());
        }
        if (bean.isEditInformapromo_regraligacao()) {
            objectNode.put("informapromo_regraligacao", bean.getInformapromo_regraligacao());
        }
        if (bean.isEditInformapromo_comousar()) {
            objectNode.put("informapromo_comousar", bean.getInformapromo_comousar());
        }
        if (bean.isEditInformapromo_validadepromo()) {
            objectNode.put("informapromo_validadepromo", bean.getInformapromo_validadepromo());
        }
        if (bean.isEditInformapromo_dados()) {
            objectNode.put("informapromo_dados", bean.getInformapromo_dados());
        }
        if (bean.isEditOutrosplanos_informapromooicontrole()) {
            objectNode.put("outrosplanos_informapromooicontrole", bean.getOutrosplanos_informapromooicontrole());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, OiCMenu bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode nodeUser = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", nodeUser);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("tb_id_beneficio", bean.getTb_id_beneficio());
        objectNode.put("tb_id_programa", bean.getTb_id_programa());
        objectNode.put("tb_ddd", bean.getTb_ddd());

        if (bean.isEditTb_regiao()) {
            objectNode.put("tb_regiao", bean.getTb_regiao());
        }
        if (bean.isEditUpr1_oicpp_mensageminicial()) {
            objectNode.put("upr1_oicpp_mensageminicial", bean.getUpr1_oicpp_mensageminicial());
        }
        if (bean.isEditUpr1_oicpp_regtorpedo()) {
            objectNode.put("upr1_oicpp_regtorpedo", bean.getUpr1_oicpp_regtorpedo());
        }
        if (bean.isEditUpr1_oicpp_regligacao()) {
            objectNode.put("upr1_oicpp_regligacao", bean.getUpr1_oicpp_regligacao());
        }
        if (bean.isEditUpr1_oicpp_comousar()) {
            objectNode.put("upr1_oicpp_comousar", bean.getUpr1_oicpp_comousar());
        }
        if (bean.isEditUpr1_oicinfp_mensageminicial()) {
            objectNode.put("upr1_oicinfp_mensageminicial", bean.getUpr1_oicinfp_mensageminicial());
        }
        if (bean.isEditUpr1_oicinfp_regtorpedo()) {
            objectNode.put("upr1_oicinfp_regtorpedo", bean.getUpr1_oicinfp_regtorpedo());
        }
        if (bean.isEditUpr1_oicinfop_regligacao()) {
            objectNode.put("upr1_oicinfop_regligacao", bean.getUpr1_oicinfop_regligacao());
        }
        if (bean.isEditUpr1_oicinfp_comousar()) {
            objectNode.put("upr1_oicinfp_comousar", bean.getUpr1_oicinfp_comousar());
        }
        if (bean.isEditUpr1_oicinfp_validadepromo()) {
            objectNode.put("upr1_oicinfp_validadepromo", bean.getUpr1_oicinfp_validadepromo());
        }
        if (bean.isEditUpr1_oiccpoplanos_promooicontrole()) {
            objectNode.put("upr1_oiccpoplanos_promooicontrole", bean.getUpr1_oiccpoplanos_promooicontrole());
        }
        if (bean.isEditUpr1_oicinfp_regdados()) {
            objectNode.put("upr1_oicinfp_regdados", bean.getUpr1_oicinfp_regdados());
        }
        if (bean.isEditUpr1_oicpp_regdados()) {
            objectNode.put("upr1_oicpp_regdados", bean.getUpr1_oicpp_regdados());
        }
        if (bean.isEditJacliente_sva()) {
            objectNode.put("jacliente_sva", bean.getJacliente_sva());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, OiCPromocoes bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode nodeUser = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", nodeUser);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("tb_id_beneficio", bean.getTb_id_beneficio());
        objectNode.put("tb_id_programa", bean.getTb_id_programa());
        objectNode.put("tb_ddd", bean.getTb_ddd());

        if (bean.isEditTb_regiao()) {
            objectNode.put("tb_regiao", bean.getTb_regiao());
        }
        if (bean.isEditIncentivo1()) {
            objectNode.put("incentivo1", bean.getIncentivo1());
        }
        if (bean.isEditIncentivo2()) {
            objectNode.put("incentivo2", bean.getIncentivo2());
        }
        if (bean.isEditOutrosplanos()) {
            objectNode.put("outrosplanos", bean.getOutrosplanos());
        }
        if (bean.isEditOffercode()) {
            objectNode.put("offercode", bean.getOffercode());
        }
        if (bean.isEditTb_plano()) {
            objectNode.put("tb_plano", bean.getTb_plano());
        }
        if (bean.isEditTb_nome_promocao()) {
            objectNode.put("tb_nome_promocao", bean.getTb_nome_promocao());
        }
        if (bean.isEditTb_tipo_promo()) {
            objectNode.put("tb_tipo_promo", bean.getTb_tipo_promo());
        }
        if (bean.isEditTb_sn_elegivel_nova_promo()) {
            objectNode.put("tb_sn_elegivel_nova_promo", bean.getTb_sn_elegivel_nova_promoStr());
        }
        if (bean.isEditTb_taxa_adesao()) {
            objectNode.put("tb_taxa_adesao", bean.getTb_taxa_adesao());
        }
        if (bean.isEditTb_dt_inicio_promo()) {
            objectNode.put("tb_dt_inicio_promo", bean.getTb_dt_inicio_promo().getTime());
        }
        if (bean.isEditTb_dt_fim_promo()) {
            objectNode.put("tb_dt_fim_promo", bean.getTb_dt_fim_promo().getTime());
        }
        if (bean.isEditTb_tipo_bonus()) {
            objectNode.put("tb_tipo_bonus", bean.getTb_tipo_bonus());
        }
        if (bean.isEditTb_franquia()) {
            objectNode.put("tb_franquia", bean.getTb_franquia());
        }
        if (bean.isEditTb_bonus()) {
            objectNode.put("tb_bonus", bean.getTb_bonus());
        }
        if (bean.isEditTb_sn_bonus_torpedo()) {
            objectNode.put("tb_sn_bonus_torpedo", bean.getTb_sn_bonus_torpedoStr());
        }
        if (bean.isEditTb_qtd_torp()) {
            objectNode.put("tb_qtd_torp", bean.getTb_qtd_torp());
        }
        if (bean.isEditTb_bonus_torp()) {
            objectNode.put("tb_bonus_torp", bean.getTb_bonus_torp());
        }
        if (bean.isEditSn_oferta_sem_franquia()) {
            objectNode.put("sn_oferta_sem_franquia", bean.getSn_oferta_sem_franquiaStr());
        }
        if (bean.isEditTb_ligacaooimovelfixo_dados()) {
            objectNode.put("tb_ligacaooimovelfixo_dados", bean.getTb_ligacaooimovelfixo_dadosStr());
        }
        if (bean.isEditTb_bonusligacaomix_dados()) {
            objectNode.put("tb_bonusligacaomix_dados", bean.getTb_bonusligacaomix_dadosStr());
        }
        if (bean.isEditTb_ussd_convertido()) {
            objectNode.put("tb_ussd_convertido", bean.getTb_ussd_convertidoStr());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, TbTemplate bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode nodeUser = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", nodeUser);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());

        if (bean.isEditOferta1()) {
            objectNode.put("oferta1", bean.getOferta1());
        }
        if (bean.isEditOferta2()) {
            objectNode.put("oferta2", bean.getOferta2());
        }
        if (bean.isEditOferta3()) {
            objectNode.put("oferta3", bean.getOferta3());
        }
        if (bean.isEditOferta_erro_in()) {
            objectNode.put("oferta_erro_in", bean.getOferta_erro_in());
        }
        if (bean.isEditOferta_erro_siebel()) {
            objectNode.put("oferta_erro_siebel", bean.getOferta_erro_siebel());
        }
        if (bean.isEditSegprompt()) {
            objectNode.put("segprompt", bean.getSegprompt());
        }
        if (bean.isEditTipo_bonus()) {
            objectNode.put("tipo_bonus", bean.getTipo_bonus());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, TbTemplateControle bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode nodeUser = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", nodeUser);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());

        if (bean.isEditOffercode()) {
            objectNode.put("offercode", bean.getOffercode());
        }
        if (bean.isEditOferta_cadastro()) {
            objectNode.put("oferta_cadastro", bean.getOferta_cadastro());
        }
        if (bean.isEditOferta_cadastro_escolhida()) {
            objectNode.put("oferta_cadastro_escolhida", bean.getOferta_cadastro_escolhida());
        }
        if (bean.isEditTx_adesao_boleto()) {
            objectNode.put("tx_adesao_boleto", bean.getTx_adesao_boleto());
        }
        if (bean.isEditTb_franquia_oferta()) {
            objectNode.put("tb_franquia_oferta", bean.getTb_franquia_oferta());
        }
        if (bean.isEditTipo_pagamento()) {
            objectNode.put("tipo_pagamento", bean.getTipo_pagamento());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, TbTemplateOcs bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode nodeUser = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", nodeUser);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());

        if (bean.isEditId_campanha_siebel1()) {
            objectNode.put("id_campanha_siebel1", bean.getId_campanha_siebel1());
        }
        if (bean.isEditId_oferta_siebel1()) {
            objectNode.put("id_oferta_siebel1", bean.getId_oferta_siebel1());
        }
        if (bean.isEditId_campanha_siebel2()) {
            objectNode.put("id_campanha_siebel2", bean.getId_campanha_siebel2());
        }
        if (bean.isEditId_oferta_siebel2()) {
            objectNode.put("id_oferta_siebel2", bean.getId_oferta_siebel2());
        }
        if (bean.isEditId_campanha_siebel3()) {
            objectNode.put("id_campanha_siebel3", bean.getId_campanha_siebel3());
        }
        if (bean.isEditId_oferta_siebel3()) {
            objectNode.put("id_oferta_siebel3", bean.getId_oferta_siebel3());
        }
        if (bean.isEditId_campanha_default()) {
            objectNode.put("id_campanha_default", bean.getId_campanha_default());
        }
        if (bean.isEditId_oferta_default()) {
            objectNode.put("id_oferta_default", bean.getId_oferta_default());
        }
        if (bean.isEditMsg_ocs_sva()) {
            objectNode.put("msg_ocs_sva", bean.getMsg_ocs_sva());
        }
        if (bean.isEditTipo_bonus()) {
            objectNode.put("tipo_bonus", bean.getTipo_bonus());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, FrasePre bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());

        if (bean.isEditCod_oferta()) {
            objectNode.put("cod_oferta", bean.getCod_oferta());
        }
        if (bean.isEditOferta1()) {
            objectNode.put("oferta1", bean.getOferta1());
        }
        if (bean.isEditOferta2()) {
            objectNode.put("oferta2", bean.getOferta2());
        }
        if (bean.isEditOferta3()) {
            objectNode.put("oferta3", bean.getOferta3());
        }
        if (bean.isEditOferta_erro_in()) {
            objectNode.put("oferta_erro_in", bean.getOferta_erro_in());
        }
        if (bean.isEditOferta_siebel()) {
            objectNode.put("oferta_siebel", bean.getOferta_siebel());
        }
        if (bean.isEditSegprompt()) {
            objectNode.put("segprompt", bean.getSegprompt());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }

    }

    public static String addToJsonArray(List<? extends Template> list, IoControleControleS2S bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode nodeUser = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", nodeUser);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("oferta", bean.getOferta());

        if (bean.isEditTb_incentivo1()) {
            objectNode.put("tb_incentivo1", bean.getTb_incentivo1());
        }
        if (bean.isEditTb_incentivo2()) {
            objectNode.put("tb_incentivo2", bean.getTb_incentivo2());
        }
        if (bean.isEditTb_incentivo3()) {
            objectNode.put("tb_incentivo3", bean.getTb_incentivo3());
        }
        if (bean.isEditTb_incentivo4()) {
            objectNode.put("tb_incentivo4", bean.getTb_incentivo4());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, IoCartaoCartaoS2S bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());

        if (bean.isEditRecacumuladamesanteriorinicial()) {
            objectNode.put("recacumuladamesanteriorinicial", bean.getRecacumuladamesanteriorinicial());
        }
        if (bean.isEditRecacumuladamesanteriorfinal()) {
            objectNode.put("recacumuladamesanteriorfinal", bean.getRecacumuladamesanteriorfinal());
        }
        if (bean.isEditOfertapromo1()) {
            objectNode.put("ofertapromo1", bean.getOfertapromo1());
        }
        if (bean.isEditOfertapromo2()) {
            objectNode.put("ofertapromo2", bean.getOfertapromo2());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, IncentivoOfertasPreS2S bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());

        if (bean.isEditRecacumuladamesanteriorinicial()) {
            objectNode.put("recacumuladamesanteriorinicial", bean.getRecacumuladamesanteriorinicial());
        }
        if (bean.isEditRecacumuladamesanteriorfinal()) {
            objectNode.put("recacumuladamesanteriorfinal", bean.getRecacumuladamesanteriorfinal());
        }
        if (bean.isEditOfertapromo1()) {
            objectNode.put("ofertapromo1", bean.getOfertapromo1());
        }
        if (bean.isEditOfertapromo2()) {
            objectNode.put("ofertapromo2", bean.getOfertapromo2());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    //Portabilidade
    public static String addToJsonArray(List<? extends Template> list, FrasesPortabilidade bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("idbeneficio", bean.getIdbeneficio());
        objectNode.put("idprograma", bean.getIdprograma());
        objectNode.put("ddd", bean.getDdd());

        if (bean.isEditInformapromo()) {
            objectNode.put("informapromo", bean.getInformapromo());
        }
        if (bean.isEditIncentivo_portabilidade()) {
            objectNode.put("incentivo_portabilidade", bean.getIncentivo_portabilidade());
        }
        if (bean.isEditNaocliente_sva()) {
            objectNode.put("naocliente_sva", bean.getNaocliente_sva());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, FrasesPortabilidadeOcs bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("idbeneficio", bean.getIdbeneficio());
        objectNode.put("idprograma", bean.getIdprograma());
        objectNode.put("idofertaocs", bean.getIdofertaocs());
        objectNode.put("ddd", bean.getDdd());

        if (bean.isEditInformapromo()) {
            objectNode.put("informapromo", bean.getInformapromo());
        }
        if (bean.isEditIncentivo_portabilidade()) {
            objectNode.put("incentivo_portabilidade", bean.getIncentivo_portabilidade());
        }
        if (bean.isEditNaocliente_sva()) {
            objectNode.put("naocliente_sva", bean.getNaocliente_sva());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, IncentivosPortabilidade bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());

        if (bean.isEditOferta1()) {
            objectNode.put("oferta1", bean.getOferta1());
        }
        if (bean.isEditOferta2()) {
            objectNode.put("oferta2", bean.getOferta2());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, IncentivosPortabilidadeOcs bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("ddd", bean.getDdd());

        if (bean.isEditOferta1()) {
            objectNode.put("oferta1", bean.getOferta1());
        }
        if (bean.isEditOferta2()) {
            objectNode.put("oferta2", bean.getOferta2());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, OfertasPortabilidadeOcs bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("idbeneficio", bean.getIdbeneficio());
        objectNode.put("idprograma", bean.getIdprograma());
        objectNode.put("idofertaocs", bean.getIdofertaocs());
        objectNode.put("ddd", bean.getDdd());

        if (bean.isEditId_campanhasiebel()) {
            objectNode.put("id_campanhasiebel", bean.getId_campanhasiebel());
        }
        if (bean.isEditId_ofertasiebel()) {
            objectNode.put("id_ofertasiebel", bean.getId_ofertasiebel());
        }
        if (bean.isEditTb_tipo_bonus()) {
            objectNode.put("tb_tipo_bonus", bean.getTb_tipo_bonus());
        }
        if (bean.isEditData_inic()) {
            objectNode.put("data_inic", bean.getData_inic().getTime());
        }
        if (bean.isEditData_fim()) {
            objectNode.put("data_fim", bean.getData_fim().getTime());
        }
        if (bean.isEditInformapromo()) {
            objectNode.put("informapromo", bean.getInformapromo());
        }

        if (bean.isEditIncentivo_portabilidade()) {
            objectNode.put("incentivo_portabilidade", bean.getIncentivo_portabilidade());
        }
        if (bean.isEditNaocliente_sva()) {
            objectNode.put("naocliente_sva", bean.getNaocliente_sva());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static String addToJsonArray(List<? extends Template> list, OfertasPortabilidade bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        JsonNode node = mapper.convertValue(bean.getUser(), JsonNode.class);
        objectNode.set("user", node);

        JsonNode nodeEdit = mapper.convertValue(bean.getEditMap(), JsonNode.class);
        objectNode.set("editMap", nodeEdit);

        objectNode.put("modified", bean.getModified() == null ? new Date().getTime() : bean.getModified().getTime());
        objectNode.put("idbeneficio", bean.getIdbeneficio());
        objectNode.put("idprograma", bean.getIdprograma());
        objectNode.put("ddd", bean.getDdd());

        if (bean.isEditTb_tipo_bonus()) {
            objectNode.put("tb_tipo_bonus", bean.getTb_tipo_bonus());
        }
        if (bean.isEditData_inic()) {
            objectNode.put("data_inic", bean.getData_inic().getTime());
        }
        if (bean.isEditData_fim()) {
            objectNode.put("data_fim", bean.getData_fim().getTime());
        }
        if (bean.isEditInformapromo()) {
            objectNode.put("informapromo", bean.getInformapromo());
        }

        if (bean.isEditIncentivo_portabilidade()) {
            objectNode.put("incentivo_portabilidade", bean.getIncentivo_portabilidade());
        }
        if (bean.isEditNaocliente_sva()) {
            objectNode.put("naocliente_sva", bean.getNaocliente_sva());
        }

        if (list != null) {
            JsonNode jsonNode = mapper.readTree(toJson(list));
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            arrayNode.add(objectNode);
            return arrayNode.toString();
        } else {
            return "[" + objectNode.toString() + "]";
        }
    }

    public static void mergeJsonHistory(TabelasEnum tabela, Map<String, Boolean> mapEdit, String[] value, String mergedHistory, String mergedJson) throws IOException, SQLException {
        switch (tabela) {
            case CONFIG_OFERTASPRE:
                ConfigOfertasPre mergedConfigOfertasPre = (ConfigOfertasPre) JsonUtils.fromJson(value[3], new ConfigOfertasPre());

                List<ConfigOfertasPre> configOfertasPreList = JsonUtils.fromJsonList(value[2], ConfigOfertasPre.class);

                logger.trace("configOfertasPreList.getClass(): {}", configOfertasPreList.get(0).getClass());

                Collections.sort(configOfertasPreList);

                for (int i = 0; i < configOfertasPreList.size(); i++) {
                    ConfigOfertasPre configOfertasPre = (ConfigOfertasPre) JsonUtils.fromJson(JsonUtils.toJson(configOfertasPreList.get(i)), mergedConfigOfertasPre);
                    Map<String, Boolean> collect = configOfertasPre.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(configOfertasPre.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, ConfigOfertasPre.class), configOfertasPre);
                }
                mergedConfigOfertasPre.setEditMap(mapEdit);

                configOfertasPreList = new ArrayList<>();
                configOfertasPreList.add(mergedConfigOfertasPre);
                mergedJson = JsonUtils.toJson(configOfertasPreList);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;
            case BO_CONTROLE:

                BoControle mergedBoControle = (BoControle) JsonUtils.fromJson(value[3], new BoControle());

                List<BoControle> BoControleList = JsonUtils.fromJsonList(value[2], BoControle.class);

                logger.trace("configOfertasPreList.getClass(): {}", BoControleList.get(0).getClass());

                Collections.sort(BoControleList);

                for (int i = 0; i < BoControleList.size(); i++) {
                    BoControle boControle = (BoControle) JsonUtils.fromJson(JsonUtils.toJson(BoControleList.get(i)), mergedBoControle);
                    Map<String, Boolean> collect = boControle.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(boControle.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, BoControle.class), boControle);
                }
                mergedBoControle.setEditMap(mapEdit);

                BoControleList = new ArrayList<>();
                BoControleList.add(mergedBoControle);
                mergedJson = JsonUtils.toJson(BoControleList);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;
            case IO_CARTAO_CONTROLE:

                IoCartaoControle mergedIoCartaoControle = (IoCartaoControle) JsonUtils.fromJson(value[3], new IoCartaoControle());

                List<IoCartaoControle> ioCartaoControleList = JsonUtils.fromJsonList(value[2], IoCartaoControle.class);

                logger.trace("configOfertasPreList.getClass(): {}", ioCartaoControleList.get(0).getClass());

                Collections.sort(ioCartaoControleList);

                for (int i = 0; i < ioCartaoControleList.size(); i++) {
                    IoCartaoControle ioCartaoControle = (IoCartaoControle) JsonUtils.fromJson(JsonUtils.toJson(ioCartaoControleList.get(i)), mergedIoCartaoControle);
                    Map<String, Boolean> collect = ioCartaoControle.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(ioCartaoControle.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, IoCartaoControle.class), ioCartaoControle);
                }
                mergedIoCartaoControle.setEditMap(mapEdit);

                ioCartaoControleList = new ArrayList<>();
                ioCartaoControleList.add(mergedIoCartaoControle);
                mergedJson = JsonUtils.toJson(ioCartaoControleList);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;
            case IO_CONTROLE_CONTROLE:

                IoControleControle mergedIoControleControle = (IoControleControle) JsonUtils.fromJson(value[3], new IoControleControle());

                List<IoControleControle> ioControleControleList = JsonUtils.fromJsonList(value[2], IoControleControle.class);

                logger.trace("configOfertasPreList.getClass(): {}", ioControleControleList.get(0).getClass());

                Collections.sort(ioControleControleList);

                for (int i = 0; i < ioControleControleList.size(); i++) {
                    IoControleControle ioControleControle = (IoControleControle) JsonUtils.fromJson(JsonUtils.toJson(ioControleControleList.get(i)), mergedIoControleControle);
                    Map<String, Boolean> collect = ioControleControle.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(ioControleControle.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, IoControleControle.class), ioControleControle);
                }
                mergedIoControleControle.setEditMap(mapEdit);

                ioControleControleList = new ArrayList<>();
                ioControleControleList.add(mergedIoControleControle);
                mergedJson = JsonUtils.toJson(ioControleControleList);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            case IO_CONTROLE_CONTROLE_S2S:

                IoControleControleS2S mergedIoControleControleS2s = (IoControleControleS2S) JsonUtils.fromJson(value[3], new IoControleControleS2S());

                List<IoControleControleS2S> ioControleControleS2SList = JsonUtils.fromJsonList(value[2], IoControleControleS2S.class);

                logger.trace("configOfertasPreList.getClass(): {}", ioControleControleS2SList.get(0).getClass());

                Collections.sort(ioControleControleS2SList);

                for (int i = 0; i < ioControleControleS2SList.size(); i++) {
                    IoControleControleS2S ioControleControleS2s = (IoControleControleS2S) JsonUtils.fromJson(JsonUtils.toJson(ioControleControleS2SList.get(i)), mergedIoControleControleS2s);
                    Map<String, Boolean> collect = ioControleControleS2s.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(ioControleControleS2s.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, IoControleControleS2S.class), ioControleControleS2s);
                }
                mergedIoControleControleS2s.setEditMap(mapEdit);

                ioControleControleS2SList = new ArrayList<>();
                ioControleControleS2SList.add(mergedIoControleControleS2s);
                mergedJson = JsonUtils.toJson(ioControleControleS2SList);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            case TB_OICMENU:

                OiCMenu mergedOiCMenu = (OiCMenu) JsonUtils.fromJson(value[3], new OiCMenu());

                List<OiCMenu> oiCMenuList = JsonUtils.fromJsonList(value[2], OiCMenu.class);

                logger.trace("configOfertasPreList.getClass(): {}", oiCMenuList.get(0).getClass());

                Collections.sort(oiCMenuList);

                for (int i = 0; i < oiCMenuList.size(); i++) {
                    OiCMenu oiCMenu = (OiCMenu) JsonUtils.fromJson(JsonUtils.toJson(oiCMenuList.get(i)), mergedOiCMenu);
                    Map<String, Boolean> collect = oiCMenu.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(oiCMenu.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, OiCMenu.class), oiCMenu);
                }
                mergedOiCMenu.setEditMap(mapEdit);

                oiCMenuList = new ArrayList<>();
                oiCMenuList.add(mergedOiCMenu);
                mergedJson = JsonUtils.toJson(oiCMenuList);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;
            case TB_OICPROMOCOES:

                OiCPromocoes mergedOiCPromocoes = (OiCPromocoes) JsonUtils.fromJson(value[3], new OiCPromocoes());

                List<OiCPromocoes> oiCPromocoesList = JsonUtils.fromJsonList(value[2], OiCPromocoes.class);

                logger.trace("configOfertasPreList.getClass(): {}", oiCPromocoesList.get(0).getClass());

                Collections.sort(oiCPromocoesList);

                for (int i = 0; i < oiCPromocoesList.size(); i++) {
                    OiCPromocoes oiCPromocoes = (OiCPromocoes) JsonUtils.fromJson(JsonUtils.toJson(oiCPromocoesList.get(i)), mergedOiCPromocoes);
                    Map<String, Boolean> collect = oiCPromocoes.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(oiCPromocoes.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, OiCPromocoes.class), oiCPromocoes);
                }
                mergedOiCPromocoes.setEditMap(mapEdit);

                oiCPromocoesList = new ArrayList<>();
                oiCPromocoesList.add(mergedOiCPromocoes);
                mergedJson = JsonUtils.toJson(oiCPromocoesList);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;
            case TB_TEMPLATE:

                TbTemplate mergedTbTemplate = (TbTemplate) JsonUtils.fromJson(value[3], new TbTemplate());

                List<TbTemplate> tbTemplateList = JsonUtils.fromJsonList(value[2], TbTemplate.class);

                logger.trace("configOfertasPreList.getClass(): {}", tbTemplateList.get(0).getClass());

                Collections.sort(tbTemplateList);

                for (int i = 0; i < tbTemplateList.size(); i++) {
                    TbTemplate tbTemplate = (TbTemplate) JsonUtils.fromJson(JsonUtils.toJson(tbTemplateList.get(i)), mergedTbTemplate);
                    Map<String, Boolean> collect = tbTemplate.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(tbTemplate.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, TbTemplate.class), tbTemplate);
                }
                mergedTbTemplate.setEditMap(mapEdit);

                tbTemplateList = new ArrayList<>();
                tbTemplateList.add(mergedTbTemplate);
                mergedJson = JsonUtils.toJson(tbTemplateList);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;
            case TB_TEMPLATE_CONTROLE:

                TbTemplateControle mergedTbTemplateControle = (TbTemplateControle) JsonUtils.fromJson(value[3], new TbTemplateControle());

                List<TbTemplateControle> tbTemplateControleList = JsonUtils.fromJsonList(value[2], TbTemplateControle.class);

                logger.trace("configOfertasPreList.getClass(): {}", tbTemplateControleList.get(0).getClass());

                Collections.sort(tbTemplateControleList);

                for (int i = 0; i < tbTemplateControleList.size(); i++) {
                    TbTemplateControle tbTemplateControle = (TbTemplateControle) JsonUtils.fromJson(JsonUtils.toJson(tbTemplateControleList.get(i)), mergedTbTemplateControle);
                    Map<String, Boolean> collect = tbTemplateControle.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(tbTemplateControle.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, TbTemplateControle.class), tbTemplateControle);
                }
                mergedTbTemplateControle.setEditMap(mapEdit);

                tbTemplateControleList = new ArrayList<>();
                tbTemplateControleList.add(mergedTbTemplateControle);
                mergedJson = JsonUtils.toJson(tbTemplateControleList);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;
            case TB_TEMPLATE_OCS:

                TbTemplateOcs mergedTbTemplateOcs = (TbTemplateOcs) JsonUtils.fromJson(value[3], new TbTemplateOcs());

                List<TbTemplateOcs> tbTemplateOcsList = JsonUtils.fromJsonList(value[2], TbTemplateOcs.class);

                logger.trace("configOfertasPreList.getClass(): {}", tbTemplateOcsList.get(0).getClass());

                Collections.sort(tbTemplateOcsList);

                for (int i = 0; i < tbTemplateOcsList.size(); i++) {
                    TbTemplateOcs tbTemplateOcs = (TbTemplateOcs) JsonUtils.fromJson(JsonUtils.toJson(tbTemplateOcsList.get(i)), mergedTbTemplateOcs);
                    Map<String, Boolean> collect = tbTemplateOcs.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(tbTemplateOcs.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, TbTemplateOcs.class), tbTemplateOcs);
                }
                mergedTbTemplateOcs.setEditMap(mapEdit);

                tbTemplateOcsList = new ArrayList<>();
                tbTemplateOcsList.add(mergedTbTemplateOcs);
                mergedJson = JsonUtils.toJson(tbTemplateOcsList);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;
            case VO_CONTROLE:

                VoControle mergedVoControle = (VoControle) JsonUtils.fromJson(value[3], new VoControle());

                List<VoControle> voControleList = JsonUtils.fromJsonList(value[2], VoControle.class);

                logger.trace("configOfertasPreList.getClass(): {}", voControleList.get(0).getClass());

                Collections.sort(voControleList);

                for (int i = 0; i < voControleList.size(); i++) {
                    VoControle voControle = (VoControle) JsonUtils.fromJson(JsonUtils.toJson(voControleList.get(i)), mergedVoControle);
                    Map<String, Boolean> collect = voControle.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(voControle.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, VoControle.class), voControle);
                }
                mergedVoControle.setEditMap(mapEdit);

                voControleList = new ArrayList<>();
                voControleList.add(mergedVoControle);
                mergedJson = JsonUtils.toJson(voControleList);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            case PROMPT_CONTROLEMENU:

                PromptControleMenu mergedPromptControleMenu = (PromptControleMenu) JsonUtils.fromJson(value[3], new PromptControleMenu());

                List<PromptControleMenu> promptControleMenuList = JsonUtils.fromJsonList(value[2], PromptControleMenu.class);

                logger.trace("configOfertasPreList.getClass(): {}", promptControleMenuList.get(0).getClass());

                Collections.sort(promptControleMenuList);

                for (int i = 0; i < promptControleMenuList.size(); i++) {
                    PromptControleMenu promptControleMenu = (PromptControleMenu) JsonUtils.fromJson(JsonUtils.toJson(promptControleMenuList.get(i)), mergedPromptControleMenu);
                    Map<String, Boolean> collect = promptControleMenu.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(promptControleMenu.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, PromptControleMenu.class), promptControleMenu);
                }
                mergedPromptControleMenu.setEditMap(mapEdit);

                promptControleMenuList = new ArrayList<>();
                promptControleMenuList.add(mergedPromptControleMenu);
                mergedJson = JsonUtils.toJson(promptControleMenuList);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            case FRASES_CONTROLE:

                FrasesControle mergedFrasesControle = (FrasesControle) JsonUtils.fromJson(value[3], new FrasesControle());

                List<FrasesControle> FrasesControleList = JsonUtils.fromJsonList(value[2], FrasesControle.class);

                logger.trace("configOfertasPreList.getClass(): {}", FrasesControleList.get(0).getClass());

                Collections.sort(FrasesControleList);

                for (int i = 0; i < FrasesControleList.size(); i++) {
                    FrasesControle frasesControle = (FrasesControle) JsonUtils.fromJson(JsonUtils.toJson(FrasesControleList.get(i)), mergedFrasesControle);
                    Map<String, Boolean> collect = frasesControle.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(frasesControle.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, FrasesControle.class), frasesControle);
                }
                mergedFrasesControle.setEditMap(mapEdit);

                FrasesControleList = new ArrayList<>();
                FrasesControleList.add(mergedFrasesControle);
                mergedJson = JsonUtils.toJson(FrasesControleList);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            case FRASE_PRE:

                FrasePre mergedFrasePre = (FrasePre) JsonUtils.fromJson(value[3], new FrasePre());

                List<FrasePre> FrasePreList = JsonUtils.fromJsonList(value[2], FrasePre.class);

                logger.trace("configOfertasPreList.getClass(): {}", FrasePreList.get(0).getClass());

                Collections.sort(FrasePreList);

                for (int i = 0; i < FrasePreList.size(); i++) {
                    FrasePre frasePre = (FrasePre) JsonUtils.fromJson(JsonUtils.toJson(FrasePreList.get(i)), mergedFrasePre);
                    Map<String, Boolean> collect = frasePre.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(frasePre.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, FrasePre.class), frasePre);
                }
                mergedFrasePre.setEditMap(mapEdit);

                FrasePreList = new ArrayList<>();
                FrasePreList.add(mergedFrasePre);
                mergedJson = JsonUtils.toJson(FrasePreList);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;
            case FRASE_PRE_OCS:

                FrasePreOcs mergedFrasePreOcs = (FrasePreOcs) JsonUtils.fromJson(value[3], new FrasePreOcs());

                List<FrasePreOcs> FrasePreOcsList = JsonUtils.fromJsonList(value[2], FrasePreOcs.class);

                logger.trace("configOfertasPreList.getClass(): {}", FrasePreOcsList.get(0).getClass());

                Collections.sort(FrasePreOcsList);

                for (int i = 0; i < FrasePreOcsList.size(); i++) {
                    FrasePreOcs frasePreOcs = (FrasePreOcs) JsonUtils.fromJson(JsonUtils.toJson(FrasePreOcsList.get(i)), mergedFrasePreOcs);
                    Map<String, Boolean> collect = frasePreOcs.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(frasePreOcs.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, FrasePreOcs.class), frasePreOcs);
                }
                mergedFrasePreOcs.setEditMap(mapEdit);

                FrasePreOcsList = new ArrayList<>();
                FrasePreOcsList.add(mergedFrasePreOcs);
                mergedJson = JsonUtils.toJson(FrasePreOcsList);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            case PROMPTS_OFERTASPRE:
                PromptsOfertasPre mergedPromptsOfertasPre = (PromptsOfertasPre) JsonUtils.fromJson(value[3], new PromptsOfertasPre());

                List<PromptsOfertasPre> promptsOfertasPreList = JsonUtils.fromJsonList(value[2], PromptsOfertasPre.class);
                Collections.sort(promptsOfertasPreList);
                logger.trace("configOfertasPreList.getClass(): {}", promptsOfertasPreList.getClass());
                for (int i = 0; i < promptsOfertasPreList.size(); i++) {
                    PromptsOfertasPre promptsOfertasPre = (PromptsOfertasPre) JsonUtils.fromJson(JsonUtils.toJson(promptsOfertasPreList.get(i)), mergedPromptsOfertasPre);
                    Map<String, Boolean> collect = promptsOfertasPre.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(promptsOfertasPre.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, PromptsOfertasPre.class), promptsOfertasPre);
                }
                mergedPromptsOfertasPre.setEditMap(mapEdit);

                promptsOfertasPreList = new ArrayList<>();
                promptsOfertasPreList.add(mergedPromptsOfertasPre);
                mergedJson = JsonUtils.toJson(promptsOfertasPreList);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            case FRASEOLOGIAS_OFERTASPRE:
                FraseologiasOfertasPre mergedFraseologiasOfertasPre = (FraseologiasOfertasPre) JsonUtils.fromJson(value[3], new FraseologiasOfertasPre());

                List<FraseologiasOfertasPre> promptsFraseologiasOfertasPres = JsonUtils.fromJsonList(value[2], FraseologiasOfertasPre.class);
                Collections.sort(promptsFraseologiasOfertasPres);
                logger.trace("configOfertasPreList.getClass(): {}", promptsFraseologiasOfertasPres.getClass());
                for (int i = 0; i < promptsFraseologiasOfertasPres.size(); i++) {
                    FraseologiasOfertasPre bean = (FraseologiasOfertasPre) JsonUtils.fromJson(JsonUtils.toJson(promptsFraseologiasOfertasPres.get(i)), mergedFraseologiasOfertasPre);
                    Map<String, Boolean> collect = bean.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(bean.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, FraseologiasOfertasPre.class), bean);
                }
                mergedFraseologiasOfertasPre.setEditMap(mapEdit);

                promptsFraseologiasOfertasPres = new ArrayList<>();
                promptsFraseologiasOfertasPres.add(mergedFraseologiasOfertasPre);
                mergedJson = JsonUtils.toJson(promptsFraseologiasOfertasPres);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            case INCENTIVO_OFERTASPRE:
                IncentivoOfertasPre mergedIncentivoOfertasPre = (IncentivoOfertasPre) JsonUtils.fromJson(value[3], new IncentivoOfertasPre());

                List<IncentivoOfertasPre> promptsIncentivoOfertasPres = JsonUtils.fromJsonList(value[2], IncentivoOfertasPre.class);
                Collections.sort(promptsIncentivoOfertasPres);
                logger.trace("configOfertasPreList.getClass(): {}", promptsIncentivoOfertasPres.getClass());
                for (int i = 0; i < promptsIncentivoOfertasPres.size(); i++) {
                    IncentivoOfertasPre bean = (IncentivoOfertasPre) JsonUtils.fromJson(JsonUtils.toJson(promptsIncentivoOfertasPres.get(i)), mergedIncentivoOfertasPre);
                    Map<String, Boolean> collect = bean.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(bean.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, IncentivoOfertasPre.class), bean);
                }
                mergedIncentivoOfertasPre.setEditMap(mapEdit);

                promptsIncentivoOfertasPres = new ArrayList<>();
                promptsIncentivoOfertasPres.add(mergedIncentivoOfertasPre);
                mergedJson = JsonUtils.toJson(promptsIncentivoOfertasPres);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            case INCENTIVOS_PORTABILIDADE:
                IncentivosPortabilidade mergedIncentivosPortabilidade = (IncentivosPortabilidade) JsonUtils.fromJson(value[3], new IncentivosPortabilidade());

                List<IncentivosPortabilidade> promptsIncentivosPortabilidade = JsonUtils.fromJsonList(value[2], IncentivosPortabilidade.class);
                Collections.sort(promptsIncentivosPortabilidade);
                logger.trace("configOfertasPreList.getClass(): {}", promptsIncentivosPortabilidade.getClass());
                for (int i = 0; i < promptsIncentivosPortabilidade.size(); i++) {
                    IncentivosPortabilidade bean = (IncentivosPortabilidade) JsonUtils.fromJson(JsonUtils.toJson(promptsIncentivosPortabilidade.get(i)), mergedIncentivosPortabilidade);
                    Map<String, Boolean> collect = bean.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(bean.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, IncentivosPortabilidade.class), bean);
                }
                mergedIncentivosPortabilidade.setEditMap(mapEdit);

                promptsIncentivosPortabilidade = new ArrayList<>();
                promptsIncentivosPortabilidade.add(mergedIncentivosPortabilidade);
                mergedJson = JsonUtils.toJson(promptsIncentivosPortabilidade);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            case INCENTIVO_OFERTASPRE_S2S:
                IncentivoOfertasPreS2S mergedIncentivoOfertasPreS2s = (IncentivoOfertasPreS2S) JsonUtils.fromJson(value[3], new IncentivoOfertasPreS2S());

                List<IncentivoOfertasPreS2S> promptsIncentivoOfertasPresS2s = JsonUtils.fromJsonList(value[2], IncentivoOfertasPreS2S.class);
                Collections.sort(promptsIncentivoOfertasPresS2s);
                logger.trace("configOfertasPreList.getClass(): {}", promptsIncentivoOfertasPresS2s.getClass());
                for (int i = 0; i < promptsIncentivoOfertasPresS2s.size(); i++) {
                    IncentivoOfertasPreS2S bean = (IncentivoOfertasPreS2S) JsonUtils.fromJson(JsonUtils.toJson(promptsIncentivoOfertasPresS2s.get(i)), mergedIncentivoOfertasPreS2s);
                    Map<String, Boolean> collect = bean.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(bean.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, IncentivoOfertasPreS2S.class), bean);
                }
                mergedIncentivoOfertasPreS2s.setEditMap(mapEdit);

                promptsIncentivoOfertasPresS2s = new ArrayList<>();
                promptsIncentivoOfertasPresS2s.add(mergedIncentivoOfertasPreS2s);
                mergedJson = JsonUtils.toJson(promptsIncentivoOfertasPresS2s);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            case VO_CARTAO:
                VoCartao mergedVoCartao = (VoCartao) JsonUtils.fromJson(value[3], new VoCartao());

                List<VoCartao> promptsVoCartaos = JsonUtils.fromJsonList(value[2], VoCartao.class);
                Collections.sort(promptsVoCartaos);
                logger.trace("configOfertasPreList.getClass(): {}", promptsVoCartaos.getClass());
                for (int i = 0; i < promptsVoCartaos.size(); i++) {
                    VoCartao bean = (VoCartao) JsonUtils.fromJson(JsonUtils.toJson(promptsVoCartaos.get(i)), mergedVoCartao);
                    Map<String, Boolean> collect = bean.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(bean.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, VoCartao.class), bean);
                }
                mergedVoCartao.setEditMap(mapEdit);

                promptsVoCartaos = new ArrayList<>();
                promptsVoCartaos.add(mergedVoCartao);
                mergedJson = JsonUtils.toJson(promptsVoCartaos);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            case BO_CARTAO:
                BoCartao mergedBoCartao = (BoCartao) JsonUtils.fromJson(value[3], new BoCartao());

                List<BoCartao> promptsBoCartaos = JsonUtils.fromJsonList(value[2], BoCartao.class);
                Collections.sort(promptsBoCartaos);
                logger.trace("configOfertasPreList.getClass(): {}", promptsBoCartaos.getClass());
                for (int i = 0; i < promptsBoCartaos.size(); i++) {
                    BoCartao bean = (BoCartao) JsonUtils.fromJson(JsonUtils.toJson(promptsBoCartaos.get(i)), mergedBoCartao);
                    Map<String, Boolean> collect = bean.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(bean.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, BoCartao.class), bean);
                }
                mergedBoCartao.setEditMap(mapEdit);

                promptsBoCartaos = new ArrayList<>();
                promptsBoCartaos.add(mergedBoCartao);
                mergedJson = JsonUtils.toJson(promptsBoCartaos);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            case FRASES_CARTAO:
                FrasesCartao mergedFrasesCartao = (FrasesCartao) JsonUtils.fromJson(value[3], new FrasesCartao());

                List<FrasesCartao> promptsFrasesCartaos = JsonUtils.fromJsonList(value[2], FrasesCartao.class);
                Collections.sort(promptsFrasesCartaos);
                logger.trace("configOfertasPreList.getClass(): {}", promptsFrasesCartaos.getClass());
                for (int i = 0; i < promptsFrasesCartaos.size(); i++) {
                    FrasesCartao bean = (FrasesCartao) JsonUtils.fromJson(JsonUtils.toJson(promptsFrasesCartaos.get(i)), mergedFrasesCartao);
                    Map<String, Boolean> collect = bean.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(bean.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, FrasesCartao.class), bean);
                }
                mergedFrasesCartao.setEditMap(mapEdit);

                promptsFrasesCartaos = new ArrayList<>();
                promptsFrasesCartaos.add(mergedFrasesCartao);
                mergedJson = JsonUtils.toJson(promptsFrasesCartaos);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            case IO_CARTAO_CARTAO:
                IoCartaoCartao mergedIoCartaoCartao = (IoCartaoCartao) JsonUtils.fromJson(value[3], new IoCartaoCartao());

                List<IoCartaoCartao> promptsIoCartaoCartaos = JsonUtils.fromJsonList(value[2], IoCartaoCartao.class);
                Collections.sort(promptsIoCartaoCartaos);
                logger.trace("configOfertasPreList.getClass(): {}", promptsIoCartaoCartaos.getClass());
                for (int i = 0; i < promptsIoCartaoCartaos.size(); i++) {
                    IoCartaoCartao bean = (IoCartaoCartao) JsonUtils.fromJson(JsonUtils.toJson(promptsIoCartaoCartaos.get(i)), mergedIoCartaoCartao);
                    Map<String, Boolean> collect = bean.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(bean.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, IoCartaoCartao.class), bean);
                }
                mergedIoCartaoCartao.setEditMap(mapEdit);

                promptsIoCartaoCartaos = new ArrayList<>();
                promptsIoCartaoCartaos.add(mergedIoCartaoCartao);
                mergedJson = JsonUtils.toJson(promptsIoCartaoCartaos);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            case IO_CARTAO_CARTAO_S2S:
                IoCartaoCartaoS2S mergedIoCartaoCartaoS2s = (IoCartaoCartaoS2S) JsonUtils.fromJson(value[3], new IoCartaoCartaoS2S());

                List<IoCartaoCartaoS2S> promptsIoCartaoCartaosS2s = JsonUtils.fromJsonList(value[2], IoCartaoCartaoS2S.class);
                Collections.sort(promptsIoCartaoCartaosS2s);
                logger.trace("configOfertasPreList.getClass(): {}", promptsIoCartaoCartaosS2s.getClass());
                for (int i = 0; i < promptsIoCartaoCartaosS2s.size(); i++) {
                    IoCartaoCartaoS2S bean = (IoCartaoCartaoS2S) JsonUtils.fromJson(JsonUtils.toJson(promptsIoCartaoCartaosS2s.get(i)), mergedIoCartaoCartaoS2s);
                    Map<String, Boolean> collect = bean.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(bean.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, IoCartaoCartaoS2S.class), bean);
                }
                mergedIoCartaoCartaoS2s.setEditMap(mapEdit);

                promptsIoCartaoCartaosS2s = new ArrayList<>();
                promptsIoCartaoCartaosS2s.add(mergedIoCartaoCartaoS2s);
                mergedJson = JsonUtils.toJson(promptsIoCartaoCartaosS2s);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            case TB_CARTPROMOCOES:
                PromocoesCartao mergedPromocoesCartao = (PromocoesCartao) JsonUtils.fromJson(value[3], new PromocoesCartao());

                List<PromocoesCartao> promptsPromocoesCartaos = JsonUtils.fromJsonList(value[2], PromocoesCartao.class);
                Collections.sort(promptsPromocoesCartaos);
                logger.trace("configOfertasPreList.getClass(): {}", promptsPromocoesCartaos.getClass());
                for (int i = 0; i < promptsPromocoesCartaos.size(); i++) {
                    PromocoesCartao bean = (PromocoesCartao) JsonUtils.fromJson(JsonUtils.toJson(promptsPromocoesCartaos.get(i)), mergedPromocoesCartao);
                    Map<String, Boolean> collect = bean.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(bean.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, PromocoesCartao.class), bean);
                }
                mergedPromocoesCartao.setEditMap(mapEdit);

                promptsPromocoesCartaos = new ArrayList<>();
                promptsPromocoesCartaos.add(mergedPromocoesCartao);
                mergedJson = JsonUtils.toJson(promptsPromocoesCartaos);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            case TB_CARTAOMENU:
                CartaoMenu mergedCartaoMenu = (CartaoMenu) JsonUtils.fromJson(value[3], new CartaoMenu());

                List<CartaoMenu> promptsCartaoMenus = JsonUtils.fromJsonList(value[2], CartaoMenu.class);
                Collections.sort(promptsCartaoMenus);
                logger.trace("configOfertasPreList.getClass(): {}", promptsCartaoMenus.getClass());
                for (int i = 0; i < promptsCartaoMenus.size(); i++) {
                    CartaoMenu bean = (CartaoMenu) JsonUtils.fromJson(JsonUtils.toJson(promptsCartaoMenus.get(i)), mergedCartaoMenu);
                    Map<String, Boolean> collect = bean.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(bean.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, CartaoMenu.class), bean);
                }
                mergedCartaoMenu.setEditMap(mapEdit);

                promptsCartaoMenus = new ArrayList<>();
                promptsCartaoMenus.add(mergedCartaoMenu);
                mergedJson = JsonUtils.toJson(promptsCartaoMenus);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            case PROMPT_CARTAOMENU:
                PromptCartaoMenu mergedPromptCartaoMenu = (PromptCartaoMenu) JsonUtils.fromJson(value[3], new PromptCartaoMenu());

                List<PromptCartaoMenu> promptsPromptCartaoMenus = JsonUtils.fromJsonList(value[2], PromptCartaoMenu.class);
                Collections.sort(promptsPromptCartaoMenus);
                logger.trace("configOfertasPreList.getClass(): {}", promptsPromptCartaoMenus.getClass());
                for (int i = 0; i < promptsPromptCartaoMenus.size(); i++) {
                    PromptCartaoMenu bean = (PromptCartaoMenu) JsonUtils.fromJson(JsonUtils.toJson(promptsPromptCartaoMenus.get(i)), mergedPromptCartaoMenu);
                    Map<String, Boolean> collect = bean.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(bean.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, PromptCartaoMenu.class), bean);
                }
                mergedPromptCartaoMenu.setEditMap(mapEdit);

                promptsPromptCartaoMenus = new ArrayList<>();
                promptsPromptCartaoMenus.add(mergedPromptCartaoMenu);
                mergedJson = JsonUtils.toJson(promptsPromptCartaoMenus);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            case FRASES_CONTROLE_CAD:
                FrasesControleCad mergedFrasesControleCad = (FrasesControleCad) JsonUtils.fromJson(value[3], new FrasesControleCad());

                List<FrasesControleCad> promptsFrasesControleCads = JsonUtils.fromJsonList(value[2], FrasesControleCad.class);
                Collections.sort(promptsFrasesControleCads);
                logger.trace("configOfertasPreList.getClass(): {}", promptsFrasesControleCads.getClass());
                for (int i = 0; i < promptsFrasesControleCads.size(); i++) {
                    FrasesControleCad bean = (FrasesControleCad) JsonUtils.fromJson(JsonUtils.toJson(promptsFrasesControleCads.get(i)), mergedFrasesControleCad);
                    Map<String, Boolean> collect = bean.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(bean.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, FrasesControleCad.class), bean);
                }
                mergedFrasesControleCad.setEditMap(mapEdit);

                promptsFrasesControleCads = new ArrayList<>();
                promptsFrasesControleCads.add(mergedFrasesControleCad);
                mergedJson = JsonUtils.toJson(promptsFrasesControleCads);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            case LISTA_PROMPTS:
                Prompt mergedPrompt = (Prompt) JsonUtils.fromJson(value[3], new Prompt());

                List<Prompt> prompts = JsonUtils.fromJsonList(value[2], Prompt.class);
                Collections.sort(prompts);
                logger.trace("configOfertasPreList.getClass(): {}", prompts.getClass());
                for (int i = 0; i < prompts.size(); i++) {
                    Prompt bean = (Prompt) JsonUtils.fromJson(JsonUtils.toJson(prompts.get(i)), mergedPrompt);
                    Map<String, Boolean> collect = bean.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(bean.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, Prompt.class), bean);
                }
                mergedPrompt.setEditMap(mapEdit);

                prompts = new ArrayList<>();
                prompts.add(mergedPrompt);
                mergedJson = JsonUtils.toJson(prompts);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;
            case INCENTIVOS_PORTABILIDADE_OCS:
                IncentivosPortabilidadeOcs mergedIncentivosPortabilidadeOcs = (IncentivosPortabilidadeOcs) JsonUtils.fromJson(value[3], new IncentivosPortabilidadeOcs());

                List<IncentivosPortabilidadeOcs> promptsIncentivosPortabilidadeOcs = JsonUtils.fromJsonList(value[2], IncentivosPortabilidadeOcs.class);
                Collections.sort(promptsIncentivosPortabilidadeOcs);
                logger.trace("configOfertasPreList.getClass(): {}", promptsIncentivosPortabilidadeOcs.getClass());
                for (int i = 0; i < promptsIncentivosPortabilidadeOcs.size(); i++) {
                    IncentivosPortabilidadeOcs bean = (IncentivosPortabilidadeOcs) JsonUtils.fromJson(JsonUtils.toJson(promptsIncentivosPortabilidadeOcs.get(i)), mergedIncentivosPortabilidadeOcs);
                    Map<String, Boolean> collect = bean.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(bean.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, IncentivosPortabilidadeOcs.class), bean);
                }
                mergedIncentivosPortabilidadeOcs.setEditMap(mapEdit);

                promptsIncentivosPortabilidadeOcs = new ArrayList<>();
                promptsIncentivosPortabilidadeOcs.add(mergedIncentivosPortabilidadeOcs);
                mergedJson = JsonUtils.toJson(promptsIncentivosPortabilidadeOcs);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;
            case OFERTAS_PORTABILIDADE:
                OfertasPortabilidade mergedOfertasPortabilidade = (OfertasPortabilidade) JsonUtils.fromJson(value[3], new OfertasPortabilidade());

                List<OfertasPortabilidade> promptsOfertasPortabilidade = JsonUtils.fromJsonList(value[2], OfertasPortabilidade.class);
                Collections.sort(promptsOfertasPortabilidade);
                logger.trace("configOfertasPreList.getClass(): {}", promptsOfertasPortabilidade.getClass());
                for (int i = 0; i < promptsOfertasPortabilidade.size(); i++) {
                    OfertasPortabilidade bean = (OfertasPortabilidade) JsonUtils.fromJson(JsonUtils.toJson(promptsOfertasPortabilidade.get(i)), mergedOfertasPortabilidade);
                    Map<String, Boolean> collect = bean.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(bean.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, OfertasPortabilidade.class), bean);
                }
                mergedOfertasPortabilidade.setEditMap(mapEdit);

                promptsOfertasPortabilidade = new ArrayList<>();
                promptsOfertasPortabilidade.add(mergedOfertasPortabilidade);
                mergedJson = JsonUtils.toJson(promptsOfertasPortabilidade);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;
            case OFERTAS_PORTABILIDADE_OCS:
                OfertasPortabilidadeOcs mergedOfertasPortabilidadeOcs = (OfertasPortabilidadeOcs) JsonUtils.fromJson(value[3], new OfertasPortabilidadeOcs());

                List<OfertasPortabilidadeOcs> promptsOfertasPortabilidadeOcs = JsonUtils.fromJsonList(value[2], OfertasPortabilidadeOcs.class);
                Collections.sort(promptsOfertasPortabilidadeOcs);
                logger.trace("configOfertasPreList.getClass(): {}", promptsOfertasPortabilidadeOcs.getClass());
                for (int i = 0; i < promptsOfertasPortabilidadeOcs.size(); i++) {
                    OfertasPortabilidadeOcs bean = (OfertasPortabilidadeOcs) JsonUtils.fromJson(JsonUtils.toJson(promptsOfertasPortabilidadeOcs.get(i)), mergedOfertasPortabilidadeOcs);
                    Map<String, Boolean> collect = bean.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(bean.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, OfertasPortabilidadeOcs.class), bean);
                }
                mergedOfertasPortabilidadeOcs.setEditMap(mapEdit);

                promptsOfertasPortabilidadeOcs = new ArrayList<>();
                promptsOfertasPortabilidadeOcs.add(mergedOfertasPortabilidadeOcs);
                mergedJson = JsonUtils.toJson(promptsOfertasPortabilidadeOcs);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;
            case FRASES_PORTABILIDADE:
                FrasesPortabilidade mergedFrasesPortabilidade = (FrasesPortabilidade) JsonUtils.fromJson(value[3], new FrasesPortabilidade());

                List<FrasesPortabilidade> promptsFrasesPortabilidade = JsonUtils.fromJsonList(value[2], FrasesPortabilidade.class);
                Collections.sort(promptsFrasesPortabilidade);
                logger.trace("configOfertasPreList.getClass(): {}", promptsFrasesPortabilidade.getClass());
                for (int i = 0; i < promptsFrasesPortabilidade.size(); i++) {
                    FrasesPortabilidade bean = (FrasesPortabilidade) JsonUtils.fromJson(JsonUtils.toJson(promptsFrasesPortabilidade.get(i)), mergedFrasesPortabilidade);
                    Map<String, Boolean> collect = bean.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(bean.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, FrasesPortabilidade.class), bean);
                }
                mergedFrasesPortabilidade.setEditMap(mapEdit);

                promptsFrasesPortabilidade = new ArrayList<>();
                promptsFrasesPortabilidade.add(mergedFrasesPortabilidade);
                mergedJson = JsonUtils.toJson(promptsFrasesPortabilidade);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;
            case FRASES_PORTABILIDADE_OCS:
                FrasesPortabilidadeOcs mergedFrasesPortabilidadeOcs = (FrasesPortabilidadeOcs) JsonUtils.fromJson(value[3], new FrasesPortabilidadeOcs());

                List<FrasesPortabilidadeOcs> promptsFrasesPortabilidadeOcs = JsonUtils.fromJsonList(value[2], FrasesPortabilidadeOcs.class);
                Collections.sort(promptsFrasesPortabilidadeOcs);
                logger.trace("configOfertasPreList.getClass(): {}", promptsFrasesPortabilidadeOcs.getClass());
                for (int i = 0; i < promptsFrasesPortabilidadeOcs.size(); i++) {
                    FrasesPortabilidadeOcs bean = (FrasesPortabilidadeOcs) JsonUtils.fromJson(JsonUtils.toJson(promptsFrasesPortabilidadeOcs.get(i)), mergedFrasesPortabilidadeOcs);
                    Map<String, Boolean> collect = bean.getEditMap().entrySet().stream()
                            .filter(x -> x.getValue().equals(Boolean.TRUE))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    mapEdit.putAll(collect);

                    logger.trace("addHistory: {}", JsonUtils.toJson(bean.getHistory()));
                    mergedHistory = JsonUtils.addToJsonArray(JsonUtils.fromJsonList(mergedHistory, FrasesPortabilidadeOcs.class), bean);
                }
                mergedFrasesPortabilidadeOcs.setEditMap(mapEdit);

                promptsFrasesPortabilidadeOcs = new ArrayList<>();
                promptsFrasesPortabilidadeOcs.add(mergedFrasesPortabilidadeOcs);
                mergedJson = JsonUtils.toJson(promptsFrasesPortabilidadeOcs);

                logger.trace("mergedJson: {}", mergedJson);
                logger.trace("mergedHistory: {}", mergedHistory);

                break;

            default:
                throw new SQLException("regras para tabela " + tabela.name() + " não encontradas!");
        }
    }
}
