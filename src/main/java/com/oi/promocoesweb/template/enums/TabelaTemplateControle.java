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
public class TabelaTemplateControle {

    public static final String SELECT
            = "SELECT JSON_QUERY(\n"
            + " concat(concat(concat(concat(concat(concat(\n"
            + " concat(concat('{\"offercode\":\"',OFFERCODE),'\",'),\n"
            + " concat(concat('\"oferta_cadastro\":\"',OFERTA_CADASTRO),'\",')),\n"
            + " concat(concat('\"oferta_cadastro_escolhida\":\"',OFERTA_CADASTRO_ESCOLHIDA),'\",')),\n"
            + " concat(concat('\"tx_adesao_boleto\":\"',TX_ADESAO_BOLETO),'\",')),\n"
            + " concat(concat('\"tb_franquia_oferta\":\"',TB_FRANQUIA_OFERTA),'\",')),\n"
            + " concat(concat('\"ddd\":\"',DDD),'\",')),\n"
            + " concat(concat('\"tipo_pagamento\":\"',TIPO_PAGAMENTO),'\"}'))\n"
            + ",'$') as json,ID,TEMPID,HISTORY,INSERTED,DT_GMUD,DELETED FROM TB_TEMPLATE_CONTROLE t WHERE (DT_GMUD IS NULL AND DELETED = 0) OR (DT_GMUD IS NOT NULL) ORDER BY INSERTED DESC NULLS LAST";

    public static final String INSERT
            = "INSERT INTO TB_TEMPLATE_CONTROLE (\n"
            + " OFFERCODE,\n"
            + " OFERTA_CADASTRO,\n"
            + " OFERTA_CADASTRO_ESCOLHIDA,\n"
            + " TX_ADESAO_BOLETO,\n"
            + " TB_FRANQUIA_OFERTA,\n"
            + " DDD,\n"
            + " TIPO_PAGAMENTO,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD\n"
            + ")(SELECT \n"
            + " OFFERCODE,\n"
            + " OFERTA_CADASTRO,\n"
            + " OFERTA_CADASTRO_ESCOLHIDA,\n"
            + " TX_ADESAO_BOLETO,\n"
            + " TB_FRANQUIA_OFERTA,\n"
            + " DDD,\n"
            + " TIPO_PAGAMENTO,\n"
            + "	?,\n"
            + "   DT_GMUD\n"
            + "FROM dual, JSON_TABLE(?, '$' COLUMNS (\n"
            + " OFFERCODE VARCHAR2(2500) PATH '$.offercode',\n"
            + " OFERTA_CADASTRO VARCHAR2(2500) PATH '$.oferta_cadastro',\n"
            + " OFERTA_CADASTRO_ESCOLHIDA VARCHAR2(2500) PATH '$.oferta_cadastro_escolhida',\n"
            + " TX_ADESAO_BOLETO VARCHAR2(2500) PATH '$.tx_adesao_boleto',\n"
            + " TB_FRANQUIA_OFERTA VARCHAR2(2500) PATH '$.tb_franquia_oferta',\n"
            + " DDD VARCHAR2(2500) PATH '$.ddd',\n"
            + " TIPO_PAGAMENTO VARCHAR2(2500) PATH '$.tipo_pagamento',\n"
            + "	DT_GMUD VARCHAR2(2500) PATH '$.dt_gmud'\n"
            + ")))";

