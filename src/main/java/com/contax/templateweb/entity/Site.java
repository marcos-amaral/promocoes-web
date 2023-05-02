package com.contax.templateweb.entity;

import com.oi.promocoesweb.dbms.entity.ConvertibleEntity;
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
@Table(name = "SIGO_SITE")
public class Site implements ConvertibleEntity,Serializable {

    private static final long serialVersionUID = 8623694886127184480L;
    ////////////////////////////////////////////////////////////////////////
    //FIELDS
    ////////////////////////////////////////////////////////////////////////
    @Id
    @Column(name = "SITEID", nullable = false)
    private int siteId;
    
    @Column(name = "SITE", nullable = false, unique = true)
    private String siteName;
    
    @Column(name = "TIMEZONEOFFSET", nullable = false)
    private long timeZoneOffset;
    
    @Column(name = "TIMEZONENAME", nullable = false)
    private String timeZoneName;
    
    @Column(name = "ACTIVE", nullable = false) 
    private int active;

    ////////////////////////////////////////////////////////////////////////
    //GETTERS
    ////////////////////////////////////////////////////////////////////////
    /**
     * The Site Name
     *
     * @return The DataCenter Name.
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     * The Site Id.
     *
     * @return The DataCenter Id.
     */
    public int getSiteId() {
        return siteId;
    }

    /**
     * The Site Time Zone OffSet. The current time in milliseconds from UTC default time.
     *
     * @return the timeZoneOffset
     */
    public long getTimeZoneOffset() {
        return timeZoneOffset;
    }

    /**
     * The Site Time Zone Name. This field should contain the Java TimeZone Name for the Region.
     *
     * @return the timeZoneName
     */
    public String getTimeZoneName() {
        return timeZoneName;
    }

    /**
     * Informs if the Site is active or not.
     *
     * @return if the Site is active
     */
    public boolean isActive() {
        return active==1;
    }

    /**
     * Informs if the Site is active or not.
     *
     * @return if the Site is active
     */
    public byte getActive() {
        byte activeByte;
        if (active==1) {
            activeByte = 1;
        } else {
            activeByte = 0;
        }
        return activeByte;
    }

    ////////////////////////////////////////////////////////////////////////
    //SETTERS
    ////////////////////////////////////////////////////////////////////////
    protected void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    protected void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    protected void setTimeZoneOffset(long timeZoneOffset) {
        this.timeZoneOffset = timeZoneOffset;
    }

    protected void setTimeZoneName(String timeZoneName) {
        this.timeZoneName = timeZoneName;
    }

    protected void setActive(int active) {
        this.active = active;
    }
    
    ////////////////////////////////////////////////////////////////////////
    // CONVERTIBLE ENTITY
    ////////////////////////////////////////////////////////////////////////
    @Override
    public String getUniqueName() {
        return this.siteName;
    }

    @Override
    public int getUniqueId() {
        return this.hashCode();
    }
}
