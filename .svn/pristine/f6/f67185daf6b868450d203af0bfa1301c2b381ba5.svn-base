package com.datanew.service.impl;

import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.*;
import com.datanew.service.ZwService;
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
@Service("zwService")
public class ZwServiceImpl implements ZwService {

    @Resource
    private BaseDao baseDao;


    public Pages getZwList(Pages page, String zwmc, String ptgs, HttpSession session){
        StringBuffer sql = new StringBuffer();
        sql.append("select z.id,z.zwbm,z.zwmc,z.zwksrq,z.zwjsrq,z.ptgs," +
                "case when z.zwze_bj='' then 0 else z.zwze_bj/10000 end  as zwze_bj," +
                "case when z.zwze_lx='' then 0 else z.zwze_lx/10000 end  as zwze_lx," +
                "case when z.zwqcye_bj='' then 0 else z.zwqcye_bj/10000 end  as zwqcye_bj," +
                "case when z.zwqcye_lx='' then 0 else z.zwqcye_lx/10000 end  as zwqcye_lx," +
                "z.bz,z.zt,z.djr,to_char(z.djrq,'yyyy-MM-dd hh24:mi:ss') as djrq " +
                " from fbyx_zwgl z left join fbyx_ptgs p on z.ptgs=p.id where  z.zt=1 ");
        if(!StringUtil.isblank(zwmc)){
            sql.append(" and z.zwmc like '%"+zwmc+"%'");

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
        List<FbyxZwgl> l = baseDao.selectByHql(" from FbyxZwgl where zt=1 and zwbm='"+bm+"'");
        if(l.size()>0){
            result.setSuccess(true);
            result.setContent("编码已经被使用!");
            return  result;
        }

        return  result;
    }

    public $Result saveOrUpdate(FbyxZwgl t, HttpSession session){
        YhglYwYhyy userinfo= (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER );

        try{
            if(t.getId()==null){
                t.setZwzebj(t.getZwzebj()==null?null:BigDecimal.valueOf(t.getZwzebj().doubleValue()*10000));
                t.setZwzelx(t.getZwzelx()==null?null:BigDecimal.valueOf(t.getZwzelx().doubleValue()*10000));
                t.setZwqcyebj(t.getZwqcyebj()==null?null:BigDecimal.valueOf(t.getZwqcyebj().doubleValue()*10000));
                t.setZwqcyelx(t.getZwqcyelx()==null?null:BigDecimal.valueOf(t.getZwqcyelx().doubleValue()*10000));
                t.setZt(1L);
                t.setDjr(userinfo.getYhzh());
                t.setDjrq(new Date());
                baseDao.save(t);
            }else{
                baseDao.executeBySql("update fbyx_zwgl set zwbm='"+t.getZwbm()+"'," +
                        " zwmc='"+t.getZwmc()+"',"+
                        " zwksrq="+t.getZwksrq()+","+
                        " zwjsrq="+t.getZwjsrq()+","+
                        " ptgs='"+t.getPtgs()+"',"+
                        (t.getZwzebj()==null?" zwze_bj='',":" zwze_bj="+BigDecimal.valueOf(t.getZwzebj().doubleValue()*10000)+",")+
                        (t.getZwzelx()==null?" zwze_lx='',":" zwze_lx="+BigDecimal.valueOf(t.getZwzelx().doubleValue()*10000)+",")+
                        (t.getZwqcyebj()==null?" zwqcye_bj='',":" zwqcye_bj="+BigDecimal.valueOf(t.getZwqcyebj().doubleValue()*10000)+",")+
                        (t.getZwqcyelx()==null?" zwqcye_lx='',":" zwqcye_lx="+BigDecimal.valueOf(t.getZwqcyelx().doubleValue()*10000)+",")+
                        " bz='"+t.getBz()+"' "+
                        " where id = "+t.getId()
                );

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
                baseDao.executeBySql(" update fbyx_zwgl set zt=0  where id="+id);
                baseDao.executeBySql("delete from fbyx_ndhzb where zwid="+id);
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
        for(int i=2;i<list.size();i++){
            Object[] ss = list.get(i);
            FbyxZwgl zwgl = new FbyxZwgl();
            List<FbyxZwgl> zl = baseDao.selectByHql(" from FbyxZwgl where zwbm='"+ss[1].toString()+"' and zt='1' ");
            if(zl.size()==0){
                zwgl.setZwbm(ss[1].toString());
                zwgl.setZwmc(ss[2].toString());
                zwgl.setZwksrq("".equals(ss[3].toString())?null: Long.parseLong(ss[3].toString()));
                zwgl.setZwjsrq("".equals(ss[4].toString())?null: Long.parseLong(ss[4].toString()));
                List<Ptgs> l = baseDao.selectByHql(" from Ptgs where mc = '"+ss[5].toString()+"'");
                if(l!=null&&l.size()>0){
                    Ptgs p = l.get(0);
                    zwgl.setPtgs(p.getId());
                }

                zwgl.setZwzebj("".equals(ss[7].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[7].toString())*10000));
                zwgl.setZwzelx("".equals(ss[8].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[8].toString())*10000));
                zwgl.setZwqcyebj("".equals(ss[10].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[10].toString())*10000));
                zwgl.setZwqcyelx("".equals(ss[11].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[11].toString())*10000));

                zwgl.setZt(1L);
                zwgl.setDjr(userinfo.getYhzh());
                zwgl.setDjrq(date);
                baseDao.save(zwgl);
            }


        }
    }

    public String[][] exportexcel(String zwmc,String ptgs){
        StringBuffer sql = new StringBuffer();
        sql.append("select z.id,z.zwbm,z.zwmc,z.zwksrq,z.zwjsrq ,z.ptgs,s.mc as ptgsmc," +
                "(nvl(z.zwze_bj,0)+nvl(z.zwze_lx,0))/10000 as zwze_hj,"+
                "z.zwze_bj/10000 as zwze_bj," +
                "z.zwze_lx/10000 as zwze_lx," +
                "(nvl(z.zwqcye_bj,0)+nvl(z.zwqcye_lx,0))/10000 as zwqcye_hj,"+
                "z.zwqcye_bj/10000 as zwqcye_bj," +
                "z.zwqcye_lx/10000 as zwqcye_lx," +
                "z.bz,z.zt,z.djr,z.djrq " +
                "from fbyx_zwgl z " +
                "left join fbyx_ptgs s on z.ptgs=s.id  " +
                "where 1=1 ");
        if(!StringUtil.isblank(zwmc)){
            sql.append(" and z.zwmc like '%"+zwmc+"%'");

        }
        if(!StringUtil.isblank(ptgs)){
            sql.append(" and z.ptgs='"+ptgs+"'");

        }

        sql.append(" order by z.id");
        List list =baseDao.selectMapsBySQL(sql.toString());


        String[][] a1 = new String[list.size()+2][12];
        a1[0][0] = "序号";
        a1[0][1] = "债务编码";
        a1[0][2] = "债务名称";
        a1[0][3] = "债务开始时间";
        a1[0][4] = "债务结束时间";
        a1[0][5] = "平台公司";
        a1[0][6] = "债务总额(万元)";
        a1[0][9] = "封闭运行期初债务余额(万元)";
        a1[1][6] = "合计";
        a1[1][7] = "本金";
        a1[1][8] = "利息";
        a1[1][9] = "合计";
        a1[1][10] = "本金";
        a1[1][11] = "利息";



        for (int j=0;j<list.size();j++) {
            Map map = (Map) list.get(j);

            a1[j + 2][0] = String.valueOf(j+1);
            a1[j + 2][1] = (map.get("ZWBM")==null?"":map.get("ZWBM").toString());
            a1[j + 2][2] = (map.get("ZWMC")==null?"":map.get("ZWMC").toString());
            a1[j + 2][3] = (map.get("ZWKSRQ")==null?"":map.get("ZWKSRQ").toString());
            a1[j + 2][4] = (map.get("ZWJSRQ")==null?"":map.get("ZWJSRQ").toString());
            a1[j + 2][5] = (map.get("PTGSMC")==null?"":map.get("PTGSMC").toString());
            a1[j + 2][6] = (map.get("ZWZE_HJ")==null?"":map.get("ZWZE_HJ").toString());
            a1[j + 2][7] = (map.get("ZWZE_BJ")==null?"":map.get("ZWZE_BJ").toString());
            a1[j + 2][8] = (map.get("ZWZE_LX")==null?"":map.get("ZWZE_LX").toString());
            a1[j + 2][9] = (map.get("ZWQCYE_HJ")==null?"":map.get("ZWQCYE_HJ").toString());
            a1[j + 2][10] = (map.get("ZWQCYE_BJ")==null?"":map.get("ZWQCYE_BJ").toString());
            a1[j + 2][11] = (map.get("ZWQCYE_LX")==null?"":map.get("ZWQCYE_LX").toString());
        }

        return a1;
    }

}
