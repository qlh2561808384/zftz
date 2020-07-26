package com.datanew.action;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.*;
import com.datanew.service.NdjhService;
import com.datanew.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

/**
 * @author wuwei
 * @create 2019/5/22 9:15
 * @desc
 **/
@Controller
@RequestMapping("/ndjh")
public class NdjhController {

    @Autowired
    private NdjhService ndjhService;

    @RequestMapping("/queryNd")
    @ResponseBody
    public List<Xxb> queryNd(){

        return ndjhService.queryNd();    }

    @RequestMapping("/queryXzqh")
    @ResponseBody
    public List queryXzqh(){

        return ndjhService.queryXzqh();    }

    @RequestMapping(value="/findZylx", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String findZylx(String id){

        return ndjhService.findZylx(id);    }

    @RequestMapping("/queryGsnjh")
    @ResponseBody
    public Pages queryGsnjh(Pages page, String nd,String ptgs, HttpSession session){

        return ndjhService.queryGsnjh(page,nd,ptgs,session);

    }

    @RequestMapping("webupload")
    @ResponseBody
    public Map webupload(HttpServletRequest req, Integer chunks, Integer chunk, String name, String zyid, HttpSession session) throws IOException {
        FTPUtils ftp = new FTPUtils();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
        MultiValueMap<String, MultipartFile> map = multipartRequest.getMultiFileMap();
        String token = req.getParameter("token");
        YhglYwYhyy userinfo =  (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
        Map fmap = new HashMap();
        Iterator<String> iter = map.keySet().iterator();
        while(iter.hasNext()) {
            String str = (String) iter.next();
            List<MultipartFile> fileList =  map.get(str);
            for(MultipartFile multipartFile : fileList) {
                String[] fstr = name.split("\\.");
                String fend = fstr[1];
                String fs =  fstr[0];
                File targetFile=new File(ConfigureParser.getPropert("ftp.upload.path")+File.separator+ UUID.randomUUID()+"."+fend);
                //否则直接将文件内容拷贝至新文件
                //saveUploadFile(multipartFile.getInputStream(), targetFile,fmap);
                ftp.uploadFileInputStream(targetFile,multipartFile.getInputStream(),fmap);
                if((Boolean)fmap.get("success")){
                    String fname = targetFile.getName();
                    String fileId = fname.split("\\.")[0];

                    XtFj fj = new XtFj();
                    fj.setCjsj(new Date());
                    fj.setYhid(Long.parseLong(String.valueOf(userinfo.getYhid())));
                    fj.setDx(Long.valueOf(""+fmap.get("SIZE")));
                    fj.setHashval(""+fmap.get("MD5"));
                    fj.setLx(fend);
                    fj.setZyid(Long.parseLong(zyid));
                    fj.setMc(name);
                    fj.setWjm(fileId);

                    Long fjGuid = ndjhService.insertXtFj(fj);
                    //将附件的主键返回给页面
                    fmap.put("fjGuid",fjGuid);
                    fmap.put("fileId",fileId);
                }
//                multipartFile.transferTo(targetFile);
            }
        }

        return fmap;
    }

    @RequestMapping("fileInfo")
    @ResponseBody
    public List fileInfo(HttpServletRequest req){
        String uuid = req.getParameter("uuid");
        return ndjhService.fileInfo(uuid);
    }

    @RequestMapping("delFj")
    @ResponseBody
    public Object delFj(String id, Result result){
        return ndjhService.delFj(id,result);
    }

    @RequestMapping("/queryZycTree")
    @ResponseBody
    public List queryZycTree(String nd,String ptgs){

        return ndjhService.queryZycTree(nd,ptgs);
    }

    @RequestMapping("/getZmlxByZy")
    @ResponseBody
    public Long getZmlxByZy(String id){

        return ndjhService.getZmlxByZy(id);
    }

    @RequestMapping("/queryXmcTree")
    @ResponseBody
    public List queryXmcTree(){

        return ndjhService.queryXmcTree();
    }

    @RequestMapping("/queryZwcTree")
    @ResponseBody
    public List queryZwcTree(String ptgs){

        return ndjhService.queryZwcTree(ptgs);
    }

    @RequestMapping("/getSrbList")
    @ResponseBody
    public Pages getSrbList(Pages page,String nd,String ptgs, HttpSession session){

        return ndjhService.getSrbList(page,nd,ptgs,session);

    }

    @RequestMapping("/getSrbDzList")
    @ResponseBody
    public Pages getSrbDzList(Pages page,String srid, HttpSession session){

        return ndjhService.getSrbDzList(page,srid,session);

    }

    @ResponseBody
    @RequestMapping("/validateSrb")
    public Result validateSrb(String zymcid, HttpSession session){

        return ndjhService.validateSrb(zymcid,session);

    }

    @ResponseBody
    @RequestMapping("/validateZcb")
    public Result validateZcb(String xmid,String nd, HttpSession session){

        return ndjhService.validateZcb(xmid,nd,session);

    }

    @ResponseBody
    @RequestMapping("/validateHzb")
    public Result validateHzb(String zwid,String nd, HttpSession session){

        return ndjhService.validateHzb(zwid,nd,session);

    }

    @ResponseBody
    @RequestMapping("/saveOrUpdateBySrb")
    public Result saveOrUpdateBySrb(FbyxNdsrb t,String ptgs, HttpSession session,Result result){

        return ndjhService.saveOrUpdateBySrb(t,ptgs,session,result);

    }

    @RequestMapping("/deleteBySrb")
    @ResponseBody
    public $Result deleteBySrb(String  id, HttpSession session){

        return ndjhService.deleteBySrb(id,session);

    }

    @RequestMapping("/deleteBySrDz")
    @ResponseBody
    public $Result deleteBySrDz(String  id, HttpSession session){

        return ndjhService.deleteBySrDz(id,session);

    }

    @RequestMapping("/getZcbList")
    @ResponseBody
    public Pages getZcbList(Pages page,String nd,String ptgs, HttpSession session){

        return ndjhService.getZcbList(page,nd,ptgs,session);

    }

    @ResponseBody
    @RequestMapping("/saveOrUpdateByZcb")
    public $Result saveOrUpdateByZcb(FbyxNdzcjh t, String ptgs, HttpSession session){

        return ndjhService.saveOrUpdateByZcb(t,ptgs,session);

    }

    @RequestMapping("/deleteByZcb")
    @ResponseBody
    public $Result deleteByZcb(String  id, HttpSession session){

        return ndjhService.deleteByZcb(id,session);

    }

    @RequestMapping("/getHzbList")
    @ResponseBody
    public Pages getHzbList(Pages page,String nd,String ptgs, HttpSession session){

        return ndjhService.getHzbList(page,nd,ptgs,session);

    }

    @ResponseBody
    @RequestMapping("/saveOrUpdateByHzb")
    public $Result saveOrUpdateByHzb(FbyxNdhzb t, String ptgs, HttpSession session){

        return ndjhService.saveOrUpdateByHzb(t,ptgs,session);

    }

    @RequestMapping("/deleteByHzb")
    @ResponseBody
    public $Result deleteByHzb(String  id, HttpSession session){

        return ndjhService.deleteByHzb(id,session);

    }

    @ResponseBody
    @RequestMapping("/saveOrUpdateBySrbDz")
    public $Result saveOrUpdateBySrbDz(FbyxNdsrjhDz t, String srid, HttpSession session){

        return ndjhService.saveOrUpdateBySrbDz(t,srid,session);

    }

    @RequestMapping(value = "webuploadByNc",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Map webuploadByNc(HttpServletRequest req,String name,String ptgs, HttpSession session){

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
                ndjhService.insertExcelNc(list,ptgs,session);

//                FileInputStream fi = (FileInputStream) file.getInputStream();
//                if("xls".equals(fend.toLowerCase())){//2003版本
//                    List<Object[]>  list =  ex.importExcel1(fi);
//                    ndjhService.insertExcelNc(list,ptgs,session);
//                }else if("xlsx".equals(fend.toLowerCase())){//2007版本
//                    List<Object[]>  list =  ex.importExcel2(fi);
//                    ndjhService.insertExcelNc(list,ptgs,session);
//                }

            }catch (Exception e){
                resultMap.put("success",false);
                resultMap.put("error","上传失败");
                e.printStackTrace();
            }
        }
        return  resultMap;
    }

    @RequestMapping(value = "webuploadByTz",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Map webuploadByTz(HttpServletRequest req,String name,String ptgs, HttpSession session){

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
                  ndjhService.insertExcelTz(list,ptgs,session);

//                FileInputStream fi = (FileInputStream) file.getInputStream();
//                if("xls".equals(fend.toLowerCase())){//2003版本
//                    List<Object[]>  list =  ex.importExcel1(fi);
//                    ndjhService.insertExcelTz(list,ptgs,session);
//                }else if("xlsx".equals(fend.toLowerCase())){//2007版本
//                    List<Object[]>  list =  ex.importExcel2(fi);
//                    ndjhService.insertExcelTz(list,ptgs,session);
//                }

            }catch (Exception e){
                resultMap.put("success",false);
                resultMap.put("error","上传失败");
                e.printStackTrace();
            }
        }
        return  resultMap;
    }

