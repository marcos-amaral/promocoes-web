/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.dbms.control;

import com.oi.promocoesweb.dbms.dao.ImportarDAO;
import com.oi.promocoesweb.exception.DbPoolFatalException;
import com.oi.promocoesweb.importar.entity.ui.Registro;
import com.oi.promocoesweb.importar.enumerate.AbaTemplate;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author mmouraam
 */
public class ImportarControl {
    
    private static final ImportarDAO dao = new ImportarDAO();
    
    ////////////////////////////////////////////////////////////////////////
    //CONSTRUCTORS
    ////////////////////////////////////////////////////////////////////////
    private ImportarControl() {
    }
    
    public static void save(List<Registro> tabela, AbaTemplate aba) throws DbPoolFatalException, SQLException, Exception {
        dao.save(tabela, aba);
    }
}
