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
public class TabelaConfigOfertasPre {

    public static final String SELECT
            = "SELECT JSON_QUERY(\n"
            + " concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(\n"
            + " concat(concat('{\"id_ofertaocs\":\"',ID_OFERTAOCS),'\",'),\n"
            + " concat(concat('\"id_campanhasiebel\":\"',ID_CAMPANHASIEBEL),'\",')),\n"
            + " concat(concat('\"id_ofertasiebel\":\"',ID_OFERTASIEBEL),'\",')),\n"
            + " concat(concat('\"ddd\":\"',DDD),'\",')),\n"
            + " concat(concat('\"data_inic\":\"',DATA_INIC),'\",')),\n"
            + " concat(concat('\"data_fim\":\"',DATA_FIM),'\",')),\n"
            + " concat(concat('\"tipo_oferta\":\"',TIPO_OFERTA),'\",')),\n"
            + " concat(concat('\"elegivelconversao\":\"',ELEGIVELCONVERSAO),'\",')),\n"
            + " concat(concat('\"freeunitvozelegivel\":\"',FREEUNITVOZELEGIVEL),'\",')),\n"
            + " concat(concat('\"freeunitdadoselegivel\":\"',FREEUNITDADOSELEGIVEL),'\",')),\n"
            + " concat(concat('\"limmaxconvmin\":\"',LIMMAXCONVMIN),'\",')),\n"
            + " concat(concat('\"limmaxconvmb\":\"',LIMMAXCONVMB),'\",')),\n"
            + " concat(concat('\"saldomindadosconvmb\":\"',SALDOMINDADOSCONVMB),'\",')),\n"
            + " concat(concat('\"valor_taxaadesao\":\"',VALOR_TAXAADESAO),'\",')),\n"
            + " concat(concat('\"existefallback\":\"',EXISTEFALLBACK),'\",')),\n"
            + " concat(concat('\"existerollover\":\"',EXISTEROLLOVER),'\",')),\n"
            + " concat(concat('\"short_code\":\"',SHORT_CODE),'\",')),\n"
            + " concat(concat('\"ussd_convertido\":\"',USSD_CONVERTIDO),'\",')),\n"
            + " concat(concat('\"id_oferta_rentcycle\":\"',ID_OFERTA_RENTCYCLE),'\",')),\n"
            + " concat(concat('\"id_beneficio\":\"',ID_BENEFICIO),'\",')),\n"
            + " concat(concat('\"id_programa\":\"',ID_PROGRAMA),'\"}'))\n"
            + ",'$') as json,ID,TEMPID,HISTORY,INSERTED,DT_GMUD,DELETED FROM CONFIG_OFERTASPRE t WHERE (DT_GMUD IS NULL AND DELETED = 0) OR (DT_GMUD IS NOT NULL) ORDER BY INSERTED DESC NULLS LAST";

