package com.idatabank.bisu.calhub.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.component.VEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-config.xml", "classpath:db-config.xml" })
public class TestICalDAO {
    
    @Before
    public void setUp() {
        testStuff = new HashMap<String, String>();
        testStuff.put("kim37", "http://www.google.com/calendar/ical/kim37kim%40gmail.com/private-33d64d623ae4c83480b781af3dbf7862/basic.ics");
        testStuff.put("jwlee", "http://www.google.com/calendar/ical/en91ne%40gmail.com/private-e02923de888f639566f2f5cb703ca78c/basic.ics");
        testStuff.put("dgkim", "https://www.dgkim.net/horde4/rpc.php/kronolith/dgkim/qA4bHDA2xJ5QcB0hp-PW5wA.ics");
        testStuff.put("shahn", "https://www.google.com/calendar/ical/ash122369%40gmail.com/private-e3b6227acae66c8bf10adcaaae6e2d45/basic.ics");
        testStuff.put("gspark", "https://www.google.com/calendar/ical/moonnine0502%40gmail.com/private-6c7cba32db8bab4f7e75a37ee5e8174f/basic.ics");
        testStuff.put("gjpark", "https://www.google.com/calendar/ical/qkr4qkr4%40gmail.com/private-6d8850e269f69b7e3ce4f03c2d97b452/basic.ics");
        testStuff.put("ysan", "https://www.google.com/calendar/ical/anyoung0823%40gmail.com/private-db71dc4eca12fa145a5e195e65972660/basic.ics");
        testStuff.put("chris", "http://www.google.com/calendar/ical/sadmysoul24%40gmail.com/public/basic.ics");
        testStuff.put("gtkim", "http://www.dgkim.net/dav/users/gtkim/%EF%BF%BD%EB%9A%AF%EA%B6%97_%EF%BF%BD%EC%87%B1%EC%A0%99.ics");
        testStuff.put("gtkim", "http://www.dgkim.net/dav/users/gtkim/%ed%9a%8c%ec%82%ac_%ec%9d%bc%ec%a0%95.ics");
        
        //testStuff.put("ycchoi", "http://www.google.com/calendar/ical/samhawk07%40gmail.com/private-876b781a7f9a8db4fa8f34dab45b17d8/basic.ics");
        //testStuff.put("ywcheon", "http://www.google.com/calendar/ical/ywcheon%40gmail.com/private-e8902f761c06eff00d5ead74d8d1bb9a/basic.ics");
    }

    @Test
    public void testFull() {
        List<VEvent> eventList = null;
        try {
            for ( String name : testStuff.keySet() ) {
                eventList = dao.getFullEventUsingUri(testStuff.get(name));
                int count = eventList.size();
                System.out.println(String.format("Full count for %s : %d", name, count));
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        } catch (ParserException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testRanged() {
        List<VEvent> eventList = null;
        try {
            for ( String name : testStuff.keySet() ) {
                eventList = dao.getRangedEventUsingUri(testStuff.get(name), 30L);
                int count = eventList.size();
                System.out.println(String.format("Ranged count for %s : %d", name, count));
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        } catch (ParserException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    private Map<String, String> testStuff = null;
    
    @Autowired
    public void setDao(ICalDAO dao) {
        this.dao = dao;
    }
    
    private ICalDAO dao = null;
}
