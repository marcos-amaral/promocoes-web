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
public class DateRangeFilter implements Serializable {

    private static final Logger logger = LogManager.getLogger();

    public boolean filterByDate(Object value, Object filter, Locale locale) {

        String filterText = (filter == null) ? null : filter.toString().trim();
        
        //logger.trace("filterText: {}|{}",filterText,value);
        
        if (filterText == null || filterText.isEmpty() || filterText.equals("-") || filterText.equals("todos")) {
            return true;
        }
        
        if(filterText.equals("null")){
            if(value==null)
                return true;
            else
                return false;
        }
        else if (value == null) {
            return false;
        }

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        Date filterDate = (Date) value;
        Date dateFrom;
        Date dateTo;
        try {
            String fromPart = filterText.substring(0, filterText.indexOf("-"));
            String toPart = filterText.substring(filterText.indexOf("-") + 1);
            dateFrom = fromPart.isEmpty() ? null : df.parse(fromPart);
            dateTo = toPart.isEmpty() ? null : df.parse(toPart);
        } catch (ParseException pe) {
            logger.warn("unable to parse date: " + filterText, pe);
            return false;
        }
        
        //logger.trace("dateFrom: {}",dateFrom);
        //logger.trace("dateTo: {}",dateTo);
        //logger.trace("filterDate: {}",filterDate);

        return (dateFrom == null || filterDate.after(dateFrom)) && (dateTo == null || filterDate.before(dateTo));
    }
}
