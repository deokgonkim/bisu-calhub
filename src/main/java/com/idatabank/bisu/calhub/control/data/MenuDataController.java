package com.idatabank.bisu.calhub.control.data;

import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idatabank.bisu.calhub.model.Menu;
import com.idatabank.bisu.calhub.model.User;
import com.idatabank.bisu.calhub.service.IcalCalendarService;
import com.idatabank.bisu.calhub.service.UserService;

@Controller
public class MenuDataController {
    
    @RequestMapping("/data/menus/users")
    public @ResponseBody Model getRoot(Model model) {
        List<Menu> menus = null;
        List<User> users = null;
        Menu menu = null;
        
        menus = new LinkedList<Menu>();
        
        try {
            users = userService.getAllUsers();
            
            for ( User user : users ) {
                menu = new Menu();
                menu.setId("userCal_" + user.getUid());
                menu.setText(user.getDisplayName());
                menu.setLeaf(true);
                
                menu.setViewName("calendarview");
                menu.setMenuDescription(user.getDisplayName() + "'s Calendar");
                menus.add(menu);
            }
            
        } catch (NamingException e) {
            e.printStackTrace();
            menu = new Menu();
            menu.setId("userCal_noavailable");
            menu.setText("unavailable");
            menu.setLeaf(true);
        }
        
        model.addAttribute("menu", menus);
        return model;
    }
    
    @Autowired
    public void setIcalCalendarService(IcalCalendarService icalCalendarService) {
        this.icalCalendarService = icalCalendarService;
    }
    
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    private IcalCalendarService icalCalendarService = null;
    private UserService userService = null;
}
