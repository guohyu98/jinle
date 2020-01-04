package com.jyyq.platformweb.dao;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/*
 * 预约房间状态
 */
@Getter
@Setter
public class HotelRoomTypeStatus {

	private Integer id;
	private Integer hotelId;//所属酒店
	private Integer roomTypeId;//房型
	private Date day;//日期
	private Integer hotelCheckType;//酒店预约状态(-1关房，0人工审核，1自动接单，2满房)
	private Integer platformCheckType;//平台预约状态(-1关房，0人工审核，1自动接单，2满房)
	private Integer freeCouponMax;//免费券最大预约数量
	private BigDecimal originalPrice;//酒店价格/原价
	private BigDecimal memberPrice;//结算价格/会员价
	private Date addTime;
	private Date updateTime;
}