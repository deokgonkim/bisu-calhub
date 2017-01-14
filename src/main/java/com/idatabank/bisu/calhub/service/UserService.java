package com.idatabank.bisu.calhub.service;

import java.util.List;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idatabank.bisu.calhub.dao.UserDAO;
import com.idatabank.bisu.calhub.model.User;

@Service
public class UserService {
    
    public List<User> getAllUsers() throws NamingException {
        List<User> users = null;
        
        users = userDao.getAllUsers();
        
        return users;
    }
    
    @Autowired
    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }
    
    private UserDAO userDao = null;
}
