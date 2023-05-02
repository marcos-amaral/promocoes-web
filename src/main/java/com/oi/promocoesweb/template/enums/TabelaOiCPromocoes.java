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
public class TabelaOiCPromocoes {

    public static final String SELECT
            = "SELECT JSON_QUERY(\n"
            + " concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(\n"
            + " concat(concat('{\"tb_id_beneficio\":\"',TB_ID_BENEFICIO),'\",'),\n"
            + " concat(concat('\"tb_id_programa\":\"',TB_ID_PROGRAMA),'\",')),\n"
            + " concat(concat('\"tb_ddd\":\"',TB_DDD),'\",')),\n"
            + " concat(concat('\"tb_regiao\":\"',TB_REGIAO),'\",')),\n"
            + " concat(concat('\"incentivo1\":\"',INCENTIVO1),'\",')),\n"
            + " concat(concat('\"incentivo2\":\"',INCENTIVO2),'\",')),\n"
            + " concat(concat('\"outrosplanos\":\"',OUTROSPLANOS),'\",')),\n"
            + " concat(concat('\"offercode\":\"',OFFERCODE),'\",')),\n"
            + " concat(concat('\"tb_plano\":\"',TB_PLANO),'\",')),\n"
            + " concat(concat('\"tb_nome_promocao\":\"',TB_NOME_PROMOCAO),'\",')),\n"
            + " concat(concat('\"tb_tipo_promo\":\"',TB_TIPO_PROMO),'\",')),\n"
            + " concat(concat('\"tb_sn_elegivel_nova_promo\":\"',TB_SN_ELEGIVEL_NOVA_PROMO),'\",')),\n"
            + " concat(concat('\"tb_taxa_adesao\":\"',TB_TAXA_ADESAO),'\",')),\n"
            + " concat(concat('\"tb_dt_inicio_promo\":\"',TB_DT_INICIO_PROMO),'\",')),\n"
            + " concat(concat('\"tb_dt_fim_promo\":\"',TB_DT_FIM_PROMO),'\",')),\n"
            + " concat(concat('\"tb_tipo_bonus\":\"',TB_TIPO_BONUS),'\",')),\n"
            + " concat(concat('\"tb_franquia\":\"',TB_FRANQUIA),'\",')),\n"
            + " concat(concat('\"tb_bonus\":\"',TB_BONUS),'\",')),\n"
            + " concat(concat('\"tb_sn_bonus_torpedo\":\"',TB_SN_BONUS_TORPEDO),'\",')),\n"
            + " concat(concat('\"tb_qtd_torp\":\"',TB_QTD_TORP),'\",')),\n"
            + " concat(concat('\"tb_bonus_torp\":\"',TB_BONUS_TORP),'\",')),\n"
            + " concat(concat('\"sn_oferta_sem_franquia\":\"',SN_OFERTA_SEM_FRANQUIA),'\",')),\n"
            + " concat(concat('\"tb_ligacaooimovelfixo_dados\":\"',TB_LIGACAOOIMOVELFIXO_DADOS),'\",')),\n"
            + " concat(concat('\"tb_bonusligacaomix_dados\":\"',TB_BONUSLIGACAOMIX_DADOS),'\",')),\n"
            + " concat(concat('\"tb_ussd_convertido\":\"',TB_USSD_CONVERTIDO),'\"}'))\n"
            + ",'$') as json,ID,TEMPID,HISTORY,INSERTED,DT_GMUD,DELETED FROM TB_OICPROMOCOES t WHERE (DT_GMUD IS NULL AND DELETED = 0) OR (DT_GMUD IS NOT NULL) ORDER BY INSERTED DESC NULLS LAST";

