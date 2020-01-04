package com.jyyq.platformweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.jyyq.platformweb.dao.ConfigFacility;

import java.util.List;

@Mapper
public interface ConfigFacilityMapper {

	int insert(ConfigFacility record);
	int updateByPrimaryKey(ConfigFacility record);
	int deleteByPrimaryKey(Integer id);
	ConfigFacility selectByPrimaryKey(Integer id);
	List<ConfigFacility> selectListAll();
	List<ConfigFacility> selectListParent();
	List<ConfigFacility> selectListChildren();

}
