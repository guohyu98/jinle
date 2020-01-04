package com.jyyq.platformweb.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/*
 * 床型
 */
@Getter
@Setter
public class ConfigBedType {

	private Integer id;
	private String name;//名称
	private Integer isDel;//是否已删除(1是，0否)
	private Date addTime;//添加时间
	private Date updateTime;//修改时间
}