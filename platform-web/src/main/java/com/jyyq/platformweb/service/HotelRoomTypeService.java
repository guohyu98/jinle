package com.jyyq.platformweb.service;

import com.jyyq.platformweb.dao.HotelRoomType;

import java.util.List;

public interface HotelRoomTypeService {

	int insert(HotelRoomType record);
	int updateByPrimaryKey(HotelRoomType record);
	int deleteByPrimaryKey(Integer id);
	HotelRoomType selectByPrimaryKey(Integer id);
	List<HotelRoomType> selectAll();
	List<HotelRoomType> selectListHotelRoomType(HotelRoomType record);

}
