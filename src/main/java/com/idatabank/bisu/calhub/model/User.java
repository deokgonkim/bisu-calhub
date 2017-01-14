package com.idatabank.bisu.calhub.model;

import java.io.Serializable;

public class User implements Serializable {
    private String uid = null;
    private String dn = null;
    private String displayName = null;
    private String employeeNumber = null;
    private String altId = null;
    private String calCalUri = null;
    
    public String getUid() {
        return uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    public String getDn() {
        return dn;
    }
    
    public void setDn(String dn) {
        this.dn = dn;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    public String getEmployeeNumber() {
        return employeeNumber;
    }
    
    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
    
    public String getAltId() {
        return altId;
    }
    
    public void setAltId(String altId) {
        this.altId = altId;
    }
    
    public String getCalCalUri() {
        return calCalUri;
    }
    
    public void setCalCalUri(String calCalUri) {
        this.calCalUri = calCalUri;
    }
}
