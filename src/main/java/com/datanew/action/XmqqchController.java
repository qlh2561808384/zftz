package com.datanew.action;

import com.datanew.annotation.UserInfo;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.YhglYwYhyy;
import com.datanew.model.ZftzXm;
import com.datanew.model.ZftzXmqqch;
import com.datanew.service.XmqqchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2019/5/23.
 */
@Controller
@RequestMapping("/xmqqch")
public class XmqqchController {
    @Autowired
    private XmqqchService xmqqchService;


    @ResponseBody
    @RequestMapping("/saveZx")
    public Result saveZx(@ModelAttribute ZftzXmqqch zx, ZftzXm zxz, String sldh,String fileId, HttpSession session){
        return xmqqchService.saveZx(zx,zxz,sldh,fileId,session);
    }

    @ResponseBody
    @RequestMapping("/submitZx")
    public Result submit(String guids, HttpSession session) throws Exception{
        Result result=new Result();
        return xmqqchService.submit(guids,session);
    }


    @ResponseBody
    @RequestMapping("/delXmqqch")
    public Result delXmqqch(String ids, HttpSession session){
        Result result=new Result();
        result=xmqqchService.delXmqqch(ids,session);
        return result;
    }


    @RequestMapping("/getXmqqch")
    @ResponseBody
    public Pages getXmqqch(Pages page, String id ,String xmid,String zt, HttpSession session) throws UnsupportedEncodingException {

        return xmqqchService.getXmqqch(page,id,xmid,zt,session);

    }

    @RequestMapping("/selectXxById")
    @ResponseBody
    public Object selectXxById(Long  xmid){

        return xmqqchService.selectById(xmid);

    }

    @RequestMapping("/selectXmById")
    @ResponseBody
    public Object selectXmById(Long id){

        return xmqqchService.selecByXmId(id);

    }

//    @RequestMapping("/updateOrCheck")
//    @ResponseBody
//    public Result updateOrCheck(@ModelAttribute ZftzXmqqch zx, ZftzXm zxz,HttpSession session){
//        return xmqqchService.updateOrCheck(zx,zxz,session);
//    };

    @RequestMapping("/selectXmmc")
    @ResponseBody
    public Object selectXmmc(HttpSession session){
        return  xmqqchService.selectXmmc(session);
    }


    //获得项目类型
    @RequestMapping("/selectXmlx")
    @ResponseBody
    public List selectXmlx(){
        return  xmqqchService.selectXmlx();
    }

    @RequestMapping("/getXMLXTree")
    @ResponseBody
    public List getXMLXTree(){
        return  xmqqchService.getXMLXTree();
    }

    //获得主管部门
    @RequestMapping("/selectZgbm")
    @ResponseBody
    public List selectZgbm() {
        return xmqqchService.selectZgbm();
    }
    //批次主管单位
    @RequestMapping("/getZGBMTree")
    @ResponseBody
    public List getZGBMTree(HttpSession session) {
        return xmqqchService.getZGBMTree(session);
    }
    //获得建设单位
    @RequestMapping("/selectJsdw")
    @ResponseBody
    public List selectJsdw(HttpSession session) {
        return xmqqchService.selectJsdw(session);
    }
    //批次建设单位
    @RequestMapping("/getJSDWTree")
    @ResponseBody
    public List getJSDWTree(HttpSession session) {
        return xmqqchService.getJSDWTree(session);
    }
    //批次建设单位
    @RequestMapping("/getJSDWNoTree")
    @ResponseBody
    public List getJSDWNoTree(HttpSession session) {
        return xmqqchService.getJSDWNoTree(session);
    }
//    @RequestMapping("/submitZx")
//    @ResponseBody//@RequestBody接收前台传过来的json数据  解析成Map 要写 contentType: 'application/json',
//    public Result submit(@RequestBody Map model, @UserInfo YhglYwYhyy userInfo) {
//        Result result = new Result();
//        List<Integer> ids = (List<Integer>) model.get("ids");
//        List<Integer> currentFlowCode = (List<Integer>) model.get("lchj");
//        int flag = ((Integer) model.get("flag")).intValue();
//        try {
//            //proService.submit(userInfo.getYhid(), ids,result,flag,currentFlowCode);
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.setSuccess(false);
//            result.setContent("提交失败，失败原因" + e.getMessage());
//        }
//        return result;
//    }

    @RequestMapping("/xmqqchFileById")
    @ResponseBody
    public Object xmqqchFileById(String id){
        return  xmqqchService.xmqqchFileById(id);
    }
    
    @RequestMapping("/getXmqqchfj")
    @ResponseBody
    public Object getXmqqchfj(@RequestParam(value = "lx", required = false)String lx,@RequestParam(value = "guid", required = false) String guid){
        return  xmqqchService.getXmqqchfj(lx,guid);
    } 
    
  //获得项目类型
    @RequestMapping("/selectJldw")
    @ResponseBody
    public List selectJldw(){
        return  xmqqchService.selectJldw();
    }
    //获取退回意见
    @ResponseBody
    @RequestMapping("/getThyj")
    public Result getThyj(String id, HttpSession session){
        
    	return xmqqchService.getThyj(id,session);
       
    }
}
