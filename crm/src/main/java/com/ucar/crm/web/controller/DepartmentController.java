package com.ucar.crm.web.controller;

import com.ucar.crm.domain.Department;
import com.ucar.crm.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/dept_selectAll")
    @ResponseBody
    public List<Department> selectAll(){
        return departmentService.selectAll();
    }
}
