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

public class ConfigurationDAO {

    /**
     * DAO Logger.
     */
    private static final Logger logger = LogManager.getLogger();
    private static final Charset defaultChartSet = StandardCharsets.UTF_8;
    private final TemplateWebDAO ngrDao;

    public ConfigurationDAO() {
        this.ngrDao = new TemplateWebDAO(TemplateWebDataPersistence.getInstance());
    }

    public List<String> getStuffToHide() {
        return new ArrayList<>();
    }
}
