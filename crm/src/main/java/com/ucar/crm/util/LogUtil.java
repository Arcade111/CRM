package com.ucar.crm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucar.crm.domain.Employee;
import com.ucar.crm.domain.Systemlog;
import com.ucar.crm.service.ISystemlogService;
import com.ucar.crm.service.impl.SystemlogServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class LogUtil {
    @Autowired
    private ISystemlogService systemlogService;

    //记录日志的方法
    public void writeLog(JoinPoint point){
        if(point.getTarget() instanceof ISystemlogService){
            //记录日志的service本身不需要记录，防止进入死循环
            return;
        }

        System.out.println("-----日志记录-----");
        Systemlog log = new Systemlog();

        HttpServletRequest request = UserContext.getRequest();
        //操作ip
        log.setOpip(request.getRemoteAddr());
//        log.setOpip(request.getRemoteHost());
        //操作用户
        log.setOpuser((Employee) request.getSession().getAttribute(UserContext.USERINSESSION));
        //操作时间
        log.setOptime(new Date());
        //操作方法的表达式
        String function = point.getTarget().getClass().getName()+":"+point.getSignature().getName();
        log.setFunction(function);
        //传入的参数
        ObjectMapper mapper = new ObjectMapper();
        try {
            String params = mapper.writeValueAsString(point.getArgs());
            log.setParams(params);
//            System.out.println(params);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        systemlogService.insert(log);
    }
}
