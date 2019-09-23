package com.ucar.crm.web.interceptor;

import com.ucar.crm.util.PermissionUtil;
import com.ucar.crm.util.UserContext;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //把请求对象放到threadlocal中
        UserContext.setRequest(httpServletRequest);

        Object employee = httpServletRequest.getSession().getAttribute(UserContext.USERINSESSION);
        System.out.println("LoginInterceptor.preHandle下的employee-->"+employee);
        if (employee != null) {
            //如果已经登录，就进行权限验证
            //强转成HandlerMethod对象（安全性判断;如果o对象是HandlerMethod的实例，才进行强转）
            if(o instanceof HandlerMethod){
                HandlerMethod handlerObj = (HandlerMethod) o;
                //把请求url转换成权限表达式
                String function = handlerObj.getBean().getClass().getName() + ":" + handlerObj.getMethod().getName();
                System.out.println("function-->" + function);
                //进行权限验证的逻辑
                if (!PermissionUtil.checkPermission(function)) {
                    //判断该请求是否是ajax请求，判断该方法上是否贴了@ResponseBody注解，有：ajax，无：y页面
                    if (handlerObj.getMethod().isAnnotationPresent(ResponseBody.class)) {
                        //返回json数据{"success":false,"msg":"您没有此权限操作！"}
                        httpServletResponse.setCharacterEncoding("utf-8");
                        httpServletResponse.getWriter().print("{\"success\":false,\"msg\":\"您没有此权限操作！\"}");
                    } else {
                        //跳转到没有权限页面
                        httpServletResponse.sendRedirect("/noPermission.jsp");
                    }
                    return false;
                }
                //放行
                return true;
            }
        }
        //重定向到登陆页面
        httpServletResponse.sendRedirect("/login.jsp");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
