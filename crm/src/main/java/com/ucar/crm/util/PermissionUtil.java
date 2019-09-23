package com.ucar.crm.util;

import com.ucar.crm.domain.Employee;
import com.ucar.crm.domain.Menu;
import com.ucar.crm.domain.Permission;
import com.ucar.crm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class PermissionUtil {

    //存储所有要进行验证的权限:存储的是权限表达式的字符串
    private static List<String> allPermissions;

    private static IPermissionService permissionService;

    @Autowired
    public void setPermissionService(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    //进行权限验证的方法
    public static boolean checkPermission(String function) {
        //获取当前登录员工的对象，并把该员工的所有权限查询出来放入session中
        Employee employee = (Employee) UserContext.getRequest().getSession().getAttribute(UserContext.USERINSESSION);
        //如果是管理员直接放行
        if (employee.getAdmin()) {
            return true;
        }

        //判断allPermissions：如果为null，则进行相应的初始化数据
        if (allPermissions == null) {
            List<Permission> permissions = permissionService.selectAll();
            allPermissions = new ArrayList<>();
            for (Permission permission : permissions) {
                allPermissions.add(permission.getResource());
            }
            System.out.println("allPermissions-->" + allPermissions);
        }

        //判断该权限是否存在数据库中，如果存在则进行验证
        if (allPermissions.contains(function)) {
            //包含function，需要进行具体的权限验证
            //登录员工有的权限
            List<String> selfPermission = (List<String>) UserContext.getRequest().getSession().getAttribute(UserContext.PERMISSIONINSESSION);
            //判断员工拥有的权限集合中是否包含了这个权限表达式：
            if (selfPermission.contains(function)) {
                // 1、完全匹配
                return true;
            } else {
                // 2、ALL匹配
                String allFunction = function.split(":")[0] + ":ALL";
//                System.out.println(allFunction);
                return selfPermission.contains(allFunction);
            }
        }
        //放行
        return true;
    }

    /**
     * 菜单的过滤
     *
     * @param menus
     */
    public static void checkMenuPermission(List<Menu> menus) {
        //1.遍历取出集合中的每一个菜单
        Iterator<Menu> iterator = menus.iterator();
        while (iterator.hasNext()) {
            Menu menu = iterator.next();
            //2.判断这个菜单是否有权限对象:如果有则进行权限验证
            if (menu.getPermission() != null) {
                //如果没有权限则进行移除
                if (!checkPermission(menu.getPermission().getResource())) {
                    iterator.remove();
                }
            }
            //如果有子菜单，则对子菜单进行对应的验证
            if (menu.getChildren().size() > 0) {
                checkMenuPermission(menu.getChildren());
            }
        }
    }
}
