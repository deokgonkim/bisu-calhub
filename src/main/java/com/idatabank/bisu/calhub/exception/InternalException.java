package com.idatabank.bisu.calhub.exception;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class InternalException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 2323534522644066914L;

    public InternalException() {
        super();
    }
    
    public InternalException(String message) {
        super(message);
        this.internalMessage = message;
        Enumeration<?> keys = rb.getKeys();
        while ( keys.hasMoreElements() ) {
            String key = keys.nextElement().toString();
            if ( key != null && key.equalsIgnoreCase(message) ) {
                this.message = rb.getString(message);
            }
        }
        if ( this.message == null ) this.message = message;
    }
    
    public InternalException(String message, Object ... args) {
        super(message);
        this.internalMessage = message;
        Enumeration<?> keys = rb.getKeys();
        while ( keys.hasMoreElements() ) {
            String key = keys.nextElement().toString();
            if ( key != null && key.equalsIgnoreCase(message) ) {
                this.message = MessageFormat.format(rb.getString(message), args);
            }
        }
        if ( this.message == null ) this.message = message + extractArgs(args);
    }
    
    public InternalException(Throwable t) {
        super(t);
    }
    
    public InternalException(String message, Throwable t) {
        super(t);
        this.internalMessage = message;
        Enumeration<?> keys = rb.getKeys();
        while ( keys.hasMoreElements() ) {
            String key = keys.nextElement().toString();
            if ( key != null && key.equalsIgnoreCase(message) ) {
                this.message = rb.getString(message);
            }
        }
        if ( this.message == null ) this.message = message;
    }
    
    public InternalException(String message, Throwable t, Object ... args) {
        super(t);
        this.internalMessage = message;
        Enumeration<?> keys = rb.getKeys();
        while ( keys.hasMoreElements() ) {
            String key = keys.nextElement().toString();
            if ( key != null && key.equalsIgnoreCase(message) ) {
                this.message = MessageFormat.format(rb.getString(message), args);
            }
        }
        if ( this.message == null ) this.message = message + extractArgs(args);
    }
    
    @Override
    public String getMessage() {
        if ( message == null ) return super.getMessage();
        else return message;
    }
    
    public String getInternalMessage() {
        return internalMessage;
    }
    
    private String extractArgs(Object ... args) {
        StringBuilder sb = null;
        sb = new StringBuilder();
        for ( Object arg : args ) {
            if ( sb.length() != 0 ) sb.append(",");
            sb.append("{ ").append(arg.getClass());
            sb.append("[").append(arg.toString()).append("] }");
        }
        
        return sb.toString();
    }
    
    private String message = null;
    private String internalMessage = null;
    
    private static final ResourceBundle rb = ResourceBundle.getBundle("com.idatabank.bisu.calhub.exception.InternalException", Locale.KOREA);
}
