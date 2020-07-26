package com.datanew.util.unalterable;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lustin on 2018/04/01
 * 此类不可更改
 */
public class HqlGen {

    public static Object typeReverse(Class type, Object val,String pattern){
        if(val==null)
            return null;
        String strVal=String.valueOf(val);
        if(strVal.trim().equals(""))
            return null;
        if(type==String.class){
            return strVal;
        }
        if(type==Date.class){
            try {
                return new SimpleDateFormat(pattern).parse(strVal);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(type==Integer.class){
            return Integer.valueOf(strVal);
        }
        if(type==BigDecimal.class){
            return new BigDecimal(strVal);
        }
        if(type==Long.class){
            return Long.valueOf(strVal);
        }
        return null;
    }
        public static String typeFormat(Class type,Object val,String pattern){
            if(val==null)
                return null;
            String strVal=String.valueOf(val);
            if(String.class==type){
                return strVal;
            }
            if(Date.class==type){
                return new SimpleDateFormat(pattern).format(val);
            }
            if(BigDecimal.class==type){
                return String.valueOf(val);
            }
            if(Integer.class==type){
                return String.valueOf(val);
            }
            if(Long.class==type){
                return String.valueOf(val);
            }
            return null;
        }


}
