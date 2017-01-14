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
public class TestIcalCalendarService_GroupsCalendar {

    @Test
    public void testGetGroupsCalendarWithinMonth_Nosuchgroup() {
        try {
            service.getGroupsCalendarWithinMonth("dgkimm", "displayName");
            fail("MUST FAIL");
        } catch (InternalException e) {
            assertEquals("NOSUCHGROUP", e.getInternalMessage());
            assertEquals("No such group (dgkimm)", e.getMessage());
        }
    }
    
    @Test
    public void testGetGroupsCalendarWithinMonth_Dbsuser() {
        String icalString = null;
        try {
            icalString = service.getGroupsCalendarWithinMonth("DBSUser", "displayName");
            assertEquals("BEGIN:VCALENDAR", icalString.substring(0, 15));
            //System.out.println(icalString);
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
