package com.oi.promocoesweb.datamodel;

import com.oi.promocoesweb.dbms.entity.ConvertibleEntity;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.FilterMeta;

public class GenericDataModel<T extends ConvertibleEntity> extends LazyDataModel<T> {

    private static final long serialVersionUID = 326324765742833798L;
    private static final Logger logger = LogManager.getLogger();
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private final List<T> dataSource;

    public GenericDataModel(List<T> dataSource) {
        super();
        this.dataSource = dataSource;
    }

    ////////////////////////////////////////////////////////////////////////
    // SELECTABLEDATAMODEL OVERRIDE
    ////////////////////////////////////////////////////////////////////////
    @Override
    public T getRowData(String unique) {
        try {
            for (T data : dataSource) {
                if (data.getUniqueName().equals(unique)) {
                    return data;
                }
            }
        } catch (Exception e) {
            logger.error("Invalid Data Received " + unique, e);
        }
        return null;
    }

    @Override
    public Object getRowKey(T data) {
        return data.getUniqueName();
    }

    ////////////////////////////////////////////////////////////////////////
    // LAZYDATAMODEL OVERRIDE
    ////////////////////////////////////////////////////////////////////////
    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, FilterMeta> filters) {
        logger.info("Update Request Received. Processing Table.");
        List<T> filteredUserList = getFilteredList(filters);
        // Sort
        if (sortField != null) {
            Collections.sort(filteredUserList, new DataSorter(sortField, sortOrder));
        }
        // RowCount
        int dataSize = filteredUserList.size();
        this.setRowCount(dataSize);
        // Paginate
        if (dataSize > pageSize) {
            try {
                return filteredUserList.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return filteredUserList.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return filteredUserList;
        }
    }

    private List<T> getFilteredList(Map<String, FilterMeta> filters) {
        List<T> filteredUserList = new ArrayList<>(dataSource.size());
        logger.info("dataSource.size:{}", dataSource.size()); //42
        // filter
        boolean doFilter = false;
        if (filters != null && filters.size() > 0) {
            for (Entry<String, FilterMeta> entry : filters.entrySet()) {
                String filterFieldName = entry.getKey();
                FilterMeta filterValue = entry.getValue();
                if (StringUtils.isNotBlank(filterFieldName) && filterValue != null && filterValue.getFilterValue() != null && StringUtils.isNotEmpty(String.valueOf(filterValue.getFilterValue()))) {
                    doFilter = true;
                    break;
                }
            }
        }
        if (!doFilter) {
            logger.info("No Filter to Execute. Returning Full Size:{}", dataSource.size());
            filteredUserList.addAll(dataSource);
            return filteredUserList;
        }
        for (T data : dataSource) { // N Campos 
            boolean match = true;
            if (filters != null && filters.size() > 0) {
                filterFor : for (Entry<String, FilterMeta> entry : filters.entrySet()) {
                    try {
                        String filterFieldName = entry.getKey();
                        FilterMeta filterValue = entry.getValue();
                        
                        if (StringUtils.isNotBlank(filterFieldName) && filterValue != null && filterValue.getFilterValue() != null && StringUtils.isNotEmpty(String.valueOf(filterValue.getFilterValue()))) {

                            Object fieldValue = data;
                            String[] filterFieldNameArray = filterFieldName.split("\\.");
                            for (String filterFieldNameItem : filterFieldNameArray) {
                                if (filterFieldNameItem != null && filterFieldNameItem.length() > 0 && !filterFieldNameItem.startsWith("get")) {
                                    filterFieldNameItem = "get" + filterFieldNameItem.substring(0, 1).toUpperCase() + filterFieldNameItem.substring(1); // Transforma para o Getter
                                }
                                Method method = fieldValue.getClass().getMethod(filterFieldNameItem, (Class<?>[]) null);
                                fieldValue = method.invoke(fieldValue, (Object[]) null); // Rodo o Getter
                            }
                            String filterValueString = filterValue.getFilterValue().toString().toUpperCase();
                            //logger.debug("filterFieldName: {}|fieldValue: {}|filterValueString: {}",filterFieldName,fieldValue,filterValueString);
                            if (fieldValue != null) {
                                String fieldValueString;
                                
                                if(fieldValue instanceof java.util.Date){
                                    fieldValueString = sdf.format(fieldValue);
                                }
                                else{
                                    fieldValueString = fieldValue.toString().toUpperCase();
                                }
                                
                                switch (filterValue.getFilterMatchMode()) {
                                    case STARTS_WITH:
                                        if(!fieldValueString.startsWith(filterValueString)){
                                            match = false;
                                            break filterFor;
                                        }
                                        break;
                                    case ENDS_WITH:
                                        if(!fieldValueString.endsWith(filterValueString)){
                                            match = false;
                                            break filterFor;
                                        }
                                        break;
                                    case CONTAINS:
                                    case LESS_THAN:
                                    case LESS_THAN_EQUALS:
                                    case GREATER_THAN:
                                    case GREATER_THAN_EQUALS:
                                    case IN:
                                    case GLOBAL:
                                        if(!fieldValueString.contains(filterValueString)){
                                            match = false;
                                            break filterFor;
                                        }
                                        break;
                                    case EXACT:
                                    case EQUALS:
                                        if ("dtGmudStr".equals(filterFieldName) && "".equals(fieldValueString)) {
                                            if(!"PRODUCAO".equals(filterValueString)){
                                                match = false;
                                                break filterFor;
                                            }
                                        }
                                        else if(!fieldValueString.equals(filterValueString)){
                                            match = false;
                                            break filterFor;
                                        }
                                        break;
                                    default:
                                        match = false;
                                        break filterFor;
                                }
                                if(!match){
                                    break;
                                }
                            } else {
                                match = false;
                                break;
                            }
                        }
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | RuntimeException e) {
                        logger.error("Fail Matching Fields.", e);
                        match = false;
                    }
                }
            }
            if (match) {
                filteredUserList.add(data);
            }
        }
        logger.info("filteredUserList.size:{}", filteredUserList.size()); // 0
        return filteredUserList;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private class DataSorter implements Comparator<T> {

        private Class<? extends ConvertibleEntity> declaredClass;
        private final String[] sortFieldArray;
        private final SortOrder sortOrder;

        public DataSorter(String sortField, SortOrder sortOrder) {
            if (sortField != null && sortField.length() > 0) {
                sortFieldArray = sortField.split("\\.");
                for (int i = 0; i < sortFieldArray.length; i++) {
                    String sortFieldItem = sortFieldArray[i];
                    if (sortFieldItem != null && sortFieldItem.length() > 0 && !sortFieldItem.startsWith("get")) {
                        sortFieldArray[i] = "get" + sortFieldItem.substring(0, 1).toUpperCase() + sortFieldItem.substring(1);
                    }
                }
            } else {
                sortFieldArray = new String[1];
                sortFieldArray[0] = sortField;
            }
            this.sortOrder = sortOrder;
        }

        @Override
        public int compare(T data1, T data2) {
            if (declaredClass == null) {
                declaredClass = data1.getClass();
            }
            try {
                Object value1;
                Object value2;
                //
                //logger.debug("Invoking Method {} on {}", sortFieldArray[0], declaredClass);
                Method method = declaredClass.getMethod(sortFieldArray[0], (Class<?>[]) null);
                value1 = method.invoke(data1, (Object[]) null);
                value2 = method.invoke(data2, (Object[]) null);
                //
                for (int i = 1; i < sortFieldArray.length; i++) {
                    String sortFieldItem = sortFieldArray[i];
                    Class<?> aClass = value1.getClass();
                    //logger.debug("Invoking Method({}) {} on {}", i, sortFieldItem, aClass);
                    method = aClass.getMethod(sortFieldItem, (Class<?>[]) null);
                    value1 = method.invoke(value1, (Object[]) null);
                    value2 = method.invoke(value2, (Object[]) null);
                }
                //
                int value;
                if (value1 == null && value2 == null) {
                    value = 0;
                } else if (value1 == null) {
                    value = -1;
                } else if (value2 == null) {
                    value = 1;
                } else if (value1 instanceof Comparable && value2 instanceof Comparable) {
                    value = ((Comparable) value1).compareTo(((Comparable) value2));
                } else if (value1 instanceof ConvertibleEntity && value2 instanceof ConvertibleEntity) {
                    value = ((ConvertibleEntity) value1).getUniqueName().compareTo(((ConvertibleEntity) value2).getUniqueName());
                } else {
                    value = ((Comparable<Object>) value1).compareTo(value2);
                }
                return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
            } catch (NoSuchMethodException | RuntimeException | IllegalAccessException | InvocationTargetException e) {
                logger.error("Fail Comparing Generic Model.Field:" + Arrays.toString(sortFieldArray), e);
                throw new RuntimeException(e);
            }
        }
    }
}
