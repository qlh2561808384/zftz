package com.datanew.action.xmgcjjs;

import com.datanew.annotation.UserInfo;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.*;
import com.datanew.service.xmgcjjs.XmgcjsccService;
import com.datanew.util.JavaBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author inRush
 * @date 2019/5/29
 **/
@Controller
@ResponseBody
@RequestMapping("xmgcjscc")
public class XmgcjsccController {
    @Autowired
    XmgcjsccService service;

    @RequestMapping(value = "check", method = RequestMethod.POST)
    public Result check(@RequestBody Map map) {
        Map zjjg = (Map) map.get("zjjg");
        Map gcfw = (Map) map.get("gcfw");
        List<Map> zjjgList = service.getZjjg(String.valueOf(zjjg.get("JGFL")), String.valueOf(zjjg.get("JGZZ")));
        if (zjjgList.size() == 0) {
            return new Result(false, "筛选的中介机构未找到");
        }
        List<Map> gcfwList = service.getGc(gcfw);
        service.randomMerge(gcfwList, zjjgList);
        return new Result(true, gcfwList);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody List<Map> mapList) {
        try {
            service.save(mapList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "保存失败");
        }
        return new Result(true, "保存成功");
    }

    @RequestMapping(value = "submit", method = RequestMethod.POST)
    public Result submit(@RequestBody Map map) {
        List<Integer> ccIds = (List<Integer>) map.get("ccIds");
        if (ccIds == null || ccIds.size() == 0) {
            return new Result(false, "ID 不能未空");
        }
        service.submit(ccIds);
        return new Result(true, "提交成功");
    }

    /**
     * 获取结算报备列表
     *
     * @return {@link Map}
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public Pages list(Pages page, Integer status, @UserInfo YhglYwYhyy userInfo) {
        return service.list(userInfo.getYhzh(), status, page);
    }

    /**
     * 获取合同列表
     *
     * @return id:ID HTMC:名称
     */
    @RequestMapping(value = "htlist", method = RequestMethod.GET)
    public Result htList() {
        List<Map> list = service.htList();
        return new Result(true, list);
    }

    /**
     * 获取合同信息
     *
     * @return 1. 合同名称(HTMC)，合同内容(HTNR)，项目名称(XMMC)，建设单位(JSDW),主管部门(ZGBM)
     * 2. 变更明细记录 BGMXJL:[{变更内容(BGNR),变更确认时间（BGQRSJ），涉及增减预算造价（SJZJYSZJ）}]
     * 3. 工程费用明细：[]
     */
    @RequestMapping(value = "htinfo", method = RequestMethod.GET)
    public Result getHtInfo(@RequestParam Integer htId) {
        try {
            Map ht = service.getHtInfo(htId);
            return new Result(true, ht);
        } catch (ArithmeticException e) {
            return new Result(false, "合同数据异常！");
        }
    }

    @RequestMapping(value = "ccinfo")
    public Result baInfo(@RequestParam Integer baId) {
        try {
            Map ccInfo = service.getGcjsCc(baId);
            return new Result(true, ccInfo);
        } catch (ArithmeticException e) {
            return new Result(false, "合同数据异常！");
        }
    }


    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    public Result save(@RequestBody Map model, @UserInfo YhglYwYhyy userInfo) {
        try {
            ZftzXmgcjscc saveModel = JavaBeanUtil.convertMap(ZftzXmgcjscc.class, model, new JavaBeanUtil.CamelCaseConverter());
            List<ZftzXmgcjsccFymx> mxSaveModels = new ArrayList<ZftzXmgcjsccFymx>();
            for (Map item : (ArrayList<Map>) model.get("gcfymx")) {
                mxSaveModels.add(JavaBeanUtil.<ZftzXmgcjsccFymx>convertMap(ZftzXmgcjsccFymx.class, item, new JavaBeanUtil.UppercaseConverter()));
            }
            saveModel.setCzr(userInfo.getYhzh());
            Long id = service.saveOrUpdate(saveModel, mxSaveModels, (ArrayList<Map>) model.get("fj"));
            return new Result(true, id);
        } catch (NullPointerException e) {
            return new Result(false, "账户未登录，请重新登录");
        } catch (Exception e) {
            return new Result(false, "上传属性类型错误，请检查");
        }
    }

}
