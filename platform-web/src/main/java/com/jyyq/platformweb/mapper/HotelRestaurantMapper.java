package com.jyyq.platformweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.jyyq.platformweb.dao.HotelRestaurant;

import java.util.List;

@Mapper
public interface HotelRestaurantMapper {

	int insert(HotelRestaurant record);
	int updateByPrimaryKey(HotelRestaurant record);
	int deleteByPrimaryKey(Integer id);
	HotelRestaurant selectByPrimaryKey(Integer id);
	List<HotelRestaurant> selectAll();
	List<HotelRestaurant> selectListHotelRestaurant(HotelRestaurant record);

}
