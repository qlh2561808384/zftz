package com.datanew.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.datanew.dao.BaseDao;
import com.datanew.dto.Result;
import com.datanew.model.Sgtysba;
import com.datanew.model.Sgtysbamx;
import com.datanew.model.YhglYwYhyy;
import com.datanew.model.ZftzFile;
import com.datanew.service.SgtysbaService;
import com.datanew.service.xmgcjjs.FlowService;
import com.datanew.service.xmgcjjs.impl.FlowServiceImpl;
import com.datanew.util.StaticData;
import com.datanew.util.StringUtil;

@Service("sgtysbaService")
public class SgtysbaServiceImpl implements SgtysbaService{
	private static final String SX_CODE = "4";
	@Autowired
    BaseDao baseDao;
	@Autowired
    FlowService flowService;
	@Override
	public Result saveSgtysba(String content1, String content2, String jsnr,
			String zgbmyj, String sczjbayj,HttpSession session) {
		Result result = new Result();
		// TODO Auto-generated method stub
		YhglYwYhyy yhglYwYhyy = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
		List<Sgtysba> sgtysbalist = JSONArray.parseArray(content1,Sgtysba.class);
		System.out.println(sgtysbalist.get(0).getBcbagcmc());
		Sgtysba sgtysba = sgtysbalist.get(0);
		sgtysba.setBcbajsnr(jsnr);
		sgtysba.setZgbmyj(zgbmyj);
		sgtysba.setSczjbayj(sczjbayj);
		sgtysba.setLchj(-1);
		sgtysba.setZt(1);
		sgtysba.setCzr(yhglYwYhyy.getGuid().toString());
		sgtysba.setCzsj(new Date());
		sgtysba.setBcsgtys(sgtysba.getBcsgtys()*10000);
		baseDao.saveOrUpdate(sgtysba);
		if(sgtysbalist.get(0).getId()>0){
			String delSql = "delete from zftz_sgtbamx where ID_ZFTZ_SGTBA="+sgtysbalist.get(0).getId();
			baseDao.executeBySql(delSql);
		}
		//施工图备案明细
		List<Sgtysbamx> sgtysbamxlist = sgtysbalist.get(0).getSgtysbamx();
		if(!sgtysbamxlist.isEmpty()){
			for (int i = 0; i < sgtysbamxlist.size(); i++) {
				sgtysbamxlist.get(i).setSgtbaid(sgtysbalist.get(0).getId());
				sgtysbamxlist.get(i).setBcsgtys(sgtysbamxlist.get(i).getBcsgtys()*10000);
                baseDao.save(sgtysbamxlist.get(i));
            }
		}
		//附件信息
		List<ZftzFile> zFiles = sgtysbalist.get(0).getZftzFiles();
		if(!zFiles.isEmpty()){
			for(int i=0;i<zFiles.size();i++){
				String sql = "update zftz_file set filedl=4,filexl="+zFiles.get(i).getFilexl()+"," +
						"filebsid="+sgtysba.getId()+" where guid="+zFiles.get(i).getGuid();
				baseDao.executeBySql(sql);
			}
		}
		result.setContent(sgtysba.getId());
		result.setSuccess(true);
		return result;
	}

