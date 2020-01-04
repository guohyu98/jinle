package com.jyyq.platformcommon.auto.utils;

import com.jyyq.platformcommon.auto.config.AutoConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 自动生成mapper
 * @author yu
 *
 */
public class AutoMapperUtils {
	
	public static void exportMapper(String className, String tableName, List<AutoBean> beanList){
		if(beanList==null || beanList.size()==0){
			System.out.println("beanList 为空,生成mapper失败");
			return;
		}
		String pkType = "Integer";
		List<AutoBean> primaryBeanList = new ArrayList<AutoBean>();
		for(AutoBean bean : beanList){
			if(bean.getIsPrimaryKey()==1){
				pkType = bean.getJavaType();
				primaryBeanList.add(bean);
			}
		}
		
		System.out.println("-------------mapper.java------------");
		String mapperDaoContent = AutoUtils.readFile(AutoConfig.getTemplatePath()+"mapper.java.txt");
		String packageString = AutoConfig.getMapperpath();
		String namespace = AutoConfig.getMapperpath()+"."+className;
		String importString = "import "+AutoConfig.getDomainpath()+"."+className+";";
		importString += "\n\nimport java.util.List;";
		if (primaryBeanList.size()>1) {
			importString += "\n\nimport org.apache.ibatis.annotations.Param;";
		}
		String m1 = className;
		mapperDaoContent = mapperDaoContent.replace("${namespace}", namespace);
		mapperDaoContent = mapperDaoContent.replace("${import}", importString);
		mapperDaoContent = mapperDaoContent.replace("${package}", packageString);
		mapperDaoContent = mapperDaoContent.replace("${m1}", m1);
		if(primaryBeanList.size()>0){
			String methodByPrimaryKey = "int updateByPrimaryKey("+m1+" record);\n";
			String paramsStr = "";
			if(primaryBeanList.size()==1){
				paramsStr = pkType+" id";
			}else{
				for(int i=0;i<primaryBeanList.size();i++){
					AutoBean primaryBean = primaryBeanList.get(i);
					if(i==0){
						paramsStr = "@Param(\""+primaryBean.getProperty()+"\")" + primaryBean.getJavaType() + " " + primaryBean.getProperty();
					}else{
						paramsStr += ", @Param(\""+primaryBean.getProperty()+"\")" + primaryBean.getJavaType() + " " + primaryBean.getProperty();
					}
				}
			}
			methodByPrimaryKey += "\tint deleteByPrimaryKey("+paramsStr+");\n";
			methodByPrimaryKey += "\t"+m1+" selectByPrimaryKey("+paramsStr+");";
			mapperDaoContent = mapperDaoContent.replace("${methodByPrimaryKey}", methodByPrimaryKey);
		}else{
			mapperDaoContent = mapperDaoContent.replace("${methodByPrimaryKey}", "");
		}
		System.out.println(mapperDaoContent);
		
		System.out.println("-------------mapper.xml-----------");
		String mapperXmlContent = AutoUtils.readFile(AutoConfig.getTemplatePath()+"mapper.xml.txt");
		mapperXmlContent = mapperXmlContent.replace("${namespace}", namespace);
		mapperXmlContent = mapperXmlContent.replace("${resultType}", AutoConfig.getDomainpath()+"."+className);
		mapperXmlContent = mapperXmlContent.replace("${result}", resultMap(className, tableName, beanList));
		mapperXmlContent = mapperXmlContent.replace("${columns}", columns(className, tableName, beanList));
		mapperXmlContent = mapperXmlContent.replace("${insert}", insert(className, tableName, beanList));
		if(primaryBeanList.size()>0){
			mapperXmlContent = mapperXmlContent.replace("${updateByPrimaryKey}", updateByPrimaryKey(className, tableName, beanList));
			mapperXmlContent = mapperXmlContent.replace("${deleteByPrimaryKey}", deleteByPrimaryKey(className, tableName, beanList));
			mapperXmlContent = mapperXmlContent.replace("${selectByPrimaryKey}", selectByPrimaryKey(className, tableName, beanList));
		}else{
			mapperXmlContent = mapperXmlContent.replace("${updateByPrimaryKey}", "");
			mapperXmlContent = mapperXmlContent.replace("${deleteByPrimaryKey}", "");
			mapperXmlContent = mapperXmlContent.replace("${selectByPrimaryKey}", "");
		}
		mapperXmlContent = mapperXmlContent.replace("${selectAll}", selectAll(className, tableName, beanList));
		mapperXmlContent = mapperXmlContent.replace("${selectList}", selectList(className, tableName, beanList));
		System.out.println(mapperXmlContent);
		
		//生成文件
		String daoPath = AutoConfig.getProjectpath() + AutoConfig.getClassPath()+AutoConfig.getMapperpath().replace(".", "/") +"/"+className+"Mapper.java";
		System.out.println("daoPath="+daoPath);
		AutoUtils.createFile(daoPath, mapperDaoContent);
		String mapperPath = AutoConfig.getProjectpath() +"src/main/resources/"+AutoConfig.getMapperxmlpath().replace(".", "/")+"/"+className+"Mapper.xml";
		System.out.println("mapperPath="+mapperPath);
		AutoUtils.deleteFile(mapperPath);
		AutoUtils.createFile(mapperPath, mapperXmlContent);
	}
	
