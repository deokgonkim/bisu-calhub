package com.idatabank.bisu.calhub.exception;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestInternalException {

    @Test
    public void testIoexceptionValid() {
        try {
            throw new InternalException("IOEXCEPTION", new Exception("INT"), "http://www.dgkim.net/", "INT");
        } catch(InternalException e) {
            assertEquals("Error reading http://www.dgkim.net/(INT)", e.getMessage());
        }
    }
    
    @Test
    public void testIoexceptionNoArgs() {
        try {
            throw new InternalException("IOEXCEPTION", new Exception("INT"));
        } catch(InternalException e) {
            assertEquals("Error reading {0}({1})", e.getMessage());
        }
    }
    
    @Test
    public void testNosuchuserNoArgs() {
        try {
            throw new InternalException("NOSUCHUSER");
        } catch(InternalException e) {
            assertEquals("No such user ({0})", e.getMessage());
        }
    }
    
    @Test
    public void testNosuchuserWithOneArgs() {
        try {
            throw new InternalException("NOSUCHUSER", "dgkim");
        } catch(InternalException e) {
            assertEquals("No such user (dgkim)", e.getMessage());
        }
    }
    
    @Test
    public void testNonExistingExceptionWithArgs() {
        try {
            throw new InternalException("NOM", new Exception("INT"), "dgkim");
        } catch(InternalException e) {
            assertEquals("NOM{ class java.lang.String[dgkim] }", e.getMessage());
        }
    }
    
    @Test
    public void testNonExistingExceptionWithTwoArgs() {
        try {
            throw new InternalException("NOM", new Exception("INT"), "dgkim1", "dgkim2");
        } catch(InternalException e) {
            assertEquals("NOM{ class java.lang.String[dgkim1] },{ class java.lang.String[dgkim2] }", e.getMessage());
        }
    }
    
    @Test
    public void testIoexceptionWithFewerArgs() {
        try {
            throw new InternalException("IOEXCEPTION", new Exception("INT"), "http://www.dgkim.net/");
        } catch(InternalException e) {
            assertEquals("Error reading http://www.dgkim.net/({1})", e.getMessage());
        }
    }
    
    @Test
    public void testIoexceptionWithNullArgs() {
        try {
            throw new InternalException("IOEXCEPTION", new Exception("INT"), null);
        } catch(InternalException e) {
            assertEquals("Error reading {0}({1})", e.getMessage());
        }
    }
    
    @Test
    public void testIoexceptionOverArgs() {
        try {
            throw new InternalException("IOEXCEPTION", new Exception("INT"), "dgkim1", "dgkim2", "dgkim3");
        } catch(InternalException e) {
            assertEquals("Error reading dgkim1(dgkim2)", e.getMessage());
        }
    }
}
