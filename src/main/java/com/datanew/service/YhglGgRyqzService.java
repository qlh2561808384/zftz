package com.datanew.service;


import java.util.List;

import javax.servlet.http.HttpSession;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.XtglDmYy;
import com.datanew.model.YhglDmYygw;
import com.datanew.model.YhglGgRyqz;

//应用岗位配置
public  interface  YhglGgRyqzService {
	/**
	 * 获取应用对应的岗位信息
	 * swj
	 * @param page
	 * @param id
	 * @return Pages
	 */
	Pages getAppylToRyqzInfo(Pages page);
		
	/**
	 * 获取应用树
	 * swj
	 * @return List
	 */
	List getApplyTree();
	
	/**
	 * 保存应用对应岗位信息
	 * swj
	 * 
	 */
	Result saveRyqz(YhglGgRyqz yhglggryqz,Result result,HttpSession session);
	
	/**
	 * 删除应用对应岗位信息
	 * swj
	 * @param guid
	 * @param result
	 * @return
	 */
	Result delRyqz(Long id,Result result);
	
	/**
	 * 获取单位，角色或者指定用户树
	 * swj
	 * @return List
	 */
	List getchoiceApplyTree(String id);
	
	/**
	 * 获取单位，角色或者指定用户树
	 * swj
	 * @return List
	 */
	List getchoiceApplyTrees(String id);
	
	/**
	 * 保存应用对应岗位信息
	 * swj
	 * 
	 */
	Result saveEditRyqz(String guid,String mc,String fzlx ,String fzlxval,String ms,String zt,String fzlxsql,Result result,HttpSession session);
	
	/**
	 * 获取人员群组树
	 * swj
	 * @return List
	 */
	List getRyqzTree();
	
	/**
	 * 获取事项树
	 * swj
	 * @return List
	 */
	List getsxTree();
	
	/**
	 * 获取审批流程信息
	 * swj
	 * @param page
	 * @param id
	 * @return Pages
	 */
	Pages getSplcInfo(Pages page, String id);
	
	/**
	 * 保存或者更新应用信息
	 * swj
	 * 
	 */
	Result saveSplcInfo(String  id,String dqhjbm,String xyhjbm,String dqhjmc,String czlx,String sprfzid,Result result,HttpSession session);
	
	/**
	 * 获取人员群组树
	 * swj
	 * @return List
	 */
	List getsxList();
}
