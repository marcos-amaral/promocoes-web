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
public class TabelaIncentivoOfertasPre {

    public static final String SELECT
            = "SELECT JSON_QUERY(\n"
            + " concat(concat(concat(concat(\n"
            + " concat(concat('{\"ddd\":\"',DDD),'\",'),\n"
            + " concat(concat('\"recacumuladainicial\":\"',RECACUMULADAINICIAL),'\",')),\n"
            + " concat(concat('\"recacumuladafinal\":\"',RECACUMULADAFINAL),'\",')),\n"
            + " concat(concat('\"ofertapromo1_id_ofertaocs\":\"',OFERTAPROMO1_ID_OFERTAOCS),'\",')),\n"
            + " concat(concat('\"ofertapromo2_id_ofertaocs\":\"',OFERTAPROMO2_ID_OFERTAOCS),'\"}'))\n"
            + ",'$') as json,ID,TEMPID,HISTORY,INSERTED,DT_GMUD,DELETED FROM INCENTIVO_OFERTASPRE t WHERE (DT_GMUD IS NULL AND DELETED = 0) OR (DT_GMUD IS NOT NULL) ORDER BY INSERTED DESC NULLS LAST";

    public static final String INSERT
            = "INSERT INTO INCENTIVO_OFERTASPRE (\n"
            + "	DDD,\n" +
            "	RECACUMULADAINICIAL,\n" +
            "	RECACUMULADAFINAL,\n" +
            "	OFERTAPROMO1_ID_OFERTAOCS,\n" +
            "	OFERTAPROMO2_ID_OFERTAOCS,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD\n"
            + ")(SELECT \n"
            + "	DDD,\n" +
            "	RECACUMULADAINICIAL,\n" +
            "	RECACUMULADAFINAL,\n" +
            "	OFERTAPROMO1_ID_OFERTAOCS,\n" +
            "	OFERTAPROMO2_ID_OFERTAOCS,\n"
            + "	?,\n"
            + "   DT_GMUD\n"
            + "FROM dual, JSON_TABLE(?, '$' COLUMNS (\n"
            + "	DDD VARCHAR2(2500) PATH '$.ddd',\n" +
            "	RECACUMULADAINICIAL VARCHAR2(2500) PATH '$.recacumuladainicial',\n" +
            "	RECACUMULADAFINAL VARCHAR2(2500) PATH '$.recacumuladafinal',\n" +
            "	OFERTAPROMO1_ID_OFERTAOCS VARCHAR2(2500) PATH '$.ofertapromo1_id_ofertaocs',\n" +
            "	OFERTAPROMO2_ID_OFERTAOCS VARCHAR2(2500) PATH '$.ofertapromo2_id_ofertaocs',\n"
            + "	DT_GMUD VARCHAR2(2500) PATH '$.dt_gmud'\n"
            + ")))";

    public static final String UPDATE
            = "UPDATE INCENTIVO_OFERTASPRE SET \n"
            + "	DDD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DDD as coluna,jsonEdit,json FROM INCENTIVO_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DDD',json VARCHAR2(2500) PATH '$.ddd')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	RECACUMULADAINICIAL = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT RECACUMULADAINICIAL as coluna,jsonEdit,json FROM INCENTIVO_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.RECACUMULADAINICIAL',json VARCHAR2(2500) PATH '$.recacumuladainicial')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	RECACUMULADAFINAL = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT RECACUMULADAFINAL as coluna,jsonEdit,json FROM INCENTIVO_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.RECACUMULADAFINAL',json VARCHAR2(2500) PATH '$.recacumuladafinal')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	OFERTAPROMO1_ID_OFERTAOCS = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTAPROMO1_ID_OFERTAOCS as coluna,jsonEdit,json FROM INCENTIVO_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTAPROMO1_ID_OFERTAOCS',json VARCHAR2(2500) PATH '$.ofertapromo1_id_ofertaocs')) where ID = ? order by rownum asc) where rownum = 1),\n" +
            "	OFERTAPROMO2_ID_OFERTAOCS = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTAPROMO2_ID_OFERTAOCS as coluna,jsonEdit,json FROM INCENTIVO_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTAPROMO2_ID_OFERTAOCS',json VARCHAR2(2500) PATH '$.ofertapromo2_id_ofertaocs')) where ID = ? order by rownum asc) where rownum = 1)\n"
            + ", DT_GMUD = ? WHERE ID = ?";

    public static final String UPDATEINSERT
            = "INSERT INTO INCENTIVO_OFERTASPRE (\n"
            + "	DDD,\n" +
            "	RECACUMULADAINICIAL,\n" +
            "	RECACUMULADAFINAL,\n" +
            "	OFERTAPROMO1_ID_OFERTAOCS,\n" +
            "	OFERTAPROMO2_ID_OFERTAOCS,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	DDD,\n" +
            "	RECACUMULADAINICIAL,\n" +
            "	RECACUMULADAFINAL,\n" +
            "	OFERTAPROMO1_ID_OFERTAOCS,\n" +
            "	OFERTAPROMO2_ID_OFERTAOCS,\n"
            + "	? HISTORY,\n"
            + "	? DT_GMUD,\n"
            + "	ID\n"
            + "FROM INCENTIVO_OFERTASPRE WHERE ID = ?)";

    public static final String DELETE
            = "INSERT INTO INCENTIVO_OFERTASPRE (\n"
            + "	DDD,\n" +
            "	RECACUMULADAINICIAL,\n" +
            "	RECACUMULADAFINAL,\n" +
            "	OFERTAPROMO1_ID_OFERTAOCS,\n" +
            "	OFERTAPROMO2_ID_OFERTAOCS,\n"
            + "	HISTORY,\n"
            + "	DELETED,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	DDD,\n" +
            "	RECACUMULADAINICIAL,\n" +
            "	RECACUMULADAFINAL,\n" +
            "	OFERTAPROMO1_ID_OFERTAOCS,\n" +
            "	OFERTAPROMO2_ID_OFERTAOCS,\n"
            + "	?,\n"
            + "	1,\n"
            + "	?, \n"
            + "	ID \n"
            + "FROM INCENTIVO_OFERTASPRE WHERE ID IN (?))";

    public static final String APROVAR
            = "t1.DDD = t2.DDD,\n" +
            "t1.RECACUMULADAINICIAL = t2.RECACUMULADAINICIAL,\n" +
            "t1.RECACUMULADAFINAL = t2.RECACUMULADAFINAL,\n" +
            "t1.OFERTAPROMO1_ID_OFERTAOCS = t2.OFERTAPROMO1_ID_OFERTAOCS,\n" +
            "t1.OFERTAPROMO2_ID_OFERTAOCS = t2.OFERTAPROMO2_ID_OFERTAOCS,\n"
            + "t1.HISTORY = t2.HISTORY";

}