    @RequestMapping(value = "webuploadByZcb",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Map webuploadByZcb(HttpServletRequest req,String name,String ptgs, HttpSession session){

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
                ndjhService.insertExcel2(list,ptgs,session);

//                FileInputStream fi = (FileInputStream) file.getInputStream();
//                if("xls".equals(fend.toLowerCase())){//2003版本
//                    List<Object[]>  list =  ex.importExcel1(fi);
//                    ndjhService.insertExcel2(list,ptgs,session);
//                }else if("xlsx".equals(fend.toLowerCase())){//2007版本
//                    List<Object[]>  list =  ex.importExcel2(fi);
//                    ndjhService.insertExcel2(list,ptgs,session);
//                }

            }catch (Exception e){
                resultMap.put("success",false);
                resultMap.put("error","上传失败");
                e.printStackTrace();
            }
        }
        return  resultMap;
    }

    @RequestMapping(value = "webuploadByHzb",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Map webuploadByHzb(HttpServletRequest req,String name,String ptgs, HttpSession session){

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
                ndjhService.insertExcel3(list,ptgs,session);


//                FileInputStream fi = (FileInputStream) file.getInputStream();
//                if("xls".equals(fend.toLowerCase())){//2003版本
//                    List<Object[]>  list =  ex.importExcel1(fi);
//                    ndjhService.insertExcel3(list,ptgs,session);
//                }else if("xlsx".equals(fend.toLowerCase())){//2007版本
//                    List<Object[]>  list =  ex.importExcel2(fi);
//                    ndjhService.insertExcel3(list,ptgs,session);
//                }

            }catch (Exception e){
                resultMap.put("success",false);
                resultMap.put("error","上传失败");
                e.printStackTrace();
            }
        }
        return  resultMap;
    }

    @RequestMapping("parseExcelBySrb.do")
    @ResponseBody
    public void parseExcelBySrb(String nd,String ptgs, HttpServletResponse res) {
        try {
//            String suffix = file.getOriginalFilename().split(".")[1];
//            ExcelParser.Type type = ExcelParser.Type.getType(suffix);
//            String[][] table1 = ExcelParser.getParser(type).load(file.getInputStream()).parse(0).getTable();
//            String[][] table2 = ExcelParser.getParser(type).load(file.getInputStream()).parse("Sheet1").getTable();
            String[][] table1 = ndjhService.exportexcel1(nd,ptgs);
            ExcelCreator creator = ExcelCreator.create();
            ExcelCreator.TableRange[] ranges = new ExcelCreator.TableRange[10];
            ranges[0] = new ExcelCreator.TableRange(0,1,0,0);
            ranges[1] = new ExcelCreator.TableRange(0,1,1,1);
            ranges[2] = new ExcelCreator.TableRange(0,1,2,2);
            ranges[3] = new ExcelCreator.TableRange(0,1,3,3);
            ranges[4] = new ExcelCreator.TableRange(0,1,4,4);
            ranges[5] = new ExcelCreator.TableRange(0,1,5,5);
            ranges[6] = new ExcelCreator.TableRange(0,1,6,6);
            ranges[7] = new ExcelCreator.TableRange(0,1,7,7);
            ranges[8] = new ExcelCreator.TableRange(0,0,8,10);
            ranges[9] = new ExcelCreator.TableRange(0,0,11,12);


            creator.addSheet("sheet1", table1);
            creator.mergedCell(ranges[0]).mergedCell(ranges[1]).mergedCell(ranges[2]).mergedCell(ranges[3]).mergedCell(ranges[4])
                    .mergedCell(ranges[5]).mergedCell(ranges[6]).mergedCell(ranges[7]).mergedCell(ranges[8]).mergedCell(ranges[9]);
            // 导出到文件
//            creator.exportToFile("./", "年度收入计划");
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

    @RequestMapping("parseExcelByZcb.do")
    @ResponseBody
    public void parseExcelByZcb(String nd,String ptgs, HttpServletResponse res) {
        try {
//            String suffix = file.getOriginalFilename().split(".")[1];
//            ExcelParser.Type type = ExcelParser.Type.getType(suffix);
//            String[][] table1 = ExcelParser.getParser(type).load(file.getInputStream()).parse(0).getTable();
//            String[][] table2 = ExcelParser.getParser(type).load(file.getInputStream()).parse("Sheet1").getTable();
            String[][] table1 = ndjhService.exportexcel2(nd,ptgs);
            ExcelCreator creator = ExcelCreator.create();
            ExcelCreator.TableRange[] ranges = new ExcelCreator.TableRange[9];
            ranges[0] = new ExcelCreator.TableRange(0,1,0,0);
            ranges[1] = new ExcelCreator.TableRange(0,1,1,1);
            ranges[2] = new ExcelCreator.TableRange(0,1,2,2);
            ranges[3] = new ExcelCreator.TableRange(0,1,3,3);
            ranges[4] = new ExcelCreator.TableRange(0,1,4,4);
            ranges[5] = new ExcelCreator.TableRange(0,1,5,5);
            ranges[6] = new ExcelCreator.TableRange(0,0,6,12);
            ranges[7] = new ExcelCreator.TableRange(0,0,13,19);
            ranges[8] = new ExcelCreator.TableRange(0,0,20,26);


            creator.addSheet("sheet1", table1);
            creator.mergedCell(ranges[0]).mergedCell(ranges[1]).mergedCell(ranges[2]).mergedCell(ranges[3]).mergedCell(ranges[4])
                    .mergedCell(ranges[5]).mergedCell(ranges[6]).mergedCell(ranges[7]).mergedCell(ranges[8]);
            // 导出到文件
//            creator.exportToFile("./", "年度支出计划");
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

    @RequestMapping("parseExcelByHzb.do")
    @ResponseBody
    public void parseExcelByHzb(String nd,String ptgs, HttpServletResponse res) {
        try {
//            String suffix = file.getOriginalFilename().split(".")[1];
//            ExcelParser.Type type = ExcelParser.Type.getType(suffix);
//            String[][] table1 = ExcelParser.getParser(type).load(file.getInputStream()).parse(0).getTable();
//            String[][] table2 = ExcelParser.getParser(type).load(file.getInputStream()).parse("Sheet1").getTable();
            String[][] table1 = ndjhService.exportexcel3(nd,ptgs);
            ExcelCreator creator = ExcelCreator.create();
            ExcelCreator.TableRange[] ranges = new ExcelCreator.TableRange[7];
            ranges[0] = new ExcelCreator.TableRange(0,1,0,0);
            ranges[1] = new ExcelCreator.TableRange(0,1,1,1);
            ranges[2] = new ExcelCreator.TableRange(0,1,2,2);
            ranges[3] = new ExcelCreator.TableRange(0,1,3,3);
            ranges[4] = new ExcelCreator.TableRange(0,0,4,6);
            ranges[5] = new ExcelCreator.TableRange(0,0,7,9);
            ranges[6] = new ExcelCreator.TableRange(0,0,10,12);


            creator.addSheet("sheet1", table1);
            creator.mergedCell(ranges[0]).mergedCell(ranges[1]).mergedCell(ranges[2]).mergedCell(ranges[3]).mergedCell(ranges[4])
                    .mergedCell(ranges[5]).mergedCell(ranges[6]);
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
