package com.jyyq.platformcommon.auto.utils;

import com.jyyq.platformcommon.auto.config.AutoConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class UpdateMapperUtils {
	
	public static void execute(String className, String tableName, List<AutoBean> beanList) {
		if(beanList==null || beanList.size()==0){
			System.out.println("beanList 为空,更新mapper失败");
			return;
		}
		
		//获取另外新增的属性名称;
		String daoPath = AutoConfig.getProjectpath() + "src/" + AutoConfig.getDomainpath().replace(".", "/")+"/"+className+".java";
		List<String> daoContentList = AutoUtils.getTextLinesFromFile(daoPath);
		List<AutoBean> extraBeanList = new ArrayList<AutoBean>();
		AutoBean extraBean = null;
		Boolean extraFlag = false;
		for(String string : daoContentList){
//			System.out.println(string);
			if(string.contains("public") && string.indexOf("public class")<0){
				break;
			}
			if(extraFlag){
				String[] arr1 = string.split(";");
				String[] arr2 = arr1[0].split(" ");
				extraBean = new AutoBean();
				extraBean.setProperty(arr2[arr2.length-1]);
				extraBean.setJavaType(arr2[arr2.length-2]);
				if(arr1.length>1){
					extraBean.setRemark(arr1[1]);
				}
				extraBean.setIsExtra(1);
				extraBeanList.add(extraBean);
			}
			if(string.contains("//查询")){
				extraFlag=true;
			}
		}
		
		//获取已有mapper文件的内容
		String mapperPath = AutoConfig.getProjectpath() +"src/"+AutoConfig.getMapperxmlpath().replace(".", "/")+"/"+className+"Mapper.xml";
		String oldMapperContent = AutoUtils.readFile(mapperPath);
		
		//获取新的mapper文件的内容
		String resultMapString = oldMapperContent.substring(oldMapperContent.indexOf("<resultMap"), oldMapperContent.indexOf("</resultMap>"));
		String tempString = "";
		String column = "",property="",jdbcType="";
		List<AutoBean> newBeanList = new ArrayList<AutoBean>();
		newBeanList.addAll(beanList);
		while(resultMapString.indexOf("column")>=0){
			tempString = resultMapString.substring(resultMapString.indexOf("column")+8);
			column = tempString.substring(0, tempString.indexOf("\""));
			tempString = tempString.substring(tempString.indexOf("property")+10);
			property = tempString.substring(0, tempString.indexOf("\""));
			tempString = tempString.substring(tempString.indexOf("jdbcType")+10);
			jdbcType = tempString.substring(0, tempString.indexOf("\""));
			resultMapString = tempString.substring(tempString.indexOf("/>"));
			for (AutoBean bean :extraBeanList) {
				if(bean.getProperty().equals(property)){
					bean.setColumn(column);
					bean.setJdbcType(jdbcType);
					newBeanList.add(bean);
					break;
				}
			}
		}
		//ShowVarUtil.showJsonList("newBeanList", newBeanList);
		beanList.addAll(extraBeanList);
		//生成新的dao文件
		String daoContent = exportDao(className, tableName, beanList);
		//生成新的mapper文件
		String mapperContent = exportMapper(className, tableName, newBeanList, oldMapperContent);
		
		System.out.println(mapperContent);
		
		AutoUtils.createFile(daoPath, daoContent);
		AutoUtils.deleteFile(mapperPath);
		AutoUtils.createFile(mapperPath, mapperContent);
	}
	
	public static String exportDao(String className, String tableName, List<AutoBean> beanList){
		StringBuffer sb = new StringBuffer();
		
		Boolean haveDecimal = false;
		Boolean haveDate = false;
		String tableRemark = "";
		for(AutoBean bean : beanList){
			if(bean.getJavaType().equals("BigDecimal")){
				haveDecimal = true;
			}else if(bean.getJavaType().equals("Date")){
				haveDate = true;
			}
			tableRemark = bean.getTableRemark();
		}
		
		sb.append("package "+AutoConfig.getDomainpath()+";\n\n");
		if(haveDecimal){
			sb.append("import java.math.BigDecimal;\n");
		}
		if(haveDate){
			sb.append("import java.util.Date;\n");
		}
		sb.append("\n/*");
		sb.append("\n * " + tableRemark);
		sb.append("\n */");
		sb.append("\npublic class "+className+" {\n");
		
		for(AutoBean bean : beanList){
			if(bean.getIsExtra()==0){
				sb.append("\n\tprivate "+bean.getJavaType()+" "+bean.getProperty()+";");
				if(!StringUtils.isBlank(bean.getRemark())){//添加备注
					sb.append("//"+bean.getRemark());
				}
			}
		}
		int i=0;
		for(AutoBean bean : beanList){
			if(bean.getIsExtra()==1){
				if(i++==0){
					sb.append("\n\n\t//查询");
				}
				sb.append("\n\tprivate "+bean.getJavaType()+" "+bean.getProperty()+";");
				if(!StringUtils.isBlank(bean.getRemark())){//添加备注
					sb.append("//"+bean.getRemark());
				}
			}
		}
		for(AutoBean bean : beanList){
			sb.append("\n\tpublic "+bean.getJavaType()+" get"+AutoUtils.upperFirst(bean.getProperty())+"(){");
			sb.append("\n\t\treturn "+bean.getProperty()+";");
			sb.append("\n\t}");
			sb.append("\n\tpublic void set"+AutoUtils.upperFirst(bean.getProperty())+"("+bean.getJavaType()+" "+bean.getProperty()+"){");
			sb.append("\n\t\tthis."+bean.getProperty()+" = "+bean.getProperty()+";");
			sb.append("\n\t}");
		}
		
		sb.append("\n}");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public static String exportMapper(String className, String tableName, List<AutoBean> newBeanList, String oldMapperContent){
		String resultString = resultMap(className, tableName, newBeanList);
		String insertString = insert(className, tableName, newBeanList);
		String updateByPrimaryKeyString = updateByPrimaryKey(className, tableName, newBeanList);
		String mapperContent = oldMapperContent.substring(0,oldMapperContent.indexOf("<resultMap"))
				+ resultString + oldMapperContent.substring(oldMapperContent.indexOf("</resultMap>")+12,oldMapperContent.length());
		mapperContent = mapperContent.substring(0,mapperContent.indexOf("<insert"))
				+ insertString + mapperContent.substring(mapperContent.indexOf("</insert>")+9,mapperContent.length());
		mapperContent = mapperContent.substring(0,mapperContent.indexOf("<update"))
				+ updateByPrimaryKeyString + mapperContent.substring(mapperContent.indexOf("</update>")+9,mapperContent.length());
		System.out.println(mapperContent);
		return mapperContent;
	}
	
	/*
	 * resultMap
	 */
	private static String resultMap(String className, String tableName, List<AutoBean> beanList){
		StringBuffer sb = new StringBuffer();
		sb.append("<resultMap id=\"BaseResultMap\" type=\""+AutoConfig.getDomainpath()+"."+className+"\" >");
		for(AutoBean bean : beanList){
			if(bean.getIsPrimaryKey()==1)
			sb.append("\n\t<id column=\""+bean.getColumn()+"\" property=\""+bean.getProperty()+"\" jdbcType=\""+bean.getJdbcType()+"\" />");
		}
		for(AutoBean bean : beanList){
			if(bean.getIsPrimaryKey()==0)
			sb.append("\n\t<result column=\""+bean.getColumn()+"\" property=\""+bean.getProperty()+"\" jdbcType=\""+bean.getJdbcType()+"\" />");
		}
		sb.append("\n  </resultMap>");
		return sb.toString();
	}
	
	/*
	 * insert
	 */
	private static String insert(String className, String tableName, List<AutoBean> beanList){
		StringBuffer sb = new StringBuffer(); 
		sb.append("<insert id=\"insert\" parameterType=\""+AutoConfig.getDomainpath()+"."+className+"\" >");
		sb.append("\n\tinsert into "+tableName+" (");
		int i=0,j=0;
		for(AutoBean bean : beanList){
			j++;
			if(bean.getIsExtra()==1){
				continue;
			}
			if(i%3==0){
				sb.append("\n\t  ");
			}
			if(j==beanList.size()){
				sb.append(bean.getColumn());
			}else{
				sb.append(bean.getColumn()+", ");
			}
			i++;
		}
		sb.append(")\n\tvalues (");
		i=0;j=0;
		for(AutoBean bean : beanList){
			j++;
			if(bean.getIsExtra()==1){
				continue;
			}
			if(i%3==0){
				sb.append("\n\t  ");
			}
			sb.append("#{"+bean.getProperty());
			if(j==beanList.size()){
				sb.append(",jdbcType="+bean.getJdbcType()+"}");
			}else{
				sb.append(",jdbcType="+bean.getJdbcType()+"}, ");
			}
			i++;
		}
		sb.append(")");
		sb.append("\n  </insert>");
		return sb.toString();
	}

	/*
	 * updateByPrimaryKey
	 */
	private static String updateByPrimaryKey(String className, String tableName, List<AutoBean> beanList){
		AutoBean primaryBean = null;
		for(AutoBean bean : beanList){
			if(bean.getIsPrimaryKey()==1){
				primaryBean = bean;
				continue;
			}
		}
		if(primaryBean==null){
			return "";
		}
		StringBuffer sb = new StringBuffer(); 
		sb.append("<update id=\"updateByPrimaryKey\" parameterType=\""+AutoConfig.getDomainpath()+"."+className+"\" >");
		sb.append("\n\tupdate "+tableName);
		sb.append("\n\t<trim prefix=\"set\" suffixOverrides=\",\">");
		for(AutoBean bean : beanList){
			if(bean.getIsExtra()==1){
				continue;
			}
			if(bean.getIsPrimaryKey()==1){
				primaryBean = bean;
				continue;
			}
			System.out.println(bean.getColumn()+";;"+bean.getJdbcType()+(bean.getJdbcType().equals("VARCHAR")));
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
		sb.append("\n\twhere "+primaryBean.getColumn()+" = #{"+primaryBean.getProperty()+",jdbcType="+primaryBean.getJdbcType()+"}");
		sb.append("\n  </update>");
		return sb.toString();
	}
}
