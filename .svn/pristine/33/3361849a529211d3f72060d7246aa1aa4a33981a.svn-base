package com.datanew.filter;

import com.datanew.sso.client.enums.CharsetEnum;
import com.datanew.sso.client.util.EmptyVeify;
import com.datanew.sso.client.util.HttpUtil;
import com.datanew.util.ConfigureParser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @ClassName: SSOBranchFilter
 * @Description: cas，org sso登录分配
 * @author ghost.he
 * @date
 *
 */
public class SSOBranchFilter implements Filter {

    private static String sso_mode_key = null;

    private static String sso_mode_lczorg = null;

    private static String sso_mode_sxcas = null;

    private static String sso_mode_sxcas_loginurl = null;

    private static String sso_mode_lczorg_loginurl = null;

    private static String sso_mode_redirectto_key = null;
    
    private static String sso_mode_accountId = null;
    
    private static String sso_mode_appCode = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        sso_mode_key = ConfigureParser.getPropert("sso.mode.key");
        sso_mode_lczorg = ConfigureParser.getPropert("sso.mode.lczorg");
        sso_mode_sxcas = ConfigureParser.getPropert("sso.mode.sxcas");
        sso_mode_sxcas_loginurl = ConfigureParser.getPropert("sso.mode.sxcas.loginurl");
        sso_mode_lczorg_loginurl = ConfigureParser.getPropert("sso.mode.lczorg.loginurl");
        sso_mode_redirectto_key = ConfigureParser.getPropert("sso.mode.redirectto.key");
        sso_mode_accountId = ConfigureParser.getPropert("sso.mode.accountId");
        sso_mode_appCode = ConfigureParser.getPropert("sso.mode.appCode");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        boolean isRedirect = false;
        if(!EmptyVeify.string(sso_mode_key)){
            String mode_val = httpServletRequest.getParameter(sso_mode_key);
            if(!EmptyVeify.string(mode_val)){
                String loginurl = null;
                if(mode_val.equals(sso_mode_lczorg)){
                    loginurl = sso_mode_lczorg_loginurl;
                }else if(mode_val.equals(sso_mode_sxcas)){
                    loginurl = sso_mode_sxcas_loginurl;
                }
                if(!EmptyVeify.string(loginurl)){
                    Enumeration<String> paramNames = httpServletRequest.getParameterNames();
                    Map<String, String> paramMap = new HashMap();
                    String accountId = null;
                    String appCode = null;
                    while(paramNames.hasMoreElements()){
                        String key = paramNames.nextElement();
                        if(key.equals(sso_mode_accountId)){
                        	accountId = httpServletRequest.getParameter(key);
                        }else if(key.equals(sso_mode_appCode)){
                        	appCode = httpServletRequest.getParameter(key);
                        }else if(!key.equals(sso_mode_key)){
                            paramMap.put(key, httpServletRequest.getParameter(key));
                        }
                    }
                    String redirectto = httpServletRequest.getRequestURI();
                    redirectto = HttpUtil.urlAddParams(redirectto, paramMap);
                    paramMap.clear();
                    paramMap.put(sso_mode_redirectto_key, URLEncoder.encode(redirectto, CharsetEnum.UTF_8.getName()));
                    paramMap.put(sso_mode_accountId, accountId);
                    paramMap.put(sso_mode_appCode, appCode);
                    loginurl = HttpUtil.urlAddParams(loginurl, paramMap);
                    loginurl = HttpUtil.domainAddPath(httpServletRequest.getContextPath(), loginurl);
                    httpServletResponse.sendRedirect(loginurl);
                    isRedirect = true;
                }
            }
        }
        if(!isRedirect){
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
