/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.enums;

/**
 *
 * @author mpma0
 */
public class TabelaFraseologiasOfertasPre {

    public static final String SELECT = 
            "SELECT '' as json,\n" +
            "	ID_OFERTAOCS,\n" +
            "	DDD,\n" +
            "	JACLIENTE_OFERTA_USSD,\n" +
            "	JACLIENTE_SVA_USSD,\n" +
            "	DETALHESOFERTA_USSD,\n" +
            "	REGRAFALLBACK_USSD,\n" +
            "	REGRAROLLOVER_USSD,\n" +
            "	NAOCLIENTE_INFORMAOFERTA_USSD,\n" +
            "	NAOCLIENTE_INCENTIVOOFERTA_USSD,\n" +
            "	OFERTAMIGRADOS_USSD,\n" +
            "	INCENTIVOOFERTA_SC_USSD,\n" +
            "	ID_BENEFICIO,\n" +
            "	ID_PROGRAMA,\n" +
            "	JACLIENTE_OFERTA_144,\n" +
            "	JACLIENTE_SVA_144,\n" +
            "	DETALHESOFERTA_144,\n" +
            "	REGRAFALLBACK_144,\n" +
            "	REGRAROLLOVER_144,\n" +
            "	NAOCLIENTE_INFORMAOFERTA_144,\n" +
            "	NAOCLIENTE_INCENTIVOOFERTA_144,\n" +
            "	OFERTAMIGRADOS_144,\n" +
            "	INCENTIVOOFERTA_SC_144," +
            "   t.ID,t.TEMPID,t.HISTORY,t.INSERTED,t.DT_GMUD,t.DELETED FROM FRASEOLOGIAS_OFERTASPRE t WHERE (t.DT_GMUD IS NULL AND t.DELETED = 0) OR (t.DT_GMUD IS NOT NULL) ORDER BY t.INSERTED DESC NULLS LAST";
            
