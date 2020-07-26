package com.datanew.action;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.Xxb;
import com.datanew.service.XtPcService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/xtPc")
//
public  class XtPcController {

    @Resource
    private XtPcService xtPcService;


    @RequestMapping("/queryXxb")
    @ResponseBody
    public List<Xxb> queryXxb(@RequestParam Map pageMap){

        return xtPcService.queryXxb(pageMap);    }


    @ResponseBody
    @RequestMapping("/validateMc")
    public Result validateMc(Xxb t, Result result){

        return xtPcService.validateMc(t,result);

    }

    @ResponseBody
    @RequestMapping("/saveXxb")
    public Result saveXxb(Xxb t, Result result){

        return xtPcService.saveXxb(t,result);

    }


    @RequestMapping("/updateXxb")
    @ResponseBody
    public Result updateXxb(Xxb t, Result result){

        return xtPcService.updateXxb(t,result);

    }

    @RequestMapping("/deleteXxb")
    @ResponseBody
    public Result deleteXxb(Xxb t, Result result){

        return xtPcService.deleteXxb(t,result);

    }




}
