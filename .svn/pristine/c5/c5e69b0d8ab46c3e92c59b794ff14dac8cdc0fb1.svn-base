package com.datanew.service.impl;

import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.dto.unalterable.$Pages;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.Xxb;
import com.datanew.model.YhglYwYhyy;
import com.datanew.service.XtPcService;
import com.datanew.util.StaticData;
import com.datanew.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.*;

@Service
//
public  class XtPcServiceImpl implements XtPcService {

    @Resource
    private BaseDao baseDao;

    public List<Xxb> queryXxb(Map<String,String> pageMap){
//        List rlist = new ArrayList();
//        String lx="";
//        List l = baseDao.selectMapsBySQL("select lx from fbyx_xxb group by lx");
//        for (int i = 0; i <l.size() ; i++) {
//            Map m = (Map)l.get(i);
//            lx=m.get("LX").toString();
//
//            List lm = baseDao.selectMapsBySQL("select id,bm,mc,fjbm,yxbz,lx,ms from fbyx_xxb where lx='"+lx+"'");
//            for (int j = 0; j <lm.size() ; j++) {
//                rlist.add(JSON.parseObject(JSON.toJSONString(lm.get(j)),Xxb.class));
//            }
//
//        }
//
//        return rlist;

        List param=new ArrayList();
        String hql="select new map(t.id as id,t.bm as bm,t.mc as mc,t.fjbm as fjbm,t.yxbz as yxbz,t.lx as lx,t.ms as ms)  from com.datanew.model.Xxb t where t.yxbz='Y' ";

        return baseDao.selectByHql(hql,param);
    }

    public Result validateMc(Xxb t, Result result){
        try{
            List list = baseDao.selectMapsBySQL("select * from fbyx_xxb where mc='"+t.getMc()+"'");
            if(list!=null&&list.size()>0){
                result.setSuccess(false);
                result.setContent("选项名称已存在");
                return result;
            }else{
                result.setSuccess(true);
                result.setContent("名称可使用");
                return result;
            }

        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setContent("查询选项名称错误");
            return result;
        }
    }

    public Result saveXxb(Xxb t, Result result){

        try{
            baseDao.save(t);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setContent("新增失败");
            return result;
        }
        result.setSuccess(true);
        result.setContent("新增成功");
        return result;

    }

    public Result updateXxb(Xxb t, Result result){

        try{
            baseDao.update(t);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setContent("修改失败");
            return result;
        }
        result.setSuccess(true);
        result.setContent("修改成功");
        return result;
    }

    public Result deleteXxb(Xxb t, Result result){

        try{
            baseDao.delete(t);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setContent("删除失败");
            return result;
        }
        result.setSuccess(true);
        result.setContent("删除成功");
        return result;
    }



}
