package com.jyyq.platformweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.jyyq.platformweb.dao.HotelFacility;

import java.util.List;

@Mapper
public interface HotelFacilityMapper {

	int insert(HotelFacility record);
	int updateByPrimaryKey(HotelFacility record);
	int deleteByPrimaryKey(Integer id);
	HotelFacility selectByPrimaryKey(Integer id);
	List<HotelFacility> selectAll();
	List<HotelFacility> selectListHotelFacility(HotelFacility record);

}
