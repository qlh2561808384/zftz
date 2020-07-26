package com.datanew.service;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.ZftzXm;
import com.datanew.model.ZftzXmzjlydj;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 * Created by admin on 2019/6/4.
 */
public interface XmzjlydjService {
//    public List selectXmmc();
    public Result saveZjly(String id, ZftzXm zx, ZftzXmzjlydj zjly,String fileId, HttpSession session);
    public Pages getZjly(Pages page, String id, HttpSession session);
    public Result delXmzjlydj(String ids, HttpSession session);
    public List getXmqqsh(String id);
    Object selectXmmc(HttpSession session);
    Object getGsxxByXmid(String xmid);
    Object getXmqqch(String xmid);
    public Object  xmqqchFileById(String id);
    //获得建设单位
    public List selectJsdw(HttpSession session);
    public List getJSDWTree(HttpSession session);
    List getXmqqchfj(String guid);
    public List getXmqqshzjlyxm(String id);
   

}
