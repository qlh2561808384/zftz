package com.datanew.service.impl;

import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.FbyxZygl;
import com.datanew.model.Ptgs;
import com.datanew.model.Xxb;
import com.datanew.model.YhglYwYhyy;
import com.datanew.service.ZycService;
import com.datanew.util.StaticData;
import com.datanew.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wuwei
 * @create 2019/5/17 13:57
 * @desc
 **/
@Service("zycService")
public class ZycServiceImpl implements ZycService {

    @Resource
    private BaseDao baseDao;

    public List queryPtgs(){
        String sql="select id,bm,mc,0 as pid,1 as isleaf from  fbyx_ptgs where  zt='1'";
        List list =baseDao.selectMapsBySQL(sql);
        return list;
    }

    public List<Xxb> queryZyzl(){
        List param=new ArrayList();
        String hql="select new map(t.id as id,t.bm as bm,t.mc as mc,t.fjbm as fjbm,t.yxbz as yxbz,t.lx as lx,t.ms as ms,t.fjbm as pid,case when t.fjbm='0' then 0  else 1 end as ISLEAF  )  from com.datanew.model.Xxb t where t.yxbz='Y' and t.lx='srgh' order by t.bm";

        return baseDao.selectByHql(hql,param);
    }

    public Pages getZycList(Pages page, String zymc, String zylx, String ptgs, HttpSession session){
        StringBuffer sql = new StringBuffer();
        sql.append("select z.id,z.zybm,z.zymc,z.zmlx,z.ptgs,z.dz,z.mj," +
                "case when z.ygjz='' then 0 else z.ygjz/10000 end  as ygjz," +
                "z.fbrq,z.jsrq,z.zt,z.djr,to_char(z.djrq,'yyyy-MM-dd hh24:mi:ss') as djrq " +
                " from fbyx_zygl z left join fbyx_ptgs p on z.ptgs=p.id where  z.zt=1 ");
        if(!StringUtil.isblank(zymc)){
            sql.append(" and z.zymc like '%"+zymc+"%'");

        }
        if(!StringUtil.isblank(zylx)){
            sql.append(" and z.zmlx='"+zylx+"'");

        }
        if(!StringUtil.isblank(ptgs)){
            sql.append(" and z.ptgs='"+ptgs+"'");

        }
        sql.append(" order by z.id");
        List list =baseDao.selectMapsBySQL(sql.toString(),null,page.getOffset(),page.getLimit());
        Long count =baseDao.getCountBySQL("select count(*) from ("+sql+") t",null);


        page.setTotal(count.intValue());
        page.setRows(list);

        return page;
    }

    public Result validateBm(String bm, HttpSession session){
        Result result = new Result();
        List<FbyxZygl> l = baseDao.selectByHql(" from FbyxZygl where zt=1 and zybm='"+bm+"'");
        if(l.size()>0){
            result.setSuccess(true);
            result.setContent("编码已经被使用!");
            return  result;
        }

        return  result;
    }


    public $Result saveOrUpdate(FbyxZygl t, HttpSession session){
        YhglYwYhyy userinfo= (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER );

        try{
            if(t.getId()==null){
                t.setYgjz(t.getYgjz()==null?null:BigDecimal.valueOf(t.getYgjz().doubleValue()*10000));
                t.setZt(1L);
                t.setDjr(userinfo.getYhzh());
                t.setDjrq(new Date());
                baseDao.save(t);
            }else{
                String sql = "update fbyx_zygl set zybm='"+t.getZybm()+"'," +
                        " zymc='"+t.getZymc()+"',"+
                        " zmlx='"+t.getZmlx()+"',"+
                        " ptgs='"+t.getPtgs()+"',"+
                        " dz='"+t.getDz()+"',"+
                        " mj="+t.getMj()+","+
                        (t.getYgjz()==null?" ygjz='',":" ygjz="+BigDecimal.valueOf(t.getYgjz().doubleValue()*10000)+",")+
                        " fbrq="+t.getFbrq()+","+
                        " jsrq="+t.getJsrq()+" "+
                        " where id = "+t.getId();

                baseDao.executeBySql(sql);
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
                baseDao.executeBySql(" update fbyx_zygl set zt=0  where id="+id);
                baseDao.executeBySql("delete from fbyx_ndsrb where zymcid="+id);
            }catch (Exception e){
                e.printStackTrace();
                return $Result.fail();
            }
        }

        return $Result.success();
    }

