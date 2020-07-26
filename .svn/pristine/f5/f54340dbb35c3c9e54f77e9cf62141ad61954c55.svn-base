package com.datanew.action;

import com.datanew.dto.Pages;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.FbyxZygl;
import com.datanew.model.Xxb;
import com.datanew.model.ZftzXm;
import com.datanew.model.ZftzXmtzwh;
import com.datanew.service.XmcService;
import com.datanew.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuwei
 * @create 2019/5/17 13:55
 * @desc
 **/
@Controller
@RequestMapping("/xmc")
public class XmcController {

    @Autowired
    private XmcService xmcService;


    @RequestMapping("/queryPtgs")
    @ResponseBody
    public List queryPtgs(){

        return xmcService.queryPtgs();
    }

    @RequestMapping("/queryXmlx")
    @ResponseBody
    public List queryXmlx(){

        return xmcService.queryXmlx();    }

    @RequestMapping("/queryJsdw")
    @ResponseBody
    public List queryJsdw(){

        return xmcService.queryJsdw();    }

    @RequestMapping("/queryXmzt")
    @ResponseBody
    public List queryXmzt(){

        return xmcService.queryXmzt();    }


    @RequestMapping("/getXmcList")
    @ResponseBody
    public Pages getXmcList(Pages page, String sffb, String xmmc,String xmlx,String ptgs, HttpSession session){

        return xmcService.getXmcList(page,sffb, xmmc,xmlx, ptgs,session);

    }

    @ResponseBody
    @RequestMapping("/update")
    public $Result update(ZftzXm t, ZftzXmtzwh z, HttpSession session){

        return xmcService.update(t,z,session);

    }

}
