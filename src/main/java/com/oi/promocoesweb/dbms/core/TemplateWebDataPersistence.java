package com.oi.promocoesweb.dbms.core;

import com.oi.promocoesweb.dbms.control.TemplateControl;
import com.oi.promocoesweb.dbms.dao.AbstractTemplateWebDAO;
import com.oi.promocoesweb.dbms.dao.TemplateWebDAO;
import com.oi.promocoesweb.exception.DbPoolFatalException;
import com.contax.templateweb.util.time.TaskTimer;
import com.oi.promocoesweb.template.entity.Prompt;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TemplateWebDataPersistence implements TemplateWebPersistence {

    ////////////////////////////////////////////////////////////////////////
    // STATIC
    ////////////////////////////////////////////////////////////////////////
    private static String DEFAULT_DATASOURCE = "TemplateWebDataSource";
    private static final HashMap<String, TemplateWebDataSource> dataSourceMap = new HashMap<>();
    private static final HashMap<String, TemplateWebDataPersistence> instanceMap = new HashMap<>();
    private static final Logger logger = LogManager.getLogger();
    boolean valor = true;
    
    private static Map<String,String> promptsMap = new ConcurrentHashMap<>();

    public static String getDefaultDatasource() {
        return DEFAULT_DATASOURCE;
    }

    public static TemplateWebDataPersistence getInstance() {
        return instanceMap.get(getDefaultDatasource());
    }

    public static TemplateWebDataPersistence getInstance(String instanceName) {
        if (instanceName == null) {
            return instanceMap.get(getDefaultDatasource());
        } else {
            return instanceMap.get(instanceName);
        }
    }

    public static void init(String contextPath) {
        DEFAULT_DATASOURCE = "java:jboss/datasources/" + contextPath;
        TemplateWebDataSource ngrMonitorDataSourceObj = new TemplateWebDataSource(contextPath, DEFAULT_DATASOURCE, contextPath);
        List<TemplateWebDataSource> ngrMonitorDataSourceList = Arrays.asList(ngrMonitorDataSourceObj);
        ngrMonitorDataSourceList.stream().map((ngrMonitorDataSource) -> {
            dataSourceMap.put(ngrMonitorDataSource.getDataSourceName(), ngrMonitorDataSource);
            return ngrMonitorDataSource;
        }).forEachOrdered((ngrMonitorDataSource) -> {
            instanceMap.put(ngrMonitorDataSource.getDataSourceName(), new TemplateWebDataPersistence(ngrMonitorDataSource.getDataSourceName()));
        });
    }

    public static void stop() {
        instanceMap.values().forEach((ngrMonitorWebDataPersistence) -> {
            ngrMonitorWebDataPersistence.shutdown();
        });
        instanceMap.clear();
        dataSourceMap.clear();
        promptsMap.clear();
    }

    public static void refresh() throws SQLException, DbPoolFatalException, Exception {
        for (TemplateWebDataPersistence ngrMonitorWebDataPersistence : instanceMap.values()) {
            ngrMonitorWebDataPersistence.refreshPersistence();
        }
    }

    private TemplateWebDataPersistence(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }
    ////////////////////////////////////////////////////////////////////////
    // IMPLEMENTATION
    ////////////////////////////////////////////////////////////////////////
    private final String dataSourceName;
    
    @Override
    public void refreshPersistence() throws SQLException, DbPoolFatalException, Exception {
        logger.trace("==== Starting WebData Persistence From NgrDataBase ====");
        ////////////////////////////////////////////////////////////////////////////////////
        /*List<Prompt> promptList = Collections.unmodifiableList(TemplateControl.getAllPrompts(-1));
        Map<String, String> newTempPromptsMap = new ConcurrentHashMap<>(promptList.size()); 
        Map<String, String> oldTempPlanMap = promptsMap;
        promptList.forEach((prompt) -> {
            newTempPromptsMap.put(prompt.getWav(), prompt.getConteudo()==null?"":prompt.getConteudo());
        });
        promptsMap = newTempPromptsMap;
        oldTempPlanMap.clear();
        TaskTimer.checkPointTimer("refreshPersistenceWeb", "Plano Completed", Level.TRACE);
        ////////////////////////////////////////////////////////////////////////////////////
        
        TaskTimer.startTimer("refreshPersistenceWeb", "refreshPersistenceWeb");
        ////////////////////////////////////////////////////////////////////////////////////
        List<Produto> produtoList = Collections.unmodifiableList(ProdutoControl.getAll());
        Map<Integer, Produto> newTempProdMap = new ConcurrentHashMap<>(produtoList.size());  
        Map<Integer, Produto> oldTempProdMap = produtoMap;
        for(Produto produto : produtoList){
            newTempProdMap.put(produto.getId(), produto);
        }
        produtoMap = newTempProdMap;
        oldTempProdMap.clear();
        TaskTimer.checkPointTimer("refreshPersistenceWeb", "Produto Completed", Level.TRACE);
        ////////////////////////////////////////////////////////////////////////////////////
        List<TipoPagamento> tipoPagamentoList = Collections.unmodifiableList(TipoPagamentoControl.getAll());
        Map<Integer, TipoPagamento> newTempPagMap = new ConcurrentHashMap<>(tipoPagamentoList.size()); 
        Map<Integer, TipoPagamento> oldTempPagMap = tipoPagamentoMap;
        for(TipoPagamento tipoPagamento : tipoPagamentoList){
            newTempPagMap.put(tipoPagamento.getId(), tipoPagamento);
        }
        tipoPagamentoMap = newTempPagMap;
        oldTempPagMap.clear();
        TaskTimer.checkPointTimer("refreshPersistenceWeb", "TipoPagamento Completed", Level.TRACE);
        ////////////////////////////////////////////////////////////////////////////////////
        List<GrupoBonus> grupoBonusList = Collections.unmodifiableList(GrupoBonusControl.getAll());
        Map<Integer, GrupoBonus> newTempGBonusMap = new ConcurrentHashMap<>(grupoBonusList.size()); 
        Map<Integer, GrupoBonus> oldTempGBonusMap = grupoBonusMap;
        for(GrupoBonus bonus : grupoBonusList){
            newTempGBonusMap.put(bonus.getId(), bonus);
        }
        grupoBonusMap = newTempGBonusMap;
        oldTempGBonusMap.clear();
        TaskTimer.checkPointTimer("refreshPersistenceWeb", "Bonus Completed", Level.TRACE);
        ////////////////////////////////////////////////////////////////////////////////////
        List<TipoPlano> planoList = Collections.unmodifiableList(TipoPlanoControl.getAll());
        List<TipoBonus> bonusList = Collections.unmodifiableList(TipoBonusControl.getAll());
        Map<Integer, TipoBonus> newTempBonusMap = new ConcurrentHashMap<>(bonusList.size()); 
        Map<Integer, TipoBonus> oldTempBonusMap = tipoBonusMap;
        for(TipoBonus bonus : bonusList){
            newTempBonusMap.put(bonus.getId(), bonus);
        }
        tipoBonusMap = newTempBonusMap;
        oldTempBonusMap.clear();
        TaskTimer.checkPointTimer("refreshPersistenceWeb", "Bonus Completed", Level.TRACE);
        ////////////////////////////////////////////////////////////////////////////////////
        Map<Integer, TipoPlano> newTempPlanMap = new ConcurrentHashMap<>(planoList.size()); 
        Map<Integer, TipoPlano> oldTempPlanMap = tipoPlanoMap;
        for(TipoPlano plano : planoList){
            newTempPlanMap.put(plano.getId(), plano);
        }
        tipoPlanoMap = newTempPlanMap;
        oldTempPlanMap.clear();
        TaskTimer.checkPointTimer("refreshPersistenceWeb", "Plano Completed", Level.TRACE);
        ////////////////////////////////////////////////////////////////////////////////////
        */
        
    }

    /**
     * Shutdown the Persisted NgrMonitor Data and release the used Objects for Garbage Collecting.
     */
    @Override
    public void shutdown() {
    }
    

    ////////////////////////////////////////////////////////////////////////
    // GETTERS
    ////////////////////////////////////////////////////////////////////////
    public static Map<String, String> getPromptsMap() {    
        return promptsMap;
    }
    ////////////////////////////////////////////////////////////////////////
    // GETTERS DATASOURCE
    ////////////////////////////////////////////////////////////////////////
    public String getDataSource() {
        return dataSourceName;
    }

    public Collection<TemplateWebDataSource> getTemplateWebDataSourceList() {
        return dataSourceMap.values();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getDaoMap(Class<?> daoClass) {
        HashMap<String, Object> daoMap = new HashMap<>(instanceMap.size());
        try {
            for (TemplateWebDataPersistence ngrMonitorWebDataPersistence : instanceMap.values()) {
                TemplateWebDAO dao = new TemplateWebDAO(ngrMonitorWebDataPersistence);
                Constructor<?> constructor;
                try {
                    constructor = daoClass.getConstructor(TemplateWebDAO.class);
                } catch (Exception e) {
                    constructor = daoClass.getConstructor(AbstractTemplateWebDAO.class);
                }
                Object newInstance = constructor.newInstance(dao);
                daoMap.put(ngrMonitorWebDataPersistence.getDataSource(), newInstance);
            }
        } catch (Exception e) {
            logger.error("Error Starting DAO Pool. DAO Class: " + daoClass.toString(), e);
        }
        return (T) daoMap;
    }
}
