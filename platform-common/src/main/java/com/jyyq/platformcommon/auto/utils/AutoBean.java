package com.jyyq.platformcommon.auto.utils;

/*
 * 自动生成工具实体类
 */
public class AutoBean {
	private String column;//字段名
	private String property;//属性名
	private String type;//sql类型
	private String javaType;//java类型
	private String jdbcType;//jdbcType
	private Integer isPrimaryKey=0;//是否为主键 1是，0否
	private Integer isExtra=0;//是否与数据表中的字段对应，0是，1否
	private Integer isAutoIncrement=0;//是否自增长 1是，0否
	private String remark;//备注
	
	private String tableRemark;//表备注
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getType() {
		return type;
	}
	public Integer getIsPrimaryKey() {
		return isPrimaryKey;
	}
	public void setIsPrimaryKey(Integer isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	public String getJdbcType() {
		return jdbcType;
	}
	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}
	public Integer getIsExtra() {
		return isExtra;
	}
	public void setIsExtra(Integer isExtra) {
		this.isExtra = isExtra;
	}
	public Integer getIsAutoIncrement() {
		return isAutoIncrement;
	}
	public void setIsAutoIncrement(Integer isAutoIncrement) {
		this.isAutoIncrement = isAutoIncrement;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTableRemark() {
		return tableRemark;
	}
	public void setTableRemark(String tableRemark) {
		this.tableRemark = tableRemark;
	} 
}
