package com.ucar.crm.web.controller;

import com.ucar.crm.domain.Employee;
import com.ucar.crm.domain.Role;
import com.ucar.crm.page.PageResult;
import com.ucar.crm.query.EmployeeQueryObject;
import com.ucar.crm.query.QueryObject;
import com.ucar.crm.service.IRoleService;
import com.ucar.crm.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/role")
    public String inde(){
        return "role";
    }

    @RequestMapping("/role_save")
    @ResponseBody
    public JsonResult save(Role role){
        JsonResult result = new JsonResult();
        try {
            roleService.insert(role);
            result.setSuccess(true);
            result.setMsg("角色保存成功!");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMsg("角色保存失败!");
        }
        return result;
    }

    @RequestMapping("/role_list")
    @ResponseBody
    public PageResult list(QueryObject queryObject){
        return roleService.queryPageResult(queryObject);
    }

    @RequestMapping("/role_update")
    @ResponseBody
    public JsonResult edit(Role role){
        JsonResult result = new JsonResult();
        try {
            roleService.updateByPrimaryKey(role);
            result.setSuccess(true);
            result.setMsg("更新成功!");
        }catch (Exception e){
            //可以做日志记录操作！
            result.setSuccess(false);
            result.setMsg("角色权限更新失败!");
        }
        return result;
    }

    @RequestMapping("/role_delete")
    @ResponseBody
    public JsonResult edit(Long id){
        JsonResult result = new JsonResult();
        try {
            roleService.deleteByPrimaryKey(id);
            result.setSuccess(true);
            result.setMsg("删除成功!");
        }catch (Exception e){
            //可以做日志记录操作！
            result.setSuccess(false);
            result.setMsg("删除失败!");
        }
        return result;
    }
}
