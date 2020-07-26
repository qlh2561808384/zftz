package com.datanew.service;

import com.datanew.dto.Pages;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.FbyxNdsrb;
import com.datanew.model.FbyxZwzxgl;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ZwzxService {

    public Pages getZwzxList(Pages page, String nd, String ptgs,String zwmc, HttpSession session);

    public Pages getHzbmxList(Pages page, String srid, HttpSession session);

    public $Result saveOrUpdateByZwzx(FbyxZwzxgl t, HttpSession session);

    public $Result delete(String  id, HttpSession session);

    public void insertExcel(List<String[]> list, String ptgs, HttpSession session);

    public String[][] exportexcel(String nd,String ptgs,String zymc);


}
