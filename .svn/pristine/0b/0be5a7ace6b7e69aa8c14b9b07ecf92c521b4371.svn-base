package com.datanew.util;

import com.datanew.annotation.AuthorityManagement;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 获取spring容器，以访问容器中定义的其他bean
 */
public class MyApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    private static final Map<Method,String[]> AUTHORITIESMAP = new HashMap();

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
        MyApplicationContextUtil.applicationContext = applicationContext;
        Map controllers = applicationContext.getBeansWithAnnotation(Controller.class);
        for (Map.Entry en : (Set<Map.Entry>) controllers.entrySet()) {
            for(Method method:en.getValue().getClass().getMethods()){
                if(method.isAnnotationPresent(AuthorityManagement.class)){
                    String[] authorities=method.getAnnotation(AuthorityManagement.class).value();
                    AUTHORITIESMAP.put(method,authorities);
                }

            }
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象
     * Object 一个以所给名字注册的bean的实例 (service注解方式，自动生成以首字母小写的类名为bean name)
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    public static String[] getMethodSecope(Method method) {
        return AUTHORITIESMAP.get(method);
    }


}