    public static final String INSERT
            = "INSERT INTO TB_OICPROMOCOES (\n"
            + " TB_ID_BENEFICIO,\n"
            + " TB_ID_PROGRAMA,\n"
            + " TB_DDD,\n"
            + " TB_REGIAO,\n"
            + " INCENTIVO1,\n"
            + " INCENTIVO2,\n"
            + " OUTROSPLANOS,\n"
            + " OFFERCODE,\n"
            + " TB_PLANO,\n"
            + " TB_NOME_PROMOCAO,\n"
            + " TB_TIPO_PROMO,\n"
            + " TB_SN_ELEGIVEL_NOVA_PROMO,\n"
            + " TB_TAXA_ADESAO,\n"
            + " TB_DT_INICIO_PROMO,\n"
            + " TB_DT_FIM_PROMO,\n"
            + " TB_TIPO_BONUS,\n"
            + " TB_FRANQUIA,\n"
            + " TB_BONUS,\n"
            + " TB_SN_BONUS_TORPEDO,\n"
            + " TB_QTD_TORP,\n"
            + " TB_BONUS_TORP,\n"
            + " SN_OFERTA_SEM_FRANQUIA,\n"
            + " TB_LIGACAOOIMOVELFIXO_DADOS,\n"
            + " TB_BONUSLIGACAOMIX_DADOS,\n"
            + " TB_USSD_CONVERTIDO,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD\n"
            + ")(SELECT \n"
            + " TB_ID_BENEFICIO,\n"
            + " TB_ID_PROGRAMA,\n"
            + " TB_DDD,\n"
            + " TB_REGIAO,\n"
            + " INCENTIVO1,\n"
            + " INCENTIVO2,\n"
            + " OUTROSPLANOS,\n"
            + " OFFERCODE,\n"
            + " TB_PLANO,\n"
            + " TB_NOME_PROMOCAO,\n"
            + " TB_TIPO_PROMO,\n"
            + " TB_SN_ELEGIVEL_NOVA_PROMO,\n"
            + " TB_TAXA_ADESAO,\n"
            + " TB_DT_INICIO_PROMO,\n"
            + " TB_DT_FIM_PROMO,\n"
            + " TB_TIPO_BONUS,\n"
            + " TB_FRANQUIA,\n"
            + " TB_BONUS,\n"
            + " TB_SN_BONUS_TORPEDO,\n"
            + " TB_QTD_TORP,\n"
            + " TB_BONUS_TORP,\n"
            + " SN_OFERTA_SEM_FRANQUIA,\n"
            + " TB_LIGACAOOIMOVELFIXO_DADOS,\n"
            + " TB_BONUSLIGACAOMIX_DADOS,\n"
            + " TB_USSD_CONVERTIDO,\n"
            + "	?,\n"
            + "   DT_GMUD\n"
            + "FROM dual, JSON_TABLE(?, '$' COLUMNS (\n"
            + " TB_ID_BENEFICIO VARCHAR2(2500) PATH '$.tb_id_beneficio',\n"
            + " TB_ID_PROGRAMA VARCHAR2(2500) PATH '$.tb_id_programa',\n"
            + " TB_DDD VARCHAR2(2500) PATH '$.tb_ddd',\n"
            + " TB_REGIAO VARCHAR2(2500) PATH '$.tb_regiao',\n"
            + " INCENTIVO1 VARCHAR2(2500) PATH '$.incentivo1',\n"
            + " INCENTIVO2 VARCHAR2(2500) PATH '$.incentivo2',\n"
            + " OUTROSPLANOS VARCHAR2(2500) PATH '$.outrosplanos',\n"
            + " OFFERCODE VARCHAR2(2500) PATH '$.offercode',\n"
            + " TB_PLANO VARCHAR2(2500) PATH '$.tb_plano',\n"
            + " TB_NOME_PROMOCAO VARCHAR2(2500) PATH '$.tb_nome_promocao',\n"
            + " TB_TIPO_PROMO VARCHAR2(2500) PATH '$.tb_tipo_promo',\n"
            + " TB_SN_ELEGIVEL_NOVA_PROMO VARCHAR2(2500) PATH '$.tb_sn_elegivel_nova_promo',\n"
            + " TB_TAXA_ADESAO VARCHAR2(2500) PATH '$.tb_taxa_adesao',\n"
            + " TB_DT_INICIO_PROMO VARCHAR2(2500) PATH '$.tb_dt_inicio_promo',\n"
            + " TB_DT_FIM_PROMO VARCHAR2(2500) PATH '$.tb_dt_fim_promo',\n"
            + " TB_TIPO_BONUS VARCHAR2(2500) PATH '$.tb_tipo_bonus',\n"
            + " TB_FRANQUIA VARCHAR2(2500) PATH '$.tb_franquia',\n"
            + " TB_BONUS VARCHAR2(2500) PATH '$.tb_bonus',\n"
            + " TB_SN_BONUS_TORPEDO VARCHAR2(2500) PATH '$.tb_sn_bonus_torpedo',\n"
            + " TB_QTD_TORP VARCHAR2(2500) PATH '$.tb_qtd_torp',\n"
            + " TB_BONUS_TORP VARCHAR2(2500) PATH '$.tb_bonus_torp',\n"
            + " SN_OFERTA_SEM_FRANQUIA VARCHAR2(2500) PATH '$.sn_oferta_sem_franquia',\n"
            + " TB_LIGACAOOIMOVELFIXO_DADOS VARCHAR2(2500) PATH '$.tb_ligacaooimovelfixo_dados',\n"
            + " TB_BONUSLIGACAOMIX_DADOS VARCHAR2(2500) PATH '$.tb_bonusligacaomix_dados',\n"
            + " TB_USSD_CONVERTIDO VARCHAR2(2500) PATH '$.tb_ussd_convertido',\n"
            + "	DT_GMUD VARCHAR2(2500) PATH '$.dt_gmud'\n"
            + ")))";

