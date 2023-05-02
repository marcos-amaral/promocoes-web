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
public class TabelaFrasesCartao {

    public static final String SELECT
            = "SELECT '' as json,\n" +
            "IDBENEFICIO,\n" +
            "IDPROGRAMA,\n" +
            "DDD,\n" +
            "PROMOPADRAO_USSD,\n" +
            "SEMRECARGA_USSD,\n" +
            "CHAMADA_USSD,\n" +
            "DETALHES_USSD,\n" +
            "RECARGA_USSD,\n" +
            "INFORMAVALIDADE_USSD,\n" +
            "VALIDADE_USSD,\n" +
            "CONTINGENCIA_USSD,\n" +
            "NAOCLIENTE_INFORMAPROMO_USSD,\n" +
            "NAOCLIENTE_VALIDADE_USSD,\n" +
            "REGRAS_USSD,\n" +
            "NAOCLIENTE_RECARGA_USSD,\n" +
            "INCENTIVO_OFERTA_PRE_USSD,\n" +
            "FALLBACK_USSD,\n" +
            "SVA_USSD,\n" +
            "INFORMASALDOPROMO_USSD,\n" +
            "OFERTAMIGRADOS_USSD,\n" +
            "INFORMAPROMO_USSD_USSD,\n" +
            "PROMOPADRAO_144,\n" +
            "SEMRECARGA_144,\n" +
            "CHAMADA_144,\n" +
            "DETALHES_144,\n" +
            "RECARGA_144,\n" +
            "INFORMAVALIDADE_144,\n" +
            "VALIDADE_144,\n" +
            "CONTINGENCIA_144,\n" +
            "NAOCLIENTE_INFORMAPROMO_144,\n" +
            "NAOCLIENTE_VALIDADE_144,\n" +
            "REGRAS_144,\n" +
            "NAOCLIENTE_RECARGA_144,\n" +
            "INCENTIVO_OFERTA_PRE_144,\n" +
            "FALLBACK_144,\n" +
            "SVA_144,\n" +
            "INFORMASALDOPROMO_144,\n" +
            "OFERTAMIGRADOS_144,\n" +
            "INFORMAPROMO_USSD_144,\n" +
            "t.ID,t.TEMPID,t.HISTORY,t.INSERTED,t.DT_GMUD,t.DELETED FROM FRASES_CARTAO t WHERE (t.DT_GMUD IS NULL AND t.DELETED = 0) OR (t.DT_GMUD IS NOT NULL) ORDER BY t.INSERTED DESC NULLS LAST";

