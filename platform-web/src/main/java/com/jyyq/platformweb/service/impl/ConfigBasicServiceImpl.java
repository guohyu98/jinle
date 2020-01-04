package com.jyyq.platformweb.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jyyq.platformweb.dao.ConfigBasic;
import com.jyyq.platformweb.mapper.ConfigBasicMapper;
import com.jyyq.platformweb.service.ConfigBasicService;

@Service
public class ConfigBasicServiceImpl implements ConfigBasicService {

	@Resource
	ConfigBasicMapper configBasicMapper;

	public int save(ConfigBasic record){
		ConfigBasic basic = configBasicMapper.selectConfigBasic();
		if(basic==null){
			record.setAddTime(new Date());
			return configBasicMapper.insert(record);
		}else{
			record.setUpdateTime(new Date());
			return configBasicMapper.update(record);
		}
	}
	public ConfigBasic selectConfigBasic(){
		return configBasicMapper.selectConfigBasic();
	}
	public String selectStartupPage(){
		return configBasicMapper.selectStartupPage();
	}

}
