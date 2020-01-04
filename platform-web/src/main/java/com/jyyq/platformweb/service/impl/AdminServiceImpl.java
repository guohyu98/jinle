package com.jyyq.platformweb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jyyq.platformweb.dao.Admin;
import com.jyyq.platformweb.mapper.AdminMapper;
import com.jyyq.platformweb.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Resource
	AdminMapper adminMapper;

	public Admin login(String userName, String password) {
		return adminMapper.login(userName, password);
	}

	public int insert(Admin record){
		return adminMapper.insert(record);
	}
	
	public int updateByPrimaryKey(Admin record){
		return adminMapper.updateByPrimaryKey(record);
	}

	public int deleteByPrimaryKey(Integer id){
		return adminMapper.deleteByPrimaryKey(id);
	}

	public Admin selectByPrimaryKey(Integer id){
		return adminMapper.selectByPrimaryKey(id);
	}

	public List<Admin> selectAll(){
		return adminMapper.selectAll();
	}

	public List<Admin> selectListAdmin(Admin record){
		return adminMapper.selectListAdmin(record);
	}

}
