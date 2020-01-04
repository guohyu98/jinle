package com.jyyq.platformweb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jyyq.platformweb.dao.HotelRestaurantCombo;
import com.jyyq.platformweb.mapper.HotelRestaurantComboMapper;
import com.jyyq.platformweb.service.HotelRestaurantComboService;

@Service
public class HotelRestaurantComboServiceImpl implements HotelRestaurantComboService {

	@Resource
	HotelRestaurantComboMapper hotelRestaurantComboMapper;

	public int insert(HotelRestaurantCombo record){
		return hotelRestaurantComboMapper.insert(record);
	}
	
	public int updateByPrimaryKey(HotelRestaurantCombo record){
		return hotelRestaurantComboMapper.updateByPrimaryKey(record);
	}

	public int deleteByPrimaryKey(Integer id){
		return hotelRestaurantComboMapper.deleteByPrimaryKey(id);
	}

	public HotelRestaurantCombo selectByPrimaryKey(Integer id){
		return hotelRestaurantComboMapper.selectByPrimaryKey(id);
	}

	public List<HotelRestaurantCombo> selectAll(){
		return hotelRestaurantComboMapper.selectAll();
	}

	public List<HotelRestaurantCombo> selectListHotelRestaurantCombo(HotelRestaurantCombo record){
		return hotelRestaurantComboMapper.selectListHotelRestaurantCombo(record);
	}

}
