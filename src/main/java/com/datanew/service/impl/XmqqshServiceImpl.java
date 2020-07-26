package com.datanew.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.YhglYwYhyy;
import com.datanew.model.ZftzXm;
import com.datanew.model.ZftzXmbzcs;
import com.datanew.model.ZftzXmqqch;
import com.datanew.service.XmqqshService;
import com.datanew.service.xmgcjjs.FlowService;
import com.datanew.service.xmgcjjs.impl.FlowServiceImpl;
import com.datanew.util.ConfigureParser;
import com.datanew.util.StaticData;
import com.datanew.util.StringUtil;

import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by admin on 2019/5/29.
 */
@Service
public class XmqqshServiceImpl implements XmqqshService{
    private static final String SX_CODE = "1";
    @Resource
    private BaseDao baseDao;
    @Autowired
    FlowService flowService;
    Result result = new Result();

//数据回显
    //x1为项目前期策划表   x2为项目主表，x3 为审核记录表
    @Override
    public Pages getXmqqsh(Pages page,String id, String sldh, String xmid,String zt, HttpSession session) throws UnsupportedEncodingException{
        YhglYwYhyy userinfo = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
        String sql="";
        if ("0".equals(zt)) {
        	sql = "from zftz_xmqqch x1 " +
                    "left join zftz_xm x2 on  x1.id_zftz_xm=x2.id " +
                    "left join yhgl_yw_yhyy y1 on x1.czr=to_char(y1.guid) " +
                    "left join yhgl_yw_yh y2 on y1.yhid=y2.guid "+
                    " where x1.sldh is not null and x1.zt=1 ";
            if (!StringUtil.isblank(xmid)) {
            	xmid=new String(xmid.getBytes("ISO8859-1"), "UTF-8");
            	sql =sql+" and x2.xmmc like '%"+xmid+"%'";
    		}  
            sql =sql+" and x1.lchj not in(0,-1) and x1.id in (select ywid from zftz_gg_ryqzyh where yhid='"+userinfo.getGuid()+"' and sxlx='1')";
            sql= sql+ " and x2.jsdw in (select entid from v_zftz_yhtoenter where guid="+userinfo.getGuid()+")";
		}else{
			sql = "from zftz_xmqqch x1 " +
                    "left join zftz_xm x2 on  x1.id_zftz_xm=x2.id " +
                    "left join yhgl_yw_yhyy y1 on x1.czr=to_char(y1.guid) " +
                    "left join yhgl_yw_yh y2 on y1.yhid=y2.guid "+
                    " where x1.sldh is not null and x1.zt=1 ";
            if (!StringUtil.isblank(xmid)) {
            	xmid=new String(xmid.getBytes("ISO8859-1"), "UTF-8");
            	sql =sql+" and x2.xmmc like '%"+xmid+"%'";
    		}  
//            sql =sql+" and x1.lchj not in(0,-1) ";
            sql=sql+ " and x1.id in (select xmid from zftz_shjl where sxlx='1' and clyhid="+userinfo.getGuid()+")";
            sql= sql+ " and x2.jsdw in (select entid from v_zftz_yhtoenter where guid="+userinfo.getGuid()+")";
		}
        
//        String selectyhqz="select dqhjbm from yhgl_gg_ryqzfb y1 left join zftz_splc z1 on y1.qzid=z1.sprfzid   where  z1.sxbm=1 and y1.qzval="+userinfo.getGuid();
//        List hjbmlist = baseDao.selectBySql(selectyhqz);
//        if(hjbmlist.size()>0){
//            if(hjbmlist.get(0).toString().equals("1")){
//                sql+=" and x1.lchj="+hjbmlist.get(0).toString();
//            }else if(hjbmlist.get(0).toString().equals("2")){
//                sql+=" and x1.lchj="+hjbmlist.get(0).toString();
//            }
//        }
        List list = baseDao.selectMapsBySQL("select x1.id as id1,x1.id_zftz_xm as xmId,x1.lchj,x2.id,x1.sldh,x1.czsj,y2.xm as czr,x2.xmmc,x2.xmlx,x2.jsdw,x2.zt as xmzt,x2.zgbm," +
                "x1.xmghxz,x1.rjl,x1.ydmj,x1.jzzmj,x1.dsjzmj,x1.xmlc,x1.dlkd,x1.xmdwzj,x1.wjzjbz,x1.tlxzjbz,x1.jldw,x1.lxr,x1.lxdh," +
                "case when x1.ztz_jzaztz is null then 0 else round(x1.ztz_jzaztz/10000,2) end  as ztz_jzaztz," +
                "case when x1.ztz_sbtz is null then 0 else round(x1.ztz_sbtz/10000,2) end as ztz_sbtz ," +
                "case when x1.ztz_dttz is null then 0 else round(x1.ztz_dttz/10000,2) end  as ztz_dttz," +
                "case when x1.ztz_qttz is null then 0 else round(x1.ztz_qttz/10000,2) end  as ztz_qttz," +
                "case when x1.xmzjly_czxzj is null then 0 else round(x1.xmzjly_czxzj/10000,2) end as xmzjly_czxzj," +
                "case when x1.xmzjly_zyph is null then 0 else round(x1.xmzjly_zyph/10000,2) end as xmzjly_zyph," +
                "case when x1.xmzjly_qt is null then 0 else round(x1.xmzjly_qt/10000,2) end as xmzjly_qt," +
                "case when x1.tzxe is null then 0 else round(x1.tzxe/10000,2) end as tzxe," +
                "case when x1.jykzzjbz is null then 0 else round(x1.jykzzjbz,2) end as jykzzjbz," +
                "x1.jsnr,x1.zt,x1.xmphjy,round((x1.ztz_jzaztz+x1.ztz_sbtz+x1.ztz_dttz+x1.ztz_qttz)/10000,2) as XMZTZ,round((x1.ztz_jzaztz+x1.ztz_sbtz+x1.ztz_dttz+x1.ztz_qttz-x1.tzxe)/10000,2) as HZTZXE,case when (select count(*) from zftz_gg_ryqzyh k where k.ywid= x1.id and sxlx='1' and k.yhid="+userinfo.getGuid()+ " )>=1 then 1 when   (select count(*) from zftz_gg_ryqzyh k where k.ywid= x1.id and k.sxlx='1' and k.yhid="+userinfo.getGuid()+ " )=0 then 0 end  issh  " + sql, null, page.getOffset(), page.getLimit());
        Long count = baseDao.getCountBySQL("select count(*) " + sql, null);
        page.setTotal(count.intValue());
        page.setRows(lowList(list));
        return page;
    }

//转换小写
    public  List lowList(List l){
        List list = new ArrayList();
        for(int i=0; i<l.size(); i++){
            Map m = (Map)l.get(i);
            Map p = new HashMap();
            Iterator iter=m.keySet().iterator();
            while(iter.hasNext()){
                String key=(String)iter.next();
                Object value = m.get(key);
                p.put(key.toLowerCase(), value);
            }
            list.add(p);
        }

        return list;

    }

