package com.jyyq.platformweb.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/*
 * 酒店设施
 */
@Getter
@Setter
public class HotelFacility {

	private Integer id;
	private Integer hotelId;//所属酒店
	private Integer facilityClassify;//设施分类
	private String facility;//包含的设施
	private Date addTime;//添加时间
}