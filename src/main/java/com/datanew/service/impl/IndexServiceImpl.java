package com.datanew.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.YhglYwYhyy;
import com.datanew.model.ZftzSplc;
import com.datanew.model.ZftzZbk;
import com.datanew.service.IndexService;
import com.datanew.service.ZbkService;
import com.datanew.util.ExcelUtil;
import com.datanew.util.StaticData;
import com.datanew.util.StringUtil;



/**
 * @author wuwei
 * @create 2019/5/17 13:57
 * @desc
 **/
@Service("indexService")
public class IndexServiceImpl implements IndexService {

    @Resource
    private BaseDao baseDao;

	@Override
	public List getXmfltz() {
		
		String sql = "select MC ,YEAR1,YEAR2,YEAR3,YEAR4,YEAR5\n" +
                "from V_ZFTZ_DP1_XMFLTZ ";
        List list = baseDao.selectMapsBySQL(sql);
        return list;
	}

	@Override
	public List getPtxmtz() {
		String sql = "select GUID,MC,TZS,SJTZS\n" +
                "from V_ZFTZ_DP1_XMTZ   order by guid";
        List list = baseDao.selectMapsBySQL(sql);
        return list;
	}

	@Override
	public Object getZjph() {
		String maxdate="1000";
		String sql = "select GUID,MC,ZXS,JHS\n" +
                "from V_ZFTZ_DP1_ZYFB   order by guid";
        List list = baseDao.selectMapsBySQL(sql);
        
        String sql1 = "select max(ZXS) as ZXS,max(JHS) as JHS\n" +
                "from V_ZFTZ_DP1_ZYFB where 1=1";
						
        List list1 = baseDao.selectMapsBySQL(sql1);
        Map map=(Map)list1.get(0);
        String zxs=map.get("ZXS").toString();
        String jhs=map.get("JHS").toString();
        if (Integer.parseInt(zxs)>Integer.parseInt(jhs)) {
			maxdate=zxs;
		}else{
			maxdate=jhs;
		}
        JSONObject jsonobject=new JSONObject();
        jsonobject.put("MAXDATE", maxdate);
        jsonobject.put("TASKLIST", list);
        
       
        return jsonobject;
        
       
	}

	@Override
	public List getJcsj() {
		String sql = "select XMS,ZTZ,NDZJJH, NDDWZJ, LJDWZJ\n" +
                "from V_ZFTZ_DP1_JCSJ    ";
        List list = baseDao.selectMapsBySQL(sql);
        return list;
	}

	@Override
	public Pages getXminfo(Pages page, HttpSession session) {
		String sql=" from V_ZFTZ_DP1_XM       where 1=1 ";
		List list =baseDao.selectMapsBySQL("select GUID,MC,ZTZ,NDZJJH,NDDWZJ,LJDWZJ,NDDWZJL,LJDWZJL  "+sql, null, page.getOffset(), page.getLimit());
        Long count =baseDao.getCountBySQL("select count(*) "+sql,null);
        page.setTotal(count.intValue());
        page.setRows(list);
		return page;
	}

	
	@Override
	public List getXmpic(String guid) {
		String sql="select guid from zftz_file  where 1=1 and filexl='99' and filebstype='14'";
		if (!StringUtil.isblank(guid)) {
			sql =sql + "  and filebsid="+guid;
		}
		sql = sql + "order by guid  desc";
		List list=baseDao.selectMapsBySQL(sql);
		
		return list;
	}
	
	@Override
	public List getXmnr(String guid) {
		String sql = "SELECT XMNR "
                + "	FROM V_ZFTZ_DP2_XMNR    ";
		if (!StringUtil.isblank(guid)) {
			sql =sql + " where guid="+guid;
		}
        List list = baseDao.selectMapsBySQL(sql);
        return list;
	}
	@Override
	public List getZxl(String guid) {
		String sql = "SELECT ZJDWL*100 as zjdwl, TZWCL*100 as tzwcl,cwzxl*100 as cwzxl"
                + "	FROM V_ZFTZ_DP2_ZXL       ";
		if (!StringUtil.isblank(guid)) {
			sql =sql + " where guid="+guid;
		}
        List list = baseDao.selectMapsBySQL(sql);
        return list;
	}

	@Override
	public Object getGcjd(String guid) {
		String sql = "select MC as name ,KGSJ as date1,JSSJ as date4,GCJD as date2,ZFJD as date3,rownum as id\n" +
                "from V_ZFTZ_DP2_SXT where 1=1";
		if(!StringUtil.isblank(guid)){
			sql=sql+" and guid="+guid;
		}					
        List list = baseDao.selectMapsBySQL(sql);
               
        String sql1 = "select min(KGSJ) as mindate,max(JSSJ) as maxdate\n" +
                "from V_ZFTZ_DP2_SXT where 1=1";
		if(!StringUtil.isblank(guid)){
			sql=sql+" and guid="+guid;
		}					
        List list1 = baseDao.selectMapsBySQL(sql1);
        Map map=(Map)list1.get(0);
        String mindate=map.get("MINDATE").toString();
        String maxdate=map.get("MAXDATE").toString();
        JSONObject jsonobject=new JSONObject();
        jsonobject.put("MINDATE", mindate);
        jsonobject.put("MAXDATE", maxdate);
        jsonobject.put("TASKLIST", list);
        
       
        return jsonobject;
	}

