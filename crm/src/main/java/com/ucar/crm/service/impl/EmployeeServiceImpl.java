package com.ucar.crm.service.impl;

import com.ucar.crm.domain.Employee;
import com.ucar.crm.mapper.EmployeeMapper;
import com.ucar.crm.page.PageResult;
import com.ucar.crm.query.EmployeeQueryObject;
import com.ucar.crm.query.QueryObject;
import com.ucar.crm.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Employee record) {
        return employeeMapper.insert(record);
    }

    @Override
    public Employee selectByPrimaryKey(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Employee record) {
        return employeeMapper.updateByPrimaryKey(record);
    }

    @Override
    public Employee getEmployeeByLogin(String username, String password) {
        return employeeMapper.getEmployeeByLogin(username,password);
    }


    @Override
    public PageResult queryPageResult(EmployeeQueryObject queryObject) {
        Long total = employeeMapper.queryPageCount();
        if(total>0){
            //查询结果集
            return new PageResult(total,employeeMapper.queryPageResult(queryObject));
        }
        return new PageResult(total, Collections.EMPTY_LIST);
    }

    @Override
    public void remove(Long id) {
        employeeMapper.remove(id);
    }
}
