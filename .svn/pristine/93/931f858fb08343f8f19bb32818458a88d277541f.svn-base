package com.datanew.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.datanew.dto.Result;

public interface SgtysbaService {

	Result saveSgtysba(String content1, String content2, String jsnr,
			String zgbmyj, String sczjbayj,HttpSession session);

	Object getSgtysbaData(String xmmc, String zt, String isCx, HttpSession session);

	Result deleteSgtysba(String ids);

	Object selSgtysbamxBySgtbaid(long sgtbaid, long xmid);

	Object selectXmmc(HttpSession session);

	Object getGsxxByXmid(String xmid);

	Object selectGcfymc();

	Object selectTzefl();

	Object selGsxxByFyidAndTzefl(String xmid, String gcfyid, String tzefl, String isEdit, String sgtbaid);

	Result submitSgtba(String id, HttpSession session) throws Exception;

	Object getYhxm();

	Result returnback(String id, String thyj, HttpSession session) throws Exception;

	Object getYbaByXmid(String xmid);

	Object selSgtbaFilesById(String sgtbaid);

	Object selectHxXmmc();

	Object selectGcfymcBySgtid(String xmid);

	Object selSgtysbamxById(String sgtbaid);

	Object selectSgthxXmmc();

	Result selThyj(String id);

}
