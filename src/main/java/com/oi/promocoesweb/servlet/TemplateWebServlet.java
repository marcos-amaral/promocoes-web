package com.oi.promocoesweb.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

@WebServlet(name = "TemplateWebServlet", loadOnStartup = 1, urlPatterns = {"/TemplateWebServlet"})
public class TemplateWebServlet extends HttpServlet {

    private static final long serialVersionUID = 2943893351201199909L;
    private TemplateWebRefreshThread ngrMonitorRefreshThread;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * <p>
     *
     * @param request servlet request
     * @param response servlet response
     * <p>
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NgrMonitorServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NgrMonitorServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * <p>
     *
     * @param request servlet request
     * @param response servlet response
     * <p>
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * <p>
     *
     * @param request servlet request
     * @param response servlet response
     * <p>
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * <p>
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "TemplateWeb Servlet!";
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        String contextPath = servletContext.getContextPath();
        contextPath = contextPath.replace("\\", "");
        
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        File file = new File("c://temp//conf//TemplateWebLogConfig.xml");
        context.setConfigLocation(file.toURI());
        
        Logger logger = LogManager.getLogger(TemplateWebServlet.class.getName());
        logger.info("Logger Configuration Started");

        if (ngrMonitorRefreshThread == null || ngrMonitorRefreshThread.isInterrupted() || !ngrMonitorRefreshThread.isAlive()) {
            ngrMonitorRefreshThread = new TemplateWebRefreshThread(contextPath);
            ngrMonitorRefreshThread.start();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
