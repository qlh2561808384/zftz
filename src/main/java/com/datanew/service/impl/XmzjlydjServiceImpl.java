package com.datanew.service.impl;

import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.YhglYwYhyy;
import com.datanew.model.ZftzXm;
import com.datanew.model.ZftzXmbzcs;
import com.datanew.model.ZftzXmzjlydj;
import com.datanew.service.XmzjlydjService;
import com.datanew.util.StaticData;
import com.datanew.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by admin on 2019/6/4.
 */
@Service
public class XmzjlydjServiceImpl implements XmzjlydjService {
    @Autowired
    BaseDao baseDao;


//    public List selectXmmc() {
//        String sql = "select * from zftz_xm where zt=1 ";
//        return baseDao.selectMapsBySQL(sql);
//    }

    @Override
    public Result saveZjly(String id, ZftzXm zx, ZftzXmzjlydj zjly,String fileId, HttpSession session) {
        String[] fileIds=fileId.split(",");
        YhglYwYhyy userinfo = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
        Result result = new Result();
        if (zjly.getIde() == null) {
            zjly.setCzr(userinfo.getGuid().toString());
            zjly.setZt(1);
            zjly.setZtzJzaztz(zjly.getZtzJzaztz() == null ? new BigDecimal(0) : BigDecimal.valueOf(zjly.getZtzJzaztz().doubleValue() * 10000));
            zjly.setZtzDttz(zjly.getZtzDttz() == null ? new BigDecimal(0)  : BigDecimal.valueOf(zjly.getZtzDttz().doubleValue() * 10000));
            zjly.setZtzSbtz(zjly.getZtzSbtz() == null ? new BigDecimal(0)  : BigDecimal.valueOf(zjly.getZtzSbtz().doubleValue() * 10000));
            zjly.setZtzQttz(zjly.getZtzQttz() == null ? new BigDecimal(0)  : BigDecimal.valueOf(zjly.getZtzQttz().doubleValue() * 10000));
            zjly.setXmzjlyCzxzj(zjly.getXmzjlyCzxzj() == null ? null : BigDecimal.valueOf(zjly.getXmzjlyCzxzj().doubleValue() * 10000));
            zjly.setXmzjlyZyph(zjly.getXmzjlyZyph() == null ? null : BigDecimal.valueOf(zjly.getXmzjlyZyph().doubleValue() * 10000));
            zjly.setXmzjlyQt(zjly.getXmzjlyQt() == null ? null : BigDecimal.valueOf(zjly.getXmzjlyQt().doubleValue() * 10000));
            zjly.setTzxe(zjly.getTzxe() == null ? null : BigDecimal.valueOf(zjly.getTzxe().doubleValue() * 10000));
            zjly.setXmztz(new BigDecimal(Double.parseDouble(zjly.getZtzJzaztz().toString())+Double.parseDouble(zjly.getZtzSbtz().toString())+Double.parseDouble(zjly.getZtzDttz().toString())+Double.parseDouble(zjly.getZtzQttz().toString())));
///           zjly.setXmztz(zjly.setXmztz().add(zjly.getZtzJzaztz() == null ? null : BigDecimal.valueOf(zjly.getZtzJzaztz().doubleValue() * 10000)).add(zjly.getZtzDttz() == null ? null : BigDecimal.valueOf(zjly.getZtzDttz().doubleValue() * 10000)).add(zjly.getZtzSbtz() == null ? null : BigDecimal.valueOf(zjly.getZtzSbtz().doubleValue() * 10000)).add(zjly.getZtzQttz() == null ? null : BigDecimal.valueOf(zjly.getZtzQttz().doubleValue() * 10000)));
//            zjly.setXmztz(BigDecimal.valueOf(zjly.getZtzDttz().doubleValue() * 10000)));
//            zjly.setXmztz(BigDecimal.valueOf((Integer.parseInt(zjly.getZtzJzaztz().toString())+Integer.parseInt(zjly.getZtzSbtz().toString())+Integer.parseInt(zjly.getZtzDttz().toString())+Integer.parseInt(zjly.getZtzQttz().toString()))*10000));
//            String sql = "update zftz_xm set jsdw=" + zx.getJsdw() + ",xmlx=" + zx.getXmlx() + " where id=" + zx.getId();
//            baseDao.executeBySql(sql);
            zjly.setXmid(zx.getId());
            baseDao.save(zjly);
            for (int i = 0; i < fileIds.length; i++) {
            	if (!StringUtil.isblank(fileIds[i])) {
            		String updateSql = "update zftz_file a set a.filebsid = " + zjly.getIde() + " where guid = " + fileIds[i];
                    baseDao.executeBySql(updateSql);
				}
                
            }
            result.setContent(zjly.getIde());
            result.setSuccess(true);
            return result;
        } else {
//            String zjly_sql = "update zftz_xmzjlydj set id_zftz_xm="+zx.getId()+",xmghxz='" + zjly.getXmghxz() + "',rjl=" + zjly.getRjl() + ",ydmj=" + zjly.getYdmj() + ",jzzmj=" + zjly.getJzzmj() + ",dsjzmj=" + zjly.getDsjzmj() + ",xmlc=" + zjly.getXmlc() +
//                    ",dlkd=" + zjly.getDlkd() + ",jldw='" + zjly.getJldw() +  "',jykzzjbz=" + zjly.getJykzzjbz() + ",xmdwzj=" + zjly.getXmdwzj() + ",jsnr='" + zjly.getJsnr() + "'," +
//                    (zjly.getZtzJzaztz() == null ? " ztz_jzaztz=''," : " ztz_jzaztz=" + BigDecimal.valueOf(zjly.getZtzJzaztz().doubleValue()*10000) + ",") +
//                    (zjly.getZtzSbtz() == null ? " ztz_sbtz=''," : " ztz_sbtz=" + BigDecimal.valueOf(zjly.getZtzSbtz().doubleValue() * 10000) + ",") +
//                    (zjly.getZtzDttz() == null ? " ztz_dttz=''," : " ztz_dttz=" + BigDecimal.valueOf(zjly.getZtzDttz().doubleValue() * 10000) + ",") +
//                    (zjly.getZtzQttz() == null ? " ztz_qttz=''," : " ztz_qttz=" + BigDecimal.valueOf(zjly.getZtzQttz().doubleValue() * 10000) + ",") +
//                    (zjly.getXmzjlyCzxzj() == null ? " xmzjly_czxzj=''," : " xmzjly_czxzj=" + BigDecimal.valueOf(zjly.getXmzjlyCzxzj().doubleValue() * 10000) + ",") +
//                    (zjly.getXmzjlyZyph() == null ? " xmzjly_zyph=''," : " xmzjly_zyph=" + BigDecimal.valueOf(zjly.getXmzjlyZyph().doubleValue() * 10000) + ",") +
//                    (zjly.getXmzjlyQt() == null ? " xmzjly_qt=''," : " xmzjly_qt=" + BigDecimal.valueOf(zjly.getXmzjlyQt().doubleValue() * 10000) + ",") +
//                    (zjly.getTzxe() == null ? " tzxe=''," : " tzxe=" + BigDecimal.valueOf(zjly.getTzxe().doubleValue() * 10000) + ",") +
////                    "zmztz=" + zjly.getZtzJzaztz() == null ? null : BigDecimal.valueOf(zjly.getZtzJzaztz().doubleValue() * 10000).add(zjly.getZtzDttz() == null ? null : BigDecimal.valueOf(zjly.getZtzDttz().doubleValue() * 10000)).add(zjly.getZtzSbtz() == null ? null : BigDecimal.valueOf(zjly.getZtzSbtz().doubleValue() * 10000)).add(zjly.getZtzQttz() == null ? null : BigDecimal.valueOf(zjly.getZtzQttz().doubleValue() * 10000)) +
//                    "xmztz="+new BigDecimal(Double.parseDouble(zjly.getZtzJzaztz().toString())+Double.parseDouble(zjly.getZtzSbtz().toString())+Double.parseDouble(zjly.getZtzDttz().toString())+Double.parseDouble(zjly.getZtzQttz().toString())*10000)+
//                    " where id=" + zjly.getIde();
//            String xm_sql = "update zftz_xm set xmlx=" + zx.getXmlx() + "," + "jsdw=" + zx.getJsdw() + "," + "zgbm=" + zx.getZgbm() + " where id=" + zx.getId();
//            baseDao.executeBySql(zjly_sql);
//            baseDao.executeBySql(xm_sql);
        	zjly.setZtzJzaztz(zjly.getZtzJzaztz() == null ? new BigDecimal(0) : BigDecimal.valueOf(zjly.getZtzJzaztz().doubleValue() * 10000));
            zjly.setZtzDttz(zjly.getZtzDttz() == null ? new BigDecimal(0)  : BigDecimal.valueOf(zjly.getZtzDttz().doubleValue() * 10000));
            zjly.setZtzSbtz(zjly.getZtzSbtz() == null ? new BigDecimal(0)  : BigDecimal.valueOf(zjly.getZtzSbtz().doubleValue() * 10000));
            zjly.setZtzQttz(zjly.getZtzQttz() == null ? new BigDecimal(0)  : BigDecimal.valueOf(zjly.getZtzQttz().doubleValue() * 10000));
            zjly.setXmzjlyCzxzj(zjly.getXmzjlyCzxzj() == null ? null : BigDecimal.valueOf(zjly.getXmzjlyCzxzj().doubleValue() * 10000));
            zjly.setXmzjlyZyph(zjly.getXmzjlyZyph() == null ? null : BigDecimal.valueOf(zjly.getXmzjlyZyph().doubleValue() * 10000));
            zjly.setXmzjlyQt(zjly.getXmzjlyQt() == null ? null : BigDecimal.valueOf(zjly.getXmzjlyQt().doubleValue() * 10000));
            zjly.setTzxe(zjly.getTzxe() == null ? null : BigDecimal.valueOf(zjly.getTzxe().doubleValue() * 10000));
            zjly.setXmztz(new BigDecimal(Double.parseDouble(zjly.getZtzJzaztz().toString())+Double.parseDouble(zjly.getZtzSbtz().toString())+Double.parseDouble(zjly.getZtzDttz().toString())+Double.parseDouble(zjly.getZtzQttz().toString())));
            zjly.setXmid(zx.getId());
            zjly.setCzr(userinfo.getGuid().toString());
            zjly.setZt(1);
            baseDao.update(zjly);
            for (int i = 0; i < fileIds.length; i++) {
            	if (!StringUtil.isblank(fileIds[i])) {
            		 String updateSql = "update zftz_file a set a.filebsid = " + zjly.getIde() + " where a.guid = " + fileIds[i];
                     baseDao.executeBySql(updateSql);
				}
                   
            }
            result.setContent(zjly.getIde());
            result.setSuccess(true);
            return result;
        }
    }

