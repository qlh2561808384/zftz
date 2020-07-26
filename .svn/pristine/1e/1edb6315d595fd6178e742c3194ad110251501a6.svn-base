package com.datanew.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datanew.dto.Result;
import com.datanew.service.BglxdbaService;

@Controller
@RequestMapping("/bglxdba")
public class BglxdbaController {
	@Autowired
	private BglxdbaService bglxdbaService;
	
	@ResponseBody
	@RequestMapping("getBglxdbaData")
	public Object getBglxdbaData(String xmmc,int zt,HttpSession session){
		return bglxdbaService.getBglxdbaData(xmmc,zt,session);
	}
	@ResponseBody
	@RequestMapping("saveBglxdba")
	public Result saveBglxdba(String content1,String content2,HttpSession session){
		return bglxdbaService.saveBglxdba(content1,content2,session);
	}
	@ResponseBody
	@RequestMapping("selBglxdbamxByBglxdbaid")
	public Object selBglxdbamxByBglxdbaid(String bglxdbaid,String xmid){
		return bglxdbaService.selBglxdbamxByBglxdbaid(bglxdbaid,xmid);
	}
	@ResponseBody
	@RequestMapping("getGsxxByXmid")
	public Object getGsxxByXmid(String xmid){
		return bglxdbaService.getGsxxByXmid(xmid);
	}
	@ResponseBody
	@RequestMapping("saveXmbgbaData")
	public Result saveXmbgbaData(String content1,String content2,HttpSession session){
		return bglxdbaService.saveXmbgbaData(content1, content2, session);
	}
	@ResponseBody
	@RequestMapping("selBglxdbaFilesByBglxdbaid")
	public Object selBglxdbaFilesByBglxdbaid(String bglxdbaid){
		return bglxdbaService.selBglxdbaFilesByBglxdbaid(bglxdbaid);
	}
	@ResponseBody
	@RequestMapping("deleteBglxdba")
	public Result deleteBglxdba(String ids){
		return bglxdbaService.deleteBglxdba(ids);
	}
	@ResponseBody
	@RequestMapping("selectGcfymcByXmid")
	public Object selectGcfymcByXmid(String xmid){
		return bglxdbaService.selectGcfymcByXmid(xmid);
	}
	@ResponseBody
	@RequestMapping("selGsxxByFyidAndTzefl")
	public Object selGsxxByFyidAndTzefl(String xmid, String gcfyid, String tzefl,String htbaid){
		return bglxdbaService.selGsxxByFyidAndTzefl(xmid,gcfyid,tzefl,htbaid);
	}
	
	@ResponseBody
	@RequestMapping("submitSgtba")
	public Result submitSgtba(String id,HttpSession session) throws Exception{
		return bglxdbaService.submitSgtba(id,session);
	}
	
	@ResponseBody
	@RequestMapping("returnback")
	public Result returnback(String id,HttpSession session) throws Exception{
		return bglxdbaService.returnback(id,session);
	}
}
