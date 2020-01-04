package com.jyyq.platformweb.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/*
 * 酒店展示信息
 */
@Getter
@Setter
public class HotelShowImg {

	private Integer id;
	private Integer hotelId;//所属酒店
	private Integer showInfoId;//展示信息id
	private String showImg;//展示图片
	private Date addTime;//添加时间
}