package com.datanew.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.tools.ant.taskdefs.Delete;
import org.hibernate.engine.jdbc.spi.ResultSetReturn;
import org.springframework.stereotype.Service;

import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.XtglDmYy;
import com.datanew.model.YhglDmYysplc;
import com.datanew.model.YhglGgRyqz;
import com.datanew.model.YhglGgRyqzfb;
import com.datanew.model.YhglYwYhyy;
import com.datanew.model.ZftzSplc;
import com.datanew.service.YhglGgRyqzService;
import com.datanew.util.ConfigureParser;
import com.datanew.util.StaticData;
import com.datanew.util.StringUtil;
@Service("YhglGgRyqzService")
public  class  YhglGgRyqzServiceImpl implements YhglGgRyqzService {

    @Resource
    private BaseDao baseDao;

	@Override
	public Pages getAppylToRyqzInfo(Pages page) {
		String hql1="select y.guid,y.mc,y.fzlx,y.ms,y.cjyhid,y.zt,y.ssyybm ,y.cjsj,y.fzlxsql,"
				+ "(select to_char(wmsys.wm_concat(a.qzval))  from Yhgl_Gg_Ryqzfb a where a.qzid = y.guid) as fzlxval";
		String hql=" from Yhgl_Gg_Ryqz y where y.ssyybm= 'YHGL' and y.zt!='2'";
		List list =baseDao.selectMapsBySQL(hql1+hql, null, page.getOffset(), page.getLimit());
        Long count =baseDao.getCountBySQL("select count(*) "+hql,null);
        page.setTotal(count.intValue());
        page.setRows(list);
		return page;
	}
	
	@Override
	public List getApplyTree() {
		String sql="select bm as id,mc as name, 0 as isleaf,0 as pId from XTGL_DM_YY y where y.zt='1' and y.bm!='CZTSLC'";
		List list =baseDao.selectMapsBySQL(sql);
		return list;
	}

	@Override
	public Result saveRyqz(YhglGgRyqz yhglggryqz, Result result,HttpSession session) {
			Date date=new Date();
			String fzlxval=yhglggryqz.getFzlxval();
			yhglggryqz.setRegicode(((YhglYwYhyy)session.getAttribute(StaticData.LOGINUSER)).getRegicode());
			yhglggryqz.setSsyybm("YHGL");
			yhglggryqz.setCjsj(date);
			yhglggryqz.setCjyhid(((YhglYwYhyy)session.getAttribute(StaticData.LOGINUSER)).getGuid());
			yhglggryqz.setFzlxval("");
			baseDao.save(yhglggryqz);
			if(!StringUtil.isblank(fzlxval)){
				String[] fzlxvals=fzlxval.split(",");
				for (int i = 0; i < fzlxvals.length; i++) {
					YhglGgRyqzfb yhglggryqzfb=new YhglGgRyqzfb();
					yhglggryqzfb.setQzid(yhglggryqz.getGuid());
					yhglggryqzfb.setQzval(Long.parseLong(fzlxvals[i]));
					baseDao.save(yhglggryqzfb);
				}
			}
			
			result.setContent("新增成功");
			result.setSuccess(true);
		return result;
		
	}

	@Override
	public Result delRyqz(Long id, Result result) {
		String sql="update YHGL_GG_RYQZ set zt='2' where guid="+id;
		baseDao.executeBySql(sql);
		baseDao.executeBySql("delete YHGL_GG_RYQZFB where qzid="+id );
		result.setContent("删除成功");
		result.setSuccess(true);
		return result;
	}

	@Override
	public List getchoiceApplyTree(String id) {
		if(!StringUtil.isblank(id)){
			
			if("1".equals(id)){
				String sql="select y.mc as name,y.guid as id,y.pid as pid ,y.sfdj  as isleaf from YHGL_DM_DW y where   y.yybm='YHGL' order by y.BM";
				List list =baseDao.selectMapsBySQL(sql);
				return list;
			}else if("2".equals(id)){
				String sql="select  y.guid as id,y.mc as name, 1 as isleaf,0 as pId from YHGL_DM_YYGW y where y.zt='1' and y.ssyybm='YHGL'";
				List list =baseDao.selectMapsBySQL(sql);
				return list;
			}else if("3".equals(id)){
//				String sql="select  y.guid as id,y.xm as name, 1 as isleaf,0 as pId from YHGL_YW_YH y left join YHGL_YW_YHYY h on y.guid=h.YHID where y.zt='1' and h.zt='1' and h.yybm='YHGL'";
				String sql="select a.id as id,a.name as name,a.isleaf as isleaf,a.pid    as pId from (select h.guid   as id,y.xm     as name,1 as isleaf,y.yhszdw as pId"
						+ " from YHGL_YW_YH y left join YHGL_YW_YHYY h on y.guid = h.YHID where y.zt = '1' and h.zt = '1' and h.yybm = 'YHGL' union"
						+ "  select z.guid as id,z.mc   as name,0 as isleaf,z.pid  as pid"
						+ " from YHGL_DM_DW z where z.yybm = 'YHGL' and z.guid in (select y.yhszdw"
						+ " from YHGL_YW_YH y left join YHGL_YW_YHYY h on y.guid = h.YHID where y.zt = '1' and h.zt = '1' and h.yybm = 'YHGL') )a";
				List list =baseDao.selectMapsBySQL(sql);
				return list;
			}
			List list=new ArrayList();
			return list; 
		}else{
			List list=new ArrayList();
			return list; 
		}
		
		
	}

