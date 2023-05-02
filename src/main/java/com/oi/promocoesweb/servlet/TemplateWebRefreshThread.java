package com.oi.promocoesweb.servlet;

import com.oi.promocoesweb.dbms.control.UserControl;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.oi.promocoesweb.dbms.core.TemplateWebDataPersistence;
import com.oi.promocoesweb.exception.DbPoolFatalException;
import com.contax.templateweb.util.time.TaskTimer;

public class TemplateWebRefreshThread extends Thread {

    private static final Logger logger = LogManager.getLogger();
    private static final Object lockObject = new Object();
    private boolean interrupted = false;
    private long lastUpdate = 0;
    private final String contextPath;

    public TemplateWebRefreshThread(String contextPath) {
        this.contextPath = contextPath;
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        logger.info("Starting NgrMonitor System Container Thread.");
        TaskTimer.init();
        while (!interrupted) {
            try {
                if (interrupted) {
                    break;
                }
                Thread.sleep(3000);
                checkPersistence();
                for (int i = 0; i < 10; i++) {
                    if (interrupted) {
                        break;
                    }
                    Thread.sleep(3000);
                }
            } catch (InterruptedException ex) {
                shutdown();
                Thread.currentThread().interrupt();
            } catch (Exception ex) {
                logger.error("Exception!", ex);
            }
        }
    }

    @Override
    public void interrupt() {
        this.interrupted = true;
        super.interrupt();
    }

    public void shutdown() {
        logger.info("Shuting down Container Thread.");
        this.interrupted = true;
        lastUpdate = 0;
        TemplateWebDataPersistence.stop();
        try {
            Thread.sleep(80);
        } catch (InterruptedException ex) {
            logger.error("InterruptedException!", ex);
            Thread.currentThread().interrupt();
        }
        TaskTimer.shutdown();
    }

    private void checkPersistence() throws DbPoolFatalException, SQLException, Exception {
        if (lastUpdate == 0 && !interrupted) {
            synchronized (lockObject) {
                if (lastUpdate == 0 && !interrupted) {
                    TemplateWebDataPersistence.init(contextPath);
                    TemplateWebDataPersistence.refresh();
                    lastUpdate = System.currentTimeMillis();
                }
            }
        }
        if (lastUpdate < System.currentTimeMillis() - 25000 && !interrupted) {
            synchronized (lockObject) {
                if (lastUpdate < System.currentTimeMillis() - 20000 && !interrupted) {
                    lastUpdate = System.currentTimeMillis();
                    TemplateWebDataPersistence.refresh();
                }
            }
        }
    }
}
