package com.datanew.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datanew.dto.Result;
import com.datanew.service.HtbaService;

@Controller
@RequestMapping("/htba")
public class HtbaController {
	@Autowired
	private HtbaService htbaService;
	@ResponseBody
	@RequestMapping("getHtbaData")
	public Object getHtbaData(String id_zftz_xm,String zt,HttpSession session){
		return htbaService.getHtbaData(id_zftz_xm,zt,session);
	}
	@ResponseBody
	@RequestMapping("getXybaData")
	public Object getXybaData(String id_zftz_xm,String zt,HttpSession session){
		return htbaService.getXybaData(id_zftz_xm,zt,session);
	}
	@ResponseBody
	@RequestMapping("selectHtmc")
	public Object selectHtmc(HttpSession session){
		return htbaService.selectHtmc(session);
	}
	@ResponseBody
	@RequestMapping("selectHtlx")
	public  Object selectHtlx(){
		return htbaService.selectHtlx();
	}
	@ResponseBody
	@RequestMapping("selectJsfs")
	public Object selectJsfs(){
		return htbaService.selectJsfs();
	}
	@ResponseBody
	@RequestMapping("selectSgtbamc")
	public Object selectSgtbamc(String xmid){
		return htbaService.selectSgtbamc(xmid);
	}
	@ResponseBody
	@RequestMapping("selHtbamxByHtbaid")
	public Object selHtbamxByHtbaid(String htbaid,String xmid){
		return htbaService.selHtbamxByHtbaid(htbaid,xmid);
	}
	@ResponseBody
	@RequestMapping("selHtyjzfmxByHtbaid")
	public Object selHtyjzfmxByHtbaid(String htbaid){
		return htbaService.selHtyjzfmxByHtbaid(htbaid);
	}
	@ResponseBody
	@RequestMapping("saveHtba")
	public Result saveHtba(String content1,int htxybj,HttpSession session){
		Result result = new Result();
		result = htbaService.saveHtba(content1,htxybj,session);
		return result;
	}
	@ResponseBody
	@RequestMapping("deleteHtba")
	public Result deleteHtba(String ids){
		Result result = new Result();
		result = htbaService.deleteHtba(ids);
		return result;
	}
	@ResponseBody
	@RequestMapping("selectHtmcByXmid")
	public Object selectHtmcByXmid(String xmid){
		return htbaService.selectHtmcByXmid(xmid);
	}
	@ResponseBody
	@RequestMapping("selectHtbagcmc")
	public Object selectHtbagcmc(String htbaid){
		return htbaService.selectHtbagcmc(htbaid);
	}
	@ResponseBody
	@RequestMapping("selectDygcfy")
	public Object selectDygcfy(String sgtbaid){
		return htbaService.selectDygcfy(sgtbaid);
	}
	@ResponseBody
	@RequestMapping("selectTzeflBySgtid")
	public Object selectTzeflBySgtid(String sgtbaid){
		return htbaService.selectTzeflBySgtid(sgtbaid);
	}
	@ResponseBody
	@RequestMapping("selectSgtbamcById")
	public Object selectSgtbamcById(String xmid){
		return htbaService.selectSgtbamcById(xmid);
	}
	@ResponseBody
	@RequestMapping("selectGcfymcBySgtid")
	public Object selectGcfymcBySgtid(String sgtbaid){
		return htbaService.selectGcfymcBySgtid(sgtbaid);
	}
	@ResponseBody
	@RequestMapping("submitHtba")
	public Object submitHtba(String id,String isHt,HttpSession session){
		return htbaService.submitSgtba(id,isHt,session);
	}
	@ResponseBody
	@RequestMapping("returnback")
	public Result returnback(String id,String isHt,HttpSession session) throws Exception{
		return htbaService.returnback(id,isHt,session);
	}
	@ResponseBody
	@RequestMapping("selHtbaFilesById")
	public Object selHtbaFilesById(String htid){
		return htbaService.selHtbaFilesById(htid);
	}
	@ResponseBody
	@RequestMapping("selGsxxByFyidAndTzefl")
	public Object selGsxxByFyidAndTzefl(String xmid,String gcfyid,String tzefl,String isEdit,String sgtbaid){
		return htbaService.selGsxxByFyidAndTzefl(xmid,gcfyid,tzefl,isEdit,sgtbaid);
	}
	
	@ResponseBody
	@RequestMapping("selectSgtbaIds")
	public Object selectSgtbaIds(String xmid){
		return htbaService.selectSgtbaIds(xmid);
	}
	
	@ResponseBody
	@RequestMapping("getGcmcAndFylx")
	public Object getGcmcAndFylx(String ids){
		return htbaService.getGcmcAndFylx(ids);
	}
}
