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
public class TabelaTemplatePreOcs {

    public static final String SELECT
            = "SELECT JSON_QUERY(\n"
            + " concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(\n"
            + " concat(concat('{\"ddd\":\"',DDD),'\",')),\n"
            + " concat(concat('\"id_campanha_siebel1\":\"',ID_CAMPANHA_SIEBEL1),'\",'),\n"
            + " concat(concat('\"id_oferta_siebel1\":\"',ID_OFERTA_SIEBEL1),'\",')),\n"
            + " concat(concat('\"id_campanha_siebel2\":\"',ID_CAMPANHA_SIEBEL2),'\",')),\n"
            + " concat(concat('\"id_oferta_siebel2\":\"',ID_OFERTA_SIEBEL2),'\",')),\n"
            + " concat(concat('\"id_campanha_siebel3\":\"',ID_CAMPANHA_SIEBEL3),'\",')),\n"
            + " concat(concat('\"id_oferta_siebel3\":\"',ID_OFERTA_SIEBEL3),'\",')),\n"
            + " concat(concat('\"id_campanha_default\":\"',ID_CAMPANHA_DEFAULT),'\",')),\n"
            + " concat(concat('\"id_oferta_default\":\"',ID_OFERTA_DEFAULT),'\",')),\n"
            + " concat(concat('\"msg_ocs_sva\":\"',MSG_OCS_SVA),'\",')),\n"
            + " concat(concat('\"tipo_bonus\":\"',TIPO_BONUS),'\"}'))\n"
            + ",'$') as json,ID,TEMPID,HISTORY,INSERTED,DT_GMUD,DELETED FROM TB_TEMPLATE_OCS t WHERE (DT_GMUD IS NULL AND DELETED = 0) OR (DT_GMUD IS NOT NULL) ORDER BY INSERTED DESC NULLS LAST";

    public static final String INSERT
            = "INSERT INTO TB_TEMPLATE_OCS (\n"
            + "	DDD,\n"
            + "	ID_CAMPANHA_SIEBEL1,\n"
            + "	ID_OFERTA_SIEBEL1,\n"
            + "	ID_CAMPANHA_SIEBEL2,\n"
            + "	ID_OFERTA_SIEBEL2,\n"
            + "	ID_CAMPANHA_SIEBEL3,\n"
            + "	ID_OFERTA_SIEBEL3,\n"
            + "	ID_CAMPANHA_DEFAULT,\n"
            + "	ID_OFERTA_DEFAULT,\n"
            + "	MSG_OCS_SVA,\n"
            + "	TIPO_BONUS,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD\n"
            + ")(SELECT \n"
            + "	DDD,\n"
            + "	ID_CAMPANHA_SIEBEL1,\n"
            + "	ID_OFERTA_SIEBEL1,\n"
            + "	ID_CAMPANHA_SIEBEL2,\n"
            + "	ID_OFERTA_SIEBEL2,\n"
            + "	ID_CAMPANHA_SIEBEL3,\n"
            + "	ID_OFERTA_SIEBEL3,\n"
            + "	ID_CAMPANHA_DEFAULT,\n"
            + "	ID_OFERTA_DEFAULT,\n"
            + "	MSG_OCS_SVA,\n"
            + "	TIPO_BONUS,\n"
            + "	?,\n"
            + "   DT_GMUD\n"
            + "FROM dual, JSON_TABLE(?, '$' COLUMNS (\n"
            + "	DDD VARCHAR2(2500) PATH '$.ddd',\n"
            + "	ID_CAMPANHA_SIEBEL1 VARCHAR2(2500) PATH '$.id_campanha_siebel1',\n"
            + "	ID_OFERTA_SIEBEL1 VARCHAR2(2500) PATH '$.id_oferta_siebel1',\n"
            + "	ID_CAMPANHA_SIEBEL2 VARCHAR2(2500) PATH '$.id_campanha_siebel2',\n"
            + "	ID_OFERTA_SIEBEL2 VARCHAR2(2500) PATH '$.id_oferta_siebel2',\n"
            + "	ID_CAMPANHA_SIEBEL3 VARCHAR2(2500) PATH '$.id_campanha_siebel3',\n"
            + "	ID_OFERTA_SIEBEL3 VARCHAR2(2500) PATH '$.id_oferta_siebel3',\n"
            + "	ID_CAMPANHA_DEFAULT VARCHAR2(2500) PATH '$.id_campanha_default',\n"
            + "	ID_OFERTA_DEFAULT VARCHAR2(2500) PATH '$.id_oferta_default',\n"
            + "	MSG_OCS_SVA VARCHAR2(2500) PATH '$.msg_ocs_sva',\n"
            + "	TIPO_BONUS VARCHAR2(2500) PATH '$.tipo_bonus',\n"
            + "	DT_GMUD VARCHAR2(2500) PATH '$.dt_gmud'\n"
            + ")))";

