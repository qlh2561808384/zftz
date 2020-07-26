package com.datanew.service;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.FbyxZygl;
import com.datanew.model.Xxb;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface ZycService {

    public List queryPtgs();

    public List<Xxb> queryZyzl();

    public Pages getZycList(Pages page, String zymc, String zylx,String ptgs, HttpSession session);

    public Result validateBm(String bm, HttpSession session);

    public $Result saveOrUpdate(FbyxZygl t, HttpSession session);

    public $Result delete(String  id, HttpSession session);

    public void insertExcel(List<String[]>  list, HttpSession session);

    public String[][] exportexcel(String zymc,String zmlx,String ptgs);


}
