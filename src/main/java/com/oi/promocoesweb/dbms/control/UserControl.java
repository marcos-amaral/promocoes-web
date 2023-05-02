package com.oi.promocoesweb.dbms.control;

import com.oi.promocoesweb.dbms.dao.UserDAO;
import com.oi.promocoesweb.dbms.entity.User;
import com.oi.promocoesweb.exception.DbPoolFatalException;
import java.sql.SQLException;
import java.util.List;

/**
 * <code>UserControl</code> Class provides access to the NgrMonitorUserDAO Class and it's database consults
 * <p>
 * @author Daniel do Valle
 * @version 1.12 20130118
 * @see com.contax.ivrmonitor.monitor.dbms.partition.dao.NgrMonitorUserDAO
 * @see com.contax.ivrmonitor.util.NgrMonitorUser
 * @since NGR-02.08.08
 */
public class UserControl {

    /**
     * This Control Dao.
     */
    private static final UserDAO dao = new UserDAO();

    public static void atualizar(User ngrMonitorUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    ////////////////////////////////////////////////////////////////////////
    //CONSTRUCTORS
    ////////////////////////////////////////////////////////////////////////
    private UserControl() {
    }

    ////////////////////////////////////////////////////////////////////////
    //USER GETTERS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Retrieves all users from the Database.
     *
     * @return List with all the Users on the database
     * <p>
     * @throws DbPoolFatalException
     * @throws SQLException
     * @throws Exception
     */
    public static List<User> consultAllUsers() throws DbPoolFatalException, SQLException, Exception {
        return dao.consultAllUsers();
    }

    ////////////////////////////////////////////////////////////////////////
    //USER INSERT AND UPDATE
    ////////////////////////////////////////////////////////////////////////
    /**
     * Inserts the given user on the NgrMonitor database with it's properties.
     *
     * @param ngrMonitorUser The data to be inserted on the database.
     * <p>
     * @param initialPaasword The user initial password
     * @throws DbPoolFatalException
     * @throws SQLException
     * @throws Exception
     */
    public static void insertUser(User ngrMonitorUser, String initialPaasword) throws DbPoolFatalException, SQLException, Exception {
        dao.insertUser(ngrMonitorUser, initialPaasword);
    }

    /**
     * Updates the given user data on the NgrMonitor database.
     *
     * @param ngrMonitorUser The data to be updated on the database.
     * <p>
     * @param newPassword The user new password
     * @throws DbPoolFatalException
     * @throws SQLException
     * @throws Exception
     */
    public static void updatePassword(User ngrMonitorUser, String newPassword) throws DbPoolFatalException, SQLException, Exception {
        dao.updateUserPassword(ngrMonitorUser, newPassword);
    }

    /**
     * Deletes the given user data from the NgrMonitor database.
     *
     * @param loginToDelete The DomainUserName to be Deleted.
     * <p>
     * @throws DbPoolFatalException
     * @throws SQLException
     * @throws Exception
     */
    public static void deleteUser(String loginToDelete) throws DbPoolFatalException, SQLException, Exception {
        dao.deleteUser(loginToDelete);
    }
  
    public static User consultUserByLogin(String userName) throws DbPoolFatalException, SQLException, Exception {
        return dao.consultUserByLogin(userName);
    }
}
