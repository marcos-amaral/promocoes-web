package com.contax.templateweb.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class ManagedBeanUtils {

    private static final Logger logger = LogManager.getLogger();

    @SuppressWarnings("unchecked")
    public static <T> T findBean(String beanName) {
        T returnObject = null;
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Object mb = context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
            if (mb != null) {
                returnObject = (T) mb;
            }
        } catch (Exception e) {
            logger.error("Fail finding bean " + beanName, e);
        }
        return returnObject;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getSessionAttribute(String attributeName) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null) {
            Object attribute = session.getAttribute(attributeName);
            if (attribute != null) {
                return (T) attribute;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static boolean hasSessionAttribute(String attributeName) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null) {
            Object attribute = session.getAttribute(attributeName);
            return attribute != null;
        } else {
            return false;
        }
    }

    public static void setSessionAttribute(String attributeName, Object attribute) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null) {
            session.setAttribute(attributeName, attribute);
        }
    }

    public static boolean hasParameterAttribute(String parameter) {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        Map<String, String> requestParameterMap = currentInstance.getExternalContext().getRequestParameterMap();
        if (requestParameterMap != null) {
            String attribute = requestParameterMap.get(parameter);
            return attribute != null;
        } else {
            return false;
        }
    }

    public static String getParameterAttribute(String parameter) {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        Map<String, String> requestParameterMap = currentInstance.getExternalContext().getRequestParameterMap();
        if (requestParameterMap != null) {
            String attribute = requestParameterMap.get(parameter);
            return attribute;
        } else {
            return null;
        }
    }

}
