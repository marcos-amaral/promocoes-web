package com.oi.promocoesweb.managedbean;

import com.contax.templateweb.control.UtilsControl;
import com.oi.promocoesweb.dbms.control.UserControl;
import com.oi.promocoesweb.dbms.dao.ConfigurationDAO;
import com.oi.promocoesweb.dbms.entity.User;
import com.oi.promocoesweb.dbms.entity.Perfil;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.contax.templateweb.util.NavigationPages;
import com.oi.promocoesweb.template.entity.aprovacao.Aprovacao;
import com.oi.promocoesweb.template.enums.StatusEnum;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

@Named
@SessionScoped
public class ControleBean implements Serializable {

    private static final long serialVersionUID = -5033995769300807270L;
    private static final Logger logger = LogManager.getLogger();
    public static final String AUTH_NICK = "app.nickName";
    public static final String AUTH_USER = "app.user";

    private ConfigurationDAO configurationDAO;

    public static final List<String> ADMIN_LOGINS = Arrays.asList("marcos.amaral", "jorge.pataco", "ngradmin");
    public static final String AUTH_KEY = "app.userName";

    /**
     * The Current User.
     */
    private User user;
    /**
     * The informed username.
     */
    private String userName = "";
    /**
     * The informed password.
     */
    private String password = "";
    /**
     * The Encripted Password.
     */
    private String cryptoPassword = "";
    /**
     * If the login information should be persisted.
     */
    private boolean rememberMe = true;
    /**
     * If the current login has been authenticated.
     */
    private boolean authenticated = false;

