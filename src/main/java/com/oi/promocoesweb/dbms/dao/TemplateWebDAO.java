package com.oi.promocoesweb.dbms.dao;

import com.oi.promocoesweb.dbms.core.TemplateWebPersistence;
import com.oi.promocoesweb.dbms.core.TemplateWebDataPersistence;
import com.oi.promocoesweb.exception.DbPoolFatalException;
import com.oi.promocoesweb.managedbean.ControleBean;
import com.contax.templateweb.util.ManagedBeanUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TemplateWebDAO extends AbstractTemplateWebDAO {

    private static final Logger logger = LogManager.getLogger();
    private final TemplateWebDataPersistence persistence;

    private static final boolean TEST_ENVIROMENT = false;

    public TemplateWebDAO(TemplateWebDataPersistence persistence) {
        this.persistence = persistence;
    }

    ////////////////////////////////////////////////////////////////////////
    //CONNECTION UTILS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Try to find an available connection to the NgrMonitor Database or Creates
     * a new one if none is available
     *
     * @return Connection to the NgrMonitor Database
     * @throws DbPoolFatalException
     */
    @Override
    protected Connection getConnection() throws DbPoolFatalException {
        Connection connection;
        try {
            if (TEST_ENVIROMENT) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                DriverManager.setLoginTimeout(1);
		connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=TemplateWeb", "usr_ngradmin", "ngr@2011");
                super.setupConnection(connection);
            } else {
                Context context = new InitialContext();
                DataSource ds = (DataSource) context.lookup(persistence.getDataSource());
                connection = ds.getConnection();
                super.setupConnection(connection);
            }
        } catch (NamingException e) {
            logger.error("Error Creating Connection", e);
            throw new DbPoolFatalException("NamingException Exception Creating Connection", e);
        } catch (SQLException e) {
            logger.error("Error Creating Connection", e);
            throw new DbPoolFatalException("SQLException Creating Connection", e);
        } catch (RuntimeException e) {
            logger.error("Error Creating Connection", e);
            throw new DbPoolFatalException("Generic Exception Creating Connection", e);
        } catch (ClassNotFoundException e) {
            logger.error("Error Creating Connection", e);
            throw new DbPoolFatalException("Enviroment Exception Creating Connection", e);
        }
        return connection;
    }

    /**
     * Tests and Returns the given connection to the pool of connections.
     *
     * @param connection The connection to be returned to the pool.
     */
    @Override
    protected void returnConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.clearWarnings();
                connection.setAutoCommit(true);
            } catch (SQLException | RuntimeException e) {
                logger.error("Error Closing Connection", e);
            }
            try {
                connection.close();
            } catch (SQLException | RuntimeException e) {
                logger.error("Error Closing Connection", e);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////
    //PERSISTENCE
    ////////////////////////////////////////////////////////////////////////
    @Override
    protected TemplateWebPersistence getNgrMonitorPersistence() {
        return persistence;
    }

    ////////////////////////////////////////////////////////////////////////
    //AUDIT
    ////////////////////////////////////////////////////////////////////////
    protected void auditUpdateAction(Object caller, Object object) {
        String user = getCurrentUser();
        String objectType;
        if (object != null) {
            objectType = object.getClass().getName();
        } else {
            objectType = "null";
        }
    }

    protected void auditDeleteAction(Object caller, Object object) {
        String user = getCurrentUser();
        String objectType;
        if (object != null) {
            objectType = object.getClass().getName();
        } else {
            objectType = "null";
        }
    }

    protected void auditInsertAction(Object caller, Object object) {
        String user = getCurrentUser();
        String objectType;
        if (object != null) {
            objectType = object.getClass().getName();
        } else {
            objectType = "null";
        }
    }

    private String getCurrentUser() {
        ControleBean loginBean = ManagedBeanUtils.findBean("controleBean");
        if (loginBean != null && loginBean.getUser() != null) {
            return loginBean.getUser().getLogin();
        } else {
            return "null";
        }
    }
}
