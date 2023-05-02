/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.importar.entity.ui;

import com.oi.promocoesweb.importar.enumerate.TipoCampo;
import com.oi.promocoesweb.validation.enums.ValidationEnum;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mmouraam
 */
public class Campo {
    private String nome;
    private TipoCampo tipo;
    private ValidationEnum tipoDado;
    private List<Column> columns = new ArrayList<>();
    private String nomeColuna;

    public Campo(){
        
    }
    public Campo(String stringCellValue) {
        nome = stringCellValue;
        tipo = TipoCampo.VALOR;
        tipoDado = ValidationEnum.VALOR_UNICO;
    }

    @Override
    public String toString() {
        return "Campo{" + "nome=" + nome + ", tipo=" + tipo + ", columns=" + columns + '}';
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
        final Campo other = (Campo) obj;
        if (this.nome != null && !this.nome.equals(other.nome)) {
            return false;
        }
        if (this.nome == null && other.nome != null) {
            return false;
        }
        return true;
    }
    
    public void addSubstringValues(String value) {
        Column column = new Column();
        column.setSubstringValue(value);
        this.columns.add(column);
    }
    
    public void remSubstringValues(String value) {
        Column column = new Column();
        column.setSubstringValue(value);
        this.columns.remove(column);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoCampo getTipo() {
        return tipo;
    }

    public void setTipo(TipoCampo tipo) {
        this.tipo = tipo;
    }

    public List<String> getSubstringValues() {
        List<String> substringValues = new ArrayList<>();
        for (Column column : columns) {
            substringValues.add(column.getSubstringValue());
        }
        return substringValues;
    }

    public void setSubstringValues(List<String> substringValues) {
        for (String substringValue : substringValues) {
            Column column = new Column();
            column.setSubstringValue(substringValue);
            if(!this.columns.contains(column))
                this.columns.add(column);
        }
    }
    
    public void addColumn(){
        columns.add(new Column());
    }
    
    public void deleteColumn(Column c){
        columns.remove(c);
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
    
    public ValidationEnum getTipoDado() {
        return tipoDado;
    }

    public void setTipoDado(ValidationEnum tipoDado) {
        this.tipoDado = tipoDado;
    }

    public String getNomeColuna() {
        return nomeColuna;
    }

    public void setNomeColuna(String nomeColuna) {
        this.nomeColuna = nomeColuna;
    }
    
}
