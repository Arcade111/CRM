package com.ucar.crm.service.impl;

import com.ucar.crm.domain.Employee;
import com.ucar.crm.domain.Role;
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
        int count = employeeMapper.insert(record);
        //处理中间表（employee_role），建立员工角色关系
        for (Role role:record.getRoles()) {
            employeeMapper.insertRelation(record.getId(),role.getId());
        }
        return count;
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
        int count = employeeMapper.updateByPrimaryKey(record);
        //先打破原来的关系
        employeeMapper.deleteRelation(record.getId());
        //在建立后来要搭建的关系
        //处理中间表（employee_role），建立员工角色关系
        for (Role role:record.getRoles()) {
            employeeMapper.insertRelation(record.getId(),role.getId());
        }
        return count;
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

    @Override
    public void insertRelation(Long eid, Long rid) {
        employeeMapper.insertRelation(eid,rid);
    }

    @Override
    public List<Long> getRidByEid(Long eid) {
        return employeeMapper.getRidByEid(eid);
    }

    @Override
    public void deleteRelation(Long eid) {
        employeeMapper.deleteRelation(eid);
    }
}
