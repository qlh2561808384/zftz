package com.datanew.service;

import com.datanew.dto.Pages;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.FbyxZygl;
import com.datanew.model.Xxb;
import com.datanew.model.ZftzXm;
import com.datanew.model.ZftzXmtzwh;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface XmcService {

    public List queryPtgs();

    public List queryXmlx();

    public List queryJsdw();

    public List queryXmzt();

    public Pages getXmcList(Pages page, String sffb, String xmmc,String xmlx,String ptgs, HttpSession session);

    public $Result update(ZftzXm t, ZftzXmtzwh z, HttpSession session);



}
