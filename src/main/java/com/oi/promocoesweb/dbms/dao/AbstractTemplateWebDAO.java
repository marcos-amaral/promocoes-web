/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.dbms.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.oi.promocoesweb.dbms.core.TemplateWebPersistence;
import com.oi.promocoesweb.dbms.entity.DbmsVendor;
import com.oi.promocoesweb.exception.DbPoolFatalException;

public abstract class AbstractTemplateWebDAO {

    private static final Logger logger = LogManager.getLogger();
    private DbmsVendor dbmsVendor = null;

    ////////////////////////////////////////////////////////////////////////
    // CONNECTION UTILS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Try to find an available connection to the NgrMonitor Database or Creates a new one if none is available
     *
     * @return Connection to the NgrMonitor Database
     * @throws DbPoolFatalException
     */
    protected abstract Connection getConnection() throws DbPoolFatalException;

    protected final PreparedStatement getPreparedStatement(Connection connection, String sql, int timeout) throws SQLException {
        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        prepareStatement.setQueryTimeout(timeout);
        return prepareStatement;
    }
    
    /**
     * Tests and Returns the given connection to the pool of connections.
     *
     * @param connection The connection to be returned to the pool.
     */
    protected abstract void returnConnection(Connection connection);

    /**
     * Close the given Statement, catching all errors and cleaning warnings.
     *
     * @param statement
     */
    protected final void closePreparedStatement(Statement statement) {
        logger.trace("Closing Statement");
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException | RuntimeException e) {
            logger.error("Error Closing Statement", e);
        }
    }

    /**
     * Tries to roll back the last transaction executed by the given connection, catching errors and cleaning warnings.
     *
     * @param connection
     */
    protected final void rollBackTransaction(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException | RuntimeException e) {
                logger.error("SQLException RollingBack Transaction on Connection", e);
            }
            try {
                connection.clearWarnings();
            } catch (SQLException | RuntimeException e) {
                logger.error("SQLException RollingBack Transaction on Connection", e);
            }
            try {
                connection.setAutoCommit(true);
            } catch (SQLException | RuntimeException e) {
                logger.error("SQLException RollingBack Transaction on Connection", e);
            }
        }
    }

    protected final void setupConnection(Connection connection) throws DbPoolFatalException {
        try {
            if (dbmsVendor == null) {
                DatabaseMetaData metaData = connection.getMetaData();
                String databaseProductName = metaData.getDatabaseProductName();
                if (databaseProductName != null) {
                    if (databaseProductName.contains("Postgre")) {
                        dbmsVendor = DbmsVendor.PGSQL;
                    } else if (databaseProductName.contains("Microsoft")) {
                        dbmsVendor = DbmsVendor.SQLSERVER;
                    }
                }
            }
            if (dbmsVendor == DbmsVendor.SQLSERVER && connection.getTransactionIsolation() != Connection.TRANSACTION_READ_UNCOMMITTED) {
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            }
        } catch (SQLException e) {
            logger.error("Error Creating Connection", e);
            throw new DbPoolFatalException("SQLException Setup Connection", e);
        } catch (RuntimeException e) {
            logger.error("Error Creating Connection", e);
            throw new DbPoolFatalException("Generic Exception Setup Connection", e);
        }
    }

    protected final DbmsVendor getDbmsVendor() {
        if (dbmsVendor != null) {
            return dbmsVendor;
        } else {
            return DbmsVendor.UNKNOWN;
        }
    }

    protected boolean tablesExists(String schema, String table) throws DbPoolFatalException, SQLException, RuntimeException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        boolean exists = false;
        StringBuilder statementBuilder = new StringBuilder(425);
        if (schema != null && table != null) {
            try {
                connection = getConnection();
                statementBuilder.append("SELECT \n");
                statementBuilder.append("  COUNT(*) \n");
                statementBuilder.append("FROM \n");
                statementBuilder.append("  INFORMATION_SCHEMA.TABLES \n");
                statementBuilder.append("WHERE \n");
                statementBuilder.append("  TABLE_SCHEMA = '").append(schema.toLowerCase()).append("' \n");
                statementBuilder.append("  AND TABLE_NAME = '").append(table.toLowerCase()).append("'");
                preparedStatement = connection.prepareStatement(statementBuilder.toString());
                logger.trace("====SQL==== {}", statementBuilder);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    if (resultSet.getInt(1) > 0) {
                        exists = true;
                    }
                }
            } catch (DbPoolFatalException | SQLException | RuntimeException e) {
                logger.error("Exception Executing tablesExists", e);
                throw e;
            } finally {
                closePreparedStatement(preparedStatement);
                returnConnection(connection);
            }
        }
        return exists;
    }

    ////////////////////////////////////////////////////////////////////////
    // DATE COMMON
    ////////////////////////////////////////////////////////////////////////
    protected final int getDateTimeOffSet(Date timestamp) {
        TimeZone timeZone = TimeZone.getDefault();
        int offset = timeZone.getOffset(timestamp.getTime());
        int calculatedOffSet = offset / 3600000;
        return calculatedOffSet;
    }

    protected final int getDateTimeOffSet(Timestamp timestamp) {
        TimeZone timeZone = TimeZone.getDefault();
        int offset = timeZone.getOffset(timestamp.getTime());
        int calculatedOffSet = offset / 3600000;
        return calculatedOffSet;
    }

    protected final int getDateTimeOffSet(Timestamp timestamp, long timeZoneOffset) {
        return (int) timeZoneOffset / 3600000;
    }

    protected final Timestamp getDayHourIndexStart(Timestamp timestamp) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(timestamp.getTime());
        gregorianCalendar.set(GregorianCalendar.MINUTE, 0);
        gregorianCalendar.set(GregorianCalendar.SECOND, 0);
        gregorianCalendar.set(GregorianCalendar.MILLISECOND, 0);
        Timestamp timestampReturn = new Timestamp(gregorianCalendar.getTimeInMillis());
        logger.debug("Original Timestamp: {}||New Formatted DayHourIndex: {}", timestampReturn, timestampReturn);
        return timestampReturn;
    }

    protected final Timestamp getDayHourIndexEnd(Timestamp timestamp) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(timestamp.getTime());
        gregorianCalendar.set(GregorianCalendar.MINUTE, 59);
        gregorianCalendar.set(GregorianCalendar.SECOND, 0);
        gregorianCalendar.set(GregorianCalendar.MILLISECOND, 0);
        Timestamp timestampReturn = new Timestamp(gregorianCalendar.getTimeInMillis());
        logger.debug("Original Timestamp: {}||New Formatted DayHourIndex: {}", timestampReturn, timestampReturn);
        return timestampReturn;
    }

    ////////////////////////////////////////////////////////////////////////
    // PERSISTENCE
    ////////////////////////////////////////////////////////////////////////
    protected abstract TemplateWebPersistence getNgrMonitorPersistence();

    protected String getTotalTime(long start) {
        return "[Total Execution Time:]" + String.valueOf(System.currentTimeMillis() - start);
    }
}
