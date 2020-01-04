package com.jyyq.platformweb.controller;

import com.jyyq.platformcommon.common.ShowVarUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.jyyq.platformweb.dao.Admin;

import com.jyyq.platformweb.service.AdminService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/adminManage")
public class AdminManageController {

	@Resource
	AdminService adminService;

	/**
	 * 查询列表
	 */
	@RequestMapping("/queryAdmin")
	public String queryAdmin(Admin admin, HttpServletRequest request, Model model){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("queryAdmin");
		ShowVarUtil.showJsonObject("admin", admin);
		List<Admin> adminList = adminService.selectListAdmin(admin);
		model.addAttribute("adminList", adminList);
		ShowVarUtil.end("queryAdmin", time);
		return "platform/adminManage";
	}

	/**
	 * 新增/修改页面
	 */
	@RequestMapping("/queryAdminDetails")
	public String queryAdminDetails(Integer id, HttpServletRequest request, Model model){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("queryAdminDetails");
		ShowVarUtil.input_show("id",id);
		Admin admin = null;
		if(id!=null){
			admin = adminService.selectByPrimaryKey(id);
		}else{
			admin = new Admin();
		}
		model.addAttribute("admin",admin);
		ShowVarUtil.end("queryAdminDetails", time);
		return "platform/adminDetails";
	}

	/**
	 * 添加/修改
	 */
	@RequestMapping("/saveAdmin")
	@ResponseBody
	public Map<String, Object> saveAdmin(Admin admin, HttpServletRequest request, Model model){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("saveAdmin");
		ShowVarUtil.showJsonObject("admin", admin);
		Map<String, Object> map = new HashMap<String, Object>();
		int ret = 0;
		try {
			if(admin.getId()==null){
				admin.setAddTime(new Date());
				ret = adminService.insert(admin);
			}else{
				ret = adminService.updateByPrimaryKey(admin);
			}
			if(ret>0){
				map.put("state", "success");
			}else{
				map.put("state", "error");
			}
			return map;
		} catch (Exception e) {
			map.put("state", "error");
			map.put("msg",e.getMessage());
			return map;
		}finally{
			ShowVarUtil.end("saveAdmin", time);
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping("/deleteAdmin")
	@ResponseBody
	public Map<String, Object> deleteAdmin(Integer id, HttpServletRequest request){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("deleteAdmin");
		ShowVarUtil.input_show("id",id);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int ret = adminService.deleteByPrimaryKey(id);
			if(ret>0){
				map.put("state", "success");
			}else{
				map.put("state", "error");
			}
			return map;
		} catch (Exception e) {
			map.put("state", "error");
			map.put("msg",e.getMessage());
			return map;
		}finally{
			ShowVarUtil.end("deleteAdmin", time);
		}
	}

}
