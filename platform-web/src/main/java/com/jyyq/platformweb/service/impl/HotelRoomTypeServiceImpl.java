package com.jyyq.platformweb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jyyq.platformweb.dao.HotelRoomType;
import com.jyyq.platformweb.mapper.HotelRoomTypeMapper;
import com.jyyq.platformweb.service.HotelRoomTypeService;

@Service
public class HotelRoomTypeServiceImpl implements HotelRoomTypeService {

	@Resource
	HotelRoomTypeMapper hotelRoomTypeMapper;

	public int insert(HotelRoomType record){
		return hotelRoomTypeMapper.insert(record);
	}
	
	public int updateByPrimaryKey(HotelRoomType record){
		return hotelRoomTypeMapper.updateByPrimaryKey(record);
	}

	public int deleteByPrimaryKey(Integer id){
		return hotelRoomTypeMapper.deleteByPrimaryKey(id);
	}

	public HotelRoomType selectByPrimaryKey(Integer id){
		return hotelRoomTypeMapper.selectByPrimaryKey(id);
	}

	public List<HotelRoomType> selectAll(){
		return hotelRoomTypeMapper.selectAll();
	}

	public List<HotelRoomType> selectListHotelRoomType(HotelRoomType record){
		return hotelRoomTypeMapper.selectListHotelRoomType(record);
	}

}