            public static final String INSERT = 
            "INSERT INTO FRASEOLOGIAS_OFERTASPRE (\n" +
            "	ID_OFERTAOCS,\n" +
            "	DDD,\n" +
            "	ID_BENEFICIO,\n" +
            "	ID_PROGRAMA,\n" +
            "	JACLIENTE_OFERTA_USSD,\n" +
            "	JACLIENTE_SVA_USSD,\n" +
            "	DETALHESOFERTA_USSD,\n" +
            "	REGRAFALLBACK_USSD,\n" +
            "	REGRAROLLOVER_USSD,\n" +
            "	NAOCLIENTE_INFORMAOFERTA_USSD,\n" +
            "	NAOCLIENTE_INCENTIVOOFERTA_USSD,\n" +
            "	OFERTAMIGRADOS_USSD,\n" +
            "	INCENTIVOOFERTA_SC_USSD,\n" +
            "	JACLIENTE_OFERTA_144,\n" +
            "	JACLIENTE_SVA_144,\n" +
            "	DETALHESOFERTA_144,\n" +
            "	REGRAFALLBACK_144,\n" +
            "	REGRAROLLOVER_144,\n" +
            "	NAOCLIENTE_INFORMAOFERTA_144,\n" +
            "	NAOCLIENTE_INCENTIVOOFERTA_144,\n" +
            "	OFERTAMIGRADOS_144,\n" +
            "	INCENTIVOOFERTA_SC_144,\n" +
            "	HISTORY,\n" +        
            "	DT_GMUD\n" +        
            ")(SELECT \n" +
            "	ID_OFERTAOCS,\n" +
            "	DDD,\n" +
            "	ID_BENEFICIO,\n" +
            "	ID_PROGRAMA,\n" +
            "	JACLIENTE_OFERTA_USSD,\n" +
            "	JACLIENTE_SVA_USSD,\n" +
            "	DETALHESOFERTA_USSD,\n" +
            "	REGRAFALLBACK_USSD,\n" +
            "	REGRAROLLOVER_USSD,\n" +
            "	NAOCLIENTE_INFORMAOFERTA_USSD,\n" +
            "	NAOCLIENTE_INCENTIVOOFERTA_USSD,\n" +
            "	OFERTAMIGRADOS_USSD,\n" +
            "	INCENTIVOOFERTA_SC_USSD,\n" + 
            "	JACLIENTE_OFERTA_144,\n" +
            "	JACLIENTE_SVA_144,\n" +
            "	DETALHESOFERTA_144,\n" +
            "	REGRAFALLBACK_144,\n" +
            "	REGRAROLLOVER_144,\n" +
            "	NAOCLIENTE_INFORMAOFERTA_144,\n" +
            "	NAOCLIENTE_INCENTIVOOFERTA_144,\n" +
            "	OFERTAMIGRADOS_144,\n" +
            "	INCENTIVOOFERTA_SC_144,\n" +
            "	?,\n" + 
            "   DT_GMUD\n" +
            "FROM dual, JSON_TABLE(?, '$' COLUMNS (\n" +
            "	ID_OFERTAOCS VARCHAR2(2500) PATH '$.id_ofertaocs',\n" +
            "	DDD VARCHAR2(2500) PATH '$.ddd',\n" +
            "	ID_BENEFICIO VARCHAR2(2500) PATH '$.id_beneficio',\n" +
            "	ID_PROGRAMA VARCHAR2(2500) PATH '$.id_programa',\n" +
            "	JACLIENTE_OFERTA_USSD VARCHAR2(2500) PATH '$.jacliente_oferta_ussd',\n" +
            "	JACLIENTE_SVA_USSD VARCHAR2(2500) PATH '$.jacliente_sva_ussd',\n" +
            "	DETALHESOFERTA_USSD VARCHAR2(2500) PATH '$.detalhesoferta_ussd',\n" +
            "	REGRAFALLBACK_USSD VARCHAR2(2500) PATH '$.regrafallback_ussd',\n" +
            "	REGRAROLLOVER_USSD VARCHAR2(2500) PATH '$.regrarollover_ussd',\n" +
            "	NAOCLIENTE_INFORMAOFERTA_USSD VARCHAR2(2500) PATH '$.naocliente_informaoferta_ussd',\n" +
            "	NAOCLIENTE_INCENTIVOOFERTA_USSD VARCHAR2(2500) PATH '$.naocliente_incentivooferta_ussd',\n" +
            "	OFERTAMIGRADOS_USSD VARCHAR2(2500) PATH '$.ofertamigrados_ussd',\n" +
            "	INCENTIVOOFERTA_SC_USSD VARCHAR2(2500) PATH '$.incentivooferta_sc_ussd',\n" +
            "	JACLIENTE_OFERTA_144 VARCHAR2(2500) PATH '$.jacliente_oferta_144',\n" +
            "	JACLIENTE_SVA_144 VARCHAR2(2500) PATH '$.jacliente_sva_144',\n" +
            "	DETALHESOFERTA_144 VARCHAR2(2500) PATH '$.detalhesoferta_144',\n" +
            "	REGRAFALLBACK_144 VARCHAR2(2500) PATH '$.regrafallback_144',\n" +
            "	REGRAROLLOVER_144 VARCHAR2(2500) PATH '$.regrarollover_144',\n" +
            "	NAOCLIENTE_INFORMAOFERTA_144 VARCHAR2(2500) PATH '$.naocliente_informaoferta_144',\n" +
            "	NAOCLIENTE_INCENTIVOOFERTA_144 VARCHAR2(2500) PATH '$.naocliente_incentivooferta_144',\n" +
            "	OFERTAMIGRADOS_144 VARCHAR2(2500) PATH '$.ofertamigrados_144',\n" +
            "	INCENTIVOOFERTA_SC_144 VARCHAR2(2500) PATH '$.incentivooferta_sc_144',\n" +
            "	DT_GMUD VARCHAR2(2500) PATH '$.dt_gmud'\n" +
            ")))";
            
