package com.ucar.crm.web.controller;

import com.ucar.crm.domain.Permission;
import com.ucar.crm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/permission_list")
    @ResponseBody
    public List<Permission> permission(){
        return permissionService.selectAll();
    }

    @RequestMapping("/getPermissionByRid")
    @ResponseBody
    public List<Permission> permissionsByRid(Long rid){
        return permissionService.getPermissionByRid(rid);
    }

}
