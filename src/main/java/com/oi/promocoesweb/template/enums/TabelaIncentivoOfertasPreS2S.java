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
public class TabelaIncentivoOfertasPreS2S {

    public static final String SELECT
            = "SELECT JSON_QUERY(\n"
            + " concat(concat(concat(concat(\n"
            + " concat(concat('{\"ddd\":\"',DDD),'\",'),\n"
            + " concat(concat('\"recacumuladamesanteriorinicial\":\"',RECACUMULADAMESANTERIORINICIAL),'\",')),\n"
            + " concat(concat('\"recacumuladamesanteriorfinal\":\"',RECACUMULADAMESANTERIORFINAL),'\",')),\n"
            + " concat(concat('\"ofertapromo1\":\"',OFERTAPROMO1),'\",')),\n"
            + " concat(concat('\"ofertapromo2\":\"',OFERTAPROMO2),'\"}'))\n"
            + ",'$') as json,ID,TEMPID,HISTORY,INSERTED,DT_GMUD,DELETED FROM INCENTIVO_OFERTASPRE_S2S t WHERE (DT_GMUD IS NULL AND DELETED = 0) OR (DT_GMUD IS NOT NULL) ORDER BY INSERTED DESC NULLS LAST";

    public static final String INSERT
            = "INSERT INTO INCENTIVO_OFERTASPRE_S2S (\n"
            + "	DDD,\n" 
            + "	RECACUMULADAMESANTERIORINICIAL,\n"
            + "	RECACUMULADAMESANTERIORFINAL,\n"
            + "	OFERTAPROMO1,\n"
            + "	OFERTAPROMO2,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD\n"
            + ")(SELECT \n"
            + "	DDD,\n" 
            + "	RECACUMULADAMESANTERIORINICIAL,\n"
            + "	RECACUMULADAMESANTERIORFINAL,\n"
            + "	OFERTAPROMO1,\n"
            + "	OFERTAPROMO2,\n"
            + "	?,\n"
            + "   DT_GMUD\n"
            + "FROM dual, JSON_TABLE(?, '$' COLUMNS (\n"
            + "	DDD VARCHAR2(2500) PATH '$.ddd',\n"
            + "	RECACUMULADAMESANTERIORINICIAL VARCHAR2(2500) PATH '$.recacumuladamesanteriorinicial',\n"
            + "	RECACUMULADAMESANTERIORFINAL VARCHAR2(2500) PATH '$.recacumuladamesanteriorfinal',\n"
            + "	OFERTAPROMO1 VARCHAR2(2500) PATH '$.ofertapromo1',\n"
            + "	OFERTAPROMO2 VARCHAR2(2500) PATH '$.ofertapromo2',\n"
            + "	DT_GMUD VARCHAR2(2500) PATH '$.dt_gmud'\n"
            + ")))";

    public static final String UPDATE
            = "UPDATE INCENTIVO_OFERTASPRE_S2S SET \n"
            + "	DDD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DDD as coluna,jsonEdit,json FROM INCENTIVO_OFERTASPRE_S2S,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DDD',json VARCHAR2(2500) PATH '$.ddd')) where ID = ? order by rownum asc) where rownum = 1),\n" 
            + "	RECACUMULADAMESANTERIORINICIAL = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT RECACUMULADAMESANTERIORINICIAL as coluna,jsonEdit,json FROM INCENTIVO_OFERTASPRE_S2S,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.RECACUMULADAMESANTERIORINICIAL',json VARCHAR2(2500) PATH '$.recacumuladamesanteriorinicial')) where ID = ? order by rownum asc) where rownum = 1),\n" 
            + "	RECACUMULADAMESANTERIORFINAL = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT RECACUMULADAMESANTERIORFINAL as coluna,jsonEdit,json FROM INCENTIVO_OFERTASPRE_S2S,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.RECACUMULADAMESANTERIORFINAL',json VARCHAR2(2500) PATH '$.recacumuladamesanteriorfinal')) where ID = ? order by rownum asc) where rownum = 1),\n" 
            + "	OFERTAPROMO1 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTAPROMO1 as coluna,jsonEdit,json FROM INCENTIVO_OFERTASPRE_S2S,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTAPROMO1',json VARCHAR2(2500) PATH '$.ofertapromo1')) where ID = ? order by rownum asc) where rownum = 1),\n" 
            + "	OFERTAPROMO2 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTAPROMO2 as coluna,jsonEdit,json FROM INCENTIVO_OFERTASPRE_S2S,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTAPROMO2',json VARCHAR2(2500) PATH '$.ofertapromo2')) where ID = ? order by rownum asc) where rownum = 1)\n"
            + ", DT_GMUD = ? WHERE ID = ?";

    public static final String UPDATEINSERT
            = "INSERT INTO INCENTIVO_OFERTASPRE_S2S (\n"
            + "	DDD,\n" 
            + "	RECACUMULADAMESANTERIORINICIAL,\n"
            + "	RECACUMULADAMESANTERIORFINAL,\n"
            + "	OFERTAPROMO1,\n"
            + "	OFERTAPROMO2,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	DDD,\n"
            + "	RECACUMULADAMESANTERIORINICIAL,\n"
            + "	RECACUMULADAMESANTERIORFINAL,\n"
            + "	OFERTAPROMO1,\n"
            + "	OFERTAPROMO2,\n"
            + "	? HISTORY,\n"
            + "	? DT_GMUD,\n"
            + "	ID\n"
            + "FROM INCENTIVO_OFERTASPRE_S2S WHERE ID = ?)";

    public static final String DELETE
            = "INSERT INTO INCENTIVO_OFERTASPRE_S2S (\n"
            + "	DDD,\n"
            + "	RECACUMULADAMESANTERIORINICIAL,\n"
            + "	RECACUMULADAMESANTERIORFINAL,\n"
            + "	OFERTAPROMO1,\n"
            + "	OFERTAPROMO2,\n"
            + "	HISTORY,\n"
            + "	DELETED,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	DDD,\n"
            + "	RECACUMULADAMESANTERIORINICIAL,\n"
            + "	RECACUMULADAMESANTERIORFINAL,\n"
            + "	OFERTAPROMO1,\n"
            + "	OFERTAPROMO2,\n"
            + "	?,\n"
            + "	1,\n"
            + "	?, \n"
            + "	ID \n"
            + "FROM INCENTIVO_OFERTASPRE_S2S WHERE ID IN (?))";

    public static final String APROVAR
            = "t1.DDD = t2.DDD,\n"
            + "t1.RECACUMULADAMESANTERIORINICIAL = t2.RECACUMULADAMESANTERIORINICIAL,\n"
            + "t1.RECACUMULADAMESANTERIORFINAL = t2.RECACUMULADAMESANTERIORFINAL,\n"
            + "t1.OFERTAPROMO1 = t2.OFERTAPROMO1,\n"
            + "t1.OFERTAPROMO2 = t2.OFERTAPROMO2,\n"
            + "t1.HISTORY = t2.HISTORY";

}
