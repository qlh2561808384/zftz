package com.datanew.service;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.FbyxZwgl;
import com.datanew.model.FbyxZygl;
import com.datanew.model.Xxb;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ZwService {


    public Pages getZwList(Pages page, String zwmc, String ptgs, HttpSession session);

    public Result validateBm(String bm, HttpSession session);

    public $Result saveOrUpdate(FbyxZwgl t, HttpSession session);

    public $Result delete(String id, HttpSession session);

    public void insertExcel(List<String[]> list, HttpSession session);

    public String[][] exportexcel(String zwmc,String ptgs);
}
