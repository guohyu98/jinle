package com.jyyq.platformweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.jyyq.platformweb.dao.HotelRoomTypeStatus;

import java.util.List;

@Mapper
public interface HotelRoomTypeStatusMapper {

	int insert(HotelRoomTypeStatus record);
	int updateByPrimaryKey(HotelRoomTypeStatus record);
	int deleteByPrimaryKey(Integer id);
	HotelRoomTypeStatus selectByPrimaryKey(Integer id);
	List<HotelRoomTypeStatus> selectAll();
	List<HotelRoomTypeStatus> selectListHotelRoomTypeStatus(HotelRoomTypeStatus record);

}
