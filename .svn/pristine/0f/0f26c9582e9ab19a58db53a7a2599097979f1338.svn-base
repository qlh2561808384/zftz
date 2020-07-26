package com.datanew.annotation;

import java.lang.annotation.*;

/**
 * 
  * @ClassName: MethodDescription
  * @Description: 自定义注解--必须菜单权限
  * @author hjz
  * @date 2016年9月29日 下午2:10:14
  *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface LoginRequired {
    String value() ;  //调用此方法必须的权限id  如有多个则用 ,分割
}
