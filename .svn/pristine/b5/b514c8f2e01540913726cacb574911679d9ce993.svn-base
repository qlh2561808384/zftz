package com.datanew.action;

import com.datanew.dto.Result;
import com.datanew.service.AlkService;
import com.datanew.util.ExcelCreator;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 案例库控制器
 *
 * @author inRush
 * @date 2019/6/25
 **/
@Controller
@ResponseBody
@RequestMapping(value = "/alk")
public class AlkController {
    @Autowired
    AlkService service;

    @RequestMapping(value = "columnsDefine/{bm}", method = RequestMethod.GET)
    public Result getColumnsDefine(@PathVariable String bm) {
        try {
            List<Map> list = service.getColumnsDefine(bm);
            return new Result(true, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "服务器异常！");
        }
    }

    @RequestMapping(value = "admin/columnsDefine/save", method = RequestMethod.POST)
    public Result saveColumnsDefine(@RequestBody Map map) {
        try {
            List<Map> columns = (List<Map>) map.get("columns");
            String xmlx = (String) map.get("xmlx");
            service.saveColumnsDefine(xmlx, columns);
            return new Result(true, "保存成功!");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "服务器异常");
        }
    }

    @RequestMapping(value = "admin/columnsDefine/delete", method = RequestMethod.POST)
    public Result deleteColumnsDefine(@RequestBody Map map) {
        try {
            List<Integer> ids = (List<Integer>) map.get("ids");
            service.deleteColumnsById(ids);
            return new Result(true, "删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "服务器异常");
        }
    }

    @RequestMapping(value = "admin/bsTable/info/{bm}", method = RequestMethod.GET)
    public Result getBsTableInfo(@PathVariable String bm) {
        try {
            Map info = service.getBsTableInfo(bm);
            return new Result(true, info);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "服务器异常");
        }
    }

    @RequestMapping(value = "excel/parse/{bm}", method = RequestMethod.POST)
    public Result parseExcelData(MultipartFile file, @PathVariable String bm) {
        List<Map> list;
        try {
            list = service.parseExcelData(file, bm);
            return new Result(true, list);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, "Excel 格式异常，请检查格式");
        }
    }

    @RequestMapping(value = "excel/export/{bm}", method = RequestMethod.GET)
    public void exportExcelData(HttpServletResponse response, @PathVariable String bm, Boolean getValue) {
        service.exportExcelData(response, bm, getValue);
    }

    @RequestMapping(value = "list/{bm}", method = RequestMethod.GET)
    public Result getData(@PathVariable String bm) {
        try {
            List<Map> data = service.getData(bm);
            return new Result(true, data);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "服务器异常");
        }
    }

    @RequestMapping(value = "save/{bm}", method = RequestMethod.POST)
    public Result save(@RequestBody Map map, @PathVariable String bm) {
        try {
            service.save((ArrayList<Map<String, String>>) map.get("data"), bm);
            return new Result(true, "保存成功");
        } catch (ServiceException e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "服务器异常");
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result delete(@RequestBody Map map) {
        try {
            service.delete((List<Integer>) map.get("ids"));
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "服务器异常");
        }
    }
}
