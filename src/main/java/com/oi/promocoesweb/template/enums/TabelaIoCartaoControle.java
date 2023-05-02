/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.enums;

/**
 *
 * @author jpereirc
 */
public class TabelaIoCartaoControle {

    public static final String SELECT
            = "SELECT JSON_QUERY(\n"
            + " concat(concat(concat(concat(concat(concat(concat(concat(\n"
            + " concat(concat('{\"ddd\":\"',DDD),'\",'),\n"
            + " concat(concat('\"tb_incentivo1\":\"',TB_INCENTIVO1),'\",')),\n"
            + " concat(concat('\"tb_incentivo2\":\"',TB_INCENTIVO2),'\",')),\n"
            + " concat(concat('\"tb_incentivo3\":\"',TB_INCENTIVO3),'\",')),\n"
            + " concat(concat('\"tb_incentivo4\":\"',TB_INCENTIVO4),'\",')),\n"
            + " concat(concat('\"tb_incentivo5\":\"',TB_INCENTIVO5),'\",')),\n"
            + " concat(concat('\"recacumuladamesanteriorinicial\":\"',RECACUMULADAMESANTERIORINICIAL),'\",')),\n"
            + " concat(concat('\"recacumuladamesanteriorfinal\":\"',RECACUMULADAMESANTERIORFINAL),'\",')),\n"
            + " concat(concat('\"tipopagamentoincentivo\":\"',TIPOPAGAMENTOINCENTIVO),'\"}'))\n"
            + ",'$') as json,ID,TEMPID,HISTORY,INSERTED,DT_GMUD,DELETED FROM IO_CARTAO_CONTROLE t WHERE (DT_GMUD IS NULL AND DELETED = 0) OR (DT_GMUD IS NOT NULL) ORDER BY INSERTED DESC NULLS LAST";

    public static final String INSERT
            = "INSERT INTO IO_CARTAO_CONTROLE (\n"
            + " DDD,\n"
            + " TB_INCENTIVO1,\n"
            + " TB_INCENTIVO2,\n"
            + " TB_INCENTIVO3,\n"
            + " TB_INCENTIVO4,\n"
            + " TB_INCENTIVO5,\n"
            + " RECACUMULADAMESANTERIORINICIAL,\n"
            + " RECACUMULADAMESANTERIORFINAL,\n"
            + " TIPOPAGAMENTOINCENTIVO,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD\n"
            + ")(SELECT \n"
            + " DDD,\n"
            + " TB_INCENTIVO1,\n"
            + " TB_INCENTIVO2,\n"
            + " TB_INCENTIVO3,\n"
            + " TB_INCENTIVO4,\n"
            + " TB_INCENTIVO5,\n"
            + " RECACUMULADAMESANTERIORINICIAL,\n"
            + " RECACUMULADAMESANTERIORFINAL,\n"
            + " TIPOPAGAMENTOINCENTIVO,\n"
            + "	?,\n"
            + "   DT_GMUD\n"
            + "FROM dual, JSON_TABLE(?, '$' COLUMNS (\n"
            + " DDD VARCHAR2(2500) PATH '$.ddd',\n"
            + " TB_INCENTIVO1 VARCHAR2(2500) PATH '$.tb_incentivo1',\n"
            + " TB_INCENTIVO2 VARCHAR2(2500) PATH '$.tb_incentivo2',\n"
            + " TB_INCENTIVO3 VARCHAR2(2500) PATH '$.tb_incentivo3',\n"
            + " TB_INCENTIVO4 VARCHAR2(2500) PATH '$.tb_incentivo4',\n"
            + " TB_INCENTIVO5 VARCHAR2(2500) PATH '$.tb_incentivo5',\n"
            + " RECACUMULADAMESANTERIORINICIAL VARCHAR2(2500) PATH '$.recacumuladamesanteriorinicial',\n"
            + " RECACUMULADAMESANTERIORFINAL VARCHAR2(2500) PATH '$.recacumuladamesanteriorfinal',\n"
            + " TIPOPAGAMENTOINCENTIVO VARCHAR2(2500) PATH '$.tipopagamentoincentivo',\n"
            + "	DT_GMUD VARCHAR2(2500) PATH '$.dt_gmud'\n"
            + ")))";