    //更新意见
    public Result saveOrCheck(String tzxe,String jykj,String spjy,String id,HttpSession session){
        System.out.println(id);
        Result result =new Result();
        if(id!=null && id!=""){
            String sql="update zftz_xmqqch set xmphjy='"+spjy+"',"+
                    (tzxe==null?" tzxe='',":" tzxe="+ BigDecimal.valueOf(Double.parseDouble(tzxe)*10000)+",")+
                    (jykj==null?" jykzzjbz='',":" jykzzjbz="+ BigDecimal.valueOf(Double.parseDouble(jykj)))+
                    " where id_zftz_xm="+id;
            baseDao.executeBySql(sql);
            result.setSuccess(true);
            return result;
        }else {
            result.setSuccess(false);
            return  result;
        }

    }
    public Pages getZbk(Pages page,String id,String zbmc,HttpSession session) throws UnsupportedEncodingException{
        String sql="from zftz_zbk z1 left join zftz_xm m on z1.xmlx=m.xmlx where z1.zfrq is null and z1.id not in (select id_zftz_zbk from zftz_xmbzcs where id_zftz_xm ="+id+") and m.id="+id;
        if (!StringUtil.isblank(zbmc)) {
        	zbmc=new String(zbmc.getBytes("ISO8859-1"), "UTF-8");
			sql=sql+"  and (z1.yjzb like '%"+zbmc+"%' or z1.ejzb like '%"+zbmc+"%' or z1.sjzb like '%"+zbmc+"%')";
		}
        sql=sql+" order by z1.yjzb,z1.ejzb,z1.sjzb";
        List list = baseDao.selectMapsBySQL("select z1.id as zbkId,z1.yjzb,z1.ejzb,z1.sjzb,z1.dwtze,z1.jldw,z1.zbly,z1.bz " + sql, null, page.getOffset(), page.getLimit());
        Long count = baseDao.getCountBySQL("select count(*) " + sql, null);
        page.setTotal(count.intValue());
        page.setRows(list);
        return page;
    }


