package com.jyyq.platformweb.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/*
 * 酒店展示信息配置
 */
@Getter
@Setter
public class ConfigShowInfo {

	private Integer id;
	private String name;//名称
	private Integer sort;//排序
	private Integer isDel;//是否已删除(1是，0否)
	private Date addTime;//添加时间
	private Date updateTime;//修改时间
}