package com.datanew.service;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.*;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface NdjhService {

    public List<Xxb> queryNd();

    public List queryXzqh();

    public String findZylx(String id);

    public Pages queryGsnjh(Pages page, String nd,String ptgs, HttpSession session);

    Long insertXtFj(XtFj fj);

    List fileInfo(String uuid);

    Result delFj(String id, Result result);

    public List queryZycTree(String nd,String ptgs);

    public Long getZmlxByZy(String id);

    public List queryXmcTree();

    public List queryZwcTree(String ptgs);

    public Pages getSrbList(Pages page, String nd,String ptgs, HttpSession session);

    public Pages getSrbDzList(Pages page, String srid, HttpSession session);

    public Result validateSrb(String zymcid, HttpSession session);

    public Result validateZcb(String xmid,String nd, HttpSession session);

    public Result validateHzb(String zwid,String nd, HttpSession session);

    public Result saveOrUpdateBySrb(FbyxNdsrb t,String ptgs, HttpSession session,Result result);

    public $Result deleteBySrb(String  id, HttpSession session);

    public $Result deleteBySrDz(String  id, HttpSession session);

    public Pages getZcbList(Pages page, String nd,String ptgs, HttpSession session);

    public $Result saveOrUpdateByZcb(FbyxNdzcjh t, String ptgs, HttpSession session);

    public $Result deleteByZcb(String  id, HttpSession session);

    public Pages getHzbList(Pages page, String nd,String ptgs, HttpSession session);

    public $Result saveOrUpdateByHzb(FbyxNdhzb t, String ptgs, HttpSession session);

    public $Result deleteByHzb(String  id, HttpSession session);

    public $Result saveOrUpdateBySrbDz(FbyxNdsrjhDz t, String srid, HttpSession session);

    public void insertExcelNc(List<String[]>  list,String ptgs, HttpSession session);

    public void insertExcelTz(List<String[]>  list,String ptgs, HttpSession session);

    public void insertExcel2(List<String[]>  list,String ptgs, HttpSession session);

    public void insertExcel3(List<String[]>  list,String ptgs, HttpSession session);

    public String[][] exportexcel1(String nd,String ptgs);

    public String[][] exportexcel2(String nd,String ptgs);

    public String[][] exportexcel3(String nd,String ptgs);
}
