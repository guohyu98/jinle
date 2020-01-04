package com.jyyq.platformweb.controller;

import com.jyyq.platformcommon.common.ShowVarUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.jyyq.platformweb.dao.ConfigShowInfo;
import com.jyyq.platformweb.dao.Hotel;

import com.jyyq.platformweb.service.ConfigService;
import com.jyyq.platformweb.service.HotelService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hotelManage")
public class HotelManageController {

	@Resource
	HotelService hotelService;
	@Resource
	ConfigService configService;

	/**
	 * 查询列表
	 */
	@RequestMapping("/queryHotel")
	public String queryHotel(Hotel hotel, HttpServletRequest request, Model model){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("queryHotel");
		ShowVarUtil.showJsonObject("hotel", hotel);
		List<Hotel> hotelList = hotelService.selectListHotel(hotel);
		model.addAttribute("hotelList", hotelList);
		ShowVarUtil.end("queryHotel", time);
		return "platform/hotelManage";
	}

	/**
	 * 新增/修改页面
	 */
	@RequestMapping("/queryHotelDetails")
	public String queryHotelDetails(Integer id, HttpServletRequest request, Model model){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("queryHotelDetails");
		ShowVarUtil.input_show("id",id);
		Hotel hotel = null;
		if(id!=null){
			hotel = hotelService.selectByPrimaryKey(id);
		}else{
			hotel = new Hotel();
		}
		model.addAttribute("hotel",hotel);
		//酒店展示信息列表
		List<ConfigShowInfo> showInfoList = configService.selectListConfigShowInfo();
		model.addAttribute("showInfoList", showInfoList);
		ShowVarUtil.end("queryHotelDetails", time);
		return "platform/hotelDetails";
	}

	/**
	 * 添加/修改
	 */
	@RequestMapping("/saveHotel")
	@ResponseBody
	public Map<String, Object> saveHotel(Hotel hotel, HttpServletRequest request, Model model){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("saveHotel");
		ShowVarUtil.showJsonObject("hotel", hotel);
		Map<String, Object> map = new HashMap<String, Object>();
		int ret = 0;
		try {
			if(hotel.getId()==null){
				hotel.setAddTime(new Date());
				ret = hotelService.insert(hotel);
			}else{
				ret = hotelService.updateByPrimaryKey(hotel);
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
			ShowVarUtil.end("saveHotel", time);
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping("/deleteHotel")
	@ResponseBody
	public Map<String, Object> deleteHotel(Integer id, HttpServletRequest request){
		long time = System.currentTimeMillis();
		ShowVarUtil.start("deleteHotel");
		ShowVarUtil.input_show("id",id);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int ret = hotelService.deleteByPrimaryKey(id);
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
			ShowVarUtil.end("deleteHotel", time);
		}
	}

}
