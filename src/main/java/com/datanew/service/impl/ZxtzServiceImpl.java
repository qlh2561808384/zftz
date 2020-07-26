package com.datanew.service.impl;

import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.*;
import com.datanew.service.ZxtzService;
import com.datanew.util.StaticData;
import com.datanew.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wuwei
 * @create 2019/6/4 9:19
 * @desc
 **/
@Service("zxtzService")
public class ZxtzServiceImpl implements ZxtzService {

    @Resource
    private BaseDao baseDao;

    public Pages getZyzxList(Pages page, String nd, String ptgs, String zymc, HttpSession session){
        if(!StringUtil.isblank(nd)&&!StringUtil.isblank(ptgs)){
            StringBuffer sql = new StringBuffer();
            sql.append("select z.id,z.ptgs,z.zmlx,x.mc as zmlxmc,z.zymcid,z.zymc as zymc,z.ssxzqh,r.mc as ssxzqhmc, z.jhcrrq," +
                    "case when z.nc_jhje is null then 0 else z.nc_jhje/10000 end as nc_jhje," +
                    "case when z.tz_jhje is null then 0 else (nvl(z.nc_jhje,0)+nvl(z.tz_jhje,0))/10000 end as tz_jhje,"+
                    "case when z.tz_jhje is null then (nvl(z.nc_jhje,0)+nvl(z.tz_jhje,0))/10000 else (nvl(z.nc_jhje,0)+nvl(z.tz_jhje,0)+nvl(z.nc_jhje,0))/10000 end as jhcrje,"+
                    "z.sjcr_rq,"+
                    "case when z.sjcr_je is null then 0 else z.sjcr_je/10000 end as sjcr_je," +
                    "z.czrq,z.zfrq,z.bz " +
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
            if(!StringUtil.isblank(zymc)){
                sql.append(" and z.zymc like '%"+zymc+"%'");
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
        }

        return page;
    }

    public $Result updateByZyzx(FbyxNdsrb t, String ptgs, HttpSession session){
        YhglYwYhyy userinfo= (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER );
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date date  = new Date();
        try{
            if(t.getId()==null){

            }else{
                baseDao.executeBySql( " update fbyx_ndsrb set " +
                        (t.getBz()==null?" bz='',":" bz='"+t.getBz()+"',")+
                        (t.getSjcrrq()==null?" sjcr_rq='',":" sjcr_rq="+t.getSjcrrq()+",")+
                        (t.getSjcrje()==null?" sjcr_je=''":" sjcr_je="+BigDecimal.valueOf(t.getSjcrje().doubleValue()*10000))+
                        "where id="+t.getId());

            }

        }catch (Exception e){
            e.printStackTrace();
            return $Result.fail();
        }
        return $Result.success();
    }

    public $Result updateByZyzxDz(FbyxNdsrjhDz t, String srid, HttpSession session){
        YhglYwYhyy userinfo= (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER );
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date date  = new Date();
        try{
            if(t.getId()==null){

            }else{
                baseDao.executeBySql( " update fbyx_ndsrjh_dz set " +
                        (t.getSjdzrq()==null?" sjdz_rq='',":" sjdz_rq="+t.getSjdzrq()+",")+
                        (t.getSjdzje()==null?" sjdz_je=''":" sjdz_je="+BigDecimal.valueOf(t.getSjdzje().doubleValue()*10000))+
                        "where id="+t.getId());

            }

        }catch (Exception e){
            e.printStackTrace();
            return $Result.fail();
        }
        return $Result.success();
    }

    public void insertExcel(List<String[]>  list,String ptgs, HttpSession session){
        YhglYwYhyy userinfo= (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER );
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date date = new Date();
        for(int i=2;i<list.size();i++){
            Object[] ss = list.get(i);

            if(Long.parseLong(ss[1].toString())>=Long.parseLong(sdf.format(date).substring(0,4))){//历史年份不导入
                List<FbyxZygl> l = baseDao.selectByHql(" from FbyxZygl where zybm = '"+ss[2].toString()+"' and zt='1'");
                if(l.size()!=0){
                    FbyxZygl zy = l.get(0);
                    StringBuffer sql = new StringBuffer();
                    sql.append("select z.id,z.zymc," +
                            " case when z.nc_jhje is null then 0 else z.nc_jhje/10000 end nc_jhje " +
                            "from fbyx_ndsrb z " +
                            "where to_char(sysdate,'yyyyMMdd') between z.czrq and z.zfrq and z.ptgs='"+ptgs+"' and z.zymcid='"+zy.getId()+"'");
                    List zlist = baseDao.selectMapsBySQL(sql.toString());
                    //大于0修改
                    if(zlist.size()>0){
                        Map o = (Map)zlist.get(0);
                        String sj_rq = "".equals(ss[7].toString())?"''":ss[7].toString();
                        String sj_je = "".equals(ss[8].toString())?"''":String.valueOf(BigDecimal.valueOf(Double.parseDouble(ss[8].toString())*10000));


                                baseDao.executeBySql( " update fbyx_ndsrb set sjcr_rq='"+ sj_rq+"',sjcr_je='"+sj_je+"'"
                                +"  where id="+o.get("ID").toString());
                    }

                }

            }


        }
    }


    @Override
    public String[][] exportexcel(String nd,String ptgs,String zymc){
        StringBuffer sql = new StringBuffer();
        sql.append("select z.id,z.ptgs,z.zmlx,x.mc as zmlxmc,z.zymcid,z.zymc as zymc,g.zybm,z.ssxzqh,r.mc as ssxzqhmc, z.jhcrrq," +
                "case when z.nc_jhje is null then 0 else z.nc_jhje/10000 end as nc_jhje," +
                "case when z.tz_jhje is null then 0 else (nvl(z.nc_jhje,0)+nvl(z.tz_jhje,0))/10000 end as tz_jhje,"+
                "case when z.tz_jhje is null then (nvl(z.nc_jhje,0)+nvl(z.tz_jhje,0))/10000 else (nvl(z.nc_jhje,0)+nvl(z.tz_jhje,0)+nvl(z.nc_jhje,0))/10000 end as jhcrje,"+
                "z.sjcr_rq,"+
                "case when z.sjcr_je is null then 0 else z.sjcr_je/10000 end as sjcr_je," +
                "z.czrq,z.zfrq,z.bz " +
                "from fbyx_ndsrb z " +
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
        if(!StringUtil.isblank(zymc)){
            sql.append(" and z.zymc like '%"+zymc+"%'");
        }

        sql.append(" order by z.id");
        List list =baseDao.selectMapsBySQL(sql.toString());


        String[][] a1 = new String[list.size()+2][13];
        a1[0][0] = "序号";
        a1[0][1] = "执行年度";
        a1[0][2] = "资源编码";
        a1[0][3] = "资源名称";
        a1[0][4] = "资源类型编码";
        a1[0][5] = "资源种类名称";
        a1[0][6] = "区县";
        a1[0][7] = "实际出让/获得";
        a1[0][9] = "备注";
        a1[1][7] = "时间";
        a1[1][8] = "金额";



        for (int j=0;j<list.size();j++) {
            Map map = (Map) list.get(j);

            a1[j + 2][0] = String.valueOf(j+1);
            a1[j + 2][1] = (map.get("JHCRRQ")==null?"":map.get("JHCRRQ").toString().substring(0,4));
            a1[j + 2][2] = (map.get("ZYBM")==null?"":map.get("ZYBM").toString());
            a1[j + 2][3] = (map.get("ZYMC")==null?"":map.get("ZYMC").toString());

            a1[j + 2][4] = (map.get("ZMLX")==null?"":map.get("ZMLX").toString());
            a1[j + 2][5] = (map.get("ZMLXMC")==null?"":map.get("ZMLXMC").toString());
            a1[j + 2][6] = (map.get("SSXZQHMC")==null?"":map.get("SSXZQHMC").toString());


            a1[j + 2][7] = (map.get("SJCR_RQ")==null?"":map.get("SJCR_RQ").toString());
            a1[j + 2][8] = (map.get("SJCR_JE")==null?"":map.get("SJCR_JE").toString());

            a1[j + 2][9] = (map.get("BZ")==null?"":map.get("BZ").toString());

        }

        return a1;
    }
}
