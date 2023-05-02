/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.ui.validator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("dddListValidator")
public class DddListValidator implements Validator {
    
    private static final Pattern DDD_PATTERN = Pattern.compile("([1-9][1-9])(([,][1-9][1-9])+)?");

    public DddListValidator() {

    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        //get limit

        if (value != null) {
            
            Matcher matcher = DDD_PATTERN.matcher(String.valueOf(value));
            if(!matcher.matches()){
                FacesMessage msg = new FacesMessage("DDD: Valor inválido");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
            
            String[] list = String.valueOf(value).split(",");
            Set<String> set = new HashSet<>();
            Set<String> repeatedSet = new HashSet<>();
            for (String element : list) {
                if (set.add(element) == false) {
                    repeatedSet.add(element);
                } 
            }

            if (!repeatedSet.isEmpty()) {
                FacesMessage msg = new FacesMessage("DDD: Valores repetidos: " + repeatedSet.toString());
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }

    }
}
