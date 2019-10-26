package com.wyf.controller;


import com.wyf.entity.Role;
import com.wyf.entity.UserRole;
import com.wyf.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Yunfei
 */
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/role")
    public String toRoleHtml() {
        return "/role/Role";
    }
    @RequestMapping("/role/changerole")
    public String changeRole(Model m, @RequestParam String uid) {
        List<Role> roles = roleService.getRoles(uid);
        m.addAttribute("roles",roles);
        m.addAttribute("changid",uid);
        return "/role/changerole";
    }
    @RequestMapping("/role/change")
    public String reRole(@RequestParam String uid,String rid){

        UserRole userRole = new UserRole();
        userRole.setRid(Integer.parseInt(rid));
        userRole.setUid(Integer.parseInt(uid));
        if (roleService.changeRole(userRole)>0){
            return "/role/success";
        }
        return "/role/error";
    }

}
