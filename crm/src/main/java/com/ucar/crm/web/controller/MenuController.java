package com.ucar.crm.web.controller;

import com.ucar.crm.domain.Menu;
import com.ucar.crm.domain.Role;
import com.ucar.crm.page.PageResult;
import com.ucar.crm.query.QueryObject;
import com.ucar.crm.service.IMenuService;
import com.ucar.crm.service.IRoleService;
import com.ucar.crm.util.JsonResult;
import com.ucar.crm.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @RequestMapping("/menu_getRootMenu")
    @ResponseBody
    public List<Menu> selectAll(HttpSession session){
        return (List<Menu>) session.getAttribute(UserContext.MENUINSESSION);
    }
}
