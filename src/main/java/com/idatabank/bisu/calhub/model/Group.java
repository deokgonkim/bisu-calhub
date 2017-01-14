package com.idatabank.bisu.calhub.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Group implements Serializable {
    private String cn = null;
    private List<String> uniqueMembers = null;
    
    public String getCn() {
        return cn;
    }
    
    public void setCn(String cn) {
        this.cn = cn;
    }
    
    public List<String> getUniqueMembers() {
        return uniqueMembers;
    }
    
    public void setUniqueMembers(List<String> uniqueMembers) {
        this.uniqueMembers = uniqueMembers;
    }
    
    public void addUniqueMembers(String uniqueMember) {
        if ( uniqueMembers == null ) uniqueMembers = new LinkedList<String>();
        this.uniqueMembers.add(uniqueMember);
    }
    
    public void removeUser(User user) {
        if ( uniqueMembers == null ) return;
        this.uniqueMembers.remove(user);
    }
}
