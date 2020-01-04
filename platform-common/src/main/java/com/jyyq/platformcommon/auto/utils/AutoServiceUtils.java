package com.jyyq.platformcommon.auto.utils;

import com.jyyq.platformcommon.auto.config.AutoConfig;

import java.util.ArrayList;
import java.util.List;

/*
 * 自动生成service
 */
public class AutoServiceUtils {
	
	public static void exportService(String className, List<AutoBean> beanList){
		if(beanList==null || beanList.size()==0){
			System.out.println("beanList 为空,生成service失败");
			return;
		}
		List<AutoBean> primaryBeanList = new ArrayList<AutoBean>();
		for(AutoBean bean : beanList){
			if(bean.getIsPrimaryKey()==1){
				primaryBeanList.add(bean);
			}
		}
		
		System.out.println("-------------service------------");
		String serviceContent = AutoUtils.readFile(AutoConfig.getTemplatePath()+"service.txt");
		String packageString = AutoConfig.getServicepath();
		String importString = "import "+AutoConfig.getDomainpath()+"."+className+";";
		String s1 = className;
		serviceContent = serviceContent.replace("${package}", packageString);
		serviceContent = serviceContent.replace("${import}", importString);
		serviceContent = serviceContent.replace("${s1}", s1);
		serviceContent = serviceContent.replace("${methodByPrimaryKey}", methodByPrimaryKey_service(className, primaryBeanList));
		System.out.println(serviceContent);
		
		System.out.println("-------------serviceImpl------------");
		String serviceImplContent = AutoUtils.readFile(AutoConfig.getTemplatePath()+"serviceImpl.txt");
		String packageImplString = AutoConfig.getServiceimplpath();
		String importImplString = "import "+AutoConfig.getDomainpath()+"."+className+";\n";
		importImplString += "import "+AutoConfig.getMapperpath()+"."+className+"Mapper;\n";
		importImplString += "import "+AutoConfig.getServicepath()+"."+className+"Service;";
		String s2 = AutoUtils.lowerFirst(s1);
		serviceImplContent = serviceImplContent.replace("${package}", packageImplString);
		serviceImplContent = serviceImplContent.replace("${import}", importImplString);
		serviceImplContent = serviceImplContent.replace("${s1}", s1);
		serviceImplContent = serviceImplContent.replace("${s2}", s2);
		serviceImplContent = serviceImplContent.replace("${methodByPrimaryKey}", methodByPrimaryKey_serviceImpl(className, primaryBeanList));
		System.out.println(serviceImplContent);
		
		//生成文件
		String servicePath = AutoConfig.getProjectpath() + AutoConfig.getClassPath() +AutoConfig.getServicepath().replace(".", "/") +"/"+className+"Service.java";
		AutoUtils.createFile(servicePath, serviceContent);
		String serviceImplPath = AutoConfig.getProjectpath() + AutoConfig.getClassPath() +AutoConfig.getServiceimplpath().replace(".", "/")+"/"+className+"ServiceImpl.java";
		AutoUtils.createFile(serviceImplPath, serviceImplContent);
	}
	
	
	private static String methodByPrimaryKey_service(String className, List<AutoBean> primaryBeanList){
		StringBuffer sb = new StringBuffer();
		if(primaryBeanList.size()>0){
			String methodByPrimaryKey = "int updateByPrimaryKey("+className+" record);\n";
			String paramsStr = "";
			if(primaryBeanList.size()==1){
				AutoBean primaryBean = primaryBeanList.get(0);
				paramsStr = primaryBean.getJavaType()+" id";
			}else{
				for(int i=0;i<primaryBeanList.size();i++){
					AutoBean primaryBean = primaryBeanList.get(i);
					if(i==0){
						paramsStr = primaryBean.getJavaType() + " " + primaryBean.getProperty();
					}else{
						paramsStr += ", " + primaryBean.getJavaType() + " " + primaryBean.getProperty();
					}
				}
			}
			methodByPrimaryKey += "\tint deleteByPrimaryKey("+paramsStr+");\n";
			methodByPrimaryKey += "\t"+className+" selectByPrimaryKey("+paramsStr+");";
			sb.append(methodByPrimaryKey);
		}
		return sb.toString();
	}
	
	private static String methodByPrimaryKey_serviceImpl(String className, List<AutoBean> primaryBeanList){
		String objectName = AutoUtils.lowerFirst(className);
		StringBuffer sb = new StringBuffer();
		if(primaryBeanList.size()>0){
			sb.append("public int updateByPrimaryKey("+className+" record){");
			sb.append("\n\t\treturn "+objectName+"Mapper.updateByPrimaryKey(record);");
			sb.append("\n\t}");
			sb.append("");
			String paramsStr = "";
			String paramsStr2 = "";
			if(primaryBeanList.size()==1){
				AutoBean primaryBean = primaryBeanList.get(0);
				paramsStr = primaryBean.getJavaType()+" id";
				paramsStr2 = "id";
			}else{
				for(int i=0;i<primaryBeanList.size();i++){
					AutoBean primaryBean = primaryBeanList.get(i);
					if(i==0){
						paramsStr = primaryBean.getJavaType() + " " + primaryBean.getProperty();
						paramsStr2 = primaryBean.getProperty();
					}else{
						paramsStr += ", " + primaryBean.getJavaType() + " " + primaryBean.getProperty();
						paramsStr2 += ", " + primaryBean.getProperty();
					}
				}
			}
			sb.append("\n\n\tpublic int deleteByPrimaryKey("+paramsStr+"){");
			sb.append("\n\t\treturn "+objectName+"Mapper.deleteByPrimaryKey("+paramsStr2+");");
			sb.append("\n\t}");
			sb.append("\n\n\tpublic "+className+" selectByPrimaryKey("+paramsStr+"){");
			sb.append("\n\t\treturn "+objectName+"Mapper.selectByPrimaryKey("+paramsStr2+");");
			sb.append("\n\t}");
		}
		return sb.toString();
	}
	
}
