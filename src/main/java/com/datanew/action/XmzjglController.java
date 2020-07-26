package com.datanew.action;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datanew.util.ExcelCreator;
import com.datanew.util.ExcelParser;
import com.datanew.util.ExcelUtils;
import com.datanew.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datanew.service.XmzjglService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/xmzjgl")
public class XmzjglController {

    @Autowired
    private XmzjglService xmzjglService;

    //获取年度计划管理数据
    @RequestMapping("getNdjhglInfo")
    @ResponseBody
    public List getNdjhglInfo(String nd, String xmmc,String jszt, HttpSession session) {
        return xmzjglService.getNdjhglInfo(nd, xmmc,jszt, session);
    }

    //获取项目名称
    @RequestMapping("getXmmc.do")
    @ResponseBody
    public List getXmmc(String jszt, HttpSession session) {
        return xmzjglService.getXmmc(jszt,session);
    }

    //根据项目名称获取总投资
    @RequestMapping("getXmztz.do")
    @ResponseBody
    public String getXmztz(String xmid) {
        return xmzjglService.getXmztz(xmid);
    }

    //更新年度计划管理数据
    @RequestMapping("updateNdjhglInfo.do")
    @ResponseBody
    public Object updateNdjhglInfo(String sdate, HttpSession session) {
        Result result = new Result();
        xmzjglService.updateNdjhglInfo(sdate, session, result);
        return result;
    }

    @RequestMapping("deleteNdjhglInfo.do")
    @ResponseBody
    public Object deleteNdjhglInfo(String sdate) {
        Result result = new Result();
        xmzjglService.deleteNdjhglInfo(sdate, result);
        return result;
    }

