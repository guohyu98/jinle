package com.jyyq.platformweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.jyyq.platformweb.dao.ConfigBasic;

import java.util.List;

@Mapper
public interface ConfigBasicMapper {

	int insert(ConfigBasic record);
	int update(ConfigBasic record);
	ConfigBasic selectConfigBasic();
	String selectStartupPage();

}
