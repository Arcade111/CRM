package com.ucar.crm.util;

import javax.servlet.http.HttpServletRequest;

public class UserContext {
    public static final String USERINSESSION = "USER_IN_SESSION";
    public static final String PERMISSIONINSESSION = "PERMISSION_IN_SESSION";
    public static final String MENUINSESSION = "MENU_IN_SESSION";

    //本地线程变量的对象
    private static ThreadLocal<HttpServletRequest> local = new ThreadLocal<>();

    public static void setRequest(HttpServletRequest request){
        local.set(request);
    }

    public static HttpServletRequest getRequest(){
        return local.get();
    }
}
