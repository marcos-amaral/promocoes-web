package com.oi.promocoesweb.dbms.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.oi.promocoesweb.dbms.core.TemplateWebDataPersistence;
import com.oi.promocoesweb.dbms.entity.User;
import com.oi.promocoesweb.exception.DbPoolFatalException;
import com.oi.promocoesweb.template.entity.BoCartao;
import com.oi.promocoesweb.template.entity.BoControle;
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
import com.oi.promocoesweb.template.entity.aprovacao.Aprovacao;
import com.oi.promocoesweb.template.enums.StatusEnum;
import com.oi.promocoesweb.template.enums.TabelasEnum;
import com.oi.promocoesweb.template.utils.JsonUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import oracle.jdbc.OracleTypes;

public class TemplateDAO {

    /**
     * DAO Logger.
     */
    private static final Logger logger = LogManager.getLogger();
    private static final Charset defaultChartSet = StandardCharsets.UTF_8;
    private final TemplateWebDAO ngrDao;

    public TemplateDAO() {
        this.ngrDao = new TemplateWebDAO(TemplateWebDataPersistence.getInstance());
    }

    public Map<String, Boolean> getCamposObrigatorios(TabelasEnum tabela) throws DbPoolFatalException, SQLException, RuntimeException, Exception {
        StringBuilder statementBuilder = new StringBuilder(1000);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<String, Boolean> mapObrigatorios = new HashMap<>();

        try {
            connection = ngrDao.getConnection();

            statementBuilder.append("SELECT CAMPO,OBRIGATORIO FROM CFG_CAMPOS_OBRIGATORIOS WHERE TABELA = ?");

            preparedStatement = connection.prepareStatement(statementBuilder.toString());

            preparedStatement.setString(1, tabela.name());

            logger.debug("QUERY {}", preparedStatement);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                mapObrigatorios.put(resultSet.getString("CAMPO"), resultSet.getBoolean("OBRIGATORIO"));
            }

        } catch (SQLException e) {
            logger.error("SQLException Executing getCamposObrigatorios", e);
            throw e;
        } catch (DbPoolFatalException e) {
            logger.error("DBPoolFatalException Executing getCamposObrigatorios", e);
            throw e;
        } catch (RuntimeException e) {
            logger.error("UnexpectedException Executing getCamposObrigatorios", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception Executing getCamposObrigatorios", e);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.returnConnection(connection);
        }

        return mapObrigatorios;
    }

    public List<Prompt> getAllPrompts(int limit) throws DbPoolFatalException, SQLException, RuntimeException, Exception {
        StringBuilder statementBuilder = new StringBuilder(1000);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Prompt> configOfertasPreList = new ArrayList<>();

        try {

            connection = ngrDao.getConnection();

            statementBuilder.append("SELECT * FROM (\n");
            statementBuilder.append("SELECT \n");
            statementBuilder.append("    ID,\n");
            statementBuilder.append("    DT_GMUD,\n");
            statementBuilder.append("    TEMPID,\n");
            statementBuilder.append("    WAV,\n");
            statementBuilder.append("    CONTEUDO,\n");
            statementBuilder.append("    HISTORY \n");
            statementBuilder.append("FROM LISTA_PROMPTS \n");
            statementBuilder.append("WHERE (CONTEUDO IS NOT NULL OR INSERTED IS NOT NULL) \n");
            statementBuilder.append("AND DELETED = 0 \n");
            statementBuilder.append("ORDER BY INSERTED DESC");
            statementBuilder.append(") t1 \n");
            if (limit > 0) {
                statementBuilder.append("WHERE ROWNUM <= ").append(limit);
            }

            preparedStatement = connection.prepareStatement(statementBuilder.toString());

            logger.debug("QUERY {}", preparedStatement);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Prompt prompt = new Prompt();

                prompt.setId(resultSet.getInt("ID"));
                prompt.setWav(resultSet.getString("WAV"));
                prompt.setConteudo(resultSet.getString("CONTEUDO"));
                prompt.setHistory(resultSet.getString("HISTORY"));

                if (prompt.getWav().equals("Teste.wav")) {
                    logger.trace("history: {}", prompt.getHistory());
                    logger.trace("mofified: {}", prompt.getModified());
                }

                configOfertasPreList.add(prompt);
            }

        } catch (SQLException e) {
            logger.error("SQLException Executing insertUser", e);
            throw e;
        } catch (DbPoolFatalException e) {
            logger.error("DBPoolFatalException Executing insertUser", e);
            throw e;
        } catch (RuntimeException e) {
            logger.error("UnexpectedException Executing insertUser", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception Executing insertUser", e);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.returnConnection(connection);
        }

        return configOfertasPreList;
    }

    public List<String> getValidIdOfertaOcs(Date dt_gmud) throws DbPoolFatalException, SQLException, RuntimeException, Exception {
        StringBuilder statementBuilder = new StringBuilder(1000);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> list = new ArrayList<>();
        list.add("");

        try {

            connection = ngrDao.getConnection();

            statementBuilder.append("SELECT DISTINCT ID_OFERTAOCS FROM CONFIG_OFERTASPRE \n");
            statementBuilder.append("WHERE CURRENT_DATE >= DATA_INIC AND CURRENT_DATE < DATA_FIM ");
            statementBuilder.append("AND (DT_GMUD IS NULL OR DT_GMUD = ?) ");
            statementBuilder.append("ORDER BY ID_OFERTAOCS DESC");

            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            if(dt_gmud!=null)
                preparedStatement.setDate(1, new java.sql.Date(dt_gmud.getTime()));
            else
                preparedStatement.setNull(1, OracleTypes.DATE);

            logger.debug("QUERY {}", preparedStatement);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(resultSet.getString("ID_OFERTAOCS"));
            }

        } catch (SQLException e) {
            logger.error("SQLException Executing insertUser", e);
            throw e;
        } catch (DbPoolFatalException e) {
            logger.error("DBPoolFatalException Executing insertUser", e);
            throw e;
        } catch (RuntimeException e) {
            logger.error("UnexpectedException Executing insertUser", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception Executing insertUser", e);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.returnConnection(connection);
        }

        return list;
    }

    //pORTABILIDADE
    public List<String> getValidIdOferta(Date dt_gmud) throws DbPoolFatalException, SQLException, RuntimeException, Exception {
        StringBuilder statementBuilder = new StringBuilder(1000);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> list = new ArrayList<>();
        list.add("");

        try {

            connection = ngrDao.getConnection();

            statementBuilder.append("SELECT DISTINCT TB_TIPO_BONUS FROM OFERTAS_PORTABILIDADE \n");
            statementBuilder.append("WHERE CURRENT_DATE >= DATA_INIC AND CURRENT_DATE < DATA_FIM ");
            statementBuilder.append("AND (DT_GMUD IS NULL OR DT_GMUD = ?) ");
            statementBuilder.append("ORDER BY TB_TIPO_BONUS DESC");

            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            if(dt_gmud!=null)
                preparedStatement.setDate(1, new java.sql.Date(dt_gmud.getTime()));
            else
                preparedStatement.setNull(1, OracleTypes.DATE);

            logger.debug("QUERY {}", preparedStatement);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(resultSet.getString("TB_TIPO_BONUS"));
            }

        } catch (SQLException e) {
            logger.error("SQLException Executing insertUser", e);
            throw e;
        } catch (DbPoolFatalException e) {
            logger.error("DBPoolFatalException Executing insertUser", e);
            throw e;
        } catch (RuntimeException e) {
            logger.error("UnexpectedException Executing insertUser", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception Executing insertUser", e);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.returnConnection(connection);
        }

        return list;
    }

    //PortabilidadeOcs
    public List<String> getValidIdOfertaOcsPortabilidade(Date dt_gmud) throws DbPoolFatalException, SQLException, RuntimeException, Exception {
        StringBuilder statementBuilder = new StringBuilder(1000);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> list = new ArrayList<>();
        list.add("");

        try {

            connection = ngrDao.getConnection();

            statementBuilder.append("SELECT DISTINCT TB_TIPO_BONUS FROM OFERTAS_PORTABILIDADE_OCS \n");
            statementBuilder.append("WHERE CURRENT_DATE >= DATA_INIC AND CURRENT_DATE < DATA_FIM ");
            statementBuilder.append("AND (DT_GMUD IS NULL OR DT_GMUD = ?) ");
            statementBuilder.append("ORDER BY TB_TIPO_BONUS DESC");

            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            if(dt_gmud!=null)
                preparedStatement.setDate(1, new java.sql.Date(dt_gmud.getTime()));
            else
                preparedStatement.setNull(1, OracleTypes.DATE);

            logger.debug("QUERY {}", preparedStatement);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(resultSet.getString("TB_TIPO_BONUS"));
            }

        } catch (SQLException e) {
            logger.error("SQLException Executing insertUser", e);
            throw e;
        } catch (DbPoolFatalException e) {
            logger.error("DBPoolFatalException Executing insertUser", e);
            throw e;
        } catch (RuntimeException e) {
            logger.error("UnexpectedException Executing insertUser", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception Executing insertUser", e);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.returnConnection(connection);
        }

        return list;
    }

