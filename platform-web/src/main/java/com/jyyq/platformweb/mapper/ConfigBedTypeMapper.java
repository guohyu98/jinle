package com.jyyq.platformweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.jyyq.platformweb.dao.ConfigBedType;

import java.util.List;

@Mapper
public interface ConfigBedTypeMapper {

	int insert(ConfigBedType record);
	int updateByPrimaryKey(ConfigBedType record);
	int deleteByPrimaryKey(Integer id);
	ConfigBedType selectByPrimaryKey(Integer id);
	List<ConfigBedType> selectAll();
	List<ConfigBedType> selectListConfigBedType(ConfigBedType record);

}
