package com.jyyq.platformweb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jyyq.platformweb.dao.HotelRoomTypeStatus;
import com.jyyq.platformweb.mapper.HotelRoomTypeStatusMapper;
import com.jyyq.platformweb.service.HotelRoomTypeStatusService;

@Service
public class HotelRoomTypeStatusServiceImpl implements HotelRoomTypeStatusService {

	@Resource
	HotelRoomTypeStatusMapper hotelRoomTypeStatusMapper;

	public int insert(HotelRoomTypeStatus record){
		return hotelRoomTypeStatusMapper.insert(record);
	}
	
	public int updateByPrimaryKey(HotelRoomTypeStatus record){
		return hotelRoomTypeStatusMapper.updateByPrimaryKey(record);
	}

	public int deleteByPrimaryKey(Integer id){
		return hotelRoomTypeStatusMapper.deleteByPrimaryKey(id);
	}

	public HotelRoomTypeStatus selectByPrimaryKey(Integer id){
		return hotelRoomTypeStatusMapper.selectByPrimaryKey(id);
	}

	public List<HotelRoomTypeStatus> selectAll(){
		return hotelRoomTypeStatusMapper.selectAll();
	}

	public List<HotelRoomTypeStatus> selectListHotelRoomTypeStatus(HotelRoomTypeStatus record){
		return hotelRoomTypeStatusMapper.selectListHotelRoomTypeStatus(record);
	}

}
