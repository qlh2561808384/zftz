package com.datanew.service;

import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.dto.unalterable.$Pages;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.Xxb;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

//
public  interface XtPcService {

    public List<Xxb> queryXxb(Map<String, String> pageMap);

    public Result validateMc(Xxb t, Result result);

    public Result saveXxb(Xxb t, Result result);

    public Result updateXxb(Xxb t, Result result);

    public Result deleteXxb(Xxb t, Result result);


}