    public List<String> getValidIdOfertaOcsS2S(Date dt_gmud) throws DbPoolFatalException, SQLException, RuntimeException, Exception {
        StringBuilder statementBuilder = new StringBuilder(1000);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> list = new ArrayList<>();
        list.add("");

        try {

            connection = ngrDao.getConnection();

            statementBuilder.append("SELECT DISTINCT ID_CAMPANHASIEBEL FROM (SELECT ID_CAMPANHASIEBEL FROM CONFIG_OFERTASPRE \n");
            statementBuilder.append("WHERE CURRENT_DATE >= DATA_INIC AND CURRENT_DATE < DATA_FIM ");
            statementBuilder.append("AND (DT_GMUD IS NULL OR DT_GMUD = ?) ");
            statementBuilder.append("ORDER BY DATA_INIC DESC)");

            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            if(dt_gmud!=null)
                preparedStatement.setDate(1, new java.sql.Date(dt_gmud.getTime()));
            else
                preparedStatement.setNull(1, OracleTypes.DATE);

            logger.debug("QUERY {}", preparedStatement);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(resultSet.getString("ID_CAMPANHASIEBEL"));
            }

        } catch (SQLException e) {
            logger.error("SQLException Executing insertUser", e);
            throw e;
        } catch (DbPoolFatalException e) {
            logger.error("DBPoolFatalException Executing insertUser", e);
            throw e;
        } catch (RuntimeException e) {
            logger.error("UnexpectedException Executing insertUser", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception Executing insertUser", e);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.returnConnection(connection);
        }

        return list;
    }

    public List<String> getValidTipoBonus(Date dt_gmud) throws DbPoolFatalException, SQLException, RuntimeException, Exception {
        StringBuilder statementBuilder = new StringBuilder(1000);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> list = new ArrayList<>();
        list.add("");

        try {

            connection = ngrDao.getConnection();

            statementBuilder.append("SELECT DISTINCT TB_TIPO_BONUS FROM (SELECT TB_TIPO_BONUS FROM VO_CARTAO \n");
            statementBuilder.append("WHERE CURRENT_DATE >= DATA_INIC AND CURRENT_DATE < DATA_FIM ");
            statementBuilder.append("AND (DT_GMUD IS NULL OR DT_GMUD = ?) ");
            statementBuilder.append("ORDER BY TB_TIPO_BONUS DESC)");

            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            if(dt_gmud!=null)
                preparedStatement.setDate(1, new java.sql.Date(dt_gmud.getTime()));
            else
                preparedStatement.setNull(1, OracleTypes.DATE);

            logger.debug("QUERY {}", preparedStatement);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(resultSet.getString("TB_TIPO_BONUS"));
            }

        } catch (SQLException e) {
            logger.error("SQLException Executing insertUser", e);
            throw e;
        } catch (DbPoolFatalException e) {
            logger.error("DBPoolFatalException Executing insertUser", e);
            throw e;
        } catch (RuntimeException e) {
            logger.error("UnexpectedException Executing insertUser", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception Executing insertUser", e);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.returnConnection(connection);
        }

        return list;
    }

    public List<String> getValidTipoBonusS2S(Date dt_gmud) throws DbPoolFatalException, SQLException, RuntimeException, Exception {
        StringBuilder statementBuilder = new StringBuilder(1000);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> list = new ArrayList<>();
        list.add("");

        try {

            connection = ngrDao.getConnection();

            statementBuilder.append("SELECT CONCAT(CONCAT(IDBENEFICIO,'.'),IDPROGRAMA) AS IDBENEFICIOEPROGRAMA FROM VO_CARTAO \n");
            statementBuilder.append("WHERE CURRENT_DATE >= DATA_INIC AND CURRENT_DATE < DATA_FIM ");
            statementBuilder.append("AND (DT_GMUD IS NULL OR DT_GMUD = ?) ");
            statementBuilder.append("ORDER BY DATA_INIC DESC");

            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            if(dt_gmud!=null)
                preparedStatement.setDate(1, new java.sql.Date(dt_gmud.getTime()));
            else
                preparedStatement.setNull(1, OracleTypes.DATE);

            logger.debug("QUERY {}", preparedStatement);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(resultSet.getString("IDBENEFICIOEPROGRAMA"));
            }

        } catch (SQLException e) {
            logger.error("SQLException Executing insertUser", e);
            throw e;
        } catch (DbPoolFatalException e) {
            logger.error("DBPoolFatalException Executing insertUser", e);
            throw e;
        } catch (RuntimeException e) {
            logger.error("UnexpectedException Executing insertUser", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception Executing insertUser", e);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.returnConnection(connection);
        }

        return list;
    }

    public List<String> getValidTipoPlano(Date dt_gmud) throws DbPoolFatalException, SQLException, RuntimeException, Exception {
        StringBuilder statementBuilder = new StringBuilder(1000);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> list = new ArrayList<>();
        list.add("");

        try {

            connection = ngrDao.getConnection();

            statementBuilder.append("SELECT DISTINCT TB_TIPOPLANO FROM (SELECT TB_TIPOPLANO FROM VO_CONTROLE \n");
            statementBuilder.append("WHERE CURRENT_DATE >= DATA_INIC AND CURRENT_DATE < DATA_FIM ");
            statementBuilder.append("AND (DT_GMUD IS NULL OR DT_GMUD = ?) ");
            statementBuilder.append("ORDER BY DATA_INIC DESC)");

            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            if (dt_gmud != null) {
                preparedStatement.setDate(1, new java.sql.Date(dt_gmud.getTime()));
            } else {
                preparedStatement.setNull(1, OracleTypes.DATE);
            }

            logger.debug("QUERY {}", preparedStatement);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(resultSet.getString("TB_TIPOPLANO"));
            }

        } catch (SQLException e) {
            logger.error("SQLException Executing insertUser", e);
            throw e;
        } catch (DbPoolFatalException e) {
            logger.error("DBPoolFatalException Executing insertUser", e);
            throw e;
        } catch (RuntimeException e) {
            logger.error("UnexpectedException Executing insertUser", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception Executing insertUser", e);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.returnConnection(connection);
        }

        return list;
    }

    public List<Template> getAll(TabelasEnum tabela, int limit) throws DbPoolFatalException, SQLException, RuntimeException, IOException, Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Template> list = new ArrayList<>();

