package com.contax.templateweb.control;

import com.oi.promocoesweb.dbms.dao.ConfigurationDAO;
import java.util.List;

public class UtilsControl {

    private UtilsControl() {
        throw new UnsupportedOperationException("Do not build me!");
    }

    public static List<String> getStuffToHide(ConfigurationDAO configurationDAO) {
        return configurationDAO.getStuffToHide();
    }
}
