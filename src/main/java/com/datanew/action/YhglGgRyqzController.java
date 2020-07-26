package com.datanew.action;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.XtglDmYy;
import com.datanew.model.YhglDmYygw;
import com.datanew.model.YhglGgRyqz;
import com.datanew.service.YhglGgRyqzService;

@Controller
@RequestMapping("/yhglggryqz")
public class YhglGgRyqzController {

	@Autowired
	private YhglGgRyqzService yhglggryqzservice;
	
	@RequestMapping("getAppylToRyqzInfo")
	@ResponseBody
	public Pages getAppylToRyqzInfo(Pages page){
		return yhglggryqzservice.getAppylToRyqzInfo(page);
	}
	
	@RequestMapping("getApplyTree")
	@ResponseBody
	public List getApplyTree(){
		return yhglggryqzservice.getApplyTree();
	}
	
	@RequestMapping("saveRyqz")
	@ResponseBody
	public Object saveRyqz(YhglGgRyqz yhglggryqz,Result result,HttpSession session){
		return yhglggryqzservice.saveRyqz(yhglggryqz,  result,session);
	}
	
	@RequestMapping("delRyqz")
	@ResponseBody
	public Object delRyqz(Long id,Result result){
		return yhglggryqzservice.delRyqz(id,result);
	}
	
	
	@RequestMapping("getchoiceApplyTree")
	@ResponseBody
	public List getchoiceApplyTree(String id){
		return yhglggryqzservice.getchoiceApplyTree(id);
	}
	
	@RequestMapping("getchoiceApplyTrees")
	@ResponseBody
	public List getchoiceApplyTrees(String id){
		return yhglggryqzservice.getchoiceApplyTrees(id);
	}
	
	@RequestMapping("saveEditRyqz")
	@ResponseBody
	public Object saveEditRyqz(String guid,String mc,String fzlx ,String fzlxval,String ms,String zt,String fzlxsql,Result result,HttpSession session){
		return yhglggryqzservice.saveEditRyqz(guid, mc, fzlx, fzlxval, ms, zt,fzlxsql, result,session);
	}
	
	@RequestMapping("/getRyqzTree")
	@ResponseBody
	public List getRyqzTree(){
		return yhglggryqzservice.getRyqzTree();
	}
	
	@RequestMapping("getsxTree")
	@ResponseBody
	public List getsxTree(){
		return yhglggryqzservice.getsxTree();
	}
	
	
	/**
	 * 获取审批流程信息
	 * swj
	 * @param page
	 * @param id
	 * @return Pages
	 */
	@RequestMapping("/getSplcInfo")
	@ResponseBody
	public Pages getSplcInfo(Pages page, String id){
		return yhglggryqzservice.getSplcInfo(page, id);
	}
	
	@RequestMapping("saveSplcInfo")
	@ResponseBody
	public Object saveSplcInfo(String  id,String dqhjbm,String xyhjbm,String dqhjmc,String czlx,String sprfzid,Result result,HttpSession session){
		return yhglggryqzservice.saveSplcInfo(id,dqhjbm, xyhjbm, dqhjmc, czlx, sprfzid, result, session);
	}
	
	@RequestMapping("getsxList")
	@ResponseBody
	public List getsxList(){
		return yhglggryqzservice.getsxList();
	}
	
}