    @Override
    public Pages getZjly(Pages page, String id, HttpSession session) {
        YhglYwYhyy userinfo = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
        String sql = "from zftz_xmzjlydj x1 " +
                "left join zftz_xm x2 on  x1.id_zftz_xm=x2.id " +
                "left join zftz_xmqqch x3 on x2.id=x3.id_zftz_xm  " +
                "left join yhgl_yw_yhyy y1 on x1.czr=to_char(y1.guid) "+
                "where x2.xmmc is not null and x1.zt=1 ";
//                + "and x2.jsdw='"+userinfo.getSzdwid()+"' ";
        List list = baseDao.selectMapsBySQL("select x1.id as ide,x1.id_zftz_xm as xmid,x1.xmghxz,x1.rjl,x1.ydmj,x1.jzzmj,x1.dsjzmj,x1.xmlc,x1.dlkd,x1.jldw,x1.jykzzjbz,x1.xmdwzj," +
                "x2.xmmc,x2.zgbm,x2.xmlx,x2.jsdw,x2.id ,x1.jsnr,x3.id as xmqqchguid,"+
                "case when x1.ztz_jzaztz is null then 0 else round(x1.ztz_jzaztz/10000,2) end  as ztz_jzaztz," +
                "case when x1.ztz_sbtz is null then 0 else round(x1.ztz_sbtz/10000,2) end as ztz_sbtz ," +
                "case when x1.ztz_dttz is null then 0 else round(x1.ztz_dttz/10000,2) end  as ztz_dttz," +
                "case when x1.ztz_qttz is null then 0 else round(x1.ztz_qttz/10000,2) end  as ztz_qttz," +
                "case when x1.xmzjly_czxzj is null then 0 else round(x1.xmzjly_czxzj/10000,2) end as xmzjly_czxzj," +
                "case when x1.xmzjly_zyph is null then 0 else round(x1.xmzjly_zyph/10000,2) end as xmzjly_zyph," +
                "case when x1.xmzjly_qt is null then 0 else round(x1.xmzjly_qt/10000,2) end as xmzjly_qt," +
                "case when x1.tzxe is null then 0 else round(x1.tzxe/10000,2) end as tzxe," +
                "round((x1.ztz_jzaztz+x1.ztz_sbtz+x1.ztz_dttz+x1.ztz_qttz)/10000,2) as XMZTZ,round((x1.ztz_jzaztz+x1.ztz_sbtz+x1.ztz_dttz+x1.ztz_qttz-x1.tzxe)/10000,2) as HZTZXE,(select count(*)  from zftz_xmjgjs s where s.zt='1' and s.id_zftz_xm=x1.id_zftz_xm ) as isjs " + sql, null, page.getOffset(), page.getLimit());
        Long count = baseDao.getCountBySQL("select count(*) " + sql, null);
        page.setTotal(count.intValue());
        page.setRows(lowList(list));
        return page;
    }

