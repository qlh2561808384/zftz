package com.datanew.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datanew.dao.BaseDao;
import com.datanew.model.YhglYwYhyy;
import com.datanew.util.MyApplicationContextUtil;
import com.datanew.util.StaticData;
import com.datanew.util.StringUtil;


/**
 * 
  * @ClassName: SecurityFilter
  * @Description: 登录拦截
  * @author hjz
  * @date 2016年4月21日 下午4:51:24
  *
 */
public class SecurityFilter implements Filter {
	@SuppressWarnings("unused")
	private FilterConfig filterCon = null;

	public void init(FilterConfig config) throws ServletException {
		filterCon = config;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {


		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		StringBuffer url = httpRequest.getRequestURL();
		
		/*if (userinfo == null) {
			httpResponse.sendRedirect(httpRequest.getContextPath()
							+ "/login.html");
		} else {
			filterChain.doFilter(request, response);
		}*/
		if ((url.indexOf(".do")!=-1&&url.indexOf("user/login.do")==-1&&url.indexOf("user/checkDoin.do")==-1)||url.indexOf(".jsp")!=-1||(url.indexOf(".html")!=-1&&url.indexOf("login.html")==-1)) {
			YhglYwYhyy sysUser =  (YhglYwYhyy) httpRequest.getSession().getAttribute("LOGINUSER");

			if (sysUser == null) {
				if((url.indexOf(".do")!=-1)){
					response.setContentType("application/json; charset=utf-8");
					response.setCharacterEncoding("UTF-8");

					String userJson = "{success:false,content:'请先登录'}";
					OutputStream out = response.getOutputStream();
					out.write(userJson.getBytes("UTF-8"));
					out.flush();
				}else{
					httpResponse.sendRedirect(httpRequest.getContextPath()
							+ "/login.html?backurl=" + URLEncoder.encode(url.toString(), "utf-8"));
				}


			} else {
				filterChain.doFilter(request, response);
			}
		}else{
			filterChain.doFilter(request, response);
		}
	}

	public void destroy() {
		filterCon = null;
	}
}