	@Override
	public List getchoiceApplyTrees(String id) {
		if(!StringUtil.isblank(id)){			
			if("1".equals(id)){
				String sql="select y.mc as name,y.guid as id,y.pid as pid ,y.sfdj  as isleaf from YHGL_DM_DW y where   y.yybm='YHGL' order by y.BM";
				List list =baseDao.selectMapsBySQL(sql);
				return list;
			}else if("2".equals(id)){
				String sql="select  y.guid as id,y.mc as name, 1 as isleaf,0 as pId from YHGL_DM_YYGW y where y.zt='1' and y.ssyybm='YHGL' ";
				List list =baseDao.selectMapsBySQL(sql);
				return list;
			}else if("3".equals(id)){
				String sql="select  h.guid as id,y.xm as name, 1 as isleaf,0 as pId from YHGL_YW_YH y left join YHGL_YW_YHYY h on y.guid=h.YHID where y.zt='1' and h.zt='1' and h.yybm='YHGL'";
				List list =baseDao.selectMapsBySQL(sql);
				return list;
			}
			List list=new ArrayList();
			return list; 
		}else{
			List list=new ArrayList();
			return list; 
		}
	}

	@Override
	public Result saveEditRyqz(String guid, String mc, String fzlx, String fzlxval, String ms, String zt,String fzlxsql, Result result,HttpSession session) {
		baseDao.executeBySql("delete YHGL_GG_RYQZFB where qzid="+guid);
		YhglGgRyqz yhglggryqz=(YhglGgRyqz)baseDao.load(YhglGgRyqz.class, Long.parseLong(guid));
		yhglggryqz.setMc(mc);
		yhglggryqz.setFzlx(fzlx);
		yhglggryqz.setMs(ms);
		yhglggryqz.setZt(zt);
		yhglggryqz.setRegicode(((YhglYwYhyy)session.getAttribute(StaticData.LOGINUSER)).getRegicode());
		yhglggryqz.setFzlxsql(fzlxsql);
		baseDao.update(yhglggryqz);
		if(!StringUtil.isblank(fzlxval)){
			String[] fzlxvals=fzlxval.split(",");			
			for (int i = 0; i < fzlxvals.length; i++) {
				YhglGgRyqzfb yhglggryqzfb=new YhglGgRyqzfb();
				yhglggryqzfb.setQzid(yhglggryqz.getGuid());
				yhglggryqzfb.setQzval(Long.parseLong(fzlxvals[i]));
				baseDao.save(yhglggryqzfb);
			}
		}
		
		result.setContent("修改成功");
		result.setSuccess(true);
		return result;
	}

	@Override
	public List getRyqzTree() {
	        String sql="select mc as NAME,guid as ID,0 as PID ,1 as isleaf from YHGL_GG_RYQZ where zt='1' and ssyybm='YHGL'";
			List list =baseDao.selectMapsBySQL(sql);
			return list;
	    
	}

	@Override
	public List getsxTree() {
		String sql="select bm as id,mc as name, 0 as isleaf,0 as pId from zftz_xxb where lx='sxlx' and yxbz='Y' ";
		List list =baseDao.selectMapsBySQL(sql);
		
		return list;
	}

	@Override
	public Pages getSplcInfo(Pages page, String id) {
		if(id!=null){
			String sql=" from   zftz_splc  y where  y.sxbm= '"+id+"'  order by y.dqhjbm";
			List list =baseDao.selectMapsBySQL("select *"+sql, null, page.getOffset(), page.getLimit());
	        Long count =baseDao.getCountBySQL("select count(*) "+sql,null);
	        page.setTotal(count.intValue());
	        page.setRows(list);
			
		}else{
			page.setTotal(0);
			page.setRows(new ArrayList());
		}
		
        return page;
	}

	@Override
	public Result saveSplcInfo(String id, String dqhjbm, String xyhjbm, String dqhjmc, String czlx, String sprfzid,
			Result result, HttpSession session) {
			String[] dqhjbms=dqhjbm.split(" ");
			String[] xyhjbms=xyhjbm.split(" ");
			String[] dqhjmcs=dqhjmc.split(" ");
			String[] czlxs=czlx.split(" ");
			String[] sprfzids=sprfzid.split(" ");
			baseDao.executeBySql("delete from ZFTZ_SPLC where sxbm='"+id+"'");		
				for (int i = 0; i < dqhjbms.length; i++) {
					ZftzSplc zftzsplc=new ZftzSplc();
					zftzsplc.setSxbm(id);
					zftzsplc.setDqhjbm(dqhjbms[i]);
					zftzsplc.setXyhjbm(xyhjbms[i]);
					zftzsplc.setDqhjmc(dqhjmcs[i]);
					zftzsplc.setCzlx(czlxs[i]);
					zftzsplc.setSprfzid(Long.parseLong(sprfzids[i]));
					zftzsplc.setCjsj(new Date());
					baseDao.save(zftzsplc);	
					
				}
				result.setContent("保存成功");
				result.setSuccess(true);
				return result;
	}

	@Override
	public List getsxList() {
		List list=baseDao.selectMapsBySQL("select bm as id,mc as text from zftz_xxb where lx='xmlx' and yxbz='Y' ");
		List list2=new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Map map =  new HashMap();
			String id=((Map)list.get(i)).get("ID").toString();
			String text=((Map)list.get(i)).get("TEXT").toString();
			map.remove(map);
			map.put("id", id);
			map.put("text", text);
			list2.add(map);
		}
		return list2;
	}		   

}
