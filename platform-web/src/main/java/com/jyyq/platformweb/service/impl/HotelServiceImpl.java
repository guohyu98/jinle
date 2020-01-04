package com.jyyq.platformweb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jyyq.platformweb.dao.Hotel;
import com.jyyq.platformweb.mapper.HotelMapper;
import com.jyyq.platformweb.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Resource
	HotelMapper hotelMapper;

	public int insert(Hotel record){
		return hotelMapper.insert(record);
	}
	
	public int updateByPrimaryKey(Hotel record){
		return hotelMapper.updateByPrimaryKey(record);
	}

	public int deleteByPrimaryKey(Integer id){
		return hotelMapper.deleteByPrimaryKey(id);
	}

	public Hotel selectByPrimaryKey(Integer id){
		return hotelMapper.selectByPrimaryKey(id);
	}

	public List<Hotel> selectAll(){
		return hotelMapper.selectAll();
	}

	public List<Hotel> selectListHotel(Hotel record){
		return hotelMapper.selectListHotel(record);
	}

}
