package com.oi.promocoesweb.dbms.core;

public class TemplateWebDataSource {

    private final String name;
    private final String dataSourceName;
    private final String description;

    public TemplateWebDataSource(String name, String dataSourceName, String description) {
        this.name = name;
        this.dataSourceName = dataSourceName;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public String getDescription() {
        return description;
    }

}