    public static final String UPDATE
            = "UPDATE TB_TEMPLATE_OCS SET \n"
            + "	DDD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DDD as coluna,jsonEdit,json FROM TB_TEMPLATE_OCS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DDD',json VARCHAR2(2500) PATH '$.ddd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	ID_CAMPANHA_SIEBEL1 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT ID_CAMPANHA_SIEBEL1 as coluna,jsonEdit,json FROM TB_TEMPLATE_OCS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.ID_CAMPANHA_SIEBEL1',json VARCHAR2(2500) PATH '$.id_campanha_siebel1')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	ID_OFERTA_SIEBEL1 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT ID_OFERTA_SIEBEL1 as coluna,jsonEdit,json FROM TB_TEMPLATE_OCS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.ID_OFERTA_SIEBEL1',json VARCHAR2(2500) PATH '$.id_oferta_siebel1')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	ID_CAMPANHA_SIEBEL2 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT ID_CAMPANHA_SIEBEL2 as coluna,jsonEdit,json FROM TB_TEMPLATE_OCS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.ID_CAMPANHA_SIEBEL2',json VARCHAR2(2500) PATH '$.id_campanha_siebel2')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	ID_OFERTA_SIEBEL2 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT ID_OFERTA_SIEBEL2 as coluna,jsonEdit,json FROM TB_TEMPLATE_OCS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.ID_OFERTA_SIEBEL2',json VARCHAR2(2500) PATH '$.id_oferta_siebel2')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	ID_CAMPANHA_SIEBEL3 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT ID_CAMPANHA_SIEBEL3 as coluna,jsonEdit,json FROM TB_TEMPLATE_OCS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.ID_CAMPANHA_SIEBEL3',json VARCHAR2(2500) PATH '$.id_campanha_siebel3')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	ID_OFERTA_SIEBEL3 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT ID_OFERTA_SIEBEL3 as coluna,jsonEdit,json FROM TB_TEMPLATE_OCS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.ID_OFERTA_SIEBEL3',json VARCHAR2(2500) PATH '$.id_oferta_siebel3')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	ID_CAMPANHA_DEFAULT = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT ID_CAMPANHA_DEFAULT as coluna,jsonEdit,json FROM TB_TEMPLATE_OCS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.ID_CAMPANHA_DEFAULT',json VARCHAR2(2500) PATH '$.id_campanha_default')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	ID_OFERTA_DEFAULT = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT ID_OFERTA_DEFAULT as coluna,jsonEdit,json FROM TB_TEMPLATE_OCS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.ID_OFERTA_DEFAULT',json VARCHAR2(2500) PATH '$.id_oferta_default')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	MSG_OCS_SVA = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT MSG_OCS_SVA as coluna,jsonEdit,json FROM TB_TEMPLATE_OCS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.MSG_OCS_SVA',json VARCHAR2(2500) PATH '$.msg_ocs_sva')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	TIPO_BONUS = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TIPO_BONUS as coluna,jsonEdit,json FROM TB_TEMPLATE_OCS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TIPO_BONUS',json VARCHAR2(2500) PATH '$.tipo_bonus')) where ID = ? order by rownum asc) where rownum = 1)\n"
            + ", DT_GMUD = ? WHERE ID = ?";

