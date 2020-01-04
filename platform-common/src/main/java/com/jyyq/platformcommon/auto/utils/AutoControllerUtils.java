package com.jyyq.platformcommon.auto.utils;

import com.jyyq.platformcommon.auto.config.AutoConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 自动生成Controller
 * @author yu
 *
 */
public class AutoControllerUtils {
	
	public static void exportController(String className, List<AutoBean> beanList){
		if(beanList==null || beanList.size()==0){
			System.out.println("beanList 为空,生成controller失败");
			return;
		}
		List<AutoBean> primaryBeanList = new ArrayList<AutoBean>();
		for(AutoBean bean : beanList){
			if(bean.getIsPrimaryKey()==1){
				primaryBeanList.add(bean);
			}
		}
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
		paramsStr += ", ";
		
		System.out.println("-------------controller------------");
		String controllerContent = AutoUtils.readFile(AutoConfig.getTemplatePath()+"pcController.txt");
		String packageString = AutoConfig.getControllerpath();
		String importString = "import "+AutoConfig.getDomainpath()+"."+className+";";
		importString += "\n\nimport "+AutoConfig.getServicepath()+"."+className+"Service;"; 
		String c1 = className;
		String c2 = AutoUtils.lowerFirst(c1);
		controllerContent = controllerContent.replace("${package}", packageString);
		controllerContent = controllerContent.replace("${import}", importString);
		controllerContent = controllerContent.replace("${pk}", paramsStr);
		controllerContent = controllerContent.replace("${pk2}", paramsStr2);
		controllerContent = controllerContent.replace("${c1}", c1);
		controllerContent = controllerContent.replace("${c2}", c2);
		//System.out.println(controllerContent);
		
		String controllerPath = AutoConfig.getProjectpath() + AutoConfig.getClassPath() +AutoConfig.getControllerpath().replace(".", "/")+"/"+className+"ManageController.java";
		System.out.println(controllerPath);
		AutoUtils.createFile(controllerPath, controllerContent);
	}
}
