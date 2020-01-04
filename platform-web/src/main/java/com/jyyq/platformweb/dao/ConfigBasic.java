package com.jyyq.platformweb.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/*
 * 基础设置
 */
@Getter
@Setter
public class ConfigBasic {

	private Integer id;
	private String startupPage;//启动页图片
	private String advertisingImg;//广告位图片
	private String memberAgreement;//会员协议
	private String customerAgreement;//用户协议
	private Date addTime;//新增时间
	private Date updateTime;//修改时间
}