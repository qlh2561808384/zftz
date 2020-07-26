package com.datanew.action;

import com.datanew.annotation.UserInfo;
import com.datanew.dto.Result;
import com.datanew.model.YhglYwYhyy;
import com.datanew.service.ProFlowService;
import com.datanew.service.xmgcjjs.XmgcjjsFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author inRush
 * @date 2019/6/6
 **/
@Controller
@ResponseBody
@RequestMapping(value = "/pro")
public class ProFlowController {
    @Autowired
    ProFlowService proFlowService;

    @RequestMapping(value = "shjl/{type}", method = RequestMethod.POST)
    public List<Map> getShjl(@PathVariable String type, @RequestParam Integer ywId) {
        List<Map> list = proFlowService.getShjl(type, ywId);
        return list;
    }

    @RequestMapping(value = "submit/{type}", method = RequestMethod.POST)
    public Result submit(@PathVariable String type, @UserInfo YhglYwYhyy userInfo, @RequestBody Map model) {
        try {
            List<Integer> ids = (List<Integer>) model.get("ids");
            proFlowService.submit(type, userInfo.getGuid(), ids);
            return new Result(true, "提交成功");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "提交失败");
        }
    }

    @RequestMapping(value = "next/{type}")
    public Result next(@PathVariable String type, @RequestBody Map model, @UserInfo YhglYwYhyy userInfo) {
        try {
            List<Integer> ids = (List<Integer>) model.get("ids");
            List<String> lchjs = (List<String>) model.get("lchjs");
//            String comment = (String) model.get("comment");
//            final String comment = (String) model.get("comment");
            List<String> comment = (List<String>) model.get("comment");
            proFlowService.nextFlow(type, userInfo.getGuid(), ids, lchjs, comment);
           /* new ArrayList<String>(1) {
                {
                    add(comment);
                }
            }*/
            return new Result(true, "审核成功");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "审核失败");
        }
    }

    @RequestMapping(value = "back/{type}")
    public Result back(@PathVariable String type, @RequestBody Map model, @UserInfo YhglYwYhyy userInfo) {
        try {
            List<Integer> ids = (List<Integer>) model.get("ids");
            List<String> lchjs = (List<String>) model.get("lchjs");
//            final String comment = (String) model.get("comment");
//            String comment = (String) model.get("comment");
            List<String> comment = (List<String>) model.get("comment");
            proFlowService.backFlow(type, userInfo.getGuid(), ids, lchjs,comment);
            return new Result(true, "退回成功");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "退回失败");
        }
    }
}