    @RequestMapping(value = "webupload", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Map webupload(HttpServletRequest req, String name, HttpSession session, @RequestParam(value = "file") MultipartFile file) {
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
//        MultipartFile file = multipartRequest.getFile("file");
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("success", true);
        resultMap.put("error", "");
        resultMap.put("errors", "");
        resultMap.put("list", new ArrayList());

//        ExcelUtils ex = new ExcelUtils();
        if (!file.isEmpty()) {
            try {
                String[] fstr = name.split("\\.");
                String fend = fstr[1];
                //System.out.println(fend);
//                FileInputStream fi = (FileInputStream) file.getInputStream();
                ExcelParser.Type type = ExcelParser.Type.getType(fend.toLowerCase());
                List<String[]> list = new ArrayList<String[]>(Arrays.asList(
                        ExcelParser.getParser(type)
                                .load(file.getInputStream())
                                .parse(0)
                                .getTable()
                ));
                xmzjglService.insertExcel(list, session, resultMap);
//                if ("xls".equals(fend.toLowerCase())) {//2003版本
//
//
//                } else if ("xlsx".equals(fend.toLowerCase())) {//2007版本
//                    List<Object[]> list = ex.importExcel2(fi);
//                    xmzjglService.insertExcel(list, session, resultMap);
//                }

            } catch (Exception e) {
                resultMap.put("success", false);
                resultMap.put("error", "上传失败");
                e.printStackTrace();
            }
        }
        return resultMap;
    }


    @RequestMapping(value = "parseExcel.do", method = RequestMethod.GET)
    @ResponseBody
    public void parseExcel(String nd, HttpSession session, HttpServletResponse response) {
        try {
//            String suffix = file.getOriginalFilename().split(".")[1];
//            ExcelParser.Type type = ExcelParser.Type.getType(suffix);
//            String[][] table1 = ExcelParser.getParser(type).load(file.getInputStream()).parse(0).getTable();
//            String[][] table2 = ExcelParser.getParser(type).load(file.getInputStream()).parse("Sheet1").getTable();
            String[][] table1 = xmzjglService.exportexcel(nd, session);
            ExcelCreator.TableRange[] ranges = new ExcelCreator.TableRange[]{
                    new ExcelCreator.TableRange(0, 0, 0, 13),
                    new ExcelCreator.TableRange(1, 2, 0, 0),
                    new ExcelCreator.TableRange(1, 2, 1, 1),
                    new ExcelCreator.TableRange(1, 2, 2, 2),
                    new ExcelCreator.TableRange(1, 2, 3, 3),
                    new ExcelCreator.TableRange(1, 2, 4, 4),

                    new ExcelCreator.TableRange(1, 2, 5, 5),
                    new ExcelCreator.TableRange(1, 2, 6, 6),

                    new ExcelCreator.TableRange(1, 1, 7, 13)
            };

            ExcelCreator.create()
                    .addSheet("sheet1", table1)
                    .mergedCell(0, ranges)
//                    .autoWidth(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})
                    .exportToFontEnd(response, "年度计划管理");

//            creator.addSheet("sheet1", table1);
//            creator.mergedCell(ranges[0]).mergedCell(ranges[1]).mergedCell(ranges[2]).mergedCell(ranges[3]).mergedCell(ranges[4]);
//            // 导出到文件
//            creator.exportToFile("./", "年度计划管理");
//            // 导出OutputStream
//            OutputStream os = new ByteArrayOutputStream();
//            creator.getWorkbook().write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("getXmglInfo.do")
    @ResponseBody
    public List getXmglInfo(String nd, String xmmc, HttpSession session) {
//        Result result = new Result();
        return xmzjglService.getXmglInfo(nd, xmmc, session);
//        return result;
    }

    @RequestMapping("getZjapmxInfo.do")
    @ResponseBody
    public List getZjapmxInfo(String nd, String xmid) {
//        Result result = new Result();
        return xmzjglService.getZjapmxInfo(nd, xmid);
//        return result;
    }

    @RequestMapping("getXMzbmx.do")
    @ResponseBody
    public List getXMzbmx(String nd, String xmid, String bm) {
//        Result result = new Result();
        return xmzjglService.getXMzbmx(nd, xmid, bm);
//        return result;
    }

    @RequestMapping("updateZjap.do")
    @ResponseBody
    public Object updateZjap(String sdata, String xmid, String nd, HttpSession session) {
        Result result = new Result();
        xmzjglService.updateZjap(sdata, xmid, nd, session, result);
        return result;
    }

    @RequestMapping("updateXMzbmx.do")
    @ResponseBody
    public Object updateXMzbmx(String sdata, String xmid, String nd, String zjly) {
        Result result = new Result();
        xmzjglService.updateXMzbmx(sdata, xmid, nd, zjly, result);
        return result;
    }

    @RequestMapping("getZjdwmxInfo.do")
    @ResponseBody
    public List getZjdwmxInfo(String xmid, String nd, String zjly) {
//        Result result = new Result();
        return xmzjglService.getZjdwmxInfo(xmid, nd, zjly);
//        return result;
    }

    @RequestMapping("deleteZjdwmx.do")
    @ResponseBody
    public Object deleteZjdwmx(String sid) {
        Result result = new Result();
        xmzjglService.deleteZjdwmx(sid, result);
        return result;
    }

    @RequestMapping("updateZjdwmx.do")
    @ResponseBody
    public Object updateZjdwmx(String sdata, String xmid, String nd, String zjly) {
        Result result = new Result();
        xmzjglService.updateZjdwmx(sdata, xmid, nd, zjly, result);
        return result;
    }

    @RequestMapping("updateDnje.do")
    @ResponseBody
    public Object updateDnje(String sdata, String xmid, String nd, HttpSession session) {
        Result result = new Result();
        xmzjglService.updateDnje(sdata, xmid, nd, session, result);
        return result;
    }

    //获取项目执行管理数据,table的值
    @RequestMapping("getXmzxgl.do")
    @ResponseBody
    public List getXmzxgl(String pch, String zt, HttpSession session) {
//        Result result = new Result();
        return xmzjglService.getXmzxgl(pch, zt, session);
//        return result;
    }

    //删除项目执行管理数据,假删,状态改为作废
    @RequestMapping("deleteXmzxgl.do")
    @ResponseBody
    public Object deleteXmzxgl(String sdata) {
        Result result = new Result();
        xmzjglService.deleteXmzxgl(sdata, result);
        return result;
    }

    //获取合同支付明细,table2的值
    @RequestMapping("getHtzfmx.do")
    @ResponseBody
    public List getHtzfmx(String htid, String sid) {
//        Result result = new Result();
        return xmzjglService.getHtzfmx(htid, sid);
//        return result;
    }
    @RequestMapping("getHTZFmx.do")
    @ResponseBody
    public List getHTZFmx(String sid) {
//        Result result = new Result();
        return xmzjglService.getHTZFmx( sid);
//        return result;
    }
    //获取合同名称，加载到合同下拉列表
    @RequestMapping("getHtmc.do")
    @ResponseBody
    public List getHtmc(String xmid,HttpSession session) {
//        Result result = new Result();
        return xmzjglService.getHtmc(xmid,session);
//        return result;
    }

    @RequestMapping("getHTMC.do")
    @ResponseBody
    public List getHTMC(String xmid,HttpSession session) {
//        Result result = new Result();
        return xmzjglService.getHTMC(xmid,session);
//        return result;
    }
    //获取合同执行数据  dfrom的值
    @RequestMapping("getXmzxInfo.do")
    @ResponseBody
    public Object getXmzxInfo(String pch) {
//        Result result = new Result();
        return xmzjglService.getXmzxInfo(pch);
//        return result;
    }

    //获取款项类型明细 table1的值
    @RequestMapping("getKxmx.do")
    @ResponseBody
    public List getKxmx(String zxdjid) {
//        Result result = new Result();
        return xmzjglService.getKxmx(zxdjid);
//        return result;
    }

    //修改项目执行登记状态，入账
    @RequestMapping("updateXmzxdjzt.do")
    @ResponseBody
    public Object updateXmzxdjzt(String sdata) {
        Result result = new Result();
        xmzjglService.updateXmzxdjzt(sdata, result);
        return result;
    }

    @RequestMapping("getXmmc2.do")
    @ResponseBody
    public List getXmmc2(HttpSession session) {
//        Result result = new Result();
        return xmzjglService.getXmmc2(session);
//        return result;
    }

    @RequestMapping("getXmmcall.do")
    @ResponseBody
    public List getXmmcall(HttpSession session) {
//        Result result = new Result();
        return xmzjglService.getXmmcall(session);
//        return result;
    }

    //保存项目执行登记数据
    @RequestMapping("insertXmzxdj.do")
    @ResponseBody
    public Object insertXmzxdj(String xmid, String htid, String zfrq, String zy, String xxjd, String sdata, String uploadTableData, String mxdata, HttpSession session) {
        Result result = new Result();
        xmzjglService.insertXmzxdj(xmid, htid, zfrq, zy, xxjd, sdata, mxdata, uploadTableData, session, result);
        return result;
    }

    //保存项目执行登记数据
    @RequestMapping("updateXmzxdj.do")
    @ResponseBody
    public Object updateXmzxdj(String id, String xmid, String htid, String zfrq, String zy, String xxjd, String sdata, String mxdata, String uploadTableData, HttpSession session) {
        Result result = new Result();
        xmzjglService.updateXmzxdj(id, xmid, htid, zfrq, zy, xxjd, sdata, mxdata, uploadTableData, session, result);
        return result;
    }

    @RequestMapping("getfile.do")
    @ResponseBody
    public List getfile(String bsid) {
        return xmzjglService.getfile(bsid);
    }
}
