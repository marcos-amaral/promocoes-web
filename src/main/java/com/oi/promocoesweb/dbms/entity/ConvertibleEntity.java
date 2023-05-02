package com.oi.promocoesweb.dbms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface ConvertibleEntity {

    @JsonIgnore
    String getUniqueName();

    @JsonIgnore
    int getUniqueId();
}
