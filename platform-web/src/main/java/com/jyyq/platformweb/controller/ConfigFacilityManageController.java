package com.jyyq.platformweb.controller;

import com.jyyq.platformcommon.common.ShowVarUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.jyyq.platformweb.dao.ConfigFacility;

import com.jyyq.platformweb.service.ConfigFacilityService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/configFacilityManage")
public class ConfigFacilityManageController {

	@Resource
	ConfigFacilityService configFacilityService;

	/**
	 * 查询列表
	 */
	@RequestMapping("/queryConfigFacility")
	public String queryConfigFacility(HttpServletRequest request, Model model){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("queryConfigFacility");
		List<ConfigFacility> configFacilityList = configFacilityService.selectListChildren();
		model.addAttribute("configFacilityList", configFacilityList);
		ShowVarUtil.end("queryConfigFacility", time);
		return "platform/configFacilityManage";
	}

	/**
	 * 新增/修改页面
	 */
	@RequestMapping("/queryConfigFacilityDetails")
	public String queryConfigFacilityDetails(Integer id, HttpServletRequest request, Model model){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("queryConfigFacilityDetails");
		ShowVarUtil.input_show("id",id);
		ConfigFacility configFacility = null;
		if(id!=null){
			configFacility = configFacilityService.selectByPrimaryKey(id);
		}else{
			configFacility = new ConfigFacility();
		}
		model.addAttribute("configFacility",configFacility);
		//查询所有上级
		List<ConfigFacility> parentList = configFacilityService.selectListParent();
		model.addAttribute("parentList", parentList);
		ShowVarUtil.end("queryConfigFacilityDetails", time);
		return "platform/configFacilityDetails";
	}

	/**
	 * 添加/修改
	 */
	@RequestMapping("/saveConfigFacility")
	@ResponseBody
	public Map<String, Object> saveConfigFacility(ConfigFacility configFacility, HttpServletRequest request, Model model){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("saveConfigFacility");
		ShowVarUtil.showJsonObject("configFacility", configFacility);
		Map<String, Object> map = new HashMap<String, Object>();
		int ret = 0;
		try {
			if(configFacility.getId()==null){
				configFacility.setAddTime(new Date());
				ret = configFacilityService.insert(configFacility);
			}else{
				configFacility.setUpdateTime(new Date());
				ret = configFacilityService.updateByPrimaryKey(configFacility);
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
			ShowVarUtil.end("saveConfigFacility", time);
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping("/deleteConfigFacility")
	@ResponseBody
	public Map<String, Object> deleteConfigFacility(Integer id, HttpServletRequest request){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("deleteConfigFacility");
		ShowVarUtil.input_show("id",id);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int ret = configFacilityService.deleteByPrimaryKey(id);
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
			ShowVarUtil.end("deleteConfigFacility", time);
		}
	}

}
