package com.jyyq.platformcommon.auto.utils;

import com.jyyq.platformcommon.auto.config.AutoConfig;
import com.mysql.jdbc.Connection;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AutoDbUtils {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties props =new Properties();
			props.put("user", AutoConfig.getUser());
			props.put("password", AutoConfig.getPassword()); 
			props.put("useInformationSchema","true");
			String url = AutoConfig.getUrl();
			String user = AutoConfig.getUser();
			String pass = AutoConfig.getPassword();
//			conn = (Connection) DriverManager.getConnection(url, user, pass);
			conn = (Connection) DriverManager.getConnection(url, props);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static List<AutoBean> getListFiled(String tableName){
		Connection conn = getConnection();
		try {
			DatabaseMetaData mData = conn.getMetaData();
			ResultSet rst = mData.getTables(null, "%", tableName, new String[]{"TABLE"}); //查询表
			String tableRemark = "";
			while (rst.next()) {
				tableRemark = rst.getString("REMARKS");
			}
			ResultSet rs = mData.getColumns(null, "%", tableName, "%"); //查询字段
			String columnName; 
			String columnType; 
			List<AutoBean> beanList = new ArrayList<AutoBean>();
			AutoBean bean = null;
			while(rs.next()) {
				columnName = rs.getString("COLUMN_NAME"); //字段名称
				columnType = rs.getString("TYPE_NAME"); //数据类型
				int datasize = rs.getInt("COLUMN_SIZE"); //数据长度
//				int digits = rs.getInt("DECIMAL_DIGITS"); //小数位数
//				int nullable = rs.getInt("NULLABLE"); //是否为空
				String remark = rs.getString("REMARKS");//备注
				String isAutoIncrement = rs.getString("IS_AUTOINCREMENT");//是否自增长
				
				String jdbcType = "";
				String javaType = "";
				switch (columnType) {
				case "INT":
					jdbcType = "INTEGER";
					javaType = "Integer";
					break;
				case "BIGINT":
					jdbcType = "BIGINT";
					javaType = "Long";
					break;
				case "VARCHAR":
					javaType = "String";
					jdbcType = "VARCHAR";
					if (datasize > 65533)
						jdbcType = "LONGVARCHAR";
					break;
				case "TEXT":
					javaType = "String";
					jdbcType = "VARCHAR";
					if (datasize > 65533)
						jdbcType = "LONGVARCHAR";
					break;
				case "MEDIUMTEXT":
					javaType = "String";
					jdbcType = "LONGVARCHAR";
					break;
				case "FLOAT":
					javaType = "Float";
					jdbcType = "FLOAT";
					break;
				case "DOUBLE":
					javaType = "Double";
					jdbcType = "DOUBLE";
					break;
				case "DECIMAL":
					javaType = "BigDecimal";
					jdbcType = "DECIMAL";
					break;
				case "DATE":
					javaType = "Date";
					jdbcType = "DATE";
					break;
				case "DATETIME":
					javaType = "Date";
					jdbcType = "TIMESTAMP";
					break;
				default:
					break;
				}
				
				bean = new AutoBean();
				bean.setColumn(columnName);
				bean.setProperty(CamelCaseUtils.toCamelCase(columnName));
				bean.setJdbcType(jdbcType);
				bean.setJavaType(javaType);
				if ("YES".equals(isAutoIncrement)) {
					bean.setIsAutoIncrement(1);
				}
				bean.setRemark(remark);
				bean.setTableRemark(tableRemark);
				beanList.add(bean);
			}
			//查询主键
			List<String> pkList = new ArrayList<String>();
			ResultSet pkRSet = mData.getPrimaryKeys(null, null, tableName); 
			while( pkRSet.next() ) { 
//			System.err.println("****** Comment ******"); 
//			System.err.println("TABLE_CAT : "+pkRSet.getObject(1)); 
//			System.err.println("TABLE_SCHEM: "+pkRSet.getObject(2)); 
//			System.err.println("TABLE_NAME : "+pkRSet.getObject(3)); 
//			System.err.println("COLUMN_NAME: "+pkRSet.getObject(4)); 
//			System.err.println("KEY_SEQ : "+pkRSet.getObject(5)); 
//			System.err.println("PK_NAME : "+pkRSet.getObject(6)); 
//			System.err.println("****** ******* ******"); 
				String pkName = (String) pkRSet.getObject(6);
				String pkColumn = (String) pkRSet.getObject(4);
				if("PRIMARY".equals(pkName)){
					pkList.add(pkColumn);
				}
			} 
			List<AutoBean> list = new ArrayList<AutoBean>();
			for(AutoBean bean2 : beanList){
				if(pkList.contains(bean2.getColumn())){
					bean2.setIsPrimaryKey(1);
				}
				list.add(bean2);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库连接失败"+e.getMessage());
		}
		return null;
	}
}
