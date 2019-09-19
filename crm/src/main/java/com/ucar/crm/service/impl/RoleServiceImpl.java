package com.ucar.crm.service.impl;

import com.ucar.crm.domain.Permission;
import com.ucar.crm.domain.Role;
import com.ucar.crm.mapper.RoleMapper;
import com.ucar.crm.page.PageResult;
import com.ucar.crm.query.QueryObject;
import com.ucar.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        //要删除关联了外键的对象信息，必须先删除中间表的数据,否则要报错
        roleMapper.deleteRelation(id);
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Role record) {
        int count = roleMapper.insert(record);
        //处理中间表数据
        for (Permission permission : record.getPermissions()) {
            roleMapper.insertRelation(record.getId(),permission.getId());
        }
        return count;
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        int count = roleMapper.updateByPrimaryKey(record);
        //打破原来有的关系:清空该角色在中间表的数据
        roleMapper.deleteRelation(record.getId());
        //重新设置角色权限表role_permission
        //处理中间表数据
        for (Permission permission : record.getPermissions()) {
            roleMapper.insertRelation(record.getId(),permission.getId());
        }
        return count;
    }

    @Override
    public PageResult queryPageResult(QueryObject queryObject) {
        Long total = roleMapper.queryPageCount();
        if (total > 0) {
            //查询结果集
            return new PageResult(total, roleMapper.queryPageResult(queryObject));
        }
        return new PageResult(total, Collections.EMPTY_LIST);
    }

    @Override
    public void deleteRelation(Long rid) {
        roleMapper.deleteRelation(rid);
    }
}
