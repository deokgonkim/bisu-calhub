package com.idatabank.bisu.calhub.control.data;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;

import net.fortuna.ical4j.model.component.VEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idatabank.bisu.calhub.exception.InternalException;
import com.idatabank.bisu.calhub.model.CalendarEvent;
import com.idatabank.bisu.calhub.service.IcalCalendarService;

@Controller
@RequestMapping("/data/cal")
public class IcalDataController {
    
    @RequestMapping("/user/{uid}.ics")
    public ResponseEntity<String> getUsersIcs(@PathVariable(value = "uid") String uid,
                                              @RequestParam(value = "all", required = false) String all) throws InternalException {
        String icalString = null;
        
        try {
            if ( all != null && all.equalsIgnoreCase("true") ) {
                icalString = icalCalendarService.getUsersCalendar(uid);
            } else {
                icalString = icalCalendarService.getUsersCalendarWithinMonth(uid);
            }
        } catch (InternalException e) {
            throw e;
        }
        
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/calendar; charset=UTF-8");
        return new ResponseEntity<String>(icalString, responseHeaders, HttpStatus.OK);
    }
    
    @RequestMapping("/user/{uid}.json")
    public @ResponseBody Model getUsersJson(@PathVariable(value = "uid") String uid,
                                            @RequestParam(value = "all", required = false) String all,
                                            Model model) throws InternalException {
        List<VEvent> rawEventList = null;
        List<CalendarEvent> eventList = null;
        
        try {
            if ( all != null && all.equalsIgnoreCase("true") ) {
                rawEventList = icalCalendarService.getUsersEventList(uid);
            } else {
                rawEventList = icalCalendarService.getUsersEventListWithinMonth(uid);
            }
            if ( rawEventList != null ) {
                for ( VEvent vEvent : rawEventList ) {
                    if ( eventList == null ) eventList = new LinkedList<CalendarEvent>();
                    CalendarEvent event = null;
                    event = new CalendarEvent();
                    event.setUid(vEvent.getUid().getValue());
                    event.setSummary(vEvent.getSummary().getValue());
                    if ( vEvent.getDateStamp() != null ) event.setDateStamp(vEvent.getDateStamp().getDate());
                    else event.setDateStamp( new Date() );
                    if ( vEvent.getOrganizer() != null ) event.setOrganizer(vEvent.getOrganizer().getValue());
                    else event.setOrganizer( uid );
                    if ( vEvent.getLocation() != null ) event.setLocation(vEvent.getLocation().getValue());
                    else event.setLocation( "" );
                    if ( vEvent.getStartDate() != null ) event.setStartDate(vEvent.getStartDate().getDate());
                    if ( vEvent.getEndDate() != null ) event.setEndDate(vEvent.getEndDate().getDate());
                    if ( vEvent.getDescription() != null ) event.setDescription(vEvent.getDescription().getValue());
                    else event.setDescription( "" );
                    eventList.add(event);
                }
            }
        } catch (InternalException e) {
            model.addAttribute("success", "false");
            throw e;
        }
        
        model.addAttribute("success", "true");
        model.addAttribute("eventList", eventList);
        
        return model;
    }
    
    @RequestMapping("/group/{group}.ics")
    public ResponseEntity<String> getGroupsIcs(@PathVariable(value = "group") String group,
                                               @RequestParam(value = "prefix", required = false) String prefix,
                                               @RequestParam(value = "all", required = false) String all) throws InternalException {
        String icalString = null;
        
        try {
            if ( all != null && all.equalsIgnoreCase("true") ) {
                icalString = icalCalendarService.getGroupsCalendar(group, prefix);
            } else {
                icalString = icalCalendarService.getGroupsCalendarWithinMonth(group, prefix);
            }
        } catch (InternalException e) {
            throw e;
        }
        
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/calendar; charset=UTF-8");
        return new ResponseEntity<String>(icalString, responseHeaders, HttpStatus.OK);
    }
    
    @ExceptionHandler({NamingException.class})
    public @ResponseBody String handleException(Exception e) {
        e.printStackTrace();
        return e.getMessage();
    }
    
    @Autowired
    public void setIcalCalendarService(IcalCalendarService icalCalendarService) {
        this.icalCalendarService = icalCalendarService;
    }
    
    private IcalCalendarService icalCalendarService = null;
}
