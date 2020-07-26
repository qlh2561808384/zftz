package com.datanew.service;

import com.datanew.dto.Pages;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.FbyxNdsrb;
import com.datanew.model.FbyxNdsrjhDz;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ZxtzService {

    public Pages getZyzxList(Pages page, String nd, String ptgs, String zymc, HttpSession session);

    public $Result updateByZyzx(FbyxNdsrb t, String ptgs, HttpSession session);

    public $Result updateByZyzxDz(FbyxNdsrjhDz t, String srid, HttpSession session);

    public void insertExcel(List<String[]> list, String ptgs, HttpSession session);

    public String[][] exportexcel(String nd,String ptgs,String zymc);

}
