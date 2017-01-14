package com.idatabank.bisu.calhub.dao;

import static org.junit.Assert.*;

import javax.naming.NamingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.idatabank.bisu.calhub.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-config.xml", "classpath:db-config.xml" })
public class TestUserDAO {

    @Test
    public void testGetUserByDn() {
        User user = null;
        try {
            user = dao.getUserByDn("uid=dgkim,ou=Users,dc=idatabank,dc=com");
        } catch (NamingException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testDgkim() {
        User user = null;
        
        try {
            user = dao.getUserByUid("dgkim");
        } catch (NamingException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(user);
        assertEquals("dgkim", user.getUid());
        assertEquals("김 덕곤", user.getDisplayName());
        assertEquals("uid=dgkim,ou=Users,dc=idatabank,dc=com", user.getDn());
        assertEquals("https://www.dgkim.net/horde4/rpc.php/kronolith/dgkim/qA4bHDA2xJ5QcB0hp-PW5wA.ics", user.getCalCalUri());
    }
    
    @Test
    public void testYoungsuan() {
        User user = null;
        
        try {
            user = dao.getUserByUid("youngsuan");
        } catch (NamingException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(user);
        assertEquals("youngsuan", user.getUid());
        assertEquals("안 영수", user.getDisplayName());
        assertEquals("uid=youngsuan,ou=Users,dc=idatabank,dc=com", user.getDn());
        assertEquals("https://www.google.com/calendar/ical/anyoung0823%40gmail.com/private-db71dc4eca12fa145a5e195e65972660/basic.ics", user.getCalCalUri());
    }
    
    @Autowired
    public void setDao(UserDAO dao) {
        this.dao = dao;
    }
    
    private UserDAO dao = null;
}
