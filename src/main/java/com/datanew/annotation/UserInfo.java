package com.datanew.annotation;

import java.lang.annotation.*;

/**
 * @author inRush
 * @date 2019/3/18
 **/
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserInfo {
    String value() default "";
}
