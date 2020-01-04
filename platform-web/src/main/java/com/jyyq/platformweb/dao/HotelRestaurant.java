package com.jyyq.platformweb.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/*
 * 酒店餐厅
 */
@Getter
@Setter
public class HotelRestaurant {

	private Integer id;
	private Integer hotelId;//所属酒店
	private String name;//餐厅名称
	private String cookingStyle;//菜系
	private String location;//位置
	private String openingHours;//营业时间
	private String showImg;//餐厅图片
	private Integer isDel;//是否删除(1是，0否)
	private Date addTime;//添加时间
	private Date updateTime;//修改时间
}