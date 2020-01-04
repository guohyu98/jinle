package com.jyyq.platformweb.service;

import com.jyyq.platformweb.dao.Admin;

import java.util.List;

public interface AdminService {

	Admin login(String userName, String password);
	int insert(Admin record);
	int updateByPrimaryKey(Admin record);
	int deleteByPrimaryKey(Integer id);
	Admin selectByPrimaryKey(Integer id);
	List<Admin> selectAll();
	List<Admin> selectListAdmin(Admin record);

}
