package com.oi.promocoesweb.dbms.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.oi.promocoesweb.dbms.core.TemplateWebDataPersistence;
import com.oi.promocoesweb.exception.DbPoolFatalException;
import com.oi.promocoesweb.importar.entity.ui.Registro;
import com.oi.promocoesweb.importar.enumerate.AbaTemplate;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ImportarDAO {

    /**
     * DAO Logger.
     */
    private static final Logger logger = LogManager.getLogger();
    private static final Charset defaultChartSet = StandardCharsets.UTF_8;
    private final TemplateWebDAO ngrDao;

    public ImportarDAO() {
        this.ngrDao = new TemplateWebDAO(TemplateWebDataPersistence.getInstance());
    }

    public void save(List<Registro> tabela, AbaTemplate aba) throws DbPoolFatalException, SQLException, RuntimeException {
        StringBuilder statementBuilder = new StringBuilder(1000);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ngrDao.getConnection();
            connection.setAutoCommit(false);

            for (Registro registro : tabela) {
                statementBuilder.append("INSERT INTO ").append(aba.getTabela());
                statementBuilder.append("(").append(registro.getKeysChavesList().toString().replace("[", "").replace("]", "")).append(", ");
                statementBuilder.append(registro.getColunasKeysList().toString().replace("[", "").replace("]", "")).append(")\n");

                statementBuilder.append("VALUES (");
                for (String value : registro.getValuesChavesList()) {
                    statementBuilder.append("'").append(value).append("'").append(",");
                }
                for (String value : registro.getColunasValuesList()) {
                    statementBuilder.append("'").append(value).append("'").append(",");
                }
                statementBuilder.append(");\n");
            }
            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            //
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.error("SQLException Executing insertUser", e);
            ngrDao.rollBackTransaction(connection);
            throw e;
        } catch (DbPoolFatalException e) {
            logger.error("DBPoolFatalException Executing insertUser", e);
            ngrDao.rollBackTransaction(connection);
            throw e;
        } catch (RuntimeException e) {
            logger.error("UnexpectedException Executing insertUser", e);
            ngrDao.rollBackTransaction(connection);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.returnConnection(connection);
        }
        logger.trace("Template {} Inserted!", aba);
    }
}