    public static final String INSERT
            = "INSERT INTO CONFIG_OFERTASPRE (\n"
            + "	ID_BFPG,\n"
            + "	ID_OFERTAOCS,\n"
            + "	ID_CAMPANHASIEBEL,\n"
            + "	ID_OFERTASIEBEL,\n"
            + "	DDD,\n"
            + "	DATA_INIC,\n"
            + "	DATA_FIM,\n"
            + "	TIPO_OFERTA,\n"
            + "	ELEGIVELCONVERSAO,\n"
            + "	FREEUNITVOZELEGIVEL,\n"
            + "	FREEUNITDADOSELEGIVEL,\n"
            + "	LIMMAXCONVMIN,\n"
            + "	LIMMAXCONVMB,\n"
            + "	SALDOMINDADOSCONVMB,\n"
            + "	VALOR_TAXAADESAO,\n"
            + "	EXISTEFALLBACK,\n"
            + "	EXISTEROLLOVER,\n"
            + "	SHORT_CODE,\n"
            + " USSD_CONVERTIDO,\n"
            + " ID_OFERTA_RENTCYCLE,\n"
            + " ID_BENEFICIO,\n"
            + " ID_PROGRAMA,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD\n"
            + ")(SELECT \n"
            + "	ID_BFPG,\n"
            + "	ID_OFERTAOCS,\n"
            + "	ID_CAMPANHASIEBEL,\n"
            + "	ID_OFERTASIEBEL,\n"
            + "	DDD,\n"
            + "	DATA_INIC,\n"
            + "	DATA_FIM,\n"
            + "	TIPO_OFERTA,\n"
            + "	ELEGIVELCONVERSAO,\n"
            + "	FREEUNITVOZELEGIVEL,\n"
            + "	FREEUNITDADOSELEGIVEL,\n"
            + "	LIMMAXCONVMIN,\n"
            + "	LIMMAXCONVMB,\n"
            + "	SALDOMINDADOSCONVMB,\n"
            + "	VALOR_TAXAADESAO,\n"
            + "	EXISTEFALLBACK,\n"
            + "	EXISTEROLLOVER,\n"
            + "	SHORT_CODE,\n"
            + " USSD_CONVERTIDO,\n"
            + "	ID_OFERTA_RENTCYCLE,\n"
            + "	ID_BENEFICIO,\n"
            + "	ID_PROGRAMA,\n"
            + "	?,\n"
            + "   DT_GMUD\n"
            + "FROM dual, JSON_TABLE(?, '$' COLUMNS (\n"
            + "	ID_BFPG VARCHAR2(2500) PATH '$.id_bfpg',\n"
            + "	ID_OFERTAOCS VARCHAR2(2500) PATH '$.id_ofertaocs',\n"
            + "	ID_CAMPANHASIEBEL VARCHAR2(2500) PATH '$.id_campanhasiebel',\n"
            + "	ID_OFERTASIEBEL VARCHAR2(2500) PATH '$.id_ofertasiebel',\n"
            + "	DDD VARCHAR2(2500) PATH '$.ddd',\n"
            + "	DATA_INIC VARCHAR2(2500) PATH '$.data_inic',\n"
            + "	DATA_FIM VARCHAR2(2500) PATH '$.data_fim',\n"
            + "	TIPO_OFERTA VARCHAR2(2500) PATH '$.tipo_oferta',\n"
            + "	ELEGIVELCONVERSAO VARCHAR2(2500) PATH '$.elegivelconversao',\n"
            + "	FREEUNITVOZELEGIVEL VARCHAR2(2500) PATH '$.freeunitvozelegivel',\n"
            + "	FREEUNITDADOSELEGIVEL VARCHAR2(2500) PATH '$.freeunitdadoselegivel',\n"
            + "	LIMMAXCONVMIN VARCHAR2(2500) PATH '$.limmaxconvmin',\n"
            + "	LIMMAXCONVMB VARCHAR2(2500) PATH '$.limmaxconvmb',\n"
            + "	SALDOMINDADOSCONVMB VARCHAR2(2500) PATH '$.saldomindadosconvmb',\n"
            + "	VALOR_TAXAADESAO VARCHAR2(2500) PATH '$.valor_taxaadesao',\n"
            + "	EXISTEFALLBACK VARCHAR2(2500) PATH '$.existefallback',\n"
            + "	EXISTEROLLOVER VARCHAR2(2500) PATH '$.existerollover',\n"
            + "	SHORT_CODE VARCHAR2(2500) PATH '$.short_code',\n"
            + "	USSD_CONVERTIDO VARCHAR2(2500) PATH '$.ussd_convertido',\n"
            + "	ID_OFERTA_RENTCYCLE VARCHAR2(2500) PATH '$.id_oferta_rentcycle',\n"
            + "	ID_BENEFICIO VARCHAR2(2500) PATH '$.id_beneficio',\n"
            + "	ID_PROGRAMA VARCHAR2(2500) PATH '$.id_programa',\n"
            + "	DT_GMUD VARCHAR2(2500) PATH '$.dt_gmud'\n"
            + ")))";

