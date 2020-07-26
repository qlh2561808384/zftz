package com.datanew.util;

import java.util.concurrent.Semaphore;

public class StaticData {

    //登录的用户应用信息
    public static final String LOGINUSER = "LOGINUSER";
    //登录的用户信息
    public static final String LOGINUSERINFO = "LOGINUSERINFO";
    //登录的用户对应的菜单权限
    public static final String USERMENUS = "USERMENUS";
    //登录的用户对应的菜单权限
    public static final String USERMENUSSTR = "USERMENUSSTR";

    //登录的用户对应的按钮权限
    public static final String USERBUTTONS = "USERBUTTONS";
    //登录的用户对应的按钮权限
    public static final String USERBUTTONSSTR = "USERBUTTONSSTR";

    //行政区划
  	public static final String XZQH="XZQH";
    
  	// 信号量，线程池最大可用连接，防止高并发死锁
    public static Semaphore signs = new Semaphore(
            Integer.parseInt(ConfigureParser.getPropert("hibernate.maxActive")));
}