    public static final String UPDATE
            = "UPDATE IO_CARTAO_CONTROLE SET \n"
            + " DDD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DDD as coluna,jsonEdit,json FROM IO_CARTAO_CONTROLE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DDD',json VARCHAR2(2500) PATH '$.ddd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_INCENTIVO1 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_INCENTIVO1 as coluna,jsonEdit,json FROM IO_CARTAO_CONTROLE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_INCENTIVO1',json VARCHAR2(2500) PATH '$.tb_incentivo1')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_INCENTIVO2 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_INCENTIVO2 as coluna,jsonEdit,json FROM IO_CARTAO_CONTROLE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_INCENTIVO2',json VARCHAR2(2500) PATH '$.tb_incentivo2')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_INCENTIVO3 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_INCENTIVO3 as coluna,jsonEdit,json FROM IO_CARTAO_CONTROLE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_INCENTIVO3',json VARCHAR2(2500) PATH '$.tb_incentivo3')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_INCENTIVO4 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_INCENTIVO4 as coluna,jsonEdit,json FROM IO_CARTAO_CONTROLE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_INCENTIVO4',json VARCHAR2(2500) PATH '$.tb_incentivo4')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_INCENTIVO5 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_INCENTIVO5 as coluna,jsonEdit,json FROM IO_CARTAO_CONTROLE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_INCENTIVO5',json VARCHAR2(2500) PATH '$.tb_incentivo5')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " RECACUMULADAMESANTERIORINICIAL = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT RECACUMULADAMESANTERIORINICIAL as coluna,jsonEdit,json FROM IO_CARTAO_CONTROLE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.RECACUMULADAMESANTERIORINICIAL',json VARCHAR2(2500) PATH '$.recacumuladamesanteriorinicial')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " RECACUMULADAMESANTERIORFINAL = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT RECACUMULADAMESANTERIORFINAL as coluna,jsonEdit,json FROM IO_CARTAO_CONTROLE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.RECACUMULADAMESANTERIORFINAL',json VARCHAR2(2500) PATH '$.recacumuladamesanteriorfinal')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TIPOPAGAMENTOINCENTIVO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TIPOPAGAMENTOINCENTIVO as coluna,jsonEdit,json FROM IO_CARTAO_CONTROLE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TIPOPAGAMENTOINCENTIVO',json VARCHAR2(2500) PATH '$.tipopagamentoincentivo')) where ID = ? order by rownum asc) where rownum = 1)\n"
            + ", DT_GMUD = ? WHERE ID = ?";

    public static final String UPDATEINSERT
            = "INSERT INTO IO_CARTAO_CONTROLE (\n"
            + " DDD,\n"
            + " TB_INCENTIVO1,\n"
            + " TB_INCENTIVO2,\n"
            + " TB_INCENTIVO3,\n"
            + " TB_INCENTIVO4,\n"
            + " TB_INCENTIVO5,\n"
            + " RECACUMULADAMESANTERIORINICIAL,\n"
            + " RECACUMULADAMESANTERIORFINAL,\n"
            + " TIPOPAGAMENTOINCENTIVO,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + " DDD,\n"
            + " TB_INCENTIVO1,\n"
            + " TB_INCENTIVO2,\n"
            + " TB_INCENTIVO3,\n"
            + " TB_INCENTIVO4,\n"
            + " TB_INCENTIVO5,\n"
            + " RECACUMULADAMESANTERIORINICIAL,\n"
            + " RECACUMULADAMESANTERIORFINAL,\n"
            + " TIPOPAGAMENTOINCENTIVO,\n"
            + "	? HISTORY,\n"
            + "	? DT_GMUD,\n"
            + "	ID\n"
            + "FROM IO_CARTAO_CONTROLE WHERE ID = ?)";

    public static final String DELETE
            = "INSERT INTO IO_CARTAO_CONTROLE (\n"
            + " DDD,\n"
            + " TB_INCENTIVO1,\n"
            + " TB_INCENTIVO2,\n"
            + " TB_INCENTIVO3,\n"
            + " TB_INCENTIVO4,\n"
            + " TB_INCENTIVO5,\n"
            + " RECACUMULADAMESANTERIORINICIAL,\n"
            + " RECACUMULADAMESANTERIORFINAL,\n"
            + " TIPOPAGAMENTOINCENTIVO,\n"
            + "	HISTORY,\n"
            + "	DELETED,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + " DDD,\n"
            + " TB_INCENTIVO1,\n"
            + " TB_INCENTIVO2,\n"
            + " TB_INCENTIVO3,\n"
            + " TB_INCENTIVO4,\n"
            + " TB_INCENTIVO5,\n"
            + " RECACUMULADAMESANTERIORINICIAL,\n"
            + " RECACUMULADAMESANTERIORFINAL,\n"
            + " TIPOPAGAMENTOINCENTIVO,\n"
            + "	?,\n"
            + "	1,\n"
            + "	?, \n"
            + "	ID \n"
            + "FROM IO_CARTAO_CONTROLE WHERE ID IN (?))";

    public static final String APROVAR
            = " t1.DDD= t2.DDD,\n"
            + " t1.TB_INCENTIVO1= t2.TB_INCENTIVO1,\n"
            + " t1.TB_INCENTIVO2= t2.TB_INCENTIVO2,\n"
            + " t1.TB_INCENTIVO3= t2.TB_INCENTIVO3,\n"
            + " t1.TB_INCENTIVO4= t2.TB_INCENTIVO4,\n"
            + " t1.TB_INCENTIVO5= t2.TB_INCENTIVO5,\n"
            + " t1.RECACUMULADAMESANTERIORINICIAL= t2.RECACUMULADAMESANTERIORINICIAL,\n"
            + " t1.RECACUMULADAMESANTERIORFINAL= t2.RECACUMULADAMESANTERIORFINAL,\n"
            + " t1.TIPOPAGAMENTOINCENTIVO= t2.TIPOPAGAMENTOINCENTIVO,\n"
            + "t1.HISTORY = t2.HISTORY";

}
