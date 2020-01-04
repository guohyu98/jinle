package com.jyyq.platformweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.jyyq.platformweb.dao.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {

	Admin login(@Param("loginName") String loginName, @Param("password") String password);
	int insert(Admin record);
	int updateByPrimaryKey(Admin record);
	int deleteByPrimaryKey(Integer id);
	Admin selectByPrimaryKey(Integer id);
	List<Admin> selectAll();
	List<Admin> selectListAdmin(Admin record);

}
