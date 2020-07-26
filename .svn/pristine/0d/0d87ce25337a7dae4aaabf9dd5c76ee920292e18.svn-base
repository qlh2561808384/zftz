package com.datanew.aop;

import com.datanew.annotation.UserInfo;
import com.datanew.util.StaticData;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author inRush
 * @date 2019/3/18
 **/
@Component
public class UserInfoArgumentResolver implements HandlerMethodArgumentResolver {
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(UserInfo.class);
    }

    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
//        String value = methodParameter.getParameterAnnotation(UserInfo.class).value();
        return nativeWebRequest.getAttribute(StaticData.LOGINUSER, NativeWebRequest.SCOPE_SESSION);
    }
}
