package com.contax.templateweb.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <code>Site</code> Class represents a physical place that houses a large number of computers.
 *
 * @author Daniel do Valle
 * @version 2.21, 20130122
 * @since NGR-02.08.08
 */
@Table(name = "SIGO_CONFIGURATION")
public class Configuration implements Serializable {

    private static final long serialVersionUID = 8623694886127184480L;
    ////////////////////////////////////////////////////////////////////////
    //FIELDS
    ////////////////////////////////////////////////////////////////////////
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    
    @Column(name = "MENUNAME", nullable = false, unique = true)
    private String menuName;
    
    ////////////////////////////////////////////////////////////////////////
    //GETTERS
    ////////////////////////////////////////////////////////////////////////
    /**
     * The Site Name
     *
     * @return The DataCenter Name.
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * The Site Id.
     *
     * @return The DataCenter Id.
     */
    public int getId() {
        return id;
    }

    ////////////////////////////////////////////////////////////////////////
    //SETTERS
    ////////////////////////////////////////////////////////////////////////
    protected void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    protected void setSiteId(int id) {
        this.id = id;
    }

}
