package com.idatabank.bisu.calhub.model;

public class Menu {
    private String id = null;
    private String text = null;
    private boolean leaf = false;
    
    private String viewName = null;
    private String menuDescription = null;
    
    private String url = null;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public boolean getLeaf() {
        return leaf;
    }
    
    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }
    
    public String getViewName() {
        return viewName;
    }
    
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
    
    public String getMenuDescription() {
        return menuDescription;
    }
    
    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
}
