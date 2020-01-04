package com.jyyq.platformweb.service;

import com.jyyq.platformweb.dao.HotelRestaurantCombo;

import java.util.List;

public interface HotelRestaurantComboService {

	int insert(HotelRestaurantCombo record);
	int updateByPrimaryKey(HotelRestaurantCombo record);
	int deleteByPrimaryKey(Integer id);
	HotelRestaurantCombo selectByPrimaryKey(Integer id);
	List<HotelRestaurantCombo> selectAll();
	List<HotelRestaurantCombo> selectListHotelRestaurantCombo(HotelRestaurantCombo record);

}
