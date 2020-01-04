package com.jyyq.platformweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.jyyq.platformweb.dao.Hotel;

import java.util.List;

@Mapper
public interface HotelMapper {

	int insert(Hotel record);
	int updateByPrimaryKey(Hotel record);
	int deleteByPrimaryKey(Integer id);
	Hotel selectByPrimaryKey(Integer id);
	List<Hotel> selectAll();
	List<Hotel> selectListHotel(Hotel record);

}
