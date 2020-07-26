package com.datanew.action;

import com.datanew.dto.Pages;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.FbyxNdsrb;
import com.datanew.model.FbyxNdsrjhDz;
import com.datanew.service.NdjhService;
import com.datanew.service.ZxtzService;
import com.datanew.util.ExcelCreator;
import com.datanew.util.ExcelParser;
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
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * @author wuwei
 * @create 2019/6/4 9:18
 * @desc
 **/
@Controller
@RequestMapping("/zxtz")
public class ZxtzController {

    @Autowired
    private ZxtzService zxtzService;

    @RequestMapping("/getZyzxList")
    @ResponseBody
    public Pages getZyzxList(Pages page, String nd, String ptgs, String zymc, HttpSession session){

        return zxtzService.getZyzxList(page,nd,ptgs,zymc,session);

    }

    @ResponseBody
    @RequestMapping("/updateByZyzx")
    public $Result updateByZyzx(FbyxNdsrb t, String ptgs, HttpSession session){

        return zxtzService.updateByZyzx(t,ptgs,session);

    }

    @ResponseBody
    @RequestMapping("/updateByZyzxDz")
    public $Result updateByZyzxDz(FbyxNdsrjhDz t, String srid, HttpSession session){

        return zxtzService.updateByZyzxDz(t,srid,session);

    }

    @RequestMapping(value = "webuploadByZyzx",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Map webuploadByZyzx(HttpServletRequest req, String name, String ptgs, HttpSession session){

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
        MultipartFile file = multipartRequest.getFile("file");
        Map<String,Object> resultMap = new HashMap();
        resultMap.put("success",true);
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
                zxtzService.insertExcel(list,ptgs,session);


//                FileInputStream fi = (FileInputStream) file.getInputStream();
//                if("xls".equals(fend.toLowerCase())){//2003版本
//                    List<Object[]> list =  ex.importExcel1(fi);
//                    zxtzService.insertExcel(list,ptgs,session);
//                }else if("xlsx".equals(fend.toLowerCase())){//2007版本
//                    List<Object[]>  list =  ex.importExcel2(fi);
//                    zxtzService.insertExcel(list,ptgs,session);
//                }

            }catch (Exception e){
                resultMap.put("success",false);
                resultMap.put("error","上传失败");
                e.printStackTrace();
            }
        }
        return  resultMap;
    }


    @RequestMapping("parseExcelByZyzx.do")
    @ResponseBody
    public void parseExcelByZyzx(String nd,String ptgs,String zymc, HttpServletResponse res) {
        try {
//            String suffix = file.getOriginalFilename().split(".")[1];
//            ExcelParser.Type type = ExcelParser.Type.getType(suffix);
//            String[][] table1 = ExcelParser.getParser(type).load(file.getInputStream()).parse(0).getTable();
//            String[][] table2 = ExcelParser.getParser(type).load(file.getInputStream()).parse("Sheet1").getTable();
            String[][] table1 = zxtzService.exportexcel(nd,ptgs,zymc);
            ExcelCreator creator = ExcelCreator.create();
            ExcelCreator.TableRange[] ranges = new ExcelCreator.TableRange[9];
            ranges[0] = new ExcelCreator.TableRange(0,1,0,0);
            ranges[1] = new ExcelCreator.TableRange(0,1,1,1);
            ranges[2] = new ExcelCreator.TableRange(0,1,2,2);
            ranges[3] = new ExcelCreator.TableRange(0,1,3,3);
            ranges[4] = new ExcelCreator.TableRange(0,1,4,4);
            ranges[5] = new ExcelCreator.TableRange(0,1,5,5);
            ranges[6] = new ExcelCreator.TableRange(0,1,6,6);
            ranges[7] = new ExcelCreator.TableRange(0,0,7,8);
            ranges[8] = new ExcelCreator.TableRange(0,1,9,9);


            creator.addSheet("sheet1", table1);
            creator.mergedCell(ranges[0]).mergedCell(ranges[1]).mergedCell(ranges[2]).mergedCell(ranges[3]).mergedCell(ranges[4])
                    .mergedCell(ranges[5]).mergedCell(ranges[6]).mergedCell(ranges[7]).mergedCell(ranges[8]);
//            // 导出到文件
//            creator.exportToFile("./", "年度化债计划");
//            // 导出OutputStream
//            OutputStream os = new ByteArrayOutputStream();
//            creator.getWorkbook().write(os);
            String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xlsx";
            String headStr = "attachment; filename=\"" + fileName + "\"";
            res.setContentType("APPLICATION/OCTET-STREAM");
            res.setHeader("Content-Disposition", headStr);
            OutputStream out = null;
            try {
                out = res.getOutputStream();
                creator.getWorkbook().write(out);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
