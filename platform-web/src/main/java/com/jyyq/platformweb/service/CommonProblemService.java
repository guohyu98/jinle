package com.jyyq.platformweb.service;

import com.jyyq.platformweb.dao.CommonProblem;

import java.util.List;

public interface CommonProblemService {

	int insert(CommonProblem record);
	int updateByPrimaryKey(CommonProblem record);
	int deleteByPrimaryKey(Integer id);
	CommonProblem selectByPrimaryKey(Integer id);
	List<CommonProblem> selectAll();
	List<CommonProblem> selectListCommonProblem(CommonProblem record);

}
