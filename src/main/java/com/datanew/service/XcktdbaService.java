package com.datanew.service;

import javax.servlet.http.HttpSession;

import com.datanew.dto.Result;

public interface XcktdbaService {

	Object getXmxxByHtid(String htbaid);

	Object getZfxxByHtid(String htbaid);

	Result saveXcktdData(String content,HttpSession session);

	Object getXcktdbaData(String id_zftz_xm, String zt,HttpSession session);

	Result deleteXcktdba(String ids);

	Object selectHtmc();

	Object selXcktdFilesByXcktdba(String xcktdbaid);

	Object selHtmc(HttpSession session);

	Object selectXmmcByJsdw(String jsdw);

	Object selectGcxxByXmid(String xmid);

	Object selectJsdw();

	Object getYzfAndYeByHtid(String htbaid);

}
