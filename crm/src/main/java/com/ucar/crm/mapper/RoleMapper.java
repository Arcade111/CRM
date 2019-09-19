package com.ucar.crm.mapper;

import com.ucar.crm.domain.Role;
import com.ucar.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    Long queryPageCount();

    List<Role> queryPageResult(QueryObject queryObject);

    void insertRelation(@Param("rid") Long rId, @Param("pid") Long pId);

    void deleteRelation(Long rid);
}