        try {
            connection = ngrDao.getConnection();

            preparedStatement = connection.prepareStatement(tabela.getSelect());

            logger.debug("QUERY {}", tabela.getSelect());

            resultSet = preparedStatement.executeQuery();

            Object fromJson;
            StringBuilder sb;
            String prompt;

            int i = 0;
            while (resultSet.next() && (i < limit || limit == -1)) {
                i++;

                String json = resultSet.getString("JSON");

                int id = resultSet.getInt("ID");
                int tempid = resultSet.getInt("TEMPID");
                String history = resultSet.getString("HISTORY");
                Date inserted = resultSet.getDate("INSERTED");
                Date dtGmud = resultSet.getDate("DT_GMUD");
                boolean deleted = resultSet.getBoolean("DELETED");

                switch (tabela) {
                    case CONFIG_OFERTASPRE:
                        fromJson = JsonUtils.fromJson(json, new ConfigOfertasPre());
                        ConfigOfertasPre configOfertasPre = (ConfigOfertasPre) fromJson;
                        configOfertasPre.setId(id);
                        configOfertasPre.setTempId(tempid);
                        configOfertasPre.setModified(inserted);
                        configOfertasPre.setHistory(history);
                        configOfertasPre.setDt_gmud(dtGmud);
                        configOfertasPre.setDeleted(deleted);

                        list.add(configOfertasPre);
                        break;

                    case PROMPT_CONTROLEMENU:
                        PromptControleMenu promptControleMenu = new PromptControleMenu();
                        promptControleMenu.setId(id);
                        promptControleMenu.setTempId(tempid);
                        promptControleMenu.setModified(inserted);
                        promptControleMenu.setHistory(history);
                        promptControleMenu.setDt_gmud(dtGmud);
                        promptControleMenu.setDeleted(deleted);

                        promptControleMenu.setTb_id_beneficio(resultSet.getString("TB_ID_BENEFICIO"));
                        promptControleMenu.setTb_id_programa(resultSet.getString("TB_ID_PROGRAMA"));
                        promptControleMenu.setTb_ddd(resultSet.getString("TB_DDD"));
                        promptControleMenu.setPromopadrao_mensageminicial(resultSet.getString("PROMOPADRAO_MENSAGEMINICIAL"));
                        promptControleMenu.setPromopadrao_regratorpedo(resultSet.getString("PROMOPADRAO_REGRATORPEDO"));
                        promptControleMenu.setPromopadrao_regraligacao(resultSet.getString("PROMOPADRAO_REGRALIGACAO"));
                        promptControleMenu.setPromopadrao_comousar(resultSet.getString("PROMOPADRAO_COMOUSAR"));
                        promptControleMenu.setPromopadrao_dados(resultSet.getString("PROMOPADRAO_DADOS"));
                        promptControleMenu.setInformapromo_mensageminicial(resultSet.getString("INFORMAPROMO_MENSAGEMINICIAL"));
                        promptControleMenu.setInformapromo_regratorpedo(resultSet.getString("INFORMAPROMO_REGRATORPEDO"));
                        promptControleMenu.setInformapromo_regraligacao(resultSet.getString("INFORMAPROMO_REGRALIGACAO"));
                        promptControleMenu.setInformapromo_comousar(resultSet.getString("INFORMAPROMO_COMOUSAR"));
                        promptControleMenu.setInformapromo_validadepromo(resultSet.getString("INFORMAPROMO_VALIDADEPROMO"));
                        promptControleMenu.setInformapromo_dados(resultSet.getString("INFORMAPROMO_DADOS"));
                        promptControleMenu.setOutrosplanos_informapromooicontrole(resultSet.getString("OUTROSPLANOS_INFORMAPROMOOICONTROLE"));

                        list.add(promptControleMenu);
                        break;

                    case BO_CONTROLE:
                        fromJson = JsonUtils.fromJson(json, new BoControle());
                        BoControle bocontrole = (BoControle) fromJson;
                        bocontrole.setId(id);
                        bocontrole.setTempId(tempid);
                        bocontrole.setModified(inserted);
                        bocontrole.setHistory(history);
                        bocontrole.setDt_gmud(dtGmud);
                        bocontrole.setDeleted(deleted);

                        list.add(bocontrole);
                        break;

                    case IO_CARTAO_CONTROLE:
                        fromJson = JsonUtils.fromJson(json, new IoCartaoControle());
                        IoCartaoControle ioCartaoControle = (IoCartaoControle) fromJson;
                        ioCartaoControle.setId(id);
                        ioCartaoControle.setTempId(tempid);
                        ioCartaoControle.setModified(inserted);
                        ioCartaoControle.setHistory(history);
                        ioCartaoControle.setDt_gmud(dtGmud);
                        ioCartaoControle.setDeleted(deleted);

                        list.add(ioCartaoControle);
                        break;

                    case IO_CONTROLE_CONTROLE:
                        fromJson = JsonUtils.fromJson(json, new IoControleControle());
                        IoControleControle ioControleControle = (IoControleControle) fromJson;
                        ioControleControle.setId(id);
                        ioControleControle.setTempId(tempid);
                        ioControleControle.setModified(inserted);
                        ioControleControle.setHistory(history);
                        ioControleControle.setDt_gmud(dtGmud);
                        ioControleControle.setDeleted(deleted);

                        list.add(ioControleControle);
                        break;

                    case IO_CONTROLE_CONTROLE_S2S:
                        fromJson = JsonUtils.fromJson(json, new IoControleControleS2S());
                        IoControleControleS2S ioControleControleS2s = (IoControleControleS2S) fromJson;
                        ioControleControleS2s.setId(id);
                        ioControleControleS2s.setTempId(tempid);
                        ioControleControleS2s.setModified(inserted);
                        ioControleControleS2s.setHistory(history);
                        ioControleControleS2s.setDt_gmud(dtGmud);
                        ioControleControleS2s.setDeleted(deleted);

                        list.add(ioControleControleS2s);
                        break;

                    case TB_OICMENU:
                        fromJson = JsonUtils.fromJson(json, new OiCMenu());
                        OiCMenu tbOiCMenu = (OiCMenu) fromJson;
                        tbOiCMenu.setId(id);
                        tbOiCMenu.setTempId(tempid);
                        tbOiCMenu.setModified(inserted);
                        tbOiCMenu.setHistory(history);
                        tbOiCMenu.setDt_gmud(dtGmud);
                        tbOiCMenu.setDeleted(deleted);

                        list.add(tbOiCMenu);
                        break;

                    case TB_OICPROMOCOES:
                        fromJson = JsonUtils.fromJson(json, new OiCPromocoes());
                        OiCPromocoes tbOiCPromocoes = (OiCPromocoes) fromJson;
                        tbOiCPromocoes.setId(id);
                        tbOiCPromocoes.setTempId(tempid);
                        tbOiCPromocoes.setModified(inserted);
                        tbOiCPromocoes.setHistory(history);
                        tbOiCPromocoes.setDt_gmud(dtGmud);
                        tbOiCPromocoes.setDeleted(deleted);

                        list.add(tbOiCPromocoes);
                        break;

                    case TB_TEMPLATE:
                        fromJson = JsonUtils.fromJson(json, new TbTemplate());
                        TbTemplate tbTemplate = (TbTemplate) fromJson;
                        tbTemplate.setId(id);
                        tbTemplate.setTempId(tempid);
                        tbTemplate.setModified(inserted);
                        tbTemplate.setHistory(history);
                        tbTemplate.setDt_gmud(dtGmud);
                        tbTemplate.setDeleted(deleted);

                        list.add(tbTemplate);
                        break;

                    case TB_TEMPLATE_CONTROLE:
                        fromJson = JsonUtils.fromJson(json, new TbTemplateControle());
                        TbTemplateControle tbTemplateControle = (TbTemplateControle) fromJson;
                        tbTemplateControle.setId(id);
                        tbTemplateControle.setTempId(tempid);
                        tbTemplateControle.setModified(inserted);
                        tbTemplateControle.setHistory(history);
                        tbTemplateControle.setDt_gmud(dtGmud);
                        tbTemplateControle.setDeleted(deleted);

                        list.add(tbTemplateControle);
                        break;

                    case VO_CONTROLE:
                        fromJson = JsonUtils.fromJson(json, new VoControle());
                        VoControle voControle = (VoControle) fromJson;
                        voControle.setId(id);
                        voControle.setTempId(tempid);
                        voControle.setModified(inserted);
                        voControle.setHistory(history);
                        voControle.setDt_gmud(dtGmud);
                        voControle.setDeleted(deleted);

                        list.add(voControle);
                        break;

                    case PROMPTS_OFERTASPRE:
                        fromJson = JsonUtils.fromJson(json, new PromptsOfertasPre());
                        PromptsOfertasPre promptsOfertasPre = (PromptsOfertasPre) fromJson;
                        promptsOfertasPre.setId(id);
                        promptsOfertasPre.setTempId(tempid);
                        promptsOfertasPre.setModified(inserted);
                        promptsOfertasPre.setHistory(history);
                        promptsOfertasPre.setDt_gmud(dtGmud);
                        promptsOfertasPre.setDeleted(deleted);

                        list.add(promptsOfertasPre);
                        break;

                    case FRASES_CONTROLE:
                        FrasesControle frasesControle = new FrasesControle();
                        frasesControle.setId(id);
                        frasesControle.setTempId(tempid);
                        frasesControle.setModified(inserted);
                        frasesControle.setHistory(history);
                        frasesControle.setDt_gmud(dtGmud);
                        frasesControle.setDeleted(deleted);

                        frasesControle.setIdbeneficio(resultSet.getString("IDBENEFICIO"));
                        frasesControle.setIdprograma(resultSet.getString("IDPROGRAMA"));
                        frasesControle.setDdd(resultSet.getString("DDD"));
                        frasesControle.setJacliente_promopadrao(resultSet.getString("JACLIENTE_PROMOPADRAO"));
                        frasesControle.setJacliente_regras(resultSet.getString("JACLIENTE_REGRAS"));
                        frasesControle.setJacliente_validade(resultSet.getString("JACLIENTE_VALIDADE"));
                        frasesControle.setJacliente_saldo(resultSet.getString("JACLIENTE_SALDO"));
                        frasesControle.setJacliente_conting(resultSet.getString("JACLIENTE_CONTING"));
                        frasesControle.setNaocliente_informapromo(resultSet.getString("NAOCLIENTE_INFORMAPROMO"));
                        frasesControle.setNaocliente_regras(resultSet.getString("NAOCLIENTE_REGRAS"));
                        frasesControle.setNaocliente_validade(resultSet.getString("NAOCLIENTE_VALIDADE"));
                        frasesControle.setConectado_incentivo(resultSet.getString("CONECTADO_INCENTIVO"));
                        frasesControle.setConectado_incentivopromocao(resultSet.getString("CONECTADO_INCENTIVOPROMOCAO"));
                        frasesControle.setJacliente_sva(resultSet.getString("JACLIENTE_SVA"));
                        frasesControle.setIncentivo_comboleto(resultSet.getString("INCENTIVO_COMBOLETO"));
                        frasesControle.setDetalhes_ofertaretencao(resultSet.getString("DETALHES_OFERTARETENCAO"));
                        frasesControle.setInternetemdobro_jacliente(resultSet.getString("INTERNETEMDOBRO_JACLIENTE"));
                        frasesControle.setInternetemdobro_naocliente(resultSet.getString("INTERNETEMDOBRO_NAOCLIENTE"));
                        frasesControle.setBeneficiointernet_anteriordtvendaura(resultSet.getString("BENEFICIOINTERNET_ANTERIORDTVENDAURA"));
                        frasesControle.setBeneficiointernet_aposdtvendaura(resultSet.getString("BENEFICIOINTERNET_APOSDTVENDAURA"));
                        frasesControle.setFallback(resultSet.getString("FALLBACK"));
                        frasesControle.setJacliente_promopadrao_144(resultSet.getString("JACLIENTE_PROMOPADRAO_144"));
                        frasesControle.setJacliente_regras_144(resultSet.getString("JACLIENTE_REGRAS_144"));
                        frasesControle.setJacliente_validade_144(resultSet.getString("JACLIENTE_VALIDADE_144"));
                        frasesControle.setJacliente_saldo_144(resultSet.getString("JACLIENTE_SALDO_144"));
                        frasesControle.setJacliente_conting_144(resultSet.getString("JACLIENTE_CONTING_144"));
                        frasesControle.setNaocliente_informapromo_144(resultSet.getString("NAOCLIENTE_INFORMAPROMO_144"));
                        frasesControle.setNaocliente_regras_144(resultSet.getString("NAOCLIENTE_REGRAS_144"));
                        frasesControle.setNaocliente_validade_144(resultSet.getString("NAOCLIENTE_VALIDADE_144"));
                        frasesControle.setConectado_incentivo_144(resultSet.getString("CONECTADO_INCENTIVO_144"));
                        frasesControle.setConectado_incentivopromocao_144(resultSet.getString("CONECTADO_INCENTIVOPROMOCAO_144"));
                        frasesControle.setJacliente_sva_144(resultSet.getString("JACLIENTE_SVA_144"));
                        frasesControle.setIncentivo_comboleto_144(resultSet.getString("INCENTIVO_COMBOLETO_144"));
                        frasesControle.setDetalhes_ofertaretencao_144(resultSet.getString("DETALHES_OFERTARETENCAO_144"));
                        frasesControle.setInternetemdobro_jacliente_144(resultSet.getString("INTERNETEMDOBRO_JACLIENTE_144"));
                        frasesControle.setInternetemdobro_naocliente_144(resultSet.getString("INTERNETEMDOBRO_NAOCLIENTE_144"));
                        frasesControle.setBeneficiointernet_anteriordtvendaura_144(resultSet.getString("BENEFICIOINTERNET_ANTERIORDTVENDAURA_144"));
                        frasesControle.setBeneficiointernet_aposdtvendaura_144(resultSet.getString("BENEFICIOINTERNET_APOSDTVENDAURA_144"));
                        frasesControle.setFallback_144(resultSet.getString("FALLBACK_144"));

                        list.add(frasesControle);
                        break;

                    case FRASEOLOGIAS_OFERTASPRE:
                        FraseologiasOfertasPre fraseologiasOfertasPre = new FraseologiasOfertasPre();
                        fraseologiasOfertasPre.setId(id);
                        fraseologiasOfertasPre.setTempId(tempid);
                        fraseologiasOfertasPre.setModified(inserted);
                        fraseologiasOfertasPre.setHistory(history);
                        fraseologiasOfertasPre.setDt_gmud(dtGmud);
                        fraseologiasOfertasPre.setDeleted(deleted);

                        fraseologiasOfertasPre.setId_ofertaocs(resultSet.getString("ID_OFERTAOCS"));
                        fraseologiasOfertasPre.setDdd(resultSet.getString("DDD"));
                        fraseologiasOfertasPre.setJacliente_oferta_ussd(resultSet.getString("JACLIENTE_OFERTA_USSD"));
                        fraseologiasOfertasPre.setJacliente_sva_ussd(resultSet.getString("JACLIENTE_SVA_USSD"));
                        fraseologiasOfertasPre.setDetalhesoferta_ussd(resultSet.getString("DETALHESOFERTA_USSD"));
                        fraseologiasOfertasPre.setRegrafallback_ussd(resultSet.getString("REGRAFALLBACK_USSD"));
                        fraseologiasOfertasPre.setRegrarollover_ussd(resultSet.getString("REGRAROLLOVER_USSD"));
                        fraseologiasOfertasPre.setNaocliente_informaoferta_ussd(resultSet.getString("NAOCLIENTE_INFORMAOFERTA_USSD"));
                        fraseologiasOfertasPre.setNaocliente_incentivooferta_ussd(resultSet.getString("NAOCLIENTE_INCENTIVOOFERTA_USSD"));
                        fraseologiasOfertasPre.setOfertamigrados_ussd(resultSet.getString("OFERTAMIGRADOS_USSD"));
                        fraseologiasOfertasPre.setId_beneficio(resultSet.getString("ID_BENEFICIO"));
                        fraseologiasOfertasPre.setId_programa(resultSet.getString("ID_PROGRAMA"));
                        fraseologiasOfertasPre.setJacliente_oferta_144(resultSet.getString("JACLIENTE_OFERTA_144"));
                        fraseologiasOfertasPre.setJacliente_sva_144(resultSet.getString("JACLIENTE_SVA_144"));
                        fraseologiasOfertasPre.setDetalhesoferta_144(resultSet.getString("DETALHESOFERTA_144"));
                        fraseologiasOfertasPre.setRegrafallback_144(resultSet.getString("REGRAFALLBACK_144"));
                        fraseologiasOfertasPre.setRegrarollover_144(resultSet.getString("REGRAROLLOVER_144"));
                        fraseologiasOfertasPre.setNaocliente_informaoferta_144(resultSet.getString("NAOCLIENTE_INFORMAOFERTA_144"));
                        fraseologiasOfertasPre.setNaocliente_incentivooferta_144(resultSet.getString("NAOCLIENTE_INCENTIVOOFERTA_144"));
                        fraseologiasOfertasPre.setOfertamigrados_144(resultSet.getString("OFERTAMIGRADOS_144"));

                        list.add(fraseologiasOfertasPre);
                        break;

                    case INCENTIVO_OFERTASPRE:
                        fromJson = JsonUtils.fromJson(json, new IncentivoOfertasPre());
                        IncentivoOfertasPre incentivoOfertasPre = (IncentivoOfertasPre) fromJson;
                        incentivoOfertasPre.setId(id);
                        incentivoOfertasPre.setTempId(tempid);
                        incentivoOfertasPre.setModified(inserted);
                        incentivoOfertasPre.setHistory(history);
                        incentivoOfertasPre.setDt_gmud(dtGmud);
                        incentivoOfertasPre.setDeleted(deleted);

                        list.add(incentivoOfertasPre);
                        break;

                    case INCENTIVOS_PORTABILIDADE:
                        fromJson = JsonUtils.fromJson(json, new IncentivosPortabilidade());
                        IncentivosPortabilidade incentivosPortabilidade = (IncentivosPortabilidade) fromJson;
                        incentivosPortabilidade.setId(id);
                        incentivosPortabilidade.setTempId(tempid);
                        incentivosPortabilidade.setModified(inserted);
                        incentivosPortabilidade.setHistory(history);
                        incentivosPortabilidade.setDt_gmud(dtGmud);
                        incentivosPortabilidade.setDeleted(deleted);

                        list.add(incentivosPortabilidade);
                        break;
                    case INCENTIVOS_PORTABILIDADE_OCS:
                        fromJson = JsonUtils.fromJson(json, new IncentivosPortabilidadeOcs());
                        IncentivosPortabilidadeOcs incentivosPortabilidadeOcs = (IncentivosPortabilidadeOcs) fromJson;
                        incentivosPortabilidadeOcs.setId(id);
                        incentivosPortabilidadeOcs.setTempId(tempid);
                        incentivosPortabilidadeOcs.setModified(inserted);
                        incentivosPortabilidadeOcs.setHistory(history);
                        incentivosPortabilidadeOcs.setDt_gmud(dtGmud);
                        incentivosPortabilidadeOcs.setDeleted(deleted);

                        list.add(incentivosPortabilidadeOcs);
                        break;
                    case OFERTAS_PORTABILIDADE:
                        fromJson = JsonUtils.fromJson(json, new OfertasPortabilidade());
                        OfertasPortabilidade ofertasPortabilidade = (OfertasPortabilidade) fromJson;
                        ofertasPortabilidade.setId(id);
                        ofertasPortabilidade.setTempId(tempid);
                        ofertasPortabilidade.setModified(inserted);
                        ofertasPortabilidade.setHistory(history);
                        ofertasPortabilidade.setDt_gmud(dtGmud);
                        ofertasPortabilidade.setDeleted(deleted);

                        list.add(ofertasPortabilidade);
                        break;
                    case OFERTAS_PORTABILIDADE_OCS:
                        fromJson = JsonUtils.fromJson(json, new OfertasPortabilidadeOcs());
                        OfertasPortabilidadeOcs ofertasPortabilidadeOcs = (OfertasPortabilidadeOcs) fromJson;
                        ofertasPortabilidadeOcs.setId(id);
                        ofertasPortabilidadeOcs.setTempId(tempid);
                        ofertasPortabilidadeOcs.setModified(inserted);
                        ofertasPortabilidadeOcs.setHistory(history);
                        ofertasPortabilidadeOcs.setDt_gmud(dtGmud);
                        ofertasPortabilidadeOcs.setDeleted(deleted);

                        list.add(ofertasPortabilidadeOcs);
                        break;
                    case FRASES_PORTABILIDADE:
                        fromJson = JsonUtils.fromJson(json, new FrasesPortabilidade());
                        FrasesPortabilidade frasesPortabilidade = (FrasesPortabilidade) fromJson;
                        frasesPortabilidade.setId(id);
                        frasesPortabilidade.setTempId(tempid);
                        frasesPortabilidade.setModified(inserted);
                        frasesPortabilidade.setHistory(history);
                        frasesPortabilidade.setDt_gmud(dtGmud);
                        frasesPortabilidade.setDeleted(deleted);

                        list.add(frasesPortabilidade);
                        break;

                    case FRASES_PORTABILIDADE_OCS:
                        fromJson = JsonUtils.fromJson(json, new FrasesPortabilidadeOcs());
                        FrasesPortabilidadeOcs frasesPortabilidadeOcs = (FrasesPortabilidadeOcs) fromJson;
                        frasesPortabilidadeOcs.setId(id);
                        frasesPortabilidadeOcs.setTempId(tempid);
                        frasesPortabilidadeOcs.setModified(inserted);
                        frasesPortabilidadeOcs.setHistory(history);
                        frasesPortabilidadeOcs.setDt_gmud(dtGmud);
                        frasesPortabilidadeOcs.setDeleted(deleted);

                        list.add(frasesPortabilidadeOcs);
                        break;

                    case INCENTIVO_OFERTASPRE_S2S:
                        fromJson = JsonUtils.fromJson(json, new IncentivoOfertasPreS2S());
                        IncentivoOfertasPreS2S incentivoOfertasPreS2s = (IncentivoOfertasPreS2S) fromJson;
                        incentivoOfertasPreS2s.setId(id);
                        incentivoOfertasPreS2s.setTempId(tempid);
                        incentivoOfertasPreS2s.setModified(inserted);
                        incentivoOfertasPreS2s.setHistory(history);
                        incentivoOfertasPreS2s.setDt_gmud(dtGmud);
                        incentivoOfertasPreS2s.setDeleted(deleted);

                        list.add(incentivoOfertasPreS2s);
                        break;

                    case VO_CARTAO:
                        fromJson = JsonUtils.fromJson(json, new VoCartao());
                        VoCartao voCartao = (VoCartao) fromJson;
                        voCartao.setId(id);
                        voCartao.setTempId(tempid);
                        voCartao.setModified(inserted);
                        voCartao.setHistory(history);
                        voCartao.setDt_gmud(dtGmud);
                        voCartao.setDeleted(deleted);

                        list.add(voCartao);
                        break;

                    case BO_CARTAO:
                        fromJson = JsonUtils.fromJson(json, new BoCartao());
                        BoCartao boCartao = (BoCartao) fromJson;
                        boCartao.setId(id);
                        boCartao.setTempId(tempid);
                        boCartao.setModified(inserted);
                        boCartao.setHistory(history);
                        boCartao.setDt_gmud(dtGmud);
                        boCartao.setDeleted(deleted);

                        list.add(boCartao);
                        break;

                    case FRASES_CARTAO:
                        FrasesCartao frasesCartao = new FrasesCartao();
                        frasesCartao.setId(id);
                        frasesCartao.setTempId(tempid);
                        frasesCartao.setModified(inserted);
                        frasesCartao.setHistory(history);
                        frasesCartao.setDt_gmud(dtGmud);
                        frasesCartao.setDeleted(deleted);

                        frasesCartao.setIdbeneficio(resultSet.getString("IDBENEFICIO"));
                        frasesCartao.setIdprograma(resultSet.getString("IDPROGRAMA"));
                        frasesCartao.setDdd(resultSet.getString("DDD"));
                        frasesCartao.setPromopadrao_ussd(resultSet.getString("PROMOPADRAO_USSD"));
                        frasesCartao.setSemrecarga_ussd(resultSet.getString("SEMRECARGA_USSD"));
                        frasesCartao.setChamada_ussd(resultSet.getString("CHAMADA_USSD"));
                        frasesCartao.setDetalhes_ussd(resultSet.getString("DETALHES_USSD"));
                        frasesCartao.setRecarga_ussd(resultSet.getString("RECARGA_USSD"));
                        frasesCartao.setInformavalidade_ussd(resultSet.getString("INFORMAVALIDADE_USSD"));
                        frasesCartao.setValidade_ussd(resultSet.getString("VALIDADE_USSD"));
                        frasesCartao.setContingencia_ussd(resultSet.getString("CONTINGENCIA_USSD"));
                        frasesCartao.setNaocliente_informapromo_ussd(resultSet.getString("NAOCLIENTE_INFORMAPROMO_USSD"));
                        frasesCartao.setNaocliente_validade_ussd(resultSet.getString("NAOCLIENTE_VALIDADE_USSD"));
                        frasesCartao.setRegras_ussd(resultSet.getString("REGRAS_USSD"));
                        frasesCartao.setNaocliente_recarga_ussd(resultSet.getString("NAOCLIENTE_RECARGA_USSD"));
                        frasesCartao.setIncentivo_oferta_pre_ussd(resultSet.getString("INCENTIVO_OFERTA_PRE_USSD"));
                        frasesCartao.setFallback_ussd(resultSet.getString("FALLBACK_USSD"));
                        frasesCartao.setSva_ussd(resultSet.getString("SVA_USSD"));
                        frasesCartao.setInformasaldopromo_ussd(resultSet.getString("INFORMASALDOPROMO_USSD"));
                        frasesCartao.setOfertamigrados_ussd(resultSet.getString("OFERTAMIGRADOS_USSD"));
                        frasesCartao.setInformapromo_ussd_ussd(resultSet.getString("INFORMAPROMO_USSD_USSD"));
                        frasesCartao.setPromopadrao_144(resultSet.getString("PROMOPADRAO_144"));
                        frasesCartao.setSemrecarga_144(resultSet.getString("SEMRECARGA_144"));
                        frasesCartao.setChamada_144(resultSet.getString("CHAMADA_144"));
                        frasesCartao.setDetalhes_144(resultSet.getString("DETALHES_144"));
                        frasesCartao.setRecarga_144(resultSet.getString("RECARGA_144"));
                        frasesCartao.setInformavalidade_144(resultSet.getString("INFORMAVALIDADE_144"));
                        frasesCartao.setValidade_144(resultSet.getString("VALIDADE_144"));
                        frasesCartao.setContingencia_144(resultSet.getString("CONTINGENCIA_144"));
                        frasesCartao.setNaocliente_informapromo_144(resultSet.getString("NAOCLIENTE_INFORMAPROMO_144"));
                        frasesCartao.setNaocliente_validade_144(resultSet.getString("NAOCLIENTE_VALIDADE_144"));
                        frasesCartao.setRegras_144(resultSet.getString("REGRAS_144"));
                        frasesCartao.setNaocliente_recarga_144(resultSet.getString("NAOCLIENTE_RECARGA_144"));
                        frasesCartao.setIncentivo_oferta_pre_144(resultSet.getString("INCENTIVO_OFERTA_PRE_144"));
                        frasesCartao.setFallback_144(resultSet.getString("FALLBACK_144"));
                        frasesCartao.setSva_144(resultSet.getString("SVA_144"));
                        frasesCartao.setInformasaldopromo_144(resultSet.getString("INFORMASALDOPROMO_144"));
                        frasesCartao.setOfertamigrados_144(resultSet.getString("OFERTAMIGRADOS_144"));
                        frasesCartao.setInformapromo_ussd_144(resultSet.getString("INFORMAPROMO_USSD_144"));

                        list.add(frasesCartao);
                        break;

                    case IO_CARTAO_CARTAO:
                        fromJson = JsonUtils.fromJson(json, new IoCartaoCartao());
                        IoCartaoCartao ioCartaoCartao = (IoCartaoCartao) fromJson;
                        ioCartaoCartao.setId(id);
                        ioCartaoCartao.setTempId(tempid);
                        ioCartaoCartao.setModified(inserted);
                        ioCartaoCartao.setHistory(history);
                        ioCartaoCartao.setDt_gmud(dtGmud);
                        ioCartaoCartao.setDeleted(deleted);

                        list.add(ioCartaoCartao);
                        break;

                    case IO_CARTAO_CARTAO_S2S:
                        fromJson = JsonUtils.fromJson(json, new IoCartaoCartaoS2S());
                        IoCartaoCartaoS2S ioCartaoCartaoS2s = (IoCartaoCartaoS2S) fromJson;
                        ioCartaoCartaoS2s.setId(id);
                        ioCartaoCartaoS2s.setTempId(tempid);
                        ioCartaoCartaoS2s.setModified(inserted);
                        ioCartaoCartaoS2s.setHistory(history);
                        ioCartaoCartaoS2s.setDt_gmud(dtGmud);
                        ioCartaoCartaoS2s.setDeleted(deleted);

                        list.add(ioCartaoCartaoS2s);
                        break;

                    case TB_CARTPROMOCOES:
                        fromJson = JsonUtils.fromJson(json, new PromocoesCartao());
                        PromocoesCartao promocoesCartao = (PromocoesCartao) fromJson;
                        promocoesCartao.setId(id);
                        promocoesCartao.setTempId(tempid);
                        promocoesCartao.setModified(inserted);
                        promocoesCartao.setHistory(history);
                        promocoesCartao.setDt_gmud(dtGmud);
                        promocoesCartao.setDeleted(deleted);

                        list.add(promocoesCartao);
                        break;

                    case TB_CARTAOMENU:
                        fromJson = JsonUtils.fromJson(json, new CartaoMenu());
                        CartaoMenu cartaoMenu = (CartaoMenu) fromJson;
                        cartaoMenu.setId(id);
                        cartaoMenu.setTempId(tempid);
                        cartaoMenu.setModified(inserted);
                        cartaoMenu.setHistory(history);
                        cartaoMenu.setDt_gmud(dtGmud);
                        cartaoMenu.setDeleted(deleted);

                        list.add(cartaoMenu);
                        break;

                    case PROMPT_CARTAOMENU:
                        PromptCartaoMenu promptCartaoMenu = new PromptCartaoMenu();
                        promptCartaoMenu.setId(id);
                        promptCartaoMenu.setTempId(tempid);
                        promptCartaoMenu.setModified(inserted);
                        promptCartaoMenu.setHistory(history);
                        promptCartaoMenu.setDt_gmud(dtGmud);
                        promptCartaoMenu.setDeleted(deleted);

                        promptCartaoMenu.setTb_id_beneficio(resultSet.getString("TB_ID_BENEFICIO"));
                        promptCartaoMenu.setTb_id_programa(resultSet.getString("TB_ID_PROGRAMA"));
                        promptCartaoMenu.setTb_ddd(resultSet.getString("TB_DDD"));
                        promptCartaoMenu.setCliente_mensageminicial_ussd(resultSet.getString("CLIENTE_MENSAGEMINICIAL_USSD"));
                        promptCartaoMenu.setCliente_regratorpedo_ussd(resultSet.getString("CLIENTE_REGRATORPEDO_USSD"));
                        promptCartaoMenu.setCliente_regraligacao_ussd(resultSet.getString("CLIENTE_REGRALIGACAO_USSD"));
                        promptCartaoMenu.setCliente_regradados_ussd(resultSet.getString("CLIENTE_REGRADADOS_USSD"));
                        promptCartaoMenu.setCliente_sva_ussd(resultSet.getString("CLIENTE_SVA_USSD"));
                        promptCartaoMenu.setCliente_comousar_ussd(resultSet.getString("CLIENTE_COMOUSAR_USSD"));
                        promptCartaoMenu.setRegraligacaooimovel_ussd(resultSet.getString("REGRALIGACAOOIMOVEL_USSD"));
                        promptCartaoMenu.setRegraligacaooimovelfixo_ussd(resultSet.getString("REGRALIGACAOOIMOVELFIXO_USSD"));
                        promptCartaoMenu.setRegraligacaomix_ussd(resultSet.getString("REGRALIGACAOMIX_USSD"));
                        promptCartaoMenu.setRecebebonusiniciopromodegrau_ussd(resultSet.getString("RECEBEBONUSINICIOPROMODEGRAU_USSD"));
                        promptCartaoMenu.setRecebebonusmax_ussd(resultSet.getString("RECEBEBONUSMAX_USSD"));
                        promptCartaoMenu.setRecebebonusmindegrau2_ussd(resultSet.getString("RECEBEBONUSMINDEGRAU2_USSD"));
                        promptCartaoMenu.setRecebebonusmindegrau3_ussd(resultSet.getString("RECEBEBONUSMINDEGRAU3_USSD"));
                        promptCartaoMenu.setRecebebonusmindegrau2e3_ussd(resultSet.getString("RECEBEBONUSMINDEGRAU2E3_USSD"));
                        promptCartaoMenu.setRecebebonusmax3_ussd(resultSet.getString("RECEBEBONUSMAX3_USSD"));
                        promptCartaoMenu.setNaocliente_mensageminicial_ussd(resultSet.getString("NAOCLIENTE_MENSAGEMINICIAL_USSD"));
                        promptCartaoMenu.setNaocliente_regratorpedo_ussd(resultSet.getString("NAOCLIENTE_REGRATORPEDO_USSD"));
                        promptCartaoMenu.setNaocliente_regraligacao_ussd(resultSet.getString("NAOCLIENTE_REGRALIGACAO_USSD"));
                        promptCartaoMenu.setNaocliente_comousar_ussd(resultSet.getString("NAOCLIENTE_COMOUSAR_USSD"));
                        promptCartaoMenu.setNaocliente_validadepromo_ussd(resultSet.getString("NAOCLIENTE_VALIDADEPROMO_USSD"));
                        promptCartaoMenu.setNaocliente_regradados_ussd(resultSet.getString("NAOCLIENTE_REGRADADOS_USSD"));
                        promptCartaoMenu.setPromooicartao_ussd(resultSet.getString("PROMOOICARTAO_USSD"));
                        promptCartaoMenu.setOferta_pre_ussd(resultSet.getString("OFERTA_PRE_USSD"));
                        promptCartaoMenu.setCliente_mensageminicial_144(resultSet.getString("CLIENTE_MENSAGEMINICIAL_144"));
                        promptCartaoMenu.setCliente_regratorpedo_144(resultSet.getString("CLIENTE_REGRATORPEDO_144"));
                        promptCartaoMenu.setCliente_regraligacao_144(resultSet.getString("CLIENTE_REGRALIGACAO_144"));
                        promptCartaoMenu.setCliente_regradados_144(resultSet.getString("CLIENTE_REGRADADOS_144"));
                        promptCartaoMenu.setCliente_sva_144(resultSet.getString("CLIENTE_SVA_144"));
                        promptCartaoMenu.setCliente_comousar_144(resultSet.getString("CLIENTE_COMOUSAR_144"));
                        promptCartaoMenu.setRegraligacaooimovel_144(resultSet.getString("REGRALIGACAOOIMOVEL_144"));
                        promptCartaoMenu.setRegraligacaooimovelfixo_144(resultSet.getString("REGRALIGACAOOIMOVELFIXO_144"));
                        promptCartaoMenu.setRegraligacaomix_144(resultSet.getString("REGRALIGACAOMIX_144"));
                        promptCartaoMenu.setRecebebonusiniciopromodegrau_144(resultSet.getString("RECEBEBONUSINICIOPROMODEGRAU_144"));
                        promptCartaoMenu.setRecebebonusmax_144(resultSet.getString("RECEBEBONUSMAX_144"));
                        promptCartaoMenu.setRecebebonusmindegrau2_144(resultSet.getString("RECEBEBONUSMINDEGRAU2_144"));
                        promptCartaoMenu.setRecebebonusmindegrau3_144(resultSet.getString("RECEBEBONUSMINDEGRAU3_144"));
                        promptCartaoMenu.setRecebebonusmindegrau2e3_144(resultSet.getString("RECEBEBONUSMINDEGRAU2E3_144"));
                        promptCartaoMenu.setRecebebonusmax3_144(resultSet.getString("RECEBEBONUSMAX3_144"));
                        promptCartaoMenu.setNaocliente_mensageminicial_144(resultSet.getString("NAOCLIENTE_MENSAGEMINICIAL_144"));
                        promptCartaoMenu.setNaocliente_regratorpedo_144(resultSet.getString("NAOCLIENTE_REGRATORPEDO_144"));
                        promptCartaoMenu.setNaocliente_regraligacao_144(resultSet.getString("NAOCLIENTE_REGRALIGACAO_144"));
                        promptCartaoMenu.setNaocliente_comousar_144(resultSet.getString("NAOCLIENTE_COMOUSAR_144"));
                        promptCartaoMenu.setNaocliente_validadepromo_144(resultSet.getString("NAOCLIENTE_VALIDADEPROMO_144"));
                        promptCartaoMenu.setNaocliente_regradados_144(resultSet.getString("NAOCLIENTE_REGRADADOS_144"));
                        promptCartaoMenu.setPromooicartao_144(resultSet.getString("PROMOOICARTAO_144"));
                        promptCartaoMenu.setOferta_pre_144(resultSet.getString("OFERTA_PRE_144"));

                        list.add(promptCartaoMenu);
                        break;

                    case TB_TEMPLATE_OCS:
                        fromJson = JsonUtils.fromJson(json, new TbTemplateOcs());
                        TbTemplateOcs tbTemplateOcs = (TbTemplateOcs) fromJson;
                        tbTemplateOcs.setId(id);
                        tbTemplateOcs.setTempId(tempid);
                        tbTemplateOcs.setModified(inserted);
                        tbTemplateOcs.setHistory(history);
                        tbTemplateOcs.setDt_gmud(dtGmud);
                        tbTemplateOcs.setDeleted(deleted);

                        list.add(tbTemplateOcs);
                        break;

                    case FRASE_PRE_OCS:
                        fromJson = JsonUtils.fromJson(json, new FrasePreOcs());
                        FrasePreOcs frasePreOcs = (FrasePreOcs) fromJson;
                        frasePreOcs.setId(id);
                        frasePreOcs.setTempId(tempid);
                        frasePreOcs.setModified(inserted);
                        frasePreOcs.setHistory(history);
                        frasePreOcs.setDt_gmud(dtGmud);
                        frasePreOcs.setDeleted(deleted);

                        list.add(frasePreOcs);
                        break;
                    case FRASE_PRE:
                        fromJson = JsonUtils.fromJson(json, new FrasePre());
                        FrasePre frasePre = (FrasePre) fromJson;
                        frasePre.setId(id);
                        frasePre.setTempId(tempid);
                        frasePre.setModified(inserted);
                        frasePre.setHistory(history);
                        frasePre.setDt_gmud(dtGmud);
                        frasePre.setDeleted(deleted);

                        list.add(frasePre);
                        break;

                    case FRASES_CONTROLE_CAD:
                        fromJson = JsonUtils.fromJson(json, new FrasesControleCad());
                        FrasesControleCad frasesControleCad = (FrasesControleCad) fromJson;
                        frasesControleCad.setId(id);
                        frasesControleCad.setTempId(tempid);
                        frasesControleCad.setModified(inserted);
                        frasesControleCad.setHistory(history);
                        frasesControleCad.setDt_gmud(dtGmud);
                        frasesControleCad.setDeleted(deleted);

                        list.add(frasesControleCad);
                        break;

                    case LISTA_PROMPTS:
                        fromJson = JsonUtils.fromJson(json, new Prompt());
                        Prompt promptLista = (Prompt) fromJson;
                        promptLista.setId(id);
                        promptLista.setTempId(tempid);
                        promptLista.setModified(inserted);
                        promptLista.setHistory(history);
                        promptLista.setDt_gmud(dtGmud);
                        promptLista.setDeleted(deleted);

                        list.add(promptLista);
                        break;

                }
            }

        } catch (SQLException e) {
            throw e;
        } catch (DbPoolFatalException e) {
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Exception Executing insertUser", e);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.returnConnection(connection);
        }

