package com.jyyq.platformweb.controller;

import com.jyyq.platformcommon.common.ShowVarUtil;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.jyyq.platformweb.dao.BannerDao;
import com.jyyq.platformweb.dao.ConfigBasic;

import com.jyyq.platformweb.service.ConfigBasicService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/configBasicManage")
public class ConfigBasicManageController {

	@Resource
	ConfigBasicService configBasicService;

	/**
	 * 查询列表
	 */
	@RequestMapping("/queryConfigBasic")
	public String queryConfigBasic(HttpServletRequest request, Model model){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("queryConfigBasic");
		ConfigBasic configBasic = configBasicService.selectConfigBasic();
		model.addAttribute("configBasic", configBasic);
		if(!StringUtils.isBlank(configBasic.getAdvertisingImg())){
//			List<String> bannerList = new ArrayList<String>();
//			List<Integer> typeList = new ArrayList<Integer>();
//			List<String>
//			model.addAttribute("bannerList", bannerList);
		}
		ShowVarUtil.end("queryConfigBasic", time);
		return "platform/configBasic";
	}

	/**
	 * 添加/修改
	 */
	@RequestMapping("/saveConfigBasic")
	@ResponseBody
	public Map<String, Object> saveConfigBasic(ConfigBasic configBasic, HttpServletRequest request, Model model){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("saveConfigBasic");
		ShowVarUtil.showJsonObject("configBasic", configBasic);
		Map<String, Object> map = new HashMap<String, Object>();
		int ret = 0;
		try {
			ret = configBasicService.save(configBasic);
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
			ShowVarUtil.end("saveConfigBasic", time);
		}
	}
	/**
	 * 查询启动页图片
	 */
	@RequestMapping("/queryStartupPage")
	public String queryStartupPage(ConfigBasic configBasic, HttpServletRequest request, Model model){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("queryStartupPage");
		ShowVarUtil.showJsonObject("configBasic", configBasic);
		String startupPage = configBasicService.selectStartupPage();
		model.addAttribute("startupPage", startupPage);
		ShowVarUtil.end("queryStartupPage", time);
		return "platform/startupPage";
	}

	/**
	 * 添加/修改
	 */
	@RequestMapping("/saveStartupPage")
	@ResponseBody
	public Map<String, Object> saveStartupPage(String startupPage, HttpServletRequest request, Model model){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("saveStartupPage");
		ShowVarUtil.showJsonObject("startupPage", startupPage);
		Map<String, Object> map = new HashMap<String, Object>();
		int ret = 0;
		try {
			ConfigBasic configBasic = new ConfigBasic();
			configBasic.setStartupPage(startupPage);
			ret = configBasicService.save(configBasic);
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
			ShowVarUtil.end("saveStartupPage", time);
		}
	}

}
