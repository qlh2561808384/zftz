package com.datanew.util;

import com.alibaba.druid.util.DruidPasswordCallback;
import org.hibernate.cfg.Environment;

import java.util.Properties;

/**
 * @author kedou
 * @create 2018-03-22 14:30
 * @desc
 **/
public class DBPasswordCallback extends DruidPasswordCallback {

    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String password = ConfigureParser.getPropert("jdbc.password");
       try {
            if("true".equals(ConfigureParser.getPropert("jdbc.encrypt","false"))){
                DesUtil des2 = new DesUtil("www.datanew.com");// 自定义密钥
                password = des2.decrypt(password);
                //解密密码,添加回去读取连接数据库
                setPassword(password.toCharArray());
            }
        } catch (Exception e) {

        }



    }
}