    public static final String UPDATE
            = "UPDATE TB_OICPROMOCOES SET \n"
            + " TB_ID_BENEFICIO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_ID_BENEFICIO as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_ID_BENEFICIO',json NUMBER(38,0) PATH '$.tb_id_beneficio')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_ID_PROGRAMA = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_ID_PROGRAMA as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_ID_PROGRAMA',json NUMBER(38,0) PATH '$.tb_id_programa')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_DDD = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_DDD as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_DDD',json VARCHAR2(2500) PATH '$.tb_ddd')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_REGIAO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_REGIAO as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_REGIAO',json VARCHAR2(2500) PATH '$.tb_regiao')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " INCENTIVO1 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT INCENTIVO1 as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.INCENTIVO1',json VARCHAR2(2500) PATH '$.incentivo1')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " INCENTIVO2 = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT INCENTIVO2 as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.INCENTIVO2',json VARCHAR2(2500) PATH '$.incentivo2')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " OUTROSPLANOS = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OUTROSPLANOS as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OUTROSPLANOS',json VARCHAR2(2500) PATH '$.outrosplanos')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " OFFERCODE = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT OFFERCODE as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.OFFERCODE',json VARCHAR2(2500) PATH '$.offercode')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_PLANO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_PLANO as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_PLANO',json VARCHAR2(2500) PATH '$.tb_plano')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_NOME_PROMOCAO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_NOME_PROMOCAO as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_NOME_PROMOCAO',json VARCHAR2(2500) PATH '$.tb_nome_promocao')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_TIPO_PROMO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_TIPO_PROMO as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_TIPO_PROMO',json VARCHAR2(2500) PATH '$.tb_tipo_promo')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_SN_ELEGIVEL_NOVA_PROMO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_SN_ELEGIVEL_NOVA_PROMO as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_SN_ELEGIVEL_NOVA_PROMO',json VARCHAR2(2500) PATH '$.tb_sn_elegivel_nova_promo')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_TAXA_ADESAO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_TAXA_ADESAO as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_TAXA_ADESAO',json NUMBER(4,2) PATH '$.tb_taxa_adesao')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_DT_INICIO_PROMO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_DT_INICIO_PROMO as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_DT_INICIO_PROMO',json DATE PATH '$.tb_dt_inicio_promo')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_DT_FIM_PROMO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_DT_FIM_PROMO as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_DT_FIM_PROMO',json DATE PATH '$.tb_dt_fim_promo')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_TIPO_BONUS = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_TIPO_BONUS as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_TIPO_BONUS',json VARCHAR2(2500) PATH '$.tb_tipo_bonus')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_FRANQUIA = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_FRANQUIA as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_FRANQUIA',json NUMBER(4,2) PATH '$.tb_franquia')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_BONUS = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_BONUS as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_BONUS',json NUMBER(38,0) PATH '$.tb_bonus')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_SN_BONUS_TORPEDO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_SN_BONUS_TORPEDO as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_SN_BONUS_TORPEDO',json VARCHAR2(2500) PATH '$.tb_sn_bonus_torpedo')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_QTD_TORP = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_QTD_TORP as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_QTD_TORP',json NUMBER(38,0) PATH '$.tb_qtd_torp')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_BONUS_TORP = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_BONUS_TORP as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_BONUS_TORP',json NUMBER(38,0) PATH '$.tb_bonus_torp')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " SN_OFERTA_SEM_FRANQUIA = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT SN_OFERTA_SEM_FRANQUIA as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.SN_OFERTA_SEM_FRANQUIA',json VARCHAR2(2500) PATH '$.sn_oferta_sem_franquia')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_LIGACAOOIMOVELFIXO_DADOS = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_LIGACAOOIMOVELFIXO_DADOS as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_LIGACAOOIMOVELFIXO_DADOS',json VARCHAR2(2500) PATH '$.tb_ligacaooimovelfixo_dados')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_BONUSLIGACAOMIX_DADOS = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_BONUSLIGACAOMIX_DADOS as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_BONUSLIGACAOMIX_DADOS',json VARCHAR2(2500) PATH '$.tb_bonusligacaomix_dados')) where ID = ? order by rownum asc) where rownum = 1),\n"
            + " TB_USSD_CONVERTIDO = (SELECT case when jsonedit='true' then json else coluna end FROM (SELECT TB_USSD_CONVERTIDO as coluna,jsonEdit,json FROM TB_OICPROMOCOES,JSON_TABLE(history, '$[*]' COLUMNS (jsonEdit VARCHAR2(2500) PATH '$.editMap.TB_USSD_CONVERTIDO',json VARCHAR2(2500) PATH '$.tb_ussd_convertido')) where ID = ? order by rownum asc) where rownum = 1)\n"
            + ", DT_GMUD = ? WHERE ID = ?";

    public static final String UPDATEINSERT
            = "INSERT INTO TB_OICPROMOCOES (\n"
            + " TB_ID_BENEFICIO,\n"
            + " TB_ID_PROGRAMA,\n"
            + " TB_DDD,\n"
            + " TB_REGIAO,\n"
            + " INCENTIVO1,\n"
            + " INCENTIVO2,\n"
            + " OUTROSPLANOS,\n"
            + " OFFERCODE,\n"
            + " TB_PLANO,\n"
            + " TB_NOME_PROMOCAO,\n"
            + " TB_TIPO_PROMO,\n"
            + " TB_SN_ELEGIVEL_NOVA_PROMO,\n"
            + " TB_TAXA_ADESAO,\n"
            + " TB_DT_INICIO_PROMO,\n"
            + " TB_DT_FIM_PROMO,\n"
            + " TB_TIPO_BONUS,\n"
            + " TB_FRANQUIA,\n"
            + " TB_BONUS,\n"
            + " TB_SN_BONUS_TORPEDO,\n"
            + " TB_QTD_TORP,\n"
            + " TB_BONUS_TORP,\n"
            + " SN_OFERTA_SEM_FRANQUIA,\n"
            + " TB_LIGACAOOIMOVELFIXO_DADOS,\n"
            + " TB_BONUSLIGACAOMIX_DADOS,\n"
            + " TB_USSD_CONVERTIDO,\n"
            + "	HISTORY,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + " TB_ID_BENEFICIO,\n"
            + " TB_ID_PROGRAMA,\n"
            + " TB_DDD,\n"
            + " TB_REGIAO,\n"
            + " INCENTIVO1,\n"
            + " INCENTIVO2,\n"
            + " OUTROSPLANOS,\n"
            + " OFFERCODE,\n"
            + " TB_PLANO,\n"
            + " TB_NOME_PROMOCAO,\n"
            + " TB_TIPO_PROMO,\n"
            + " TB_SN_ELEGIVEL_NOVA_PROMO,\n"
            + " TB_TAXA_ADESAO,\n"
            + " TB_DT_INICIO_PROMO,\n"
            + " TB_DT_FIM_PROMO,\n"
            + " TB_TIPO_BONUS,\n"
            + " TB_FRANQUIA,\n"
            + " TB_BONUS,\n"
            + " TB_SN_BONUS_TORPEDO,\n"
            + " TB_QTD_TORP,\n"
            + " TB_BONUS_TORP,\n"
            + " SN_OFERTA_SEM_FRANQUIA,\n"
            + " TB_LIGACAOOIMOVELFIXO_DADOS,\n"
            + " TB_BONUSLIGACAOMIX_DADOS,\n"
            + " TB_USSD_CONVERTIDO,\n"
            + "	? HISTORY,\n"
            + "	? DT_GMUD,\n"
            + "	ID\n"
            + "FROM TB_OICPROMOCOES WHERE ID = ?)";

    public static final String DELETE
            = "INSERT INTO TB_OICPROMOCOES (\n"
            + " TB_ID_BENEFICIO,\n"
            + " TB_ID_PROGRAMA,\n"
            + " TB_DDD,\n"
            + " TB_REGIAO,\n"
            + " INCENTIVO1,\n"
            + " INCENTIVO2,\n"
            + " OUTROSPLANOS,\n"
            + " OFFERCODE,\n"
            + " TB_PLANO,\n"
            + " TB_NOME_PROMOCAO,\n"
            + " TB_TIPO_PROMO,\n"
            + " TB_SN_ELEGIVEL_NOVA_PROMO,\n"
            + " TB_TAXA_ADESAO,\n"
            + " TB_DT_INICIO_PROMO,\n"
            + " TB_DT_FIM_PROMO,\n"
            + " TB_TIPO_BONUS,\n"
            + " TB_FRANQUIA,\n"
            + " TB_BONUS,\n"
            + " TB_SN_BONUS_TORPEDO,\n"
            + " TB_QTD_TORP,\n"
            + " TB_BONUS_TORP,\n"
            + " SN_OFERTA_SEM_FRANQUIA,\n"
            + " TB_LIGACAOOIMOVELFIXO_DADOS,\n"
            + " TB_BONUSLIGACAOMIX_DADOS,\n"
            + " TB_USSD_CONVERTIDO,\n"
            + "	HISTORY,\n"
            + "	DELETED,\n"
            + "	DT_GMUD,\n"
            + "	TEMPID\n"
            + ")(SELECT \n"
            + " TB_ID_BENEFICIO,\n"
            + " TB_ID_PROGRAMA,\n"
            + " TB_DDD,\n"
            + " TB_REGIAO,\n"
            + " INCENTIVO1,\n"
            + " INCENTIVO2,\n"
            + " OUTROSPLANOS,\n"
            + " OFFERCODE,\n"
            + " TB_PLANO,\n"
            + " TB_NOME_PROMOCAO,\n"
            + " TB_TIPO_PROMO,\n"
            + " TB_SN_ELEGIVEL_NOVA_PROMO,\n"
            + " TB_TAXA_ADESAO,\n"
            + " TB_DT_INICIO_PROMO,\n"
            + " TB_DT_FIM_PROMO,\n"
            + " TB_TIPO_BONUS,\n"
            + " TB_FRANQUIA,\n"
            + " TB_BONUS,\n"
            + " TB_SN_BONUS_TORPEDO,\n"
            + " TB_QTD_TORP,\n"
            + " TB_BONUS_TORP,\n"
            + " SN_OFERTA_SEM_FRANQUIA,\n"
            + " TB_LIGACAOOIMOVELFIXO_DADOS,\n"
            + " TB_BONUSLIGACAOMIX_DADOS,\n"
            + " TB_USSD_CONVERTIDO,\n"
            + "	?,\n"
            + "	1,\n"
            + "	?, \n"
            + "	ID \n"
            + "FROM TB_OICPROMOCOES WHERE ID IN (?))";

    public static final String APROVAR
            = "t1.TB_ID_BENEFICIO = t2.TB_ID_BENEFICIO,\n"
            + "t1.TB_ID_PROGRAMA = t2.TB_ID_PROGRAMA,\n"
            + "t1.TB_DDD = t2.TB_DDD,\n"
            + "t1.TB_REGIAO = t2.TB_REGIAO,\n"
            + "t1.INCENTIVO1 = t2.INCENTIVO1,\n"
            + "t1.INCENTIVO2 = t2.INCENTIVO2,\n"
            + "t1.OUTROSPLANOS = t2.OUTROSPLANOS,\n"
            + "t1.OFFERCODE = t2.OFFERCODE,\n"
            + "t1.TB_PLANO = t2.TB_PLANO,\n"
            + "t1.TB_NOME_PROMOCAO = t2.TB_NOME_PROMOCAO,\n"
            + "t1.TB_TIPO_PROMO = t2.TB_TIPO_PROMO,\n"
            + "t1.TB_SN_ELEGIVEL_NOVA_PROMO = t2.TB_SN_ELEGIVEL_NOVA_PROMO,\n"
            + "t1.TB_TAXA_ADESAO = t2.TB_TAXA_ADESAO,\n"
            + "t1.TB_DT_INICIO_PROMO = t2.TB_DT_INICIO_PROMO,\n"
            + "t1.TB_DT_FIM_PROMO = t2.TB_DT_FIM_PROMO,\n"
            + "t1.TB_TIPO_BONUS = t2.TB_TIPO_BONUS,\n"
            + "t1.TB_FRANQUIA = t2.TB_FRANQUIA,\n"
            + "t1.TB_BONUS = t2.TB_BONUS,\n"
            + "t1.TB_SN_BONUS_TORPEDO = t2.TB_SN_BONUS_TORPEDO,\n"
            + "t1.TB_QTD_TORP = t2.TB_QTD_TORP,\n"
            + "t1.TB_BONUS_TORP = t2.TB_BONUS_TORP,\n"
            + "t1.SN_OFERTA_SEM_FRANQUIA = t2.SN_OFERTA_SEM_FRANQUIA,\n"
            + "t1.TB_LIGACAOOIMOVELFIXO_DADOS = t2.TB_LIGACAOOIMOVELFIXO_DADOS,\n"
            + "t1.TB_BONUSLIGACAOMIX_DADOS = t2.TB_BONUSLIGACAOMIX_DADOS,\n"
            + "t1.TB_USSD_CONVERTIDO = t2.TB_USSD_CONVERTIDO,\n"
            + "t1.HISTORY = t2.HISTORY";

}
