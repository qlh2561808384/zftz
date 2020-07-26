package com.datanew.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.datanew.dao.BaseDao;
import com.datanew.dto.Result;
import com.datanew.model.Xmktdba;
import com.datanew.model.YhglYwYhyy;
import com.datanew.model.ZftzFile;
import com.datanew.service.XcktdbaService;
import com.datanew.util.StaticData;
import com.datanew.util.StringUtil;

@Service("xcktdbaService")
public class XcktdbaServiceImpl implements XcktdbaService {
	
	@Autowired
	private BaseDao baseDao;

	@Override
	public Object getXmxxByHtid(String htbaid) {
		String sql = "select h.id_zftz_xm as xmid,x.xmmc as xmmc from " +
				"zftz_htba h,zftz_xm x where h.id_zftz_xm=x.id and h.id="+htbaid;
		return StringUtil.lowList(baseDao.selectMapsBySQL(sql));
	}

	@Override
	public Object getZfxxByHtid(String htbaid) {
		/*String sql = "select v.id,v.yjzfsj,round(v.yjzfje/10000,2) yjzfje,v.yjxxjd,v.zfrq,round(v.je/10000,2),v.zfkxsm,v.zfzt " +
				"from v_zftz_zfjdmx v where v.id_zftz_htba="+htbaid+" order by v.yjzfsj";*/
		String sql = "select zfmx.yjzfsj as yjzfsj,round(zfmx.yjzfje/10000,2) as yjzfje " +
				"from zftz_htyjzfmx zfmx where zfmx.id_zftz_htba="+htbaid;
		/*List<Map<String,Object>> map = (List<Map<String, Object>>) baseDao.selectMapsBySQL(sql);
		System.out.println(map);*/
		return StringUtil.lowList(baseDao.selectMapsBySQL(sql));
	}

	@Override
	public Result saveXcktdData(String content,HttpSession session) {
		Result result = new Result();
		long num = 0;
		String dqrqStr = "";
		// TODO Auto-generated method stub
		String countSql = "select max(ktdh) from zftz_xmktdba where to_number(to_char(czsj,'yyyymmdd'))=to_number(to_char(sysdate,'yyyymmdd'))";
		List l = baseDao.selectBySql(countSql);
		if(l.size()>0){
			if(l.get(0)!=null){
				num = Long.valueOf(l.get(0).toString());
				dqrqStr = String.valueOf(num+1);
			}else{
				dqrqStr = StringUtil.getRqStr();
			}
		}
		long dh = Long.parseLong(dqrqStr);
		YhglYwYhyy yhglYwYhyy = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
		List<Xmktdba> xmktdbaList = JSONArray.parseArray(content,Xmktdba.class);
		Xmktdba xmktdba = xmktdbaList.get(0);
		xmktdba.setCzr(yhglYwYhyy.getGuid().toString());
		xmktdba.setCzsj(new Date());
		if(xmktdba.getKtdh()==null||xmktdba.getKtdh().equals("")){
			xmktdba.setKtdh(String.valueOf(dh));
		}
		//System.out.println(xmktdba.getKtdh());
		xmktdba.setLchj(-1);
		xmktdba.setZt(1);
		baseDao.saveOrUpdate(xmktdba);
		List<ZftzFile> zFiles = xmktdbaList.get(0).getZftzfiles();
		if(!zFiles.isEmpty()){
			for(int i=0;i<zFiles.size();i++){
				String sql = "update zftz_file set filedl=8," +
						"filebsid="+xmktdba.getId()+" where guid="+zFiles.get(i).getGuid();
				baseDao.executeBySql(sql);
			}
		}
		result.setContent(xmktdba.getId());
		result.setSuccess(true);
		return result;
	}

