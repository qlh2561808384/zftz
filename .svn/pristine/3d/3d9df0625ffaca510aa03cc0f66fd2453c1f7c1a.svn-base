package com.datanew.util;

import org.apache.poi.ss.formula.functions.T;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author inRush
 * @date 2019/4/25
 **/
public class JavaBeanUtil {
    private static String[] dateFormatStringArray = new String[]{
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH.mm.ss"
    };

    /**
     * 将一个 JavaBean 对象转化为一个  Map
     *
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException    如果分析类属性失败
     * @throws IllegalAccessException    如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Map convertBean(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }


    /**
     * 将map的value值转为实体类中字段类型匹配的方法
     *
     * @param value
     * @param fieldTypeClass
     * @return
     */
    private static Object convertValType(Object value, Class<?> fieldTypeClass, int dateFormatIndex) throws ParseException {
        if (value == null || value.equals("")) {
            return null;
        }
        Object retVal;

        if (Long.class.getName().equals(fieldTypeClass.getName())
                || long.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Long.parseLong(value.toString());
        } else if (Integer.class.getName().equals(fieldTypeClass.getName())
                || int.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Integer.parseInt(value.toString());
        } else if (Float.class.getName().equals(fieldTypeClass.getName())
                || float.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Float.parseFloat(value.toString());
        } else if (Double.class.getName().equals(fieldTypeClass.getName())
                || double.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Double.parseDouble(value.toString());
        } else if (Date.class.getName().equals(fieldTypeClass.getName())) {
            try {
                if (dateFormatIndex >= dateFormatStringArray.length) {
                    retVal = null;
                } else {
                    DateFormat format = new SimpleDateFormat(dateFormatStringArray[dateFormatIndex]);
                    retVal = format.parse(value.toString());
                }
            } catch (ParseException e) {
                if (dateFormatIndex >= dateFormatStringArray.length) {
                    throw e;
                }
                retVal = convertValType(value, fieldTypeClass, dateFormatIndex + 1);
            }
        } else if (BigDecimal.class.getName().equals(fieldTypeClass.getName())) {
            retVal = new BigDecimal(value.toString());
        } else {
            retVal = String.valueOf(value);
        }
        return retVal;
    }

    /**
     * 根据给定对象类匹配对象中的特定字段
     *
     * @param clazz
     * @param fieldName
     * @return
     */
    private static Field getClassField(Class<?> clazz, String fieldName) {
        if (Object.class.getName().equals(clazz.getName())) {
            return null;
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        Class<?> superClass = clazz.getSuperclass();    //如果该类还有父类，将父类对象中的字段也取出
        if (superClass != null) {                        //递归获取
            return getClassField(superClass, fieldName);
        }
        return null;
    }

    public interface PropNameConverter {
        String convert(String originName);
    }

    /**
     * 大写转换器
     */
    public static class UppercaseConverter implements PropNameConverter {
        public String convert(String originName) {
            return originName.toUpperCase();
        }
    }

    public static class CamelCaseConverter implements PropNameConverter {

        public String convert(String originName) {
            StringBuilder sb = new StringBuilder(originName);
            int temp = 0;//定位
            if (!originName.contains("_")) {
                for (int i = 0; i < originName.length(); i++) {
                    if (Character.isUpperCase(originName.charAt(i))) {
                        sb.insert(i + temp, "_");
                        temp += 1;
                    }
                }
            }
            return sb.toString().toUpperCase();
        }
    }

    /**
     * 默认转换器，不做转换
     */
    public static class DefaultConverter implements PropNameConverter {
        public String convert(String originName) {
            return originName;
        }
    }


    /**
     * 将一个 Map 对象转化为一个 JavaBean
     *
     * @param type               要转化的类型
     * @param map                包含属性值的 map
     * @param classPropConverter 类属性名称转换器
     * @return 转化出来的 JavaBean 对象
     * @throws IntrospectionException    如果分析类属性失败
     * @throws IllegalAccessException    如果实例化 JavaBean 失败
     * @throws InstantiationException    如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    @SuppressWarnings("rawtypes")
    public static <T> T convertMap(Class type, Map map, PropNameConverter classPropConverter)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException, ParseException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        Object obj = type.newInstance(); // 创建 JavaBean 对象

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = classPropConverter.convert(descriptor.getName());

            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = map.get(propertyName);
                Object[] args = new Object[1];
                args[0] = convertValType(value, descriptor.getPropertyType(), 0);
                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        return (T) obj;
    }
}