            public static final String UPDATE = 
            "UPDATE FRASEOLOGIAS_OFERTASPRE SET \n" +
            "	ID_OFERTAOCS = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT ID_OFERTAOCS as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.ID_OFERTAOCS',json VARCHAR2(2500) PATH '$.id_ofertaocs')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	DDD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DDD as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DDD',json VARCHAR2(2500) PATH '$.ddd')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	ID_BENEFICIO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT ID_BENEFICIO as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.ID_BENEFICIO',json VARCHAR2(2500) PATH '$.id_beneficio')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	ID_PROGRAMA = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT ID_PROGRAMA as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.ID_PROGRAMA',json VARCHAR2(2500) PATH '$.id_programa')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	JACLIENTE_OFERTA_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT JACLIENTE_OFERTA_USSD as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.JACLIENTE_OFERTA_USSD',json VARCHAR2(2500) PATH '$.jacliente_oferta_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	JACLIENTE_SVA_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT JACLIENTE_SVA_USSD as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.JACLIENTE_SVA_USSD',json VARCHAR2(2500) PATH '$.jacliente_sva_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	DETALHESOFERTA_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DETALHESOFERTA_USSD as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DETALHESOFERTA_USSD',json VARCHAR2(2500) PATH '$.detalhesoferta_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	REGRAFALLBACK_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT REGRAFALLBACK_USSD as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.REGRAFALLBACK_USSD',json VARCHAR2(2500) PATH '$.regrafallback_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	REGRAROLLOVER_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT REGRAROLLOVER_USSD as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.REGRAROLLOVER_USSD',json VARCHAR2(2500) PATH '$.regrarollover_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	NAOCLIENTE_INFORMAOFERTA_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT NAOCLIENTE_INFORMAOFERTA_USSD as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.NAOCLIENTE_INFORMAOFERTA_USSD',json VARCHAR2(2500) PATH '$.naocliente_informaoferta_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	NAOCLIENTE_INCENTIVOOFERTA_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT NAOCLIENTE_INCENTIVOOFERTA_USSD as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.NAOCLIENTE_INCENTIVOOFERTA_USSD',json VARCHAR2(2500) PATH '$.naocliente_incentivooferta_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	OFERTAMIGRADOS_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTAMIGRADOS_USSD as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTAMIGRADOS_USSD',json VARCHAR2(2500) PATH '$.ofertamigrados_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	INCENTIVOOFERTA_SC_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT INCENTIVOOFERTA_SC_USSD as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.INCENTIVOOFERTA_SC_USSD',json VARCHAR2(2500) PATH '$.incentivooferta_sc_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	JACLIENTE_OFERTA_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT JACLIENTE_OFERTA_144 as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.JACLIENTE_OFERTA_144',json VARCHAR2(2500) PATH '$.jacliente_oferta_144')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	JACLIENTE_SVA_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT JACLIENTE_SVA_144 as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.JACLIENTE_SVA_144',json VARCHAR2(2500) PATH '$.jacliente_sva_144')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	DETALHESOFERTA_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DETALHESOFERTA_144 as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DETALHESOFERTA_144',json VARCHAR2(2500) PATH '$.detalhesoferta_144')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	REGRAFALLBACK_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT REGRAFALLBACK_144 as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.REGRAFALLBACK_144',json VARCHAR2(2500) PATH '$.regrafallback_144')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	REGRAROLLOVER_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT REGRAROLLOVER_144 as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.REGRAROLLOVER_144',json VARCHAR2(2500) PATH '$.regrarollover_144')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	NAOCLIENTE_INFORMAOFERTA_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT NAOCLIENTE_INFORMAOFERTA_144 as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.NAOCLIENTE_INFORMAOFERTA_144',json VARCHAR2(2500) PATH '$.naocliente_informaoferta_144')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	NAOCLIENTE_INCENTIVOOFERTA_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT NAOCLIENTE_INCENTIVOOFERTA_144 as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.NAOCLIENTE_INCENTIVOOFERTA_144',json VARCHAR2(2500) PATH '$.naocliente_incentivooferta_144')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	OFERTAMIGRADOS_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTAMIGRADOS_144 as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTAMIGRADOS_144',json VARCHAR2(2500) PATH '$.ofertamigrados_144')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	INCENTIVOOFERTA_SC_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT INCENTIVOOFERTA_SC_144 as coluna,jsonEdit,json FROM FRASEOLOGIAS_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.INCENTIVOOFERTA_SC_144',json VARCHAR2(2500) PATH '$.incentivooferta_sc_144')) where ID = ? order by rownum asc) where rownum = 1)\n" +
            ", DT_GMUD = ? WHERE ID = ?";
            
