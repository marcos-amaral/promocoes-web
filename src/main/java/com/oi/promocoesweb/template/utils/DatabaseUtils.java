/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.utils;

import com.oi.promocoesweb.dbms.entity.User;
import com.oi.promocoesweb.template.entity.BoCartao;
import com.oi.promocoesweb.template.entity.CadTemplatePreOcs;
import com.oi.promocoesweb.template.entity.ConfigOfertasPre;
import com.oi.promocoesweb.template.entity.FraseologiasOfertasPre;
import com.oi.promocoesweb.template.entity.FrasesCartao;
import com.oi.promocoesweb.template.entity.IncentivoOfertasPre;
import com.oi.promocoesweb.template.entity.IoCartaoCartao;
import com.oi.promocoesweb.template.entity.IoCartaoControle;
import com.oi.promocoesweb.template.entity.Prompt;
import com.oi.promocoesweb.template.entity.PromptsOfertasPre;
import com.oi.promocoesweb.template.entity.Template;
import com.oi.promocoesweb.template.entity.VoCartao;
import com.oi.promocoesweb.template.enums.TabelasEnum;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mpma0
 */
public class DatabaseUtils {

    private static final String json_ex = 
            "[ {\n"
            + "  \"user\" : null,\n"
            + "  \"editMap\" : { },\n"
            + "  \"modified\" : null,\n"
            + "  \"deleted\" : false,\n"
            + "  \"ddd\" : \"11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 22, 24, 27, 28, 31, 32, 33, 34, 35, 37, 38, 41, 42, 43, 44, 45, 46, 47, 48, 49, 51, 53, 54, 55, 61, 62, 63, 64, 65, 66, 67, 68, 69, 73, 74, 75, 77, 79, 81, 82, 83, 84, 85, 86, 87, 88, 89, 92, 93, 94, 95, 96, 97, 98, 99\",\n"
            + "  \"id_campanha_siebel1\" : \"1-11326004771\",\n"
            + "  \"id_oferta_siebel1\" : \"1-11326028331\",\n"
            + "  \"id_campanha_siebel2\" : null,\n"
            + "  \"id_oferta_siebel2\" : null,\n"
            + "  \"id_campanha_siebel3\" : null,\n"
            + "  \"id_oferta_siebel3\" : null,\n"
            + "  \"id_campanha_default\" : \"1-11326004771\",\n"
            + "  \"id_oferta_default\" : \"1-11326028331\",\n"
            + "  \"msg_ocs_sva\" : null,\n"
            + "  \"tipo_bonus\" : \"PILOTO_OCS_FLEX-BD\"\n"
            + "} ]";

    public static void main(String[] args) throws SQLException, Exception {
        CadTemplatePreOcs editConfigOfertasPre = new CadTemplatePreOcs();
        
        User user = new User();
        user.setLogin("marcos.amaral");
        user.setId(1);
        
        editConfigOfertasPre.setUser(user);
        editConfigOfertasPre.setEditMsg_ocs_sva(true);
        editConfigOfertasPre.setMsg_ocs_sva("MSG");
        editConfigOfertasPre.setId(1);
        editConfigOfertasPre.setModified(new Date());
        editConfigOfertasPre.setHistory(json_ex);
        
        System.out.println(JsonUtils.addToJsonArray(editConfigOfertasPre.getHistory(), editConfigOfertasPre));
    }

    public static void insertHistory() throws SQLException, Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        //Connection con = DriverManager.getConnection("jdbc:oracle:thin:@10.3.29.30:1521:xe", "PROMOCOESWEB", "Everis@2020");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PROMOCOESWEB", "Everis@2020");