    public static final String INSERT
            = "INSERT INTO FRASES_CARTAO (\n"
            + "	IDBENEFICIO,\n"
            + "	IDPROGRAMA,\n"
            + "	DDD,\n"
            + "	PROMOPADRAO_USSD,\n"
            + "	SEMRECARGA_USSD,\n"
            + "	CHAMADA_USSD,\n"
            + "	DETALHES_USSD,\n"
            + "	RECARGA_USSD,\n"
            + "	INFORMAVALIDADE_USSD,\n"
            + "	VALIDADE_USSD,\n"
            + "	CONTINGENCIA_USSD,\n"
            + "	NAOCLIENTE_INFORMAPROMO_USSD,\n"
            + "	NAOCLIENTE_VALIDADE_USSD,\n"
            + "	REGRAS_USSD,\n"
            + "	NAOCLIENTE_RECARGA_USSD,\n"
            + "	INCENTIVO_OFERTA_PRE_USSD,\n"
            + "	FALLBACK_USSD,\n"
            + "	SVA_USSD,\n"
            + "	INFORMASALDOPROMO_USSD,\n"
            + "	OFERTAMIGRADOS_USSD,\n"
            + "	INFORMAPROMO_USSD_USSD,\n"
            + "	PROMOPADRAO_144,\n"
            + "	SEMRECARGA_144,\n"
            + "	CHAMADA_144,\n"
            + "	DETALHES_144,\n"
            + "	RECARGA_144,\n"
            + "	INFORMAVALIDADE_144,\n"
            + "	VALIDADE_144,\n"
            + "	CONTINGENCIA_144,\n"
            + "	NAOCLIENTE_INFORMAPROMO_144,\n"
            + "	NAOCLIENTE_VALIDADE_144,\n"
            + "	REGRAS_144,\n"
            + "	NAOCLIENTE_RECARGA_144,\n"
            + "	INCENTIVO_OFERTA_PRE_144,\n"
            + "	FALLBACK_144,\n"
            + "	SVA_144,\n"
            + "	INFORMASALDOPROMO_144,\n"
            + "	OFERTAMIGRADOS_144,\n"
            + "	INFORMAPROMO_USSD_144,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD\n"
            + ")(SELECT \n"
            + "	IDBENEFICIO,\n"
            + "	IDPROGRAMA,\n"
            + "	DDD,\n"
            + "	PROMOPADRAO_USSD,\n"
            + "	SEMRECARGA_USSD,\n"
            + "	CHAMADA_USSD,\n"
            + "	DETALHES_USSD,\n"
            + "	RECARGA_USSD,\n"
            + "	INFORMAVALIDADE_USSD,\n"
            + "	VALIDADE_USSD,\n"
            + "	CONTINGENCIA_USSD,\n"
            + "	NAOCLIENTE_INFORMAPROMO_USSD,\n"
            + "	NAOCLIENTE_VALIDADE_USSD,\n"
            + "	REGRAS_USSD,\n"
            + "	NAOCLIENTE_RECARGA_USSD,\n"
            + "	INCENTIVO_OFERTA_PRE_USSD,\n"
            + "	FALLBACK_USSD,\n"
            + "	SVA_USSD,\n"
            + "	INFORMASALDOPROMO_USSD,\n"
            + "	OFERTAMIGRADOS_USSD,\n"
            + "	INFORMAPROMO_USSD_USSD,\n"
            + "	PROMOPADRAO_144,\n"
            + "	SEMRECARGA_144,\n"
            + "	CHAMADA_144,\n"
            + "	DETALHES_144,\n"
            + "	RECARGA_144,\n"
            + "	INFORMAVALIDADE_144,\n"
            + "	VALIDADE_144,\n"
            + "	CONTINGENCIA_144,\n"
            + "	NAOCLIENTE_INFORMAPROMO_144,\n"
            + "	NAOCLIENTE_VALIDADE_144,\n"
            + "	REGRAS_144,\n"
            + "	NAOCLIENTE_RECARGA_144,\n"
            + "	INCENTIVO_OFERTA_PRE_144,\n"
            + "	FALLBACK_144,\n"
            + "	SVA_144,\n"
            + "	INFORMASALDOPROMO_144,\n"
            + "	OFERTAMIGRADOS_144,\n"
            + "	INFORMAPROMO_USSD_144,\n"
            + "	?,\n"
            + "   DT_GMUD\n"
            + "FROM dual, JSON_TABLE(?, '$' COLUMNS (\n"
            + "	IDBENEFICIO VARCHAR2(2500) PATH '$.idbeneficio',\n"
            + "	IDPROGRAMA VARCHAR2(2500) PATH '$.idprograma',\n"
            + "	DDD VARCHAR2(2500) PATH '$.ddd',\n"
            + "	PROMOPADRAO_USSD VARCHAR2(2500) PATH '$.promopadrao_ussd',\n"
            + "	SEMRECARGA_USSD VARCHAR2(2500) PATH '$.semrecarga_ussd',\n"
            + "	CHAMADA_USSD VARCHAR2(2500) PATH '$.chamada_ussd',\n"
            + "	DETALHES_USSD VARCHAR2(2500) PATH '$.detalhes_ussd',\n"
            + "	RECARGA_USSD VARCHAR2(2500) PATH '$.recarga_ussd',\n"
            + "	INFORMAVALIDADE_USSD VARCHAR2(2500) PATH '$.informavalidade_ussd',\n"
            + "	VALIDADE_USSD VARCHAR2(2500) PATH '$.validade_ussd',\n"
            + "	CONTINGENCIA_USSD VARCHAR2(2500) PATH '$.contingencia_ussd',\n"
            + "	NAOCLIENTE_INFORMAPROMO_USSD VARCHAR2(2500) PATH '$.naocliente_informapromo_ussd',\n"
            + "	NAOCLIENTE_VALIDADE_USSD VARCHAR2(2500) PATH '$.naocliente_validade_ussd',\n"
            + "	REGRAS_USSD VARCHAR2(2500) PATH '$.regras_ussd',\n"
            + "	NAOCLIENTE_RECARGA_USSD VARCHAR2(2500) PATH '$.naocliente_recarga_ussd',\n"
            + "	INCENTIVO_OFERTA_PRE_USSD VARCHAR2(2500) PATH '$.incentivo_oferta_pre_ussd',\n"
            + "	FALLBACK_USSD VARCHAR2(2500) PATH '$.fallback_ussd',\n"
            + "	SVA_USSD VARCHAR2(2500) PATH '$.sva_ussd',\n"
            + "	INFORMASALDOPROMO_USSD VARCHAR2(2500) PATH '$.informasaldopromo_ussd',\n"
            + "	OFERTAMIGRADOS_USSD VARCHAR2(2500) PATH '$.ofertamigrados_ussd',\n"
            + "	INFORMAPROMO_USSD_USSD VARCHAR2(2500) PATH '$.informapromo_ussd_ussd',\n"
            + "	PROMOPADRAO_144 VARCHAR2(2500) PATH '$.promopadrao_144',\n"
            + "	SEMRECARGA_144 VARCHAR2(2500) PATH '$.semrecarga_144',\n"
            + "	CHAMADA_144 VARCHAR2(2500) PATH '$.chamada_144',\n"
            + "	DETALHES_144 VARCHAR2(2500) PATH '$.detalhes_144',\n"
            + "	RECARGA_144 VARCHAR2(2500) PATH '$.recarga_144',\n"
            + "	INFORMAVALIDADE_144 VARCHAR2(2500) PATH '$.informavalidade_144',\n"
            + "	VALIDADE_144 VARCHAR2(2500) PATH '$.validade_144',\n"
            + "	CONTINGENCIA_144 VARCHAR2(2500) PATH '$.contingencia_144',\n"
            + "	NAOCLIENTE_INFORMAPROMO_144 VARCHAR2(2500) PATH '$.naocliente_informapromo_144',\n"
            + "	NAOCLIENTE_VALIDADE_144 VARCHAR2(2500) PATH '$.naocliente_validade_144',\n"
            + "	REGRAS_144 VARCHAR2(2500) PATH '$.regras_144',\n"
            + "	NAOCLIENTE_RECARGA_144 VARCHAR2(2500) PATH '$.naocliente_recarga_144',\n"
            + "	INCENTIVO_OFERTA_PRE_144 VARCHAR2(2500) PATH '$.incentivo_oferta_pre_144',\n"
            + "	FALLBACK_144 VARCHAR2(2500) PATH '$.fallback_144',\n"
            + "	SVA_144 VARCHAR2(2500) PATH '$.sva_144',\n"
            + "	INFORMASALDOPROMO_144 VARCHAR2(2500) PATH '$.informasaldopromo_144',\n"
            + "	OFERTAMIGRADOS_144 VARCHAR2(2500) PATH '$.ofertamigrados_144',\n"
            + "	INFORMAPROMO_USSD_144 VARCHAR2(2500) PATH '$.informapromo_ussd_144',\n"
            + "	DT_GMUD VARCHAR2(2500) PATH '$.dt_gmud'\n"
            + ")))";

