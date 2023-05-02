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
public class TabelaPromptControleMenu {

    public static final String SELECT
            = " SELECT '' as json,\n" +
            "TB_ID_BENEFICIO,\n" +
            "TB_ID_PROGRAMA,\n" +
            "TB_DDD,\n" +
            "PROMOPADRAO_MENSAGEMINICIAL,\n" +
            "PROMOPADRAO_REGRATORPEDO,\n" +
            "PROMOPADRAO_REGRALIGACAO,\n" +
            "PROMOPADRAO_COMOUSAR,\n" +
            "PROMOPADRAO_DADOS,\n" +
            "INFORMAPROMO_MENSAGEMINICIAL,\n" +
            "INFORMAPROMO_REGRATORPEDO,\n" +
            "INFORMAPROMO_REGRALIGACAO,\n" +
            "INFORMAPROMO_COMOUSAR,\n" +
            "INFORMAPROMO_VALIDADEPROMO,\n" +
            "INFORMAPROMO_DADOS,\n" +
            "OUTROSPLANOS_INFORMAPROMOOICONTROLE,\n" +
            "ID,TEMPID,HISTORY,INSERTED,DT_GMUD,DELETED FROM PROMPT_CONTROLEMENU t WHERE (DT_GMUD IS NULL AND DELETED = 0) OR (DT_GMUD IS NOT NULL) ORDER BY INSERTED DESC NULLS LAST" ;
    
    public static final String INSERT
            = "INSERT INTO PROMPT_CONTROLEMENU (\n"
            + " TB_ID_BENEFICIO,\n"
            + " TB_ID_PROGRAMA,\n"
            + " TB_DDD,\n"
            + " PROMOPADRAO_MENSAGEMINICIAL,\n"
            + " PROMOPADRAO_REGRATORPEDO,\n"
            + " PROMOPADRAO_REGRALIGACAO,\n"
            + " PROMOPADRAO_COMOUSAR,\n"
            + " PROMOPADRAO_DADOS,\n"
            + " INFORMAPROMO_MENSAGEMINICIAL,\n"
            + " INFORMAPROMO_REGRATORPEDO,\n"
            + " INFORMAPROMO_REGRALIGACAO,\n"
            + " INFORMAPROMO_COMOUSAR,\n"
            + " INFORMAPROMO_VALIDADEPROMO,\n"
            + " INFORMAPROMO_DADOS,\n"
            + " OUTROSPLANOS_INFORMAPROMOOICONTROLE,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD\n"
            + ")(SELECT \n"
            + " TB_ID_BENEFICIO,\n"
            + " TB_ID_PROGRAMA,\n"
            + " TB_DDD,\n"
            + " PROMOPADRAO_MENSAGEMINICIAL,\n"
            + " PROMOPADRAO_REGRATORPEDO,\n"
            + " PROMOPADRAO_REGRALIGACAO,\n"
            + " PROMOPADRAO_COMOUSAR,\n"
            + " PROMOPADRAO_DADOS,\n"
            + " INFORMAPROMO_MENSAGEMINICIAL,\n"
            + " INFORMAPROMO_REGRATORPEDO,\n"
            + " INFORMAPROMO_REGRALIGACAO,\n"
            + " INFORMAPROMO_COMOUSAR,\n"
            + " INFORMAPROMO_VALIDADEPROMO,\n"
            + " INFORMAPROMO_DADOS,\n"
            + " OUTROSPLANOS_INFORMAPROMOOICONTROLE,\n"
            + "	?,\n"
            + "   DT_GMUD\n"
            + "FROM dual, JSON_TABLE(?, '$' COLUMNS (\n"
            + " TB_ID_BENEFICIO VARCHAR2(2500) PATH '$.tb_id_beneficio',\n"
            + " TB_ID_PROGRAMA VARCHAR2(2500) PATH '$.tb_id_programa',\n"
            + " TB_DDD VARCHAR2(2500) PATH '$.tb_ddd',\n"
            + " PROMOPADRAO_MENSAGEMINICIAL VARCHAR2(2500) PATH '$.promopadrao_mensageminicial',\n"
            + " PROMOPADRAO_REGRATORPEDO VARCHAR2(2500) PATH '$.promopadrao_regratorpedo',\n"
            + " PROMOPADRAO_REGRALIGACAO VARCHAR2(2500) PATH '$.promopadrao_regraligacao',\n"
            + " PROMOPADRAO_COMOUSAR VARCHAR2(2500) PATH '$.promopadrao_comousar',\n"
            + " PROMOPADRAO_DADOS VARCHAR2(2500) PATH '$.promopadrao_dados',\n"
            + " INFORMAPROMO_MENSAGEMINICIAL VARCHAR2(2500) PATH '$.informapromo_mensageminicial',\n"
            + " INFORMAPROMO_REGRATORPEDO VARCHAR2(2500) PATH '$.informapromo_regratorpedo',\n"
            + " INFORMAPROMO_REGRALIGACAO VARCHAR2(2500) PATH '$.informapromo_regraligacao',\n"
            + " INFORMAPROMO_COMOUSAR VARCHAR2(2500) PATH '$.informapromo_comousar',\n"
            + " INFORMAPROMO_VALIDADEPROMO VARCHAR2(2500) PATH '$.informapromo_validadepromo',\n"
            + " INFORMAPROMO_DADOS VARCHAR2(2500) PATH '$.informapromo_dados',\n"
            + " OUTROSPLANOS_INFORMAPROMOOICONTROLE VARCHAR2(2500) PATH '$.outrosplanos_informapromooicontrole',\n"
            + "	DT_GMUD VARCHAR2(2500) PATH '$.dt_gmud'\n"
            + ")))";

