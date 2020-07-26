package com.datanew.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.datanew.util.MyApplicationContextUtil;
import com.datanew.util.StringBitwiseOperation;

/**
 * Created by Lustin on 2017/6/8.
 */
public abstract class AbstractAuthorityManageInterceptor implements HandlerInterceptor {
    public abstract String[] getAuth(HttpServletRequest request,HttpServletResponse response);
    public abstract void afterFailer(HttpServletRequest request,HttpServletResponse response);
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        if(o instanceof HandlerMethod){
            try{
                Method method=((HandlerMethod) o).getMethod();
                String[] authorities=MyApplicationContextUtil.getMethodSecope(method);
                if(authorities==null){
                    return true;
                }
                //System.out.println(String.format("请求带权限方法%s \r\n该方法要求权限为:%s",method,Arrays.toString(authorities)));
                String[] userAuth=getAuth(httpServletRequest,httpServletResponse);
                if(userAuth==null||userAuth.length==0){
                    //System.out.println("用户权限为空,权限验证失败");
                    afterFailer(httpServletRequest, httpServletResponse);
                    return false;
                }
                Set<String> userAuthSet= new HashSet<String>(Arrays.asList(userAuth));
                boolean result=false;
                for(String au:authorities){
                    String auTrim=au.trim();
                    if("".equals(auTrim))continue;
                    if(userAuthSet.contains(au))
                    {
                        result =true;
                        break;
                    }
                    String curAu="";
                    String authoritiesExpression="";
                    for(int i=0;i<auTrim.length();i++){
                        if(auTrim.charAt(i)=='&'){
                            authoritiesExpression+=String.format("%s&",userAuthSet.contains(curAu)?"1":"0");
                            curAu="";
                        }else if(auTrim.charAt(i)=='|'){
                            authoritiesExpression+=String.format("%s|",userAuthSet.contains(curAu)?"1":"0");
                            curAu="";
                        }else if(auTrim.charAt(i)=='^'){
                            authoritiesExpression+=String.format("%s^",userAuthSet.contains(curAu)?"1":"0");
                            curAu="";
                        }else if(auTrim.charAt(i)=='!'){
                            if(i==0||"".equals(curAu)){
                                authoritiesExpression+=String.format("!");
                            }else{
                                authoritiesExpression+=String.format("%s!",userAuthSet.contains(curAu)?"1":"0");
                            }
                            curAu="";
                        }else {
                            curAu+=String.valueOf(auTrim.charAt(i));
                            if(i==auTrim.length()-1){
                                authoritiesExpression+=userAuthSet.contains(curAu)?"1":"0";
                            }
                        }
                    }
                    if(StringBitwiseOperation.conversion(authoritiesExpression)==1){
                        result =true;
                    }
                }
                if(!result){
                    afterFailer(httpServletRequest, httpServletResponse);
                }
                //System.out.println(String.format("权限验证%s",result?"通过":"失败"));
                return result;
            }catch (Exception e){
                e.printStackTrace();

            }
        }
        return true;


    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {


    }

}