    public Result saveZbk(String idZftzXm,String idZftzZbk,String sl, HttpSession session){
        Result result =new Result();
        String [] idZftzZbks=idZftzZbk.split(",");
        String [] sls=sl.split(",");
        baseDao.executeBySql("delete ZFTZ_XMBZCS where ID_ZFTZ_XM="+idZftzXm);
        for (int i = 0; i < idZftzZbks.length; i++) {
            if(sls.length==0 || sls.length <=i || sls[i]==null){
                result.setContent("请输入数量");
                result.setSuccess(false);
                return  result;
            }else if(idZftzXm==null){
                result.setContent("请选择项目");
                result.setSuccess(false);
                return  result;
            }else{
                ZftzXmbzcs zftzxmbzcs=new ZftzXmbzcs();
                zftzxmbzcs.setIdZftzXm(Long.parseLong(idZftzXm));
                zftzxmbzcs.setIdZftzZbk(Long.parseLong(idZftzZbks[i]));
                zftzxmbzcs.setSl(Double.parseDouble(sls[i]));
                baseDao.save(zftzxmbzcs);
                result.setSuccess(true);
            }
		}
        return  result;
    }

    public Result deleteZbk(String ids, HttpSession session){
        Result result = new Result();
        String [] ids1=ids.split(",");
        for(int i=0;i<ids1.length;i++){
            if("-1".equals(ids1[i])){
                result.setSuccess(true);
                result.setContent("删除成功");
            }else{
                String xmqqsh_sql = "delete from zftz_xmbzcs where id="+ids1[i];
                baseDao.executeBySql(xmqqsh_sql);
                result.setSuccess(true);
                result.setContent("删除成功");
            }
        }
        return  result;
    }

    @Override
    public List getShyj(String id) {
//        id="45";
        String sql = "";
        List<Map> list = new ArrayList<Map>();
        if (id != null) {
            sql = 	"select t.guid,t.ddrq,t.clsj,t.clyj, t.FKR, t.dqhjmc  from  "+
            		" (select a.guid,a.ddrq,a.clsj,a.clyj, i.xm as FKR, c.dqhjmc from zftz_shjl a " +
                    "  left join (select m.guid yyguid, n.*  from yhgl_yw_yhyy m, yhgl_yw_yh n  where m.yhid = n.guid) i " +
                    "   on a.clyhid = i.yyguid  left join zftz_splc c  on a.dqhjbm = c.dqhjbm " +
                    "    where a.xmid =  " + id+
                    "     and c.sxbm=1  union all "
                    + " select b.guid,b.ddrq,b.clsj,b.clyj, z.xm as FKR, '提交' as dqhjmc "
                    + "  from zftz_shjl b left join (select x.guid yyguid, y.* from yhgl_yw_yhyy x, yhgl_yw_yh y where x.yhid = y.guid) z "
                    + " on b.clyhid = z.yyguid  "
                    + "  where b.xmid =  "+ id
                    + " and b.dqhjbm=-1)  t  order by t.clsj ";                   
				
                   
            list = baseDao.selectMapsBySQL(sql);
        }else {
        	
        }
        return list;
    }
    @Override
    public Result submit(String guids, HttpSession session) throws Exception{

        Result result = new Result();
        YhglYwYhyy yhglYwYhyy = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
        String [] guid=guids.split(",");
        List<String> lchjs = new ArrayList<String>();
        List<Integer> ids = new ArrayList<Integer>();
        List<String> shyjs = new ArrayList<String>();
            for (int i = 0; i < guid.length; i++) {
                ZftzXmqqch zftzXmqqch=(ZftzXmqqch)baseDao.load(ZftzXmqqch.class, Long.parseLong(guid[i]));
                lchjs.add(zftzXmqqch.getLchj().toString());
                ids.add(Integer.parseInt(guid[i]));
                shyjs.add(zftzXmqqch.getXmphjy());
            }
            this.nextFlow(yhglYwYhyy.getGuid(), ids, lchjs, shyjs);
            result.setSuccess(true);
        return result;
    }

