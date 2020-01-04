package com.jyyq.platformweb.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/*
 * 管理员账号管理
 */
@Getter
@Setter
public class Admin {

	private Integer id;//id
	private String loginName;//用户名称(账号)
	private String password;//用户密码
	private String name;//姓名
	private String phone;//电话
	private Integer type;//管理员类型(0平台端，1酒店端)
	private Integer hotelId;//所属酒店
	private String department;//酒店管理员所属部门
	private String permission;//权限
	private Integer isDel;//删除状态(0未删除，1已删除)
	private Date addTime;//添加时间
	private Date updateTime;//修改时间
}