package com.jyyq.platformweb.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/*
 * 常见问题
 */
@Getter
@Setter
public class CommonProblem {

	private Integer id;
	private String title;//问题
	private String icon;//图标
	private String content;//回答
	private Integer sort;//排序
	private Integer isDel;//是否已删除(1是，0否)
	private Date addTime;//添加时间
	private Date updateTime;//修改时间
}