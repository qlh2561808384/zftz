package com.datanew.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.*;
import com.datanew.service.XmqqchService;
import com.datanew.service.xmgcjjs.FlowService;
import com.datanew.service.xmgcjjs.impl.FlowServiceImpl;
import com.datanew.service.xmgcjjs.impl.XmgcjsbbServiceImpl;
import com.datanew.util.StaticData;
import com.datanew.util.StringUtil;

import org.hibernate.persister.entity.Loadable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by admin on 2019/5/23.
 */
@Service
public class XmqqchServiceImpl implements XmqqchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmgcjsbbServiceImpl.class);
    private static final String SX_CODE = "1";
    @Resource
    private BaseDao baseDao;
    @Autowired
    FlowService flowService;
    Result result = new Result();

    @Override
    public Result saveZx(ZftzXmqqch zx, ZftzXm zxz, String sldh,String fileId, HttpSession session) {
        Result result = new Result();
        String[] fileIds=fileId.split(",");
        YhglYwYhyy userinfo = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
        if (zx.getId1() == null) {
            try {
                String sql = "select to_char(replace(lpad(SEQ_ZFTZ_XMQQCH.nextval,4,'0'),'','0')) from dual";//获取自增序列并补全000
                sldh = baseDao.selectBySql(sql).toString().substring(1, 5);   //执行sql并转换为字符串
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  //获取当前年份
                Date date = new Date();
                String formatDate = sdf.format(date);
                zx.setZtzJzaztz(zx.getZtzJzaztz()==null?null: BigDecimal.valueOf(zx.getZtzJzaztz().doubleValue()*10000));
                zx.setZtzDttz(zx.getZtzDttz()==null?null: BigDecimal.valueOf(zx.getZtzDttz().doubleValue()*10000));
                zx.setZtzSbtz(zx.getZtzSbtz()==null?null: BigDecimal.valueOf(zx.getZtzSbtz().doubleValue()*10000));
                zx.setZtzQttz(zx.getZtzQttz()==null?null: BigDecimal.valueOf(zx.getZtzQttz().doubleValue()*10000));
                zx.setXmzjlyCzxzj(zx.getXmzjlyCzxzj()==null?null: BigDecimal.valueOf(zx.getXmzjlyCzxzj().doubleValue()*10000));
                zx.setXmzjlyZyph(zx.getXmzjlyZyph()==null?null: BigDecimal.valueOf(zx.getXmzjlyZyph().doubleValue()*10000));
                zx.setXmzjlyQt(zx.getXmzjlyQt()==null?null: BigDecimal.valueOf(zx.getXmzjlyQt().doubleValue()*10000));
                zx.setCzr(userinfo.getGuid().toString());
                zx.setSldh(Integer.parseInt(formatDate + sldh));   //设置受理单号
                zxz.setZt(1);
                baseDao.save(zxz);
                Long id = zxz.getId();
                zx.setZxXmid(id);   //设置对应id的关系
                zx.setZt(1);
                zx.setLchj(-1);
                baseDao.save(zx);
          
                	for (int i = 0; i < fileIds.length; i++) {
                		if (!StringUtil.isblank(fileIds[i]) ) {
                			String updateSql = "update zftz_file a set a.filebsid = " + zx.getId1() + " where guid = " + fileIds[i];
                            baseDao.executeBySql(updateSql);
						}
                        
                    }

                	result.setContent(zx.getId1()+","+zxz.getId());
                	result.setSuccess(true);
                

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else{
            String zx_sql="update zftz_xmqqch set xmghxz='"+zx.getXmghxz()+"',rjl="+zx.getRjl()+",ydmj="+zx.getYdmj()+",jzzmj="+zx.getJzzmj()+",dsjzmj="+zx.getDsjzmj()+",xmlc="+zx.getXmlc()+
                    ",dlkd="+zx.getDlkd()+",wjzjbz="+zx.getWjzjbz()+",tlxzjbz="+zx.getTlxzjbz()	+",xmdwzj="+zx.getXmdwzj()+",jldw='"+zx.getJldw()+"',lxr='"+zx.getLxr()+"',lxdh='"+zx.getLxdh()+
                    "',jsnr='"+zx.getJsnr()+"',"+
                    (zx.getZtzJzaztz()==null?" ztz_jzaztz='',":" ztz_jzaztz="+BigDecimal.valueOf(zx.getZtzJzaztz().doubleValue()*10000)+",")+
                    (zx.getZtzSbtz()==null?" ztz_sbtz='',":" ztz_sbtz="+BigDecimal.valueOf(zx.getZtzSbtz().doubleValue()*10000)+",")+
                    (zx.getZtzDttz()==null?" ztz_dttz='',":" ztz_dttz="+BigDecimal.valueOf(zx.getZtzDttz().doubleValue()*10000)+",")+
                    (zx.getZtzQttz()==null?" ztz_qttz='',":" ztz_qttz="+BigDecimal.valueOf(zx.getZtzQttz().doubleValue()*10000)+",")+
                    (zx.getXmzjlyCzxzj()==null?" xmzjly_czxzj='',":" xmzjly_czxzj="+BigDecimal.valueOf(zx.getXmzjlyCzxzj().doubleValue()*10000)+",")+
                    (zx.getXmzjlyZyph()==null?" xmzjly_zyph='',":" xmzjly_zyph="+BigDecimal.valueOf(zx.getXmzjlyZyph().doubleValue()*10000)+",")+
                    (zx.getXmzjlyQt()==null?" xmzjly_qt=''":" xmzjly_qt="+BigDecimal.valueOf(zx.getXmzjlyQt().doubleValue()*10000))+
                    " where ID="+zx.getId1();
            String xm_sql="update zftz_xm set xmlx="+zxz.getXmlx()+","+"jsdw="+zxz.getJsdw()+","+"zgbm="+zxz.getZgbm()+",xmmc='"+zxz.getXmmc()+"' where id="+zx.getZxXmid();
            baseDao.executeBySql(zx_sql);
            baseDao.executeBySql(xm_sql);
                for (int i = 0; i < fileIds.length; i++) {
                   
                    	if (!StringUtil.isblank(fileIds[i])) {
                    		String updateSql = "update zftz_file a set a.filebsid = " + zx.getId1() + " where a.guid = " + fileIds[i];
                            baseDao.executeBySql(updateSql);
						}
                        
                    

            }
                
                result.setContent(zx.getId1()+","+zxz.getId());
            	result.setSuccess(true);
            
        }
        return result;
    }


    @Override
    public Pages getXmqqch(Pages page, String id,String xmid,String zt, HttpSession session) throws UnsupportedEncodingException {
        YhglYwYhyy userinfo = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
        String sql = "from zftz_xmqqch x1 " +
                "left join zftz_xm x2 on  x1.id_zftz_xm=x2.id " +
                "left join yhgl_yw_yhyy y1 on x1.czr=to_char(y1.guid) " +
                "left join yhgl_yw_yh y2 on y1.yhid=y2.guid "+
                " where x1.sldh is not null and x1.zt=1 and x2.jsdw in (select entid from v_zftz_yhtoenter where guid="+userinfo.getGuid()+")";
//        String selectsql="select id from zftz_xm where xmmc like '%"+xmid+"%'";
//        List l = baseDao.selectBySql(selectsql);
//        String ids = "";
//        if(l.size()>0){
//            for(int i=0;i<l.size();i++){
//                ids+=l.get(i).toString()+",";
//            }
//            sql+=" and x2.id in("+ids.substring(0,ids.length()-1)+")";
//        }
        if (!StringUtil.isblank(xmid)) {
        	xmid=new String(xmid.getBytes("ISO8859-1"), "UTF-8");
        	sql =sql+" and x2.xmmc like '%"+xmid+"%'";
        }
        if(!StringUtil.isblank(zt)){
            if(zt.equals("0")){
                sql+=" and x1.lchj="+zt;
            }else if(zt.equals("-1")){
                sql+=" and x1.lchj="+zt;
            }else{
                sql+=" and x1.lchj not in(0,-1)";
            }

        }
//        baseDao.selectByHql(sql);

        List list = baseDao.selectMapsBySQL("select x1.id as id1,x1.id_zftz_xm as zxXmid,x2.id,x1.sldh,x1.czsj,y2.xm as yhxm,x2.xmmc,x2.xmlx,x2.jsdw,x2.zt as xmzt,x2.zgbm," +
                "x1.xmghxz,x1.rjl,x1.ydmj,x1.jzzmj,x1.dsjzmj,x1.xmlc,x1.dlkd,x1.xmdwzj,x1.wjzjbz,x1.tlxzjbz,x1.jldw,x1.lxr,x1.lxdh,x1.jsnr," +
                "case when x1.ztz_jzaztz is null then 0 else round(x1.ztz_jzaztz/10000,2) end  as ztz_jzaztz," +
                "case when x1.ztz_sbtz is null then 0 else round(x1.ztz_sbtz/10000,2) end as ztz_sbtz ," +
                "case when x1.ztz_dttz is null then 0 else round(x1.ztz_dttz/10000,2) end  as ztz_dttz," +
                "case when x1.ztz_qttz is null then 0 else round(x1.ztz_qttz/10000,2) end  as ztz_qttz," +
                "case when x1.xmzjly_czxzj is null then 0 else round(x1.xmzjly_czxzj/10000,2) end as xmzjly_czxzj," +
                "case when x1.xmzjly_zyph is null then 0 else round(x1.xmzjly_zyph/10000,2) end as xmzjly_zyph," +
                "case when x1.xmzjly_qt is null then 0 else round(x1.xmzjly_qt/10000,2) end as xmzjly_qt," +
                "case when x1.tzxe is null then 0 else round(x1.tzxe/10000,2) end as tzxe,"+
                "x1.zt,x1.jykzzjbz,x1.xmphjy,x1.lchj,round((x1.ztz_jzaztz+x1.ztz_sbtz+x1.ztz_dttz+x1.ztz_qttz)/10000,2) as XMZTZ,round((x1.ztz_jzaztz+x1.ztz_sbtz+x1.ztz_dttz+x1.ztz_qttz-x1.tzxe)/10000,2) as HZTZXE  "
                + " ,case when x1.lchj=0 then '已审核' when x1.lchj=-1 and (select count(*) from zftz_shjl t where t.xmid=x1.id and t.sxlx='1')>=1 then '退回' "
                + "  when  x1.lchj=-1 and (select count(*) from zftz_shjl t where t.xmid=x1.id and t.sxlx='1')=0 then '未提交' "
                + " else '流程中' end zt1 " + sql, null, page.getOffset(), page.getLimit());
        Long count = baseDao.getCountBySQL("select count(*) " + sql, null);
        page.setTotal(count.intValue());
        page.setRows(lowList(list));
        return page;
    }




    @Override
    public Result submit(String guids, HttpSession session) throws Exception {
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
                shyjs.add("提交流程");
            }
            this.nextFlow(yhglYwYhyy.getGuid(), ids, lchjs, shyjs);
            result.setSuccess(true);
        return result;
    }



    @Override
    public Result delXmqqch(String ids, HttpSession session) {
        Result result = new Result();

        String xmqqch_sql = "update zftz_xmqqch set zt=0 where id_zftz_xm in ("+ids+")";
        String xm_sql = "update zftz_xm set zt=0 where id in ("+ids+")";
        baseDao.executeBySql(xmqqch_sql);
        baseDao.executeBySql(xm_sql);
        result.setSuccess(true);
        return result;
    }
    public Object selectById(Long xmId){

        String sql="from ZftzXmqqch where idZftzXm ="+xmId;
        return baseDao.selectByHql(sql);
    };

    public Object selecByXmId(Long id){
        System.out.println("99-----"+id);
        String sql="from ZftzXm where id ="+id;
        return  baseDao.selectByHql(sql);
    };
    public Object selectXmmc(HttpSession session){
        String hql="from zftz_xm where zt=1";
        return  baseDao.selectByHql(hql);
    };


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
    public List selectXmlx() {
        String sql = "select * from zftz_xxb t where t.lx='xmlx'";
        return baseDao.selectMapsBySQL(sql);
    }

    @Override
    public List getXMLXTree() {
        List dataList = new ArrayList();
        String sql = "select mc as name,id as ID,bm as BM ,fjbm as PID from zftz_xxb t where t.lx='xmlx'";
        List list =  baseDao.selectBySql(sql);//得到List集合
        if(list.size()>0){
            for(int i = 0; i < list.size() ; i++ ){
                Object[] obj = (Object[]) list.get(i);
                Map map = new HashMap();
                map.put("id",obj[2]);
                map.put("name",obj[0]);
                map.put("pId",obj[3]);
                dataList.add(map);
            }
        }
        return dataList;
    }

    //获得主管部门
    @Override
    public List selectZgbm() {
        String sql = "select * from v_zftz_zgdw";
        return baseDao.selectMapsBySQL(sql);
    }
    @Override
    public List getZGBMTree(HttpSession session) {
    	YhglYwYhyy userinfo = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
        List dataList = new ArrayList();
        String sql = "select d.mc as name,d.id as ID,d.bm as BM ,d.fjbm as PID from v_zftz_zgdw d where "
        		+ " d.id in (select p_entid from v_zftz_yhtoenter where guid="+userinfo.getGuid()+")";
        List list =  baseDao.selectBySql(sql);//得到List集合
        if(list.size()>0){
            for(int i = 0; i < list.size() ; i++ ){
                Object[] obj = (Object[]) list.get(i);
                Map map = new HashMap();
                map.put("id",obj[1]);
                map.put("name",obj[0]);
                map.put("pId",obj[3]);
                dataList.add(map);
            }
        }
        return dataList;
    }

//获得建设单位
@Override
    public List selectJsdw(HttpSession session) {
//        YhglYwYhyy userinfo = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
//        String sql = "select * from v_zftz_jsdw v1 left join yhgl_yw_yhyy y1 on v1.id=y1.szdwid where y1.yhzh='"+userinfo.getYhzh()+"'";
    YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
    String sql = "select * from v_zftz_jsdw   " ;

        return baseDao.selectMapsBySQL(sql);
    }
    @Override
    public List getJSDWTree(HttpSession session) {
        YhglYwYhyy userinfo = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
        List dataList = new ArrayList();
//        String sql = "select mc as NAME,id as ID,0 as PID from v_zftz_jsdw ";
//        String sql = "select mc as name,id as ID,bm as BM ,fjbm as PID from v_zftz_jsdw v1 left join yhgl_yw_yhyy y1 on v1.id=y1.szdwid where y1.yhzh='"+userinfo.getYhzh()+"' and yybm='"+userinfo.getYybm()+"'";
//        String sql = "select mc as name,id as ID,bm as BM ,fjbm as PID from v_zftz_jsdw " ;
        String sql = "select mc as name, id as ID, bm as BM, fjbm as PID\n" +
                "  from v_zftz_jsdw\n" +
                " where id in (select entid from v_zftz_yhtoenter where guid = " + userinfo.getGuid() + ")";
        List list =  baseDao.selectBySql(sql);//得到List集合
        if(list.size()>0){
            for(int i = 0; i < list.size() ; i++ ){
                Object[] obj = (Object[]) list.get(i);
                Map map = new HashMap();
                map.put("id",obj[1]);
                map.put("name",obj[0]);
                map.put("pId",obj[3]);
                dataList.add(map);
            }
        }
        return dataList;
    }

    @Override
    public List getJSDWNoTree(HttpSession session) {
        YhglYwYhyy userinfo = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
        List dataList = new ArrayList();
//        String sql = "select mc as NAME,id as ID,0 as PID from v_zftz_jsdw ";
//        String sql = "select mc as name,id as ID,bm as BM ,fjbm as PID from v_zftz_jsdw v1 left join yhgl_yw_yhyy y1 on v1.id=y1.szdwid where y1.yhzh='"+userinfo.getYhzh()+"' and yybm='"+userinfo.getYybm()+"'";
//        String sql = "select mc as name,id as ID,bm as BM ,fjbm as PID from v_zftz_jsdw where id = " + userinfo.getSzdwid();
        String sql = "select mc as name, id as ID, bm as BM, fjbm as PID\n" +
                "  from v_zftz_jsdw\n" +
                " where id in (select entid from v_zftz_yhtoenter where guid = " + userinfo.getGuid() + ")";
        List list =  baseDao.selectBySql(sql);//得到List集合
        if(list.size()>0){
            for(int i = 0; i < list.size() ; i++ ){
                Object[] obj = (Object[]) list.get(i);
                Map map = new HashMap();
                map.put("id",obj[1]);
                map.put("name",obj[0]);
                map.put("pId",obj[3]);
                dataList.add(map);
            }
        }
        return dataList;
    }

    public Object  xmqqchFileById(String id){
        String sql="select guid ,filebstype,filebsid,filename,filesize from zftz_file where filebstype=1 and filebsid="+id;
        return lowList(baseDao.selectBySql(sql));
    }

	@Override
	public List getXmqqchfj(String lx,String guid) {
		List list=new ArrayList();
		if (!StringUtil.isblank(guid)&&!StringUtil.isblank(lx)) {
			String sql ="select guid as fileid,filename,filedl,filesize from zftz_file where 1=1";
			sql=sql+" and filebsid='"+guid+"'";
			sql=sql+" and filebstype='"+lx+"'";
			 list=baseDao.selectMapsBySQL(sql);
		}

		return list;
	}

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
        String updateSql = "update zftz_xmqqch set LCHJ='" + lcbm + "' where ID=" + id;
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


	@Override
	public List selectJldw() {
		List list=baseDao.selectMapsBySQL("select bm as id,mc as text from zftz_xxb where lx='jldw' and yxbz='Y' ");
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


	@Override
	public Result getThyj(String id, HttpSession session) {
		Result result=new Result();
		List list=baseDao.selectBySql("select clyj from zftz_shjl where czlx='2' and sxlx='1' and xmid="+id+"order by clsj desc");
		if (list.size()>0) {
			result.setContent(list.get(0).toString());
			result.setSuccess(true);
		}else{
			result.setContent("无退回意见！");
			result.setSuccess(true);
		}
		
		return result;
	}

}