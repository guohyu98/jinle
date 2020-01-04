package com.jyyq.platformweb.service;

import com.jyyq.platformweb.dao.HotelFacility;

import java.util.List;

public interface HotelFacilityService {

	int insert(HotelFacility record);
	int updateByPrimaryKey(HotelFacility record);
	int deleteByPrimaryKey(Integer id);
	HotelFacility selectByPrimaryKey(Integer id);
	List<HotelFacility> selectAll();
	List<HotelFacility> selectListHotelFacility(HotelFacility record);

}
