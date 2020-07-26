package com.datanew.service;

import com.datanew.dto.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface XmzjglService {

    List getNdjhglInfo(String nd,String xmmc,String jszt,HttpSession session);

    List getXmmc(String jszt,HttpSession session);

    String getXmztz(String xmid);

    void updateNdjhglInfo(String sdate, HttpSession session, Result result);

    void deleteNdjhglInfo(String sdate,Result result);

    void insertExcel(List<String[]>  list, HttpSession session,Map<String,Object> resultMap);

    String[][] exportexcel(String nd,HttpSession session);

    List getXmglInfo(String nd,String xmmc,HttpSession session);

    List getZjapmxInfo(String nd,String xmid);

    List getXMzbmx(String nd,String xmid,String bm);

    void updateZjap(String sdata,String xmid,String nd,HttpSession session,Result result);

    void updateXMzbmx(String sdata,String xmid,String nd,String zjly,Result result);

    List getZjdwmxInfo(String xmid,String nd,String zjly);

    void deleteZjdwmx(String sid,Result result);

    void updateZjdwmx(String sdata,String xmid,String nd,String zjly,Result result);

    void updateDnje(String sdata,String xmid,String nd,HttpSession session,Result result);

    List getXmzxgl(String pch,String zt,HttpSession session);

    void  deleteXmzxgl(String sdata,Result result);

    List getHtzfmx(String htid,String sid);

    List getHTZFmx(String sid);

    List getHtmc(String xmid,HttpSession session);

    List getHTMC(String xmid,HttpSession session);

    Object getXmzxInfo(String pch);

    List getKxmx(String zxdjid);

    void updateXmzxdjzt(String sdata,Result result);

    List getXmmc2(HttpSession session);

    List getXmmcall(HttpSession session);

    void insertXmzxdj(String xmid,String htid,String zfrq,String zy,String xxjd,String sdata,String mxdata,String uploadTableData,HttpSession session,Result result);

    void updateXmzxdj(String id,String xmid,String htid,String zfrq,String zy,String xxjd,String sdata,String mxdata,String uploadTableData,HttpSession session,Result result);

    List getfile(String bsid);
}