    public static final String UPDATE
            = "UPDATE TB_TEMPLATE_CONTROLE SET \n"
            + " OFFERCODE = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFFERCODE as coluna,jsonEdit,json FROM TB_TEMPLATE_CONTROLE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFFERCODE',json VARCHAR2(2500) PATH '$.offercode')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " OFERTA_CADASTRO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTA_CADASTRO as coluna,jsonEdit,json FROM TB_TEMPLATE_CONTROLE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTA_CADASTRO',json VARCHAR2(2500) PATH '$.oferta_cadastro')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " OFERTA_CADASTRO_ESCOLHIDA = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTA_CADASTRO_ESCOLHIDA as coluna,jsonEdit,json FROM TB_TEMPLATE_CONTROLE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTA_CADASTRO_ESCOLHIDA',json VARCHAR2(2500) PATH '$.oferta_cadastro_escolhida')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TX_ADESAO_BOLETO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TX_ADESAO_BOLETO as coluna,jsonEdit,json FROM TB_TEMPLATE_CONTROLE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TX_ADESAO_BOLETO',json NUMBER(38,0) PATH '$.tx_adesao_boleto')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_FRANQUIA_OFERTA = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_FRANQUIA_OFERTA as coluna,jsonEdit,json FROM TB_TEMPLATE_CONTROLE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_FRANQUIA_OFERTA',json NUMBER(38,0) PATH '$.tb_franquia_oferta')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " DDD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DDD as coluna,jsonEdit,json FROM TB_TEMPLATE_CONTROLE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DDD',json VARCHAR2(2500) PATH '$.ddd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TIPO_PAGAMENTO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TIPO_PAGAMENTO as coluna,jsonEdit,json FROM TB_TEMPLATE_CONTROLE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TIPO_PAGAMENTO',json VARCHAR2(2500) PATH '$.tipo_pagamento')) where ID = ? order by rownum asc) where rownum = 1)\n"
            + ", DT_GMUD = ? WHERE ID = ?";

    public static final String UPDATEINSERT
            = "INSERT INTO TB_TEMPLATE_CONTROLE (\n"
            + " OFFERCODE,\n"
            + " OFERTA_CADASTRO,\n"
            + " OFERTA_CADASTRO_ESCOLHIDA,\n"
            + " TX_ADESAO_BOLETO,\n"
            + " TB_FRANQUIA_OFERTA,\n"
            + " DDD,\n"
            + " TIPO_PAGAMENTO,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + " OFFERCODE,\n"
            + " OFERTA_CADASTRO,\n"
            + " OFERTA_CADASTRO_ESCOLHIDA,\n"
            + " TX_ADESAO_BOLETO,\n"
            + " TB_FRANQUIA_OFERTA,\n"
            + " DDD,\n"
            + " TIPO_PAGAMENTO,\n"
            + "	? HISTORY,\n"
            + "	? DT_GMUD,\n"
            + "	ID\n"
            + "FROM TB_TEMPLATE_CONTROLE WHERE ID = ?)";

    public static final String DELETE
            = "INSERT INTO TB_TEMPLATE_CONTROLE (\n"
            + " OFFERCODE,\n"
            + " OFERTA_CADASTRO,\n"
            + " OFERTA_CADASTRO_ESCOLHIDA,\n"
            + " TX_ADESAO_BOLETO,\n"
            + " TB_FRANQUIA_OFERTA,\n"
            + " DDD,\n"
            + " TIPO_PAGAMENTO,\n"
            + "	HISTORY,\n"
            + "	DELETED,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + " OFFERCODE,\n"
            + " OFERTA_CADASTRO,\n"
            + " OFERTA_CADASTRO_ESCOLHIDA,\n"
            + " TX_ADESAO_BOLETO,\n"
            + " TB_FRANQUIA_OFERTA,\n"
            + " DDD,\n"
            + " TIPO_PAGAMENTO,\n"
            + "	?,\n"
            + "	1,\n"
            + "	?, \n"
            + "	ID \n"
            + "FROM TB_TEMPLATE_CONTROLE WHERE ID IN (?))";

    public static final String APROVAR
            = "t1.OFFERCODE = t2.OFFERCODE,\n"
            + "t1.OFERTA_CADASTRO = t2.OFERTA_CADASTRO,\n"
            + "t1.OFERTA_CADASTRO_ESCOLHIDA = t2.OFERTA_CADASTRO_ESCOLHIDA,\n"
            + "t1.TX_ADESAO_BOLETO = t2.TX_ADESAO_BOLETO,\n"
            + "t1.TB_FRANQUIA_OFERTA = t2.TB_FRANQUIA_OFERTA,\n"
            + "t1.DDD = t2.DDD,\n"
            + "t1.TIPO_PAGAMENTO = t2.TIPO_PAGAMENTO,\n"
            + "t1.HISTORY = t2.HISTORY";

}