    public static final String UPDATE
            = "UPDATE CONFIG_OFERTASPRE SET \n"
            + "	ID_BFPG = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT ID_BFPG as coluna,jsonEdit,json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.ID_BFPG',json VARCHAR2(2500) PATH '$.id_bfpg')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	ID_OFERTAOCS = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT ID_OFERTAOCS as coluna,jsonEdit,json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.ID_OFERTAOCS',json NUMBER(38,0) PATH '$.id_ofertaocs')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	ID_CAMPANHASIEBEL = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT ID_CAMPANHASIEBEL as coluna,jsonEdit,json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.ID_CAMPANHASIEBEL',json VARCHAR2(2500) PATH '$.id_campanhasiebel')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	ID_OFERTASIEBEL = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT ID_OFERTASIEBEL as coluna,jsonEdit,json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.ID_OFERTASIEBEL',json VARCHAR2(2500) PATH '$.id_ofertasiebel')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	DDD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DDD as coluna,jsonEdit,json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DDD',json VARCHAR2(2500) PATH '$.ddd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	DATA_INIC = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DATA_INIC as coluna,jsonEdit,TO_DATE(json,'YY/MM/DD') as json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DATA_INIC',json DATE PATH '$.data_inic')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	DATA_FIM = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT DATA_FIM as coluna,jsonEdit,TO_DATE(json,'YY/MM/DD') as json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.DATA_FIM',json DATE PATH '$.data_fim')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	TIPO_OFERTA = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TIPO_OFERTA as coluna,jsonEdit,json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TIPO_OFERTA',json VARCHAR2(2500) PATH '$.tipo_oferta')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	ELEGIVELCONVERSAO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT ELEGIVELCONVERSAO as coluna,jsonEdit,json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.ELEGIVELCONVERSAO',json VARCHAR2(2500) PATH '$.elegivelconversao')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	FREEUNITVOZELEGIVEL = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT FREEUNITVOZELEGIVEL as coluna,jsonEdit,json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.FREEUNITVOZELEGIVEL',json VARCHAR2(2500) PATH '$.freeunitvozelegivel')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	FREEUNITDADOSELEGIVEL = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT FREEUNITDADOSELEGIVEL as coluna,jsonEdit,json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.FREEUNITDADOSELEGIVEL',json VARCHAR2(2500) PATH '$.freeunitdadoselegivel')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	LIMMAXCONVMIN = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT LIMMAXCONVMIN as coluna,jsonEdit,json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.LIMMAXCONVMIN',json NUMBER(38,0) PATH '$.limmaxconvmin')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	LIMMAXCONVMB = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT LIMMAXCONVMB as coluna,jsonEdit,json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.LIMMAXCONVMB',json NUMBER(38,0) PATH '$.limmaxconvmb')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	SALDOMINDADOSCONVMB = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT SALDOMINDADOSCONVMB as coluna,jsonEdit,json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.SALDOMINDADOSCONVMB',json NUMBER(38,0) PATH '$.saldomindadosconvmb')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	VALOR_TAXAADESAO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT VALOR_TAXAADESAO as coluna,jsonEdit,json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.VALOR_TAXAADESAO',json VARCHAR2(2500) PATH '$.valor_taxaadesao')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	EXISTEFALLBACK = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT EXISTEFALLBACK as coluna,jsonEdit,json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.EXISTEFALLBACK',json VARCHAR2(2500) PATH '$.existefallback')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	EXISTEROLLOVER = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT EXISTEROLLOVER as coluna,jsonEdit,json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.EXISTEROLLOVER',json VARCHAR2(2500) PATH '$.existerollover')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	SHORT_CODE = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT SHORT_CODE as coluna,jsonEdit,json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.SHORT_CODE',json NUMBER(38,0) PATH '$.short_code')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	USSD_CONVERTIDO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT USSD_CONVERTIDO as coluna,jsonEdit,json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.USSD_CONVERTIDO',json VARCHAR2(2500) PATH '$.ussd_convertido')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	ID_OFERTA_RENTCYCLE = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT ID_OFERTA_RENTCYCLE as coluna,jsonEdit,json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.ID_OFERTA_RENTCYCLE',json VARCHAR2(2500) PATH '$.id_oferta_rentcycle')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	ID_BENEFICIO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT ID_BENEFICIO as coluna,jsonEdit,json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.ID_BENEFICIO',json VARCHAR2(2500) PATH '$.id_beneficio')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + "	ID_PROGRAMA = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT ID_PROGRAMA as coluna,jsonEdit,json FROM CONFIG_OFERTASPRE,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.ID_PROGRAMA',json VARCHAR2(2500) PATH '$.id_programa')) where ID = ? order by rownum asc) where rownum = 1)\n"
            + ", DT_GMUD = ? WHERE ID = ?";

