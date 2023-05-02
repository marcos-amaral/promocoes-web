/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.entity.ui;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oi.promocoesweb.template.entity.Template;
import com.oi.promocoesweb.validation.annotation.FieldInput;
import com.oi.promocoesweb.validation.annotation.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mmouraam
 */
public class ColumnModel implements Serializable {

    String key;
    String property;
    String value;
    Class type;
    
    String label;
    String responsiveClass;
    String mask;
    InputType inputType;
    int width;
    int maxLength;
    List<String> autoComplete;

    public ColumnModel(Field field, Template t) {
        try {
            init(field);
            value = String.valueOf(field.get(t));
        } catch (IllegalArgumentException | IllegalAccessException ex) {
        }
    }
    
    public ColumnModel(Field field) {
        init(field);
    }
    
    private void init(Field field){
        this.key = field.getName().toUpperCase();
        this.property = field.getName();
        this.type = field.getType();
        
        if("DDD".equals(key)) responsiveClass = "ui-g-12 ui-md-6 ui-lg-4";
        
        FieldInput annotation = field.getAnnotation(FieldInput.class);
        if(annotation!=null){
            inputType = annotation.name();
            mask = annotation.mask();
            maxLength = annotation.maxLength();
            label = annotation.label();
            autoComplete = Arrays.asList(annotation.autoComplete());
        } else {
            inputType = InputType.STRING;
            maxLength = 2500;
            
        }
        
        if(label==null || "".equals(label)) label = key;
        
        if((key!=null && key.length()>31) || inputType == InputType.WAV) responsiveClass = "ui-g-12 ui-md-6 ui-lg-4";
        else if(key!=null && key.length()<14) responsiveClass = "ui-g-12 ui-md-6 ui-lg-2";
        else responsiveClass = "ui-g-12 ui-md-6 ui-lg-3";
        
        if(key!=null) width = key.length()*10;
        if(width<=50) width = 200;
        if(width>500 || inputType==InputType.FRASE) width = 500;
    }

    public String getKey() {
        return key;
    }

    public String getProperty() {
        return property;
    }

    public int getWidth() {
        return width;
    }
    
    public int getWidth(String cellValue) {
        if(cellValue!=null && cellValue.length()>key.length() && inputType!=InputType.FRASE){
            return (cellValue.length()*10)>500?500:(cellValue.length()*10);
        }
        return width;
    }

    public String getType() {
        return type.toString();
    }

    public String getResponsiveClass() {
        return responsiveClass;
    }

    public InputType getInputType() {
        return inputType;
    }

    public String getMask() {
        return mask;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public String getLabel() {
        return label;
    }

    public List<String> getAutoComplete(String query) {
        return autoComplete;
    }
    
    public List<String> getAutoComplete() {
        return autoComplete;
    }

    public String getValue() {
        return value;
    }
    
    public static boolean isValidColumn(Field field){
        if (field.getAnnotation(Deprecated.class) == null && field.getAnnotation(JsonIgnore.class) == null
                            && (field.getType() == String.class || 
                                field.getType() == Date.class || 
                                field.getType() == Boolean.class ||
                                field.getType() == boolean.class ||
                                field.getType() == Integer.class || 
                                field.getType() == int.class || 
                                field.getType() == Float.class || 
                                field.getType() == float.class || 
                                field.getType() == Double.class || 
                                field.getType() == double.class)) {
            return true;
        }
        
        return false;
    }
    
    public static boolean isValidColumnNotId(Field field){
        if (field.getAnnotation(Deprecated.class) == null && field.getAnnotation(JsonIgnore.class) == null && field.getAnnotation(Id.class) == null
                            && (field.getType() == String.class || 
                                field.getType() == Date.class || 
                                field.getType() == Boolean.class ||
                                field.getType() == boolean.class ||
                                field.getType() == Integer.class || 
                                field.getType() == int.class || 
                                field.getType() == Float.class || 
                                field.getType() == float.class || 
                                field.getType() == Double.class || 
                                field.getType() == double.class)) {
            return true;
        }
        
        return false;
    }
    
    public static boolean isValidColumnId(Field field){
        if (field.getAnnotation(Id.class) != null
                            && (field.getType() == String.class || 
                                field.getType() == Date.class || 
                                field.getType() == Boolean.class ||
                                field.getType() == boolean.class ||
                                field.getType() == Integer.class || 
                                field.getType() == int.class || 
                                field.getType() == Float.class || 
                                field.getType() == float.class || 
                                field.getType() == Double.class || 
                                field.getType() == double.class)) {
            return true;
        }
        
        return false;
    }
    
    public static boolean isValidColumnFraseUSSD(Field field){
        if (field.getAnnotation(Deprecated.class) == null && field.getAnnotation(JsonIgnore.class) == null && field.getAnnotation(Id.class) == null
                            && (field.getType() == String.class && !field.getName().endsWith("_144"))) {
            return true;
        }
        
        return false;
    }
    
    public static boolean isValidColumnFrase144(Field field){
        if (field.getAnnotation(Deprecated.class) == null && field.getAnnotation(JsonIgnore.class) == null && field.getAnnotation(Id.class) == null
                            && (field.getType() == String.class && field.getName().endsWith("_144"))) {
            return true;
        }
        
        return false;
    }
}
