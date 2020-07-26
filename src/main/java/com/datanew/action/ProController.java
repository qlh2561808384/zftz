package com.datanew.action;

import com.datanew.annotation.UserInfo;
import com.datanew.dto.Result;
import com.datanew.model.YhglYwYhyy;
import com.datanew.model.ZftzXm;
import com.datanew.service.ProService;
import com.datanew.util.ExcelParser;
import com.datanew.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.util.*;

@Controller
@RequestMapping("/pro")
public class ProController {
    @Autowired
    private ProService proService;

    /*********************************************************************项目台账模块****************************************************************************/
    @RequestMapping("save")
    @ResponseBody
    public Result save(HttpServletRequest request, String jsonData, String id, String dataArr,String fileId,String zcdjFileId) {
        Result result = new Result();
        try {
            proService.save(request, jsonData, result, id, dataArr,fileId,zcdjFileId);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setContent("保存失败，失败原因" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("savePro")
    @ResponseBody
    public Result savePro(HttpServletRequest request, ZftzXm zftzxm) {
        Result result = new Result();
        try {
             proService.savePro(request,zftzxm,result);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setContent("保存失败，失败原因" + e.getMessage());
        }
        return result;
    }


    @RequestMapping("getXMData")
    @ResponseBody
    public List getXMData(String xmmc,String XMLX,String XMSSLX,HttpSession session) {
        List xmData = proService.getXMData(xmmc,XMLX,XMSSLX,session);
        return xmData;
    }

    @RequestMapping("getZcdjData")
    @ResponseBody
    public List getZcdjData(String id) {
        List zcdjData = proService.getZcdjData(id);
        return zcdjData;
    }

    //批次主管单位
    @RequestMapping("getZGBMTree")
    @ResponseBody
    public List getZGBMTree(HttpSession session) {
        return this.proService.getZGBMTree(session);
    }
    @RequestMapping("getAllZGBMTree")
    @ResponseBody
    public List getAllZGBMTree(HttpSession session) {
        return this.proService.getAllZGBMTree(session);
    }

    //批次主管单位
    @RequestMapping("getZGBMNOTree")
    @ResponseBody
    public List getZGBMNOTree() {
        return this.proService.getZGBMNOTree();
    }
    //批次建设单位
    @RequestMapping("getJSDWTree")
    @ResponseBody
    public List getJSDWTree(HttpSession session) {
        return this.proService.getJSDWTree(session);
    }
    @RequestMapping("getJSDWNoTree")
    @ResponseBody
    public List getJSDWNoTree() {
        return this.proService.getJSDWNoTree();
    }
    @RequestMapping("query")
    @ResponseBody
    public List query(String xmmc, String xmlx, String xmsslx) {
        Result result = new Result();
        List list = proService.query(xmmc, xmlx, xmsslx, result);
        return list;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Result delete(@RequestBody Map map) {
        Result result = new Result();
        List<Integer> ids = (List<Integer>) map.get("id");
        try {
            proService.delete(ids, result);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setContent("保存失败，失败原因" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("deleteGSMX")
    @ResponseBody
    public Result deleteGSMX(@RequestBody Map map) {
        Result result = new Result();
        List<Integer> ids = (List<Integer>) map.get("id");
        try {
            proService.deleteGSMX(ids, result);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setContent("保存失败，失败原因" + e.getMessage());
        }
        return result;
    }

    //资产类别树
    @RequestMapping("getJclbTreeData")
    @ResponseBody
    public List getJclbTreeData() {
        return this.proService.getJclbTreeData();
    }

    //项目阶段树
    @RequestMapping("getXMJDTree")
    @ResponseBody
    public List getXMJDTree() {
        return this.proService.getXMJDTree();
    }
    @RequestMapping("getXMJD")
    @ResponseBody
    public List getXMJD(String id) {
        return this.proService.getXMJD(id);
    }
    //项目类别树
    @RequestMapping("getXMLXTree")
    @ResponseBody
    public List getXMLXTree() {
        return this.proService.getXMLXTree();
    }

    //项目实施类型树
    @RequestMapping("getXmsslxTree")
    @ResponseBody
    public List getXmsslxTree() {
        return this.proService.getXmsslxTree();
    }

    //资产入账方式
    @RequestMapping("getRzfsTreeData")
    @ResponseBody
    public List getRzfsTreeData() {
        return this.proService.getRzfsTreeData();
    }

    //删除资产登记数据
    @RequestMapping("deleteZcdj")
    @ResponseBody
    public Result deleteZcdj(String id) {
        Result result = new Result();
        try {
            proService.deleteZcdj(id, result);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setContent("删除失败，失败原因" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("selectXmlx")
    @ResponseBody
    public List selectXmlx() {
        return this.proService.selectXmlx();
    }

    @RequestMapping("selectXmmc")
    @ResponseBody
    public List selectXmmc() {
        return this.proService.selectXmmc();
    }

    @RequestMapping("selectXmsslx")
    @ResponseBody
    public List selectXmsslx() {
        return this.proService.selectXmsslx();
    }
    @RequestMapping("selectXmjd")
    @ResponseBody
    public List selectXmjd() {
        return this.proService.selectXmjd();
    }
    @RequestMapping("getXmmcTree")
    @ResponseBody
    public List getXmmcTree(String id,HttpSession session) {
        return this.proService.getXmmcTree(id,session);
    }
    @RequestMapping("getXmtzwhTree")
    @ResponseBody
    public List getXmtzwhTree(String id,HttpSession session) {
        return this.proService.getXmtzwhTree(id,session);
    }
    @RequestMapping("loadxmData")
    @ResponseBody
    public List loadxmData(String id,HttpSession session) {
        List list = proService.loadxmData(id,session);
        return list;
    }
    /*********************************************************************项目资金来源登记模块********************************************************************/
    @RequestMapping("loadXMData")
    @ResponseBody
    public List loadXMData(String id) {
        List list = proService.loadXMData(id);
        return list;
    }

    @RequestMapping("saveXmgs")
    @ResponseBody
    public Result saveXmgs(HttpServletRequest request, String json,String xmgsyjid,String xmgszxd) {
        Result result = new Result();
        try {
            proService.saveXmgs(request, json,xmgsyjid,result,xmgszxd);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setContent("保存失败，失败原因" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("getXmgsyjData")
    @ResponseBody
    public List getXmgsyjData(String xmmc, String zt,HttpSession session) {
        List xmData = proService.getXmgsyjData(xmmc,zt,session);
        return xmData;
    }

    @RequestMapping("getXmGSYJData")
    @ResponseBody
    public List getXmGSYJData(String xmmc, String zt,HttpSession session) {
        List xmData = proService.getXmGSYJData(xmmc,zt,session);
        return xmData;
    }
    @RequestMapping("getXmgsfkData")
    @ResponseBody
    public List getXmgsfkData(String xmmc,HttpSession session) {
        List xmData = proService.getXmgsfkData(xmmc,session);
        return xmData;
    }
    @RequestMapping("selectZgbm")
    @ResponseBody
    public List selectZgbm() {
        return this.proService.selectZgbm();
    }

    @RequestMapping("selectJsdw")
    @ResponseBody
    public List selectJsdw(HttpSession session) {
        return this.proService.selectJsdw(session);
    }

    @RequestMapping("selectNoJsdw")
    @ResponseBody
    public List selectNoJsdw() {
        return this.proService.selectNoJsdw();
    }

    @RequestMapping("deleteXmgsyj")
    @ResponseBody
    public Result deleteXmgsyj(@RequestBody Map map) {
        Result result = new Result();
        List<Integer> ids = (List<Integer>) map.get("id");
        try {
            proService.deleteXmgsyj(ids, result);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setContent("保存失败，失败原因" + e.getMessage());
        }
        return result;
    }
    @RequestMapping("getZBKData")
    @ResponseBody
    public List getZBKData(String id) {
        return proService.getZBKData(id);
    }

    @RequestMapping("getZjlydj")
    @ResponseBody
    public List getZjlydj(String id) {
        return proService.getZjlydj(id);
    }
    @RequestMapping("getXmqqch")
    @ResponseBody
    public List getXmqqch(String id) {
        return proService.getXmqqch(id);
    }
    @RequestMapping("getShyj")
    @ResponseBody
    public List getShyj(String id) {
        return proService.getShyj(id);
    }

    @RequestMapping("getXmgsmcTree")
    @ResponseBody
    public List getXmgsmcTree(String id,HttpSession session) {
        return this.proService.getXmgsmcTree(id,session);
    }

    @RequestMapping("saveGs")
    @ResponseBody
    public Result saveGs(HttpServletRequest request,@RequestBody Map map) {
        Result result = new Result();
        String xmgsid = map.get("xmgsid") == null ? "" : map.get("xmgsid").toString();
        List<Map> xmgsmx = (List<Map>) map.get("xmgsmx");
        Map xmgs = (Map) map.get("xmgs");
        List<Map> gsfileid = (List<Map>) map.get("gsFileId");
        try {
            proService.saveGs(request,result,xmgsid,xmgs,xmgsmx,gsfileid);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setContent("保存失败，失败原因" + e.getMessage());
        }
        return result;
    }
    @RequestMapping("getFylx")
    @ResponseBody
    public List getFylx(){
        return proService.getFylx();
    }

    @RequestMapping("getXmgsData")
    @ResponseBody
    public List getXmgsData(String xmmc,String zt,HttpSession session){
        return proService.getXmgsData(xmmc,zt,session);
    }
    @RequestMapping("getXmgsmxData")
    @ResponseBody
    public List getXmgsmxData(String id){
        return proService.getXmgsmxData(id);
    }

    @RequestMapping("delXmgs")
    @ResponseBody
    public Result delXmgs(@RequestBody Map map){
        List<Integer> ids = (List<Integer>) map.get("id");
        Result result;
        try {
            proService.delXmgs(ids);
            result = new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "删除失败，失败原因" + e.getMessage());
        }
        return result;
    }
    @RequestMapping("getFile")
    @ResponseBody
    public List getFile(String id){
        return proService.getFile(id);
    }
    @RequestMapping("getZcdjFile")
    @ResponseBody
    public List getZcdjFile(String id){
        return proService.getZcdjFile(id);
    }
    @RequestMapping("getXmgszxdFile")
    @ResponseBody
    public List getXmgszxdFile(String id){
        return proService.getXmgszxdFile(id);
    }

    @RequestMapping("getZjlyFile")
    @ResponseBody
    public List getZjlyFile(String id){
        return proService.getZjlyFile(id);
    }

    @RequestMapping("getQqchFile")
    @ResponseBody
    public List getQqchFile(String id){
        return proService.getQqchFile(id);
    }
    @RequestMapping("getXmgsFile")
    @ResponseBody
    public List getXmgsFile(String id){
        return proService.getXmgsFile(id);
    }

    @RequestMapping("checkData")
    @ResponseBody
    public Result checkData(String id){
        Result result = new Result();
        proService.checkData(id,result);
        return result;
    }

    @RequestMapping(value = "webupload", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Map webupload(HttpServletRequest req, String name, HttpSession session,String xmid) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
        MultipartFile file = multipartRequest.getFile("file");
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("success", false);
        resultMap.put("error", "");
        resultMap.put("errors", "");
        resultMap.put("list", new ArrayList());

        ExcelUtils ex = new ExcelUtils();
        if (!file.isEmpty()) {
            try {
                String[] fstr = name.split("\\.");
                String fend = fstr[1];
                //System.out.println(fend);
                FileInputStream fi = (FileInputStream) file.getInputStream();
                if ("xls".equals(fend.toLowerCase())) {//2003版本
                    List<Object[]> list = ex.importExcel1(fi);
                    proService.insertExcel(list, session, resultMap,xmid);
                } else if ("xlsx".equals(fend.toLowerCase())) {//2007版本
                    List<Object[]> list = ex.importExcel2(fi);
                    proService.insertExcel(list, session, resultMap,xmid);
                }

            } catch (Exception e) {
                resultMap.put("success", false);
                resultMap.put("error", "上传失败");
                e.printStackTrace();
            }
        }
        return resultMap;
    }

    @RequestMapping(value = "webuploadExcel", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Map webuploadExcel(HttpServletRequest req, String name, HttpSession session,String xmid) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
        MultipartFile file = multipartRequest.getFile("file");
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("success", false);
        resultMap.put("error", "");
        resultMap.put("errors", "");
        resultMap.put("list", new ArrayList());

        ExcelUtils ex = new ExcelUtils();
        if (!file.isEmpty()) {
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
                proService.saveExcel(list, session, resultMap,xmid);
                /*FileInputStream fi = (FileInputStream) file.getInputStream();
                if ("xls".equals(fend.toLowerCase())) {//2003版本
                    List<Object[]> list = ex.importExcel1(fi);
                    proService.uploadExcel(list, session, resultMap,xmid);
                } else if ("xlsx".equals(fend.toLowerCase())) {//2007版本
                    List<Object[]> list = ex.importExcel2(fi);
                    proService.uploadExcel(list, session, resultMap,xmid);
                }*/

            } catch (Exception e) {
                resultMap.put("success", false);
                resultMap.put("error", "上传失败");
                e.printStackTrace();
            }
        }
        return resultMap;
    }

    @RequestMapping("getTzbadj")
    @ResponseBody
    public List getTzbadj(String xmmc,String lchj,HttpSession session) {
        return proService.getTzbadj(xmmc,lchj,session);
    }

    //获取项目名称
    @RequestMapping("getXmmc.do")
    @ResponseBody
    public List getXmmc(HttpSession session) {
        return proService.getXmmc(session);
    }

    //获取项目名称
    @RequestMapping("getXmmc2.do")
    @ResponseBody
    public List getXmmc2(HttpSession session) {
        return proService.getXmmc2(session);
    }

    @RequestMapping("getJsdw.do")
    @ResponseBody
    public List getJsdw() {
        return proService.getJsdw();
    }

    @RequestMapping("getZgbm.do")
    @ResponseBody
    public List getZgbm() {
        return proService.getZgbm();
    }

    @RequestMapping("getXmlx.do")
    @ResponseBody
    public List getXmlx() {
        return proService.getXmlx();
    }

    @RequestMapping("getXmgsinfo.do")
    @ResponseBody
    public Object getXmgsinfo(String xmid) {
        return proService.getXmgsinfo(xmid);
    }

    @RequestMapping("getXmgsinfobyid.do")
    @ResponseBody
    public Object getXmgsinfobyid(String tzid) {
        return proService.getXmgsinfobyid(tzid);
    }

    @RequestMapping("getGcfymc.do")
    @ResponseBody
    public List getGcfymc(String xmid) {
        return proService.getGcfymc(xmid);
    }

    @RequestMapping("getGcfymctree.do")
    @ResponseBody
    public List getGcfymctree(String xmid) {
        return proService.getGcfymctree(xmid);
    }

    @RequestMapping("getGsmxinfo.do")
    @ResponseBody
    public Map getGsmxinfo(String gsid,String tzid) {
        return proService.getGsmxinfo(gsid,tzid);
    }

    @RequestMapping("getFylxtree1.do")
    @ResponseBody
    public List getFylxtree1() {
        return proService.getFylxtree1();
    }

    @RequestMapping("getFylx1.do")
    @ResponseBody
    public List getFylx1() {
        return proService.getFylx1();
    }

    @RequestMapping("getFylxbygsmxid.do")
    @ResponseBody
    public String getFylxbygsmxid(String gsmxid) {
        return proService.getFylxbygsmxid(gsmxid);
    }

    @RequestMapping("getTzefl.do")
    @ResponseBody
    public List getTzefl() {
        return proService.getTzefl();
    }

    @RequestMapping("getGstzje.do")
    @ResponseBody
    public Map getGstzje(String xmid,String gc,String fylx,String tzefl) {
        return proService.getGstzje(xmid,gc,fylx,tzefl);
    }

    @RequestMapping("deleteGstzbadj.do")
    @ResponseBody
    public Object deleteGstzbadj(String sdata, HttpSession session) {
        Result result = new Result();
        proService.deleteGstzbadj(sdata,session, result);
        return result;
    }

    @RequestMapping("deleteGstzba.do")
    @ResponseBody
    public Object deleteGstzba(String sdata,String xmid,String gsid, HttpSession session) {
        Result result = new Result();
        proService.deleteGstzba(sdata,xmid,gsid,session, result);
        return result;
    }

    @RequestMapping("insertGstzba.do")
    @ResponseBody
    public Object insertGstzba(String sdata,String uploadTableData,String form, String xmid,String gsid, HttpSession session) {
        Result result = new Result();
        proService.insertGstzba(sdata,uploadTableData,form, xmid, gsid,session, result);
        return result;
    }

    @RequestMapping("getBatzmx.do")
    @ResponseBody
    public List getBatzmx(String tzid,String xmid) {
        return proService.getBatzmx(tzid,xmid);
    }

    @RequestMapping("updateGstzbamx.do")
    @ResponseBody
    public Object updateGstzbamx(String sdata,String uploadTableData,String tzid, HttpSession session,String formData) {
        Result result = new Result();
        proService.updateGstzbamx(sdata,uploadTableData,tzid,session, result,formData);
        return result;
    }

    @RequestMapping("deleteGstzbamx.do")
    @ResponseBody
    public Object deleteGstzbamx(String sdata, HttpSession session) {
        Result result = new Result();
        proService.deleteGstzbamx(sdata,session, result);
        return result;
    }

    @RequestMapping("getfile.do")
    @ResponseBody
    public List getfile(String bsid) {
        return proService.getfile(bsid);
    }

    @RequestMapping("saveGsmx.do")
    @ResponseBody
    public Object saveGsmx(String sdata, HttpSession session) {
        Result result = new Result();
        proService.saveGsmx(sdata,session, result);
        return result;
    }
    
    @RequestMapping("getXmZjlymcTree")
    @ResponseBody
    public List getXmZjlymcTree(String id,HttpSession session) {
        return proService.getXmZjlymcTree(id,session);
    }

    @RequestMapping("getXmgsYj")
    @ResponseBody
    public List getXmgsYj(String id) {
        return proService.getXmgsYj(id);
    }


    @ResponseBody
    @RequestMapping("selThyj")
    public Result selThyj(String id,String type){
        Result result = proService.selThyj(id,type);
        return result;
    }
}