    public static final String UPDATE
            = "UPDATE FRASES_CARTAO SET \n"
            + "	IDBENEFICIO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT IDBENEFICIO as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.IDBENEFICIO',json NUMBER(38,0) PATH '$.idbeneficio')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	IDPROGRAMA = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT IDPROGRAMA as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.IDPROGRAMA',json NUMBER(38,0) PATH '$.idprograma')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	DDD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DDD as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DDD',json VARCHAR2(2500) PATH '$.ddd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	PROMOPADRAO_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT PROMOPADRAO_USSD as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.PROMOPADRAO_USSD',json VARCHAR2(2500) PATH '$.promopadrao_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	SEMRECARGA_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT SEMRECARGA_USSD as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.SEMRECARGA_USSD',json VARCHAR2(2500) PATH '$.semrecarga_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	CHAMADA_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT CHAMADA_USSD as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.CHAMADA_USSD',json VARCHAR2(2500) PATH '$.chamada_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	DETALHES_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DETALHES_USSD as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DETALHES_USSD',json VARCHAR2(2500) PATH '$.detalhes_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	RECARGA_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT RECARGA_USSD as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.RECARGA_USSD',json VARCHAR2(2500) PATH '$.recarga_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	INFORMAVALIDADE_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT INFORMAVALIDADE_USSD as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.INFORMAVALIDADE_USSD',json VARCHAR2(2500) PATH '$.informavalidade_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	VALIDADE_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT VALIDADE_USSD as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.VALIDADE_USSD',json VARCHAR2(2500) PATH '$.validade_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	CONTINGENCIA_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT CONTINGENCIA_USSD as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.CONTINGENCIA_USSD',json VARCHAR2(2500) PATH '$.contingencia_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	NAOCLIENTE_INFORMAPROMO_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT NAOCLIENTE_INFORMAPROMO_USSD as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.NAOCLIENTE_INFORMAPROMO_USSD',json VARCHAR2(2500) PATH '$.naocliente_informapromo_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	NAOCLIENTE_VALIDADE_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT NAOCLIENTE_VALIDADE_USSD as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.NAOCLIENTE_VALIDADE_USSD',json VARCHAR2(2500) PATH '$.naocliente_validade_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	REGRAS_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT REGRAS_USSD as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.REGRAS_USSD',json VARCHAR2(2500) PATH '$.regras_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	NAOCLIENTE_RECARGA_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT NAOCLIENTE_RECARGA_USSD as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.NAOCLIENTE_RECARGA_USSD',json VARCHAR2(2500) PATH '$.naocliente_recarga_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	INCENTIVO_OFERTA_PRE_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT INCENTIVO_OFERTA_PRE_USSD as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.INCENTIVO_OFERTA_PRE_USSD',json VARCHAR2(2500) PATH '$.incentivo_oferta_pre_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	FALLBACK_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT FALLBACK_USSD as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.FALLBACK_USSD',json VARCHAR2(2500) PATH '$.fallback_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	SVA_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT SVA_USSD as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.SVA_USSD',json VARCHAR2(2500) PATH '$.sva_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	INFORMASALDOPROMO_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT INFORMASALDOPROMO_USSD as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.INFORMASALDOPROMO_USSD',json VARCHAR2(2500) PATH '$.informasaldopromo_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	OFERTAMIGRADOS_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTAMIGRADOS_USSD as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTAMIGRADOS_USSD',json VARCHAR2(2500) PATH '$.ofertamigrados_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	INFORMAPROMO_USSD_USSD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT INFORMAPROMO_USSD_USSD as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.INFORMAPROMO_USSD_USSD',json VARCHAR2(2500) PATH '$.informapromo_ussd_ussd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	PROMOPADRAO_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT PROMOPADRAO_144 as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.PROMOPADRAO_144',json VARCHAR2(2500) PATH '$.promopadrao_144')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	SEMRECARGA_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT SEMRECARGA_144 as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.SEMRECARGA_144',json VARCHAR2(2500) PATH '$.semrecarga_144')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	CHAMADA_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT CHAMADA_144 as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.CHAMADA_144',json VARCHAR2(2500) PATH '$.chamada_144')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	DETALHES_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DETALHES_144 as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DETALHES_144',json VARCHAR2(2500) PATH '$.detalhes_144')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	RECARGA_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT RECARGA_144 as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.RECARGA_144',json VARCHAR2(2500) PATH '$.recarga_144')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	INFORMAVALIDADE_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT INFORMAVALIDADE_144 as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.INFORMAVALIDADE_144',json VARCHAR2(2500) PATH '$.informavalidade_144')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	VALIDADE_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT VALIDADE_144 as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.VALIDADE_144',json VARCHAR2(2500) PATH '$.validade_144')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	CONTINGENCIA_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT CONTINGENCIA_144 as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.CONTINGENCIA_144',json VARCHAR2(2500) PATH '$.contingencia_144')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	NAOCLIENTE_INFORMAPROMO_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT NAOCLIENTE_INFORMAPROMO_144 as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.NAOCLIENTE_INFORMAPROMO_144',json VARCHAR2(2500) PATH '$.naocliente_informapromo_144')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	NAOCLIENTE_VALIDADE_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT NAOCLIENTE_VALIDADE_144 as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.NAOCLIENTE_VALIDADE_144',json VARCHAR2(2500) PATH '$.naocliente_validade_144')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	REGRAS_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT REGRAS_144 as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.REGRAS_144',json VARCHAR2(2500) PATH '$.regras_144')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	NAOCLIENTE_RECARGA_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT NAOCLIENTE_RECARGA_144 as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.NAOCLIENTE_RECARGA_144',json VARCHAR2(2500) PATH '$.naocliente_recarga_144')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	INCENTIVO_OFERTA_PRE_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT INCENTIVO_OFERTA_PRE_144 as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.INCENTIVO_OFERTA_PRE_144',json VARCHAR2(2500) PATH '$.incentivo_oferta_pre_144')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	FALLBACK_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT FALLBACK_144 as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.FALLBACK_144',json VARCHAR2(2500) PATH '$.fallback_144')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	SVA_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT SVA_144 as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.SVA_144',json VARCHAR2(2500) PATH '$.sva_144')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	INFORMASALDOPROMO_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT INFORMASALDOPROMO_144 as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.INFORMASALDOPROMO_144',json VARCHAR2(2500) PATH '$.informasaldopromo_144')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	OFERTAMIGRADOS_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFERTAMIGRADOS_144 as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFERTAMIGRADOS_144',json VARCHAR2(2500) PATH '$.ofertamigrados_144')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	INFORMAPROMO_USSD_144 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT INFORMAPROMO_USSD_144 as coluna,jsonEdit,json FROM FRASES_CARTAO,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.INFORMAPROMO_USSD_144',json VARCHAR2(2500) PATH '$.informapromo_ussd_144')) where ID = ? order by rownum asc) where rownum = 1)\n"
            + ", DT_GMUD = ? WHERE ID = ?";

