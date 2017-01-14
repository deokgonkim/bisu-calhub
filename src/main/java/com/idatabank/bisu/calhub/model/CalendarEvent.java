package com.idatabank.bisu.calhub.model;

import java.util.Date;

public class CalendarEvent {
    
    private String uid = null;
    private String summary = null;
    private Date dateStamp = null;
    private String organizer = null;
    private String location = null;
    private Date startDate = null;
    private Date endDate = null;
    private String description = null;
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public Date getDateStamp() {
        return dateStamp;
    }
    public void setDateStamp(Date dateStamp) {
        this.dateStamp = dateStamp;
    }
    public String getOrganizer() {
        return organizer;
    }
    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}