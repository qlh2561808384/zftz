package com.datanew.action;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.ZftzXm;
import com.datanew.model.ZftzXmbzcs;
import com.datanew.model.ZftzXmqqch;
import com.datanew.service.XmqqshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.DocFlavor.STRING;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by admin on 2019/5/29.
 */
@Controller
@RequestMapping("/xmqqsh")
public class XmqqshController {
    @Autowired
    private XmqqshService xmqqshService;





    @RequestMapping("/getXmqqsh")
    @ResponseBody
    public Pages getXmqqsh(Pages page, String id ,String sldh, String xmid,String zt,  HttpSession session) throws UnsupportedEncodingException{

        return xmqqshService.getXmqqsh(page,id,sldh,xmid,zt, session);

    }


    @RequestMapping("/saveOrCheck")
    @ResponseBody
    public Result saveOrCheck(String tzxe,String jykj,String spjy,String id,HttpSession session){
        return  xmqqshService.saveOrCheck(tzxe,jykj,spjy,id,session);
    }

    @RequestMapping("/getZbk")
    @ResponseBody
    public Pages getZbk(Pages page, String id,String zbmc, HttpSession session) throws UnsupportedEncodingException{

        return xmqqshService.getZbk(page,id,zbmc ,session);

    }

    @RequestMapping("/getShyj")
    @ResponseBody
    public List getShyj(String id) {
        return xmqqshService.getShyj(id);
    }

    @RequestMapping("/saveZbk")
    @ResponseBody
    public Result saveZbk(String idZftzXm,String idZftzZbk,String sl, HttpSession session){
        return  xmqqshService.saveZbk(idZftzXm,idZftzZbk,sl,session);
    }

    @RequestMapping("/delZbk")
    @ResponseBody
    public Result deleteZbk(String ids, HttpSession session){
        return  xmqqshService.deleteZbk(ids,session);
    }

    @ResponseBody
    @RequestMapping("/submitSh")
    public Result submit(String guids, HttpSession session) throws Exception{
        Result result=new Result();
        return xmqqshService.submit(guids,session);
    }

    @ResponseBody
    @RequestMapping("/backSh")
    public Result back(String guids,String shyj, HttpSession session) throws  Exception{
        Result result=new Result();
        return xmqqshService.back(guids,shyj,session);
    }

    @RequestMapping("/getXmbzcs")
    @ResponseBody
    public Pages getXmbzcs(Pages page, String id, HttpSession session){

        return xmqqshService.getXmbzcs(page,id, session);

    }
    @RequestMapping("/getxmId")
    @ResponseBody
    public Object getxmId(HttpSession session){
        return  xmqqshService.getxmId(session);
    }
    
    @ResponseBody
    @RequestMapping("/getZbkPrint")
    public Result getZbkPrint(String xmid) throws  Exception{
        return xmqqshService.getZbkPrint(xmid);
    }
    
    @ResponseBody
    @RequestMapping("/getAlkmb")
    public List getAlkmb(String xmid,HttpSession session) throws  Exception{
        return xmqqshService.getAlkmb(xmid,session);
    }
    
    @RequestMapping("/getAlk")
    @ResponseBody
    public Pages getAlk(Pages page,String al, String zt,String xmid,  HttpSession session) throws UnsupportedEncodingException{

        return xmqqshService.getAlk(page,al,zt,xmid, session);

    }
    
    @RequestMapping("/savealk")
    @ResponseBody
    public Result savealk(String xmid,String alk, HttpSession session){
        return  xmqqshService.savealk(xmid,alk,session);
    }
}
