package com.jyyq.platformcommon.auto.config;

public class AutoConfig {
	
	private final static String url = "jdbc:mysql://localhost:3306/jinle?useUnicode=true&amp;characterEncoding=UTF-8";//数据库连接字符串
	private final static String user = "root";//数据库用户名
	private final static String password = "123456";//数据库密码

	private final static String projectPath = "D:/WorkSpace/IdeaProjects/jinle/platform-web/";
	private final static String templatePath = "D:/WorkSpace/IdeaProjects/jinle/platform-common/autoconf/";

    private final static String classPath = "src/main/java/";
	private final static String domainPath = "com.jyyq.platformweb.dao";//实体类
	private final static String mapperPath = "com.jyyq.platformweb.mapper";//dao接口路径
	private final static String mapperxmlPath = "mapper";//mapper.xml路径
	private final static String servicePath = "com.jyyq.platformweb.service";//service接口路径
	private final static String serviceImplPath = "com.jyyq.platformweb.service.impl";//service实现路径
	private final static String controllerPath = "com.jyyq.platformweb.controller";//controller路径
	
	
	public static String getUrl() {
		return url;
	}
	public static String getUser() {
		return user;
	}
	public static String getPassword() {
		return password;
	}
	public static String getProjectpath() { return projectPath; }
	public static String getDomainpath() {
		return domainPath;
	}
	public static String getMapperpath() {
		return mapperPath;
	}
	public static String getMapperxmlpath() {
		return mapperxmlPath;
	}
	public static String getServicepath() {
		return servicePath;
	}
	public static String getServiceimplpath() {
		return serviceImplPath;
	}
	public static String getControllerpath() {
		return controllerPath;
	}
    public static String getClassPath() { return classPath; }
    public static String getTemplatePath(){return templatePath;}
}
