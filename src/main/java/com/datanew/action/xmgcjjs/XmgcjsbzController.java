package com.datanew.action.xmgcjjs;

import com.datanew.annotation.SessionData;
import com.datanew.annotation.UserInfo;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.*;
import com.datanew.service.xmgcjjs.XmgcjsbzService;
import com.datanew.util.JavaBeanUtil;
import com.datanew.util.StaticData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author inRush
 * @date 2019/6/3
 **/
@Controller
@ResponseBody
@RequestMapping(value = "/xmgcjsbz")
public class XmgcjsbzController {
    @Autowired
    XmgcjsbzService service;

    @RequestMapping(value = "xm", method = RequestMethod.GET)
    public Result getXmList(@UserInfo YhglYwYhyy userInfo, boolean excludeDw, boolean excludeExistItem) {
        List list = service.getXmList(excludeDw ? null : userInfo.getGuid().toString(), excludeExistItem);
        return new Result(true, list);
    }

    @RequestMapping(value = "xmInfo", method = RequestMethod.GET)
    public Result getXmInfo(@RequestParam Integer xmId) {
        try {
            Map data = service.getXmInfo(xmId, true);
            return new Result(true, data);
        } catch (IllegalArgumentException e) {
            return new Result(false, e.getMessage());
        }
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public List<Map> list(@UserInfo YhglYwYhyy userInfo, @RequestParam Integer status) {
        return service.list(userInfo.getGuid().toString(), status);
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public Result detail(Integer id) {
        Map detail = service.getDetail(id);
        return new Result(true, detail);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result delete(@RequestBody Map map) {
        try {
            List<Integer> ids = (List<Integer>) map.get("ids");
            service.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result saveOrUpdate(@RequestBody Map model, @SessionData(StaticData.LOGINUSERINFO) YhglYwYh yhInfo) {
        try {
            ZftzXmjgjs saveModel = JavaBeanUtil.convertMap(ZftzXmjgjs.class, model, new JavaBeanUtil.CamelCaseConverter());
            if (model.get("notUpdateCzr") == null || !(Boolean) model.get("notUpdateCzr")) {
                saveModel.setCzr(yhInfo.getXm());
            }
            Long id = service.saveOrUpdate(saveModel, (ArrayList<Map>) model.get("fj"));
            return new Result(true, id);
        } catch (NullPointerException e) {
            return new Result(false, "账户未登录，请重新登录");
        } catch (NumberFormatException e) {
            return new Result(false, "数字转换异常");
        } catch (ClassCastException e) {
            return new Result(false, "上传属性类型错误，请检查");
        } catch (Exception e) {
            return new Result(false, "服务其异常");
        }
    }

    /**
     * 待办列表
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "todo/list", method = RequestMethod.POST)
    public List<Map> todoList(@UserInfo YhglYwYhyy userInfo, @RequestParam Integer status) {
        try {
            return service.todoList(userInfo.getGuid(), status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Map>();
    }

    @RequestMapping(value = "update/comment", method = RequestMethod.POST)
    public Result updateComment(@RequestBody Map map) {
        try {
            List<Integer> ids = (List<Integer>) map.get("ids");
            String comment = (String) map.get("comment");
            service.updateComment(ids, comment);
            return new Result(true, "success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false, "更新失败");
    }
}
