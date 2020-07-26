package com.datanew.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public  interface FileService {


    Object uploader(HttpServletRequest req);

    Object deleteById(String guid, HttpSession session);

    void downloadByid(String guid, HttpServletResponse response);

    List getFilesByids(String ids);
    
    Object uploaderJpg(HttpServletRequest req);



}
