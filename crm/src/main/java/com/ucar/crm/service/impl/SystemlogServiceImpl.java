package com.ucar.crm.service.impl;

import com.ucar.crm.domain.Systemlog;
import com.ucar.crm.mapper.SystemlogMapper;
import com.ucar.crm.service.ISystemlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemlogServiceImpl implements ISystemlogService {

    @Autowired
    private SystemlogMapper systemlogMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return systemlogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Systemlog record) {
        return systemlogMapper.insert(record);
    }

    @Override
    public Systemlog selectByPrimaryKey(Long id) {
        return systemlogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Systemlog> selectAll() {
        return systemlogMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Systemlog record) {
        return systemlogMapper.updateByPrimaryKey(record);
    }
}
