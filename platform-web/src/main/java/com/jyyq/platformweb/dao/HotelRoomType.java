package com.jyyq.platformweb.dao;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/*
 * 酒店房型
 */
@Getter
@Setter
public class HotelRoomType {

	private Integer id;
	private Integer hotelId;//所属酒店
	private String name;//房型名称
	private String roomSize;//房型大小
	private Integer bedType;//床型(关联bed_type中id）
	private String bedSize;//床型大小
	private String floor;//所在楼层
	private Integer guestNumber;//入住人数
	private BigDecimal originalPrice;//原价
	private BigDecimal settlementFund;//结算数值
	private BigDecimal memberPrice;//会员价
	private String showImg;//房型图片
	private String reservationNotes;//预定须知
	private Integer status;//状态(1已上架，0未上架)
	private Integer isDel;//是否已删除(1是，0否)
	private Date addTime;//添加时间
	private Date updateTime;//修改时间
}