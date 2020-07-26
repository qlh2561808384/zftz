package com.datanew.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.dto.SSOLoginDTO;
import com.datanew.model.XtglGgMenu;
import com.datanew.service.UserService;
import com.datanew.util.StaticData;
import com.datanew.util.StringUtil;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@ResponseBody
	@RequestMapping("updatePassword")
	public Object updatePassword(@RequestParam String oldpassword, @RequestParam String newpassword, HttpSession session){
		Result result=userService.updatePassword(oldpassword,newpassword,session);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("login")
	//@AuthorityManagement({"3306"})
	public Object login(@RequestParam String username, @RequestParam String password, HttpSession session){
		Result result=userService.login(username,password,session);
		return result;
	}

//	@RequestMapping("caslogin")
//	public void caslogin(HttpServletRequest request , HttpSession session,HttpServletResponse response ) throws IOException {
//		Result result=userService.caslogin(request,session);
//		if(result.isSuccess()){
//			response.sendRedirect("../index.jsp");
//		}else{
//			response.sendRedirect("../login.html");
//		}
//
//	}
	@RequestMapping("caslogin")
	public void caslogin(HttpServletRequest request , HttpSession session,HttpServletResponse response ) throws IOException {

		Result result=userService.caslogin(request,session);
		if(result.isSuccess()){
			String redirectTo = "../index.jsp";
			if(result.getContent() != null){
				SSOLoginDTO ssoLoginDTO = (SSOLoginDTO)result.getContent();
				if(!StringUtil.isblank(ssoLoginDTO.getRedirect_to())){
					redirectTo = ssoLoginDTO.getRedirect_to();
				}
			}
		
//			response.setHeader("P3P", "CP=\"CAO PSA OUR\"");
//			response.setHeader("P3P","CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");
//			response.addHeader("P3P", "CP=\"CAO PSA OUR\"");
//			response.addHeader("P3P","CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");
			response.sendRedirect(redirectTo);
		
		}else{
			response.sendRedirect("../login.html");
		}

	}

	@RequestMapping("orglogin")
	public void orglogin(HttpServletRequest request , HttpSession session,HttpServletResponse response ) throws IOException {
		Result result=userService.orglogin(request);
		if(result.isSuccess()){
			String redirectTo = "../index.jsp";
			if(result.getContent() != null){
				SSOLoginDTO ssoLoginDTO = (SSOLoginDTO)result.getContent();
				if(!StringUtil.isblank(ssoLoginDTO.getRedirect_to())){
					redirectTo = ssoLoginDTO.getRedirect_to();
				}
			}
			response.sendRedirect(redirectTo);
		}else{
			response.sendRedirect("../login.html");
		}

	}

	@RequestMapping("loginout")
	public Object loginout(HttpServletResponse response, HttpSession session) throws IOException {
		session.invalidate();
		response.sendRedirect("../login.html");
		return null;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping("getUserInfo")
	public Object getUserInfo(HttpSession session){
		Result result= new Result();
		Map map = new HashMap();
		map.put("loginuser", session.getAttribute(StaticData.LOGINUSER));
		map.put("loginuserinfo", session.getAttribute(StaticData.LOGINUSERINFO));
//		map.put("userbuttons", session.getAttribute(StaticData.USERBUTTONS));
		map.put("usermenus", session.getAttribute(StaticData.USERMENUS));
		result.setSuccess(true);
		result.setContent(map);
		return result;
	}
	


	@ResponseBody
	@RequestMapping("saveDesktop")
	public Object saveDesktop(String html, HttpServletRequest request){
		Result result = new Result();
		userService.saveDesktop(html,request, result);;
		return result;
	}

	@ResponseBody
	@RequestMapping("getUserDaiban")
	public Object getUserDaiban( HttpServletRequest request,HttpSession session){
		List list  = userService.getUserDaiban(request,session);
		return list;
	}

	@ResponseBody
	@RequestMapping("getDesktop")
	public Object getDesktop(HttpServletRequest request){
		return userService.getDesktop(request);
	}
	
	@ResponseBody
    @RequestMapping("getPostTree")
    public List getPostTree(HttpSession session) {
        return userService.getPostTree();
    }

    @ResponseBody
    @RequestMapping("getMenuTree")
    public List getMenuTree(HttpSession session) {
        return userService.getMenuTree();
    }
    
    
    @ResponseBody
	@RequestMapping("getPostMenu")
	public Object getPostMenu(String postId) {
		Result result = new Result();
		userService.getPostMenu(postId, result);
		return result;
	}
    
    @ResponseBody
	@RequestMapping("savePostMenu")
	public Object savePostMenu(String postId, String menus){
		Result result = new Result();
		userService.savePostMenu(postId, menus, result);
		return result;
	}

    @ResponseBody
	@RequestMapping(value="/getUserName",produces = "text/plain;charset=utf-8")
	public String getUserName(HttpSession session){
		return userService.getUserName(session);
	}
    
    @RequestMapping("/getMenuInformation")
	@ResponseBody
	public Pages getMenuInformation(Pages pages){
		return userService.getMenuInformation(pages);
	}
    
    @ResponseBody
	@RequestMapping("saveMenu")
	public Object saveMenu(XtglGgMenu menu, Result result, int stuts){
		userService.saveMenu(menu, result, stuts);
		return result;
	}
    
    @ResponseBody
	@RequestMapping("delMenu")
	public Object delMenu(String menuid, Result result){
		
		userService.delMenu(menuid, result);
		return result;
	}
    
    @ResponseBody
    @RequestMapping("getGkTree")
    public List getGkTree(HttpSession session) {
        return userService.getGkTree();
    }
    
    
    @ResponseBody
	@RequestMapping("getPostGk")
	public Object getPostGk(String postId) {
		Result result = new Result();
		userService.getPostGk(postId, result);
		return result;
	}
    
    @ResponseBody
	@RequestMapping("savePostToGk")
	public Object savePostToGk(String postId, String gks){
		Result result = new Result();
		userService.savePostToGk(postId, gks, result);
		return result;
	}
    
    @ResponseBody
	@RequestMapping("getButtons")
	public Object getButtons(HttpSession session){
		return userService.getButtons(session);
	}
    
    @ResponseBody
	@RequestMapping("checkDoin")
	public Object checkDoenter(@RequestParam String sessionid, @RequestParam String code, HttpSession session){
		Result result = new Result();
		String vsid=(String)session.getAttribute("sid");
		String vcode=(String)session.getAttribute("dlcode");
		System.out.println(vsid+"---45sessionid=="+sessionid);
		System.out.println(vcode+"---code =="+code);
		if(vsid==null||vcode==null){
			result.setSuccess(false);
			result.setContent("未登录");
			return result;
		}
		System.out.println(vsid+"---sessionid=="+sessionid);
		System.out.println(vcode+"---code =="+code);
		if(sessionid!=null&&sessionid!=""&&code!=null&code!=""){
			if(vsid.equals(sessionid)&&vcode.equals(code)){
				result.setSuccess(true);
				result.setContent("验证成功");
			}else{
				result.setSuccess(false);
				result.setContent("验证失败,非法访问");
			}
		}else{
			result.setSuccess(false);
			result.setContent("验证失败,参数有误");
		}
		
		
		return result;
	}
}
