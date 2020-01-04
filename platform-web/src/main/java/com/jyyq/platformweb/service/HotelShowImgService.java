package com.jyyq.platformweb.service;

import com.jyyq.platformweb.dao.HotelShowImg;

import java.util.List;

public interface HotelShowImgService {

	int insert(HotelShowImg record);
	int updateByPrimaryKey(HotelShowImg record);
	int deleteByPrimaryKey(Integer id);
	HotelShowImg selectByPrimaryKey(Integer id);
	List<HotelShowImg> selectAll();
	List<HotelShowImg> selectListHotelShowImg(HotelShowImg record);

}
