package com.datanew.annotation;

import java.lang.annotation.*;

/**
 * @author inRush
 * @date 2019/6/18
 **/
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface SessionData {
    String value();
}
