package com.jyyq.platformweb.controller;

import com.jyyq.platformcommon.common.ShowVarUtil;
import com.jyyq.platformweb.dao.Admin;
import com.jyyq.platformweb.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户功能(账户设置)类
 * @author yu
 *
 */
@Controller
@RequestMapping("/pc")
public class LoginController {
	
	@Resource
	AdminService adminService;

	/**
	 * 登录页面
	 * @param request
	 * @param model
	 * @return
	 */
//	@RequestMapping("/login")
	@RequestMapping(value="/login", method= RequestMethod.GET)
	public String login(HttpServletRequest request, Model model){
		request.getSession().removeAttribute("admin");
		return "login";
	}
	
	/**
	 * 登录
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping("/loginForm")
	@ResponseBody
	public Map<String, Object>loginForm(String loginName, String password, HttpServletRequest request){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("loginForm");
		Map<String, Object> map = new HashMap<String, Object>();
		ShowVarUtil.input_show("loginName="+loginName, "password="+password);
		if(StringUtils.isBlank(loginName)|| StringUtils.isBlank(password)){
			map.put("state", "error");
			map.put("msg", "用户名和密码不能为空");
			return map;
		}
		Admin admin = adminService.login(loginName, password);
		ShowVarUtil.showJsonObject("admin==",admin);
		if(admin==null){
			map.put("state", "error");
			map.put("msg", "用户名或密码错误");
			return map;
		}
		//登录成功
		request.getSession().setAttribute("admin", admin);
		map.put("state", "success");
		map.put("msg", "admin");
		ShowVarUtil.end("loginForm", time);
		return map;
	}
	
	/**
	 * 首页
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model) {
		//权限
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		/*List<Menu> menuList = menuService.selectListMenuByRole(admin.getRole());
		model.addAttribute("menuList", menuList);*/
		return "platform/index";
	}
	
	/**
	 * 主页
	 * @param request
	 * @return
	 */
	@RequestMapping("/main")
	public String main(HttpServletRequest request, Model model){
		return "welcome";
	}
	
	/**
	 * 到修改密码的页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/setPassword")
	public String setPassword(HttpServletRequest request, Model model){
		if(request.getSession().getAttribute("admin")==null){
    		return "login";
    	}
    	return "setPassword";
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @return
	 */
	@RequestMapping("/updatePassword")
	@ResponseBody
	public Map<String, Object> updatePassword(HttpServletRequest request){
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		String password = request.getParameter("password");//新密码 
		ShowVarUtil.input_show("password",password);
		Admin updateAdmin = new Admin();
		updateAdmin.setId(admin.getId());
		if(password != null && !password.equals("")){
			updateAdmin.setPassword(password);
		}
    	Map<String, Object> modelMap = new HashMap<String, Object>();	    	 
    	try {
    		int ret = adminService.updateByPrimaryKey(updateAdmin);
    		if(ret>0){
    			modelMap.put("state", "success");
    		}else{
    			modelMap.put("state", "error");
    		}
		} catch (Exception e) {
			ShowVarUtil.output_show("修改密码错误："+e.getMessage());
			modelMap.put("state", "error");
		}
	    return modelMap;
	}
	
	/**
	 * 退出平台
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/quitPlatform")
	public String quitPlatform(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.getSession().removeAttribute("admin");
		return "redirect:/pc/login";
	}
	
	/**
	 * 跳转到登录页
	 * @param request
	 * @return
	 */
	@RequestMapping("/redirect")
	public String redirect(String msg, HttpServletRequest request, Model model){
		ShowVarUtil.debug("msg", msg);
		model.addAttribute("msg", msg);
		return "redirect";
	}
	
}