	/*
	 * resultMap
	 */
	private static String resultMap(String className, String tableName, List<AutoBean> beanList){
		StringBuffer sb = new StringBuffer();
		for(AutoBean bean : beanList){
			if(bean.getIsPrimaryKey()==1)
			sb.append("\t<id column=\""+bean.getColumn()+"\" property=\""+bean.getProperty()+"\" jdbcType=\""+bean.getJdbcType()+"\" />");
		}
		for(AutoBean bean : beanList){
			if(bean.getIsPrimaryKey()==0)
			sb.append("\n\t<result column=\""+bean.getColumn()+"\" property=\""+bean.getProperty()+"\" jdbcType=\""+bean.getJdbcType()+"\" />");
		}
		return sb.toString();
	}
	
	/*
	 * columns
	 */
	private static String columns(String className, String tableName, List<AutoBean> beanList){
		StringBuffer sb = new StringBuffer(); 
		for(int i=0;i<beanList.size();i++){
			if(i%5==0 && i!=0){
				sb.append("\n\t");
			}
//			if(beanList.get(i).getIsAutoIncrement()==0){
//			}
			if(i==beanList.size()-1){
				sb.append(beanList.get(i).getColumn());
			}else{
				sb.append(beanList.get(i).getColumn()+", ");
			}
		}
		return sb.toString();
	}
	
	/*
	 * insert
	 */
	private static String insert(String className, String tableName, List<AutoBean> beanList){
		StringBuffer sb = new StringBuffer(); 
		sb.append("\n");
		sb.append("  <insert id=\"insert\" parameterType=\""+AutoConfig.getDomainpath()+"."+className+"\" >");
		sb.append("\n\tinsert into "+tableName+" (");
		for(int i=0;i<beanList.size();i++){
			if(i%3==0){
				sb.append("\n\t  ");
			}
			if(beanList.get(i).getIsAutoIncrement()==0){
				if(i==beanList.size()-1){
					sb.append(beanList.get(i).getColumn());
				}else{
					sb.append(beanList.get(i).getColumn()+", ");
				}
			}
		}
		sb.append(")\n\tvalues (");
		for(int i=0;i<beanList.size();i++){
			if(i%3==0){
				sb.append("\n\t  ");
			}
			if(beanList.get(i).getIsAutoIncrement()==0){
				sb.append("#{"+beanList.get(i).getProperty());
				if(i==beanList.size()-1){
					sb.append(",jdbcType="+beanList.get(i).getJdbcType()+"}");
				}else{
					sb.append(",jdbcType="+beanList.get(i).getJdbcType()+"}, ");
				}
			}
		}
		sb.append(")");
		sb.append("\n  </insert>");
		return sb.toString();
	}

	/*
	 * updateByPrimaryKey
	 */
	private static String updateByPrimaryKey(String className, String tableName, List<AutoBean> beanList){
		List<AutoBean> primaryBeanList = new ArrayList<AutoBean>();
		for(AutoBean bean : beanList){
			if(bean.getIsPrimaryKey()==1){
				primaryBeanList.add(bean);
			}
		}
		if(primaryBeanList.size()==0){
			return "";
		}
		StringBuffer sb = new StringBuffer(); 
		sb.append("\n");
		sb.append("  <update id=\"updateByPrimaryKey\" parameterType=\""+AutoConfig.getDomainpath()+"."+className+"\" >");
		sb.append("\n\tupdate "+tableName);
		sb.append("\n\t<trim prefix=\"set\" suffixOverrides=\",\">");
		for(AutoBean bean : beanList){
			if(bean.getIsPrimaryKey()==1){
				continue;
			}
			if(bean.getJdbcType().equals("VARCHAR")){
				sb.append("\n\t  <if test=\""+bean.getProperty()+" != null and "+bean.getProperty()+" != ''\">");
				sb.append("\n\t\t"+bean.getColumn()+" = #{"+bean.getProperty()+",jdbcType="+bean.getJdbcType()+"},");
				sb.append("\n\t  </if>");
			}else{
				sb.append("\n\t  <if test=\""+bean.getProperty()+" != null\">");
				sb.append("\n\t\t"+bean.getColumn()+" = #{"+bean.getProperty()+",jdbcType="+bean.getJdbcType()+"},");
				sb.append("\n\t  </if>");
			}
		}
		sb.append("\n\t</trim>");
		sb.append("\n\twhere ");
		for(int i=0;i<primaryBeanList.size();i++){
			AutoBean primaryBean = primaryBeanList.get(i);
			if(i==0){
				sb.append(primaryBean.getColumn()+" = #{"+primaryBean.getProperty()+",jdbcType="+primaryBean.getJdbcType()+"}");
			}else{
				sb.append("\n\tand "+primaryBean.getColumn()+" = #{"+primaryBean.getProperty()+",jdbcType="+primaryBean.getJdbcType()+"}");
			}
		}
		sb.append("\n  </update>");
		return sb.toString();
	}
	