	@Override
	public Object getXcktdbaData(String id_zftz_xm, String zt,HttpSession session) {
		YhglYwYhyy yhglYwYhyy = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
		String hql = "select s.id as id,s.id_zftz_xm as xmid,s.id_zftz_htba as htbaid," +
				"s.jsdw,s.sgdw,s.cyshjdnr,s.rq,s.xxjd,s.cyshjdqk,s.jsdwyj," +
				"s.sgdwyj,s.jldwyj,s.lchj,s.zt,s.czsj,s.czr,s.ktdh from zftz_xmktdba s where s.zt=1";
		//String hql = "select * from zftz_xmktdba s where s.zt=1";
		
		String xm_ids = "";
		String xmidSql = "select id from zftz_xm where " +
				//"jsdw="+yhglYwYhyy.getSzdwid();
				"jsdw in(select id from v_zftz_jsdw where id " +
				"in(select entid from v_zftz_yhtoenter where guid="+yhglYwYhyy.getSzdwid()+"))";
		List xmidList = baseDao.selectBySql(xmidSql);
		if(xmidList.size()>0){
			for(int i=0;i<xmidList.size();i++){
				xm_ids+=xmidList.get(i)+",";
			}
			hql+=" and s.id_zftz_xm in("+xm_ids.substring(0, xm_ids.length()-1)+")";
		}
		
		String sql = "select id from zftz_xm where xmmc like '%"+id_zftz_xm+"%'";
		List l = baseDao.selectBySql(sql);
		String ids = "";
		if(id_zftz_xm!=""){
			if(l.size()>0){
				for(int i=0;i<l.size();i++){
					//String xmid = l.get(i).toString();
					if(l.get(i).toString()!=""){
						//hql += " and s.id_zftz_xm='"+xmid+"'";
						ids+=l.get(i).toString()+",";
					}
				}
				hql+=" and s.id_zftz_xm in ("+ids.substring(0, ids.length()-1)+")";
			}
		}
		if(zt!=""&&zt!=null){
			if(zt.equals("1")){
				hql += " and s.lchj=-1";
			}else if(zt.equals("2")){
				hql += " and s.lchj!=-1";
			}
		}
		hql+=" order by s.czsj desc";
		return StringUtil.lowList(baseDao.selectMapsBySQL(hql));
		//return baseDao.selectByHql(hql);
	}

	@Override
	public Result deleteXcktdba(String ids) {
		Result result = new Result();
		String sql = "update zftz_xmktdba set zt=0 where id in ("+ids+")";
		baseDao.executeBySql(sql);
		result.setSuccess(true);
		return result;
	}

	@Override
	public Object selectHtmc() {
		String sql = "select id,gcmc from zftz_htba";
		
		return StringUtil.lowList(baseDao.selectMapsBySQL(sql));
	}

	@Override
	public Object selXcktdFilesByXcktdba(String xcktdbaid) {
		String sql = "select guid,filebstype,filebsid,filename,filesize from zftz_file where filebstype=8 and filebsid="+xcktdbaid;
		return StringUtil.lowList(baseDao.selectMapsBySQL(sql));
	}
	
	@Override
	public Object selHtmc(HttpSession session) {
		YhglYwYhyy yhglYwYhyy = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
		/*String sql = "Select id,htmc,gcmc from zftz_htba where zt=1 and lchj!=-1";
		String xmids = "";
		String xmidSql = "select id from zftz_xm where jsdw="+yhglYwYhyy.getSzdwid();
		List xmidList = baseDao.selectBySql(xmidSql);
		if(xmidList.size()>0){
			for(int i=0;i<xmidList.size();i++){
				xmids+=xmidList.get(i)+",";
			}
			sl+=" and id_zftz_xm in("+xmids.substring(0, xmids.length()-1)+")";
		}*/
		String sql = "select ht.* from zftz_htba ht where ht.zt=1 and " +
				"ht.id_zftz_xm in(select id from zftz_xm where zt=1 and jsdw="+yhglYwYhyy.getSzdwid()+") " +
				"and id not in (select z.id_zftz_htba from zftz_xmgcjsba z " +
				"where zt=1 and z.jsdw="+yhglYwYhyy.getSzdwid()+")";
		return StringUtil.lowList(baseDao.selectMapsBySQL(sql));
	}

	@Override
	public Object selectXmmcByJsdw(String jsdw) {
		String sql = "select id,xmmc from zftz_xm m where m.jsdw='" +jsdw+
				"' and m.zt=1 and m.id not in (select s.id_zftz_xm from  " +
				"zftz_xmjgjs s where s.zt=1 and jsdw='"+jsdw+"') order by m.xmmc";
		return StringUtil.lowList(baseDao.selectMapsBySQL(sql));
	}

	@Override
	public Object selectGcxxByXmid(String xmid) {
		String sql = "select ht.id as id,ht.gcmc as gcmc from zftz_htba ht where ht.zt=1 and ht.id_zftz_xm='" +xmid+
				"' and id not in (select z.id_zftz_htba from zftz_xmgcjsba z where zt=1)";
		return StringUtil.lowList(baseDao.selectMapsBySQL(sql));
	}

	@Override
	public Object selectJsdw() {
		String sql = "select id,mc from v_zftz_jsdw";
		return StringUtil.lowList(baseDao.selectMapsBySQL(sql));
	}

	@Override
	public Object getYzfAndYeByHtid(String htbaid) {
		String sql = "select round(nvl(sum(nvl(zf.je, 0)),0)/10000,2) as yzfje,round(nvl((sum(nvl(ht.htje, 0)) - sum(nvl(zf.je, 0))),0)/10000,2) as ye " +
				"from zftz_xmzxdj_zfmx zf,zftz_htba ht where " +
				"zf.id_zftz_htba=ht.id and ht.id="+htbaid;
		return StringUtil.lowList(baseDao.selectMapsBySQL(sql));
	}

}
