package com.oi.promocoesweb.dbms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

public class User implements ConvertibleEntity, Serializable {

    private static final long serialVersionUID = -2255435997488409450L;
    ////////////////////////////////////////////////////////////////////////
    // FIELDS
    ////////////////////////////////////////////////////////////////////////
    private int id;
    private String login;
    private String name;
    private Perfil perfil;
    private String department;
    private String email;
    private String password;

    @JsonIgnore
    public boolean isUserAdministrationAccess() {
        return true;
    }

    @JsonIgnore
    public boolean isAdministrationViewAccess() {
        return true;
    }

    @JsonIgnore
    public boolean isSystemAdministrationAccess() {
        return true;
    }

    public User() {
        this.login = "";
        this.name = "";
        this.department = "";
        this.email = "";
    }

    ////////////////////////////////////////////////////////////////////////
    // GETTERS
    ////////////////////////////////////////////////////////////////////////
    /**
     * The User Domain Login. This is the username used to start the Virtual
     * Machine on the user station. This name must be unique.
     *
     * @return the domainUserName
     */
    public String getLogin() {
        return login;
    }

    /**
     * User Name. The complete name of the user for identification.
     *
     * @return the name
     */
    @JsonIgnore
    public String getName() {
        return name;
    }

    /**
     * User Department Name.
     *
     * @return the department
     */
    @JsonIgnore
    public String getDepartment() {
        return department;
    }

    /**
     * User Email.
     *
     * @return the email
     */
    @JsonIgnore
    public String getEmail() {
        return email;
    }

    /**
     * User Default Site. This is used as reference for Time Zone Adjustments.
     *
     * @return the dataCenter
     */
    /**
     * The user password.
     * <p>
     *
     * @return the password
     */
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    ////////////////////////////////////////////////////////////////////////
    // SETTERS
    ////////////////////////////////////////////////////////////////////////
    /**
     * The User Domain Name. This is the username used to start the Virtual
     * Machine on the user station. This name must be unique.
     *
     * @param login the domainUserName to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * User Name. The complete name of the user for identification.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * User Nick Name For the JApplet Interface. This name is used on the bottom
     * bar on the Viewer interface to greet the users.
     *
     * @param nickName the systemNickName to set
     */
    /**
     * User Department Name.
     *
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * User Email.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * User Default Site. This is used as reference for Time Zone Adjustments.
     *
     * @param password the site to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    ////////////////////////////////////////////////////////////////////////
    // CONVERTIBLE ENTITY
    ////////////////////////////////////////////////////////////////////////
    @Override
    @JsonIgnore
    public String getUniqueName() {
        return this.login;
    }

    @Override
    @JsonIgnore
    public int getUniqueId() {
        return this.hashCode();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonIgnore
    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
