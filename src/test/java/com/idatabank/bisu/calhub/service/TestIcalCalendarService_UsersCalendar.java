package com.idatabank.bisu.calhub.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.idatabank.bisu.calhub.exception.InternalException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-config.xml", "classpath:db-config.xml" })
public class TestIcalCalendarService_UsersCalendar {

    @Test
    public void testGetUsersCalendarWithinMonth_Nosuchuser() {
        try {
            service.getUsersCalendarWithinMonth("dgkimm");
            fail("MUST FAIL");
        } catch (InternalException e) {
            assertEquals("NOSUCHUSER", e.getInternalMessage());
            assertEquals("No such user (dgkimm)", e.getMessage());
        }
    }
    
    @Test
    public void testGetUsersCalendarWithinMonth_Nocalcaluri() {
        try {
            service.getUsersCalendarWithinMonth("tskimm");
            fail("MUST FAIL");
        } catch (InternalException e) {
            assertEquals("NOCALCALURI", e.getInternalMessage());
            assertEquals("The user's calCalUri is not registered in LDAP.", e.getMessage());
        }
    }
    
    @Test
    public void testGetUsersCalendarWithinMonth_Nosuchfile() {
//        try {
//            service.getUsersCalendarWithinMonth("sunyoungcho");
//            fail("MUST FAIL");
//        } catch (InternalException e) {
//            assertEquals("PARSEREXCEPTION", e.getInternalMessage());
//            assertEquals("Error parsing http://www.dgkim.net/horde/ Error at line 1:Expected [BEGIN], read [<!DOCTYPE html PUBLIC ]", e.getMessage());
//        }
    }
    
    @Test
    public void testGetUsersCalendarWithinMonth_Parsefail() {
//        try {
//            service.getUsersCalendarWithinMonth("sunyoungcho");
//            fail("MUST FAIL");
//        } catch (InternalException e) {
//            assertEquals("PARSEREXCEPTION", e.getInternalMessage());
//            assertEquals("Error parsing http://www.dgkim.net/horde/ Error at line 1:Expected [BEGIN], read [<!DOCTYPE html PUBLIC ]", e.getMessage());
//        }
    }
    
    @Test
    public void testGetUsersCalendarWithinMonth_dgkim() {
        String icalString = null;
        try {
            icalString = service.getUsersCalendarWithinMonth("dgkim");
//            System.out.println("dgkim : " + icalString);
            assertEquals("BEGIN:VCALENDAR", icalString.substring(0, 15));
        } catch (InternalException e) {
            fail(e.getMessage());
        }
    }
    
    @Autowired
    public void setService(IcalCalendarService service) {
        this.service = service;
    }
    
    private IcalCalendarService service = null;
}