	@Override
	public Object getSgtysbaData(String xmmc, String lchj,String isCx,HttpSession session) {
		YhglYwYhyy yhglYwYhyy = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
		String s = "select * from (";
		String sgtSql = "select a.id as id,a.id_zftz_xm as id_zftz_xm,a.jsdwlxr as jsdwlxr," +
				"a.lxdh as lxdh,round(a.bcsgtys/10000,2) as bcsgtys,a.bcbagcmc as bcbagcmc,a.bcsgtysshdw as bcsgtysshdw," +
				"a.bcbajsnr as bcbajsnr,a.zgbmyj as zgbmyj,a.sczjbayj as sczjbayj,a.czsj as czsj,a.czr as czr," +
				"case when a.lchj = 0 then '已审核' when a.lchj !=0 and (select czlx from zftz_shjl sh " +
				"where sh.guid=(select max(guid) from zftz_shjl l where l.sxlx='4' and l.xmid=a.id))=2 then '退回' " +
				"when a.lchj !=-1 and a.lchj !=0 and (select czlx from zftz_shjl sh " +
				"where sh.guid=(select max(guid) from zftz_shjl l where l.sxlx='4' and l.xmid=a.id))=1 then '待核实' " +
				"else '未备案' end zt1,"+
				"a.lchj as lchj from zftz_sgtba a,zftz_xm xm where a.id_zftz_xm=xm.id and a.zt=1"+
				" and xm.id in(select x.id from zftz_xm x where x.zt=1 and x.jsdw in(" +
				"select vy.entid from v_zftz_yhtoenter vy " +
				"where vy.guid="+yhglYwYhyy.getGuid()+")) and xm.xmmc like '%"+xmmc+"%'";
		
		/*String sgtSql = "select a.id as id,a.id_zftz_xm as id_zftz_xm,a.jsdwlxr as jsdwlxr," +
				"a.lxdh as lxdh,round(a.bcsgtys/10000,2) as bcsgtys,a.bcbagcmc as bcbagcmc," +
				"a.bcsgtysshdw as bcsgtysshdw,a.bcbajsnr as bcbajsnr,a.zgbmyj as zgbmyj," +
				"a.sczjbayj as sczjbayj,a.czsj as czsj,a.czr as czr,case when " +
				" from zftz_sgtba a ,zftz_gg_ryqzyh b ,v_zftz_yhtoenter c,zftz_xm d," +
				"(select * from zftz_shjl where  sxlx='4' and guid in (select max(t.guid) from zftz_shjl t " +
				"where t.sxlx='4' group by t.xmid)) e where a.id=b.ywid and a.id=e.xmid and a.id_zftz_xm=d.id " +
				"and d.jsdw=c.entid and c.guid="+yhglYwYhyy.getGuid()+" and b.sxlx='4'";*/
			
		s+= sgtSql;
		String lchjSql = "select dqhjbm from zftz_splc where sprfzid in(select " +
				"qzid from yhgl_gg_ryqzfb where qzval="+yhglYwYhyy.getGuid()+") and sxbm=4";
		List lchjbm = baseDao.selectBySql(lchjSql);
		String dqlcbm = "";
		if(lchjbm.size()>0){
			dqlcbm = lchjbm.get(0).toString();
			//s+=" and a.lchj="+dqlcbm;
		}
		if(lchj!=""&&lchj!=null){
			if(lchj.equals("1")){
				s += " and a.lchj=-1";
				if(dqlcbm!=""){
					s+=" and a.lchj="+dqlcbm;
				}
			}else if(lchj.equals("2")){
				s += " and a.lchj!=-1";
				if(dqlcbm!=""){
					s+=" and a.lchj="+dqlcbm;
				}
			}else{
				s+=" and a.id in(select l.xmid from zftz_shjl l where l.sxlx=4 and l.clyhid="+yhglYwYhyy.getGuid()+")";
			}
		}
		//String sql = s+" union "+sgtSql+" and a.id in(select l.xmid from zftz_shjl l where l.sxlx=4 and l.clyhid="+yhglYwYhyy.getGuid()+")) order by czsj desc";
		String sql = s+") order by czsj desc";
		return StringUtil.lowList(baseDao.selectMapsBySQL(sql));
	}

	@Override
	public Result deleteSgtysba(String ids) {
		Result result = new Result();
		
		String sql = "update ZFTZ_SGTBA set zt=0 where id in ("+ids+")";
		baseDao.executeBySql(sql);
		result.setSuccess(true);
		return result;
	}

