package com.idatabank.bisu.calhub.dao;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;

import org.springframework.stereotype.Repository;

import com.idatabank.bisu.calhub.model.Group;

@Repository
public class GroupDAO extends AbstractLdapDAO {
    public Group getGroupByCn(String cn) throws NamingException {
        Group group = null;
        
        InitialLdapContext ctx = null;
        
        NamingEnumeration<SearchResult> ne = null;
        
        try {
            ctx = getContext();
            
            SearchControls sc = new SearchControls();
            sc.setSearchScope(SearchControls.ONELEVEL_SCOPE);
            
            ne = ctx.search(searchBase, "(cn={0})", new Object[] { cn }, sc);
                        
            while ( ne.hasMoreElements() ) {
                SearchResult sr = (SearchResult)ne.nextElement();
                
                group = new Group();
                
                group.setCn(cn);
                
                if ( sr.getAttributes().get("uniqueMember") != null ) {
                    
                    NamingEnumeration<?> ne2 = sr.getAttributes().get("uniqueMember").getAll();
                    while ( ne2.hasMoreElements() ) {
                        String dn = (String)ne2.nextElement();
                        group.addUniqueMembers(dn);
                    }
                }
            }
        } catch ( NamingException e ) {
            throw e;
        } finally {
            releaseContext(ctx);
        }
        
        return group;
    }
    
    public void setSearchBase(String searchBase) {
        this.searchBase = searchBase;
    }

    private String searchBase = "ou=Groups";
}