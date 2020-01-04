package com.jyyq.platformweb.service;

import com.jyyq.platformweb.dao.HotelRestaurant;

import java.util.List;

public interface HotelRestaurantService {

	int insert(HotelRestaurant record);
	int updateByPrimaryKey(HotelRestaurant record);
	int deleteByPrimaryKey(Integer id);
	HotelRestaurant selectByPrimaryKey(Integer id);
	List<HotelRestaurant> selectAll();
	List<HotelRestaurant> selectListHotelRestaurant(HotelRestaurant record);

}
