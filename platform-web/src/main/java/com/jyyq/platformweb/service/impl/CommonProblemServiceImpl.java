package com.jyyq.platformweb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jyyq.platformweb.dao.CommonProblem;
import com.jyyq.platformweb.mapper.CommonProblemMapper;
import com.jyyq.platformweb.service.CommonProblemService;

@Service
public class CommonProblemServiceImpl implements CommonProblemService {

	@Resource
	CommonProblemMapper commonProblemMapper;

	public int insert(CommonProblem record){
		return commonProblemMapper.insert(record);
	}
	
	public int updateByPrimaryKey(CommonProblem record){
		return commonProblemMapper.updateByPrimaryKey(record);
	}

	public int deleteByPrimaryKey(Integer id){
		return commonProblemMapper.deleteByPrimaryKey(id);
	}

	public CommonProblem selectByPrimaryKey(Integer id){
		return commonProblemMapper.selectByPrimaryKey(id);
	}

	public List<CommonProblem> selectAll(){
		return commonProblemMapper.selectAll();
	}

	public List<CommonProblem> selectListCommonProblem(CommonProblem record){
		return commonProblemMapper.selectListCommonProblem(record);
	}

}
