package com.jyyq.platformweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.jyyq.platformweb.dao.HotelRoomType;

import java.util.List;

@Mapper
public interface HotelRoomTypeMapper {

	int insert(HotelRoomType record);
	int updateByPrimaryKey(HotelRoomType record);
	int deleteByPrimaryKey(Integer id);
	HotelRoomType selectByPrimaryKey(Integer id);
	List<HotelRoomType> selectAll();
	List<HotelRoomType> selectListHotelRoomType(HotelRoomType record);

}