    public Result back(String guids,String shyj, HttpSession session) throws  Exception{
        Result result = new Result();
        YhglYwYhyy yhglYwYhyy = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
        String [] guid=guids.split(",");
        List<String> lchjs = new ArrayList<String>();
        List<Integer> ids = new ArrayList<Integer>();
        List<String> shyjs = new ArrayList<String>();
        for (int i = 0; i < guid.length; i++) {
            ZftzXmqqch zftzXmqqch=(ZftzXmqqch)baseDao.load(ZftzXmqqch.class, Long.parseLong(guid[i]));
            zftzXmqqch.setXmphjy(shyj);

            lchjs.add(zftzXmqqch.getLchj().toString());
            ids.add(Integer.parseInt(guid[i]));
            shyjs.add(shyj);
        }
        this.backFlow(yhglYwYhyy.getGuid(), ids, lchjs, shyjs);
        result.setSuccess(true);
        return result;
    }

    public Pages getXmbzcs(Pages page,String id,HttpSession session){
        String sql="from zftz_zbk z1 left join zftz_xmbzcs x1 on z1.id=x1.id_zftz_zbk where x1.id_zftz_xm= "+id +" order by xmlx,yjzb,ejzb,sjzb";
        List list = baseDao.selectMapsBySQL("select z1.id as zbkId,z1.yjzb,z1.ejzb,z1.sjzb,z1.dwtze,z1.jldw,z1.zbly,z1.bz,x1.sl,x1.id_zftz_xm as xmId,x1.id as xmbzcsId,(z1.dwtze * x1.sl) as xj " + sql, null, page.getOffset(), page.getLimit());
        Long count = baseDao.getCountBySQL("select count(*) " + sql, null);
        page.setTotal(count.intValue());
        page.setRows(list);
        return page;
    }
    public Object getxmId(HttpSession session){
        String hql="select id_zftz_xm from zftz_xmqqch";
        return  baseDao.selectByHql(hql);
    };






//审核流程
    public void nextFlow(Long yhId, List<Integer> ids, final List<String> lchjs, final List<String> comment) throws Exception {
        flowService.submit(yhId, ids, SX_CODE,
                FlowServiceImpl.OperateCode.NEXT, comment, getSubmitEvent(lchjs));
    }

