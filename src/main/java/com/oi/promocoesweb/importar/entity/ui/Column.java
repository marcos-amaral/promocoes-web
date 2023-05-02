/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.importar.entity.ui;

import java.util.Objects;

/**
 *
 * @author mpma0
 */
public class Column {
    private String substringValue;
    private String columnName;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.substringValue);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Column other = (Column) obj;
        if (this.substringValue!=null) {
            if(!this.substringValue.equals(other.substringValue)){
                return false;
            }
        }
        else{
            if(other.substringValue!=null){
                return false;
            }
        }
        return true;
    }

    public String getSubstringValue() {
        if(substringValue!=null)
            return substringValue.toUpperCase();
        else
            return null;
    }

    public void setSubstringValue(String substringValue) {
        this.substringValue = substringValue;
    }

    public String getColumnName() {
        if(columnName!=null)
            return columnName.toUpperCase();
        else
            return null;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    
}
