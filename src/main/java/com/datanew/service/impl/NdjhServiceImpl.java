package com.datanew.service.impl;

import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.*;
import com.datanew.service.NdjhService;
import com.datanew.util.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wuwei
 * @create 2019/5/22 9:16
 * @desc
 **/
@Service("ndjhService")
public class NdjhServiceImpl implements NdjhService {

    @Resource
    private BaseDao baseDao;

    public List<Xxb> queryNd(){
        List param=new ArrayList();
        String hql="select new map(t.id as id,t.bm as bm,t.mc as mc,t.fjbm as fjbm,t.yxbz as yxbz,t.lx as lx,t.ms as ms,t.fjbm as pid,case when t.fjbm='0' then 0  else 1 end as ISLEAF  )  from com.datanew.model.Xxb t where t.yxbz='Y' and t.fjbm = '2000' and mc>=to_char(sysdate,'yyyy')-5 and mc<=to_char(sysdate,'yyyy')+5 and lx = 'nd' order by t.mc";

        return baseDao.selectByHql(hql,param);
    }

    public List queryXzqh(){
        String sql="select t.bm as bm,t.mc as mc,t.fjbm as fjbm,t.yxbz as yxbz,t.fjbm as pid,case when t.fjbm='0' then 0  else 1 end as ISLEAF    from v_zftz_xzqh t where t.yxbz='Y' ";

        return baseDao.selectMapsBySQL(sql);
    }

    public String findZylx(String id){
        String sql="select  b.mc   from fbyx_zygl t,fbyx_xxb b where t.zmlx=b.bm and t.id="+id;
        List list = baseDao.selectMapsBySQL(sql);
        Map m = (Map)list.get(0);
        return m.get("MC").toString();
    }

    public Pages queryGsnjh(Pages page, String nd,String ptgs, HttpSession session){
        if(!StringUtil.isblank(nd)&&!StringUtil.isblank(ptgs)){
            YhglYwYhyy userinfo =  (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);

            //获取各批次下的年份
            List list  = new ArrayList();

            int ksnf = Integer.valueOf(nd);
            int[] ndarr = new int[5];
            for (int i = 0; i < 5; i++) {
                ndarr[i]=ksnf;
                ksnf++;
            }

            DecimalFormat df = new DecimalFormat("0.00");

            String sql1 = "";
            String sql2 = "";
            String sql3 = "";
            String nsrjh = "";
            String nzcjh = "";
            String nhzjh = "";

            for(int  i =0;i<ndarr.length;i++){

                sql1 = " select " +
                        "case when sum(nvl(s.nc_jhje,0)/10000) is null then 0 else sum(nvl(s.nc_jhje,0)/10000) end  +" +
                        "sum(case when s.tz_jhje is null then 0 else s.tz_jhje/10000+s.nc_jhje/10000 end)  " +
                        " as nsrjh  from fbyx_ndsrb s where  to_char(sysdate,'yyyyMMdd') between s.czrq and s.zfrq and  substr(s.jhcrrq,0,4) ="+ndarr[i]+" and s.ptgs="+ptgs;
                List<Map> list1 = baseDao.selectMapsBySQL(sql1);
                String je1 = "";
                if(list1.get(0).get("NSRJH")==null){
                    je1 = "";
                }else{
                    je1 = list1.get(0).get("NSRJH").toString();
                }

                nsrjh = df.format(Double.parseDouble("".equals(je1)?"0":je1));

                sql2 = "select " +
                        "case when sum(z.NC_YYGGYS/10000) is null then 0 else sum(z.NC_YYGGYS/10000) end +" +
                        "case when sum(z.NC_PHZY/10000) is null then 0 else sum(z.NC_PHZY/10000) end +" +
                        "case when sum(z.NC_PHZC/10000) is null then 0 else sum(z.NC_PHZC/10000) end +" +
                        "case when sum(z.NC_PPPSHZB/10000) is null then 0 else sum(z.NC_PPPSHZB/10000) end +" +
                        "case when sum(z.NC_QXFD/10000) is null then 0 else sum(z.NC_QXFD/10000) end +" +
                        "case when sum(z.NC_QT/10000) is null then 0 else sum(z.NC_QT/10000) end +" +
                        "case when sum(z.TZ_YYGGYS/10000) is null then 0 else sum(z.TZ_YYGGYS/10000) end +" +
                        "case when sum(z.TZ_PHZY/10000) is null then 0 else sum(z.TZ_PHZY/10000) end +" +
                        "case when sum(z.TZ_PHZC/10000) is null then 0 else sum(z.TZ_PHZC/10000) end +" +
                        "case when sum(z.TZ_PPPSHZB/10000) is null then 0 else sum(z.TZ_PPPSHZB/10000) end +" +
                        "case when sum(z.TZ_QXFD/10000) is null then 0 else sum(z.TZ_QXFD/10000) end +" +
                        "case when sum(z.TZ_QT/10000) is null then 0 else sum(z.TZ_QT/10000) end " +
                        " as nzcjh from fbyx_ndzcjh z where to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq and z.ptgs="+ptgs+" and z.nd="+ndarr[i];
                List<Map> list2 = baseDao.selectMapsBySQL(sql2);
                String je2 = list2.get(0).get("NZCJH").toString();
                nzcjh = df.format(Double.parseDouble("".equals(je2)?"0":je2));

                sql3 = "select " +
                        "case when sum(n.NC_BJ/10000) is null then 0 else sum(n.NC_BJ/10000) end +" +
                        "case when sum(n.NC_LX/10000) is null then 0 else sum(n.NC_LX/10000) end +" +
                        "case when sum(n.TZ_BJ/10000) is null then 0 else sum(n.TZ_BJ/10000) end +" +
                        "case when sum(n.TZ_LX/10000) is null then 0 else sum(n.TZ_LX/10000) end " +
                        "as nhzjh from fbyx_ndhzb n where to_char(sysdate,'yyyyMMdd') between n.czrq and n.zfrq and n.ptgs="+ptgs+" and n.nd="+ndarr[i];
                List<Map> list3 = baseDao.selectMapsBySQL(sql3);
                String je3 = list3.get(0).get("NHZJH").toString();
                nhzjh = df.format(Double.parseDouble("".equals(je3)?"0":je3));

                Map map = new HashMap();
                map.put("year",ndarr[i]);
                map.put("ptgs",ptgs);
                map.put("nsrjh",nsrjh);
                map.put("nzcjh",nzcjh);
                map.put("nhzjh",nhzjh);

                list.add(map);

            }

            page.setTotal(ndarr.length);
            page.setRows(list);


        }
        return page;
    }

    public Long insertXtFj(XtFj fj) {
        XtFj t = (XtFj)baseDao.save(fj);
        return t.getId();
    }

    public List fileInfo(String uuid){

        List l = new ArrayList();
        if(!StringUtil.isblank(uuid)){
            String[] arr = uuid.split(",");
            for(int i =0;i<arr.length;i++){
                String sql = "select t.* from fbyx_fj t where t.wjm='"+arr[i]+"'";
                List list =  baseDao.selectMapsBySQL(sql);
                if(list.size()>0){
                    Map  map = (Map) list.get(0);
                    Map filemap = new HashMap();
                    filemap.put("name",map.get("MC"));
                    filemap.put("type",map.get("LX"));
                    filemap.put("size",map.get("DX"));
                    filemap.put("uploadDate",map.get("CJSJ"));
                    filemap.put("fileId",map.get("WJM"));
                    l.add(filemap);
                }
            }
        }



        return l;
    }

    public Result delFj(String id, Result result){
        FTPUtils ftp = new FTPUtils();
        List<XtFj> fj = baseDao.selectByHql("from XtFj where wjm='"+id+"'");
//	    baseDao.delete(fj.get(0));

        String path = RegexUtil.formateDir(ConfigureParser.getPropert("ftp.upload.path"));
        String name = fj.get(0).getWjm()+"."+fj.get(0).getLx();
        ftp.deleteFile(path,name);
        baseDao.executeBySql("delete from fbyx_fj where wjm='"+id+"'");

        result.setContent("删除成功");
        result.setSuccess(true);
        return result;

    }

    public List queryZycTree(String nd,String ptgs){
//        String sql="select z.id,z.zymc as mc,0 as pid,1 as isleaf from  fbyx_zygl z where  " +
//
//                " z.ptgs='"+ptgs+"' and z.zt=1";
        String sql = "select z.id, z.zymc as mc, 0 as pid, 1 as isleaf" +
                "  from fbyx_zygl z" +
                "  where not exists" +
                " (select *" +
                "          from fbyx_ndsrb t" +
                "         where to_char(sysdate, 'yyyyMMdd') between t.czrq and t.zfrq" +
                "           and (substr(t.jhcrrq, 0, 4) < "+nd+" or" +
                "                substr(t.jhcrrq, 0, 4) > ("+nd+" + 4))" +
                "           and z.id = t.zymcid)" +
                "   and z.ptgs = '"+ptgs+"'" +
                "   and z.zt = 1  order by z.id";
        List list =baseDao.selectMapsBySQL(sql);
        return list;
    }

