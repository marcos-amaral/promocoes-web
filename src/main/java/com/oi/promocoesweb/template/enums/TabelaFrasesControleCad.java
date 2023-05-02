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
public class TabelaFrasesControleCad {

    public static final String SELECT
            = "SELECT JSON_QUERY(\n"
            + " concat(concat(concat(concat(\n"			
            + " concat(concat('{\"ddd\":\"',DDD),'\",'),\n"
            + " concat(concat('\"oferta_cadastro\":\"',OFERTA_CADASTRO),'\",')),\n"
            + " concat(concat('\"oferta_cadastro_conteudo\":\"',OFERTA_CADASTRO_CONTEUDO),'\",')),\n"
            + " concat(concat('\"oferta_escolhida\":\"',OFERTA_ESCOLHIDA),'\",')),\n"
            + " concat(concat('\"oferta_escolhida_conteudo\":\"',OFERTA_ESCOLHIDA_CONTEUDO),'\"}'))\n"
            + ",'$') as json,ID,TEMPID,HISTORY,INSERTED,DT_GMUD,DELETED FROM FRASES_CONTROLE_CAD t WHERE (DT_GMUD IS NULL AND DELETED = 0) OR (DT_GMUD IS NOT NULL) ORDER BY INSERTED DESC NULLS LAST";

    public static final String INSERT
            = "INSERT INTO FRASES_CONTROLE_CAD (\n"
            + " DDD,\n"
            + " OFERTA_CADASTRO,\n"
            + " OFERTA_CADASTRO_CONTEUDO,\n"
            + " OFERTA_ESCOLHIDA,\n"
            + " OFERTA_ESCOLHIDA_CONTEUDO,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD\n"
            + ")(SELECT \n"
            + " DDD,\n"
            + " OFERTA_CADASTRO,\n"
            + " OFERTA_CADASTRO_CONTEUDO,\n"
            + " OFERTA_ESCOLHIDA,\n"
            + " OFERTA_ESCOLHIDA_CONTEUDO,\n"
            + "	?,\n"
            + "   DT_GMUD\n"
            + "FROM dual, JSON_TABLE(?, '$' COLUMNS (\n"
            + " DDD VARCHAR2(2500) PATH '$.ddd',\n"
            + " OFERTA_CADASTRO VARCHAR2(2500) PATH '$.oferta_cadastro',\n"
            + " OFERTA_CADASTRO_CONTEUDO VARCHAR2(2500) PATH '$.oferta_cadastro_conteudo',\n"
            + " OFERTA_ESCOLHIDA VARCHAR2(2500) PATH '$.oferta_escolhida',\n"
            + " OFERTA_ESCOLHIDA_CONTEUDO VARCHAR2(2500) PATH '$.oferta_escolhida_conteudo',\n"
            + " DT_GMUD VARCHAR2(2500) PATH '$.dt_gmud'\n"
            + ")))";

    public static final String UPDATE
            = "UPDATE FRASES_CONTROLE_CAD SET \n"
            + " DDD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DDD as coluna,jsonEdit,json FROM FRASES_CONTROLE_CAD,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DDD',json VARCHAR2(2500) PATH '$.ddd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " OFERTA_CADASTRO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTA_CADASTRO as coluna,jsonEdit,json FROM FRASES_CONTROLE_CAD,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTA_CADASTRO',json VARCHAR2(2500) PATH '$.oferta_cadastro')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " OFERTA_CADASTRO_CONTEUDO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTA_CADASTRO_CONTEUDO as coluna,jsonEdit,json FROM FRASES_CONTROLE_CAD,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTA_CADASTRO_CONTEUDO',json VARCHAR2(2500) PATH '$.oferta_cadastro_conteudo')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " OFERTA_ESCOLHIDA = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTA_ESCOLHIDA as coluna,jsonEdit,json FROM FRASES_CONTROLE_CAD,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTA_ESCOLHIDA',json VARCHAR2(2500) PATH '$.oferta_escolhida')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " OFERTA_ESCOLHIDA_CONTEUDO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTA_ESCOLHIDA_CONTEUDO as coluna,jsonEdit,json FROM FRASES_CONTROLE_CAD,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTA_ESCOLHIDA_CONTEUDO',json VARCHAR2(2500) PATH '$.oferta_escolhida_conteudo')) where ID = ? order by rownum asc) where rownum = 1)\n"
            + ", DT_GMUD = ? WHERE ID = ?";

    public static final String UPDATEINSERT
            = "INSERT INTO FRASES_CONTROLE_CAD (\n"
            + " DDD,\n"
            + " OFERTA_CADASTRO,\n"
            + " OFERTA_CADASTRO_CONTEUDO,\n"
            + " OFERTA_ESCOLHIDA,\n"
            + " OFERTA_ESCOLHIDA_CONTEUDO,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + " DDD,\n"
            + " OFERTA_CADASTRO,\n"
            + " OFERTA_CADASTRO_CONTEUDO,\n"
            + " OFERTA_ESCOLHIDA,\n"
            + " OFERTA_ESCOLHIDA_CONTEUDO,\n"
            + "	? HISTORY,\n"
            + "	? DT_GMUD,\n"
            + "	ID\n"
            + "FROM FRASES_CONTROLE_CAD WHERE ID = ?)";

    public static final String DELETE
            = "INSERT INTO FRASES_CONTROLE_CAD (\n"
            + " DDD,\n"
            + " OFERTA_CADASTRO,\n"
            + " OFERTA_CADASTRO_CONTEUDO,\n"
            + " OFERTA_ESCOLHIDA,\n"
            + " OFERTA_ESCOLHIDA_CONTEUDO,\n"
            + "	HISTORY,\n"
            + "	DELETED,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + " DDD,\n"
            + " OFERTA_CADASTRO,\n"
            + " OFERTA_CADASTRO_CONTEUDO,\n"
            + " OFERTA_ESCOLHIDA,\n"
            + " OFERTA_ESCOLHIDA_CONTEUDO,\n"
            + "	?,\n"
            + "	1,\n"
            + "	?, \n"
            + "	ID \n"
            + "FROM FRASES_CONTROLE_CAD WHERE ID IN (?))";

    public static final String APROVAR
            = " t1.DDD = t2.DDD,\n"
            + " t1.OFERTA_CADASTRO = t2.OFERTA_CADASTRO,\n"
            + " t1.OFERTA_CADASTRO_CONTEUDO = t2.OFERTA_CADASTRO_CONTEUDO,\n"
            + " t1.OFERTA_ESCOLHIDA = t2.OFERTA_ESCOLHIDA,\n"
            + " t1.OFERTA_ESCOLHIDA_CONTEUDO = t2.OFERTA_ESCOLHIDA_CONTEUDO,\n"
            + " t1.HISTORY = t2.HISTORY";

}
