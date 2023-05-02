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
public class TabelaListaPrompts {

    public static final String SELECT
            = "SELECT json_object(\n"
            + "	'wav' VALUE WAV,\n"
            + "	'conteudo' VALUE CONTEUDO\n"
            + ") as json,ID,TEMPID,HISTORY,INSERTED,DT_GMUD,DELETED FROM LISTA_PROMPTS t WHERE (DT_GMUD IS NULL AND DELETED = 0) OR (DT_GMUD IS NOT NULL) ORDER BY INSERTED DESC NULLS LAST";

    public static final String INSERT
            = "INSERT INTO LISTA_PROMPTS (\n"
            + "	WAV,\n"
            + "	CONTEUDO,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD\n"
            + ")(SELECT \n"
            + "	WAV,\n"
            + "	CONTEUDO,\n"
            + "	?,\n"
            + "   DT_GMUD\n"
            + "FROM dual, JSON_TABLE(?, '$' COLUMNS (\n"
            + "	WAV VARCHAR2(2500) PATH '$.wav',\n"
            + "	CONTEUDO VARCHAR2(2500) PATH '$.conteudo',\n"
            + "	DT_GMUD VARCHAR2(2500) PATH '$.dt_gmud'\n"
            + ")))";

    public static final String UPDATE
            = "UPDATE LISTA_PROMPTS SET \n"
            + "	WAV = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT WAV as coluna,jsonEdit,json FROM LISTA_PROMPTS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.WAV',json VARCHAR2(2500) PATH '$.wav')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	CONTEUDO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT CONTEUDO as coluna,jsonEdit,json FROM LISTA_PROMPTS,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.CONTEUDO',json VARCHAR2(2500) PATH '$.conteudo')) where ID = ? order by rownum asc) where rownum = 1)\n"
            + ", DT_GMUD = ? WHERE ID = ?";

    public static final String UPDATEINSERT
            = "INSERT INTO LISTA_PROMPTS (\n"
            + "	WAV,\n"
            + "	CONTEUDO,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	WAV,\n"
            + "	CONTEUDO,\n"
            + "	? HISTORY,\n"
            + "	? DT_GMUD,\n"
            + "	ID\n"
            + "FROM LISTA_PROMPTS WHERE ID = ?)";

    public static final String DELETE
            = "INSERT INTO LISTA_PROMPTS (\n"
            + "	WAV,\n"
            + "	CONTEUDO,\n"
            + "	HISTORY,\n"
            + "	DELETED,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	WAV,\n"
            + "	CONTEUDO,\n"
            + "	?,\n"
            + "	1,\n"
            + "	?, \n"
            + "	ID \n"
            + "FROM LISTA_PROMPTS WHERE ID IN (?))";

    public static final String APROVAR
            = "t1.WAV = t2.WAV,\n"
            + "t1.CONTEUDO = t2.CONTEUDO,\n"
            + "t1.HISTORY = t2.HISTORY";

}
