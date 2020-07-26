package com.datanew.filter;

import com.caucho.hessian.client.HessianProxyFactory;
import com.datanew.dao.BaseDao;
import com.datanew.model.YhglYwYhyy;
import com.datanew.util.ConfigureParser;
import com.datanew.util.SpringContextUtil;
import com.datanew.util.StaticData;
import com.datanew.util.StringUtil;
import com.gsoft.modules.ums.service.UserMapperHessianService;
import org.hibernate.Session;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author kedou
 * @create 2018-06-12 21:10
 * @see
 **/
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = -7549277807214892918L;

    public LoginServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // _const_cas_assertion_是CAS中存放登录用户名的session标志
            //获取CAS服务端设置的用户信息
            Object object = request.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);

            if (object != null) {
                //获取用户信息
                Assertion assertion = (Assertion) object;
                AttributePrincipal attributePrincipal = assertion.getPrincipal();   //获取AttributePrincipal对象，这是客户端对象

                String name = attributePrincipal.getName();
                Map<String,Object> attributes = attributePrincipal.getAttributes();//获取属性值，为一个Map类型。
                //获取到的name实际上是平台（CAS认证）的用户信息，需要进一步通过远程接口调用，拿到对应平台自身的账号信息
                System.out.println("开始hessian调用");
                HessianProxyFactory factory = new HessianProxyFactory();
                String url = ConfigureParser.getPropert("CASSERVERURL")+"/workportal-war/hessian/com.gsoft.modules.ums.service.UserMapperHessianService";
                UserMapperHessianService basicService = (UserMapperHessianService) factory.create(UserMapperHessianService.class, url);
                //从request中获取到accountId
                //从cas客户端中获取到平台name
                //当前应用在平台中注册的appCode
                String accountId =request.getParameter("accountId");
                if(StringUtil.isblank(accountId)){
                    response.sendRedirect("login.jsp");
                }
                String accountName = basicService.getThirdUserName(name, Long.parseLong(accountId), "FXPT");
                //拿到accountName不为空后，即视为登录成功，为该用户创建会话

                if(StringUtil.isblank(accountName)){
                    response.sendRedirect("login.jsp");
                }else {
                    System.out.println(accountName + "登录成功，创建用户会话");
                    Session s = ((BaseDao) SpringContextUtil.getApplicationContext().getBean("baseDao")).getNewSession();

                    String hql ="select yy from YhglYwYh y,YhglYwYhyy yy,XtglDmYy xt " +
                            " where yy.yhid = y.guid and yy.yyid = xt.guid " +
                            " and xt.zt ='1'   and   y.zt='1'  and   yy.zt ='1'" +
                            " and xt.bm ='"+ ConfigureParser.getPropert("YYBM")+"' "+
                            " and   yy.yhzh ='"+accountName+"'  " ;
                    List list = s.createQuery(hql).list();
                    YhglYwYhyy userinfo = (YhglYwYhyy) list.get(0);
                    s.close();
                    if(userinfo==null){
                        response.sendRedirect("/login.html");
                    }else {
                        request.getSession().setAttribute(StaticData.LOGINUSER, userinfo);
                        response.sendRedirect("/index.jsp");
                    }

                }
            }  else
            {
                response.sendRedirect("login.jsp");
            }
        }catch (Exception e){
            request.getSession().invalidate();
            e.printStackTrace();
            response.sendRedirect("login.jsp");
        }


    }


}