    public static final String UPDATEINSERT
            = "INSERT INTO CONFIG_OFERTASPRE (\n"
            + "	ID_BFPG,\n"
            + "	ID_OFERTAOCS,\n"
            + "	ID_CAMPANHASIEBEL,\n"
            + "	ID_OFERTASIEBEL,\n"
            + "	DDD,\n"
            + "	DATA_INIC,\n"
            + "	DATA_FIM,\n"
            + "	TIPO_OFERTA,\n"
            + "	ELEGIVELCONVERSAO,\n"
            + "	FREEUNITVOZELEGIVEL,\n"
            + "	FREEUNITDADOSELEGIVEL,\n"
            + "	LIMMAXCONVMIN,\n"
            + "	LIMMAXCONVMB,\n"
            + "	SALDOMINDADOSCONVMB,\n"
            + "	VALOR_TAXAADESAO,\n"
            + "	EXISTEFALLBACK,\n"
            + "	EXISTEROLLOVER,\n"
            + "	SHORT_CODE,\n"
            + " USSD_CONVERTIDO,\n"
            + " ID_OFERTA_RENTCYCLE,\n"
            + " ID_BENEFICIO,\n"
            + " ID_PROGRAMA,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	ID_BFPG,\n"
            + "	ID_OFERTAOCS,\n"
            + "	ID_CAMPANHASIEBEL,\n"
            + "	ID_OFERTASIEBEL,\n"
            + "	DDD,\n"
            + "	DATA_INIC,\n"
            + "	DATA_FIM,\n"
            + "	TIPO_OFERTA,\n"
            + "	ELEGIVELCONVERSAO,\n"
            + "	FREEUNITVOZELEGIVEL,\n"
            + "	FREEUNITDADOSELEGIVEL,\n"
            + "	LIMMAXCONVMIN,\n"
            + "	LIMMAXCONVMB,\n"
            + "	SALDOMINDADOSCONVMB,\n"
            + "	VALOR_TAXAADESAO,\n"
            + "	EXISTEFALLBACK,\n"
            + "	EXISTEROLLOVER,\n"
            + "	SHORT_CODE,\n"
            + " USSD_CONVERTIDO,\n"
            + " ID_OFERTA_RENTCYCLE,\n"
            + " ID_BENEFICIO,\n"
            + " ID_PROGRAMA,\n"
            + "	? HISTORY,\n"
            + "	? DT_GMUD,\n"
            + "	ID\n"
            + "FROM CONFIG_OFERTASPRE WHERE ID = ?)";

