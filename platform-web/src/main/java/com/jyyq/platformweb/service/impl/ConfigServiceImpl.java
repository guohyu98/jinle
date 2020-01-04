package com.jyyq.platformweb.service.impl;

import com.jyyq.platformweb.dao.ConfigBedType;
import com.jyyq.platformweb.dao.ConfigShowInfo;
import com.jyyq.platformweb.mapper.ConfigBedTypeMapper;
import com.jyyq.platformweb.mapper.ConfigShowInfoMapper;
import com.jyyq.platformweb.service.ConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Resource
    ConfigBedTypeMapper configBedTypeMapper;
    @Resource
    ConfigShowInfoMapper configShowInfoMapper;

    @Override
    public List<ConfigBedType> selectListConfigBedType() {
        return configBedTypeMapper.selectAll();
    }

    @Override
    public List<ConfigShowInfo> selectListConfigShowInfo() {
        return configShowInfoMapper.selectAll();
    }
}
