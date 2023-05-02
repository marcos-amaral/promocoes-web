/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.oi.promocoesweb.dbms.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oi.promocoesweb.dbms.entity.ConvertibleEntity;
import com.oi.promocoesweb.template.enums.StatusEnum;
import com.oi.promocoesweb.template.utils.DateUtils;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.validator.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mpma0
 */
public class Template implements Comparable<Template>, Serializable, ConvertibleEntity {

    private static final Logger logger = LogManager.getLogger();

    private int id;
    private int tempId = 0;
    private User user;
    private Map<String, Boolean> editMap = new HashMap<>();
    private Date modified;
    private List<? extends Template> history = new ArrayList<>();
    private boolean deleted;
    private Date dt_gmud;
    private StatusEnum lastStatus;

    public Template() {
    }

    @JsonIgnore
    public boolean isEditDt_gmud() {
        return getEditMap().getOrDefault("DT_GMUD", false);
    }

    public void setEditDt_gmud(boolean checked) {
        getEditMap().put("DT_GMUD", checked);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, Boolean> getEditMap() {
        return editMap;
    }

    public void setEditMap(Map<String, Boolean> editMap) {
        this.editMap = editMap;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @JsonIgnore
    public List<? extends Template> getHistory() {
        return history;
    }

    public void setHistory(List<? extends Template> history) {
        this.history = history;
        try {
            this.history.sort(Comparator.comparing(Template::getModified).reversed());
        } catch (Exception e) {
        }
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getDt_gmud() {
        return dt_gmud;
    }

    @JsonGetter("dt_gmud")
    public String getDtGmudStr() {
        if (dt_gmud != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            return sdf.format(dt_gmud);
        }

        return "";
    }

    @JsonSetter("dt_gmud")
    public void setDt_gmudAsString(String dt_gmud) {
        try {
            if (dt_gmud != null && !"".equals(dt_gmud)) {
                this.dt_gmud = DateUtils.parse(dt_gmud);
            }
        } catch (Exception e) {
            logger.warn("invalid dt_gmud: {}", dt_gmud);
        }
    }

    public void setDt_gmud(Date dt_gmud) {
        this.dt_gmud = dt_gmud;
    }

    @JsonIgnore
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonIgnore
    public int getTempId() {
        return tempId;
    }

    public void setTempId(int tempId) {
        this.tempId = tempId;
    }

    @JsonIgnore
    public StatusEnum getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(StatusEnum lastStatus) {
        this.lastStatus = lastStatus;
    }

    public void setHistory(String history) throws Exception {
        if (history != null && !"".equals(history)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                Template[] pp1 = mapper.readValue(history, Template[].class);
                setHistory(Arrays.asList(mapper.readValue(history, Template[].class)));

                Date tempDate = new Date(0);
                for (Template template : pp1) {
                    if (template.getModified() != null && template.getModified().after(tempDate)) {
                        tempDate = template.getModified();
                    }

                    template.getEditMap().forEach((k, v) -> {
                        if (Boolean.TRUE.equals(v)) {
                            getEditMap().put(k, v);
                        }
                    });
                }
                setModified(tempDate);

            } catch (Exception ex) {
                throw ex;
            }
        }
    }

    public void normalize() {

    }

    @JsonIgnore
    public String getStyleClass(String field) {
        if (getDt_gmud() == null) {
            return "cell-normal";
        } else if (isDeleted()) {
            return "deletedcell";
        } else if (getTempId() == 0) {
            return "editedcell";
        }

        switch (field) {
            case "GMUD":
            case "MODIFICADA":
            case "ID_OFERTAOCS":
            case "DDD":
            case "LOGIN":
                if (getEditMap().isEmpty()) {
                    return "editedcell";
                }
                break;
            default:
                if (getEditMap().isEmpty() || getEditMap().getOrDefault(field, false)) {
                    return "editedcell";
                }
                break;
        }

        return "cell-normal";
    }

    @Override
    public int compareTo(Template t) {
        if (getModified() == null || t.getModified() == null) {
            return 0;
        }
        return getModified().compareTo(t.getModified());
    }

    @Override
    public String getUniqueName() {
        return String.valueOf(getId());
    }

    @Override
    public int getUniqueId() {
        return getId();
    }
    
    public boolean validate() throws ValidatorException{
        return true;
    }
}
