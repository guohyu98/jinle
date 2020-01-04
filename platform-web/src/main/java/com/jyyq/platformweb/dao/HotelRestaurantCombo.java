package com.jyyq.platformweb.dao;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/*
 * 酒店餐厅套餐
 */
@Getter
@Setter
public class HotelRestaurantCombo {

	private Integer id;
	private Integer hotelId;//所属酒店
	private Integer restaurantId;//所在餐厅
	private String name;//套餐名称
	private String content;//套餐内容
	private Integer userNumber;//使用人数
	private BigDecimal originalPrice;//原价
	private Integer settlementType;//结算方式(1价差，2结算金额，3折扣)
	private BigDecimal settlementFund;//结算数值
	private BigDecimal memberPrice;//会员价
	private String showImg;//套餐图片
	private Integer status;//上架状态(1已上架，0下架)
	private Integer isDel;//是否已删除(1是，0否)
	private Date addTime;//添加时间
	private Date updateTime;//修改时间
}