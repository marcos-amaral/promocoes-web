/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.enums;

/**
 *
 * @author jpereic
 */
public class TabelaTemplatePre {

    public static final String SELECT
            = "SELECT JSON_QUERY(\n"
            + " concat(concat(concat(concat(concat(concat(concat(\n"
            + " concat(concat('{\"ddd\":\"',DDD),'\",'),\n"
            + " concat(concat('\"oferta1\":\"',OFERTA1),'\",')),\n"
            + " concat(concat('\"oferta2\":\"',OFERTA2),'\",')),\n"
            + " concat(concat('\"oferta3\":\"',OFERTA3),'\",')),\n"
            + " concat(concat('\"oferta_erro_in\":\"',OFERTA_ERRO_IN),'\",')),\n"
            + " concat(concat('\"oferta_erro_siebel\":\"',OFERTA_ERRO_SIEBEL),'\",')),\n"
            + " concat(concat('\"segprompt\":\"',SEGPROMPT),'\",')),\n"
            + " concat(concat('\"tipo_bonus\":\"',TIPO_BONUS),'\"}'))\n"
            + ",'$') as json,ID,TEMPID,HISTORY,INSERTED,DT_GMUD,DELETED FROM TB_TEMPLATE t WHERE (DT_GMUD IS NULL AND DELETED = 0) OR (DT_GMUD IS NOT NULL) ORDER BY INSERTED DESC NULLS LAST";

    public static final String INSERT
            = "INSERT INTO TB_TEMPLATE (\n"
            + "	DDD,\n"
            + "	OFERTA1,\n"
            + "	OFERTA2,\n"
            + "	OFERTA3,\n"
            + "	OFERTA_ERRO_IN,\n"
            + "	OFERTA_ERRO_SIEBEL,\n"
            + "	SEGPROMPT,\n"
            + "	TIPO_BONUS,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD\n"
            + ")(SELECT \n"
            + "	DDD,\n"
            + "	OFERTA1,\n"
            + "	OFERTA2,\n"
            + "	OFERTA3,\n"
            + "	OFERTA_ERRO_IN,\n"
            + "	OFERTA_ERRO_SIEBEL,\n"
            + "	SEGPROMPT,\n"
            + "	TIPO_BONUS,\n"
            + "	?,\n"
            + "   DT_GMUD\n"
            + "FROM dual, JSON_TABLE(?, '$' COLUMNS (\n"
            + "	DDD VARCHAR2(2500) PATH '$.ddd',\n"
            + "	OFERTA1 VARCHAR2(2500) PATH '$.oferta1',\n"
            + "	OFERTA2 VARCHAR2(2500) PATH '$.oferta2',\n"
            + "	OFERTA3 VARCHAR2(2500) PATH '$.oferta3',\n"
            + "	OFERTA_ERRO_IN VARCHAR2(2500) PATH '$.oferta_erro_in',\n"
            + "	OFERTA_ERRO_SIEBEL VARCHAR2(2500) PATH '$.oferta_erro_siebel',\n"
            + "	SEGPROMPT VARCHAR2(2500) PATH '$.segprompt',\n"
            + "	TIPO_BONUS VARCHAR2(2500) PATH '$.tipo_bonus',\n"
            + "	DT_GMUD VARCHAR2(2500) PATH '$.dt_gmud'\n"
            + ")))";