    public static final String UPDATEINSERT
            = "INSERT INTO FRASES_CARTAO (\n"
            + "	IDBENEFICIO,\n"
            + "	IDPROGRAMA,\n"
            + "	DDD,\n"
            + "	PROMOPADRAO_USSD,\n"
            + "	SEMRECARGA_USSD,\n"
            + "	CHAMADA_USSD,\n"
            + "	DETALHES_USSD,\n"
            + "	RECARGA_USSD,\n"
            + "	INFORMAVALIDADE_USSD,\n"
            + "	VALIDADE_USSD,\n"
            + "	CONTINGENCIA_USSD,\n"
            + "	NAOCLIENTE_INFORMAPROMO_USSD,\n"
            + "	NAOCLIENTE_VALIDADE_USSD,\n"
            + "	REGRAS_USSD,\n"
            + "	NAOCLIENTE_RECARGA_USSD,\n"
            + "	INCENTIVO_OFERTA_PRE_USSD,\n"
            + "	FALLBACK_USSD,\n"
            + "	SVA_USSD,\n"
            + "	INFORMASALDOPROMO_USSD,\n"
            + "	OFERTAMIGRADOS_USSD,\n"
            + "	INFORMAPROMO_USSD_USSD,\n"
            + "	PROMOPADRAO_144,\n"
            + "	SEMRECARGA_144,\n"
            + "	CHAMADA_144,\n"
            + "	DETALHES_144,\n"
            + "	RECARGA_144,\n"
            + "	INFORMAVALIDADE_144,\n"
            + "	VALIDADE_144,\n"
            + "	CONTINGENCIA_144,\n"
            + "	NAOCLIENTE_INFORMAPROMO_144,\n"
            + "	NAOCLIENTE_VALIDADE_144,\n"
            + "	REGRAS_144,\n"
            + "	NAOCLIENTE_RECARGA_144,\n"
            + "	INCENTIVO_OFERTA_PRE_144,\n"
            + "	FALLBACK_144,\n"
            + "	SVA_144,\n"
            + "	INFORMASALDOPROMO_144,\n"
            + "	OFERTAMIGRADOS_144,\n"
            + "	INFORMAPROMO_USSD_144,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	IDBENEFICIO,\n"
            + "	IDPROGRAMA,\n"
            + "	DDD,\n"
            + "	PROMOPADRAO_USSD,\n"
            + "	SEMRECARGA_USSD,\n"
            + "	CHAMADA_USSD,\n"
            + "	DETALHES_USSD,\n"
            + "	RECARGA_USSD,\n"
            + "	INFORMAVALIDADE_USSD,\n"
            + "	VALIDADE_USSD,\n"
            + "	CONTINGENCIA_USSD,\n"
            + "	NAOCLIENTE_INFORMAPROMO_USSD,\n"
            + "	NAOCLIENTE_VALIDADE_USSD,\n"
            + "	REGRAS_USSD,\n"
            + "	NAOCLIENTE_RECARGA_USSD,\n"
            + "	INCENTIVO_OFERTA_PRE_USSD,\n"
            + "	FALLBACK_USSD,\n"
            + "	SVA_USSD,\n"
            + "	INFORMASALDOPROMO_USSD,\n"
            + "	OFERTAMIGRADOS_USSD,\n"
            + "	INFORMAPROMO_USSD_USSD,\n"
            + "	PROMOPADRAO_144,\n"
            + "	SEMRECARGA_144,\n"
            + "	CHAMADA_144,\n"
            + "	DETALHES_144,\n"
            + "	RECARGA_144,\n"
            + "	INFORMAVALIDADE_144,\n"
            + "	VALIDADE_144,\n"
            + "	CONTINGENCIA_144,\n"
            + "	NAOCLIENTE_INFORMAPROMO_144,\n"
            + "	NAOCLIENTE_VALIDADE_144,\n"
            + "	REGRAS_144,\n"
            + "	NAOCLIENTE_RECARGA_144,\n"
            + "	INCENTIVO_OFERTA_PRE_144,\n"
            + "	FALLBACK_144,\n"
            + "	SVA_144,\n"
            + "	INFORMASALDOPROMO_144,\n"
            + "	OFERTAMIGRADOS_144,\n"
            + "	INFORMAPROMO_USSD_144,\n"
            + "	? HISTORY,\n"
            + "	? DT_GMUD,\n"
            + "	ID\n"
            + "FROM FRASES_CARTAO WHERE ID = ?)";

