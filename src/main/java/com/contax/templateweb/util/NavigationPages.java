package com.contax.templateweb.util;

public enum NavigationPages {
    WELCOME("/xhtml/Welcome.jsf?faces-redirect=true"), //
    LOGIN("/xhtml/login.xhtml"), //
    LOGOUT("/xhtml/login.xhtml?faces-redirect=true"),
    IMPORTAR("/xhtml/importar/Importar.jsf?faces-redirect=true"),
    ADD_PROMO("/xhtml/wizard/AddPromo.jsf?faces-redirect=true"),
    ADD_INCENTIVO("/xhtml/wizard/AddIncentivo.jsf?faces-redirect=true"),
    ADD_INCENTIVOS2S("/xhtml/wizard/AddIncentivoS2S.jsf?faces-redirect=true"),
    ADD_INCENTIVOPORTABILIDADE("/xhtml/wizard/AddIncentivoPortabilidade.jsf?faces-redirect=true"),
    ADD_PROMOPORTABILIDADE("/xhtml/wizard/AddPromoPortabilidade.jsf?faces-redirect=true"),
    CONFIG_OFERTASPRE("/xhtml/ocs/ConfigOfertasPre.jsf?faces-redirect=true"),
    PROMPTS_OFERTASPRE("/xhtml/ocs/PromptsOfertasPre.jsf?faces-redirect=true"),
    FRASEOLOGIAS_OFERTASPRE("/xhtml/ocs/FraseologiasOfertasPre.jsf?faces-redirect=true"),
    INCENTIVO_OFERTASPRE("/xhtml/ocs/IncentivoOfertasPre.jsf?faces-redirect=true"),
    INCENTIVOS_PORTABILIDADE("/xhtml/portabilidade/IncentivosPortabilidade.jsf?faces-redirect=true"),
    INCENTIVOS_PORTABILIDADE_OCS("/xhtml/portabilidade/IncentivosPortabilidadeOcs.jsf?faces-redirect=true"),
    OFERTAS_PORTABILIDADE("/xhtml/portabilidade/OfertasPortabilidade.jsf?faces-redirect=true"),
    OFERTAS_PORTABILIDADE_OCS("/xhtml/portabilidade/OfertasPortabilidadeOcs.jsf?faces-redirect=true"),
    FRASES_PORTABILIDADE("/xhtml/portabilidade/FrasesPortabilidade.jsf?faces-redirect=true"),
    FRASES_PORTABILIDADE_OCS("/xhtml/portabilidade/FrasesPortabilidadeOcs.jsf?faces-redirect=true"),
    INCENTIVO_OFERTASPRES2S("/xhtml/ocs/IncentivoOfertasPreS2S.jsf?faces-redirect=true"),
    VO_CARTAO("/xhtml/sec/VoCartao.jsf?faces-redirect=true"),
    BO_CARTAO("/xhtml/sec/BoCartao.jsf?faces-redirect=true"),
    FRASES_CARTAO("/xhtml/sec/FrasesCartao.jsf?faces-redirect=true"),
    IO_CARTAO_CARTAO("/xhtml/sec/IoCartaoCartao.jsf?faces-redirect=true"),
    IO_CARTAO_CARTAOS2S("/xhtml/sec/IoCartaoCartaoS2S.jsf?faces-redirect=true"),
    IO_CARTAO_CONTROLE("/xhtml/sec/IoCartaoControle.jsf?faces-redirect=true"),
    PROMOCOES_CARTAO("/xhtml/pri/PromocoesCartao.jsf?faces-redirect=true"),
    CARTAO_MENU("/xhtml/pri/CartaoMenu.jsf?faces-redirect=true"),
    PROMPT_CARTAOMENU("/xhtml/pri/PromptCartaoMenu.jsf?faces-redirect=true"),
    PROMPTS("/xhtml/prompts/Prompts.jsf?faces-redirect=true"),
    TB_TEMPLATE_OCS("/xhtml/cad/TbTemplateOcs.jsf?faces-redirect=true"),
    FRASE_PRE_OCS("/xhtml/cad/FrasePreOcs.jsf?faces-redirect=true"),
    FRASE_PRE("/xhtml/cad/FrasePre.jsf?faces-redirect=true"),
    VO_CONTROLE("/xhtml/sec/VoControle.jsf?faces-redirect=true"),
    BO_CONTROLE("/xhtml/sec/BoControle.jsf?faces-redirect=true"),
    FRASES_CONTROLE("/xhtml/sec/FrasesControle.jsf?faces-redirect=true"),
    TB_TEMPLATE("/xhtml/cad/TbTemplate.jsf?faces-redirect=true"),
    TB_TEMPLATE_CONTROLE("/xhtml/cad/TbTemplateControle.jsf?faces-redirect=true"),
    TB_OICMENU("/xhtml/pri/TbOiCMenu.jsf?faces-redirect=true"),
    TB_OICPROMOCOES("/xhtml/pri/TbOiCPromocoes.jsf?faces-redirect=true"),
    PROMPT_CONTROLEMENU("/xhtml/pri/PromptControleMenu.jsf?faces-redirect=true"),
    FRASES_CONTROLE_CAD("/xhtml/cad/FrasesControleCad.jsf?faces-redirect=true"),
    IO_CONTROLE_CONTROLES2S("/xhtml/sec/IoControleControleS2S.jsf?faces-redirect=true"),
    IO_CONTROLE_CONTROLE("/xhtml/sec/IoControleControle.jsf?faces-redirect=true");
    
    private final String pageName;

    NavigationPages(String pageName) {
        this.pageName = pageName;
    }

    @Override
    public String toString() {
        return pageName;
    }

    public String getPageName() {
        return this.pageName;
    }

    public static NavigationPages getNavigation(String page) {
        NavigationPages navigation = null;
        if (page != null && !page.isEmpty()) {
            for (NavigationPages navigationPage : NavigationPages.values()) {
                if (navigationPage.getPageName().equals(page)) {
                    navigation = navigationPage;
                    break;
                }
            }
        }
        return navigation;
    }
}
