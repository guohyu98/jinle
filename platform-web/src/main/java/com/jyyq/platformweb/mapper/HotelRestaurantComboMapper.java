package com.jyyq.platformweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.jyyq.platformweb.dao.HotelRestaurantCombo;

import java.util.List;

@Mapper
public interface HotelRestaurantComboMapper {

	int insert(HotelRestaurantCombo record);
	int updateByPrimaryKey(HotelRestaurantCombo record);
	int deleteByPrimaryKey(Integer id);
	HotelRestaurantCombo selectByPrimaryKey(Integer id);
	List<HotelRestaurantCombo> selectAll();
	List<HotelRestaurantCombo> selectListHotelRestaurantCombo(HotelRestaurantCombo record);

}
