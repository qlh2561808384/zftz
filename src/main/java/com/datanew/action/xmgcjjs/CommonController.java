package com.datanew.action.xmgcjjs;

import com.datanew.dto.Result;
import com.datanew.service.xmgcjjs.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 公共控制器
 * 主要是获取一些公共的信息，比如选项表信息
 *
 * @author inRush
 * @date 2019/5/29
 **/
@Controller
@ResponseBody
@RequestMapping(value = "/common")
public class CommonController {
    @Autowired
    CommonService service;

    @RequestMapping(value = "xx", method = RequestMethod.GET)
    public Result getXxList(@RequestParam String lx) {
        List<Map> list = service.getXxList(lx);
        return new Result(true, list);
    }

    @RequestMapping(value = "fjlx", method = RequestMethod.GET)
    public Result getFjlx(@RequestParam String sxbm) {
        List<Map> list = service.getFjlx(sxbm);
        return new Result(true, list);
    }

    @RequestMapping(value = "zjjg", method = RequestMethod.GET)
    public Result getZjjg() {
        List<Map> list = service.getZjjg();
        return new Result(true, list);
    }

    @RequestMapping(value = "zgdw")
    public List<Map> getZgdw() {
        List<Map> list = service.getZgdw();
        return list;
    }

    @RequestMapping(value = "permission/button", method = RequestMethod.GET)
    public Result getButtonPermission(Integer btnGroupId, HttpSession session) {
        List<String> btns = service.getButtonPermission(btnGroupId, session);
        return new Result(true, btns);
    }

    @RequestMapping(value = "user/type", method = RequestMethod.GET)
    public Result getUserType(HttpSession session) {
        try {
            String userType = service.getUserType(session);
            return new Result(true, userType);
        } catch (Exception e) {
            return new Result(false, "用户未登录");
        }
    }


}
