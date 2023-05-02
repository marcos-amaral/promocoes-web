/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author mmouraam
 */
@FacesConverter(value = "genericConverter")
public class GenericConverter implements Converter {

    private static final Pattern MONEY_PATTERN = Pattern.compile("([0-9]+)([.,])?(\\d{2})?");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",new java.util.Locale("pt","BR"));

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("MPA>getAsObject:"+value);
        if(value==null)
            return null;
        
        if("true".equals(value) || "S".equalsIgnoreCase(value))
            return Boolean.TRUE;
        else if("false".equals(value) || "N".equalsIgnoreCase(value))
            return Boolean.FALSE;

        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value instanceof Boolean)
            return ((boolean)value)?"S":"N";
        else if(value instanceof Date){
            return sdf.format(value);
        } else     
            return value + "";
    }
}
