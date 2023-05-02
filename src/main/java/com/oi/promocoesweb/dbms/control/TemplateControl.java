/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.dbms.control;

import com.oi.promocoesweb.dbms.dao.TemplateDAO;
import com.oi.promocoesweb.exception.DbPoolFatalException;
import com.oi.promocoesweb.template.entity.IoCartaoControle;
import com.oi.promocoesweb.template.entity.Mensagem;
import com.oi.promocoesweb.template.entity.Prompt;
import com.oi.promocoesweb.template.entity.Template;
import com.oi.promocoesweb.template.entity.aprovacao.Aprovacao;
import com.oi.promocoesweb.template.enums.StatusEnum;
import com.oi.promocoesweb.template.enums.TabelasEnum;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mmouraam
 */
public class TemplateControl {
    
    private static final TemplateDAO dao = new TemplateDAO();
    
    ////////////////////////////////////////////////////////////////////////
    //CONSTRUCTORS
    ////////////////////////////////////////////////////////////////////////
    private TemplateControl() {
    }
    
    public static Map<String, Boolean> getCamposObrigatorios(TabelasEnum tabela) throws DbPoolFatalException, SQLException, Exception {
        return dao.getCamposObrigatorios(tabela);
    }
    
    public static List<Prompt> getAllPrompts(int limit) throws DbPoolFatalException, SQLException, Exception {
        return dao.getAllPrompts(limit);
    }
    
    public static List<Aprovacao> getAprovacoes(TabelasEnum tabela) throws DbPoolFatalException, SQLException, Exception {
        return dao.getAprovacoes(tabela);
    }
    
    public static List<String> getValidIdOfertaOcs(Date dt_gmud) throws DbPoolFatalException, SQLException, Exception {
        return dao.getValidIdOfertaOcs(dt_gmud);
    }
     public static List<String> getValidIdOfertaOcsS2S(Date dt_gmud) throws DbPoolFatalException, SQLException, Exception {
        return dao.getValidIdOfertaOcsS2S(dt_gmud);
    }
    
    public static List<String> getValidTipoBonus(Date dt_gmud) throws DbPoolFatalException, SQLException, Exception {
        return dao.getValidTipoBonus(dt_gmud);
    }
    public static List<String> getValidTipoBonusS2S(Date dt_gmud) throws DbPoolFatalException, SQLException, Exception {
        return dao.getValidTipoBonusS2S(dt_gmud);
    }
    
    public static List<String> getValidTipoPlano(Date dt_gmud) throws DbPoolFatalException, SQLException, Exception {
        return dao.getValidTipoPlano(dt_gmud);
    }
    //Portabilidade
    public static List<String> getValidIdOferta(Date dt_gmud) throws DbPoolFatalException, SQLException, Exception {
        return dao.getValidIdOferta(dt_gmud);
    }
    
    //PortabilidadeOcs
    public static List<String> getValidIdOfertaOcsPortabilidade(Date dt_gmud) throws DbPoolFatalException, SQLException, Exception {
        return dao.getValidIdOfertaOcsPortabilidade(dt_gmud);
    }
    
    public static List<Template> getAll(TabelasEnum tabela, int limit) throws DbPoolFatalException, SQLException, Exception {
        return dao.getAll(tabela,limit);
    }
    
    public static void insert(Map<TabelasEnum, List<Template>> templateMap) throws DbPoolFatalException, SQLException, Exception {
        dao.insert(templateMap);
    }
    
    public static void update(TabelasEnum tabela, List<Template> templateList, Template templateEdit) throws DbPoolFatalException, SQLException, Exception {
        dao.update(tabela,templateList,templateEdit);
    }
    
    public static void delete(TabelasEnum tabela, List<Template> selectedList, Template template) throws DbPoolFatalException, SQLException, Exception {
        dao.delete(tabela,selectedList,template);
    }
    
    public static void adiar(TabelasEnum tabela, Date oldDtGmud, Date newDtGmud) throws DbPoolFatalException, SQLException, Exception {
        dao.adiar(tabela,oldDtGmud,newDtGmud);
    }
    
    public static void aprovar(TabelasEnum tabela, Date oldDtGmud, int userId, StatusEnum status) throws DbPoolFatalException, SQLException, Exception {
        dao.aprovar(tabela,oldDtGmud,userId,status);
    }
    
    public static void implantar(TabelasEnum tabela, Date oldDtGmud) throws DbPoolFatalException, SQLException, Exception {
        dao.implantar(tabela,oldDtGmud);
    }
}
