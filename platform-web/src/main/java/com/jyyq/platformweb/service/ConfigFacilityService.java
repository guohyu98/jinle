package com.jyyq.platformweb.service;

import com.jyyq.platformweb.dao.ConfigFacility;

import java.util.List;

public interface ConfigFacilityService {

	int insert(ConfigFacility record);
	int updateByPrimaryKey(ConfigFacility record);
	int deleteByPrimaryKey(Integer id);
	ConfigFacility selectByPrimaryKey(Integer id);
	List<ConfigFacility> selectListAll();
	List<ConfigFacility> selectListParent();
	List<ConfigFacility> selectListChildren();

}
