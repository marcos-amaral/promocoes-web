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
public class TabelaIoControleControleS2S {

    public static final String SELECT
            = "SELECT JSON_QUERY(\n"
            + " concat(concat(concat(concat(\n"
            + " concat(concat('{\"oferta\":\"',OFERTA),'\",'),\n"
            + " concat(concat('\"tb_incentivo1\":\"',TB_INCENTIVO1),'\",')),\n"
            + " concat(concat('\"tb_incentivo2\":\"',TB_INCENTIVO2),'\",')),\n"
            + " concat(concat('\"tb_incentivo3\":\"',TB_INCENTIVO3),'\",')),\n"
            + " concat(concat('\"tb_incentivo4\":\"',TB_INCENTIVO4),'\"}'))\n"
            + ",'$') as json,ID,TEMPID,HISTORY,INSERTED,DT_GMUD,DELETED FROM IO_CONTROLE_CONTROLE_S2S t WHERE (DT_GMUD IS NULL AND DELETED = 0) OR (DT_GMUD IS NOT NULL) ORDER BY INSERTED DESC NULLS LAST";

    public static final String INSERT
            = "INSERT INTO IO_CONTROLE_CONTROLE_S2S (\n"
            + " OFERTA,\n"
            + " TB_INCENTIVO1,\n"
            + " TB_INCENTIVO2,\n"
            + " TB_INCENTIVO3,\n"
            + " TB_INCENTIVO4,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD\n"
            + ")(SELECT \n"
            + " OFERTA,\n"
            + " TB_INCENTIVO1,\n"
            + " TB_INCENTIVO2,\n"
            + " TB_INCENTIVO3,\n"
            + " TB_INCENTIVO4,\n"
            + "	?,\n"
            + "   DT_GMUD\n"
            + "FROM dual, JSON_TABLE(?, '$' COLUMNS (\n"
            + " OFERTA VARCHAR2(2500) PATH '$.oferta', \n"
            + " TB_INCENTIVO1 VARCHAR2(2500) PATH '$.tb_incentivo1', \n"
            + " TB_INCENTIVO2 VARCHAR2(2500) PATH '$.tb_incentivo2', \n"
            + " TB_INCENTIVO3 VARCHAR2(2500) PATH '$.tb_incentivo3', \n"
            + " TB_INCENTIVO4 VARCHAR2(2500) PATH '$.tb_incentivo4', \n"
            + "	DT_GMUD VARCHAR2(2500) PATH '$.dt_gmud' \n"
            + ")))";

    public static final String UPDATE
            = "UPDATE IO_CONTROLE_CONTROLE_S2S SET \n"
            + " OFERTA = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTA as coluna,jsonEdit,json FROM IO_CONTROLE_CONTROLE_S2S,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTA',json VARCHAR2(2500) PATH '$.oferta')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_INCENTIVO1 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_INCENTIVO1 as coluna,jsonEdit,json FROM IO_CONTROLE_CONTROLE_S2S,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_INCENTIVO1',json VARCHAR2(2500) PATH '$.tb_incentivo1')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_INCENTIVO2 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_INCENTIVO2 as coluna,jsonEdit,json FROM IO_CONTROLE_CONTROLE_S2S,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_INCENTIVO2',json VARCHAR2(2500) PATH '$.tb_incentivo2')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_INCENTIVO3 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_INCENTIVO3 as coluna,jsonEdit,json FROM IO_CONTROLE_CONTROLE_S2S,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_INCENTIVO3',json VARCHAR2(2500) PATH '$.tb_incentivo3')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_INCENTIVO4 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_INCENTIVO4 as coluna,jsonEdit,json FROM IO_CONTROLE_CONTROLE_S2S,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_INCENTIVO4',json VARCHAR2(2500) PATH '$.tb_incentivo4')) where ID = ? order by rownum asc) where rownum = 1)\n"
            + ", DT_GMUD = ? WHERE ID = ?";

    public static final String UPDATEINSERT
            = "INSERT INTO IO_CONTROLE_CONTROLE_S2S (\n"
            + " OFERTA,\n"
            + " TB_INCENTIVO1,\n"
            + " TB_INCENTIVO2,\n"
            + " TB_INCENTIVO3,\n"
            + " TB_INCENTIVO4,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + " OFERTA,\n"
            + " TB_INCENTIVO1,\n"
            + " TB_INCENTIVO2,\n"
            + " TB_INCENTIVO3,\n"
            + " TB_INCENTIVO4,\n"
            + "	? HISTORY,\n"
            + "	? DT_GMUD,\n"
            + "	ID\n"
            + "FROM IO_CONTROLE_CONTROLE_S2S WHERE ID = ?)";

    public static final String DELETE
            = "INSERT INTO IO_CONTROLE_CONTROLE_S2S (\n"
            + " OFERTA,\n"
            + " TB_INCENTIVO1,\n"
            + " TB_INCENTIVO2,\n"
            + " TB_INCENTIVO3,\n"
            + " TB_INCENTIVO4,\n"
            + "	HISTORY,\n"
            + "	DELETED,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + " OFERTA,\n"
            + " TB_INCENTIVO1,\n"
            + " TB_INCENTIVO2,\n"
            + " TB_INCENTIVO3,\n"
            + " TB_INCENTIVO4,\n"
            + "	?,\n"
            + "	1,\n"
            + "	?, \n"
            + "	ID \n"
            + "FROM IO_CONTROLE_CONTROLE_S2S WHERE ID IN (?))";

    public static final String APROVAR
            = "t1.OFERTA = t2.OFERTA,\n"
            + "t1.TB_INCENTIVO1 = t2.TB_INCENTIVO1,\n"
            + "t1.TB_INCENTIVO2 = t2.TB_INCENTIVO2,\n"
            + "t1.TB_INCENTIVO3 = t2.TB_INCENTIVO3,\n"
            + "t1.TB_INCENTIVO4 = t2.TB_INCENTIVO4,\n"
            + "t1.HISTORY = t2.HISTORY";

}
