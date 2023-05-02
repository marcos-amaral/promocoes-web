package com.oi.promocoesweb.filter;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named
@ApplicationScoped
public class GenericFilter implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    public boolean filter(Object value, Object filter, Locale locale) {

        String filterText = (filter == null) ? null : filter.toString().trim();
        
        System.out.println("filterText:"+filterText+"|value:"+value);
        
        if (filterText == null || filterText.isEmpty()) {
            return true;
        }
        
        if(filterText.equals("null")){
            if(value==null || "".equals(value))
                return true;
            else
                return false;
        }
        else if (value == null) {
            return false;
        }

        String filterValue = (String) value;
        
        return filterValue.contains(filterText);
    }
}
