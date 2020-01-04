package com.jyyq.platformcommon.auto.utils;

import com.jyyq.platformcommon.auto.config.AutoConfig;

import java.util.List;

/**
 * 自动生成Controller
 * @author yu
 *
 */
public class AutoWxControllerUtils {
	
	public static void exportController(String className, String tableName, List<AutoBean> beanList){
		if(beanList==null || beanList.size()==0){
			System.out.println("beanList 为空,生成controller失败");
			return;
		}
		String pkType = "Integer";
		for(AutoBean bean: beanList){
			if(bean.getIsPrimaryKey()==1){
				pkType = bean.getJavaType();
			}
		}
		
		System.out.println("-------------controller------------");
		String controllerContent = AutoUtils.readFile(AutoConfig.getTemplatePath()+"wxController.txt");
		String packageString = AutoConfig.getControllerpath();
		String importString = "import "+AutoConfig.getDomainpath()+"."+className+";";
		importString += "\n\nimport "+AutoConfig.getServicepath()+"."+className+"Service;"; 
		String c1 = className;
		String c2 = AutoUtils.lowerFirst(c1);
		controllerContent = controllerContent.replace("${package}", packageString);
		controllerContent = controllerContent.replace("${import}", importString);
		controllerContent = controllerContent.replace("${pk}", pkType);
		controllerContent = controllerContent.replace("${c1}", c1);
		controllerContent = controllerContent.replace("${c2}", c2);
		System.out.println(controllerContent);
		
		String controllerPath = AutoConfig.getProjectpath() +"src/"+AutoConfig.getControllerpath().replace(".", "/")+"/Wx"+className+"Controller.java";
		System.out.println(controllerPath);
		AutoUtils.createFile(controllerPath, controllerContent);
	}
}
