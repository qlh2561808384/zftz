package com.datanew.action;

import com.datanew.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author kedou
 * @create 2018-11-20 17:15
 * @see
 **/
@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "uploader",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Object uploader(HttpServletRequest req, HttpSession session){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;

        return  fileService.uploader(req);
    }
    @ResponseBody
    @RequestMapping("deleteById")
    public Object deleteById(String id, HttpSession session){

        return  fileService.deleteById(id,session);
    }

    @ResponseBody
    @RequestMapping("downloadByid")
    public void downloadByid(String id, HttpServletResponse response){

          fileService.downloadByid(id,response);
    }

    @ResponseBody
    @RequestMapping("getFilesByids")
    public Object getFilesByids(String ids){

       List list =   fileService.getFilesByids(ids);
       return list;
    }
    
  
    

    @RequestMapping(value = "uploaderJpg",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Object uploaderJpg(HttpServletRequest req, HttpSession session){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;

        return  fileService.uploaderJpg(req);
    }
   



}
