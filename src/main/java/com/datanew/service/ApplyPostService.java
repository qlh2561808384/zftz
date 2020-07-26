package com.datanew.service;


import java.util.List;

import javax.servlet.http.HttpSession;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.YhglDmYygw;

//应用岗位配置
public  interface  ApplyPostService {
	/**
	 * 获取应用对应的岗位信息
	 * swj
	 * @param page
	 * @param id
	 * @return Pages
	 */
	Pages getAppylToPostInfo(Pages page,String id);
		
	/**
	 * 获取应用树
	 * swj
	 * @return List
	 */
	List getApplyTree();
	
	/**
	 * 保存或者更新应用对应岗位信息
	 * swj
	 * 
	 */
	Result saveAppylToPost(YhglDmYygw yhgldmyygw,String id,Result result,HttpSession session);
	
	/**
	 * 删除应用对应岗位信息
	 * swj
	 * @param guid
	 * @param result
	 * @return
	 */
	Result delAppylToPost(Long id,Result result);
}