    public void insertExcel(List<String[]>  list, HttpSession session){
        YhglYwYhyy userinfo= (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER );

        Date date = new Date();
        for(int i=1;i<list.size();i++){
            Object[] ss = list.get(i);
            FbyxZygl zygl = new FbyxZygl();
            List<FbyxZygl> zl = baseDao.selectByHql(" from FbyxZygl where zybm='"+ss[1].toString()+"' and zt='1'");
            if(zl.size()==0){
                zygl.setZybm(ss[1].toString());
                zygl.setZymc(ss[2].toString());
                zygl.setZmlx("".equals(ss[3].toString())?null:Long.parseLong(ss[3].toString()));
                List<Ptgs> l = baseDao.selectByHql(" from Ptgs where mc = '"+ss[5].toString()+"'");
                if(l!=null&&l.size()>0){
                    Ptgs p = l.get(0);
                    zygl.setPtgs(p.getId());
                }

                zygl.setDz(ss[6].toString());
                zygl.setMj("".equals(ss[7].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[7].toString())));
                zygl.setYgjz("".equals(ss[8].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[8].toString())*10000));
                zygl.setFbrq("".equals(ss[9].toString())?null: Long.parseLong(ss[9].toString()));
                zygl.setJsrq("".equals(ss[10].toString())?null: Long.parseLong(ss[10].toString()));
                zygl.setZt(1L);
                zygl.setDjr(userinfo.getYhzh());
                zygl.setDjrq(date);
                baseDao.save(zygl);
            }


        }
    }

    public String[][] exportexcel(String zymc,String zmlx,String ptgs){
        StringBuffer sql = new StringBuffer();
        sql.append("select z.id,z.zybm,z.zymc,z.zmlx,x.mc as zmlxmc ,z.ptgs,s.mc as ptgsmc,z.dz,z.mj," +
                "z.ygjz/10000 as ygjz,z.fbrq,z.jsrq,z.zt,z.djr,z.djrq " +
                "from fbyx_zygl z " +
                "left join fbyx_xxb x on z.zmlx=x.bm and x.lx='srgh' " +
                "left join fbyx_ptgs s on z.ptgs=s.id  " +
                "where 1=1 ");
        if(!StringUtil.isblank(zymc)){
            sql.append(" and z.zymc like '%"+zymc+"%'");

        }
        if(!StringUtil.isblank(zmlx)){
            sql.append(" and z.zmlx='"+zmlx+"'");

        }
        if(!StringUtil.isblank(ptgs)){
            sql.append(" and z.ptgs='"+ptgs+"'");

        }

        sql.append(" order by z.id");
        List list =baseDao.selectMapsBySQL(sql.toString());


        String[][] a1 = new String[list.size()+2][11];
        a1[0][0] = "序号";
        a1[0][1] = "资源编码";
        a1[0][2] = "资源名称";
        a1[0][3] = "资源类型编码";
        a1[0][4] = "资源类型名称";
        a1[0][5] = "平台公司";
        a1[0][6] = "地址";
        a1[0][7] = "面积(亩)";
        a1[0][8] = "预估价值(万元)";
        a1[0][9] = "封闭日期";
        a1[0][10] = "结束日期";



        for (int j=0;j<list.size();j++) {
            Map map = (Map) list.get(j);

            a1[j + 2][0] = String.valueOf(j+1);
            a1[j + 2][1] = (map.get("ZYBM")==null?"":map.get("ZYBM").toString());
            a1[j + 2][2] = (map.get("ZYMC")==null?"":map.get("ZYMC").toString());
            a1[j + 2][3] = (map.get("ZMLX")==null?"":map.get("ZMLX").toString());
            a1[j + 2][4] = (map.get("ZMLXMC")==null?"":map.get("ZMLXMC").toString());
            a1[j + 2][5] = (map.get("PTGSMC")==null?"":map.get("PTGSMC").toString());
            a1[j + 2][6] = (map.get("DZ")==null?"":map.get("DZ").toString());
            a1[j + 2][7] = (map.get("MJ")==null?"":map.get("MJ").toString());
            a1[j + 2][8] = (map.get("YGJZ")==null?"":map.get("YGJZ").toString());
            a1[j + 2][9] = (map.get("FBRQ")==null?"":map.get("FBRQ").toString());
            a1[j + 2][10] = (map.get("JSRQ")==null?"":map.get("JSRQ").toString());
        }

        return a1;
    }

}
