package com.idatabank.bisu.calhub.service;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.model.property.Version;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idatabank.bisu.calhub.dao.GroupDAO;
import com.idatabank.bisu.calhub.dao.ICalDAO;
import com.idatabank.bisu.calhub.dao.UserDAO;
import com.idatabank.bisu.calhub.exception.InternalException;
import com.idatabank.bisu.calhub.model.Group;
import com.idatabank.bisu.calhub.model.User;

@Service
public class IcalCalendarService {
    
    public List<VEvent> getUsersEventList(String uid) throws InternalException {
        List<VEvent> eventList = null;
        
        User user = null;
        String calendarUrl = null;
        
        try {
            user = userDao.getUserByUid(uid);
        } catch (NamingException e) {
            throw new InternalException("NAMINGEXCEPTION", e, e.getMessage());
        }
        
        if ( user == null ) throw new InternalException("NOSUCHUSER", uid);
        
        calendarUrl = user.getCalCalUri();
        
        if ( calendarUrl == null ) throw new InternalException("NOCALCALURI");
        
        try {
            eventList = icalDao.getFullEventUsingUri(calendarUrl);
        } catch (IOException e) {
            throw new InternalException("IOEXCEPTION", e, calendarUrl, e.getMessage());
        } catch (ParserException e) {
            throw new InternalException("PARSEREXCEPTION", e, calendarUrl, e.getMessage());
        }
        
        return eventList;
    }
    
    public String getUsersCalendar(String uid) throws InternalException {
        List<VEvent> eventList = null;
        
        User user = null;
        String calendarUrl = null;
        
        Calendar calendar = null;
        
        try {
            user = userDao.getUserByUid(uid);
        } catch (NamingException e) {
            throw new InternalException("NAMINGEXCEPTION", e, e.getMessage());
        }
        
        if ( user == null ) throw new InternalException("NOSUCHUSER", uid);
        
        calendarUrl = user.getCalCalUri();
        
        if ( calendarUrl == null ) throw new InternalException("NOCALCALURI");
        
        try {
            eventList = icalDao.getFullEventUsingUri(calendarUrl);
            calendar = new Calendar();
            calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
            calendar.getProperties().add(Version.VERSION_2_0);
            calendar.getProperties().add(CalScale.GREGORIAN);
            
            for ( VEvent event : eventList ) {
                PropertyList originalProperties = event.getProperties();
                
                //Property originalSummary = originalProperties.getProperty(Summary.SUMMARY);
                //Property newSummary = new Summary("[" + uid + "] " + originalSummary.getValue());
                
                //originalProperties.remove(originalSummary);
                //originalProperties.add(newSummary);
                
                VEvent newEvent = new VEvent(originalProperties);
                
                calendar.getComponents().add(newEvent);
            }
        } catch (IOException e) {
            throw new InternalException("IOEXCEPTION", e, calendarUrl, e.getMessage());
        } catch (ParserException e) {
            throw new InternalException("PARSEREXCEPTION", e, calendarUrl, e.getMessage());
        }
        
        return calendar.toString();
    }
    
    public List<VEvent> getUsersEventListWithinMonth(String uid) throws InternalException {
        List<VEvent> eventList = null;
        
        User user = null;
        String calendarUrl = null;
        
        try {
            user = userDao.getUserByUid(uid);
        } catch (NamingException e) {
            throw new InternalException("NAMINGEXCEPTION", e, e.getMessage());
        }
        
        if ( user == null ) throw new InternalException("NOSUCHUSER", uid);
        
        calendarUrl = user.getCalCalUri();
        
        if ( calendarUrl == null ) throw new InternalException("NOCALCALURI");
        
        try {
            eventList = icalDao.getRangedEventUsingUri(calendarUrl, 30L);
        } catch (IOException e) {
            throw new InternalException("IOEXCEPTION", e, calendarUrl, e.getMessage());
        } catch (ParserException e) {
            throw new InternalException("PARSEREXCEPTION", e, calendarUrl, e.getMessage());
        }
        
        return eventList;
    }
    
