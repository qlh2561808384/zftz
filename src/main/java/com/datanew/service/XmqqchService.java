package com.datanew.service;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.ZftzXm;
import com.datanew.model.ZftzXmqqch;

import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by admin on 2019/5/23.
 */
public interface XmqqchService {

    public Result saveZx(ZftzXmqqch zx, ZftzXm zxz, String sldh,String fileId, HttpSession session);

    public Pages getXmqqch(Pages page, String sldh,String xmid,String zt, HttpSession session) throws UnsupportedEncodingException ;

    public Result delXmqqch(String ids, HttpSession session);

    public Object selectById(Long xmId);

    public Object selecByXmId(Long id);

    public Result submit(String guids, HttpSession session) throws Exception;

    public Object selectXmmc(HttpSession session);

    public Object  xmqqchFileById(String id);

//    /**
//     * 提交流程
//     *
//     * @param ids
//     */
//    void submit(Long yhId, List<Integer> ids,Result result,int flag,List<Integer> currentFlowCode);
    //获得项目类型
    public List selectXmlx();
    public List getXMLXTree();

    //获得主管部门
    public List selectZgbm();
    public List getZGBMTree(HttpSession session);

    //获得建设单位
    public List selectJsdw(HttpSession session);
    public List getJSDWTree(HttpSession session);
    public List getJSDWNoTree(HttpSession session);
    
    List getXmqqchfj(String lx,String guid);
    
    public List selectJldw();
    
    public Result getThyj(String id, HttpSession session);
}