    @Override
    public Object selectXmmc(HttpSession session) {
        YhglYwYhyy userinfo = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
        String hql = " from ZftzXm where zt=1 ";
        return baseDao.selectByHql(hql);
    }

    @Override
    public Object getGsxxByXmid(String xmid) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        String sql1 = "select jsdw from zftz_xm where id=" + xmid;
        List list = baseDao.selectBySql(sql1);
        String sql2 = "select xmlx from zftz_xm where id=" + xmid;
        List list2 = baseDao.selectBySql(sql2);
//        String sql3="select x1.xmghxz,x1.rjl,x1.ydmj,x1.jzzmj,x1.dsjzmj,x1.xmlc,x1.dlkd,x1.jldw,x1.tzxe,x1.jykzzjbz,x1.xmdwzj from zftz_xmqqch where id_zftz_xm="+ xmid;
        if (list.size() > 0) {
            if (list.get(0) != null) {
                map.put("jsdw", list.get(0).toString());
            }
        } else {
            map.put("jsdw", "--");
        }
        if (list2.size() > 0) {
            if (list2.get(0) != null) {
                map.put("xmlx", list2.get(0).toString());
            }
        } else {
            map.put("xmlx", "--");
        }
        return map;
    }
   public  Object getXmqqch(String xmid){
//       String sql="select SLDH,XMGHXZ,RJL,YDMJ,JZZMJ,DSJZMJ,XMLC,DLKD,XMDWZJ,WJZJBZ,TLXZJBZ,JLDW"
//       		+ ",LXR,LXDH,JSNR,ZTZ_JZAZTZ,ZTZ_SBTZ,ZTZ_DTTZ,ZTZ_QTTZ,XMZJLY_CZXZJ,XMZJLY_ZYPH,XMZJLY_QT,"
//       		+ "TZXE,JYKZZJBZ,XMPHJY from zftz_xmqqch where id_zftz_xm="+xmid;
       String sql="select * from zftz_xmqqch where id_zftz_xm="+xmid;
       List list= baseDao.selectMapsBySQL(sql);
       if(list.size()>0){
           return  StringUtil.lowList(list);
       }else {
           return null;
       }

    }

    public List lowList(List l) {
        List list = new ArrayList();
        for (int i = 0; i < l.size(); i++) {
            Map m = (Map) l.get(i);
            Map p = new HashMap();
            Iterator iter = m.keySet().iterator();
            while (iter.hasNext()) {
                String key = (String) iter.next();
                Object value = m.get(key);
                p.put(key.toLowerCase(), value);
            }
            list.add(p);
        }

        return list;

    }

    @Override
    public Result delXmzjlydj(String ids, HttpSession session) {
        Result result = new Result();
        String xmzjlydj_sql = "update zftz_xmzjlydj set zt=0 where id in (" + ids + ")";
        baseDao.executeBySql(xmzjlydj_sql);
        result.setSuccess(true);
        return result;
    }

    public List getXmqqsh(String id) {

//        String sql ="select x1.id as id1,x1.id_zftz_xm as xmId,x1.lchj,x2.id,x1.sldh,x1.czsj,x1.czr,x2.xmmc,x2.xmlx,x2.jsdw,x2.zt as xmzt,x2.zgbm," +
//                "x1.xmghxz,x1.rjl,x1.ydmj,x1.jzzmj,x1.dsjzmj,x1.xmlc,x1.dlkd,x1.xmdwzj,x1.wjzjbz,x1.tlxzjbz,x1.jldw,x1.lxr,x1.lxdh," +
//                "case when x1.ztz_jzaztz is null then 0 else round(x1.ztz_jzaztz/10000,2) end  as ZTZ_JZAZTZ2," +
//                "case when x1.ztz_sbtz is null then 0 else round(x1.ztz_sbtz/10000,2) end as ZTZ_SBTZ1," +
//                "case when x1.ztz_dttz is null then 0 else round(x1.ztz_dttz/10000,2) end  as ZTZ_DTTZ1," +
//                "case when x1.ztz_qttz is null then 0 else round(x1.ztz_qttz/10000,2) end  as ZTZ_QTTZ1," +
//                "case when x1.xmzjly_czxzj is null then 0 else round(x1.xmzjly_czxzj/10000,2) end as xmzjly_czxzj," +
//                "case when x1.xmzjly_zyph is null then 0 else round(x1.xmzjly_zyph/10000,2) end as xmzjly_zyph," +
//                "case when x1.xmzjly_qt is null then 0 else round(x1.xmzjly_qt/10000,2) end as xmzjly_qt," +
//                "case when x1.tzxe is null then 0 else round(x1.tzxe/10000,2) end as tzxe," +
//                "case when x1.jykzzjbz is null then 0 else round(x1.jykzzjbz/10000,2) end as jykzzjbz," +
//                "x1.jsnr,x1.zt,x1.xmphjy,round((x1.ztz_jzaztz+x1.ztz_sbtz+x1.ztz_dttz+x1.ztz_qttz)/10000,2) as XMZTZ,round((x1.ztz_jzaztz+x1.ztz_sbtz+x1.ztz_dttz+x1.ztz_qttz-x1.tzxe)/10000,2) as HZTZXE   "+
//                "from zftz_xmqqch x1 left join zftz_xm x2 on x1.id_zftz_xm=x2.id where x2.id="+id;
        String sql = "select a.xmmc,\n" +
                "       a.jsdw,\n" +
                "       a.zgbm ,\n" +
                "       b.xmghxz,\n" +
                "       a.xmlx,\n" +
                "       b.rjl,\n" +
                "       b.ydmj,\n" +
                "       b.jzzmj,\n" +
                "       b.dsjzmj,\n" +
                "       b.xmlc,\n" +
                "       b.dlkd,\n" +
                "       b.lxdh,\n" +
                "       b.xmdwzj,\n" +
                "       b.wjzjbz,\n" +
                "       b.jldw,\n" +
                "       b.tlxzjbz,\n" +
                "       b.lxr,\n" +
                "       b.jsnr,\n" +
                "       b.id as guids,\n" +
                "       round(nvl(b.ztz_jzaztz, 0)/10000,2) ZTZJZAZTZ1,\n" +
                "       round(nvl(b.ztz_sbtz, 0)/10000,2) ZTZSBTZ1,\n" +
                "       round(nvl(b.ztz_dttz, 0)/10000,2) ZTZDTTZ1,\n" +
                "       round(nvl(b.ztz_qttz, 0)/10000,2) ZTZQTTZ1,\n" +
                "       round((nvl(b.ztz_jzaztz, 0) + nvl(b.ztz_sbtz, 0) + nvl(b.ztz_dttz, 0) +\n" +
                "       nvl(b.ztz_qttz, 0))/10000,2) ZTZHJ1,\n" +
                "       round(nvl(b.xmzjly_czxzj, 0)/10000,2) as XMZJLYCZXZJ1,\n" +
                "       round(nvl(b.xmzjly_zyph, 0)/10000,2) as XMZJLYZYPH1,\n" +
                "       round(nvl(b.xmzjly_qt, 0)/10000,2) as XMZJLYQT1,\n" +
                "       round((nvl(b.xmzjly_czxzj, 0) + nvl(b.xmzjly_zyph, 0) + nvl(b.xmzjly_qt, 0))/10000,2) XMZJLYHJ1,\n" +
                "       b.xmphjy,\n" +
                "       round(nvl(b.tzxe, 0)/10000,2) tzxe,\n" +
                "       round(nvl(b.jykzzjbz, 0),2) jykzzjbz,\n" +
                "       round((nvl(b.ztz_jzaztz, 0) + nvl(b.ztz_sbtz, 0) + nvl(b.ztz_dttz, 0) +\n" +
                "       nvl(b.ztz_qttz, 0) - nvl(b.tzxe, 0))/10000,2) hjje\n" +
                "  from zftz_xm a\n" +
                "  left join zftz_xmqqch b\n" +
                "    on a.id = b.id_zftz_xm\n" +
                "    where a.id = " + id;


        List list = baseDao.selectMapsBySQL(sql);
        return list;

    }

