/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.importar.entity;

import com.oi.promocoesweb.importar.exception.ImportCellValueException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mmouraam
 */
public class CellValueHolder implements Serializable{
    
    private boolean ok;
    private boolean current;
    private ImportCellValueException exception;
    private String value = "";
    private List<CellValueHolder> oldValues;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public ImportCellValueException getException() {
        return exception;
    }

    public void setException(ImportCellValueException exception) {
        this.exception = exception;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public void setValue(int value) {
        this.value = value+"";
    }
    
    public void setValue(double value) {
        this.value = (value+"").replace(".", ",");
    }

    public List<CellValueHolder> getOldValues() {
        return oldValues;
    }

    public void setOldValues(List<CellValueHolder> oldValues) {
        this.oldValues = oldValues;
    }
    
}
