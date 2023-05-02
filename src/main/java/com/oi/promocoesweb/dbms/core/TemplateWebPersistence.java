/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.dbms.core;

import com.oi.promocoesweb.exception.DbPoolFatalException;
import java.sql.SQLException;

public interface TemplateWebPersistence {

    ////////////////////////////////////////////////////////////////////////
    //INIT
    ////////////////////////////////////////////////////////////////////////
    /**
     * Refreshes the Current NgrMonitor Database NgrMonitorDataPersistence.
     *
     * @throws DbPoolFatalException On Any fail accessing the JDBC Driver, accessing the database, login in to the database, creating connections, retrieving connections from the
     * pool or any generic exception from the Connection Pool.
     * @throws SQLException On Any SQL Exception, including syntax error, variable scopes, missing variables, invalid SQL state, permission problems and connection errors.
     * @throws Exception On Any untreated or unexpected error or exception. At this point this exception is just a formality and will be removed on the future.
     */
    void refreshPersistence() throws SQLException, DbPoolFatalException, Exception;

    /**
     * Shutdown the Persisted NgrMonitor Data and release the used Objects for Garbage Collecting.
     */
    void shutdown();

}
