package com.jyyq.platformweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.jyyq.platformweb.dao.HotelShowImg;

import java.util.List;

@Mapper
public interface HotelShowImgMapper {

	int insert(HotelShowImg record);
	int updateByPrimaryKey(HotelShowImg record);
	int deleteByPrimaryKey(Integer id);
	HotelShowImg selectByPrimaryKey(Integer id);
	List<HotelShowImg> selectAll();
	List<HotelShowImg> selectListHotelShowImg(HotelShowImg record);

}
