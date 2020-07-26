package com.datanew.action;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.FbyxZwgl;
import com.datanew.model.FbyxZygl;
import com.datanew.service.ZbkService;
import com.datanew.service.ZwService;
import com.datanew.util.ExcelParser;
import com.datanew.util.ExcelUtil;
import com.datanew.util.ExcelUtils;
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
import java.util.*;

/**
 * @author wuwei
 * @create 2019/5/17 13:55
 * @desc
 **/
@Controller
@RequestMapping("/zbk")
public class ZbkController {

    @Autowired
    private ZbkService zbkservice;


    @RequestMapping("/getZbk")
    @ResponseBody
    public Pages getZbk(Pages page,String xmlx , HttpSession session){

        return zbkservice.getZbk(page,xmlx, session);

    }

    @ResponseBody
    @RequestMapping("/saveZbk")
    public Result saveZbk(String guid,String xmlx,String yjzb,String ejzb,String sjzb,String dwtze,String jldw,String bz, HttpSession session){

        return zbkservice.saveZbk(guid,xmlx, yjzb, ejzb, sjzb, dwtze, jldw, bz, session);

    }

    @RequestMapping("/delZbk")
    @ResponseBody
    public Result delZbk(String  guid, HttpSession session){

        return zbkservice.delZbk(guid, session);

    }
    
    @RequestMapping(value = "webupload",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Map webupload(HttpServletRequest req,String name, HttpSession session){

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
        MultipartFile file = multipartRequest.getFile("file");
        Map<String,Object> resultMap = new HashMap();
        resultMap.put("success",false);
        resultMap.put("error","");
        resultMap.put("errors","");
        resultMap.put("list",new ArrayList());

        ExcelUtils ex=new ExcelUtils();
        if(!file.isEmpty()){
            try {
                String[] fstr = name.split("\\.");
                String fend = fstr[1];
                //System.out.println(fend);
                ExcelParser.Type type = ExcelParser.Type.getType(fend.toLowerCase());
                List<String[]> list = new ArrayList<String[]>(Arrays.asList(
                        ExcelParser.getParser(type)
                                .load(file.getInputStream())
                                .parse(0)
                                .getTable()
                ));
                zbkservice.insertExcel(list,session);
            }catch (Exception e){
                resultMap.put("success",false);
                resultMap.put("error","上传失败");
                e.printStackTrace();
            }
        }
        return  resultMap;
    }
    
    @RequestMapping("/exportExcelByLx")
    public void exportExcelByLx(String xmlx,  HttpServletResponse res, HttpSession session){
        ExcelUtil ex=zbkservice.exportExcelByLx(xmlx,res);
        ex.fillWorkbook();
        ex.export();

    }


   
}
