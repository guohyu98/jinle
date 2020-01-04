package com.jyyq.platformweb.controller;

import com.jyyq.platformcommon.common.ShowVarUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.jyyq.platformweb.dao.CommonProblem;

import com.jyyq.platformweb.service.CommonProblemService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/commonProblemManage")
public class CommonProblemManageController {

	@Resource
	CommonProblemService commonProblemService;

	/**
	 * 查询列表
	 */
	@RequestMapping("/queryCommonProblem")
	public String queryCommonProblem(CommonProblem commonProblem, HttpServletRequest request, Model model){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("queryCommonProblem");
		ShowVarUtil.showJsonObject("commonProblem", commonProblem);
		List<CommonProblem> commonProblemList = commonProblemService.selectListCommonProblem(commonProblem);
		model.addAttribute("commonProblemList", commonProblemList);
		ShowVarUtil.end("queryCommonProblem", time);
		return "platform/commonProblemManage";
	}

	/**
	 * 新增/修改页面
	 */
	@RequestMapping("/queryCommonProblemDetails")
	public String queryCommonProblemDetails(Integer id, HttpServletRequest request, Model model){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("queryCommonProblemDetails");
		ShowVarUtil.input_show("id",id);
		CommonProblem commonProblem = null;
		if(id!=null){
			commonProblem = commonProblemService.selectByPrimaryKey(id);
		}else{
			commonProblem = new CommonProblem();
		}
		model.addAttribute("commonProblem",commonProblem);
		ShowVarUtil.end("queryCommonProblemDetails", time);
		return "platform/commonProblemDetails";
	}

	/**
	 * 添加/修改
	 */
	@RequestMapping("/saveCommonProblem")
	@ResponseBody
	public Map<String, Object> saveCommonProblem(CommonProblem commonProblem, HttpServletRequest request, Model model){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("saveCommonProblem");
		ShowVarUtil.showJsonObject("commonProblem", commonProblem);
		Map<String, Object> map = new HashMap<String, Object>();
		int ret = 0;
		try {
			if(commonProblem.getId()==null){
				commonProblem.setAddTime(new Date());
				ret = commonProblemService.insert(commonProblem);
			}else{
				ret = commonProblemService.updateByPrimaryKey(commonProblem);
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
			ShowVarUtil.end("saveCommonProblem", time);
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping("/deleteCommonProblem")
	@ResponseBody
	public Map<String, Object> deleteCommonProblem(Integer id, HttpServletRequest request){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("deleteCommonProblem");
		ShowVarUtil.input_show("id",id);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int ret = commonProblemService.deleteByPrimaryKey(id);
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
			ShowVarUtil.end("deleteCommonProblem", time);
		}
	}

}