	@Override
	public List getZjqk(String guid) {
		String sql = "select MC,YDWZJ,WDWZJ,ZZJ "+
        " from V_ZFTZ_DP2_ZJQK     where 1=1";
		if(!StringUtil.isblank(guid)){
			sql=sql+" and guid="+guid;
		}
		sql=sql+" order by guid";
        List list = baseDao.selectMapsBySQL(sql);
        return list;
	}

	@Override
	public List getPtZxl(String guid) {
	
		String sql = "SELECT ZJDWL*100 as zjdwl, TZWCL*100 as tzwcl,cwzxl*100 as cwzxl"
                + "	FROM V_ZFTZ_DP3_PTZXL     ";
		if (!StringUtil.isblank(guid)) {
			sql =sql + " where guid="+guid;
		}
        List list = baseDao.selectMapsBySQL(sql);
        return list;
	}

	@Override
	public List getPtZjqk(String guid) {
		String sql = "select MC,YDWZJ,WDWZJ,ZZJ "+
		        " from V_ZFTZ_DP3_PTZJQK     where 1=1";
		if(!StringUtil.isblank(guid)){
			sql=sql+" and guid="+guid;
		}
		sql=sql+" order by guid";
        List list = baseDao.selectMapsBySQL(sql);
        return list;
	}

	@Override
	public List getFxyj(String guid) {
		String sql = "SELECT NR "
                + "	FROM V_ZFTZ_DP3_FXYJ      ";
		if (!StringUtil.isblank(guid)) {
			sql =sql + " where guid="+guid;
		}
        List list = baseDao.selectMapsBySQL(sql);
        return list;
	}
	
	
	@Override
	public List getXmjd(String guid) {
		String sql = "SELECT GUID,XMMC,ZX "
                + "	FROM V_ZFTZ_DP3_XMJD  ";
		if (!StringUtil.isblank(guid)) {
			sql =sql + " where guid="+guid;
		}
        List list = baseDao.selectMapsBySQL(sql);
        return list;
	}

	@Override
	public List getFbglszqk(String guid) {
		String sql = "select YEAR ,SRJH,SRZX,ZCJH,ZCZX,HZJH,HZZX\n" +
                "from V_ZFTZ_DP4_FBGLSZQK ";
		if (!StringUtil.isblank(guid)) {
			sql =sql + " where guid="+guid;
		}
        List list = baseDao.selectMapsBySQL(sql);
        return list;
	}

	@Override
	public Object getFbglndzxqk(String guid) {
		String maxdate="300";
		String sql = "select MC ,TDJH,TDZX,XMJH,XMZX\n" +
                "from V_ZFTZ_DP4_FBGLZXQK ";
		if (!StringUtil.isblank(guid)) {
			sql =sql + " where guid="+guid;
		}
		 List list = baseDao.selectMapsBySQL(sql);
		//TODO
		String sql1 = "select max(TDJH) as TDJH,max(TDZX) as TDZX,max(XMJH) as XMJH,max(XMZX) as XMZX\n" +
                "from V_ZFTZ_DP4_FBGLZXQK ";
		if (!StringUtil.isblank(guid)) {
			sql =sql + " where guid="+guid;
		}				
        List list1 = baseDao.selectMapsBySQL(sql1);
        Map map=(Map)list1.get(0);
        String tdjh=map.get("TDJH").toString();
        String tdzx=map.get("TDZX").toString();
        String xmjh=map.get("XMJH").toString();
        String xmzx=map.get("XMZX").toString();
        if (Integer.parseInt(tdjh)>Integer.parseInt(tdzx)) {
			maxdate=tdjh;
		}else{
			maxdate=tdzx;
		}
        if (Integer.parseInt(maxdate)>Integer.parseInt(xmjh)) {
        	maxdate=maxdate;
		}else{
			maxdate=xmjh;
		}
        if (Integer.parseInt(maxdate)>Integer.parseInt(xmzx)) {
        	maxdate=maxdate;
		}else{
			maxdate=xmzx;
		}
        JSONObject jsonobject=new JSONObject();
        jsonobject.put("MAXDATE", maxdate);
        jsonobject.put("TASKLIST", list);
       
        return jsonobject;
	}

	@Override
	public List getFbglzxl(String guid) {
		String sql = "select NDSR ,NDZC,NDHZ\n" +
                "from V_ZFTZ_DP4_FBGLZXL ";
		if (!StringUtil.isblank(guid)) {
			sql =sql + " where guid="+guid;
		}
        List list = baseDao.selectMapsBySQL(sql);
		return list;
	}
	

	
    

}
