package com.datanew.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Lustin on 2017/6/9.
 */
public class AuthorityManagerInterceptor extends AbstractAuthorityManageInterceptor {
    @Override
    public void afterFailer(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().write("test111111111111111111");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] getAuth(HttpServletRequest request, HttpServletResponse response) {
        return new String[]{"3301","3302","3303","3304"};
    }
}