    public String getUsersCalendarWithinMonth(String uid) throws InternalException {
        List<VEvent> eventList = null;
        
        User user = null;
        String calendarUrl = null;
        
        Calendar calendar = null;
        
        try {
            user = userDao.getUserByUid(uid);
        } catch (NamingException e) {
            throw new InternalException("NAMINGEXCEPTION", e, e.getMessage());
        }
        
        if ( user == null ) throw new InternalException("NOSUCHUSER", uid);
        
        calendarUrl = user.getCalCalUri();
        
        if ( calendarUrl == null ) throw new InternalException("NOCALCALURI");
        
        try {
            eventList = icalDao.getRangedEventUsingUri(calendarUrl, 30L);
            calendar = new Calendar();
            calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
            calendar.getProperties().add(Version.VERSION_2_0);
            calendar.getProperties().add(CalScale.GREGORIAN);
            
            for ( VEvent event : eventList ) {
                PropertyList originalProperties = event.getProperties();
                
                //Property originalSummary = originalProperties.getProperty(Summary.SUMMARY);
                //Property newSummary = new Summary("[" + uid + "] " + originalSummary.getValue());
                
                //originalProperties.remove(originalSummary);
                //originalProperties.add(newSummary);
                
                VEvent newEvent = new VEvent(originalProperties);
                
                calendar.getComponents().add(newEvent);
            }
        } catch (IOException e) {
            throw new InternalException("IOEXCEPTION", e, calendarUrl, e.getMessage());
        } catch (ParserException e) {
            throw new InternalException("PARSEREXCEPTION", e, calendarUrl, e.getMessage());
        }
        
        return calendar.toString();
    }
    
    public List<VEvent> getGroupsEventList(String cn, String prefixAttribute) throws InternalException {
        List<VEvent> eventList = null;
        
        Group group = null;
        User user = null;
        
        try {
            group = groupDao.getGroupByCn(cn);
        } catch (NamingException e) {
            throw new InternalException("NAMINGEXCEPTION", e.getMessage());
        }
        
        if ( group == null ) throw new InternalException("NOSUCHGROUP", cn);
        
        for ( String userDn : group.getUniqueMembers() ) {
            try {
                user = userDao.getUserByDn(userDn);
            } catch (NamingException e) {
                e.printStackTrace();
                continue;
            }
            if ( user == null ) continue;
            if ( user.getCalCalUri() == null ) continue;
            
            try {
                List<VEvent> usersEventList = icalDao.getFullEventUsingUri(user.getCalCalUri());
                
                for ( VEvent event : usersEventList ) {
                    PropertyList originalProperties = event.getProperties();
                    
                    Property originalSummary = originalProperties.getProperty(Summary.SUMMARY);
                    Property newSummary = null;
                    if ( prefixAttribute != null ) {
                        if ( prefixAttribute.equalsIgnoreCase("uid") ) {
                            newSummary = new Summary("[" + user.getUid() + "] " + originalSummary.getValue());
                        } else if ( prefixAttribute.equalsIgnoreCase("displayName") ) {
                            newSummary = new Summary("[" + user.getDisplayName() + "] " + originalSummary.getValue());
                        } else if ( prefixAttribute.equalsIgnoreCase("ALTID") ) {
                            String altId = user.getAltId();
                            newSummary = new Summary("[" + ( altId == null ? user.getUid() : altId ) + "] " + originalSummary.getValue());
                        }
                    } else {
                        newSummary = new Summary("[" + user.getUid() + "] " + originalSummary.getValue());
                    }
                    
                    originalProperties.remove(originalSummary);
                    originalProperties.add(newSummary);
                    
                    VEvent newEvent = new VEvent(originalProperties);
                    
                    if ( eventList == null ) eventList = new LinkedList<VEvent>();
                    eventList.add(newEvent);
                }
            } catch (IOException e) {
                e.printStackTrace();
                //throw new InternalException("IOEXCEPTION", e, calendarUrl, e.getMessage());
            } catch (ParserException e) {
                e.printStackTrace();
                //throw new InternalException("PARSEREXCEPTION", e, calendarUrl, e.getMessage());
            }
        }
        
        return eventList;
    }
    
