/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.converter;

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
@FacesConverter(value = "moneyConverter")
public class MoneyConverter implements Converter {

    private static final Pattern MONEY_PATTERN = Pattern.compile("([0-9]+)([.,])?(\\d{2})?");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Matcher matcher = MONEY_PATTERN.matcher(value);
        if (!matcher.matches()) {
            throw new RuntimeException(
                    String.format("invalid input: %s. The valid format is of type %s%n",
                            value, "99.99"));
        }

        int real;
        int centavos;

        real = Integer.parseInt(matcher.group(1));
        if (matcher.group(3) != null) {
            centavos = Integer.parseInt(matcher.group(3));
        } else {
            centavos = 0;
        }

        return real + "." + String.format("%02d", centavos);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && String.valueOf(value).contains(",")) {
            return String.valueOf(value).replace(",", ".");
        }
        return value + "";
    }
}
