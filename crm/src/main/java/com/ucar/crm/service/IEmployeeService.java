package com.ucar.crm.service;

import com.ucar.crm.domain.Employee;
import com.ucar.crm.domain.Role;
import com.ucar.crm.page.PageResult;
import com.ucar.crm.query.EmployeeQueryObject;
import com.ucar.crm.query.QueryObject;

import java.util.List;

public interface IEmployeeService {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    Employee getEmployeeByLogin(String username , String password);

    PageResult queryPageResult(EmployeeQueryObject queryObject);

    void remove(Long id);

    void insertRelation(Long eid,Long rid);

    List<Long> getRidByEid(Long eid);

    void deleteRelation(Long eid);
}
