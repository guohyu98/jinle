package com.jyyq.platformweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.jyyq.platformweb.dao.CommonProblem;

import java.util.List;

@Mapper
public interface CommonProblemMapper {

	int insert(CommonProblem record);
	int updateByPrimaryKey(CommonProblem record);
	int deleteByPrimaryKey(Integer id);
	CommonProblem selectByPrimaryKey(Integer id);
	List<CommonProblem> selectAll();
	List<CommonProblem> selectListCommonProblem(CommonProblem record);

}