    public static final String DELETE
            = "INSERT INTO FRASES_CARTAO (\n"
            + "	IDBENEFICIO,\n"
            + "	IDPROGRAMA,\n"
            + "	DDD,\n"
            + "	PROMOPADRAO_USSD,\n"
            + "	SEMRECARGA_USSD,\n"
            + "	CHAMADA_USSD,\n"
            + "	DETALHES_USSD,\n"
            + "	RECARGA_USSD,\n"
            + "	INFORMAVALIDADE_USSD,\n"
            + "	VALIDADE_USSD,\n"
            + "	CONTINGENCIA_USSD,\n"
            + "	NAOCLIENTE_INFORMAPROMO_USSD,\n"
            + "	NAOCLIENTE_VALIDADE_USSD,\n"
            + "	REGRAS_USSD,\n"
            + "	NAOCLIENTE_RECARGA_USSD,\n"
            + "	INCENTIVO_OFERTA_PRE_USSD,\n"
            + "	FALLBACK_USSD,\n"
            + "	SVA_USSD,\n"
            + "	INFORMASALDOPROMO_USSD,\n"
            + "	OFERTAMIGRADOS_USSD,\n"
            + "	INFORMAPROMO_USSD_USSD,\n"
            + "	PROMOPADRAO_144,\n"
            + "	SEMRECARGA_144,\n"
            + "	CHAMADA_144,\n"
            + "	DETALHES_144,\n"
            + "	RECARGA_144,\n"
            + "	INFORMAVALIDADE_144,\n"
            + "	VALIDADE_144,\n"
            + "	CONTINGENCIA_144,\n"
            + "	NAOCLIENTE_INFORMAPROMO_144,\n"
            + "	NAOCLIENTE_VALIDADE_144,\n"
            + "	REGRAS_144,\n"
            + "	NAOCLIENTE_RECARGA_144,\n"
            + "	INCENTIVO_OFERTA_PRE_144,\n"
            + "	FALLBACK_144,\n"
            + "	SVA_144,\n"
            + "	INFORMASALDOPROMO_144,\n"
            + "	OFERTAMIGRADOS_144,\n"
            + "	INFORMAPROMO_USSD_144,\n"
            + "	HISTORY,\n"
            + "	DELETED,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	IDBENEFICIO,\n"
            + "	IDPROGRAMA,\n"
            + "	DDD,\n"
            + "	PROMOPADRAO_USSD,\n"
            + "	SEMRECARGA_USSD,\n"
            + "	CHAMADA_USSD,\n"
            + "	DETALHES_USSD,\n"
            + "	RECARGA_USSD,\n"
            + "	INFORMAVALIDADE_USSD,\n"
            + "	VALIDADE_USSD,\n"
            + "	CONTINGENCIA_USSD,\n"
            + "	NAOCLIENTE_INFORMAPROMO_USSD,\n"
            + "	NAOCLIENTE_VALIDADE_USSD,\n"
            + "	REGRAS_USSD,\n"
            + "	NAOCLIENTE_RECARGA_USSD,\n"
            + "	INCENTIVO_OFERTA_PRE_USSD,\n"
            + "	FALLBACK_USSD,\n"
            + "	SVA_USSD,\n"
            + "	INFORMASALDOPROMO_USSD,\n"
            + "	OFERTAMIGRADOS_USSD,\n"
            + "	INFORMAPROMO_USSD_USSD,\n"
            + "	PROMOPADRAO_144,\n"
            + "	SEMRECARGA_144,\n"
            + "	CHAMADA_144,\n"
            + "	DETALHES_144,\n"
            + "	RECARGA_144,\n"
            + "	INFORMAVALIDADE_144,\n"
            + "	VALIDADE_144,\n"
            + "	CONTINGENCIA_144,\n"
            + "	NAOCLIENTE_INFORMAPROMO_144,\n"
            + "	NAOCLIENTE_VALIDADE_144,\n"
            + "	REGRAS_144,\n"
            + "	NAOCLIENTE_RECARGA_144,\n"
            + "	INCENTIVO_OFERTA_PRE_144,\n"
            + "	FALLBACK_144,\n"
            + "	SVA_144,\n"
            + "	INFORMASALDOPROMO_144,\n"
            + "	OFERTAMIGRADOS_144,\n"
            + "	INFORMAPROMO_USSD_144,\n"
            + "	?,\n"
            + "	1,\n"
            + "	?, \n"
            + "	ID \n"
            + "FROM FRASES_CARTAO WHERE ID IN (?))";