    public static final String DELETE
            = "INSERT INTO CONFIG_OFERTASPRE (\n"
            + "	ID_BFPG,\n"
            + "	ID_OFERTAOCS,\n"
            + "	ID_CAMPANHASIEBEL,\n"
            + "	ID_OFERTASIEBEL,\n"
            + "	DDD,\n"
            + "	DATA_INIC,\n"
            + "	DATA_FIM,\n"
            + "	TIPO_OFERTA,\n"
            + "	ELEGIVELCONVERSAO,\n"
            + "	FREEUNITVOZELEGIVEL,\n"
            + "	FREEUNITDADOSELEGIVEL,\n"
            + "	LIMMAXCONVMIN,\n"
            + "	LIMMAXCONVMB,\n"
            + "	SALDOMINDADOSCONVMB,\n"
            + "	VALOR_TAXAADESAO,\n"
            + "	EXISTEFALLBACK,\n"
            + "	EXISTEROLLOVER,\n"
            + "	SHORT_CODE,\n"
            + " USSD_CONVERTIDO,\n"
            + " ID_OFERTA_RENTCYCLE,\n"
            + " ID_BENEFICIO,\n"
            + " ID_PROGRAMA,\n"
            + "	HISTORY,\n"
            + "	DELETED,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + "	ID_BFPG,\n"
            + "	ID_OFERTAOCS,\n"
            + "	ID_CAMPANHASIEBEL,\n"
            + "	ID_OFERTASIEBEL,\n"
            + "	DDD,\n"
            + "	DATA_INIC,\n"
            + "	DATA_FIM,\n"
            + "	TIPO_OFERTA,\n"
            + "	ELEGIVELCONVERSAO,\n"
            + "	FREEUNITVOZELEGIVEL,\n"
            + "	FREEUNITDADOSELEGIVEL,\n"
            + "	LIMMAXCONVMIN,\n"
            + "	LIMMAXCONVMB,\n"
            + "	SALDOMINDADOSCONVMB,\n"
            + "	VALOR_TAXAADESAO,\n"
            + "	EXISTEFALLBACK,\n"
            + "	EXISTEROLLOVER,\n"
            + "	SHORT_CODE,\n"
            + " USSD_CONVERTIDO,\n"
            + " ID_OFERTA_RENTCYCLE,\n"
            + " ID_BENEFICIO,\n"
            + " ID_PROGRAMA,\n"
            + "	?,\n"
            + "	1,\n"
            + "	?, \n"
            + "	ID \n"
            + "FROM CONFIG_OFERTASPRE WHERE ID IN (?))";

    public static final String APROVAR
            = "t1.ID_BFPG = t2.ID_BFPG,\n"
            + "t1.ID_CAMPANHASIEBEL = t2.ID_CAMPANHASIEBEL,\n"
            + "t1.ID_OFERTASIEBEL = t2.ID_OFERTASIEBEL,\n"
            + "t1.DATA_INIC = t2.DATA_INIC,\n"
            + "t1.DATA_FIM = t2.DATA_FIM,\n"
            + "t1.TIPO_OFERTA = t2.TIPO_OFERTA,\n"
            + "t1.ELEGIVELCONVERSAO = t2.ELEGIVELCONVERSAO,\n"
            + "t1.FREEUNITVOZELEGIVEL = t2.FREEUNITVOZELEGIVEL,\n"
            + "t1.FREEUNITDADOSELEGIVEL = t2.FREEUNITDADOSELEGIVEL,\n"
            + "t1.LIMMAXCONVMIN = t2.LIMMAXCONVMIN,\n"
            + "t1.LIMMAXCONVMB = t2.LIMMAXCONVMB,\n"
            + "t1.SALDOMINDADOSCONVMB = t2.SALDOMINDADOSCONVMB,\n"
            + "t1.VALOR_TAXAADESAO = t2.VALOR_TAXAADESAO,\n"
            + "t1.EXISTEFALLBACK = t2.EXISTEFALLBACK,\n"
            + "t1.EXISTEROLLOVER = t2.EXISTEROLLOVER,\n"
            + "t1.SHORT_CODE = t2.SHORT_CODE,\n"
            + "t1.USSD_CONVERTIDO = t2.USSD_CONVERTIDO,\n"
            + "t1.ID_OFERTA_RENTCYCLE = t2.ID_OFERTA_RENTCYCLE,\n"
            + "t1.ID_BENEFICIO = t2.ID_BENEFICIO,\n"
            + "t1.ID_PROGRAMA = t2.ID_PROGRAMA,\n"
            + "t1.HISTORY = t2.HISTORY";

}