    public String getGroupsCalendar(String cn, String prefixAttribute) throws InternalException {
        List<VEvent> eventList = null;
        
        Group group = null;
        User user = null;
        
        Calendar calendar = null;
        
        try {
            group = groupDao.getGroupByCn(cn);
        } catch (NamingException e) {
            throw new InternalException("NAMINGEXCEPTION", e.getMessage());
        }
        
        if ( group == null ) throw new InternalException("NOSUCHGROUP", cn);
        
        calendar = new Calendar();
        calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);
        
        for ( String userDn : group.getUniqueMembers() ) {
            try {
                user = userDao.getUserByDn(userDn);
            } catch (NamingException e) {
                e.printStackTrace();
                continue;
            }
            if ( user == null ) continue;
            if ( user.getCalCalUri() == null ) continue;
            
            try {
                eventList = icalDao.getFullEventUsingUri(user.getCalCalUri());
                
                for ( VEvent event : eventList ) {
                    PropertyList originalProperties = event.getProperties();
                    
                    Property originalSummary = originalProperties.getProperty(Summary.SUMMARY);
                    Property newSummary = null;
                    if ( prefixAttribute != null ) {
                        if ( prefixAttribute.equalsIgnoreCase("uid") ) {
                            newSummary = new Summary("[" + user.getUid() + "] " + originalSummary.getValue());
                        } else if ( prefixAttribute.equalsIgnoreCase("displayName") ) {
                            newSummary = new Summary("[" + user.getDisplayName() + "] " + originalSummary.getValue());
                        } else if ( prefixAttribute.equalsIgnoreCase("ALTID") ) {
                            String altId = user.getAltId();
                            newSummary = new Summary("[" + ( altId == null ? user.getUid() : altId ) + "] " + originalSummary.getValue());
                        }
                    } else {
                        newSummary = new Summary("[" + user.getUid() + "] " + originalSummary.getValue());
                    }
                    
                    originalProperties.remove(originalSummary);
                    originalProperties.add(newSummary);
                    
                    VEvent newEvent = new VEvent(originalProperties);
                    
                    calendar.getComponents().add(newEvent);
                }
            } catch (IOException e) {
                e.printStackTrace();
                //throw new InternalException("IOEXCEPTION", e, calendarUrl, e.getMessage());
            } catch (ParserException e) {
                e.printStackTrace();
                //throw new InternalException("PARSEREXCEPTION", e, calendarUrl, e.getMessage());
            }
        }
        
