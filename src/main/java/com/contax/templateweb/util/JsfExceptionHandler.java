/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contax.templateweb.util;

import java.io.IOException;
import java.util.Iterator;
import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class JsfExceptionHandler extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;

    public JsfExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws FacesException {
        System.out.println("Passando aqui");
        Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();

        while (events.hasNext()) {
            System.out.println("Passando aqui  a");
            ExceptionQueuedEvent event = events.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

            Throwable exception = context.getException();
            boolean handled = false;
            try {
                System.out.println("Passando aqui c");
                if (exception instanceof ViewExpiredException) {
                    System.out.println("Passando aqui d");
                    handled = true;
                    redirect("/Login");
                } else if (exception instanceof ELException) {
                    System.out.println("Passando aqui ff");
                    handled = true;
                    redirect("/Login");

                } else {
                    System.out.println("Passando aqui e");
                    System.out.println("Erro: " + exception.getMessage());
                    handled = true;

                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, exception.getMessage(), null));

                    //redirect("/xhtml/error.xhtml");
                }
            } finally {
                if (handled) {
                    events.remove();
                }
            }
        }

        getWrapped().handle();
    }

    private void redirect(String page) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            String contextPath = externalContext.getRequestContextPath();
            System.out.println("REDIRECIONANDO PARA : " + contextPath + page);
            externalContext.redirect(contextPath + page);
            facesContext.responseComplete();
        } catch (IOException e) {
            throw new FacesException(e);
        }
    }
}
