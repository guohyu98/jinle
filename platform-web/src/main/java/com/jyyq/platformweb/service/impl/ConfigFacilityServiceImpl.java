package com.jyyq.platformweb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jyyq.platformweb.dao.ConfigFacility;
import com.jyyq.platformweb.mapper.ConfigFacilityMapper;
import com.jyyq.platformweb.service.ConfigFacilityService;

@Service
public class ConfigFacilityServiceImpl implements ConfigFacilityService {

	@Resource
	ConfigFacilityMapper configFacilityMapper;

	public int insert(ConfigFacility record){
		return configFacilityMapper.insert(record);
	}
	
	public int updateByPrimaryKey(ConfigFacility record){
		return configFacilityMapper.updateByPrimaryKey(record);
	}

	public int deleteByPrimaryKey(Integer id){
		return configFacilityMapper.deleteByPrimaryKey(id);
	}

	public ConfigFacility selectByPrimaryKey(Integer id){
		return configFacilityMapper.selectByPrimaryKey(id);
	}

	public List<ConfigFacility> selectListAll(){
		return configFacilityMapper.selectListAll();
	}

	public List<ConfigFacility> selectListParent(){
		return configFacilityMapper.selectListParent();
	}
	public List<ConfigFacility> selectListChildren(){
		return configFacilityMapper.selectListChildren();
	}
}
