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
public class TabelaIoCartaoCartao {

    public static final String SELECT
            = "SELECT JSON_QUERY(\n"
            + " concat(concat(concat(concat(\n"
            + " concat(concat('{\"ddd\":\"',DDD),'\",'),\n"
            + " concat(concat('\"recacumuladainicial\":\"',RECACUMULADAINICIAL),'\",')),\n"
            + " concat(concat('\"recacumuladafinal\":\"',RECACUMULADAFINAL),'\",')),\n"
            + " concat(concat('\"ofertapromo1\":\"',OFERTAPROMO1),'\",')),\n"
            + " concat(concat('\"ofertapromo2\":\"',OFERTAPROMO2),'\"}'))\n"
            + ",'$') as json,ID,TEMPID,HISTORY,INSERTED,DT_GMUD,DELETED FROM IO_CARTAO_CARTAO t WHERE (DT_GMUD IS NULL AND DELETED = 0) OR (DT_GMUD IS NOT NULL) ORDER BY INSERTED DESC NULLS LAST";

    public static final String INSERT
            = "INSERT INTO IO_CARTAO_CARTAO (\n"
            + "	DDD,\n"
            + "	RECACUMULADAINICIAL,\n"
            + "	RECACUMULADAFINAL,\n"
            + "	OFERTAPROMO1,\n"
            + "	OFERTAPROMO2,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD\n"
            + ")(SELECT \n"
            + "	DDD,\n"
            + "	RECACUMULADAINICIAL,\n"
            + "	RECACUMULADAFINAL,\n"
            + "	OFERTAPROMO1,\n"
            + "	OFERTAPROMO2,\n"
            + "	?,\n"
            + "   DT_GMUD\n"
            + "FROM dual, JSON_TABLE(?, '$' COLUMNS (\n"
            + "	DDD VARCHAR2(2500) PATH '$.ddd',\n"
            + "	RECACUMULADAINICIAL VARCHAR2(2500) PATH '$.recacumuladainicial',\n"
            + "	RECACUMULADAFINAL VARCHAR2(2500) PATH '$.recacumuladafinal',\n"
            + "	OFERTAPROMO1 VARCHAR2(2500) PATH '$.ofertapromo1',\n"
            + "	OFERTAPROMO2 VARCHAR2(2500) PATH '$.ofertapromo2',\n"
            + "	DT_GMUD VARCHAR2(2500) PATH '$.dt_gmud'\n"
            + ")))";

    public static final String UPDATE
            = "UPDATE IO_CARTAO_CARTAO SET \n"
            + "	DDD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DDD as coluna,jsonEdit,json FROM IO_CARTAO_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DDD',json VARCHAR2(2500) PATH '$.ddd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	RECACUMULADAINICIAL = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT RECACUMULADAINICIAL as coluna,jsonEdit,json FROM IO_CARTAO_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.RECACUMULADAINICIAL',json VARCHAR2(2500) PATH '$.recacumuladainicial')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	RECACUMULADAFINAL = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT RECACUMULADAFINAL as coluna,jsonEdit,json FROM IO_CARTAO_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.RECACUMULADAFINAL',json VARCHAR2(2500) PATH '$.recacumuladafinal')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	OFERTAPROMO1 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTAPROMO1 as coluna,jsonEdit,json FROM IO_CARTAO_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTAPROMO1',json VARCHAR2(2500) PATH '$.ofertapromo1')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	OFERTAPROMO2 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTAPROMO2 as coluna,jsonEdit,json FROM IO_CARTAO_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTAPROMO2',json VARCHAR2(2500) PATH '$.ofertapromo2')) where ID = ? order by rownum asc) where rownum = 1)\n"
            + ", DT_GMUD = ? WHERE ID = ?";

    public static final String UPDATEINSERT
            = "INSERT INTO IO_CARTAO_CARTAO (\n"
            + "	DDD,\n"
            + "	RECACUMULADAINICIAL,\n"
            + "	RECACUMULADAFINAL,\n"
            + "	OFERTAPROMO1,\n"
            + "	OFERTAPROMO2,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	DDD,\n"
            + "	RECACUMULADAINICIAL,\n"
            + "	RECACUMULADAFINAL,\n"
            + "	OFERTAPROMO1,\n"
            + "	OFERTAPROMO2,\n"
            + "	? HISTORY,\n"
            + "	? DT_GMUD,\n"
            + "	ID\n"
            + "FROM IO_CARTAO_CARTAO WHERE ID = ?)";

    public static final String DELETE
            = "INSERT INTO IO_CARTAO_CARTAO (\n"
            + "	DDD,\n"
            + "	RECACUMULADAINICIAL,\n"
            + "	RECACUMULADAFINAL,\n"
            + "	OFERTAPROMO1,\n"
            + "	OFERTAPROMO2,\n"
            + "	HISTORY,\n"
            + "	DELETED,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	DDD,\n"
            + "	RECACUMULADAINICIAL,\n"
            + "	RECACUMULADAFINAL,\n"
            + "	OFERTAPROMO1,\n"
            + "	OFERTAPROMO2,\n"
            + "	?,\n"
            + "	1,\n"
            + "	?, \n"
            + "	ID \n"
            + "FROM IO_CARTAO_CARTAO WHERE ID IN (?))";

    public static final String APROVAR
            = "t1.DDD = t2.DDD,\n"
            + "t1.RECACUMULADAINICIAL = t2.RECACUMULADAINICIAL,\n"
            + "t1.RECACUMULADAFINAL = t2.RECACUMULADAFINAL,\n"
            + "t1.OFERTAPROMO1 = t2.OFERTAPROMO1,\n"
            + "t1.OFERTAPROMO2 = t2.OFERTAPROMO2,\n"
            + "t1.HISTORY = t2.HISTORY";

}
