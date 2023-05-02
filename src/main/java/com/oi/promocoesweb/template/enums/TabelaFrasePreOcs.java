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
public class TabelaFrasePreOcs {

    public static final String SELECT
            = "SELECT JSON_QUERY(\n"
            + " concat(concat(concat(concat(concat(concat(concat(\n"
            + " concat(concat('{\"idcampanha\":\"',IDCAMPANHA),'\",'),\n"
            + " concat(concat('\"idoferta\":\"',IDOFERTA),'\",')),\n"
            + " concat(concat('\"ddd\":\"',DDD),'\",')),\n"
            + " concat(concat('\"msg_oferta_1\":\"',MSG_OFERTA_1),'\",')),\n"
            + " concat(concat('\"msg_oferta_2\":\"',MSG_OFERTA_2),'\",')),\n"
            + " concat(concat('\"msg_oferta_3\":\"',MSG_OFERTA_3),'\",')),\n"
            + " concat(concat('\"msg_oferta_default\":\"',MSG_OFERTA_DEFAULT),'\",')),\n"
            + " concat(concat('\"msg_ocs_sva\":\"',MSG_OCS_SVA),'\"}'))\n"
            + ",'$') as json,ID,TEMPID,HISTORY,INSERTED,DT_GMUD,DELETED FROM FRASE_PRE_OCS t WHERE (DT_GMUD IS NULL AND DELETED = 0) OR (DT_GMUD IS NOT NULL) ORDER BY INSERTED DESC NULLS LAST";

    public static final String INSERT
            = "INSERT INTO FRASE_PRE_OCS (\n"
            + "	IDCAMPANHA,\n"
            + "	IDOFERTA,\n"
            + "	DDD,\n"
            + "	MSG_OFERTA_1,\n"
            + "	MSG_OFERTA_2,\n"
            + "	MSG_OFERTA_3,\n"
            + "	MSG_OFERTA_DEFAULT,\n"
            + "	MSG_OCS_SVA,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD\n"
            + ")(SELECT \n"
            + "	IDCAMPANHA,\n"
            + "	IDOFERTA,\n"
            + "	DDD,\n"
            + "	MSG_OFERTA_1,\n"
            + "	MSG_OFERTA_2,\n"
            + "	MSG_OFERTA_3,\n"
            + "	MSG_OFERTA_DEFAULT,\n"
            + "	MSG_OCS_SVA,\n"
            + "	?,\n"
            + "   DT_GMUD\n"
            + "FROM dual, JSON_TABLE(?, '$' COLUMNS (\n"
            + "	IDCAMPANHA VARCHAR2(2500) PATH '$.idcampanha',\n"
            + "	IDOFERTA VARCHAR2(2500) PATH '$.idoferta',\n"
            + "	DDD VARCHAR2(2500) PATH '$.ddd',\n"
            + "	MSG_OFERTA_1 VARCHAR2(2500) PATH '$.msg_oferta_1',\n"
            + "	MSG_OFERTA_2 VARCHAR2(2500) PATH '$.msg_oferta_2',\n"
            + "	MSG_OFERTA_3 VARCHAR2(2500) PATH '$.msg_oferta_3',\n"
            + "	MSG_OFERTA_DEFAULT VARCHAR2(2500) PATH '$.msg_oferta_default',\n"
            + "	MSG_OCS_SVA VARCHAR2(2500) PATH '$.msg_ocs_sva',\n"
            + "	DT_GMUD VARCHAR2(2500) PATH '$.dt_gmud'\n"
            + ")))";