    public Long getZmlxByZy(String id){
        FbyxZygl  zy =(FbyxZygl)baseDao.load(FbyxZygl.class,Long.parseLong(id));
        return zy.getZmlx();
    }

    public List queryXmcTree(){
        String sql="select z.id,z.xmmc as mc,0 as pid,1 as isleaf from  zftz_xm z where z.zt=1 and z.sffb=1";
        List list =baseDao.selectMapsBySQL(sql);
        return list;
    }

    public List queryZwcTree(String ptgs){
        String sql="select z.id,z.zwmc as mc,0 as pid,1 as isleaf from  fbyx_zwgl z where z.ptgs='"+ptgs+"' and z.zt=1";
        List list =baseDao.selectMapsBySQL(sql);
        return list;
    }

    public Pages getSrbList(Pages page, String nd,String ptgs, HttpSession session){
        StringBuffer sql = new StringBuffer();
        sql.append("select z.id,z.ptgs,z.zmlx,x.mc as zmlxmc,z.zymcid,z.zymc as zymc,z.ssxzqh,r.mc as ssxzqhmc, z.jhcrrq," +
                "case when z.nc_jhje is null then 0 else z.nc_jhje/10000 end as nc_jhje," +
                "case when z.tz_jhje is null then 0 else (nvl(z.nc_jhje,0)+nvl(z.tz_jhje,0))/10000 end as tz_jhje,"+
                "z.czrq,z.zfrq " +
                "from fbyx_ndsrb z " +
                "left join fbyx_xxb x on z.zmlx=x.bm and x.lx='srgh' " +
                "left join v_zftz_xzqh r on z.ssxzqh=r.bm "+
                "where 1=1 and to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq ");
        if(!StringUtil.isblank(nd)){
            sql.append(" and substr(z.jhcrrq,0,4) ="+nd);
        }
        if(!StringUtil.isblank(ptgs)){
            sql.append(" and z.ptgs ="+ptgs);
        }

        sql.append(" order by z.id");
        List list =baseDao.selectMapsBySQL(sql.toString(),null,page.getOffset(),page.getLimit());
        Long count =baseDao.getCountBySQL("select count(*) from ("+sql+") t",null);

        String uuid="";
        for(int i=0;i<list.size();i++){
            Map m = (Map) list.get(i);
            String zyid = m.get("ZYMCID").toString();
            List<XtFj> list1 = baseDao.selectByHql(" from XtFj where zyid="+zyid);
            for(int j=0;j<list1.size();j++){
                uuid += list1.get(j).getWjm()+",";
            }
            uuid= StringUtil.isblank(uuid)?"":uuid.substring(0, uuid.length()-1);
            m.put("FJGUID",uuid);
            uuid="";
        }

        page.setTotal(count.intValue());
        page.setRows(list);

        return page;
    }

    public Pages getSrbDzList(Pages page, String srid, HttpSession session){
        if(!"undefined".equals(srid)){
            StringBuffer sql = new StringBuffer();
            sql.append("select z.id,z.id_fbyx_ndsrb,z.jhdz_rq," +
                    "case when z.jhdz_je is null then 0 else z.jhdz_je/10000 end as jhdz_je,"+
                    "z.sjdz_rq,"+
                    "case when z.sjdz_je is null then 0 else z.sjdz_je/10000 end as sjdz_je,"+
                    "z.czrq " +
                    "from fbyx_ndsrjh_dz z where 1=1 ");
            if(!StringUtil.isblank(srid)){
                sql.append(" and id_fbyx_ndsrb ="+srid);
            }

            sql.append(" order by z.id");
            List list =baseDao.selectMapsBySQL(sql.toString(),null,page.getOffset(),page.getLimit());
            Long count =baseDao.getCountBySQL("select count(*) from ("+sql+") t",null);


            page.setTotal(count.intValue());
            page.setRows(list);
        }


        return page;
    }

    public Result validateSrb(String zymcid, HttpSession session){
        Result result = new Result();
        StringBuffer sql = new StringBuffer();
        sql.append("select zymc " +
                "from fbyx_ndsrb z " +
                "where to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq and z.zymcid='"+zymcid+"'");
        List zlist = baseDao.selectMapsBySQL(sql.toString());
        if(zlist.size()>0){
            Map z = (Map)zlist.get(0);
            String zymc  = z.get("ZYMC").toString();
            result.setSuccess(true);
            result.setContent(zymc+"已被使用！");

        }

        return result;
    }

    public Result validateZcb(String xmid,String nd, HttpSession session){
        Result result = new Result();
        StringBuffer sql = new StringBuffer();
        sql.append("select z.xmid,x.xmmc " +
                "from fbyx_ndzcjh z " +
                "left join zftz_xm x on z.xmid=x.id "+
                "where to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq and z.xmid='"+xmid+"' and z.nd='"+nd+"'");
        List zlist = baseDao.selectMapsBySQL(sql.toString());
        if(zlist.size()>0){
            Map z = (Map)zlist.get(0);
            String xmmc  = z.get("XMMC").toString();
            result.setSuccess(true);
            result.setContent(xmmc+"在该年度已被使用！");

        }

        return result;
    }

    public Result validateHzb(String zwid,String nd, HttpSession session){
        Result result = new Result();
        StringBuffer sql = new StringBuffer();
        sql.append("select z.zwid,x.zwmc " +
                "from fbyx_ndhzb z " +
                "left join fbyx_zwgl x on z.zwid=x.id "+
                "where to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq and z.zwid='"+zwid+"' and z.nd='"+nd+"'");
        List zlist = baseDao.selectMapsBySQL(sql.toString());
        if(zlist.size()>0){
            Map z = (Map)zlist.get(0);
            String zwmc  = z.get("ZWMC").toString();
            result.setSuccess(true);
            result.setContent(zwmc+"在该年度已被使用！");

        }

        return result;
    }


    public Result saveOrUpdateBySrb(FbyxNdsrb t,String ptgs, HttpSession session,Result result){
        YhglYwYhyy userinfo= (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER );
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date date  = new Date();
        String mx_zb_id = "";
        try{

            if(t.getId()==null){
                FbyxZygl zy = (FbyxZygl)baseDao.load(FbyxZygl.class,t.getZymcid());
                t.setPtgs(Long.parseLong(ptgs));
                t.setZmlx(zy.getZmlx());
                t.setZymcid(zy.getId());
                t.setZymc(zy.getZymc());
                if(t.getTzjhje()!=null&&t.getNcjhje()==null){
                    t.setTzjhje(BigDecimal.valueOf((t.getTzjhje().doubleValue()-0)*10000));
                }else if(t.getTzjhje()==null&&t.getNcjhje()!=null){
                    t.setNcjhje(BigDecimal.valueOf(t.getNcjhje().doubleValue()*10000));
                }else if(t.getTzjhje()!=null&&t.getNcjhje()!=null){
                    t.setTzjhje(BigDecimal.valueOf((t.getTzjhje().doubleValue()-t.getNcjhje().doubleValue())*10000));
                    t.setNcjhje(BigDecimal.valueOf(t.getNcjhje().doubleValue()*10000));
                }

                t.setCzrq(Long.parseLong(sdf.format(date)));
                t.setZfrq(99991231L);
                t.setDjr(userinfo.getYhzh());

                baseDao.save(t);

                mx_zb_id = String.valueOf(t.getId());
            }else{
                Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
                calendar.add(Calendar.DATE, -1);    //得到前一天
                String  yestedayDate = sdf.format(calendar.getTime());
                FbyxZygl zy = (FbyxZygl)baseDao.load(FbyxZygl.class,t.getZymcid());
                //修改作废日期为当前时间-1天 并插入新数据
                baseDao.executeBySql( " update fbyx_ndsrb set zfrq="+Long.parseLong(yestedayDate)+"  where id="+t.getId());

                String  tzjhje = "''";
                String ncjhje = "''";
                if(t.getTzjhje()!=null&&t.getNcjhje()==null){
                    tzjhje = String.valueOf(BigDecimal.valueOf((t.getTzjhje().doubleValue()-0)*10000));
                }else if(t.getTzjhje()==null&&t.getNcjhje()!=null){
                    ncjhje = String.valueOf(BigDecimal.valueOf(t.getNcjhje().doubleValue()*10000));
                }else if(t.getTzjhje()!=null&&t.getNcjhje()!=null){
                    tzjhje = String.valueOf(BigDecimal.valueOf((t.getTzjhje().doubleValue()-t.getNcjhje().doubleValue())*10000));
                    ncjhje = String.valueOf(BigDecimal.valueOf(t.getNcjhje().doubleValue()*10000));
                }
                baseDao.executeBySql("insert into FBYX_NDSRB (id,ptgs, zmlx, zymcid, zymc, ssxzqh, jhcrrq, nc_jhje, tz_jhje, czrq, zfrq, djr) " +
                        "values (SEQ_FBYX_NDSRB.NEXTVAL,"+ptgs+", "+zy.getZmlx()+","+zy.getId()+" , '"+zy.getZymc()+"', '"+t.getSsxzqh()+"', "+t.getJhcrrq()+", "+
                        ncjhje+", "+
                        tzjhje+
                        ", "+Long.parseLong(sdf.format(date))+", 99991231, '"+userinfo.getYhzh()+"')");

                //刚插入按资源查肯定能查到因为资源只能使用一次
                StringBuffer sql = new StringBuffer();
                sql.append("select z.id "+
                        "from fbyx_ndsrb z " +
                        "where to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq and z.zymcid="+zy.getId());
                List list  = baseDao.selectMapsBySQL(sql.toString());
                Map m = (Map)list.get(0);
                mx_zb_id = m.get("ID").toString();
            }


            result.setSuccess(true);
            result.setContent(mx_zb_id);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setContent("修改失败!");
            return result;
        }
        return result;
    }

