package com.oi.promocoesweb.dbms.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.oi.promocoesweb.dbms.core.TemplateWebDataPersistence;
import com.oi.promocoesweb.dbms.entity.User;
import com.oi.promocoesweb.dbms.entity.Perfil;
import com.oi.promocoesweb.exception.DbPoolFatalException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    /**
     * DAO Logger.
     */
    private static final Logger logger = LogManager.getLogger();
    private static final Charset defaultChartSet = StandardCharsets.UTF_8;
    private final TemplateWebDAO ngrDao;

    public UserDAO() {
        this.ngrDao = new TemplateWebDAO(TemplateWebDataPersistence.getInstance());
    }

    public List<User> consultAllUsers() throws DbPoolFatalException, SQLException, RuntimeException {
        List<User> userList = new ArrayList<>(20);
        StringBuilder statementBuilder = new StringBuilder(1000);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ngrDao.getConnection();
            statementBuilder.append("SELECT \n");
            statementBuilder.append(" ID, \n");
            statementBuilder.append(" DEPARTMENT, \n");
            statementBuilder.append(" EMAIL, \n");
            statementBuilder.append(" LOGIN, \n");
            statementBuilder.append(" NAME, \n");
            statementBuilder.append(" PASSWORD, \n");
            statementBuilder.append(" PROFILE \n");
            statementBuilder.append("FROM \n");
            statementBuilder.append(" CFG_USER \n");
            statementBuilder.append("ORDER BY \n");
            statementBuilder.append(" LOGIN \n");
            logger.trace("====consultAllUsers==== {}", statementBuilder);
            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("ID"));
                user.setLogin(resultSet.getString("LOGIN"));
                user.setName(resultSet.getString("NAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setPerfil(Perfil.getProfile(resultSet.getInt("PROFILE")));
                
                userList.add(user);
            }
        } catch (SQLException e) {
            logger.error("SQLException Executing consultAllUsers", e);
            throw e;
        } catch (DbPoolFatalException e) {
            logger.error("DBPoolFatalException Executing consultAllUsers", e);
            throw e;
        } catch (RuntimeException e) {
            logger.error("UnexpectedException Executing consultAllUsers", e);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.returnConnection(connection);
        }
        logger.debug("Users Found: {}", userList.size());
        return userList;
    }
    
    public void insertUser(User user, String initialPassword) throws DbPoolFatalException, SQLException, RuntimeException {
        StringBuilder statementBuilder = new StringBuilder(1000);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ngrDao.getConnection();
            connection.setAutoCommit(false);
            statementBuilder.append("INSERT INTO \n");
            statementBuilder.append("CFG_USER(LOGIN, NAME, EMAIL, PROFILE, PASSWORD, DEPARTMENT)");
            statementBuilder.append("VALUES (?, ?, ?, ?, ?, ?) ");
            logger.trace("====insertUser==== {}", statementBuilder);
            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getPerfil().getId());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getDepartment());
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
        logger.trace("User {} Inserted!", user.getLogin());
    }
    
    public void updateUser(User user) throws DbPoolFatalException, SQLException, RuntimeException {
        StringBuilder statementBuilder = new StringBuilder(1000);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ngrDao.getConnection();
            connection.setAutoCommit(false);
            statementBuilder.append("UPDATE \n");
            statementBuilder.append(" CFG_USER \n");
            statementBuilder.append("SET \n");
            statementBuilder.append(" LOGIN = ?, \n");
            statementBuilder.append(" NAME = ?, \n");
            statementBuilder.append(" EMAIL = ?, \n");
            statementBuilder.append(" PROFILE = ?, \n");
            statementBuilder.append(" DEPARTMENT = ? \n");
            statementBuilder.append("WHERE \n");
            statementBuilder.append(" ID = ? \n");
            
            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getPerfil().getId());
            preparedStatement.setString(5, user.getDepartment());
            preparedStatement.setInt(6, user.getId());
            int executeUpdate = preparedStatement.executeUpdate();
            logger.trace("Users {} Updated!", executeUpdate);
            connection.commit();
        } catch (SQLException e) {
            logger.error("SQLException Executing updateUser", e);
            ngrDao.rollBackTransaction(connection);
            throw e;
        } catch (DbPoolFatalException e) {
            logger.error("DBPoolFatalException Executing updateUser", e);
            ngrDao.rollBackTransaction(connection);
            throw e;
        } catch (RuntimeException e) {
            logger.error("UnexpectedException Executing updateUser", e);
            ngrDao.rollBackTransaction(connection);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.returnConnection(connection);
        }
        logger.trace("User {} Updated!", user.getLogin());
    }
    
    public void updateUserPassword(User user, String newPassword) throws DbPoolFatalException, SQLException, RuntimeException {
        StringBuilder statementBuilder = new StringBuilder(1000);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ngrDao.getConnection();
            connection.setAutoCommit(false);
            statementBuilder.append("UPDATE \n");
            statementBuilder.append(" CFG_USER \n");
            statementBuilder.append("SET \n");
            statementBuilder.append(" PASSWORD = ? \n");
            statementBuilder.append("WHERE \n");
            statementBuilder.append(" LOGIN = ? \n");
            logger.trace("====updateUser==== {}", statementBuilder);
            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            //
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, user.getLogin());
            //
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.error("SQLException Executing updateUser", e);
            ngrDao.rollBackTransaction(connection);
            throw e;
        } catch (DbPoolFatalException e) {
            logger.error("DBPoolFatalException Executing updateUser", e);
            ngrDao.rollBackTransaction(connection);
            throw e;
        } catch (RuntimeException e) {
            logger.error("UnexpectedException Executing updateUser", e);
            ngrDao.rollBackTransaction(connection);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.returnConnection(connection);
        }
        logger.trace("User {} Updated!", user.getLogin());
    }
    
    public void deleteUser(String loginToDelete) throws DbPoolFatalException, SQLException, RuntimeException {
        StringBuilder statementBuilder = new StringBuilder(1000);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ngrDao.getConnection();
            connection.setAutoCommit(false);
            statementBuilder.append("DELETE FROM CFG_USER WHERE LOGIN = ?");
            logger.trace("====updateUser==== {}", statementBuilder);
            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            preparedStatement.setString(1, loginToDelete);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.error("SQLException Executing deleteUser", e);
            ngrDao.rollBackTransaction(connection);
            throw e;
        } catch (DbPoolFatalException e) {
            logger.error("DBPoolFatalException Executing deleteUser", e);
            ngrDao.rollBackTransaction(connection);
            throw e;
        } catch (RuntimeException e) {
            logger.error("UnexpectedException Executing deleteUser", e);
            ngrDao.rollBackTransaction(connection);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.returnConnection(connection);
        }
        logger.trace("User {} Deleted!", loginToDelete);
    }
    
    public User consultUserByLogin(String userName) throws DbPoolFatalException, SQLException, RuntimeException {
        User user = new User();
        StringBuilder statementBuilder = new StringBuilder(1000);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ngrDao.getConnection();
            statementBuilder.append("SELECT \n");
            statementBuilder.append(" ID, \n");
            statementBuilder.append(" LOGIN, \n");
            statementBuilder.append(" NAME, \n");
            statementBuilder.append(" EMAIL, \n");
            statementBuilder.append(" PROFILE, \n");
            statementBuilder.append(" PASSWORD, \n");
            statementBuilder.append(" DEPARTMENT \n");
            statementBuilder.append("FROM \n");
            statementBuilder.append(" CFG_USER \n");
            statementBuilder.append("WHERE \n");
            statementBuilder.append(" LOGIN LIKE ? \n");
            logger.trace("====consultUserByLogin==== {}", statementBuilder);
            preparedStatement = connection.prepareStatement(statementBuilder.toString());
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("ID"));
                user.setLogin(resultSet.getString("LOGIN"));
                user.setName(resultSet.getString("NAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setPerfil(Perfil.getProfile(resultSet.getInt("PROFILE")));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setDepartment(resultSet.getString("DEPARTMENT"));
                
            }
        } catch (SQLException e) {
            logger.error("SQLException Executing consultUserByDomainUserName", e);
            throw e;
        } catch (DbPoolFatalException e) {
            logger.error("DBPoolFatalException Executing consultUserByDomainUserName", e);
            throw e;
        } catch (Exception e) {
            logger.error("UnexpectedException Executing consultUserByDomainUserName", e);
            throw e;
        } finally {
            ngrDao.closePreparedStatement(preparedStatement);
            ngrDao.returnConnection(connection);
        }
        logger.trace("User Found: {}", user.getLogin());
        return user;
    }
}