    public static final String UPDATE
            = "UPDATE TB_TEMPLATE SET \n"
            + "	DDD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DDD as coluna,jsonEdit,json FROM TB_TEMPLATE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DDD',json VARCHAR2(2500) PATH '$.ddd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	OFERTA1 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTA1 as coluna,jsonEdit,json FROM TB_TEMPLATE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTA1',json NUMBER(38,0) PATH '$.oferta1')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	OFERTA2 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTA2 as coluna,jsonEdit,json FROM TB_TEMPLATE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTA2',json NUMBER(38,0) PATH '$.oferta2')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	OFERTA3 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTA3 as coluna,jsonEdit,json FROM TB_TEMPLATE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTA3',json NUMBER(38,0) PATH '$.oferta3')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	OFERTA_ERRO_IN = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTA_ERRO_IN as coluna,jsonEdit,json FROM TB_TEMPLATE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTA_ERRO_IN',json NUMBER(38,0) PATH '$.oferta_erro_in')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	OFERTA_ERRO_SIEBEL = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTA_ERRO_SIEBEL as coluna,jsonEdit,json FROM TB_TEMPLATE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTA_ERRO_SIEBEL',json NUMBER(38,0) PATH '$.oferta_erro_siebel')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	SEGPROMPT = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT SEGPROMPT as coluna,jsonEdit,json FROM TB_TEMPLATE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.SEGPROMPT',json VARCHAR2(2500) PATH '$.segprompt')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	TIPO_BONUS = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TIPO_BONUS as coluna,jsonEdit,json FROM TB_TEMPLATE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TIPO_BONUS',json VARCHAR2(2500) PATH '$.tipo_bonus')) where ID = ? order by rownum asc) where rownum = 1)\n"
            + ", DT_GMUD = ? WHERE ID = ?";

    public static final String UPDATEINSERT
            = "INSERT INTO TB_TEMPLATE (\n"
            + "	DDD,\n"
            + "	OFERTA1,\n"
            + "	OFERTA2,\n"
            + "	OFERTA3,\n"
            + "	OFERTA_ERRO_IN,\n"
            + "	OFERTA_ERRO_SIEBEL,\n"
            + "	SEGPROMPT,\n"
            + "	TIPO_BONUS,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	DDD,\n"
            + "	OFERTA1,\n"
            + "	OFERTA2,\n"
            + "	OFERTA3,\n"
            + "	OFERTA_ERRO_IN,\n"
            + "	OFERTA_ERRO_SIEBEL,\n"
            + "	SEGPROMPT,\n"
            + "	TIPO_BONUS,\n"
            + "	? HISTORY,\n"
            + "	? DT_GMUD,\n"
            + "	ID\n"
            + "FROM TB_TEMPLATE WHERE ID = ?)";

    public static final String DELETE
            = "INSERT INTO TB_TEMPLATE (\n"
            + "	DDD,\n"
            + "	OFERTA1,\n"
            + "	OFERTA2,\n"
            + "	OFERTA3,\n"
            + "	OFERTA_ERRO_IN,\n"
            + "	OFERTA_ERRO_SIEBEL,\n"
            + "	SEGPROMPT,\n"
            + "	TIPO_BONUS,\n"
            + "	HISTORY,\n"
            + "	DELETED,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	DDD,\n"
            + "	OFERTA1,\n"
            + "	OFERTA2,\n"
            + "	OFERTA3,\n"
            + "	OFERTA_ERRO_IN,\n"
            + "	OFERTA_ERRO_SIEBEL,\n"
            + "	SEGPROMPT,\n"
            + "	TIPO_BONUS,\n"
            + "	?,\n"
            + "	1,\n"
            + "	?, \n"
            + "	ID \n"
            + "FROM TB_TEMPLATE WHERE ID IN (?))";

    public static final String APROVAR
            = "t1.DDD = t2.DDD,\n"
            + "t1.OFERTA1 = t2.OFERTA1,\n"
            + "t1.OFERTA2 = t2.OFERTA2,\n"
            + "t1.OFERTA3 = t2.OFERTA3,\n"
            + "t1.OFERTA_ERRO_IN = t2.OFERTA_ERRO_IN,\n"
            + "t1.OFERTA_ERRO_SIEBEL = t2.OFERTA_ERRO_SIEBEL,\n"
            + "t1.SEGPROMPT = t2.SEGPROMPT,\n"
            + "t1.TIPO_BONUS = t2.TIPO_BONUS,\n"
            + "t1.HISTORY = t2.HISTORY";

}