        return calendar.toString();
    }
    
    public List<VEvent> getGroupsEventListWithinMonth(String cn, String prefixAttribute) throws InternalException {
        List<VEvent> eventList = null;
        
        Group group = null;
        User user = null;
        
        try {
            group = groupDao.getGroupByCn(cn);
        } catch (NamingException e) {
            throw new InternalException("NAMINGEXCEPTION", e.getMessage());
        }
        
        if ( group == null ) throw new InternalException("NOSUCHGROUP", cn);
        
        for ( String userDn : group.getUniqueMembers() ) {
            try {
                user = userDao.getUserByDn(userDn);
            } catch (NamingException e) {
                e.printStackTrace();
                continue;
            }
            if ( user == null ) continue;
            if ( user.getCalCalUri() == null ) continue;
            
            try {
                List<VEvent> usersEventList = icalDao.getRangedEventUsingUri(user.getCalCalUri(), 30L);
                
                for ( VEvent event : usersEventList ) {
                    PropertyList originalProperties = event.getProperties();
                    
                    Property originalSummary = originalProperties.getProperty(Summary.SUMMARY);
                    Property newSummary = null;
                    if ( prefixAttribute != null ) {
                        if ( prefixAttribute.equalsIgnoreCase("uid") ) {
                            newSummary = new Summary("[" + user.getUid() + "] " + originalSummary.getValue());
                        } else if ( prefixAttribute.equalsIgnoreCase("displayName") ) {
                            newSummary = new Summary("[" + user.getDisplayName() + "] " + originalSummary.getValue());
                        } else if ( prefixAttribute.equalsIgnoreCase("ALTID") ) {
                            String altId = user.getAltId();
                            newSummary = new Summary("[" + ( altId == null ? user.getUid() : altId ) + "] " + originalSummary.getValue());
                        }
                    } else {
                        newSummary = new Summary("[" + user.getUid() + "] " + originalSummary.getValue());
                    }
                    
                    originalProperties.remove(originalSummary);
                    originalProperties.add(newSummary);
                    
                    VEvent newEvent = new VEvent(originalProperties);
                    
                    if ( eventList == null ) eventList = new LinkedList<VEvent>();
                    eventList.add(newEvent);
                }
            } catch (IOException e) {
                e.printStackTrace();
                //throw new InternalException("IOEXCEPTION", e, calendarUrl, e.getMessage());
            } catch (ParserException e) {
                e.printStackTrace();
                //throw new InternalException("PARSEREXCEPTION", e, calendarUrl, e.getMessage());
            }
        }
        
        return eventList;
    }
    
    public String getGroupsCalendarWithinMonth(String cn, String prefixAttribute) throws InternalException {
        List<VEvent> eventList = null;
        
        Group group = null;
        User user = null;
        
        Calendar calendar = null;
        
        try {
            group = groupDao.getGroupByCn(cn);
        } catch (NamingException e) {
            throw new InternalException("NAMINGEXCEPTION", e.getMessage());
        }
        
        if ( group == null ) throw new InternalException("NOSUCHGROUP", cn);
        
        calendar = new Calendar();
        calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);
        
        for ( String userDn : group.getUniqueMembers() ) {
            try {
                user = userDao.getUserByDn(userDn);
            } catch (NamingException e) {
                e.printStackTrace();
                continue;
            }
            if ( user == null ) continue;
            if ( user.getCalCalUri() == null ) continue;
            
            try {
                eventList = icalDao.getRangedEventUsingUri(user.getCalCalUri(), 30L);
                
                for ( VEvent event : eventList ) {
                    PropertyList originalProperties = event.getProperties();
                    
                    Property originalSummary = originalProperties.getProperty(Summary.SUMMARY);
                    Property newSummary = null;
                    if ( prefixAttribute != null ) {
                        if ( prefixAttribute.equalsIgnoreCase("uid") ) {
                            newSummary = new Summary("[" + user.getUid() + "] " + originalSummary.getValue());
                        } else if ( prefixAttribute.equalsIgnoreCase("displayName") ) {
                            newSummary = new Summary("[" + user.getDisplayName() + "] " + originalSummary.getValue());
                        } else if ( prefixAttribute.equalsIgnoreCase("ALTID") ) {
                            String altId = user.getAltId();
                            newSummary = new Summary("[" + ( altId == null ? user.getUid() : altId ) + "] " + originalSummary.getValue());
                        }
                    } else {
                        newSummary = new Summary("[" + user.getUid() + "] " + originalSummary.getValue());
                    }
                    
                    originalProperties.remove(originalSummary);
                    originalProperties.add(newSummary);
                    
                    VEvent newEvent = new VEvent(originalProperties);
                    
                    calendar.getComponents().add(newEvent);
                }
            } catch (IOException e) {
                e.printStackTrace();
                //throw new InternalException("IOEXCEPTION", e, calendarUrl, e.getMessage());
            } catch (ParserException e) {
                e.printStackTrace();
                //throw new InternalException("PARSEREXCEPTION", e, calendarUrl, e.getMessage());
            }
        }
        
        return calendar.toString();
    }
    
    @Autowired
    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }
    
    @Autowired
    public void setGroupDao(GroupDAO groupDao) {
        this.groupDao = groupDao;
    }
    
    @Autowired
    public void setIcalDao(ICalDAO icalDao) {
        this.icalDao = icalDao;
    }
    
    private UserDAO userDao = null;
    private GroupDAO groupDao = null;
    private ICalDAO icalDao = null;
}
