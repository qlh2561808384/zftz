package com.datanew.service;

import com.datanew.dto.Page;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.ZftzXm;
import com.datanew.model.ZftzXmbzcs;
import com.datanew.model.ZftzXmqqch;

import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by admin on 2019/5/29.
 */
public interface XmqqshService {
    public Pages getXmqqsh(Pages page,String id, String sldh, String xmid,String zt,  HttpSession session) throws UnsupportedEncodingException;
    public Result saveOrCheck(String tzxe,String jykj,String spjy,String id,HttpSession session);
    public Pages getZbk(Pages page,String id,String zbmc,HttpSession session) throws UnsupportedEncodingException;
    public Result saveZbk(String idZftzXm,String idZftzZbk,String sl, HttpSession session);
    public List getShyj(String id);
    public Object getxmId(HttpSession session);
    public Result deleteZbk(String ids, HttpSession session);
    public Pages getXmbzcs(Pages page,String id,HttpSession session);
    public Result submit(String guids, HttpSession session) throws Exception;
    public Result back(String guids,String shyj, HttpSession session) throws  Exception;
    //获取指标库打印地址
    public Result getZbkPrint(String xmid);
    
    public List getAlkmb(String xmid,  HttpSession session); 
    
    public Pages getAlk(Pages page,String al, String zt,String xmid,  HttpSession session) throws UnsupportedEncodingException ;
    
    public Result savealk(String xmid,String alkid, HttpSession session);
}