    private FlowService.SubmitEvent getSubmitEvent(final List<String> lchjs) {
        return new FlowService.SubmitEvent() {
            public String onPrepare(Integer ywId, int index) {
                // 返回当前环节编码
                String realLchj = getDqLchj(ywId);
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

    private String getDqLchj(Integer ywId) {
        String sql = "select LCHJ from zftz_xmqqch where ID=" + ywId;
        //List<Map> list = baseDao.selectMapsBySQL(sql);
        List l = baseDao.selectBySql(sql);
        return l.get(0).toString();
    }

    private Long updateSubmitModel(Integer id, String lcbm, String comment) {
        String updateSql = "update zftz_xmqqch set LCHJ="+ lcbm +",xmphjy='"+comment+"' where ID=" + id;
        baseDao.executeBySql(updateSql);
        /*String sql = "select ID_ZFTZ_XM\n" +
                "from ZFTZ_HTBA t1,\n" +
                "     ZFTZ_XMGCJSBA t2\n" +
                "where t1.ID = t2.ID_ZFTZ_HTBA\n" +
                "  and t2.ID = " + id;
        List<Map> res = baseDao.selectMapsBySQL(sql);*/
        // 返回业务ID和项目ID
        return Long.parseLong(String.valueOf(id));
    }



    public void backFlow(Long yhId, List<Integer> ids, List<String> lchjs, List<String> comment) throws Exception {
        flowService.submit(yhId, ids, SX_CODE,
                FlowServiceImpl.OperateCode.BACK, comment, getSubmitEvent(lchjs));
    }

	@Override
	public Result getZbkPrint(String xmid) {
		Result result=new Result();
		String path=ConfigureParser.getPropert("ZBKPATH");
		result.setContent(path+xmid);
		result.setSuccess(true);
		return result;
	}

	@Override
	public Pages getAlk(Pages page, String al, String zt,String xmid, HttpSession session) throws UnsupportedEncodingException {
		List list=new ArrayList();
		String alkmc="";
		if (!StringUtil.isblank(xmid)) {
			String sql="";	
			String sqlcount="";
			ZftzXm  zftzxm=(ZftzXm)baseDao.load(ZftzXm.class, Long.parseLong(xmid));
			String sql1="select zdm from ZFTZ_ALKDY where zt='1' and xmlx='"+zftzxm.getXmlx()+"'";
			List list1=baseDao.selectMapsBySQL(sql1);
			if (list1.size()>0) {
				for (int i = 0; i < list1.size(); i++) {
					sql+=((Map)list1.get(i)).get("ZDM").toString()+",";
					alkmc+=((Map)list1.get(i)).get("ZDM").toString()+"||";
				}
				sql="select a.id, (select count(*) from zftz_xmtoalk k where k.id_zftz_alk=a.id and k.ID_ZFTZ_XM="+xmid+") as flag , "+sql.substring(0, sql.length()-1)+" from zftz_alk a where a.zt='1' and a.xmlx='"+zftzxm.getXmlx()+"'";
				sqlcount="select count(*) from zftz_alk a where a.zt='1' and a.xmlx='"+zftzxm.getXmlx()+"'";
				if ("0".equals(zt)||StringUtil.isblank(zt)) {//全部
					
				}else if("1".equals(zt)){//已选择
					sql=sql+" and  a.id in (select k.ID_ZFTZ_ALK from ZFTZ_XMTOALK k where k.ID_ZFTZ_XM="+xmid+")";
					sqlcount=sqlcount+" and  a.id in (select k.ID_ZFTZ_ALK from ZFTZ_XMTOALK  k where k.ID_ZFTZ_XM="+xmid+")";
				}else{//未选择
					sql=sql+" and a.id not in (select k.ID_ZFTZ_ALK from ZFTZ_XMTOALK k where k.ID_ZFTZ_XM="+xmid+")";
					sqlcount=sqlcount+"  and a.id not in (select k.ID_ZFTZ_ALK from ZFTZ_XMTOALK  k where k.ID_ZFTZ_XM="+xmid+")";
				}
				if (!StringUtil.isblank(al)) {
					//al=new String(al.getBytes("ISO8859-1"), "UTF-8");
					sql=sql+" and a.id  in (select id from zftz_alk  where "+alkmc.substring(0, alkmc.length()-2)+" like '%"+al+"%')";
					sqlcount=sqlcount+" and a.id  in (select id from zftz_alk  where "+alkmc.substring(0, alkmc.length()-2)+" like '%"+al+"%')";
				}
				list=baseDao.selectMapsBySQL(sql);
				Long count = baseDao.getCountBySQL(sqlcount,null);
				page.setTotal(count.intValue());
		        page.setRows(list);
			}else{
				page.setTotal(0);
		        page.setRows(list);
			}
			
		}else{
			page.setTotal(0);
	        page.setRows(list);
		}
		
		
       
		return page;
	}

	@Override
	public List getAlkmb(String xmid, HttpSession session) {
		ZftzXm  zftzxm=(ZftzXm)baseDao.load(ZftzXm.class, Long.parseLong(xmid));
		String sql="select zdm,xsmc from ZFTZ_ALKDY where zt='1' and xmlx='"+zftzxm.getXmlx()+"'";
		List list=baseDao.selectMapsBySQL(sql);
		return list;
	}

	@Override
	public Result savealk(String xmid, String alk, HttpSession session) {
		baseDao.executeBySql("delete from zftz_xmtoalk where id_zftz_xm="+xmid);
		String flag;
		JSONArray jsonArray = JSONArray.parseArray(alk);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			if (jsonObject.containsKey("FLAG")){
                flag = jsonObject.get("FLAG").toString();
           }else{
                flag = "";
           }
           String alkid = (jsonObject.get("ID") == null)?"":jsonObject.get("ID").toString();
           if (!(flag.equals("0")||flag.equals("false"))){
            	   baseDao.executeBySql("insert into zftz_xmtoalk values(SEQ_ZFTZ_XMTOALK.NEXTVAL,"+xmid+","+alkid+")");
           }
//           else{
//        	   	   baseDao.executeBySql("delete from zftz_xmtoalk where id_zftz_xm="+xmid+" and id_zftz_alk="+alkid);
//           }
		}
		result.setContent("保存成功");
		result.setSuccess(true);		
		return result;
	}
}
