package com.ucar.crm.service.impl;

import com.ucar.crm.domain.Employee;
import com.ucar.crm.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class EmployeeServiceImplTest {

    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void insert() {
        for (int i = 0; i < 15; i++) {
            Employee employee = new Employee();
            employee.setUsername("lucy"+i);
            employee.setPassword("123");
            employee.setAdmin(true);
            employee.setState(true);
            employeeService.insert(employee);
        }
    }
}