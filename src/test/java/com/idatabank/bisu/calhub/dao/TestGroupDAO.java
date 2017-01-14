package com.idatabank.bisu.calhub.dao;

import static org.junit.Assert.*;

import javax.naming.NamingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.idatabank.bisu.calhub.model.Group;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-config.xml", "classpath:db-config.xml" })
public class TestGroupDAO {
    
    @Test
    public void testDbsUsers() {
        Group group = null;
        
        try {
            group = dao.getGroupByCn("DBSUser");
        } catch (NamingException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(group);
        assertEquals("DBSUser", group.getCn());
        assertTrue(group.getUniqueMembers().size()>2);
    }
    
    @Autowired
    public void setDao(GroupDAO dao) {
        this.dao = dao;
    }
    
    private GroupDAO dao = null;
}
