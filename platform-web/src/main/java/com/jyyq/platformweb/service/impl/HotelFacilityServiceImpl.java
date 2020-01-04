package com.jyyq.platformweb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jyyq.platformweb.dao.HotelFacility;
import com.jyyq.platformweb.mapper.HotelFacilityMapper;
import com.jyyq.platformweb.service.HotelFacilityService;

@Service
public class HotelFacilityServiceImpl implements HotelFacilityService {

	@Resource
	HotelFacilityMapper hotelFacilityMapper;

	public int insert(HotelFacility record){
		return hotelFacilityMapper.insert(record);
	}
	
	public int updateByPrimaryKey(HotelFacility record){
		return hotelFacilityMapper.updateByPrimaryKey(record);
	}

	public int deleteByPrimaryKey(Integer id){
		return hotelFacilityMapper.deleteByPrimaryKey(id);
	}

	public HotelFacility selectByPrimaryKey(Integer id){
		return hotelFacilityMapper.selectByPrimaryKey(id);
	}

	public List<HotelFacility> selectAll(){
		return hotelFacilityMapper.selectAll();
	}

	public List<HotelFacility> selectListHotelFacility(HotelFacility record){
		return hotelFacilityMapper.selectListHotelFacility(record);
	}

}
