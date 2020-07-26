package com.datanew.service.impl;

import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.*;
import com.datanew.service.ZwzxService;
import com.datanew.util.StaticData;
import com.datanew.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wuwei
 * @create 2019/6/5 9:59
 * @desc
 **/
@Service("zwzxService")
public class ZwzxServiceImpl implements ZwzxService {

    @Resource
    private BaseDao baseDao;

    public Pages getZwzxList(Pages page, String nd, String ptgs,String zwmc, HttpSession session){
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
                " where 1=1 and to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq");
        if(!StringUtil.isblank(nd)){
            sql.append(" and z.nd ="+nd);
        }
        if(!StringUtil.isblank(ptgs)){
            sql.append(" and z.ptgs ="+ptgs);
        }
        if(!StringUtil.isblank(zwmc)){
            sql.append(" and g.zwmc like '%"+zwmc+"%'");
        }

        sql.append(" order by z.id");
        List list =baseDao.selectMapsBySQL(sql.toString(),null,page.getOffset(),page.getLimit());
        Long count =baseDao.getCountBySQL("select count(*) from ("+sql+") t",null);


        page.setTotal(count.intValue());
        page.setRows(list);

        return page;
    }

    public Pages getHzbmxList(Pages page, String hzid, HttpSession session){
        if(!"undefined".equals(hzid)){
            StringBuffer sql = new StringBuffer();
            sql.append("select z.id,z.id_fbyx_ndhzb," +
                    "z.sjhz_ny ,"+
                    "case when z.sjhz_bj is null then 0 else z.sjhz_bj/10000 end as sjhz_bj,"+
                    "case when z.sjhz_lx is null then 0 else z.sjhz_lx/10000 end as sjhz_lx,"+
                    "case when z.hzzjly_clzcph is null then 0 else z.hzzjly_clzcph/10000 end as hzzjly_clzcph,"+
                    "case when z.hzzjly_qtzcph is null then 0 else z.hzzjly_qtzcph/10000 end as hzzjly_qtzcph,"+
                    "case when z.hzzjly_dfzqzh is null then 0 else z.hzzjly_dfzqzh/10000 end as hzzjly_dfzqzh,"+
                    "case when z.hzzjly_shhrz is null then 0 else z.hzzjly_shhrz/10000 end as hzzjly_shhrz,"+
                    "z.bz,z.djr,z.djrq " +
                    "from fbyx_zwzxgl z where 1=1 ");
            if(!StringUtil.isblank(hzid)){
                sql.append(" and id_fbyx_ndhzb ="+hzid);
            }

            sql.append(" order by z.id");
            List list =baseDao.selectMapsBySQL(sql.toString(),null,page.getOffset(),page.getLimit());
            Long count =baseDao.getCountBySQL("select count(*) from ("+sql+") t",null);


            page.setTotal(count.intValue());
            page.setRows(list);
        }


        return page;
    }

    public $Result saveOrUpdateByZwzx(FbyxZwzxgl t, HttpSession session){
        YhglYwYhyy userinfo= (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER );
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date date  = new Date();
        try{
            if(t.getId()==null){
                t.setSjhz_bj(t.getSjhz_bj()==null?null:BigDecimal.valueOf(t.getSjhz_bj().doubleValue()*10000));
                t.setSjhz_lx(t.getSjhz_lx()==null?null:BigDecimal.valueOf(t.getSjhz_lx().doubleValue()*10000));
                t.setHzzjly_clzcph(t.getHzzjly_clzcph()==null?null:BigDecimal.valueOf(t.getHzzjly_clzcph().doubleValue()*10000));
                t.setHzzjly_qtzcph(t.getHzzjly_qtzcph()==null?null:BigDecimal.valueOf(t.getHzzjly_qtzcph().doubleValue()*10000));
                t.setHzzjly_dfzqzh(t.getHzzjly_dfzqzh()==null?null:BigDecimal.valueOf(t.getHzzjly_dfzqzh().doubleValue()*10000));
                t.setHzzjly_shhrz(t.getHzzjly_shhrz()==null?null:BigDecimal.valueOf(t.getHzzjly_shhrz().doubleValue()*10000));
                t.setDjr(userinfo.getYhzh());
                t.setDjrq(date);

                baseDao.save(t);
            }else{
                baseDao.executeBySql( " update fbyx_zwzxgl set " +
                        (t.getSjhz_ny()==null?" sjhz_ny='',":" sjhz_ny="+t.getSjhz_ny()+",")+
                        (t.getSjhz_bj()==null?" sjhz_bj='',":" sjhz_bj="+BigDecimal.valueOf(t.getSjhz_bj().doubleValue()*10000)+",")+
                        (t.getSjhz_lx()==null?" sjhz_lx='',":" sjhz_lx="+BigDecimal.valueOf(t.getSjhz_lx().doubleValue()*10000)+",")+
                        (t.getHzzjly_clzcph()==null?" hzzjly_clzcph='',":" hzzjly_clzcph="+BigDecimal.valueOf(t.getHzzjly_clzcph().doubleValue()*10000)+",")+
                        (t.getHzzjly_qtzcph()==null?" hzzjly_qtzcph='',":" hzzjly_qtzcph="+BigDecimal.valueOf(t.getHzzjly_qtzcph().doubleValue()*10000)+",")+
                        (t.getHzzjly_dfzqzh()==null?" hzzjly_dfzqzh='',":" hzzjly_dfzqzh="+BigDecimal.valueOf(t.getHzzjly_dfzqzh().doubleValue()*10000)+",")+
                        (t.getHzzjly_shhrz()==null?" hzzjly_shhrz=''":" hzzjly_shhrz="+BigDecimal.valueOf(t.getHzzjly_shhrz().doubleValue()*10000))+
                        "where id="+t.getId());
            }

        }catch (Exception e){
            e.printStackTrace();
            return $Result.fail();
        }
        return $Result.success();
    }

    public $Result delete(String  id, HttpSession session){
        if(!StringUtil.isblank(id)){
            try{
                baseDao.executeBySql(" delete from  fbyx_zwzxgl  where id="+id);
            }catch (Exception e){
                e.printStackTrace();
                return $Result.fail();
            }
        }

        return $Result.success();
    }

    public void insertExcel(List<String[]>  list, String ptgs, HttpSession session){
        YhglYwYhyy userinfo= (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER );
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date date = new Date();
        for(int i=2;i<list.size();i++){
            Object[] ss = list.get(i);

            baseDao.executeBySql("insert into temp_fbyx_zwzxgl (XH,ZXND, ZWBM, ZWMC, NY, BJ, LX, XJ, CLZCPH, QTZCPH, DFZQZH,SHHRZ) " +
                    "values ('"+ss[0].toString()+"'"+
                    ",'"+ss[1].toString()+ "','"+ss[2].toString()+ "','"+ss[3].toString()+"'"+
                    ",'"+ss[4].toString()+ "','"+ss[5].toString()+ "','"+ss[6].toString()+"'"+
                    ",'"+ss[7].toString()+ "','"+ss[8].toString()+ "','"+ss[9].toString()+"'"+
                    ",'"+ss[10].toString()+ "','"+ss[11].toString()+ "')");

        }

        List tlist = baseDao.selectMapsBySQL("select distinct zxnd,zwbm from temp_fbyx_zwzxgl ");

        for (int i = 0; i < tlist.size(); i++) {
            Map t = (Map)tlist.get(i);

            if(t.get("ZWBM")!=null){
                if(Long.parseLong(t.get("ZXND").toString())>=Long.parseLong(sdf.format(date).substring(0,4))){//历史年份不可导
                    List<FbyxZwgl> wlist  = baseDao.selectByHql(" from FbyxZwgl where zwbm='"+t.get("ZWBM").toString()+"'  and zt='1'");

                    if(wlist.size()!=0) {
                        FbyxZwgl zw = wlist.get(0);
                        StringBuffer sql = new StringBuffer();
                        sql.append("select z.id " +
                                "from fbyx_ndhzb z " +
                                "where to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq and z.ptgs='"+ptgs+"' and z.nd='"+t.get("ZXND").toString()+"' and z.zwid='" + zw.getId() + "'");
                        List zlist = baseDao.selectMapsBySQL(sql.toString());
                        if (zlist.size() > 0) {
                            Map z = (Map)zlist.get(0);

                            baseDao.executeBySql("delete from fbyx_zwzxgl where id_fbyx_ndhzb = '"+z.get("ID").toString()+"'");

                            List mList = baseDao.selectMapsBySQL(" select ny,bj,lx, clzcph, qtzcph, dfzqzh,shhrz from temp_fbyx_zwzxgl where zwbm = '"+t.get("ZWBM").toString()+"'");
                            for (int j = 0; j < mList.size(); j++) {
                                Map m = (Map)mList.get(j);

                                baseDao.executeBySql("insert into fbyx_zwzxgl (id,id_fbyx_ndhzb, sjhz_ny, sjhz_bj,sjhz_lx,hzzjly_clzcph,hzzjly_qtzcph,hzzjly_dfzqzh,hzzjly_shhrz, djr,djrq) " +
                                        "values (SEQ_FBYX_ZWZXGL.NEXTVAL,'"+z.get("ID").toString()+"', "+
                                        (m.get("NY")==null?"''": m.get("NY").toString())+", "+
                                        (m.get("BJ")==null?"''": BigDecimal.valueOf(Double.parseDouble(m.get("BJ").toString())*10000))+", "+
                                        (m.get("LX")==null?"''": BigDecimal.valueOf(Double.parseDouble(m.get("LX").toString())*10000))+", "+
                                        (m.get("CLZCPH")==null?"''": BigDecimal.valueOf(Double.parseDouble(m.get("CLZCPH").toString())*10000))+", "+
                                        (m.get("QTZCPH")==null?"''": BigDecimal.valueOf(Double.parseDouble(m.get("QTZCPH").toString())*10000))+", "+
                                        (m.get("DFZQZH")==null?"''": BigDecimal.valueOf(Double.parseDouble(m.get("DFZQZH").toString())*10000))+", "+
                                        (m.get("SHHRZ")==null?"''": BigDecimal.valueOf(Double.parseDouble(m.get("SHHRZ").toString())*10000))+", '"+userinfo.getYhzh()+"',sysdate)");

                            }

                        }
                    }
                }
            }




        }

        //清除临时表
        baseDao.executeBySql("delete from temp_fbyx_zwzxgl");


    }


    @Override
    public String[][] exportexcel(String nd,String ptgs,String zwmc){
        StringBuffer sql = new StringBuffer();
        sql.append("select z.id,z.nd,z.ptgs,z.zwid,g.zwbm,g.zwmc," +
                "z.nc_bj/10000 as nc_bj," +
                "z.nc_lx/10000 as nc_lx," +
                "z.tz_bj/10000 as tz_bj," +
                "z.tz_lx/10000 as tz_lx," +
                "(nvl(z.nc_bj,0)+nvl(z.tz_bj,0))/10000 as nd_bj," +
                "(nvl(z.nc_lx,0)+nvl(z.tz_lx,0))/10000 as nd_lx," +
                "z.czrq,z.zfrq, " +
                "l.sjhz_ny ,"+
                "case when l.sjhz_bj is null then 0 else l.sjhz_bj/10000 end as sjhz_bj,"+
                "case when l.sjhz_lx is null then 0 else l.sjhz_lx/10000 end as sjhz_lx,"+
                "case when l.hzzjly_clzcph is null then 0 else l.hzzjly_clzcph/10000 end as hzzjly_clzcph,"+
                "case when l.hzzjly_qtzcph is null then 0 else l.hzzjly_qtzcph/10000 end as hzzjly_qtzcph,"+
                "case when l.hzzjly_dfzqzh is null then 0 else l.hzzjly_dfzqzh/10000 end as hzzjly_dfzqzh,"+
                "case when l.hzzjly_shhrz is null then 0 else l.hzzjly_shhrz/10000 end as hzzjly_shhrz "+

                "from fbyx_ndhzb z " +
                "left join fbyx_zwgl g on z.zwid=g.id "+
                "left join fbyx_zwzxgl l on z.id=l.id_fbyx_ndhzb "+
                " where 1=1 and to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq");
        if(!StringUtil.isblank(nd)){
            sql.append(" and z.nd ="+nd);
        }
        if(!StringUtil.isblank(ptgs)){
            sql.append(" and z.ptgs ="+ptgs);
        }
        if(!StringUtil.isblank(zwmc)){
            sql.append(" and g.zwmc like '%"+zwmc+"%'");
        }

        sql.append(" order by z.id");
        List list =baseDao.selectMapsBySQL(sql.toString());


        String[][] a1 = new String[list.size()+2][13];
        a1[0][0] = "序号";
        a1[0][1] = "执行年度";
        a1[0][2] = "债务编码";
        a1[0][3] = "债务名称";
        a1[0][4] = "实际化债金额";
        a1[0][7] = "化债资金来源";
        a1[1][4] = "年月";
        a1[1][5] = "本金";
        a1[1][6] = "利息";
        a1[1][7] = "小计";
        a1[1][8] = "存量资产盘活";
        a1[1][9] = "其他资产盘活";
        a1[1][10] = "地方债券置换";
        a1[1][11] = "社会化融资(周转金)";


        for (int j=0;j<list.size();j++) {
            Map map = (Map) list.get(j);

            a1[j + 2][0] = String.valueOf(j+1);
            a1[j + 2][1] = (map.get("ND")==null?"":map.get("ND").toString().substring(0,4));
            a1[j + 2][2] = (map.get("ZWBM")==null?"":map.get("ZWBM").toString());
            a1[j + 2][3] = (map.get("ZWMC")==null?"":map.get("ZWMC").toString());

            a1[j + 2][4] = (map.get("SJHZ_NY")==null?"":map.get("SJHZ_NY").toString());
            a1[j + 2][5] = (map.get("SJHZ_BJ")==null?"":map.get("SJHZ_BJ").toString());
            a1[j + 2][6] = (map.get("SJHZ_LX")==null?"":map.get("SJHZ_LX").toString());


            a1[j + 2][7] = String.valueOf(
                            (map.get("HZZJLY_CLZCPH")==null?0:Long.parseLong(map.get("HZZJLY_CLZCPH").toString()))+
                            (map.get("HZZJLY_QTZCPH")==null?0:Long.parseLong(map.get("HZZJLY_QTZCPH").toString()))+
                            (map.get("HZZJLY_DFZQZH")==null?0:Long.parseLong(map.get("HZZJLY_DFZQZH").toString()))+
                            (map.get("HZZJLY_SHHRZ")==null?0:Long.parseLong(map.get("HZZJLY_SHHRZ").toString()))
            );
            a1[j + 2][8] = (map.get("HZZJLY_CLZCPH")==null?"":map.get("HZZJLY_CLZCPH").toString());
            a1[j + 2][9] = (map.get("HZZJLY_QTZCPH")==null?"":map.get("HZZJLY_QTZCPH").toString());
            a1[j + 2][10] = (map.get("HZZJLY_DFZQZH")==null?"":map.get("HZZJLY_DFZQZH").toString());
            a1[j + 2][11] = (map.get("HZZJLY_SHHRZ")==null?"":map.get("HZZJLY_SHHRZ").toString());

        }

        return a1;
    }
}
