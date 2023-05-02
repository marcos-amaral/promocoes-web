package com.contax.templateweb.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Messages {

    public static void info(String messageId, String header, String message) {
        FacesContext.getCurrentInstance().addMessage(messageId, new FacesMessage(FacesMessage.SEVERITY_INFO, header, message));
    }

    public static void warn(String messageId, String header, String message) {
        FacesContext.getCurrentInstance().addMessage(messageId, new FacesMessage(FacesMessage.SEVERITY_WARN, header, message));
    }

    public static void error(String messageId, String header, String message) {
        FacesContext.getCurrentInstance().addMessage(messageId, new FacesMessage(FacesMessage.SEVERITY_ERROR, header, message));
    }

    public static void fatal(String messageId, String header, String message) {
        FacesContext.getCurrentInstance().addMessage(messageId, new FacesMessage(FacesMessage.SEVERITY_FATAL, header, message));
    }

    public static void message(String messageId, String header, String message) {
        FacesContext.getCurrentInstance().addMessage(messageId, new FacesMessage(header, message));
    }
}
