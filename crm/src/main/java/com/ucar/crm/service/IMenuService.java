package com.ucar.crm.service;

import com.ucar.crm.domain.Menu;
import com.ucar.crm.page.PageResult;
import com.ucar.crm.query.QueryObject;

import java.util.List;

public interface IMenuService {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Long id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    List<Menu> getRootMenu();
}
