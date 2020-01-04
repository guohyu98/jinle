package com.jyyq.platformweb.service;

import com.jyyq.platformweb.dao.ConfigBasic;

import java.util.List;

public interface ConfigBasicService {

	int save(ConfigBasic record);
	ConfigBasic selectConfigBasic();
	String selectStartupPage();

}
