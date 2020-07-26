package com.datanew.service.impl;

import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.*;
import com.datanew.service.XmcService;
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

/**
 * @author wuwei
 * @create 2019/5/17 13:57
 * @desc
 **/
@Service("xmcService")
public class XmcServiceImpl implements XmcService {

    @Resource
    private BaseDao baseDao;

    public List queryPtgs(){
        String sql="select id,bm,mc,0 as pid,1 as isleaf from  fbyx_ptgs ";
        List list =baseDao.selectMapsBySQL(sql);
        return list;
    }

    public List queryXmlx(){
        String sql="select t.id as id,t.bm as bm,t.mc as mc,t.fjbm as fjbm,t.yxbz as yxbz,t.lx as lx,t.ms as ms,t.fjbm as pid,1 as ISLEAF    from zftz_xxb t where t.yxbz='Y' and t.lx='xmlx' order by t.bm";

        return baseDao.selectMapsBySQL(sql);
    }

    public List queryJsdw(){
        String sql="select t.id as id,t.bm as bm,t.mc as mc,t.fjbm as fjbm,t.yxbz as yxbz,t.fjbm as pid,  " +
                "case when (select count(*) from v_zftz_jsdw w where t.id=w.fjbm)>0 then 0 else 1 end as ISLEAF    from v_zftz_jsdw t where t.yxbz='Y'  order by t.bm";

        return baseDao.selectMapsBySQL(sql);
    }

    public List queryXmzt(){
        String sql="select t.id as id,t.bm as bm,t.mc as mc,t.fjbm as fjbm,t.yxbz as yxbz,t.lx as lx,t.ms as ms,t.fjbm as pid,1 as ISLEAF    from zftz_xxb t where t.yxbz='Y' and t.lx='xmjd' order by t.bm";

        return baseDao.selectMapsBySQL(sql);
    }

    public Pages getXmcList(Pages page, String sffb, String xmmc,String xmlx,String ptgs, HttpSession session){
        StringBuffer sql = new StringBuffer();
        sql.append("select " +
                "       t.id_zftz_xm as id," +
                "       t.xmmc," +
                "( select t1.mc as mc  from zftz_xxb t1 where t.xmjd = t1.bm and t1.yxbz='Y' and t1.lx='xmjd' ) as xmjd," +
                "( select t2.mc as mc  from zftz_xxb t2 where t.xmlx = t2.bm and t2.yxbz='Y' and t2.lx='xmlx' ) as xmlx," +
                "( select t3.mc as mc  from v_zftz_jsdw t3 where t.jsdw = t3.id and t3.yxbz='Y' ) as jsdw," +
                "       t.sffb," +
                "       t.xmbh," +
                "       t.lxnd," +
                "       case" +
                "         when t.gsztz is not null then" +
                "          t.gsztz/10000" +
                "         when t.ztz_zjlydj is not null then" +
                "          t.ztz_zjlydj/10000" +
                "         else" +
                "          t.ztz_qqch/10000" +
                "       end sdtze," +
                "       t.sjkgrq as jhkgrq " +
                "  from v_Zftz_Xmjbxx t, zftz_xm t4" +
                " where t. id_zftz_xm = t4.id and t4.zt=1 ");

        if(!StringUtil.isblank(sffb)){
            if(!"0".equals(sffb)){
                if("1".equals(sffb)){
                    sql.append(" and t.sffb = 1");
                }else{
                    sql.append(" and t.sffb = 0");
                }
            }
        }
        if(!StringUtil.isblank(xmmc)){
            sql.append(" and t.xmmc like '%"+xmmc+"%'");

        }
        if(!StringUtil.isblank(xmlx)){
            sql.append(" and t.xmlx="+xmlx);

        }
        if(!StringUtil.isblank(ptgs)){
            sql.append(" and t.jsdw='"+ptgs+"'");

        }
        sql.append(" order by t.id_zftz_xm");
        List list =baseDao.selectMapsBySQL(sql.toString(),null,page.getOffset(),page.getLimit());
        Long count =baseDao.getCountBySQL("select count(*) from ("+sql+") t",null);


        page.setTotal(count.intValue());
        page.setRows(list);

        return page;
    }


    public $Result update(ZftzXm t, ZftzXmtzwh z, HttpSession session){
        YhglYwYhyy userinfo= (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER );

        try{
            if(t.getId()==null){

            }else{
                String sql1 = "update zftz_xm set sffb="+t.getSffb()+
                        "  where id = "+t.getId();

                baseDao.executeBySql(sql1);

            }

        }catch (Exception e){
            e.printStackTrace();
            return $Result.fail();
        }
        return $Result.success();
    }

}
