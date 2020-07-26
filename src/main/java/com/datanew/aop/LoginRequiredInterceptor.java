package com.datanew.aop;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.datanew.annotation.LoginRequired;
import com.datanew.dto.LoginUser;
import com.datanew.util.StaticData;

/**
 * 
 * @ClassName: LoginRequiredInterceptor
 * @Description: 自定义方法拦截器--拦截未包含指定菜单权限的请求
 * @author hjz
 * @date 2016年9月29日 下午2:14:46
 *
 */
public class LoginRequiredInterceptor implements HandlerInterceptor {

	/**
	 * 在请求处理之前进行调用
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			LoginRequired  loginRequired = ((HandlerMethod) handler).getMethodAnnotation(LoginRequired.class);
			String menus = loginRequired.value();
			if (loginRequired == null || "".equals(menus)){
				return true;
			}else {
				String[] menuArr = menus.split(",");
				HttpSession session = request.getSession();
				LoginUser loginUser =(LoginUser) session.getAttribute(StaticData.LOGINUSER);
				for (String str : menuArr) {
					//如果用户权限中没有包含指定的权限id，则直接返回false
					if(!loginUser.getMenus().contains(","+str+",")){
						//如果为ajax请求，则返回对应的json格式数据，如果不是，则直接跳转到错误页面
						if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
							response.setContentType("text/html;charset=UTF-8");  
					        PrintWriter out = response.getWriter();  
					  
					        out.println("seccess");//返回的字符串数据  
						}else{
							response.sendRedirect("login.jsp");
						}
						return false;
					}
				}
				return true;
			}
		} else
			return true;
	}

	/**
	 * preHandle 方法的返回值为true 时才能被调用。 在DispatcherServlet 进行视图返回渲染之前被调用
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * preHandle 方法的返回值为true 时才会执行。 在DispatcherServlet 渲染了对应的视图之后执行
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
