package com.datanew.service;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.FbyxZwgl;
import com.datanew.model.FbyxZygl;
import com.datanew.model.Xxb;
import com.datanew.util.ExcelUtil;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.restlet.util.StringReadingListener;

import java.util.List;

public interface ZbkService {


    public Pages getZbk(Pages page,String xmlx, HttpSession session);

    public Result saveZbk(String guid,String xmlx,String yjzb,String ejzb,String sjzb,String dwtze,String jldw,String bz, HttpSession session);

    public Result delZbk(String guid, HttpSession session);

    public void insertExcel(List<String[]>  list, HttpSession session);
    
    public ExcelUtil exportExcelByLx(String xmlx, HttpServletResponse res);
}
