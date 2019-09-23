package com.ucar.crm.web.controller;

import com.ucar.crm.domain.Employee;
import com.ucar.crm.domain.Menu;
import com.ucar.crm.page.PageResult;
import com.ucar.crm.query.EmployeeQueryObject;
import com.ucar.crm.service.IEmployeeService;
import com.ucar.crm.service.IMenuService;
import com.ucar.crm.service.IPermissionService;
import com.ucar.crm.util.JsonResult;
import com.ucar.crm.util.PermissionUtil;
import com.ucar.crm.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IMenuService menuService;
    /**
     * 登录检查
     * @param username
     * @param password
     * @param request
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login(String username, String password, HttpServletRequest request) {
        //把请求对象放到threadlocal中
        UserContext.setRequest(request);

        //去数据库查询是否有对应的账号和密码
        Employee employee = employeeService.getEmployeeByLogin(username, password);
        JsonResult jsonResult = new JsonResult();
        if (employee != null) {
            //将employee放入session中
            request.getSession().setAttribute(UserContext.USERINSESSION, employee);
            jsonResult.setSuccess(true);

            //把员工所拥有的权限放入session中
            request.getSession().setAttribute(UserContext.PERMISSIONINSESSION, permissionService.getPermissionByEid(employee.getId()));
            //菜单权限控制，把员工能访问的菜单放入session中
            List<Menu> menu = menuService.getRootMenu();
            //菜单过滤，把没有权限访问的菜单过滤掉
            PermissionUtil.checkMenuPermission(menu);
            request.getSession().setAttribute(UserContext.MENUINSESSION,menu);

        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg("账号密码不匹配，请重新输入！");
        }
        return jsonResult;
    }


    /**
     * 员工首页
     * @return
     */
    @RequestMapping("/employee")
    public String index() {
        return "employee";
    }

    /**
     * 展示所有员工
     * @param queryObject
     * @return
     */
    @RequestMapping("/employee_list")
    @ResponseBody
    public PageResult list(EmployeeQueryObject queryObject) {
        return employeeService.queryPageResult(queryObject);
    }

    /**
     * 新增保存一个员工
     * @param employee
     * @return
     */
    @RequestMapping("/employee_save")
    @ResponseBody
    public JsonResult save(Employee employee) {
        JsonResult result = new JsonResult();
        try {
            employeeService.insert(employee);
            result.setSuccess(true);
            result.setMsg("保存成功!");
//            int i = 10/0;//模拟保存失败
        } catch (Exception e) {
            e.printStackTrace();
            //可以做日志记录操作！
            result.setSuccess(false);
            result.setMsg("保存失败!");
        }
        return result;
    }

    /**
     * 更新一个员工
     * @param employee
     * @return
     */
    @RequestMapping("/employee_update")
    @ResponseBody
    public JsonResult edit(Employee employee) {
        JsonResult result = new JsonResult();
        try {
            employeeService.updateByPrimaryKey(employee);
            result.setSuccess(true);
            result.setMsg("编辑成功!");
//            int i = 10/0;//模拟保存失败
        } catch (Exception e) {
            //可以做日志记录操作！
            result.setSuccess(false);
            result.setMsg("编辑失败!");
        }
        return result;
    }

    /**
     * 删除一个员工
     * @param request
     * @return
     */
    @RequestMapping("/employee_delete")
    @ResponseBody
    public JsonResult delete(HttpServletRequest request) {
        JsonResult result = new JsonResult();
        try {
            employeeService.deleteByPrimaryKey(Long.valueOf(request.getParameter("id")));
            result.setSuccess(true);
            result.setMsg("删除成功!");
        } catch (Exception e) {
            //可以做日志记录操作！
            result.setSuccess(false);
            result.setMsg("删除失败!");
        }
        return result;
    }

    /**
     * 设置员工的离职状态
     * @param request
     * @return
     */
    @RequestMapping("/employee_remove")
    @ResponseBody
    public JsonResult updateState(HttpServletRequest request) {
        JsonResult result = new JsonResult();
        try {
            employeeService.remove(Long.valueOf(request.getParameter("id")));
            result.setSuccess(true);
            result.setMsg("修改成功!");
        } catch (Exception e) {
            //可以做日志记录操作！
            result.setSuccess(false);
            result.setMsg("修改失败!");
        }
        return result;
    }

    @RequestMapping("/getRidByEid")
    @ResponseBody
    public List<Long> getRidByEid(Long eid) {
        return employeeService.getRidByEid(eid);
    }
}