            public static final String UPDATEINSERT = 
            "INSERT INTO FRASEOLOGIAS_OFERTASPRE (\n" +
            "	ID_OFERTAOCS,\n" +
            "	DDD,\n" +
            "	ID_BENEFICIO,\n" +
            "	ID_PROGRAMA,\n" +
            "	JACLIENTE_OFERTA_USSD,\n" +
            "	JACLIENTE_SVA_USSD,\n" +
            "	DETALHESOFERTA_USSD,\n" +
            "	REGRAFALLBACK_USSD,\n" +
            "	REGRAROLLOVER_USSD,\n" +
            "	NAOCLIENTE_INFORMAOFERTA_USSD,\n" +
            "	NAOCLIENTE_INCENTIVOOFERTA_USSD,\n" +
            "	OFERTAMIGRADOS_USSD,\n" +
            "	INCENTIVOOFERTA_SC_USSD,\n" + 
            "	JACLIENTE_OFERTA_144,\n" +
            "	JACLIENTE_SVA_144,\n" +
            "	DETALHESOFERTA_144,\n" +
            "	REGRAFALLBACK_144,\n" +
            "	REGRAROLLOVER_144,\n" +
            "	NAOCLIENTE_INFORMAOFERTA_144,\n" +
            "	NAOCLIENTE_INCENTIVOOFERTA_144,\n" +
            "	OFERTAMIGRADOS_144,\n" +
            "	INCENTIVOOFERTA_SC_144,\n" + 
            "	HISTORY,\n" +
            "	DT_GMUD,\n" +
            "	TEMPID\n" +
            ")(SELECT \n" +
            "	ID_OFERTAOCS,\n" +
            "	DDD,\n" +
            "	ID_BENEFICIO,\n" +
            "	ID_PROGRAMA,\n" +
            "	JACLIENTE_OFERTA_USSD,\n" +
            "	JACLIENTE_SVA_USSD,\n" +
            "	DETALHESOFERTA_USSD,\n" +
            "	REGRAFALLBACK_USSD,\n" +
            "	REGRAROLLOVER_USSD,\n" +
            "	NAOCLIENTE_INFORMAOFERTA_USSD,\n" +
            "	NAOCLIENTE_INCENTIVOOFERTA_USSD,\n" +
            "	OFERTAMIGRADOS_USSD,\n" +
            "	INCENTIVOOFERTA_SC_USSD,\n" + 
            "	JACLIENTE_OFERTA_144,\n" +
            "	JACLIENTE_SVA_144,\n" +
            "	DETALHESOFERTA_144,\n" +
            "	REGRAFALLBACK_144,\n" +
            "	REGRAROLLOVER_144,\n" +
            "	NAOCLIENTE_INFORMAOFERTA_144,\n" +
            "	NAOCLIENTE_INCENTIVOOFERTA_144,\n" +
            "	OFERTAMIGRADOS_144,\n" +
            "	INCENTIVOOFERTA_SC_144,\n" + 
            "	? HISTORY,\n" +
            "	? DT_GMUD,\n" +
            "	ID\n" +
            "FROM FRASEOLOGIAS_OFERTASPRE WHERE ID = ?)";
            
            public static final String DELETE = 
            "INSERT INTO FRASEOLOGIAS_OFERTASPRE (\n" +
            "	ID_OFERTAOCS,\n" +
            "	DDD,\n" +
            "	ID_BENEFICIO,\n" +
            "	ID_PROGRAMA,\n" +
            "	JACLIENTE_OFERTA_USSD,\n" +
            "	JACLIENTE_SVA_USSD,\n" +
            "	DETALHESOFERTA_USSD,\n" +
            "	REGRAFALLBACK_USSD,\n" +
            "	REGRAROLLOVER_USSD,\n" +
            "	NAOCLIENTE_INFORMAOFERTA_USSD,\n" +
            "	NAOCLIENTE_INCENTIVOOFERTA_USSD,\n" +
            "	OFERTAMIGRADOS_USSD,\n" +
            "	INCENTIVOOFERTA_SC_USSD,\n" + 
            "	JACLIENTE_OFERTA_144,\n" +
            "	JACLIENTE_SVA_144,\n" +
            "	DETALHESOFERTA_144,\n" +
            "	REGRAFALLBACK_144,\n" +
            "	REGRAROLLOVER_144,\n" +
            "	NAOCLIENTE_INFORMAOFERTA_144,\n" +
            "	NAOCLIENTE_INCENTIVOOFERTA_144,\n" +
            "	OFERTAMIGRADOS_144,\n" +
            "	INCENTIVOOFERTA_SC_144,\n" + 
            "	HISTORY,\n" +
            "	DELETED,\n" +
            "	DT_GMUD,\n" +
            "	TEMPID\n" +
            ")(SELECT \n" +
            "	ID_OFERTAOCS,\n" +
            "	DDD,\n" +
            "	ID_BENEFICIO,\n" +
            "	ID_PROGRAMA,\n" +
            "	JACLIENTE_OFERTA_USSD,\n" +
            "	JACLIENTE_SVA_USSD,\n" +
            "	DETALHESOFERTA_USSD,\n" +
            "	REGRAFALLBACK_USSD,\n" +
            "	REGRAROLLOVER_USSD,\n" +
            "	NAOCLIENTE_INFORMAOFERTA_USSD,\n" +
            "	NAOCLIENTE_INCENTIVOOFERTA_USSD,\n" +
            "	OFERTAMIGRADOS_USSD,\n" +
            "	INCENTIVOOFERTA_SC_USSD,\n" + 
            "	JACLIENTE_OFERTA_144,\n" +
            "	JACLIENTE_SVA_144,\n" +
            "	DETALHESOFERTA_144,\n" +
            "	REGRAFALLBACK_144,\n" +
            "	REGRAROLLOVER_144,\n" +
            "	NAOCLIENTE_INFORMAOFERTA_144,\n" +
            "	NAOCLIENTE_INCENTIVOOFERTA_144,\n" +
            "	OFERTAMIGRADOS_144,\n" +
            "	INCENTIVOOFERTA_SC_144,\n" + 
            "	?,\n" +
            "	1,\n" +
            "	?, \n" +
            "	ID \n" +
            "FROM FRASEOLOGIAS_OFERTASPRE WHERE ID IN (?))";
            
