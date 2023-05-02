/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.enums;

/**
 *
 * @author Jpereirc
 */
public class TabelaIncentivosPortabilidade {

    public static final String SELECT
            = "SELECT JSON_QUERY(\n"
            + " concat(concat(\n"
            + " concat(concat('{\"ddd\":\"',DDD),'\",'),\n"
            + " concat(concat('\"oferta1\":\"',OFERTA1),'\",')),\n"
            + " concat(concat('\"oferta2\":\"',OFERTA2),'\"}'))\n"
            + ",'$') as json,ID,TEMPID,HISTORY,INSERTED,DT_GMUD,DELETED FROM INCENTIVOS_PORTABILIDADE t WHERE (DT_GMUD IS NULL AND DELETED = 0) OR (DT_GMUD IS NOT NULL) ORDER BY INSERTED DESC NULLS LAST";

    public static final String INSERT
            = "INSERT INTO INCENTIVOS_PORTABILIDADE (\n"
            + "	DDD,\n"
            + "	OFERTA1,\n" 
            + "	OFERTA2,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD\n"
            + ")(SELECT \n"
            + "	DDD,\n"
            + "	OFERTA1,\n" 
            + "	OFERTA2,\n"
            + "	?,\n"
            + "   DT_GMUD\n"
            + "FROM dual, JSON_TABLE(?, '$' COLUMNS (\n"
            + "	DDD VARCHAR2(2500) PATH '$.ddd',\n"
            + "	OFERTA1 VARCHAR2(2500) PATH '$.oferta1',\n"
            + "	OFERTA2 VARCHAR2(2500) PATH '$.oferta2',\n"
            + "	DT_GMUD VARCHAR2(2500) PATH '$.dt_gmud'\n"
            + ")))";

    public static final String UPDATE
            = "UPDATE INCENTIVOS_PORTABILIDADE SET \n"
            + "	DDD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DDD as coluna,jsonEdit,json FROM INCENTIVOS_PORTABILIDADE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DDD',json VARCHAR2(2500) PATH '$.ddd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	OFERTA1 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTA1 as coluna,jsonEdit,json FROM INCENTIVOS_PORTABILIDADE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTA1',json VARCHAR2(2500) PATH '$.oferta1')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	OFERTA2 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTA2 as coluna,jsonEdit,json FROM INCENTIVOS_PORTABILIDADE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTA2',json VARCHAR2(2500) PATH '$.oferta2')) where ID = ? order by rownum asc) where rownum = 1)\n"
            + ", DT_GMUD = ? WHERE ID = ?";

    public static final String UPDATEINSERT
            = "INSERT INTO INCENTIVOS_PORTABILIDADE (\n"
            + "	DDD,\n"        
            + "	OFERTA1,\n"
            + "	OFERTA2,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	DDD,\n"
            + "	OFERTA1,\n"
            + "	OFERTA2,\n"
            + "	? HISTORY,\n"
            + "	? DT_GMUD,\n"
            + "	ID\n"
            + "FROM INCENTIVOS_PORTABILIDADE WHERE ID = ?)";

    public static final String DELETE
            = "INSERT INTO INCENTIVOS_PORTABILIDADE (\n"
            + "	DDD,\n"
            + "	OFERTA1,\n"
            + "	OFERTA2,\n"
            + "	HISTORY,\n"
            + "	DELETED,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	DDD,\n"
            + "	OFERTA1,\n"
            + "	OFERTA2,\n"
            + "	?,\n"
            + "	1,\n"
            + "	?, \n"
            + "	ID \n"
            + "FROM INCENTIVOS_PORTABILIDADE WHERE ID IN (?))";

    public static final String APROVAR
            = "t1.DDD = t2.DDD,\n"
            + "t1.OFERTA1 = t2.OFERTA1,\n"
            + "t1.OFERTA2 = t2.OFERTA2,\n"
            + "t1.HISTORY = t2.HISTORY";

}