    public static final String UPDATE
            = "UPDATE PROMPT_CONTROLEMENU SET \n"
            + " TB_ID_BENEFICIO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_ID_BENEFICIO as coluna,jsonEdit,json FROM PROMPT_CONTROLEMENU,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_ID_BENEFICIO',json NUMBER(38,0) PATH '$.tb_id_beneficio')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_ID_PROGRAMA = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_ID_PROGRAMA as coluna,jsonEdit,json FROM PROMPT_CONTROLEMENU,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_ID_PROGRAMA',json NUMBER(38,0) PATH '$.tb_id_programa')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_DDD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_DDD as coluna,jsonEdit,json FROM PROMPT_CONTROLEMENU,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_DDD',json VARCHAR2(2500) PATH '$.tb_ddd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " PROMOPADRAO_MENSAGEMINICIAL = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT PROMOPADRAO_MENSAGEMINICIAL as coluna,jsonEdit,json FROM PROMPT_CONTROLEMENU,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.PROMOPADRAO_MENSAGEMINICIAL',json VARCHAR2(2500) PATH '$.promopadrao_mensageminicial')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " PROMOPADRAO_REGRATORPEDO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT PROMOPADRAO_REGRATORPEDO as coluna,jsonEdit,json FROM PROMPT_CONTROLEMENU,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.PROMOPADRAO_REGRATORPEDO',json VARCHAR2(2500) PATH '$.promopadrao_regratorpedo')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " PROMOPADRAO_REGRALIGACAO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT PROMOPADRAO_REGRALIGACAO as coluna,jsonEdit,json FROM PROMPT_CONTROLEMENU,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.PROMOPADRAO_REGRALIGACAO',json VARCHAR2(2500) PATH '$.promopadrao_regraligacao')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " PROMOPADRAO_COMOUSAR = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT PROMOPADRAO_COMOUSAR as coluna,jsonEdit,json FROM PROMPT_CONTROLEMENU,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.PROMOPADRAO_COMOUSAR',json VARCHAR2(2500) PATH '$.promopadrao_comousar')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " PROMOPADRAO_DADOS = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT PROMOPADRAO_DADOS as coluna,jsonEdit,json FROM PROMPT_CONTROLEMENU,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.PROMOPADRAO_DADOS',json VARCHAR2(2500) PATH '$.promopadrao_dados')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " INFORMAPROMO_MENSAGEMINICIAL = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT INFORMAPROMO_MENSAGEMINICIAL as coluna,jsonEdit,json FROM PROMPT_CONTROLEMENU,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.INFORMAPROMO_MENSAGEMINICIAL',json VARCHAR2(2500) PATH '$.informapromo_mensageminicial')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " INFORMAPROMO_REGRATORPEDO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT INFORMAPROMO_REGRATORPEDO as coluna,jsonEdit,json FROM PROMPT_CONTROLEMENU,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.INFORMAPROMO_REGRATORPEDO',json VARCHAR2(2500) PATH '$.informapromo_regratorpedo')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " INFORMAPROMO_REGRALIGACAO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT INFORMAPROMO_REGRALIGACAO as coluna,jsonEdit,json FROM PROMPT_CONTROLEMENU,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.INFORMAPROMO_REGRALIGACAO',json VARCHAR2(2500) PATH '$.informapromo_regraligacao')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " INFORMAPROMO_COMOUSAR = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT INFORMAPROMO_COMOUSAR as coluna,jsonEdit,json FROM PROMPT_CONTROLEMENU,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.INFORMAPROMO_COMOUSAR',json VARCHAR2(2500) PATH '$.informapromo_comousar')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " INFORMAPROMO_VALIDADEPROMO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT INFORMAPROMO_VALIDADEPROMO as coluna,jsonEdit,json FROM PROMPT_CONTROLEMENU,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.INFORMAPROMO_VALIDADEPROMO',json VARCHAR2(2500) PATH '$.informapromo_validadepromo')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " INFORMAPROMO_DADOS = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT INFORMAPROMO_DADOS as coluna,jsonEdit,json FROM PROMPT_CONTROLEMENU,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.INFORMAPROMO_DADOS',json VARCHAR2(2500) PATH '$.informapromo_dados')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " OUTROSPLANOS_INFORMAPROMOOICONTROLE = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OUTROSPLANOS_INFORMAPROMOOICONTROLE as coluna,jsonEdit,json FROM PROMPT_CONTROLEMENU,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OUTROSPLANOS_INFORMAPROMOOICONTROLE',json VARCHAR2(2500) PATH '$.outrosplanos_informapromooicontrole')) where ID = ? order by rownum asc) where rownum = 1)\n"
            + ", DT_GMUD = ? WHERE ID = ?";

    public static final String UPDATEINSERT
            = "INSERT INTO PROMPT_CONTROLEMENU (\n"
            + " TB_ID_BENEFICIO,\n"
            + " TB_ID_PROGRAMA,\n"
            + " TB_DDD,\n"
            + " PROMOPADRAO_MENSAGEMINICIAL,\n"
            + " PROMOPADRAO_REGRATORPEDO,\n"
            + " PROMOPADRAO_REGRALIGACAO,\n"
            + " PROMOPADRAO_COMOUSAR,\n"
            + " PROMOPADRAO_DADOS,\n"
            + " INFORMAPROMO_MENSAGEMINICIAL,\n"
            + " INFORMAPROMO_REGRATORPEDO,\n"
            + " INFORMAPROMO_REGRALIGACAO,\n"
            + " INFORMAPROMO_COMOUSAR,\n"
            + " INFORMAPROMO_VALIDADEPROMO,\n"
            + " INFORMAPROMO_DADOS,\n"
            + " OUTROSPLANOS_INFORMAPROMOOICONTROLE,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + " TB_ID_BENEFICIO,\n"
            + " TB_ID_PROGRAMA,\n"
            + " TB_DDD,\n"
            + " PROMOPADRAO_MENSAGEMINICIAL,\n"
            + " PROMOPADRAO_REGRATORPEDO,\n"
            + " PROMOPADRAO_REGRALIGACAO,\n"
            + " PROMOPADRAO_COMOUSAR,\n"
            + " PROMOPADRAO_DADOS,\n"
            + " INFORMAPROMO_MENSAGEMINICIAL,\n"
            + " INFORMAPROMO_REGRATORPEDO,\n"
            + " INFORMAPROMO_REGRALIGACAO,\n"
            + " INFORMAPROMO_COMOUSAR,\n"
            + " INFORMAPROMO_VALIDADEPROMO,\n"
            + " INFORMAPROMO_DADOS,\n"
            + " OUTROSPLANOS_INFORMAPROMOOICONTROLE,\n"
            + "	? HISTORY,\n"
            + "	? DT_GMUD,\n"
            + "	ID\n"
            + "FROM PROMPT_CONTROLEMENU WHERE ID = ?)";

    public static final String DELETE
            = "INSERT INTO PROMPT_CONTROLEMENU (\n"
            + " TB_ID_BENEFICIO,\n"
            + " TB_ID_PROGRAMA,\n"
            + " TB_DDD,\n"
            + " PROMOPADRAO_MENSAGEMINICIAL,\n"
            + " PROMOPADRAO_REGRATORPEDO,\n"
            + " PROMOPADRAO_REGRALIGACAO,\n"
            + " PROMOPADRAO_COMOUSAR,\n"
            + " PROMOPADRAO_DADOS,\n"
            + " INFORMAPROMO_MENSAGEMINICIAL,\n"
            + " INFORMAPROMO_REGRATORPEDO,\n"
            + " INFORMAPROMO_REGRALIGACAO,\n"
            + " INFORMAPROMO_COMOUSAR,\n"
            + " INFORMAPROMO_VALIDADEPROMO,\n"
            + " INFORMAPROMO_DADOS,\n"
            + " OUTROSPLANOS_INFORMAPROMOOICONTROLE,\n"
            + "	HISTORY,\n"
            + "	DELETED,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + " TB_ID_BENEFICIO,\n"
            + " TB_ID_PROGRAMA,\n"
            + " TB_DDD,\n"
            + " PROMOPADRAO_MENSAGEMINICIAL,\n"
            + " PROMOPADRAO_REGRATORPEDO,\n"
            + " PROMOPADRAO_REGRALIGACAO,\n"
            + " PROMOPADRAO_COMOUSAR,\n"
            + " PROMOPADRAO_DADOS,\n"
            + " INFORMAPROMO_MENSAGEMINICIAL,\n"
            + " INFORMAPROMO_REGRATORPEDO,\n"
            + " INFORMAPROMO_REGRALIGACAO,\n"
            + " INFORMAPROMO_COMOUSAR,\n"
            + " INFORMAPROMO_VALIDADEPROMO,\n"
            + " INFORMAPROMO_DADOS,\n"
            + " OUTROSPLANOS_INFORMAPROMOOICONTROLE,\n"
            + "	?,\n"
            + "	1,\n"
            + "	?, \n"
            + "	ID \n"
            + "FROM PROMPT_CONTROLEMENU WHERE ID IN (?))";

    public static final String APROVAR
            = "t1.TB_ID_BENEFICIO = t2.TB_ID_BENEFICIO,\n"
            + "t1.TB_ID_PROGRAMA = t2.TB_ID_PROGRAMA,\n"
            + "t1.TB_DDD = t2.TB_DDD,\n"
            + "t1.PROMOPADRAO_MENSAGEMINICIAL = t2.PROMOPADRAO_MENSAGEMINICIAL,\n"
            + "t1.PROMOPADRAO_REGRATORPEDO = t2.PROMOPADRAO_REGRATORPEDO,\n"
            + "t1.PROMOPADRAO_REGRALIGACAO = t2.PROMOPADRAO_REGRALIGACAO,\n"
            + "t1.PROMOPADRAO_COMOUSAR = t2.PROMOPADRAO_COMOUSAR,\n"
            + "t1.PROMOPADRAO_DADOS = t2.PROMOPADRAO_DADOS,\n"
            + "t1.INFORMAPROMO_MENSAGEMINICIAL = t2.INFORMAPROMO_MENSAGEMINICIAL,\n"
            + "t1.INFORMAPROMO_REGRATORPEDO = t2.INFORMAPROMO_REGRATORPEDO,\n"
            + "t1.INFORMAPROMO_REGRALIGACAO = t2.INFORMAPROMO_REGRALIGACAO,\n"
            + "t1.INFORMAPROMO_COMOUSAR = t2.INFORMAPROMO_COMOUSAR,\n"
            + "t1.INFORMAPROMO_VALIDADEPROMO = t2.INFORMAPROMO_VALIDADEPROMO,\n"
            + "t1.INFORMAPROMO_DADOS = t2.INFORMAPROMO_DADOS,\n"
            + "t1.OUTROSPLANOS_INFORMAPROMOOICONTROLE = t2.OUTROSPLANOS_INFORMAPROMOOICONTROLE,\n"
            + "t1.HISTORY = t2.HISTORY";

}
