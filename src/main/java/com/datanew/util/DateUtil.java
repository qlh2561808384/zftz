package com.datanew.util;

import java.sql.Timestamp;

public class DateUtil {
     public static Timestamp getCurrentTimeStamp(){
    	 Timestamp d = new Timestamp(System.currentTimeMillis());   
    	 return d;
     }
}
