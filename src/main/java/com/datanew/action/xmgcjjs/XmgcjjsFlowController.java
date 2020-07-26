package com.datanew.action.xmgcjjs;

import com.datanew.annotation.UserInfo;
import com.datanew.dto.Result;
import com.datanew.model.YhglYwYhyy;
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
@RequestMapping(value = "/flow")
public class XmgcjjsFlowController {
    @Autowired
    XmgcjjsFlowService service;

    @RequestMapping(value = "shjl/{type}", method = RequestMethod.POST)
    public List<Map> getShjl(@PathVariable String type, @RequestParam Integer ywId) {
        List<Map> list = service.getShjl(type, ywId);
        return list;
    }

    @RequestMapping(value = "submit/{type}", method = RequestMethod.POST)
    public Result submit(@PathVariable String type, @UserInfo YhglYwYhyy userInfo, @RequestBody Map model) {
        try {
            List<Integer> ids = (List<Integer>) model.get("ids");
            service.submit(type, userInfo.getGuid(), ids);
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
            final String comment = (String) model.get("comment");
            service.nextFlow(type, userInfo.getGuid(), ids, lchjs, new ArrayList<String>(1) {
                {
                    add(comment);
                }
            });
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
            final String comment = (String) model.get("comment");
            service.backFlow(type, userInfo.getGuid(), ids, lchjs, new ArrayList<String>(1) {
                {
                    add(comment);
                }
            });
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