            public static final String APROVAR = 
            "	t1.ID_BFPG = t2.ID_BFPG,\n" +
            "	t1.ID_OFERTAOCS = t2.ID_OFERTAOCS,\n" +
            "	t1.DDD = t2.DDD,\n" +
            "	t1.ID_BENEFICIO = t2.ID_BENEFICIO,\n" +
            "	t1.ID_PROGRAMA = t2.ID_PROGRAMA,\n" +
            "	t1.JACLIENTE_OFERTA_USSD = t2.JACLIENTE_OFERTA_USSD,\n" +
            "	t1.JACLIENTE_SVA_USSD = t2.JACLIENTE_SVA_USSD,\n" +
            "	t1.DETALHESOFERTA_USSD = t2.DETALHESOFERTA_USSD,\n" +
            "	t1.REGRAFALLBACK_USSD = t2.REGRAFALLBACK_USSD,\n" +
            "	t1.REGRAROLLOVER_USSD = t2.REGRAROLLOVER_USSD,\n" +
            "	t1.NAOCLIENTE_INFORMAOFERTA_USSD = t2.NAOCLIENTE_INFORMAOFERTA_USSD,\n" +
            "	t1.NAOCLIENTE_INCENTIVOOFERTA_USSD = t2.NAOCLIENTE_INCENTIVOOFERTA_USSD,\n" +
            "	t1.OFERTAMIGRADOS_USSD = t2.OFERTAMIGRADOS_USSD,\n" +
            "	t1.INCENTIVOOFERTA_SC_USSD = t2.INCENTIVOOFERTA_SC_USSD,\n" +
            "	t1.JACLIENTE_OFERTA_144 = t2.JACLIENTE_OFERTA_144,\n" +
            "	t1.JACLIENTE_SVA_144 = t2.JACLIENTE_SVA_144,\n" +
            "	t1.DETALHESOFERTA_144 = t2.DETALHESOFERTA_144,\n" +
            "	t1.REGRAFALLBACK_144 = t2.REGRAFALLBACK_144,\n" +
            "	t1.REGRAROLLOVER_144 = t2.REGRAROLLOVER_144,\n" +
            "	t1.NAOCLIENTE_INFORMAOFERTA_144 = t2.NAOCLIENTE_INFORMAOFERTA_144,\n" +
            "	t1.NAOCLIENTE_INCENTIVOOFERTA_144 = t2.NAOCLIENTE_INCENTIVOOFERTA_144,\n" +
            "	t1.OFERTAMIGRADOS_144 = t2.OFERTAMIGRADOS_144,\n" +
            "	t1.INCENTIVOOFERTA_SC_144 = t2.INCENTIVOOFERTA_SC_144,\n" +
            "   t1.HISTORY = t2.HISTORY";

}