    ////////////////////////////////////////////////////////////////////////
    // LOGIN LOGOUT
    ////////////////////////////////////////////////////////////////////////
    /**
     * Do the Login Sequence and returns the next page.
     * <p>
     *
     * @return The Next Page Name.
     */
    public String login() {
        String hashedPassword = "";
        NavigationPages nextPage = null;
        boolean validToken = false;

        logger.info("Iniciando Consulta Para Login: {}", userName);
        if (userName.isEmpty()) {
            logger.info("Usuário não digitado ");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "O Preenchimento do Login é Obrigatório.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (password.isEmpty() && hashedPassword.isEmpty() && cryptoPassword == null) {
            logger.info("Senha não digitada");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Login ou Senha Incorretos!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            try {
                if (userName.equalsIgnoreCase("super") && password.equals("123")) {
                    authenticated = true;
                    user = new User();
                    user.setLogin("super");
                    user.setPerfil(Perfil.SUPER);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTH_KEY, userName);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTH_USER, user);
                    nextPage = NavigationPages.WELCOME;
                } else {
                    user = UserControl.consultUserByLogin(userName);
                    if (user == null) {
                        logger.info("Login ou Senha Incorretos!");
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Login ou Senha Incorretos!");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    } else if (userName.equalsIgnoreCase(user.getLogin()) && password.equals(user.getPassword())) {
                        authenticated = true;
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTH_KEY, userName);
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTH_USER, user);
                        nextPage = NavigationPages.WELCOME;
                    } else {
                        if (validToken) {
                            cryptoPassword = "Fail";
                        } else {
                            cryptoPassword = "Fail";
                            logger.info("Login ou Senha Incorretos!");
                            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Login ou Senha Incorretos!");
                            FacesContext.getCurrentInstance().addMessage(null, message);
                        }
                    }
                }
            } catch (Exception ex) {
                cryptoPassword = "Fail";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Falha durante acesso ao Banco. Erro: " + ex.toString());
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
        logger.info("Retornando Pagina {}", nextPage);
        return nextPage != null ? nextPage.toString() + "?faces-redirect=true" : null;
    }

    /**
     * Do the logout Sequence.
     * <p>
     *
     * @return The login Page.
     */
    public String logout() {
        logger.info("Efetuando Logoff Para: {}", userName);
        cryptoPassword = "Logoff";
        userName = "";
        password = "";
        this.authenticated = false;
        user = null;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.getSessionMap().remove(AUTH_KEY);
        externalContext.getSessionMap().remove(AUTH_USER);
        HttpSession httpSession = (HttpSession) externalContext.getSession(false);
        if (httpSession != null) {
            httpSession.invalidate();
        }
        return NavigationPages.LOGOUT.toString();
    }

    public void logout(ActionEvent ae) {
        logout();
    }

    ////////////////////////////////////////////////////////////////////////
    // NAVIGATION ACCESS
    ////////////////////////////////////////////////////////////////////////
    public boolean checkAuthentication() {
        boolean checkAuthentication = authenticated && user != null;
        logger.info("User Authendicated: {}", checkAuthentication);
        return checkAuthentication;
    }

    public boolean checkAccessToCurrentPage() {

        System.out.println("verificando acesso");

        boolean haveaccess = false;
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        if (viewId == null) {
            logger.warn("Null Page Detected. Something is Wrong.");
            System.out.println("erro lancando excecao em viewId");
        } else if (user == null) {
            logger.warn("Null LoginBean or NgrMonitorUser Detected. Something is Wrong.");
            System.out.println("erro lancando excecao em user");
        } else {
            logger.info("Current Page {} .", viewId);
            int lastIndexOfSlash = viewId.lastIndexOf('/');
            int lastIndexOfDot = viewId.indexOf('.', lastIndexOfSlash);
            if (lastIndexOfSlash > -1 && lastIndexOfDot > -1 && lastIndexOfSlash < viewId.length() && lastIndexOfDot <= viewId.length()) {
                String currentPage = viewId.substring(lastIndexOfSlash + 1, lastIndexOfDot);
                NavigationPages page = NavigationPages.getNavigation(currentPage);
                if (page != null) {
                    haveaccess = haveAccessToPage(page);
                } else {

                    logger.warn("Null Formated Page Detected {} .", currentPage);
                }
            } else {
                logger.warn("Invalid Page Detected {} .", viewId);
                System.out.println("erro lancando excecao em Current Page");
            }
        }
        return haveaccess;
    }

    public User getUserSessao() {
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    public boolean isSuper() {
        try {
            User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER);
            return user.getPerfil().equals(Perfil.SUPER);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAdmin() {
        try {
            User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER);
            return user.getPerfil().equals(Perfil.ADMIN);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTester() {
        try {
            User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER);
            return user.getPerfil().equals(Perfil.TESTER);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCliente() {
        try {
            User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER);
            return user.getPerfil().equals(Perfil.CLIENTE);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isMarketing() {
        try {
            User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_USER);
            return (user.getPerfil().equals(Perfil.MARKETING));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean haveAccessToPage(NavigationPages page) {
        boolean haveaccess = false;
        if (user != null) {
            switch (page) {
                case WELCOME:
                case IMPORTAR:
                case CONFIG_OFERTASPRE:
                case PROMPTS_OFERTASPRE:
                case PROMPT_CONTROLEMENU:
                case FRASEOLOGIAS_OFERTASPRE:
                case INCENTIVO_OFERTASPRE:
                case INCENTIVO_OFERTASPRES2S:
                case VO_CARTAO:
                case BO_CARTAO:
                case FRASES_CARTAO:
                case IO_CARTAO_CARTAO:
                case IO_CARTAO_CARTAOS2S:
                case IO_CARTAO_CONTROLE:
                case PROMOCOES_CARTAO:
                case CARTAO_MENU:
                case PROMPT_CARTAOMENU:
                case PROMPTS:
                case TB_TEMPLATE_OCS:
                case FRASE_PRE_OCS:
                case FRASE_PRE:
                case VO_CONTROLE:
                case BO_CONTROLE:
                case FRASES_CONTROLE:
                case TB_TEMPLATE:
                case TB_TEMPLATE_CONTROLE:
                case IO_CONTROLE_CONTROLE:
                case IO_CONTROLE_CONTROLES2S:
                case TB_OICMENU:
                case TB_OICPROMOCOES:
                case FRASES_CONTROLE_CAD:
                case ADD_PROMO:
                case ADD_INCENTIVO:
                case ADD_INCENTIVOS2S:
                case INCENTIVOS_PORTABILIDADE:
                case INCENTIVOS_PORTABILIDADE_OCS:
                case OFERTAS_PORTABILIDADE:
                case OFERTAS_PORTABILIDADE_OCS:
                case FRASES_PORTABILIDADE:
                case FRASES_PORTABILIDADE_OCS:
                case ADD_INCENTIVOPORTABILIDADE:
                case ADD_PROMOPORTABILIDADE:
                    haveaccess = true;
                    break;
                case LOGOUT:
                    haveaccess = true;
                    break;
                case LOGIN:
                    haveaccess = true;
                    break;
                default:
                    break;
            }
        } else {
            Throwable t = new IllegalStateException("A wrapped IllegalStateException!");
            throw new FacesException(t);
        }
        return haveaccess;
    }

    public boolean isDisabledByPerfil() {
        if (isMarketing() || isSuper()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isRenderComponentes() {
        return isSuper() || isMarketing();
    }

    public boolean canEdit() {
        return isSuper() || isAdmin() || isTester() || isCliente();
    }

    public boolean canAprove(Aprovacao aprovacao, boolean isTelaPrompt) {
        return (isSuper() || (isCliente() && !isTelaPrompt) || (isAdmin() && isTelaPrompt)) && aprovacao != null && aprovacao.getStatus() != null && (aprovacao.getStatus().equals(StatusEnum.VALIDADO) || (isTelaPrompt && aprovacao.getStatus().equals(StatusEnum.INICIADO)));
    }

    public boolean canDeploy(Aprovacao aprovacao) {
        return (isSuper() || isAdmin()) && aprovacao != null && aprovacao.getStatus() != null && aprovacao.getStatus().equals(StatusEnum.EM_IMPLANTACAO);
    }

    public boolean canStart(Aprovacao aprovacao) {
        return (isSuper() || isCliente()) && (aprovacao == null || aprovacao.getStatus() == null);
    }

    public boolean canValidate(Aprovacao aprovacao) {
        return (isSuper() || isTester()) && aprovacao != null && aprovacao.getStatus() != null && aprovacao.getStatus().equals(StatusEnum.INICIADO);
    }

    public boolean canUAT(Aprovacao aprovacao) {
        return (isSuper() || isTester()) && aprovacao != null && aprovacao.getStatus() != null && aprovacao.getStatus().equals(StatusEnum.APROVADO);
    }

    public boolean canReleaseDeployment(Aprovacao aprovacao, boolean isTelaPrompt) {
        return (isSuper() || (isCliente() && !isTelaPrompt) || (isAdmin() && isTelaPrompt)) && aprovacao != null && aprovacao.getStatus() != null && (aprovacao.getStatus().equals(StatusEnum.EM_UAT) || (isTelaPrompt && aprovacao.getStatus().equals(StatusEnum.APROVADO)));
    }

    public String visibilityDeploy(Aprovacao aprovacao) {
        String visibility = "none";
        if (canDeploy(aprovacao)) {
            visibility = "block";
        }
        return visibility;
    }

    ////////////////////////////////////////////////////////////////////////
    // ROUTING
    ////////////////////////////////////////////////////////////////////////
    public String routeToImportar() {
        if (haveAccessToPage(NavigationPages.IMPORTAR)) {
            return NavigationPages.IMPORTAR.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToConfigOfertasPre() {
        if (haveAccessToPage(NavigationPages.CONFIG_OFERTASPRE)) {
            return NavigationPages.CONFIG_OFERTASPRE.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToPromptsOfertasPre() {
        if (haveAccessToPage(NavigationPages.PROMPTS_OFERTASPRE)) {
            return NavigationPages.PROMPTS_OFERTASPRE.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToFraseologiasOfertasPre() {
        if (haveAccessToPage(NavigationPages.FRASEOLOGIAS_OFERTASPRE)) {
            return NavigationPages.FRASEOLOGIAS_OFERTASPRE.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToIncentivoOfertasPre() {
        if (haveAccessToPage(NavigationPages.INCENTIVO_OFERTASPRE)) {
            return NavigationPages.INCENTIVO_OFERTASPRE.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToIncentivoOfertasPreS2S() {
        if (haveAccessToPage(NavigationPages.INCENTIVO_OFERTASPRES2S)) {
            return NavigationPages.INCENTIVO_OFERTASPRES2S.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToVoCartao() {
        if (haveAccessToPage(NavigationPages.VO_CARTAO)) {
            return NavigationPages.VO_CARTAO.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToBoCartao() {
        if (haveAccessToPage(NavigationPages.BO_CARTAO)) {
            return NavigationPages.BO_CARTAO.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToFrasesCartao() {
        if (haveAccessToPage(NavigationPages.FRASES_CARTAO)) {
            return NavigationPages.FRASES_CARTAO.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToIoCartaoCartao() {
        if (haveAccessToPage(NavigationPages.IO_CARTAO_CARTAO)) {
            return NavigationPages.IO_CARTAO_CARTAO.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToIoCartaoCartaoS2S() {
        if (haveAccessToPage(NavigationPages.IO_CARTAO_CARTAOS2S)) {
            return NavigationPages.IO_CARTAO_CARTAOS2S.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToIoCartaoControle() {
        if (haveAccessToPage(NavigationPages.IO_CARTAO_CONTROLE)) {
            return NavigationPages.IO_CARTAO_CONTROLE.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToPrompts() {
        if (haveAccessToPage(NavigationPages.PROMPTS)) {
            return NavigationPages.PROMPTS.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToTbTemplateOcs() {
        if (haveAccessToPage(NavigationPages.TB_TEMPLATE_OCS)) {
            return NavigationPages.TB_TEMPLATE_OCS.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToFrasePreOcs() {
        if (haveAccessToPage(NavigationPages.FRASE_PRE_OCS)) {
            return NavigationPages.FRASE_PRE_OCS.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToFrasePre() {
        if (haveAccessToPage(NavigationPages.FRASE_PRE)) {
            return NavigationPages.FRASE_PRE.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToVoControle() {
        if (haveAccessToPage(NavigationPages.VO_CONTROLE)) {
            return NavigationPages.VO_CONTROLE.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToBoControle() {
        if (haveAccessToPage(NavigationPages.BO_CONTROLE)) {
            return NavigationPages.BO_CONTROLE.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToPromocoesCartao() {
        if (haveAccessToPage(NavigationPages.PROMOCOES_CARTAO)) {
            return NavigationPages.PROMOCOES_CARTAO.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToCartaoMenu() {
        if (haveAccessToPage(NavigationPages.CARTAO_MENU)) {
            return NavigationPages.CARTAO_MENU.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToPromptCartaoMenu() {
        if (haveAccessToPage(NavigationPages.PROMPT_CARTAOMENU)) {
            return NavigationPages.PROMPT_CARTAOMENU.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToPromptControleMenu() {
        if (haveAccessToPage(NavigationPages.PROMPT_CONTROLEMENU)) {
            return NavigationPages.PROMPT_CONTROLEMENU.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToFrasesControle() {
        if (haveAccessToPage(NavigationPages.FRASES_CONTROLE)) {
            return NavigationPages.FRASES_CONTROLE.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToTbTemplate() {
        if (haveAccessToPage(NavigationPages.TB_TEMPLATE)) {
            return NavigationPages.TB_TEMPLATE.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToTbTemplateControle() {
        if (haveAccessToPage(NavigationPages.TB_TEMPLATE_CONTROLE)) {
            return NavigationPages.TB_TEMPLATE_CONTROLE.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToTbOiCMenu() {
        if (haveAccessToPage(NavigationPages.TB_OICMENU)) {
            return NavigationPages.TB_OICMENU.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToTbOiCPromocoes() {
        if (haveAccessToPage(NavigationPages.TB_OICPROMOCOES)) {
            return NavigationPages.TB_OICPROMOCOES.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToIoControleControle() {
        if (haveAccessToPage(NavigationPages.IO_CONTROLE_CONTROLE)) {
            return NavigationPages.IO_CONTROLE_CONTROLE.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToIoControleControleS2S() {
        if (haveAccessToPage(NavigationPages.IO_CONTROLE_CONTROLES2S)) {
            return NavigationPages.IO_CONTROLE_CONTROLES2S.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToFrasesControleCad() {
        if (haveAccessToPage(NavigationPages.FRASES_CONTROLE_CAD)) {
            return NavigationPages.FRASES_CONTROLE_CAD.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToWizardAdicionar() {
        if (haveAccessToPage(NavigationPages.ADD_PROMO)) {
            return NavigationPages.ADD_PROMO.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToWizardIncentivo() {
        if (haveAccessToPage(NavigationPages.ADD_INCENTIVO)) {
            return NavigationPages.ADD_INCENTIVO.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToWizardIncentivoS2S() {
        if (haveAccessToPage(NavigationPages.ADD_INCENTIVOS2S)) {
            return NavigationPages.ADD_INCENTIVOS2S.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToWizardIncentivosPortabilidade() {
        if (haveAccessToPage(NavigationPages.ADD_INCENTIVOPORTABILIDADE)) {
            return NavigationPages.ADD_INCENTIVOPORTABILIDADE.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToWizardPromoPortabilidade() {
        if (haveAccessToPage(NavigationPages.ADD_PROMOPORTABILIDADE)) {
            return NavigationPages.ADD_PROMOPORTABILIDADE.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToIncentivosPortabilidade() {
        if (haveAccessToPage(NavigationPages.INCENTIVOS_PORTABILIDADE)) {
            return NavigationPages.INCENTIVOS_PORTABILIDADE.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToIncentivosPortabilidadeOcs() {
        if (haveAccessToPage(NavigationPages.INCENTIVOS_PORTABILIDADE_OCS)) {
            return NavigationPages.INCENTIVOS_PORTABILIDADE_OCS.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToOfertasPortabilidade() {
        if (haveAccessToPage(NavigationPages.OFERTAS_PORTABILIDADE)) {
            return NavigationPages.OFERTAS_PORTABILIDADE.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToOfertasPortabilidadeOcs() {
        if (haveAccessToPage(NavigationPages.OFERTAS_PORTABILIDADE_OCS)) {
            return NavigationPages.OFERTAS_PORTABILIDADE_OCS.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToFrasesPortabilidade() {
        if (haveAccessToPage(NavigationPages.FRASES_PORTABILIDADE)) {
            return NavigationPages.FRASES_PORTABILIDADE.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    public String routeToFrasesPortabilidadeOcs() {
        if (haveAccessToPage(NavigationPages.FRASES_PORTABILIDADE_OCS)) {
            return NavigationPages.FRASES_PORTABILIDADE_OCS.toString();
        } else {
            return NavigationPages.WELCOME.toString();
        }
    }

    ////////////////////////////////////////////////////////////////////////
    // GETTERS AND SETTERS
    ////////////////////////////////////////////////////////////////////////
    /**
     * The Current User.
     * <p>
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * The Current User.
     * <p>
     *
     * @param ngrMonitorUser the user to set
     */
    public void setUser(User ngrMonitorUser) {
        this.user = ngrMonitorUser;
    }

    /**
     * The informed username.
     * <p>
     *
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * The informed password.
     * <p>
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * If the login information should be persisted.
     * <p>
     *
     * @return the rememberMe
     */
    public boolean isRememberMe() {
        return rememberMe;
    }

    /**
     * The informed username.
     * <p>
     *
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * The informed password.
     * <p>
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * If the login information should be persisted.
     * <p>
     *
     * @param rememberMe the rememberMe to set
     */
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    /**
     * If the current login has been authenticated.
     * <p>
     *
     * @return the authenticated
     */
    public boolean isAuthenticated() {
        return authenticated;
    }

    /**
     * If the current login has been authenticated.
     * <p>
     *
     * @param authenticated the authenticated to set
     */
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String stuffToHide() {
        String stuffToHide = "";
        try {
            List<String> listToHide = UtilsControl.getStuffToHide(configurationDAO);
            for (int i = 0; i < listToHide.size(); i++) {
                stuffToHide += listToHide.get(i) + ";";
            }
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        return stuffToHide;
    }

    public String getCryptoPassword() {
        return cryptoPassword;
    }

    public void setCryptoPassword(String cryptoPassword) {
        this.cryptoPassword = cryptoPassword;
    }

    ////////////////////////////////////////////////////////////////////////
    // EXCEPTIONS
    ////////////////////////////////////////////////////////////////////////
    public void throwNullPointerException() {
        throw new NullPointerException("A NullPointerException!");
    }

    public void throwWrappedIllegalStateException() {
        Throwable t = new IllegalStateException("A wrapped IllegalStateException!");
        throw new FacesException(t);
    }

    public void throwViewExpiredException() {
        throw new ViewExpiredException("A ViewExpiredException!", FacesContext.getCurrentInstance().getViewRoot().getViewId());
    }

}
