package com.ucar.crm.mapper;

import com.ucar.crm.domain.Permission;
import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    List<Permission> getPermissionByRid(Long rid);

    List<String> getPermissionByEid(Long eid);
}