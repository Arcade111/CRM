package com.ucar.crm.service;

import com.ucar.crm.domain.Role;
import com.ucar.crm.page.PageResult;
import com.ucar.crm.query.QueryObject;

import java.util.List;

public interface IRoleService {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    PageResult queryPageResult(QueryObject queryObject);

    void deleteRelation(Long rid);
}