//获得建设单位
    @Override
    public List selectJsdw(HttpSession session) {
        YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
//        String sql = "select * from v_zftz_jsdw v1 left join yhgl_yw_yhyy y1 on v1.id=y1.szdwid where y1.szdwid='"+userinfo.getSzdwid()+"' ";
        String sql = "select * from v_zftz_jsdw   " ;
        return baseDao.selectMapsBySQL(sql);
    }
    @Override
    public List getJSDWTree(HttpSession session) {
        YhglYwYhyy userinfo = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
        List dataList = new ArrayList();
//        String sql = "select mc as NAME,id as ID,0 as PID from v_zftz_jsdw ";
        String sql = "select mc as name,id as ID,bm as BM ,fjbm as PID from v_zftz_jsdw  " ;
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
        String sql="select guid,filebstype,filebsid,filename,filesize from zftz_file where filebstype=1 and filebsid="+id;
        return lowList(baseDao.selectBySql(sql));
    }

    @Override
    public List getXmqqchfj(String guid) {
        List list=new ArrayList();
        if (!StringUtil.isblank(guid)) {
            String sql ="select * from zftz_file where 1=1";
            sql=sql+" and filebsid="+guid;
            list=baseDao.selectMapsBySQL(sql);
        }

        return list;
    }

	@Override
	public List getXmqqshzjlyxm(String id) {
		String sql = "select b.xmmc,\n" +
                "       b.jsdw,\n" +
                "       b.zgbm,\n" +
                "       b.xmghxz,\n" +
                "       b.xmlx,\n" +
                "       b.rjl,\n" +
                "       b.ydmj,\n" +
                "       b.jzzmj,\n" +
                "       b.dsjzmj,\n" +
                "       b.xmlc,\n" +
                "       b.dlkd,\n" +
                "       b.lxdh,\n" +
                "       b.xmdwzj,\n" +
                "       b.wjzjbz,\n" +
                "       b.jldw,\n" +
                "       b.tlxzjbz,\n" +
                "       b.lxr,\n" +
                "       b.jsnr,\n" +
                "       b.id as guids,\n" +
                "       round(nvl(b.ztz_jzaztz, 0)/10000,2) ZTZJZAZTZ1,\n" +
                "       round(nvl(b.ztz_sbtz, 0)/10000,2) ZTZSBTZ1,\n" +
                "       round(nvl(b.ztz_dttz, 0)/10000,2) ZTZDTTZ1,\n" +
                "       round(nvl(b.ztz_qttz, 0)/10000,2) ZTZQTTZ1,\n" +
                "       round((nvl(b.ztz_jzaztz, 0) + nvl(b.ztz_sbtz, 0) + nvl(b.ztz_dttz, 0) +\n" +
                "       nvl(b.ztz_qttz, 0))/10000,2) ZTZHJ1,\n" +
                "       round(nvl(b.xmzjly_czxzj, 0)/10000,2) as XMZJLYCZXZJ1,\n" +
                "       round(nvl(b.xmzjly_zyph, 0)/10000,2) as XMZJLYZYPH1,\n" +
                "       round(nvl(b.xmzjly_qt, 0)/10000,2) as XMZJLYQT1,\n" +
                "       round((nvl(b.xmzjly_czxzj, 0) + nvl(b.xmzjly_zyph, 0) + nvl(b.xmzjly_qt, 0))/10000,2) XMZJLYHJ1,\n" +
                "       b.xmphjy,\n" +
                "       round(nvl(b.tzxe, 0)/10000,2) tzxe,\n" +
                "       round(nvl(b.jykzzjbz, 0)/10000,2) jykzzjbz,\n" +
                "       round((nvl(b.ztz_jzaztz, 0) + nvl(b.ztz_sbtz, 0) + nvl(b.ztz_dttz, 0) +\n" +
                "       nvl(b.ztz_qttz, 0) - nvl(b.tzxe, 0))/10000,2) hjje\n" +
                "  from  " +
                "   v_zftz_xmjbxx b\n" +
                "    " +
                "    where b.id_zftz_xm = " + id;


        List list = baseDao.selectMapsBySQL(sql);
        return list;
	}

	
}