        return list;
    }

    public List<Aprovacao> getAprovacoes(TabelasEnum tabela) throws DbPoolFatalException, SQLException, RuntimeException, IOException, Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Aprovacao> list = new ArrayList<>();

        try {
            connection = ngrDao.getConnection();

            String query
                    = "SELECT t1.ID,ID_USER,NAME,DT_GMUD,DT_APROVACAO,STATUS \n"
                    + "FROM CFG_APROVACAO t1\n"
                    + "INNER JOIN CFG_USER t2 ON (t1.ID_USER = t2.ID)\n"
                    + "WHERE  TO_DATE(t1.DT_GMUD, 'DD-MM-YY') IN (SELECT DISTINCT  TO_DATE(t1.DT_GMUD, 'DD-MM-YY') FROM " + tabela.name() + " WHERE DT_GMUD IS NOT NULL)\n"
                    + "AND t1.TABELA = '" + tabela.name() + "'\n"
                    + "ORDER BY DT_APROVACAO DESC";

            preparedStatement = connection.prepareStatement(query);

            logger.debug("QUERY {}", query);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Aprovacao aprovacao = new Aprovacao();

                aprovacao.setId(resultSet.getInt("ID"));

                User user = new User();
                user.setId(resultSet.getInt("ID_USER"));
                user.setName(resultSet.getString("NAME"));
                aprovacao.setUser(user);

                aprovacao.setDt_gmud(resultSet.getDate("DT_GMUD"));
                aprovacao.setDt_aprovacao(resultSet.getDate("DT_APROVACAO"));
                aprovacao.setStatus(StatusEnum.valueOf(resultSet.getString("STATUS")));

                list.add(aprovacao);
            }

        } catch (SQLException | DbPoolFatalException | RuntimeException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Exception Executing insertUser", e);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.returnConnection(connection);
        }

        return list;
    }

    public void insert(Map<TabelasEnum, List<Template>> templateMap) throws DbPoolFatalException, SQLException, RuntimeException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementNls = null;

        try {
            connection = ngrDao.getConnection();
            connection.setAutoCommit(false);
            //preparedStatementNls = connection.prepareStatement("ALTER SESSION SET NLS_DATE_FORMAT='DD/MM/YY'");
            //preparedStatementNls.execute();

            for (Map.Entry<TabelasEnum, List<Template>> entry : templateMap.entrySet()) {
                TabelasEnum tabela = entry.getKey();
                List<Template> templateList = entry.getValue();

                String query = "INSERT INTO CFG_APROVACAO(ID_USER,DT_GMUD,TABELA,STATUS,DT_APROVACAO) VALUES (?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, ((Template) templateList.get(0)).getUser().getId());
                preparedStatement.setDate(2, new java.sql.Date(((Template) templateList.get(0)).getDt_gmud().getTime()));
                preparedStatement.setString(3, tabela.name());
                preparedStatement.setString(4, StatusEnum.INICIADO.name());
                preparedStatement.setDate(5, new java.sql.Date(System.currentTimeMillis()));
                preparedStatement.executeUpdate();
                ngrDao.closePreparedStatement(preparedStatement);

                for (Template template : templateList) {

                    logger.trace("INSERT: {}", JsonUtils.toJson(template));
                    logger.trace("QUERY: {}", tabela.getInsert().replace("'?'", "'" + JsonUtils.toJson(template) + "'"));
                    preparedStatement = connection.prepareStatement(tabela.getInsert());
                    Clob clob = preparedStatement.getConnection().createClob();
                    List<Object> list = new ArrayList<>();
                    list.add(template);
                    clob.setString(1, JsonUtils.toJson(list));
                    preparedStatement.setClob(1, clob);
                    preparedStatement.setString(2, JsonUtils.toJson(template));
                    preparedStatement.executeUpdate();

                }
            }

            ngrDao.closePreparedStatement(preparedStatement);

            connection.commit();

        } catch (SQLException | DbPoolFatalException | RuntimeException | IOException e) {
            connection.rollback();
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.closePreparedStatement(preparedStatementNls);
            ngrDao.returnConnection(connection);
        }
    }

    public void update(TabelasEnum tabela, List<Template> templateList, Template templateEdit) throws DbPoolFatalException, SQLException, RuntimeException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementNls = null;
        String query;

        try {
            connection = ngrDao.getConnection();
            connection.setAutoCommit(false);
            //preparedStatementNls = connection.prepareStatement("ALTER SESSION SET NLS_DATE_FORMAT='DD/MM/YY'");
            //preparedStatementNls.execute();

            Date dt_gmud = templateEdit.getDt_gmud() != null ? templateEdit.getDt_gmud() : templateList.get(0).getDt_gmud();

            query = "INSERT INTO CFG_APROVACAO(ID_USER,DT_GMUD,TABELA,STATUS,DT_APROVACAO) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, templateEdit.getUser().getId());
            preparedStatement.setDate(2, new java.sql.Date(dt_gmud.getTime()));
            preparedStatement.setString(3, tabela.name());
            preparedStatement.setString(4, StatusEnum.INICIADO.name());
            preparedStatement.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
            ngrDao.closePreparedStatement(preparedStatement);

            for (Template templateObj : templateList) {
                StringBuilder statementBuilder = new StringBuilder(1000);

                int idProdEdited = templateObj.getDt_gmud() == null ? templateObj.getId() : -1;

                if (idProdEdited > 0) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss,SSS");
                    Date inserted = new Date();

                    query = tabela.getUpdateInsert()
                            .replace("? HISTORY", "'" + JsonUtils.toJson(templateObj.getHistory()) + "' HISTORY")
                            .replace("? DT_GMUD", "'" + templateEdit.getDtGmudStr() + "' DT_GMUD")
                            .replace("ID = ?", "ID = " + idProdEdited);

                    logger.debug("QUERY1: {}", query);

                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.executeUpdate();
                    ngrDao.closePreparedStatement(preparedStatement);

                    query = tabela.getUpdate().replace(", DT_GMUD = ?", "");
                    query = query.replace("ID = ?", "TEMPID = " + idProdEdited + " AND DT_GMUD = '" + templateEdit.getDtGmudStr() + "'");

                    logger.debug("QUERY2: {}", query);
                    preparedStatement = connection.prepareStatement(query);

                    preparedStatement.executeUpdate();

                    ngrDao.closePreparedStatement(preparedStatement);
                } else {
                    statementBuilder.append("UPDATE ").append(tabela.name()).append(" SET ");
                    statementBuilder.append("HISTORY = ? ");
                    statementBuilder.append("WHERE ID = ").append(templateObj.getId()).append("\n");

                    logger.debug("QUERY1: {}", statementBuilder.toString());

                    preparedStatement = connection.prepareStatement(statementBuilder.toString());

                    Clob clob = preparedStatement.getConnection().createClob();
                    clob.setString(1, JsonUtils.toJson(templateObj.getHistory()));
                    preparedStatement.setClob(1, clob);

                    preparedStatement.executeUpdate();

                    ngrDao.closePreparedStatement(preparedStatement);

                    query = tabela.getUpdate().replace("ID = ?", "ID = " + templateObj.getId() + "");
                    if (templateEdit.getDtGmudStr() != null && !"".equals(templateEdit.getDtGmudStr())) {
                        query = query.replace(", DT_GMUD = ?", ", DT_GMUD = '" + templateEdit.getDtGmudStr() + "'");
                    } else {
                        query = query.replace(", DT_GMUD = ?", "");
                    }

                    logger.debug("QUERY2: {}", query);
                    preparedStatement = connection.prepareStatement(query);

                    preparedStatement.executeUpdate();

                    ngrDao.closePreparedStatement(preparedStatement);
                }
            }

            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } catch (DbPoolFatalException e) {
            connection.rollback();
            throw e;
        } catch (RuntimeException e) {
            connection.rollback();
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.closePreparedStatement(preparedStatementNls);
            ngrDao.returnConnection(connection);
        }
    }

    public void delete(TabelasEnum tabela, List<Template> selectedList, Template template) throws DbPoolFatalException, SQLException, RuntimeException, IOException {
        StringBuilder statementBuilder = new StringBuilder(1000);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementNls = null;
        try {
            connection = ngrDao.getConnection();
            connection.setAutoCommit(false);
            //preparedStatementNls = connection.prepareStatement("ALTER SESSION SET NLS_DATE_FORMAT='DD/MM/YY'");
            //preparedStatementNls.execute();

            String query = "INSERT INTO CFG_APROVACAO(ID_USER,DT_GMUD,TABELA,STATUS,DT_APROVACAO) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, template.getUser().getId());
            preparedStatement.setDate(2, new java.sql.Date(template.getDt_gmud().getTime()));
            preparedStatement.setString(3, tabela.name());
            preparedStatement.setString(4, StatusEnum.INICIADO.name());
            preparedStatement.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
            ngrDao.closePreparedStatement(preparedStatement);

            query = "";
            if (selectedList.get(0).getDt_gmud() == null) {
                statementBuilder.append("ID IN (");
                selectedList.forEach((selected) -> {
                    statementBuilder.append(selected.getId()).append(",");
                });
                statementBuilder.append(")");

                query = tabela.getDelete().replace("ID IN (?)", statementBuilder.toString().replace(",)", ")"));
                preparedStatement = connection.prepareStatement(query);

                StringBuilder deleteHistory = new StringBuilder(1000);
                deleteHistory
                        .append("[{\"user\":{\"id\":")
                        .append(template.getUser().getId())
                        .append(",\"login\":\"")
                        .append(template.getUser().getLogin())
                        .append("\"},\"modified\":")
                        .append(System.currentTimeMillis())
                        .append(",\"deleted\":true,\"dt_gmud\":\"")
                        .append(template.getDtGmudStr())
                        .append("\"}]");
                Clob clob = preparedStatement.getConnection().createClob();
                clob.setString(1, deleteHistory.toString());
                preparedStatement.setClob(1, clob);
                preparedStatement.setDate(2, new java.sql.Date(template.getDt_gmud().getTime()));
            } else {
                statementBuilder.append("DELETE FROM ").append(tabela.name()).append(" WHERE DT_GMUD IS NOT NULL AND ID IN (");
                selectedList.forEach((selected) -> {
                    statementBuilder.append(selected.getId()).append(",");
                });
                statementBuilder.append(")");

                preparedStatement = connection.prepareStatement(statementBuilder.toString().replace(",)", ")"));
            }

            logger.debug("QUERY: {}", query);

            preparedStatement.executeUpdate();
            ngrDao.closePreparedStatement(preparedStatement);

            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            ngrDao.rollBackTransaction(connection);
            throw e;
        } catch (DbPoolFatalException e) {
            connection.rollback();
            ngrDao.rollBackTransaction(connection);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.closePreparedStatement(preparedStatementNls);
            ngrDao.returnConnection(connection);
        }
        logger.trace("Template {} Deleted!");
    }

    public void adiar(TabelasEnum tabela, Date oldDtGmud, Date newDtGmud) throws DbPoolFatalException, SQLException, RuntimeException, IOException {
        StringBuilder statementBuilder = new StringBuilder(1000);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ngrDao.getConnection();
            connection.setAutoCommit(false);

            String query = "UPDATE CFG_APROVACAO SET DT_GMUD = ? WHERE DT_GMUD = ? AND TABELA = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, new java.sql.Date(newDtGmud.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(oldDtGmud.getTime()));
            preparedStatement.setString(3, tabela.name());
            preparedStatement.executeUpdate();
            ngrDao.closePreparedStatement(preparedStatement);

            statementBuilder.append("UPDATE ").append(tabela.name()).append(" SET DT_GMUD = ? WHERE DT_GMUD = ?");
            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            preparedStatement.setDate(1, new java.sql.Date(newDtGmud.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(oldDtGmud.getTime()));

            logger.debug("QUERY: {}", statementBuilder.toString().replace("SET DT_GMUD = ?", "SET DT_GMUD = " + newDtGmud).replace("WHERE DT_GMUD = ?", "WHERE DT_GMUD = " + oldDtGmud));

            preparedStatement.executeUpdate();
            ngrDao.closePreparedStatement(preparedStatement);

            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            ngrDao.rollBackTransaction(connection);
            throw e;
        } catch (DbPoolFatalException e) {
            connection.rollback();
            ngrDao.rollBackTransaction(connection);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.returnConnection(connection);
        }
        logger.trace("Template {} Adiada para {}", oldDtGmud, newDtGmud);
    }

    public void aprovar(TabelasEnum tabela, Date oldDtGmud, int userId, StatusEnum status) throws DbPoolFatalException, SQLException, RuntimeException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ngrDao.getConnection();
            connection.setAutoCommit(false);

            String query = "INSERT INTO CFG_APROVACAO(ID_USER,DT_GMUD,TABELA,STATUS,DT_APROVACAO) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setDate(2, new java.sql.Date(oldDtGmud.getTime()));
            preparedStatement.setString(3, tabela.name());
            preparedStatement.setString(4, status.name());
            preparedStatement.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
            ngrDao.closePreparedStatement(preparedStatement);

            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            ngrDao.rollBackTransaction(connection);
            throw e;
        } catch (DbPoolFatalException e) {
            connection.rollback();
            ngrDao.rollBackTransaction(connection);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.returnConnection(connection);
        }
        logger.trace("Template {} Aprovada!", oldDtGmud);
    }

    public void implantar(TabelasEnum tabela, Date oldDtGmud) throws DbPoolFatalException, SQLException, RuntimeException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ngrDao.getConnection();
            connection.setAutoCommit(false);

            StringBuilder statementBuilder = new StringBuilder();

            statementBuilder.append("DELETE FROM ").append(tabela.name()).append("_BKP");
            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            preparedStatement.executeUpdate();
            ngrDao.closePreparedStatement(preparedStatement);

            statementBuilder = new StringBuilder();

            statementBuilder.append("INSERT INTO ").append(tabela.name()).append("_BKP").append(" SELECT * FROM ").append(tabela.name());
            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            preparedStatement.executeUpdate();
            ngrDao.closePreparedStatement(preparedStatement);

            statementBuilder = new StringBuilder(1000);

            statementBuilder
                    .append("SELECT t2.ID,t2.TEMPID,t1.HISTORY AS HISTORYOLD ,t2.HISTORY AS HISTORYNEW,")
                    .append(tabela.getSelect().substring(0, tabela.getSelect().indexOf("as json") + 7).replace("\":\"',", "\":\"',t2.").replace("SELECT ", ""))
                    .append(" FROM ")
                    .append(tabela.name())
                    .append(" t1 INNER JOIN ")
                    .append(tabela.name())
                    .append(" t2 ON (t1.ID = t2.TEMPID AND t2.DT_GMUD = ? AND t2.DELETED = 0)");
            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            preparedStatement.setDate(1, new java.sql.Date(oldDtGmud.getTime()));

            logger.debug("QUERY0: {}", statementBuilder.toString().replace("?", oldDtGmud.toString()));

            ResultSet result = preparedStatement.executeQuery();

            Map<String, String[]> map = new HashMap<>();
            while (result.next()) {
                String[] array = new String[4];
                array[0] = result.getString("TEMPID");
                array[1] = result.getString("HISTORYOLD");
                array[2] = result.getString("HISTORYNEW");
                array[3] = result.getString("json");
                map.put(result.getString("ID"), array);
            }
            ngrDao.closePreparedStatement(preparedStatement);

            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                Map<String, Boolean> mapEdit = new HashMap<>();
                String key = entry.getKey();
                String[] value = entry.getValue();

                String mergedJson = "";
                String mergedHistory = value[1];
                logger.trace("historyold: {}", mergedHistory);

                JsonUtils.mergeJsonHistory(tabela, mapEdit, value, mergedHistory, mergedJson);

                statementBuilder = new StringBuilder(1000);
                statementBuilder.append(tabela.getUpdate()
                        .replace("history", "'" + mergedJson + "'")
                        .replace("ID = ?", "ID = " + value[0])
                        .replace(", DT_GMUD = ?", ", HISTORY = ?"));
                preparedStatement = connection.prepareStatement(statementBuilder.toString());

                logger.trace("QUERY1: {}", statementBuilder.toString());

                Clob clob = preparedStatement.getConnection().createClob();
                clob.setString(1, mergedHistory);
                preparedStatement.setClob(1, clob);

                preparedStatement.executeUpdate();
                ngrDao.closePreparedStatement(preparedStatement);
            }

            //statementBuilder.append("MERGE INTO ").append(tabela.name()).append(" t1 USING ").append(tabela.name()).append(" t2 ON (t1.ID = t2.TEMPID AND t2.DT_GMUD = ?) WHEN MATCHED THEN UPDATE SET ");
            statementBuilder = new StringBuilder(1000);
            statementBuilder.append("DELETE FROM ").append(tabela.name()).append(" t1 WHERE ID IN (SELECT TEMPID FROM ").append(tabela.name()).append(" t2 WHERE DT_GMUD = ? AND DELETED = 1 AND TEMPID IS NOT NULL)");
            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            preparedStatement.setDate(1, new java.sql.Date(oldDtGmud.getTime()));

            logger.debug("QUERY2: {}", statementBuilder.toString().replace("?", oldDtGmud.toString()));

            preparedStatement.executeUpdate();
            ngrDao.closePreparedStatement(preparedStatement);

            statementBuilder = new StringBuilder(1000);
            statementBuilder.append("UPDATE ").append(tabela.name()).append(" SET DT_GMUD = NULL WHERE DT_GMUD = ? AND TEMPID IS NULL");
            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            preparedStatement.setDate(1, new java.sql.Date(oldDtGmud.getTime()));

            logger.debug("QUERY3: {}", statementBuilder.toString().replace("?", oldDtGmud.toString()));

            preparedStatement.executeUpdate();
            ngrDao.closePreparedStatement(preparedStatement);

            statementBuilder = new StringBuilder(1000);
            statementBuilder.append("DELETE FROM ").append(tabela.name()).append(" WHERE DT_GMUD = ?");
            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            preparedStatement.setDate(1, new java.sql.Date(oldDtGmud.getTime()));

            logger.debug("QUERY4: {}", statementBuilder.toString().replace("?", oldDtGmud.toString()));

            preparedStatement.executeUpdate();
            ngrDao.closePreparedStatement(preparedStatement);

            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            ngrDao.rollBackTransaction(connection);
            throw e;
        } catch (DbPoolFatalException e) {
            connection.rollback();
            ngrDao.rollBackTransaction(connection);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.returnConnection(connection);
        }
        logger.trace("Template {} Aprovada!", oldDtGmud);
    }
}
