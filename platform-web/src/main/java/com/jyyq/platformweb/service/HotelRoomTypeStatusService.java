package com.jyyq.platformweb.service;

import com.jyyq.platformweb.dao.HotelRoomTypeStatus;

import java.util.List;

public interface HotelRoomTypeStatusService {

	int insert(HotelRoomTypeStatus record);
	int updateByPrimaryKey(HotelRoomTypeStatus record);
	int deleteByPrimaryKey(Integer id);
	HotelRoomTypeStatus selectByPrimaryKey(Integer id);
	List<HotelRoomTypeStatus> selectAll();
	List<HotelRoomTypeStatus> selectListHotelRoomTypeStatus(HotelRoomTypeStatus record);

}