	@Override
	public Object selSgtysbamxBySgtbaid(long sgtbaid,long xmid) {
		/*String hql = " from Sgtysbamx where sgtbaid="+sgtbaid;
		return baseDao.selectByHql(hql);*/
		List<Map> bglxdList = new ArrayList<Map>();
		String sql = "select z.id from  zftz_sgtbamx z where z.id_zftz_sgtba="+sgtbaid;
		List list = baseDao.selectBySql(sql);
		String bgidString = "select id from zftz_sgtba z where z.zt=1 and z.id_zftz_xm="+xmid;
		List bgidList = baseDao.selectBySql(bgidString);
		String bgids = "";
		if(bgidList.size()>0){
			for(int i=0;i<bgidList.size();i++){
				bgids+=bgidList.get(i).toString()+",";
			}
		}
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Map<Object,Object> map = new HashMap<Object,Object>();
				String sgtysmxSql = " from Sgtysbamx where id="+list.get(i).toString();
				List<Sgtysbamx> sgtysbamxs = baseDao.selectByHql(sgtysmxSql);
				String gssSql = "select round(sum(v.gstzje)/10000,2) as gss from v_zftz_gcfy v," +
						" (select mx.* from zftz_sgtbamx mx where mx.id_zftz_sgtba = "+sgtbaid+" and mx.gcfyid="+sgtysbamxs.get(0).getGcfyid()+" and mx.tzefl="+sgtysbamxs.get(0).getTzefl()+") a " +
						"where  v.gcfyid = a.gcfyid and v.tzefl = a.tzefl";
				List l = baseDao.selectBySql(gssSql);
				String ysqSql = "select round(sum(bcsgtys)/10000,2) as ysqje from zftz_sgtbamx mx " +
						"where mx.tzefl="+sgtysbamxs.get(0).getTzefl()+" and mx.gcfyid="+sgtysbamxs.get(0).getGcfyid()+" and mx.id_zftz_sgtba in("+bgids.substring(0, bgids.length()-1)+")";
				String ysqje = baseDao.selectBySql(ysqSql).get(0).toString();
				map.put("id", sgtysbamxs.get(0).getId());
				map.put("gcfyid", sgtysbamxs.get(0).getGcfyid());
				map.put("tzefl",sgtysbamxs.get(0).getTzefl());
				System.out.println("178---"+ysqje);
				if(l.size()>0){
					if(l.get(0)!=null){
						BigDecimal b1 = new BigDecimal(ysqje);
						BigDecimal b2 = new BigDecimal(l.get(0).toString());
						map.put("gss", Double.valueOf(l.get(0).toString()));
						map.put("ye", b2.subtract(b1).doubleValue());
					}else{
						map.put("gss", 0);
						map.put("ye", 0-Double.valueOf(ysqje));
					}
				}
				map.put("bz", sgtysbamxs.get(0).getBz());
				map.put("bcsgtys", Double.valueOf(sgtysbamxs.get(0).getBcsgtys())/10000);
				if(Double.valueOf(ysqje)!=0){
					double d1 = Double.valueOf(sgtysbamxs.get(0).getBcsgtys()/10000);
					BigDecimal b1 = new BigDecimal(ysqje);
					BigDecimal b2 = new BigDecimal(String.valueOf(d1));
					map.put("ysqje",b1.doubleValue());
				}else{
					map.put("ysqje","0");
				}
				System.out.println(map.get("ysqje"));
				bglxdList.add(map);
			}
		}
		
		
		return bglxdList;
	}

	@Override
	public Object selectXmmc(HttpSession session) {
		YhglYwYhyy yhglYwYhyy = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);

		/*String sql = "select to_char(t.id) || '_jsdw' as id, t.bm, t.mc as xmmc, '0' as pid,0 isleaf " +
				"from v_zftz_jsdw t where t.id " +
				"in (select y.entid from v_zftz_yhtoenter y where y.guid ="+yhglYwYhyy.getGuid()+") " +
				"and t.id in (select x.jsdw from zftz_xm x where x.zt=1 and x.jsdw in(select y.entid from v_zftz_yhtoenter y where y.guid ="+yhglYwYhyy.getGuid()+"))" +
				"union all " +
				"select to_char(t.id) as id, to_char(t.id), t.xmmc as xmmc, to_char(t.jsdw)|| '_jsdw' as pid,1 isleaf from zftz_xm t where t.zt = 1 "+
				"and t.ID not in (select ID_ZFTZ_XM from ZFTZ_XMJGJS where t.ZT = 1) and " +
				"t.jsdw in(select y.entid from v_zftz_yhtoenter y where y.guid ="+yhglYwYhyy.getGuid()+") ";*/
		
		String sql = "with xmTable as (select t.id, t.xmmc, t.jsdw from zftz_xm t where t.zt = 1 " +
				"and JSDW in (select y.entid from v_zftz_yhtoenter y where y.guid = '"+yhglYwYhyy.getGuid()+"')"+
                " and t.id not in (select ID_ZFTZ_XM from ZFTZ_XMJGJS where ZT = 1) " +
                "and t.id in(select s.id_zftz_xm from zftz_xmgs s where s.zt=1 and s.lchj!=-1))," +
                " dwTable as (select t.id, t.mc, FJBM from v_zftz_jsdw t, xmTable t1 where t.ID = t1.JSDW " +
                "group by t.ID, MC, BM, FJBM) " +
                "select distinct t.ID || '_dw' ID, t.MC xmmc, t.FJBM || '_dw' PID from V_ZFTZ_JSDW t " +
                "start with ID in (select ID from dwTable) connect by prior FJBM = ID " +
                "union all " +
                "select to_char(t.ID) id, t.XMMC xmmc, t.JSDW || '_dw' PID from xmTable t";
		return StringUtil.lowList(baseDao.selectMapsBySQL(sql));
	}

	@Override
	public Object getGsxxByXmid(String xmid) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		String sql1 = "select round(sum(gstzje)/10000,2) from v_zftz_gcfy where id_zftz_xm="+xmid;
		List list = baseDao.selectBySql(sql1);
		String sql2 = "select round(sum(gstzje)/10000,2) from v_zftz_gcfy where id_zftz_xm="+xmid+" and tzefl in(1,2)";
		List list2 = baseDao.selectBySql(sql2);
		String sql = "select gspfwh from ZFTZ_XMTZWH where id_ZFTZ_XM="+xmid;
		List l = baseDao.selectBySql(sql);
		if(l.size()>0){
			if(l.get(0)!=null){
				map.put("gspfwh", l.get(0).toString());
			}else{
				map.put("gspfwh", "--");
			}
		}else{
			map.put("gspfwh", "--");
		}
		if(list.size()>0){
			if(list.get(0)!=null){
				map.put("gsztz",Double.valueOf(list.get(0).toString()));
			}else{
				map.put("gsztz",0);
			}
		}else{
			map.put("gsztz",0);
		}
		if(list2.size()>0){
			if(list2.get(0)!=null){
				map.put("gsjaztz", Double.valueOf(list2.get(0).toString()));
			}else{
				map.put("gsjaztz", 0);
			}
		}else{
			map.put("gsjaztz", 0);
		}
		return map;
	}

	@Override
	public Object selectGcfymc() {
		//select distinct t.gcfyid,t.gcfymc from v_zftz_gcfy t where t.id_zftz_xm=220;
		String hql = "select distinct t.gcfyid bm,t.gcfymc mc from v_zftz_gcfy t";
		return baseDao.selectMapsBySQL(hql);
	}
	
	

	@Override
	public Object selectTzefl() {
		String hql = "select * from zftz_xxb where lx='tzefl' order by bm";
		return baseDao.selectMapsBySQL(hql);
	}

	@Override
	public Object selGsxxByFyidAndTzefl(String xmid, String gcfyid, String tzefl,String isEdit,String sgtbaid) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		String sql = "select round(sum(gstzje)/10000,2) sumje from v_zftz_gcfy where " +
				//"id_zftz_xm="+xmid+
				" gcfyid="+gcfyid+
				" and TZEFL="+tzefl;
		List list = baseDao.selectBySql(sql);
		if(list.get(0)!=null){
			map.put("gss",Double.valueOf(list.get(0).toString()));
		}else{
			map.put("gss",0);
		}

		String ysqsql = "";
		
		ysqsql = "select round(sum(s.bcsgtys)/10000,2) as ysqje from zftz_sgtbamx s,zftz_sgtba z " +
				"where s.id_ZFTZ_SGTBA=z.id and z.zt=1 and z.ID_ZFTZ_XM="+xmid+" and s.tzefl="+tzefl+" and s.gcfyid="+gcfyid;
		List ysqjelist = baseDao.selectBySql(ysqsql);
		if(ysqjelist.get(0)!=null){
			map.put("ysqje",Double.valueOf(ysqjelist.get(0).toString()));
		}else{
			map.put("ysqje","0");
		}
		return map;
	}

	private String[] updateSubmitModel(String id, String lcbm) {
        String updateSql = "update zftz_sgtba set lchj =" + lcbm + " where id = " + id;
        baseDao.executeBySql(updateSql);
        String sql = "select id_zftz_xm as xmid from zftz_sgtba where id= " + id;
        List list = baseDao.selectMapsBySQL(sql);
        Map map = (Map) list.get(0);
        Object xmid = map.get("XMID");
        // 返回业务ID和项目ID
        return new String[]{id.toString(), String.valueOf(xmid)};

    }
	@Override
	public Result submitSgtba(String idStr, HttpSession session) throws Exception{
		
		Result result = new Result();
		YhglYwYhyy yhglYwYhyy = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
		long yhId = yhglYwYhyy.getGuid();
		List<String> idList = Arrays.asList(idStr.subSequence(1, idStr.length()-1).toString().split(","));
		List<Integer> ids = new ArrayList<Integer>();
		for(int i=0;i<idList.size();i++){
			System.out.println("282----"+idList.get(i));
			ids.add(Integer.valueOf((idList.get(i).toString())));
		}
		//JSONArray jsonArray = JSONArray.parseArray(idStr);
		List<String> lchjs = new ArrayList<String>();
        for (Integer id1 : ids) {
        	String dqhjSql = "select lchj from zftz_sgtba where id="+id1;
        	List dqlcList = baseDao.selectBySql(dqhjSql);
        	if(dqlcList.size()>0){
        		lchjs.add(dqlcList.get(0).toString());
        	}else{
        		lchjs.add("-1");
        	}
        }
        Map<Object,Object> map = new HashMap<Object,Object>();
        map.put(1, ids.get(0));
        List<String> list = new ArrayList<String>();
        list.add("提交流程");
        
		this.nextFlow(yhId, ids, lchjs, list,session);
		
		return result;
	}

	@Override
	public Object getYhxm() {
		// TODO Auto-generated method stub
		String sql = "select yy.guid guid,yh.xm xm from yhgl_yw_yhyy yy,yhgl_yw_yh yh where yh.guid=yy.yhid and yy.yybm='YHGL'";
		//ConfigureParser.getPropert("YYBM");
		return StringUtil.lowList(baseDao.selectMapsBySQL(sql));
	}
	
	public void nextFlow(Long yhId, List<Integer> ids, final List<String> lchjs, final List<String> comment,HttpSession session) throws Exception {
        flowService.submit(yhId, ids, SX_CODE,
                FlowServiceImpl.OperateCode.NEXT, comment, getSubmitEvent(lchjs,session));
    }
	
	private FlowService.SubmitEvent getSubmitEvent(final List<String> lchjs,final HttpSession session) {
        return new FlowService.SubmitEvent() {
            public String onPrepare(Integer ywId, int index) {
                // 返回当前环节编码
                String realLchj = getDqLchj(ywId,session);
                // 校验流程编码，判断是否已经被处理
                if (!realLchj.equals(lchjs.get(index))) {
                    throw new IllegalArgumentException("流程已被处理！请刷新页面");
                }
                return realLchj;
            }

            public Long onSubmit(Integer id, String nextFlowCode,String comment) {
                // 更新环节编码并且返回项目ID
                return updateSubmitModel(id, nextFlowCode, comment);
            }
        };
    }
	
	private String getDqLchj(Integer ywId,HttpSession session) {
		YhglYwYhyy yhglYwYhyy = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
		String lchjSql = "select dqhjbm from zftz_splc where sprfzid in(select " +
				"qzid from yhgl_gg_ryqzfb where qzval="+yhglYwYhyy.getGuid()+") and sxbm=4";
		List lchjbm = baseDao.selectBySql(lchjSql);
		String dqlcbm = "";
		if(lchjbm.size()>0){
			dqlcbm = lchjbm.get(0).toString();
		}else{
			dqlcbm = "-1";
		}
        String sql = "select LCHJ from ZFTZ_SGTBA where ID=" + ywId;
        //List<Map> list = baseDao.selectMapsBySQL(sql);
        List l = baseDao.selectBySql(lchjSql);
        return dqlcbm;
    }
	
	private Long updateSubmitModel(Integer id, String lcbm, String comment) {
        String updateSql = "update ZFTZ_SGTBA set LCHJ='" + lcbm + "' where ID=" + id;
        baseDao.executeBySql(updateSql);
        return Long.parseLong(String.valueOf(id));
    }

	@Override
	public Result returnback(String id, String thyj, HttpSession session) throws Exception {
		Result result = new Result();
		YhglYwYhyy yhglYwYhyy = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
		long yhId = yhglYwYhyy.getGuid();
		List<String> idList = Arrays.asList(id.subSequence(1, id.length()-1).toString().split(","));
		List<Integer> ids = new ArrayList<Integer>();
		for(int i=0;i<idList.size();i++){
			System.out.println("282----"+idList.get(i));
			ids.add(Integer.valueOf((idList.get(i).toString())));
		}
		
		List<String> lchjs = new ArrayList<String>();
        for (Integer id1 : ids) {
        	String dqhjSql = "select lchj from zftz_sgtba where id="+id1;
        	List dqlcList = baseDao.selectBySql(dqhjSql);
        	if(dqlcList.size()>0){
        		lchjs.add(dqlcList.get(0).toString());
        	}
        }
        List<String> list = new ArrayList<String>();
        list.add(thyj);
		this.backFlow(yhId, ids, lchjs, list,session);
		result.setSuccess(true);
		return result;
	}
	
	public void backFlow(Long yhId, List<Integer> ids, List<String> lchjs, List<String> comment,HttpSession session) throws Exception {
        flowService.submit(yhId, ids, SX_CODE,
                FlowServiceImpl.OperateCode.BACK, comment, getSubmitEvent(lchjs,session));
    }

	@Override
	public Object getYbaByXmid(String xmid) {
		String hql = " from Sgtysba where id_zftz_xm="+xmid+" and lchj!=-1";
		List<Sgtysba> list = baseDao.selectByHql(hql);
		if(list.size()>0){
			list.get(0).setBcsgtys(list.get(0).getBcsgtys()/10000);
		}
		return list;
	}

	@Override
	public Object selSgtbaFilesById(String sgtbaid) {
		String sql = "select guid,filebstype,filebsid,filename,filesize from zftz_file where filebstype=4 and filebsid="+sgtbaid;
		return StringUtil.lowList(baseDao.selectMapsBySQL(sql));
	}

	@Override
	public Object selectHxXmmc() {
		//String hql = " from ZftzXm";
		String sql = "select t.id, to_char(t.id), t.xmmc as xmmc, to_char(t.jsdw) as pid,1 isleaf from zftz_xm t where t.zt = 1 ";
		return StringUtil.lowList(baseDao.selectMapsBySQL(sql));
		//return baseDao.selectByHql(hql);
	}

	@Override
	public Object selectGcfymcBySgtid(String xmid) {
		String hql = "select distinct t.gcfyid bm,t.gcfymc mc from v_zftz_gcfy t where t.id_zftz_xm="+xmid+" order by t.gcfyid";
		return baseDao.selectMapsBySQL(hql);
	}

	@Override
	public Object selSgtysbamxById(String sgtbaid) {
		String hql = " from Sgtysbamx where sgtbaid="+sgtbaid;
		List<Sgtysbamx> list = baseDao.selectByHql(hql);
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				list.get(i).setBcsgtys(list.get(i).getBcsgtys()/10000);
			}
		} 
		return list;
	}

	@Override
	public Object selectSgthxXmmc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result selThyj(String id) {
		Result result = new Result();
		String sql = "select l.clyj from zftz_shjl l where sxlx=4 and czlx=2 " +
				"and xmid="+id+" order by l.clsj desc";
		List l = baseDao.selectBySql(sql);
		if(l.size()>0){
			result.setContent(l.get(0).toString());
		}else{
			result.setContent("无退回意见");
		}
		return result;
	}

}
