package com.datanew.service;

import javax.servlet.http.HttpSession;

import com.datanew.dto.Result;

public interface HtbaService {

	Object getHtbaData(String id_zftz_xm, String zt, HttpSession session);

	Object selectHtlx();

	Object selectJsfs();

	Object selectSgtbamc(String xmid);

	Object selHtbamxByHtbaid(String htbaid, String xmid);

	Object selHtyjzfmxByHtbaid(String htbaid);

	Result saveHtba(String content1,int htxybj, HttpSession session);

	Result deleteHtba(String ids);

	Object getXybaData(String id_zftz_xm, String zt, HttpSession session);

	Object selectHtmc(HttpSession session);

	Object selectHtmcByXmid(String xmid);

	Object selectHtbagcmc(String htbaid);

	Object selectDygcfy(String sgtbaid);

	Object selectTzeflBySgtid(String sgtbaid);

	Object selectSgtbamcById(String xmid);

	Object selectGcfymcBySgtid(String sgtbaid);

	Object submitSgtba(String id, String isHt, HttpSession session);

	Result returnback(String id,String isHt, HttpSession session);

	Object selHtbaFilesById(String htid);

	Object selGsxxByFyidAndTzefl(String xmid, String gcfyid, String tzefl,
			String isEdit, String sgtbaid);

	Object selectSgtbaIds(String xmid);

	Object getGcmcAndFylx(String ids);

}