    public $Result deleteBySrb(String  id, HttpSession session){
        if(!StringUtil.isblank(id)){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
                calendar.add(Calendar.DATE, -1);    //得到前一天
                String  yestedayDate = sdf.format(calendar.getTime());

                baseDao.executeBySql(" update fbyx_ndsrb set zfrq="+Long.parseLong(yestedayDate)+"  where id="+id);
            }catch (Exception e){
                e.printStackTrace();
                return $Result.fail();
            }
        }

        return $Result.success();
    }

    public $Result deleteBySrDz(String  id, HttpSession session){
        if(!StringUtil.isblank(id)){
            try{
                baseDao.executeBySql(" delete from  fbyx_ndsrjh_dz where id="+id);
            }catch (Exception e){
                e.printStackTrace();
                return $Result.fail();
            }
        }

        return $Result.success();
    }

    public Pages getZcbList(Pages page, String nd,String ptgs, HttpSession session){
        StringBuffer sql = new StringBuffer();
        sql.append("select z.id,z.nd,z.ptgs,z.xmid,x.xmmc," +
                "case when z.bnjhtz is null then 0 else z.bnjhtz/10000 end as bnjhtz," +
                "case when z.nc_yyggys is null then 0 else z.nc_yyggys/10000 end as nc_yyggys," +
                "case when z.nc_phzy is null then 0 else z.nc_phzy/10000 end as nc_phzy," +
                "case when z.nc_phzc is null then 0 else z.nc_phzc/10000 end as nc_phzc," +
                "case when z.nc_pppshzb is null then 0 else z.nc_pppshzb/10000 end as nc_pppshzb," +
                "case when z.nc_qxfd is null then 0 else z.nc_qxfd/10000 end as nc_qxfd," +
                "case when z.nc_qt is null then 0 else z.nc_qt/10000 end as nc_qt," +
                "case when z.tz_yyggys is null then 0 else z.tz_yyggys/10000 end as tz_yyggys," +
                "case when z.tz_phzy is null then 0 else z.tz_phzy/10000 end as tz_phzy," +
                "case when z.tz_phzc is null then 0 else z.tz_phzc/10000 end as tz_phzc," +
                "case when z.tz_pppshzb is null then 0 else z.tz_pppshzb/10000 end as tz_pppshzb," +
                "case when z.tz_qxfd is null then 0 else z.tz_qxfd/10000 end as tz_qxfd," +
                "case when z.tz_qt is null then 0 else z.tz_qt/10000 end as tz_qt," +
                "(nvl(z.nc_yyggys,0)+nvl(z.tz_yyggys,0))/10000 as nd_yyggys," +
                "(nvl(z.nc_phzy,0)+nvl(z.tz_phzy,0))/10000 as nd_phzy," +
                "(nvl(z.nc_phzc,0)+nvl(z.tz_phzc,0))/10000 as nd_phzc," +
                "(nvl(z.nc_pppshzb,0)+nvl(z.tz_pppshzb,0))/10000 as nd_pppshzb," +
                "(nvl(z.nc_qxfd,0)+nvl(z.tz_qxfd,0))/10000 as nd_qxfd," +
                "(nvl(z.nc_qt,0)+nvl(z.tz_qt,0))/10000 as nd_qt," +
                "z.czrq,z.zfrq " +
                "from fbyx_ndzcjh z " +
                "left join zftz_xm x on z.xmid=x.id "+
                "where 1=1 and to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq");
        if(!StringUtil.isblank(nd)){
            sql.append(" and z.nd ="+nd);
        }
        if(!StringUtil.isblank(ptgs)){
            sql.append(" and z.ptgs ="+ptgs);
        }

        sql.append(" order by z.id");
        List list =baseDao.selectMapsBySQL(sql.toString(),null,page.getOffset(),page.getLimit());
        Long count =baseDao.getCountBySQL("select count(*) from ("+sql+") t",null);

        for (int i = 0; i <list.size() ; i++) {
            Map m = (Map)list.get(i);
            String ztz = "";

            String sql1 = "select x.id,(select sum(ztz_jzaztz + ztz_sbtz + ztz_dttz + ztz_qttz)/10000" +
                    "          from zftz_xmzjlydj j " +
                    "         where j.id_zftz_xm = x.id) ztz " +
                    "  from zftz_xm x  where x.id="+m.get("XMID").toString();
            List xlist = baseDao.selectMapsBySQL(sql1);
            if(xlist.size()!=0){
                Map t = (Map)xlist.get(0);
                ztz = t.get("ZTZ")==null?"":t.get("ZTZ").toString();

            }

            m.put("ZTZ",ztz);
        }

        page.setTotal(count.intValue());
        page.setRows(list);

        return page;
    }

    public $Result saveOrUpdateByZcb(FbyxNdzcjh t, String ptgs, HttpSession session){
        YhglYwYhyy userinfo= (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER );
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date date  = new Date();
        try{
            if(t.getId()==null){
                t.setPtgs(Long.parseLong(ptgs));
                t.setBnjhtz(t.getBnjhtz()==null?null:BigDecimal.valueOf(t.getBnjhtz().doubleValue()*10000));
                t.setNc_yyggys(t.getNc_yyggys()==null?null:BigDecimal.valueOf(t.getNc_yyggys().doubleValue()*10000));
                t.setNc_phzy(t.getNc_phzy()==null?null:BigDecimal.valueOf(t.getNc_phzy().doubleValue()*10000));
                t.setNc_phzc(t.getNc_phzc()==null?null:BigDecimal.valueOf(t.getNc_phzc().doubleValue()*10000));
                t.setNc_pppshzb(t.getNc_pppshzb()==null?null:BigDecimal.valueOf(t.getNc_pppshzb().doubleValue()*10000));
                t.setNc_qxfd(t.getNc_qxfd()==null?null:BigDecimal.valueOf(t.getNc_qxfd().doubleValue()*10000));
                t.setNc_qt(t.getNc_qt()==null?null:BigDecimal.valueOf(t.getNc_qt().doubleValue()*10000));
                t.setTz_yyggys(t.getTz_yyggys()==null?null:BigDecimal.valueOf(t.getTz_yyggys().doubleValue()*10000));
                t.setTz_phzy(t.getTz_phzy()==null?null:BigDecimal.valueOf(t.getTz_phzy().doubleValue()*10000));
                t.setTz_phzc(t.getTz_phzc()==null?null:BigDecimal.valueOf(t.getTz_phzc().doubleValue()*10000));
                t.setTz_pppshzb(t.getTz_pppshzb()==null?null:BigDecimal.valueOf(t.getTz_pppshzb().doubleValue()*10000));
                t.setTz_qxfd(t.getTz_qxfd()==null?null:BigDecimal.valueOf(t.getTz_qxfd().doubleValue()*10000));
                t.setTz_qt(t.getTz_qt()==null?null:BigDecimal.valueOf(t.getTz_qt().doubleValue()*10000));

                t.setCzrq(Long.parseLong(sdf.format(date)));
                t.setZfrq(99991231L);
                t.setDjr(userinfo.getYhzh());

                baseDao.save(t);
            }else{

                Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
                calendar.add(Calendar.DATE, -1);    //得到前一天
                String  yestedayDate = sdf.format(calendar.getTime());

                //修改作废日期为当前时间-1天 并插入新数据
                baseDao.executeBySql( " update fbyx_ndzcjh set zfrq="+Long.parseLong(yestedayDate)+"  where id="+t.getId());

                baseDao.executeBySql("insert into FBYX_NDZCJH (id,nd,ptgs,xmid,bnjhtz, nc_yyggys, nc_phzy, nc_phzc, nc_pppshzb, nc_qxfd, nc_qt, tz_yyggys, tz_phzy, tz_phzc, tz_pppshzb, tz_qxfd, tz_qt,czrq, zfrq, djr) " +
                        "values (SEQ_FBYX_NDZCJH.NEXTVAL,"+t.getNd()+","+ptgs+", "+t.getXmid()+","+
                        (t.getBnjhtz()==null?"''":BigDecimal.valueOf(t.getBnjhtz().doubleValue()*10000))+", "+
                        (t.getNc_yyggys()==null?"''":BigDecimal.valueOf(t.getNc_yyggys().doubleValue()*10000))+", "+
                        (t.getNc_phzy()==null?"''":BigDecimal.valueOf(t.getNc_phzy().doubleValue()*10000))+", "+
                        (t.getNc_phzc()==null?"''":BigDecimal.valueOf(t.getNc_phzc().doubleValue()*10000))+", "+
                        (t.getNc_pppshzb()==null?"''":BigDecimal.valueOf(t.getNc_pppshzb().doubleValue()*10000))+", "+
                        (t.getNc_qxfd()==null?"''":BigDecimal.valueOf(t.getNc_qxfd().doubleValue()*10000))+", "+
                        (t.getNc_qt()==null?"''":BigDecimal.valueOf(t.getNc_qt().doubleValue()*10000))+", "+
                        (t.getTz_yyggys()==null?"''":BigDecimal.valueOf(t.getTz_yyggys().doubleValue()*10000))+", "+
                        (t.getTz_phzy()==null?"''":BigDecimal.valueOf(t.getTz_phzy().doubleValue()*10000))+", "+
                        (t.getTz_phzc()==null?"''":BigDecimal.valueOf(t.getTz_phzc().doubleValue()*10000))+", "+
                        (t.getTz_pppshzb()==null?"''":BigDecimal.valueOf(t.getTz_pppshzb().doubleValue()*10000))+", "+
                        (t.getTz_qxfd()==null?"''":BigDecimal.valueOf(t.getTz_qxfd().doubleValue()*10000))+", "+
                        (t.getTz_qt()==null?"''":BigDecimal.valueOf(t.getTz_qt().doubleValue()*10000))+", "+
                        Long.parseLong(sdf.format(date))+", 99991231, '"+userinfo.getYhzh()+"')");

            }

        }catch (Exception e){
            e.printStackTrace();
            return $Result.fail();
        }
        return $Result.success();
    }

    public $Result deleteByZcb(String  id, HttpSession session){
        if(!StringUtil.isblank(id)){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
                calendar.add(Calendar.DATE, -1);    //得到前一天
                String  yestedayDate = sdf.format(calendar.getTime());

                baseDao.executeBySql(" update fbyx_ndzcjh set zfrq="+Long.parseLong(yestedayDate)+"  where id="+id);
            }catch (Exception e){
                e.printStackTrace();
                return $Result.fail();
            }
        }

        return $Result.success();
    }

    public Pages getHzbList(Pages page, String nd,String ptgs, HttpSession session){
        StringBuffer sql = new StringBuffer();
        sql.append("select z.id,z.nd,z.ptgs,z.zwid,g.zwmc," +
                "z.nc_bj/10000 as nc_bj," +
                "z.nc_lx/10000 as nc_lx," +
                "z.tz_bj/10000 as tz_bj," +
                "z.tz_lx/10000 as tz_lx," +
                "(nvl(z.nc_bj,0)+nvl(z.tz_bj,0))/10000 as nd_bj," +
                "(nvl(z.nc_lx,0)+nvl(z.tz_lx,0))/10000 as nd_lx," +
                "z.czrq,z.zfrq " +
                "from fbyx_ndhzb z " +
                "left join fbyx_zwgl g on z.zwid=g.id "+
                "where 1=1 and to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq");
        if(!StringUtil.isblank(nd)){
            sql.append(" and z.nd ="+nd);
        }
        if(!StringUtil.isblank(ptgs)){
            sql.append(" and z.ptgs ="+ptgs);
        }

        sql.append(" order by z.id");
        List list =baseDao.selectMapsBySQL(sql.toString(),null,page.getOffset(),page.getLimit());
        Long count =baseDao.getCountBySQL("select count(*) from ("+sql+") t",null);


        page.setTotal(count.intValue());
        page.setRows(list);

        return page;
    }

    public $Result saveOrUpdateByHzb(FbyxNdhzb t, String ptgs, HttpSession session){
        YhglYwYhyy userinfo= (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER );
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date date  = new Date();
        try{
            if(t.getId()==null){
                t.setPtgs(Long.parseLong(ptgs));
                t.setNc_bj(t.getNc_bj()==null?null:BigDecimal.valueOf(t.getNc_bj().doubleValue()*10000));
                t.setNc_lx(t.getNc_lx()==null?null:BigDecimal.valueOf(t.getNc_lx().doubleValue()*10000));
                t.setTz_bj(t.getTz_bj()==null?null:BigDecimal.valueOf(t.getTz_bj().doubleValue()*10000));
                t.setTz_lx(t.getTz_lx()==null?null:BigDecimal.valueOf(t.getTz_lx().doubleValue()*10000));


                t.setCzrq(Long.parseLong(sdf.format(date)));
                t.setZfrq(99991231L);
                t.setDjr(userinfo.getYhzh());

                baseDao.save(t);
            }else{
                Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
                calendar.add(Calendar.DATE, -1);    //得到前一天
                String  yestedayDate = sdf.format(calendar.getTime());

                //修改作废日期为当前时间-1天 并插入新数据
                baseDao.executeBySql( " update fbyx_ndhzb set zfrq="+Long.parseLong(yestedayDate)+"  where id="+t.getId());

                baseDao.executeBySql("insert into FBYX_NDHZB (id,nd,ptgs,zwid, nc_bj, nc_lx, tz_bj, tz_lx,czrq, zfrq, djr) " +
                        "values (SEQ_FBYX_NDHZB.NEXTVAL,"+t.getNd()+","+ptgs+", "+t.getZwid()+","+
                        (t.getNc_bj()==null?"''":BigDecimal.valueOf(t.getNc_bj().doubleValue()*10000))+", "+
                        (t.getNc_lx()==null?"''":BigDecimal.valueOf(t.getNc_lx().doubleValue()*10000))+", "+
                        (t.getTz_bj()==null?"''":BigDecimal.valueOf(t.getTz_bj().doubleValue()*10000))+", "+
                        (t.getTz_lx()==null?"''":BigDecimal.valueOf(t.getTz_lx().doubleValue()*10000))+", "+
                        Long.parseLong(sdf.format(date))+", 99991231, '"+userinfo.getYhzh()+"')");

            }

        }catch (Exception e){
            e.printStackTrace();
            return $Result.fail();
        }
        return $Result.success();
    }

    public $Result deleteByHzb(String  id, HttpSession session){
        if(!StringUtil.isblank(id)){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
                calendar.add(Calendar.DATE, -1);    //得到前一天
                String  yestedayDate = sdf.format(calendar.getTime());

                baseDao.executeBySql(" update fbyx_ndhzb set zfrq="+Long.parseLong(yestedayDate)+"  where id="+id);
            }catch (Exception e){
                e.printStackTrace();
                return $Result.fail();
            }
        }

        return $Result.success();
    }

    public $Result saveOrUpdateBySrbDz(FbyxNdsrjhDz t, String srid, HttpSession session){
        YhglYwYhyy userinfo= (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER );
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date date  = new Date();
        try{
            if(t.getId()==null){
                t.setNdsrbid(Long.parseLong(srid));
                t.setJhdzje(t.getJhdzje()==null?null:BigDecimal.valueOf(t.getJhdzje().doubleValue()*10000));

                t.setCzrq(Long.parseLong(sdf.format(date)));
                baseDao.save(t);
            }else{
                t.setNdsrbid(Long.parseLong(srid));
                t.setJhdzje(t.getJhdzje()==null?null:BigDecimal.valueOf(t.getJhdzje().doubleValue()*10000));

                t.setCzrq(Long.parseLong(sdf.format(date)));
                baseDao.update(t);

            }

        }catch (Exception e){
            e.printStackTrace();
            return $Result.fail();
        }
        return $Result.success();
    }

    public void insertExcelNc(List<String[]>  list,String ptgs, HttpSession session){
        YhglYwYhyy userinfo= (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER );
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date date = new Date();
        for(int i=2;i<list.size();i++){
            Object[] ss = list.get(i);
            baseDao.executeBySql("insert into temp_fbyx_ndsrb (XH,JHND, ZYBM, ZYMC, ZYLXBM, ZYZLMC, QX, JHCRRQ, HJ, NC_JE, TZ_JE, JH_SJ,JH_JE) " +
                    "values ('"+ss[0].toString()+"'"+
                    ",'"+ss[1].toString()+ "','"+ss[2].toString()+ "','"+ss[3].toString()+"'"+
                    ",'"+ss[4].toString()+ "','"+ss[5].toString()+ "','"+ss[6].toString()+"'"+
                    ",'"+ss[7].toString()+ "','"+ss[8].toString()+ "','"+ss[9].toString()+"'"+
                    ",'"+ss[10].toString()+ "','"+ss[11].toString()+ "','"+ss[12].toString()+"')");
        }

        List tlist = baseDao.selectMapsBySQL("select distinct jhnd,zybm,qx,jhcrrq,hj,nc_je,tz_je from temp_fbyx_ndsrb ");

        for (int i = 0; i < tlist.size(); i++) {
            Map t = (Map)tlist.get(i);
            if(t.get("ZYBM")!=null){
                if(Long.parseLong(t.get("JHND").toString())>=Long.parseLong(sdf.format(date).substring(0,4))){//历史年份不可导
                    List<FbyxZygl> ylist  = baseDao.selectByHql(" from FbyxZygl where zybm='"+t.get("ZYBM").toString()+"' and zt='1'");
                    String xzqhbm = "";
                    if(t.get("QX")!=null){
                        Map x = new HashMap();
                        List xlist = baseDao.selectMapsBySQL("select *  from v_zftz_xzqh where mc = '"+t.get("QX").toString()+"'");
                        if(xlist!=null&&xlist.size()>0){
                            x =(Map) xlist.get(0);
                            xzqhbm = x.get("BM").toString();
                        }
                    }

                    if(ylist.size()!=0){
                        FbyxZygl zy = ylist.get(0);
                        StringBuffer sql = new StringBuffer();
                        sql.append("select z.id,zymc " +
                                "from fbyx_ndsrb z " +
                                "where to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq and z.ptgs='"+ptgs+"' and z.zymcid='"+zy.getId()+"'");
                        List zlist = baseDao.selectMapsBySQL(sql.toString());
                        //大于0修改
                        if(zlist.size()>0){
                            StringBuffer sql3 = new StringBuffer();
                            sql3.append("select z.id,zymc " +
                                    "from fbyx_ndsrb z " +
                                    "where to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq and z.ptgs='"+ptgs+"' and z.zymcid='"+zy.getId()+"'");
                            List olist = baseDao.selectMapsBySQL(sql3.toString());
                            Map o = (Map)olist.get(0);
                            String oid = o.get("ID").toString();
                            Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
                            calendar.add(Calendar.DATE, -1);    //得到前一天
                            String  yestedayDate = sdf.format(calendar.getTime());

                            //修改作废日期为当前时间-1天 并插入新数据
                            baseDao.executeBySql( " update fbyx_ndsrb set zfrq="+Long.parseLong(yestedayDate)+"  where id="+oid);

                            //删除明细表垃圾数据
                            baseDao.executeBySql( " delete  from fbyx_ndsrjh_dz where id_fbyx_ndsrb="+oid);
                        }


                        //旧数据作废 新数据插入
                        if(Long.parseLong(t.get("JHND").toString())>=Long.parseLong(sdf.format(date).substring(0,4))){//历史年份不导入

                            baseDao.executeBySql("insert into FBYX_NDSRB (id,ptgs, zmlx, zymcid, zymc, ssxzqh, jhcrrq, nc_jhje, tz_jhje, czrq, zfrq, djr) " +
                                    "values (SEQ_FBYX_NDSRB.NEXTVAL,"+ptgs+", "+zy.getZmlx()+","+zy.getId()+" , '"+zy.getZymc()+"', '"+xzqhbm+"', "+t.get("JHCRRQ").toString()+", "+
                                    (t.get("NC_JE")==null?"''": BigDecimal.valueOf(Double.parseDouble(t.get("NC_JE").toString())*10000))+", "+
                                    "''"+
                                    ", "+Long.parseLong(sdf.format(date))+", 99991231, '"+userinfo.getYhzh()+"')");

                            List nlist = baseDao.selectMapsBySQL(sql.toString());
                            String srbid = "";
                            if(nlist.size()!=0){
                                Map n = (Map)nlist.get(0);
                                srbid = n.get("ID").toString();
                            }

                            List mlist = baseDao.selectMapsBySQL("select jh_sj,jh_je from temp_fbyx_ndsrb where zybm='"+t.get("ZYBM").toString()+"'");
                            if(mlist.size()>0){
                                for (int j = 0; j <mlist.size() ; j++) {
                                    Map mx = (Map)mlist.get(j);
                                    baseDao.executeBySql("insert into fbyx_ndsrjh_dz (id,id_fbyx_ndsrb, jhdz_rq, jhdz_je, czrq) " +
                                            "values (SEQ_FBYX_NDSRJH_DZ.NEXTVAL,'"+srbid+"', "+
                                            (mx.get("JH_SJ")==null?"''": mx.get("JH_SJ").toString())+", "+
                                            (mx.get("JH_JE")==null?"''": BigDecimal.valueOf(Double.parseDouble(mx.get("JH_JE").toString())*10000))+", "+
                                            Long.parseLong(sdf.format(date))+")");
                                }
                            }
                        }

                    }
                }
            }





        }

        //清除临时表
        baseDao.executeBySql("delete from temp_fbyx_ndsrb");

    }

    public void insertExcelTz(List<String[]>  list,String ptgs, HttpSession session){
        YhglYwYhyy userinfo= (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER );
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date date = new Date();
        for(int i=2;i<list.size();i++){
            Object[] ss = list.get(i);
            baseDao.executeBySql("insert into temp_fbyx_ndsrb (XH,JHND, ZYBM, ZYMC, ZYLXBM, ZYZLMC, QX, JHCRRQ, HJ, NC_JE, TZ_JE, JH_SJ,JH_JE) " +
                    "values ('"+ss[0].toString()+"'"+
                    ",'"+ss[1].toString()+ "','"+ss[2].toString()+ "','"+ss[3].toString()+"'"+
                    ",'"+ss[4].toString()+ "','"+ss[5].toString()+ "','"+ss[6].toString()+"'"+
                    ",'"+ss[7].toString()+ "','"+ss[8].toString()+ "','"+ss[9].toString()+"'"+
                    ",'"+ss[10].toString()+ "','"+ss[11].toString()+ "','"+ss[12].toString()+"')");
        }

        List tlist = baseDao.selectMapsBySQL("select distinct jhnd,zybm,qx,jhcrrq,hj,nc_je,tz_je from temp_fbyx_ndsrb ");

        for (int i = 0; i < tlist.size(); i++) {
            Map t = (Map)tlist.get(i);

            if(t.get("ZYBM")!=null){
                if(Long.parseLong(t.get("JHND").toString())>=Long.parseLong(sdf.format(date).substring(0,4))){//历史年份不可导
                    List<FbyxZygl> ylist  = baseDao.selectByHql(" from FbyxZygl where zybm='"+t.get("ZYBM").toString()+"' and zt='1'");

                    if(ylist.size()!=0){
                        FbyxZygl zy = ylist.get(0);
                        StringBuffer sql = new StringBuffer();
                        sql.append("select z.id,z.zymc," +
                                " case when z.nc_jhje is null then 0 else z.nc_jhje/10000 end nc_jhje " +
                                "from fbyx_ndsrb z " +
                                "where to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq and z.ptgs='"+ptgs+"' and z.zymcid='"+zy.getId()+"'");
                        List zlist = baseDao.selectMapsBySQL(sql.toString());
                        //大于0修改
                        if(zlist.size()>0){
                            Map o = (Map)zlist.get(0);
                            String nc_je = o.get("NC_JHJE").toString();
                            String tz_je = (t.get("TZ_JE")==null?"": t.get("TZ_JE").toString());


                            String tzjhje = "";
                            if(!"".equals(tz_je)&&"0".equals(nc_je)){
                                tzjhje = String.valueOf(BigDecimal.valueOf((Double.parseDouble(tz_je)-0)*10000));
                            }else if(!"".equals(tz_je)&&!"0".equals(nc_je)){
                                tzjhje = String.valueOf(BigDecimal.valueOf((Double.parseDouble(tz_je)-Double.parseDouble(nc_je))*10000));
                            }

                            baseDao.executeBySql( " update fbyx_ndsrb set tz_jhje='"+ tzjhje+"'"
                                    +"  where id="+o.get("ID").toString());
                        }

                    }
                }
            }


        }

        //清除临时表
        baseDao.executeBySql("delete from temp_fbyx_ndsrb");

    }

    public void insertExcel2(List<String[]>  list,String ptgs, HttpSession session){
        YhglYwYhyy userinfo= (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER );
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date date = new Date();
        for(int i=2;i<list.size();i++){
            Object[] ss = list.get(i);

            if(Long.parseLong(ss[1].toString())>=Long.parseLong(sdf.format(date).substring(0,4))) {//历史年份不导入
                List<ZftzXm> wlist  = baseDao.selectByHql(" from ZftzXm where id='"+ss[2].toString()+"' and zt='1'");

                if(wlist.size()!=0) {
                    ZftzXm zm = wlist.get(0);
                    StringBuffer sql = new StringBuffer();
                    sql.append("select z.id " +
                            "from fbyx_ndzcjh z " +
                            "where to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq and z.ptgs='"+ptgs+"' and z.nd='"+ss[1].toString()+"' and z.xmid='" + zm.getId() + "'");
                    List zlist = baseDao.selectMapsBySQL(sql.toString());
                    //查询为空为第一次新增
                    if (zlist.size() == 0) {
                        //System.out.println(Arrays.asList(ss));

                        FbyxNdzcjh zcb = new FbyxNdzcjh();
                        zcb.setNd(Long.parseLong(ss[1].toString()));
                        zcb.setPtgs(Long.parseLong(ptgs));
                        zcb.setXmid(zm.getId());

                        zcb.setBnjhtz("".equals(ss[4].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[4].toString())*10000));
                        zcb.setNc_yyggys("".equals(ss[7].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[7].toString())*10000));
                        zcb.setNc_pppshzb("".equals(ss[8].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[8].toString())*10000));
                        zcb.setNc_phzy("".equals(ss[9].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[9].toString())*10000));
                        zcb.setNc_qxfd("".equals(ss[10].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[10].toString())*10000));
                        zcb.setNc_phzc("".equals(ss[11].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[11].toString())*10000));
                        zcb.setNc_qt("".equals(ss[12].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[12].toString())*10000));
                        zcb.setTz_yyggys("".equals(ss[14].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[14].toString())*10000));
                        zcb.setTz_pppshzb("".equals(ss[15].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[15].toString())*10000));
                        zcb.setTz_phzy("".equals(ss[16].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[16].toString())*10000));
                        zcb.setTz_qxfd("".equals(ss[17].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[17].toString())*10000));
                        zcb.setTz_phzc("".equals(ss[18].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[18].toString())*10000));
                        zcb.setTz_qt("".equals(ss[19].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[19].toString())*10000));

                        zcb.setCzrq(Long.parseLong(sdf.format(date)));
                        zcb.setZfrq(99991231L);
                        zcb.setDjr(userinfo.getYhzh());
                        baseDao.save(zcb);

                    }else{
                        StringBuffer sql3 = new StringBuffer();
                        sql3.append("select z.id " +
                                "from fbyx_ndzcjh z " +
                                "where to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq and z.ptgs='"+ptgs+"'  and z.nd='"+ss[1].toString()+"' and z.xmid='"+zm.getId()+"'");
                        List olist = baseDao.selectMapsBySQL(sql3.toString());
                        Map o = (Map)olist.get(0);
                        String oid = o.get("ID").toString();

                        Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
                        calendar.add(Calendar.DATE, -1);    //得到前一天
                        String  yestedayDate = sdf.format(calendar.getTime());

                        //修改作废日期为当前时间-1天 并插入新数据
                        baseDao.executeBySql( " update fbyx_ndzcjh set zfrq="+Long.parseLong(yestedayDate)+"  where id="+oid);

                        baseDao.executeBySql("insert into FBYX_NDZCJH (id,nd,ptgs,xmid,bnjhtz, nc_yyggys, nc_pppshzb, nc_phzy, nc_qxfd, nc_phzc, nc_qt, tz_yyggys, tz_pppshzb, tz_phzy, tz_qxfd, tz_phzc, tz_qt,czrq, zfrq, djr) " +
                                "values (SEQ_FBYX_NDZCJH.NEXTVAL,"+ss[1].toString()+","+ptgs+", "+zm.getId()+","+
                                ("".equals(ss[4].toString())?"''": BigDecimal.valueOf(Double.parseDouble(ss[4].toString())*10000))+", "+
                                ("".equals(ss[7].toString())?"''": BigDecimal.valueOf(Double.parseDouble(ss[7].toString())*10000))+", "+
                                ("".equals(ss[8].toString())?"''": BigDecimal.valueOf(Double.parseDouble(ss[8].toString())*10000))+", "+
                                ("".equals(ss[9].toString())?"''": BigDecimal.valueOf(Double.parseDouble(ss[9].toString())*10000))+", "+
                                ("".equals(ss[10].toString())?"''": BigDecimal.valueOf(Double.parseDouble(ss[10].toString())*10000))+", "+
                                ("".equals(ss[11].toString())?"''": BigDecimal.valueOf(Double.parseDouble(ss[11].toString())*10000))+", "+
                                ("".equals(ss[12].toString())?"''": BigDecimal.valueOf(Double.parseDouble(ss[12].toString())*10000))+", "+
                                ("".equals(ss[14].toString())?"''": BigDecimal.valueOf(Double.parseDouble(ss[14].toString())*10000))+", "+
                                ("".equals(ss[15].toString())?"''": BigDecimal.valueOf(Double.parseDouble(ss[15].toString())*10000))+", "+
                                ("".equals(ss[16].toString())?"''": BigDecimal.valueOf(Double.parseDouble(ss[16].toString())*10000))+", "+
                                ("".equals(ss[17].toString())?"''": BigDecimal.valueOf(Double.parseDouble(ss[17].toString())*10000))+", "+
                                ("".equals(ss[18].toString())?"''": BigDecimal.valueOf(Double.parseDouble(ss[18].toString())*10000))+", "+
                                ("".equals(ss[19].toString())?"''": BigDecimal.valueOf(Double.parseDouble(ss[19].toString())*10000))+", "+
                                Long.parseLong(sdf.format(date))+", 99991231, '"+userinfo.getYhzh()+"')");
                    }
                }
            }

        }
    }

    public void insertExcel3(List<String[]>  list,String ptgs, HttpSession session){
        YhglYwYhyy userinfo= (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER );
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date date = new Date();
        for(int i=2;i<list.size();i++){
            Object[] ss = list.get(i);

            if(Long.parseLong(ss[1].toString())>=Long.parseLong(sdf.format(date).substring(0,4))) {//历史年份不导入
                List<FbyxZwgl> wlist  = baseDao.selectByHql(" from FbyxZwgl where zwbm='"+ss[2].toString()+"' and zt='1'");

                if(wlist.size()!=0) {
                    FbyxZwgl zw = wlist.get(0);
                    StringBuffer sql = new StringBuffer();
                    sql.append("select z.id " +
                            "from fbyx_ndhzb z " +
                            "where to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq and z.ptgs='"+ptgs+"'and z.nd='"+ss[1].toString()+"' and z.zwid='" + zw.getId() + "'");
                    List zlist = baseDao.selectMapsBySQL(sql.toString());
                    //查询为空为第一次新增
                    if (zlist.size() == 0) {
                        //System.out.println(Arrays.asList(ss));
                        FbyxNdhzb hzb = new FbyxNdhzb();
                        hzb.setNd(Long.parseLong(ss[1].toString()));
                        hzb.setPtgs(Long.parseLong(ptgs));
                        hzb.setZwid(zw.getId());

                        hzb.setNc_bj("".equals(ss[5].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[5].toString())*10000));
                        hzb.setNc_lx("".equals(ss[6].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[6].toString())*10000));
                        hzb.setTz_bj("".equals(ss[8].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[8].toString())*10000));
                        hzb.setTz_lx("".equals(ss[9].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[9].toString())*10000));

                        hzb.setCzrq(Long.parseLong(sdf.format(date)));
                        hzb.setZfrq(99991231L);
                        hzb.setDjr(userinfo.getYhzh());
                        baseDao.save(hzb);


                    }else{
                        StringBuffer sql3 = new StringBuffer();
                        sql3.append("select z.id " +
                                "from fbyx_ndhzb z " +
                                "where to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq and z.ptgs='"+ptgs+"'and z.nd='"+ss[1].toString()+"' and z.zwid='" + zw.getId() + "'");
                        List olist = baseDao.selectMapsBySQL(sql3.toString());
                        Map o = (Map)olist.get(0);
                        String oid = o.get("ID").toString();

                        Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
                        calendar.add(Calendar.DATE, -1);    //得到前一天
                        String  yestedayDate = sdf.format(calendar.getTime());

                        //修改作废日期为当前时间-1天 并插入新数据
                        baseDao.executeBySql( " update fbyx_ndhzb set zfrq="+Long.parseLong(yestedayDate)+"  where id="+oid);

                        baseDao.executeBySql("insert into FBYX_NDHZB (id,nd,ptgs,zwid, nc_bj, nc_lx, tz_bj, tz_lx,czrq, zfrq, djr) " +
                                "values (SEQ_FBYX_NDHZB.NEXTVAL,"+ss[1].toString()+","+ptgs+", "+zw.getId()+","+
                                ("".equals(ss[5].toString())?"''": BigDecimal.valueOf(Double.parseDouble(ss[5].toString())*10000))+", "+
                                ("".equals(ss[6].toString())?"''": BigDecimal.valueOf(Double.parseDouble(ss[6].toString())*10000))+", "+
                                ("".equals(ss[8].toString())?"''": BigDecimal.valueOf(Double.parseDouble(ss[8].toString())*10000))+", "+
                                ("".equals(ss[9].toString())?"''": BigDecimal.valueOf(Double.parseDouble(ss[9].toString())*10000))+", "+
                                Long.parseLong(sdf.format(date))+", 99991231, '"+userinfo.getYhzh()+"')");

                    }

                }
            }

        }
    }


    @Override
    public String[][] exportexcel1(String nd,String ptgs){
        StringBuffer sql = new StringBuffer();
        sql.append("select z.id,z.ptgs,z.zmlx,x.mc as zmlxmc,z.zymcid,z.zymc as zymc,g.zybm,z.ssxzqh,r.mc as ssxzqhmc, z.jhcrrq," +
                "z.nc_jhje/10000 as nc_jhje," +
                "case when z.tz_jhje is null then 0 else (nvl(z.nc_jhje,0)+nvl(z.tz_jhje,0))/10000 end as tz_jhje,"+
                "z.czrq,z.zfrq,d.jhdz_rq,d.jhdz_je/10000 as jhdz_je  " +
                "from fbyx_ndsrb z " +
                "left join fbyx_ndsrjh_dz d on z.id=d.id_fbyx_ndsrb  " +
                "left join fbyx_xxb x on z.zmlx=x.bm and x.lx='srgh' " +
                "left join fbyx_zygl g on z.zymcid=g.id  " +
                "left join v_zftz_xzqh r on z.ssxzqh=r.bm "+
                "where 1=1 and to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq ");
        if(!StringUtil.isblank(nd)){
            sql.append(" and substr(z.jhcrrq,0,4) ="+nd);
        }
        if(!StringUtil.isblank(ptgs)){
            sql.append(" and z.ptgs ="+ptgs);
        }

        sql.append(" order by z.id");
        List list =baseDao.selectMapsBySQL(sql.toString());


        String[][] a1 = new String[list.size()+2][13];
        a1[0][0] = "序号";
        a1[0][1] = "计划年度";
        a1[0][2] = "资源编码";
        a1[0][3] = "资源名称";
        a1[0][4] = "资源类型编码";
        a1[0][5] = "资源种类名称";
        a1[0][6] = "区县";
        a1[0][7] = "计划出让/获得";
        a1[0][8] = "计划金额";
        a1[0][11] = "计划到账";
        a1[1][8] = "合计";
        a1[1][9] = "年初";
        a1[1][10] = "调整";
        a1[1][11] = "时间";
        a1[1][12] = "金额";


        for (int j=0;j<list.size();j++) {
            Map map = (Map) list.get(j);

            a1[j + 2][0] = String.valueOf(j+1);
            a1[j + 2][1] = (map.get("JHCRRQ")==null?"":map.get("JHCRRQ").toString().substring(0,4));
            a1[j + 2][2] = (map.get("ZYBM")==null?"":map.get("ZYBM").toString());
            a1[j + 2][3] = (map.get("ZYMC")==null?"":map.get("ZYMC").toString());
            a1[j + 2][4] = (map.get("ZMLX")==null?"":map.get("ZMLX").toString());
            a1[j + 2][5] = (map.get("ZMLXMC")==null?"":map.get("ZMLXMC").toString());
            a1[j + 2][6] = (map.get("SSXZQHMC")==null?"":map.get("SSXZQHMC").toString());
            a1[j + 2][7] = (map.get("JHCRRQ")==null?"":map.get("JHCRRQ").toString());
            a1[j + 2][8] = String.valueOf(map.get("NC_JHJE")==null?0:Long.parseLong(map.get("NC_JHJE").toString())+(map.get("TZ_JHJE")==null?0:Long.parseLong(map.get("TZ_JHJE").toString())));
            a1[j + 2][9] = (map.get("NC_JHJE")==null?"":map.get("NC_JHJE").toString());
            a1[j + 2][10] = (map.get("TZ_JHJE")==null?"":map.get("TZ_JHJE").toString());
            a1[j + 2][11] = (map.get("JHDZ_RQ")==null?"":map.get("JHDZ_RQ").toString());
            a1[j + 2][12] = (map.get("JHDZ_JE")==null?"":map.get("JHDZ_JE").toString());
        }

        return a1;
    }

    @Override
    public String[][] exportexcel2(String nd,String ptgs){
        StringBuffer sql = new StringBuffer();
        sql.append("select z.id,z.nd,z.ptgs,z.xmid,x.xmmc," +
                "z.bnjhtz/10000 as bnjhtz," +
                "z.nc_yyggys/10000 as nc_yyggys," +
                "z.nc_phzy/10000 as nc_phzy," +
                "z.nc_phzc/10000 as nc_phzc," +
                "z.nc_pppshzb/10000 as nc_pppshzb," +
                "z.nc_qxfd/10000 as nc_qxfd," +
                "z.nc_qt/10000 as nc_qt," +
                "z.tz_yyggys/10000 as tz_yyggys," +
                "z.tz_phzy/10000 as tz_phzy," +
                "z.tz_phzc/10000 as tz_phzc," +
                "z.tz_pppshzb/10000 as tz_pppshzb," +
                "z.tz_qxfd/10000 as tz_qxfd," +
                "z.tz_qt/10000 as tz_qt," +
                "(nvl(z.nc_yyggys,0)+nvl(z.tz_yyggys,0))/10000 as nd_yyggys," +
                "(nvl(z.nc_phzy,0)+nvl(z.tz_phzy,0))/10000 as nd_phzy," +
                "(nvl(z.nc_phzc,0)+nvl(z.tz_phzc,0))/10000 as nd_phzc," +
                "(nvl(z.nc_pppshzb,0)+nvl(z.tz_pppshzb,0))/10000 as nd_pppshzb," +
                "(nvl(z.nc_qxfd,0)+nvl(z.tz_qxfd,0))/10000 as nd_qxfd," +
                "(nvl(z.nc_qt,0)+nvl(z.tz_qt,0))/10000 as nd_qt," +
                "z.czrq,z.zfrq " +
                "from fbyx_ndzcjh z " +
                "left join zftz_xm x on z.xmid=x.id " +
                "where 1=1 and to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq");
        if(!StringUtil.isblank(nd)){
            sql.append(" and z.nd ="+nd);
        }
        if(!StringUtil.isblank(ptgs)){
            sql.append(" and z.ptgs ="+ptgs);
        }

        sql.append(" order by z.id");
        List list =baseDao.selectMapsBySQL(sql.toString());


        String[][] a1 = new String[list.size()+2][27];
        a1[0][0] = "序号";
        a1[0][1] = "计划年度";
        a1[0][2] = "项目编码";
        a1[0][3] = "项目名称";
        a1[0][4] = "本年计划投资额";
        a1[0][5] = "合计";
        a1[0][6] = "年初资金计划";
        a1[0][13] = "调整资金计划";
        a1[0][20] = "年度资金计划";
        a1[1][6] = "小计";
        a1[1][7] = "年初一般公共预算";
        a1[1][8] = "PPP社会资本";
        a1[1][9] = "盘活资源/政府性基金";
        a1[1][10] = "区域分担";
        a1[1][11] = "盘活资产";
        a1[1][12] = "调其他(自筹)整";
        a1[1][13] = "小计";
        a1[1][14] = "年初一般公共预算";
        a1[1][15] = "PPP社会资本";
        a1[1][16] = "盘活资源/政府性基金";
        a1[1][17] = "区域分担";
        a1[1][18] = "盘活资产";
        a1[1][19] = "调其他(自筹)整";
        a1[1][20] = "小计";
        a1[1][21] = "年初一般公共预算";
        a1[1][22] = "PPP社会资本";
        a1[1][23] = "盘活资源/政府性基金";
        a1[1][24] = "区域分担";
        a1[1][25] = "盘活资产";
        a1[1][26] = "调其他(自筹)整";

        for (int j=0;j<list.size();j++) {
            Map map = (Map) list.get(j);

            a1[j + 2][0] = String.valueOf(j+1);
            a1[j + 2][1] = (map.get("ND")==null?"":map.get("ND").toString());
            a1[j + 2][2] = (map.get("XMID")==null?"":map.get("XMID").toString());
            a1[j + 2][3] = (map.get("XMMC")==null?"":map.get("XMMC").toString());
            a1[j + 2][4] = (map.get("BNJHTZ")==null?"":map.get("BNJHTZ").toString());


            a1[j + 2][6] = String.valueOf(
                    (map.get("NC_YYGGYS")==null?0:Long.parseLong(map.get("NC_YYGGYS").toString()))+
                    (map.get("NC_PPPSHZB")==null?0:Long.parseLong(map.get("NC_PPPSHZB").toString()))+
                    (map.get("NC_PHZY")==null?0:Long.parseLong(map.get("NC_PHZY").toString()))+
                    (map.get("NC_QXFD")==null?0:Long.parseLong(map.get("NC_QXFD").toString()))+
                    (map.get("NC_PHZC")==null?0:Long.parseLong(map.get("NC_PHZC").toString()))+
                    (map.get("NC_QT")==null?0:Long.parseLong(map.get("NC_QT").toString()))
                    );
            a1[j + 2][7] = (map.get("NC_YYGGYS")==null?"":map.get("NC_YYGGYS").toString());
            a1[j + 2][8] = (map.get("NC_PPPSHZB")==null?"":map.get("NC_PPPSHZB").toString());
            a1[j + 2][9] = (map.get("NC_PHZY")==null?"":map.get("NC_PHZY").toString());
            a1[j + 2][10] = (map.get("NC_QXFD")==null?"":map.get("NC_QXFD").toString());
            a1[j + 2][11] = (map.get("NC_PHZC")==null?"":map.get("NC_PHZC").toString());
            a1[j + 2][12] = (map.get("NC_QT")==null?"":map.get("NC_QT").toString());

            a1[j + 2][13] = String.valueOf(
                    (map.get("TZ_YYGGYS")==null?0:Long.parseLong(map.get("TZ_YYGGYS").toString()))+
                    (map.get("TZ_PPPSHZB")==null?0:Long.parseLong(map.get("TZ_PPPSHZB").toString()))+
                    (map.get("TZ_PHZY")==null?0:Long.parseLong(map.get("TZ_PHZY").toString()))+
                    (map.get("TZ_QXFD")==null?0:Long.parseLong(map.get("TZ_QXFD").toString()))+
                    (map.get("TZ_PHZC")==null?0:Long.parseLong(map.get("TZ_PHZC").toString()))+
                    (map.get("TZ_QT")==null?0:Long.parseLong(map.get("TZ_QT").toString()))
                    );
            a1[j + 2][14] = (map.get("TZ_YYGGYS")==null?"":map.get("TZ_YYGGYS").toString());
            a1[j + 2][15] = (map.get("TZ_PPPSHZB")==null?"":map.get("TZ_PPPSHZB").toString());
            a1[j + 2][16] = (map.get("TZ_PHZY")==null?"":map.get("TZ_PHZY").toString());
            a1[j + 2][17] = (map.get("TZ_QXFD")==null?"":map.get("TZ_QXFD").toString());
            a1[j + 2][18] = (map.get("TZ_PHZC")==null?"":map.get("TZ_PHZC").toString());
            a1[j + 2][19] = (map.get("TZ_QT")==null?"":map.get("TZ_QT").toString());

            a1[j + 2][20] = String.valueOf(Long.parseLong(a1[j + 2][6])+Long.parseLong(a1[j + 2][13]));
            a1[j + 2][21] = (map.get("ND_YYGGYS")==null?"":map.get("ND_YYGGYS").toString());
            a1[j + 2][22] = (map.get("ND_PPPSHZB")==null?"":map.get("ND_PPPSHZB").toString());
            a1[j + 2][23] = (map.get("ND_PHZY")==null?"":map.get("ND_PHZY").toString());
            a1[j + 2][24] = (map.get("ND_QXFD")==null?"":map.get("ND_QXFD").toString());
            a1[j + 2][25] = (map.get("ND_PHZC")==null?"":map.get("ND_PHZC").toString());
            a1[j + 2][26] = (map.get("ND_QT")==null?"":map.get("ND_QT").toString());

            a1[j + 2][5] = String.valueOf(Long.parseLong(a1[j + 2][6])+Long.parseLong(a1[j + 2][13])+Long.parseLong(a1[j + 2][20]));
        }

        return a1;
    }

    @Override
    public String[][] exportexcel3(String nd,String ptgs){
        StringBuffer sql = new StringBuffer();
        sql.append("select z.id,z.nd,z.ptgs,z.zwid,x.zwbm,x.zwmc," +
                "z.nc_bj/10000 as nc_bj," +
                "z.nc_lx/10000 as nc_lx," +
                "z.tz_bj/10000 as tz_bj," +
                "z.tz_lx/10000 as tz_lx," +
                "(nvl(z.nc_bj,0)+nvl(z.nc_lx,0))/10000 as nd_bj," +
                "(nvl(z.tz_bj,0)+nvl(z.tz_lx,0))/10000 as nd_lx," +
                "z.czrq,z.zfrq " +
                "from fbyx_ndhzb z " +
                "left join fbyx_zwgl x on z.zwid=x.id " +
                "where 1=1 and to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq");
        if(!StringUtil.isblank(nd)){
            sql.append(" and z.nd ="+nd);
        }
        if(!StringUtil.isblank(ptgs)){
            sql.append(" and z.ptgs ="+ptgs);
        }

        sql.append(" order by z.id");
        List list =baseDao.selectMapsBySQL(sql.toString());


        String[][] a1 = new String[list.size()+2][13];
        a1[0][0] = "序号";
        a1[0][1] = "计划年度";
        a1[0][2] = "债务编码";
        a1[0][3] = "债务名称";
        a1[0][4] = "年初计划";
        a1[0][7] = "调整计划";
        a1[0][10] = "年度计划";
        a1[1][4] = "小计";
        a1[1][5] = "本金";
        a1[1][6] = "利息";
        a1[1][7] = "小计";
        a1[1][8] = "本金";
        a1[1][9] = "利息";
        a1[1][10] = "小计";
        a1[1][11] = "本金";
        a1[1][12] = "利息";


        for (int j=0;j<list.size();j++) {
            Map map = (Map) list.get(j);

            a1[j + 2][0] = String.valueOf(j+1);
            a1[j + 2][1] = (map.get("ND")==null?"":map.get("ND").toString());
            a1[j + 2][2] = (map.get("ZWBM")==null?"":map.get("ZWBM").toString());
            a1[j + 2][3] = (map.get("ZWMC")==null?"":map.get("ZWMC").toString());

            a1[j + 2][4] = String.valueOf(
                    (map.get("NC_BJ")==null?0:Long.parseLong(map.get("NC_BJ").toString()))+
                    (map.get("NC_LX")==null?0:Long.parseLong(map.get("NC_LX").toString()))
            );
            a1[j + 2][5] = (map.get("NC_BJ")==null?"":map.get("NC_BJ").toString());
            a1[j + 2][6] = (map.get("NC_LX")==null?"":map.get("NC_LX").toString());

            a1[j + 2][7] = String.valueOf(
                    (map.get("TZ_BJ")==null?0:Long.parseLong(map.get("TZ_BJ").toString()))+
                    (map.get("TZ_LX")==null?0:Long.parseLong(map.get("TZ_LX").toString()))
            );
            a1[j + 2][8] = (map.get("TZ_BJ")==null?"":map.get("TZ_BJ").toString());
            a1[j + 2][9] = (map.get("TZ_LX")==null?"":map.get("TZ_LX").toString());

            a1[j + 2][10] = String.valueOf(
                    (map.get("ND_BJ")==null?0:Long.parseLong(map.get("ND_BJ").toString()))+
                    (map.get("ND_LX")==null?0:Long.parseLong(map.get("ND_LX").toString()))
            );
            a1[j + 2][11] = (map.get("ND_BJ")==null?"":map.get("ND_BJ").toString());
            a1[j + 2][12] = (map.get("ND_LX")==null?"":map.get("ND_LX").toString());

        }

        return a1;
    }
}
