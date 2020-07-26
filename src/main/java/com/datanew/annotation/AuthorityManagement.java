package com.datanew.annotation;

import java.lang.annotation.*;

/**
 * Created by Lustin on 2017/6/7.
 * controller层权限控制注解，表示该方法需要的用户权限，支持位运算表达式，排除了原有位运算的非操作，替换为!
 * 样例	@AuthorityManagement({"3306","!3305","3303&3304","3307^3308"})
 * 以上数组里有四个表达式，满足一个即可放行，表示的意思分别是
 * 用户拥有3306权限,用户没有3305权限，用户同时拥有3303权限和3304权限,用户只能拥有3307和3308权限中的一个
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorityManagement {
    String[] value();
}
