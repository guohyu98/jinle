package com.jyyq.platformweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.jyyq.platformweb.dao.ConfigShowInfo;

import java.util.List;

@Mapper
public interface ConfigShowInfoMapper {

	int insert(ConfigShowInfo record);
	int updateByPrimaryKey(ConfigShowInfo record);
	int deleteByPrimaryKey(Integer id);
	ConfigShowInfo selectByPrimaryKey(Integer id);
	List<ConfigShowInfo> selectAll();
	List<ConfigShowInfo> selectListConfigShowInfo(ConfigShowInfo record);

}
