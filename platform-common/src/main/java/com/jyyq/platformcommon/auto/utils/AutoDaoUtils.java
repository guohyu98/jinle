package com.jyyq.platformcommon.auto.utils;

import com.jyyq.platformcommon.auto.config.AutoConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 自动生成实体类
 * @author yu
 *
 */
public class AutoDaoUtils {
	
	public static void exportDao(String className, String tableName, List<AutoBean> beanList){
		if(beanList==null || beanList.size()==0){
			System.out.println("beanList 为空,生成dao失败");
			return;
		}
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
		
		sb.append("package "+ AutoConfig.getDomainpath()+";\n\n");

		sb.append("import lombok.Getter;\n");
		sb.append("import lombok.Setter;\n\n");

		if(haveDecimal){
			sb.append("import java.math.BigDecimal;\n");
		}
		if(haveDate){
			sb.append("import java.util.Date;\n");
		}
		sb.append("\n/*");
		sb.append("\n * " + tableRemark);
		sb.append("\n */");
		sb.append("\n@Getter");
		sb.append("\n@Setter");
		sb.append("\npublic class "+className+" {\n");
		
		for(AutoBean bean : beanList){
			sb.append("\n\tprivate "+bean.getJavaType()+" "+bean.getProperty()+";");
			if(!StringUtils.isBlank(bean.getRemark())){//添加备注
				sb.append("//"+bean.getRemark());
			}
		}
		/*//引入@Setter和@Getter,不需要生成get set方法
		for(AutoBean bean : beanList){
			sb.append("\n\tpublic "+bean.getJavaType()+" get"+toUpperCase(bean.getProperty())+"(){");
			sb.append("\n\t\treturn "+bean.getProperty()+";");
			sb.append("\n\t}");
			sb.append("\n\tpublic void set"+toUpperCase(bean.getProperty())+"("+bean.getJavaType()+" "+bean.getProperty()+"){");
			sb.append("\n\t\tthis."+bean.getProperty()+" = "+bean.getProperty()+";");
			sb.append("\n\t}");
		}*/
		
		sb.append("\n}");
		System.out.println(sb.toString());
		
		String domainPath = AutoConfig.getProjectpath() + AutoConfig.getClassPath() +AutoConfig.getDomainpath().replace(".", "/") +"/"+className+".java";
		System.out.println("domainPath=="+domainPath);
		AutoUtils.createFile(domainPath, sb.toString());
	}
	


}
