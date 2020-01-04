package com.jyyq.platformweb.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/*
 * 设施
 */
@Getter
@Setter
public class ConfigFacility {

	private Integer id;
	private String name;//设施名称
	private Integer parentId;//父级id
	private Integer isDel;//是否删除(1是，0否)
	private Date addTime;//添加时间
	private Date updateTime;//修改时间

	//查询
	private String parentName;//分类名称
}