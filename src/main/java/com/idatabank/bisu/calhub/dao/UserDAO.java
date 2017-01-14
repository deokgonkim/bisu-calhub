package com.idatabank.bisu.calhub.dao;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;

import org.springframework.stereotype.Repository;

import com.idatabank.bisu.calhub.model.User;

@Repository
public class UserDAO extends AbstractLdapDAO {
    
    public List<User> getAllUsers() throws NamingException {
        List<User> users = null;
        User user = null;
        
        InitialLdapContext ctx = null;
        
        NamingEnumeration<SearchResult> ne = null;
        
        try {
            ctx = getContext();
            
            SearchControls sc = new SearchControls();
            sc.setSearchScope(SearchControls.ONELEVEL_SCOPE);
            
            ne = ctx.search(searchBase, "(objectClass=*)", null, sc);
                        
            while ( ne.hasMoreElements() ) {
                if ( users == null ) users = new LinkedList<User>();
                SearchResult sr = (SearchResult)ne.nextElement();
                
                user = new User();
                
                user.setUid((String)sr.getAttributes().get("uid").get());
                user.setDn(sr.getNameInNamespace());
                
                if ( sr.getAttributes().get("displayName") != null ) {
                    user.setDisplayName((String)sr.getAttributes().get("displayName").get());
                }
                if ( sr.getAttributes().get("calCalURI") != null ) {
                    user.setCalCalUri((String)sr.getAttributes().get("calCalURI").get());
                }
                
                users.add(user);
            }
        } catch ( NamingException e ) {
            throw e;
        } finally {
            releaseContext(ctx);
        }
        
        return users;
    }
    
    public User getUserByUid(String uid) throws NamingException {
        User user = null;
        
        InitialLdapContext ctx = null;
        
        NamingEnumeration<SearchResult> ne = null;
        
        try {
            ctx = getContext();
            
            SearchControls sc = new SearchControls();
            sc.setSearchScope(SearchControls.ONELEVEL_SCOPE);
            
            ne = ctx.search(searchBase, "(uid={0})", new Object[] { uid }, sc);
                        
            while ( ne.hasMoreElements() ) {
                SearchResult sr = (SearchResult)ne.nextElement();
                
                user = new User();
                
                user.setUid(uid);
                user.setDn(sr.getNameInNamespace());
                
                if ( sr.getAttributes().get("displayName") != null ) {
                    user.setDisplayName((String)sr.getAttributes().get("displayName").get());
                }
                if ( sr.getAttributes().get("calCalURI") != null ) {
                    user.setCalCalUri((String)sr.getAttributes().get("calCalURI").get());
                }
            }
        } catch ( NamingException e ) {
            throw e;
        } finally {
            releaseContext(ctx);
        }
        
        return user;
    }
    
    public User getUserByDn(String dn) throws NamingException {
        User user = null;
        
        InitialLdapContext ctx = null;
        
        NamingEnumeration<SearchResult> ne = null;
        
        try {
            ctx = getContext();
            
            SearchControls sc = new SearchControls();
            sc.setSearchScope(SearchControls.OBJECT_SCOPE);
            
            ne = ctx.search(dn.replaceAll("," + baseDn, ""), "(objectClass=*)", null, sc);
            
            while ( ne.hasMoreElements() ) {
                SearchResult sr = (SearchResult)ne.nextElement();
                
                user = new User();
                
                user.setUid((String)sr.getAttributes().get("uid").get());
                user.setDn(sr.getNameInNamespace());
                
                if ( sr.getAttributes().get("displayName") != null ) {
                    user.setDisplayName((String)sr.getAttributes().get("displayName").get());
                }
                if ( sr.getAttributes().get("employeeNumber") != null ) {
                    user.setEmployeeNumber((String)sr.getAttributes().get("employeeNumber").get());
                }
                if ( sr.getAttributes().get("description") != null ) {
                    Enumeration<?> descriptions = sr.getAttributes().get("description").getAll();
                    while ( descriptions.hasMoreElements() ) {
                        String value = descriptions.nextElement().toString();
                        if ( value != null && value.indexOf("ALTID:") == 0 ) {
                            user.setAltId(value.substring(6));
                        }
                    }
                }
                if ( sr.getAttributes().get("calCalURI") != null ) {
                    user.setCalCalUri((String)sr.getAttributes().get("calCalURI").get());
                }
            }
            
        } catch ( NamingException e ) {
            throw e;
        } finally {
            releaseContext(ctx);
        }
        
        return user;
    }
    
    public void setSearchBase(String searchBase) {
        this.searchBase = searchBase;
    }

    private String searchBase = "ou=Users";
}
