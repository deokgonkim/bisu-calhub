package com.idatabank.bisu.calhub.dao;

import java.util.Hashtable;

import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;

public abstract class AbstractLdapDAO {
    protected InitialLdapContext getContext() throws NamingException {
        InitialLdapContext context = null;
        
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put("java.naming.factory.initial", ctxFactory);
        env.put("java.naming.provider.url", ldapUrl + baseDn);

        env.put("java.naming.security.authentication", authentication);
        
        context = new InitialLdapContext(env, null);
        return context;
    }
    
    protected void releaseContext(InitialLdapContext context) {
        if ( context != null ) {
            try {
                context.close();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void setCtxFactory(String ctxFactory) {
        this.ctxFactory = ctxFactory;
    }
    
    public void setLdapUrl(String ldapUrl) {
        this.ldapUrl = ldapUrl;
    }
    
    public void setBaseDn(String baseDn) {
        this.baseDn = baseDn;
    }
    
    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }
    
    private String ctxFactory = "com.sun.jndi.ldap.LdapCtxFactory";
    protected String ldapUrl = "ldap://localhost:389/";
    protected String baseDn = "dc=domain,dc=com";
    protected String authentication = "simple";
}
