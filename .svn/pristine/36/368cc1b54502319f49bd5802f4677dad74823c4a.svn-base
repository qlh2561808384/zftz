package com.datanew.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datanew.dto.Result;
import com.datanew.service.SgtysbaService;

@Controller
@RequestMapping("/sgtysba")
public class SgtysbaController {
	@Autowired
	private SgtysbaService sgtysbaService;
	
	@ResponseBody
	@RequestMapping("saveSgtysba")
	public Result saveSgtysba(String content1,String content2,String jsnr,String zgbmyj,String sczjbayj,HttpSession session){
		Result result = new Result();
		result = sgtysbaService.saveSgtysba(content1,content2,jsnr,zgbmyj,sczjbayj,session);
		return result;
	}
	@ResponseBody
	@RequestMapping("getSgtysbaData")
	public Object getSgtysbaData(String id_zftz_xm,String zt,String isCx,HttpSession session){
		return sgtysbaService.getSgtysbaData(id_zftz_xm,zt,isCx,session);
	}
	@ResponseBody
	@RequestMapping("deleteSgtysba")
	public Result deleteSgtysba(String ids){
		Result result = new Result();
		result = sgtysbaService.deleteSgtysba(ids);
		return result;
	}
	@ResponseBody
	@RequestMapping("selSgtysbamxBySgtbaid")
	public Object selSgtysbamxBySgtbaid(long sgtbaid,long xmid){
		return sgtysbaService.selSgtysbamxBySgtbaid(sgtbaid,xmid);
	}
	
	@ResponseBody
	@RequestMapping("selectXmmc")
	public Object selectXmmc(HttpSession session){
		return sgtysbaService.selectXmmc(session);
	}
	
	@ResponseBody
	@RequestMapping("getGsxxByXmid")
	public Object getGsxxByXmid(String xmid){
		return sgtysbaService.getGsxxByXmid(xmid);
	}
	
	@ResponseBody 
	@RequestMapping("selectGcfymc")
	public Object selectGcfymc(){
		return sgtysbaService.selectGcfymc();
	}
	
	@ResponseBody
	@RequestMapping("selectTzefl")
	public Object selectTzefl(){
		return sgtysbaService.selectTzefl();
	}
	@ResponseBody
	@RequestMapping("selGsxxByFyidAndTzefl")
	public Object selGsxxByFyidAndTzefl(String xmid,String gcfyid,String tzefl,String isEdit,String sgtbaid){
		return sgtysbaService.selGsxxByFyidAndTzefl(xmid,gcfyid,tzefl,isEdit,sgtbaid);
	}
	@ResponseBody
	@RequestMapping("submitSgtba")
	public Result submitSgtba(String id,HttpSession session){
		Result result = new Result();
		
		try {
			result = sgtysbaService.submitSgtba(id,session);
			result.setContent("提交成功！");
			result.setSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setContent("提交失败，请检查！");
			result.setSuccess(false);
			e.printStackTrace();
		}
		
		return result;
	}
	@ResponseBody
	@RequestMapping("getYhxm")
	public Object getYhxm(){
		return sgtysbaService.getYhxm();
	}
	
	@ResponseBody
	@RequestMapping("returnback")
	public Result returnback(String id,String thyj,HttpSession session){
		Result result = new Result();
		try {
			result = sgtysbaService.returnback(id,thyj,session);
			result.setContent("退回成功！");
			result.setSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setContent("退回失败，请检查！");
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("getYbaByXmid")
	public Object getYbaByXmid(String xmid){
		return sgtysbaService.getYbaByXmid(xmid);
	}
	@ResponseBody
	@RequestMapping("selSgtbaFilesById")
	public Object selSgtbaFilesById(String sgtbaid){
		return sgtysbaService.selSgtbaFilesById(sgtbaid);
	}
	@ResponseBody
	@RequestMapping("selectHxXmmc")
	public Object selectHxXmmc(){
		return sgtysbaService.selectHxXmmc();
	}
	@ResponseBody
	@RequestMapping("selectGcfymcBySgtid")
	public Object selectGcfymcBySgtid(String xmid){
		return sgtysbaService.selectGcfymcBySgtid(xmid);
	}
	@ResponseBody
	@RequestMapping("selSgtysbamxById")
	public Object selSgtysbamxById(String sgtbaid){
		return sgtysbaService.selSgtysbamxById(sgtbaid);
	}
	@ResponseBody
	@RequestMapping("selectSgthxXmmc")
	public Object selectSgthxXmmc(){
		return sgtysbaService.selectSgthxXmmc();
	}
	
	@ResponseBody
	@RequestMapping("selThyj")
	public Result selThyj(String id){
		Result result = sgtysbaService.selThyj(id);
		return result;
	}
}
