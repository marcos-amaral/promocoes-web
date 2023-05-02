package com.oi.promocoesweb.dbms.entity;

/**
 * A List of Vendors of Database Management System.
 *
 * @author Daniel do Valle
 * @version 1.03, 20130220
 * @see com.contax.ivrmonitor.entity.dbms.DBMS
 * @since NGR-02.18.08
 */
public enum DbmsVendor {
    /**
     * Microsoft Vendor with Microsoft SQL Server Product.
     */
    SQLSERVER("com.microsoft.sqlserver.jdbc.SQLServerDriver"), //
    PGSQL("org.postgresql.Driver"), //
    MYSQL("mysql-connector-java-5.1.42-bin.jar_com.mysql.jdbc.Driver_5_1"), //
    UNKNOWN("");

    private final String driveClass;

    DbmsVendor(String driveClass) {
        this.driveClass = driveClass;
    }

    /**
     * Based on this Database Management System Vendor Name, returns the default Class Driver for it.
     *
     * @return The Default Class Driver for this Database Management System Vendor Name.
     */
    public String getClassName() {
        return driveClass;
    }
}