        //loadHistoryCONFIG_OFERTASPRE(con, "CONFIG_OFERTASPRE");
        //loadHistoryPROMPTS_OFERTASPRE(con, "PROMPTS_OFERTASPRE");
        //loadHistoryFRASEOLOGIAS_OFERTASPRE(con, "FRASEOLOGIAS_OFERTASPRE");
        //loadHistoryINCENTIVO_OFERTASPRE(con, "INCENTIVO_OFERTASPRE");
        //loadHistoryVO_CARTAO(con, "VO_CARTAO");
        //loadHistoryBO_CARTAO(con, "BO_CARTAO");
        //loadHistoryFRASES_CARTAO(con, "FRASES_CARTAO");
        //loadHistoryIO_CARTAO_CARTAO(con, "IO_CARTAO_CARTAO");
        //loadHistoryIO_CARTAO_CONTROLE(con, "IO_CARTAO_CONTROLE");
        //loadHistoryLISTA_PROMPTS(con, "LISTA_PROMPTS");
        //getAll(Tabelas.VO_CARTAO, con);
        //insert(Tabelas.VO_CARTAO, con);
        con.close();

    }

    public static void loadHistoryCONFIG_OFERTASPRE(Connection con, String tabela) throws SQLException, IOException, Exception {
        Statement stmt = con.createStatement();

        ResultSet resultSet = stmt.executeQuery("select * from " + tabela);

        List<Template> globalConfigOfertasPreList = new ArrayList<>();
        while (resultSet.next()) {
            List<Object> localConfigOfertasPreList = new ArrayList<>();
            ConfigOfertasPre obj = new ConfigOfertasPre();
            obj.setId(resultSet.getInt("ID"));
            obj.setId_beneficio(resultSet.getString("ID_BENEFICIO"));
            obj.setId_programa(resultSet.getString("ID_PROGRAMA"));
            obj.setId_ofertaocs(resultSet.getString("ID_OFERTAOCS"));
            obj.setId_campanhasiebel(resultSet.getString("ID_CAMPANHASIEBEL"));
            obj.setId_ofertasiebel(resultSet.getString("ID_OFERTASIEBEL"));
            obj.setDdd(resultSet.getString("DDD"));
            obj.setData_inic(resultSet.getDate("DATA_INIC"));
            obj.setData_fim(resultSet.getDate("DATA_FIM"));
            obj.setTipo_oferta(resultSet.getString("TIPO_OFERTA"));
            obj.setElegivelconversao(resultSet.getString("ELEGIVELCONVERSAO"));
            obj.setFreeunitvozelegivel(resultSet.getString("FREEUNITVOZELEGIVEL"));
            obj.setFreeunitdadoselegivel(resultSet.getString("FREEUNITDADOSELEGIVEL"));
            obj.setLimmaxconvmin(resultSet.getString("LIMMAXCONVMIN"));
            obj.setLimmaxconvmb(resultSet.getString("LIMMAXCONVMB"));
            obj.setSaldomindadosconvmb(resultSet.getString("SALDOMINDADOSCONVMB"));
            obj.setValor_taxaadesao(resultSet.getString("VALOR_TAXAADESAO"));
            obj.setExistefallback(resultSet.getString("EXISTEFALLBACK"));
            obj.setExisterollover(resultSet.getString("EXISTEROLLOVER"));
            obj.setShort_code(resultSet.getString("SHORT_CODE"));

            obj.getEditMap().put("DATA_INIC", Boolean.TRUE);
            obj.getEditMap().put("DATA_FIM", Boolean.TRUE);
            obj.getEditMap().put("TIPO_OFERTA", Boolean.TRUE);
            obj.getEditMap().put("ELEGIVELCONVERSAO", Boolean.TRUE);
            obj.getEditMap().put("FREEUNITVOZELEGIVEL", Boolean.TRUE);
            obj.getEditMap().put("FREEUNITDADOSELEGIVEL", Boolean.TRUE);
            obj.getEditMap().put("LIMMAXCONVMIN", Boolean.TRUE);
            obj.getEditMap().put("LIMMAXCONVMB", Boolean.TRUE);
            obj.getEditMap().put("SALDOMINDADOSCONVMB", Boolean.TRUE);
            obj.getEditMap().put("VALOR_TAXAADESAO", Boolean.TRUE);
            obj.getEditMap().put("EXISTEFALLBACK", Boolean.TRUE);
            obj.getEditMap().put("EXISTEROLLOVER", Boolean.TRUE);
            obj.getEditMap().put("SHORT_CODE", Boolean.TRUE);

            localConfigOfertasPreList.add(obj);
            String json = JsonUtils.toJson(localConfigOfertasPreList);
            obj.setHistory(json);

            globalConfigOfertasPreList.add(obj);

        }

        updateHistory(globalConfigOfertasPreList, tabela, con);
    }

    public static void loadHistoryPROMPTS_OFERTASPRE(Connection con, String tabela) throws SQLException, IOException, Exception {
        Statement stmt = con.createStatement();

        ResultSet resultSet = stmt.executeQuery("select * from " + tabela);

        List<Template> globalList = new ArrayList<>();
        while (resultSet.next()) {
            List<Object> localList = new ArrayList<>();
            PromptsOfertasPre obj = new PromptsOfertasPre();
            obj.setId(resultSet.getInt("ID"));
            obj.setId_bfpg(resultSet.getString("ID_BFPG"));
            obj.setId_ofertaocs(resultSet.getString("ID_OFERTAOCS"));
            obj.setDdd(resultSet.getString("DDD"));
            obj.setJacliente_oferta(resultSet.getString("JACLIENTE_OFERTA"));
            obj.setJacliente_sva(resultSet.getString("JACLIENTE_SVA"));
            obj.setDetalhesoferta(resultSet.getString("DETALHESOFERTA"));
            obj.setRegrarollover(resultSet.getString("REGRAROLLOVER"));
            obj.setRegrafallback(resultSet.getString("REGRAFALLBACK"));
            obj.setOfertamigrados(resultSet.getString("OFERTAMIGRADOS"));
            obj.setNaocliente_informaoferta(resultSet.getString("NAOCLIENTE_INFORMAOFERTA"));
            obj.setNaocliente_incentivooferta(resultSet.getString("NAOCLIENTE_INCENTIVOOFERTA"));
            obj.setIncentivooferta(resultSet.getString("INCENTIVOOFERTA"));
            obj.setHistory(resultSet.getString("HISTORY"));

            obj.getEditMap().put("JACLIENTE_OFERTA", Boolean.TRUE);
            obj.getEditMap().put("JACLIENTE_SVA", Boolean.TRUE);
            obj.getEditMap().put("DETALHESOFERTA", Boolean.TRUE);
            obj.getEditMap().put("REGRAROLLOVER", Boolean.TRUE);
            obj.getEditMap().put("REGRAFALLBACK", Boolean.TRUE);
            obj.getEditMap().put("OFERTAMIGRADOS", Boolean.TRUE);
            obj.getEditMap().put("NAOCLIENTE_INFORMAOFERTA", Boolean.TRUE);
            obj.getEditMap().put("NAOCLIENTE_INCENTIVOOFERTA", Boolean.TRUE);
            obj.getEditMap().put("INCENTIVOOFERTA", Boolean.TRUE);

            localList.add(obj);
            String json = JsonUtils.toJson(localList);
            obj.setHistory(json);

            globalList.add(obj);

        }

        updateHistory(globalList, tabela, con);
    }

    public static void loadHistoryFRASEOLOGIAS_OFERTASPRE(Connection con, String tabela) throws SQLException, IOException, Exception {
        Statement stmt = con.createStatement();

        ResultSet resultSet = stmt.executeQuery("select * from " + tabela);

        List<Template> globalList = new ArrayList<>();
        while (resultSet.next()) {
            List<Object> localList = new ArrayList<>();
            FraseologiasOfertasPre obj = new FraseologiasOfertasPre();
            obj.setId(resultSet.getInt("ID"));
            obj.setId_beneficio(resultSet.getString("ID_BENEFICIO"));
            obj.setId_programa(resultSet.getString("ID_PROGRAMA"));
            obj.setId_ofertaocs(resultSet.getString("ID_OFERTAOCS"));
            obj.setDdd(resultSet.getString("DDD"));
            obj.setJacliente_oferta_ussd(resultSet.getString("JACLIENTE_OFERTA_USSD"));
            obj.setJacliente_sva_ussd(resultSet.getString("JACLIENTE_SVA_USSD"));
            obj.setDetalhesoferta_ussd(resultSet.getString("DETALHESOFERTA_USSD"));
            obj.setRegrarollover_ussd(resultSet.getString("REGRAROLLOVER_USSD"));
            obj.setRegrafallback_ussd(resultSet.getString("REGRAFALLBACK_USSD"));
            obj.setOfertamigrados_ussd(resultSet.getString("OFERTAMIGRADOS_USSD"));
            obj.setNaocliente_informaoferta_ussd(resultSet.getString("NAOCLIENTE_INFORMAOFERTA_USSD"));
            obj.setNaocliente_incentivooferta_ussd(resultSet.getString("NAOCLIENTE_INCENTIVOOFERTA_USSD"));
            obj.setJacliente_oferta_144(resultSet.getString("JACLIENTE_OFERTA_144"));
            obj.setJacliente_sva_144(resultSet.getString("JACLIENTE_SVA_144"));
            obj.setDetalhesoferta_144(resultSet.getString("DETALHESOFERTA_144"));
            obj.setRegrarollover_144(resultSet.getString("REGRAROLLOVER_144"));
            obj.setRegrafallback_144(resultSet.getString("REGRAFALLBACK_144"));
            obj.setOfertamigrados_144(resultSet.getString("OFERTAMIGRADOS_144"));
            obj.setNaocliente_informaoferta_144(resultSet.getString("NAOCLIENTE_INFORMAOFERTA_144"));
            obj.setNaocliente_incentivooferta_144(resultSet.getString("NAOCLIENTE_INCENTIVOOFERTA_144"));
            obj.setHistory(resultSet.getString("HISTORY"));

            obj.getEditMap().put("JACLIENTE_OFERTA_USSD", Boolean.TRUE);
            obj.getEditMap().put("JACLIENTE_SVA_USSD", Boolean.TRUE);
            obj.getEditMap().put("DETALHESOFERTA_USSD", Boolean.TRUE);
            obj.getEditMap().put("REGRAFALLBACK_USSD", Boolean.TRUE);
            obj.getEditMap().put("REGRAROLLOVER_USSD", Boolean.TRUE);
            obj.getEditMap().put("NAOCLIENTE_INFORMAOFERTA_USSD", Boolean.TRUE);
            obj.getEditMap().put("NAOCLIENTE_INCENTIVOOFERTA_USSD", Boolean.TRUE);
            obj.getEditMap().put("OFERTAMIGRADOS_USSD", Boolean.TRUE);
            obj.getEditMap().put("INCENTIVOOFERTA_SC_USSD", Boolean.TRUE);
            obj.getEditMap().put("JACLIENTE_OFERTA_144", Boolean.TRUE);
            obj.getEditMap().put("JACLIENTE_SVA_144", Boolean.TRUE);
            obj.getEditMap().put("DETALHESOFERTA_144", Boolean.TRUE);
            obj.getEditMap().put("REGRAFALLBACK_144", Boolean.TRUE);
            obj.getEditMap().put("REGRAROLLOVER_144", Boolean.TRUE);
            obj.getEditMap().put("NAOCLIENTE_INFORMAOFERTA_144", Boolean.TRUE);
            obj.getEditMap().put("NAOCLIENTE_INCENTIVOOFERTA_144", Boolean.TRUE);
            obj.getEditMap().put("OFERTAMIGRADOS_144", Boolean.TRUE);
            obj.getEditMap().put("INCENTIVOOFERTA_SC_144", Boolean.TRUE);

            localList.add(obj);
            String json = JsonUtils.toJson(localList);
            obj.setHistory(json);

            globalList.add(obj);

        }

        updateHistory(globalList, tabela, con);
    }

    public static void loadHistoryINCENTIVO_OFERTASPRE(Connection con, String tabela) throws SQLException, IOException, Exception {
        Statement stmt = con.createStatement();

        ResultSet resultSet = stmt.executeQuery("select * from " + tabela);

        List<Template> globalList = new ArrayList<>();
        while (resultSet.next()) {
            List<Object> localList = new ArrayList<>();
            IncentivoOfertasPre obj = new IncentivoOfertasPre();
            obj.setId(resultSet.getInt("ID"));
            obj.setDdd(resultSet.getString("DDD"));
            obj.setOfertapromo1_id_ofertaocs(resultSet.getString("OFERTAPROMO1_ID_OFERTAOCS"));
            obj.setOfertapromo2_id_ofertaocs(resultSet.getString("OFERTAPROMO2_ID_OFERTAOCS"));
            obj.setRecacumuladainicial(resultSet.getString("RECACUMULADAINICIAL"));
            obj.setRecacumuladafinal(resultSet.getString("RECACUMULADAFINAL"));
            obj.setHistory(resultSet.getString("HISTORY"));

            obj.getEditMap().put("RECACUMULADAINICIAL", Boolean.TRUE);
            obj.getEditMap().put("RECACUMULADAFINAL", Boolean.TRUE);
            obj.getEditMap().put("ID_OFERTAOCS1", Boolean.TRUE);
            obj.getEditMap().put("ID_OFERTAOCS2", Boolean.TRUE);

            localList.add(obj);
            String json = JsonUtils.toJson(localList);
            obj.setHistory(json);

            globalList.add(obj);

        }

        updateHistory(globalList, tabela, con);
    }

    public static void loadHistoryVO_CARTAO(Connection con, String tabela) throws SQLException, IOException, Exception {
        Statement stmt = con.createStatement();

        ResultSet resultSet = stmt.executeQuery("select * from " + tabela);

        List<Template> globalList = new ArrayList<>();
        while (resultSet.next()) {
            List<Object> localList = new ArrayList<>();
            VoCartao obj = new VoCartao();
            obj.setId(resultSet.getInt("ID"));
            obj.setIdbeneficio(resultSet.getString("IDBENEFICIO"));
            obj.setIdprograma(resultSet.getString("IDPROGRAMA"));
            obj.setDdd(resultSet.getString("DDD"));
            obj.setIncentivo1(resultSet.getString("INCENTIVO1"));
            obj.setIncentivo2(resultSet.getString("INCENTIVO2"));
            obj.setOutrosplanos(resultSet.getString("OUTROSPLANOS"));
            obj.setOfertapromo1(resultSet.getString("OFERTAPROMO1"));
            obj.setOfertapromo2(resultSet.getString("OFERTAPROMO2"));
            obj.setTb_tipo_bonus(resultSet.getString("TB_TIPO_BONUS"));
            obj.setData_inic(resultSet.getDate("DATA_INIC"));
            obj.setData_fim(resultSet.getDate("DATA_FIM"));
            obj.setTaxaadesao(resultSet.getString("TAXAADESAO"));
            obj.setTarifasmsdadoswifi(resultSet.getString("TARIFASMSDADOSWIFI"));
            obj.setTarifasms(resultSet.getString("TARIFASMS"));
            obj.setTarifadados(resultSet.getString("TARIFADADOS"));
            obj.setTarifavoz(resultSet.getString("TARIFAVOZ"));
            obj.setTarifavozfixo(resultSet.getString("TARIFAVOZFIXO"));
            obj.setRecargamin(resultSet.getString("RECARGAMIN"));
            obj.setElegivel_nova_promo(resultSet.getString("ELEGIVEL_NOVA_PROMO"));
            obj.setElegivel_promo_template_primaria(resultSet.getString("ELEGIVEL_PROMO_TEMPLATE_PRIMARIA"));
            obj.setPrioridade(resultSet.getString("PRIORIDADE"));
            obj.setOfertar_sempromo(resultSet.getString("OFERTAR_SEMPROMO"));
            obj.setPrecontratacao(resultSet.getString("PRECONTRATACAO"));
            obj.setCobrartaxa(resultSet.getString("COBRARTAXA"));
            obj.setTb_ussd_convertido(resultSet.getString("TB_USSD_CONVERTIDO"));
            obj.setTb_gatilhoofertasflex(resultSet.getString("TB_GATILHOOFERTASFLEX"));
            obj.setHistory(resultSet.getString("HISTORY"));

            obj.getEditMap().put("INCENTIVO1", Boolean.TRUE);
            obj.getEditMap().put("INCENTIVO2", Boolean.TRUE);
            obj.getEditMap().put("OUTROSPLANOS", Boolean.TRUE);
            obj.getEditMap().put("OFERTAPROMO1", Boolean.TRUE);
            obj.getEditMap().put("OFERTAPROMO2", Boolean.TRUE);
            obj.getEditMap().put("TB_TIPO_BONUS", Boolean.TRUE);
            obj.getEditMap().put("DATA_INIC", Boolean.TRUE);
            obj.getEditMap().put("DATA_FIM", Boolean.TRUE);
            obj.getEditMap().put("TAXAADESAO", Boolean.TRUE);
            obj.getEditMap().put("TARIFASMSDADOSWIFI", Boolean.TRUE);
            obj.getEditMap().put("TARIFASMS", Boolean.TRUE);
            obj.getEditMap().put("TARIFADADOS", Boolean.TRUE);
            obj.getEditMap().put("TARIFAVOZ", Boolean.TRUE);
            obj.getEditMap().put("TARIFAVOZFIXO", Boolean.TRUE);
            obj.getEditMap().put("RECARGAMIN", Boolean.TRUE);
            obj.getEditMap().put("ELEGIVEL_NOVA_PROMO", Boolean.TRUE);
            obj.getEditMap().put("ELEGIVEL_PROMO_TEMPLATE_PRIMARIA", Boolean.TRUE);
            obj.getEditMap().put("PRIORIDADE", Boolean.TRUE);
            obj.getEditMap().put("OFERTAR_SEMPROMO", Boolean.TRUE);
            obj.getEditMap().put("PRECONTRATACAO", Boolean.TRUE);
            obj.getEditMap().put("COBRARTAXA", Boolean.TRUE);
            obj.getEditMap().put("TB_USSD_CONVERTIDO", Boolean.TRUE);
            obj.getEditMap().put("TB_GATILHOOFERTASFLEX", Boolean.TRUE);

            localList.add(obj);
            String json = JsonUtils.toJson(localList);
            obj.setHistory(json);

            globalList.add(obj);

        }

        updateHistory(globalList, tabela, con);
    }

    public static void loadHistoryBO_CARTAO(Connection con, String tabela) throws SQLException, IOException, Exception {
        Statement stmt = con.createStatement();

        ResultSet resultSet = stmt.executeQuery("select * from " + tabela);

        List<Template> globalList = new ArrayList<>();
        while (resultSet.next()) {
            List<Object> localList = new ArrayList<>();
            BoCartao obj = new BoCartao();
            obj.setId(resultSet.getInt("ID"));
            obj.setIdbeneficio(resultSet.getString("IDBENEFICIO"));
            obj.setIdprograma(resultSet.getString("IDPROGRAMA"));
            obj.setDdd(resultSet.getString("DDD"));
            obj.setCpfxc_promopadrao(resultSet.getString("CPFXC_PROMOPADRAO"));
            obj.setCpfxc_semrecarga(resultSet.getString("CPFXC_SEMRECARGA"));
            obj.setCpfxc_chamada(resultSet.getString("CPFXC_CHAMADA"));
            obj.setCpfxc_detalhes(resultSet.getString("CPFXC_DETALHES"));
            obj.setCpfxc_recarga(resultSet.getString("CPFXC_RECARGA"));
            obj.setCpfxc_informavalidade(resultSet.getString("CPFXC_INFORMAVALIDADE"));
            obj.setCpfxc_validade(resultSet.getString("CPFXC_VALIDADE"));
            obj.setCpfxc_contingencia(resultSet.getString("CPFXC_CONTINGENCIA"));
            obj.setNaocpfxc_informapromo(resultSet.getString("NAOCPFXC_INFORMAPROMO"));
            obj.setNaocpfxc_validade(resultSet.getString("NAOCPFXC_VALIDADE"));
            obj.setNaocpfxc_regras(resultSet.getString("NAOCPFXC_REGRAS"));
            obj.setNaocpfxc_recarga(resultSet.getString("NAOCPFXC_RECARGA"));
            obj.setIncentivo_oferta_pre(resultSet.getString("INCENTIVO_OFERTA_PRE"));
            obj.setFallback(resultSet.getString("FALLBACK"));
            obj.setJacliente_sva(resultSet.getString("JACLIENTE_SVA"));
            obj.setInformasaldopromo(resultSet.getString("INFORMASALDOPROMO"));
            obj.setOfertamigrados(resultSet.getString("OFERTAMIGRADOS"));
            obj.setNaocps_infop_ussd(resultSet.getString("NAOCPS_INFOP_USSD"));
            obj.setHistory(resultSet.getString("HISTORY"));

            obj.getEditMap().put("CPFXC_PROMOPADRAO", Boolean.TRUE);
            obj.getEditMap().put("CPFXC_SEMRECARGA", Boolean.TRUE);
            obj.getEditMap().put("CPFXC_CHAMADA", Boolean.TRUE);
            obj.getEditMap().put("CPFXC_DETALHES", Boolean.TRUE);
            obj.getEditMap().put("CPFXC_RECARGA", Boolean.TRUE);
            obj.getEditMap().put("CPFXC_INFORMAVALIDADE", Boolean.TRUE);
            obj.getEditMap().put("CPFXC_VALIDADE", Boolean.TRUE);
            obj.getEditMap().put("CPFXC_CONTINGENCIA", Boolean.TRUE);
            obj.getEditMap().put("NAOCPFXC_INFORMAPROMO", Boolean.TRUE);
            obj.getEditMap().put("NAOCPFXC_VALIDADE", Boolean.TRUE);
            obj.getEditMap().put("NAOCPFXC_REGRAS", Boolean.TRUE);
            obj.getEditMap().put("NAOCPFXC_RECARGA", Boolean.TRUE);
            obj.getEditMap().put("INCENTIVO_OFERTA_PRE", Boolean.TRUE);
            obj.getEditMap().put("FALLBACK", Boolean.TRUE);
            obj.getEditMap().put("JACLIENTE_SVA", Boolean.TRUE);
            obj.getEditMap().put("INFORMASALDOPROMO", Boolean.TRUE);
            obj.getEditMap().put("OFERTAMIGRADOS", Boolean.TRUE);
            obj.getEditMap().put("NAOCPS_INFOP_USSD", Boolean.TRUE);

            localList.add(obj);
            String json = JsonUtils.toJson(localList);
            obj.setHistory(json);

            globalList.add(obj);

        }

        updateHistory(globalList, tabela, con);
    }

    public static void loadHistoryFRASES_CARTAO(Connection con, String tabela) throws SQLException, IOException, Exception {
        Statement stmt = con.createStatement();

        ResultSet resultSet = stmt.executeQuery("select * from " + tabela);

        List<Template> globalList = new ArrayList<>();
        while (resultSet.next()) {
            List<Object> localList = new ArrayList<>();
            FrasesCartao obj = new FrasesCartao();
            obj.setId(resultSet.getInt("ID"));
            obj.setIdbeneficio(resultSet.getString("IDBENEFICIO"));
            obj.setIdprograma(resultSet.getString("IDPROGRAMA"));
            obj.setDdd(resultSet.getString("DDD"));
            obj.setPromopadrao_ussd(resultSet.getString("PROMOPADRAO_USSD"));
            obj.setSemrecarga_ussd(resultSet.getString("SEMRECARGA_USSD"));
            obj.setChamada_ussd(resultSet.getString("CHAMADA_USSD"));
            obj.setDetalhes_ussd(resultSet.getString("DETALHES_USSD"));
            obj.setRecarga_ussd(resultSet.getString("RECARGA_USSD"));
            obj.setInformavalidade_ussd(resultSet.getString("INFORMAVALIDADE_USSD"));
            obj.setValidade_ussd(resultSet.getString("VALIDADE_USSD"));
            obj.setContingencia_ussd(resultSet.getString("CONTINGENCIA_USSD"));
            obj.setNaocliente_informapromo_ussd(resultSet.getString("NAOCLIENTE_INFORMAPROMO_USSD"));
            obj.setNaocliente_validade_ussd(resultSet.getString("NAOCLIENTE_VALIDADE_USSD"));
            obj.setRegras_ussd(resultSet.getString("REGRAS_USSD"));
            obj.setNaocliente_recarga_ussd(resultSet.getString("NAOCLIENTE_RECARGA_USSD"));
            obj.setIncentivo_oferta_pre_ussd(resultSet.getString("INCENTIVO_OFERTA_PRE_USSD"));
            obj.setFallback_ussd(resultSet.getString("FALLBACK_USSD"));
            obj.setSva_ussd(resultSet.getString("SVA_USSD"));
            obj.setInformasaldopromo_ussd(resultSet.getString("INFORMASALDOPROMO_USSD"));
            obj.setOfertamigrados_ussd(resultSet.getString("OFERTAMIGRADOS_USSD"));
            obj.setInformapromo_ussd_ussd(resultSet.getString("INFORMAPROMO_USSD_USSD"));
            obj.setPromopadrao_144(resultSet.getString("PROMOPADRAO_144"));
            obj.setSemrecarga_144(resultSet.getString("SEMRECARGA_144"));
            obj.setChamada_144(resultSet.getString("CHAMADA_144"));
            obj.setDetalhes_144(resultSet.getString("DETALHES_144"));
            obj.setRecarga_144(resultSet.getString("RECARGA_144"));
            obj.setInformavalidade_144(resultSet.getString("INFORMAVALIDADE_144"));
            obj.setValidade_144(resultSet.getString("VALIDADE_144"));
            obj.setContingencia_144(resultSet.getString("CONTINGENCIA_144"));
            obj.setNaocliente_informapromo_144(resultSet.getString("NAOCLIENTE_INFORMAPROMO_144"));
            obj.setNaocliente_validade_144(resultSet.getString("NAOCLIENTE_VALIDADE_144"));
            obj.setRegras_144(resultSet.getString("REGRAS_144"));
            obj.setNaocliente_recarga_144(resultSet.getString("NAOCLIENTE_RECARGA_144"));
            obj.setIncentivo_oferta_pre_144(resultSet.getString("INCENTIVO_OFERTA_PRE_144"));
            obj.setFallback_144(resultSet.getString("FALLBACK_144"));
            obj.setSva_144(resultSet.getString("SVA_144"));
            obj.setInformasaldopromo_144(resultSet.getString("INFORMASALDOPROMO_144"));
            obj.setOfertamigrados_144(resultSet.getString("OFERTAMIGRADOS_144"));
            obj.setInformapromo_ussd_144(resultSet.getString("INFORMAPROMO_USSD_144"));
            obj.setHistory(resultSet.getString("HISTORY"));

            obj.getEditMap().put("PROMOPADRAO_USSD", Boolean.TRUE);
            obj.getEditMap().put("SEMRECARGA_USSD", Boolean.TRUE);
            obj.getEditMap().put("CHAMADA_USSD", Boolean.TRUE);
            obj.getEditMap().put("DETALHES_USSD", Boolean.TRUE);
            obj.getEditMap().put("RECARGA_USSD", Boolean.TRUE);
            obj.getEditMap().put("INFORMAVALIDADE_USSD", Boolean.TRUE);
            obj.getEditMap().put("VALIDADE_USSD", Boolean.TRUE);
            obj.getEditMap().put("CONTINGENCIA_USSD", Boolean.TRUE);
            obj.getEditMap().put("NAOCLIENTE_INFORMAPROMO_USSD", Boolean.TRUE);
            obj.getEditMap().put("NAOCLIENTE_VALIDADE_USSD", Boolean.TRUE);
            obj.getEditMap().put("REGRAS_USSD", Boolean.TRUE);
            obj.getEditMap().put("NAOCLIENTE_RECARGA_USSD", Boolean.TRUE);
            obj.getEditMap().put("INCENTIVO_OFERTA_PRE_USSD", Boolean.TRUE);
            obj.getEditMap().put("FALLBACK_USSD", Boolean.TRUE);
            obj.getEditMap().put("SVA_USSD", Boolean.TRUE);
            obj.getEditMap().put("INFORMASALDOPROMO_USSD", Boolean.TRUE);
            obj.getEditMap().put("OFERTAMIGRADOS_USSD", Boolean.TRUE);
            obj.getEditMap().put("INFORMAPROMO_USSD_USSD", Boolean.TRUE);
            obj.getEditMap().put("PROMOPADRAO_144", Boolean.TRUE);
            obj.getEditMap().put("SEMRECARGA_144", Boolean.TRUE);
            obj.getEditMap().put("CHAMADA_144", Boolean.TRUE);
            obj.getEditMap().put("DETALHES_144", Boolean.TRUE);
            obj.getEditMap().put("RECARGA_144", Boolean.TRUE);
            obj.getEditMap().put("INFORMAVALIDADE_144", Boolean.TRUE);
            obj.getEditMap().put("VALIDADE_144", Boolean.TRUE);
            obj.getEditMap().put("CONTINGENCIA_144", Boolean.TRUE);
            obj.getEditMap().put("NAOCLIENTE_INFORMAPROMO_144", Boolean.TRUE);
            obj.getEditMap().put("NAOCLIENTE_VALIDADE_144", Boolean.TRUE);
            obj.getEditMap().put("REGRAS_144", Boolean.TRUE);
            obj.getEditMap().put("NAOCLIENTE_RECARGA_144", Boolean.TRUE);
            obj.getEditMap().put("INCENTIVO_OFERTA_PRE_144", Boolean.TRUE);
            obj.getEditMap().put("FALLBACK_144", Boolean.TRUE);
            obj.getEditMap().put("SVA_144", Boolean.TRUE);
            obj.getEditMap().put("INFORMASALDOPROMO_144", Boolean.TRUE);
            obj.getEditMap().put("OFERTAMIGRADOS_144", Boolean.TRUE);
            obj.getEditMap().put("INFORMAPROMO_USSD_144", Boolean.TRUE);

            localList.add(obj);
            String json = JsonUtils.toJson(localList);
            obj.setHistory(json);

            globalList.add(obj);

        }

        updateHistory(globalList, tabela, con);
    }

    public static void loadHistoryIO_CARTAO_CARTAO(Connection con, String tabela) throws SQLException, IOException, Exception {
        Statement stmt = con.createStatement();

        ResultSet resultSet = stmt.executeQuery("select * from " + tabela);

        List<Template> globalList = new ArrayList<>();
        while (resultSet.next()) {
            List<Object> localList = new ArrayList<>();
            IoCartaoCartao obj = new IoCartaoCartao();
            obj.setId(resultSet.getInt("ID"));
            obj.setDdd(resultSet.getString("DDD"));
            obj.setOfertapromo1(resultSet.getString("OFERTAPROMO1"));
            obj.setOfertapromo2(resultSet.getString("OFERTAPROMO2"));
            obj.setRecacumuladainicial(resultSet.getString("RECACUMULADAINICIAL"));
            obj.setRecacumuladafinal(resultSet.getString("RECACUMULADAFINAL"));
            obj.setHistory(resultSet.getString("HISTORY"));

            obj.getEditMap().put("RECACUMULADAINICIAL", Boolean.TRUE);
            obj.getEditMap().put("RECACUMULADAFINAL", Boolean.TRUE);
            obj.getEditMap().put("OFERTAPROMO1", Boolean.TRUE);
            obj.getEditMap().put("OFERTAPROMO2", Boolean.TRUE);

            localList.add(obj);
            String json = JsonUtils.toJson(localList);
            obj.setHistory(json);

            globalList.add(obj);

        }

        updateHistory(globalList, tabela, con);
    }

    public static void loadHistoryIO_CARTAO_CONTROLE(Connection con, String tabela) throws SQLException, IOException, Exception {
        Statement stmt = con.createStatement();

        ResultSet resultSet = stmt.executeQuery("select * from " + tabela);

        List<Template> globalList = new ArrayList<>();
        while (resultSet.next()) {
            List<Object> localList = new ArrayList<>();
            IoCartaoControle obj = new IoCartaoControle();
            obj.setId(resultSet.getInt("ID"));
            obj.setDdd(resultSet.getString("DDD"));
            obj.setTb_incentivo1(resultSet.getString("TB_INCENTIVO1"));
            obj.setTb_incentivo2(resultSet.getString("TB_INCENTIVO2"));
            obj.setTb_incentivo3(resultSet.getString("TB_INCENTIVO3"));
            obj.setTb_incentivo4(resultSet.getString("TB_INCENTIVO4"));
            obj.setHistory(resultSet.getString("HISTORY"));

            obj.getEditMap().put("TB_INCENTIVO1", Boolean.TRUE);
            obj.getEditMap().put("TB_INCENTIVO2", Boolean.TRUE);
            obj.getEditMap().put("TB_INCENTIVO3", Boolean.TRUE);
            obj.getEditMap().put("TB_INCENTIVO4", Boolean.TRUE);

            localList.add(obj);
            String json = JsonUtils.toJson(localList);
            obj.setHistory(json);

            globalList.add(obj);

        }

        updateHistory(globalList, tabela, con);
    }

    public static void loadHistoryLISTA_PROMPTS(Connection con, String tabela) throws SQLException, IOException, Exception {
        Statement stmt = con.createStatement();

        ResultSet resultSet = stmt.executeQuery("select * from " + tabela);

        List<Template> globalList = new ArrayList<>();
        while (resultSet.next()) {
            List<Object> localList = new ArrayList<>();
            Prompt obj = new Prompt();
            obj.setId(resultSet.getInt("ID"));
            obj.setWav(resultSet.getString("WAV"));
            obj.setConteudo(resultSet.getString("CONTEUDO"));
            obj.setHistory(resultSet.getString("HISTORY"));

            obj.getEditMap().put("CONTEUDO", Boolean.TRUE);
            obj.setModified(new Date());

            localList.add(obj);
            String json = JsonUtils.toJson(localList);

            obj.setHistory(json);

            globalList.add(obj);

        }

        updateHistory(globalList, tabela, con);
    }

    public static void updateHistory(List<Template> template, String tabela, Connection con) throws IOException, Exception {
        File f = new File("\\script.txt");
        FileWriter fileWriter = new FileWriter(f);

        boolean isFile = true;

        template.forEach((list -> {
            String json = "";
            try {
                json = JsonUtils.toJson(list.getHistory());
                if (isFile) {
                    fileWriter.write("UPDATE " + tabela + " SET HISTORY = " + json + " WHERE ID = " + list.getId());

                } else {

                    String stmt = "UPDATE " + tabela + " SET HISTORY = ? WHERE ID = ?";

                    PreparedStatement preparedStatement = con.prepareStatement(stmt);
                    Clob clob = preparedStatement.getConnection().createClob();
                    clob.setString(1, json);

                    preparedStatement.setClob(1, clob);
                    preparedStatement.setInt(2, list.getId());

                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                }

            } catch (Exception ex) {
                System.out.println("JSON length: " + json.length());
                ex.printStackTrace();
            }
        }));

        fileWriter.close();
    }

    public static void getAll(TabelasEnum tabela, Connection con) throws IOException, Exception {
        try {
            PreparedStatement preparedStatement = con.prepareStatement(tabela.getSelect());

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String json = rs.getString("JSON");
                Object fromJson = JsonUtils.fromJson(json, new VoCartao());
                VoCartao voCartao = (VoCartao) fromJson;

                System.out.println(JsonUtils.toJson(voCartao));
            }

            preparedStatement.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void insert(TabelasEnum tabela, Connection con) throws IOException, Exception {
        try {
            PreparedStatement preparedStatement = con.prepareStatement(tabela.getInsert().replace("?", json_ex));

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void update(TabelasEnum tabela, Connection con) throws IOException, Exception {
        try {
            PreparedStatement preparedStatement = con.prepareStatement(tabela.getUpdate().replaceAll("?", "532"));

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