    public static final String UPDATEINSERT
            = "INSERT INTO TB_TEMPLATE_OCS (\n"
            + "	DDD,\n"
            + "	ID_CAMPANHA_SIEBEL1,\n"
            + "	ID_OFERTA_SIEBEL1,\n"
            + "	ID_CAMPANHA_SIEBEL2,\n"
            + "	ID_OFERTA_SIEBEL2,\n"
            + "	ID_CAMPANHA_SIEBEL3,\n"
            + "	ID_OFERTA_SIEBEL3,\n"
            + "	ID_CAMPANHA_DEFAULT,\n"
            + "	ID_OFERTA_DEFAULT,\n"
            + "	MSG_OCS_SVA,\n"
            + "	TIPO_BONUS,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	DDD,\n"
            + "	ID_CAMPANHA_SIEBEL1,\n"
            + "	ID_OFERTA_SIEBEL1,\n"
            + "	ID_CAMPANHA_SIEBEL2,\n"
            + "	ID_OFERTA_SIEBEL2,\n"
            + "	ID_CAMPANHA_SIEBEL3,\n"
            + "	ID_OFERTA_SIEBEL3,\n"
            + "	ID_CAMPANHA_DEFAULT,\n"
            + "	ID_OFERTA_DEFAULT,\n"
            + "	MSG_OCS_SVA,\n"
            + "	TIPO_BONUS,\n"
            + "	? HISTORY,\n"
            + "	? DT_GMUD,\n"
            + "	ID\n"
            + "FROM TB_TEMPLATE_OCS WHERE ID = ?)";

    public static final String DELETE
            = "INSERT INTO TB_TEMPLATE_OCS (\n"
            + "	DDD,\n"
            + "	ID_CAMPANHA_SIEBEL1,\n"
            + "	ID_OFERTA_SIEBEL1,\n"
            + "	ID_CAMPANHA_SIEBEL2,\n"
            + "	ID_OFERTA_SIEBEL2,\n"
            + "	ID_CAMPANHA_SIEBEL3,\n"
            + "	ID_OFERTA_SIEBEL3,\n"
            + "	ID_CAMPANHA_DEFAULT,\n"
            + "	ID_OFERTA_DEFAULT,\n"
            + "	MSG_OCS_SVA,\n"
            + "	TIPO_BONUS,\n"
            + "	HISTORY,\n"
            + "	DELETED,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	DDD,\n"
            + "	ID_CAMPANHA_SIEBEL1,\n"
            + "	ID_OFERTA_SIEBEL1,\n"
            + "	ID_CAMPANHA_SIEBEL2,\n"
            + "	ID_OFERTA_SIEBEL2,\n"
            + "	ID_CAMPANHA_SIEBEL3,\n"
            + "	ID_OFERTA_SIEBEL3,\n"
            + "	ID_CAMPANHA_DEFAULT,\n"
            + "	ID_OFERTA_DEFAULT,\n"
            + "	MSG_OCS_SVA,\n"
            + "	TIPO_BONUS,\n"
            + "	?,\n"
            + "	1,\n"
            + "	?, \n"
            + "	ID \n"
            + "FROM TB_TEMPLATE_OCS WHERE ID IN (?))";

    public static final String APROVAR
            = "t1.DDD = t2.DDD,\n"
            + "t1.ID_CAMPANHA_SIEBEL1 = t2.ID_CAMPANHA_SIEBEL1,\n"
            + "t1.ID_OFERTA_SIEBEL1 = t2.ID_OFERTA_SIEBEL1,\n"
            + "t1.ID_CAMPANHA_SIEBEL2 = t2.ID_CAMPANHA_SIEBEL2,\n"
            + "t1.ID_OFERTA_SIEBEL2 = t2.ID_OFERTA_SIEBEL2,\n"
            + "t1.ID_CAMPANHA_SIEBEL3 = t2.ID_CAMPANHA_SIEBEL3,\n"
            + "t1.ID_OFERTA_SIEBEL3 = t2.ID_OFERTA_SIEBEL3,\n"
            + "t1.ID_CAMPANHA_DEFAULT = t2.ID_CAMPANHA_DEFAULT,\n"
            + "t1.ID_OFERTA_DEFAULT = t2.ID_OFERTA_DEFAULT,\n"
            + "t1.MSG_OCS_SVA = t2.MSG_OCS_SVA,\n"
            + "t1.TIPO_BONUS = t2.TIPO_BONUS,\n"
            + "t1.HISTORY = t2.HISTORY";

}