    public static final String UPDATE
            = "UPDATE FRASE_PRE_OCS SET \n"
            + "	IDCAMPANHA = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT IDCAMPANHA as coluna,jsonEdit,json FROM FRASE_PRE_OCS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.IDCAMPANHA',json VARCHAR2(2500) PATH '$.idcampanha')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	IDOFERTA = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT IDOFERTA as coluna,jsonEdit,json FROM FRASE_PRE_OCS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.IDOFERTA',json VARCHAR2(2500) PATH '$.idoferta')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	DDD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DDD as coluna,jsonEdit,json FROM FRASE_PRE_OCS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DDD',json VARCHAR2(2500) PATH '$.ddd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	MSG_OFERTA_1 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT MSG_OFERTA_1 as coluna,jsonEdit,json FROM FRASE_PRE_OCS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.MSG_OFERTA_1',json VARCHAR2(2500) PATH '$.msg_oferta_1')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	MSG_OFERTA_2 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT MSG_OFERTA_2 as coluna,jsonEdit,json FROM FRASE_PRE_OCS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.MSG_OFERTA_2',json VARCHAR2(2500) PATH '$.msg_oferta_2')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	MSG_OFERTA_3 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT MSG_OFERTA_3 as coluna,jsonEdit,json FROM FRASE_PRE_OCS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.MSG_OFERTA_3',json VARCHAR2(2500) PATH '$.msg_oferta_3')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	MSG_OFERTA_DEFAULT = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT MSG_OFERTA_DEFAULT as coluna,jsonEdit,json FROM FRASE_PRE_OCS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.MSG_OFERTA_DEFAULT',json VARCHAR2(2500) PATH '$.msg_oferta_default')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	MSG_OCS_SVA = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT MSG_OCS_SVA as coluna,jsonEdit,json FROM FRASE_PRE_OCS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.MSG_OCS_SVA',json VARCHAR2(2500) PATH '$.msg_ocs_sva')) where ID = ? order by rownum asc) where rownum = 1)\n"
            + ", DT_GMUD = ? WHERE ID = ?";

    public static final String UPDATEINSERT
            = "INSERT INTO FRASE_PRE_OCS (\n"
            + "	IDCAMPANHA,\n"
            + "	IDOFERTA,\n"
            + "	DDD,\n"
            + "	MSG_OFERTA_1,\n"
            + "	MSG_OFERTA_2,\n"
            + "	MSG_OFERTA_3,\n"
            + "	MSG_OFERTA_DEFAULT,\n"
            + "	MSG_OCS_SVA,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	IDCAMPANHA,\n"
            + "	IDOFERTA,\n"
            + "	DDD,\n"
            + "	MSG_OFERTA_1,\n"
            + "	MSG_OFERTA_2,\n"
            + "	MSG_OFERTA_3,\n"
            + "	MSG_OFERTA_DEFAULT,\n"
            + "	MSG_OCS_SVA,\n"
            + "	? HISTORY,\n"
            + "	? DT_GMUD,\n"
            + "	ID\n"
            + "FROM FRASE_PRE_OCS WHERE ID = ?)";

    public static final String DELETE
            = "INSERT INTO FRASE_PRE_OCS (\n"
            + "	IDCAMPANHA,\n"
            + "	IDOFERTA,\n"
            + "	DDD,\n"
            + "	MSG_OFERTA_1,\n"
            + "	MSG_OFERTA_2,\n"
            + "	MSG_OFERTA_3,\n"
            + "	MSG_OFERTA_DEFAULT,\n"
            + "	MSG_OCS_SVA,\n"
            + "	HISTORY,\n"
            + "	DELETED,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	IDCAMPANHA,\n"
            + "	IDOFERTA,\n"
            + "	DDD,\n"
            + "	MSG_OFERTA_1,\n"
            + "	MSG_OFERTA_2,\n"
            + "	MSG_OFERTA_3,\n"
            + "	MSG_OFERTA_DEFAULT,\n"
            + "	MSG_OCS_SVA,\n"
            + "	?,\n"
            + "	1,\n"
            + "	?, \n"
            + "	ID \n"
            + "FROM FRASE_PRE_OCS WHERE ID IN (?))";

    public static final String APROVAR
            = "t1.IDCAMPANHA = t2.IDCAMPANHA,\n"
            + "t1.IDOFERTA = t2.IDOFERTA,\n"
            + "t1.DDD = t2.DDD,\n"
            + "t1.MSG_OFERTA_1 = t2.MSG_OFERTA_1,\n"
            + "t1.MSG_OFERTA_2 = t2.MSG_OFERTA_2,\n"
            + "t1.MSG_OFERTA_3 = t2.MSG_OFERTA_3,\n"
            + "t1.MSG_OFERTA_DEFAULT = t2.MSG_OFERTA_DEFAULT,\n"
            + "t1.MSG_OCS_SVA = t2.MSG_OCS_SVA,\n"
            + "t1.HISTORY = t2.HISTORY";

}
