package com.jyyq.platformweb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jyyq.platformweb.dao.HotelRestaurant;
import com.jyyq.platformweb.mapper.HotelRestaurantMapper;
import com.jyyq.platformweb.service.HotelRestaurantService;

@Service
public class HotelRestaurantServiceImpl implements HotelRestaurantService {

	@Resource
	HotelRestaurantMapper hotelRestaurantMapper;

	public int insert(HotelRestaurant record){
		return hotelRestaurantMapper.insert(record);
	}
	
	public int updateByPrimaryKey(HotelRestaurant record){
		return hotelRestaurantMapper.updateByPrimaryKey(record);
	}

	public int deleteByPrimaryKey(Integer id){
		return hotelRestaurantMapper.deleteByPrimaryKey(id);
	}

	public HotelRestaurant selectByPrimaryKey(Integer id){
		return hotelRestaurantMapper.selectByPrimaryKey(id);
	}

	public List<HotelRestaurant> selectAll(){
		return hotelRestaurantMapper.selectAll();
	}

	public List<HotelRestaurant> selectListHotelRestaurant(HotelRestaurant record){
		return hotelRestaurantMapper.selectListHotelRestaurant(record);
	}

}
