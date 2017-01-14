package com.idatabank.bisu.calhub.control.data;

import static org.junit.Assert.*;

import javax.naming.NamingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.idatabank.bisu.calhub.exception.InternalException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-config.xml", "classpath:db-config.xml" })
public class TestIcalDataController {

    @Test
    public void testDgkim() {
        ResponseEntity<?> responseEntity = null;
        try {
            responseEntity = controller.getUsersIcs("dgkim", "false");
        } catch (InternalException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody().toString().startsWith("BEGIN:VCALENDAR"));
        assertTrue(responseEntity.getBody().toString().contains(" 작업"));
    }
    
    @Test
    public void testDbsuser() {
        ResponseEntity<?> responseEntity = null;
        try {
            responseEntity = controller.getGroupsIcs("DBSUser", "displayName", "false");
        } catch (InternalException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody().toString().startsWith("BEGIN:VCALENDAR"));
        assertTrue(responseEntity.getBody().toString().contains("[김 덕곤]"));
    }
    
    @Autowired
    public void setController(IcalDataController controller) {
        this.controller = controller;
    }
    
    private IcalDataController controller = null;
}
