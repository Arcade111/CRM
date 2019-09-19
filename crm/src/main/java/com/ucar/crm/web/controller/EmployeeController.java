package com.ucar.crm.web.controller;

import com.ucar.crm.domain.Employee;
import com.ucar.crm.page.PageResult;
import com.ucar.crm.query.EmployeeQueryObject;
import com.ucar.crm.service.IEmployeeService;
import com.ucar.crm.util.JsonResult;
import com.ucar.crm.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/login")
    @ResponseBody
    public JsonResult index(String username, String password, HttpSession session){
        //去数据库查询是否有对应的账号和密码
        Employee employee = employeeService.getEmployeeByLogin(username, password);
        JsonResult jsonResult = new JsonResult();
        if(employee!=null){
            //将employee放入session中
            session.setAttribute(UserContext.USERINSESSION,employee);
            jsonResult.setSuccess(true);
        }else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg("账号密码不匹配，请重新输入！");
        }
        return jsonResult;
    }

    @RequestMapping("/employee")
    public String employee(){
        return "employee";
    }

    @RequestMapping("/employee_list")
    @ResponseBody
    public PageResult list(EmployeeQueryObject queryObject){
        return employeeService.queryPageResult(queryObject);
    }

    @RequestMapping("/employee_save")
    @ResponseBody
    public JsonResult save(Employee employee){
        JsonResult result = new JsonResult();
        try {
            employeeService.insert(employee);
            result.setSuccess(true);
            result.setMsg("保存成功!");
//            int i = 10/0;//模拟保存失败
        }catch (Exception e){
            //可以做日志记录操作！
            result.setSuccess(false);
            result.setMsg("保存失败!");
        }
        return result;
    }

    @RequestMapping("/employee_update")
    @ResponseBody
    public JsonResult edit(Employee employee){
        JsonResult result = new JsonResult();
        try {
            employeeService.updateByPrimaryKey(employee);
            result.setSuccess(true);
            result.setMsg("编辑成功!");
//            int i = 10/0;//模拟保存失败
        }catch (Exception e){
            //可以做日志记录操作！
            result.setSuccess(false);
            result.setMsg("编辑失败!");
        }
        return result;
    }

    @RequestMapping("/employee_delete")
    @ResponseBody
    public JsonResult delete(HttpServletRequest request){
        JsonResult result = new JsonResult();
        try {
            employeeService.deleteByPrimaryKey(Long.valueOf(request.getParameter("id")));
            result.setSuccess(true);
            result.setMsg("删除成功!");
        }catch (Exception e){
            //可以做日志记录操作！
            result.setSuccess(false);
            result.setMsg("删除失败!");
        }
        return result;
    }

    @RequestMapping("/employee_remove")
    @ResponseBody
    public JsonResult remove(HttpServletRequest request){
        JsonResult result = new JsonResult();
        try {
            employeeService.remove(Long.valueOf(request.getParameter("id")));
            result.setSuccess(true);
            result.setMsg("修改成功!");
        }catch (Exception e){
            //可以做日志记录操作！
            result.setSuccess(false);
            result.setMsg("修改失败!");
        }
        return result;
    }
}
