package com.datanew.action;


import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.YhglDmYygw;
import com.datanew.service.ApplyPostService;

@Controller
@RequestMapping("/appyltopost")
public class ApplyPostController {

	@Autowired
	private ApplyPostService applypostservice;
	
	@RequestMapping("getAppylToPostInfo")
	@ResponseBody
	public Pages getAppylToPostInfo(Pages page,String id){
		return applypostservice.getAppylToPostInfo(page, id);
	}
	
	@RequestMapping("getApplyTree")
	@ResponseBody
	public List getApplyTree(){
		return applypostservice.getApplyTree();
	}
	
	@RequestMapping("saveappyltopost")
	@ResponseBody
	public Object saveAppylToPost(YhglDmYygw yhgldmyygw,String id,Result result,HttpSession session){
		return applypostservice.saveAppylToPost(yhgldmyygw, id, result,session);
	}
	
	@RequestMapping("delAppylToPost")
	@ResponseBody
	public Object delAppylToPost(Long id,Result result){
		return applypostservice.delAppylToPost(id,result);
	}
	
	
}