    public static final String APROVAR
            = "t1.IDBENEFICIO = t2.IDBENEFICIO,\n"
            + "t1.IDPROGRAMA = t2.IDPROGRAMA,\n"
            + "t1.DDD = t2.DDD,\n"
            + "t1.PROMOPADRAO_USSD = t2.PROMOPADRAO_USSD,\n"
            + "t1.SEMRECARGA_USSD = t2.SEMRECARGA_USSD,\n"
            + "t1.CHAMADA_USSD = t2.CHAMADA_USSD,\n"
            + "t1.DETALHES_USSD = t2.DETALHES_USSD,\n"
            + "t1.RECARGA_USSD = t2.RECARGA_USSD,\n"
            + "t1.INFORMAVALIDADE_USSD = t2.INFORMAVALIDADE_USSD,\n"
            + "t1.VALIDADE_USSD = t2.VALIDADE_USSD,\n"
            + "t1.CONTINGENCIA_USSD = t2.CONTINGENCIA_USSD,\n"
            + "t1.NAOCLIENTE_INFORMAPROMO_USSD = t2.NAOCLIENTE_INFORMAPROMO_USSD,\n"
            + "t1.NAOCLIENTE_VALIDADE_USSD = t2.NAOCLIENTE_VALIDADE_USSD,\n"
            + "t1.REGRAS_USSD = t2.REGRAS_USSD,\n"
            + "t1.NAOCLIENTE_RECARGA_USSD = t2.NAOCLIENTE_RECARGA_USSD,\n"
            + "t1.INCENTIVO_OFERTA_PRE_USSD = t2.INCENTIVO_OFERTA_PRE_USSD,\n"
            + "t1.FALLBACK_USSD = t2.FALLBACK_USSD,\n"
            + "t1.SVA_USSD = t2.SVA_USSD,\n"
            + "t1.INFORMASALDOPROMO_USSD = t2.INFORMASALDOPROMO_USSD,\n"
            + "t1.OFERTAMIGRADOS_USSD = t2.OFERTAMIGRADOS_USSD,\n"
            + "t1.INFORMAPROMO_USSD_USSD = t2.INFORMAPROMO_USSD_USSD,\n"
            + "t1.PROMOPADRAO_144 = t2.PROMOPADRAO_144,\n"
            + "t1.SEMRECARGA_144 = t2.SEMRECARGA_144,\n"
            + "t1.CHAMADA_144 = t2.CHAMADA_144,\n"
            + "t1.DETALHES_144 = t2.DETALHES_144,\n"
            + "t1.RECARGA_144 = t2.RECARGA_144,\n"
            + "t1.INFORMAVALIDADE_144 = t2.INFORMAVALIDADE_144,\n"
            + "t1.VALIDADE_144 = t2.VALIDADE_144,\n"
            + "t1.CONTINGENCIA_144 = t2.CONTINGENCIA_144,\n"
            + "t1.NAOCLIENTE_INFORMAPROMO_144 = t2.NAOCLIENTE_INFORMAPROMO_144,\n"
            + "t1.NAOCLIENTE_VALIDADE_144 = t2.NAOCLIENTE_VALIDADE_144,\n"
            + "t1.REGRAS_144 = t2.REGRAS_144,\n"
            + "t1.NAOCLIENTE_RECARGA_144 = t2.NAOCLIENTE_RECARGA_144,\n"
            + "t1.INCENTIVO_OFERTA_PRE_144 = t2.INCENTIVO_OFERTA_PRE_144,\n"
            + "t1.FALLBACK_144 = t2.FALLBACK_144,\n"
            + "t1.SVA_144 = t2.SVA_144,\n"
            + "t1.INFORMASALDOPROMO_144 = t2.INFORMASALDOPROMO_144,\n"
            + "t1.OFERTAMIGRADOS_144 = t2.OFERTAMIGRADOS_144,\n"
            + "t1.INFORMAPROMO_USSD_144 = t2.INFORMAPROMO_USSD_144,\n"
            + "t1.HISTORY = t2.HISTORY";

}