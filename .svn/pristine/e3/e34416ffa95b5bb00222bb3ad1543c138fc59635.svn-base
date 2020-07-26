package com.datanew.service;

import javax.servlet.http.HttpSession;

import com.datanew.dto.Result;

public interface BglxdbaService {

	Object getBglxdbaData(String xmmc, int zt, HttpSession session);

	Result saveBglxdba(String content1, HttpSession session);

	Object selBglxdbamxByBglxdbaid(String bglxdbaid,String xmid);

	Object getGsxxByXmid(String xmid);

	Result saveBglxdba(String content1, String content2, HttpSession session);

	Result saveXmbgbaData(String content1, String content2, HttpSession session);

	Object selBglxdbaFilesByBglxdbaid(String bglxdbaid);

	Result deleteBglxdba(String ids);

	Object selectGcfymcByXmid(String xmid);

	Object selGsxxByFyidAndTzefl(String xmid, String gcfyid, String tzefl,
			String htbaid);

	Result submitSgtba(String id, HttpSession session) throws Exception;

	Result returnback(String id, HttpSession session) throws Exception;

}
