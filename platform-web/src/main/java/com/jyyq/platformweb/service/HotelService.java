package com.jyyq.platformweb.service;

import com.jyyq.platformweb.dao.Hotel;

import java.util.List;

public interface HotelService {

	int insert(Hotel record);
	int updateByPrimaryKey(Hotel record);
	int deleteByPrimaryKey(Integer id);
	Hotel selectByPrimaryKey(Integer id);
	List<Hotel> selectAll();
	List<Hotel> selectListHotel(Hotel record);

}
