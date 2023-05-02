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
public class TabelaFrasePre {

    public static final String SELECT
            = "SELECT JSON_QUERY(\n"
            + " concat(concat(concat(concat(concat(concat(concat(\n"
            + " concat(concat('{\"cod_oferta\":\"',COD_OFERTA),'\",'),\n"
            + " concat(concat('\"ddd\":\"',DDD),'\",')),\n"
            + " concat(concat('\"oferta1\":\"',OFERTA1),'\",')),\n"
            + " concat(concat('\"oferta2\":\"',OFERTA2),'\",')),\n"
            + " concat(concat('\"oferta3\":\"',OFERTA3),'\",')),\n"
            + " concat(concat('\"oferta_erro_in\":\"',OFERTA_ERRO_IN),'\",')),\n"
            + " concat(concat('\"oferta_siebel\":\"',OFERTA_SIEBEL),'\",')),\n"
            + " concat(concat('\"segprompt\":\"',SEGPROMPT),'\"}'))\n"
            + ",'$') as json,ID,TEMPID,HISTORY,INSERTED,DT_GMUD,DELETED FROM FRASE_PRE t WHERE (DT_GMUD IS NULL AND DELETED = 0) OR (DT_GMUD IS NOT NULL) ORDER BY INSERTED DESC NULLS LAST";

    public static final String INSERT
            = "INSERT INTO FRASE_PRE (\n"
            + "	COD_OFERTA,\n"
            + "	DDD,\n"
            + "	OFERTA1,\n"
            + "	OFERTA2,\n"
            + "	OFERTA3,\n"
            + "	OFERTA_ERRO_IN,\n"
            + "	OFERTA_SIEBEL,\n"
            + "	SEGPROMPT,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD\n"
            + ")(SELECT \n"
            + "	COD_OFERTA,\n"
            + "	DDD,\n"
            + "	OFERTA1,\n"
            + "	OFERTA2,\n"
            + "	OFERTA3,\n"
            + "	OFERTA_ERRO_IN,\n"
            + "	OFERTA_SIEBEL,\n"
            + "	SEGPROMPT,\n"
            + "	?,\n"
            + "   DT_GMUD\n"
            + "FROM dual, JSON_TABLE(?, '$' COLUMNS (\n"
            + "	COD_OFERTA VARCHAR2(2500) PATH '$.cod_oferta',\n"
            + "	DDD VARCHAR2(2500) PATH '$.ddd',\n"
            + "	OFERTA1 VARCHAR2(2500) PATH '$.oferta1',\n"
            + "	OFERTA2 VARCHAR2(2500) PATH '$.oferta2',\n"
            + "	OFERTA3 VARCHAR2(2500) PATH '$.oferta3',\n"
            + "	OFERTA_ERRO_IN VARCHAR2(2500) PATH '$.oferta_erro_in',\n"
            + "	OFERTA_SIEBEL VARCHAR2(2500) PATH '$.oferta_siebel',\n"
            + "	SEGPROMPT VARCHAR2(2500) PATH '$.segprompt',\n"
            + "	DT_GMUD VARCHAR2(2500) PATH '$.dt_gmud'\n"
            + ")))";

    public static final String UPDATE
            = "UPDATE FRASE_PRE SET \n"
            + "	COD_OFERTA = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT COD_OFERTA as coluna,jsonEdit,json FROM FRASE_PRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.COD_OFERTA',json NUMBER(38,0) PATH '$.cod_oferta')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	DDD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DDD as coluna,jsonEdit,json FROM FRASE_PRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DDD',json VARCHAR2(2500) PATH '$.ddd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	OFERTA1 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTA1 as coluna,jsonEdit,json FROM FRASE_PRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTA1',json VARCHAR2(2500) PATH '$.oferta1')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	OFERTA2 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTA2 as coluna,jsonEdit,json FROM FRASE_PRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTA2',json VARCHAR2(2500) PATH '$.oferta2')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	OFERTA3 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTA3 as coluna,jsonEdit,json FROM FRASE_PRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTA3',json VARCHAR2(2500) PATH '$.oferta3')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	OFERTA_ERRO_IN = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTA_ERRO_IN as coluna,jsonEdit,json FROM FRASE_PRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTA_ERRO_IN',json VARCHAR2(2500) PATH '$.oferta_erro_in')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	OFERTA_SIEBEL = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTA_SIEBEL as coluna,jsonEdit,json FROM FRASE_PRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTA_SIEBEL',json VARCHAR2(2500) PATH '$.oferta_siebel')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	SEGPROMPT = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT SEGPROMPT as coluna,jsonEdit,json FROM FRASE_PRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.SEGPROMPT',json VARCHAR2(2500) PATH '$.segprompt')) where ID = ? order by rownum asc) where rownum = 1)\n"
            + ", DT_GMUD = ? WHERE ID = ?";

    public static final String UPDATEINSERT
            = "INSERT INTO FRASE_PRE (\n"
            + "	COD_OFERTA,\n"
            + "	DDD,\n"
            + "	OFERTA1,\n"
            + "	OFERTA2,\n"
            + "	OFERTA3,\n"
            + "	OFERTA_ERRO_IN,\n"
            + "	OFERTA_SIEBEL,\n"
            + "	SEGPROMPT,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	COD_OFERTA,\n"
            + "	DDD,\n"
            + "	OFERTA1,\n"
            + "	OFERTA2,\n"
            + "	OFERTA3,\n"
            + "	OFERTA_ERRO_IN,\n"
            + "	OFERTA_SIEBEL,\n"
            + "	SEGPROMPT,\n"
            + "	? HISTORY,\n"
            + "	? DT_GMUD,\n"
            + "	ID\n"
            + "FROM FRASE_PRE WHERE ID = ?)";

    public static final String DELETE
            = "INSERT INTO FRASE_PRE (\n"
            + "	COD_OFERTA,\n"
            + "	DDD,\n"
            + "	OFERTA1,\n"
            + "	OFERTA2,\n"
            + "	OFERTA3,\n"
            + "	OFERTA_ERRO_IN,\n"
            + "	OFERTA_SIEBEL,\n"
            + "	SEGPROMPT,\n"
            + "	HISTORY,\n"
            + "	DELETED,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	COD_OFERTA,\n"
            + "	DDD,\n"
            + "	OFERTA1,\n"
            + "	OFERTA2,\n"
            + "	OFERTA3,\n"
            + "	OFERTA_ERRO_IN,\n"
            + "	OFERTA_SIEBEL,\n"
            + "	SEGPROMPT,\n"
            + "	?,\n"
            + "	1,\n"
            + "	?, \n"
            + "	ID \n"
            + "FROM FRASE_PRE WHERE ID IN (?))";

    public static final String APROVAR
            = "t1.COD_OFERTA = t2.COD_OFERTA,\n"
            + "t1.DDD = t2.DDD,\n"
            + "t1.OFERTA1 = t2.OFERTA1,\n"
            + "t1.OFERTA2 = t2.OFERTA2,\n"
            + "t1.OFERTA3 = t2.OFERTA3,\n"
            + "t1.OFERTA_ERRO_IN = t2.OFERTA_ERRO_IN,\n"
            + "t1.OFERTA_SIEBEL = t2.OFERTA_SIEBEL,\n"
            + "t1.SEGPROMPT = t2.SEGPROMPT,\n"
            + "t1.HISTORY = t2.HISTORY";

}
