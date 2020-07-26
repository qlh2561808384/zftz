package com.datanew.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.XtglGgMenu;

public interface UserService {
	
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 用户登录
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 * @return: Result
	 */
	Result login(String username, String password, HttpSession session);

  

	/**
	 * 
	 * @Title: updatePassword 
	 * @Description: 更新用户密码
	 * @param oldpassword
	 * @param newpassword
	 * @param session
	 * @return
	 * @return: Result
	 */
	Result updatePassword(String oldpassword, String newpassword, HttpSession session);
	
	

	/**
	 * 保存用户首页桌面设置
	 * @param html
	 * @param request
	 * @param result
	 */
    void saveDesktop(String html, HttpServletRequest request, Result result);

	/**
	 * 获得桌面信息
	 * @param request
	 * @return
	 */
	Result getDesktop(HttpServletRequest request);
    /***
     * @method      caslogin
     * @author      kedou
     * @version     
     * @see
      * @param request
 * @param session
     * @return      com.datanew.dto.Result
     * @exception   
     * @date        2018/7/19 14:42
     */
//    Result caslogin(HttpServletRequest request, HttpSession session);
	Result caslogin(HttpServletRequest request, HttpSession session);
    
    Result orglogin(HttpServletRequest request);
    /**
	 * 获取岗位信息树
	 * @param request
	 * @return
	 */
    List getPostTree();
    /**
	 * 获得菜单信息树
	 * @param request
	 * @return
	 */
	List getMenuTree();
	
	/**
	 * 
	 * @Title: findPostMenu 
	 * @Description: 查询岗位对功能
	 * @param postId
	 * @param result
	 * @return: void
	 */
	void getPostMenu(String postId, Result result);
	
	/**
	 * 
	 * @Title: savePostMenu 
	 * @Description: 保存岗位对功能
	 * @param postId
	 * @param menus
	 * @param result
	 * @return: void
	 */
	void savePostMenu(String postId, String menus, Result result);
	
	String getUserName(HttpSession session);
	
	Pages getMenuInformation(Pages pages);
	
	Result saveMenu(XtglGgMenu menu, Result result, int stuts);
	
	Result delMenu(String menuid, Result result);
	
	/**
	 * 获得归口树
	 * @param request
	 * @return
	 */
	List getGkTree();
	
	/**
	 * 
	 * @Title: findPostMenu 
	 * @Description: 查询岗位对归口
	 * @param postId
	 * @param result
	 * @return: void
	 */
	void getPostGk(String postId, Result result);
	
	/**
	 * 
	 * @Title: savePostMenu 
	 * @Description: 保存岗位对功能
	 * @param postId
	 * @param gks
	 * @param result
	 * @return: void
	 */
	void savePostToGk(String postId, String gks, Result result);
    /***
     * @method      getUserDaiban
     * @author      kedou
     * @version
     * @see           获取用户待办信息
      * @param request
     * @return      java.util.List
     * @exception
     * @date        2019/5/22 9:35
     */
    List getUserDaiban(HttpServletRequest request,HttpSession session);



	Object getButtons(HttpSession session);

	
}
