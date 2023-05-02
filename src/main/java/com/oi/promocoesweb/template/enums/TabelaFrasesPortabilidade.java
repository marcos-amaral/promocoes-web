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
public class TabelaFrasesPortabilidade {

    public static final String SELECT
            = "SELECT JSON_QUERY(\n"       
            + " concat(concat(concat(concat(concat(\n"
            + " concat(concat('{\"idbeneficio\":\"',IDBENEFICIO),'\",'),\n"
            + " concat(concat('\"idprograma\":\"',IDPROGRAMA),'\",')),\n"
            + " concat(concat('\"ddd\":\"',DDD),'\",')),\n"
            + " concat(concat('\"informapromo\":\"',INFORMAPROMO),'\",')),\n"
            + " concat(concat('\"incentivo_portabilidade\":\"',INCENTIVO_PORTABILIDADE),'\",')),\n"
            + " concat(concat('\"naocliente_sva\":\"',NAOCLIENTE_SVA),'\"}'))\n"
            + ",'$') as json,ID,TEMPID,HISTORY,INSERTED,DT_GMUD,DELETED FROM FRASES_PORTABILIDADE t WHERE (DT_GMUD IS NULL AND DELETED = 0) OR (DT_GMUD IS NOT NULL) ORDER BY INSERTED DESC NULLS LAST";

    public static final String INSERT
            = "INSERT INTO FRASES_PORTABILIDADE (\n"
            + "	DDD,\n"
            + "	IDBENEFICIO,\n" 
            + "	IDPROGRAMA,\n"
            + "	INFORMAPROMO,\n"
            + "	INCENTIVO_PORTABILIDADE,\n"
            + "	NAOCLIENTE_SVA,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD\n"
            + ")(SELECT \n"
            + "	DDD,\n"
            + "	IDBENEFICIO,\n" 
            + "	IDPROGRAMA,\n"
            + "	INFORMAPROMO,\n"
            + "	INCENTIVO_PORTABILIDADE,\n"
            + "	NAOCLIENTE_SVA,\n"
            + "	?,\n"
            + "   DT_GMUD\n"
            + "FROM dual, JSON_TABLE(?, '$' COLUMNS (\n"
            + "	DDD VARCHAR2(2500) PATH '$.ddd',\n"
            + "	IDBENEFICIO VARCHAR2(2500) PATH '$.idbeneficio',\n"
            + "	IDPROGRAMA VARCHAR2(2500) PATH '$.idprograma',\n"
            + "	INFORMAPROMO VARCHAR2(2500) PATH '$.informapromo',\n"
            + "	INCENTIVO_PORTABILIDADE VARCHAR2(2500) PATH '$.incentivo_portabilidade',\n"
            + "	NAOCLIENTE_SVA VARCHAR2(2500) PATH '$.naocliente_sva',\n"
            + "	DT_GMUD VARCHAR2(2500) PATH '$.dt_gmud'\n"
            + ")))";

    public static final String UPDATE
            = "UPDATE FRASES_PORTABILIDADE SET \n"
            + "	DDD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DDD as coluna,jsonEdit,json FROM FRASES_PORTABILIDADE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DDD',json VARCHAR2(2500) PATH '$.ddd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	IDBENEFICIO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT IDBENEFICIO as coluna,jsonEdit,json FROM FRASES_PORTABILIDADE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.IDBENEFICIO',json NUMBER(38,0) PATH '$.idbeneficio')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	IDPROGRAMA = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT IDPROGRAMA as coluna,jsonEdit,json FROM FRASES_PORTABILIDADE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.IDPROGRAMA',json NUMBER(38,0) PATH '$.idprograma')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	INFORMAPROMO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT INFORMAPROMO as coluna,jsonEdit,json FROM FRASES_PORTABILIDADE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.INFORMAPROMO',json VARCHAR2(2500) PATH '$.informapromo')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	INCENTIVO_PORTABILIDADE = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT INCENTIVO_PORTABILIDADE as coluna,jsonEdit,json FROM FRASES_PORTABILIDADE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.INCENTIVO_PORTABILIDADE',json VARCHAR2(2500) PATH '$.incentivo_portabilidade')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	NAOCLIENTE_SVA = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT NAOCLIENTE_SVA as coluna,jsonEdit,json FROM FRASES_PORTABILIDADE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.NAOCLIENTE_SVA',json VARCHAR2(2500) PATH '$.naocliente_sva')) where ID = ? order by rownum asc) where rownum = 1)\n"
            + ", DT_GMUD = ? WHERE ID = ?";

    public static final String UPDATEINSERT
            = "INSERT INTO FRASES_PORTABILIDADE (\n"
            + "	DDD,\n"
            + "	IDBENEFICIO,\n" 
            + "	IDPROGRAMA,\n"
            + "	INFORMAPROMO,\n"
            + "	INCENTIVO_PORTABILIDADE,\n"
            + "	NAOCLIENTE_SVA,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	DDD,\n"
            + "	IDBENEFICIO,\n" 
            + "	IDPROGRAMA,\n"
            + "	INFORMAPROMO,\n"
            + "	INCENTIVO_PORTABILIDADE,\n"
            + "	NAOCLIENTE_SVA,\n"
            + "	? HISTORY,\n"
            + "	? DT_GMUD,\n"
            + "	ID\n"
            + "FROM FRASES_PORTABILIDADE WHERE ID = ?)";

    public static final String DELETE
            = "INSERT INTO FRASES_PORTABILIDADE (\n"
            + "	DDD,\n"
            + "	IDBENEFICIO,\n" 
            + "	IDPROGRAMA,\n"
            + "	INFORMAPROMO,\n"
            + "	INCENTIVO_PORTABILIDADE,\n"
            + "	NAOCLIENTE_SVA,\n"
            + "	HISTORY,\n"
            + "	DELETED,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	DDD,\n"
            + "	IDBENEFICIO,\n" 
            + "	IDPROGRAMA,\n"
            + "	INFORMAPROMO,\n"
            + "	INCENTIVO_PORTABILIDADE,\n"
            + "	NAOCLIENTE_SVA,\n"
            + "	?,\n"
            + "	1,\n"
            + "	?, \n"
            + "	ID \n"
            + "FROM FRASES_PORTABILIDADE WHERE ID IN (?))";

    public static final String APROVAR
            = "t1.DDD = t2.DDD,\n"
            + "t1.IDBENEFICIO = t2.IDBENEFICIO,\n"
            + "t1.IDPROGRAMA = t2.IDPROGRAMA,\n"
            + "t1.INFORMAPROMO = t2.INFORMAPROMO,\n"
            + "t1.INCENTIVO_PORTABILIDADE = t2.INCENTIVO_PORTABILIDADE,\n"
            + "t1.NAOCLIENTE_SVA = t2.NAOCLIENTE_SVA,\n"
            + "t1.HISTORY = t2.HISTORY";

}
