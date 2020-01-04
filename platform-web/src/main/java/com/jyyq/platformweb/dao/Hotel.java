package com.jyyq.platformweb.dao;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/*
 * 酒店
 */
@Getter
@Setter
public class Hotel {

	private Integer id;
	private String name;//酒店名称
	private Integer isHot;//是否热门(1是，0否)
	private Integer hotSort;//热门排序
	private Integer status;//上架状态(1已上架，0未上架)
	private Integer hotelStar;//酒店星级
	private String cover;//封面图
	private String address;//酒店地址
	private String contact;//联系人
	private String phoneNumber;//酒店电话
	private String banner;//banner图，最多7张
	private String checkInTime;//入住时间点
	private String checkOutTime;//离店时间点
	private Integer isAllowPet;//是否可携带宠物(1是，0否)
	private Integer hasBreakfast;//是否提供早餐(1是，0否)
	private BigDecimal breakfastPrice;//早餐价格
	private String breakfastServedDate;//开餐日期
	private String breakfastStartTime;//开餐时间点
	private String breakfastEndTime;//收餐时间点
	private Integer isDiningReservation;//是否支持餐厅预定(1是，0否)
	private Integer settlementType;//结算方式(1差价，2结算价，3折扣)
	private String details;//酒店详情
	private Integer isDel;//是否删除(1是，0否)
	private Date addTime;//添加时间
	private Date updateTime;//修改时间
}