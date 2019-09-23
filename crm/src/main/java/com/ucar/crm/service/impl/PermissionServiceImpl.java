package com.ucar.crm.service.impl;

import com.ucar.crm.domain.Permission;
import com.ucar.crm.mapper.PermissionMapper;
import com.ucar.crm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Permission record) {
        return permissionMapper.insert(record);
    }

    @Override
    public Permission selectByPrimaryKey(Long id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Permission record) {
        return permissionMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Permission> getPermissionByRid(Long rid) {
        return permissionMapper.getPermissionByRid(rid);
    }

    @Override
    public List<String> getPermissionByEid(Long eid) {
        return permissionMapper.getPermissionByEid(eid);
    }
}
