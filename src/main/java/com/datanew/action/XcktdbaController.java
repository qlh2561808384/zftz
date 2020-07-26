package com.datanew.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datanew.dto.Result;
import com.datanew.service.XcktdbaService;

@Controller
@RequestMapping("/xcktdba")
public class XcktdbaController {
	@Autowired
	private XcktdbaService xcktdbaService;
	
	@ResponseBody
	@RequestMapping("getXmxxByHtid")
	public Object getXmxxByHtid(String htbaid){
		return xcktdbaService.getXmxxByHtid(htbaid);
	}
	@ResponseBody
	@RequestMapping("getZfxxByHtid")
	public Object getZfxxByHtid(String htbaid){
		return xcktdbaService.getZfxxByHtid(htbaid);
	} 
	@ResponseBody
	@RequestMapping("saveXcktdData")
	public Result saveXcktdData(String content,HttpSession session){
		return xcktdbaService.saveXcktdData(content,session);
	}
	@ResponseBody
	@RequestMapping("getXcktdbaData")
	public Object  getXcktdbaData(String id_zftz_xm,String zt,HttpSession session){
		return xcktdbaService.getXcktdbaData(id_zftz_xm,zt,session);
	}
	@ResponseBody
	@RequestMapping("deleteXcktdba")
	public Result deleteXcktdba(String ids){
		return xcktdbaService.deleteXcktdba(ids);
	}
	@ResponseBody
	@RequestMapping("selectHtmc")
	public Object selectHtmc(){
		return xcktdbaService.selectHtmc();
	}
	@ResponseBody
	@RequestMapping("selXcktdFilesByXcktdba")
	public Object selXcktdFilesByXcktdba(String xcktdbaid){
		return xcktdbaService.selXcktdFilesByXcktdba(xcktdbaid);
	}
	
	@ResponseBody
	@RequestMapping("selHtmc")
	public Object selHtmc(HttpSession session){
		return xcktdbaService.selHtmc(session);
	}
	
	@ResponseBody
	@RequestMapping("selectXmmcByJsdw")
	public Object selectXmmcByJsdw(String jsdw){
		return xcktdbaService.selectXmmcByJsdw(jsdw);
	}
	
	@ResponseBody
	@RequestMapping("selectGcxxByXmid")
	public Object selectGcxxByXmid(String xmid){
		return xcktdbaService.selectGcxxByXmid(xmid);
	}
	
	@ResponseBody
	@RequestMapping("selectJsdw")
	public Object selectJsdw(){
		return xcktdbaService.selectJsdw();
	}
	
	@ResponseBody
	@RequestMapping("getYzfAndYeByHtid")
	public Object getYzfAndYeByHtid(String htbaid){
		return xcktdbaService.getYzfAndYeByHtid(htbaid);
	}
}