	/*
	 * deleteByPrimaryKey
	 */
	private static String deleteByPrimaryKey(String className, String tableName, List<AutoBean> beanList) {
		List<AutoBean> primaryBeanList = new ArrayList<AutoBean>();
		for(AutoBean bean : beanList){
			if(bean.getIsPrimaryKey()==1){
				primaryBeanList.add(bean);
			}
		}
		if(primaryBeanList.size()==0){
			return "";
		}
		StringBuffer sb = new StringBuffer(); 
		sb.append("\n");
		if(primaryBeanList.size()==1){
			AutoBean primaryBean = primaryBeanList.get(0);
			sb.append("  <delete id=\"deleteByPrimaryKey\" parameterType=\"java.lang."+primaryBean.getJavaType()+"\" >");
		}else{
			sb.append("  <delete id=\"deleteByPrimaryKey\" parameterType=\"map\" >");
		}
		sb.append("\n\tdelete from "+tableName);
		sb.append("\n\twhere ");
		for(int i=0;i<primaryBeanList.size();i++){
			AutoBean primaryBean = primaryBeanList.get(i);
			if(i==0){
				sb.append(primaryBean.getColumn()+" = #{"+primaryBean.getProperty()+",jdbcType="+primaryBean.getJdbcType()+"}");
			}else{
				sb.append("\n\tand "+primaryBean.getColumn()+" = #{"+primaryBean.getProperty()+",jdbcType="+primaryBean.getJdbcType()+"}");
			}
		}
		sb.append("\n  </delete>");
		return sb.toString();
	}
	
	/*
	 * selectByPrimaryKey
	 */
	private static String selectByPrimaryKey(String className ,String tableName, List<AutoBean> beanList) {
		List<AutoBean> primaryBeanList = new ArrayList<AutoBean>();
		for(AutoBean bean : beanList){
			if(bean.getIsPrimaryKey()==1){
				primaryBeanList.add(bean);
			}
		}
		if(primaryBeanList.size()==0){
			return "";
		}
		StringBuffer sb = new StringBuffer(); 
		sb.append("\n");
		if(primaryBeanList.size()==1){
			AutoBean primaryBean = primaryBeanList.get(0);
			sb.append("  <select id=\"selectByPrimaryKey\" resultMap=\"BaseResultMap\" parameterType=\"java.lang."+primaryBean.getJavaType()+"\" >");
		}else{
			sb.append("  <select id=\"selectByPrimaryKey\" resultMap=\"BaseResultMap\" parameterType=\"map\" >");
		}
		sb.append("\n\tselect ");
		sb.append("\n\t  ");
		sb.append("<include refid=\"Base_Column_List\" />");
		sb.append("\n\tfrom "+tableName);
		sb.append("\n\twhere ");
		for(int i=0;i<primaryBeanList.size();i++){
			AutoBean primaryBean = primaryBeanList.get(i);
			if(i==0){
				sb.append(primaryBean.getColumn()+" = #{"+primaryBean.getProperty()+",jdbcType="+primaryBean.getJdbcType()+"}");
			}else{
				sb.append("\n\tand "+primaryBean.getColumn()+" = #{"+primaryBean.getProperty()+",jdbcType="+primaryBean.getJdbcType()+"}");
			}
		}
		sb.append("\n  </select>");
		return sb.toString();
	}
	
	/*
	 * selectAll
	 */
	private static String selectAll(String className, String tableName, List<AutoBean> beanList) {
		StringBuffer sb = new StringBuffer(); 
		sb.append("\n");
		sb.append("  <select id=\"selectAll\" resultMap=\"BaseResultMap\" >");
		sb.append("\n\tselect ");
		sb.append("\n\t  ");
		sb.append("<include refid=\"Base_Column_List\" />");
		sb.append("\n\tfrom "+tableName);
		sb.append("\n  </select>");
		return sb.toString();
	}
	/*
	 * selectList
	 */
	private static String selectList(String className, String tableName, List<AutoBean> beanList) {
		StringBuffer sb = new StringBuffer(); 
		sb.append("\n");
		sb.append("  <select id=\"selectList"+className+"\" resultMap=\"BaseResultMap\" parameterType=\""+AutoConfig.getDomainpath()+"."+className+"\" >");
		sb.append("\n\tselect ");
		sb.append("\n\t  ");
		sb.append("<include refid=\"Base_Column_List\" />");
		sb.append("\n\tfrom "+tableName);
		sb.append("\n  </select>");
		return sb.toString();
	}
	
	
	
}
