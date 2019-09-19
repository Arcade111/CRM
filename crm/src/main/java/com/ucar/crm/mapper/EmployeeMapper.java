package com.ucar.crm.mapper;

import com.ucar.crm.domain.Employee;
import com.ucar.crm.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    Employee getEmployeeByLogin(@Param("username") String username , @Param("password") String password);

    Long queryPageCount();

    List<Employee> queryPageResult(EmployeeQueryObject queryObject);

    //设置离职
    void remove(Long id);
}