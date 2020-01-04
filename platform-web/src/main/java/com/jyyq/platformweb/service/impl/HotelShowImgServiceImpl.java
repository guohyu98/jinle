package com.jyyq.platformweb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jyyq.platformweb.dao.HotelShowImg;
import com.jyyq.platformweb.mapper.HotelShowImgMapper;
import com.jyyq.platformweb.service.HotelShowImgService;

@Service
public class HotelShowImgServiceImpl implements HotelShowImgService {

	@Resource
	HotelShowImgMapper hotelShowImgMapper;

	public int insert(HotelShowImg record){
		return hotelShowImgMapper.insert(record);
	}
	
	public int updateByPrimaryKey(HotelShowImg record){
		return hotelShowImgMapper.updateByPrimaryKey(record);
	}

	public int deleteByPrimaryKey(Integer id){
		return hotelShowImgMapper.deleteByPrimaryKey(id);
	}

	public HotelShowImg selectByPrimaryKey(Integer id){
		return hotelShowImgMapper.selectByPrimaryKey(id);
	}

	public List<HotelShowImg> selectAll(){
		return hotelShowImgMapper.selectAll();
	}

	public List<HotelShowImg> selectListHotelShowImg(HotelShowImg record){
		return hotelShowImgMapper.selectListHotelShowImg(record);
	}

}
