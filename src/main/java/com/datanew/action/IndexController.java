package com.datanew.action;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.FbyxZwgl;
import com.datanew.model.FbyxZygl;
import com.datanew.service.IndexService;
import com.datanew.service.ZbkService;
import com.datanew.service.ZwService;
import com.datanew.util.ExcelUtil;
import com.datanew.util.ExcelUtils;

import org.apache.log4j.lf5.viewer.TrackingAdjustmentListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IndexService indexservice;


    @RequestMapping("/getXmfltz")
    @ResponseBody
    public List getXmfltz( HttpSession session){

        return indexservice.getXmfltz();

    }
    
    @RequestMapping("/getPtxmtz")
    @ResponseBody
    public List getPtxmtz( HttpSession session){

        return indexservice.getPtxmtz();

    }
    
    @RequestMapping("/getZjph")
    @ResponseBody
    public Object getZjph( HttpSession session){

        return indexservice.getZjph();

    }
    
    @RequestMapping("/getJcsj")
    @ResponseBody
    public List getJcsj( HttpSession session){

        return indexservice.getJcsj();

    }

    @RequestMapping("/getXminfo")
    @ResponseBody
    public Pages getXminfo(Pages page, HttpSession session){

        return indexservice.getXminfo(page, session);

    }
    
    
    @RequestMapping("/getXmpic")
    @ResponseBody
    public List getXmpic(String guid, HttpSession session){

        return indexservice.getXmpic(guid);

    }
    
    @RequestMapping("/getXmnr")
    @ResponseBody
    public List getXmnr(String guid, HttpSession session){

        return indexservice.getXmnr(guid);

    }
    
    @RequestMapping("/getZxl")
    @ResponseBody
    public List getZxl(String guid, HttpSession session){

        return indexservice.getZxl(guid);

    }
   
    @RequestMapping("/getGcjd")
    @ResponseBody
    public Object getGcjd(String guid, HttpSession session){

        return indexservice.getGcjd(guid);

    }
    
    @RequestMapping("/getZjqk")
    @ResponseBody
    public List getZjqk(String guid, HttpSession session){

        return indexservice.getZjqk(guid);

    }
    
    @RequestMapping("/getPtZxl")
    @ResponseBody
    public List getPtZxl(String guid, HttpSession session){

        return indexservice.getPtZxl(guid);

    }
    
    @RequestMapping("/getPtZjqk")
    @ResponseBody
    public List getPtZjqk(String guid, HttpSession session){

        return indexservice.getPtZjqk(guid);

    }
    
    @RequestMapping("/getFxyj")
    @ResponseBody
    public List getFxyj(String guid, HttpSession session){

        return indexservice.getFxyj(guid);

    }
    
    @RequestMapping("/getXmjd")
    @ResponseBody
    public List getXmjd(String guid, HttpSession session){

        return indexservice.getXmjd(guid);

    }
    
    
    @RequestMapping("/getFbglszqk")
    @ResponseBody
    public List getFbglszqk(String guid, HttpSession session){

        return indexservice.getFbglszqk(guid);

    }
    
    @RequestMapping("/getFbglndzxqk")
    @ResponseBody
    public Object getFbglndzxqk(String guid, HttpSession session){

        return indexservice.getFbglndzxqk(guid);

    }
    
    @RequestMapping("/getFbglzxl")
    @ResponseBody
    public List getFbglzxl(String guid, HttpSession session){

        return indexservice.getFbglzxl(guid);

    }
    
   
}
