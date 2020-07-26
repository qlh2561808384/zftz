package com.datanew.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.model.YhglYwYhyy;
import com.datanew.model.ZftzSplc;
import com.datanew.model.ZftzZbk;
import com.datanew.service.ZbkService;
import com.datanew.util.ExcelUtil;
import com.datanew.util.StaticData;
import com.datanew.util.StringUtil;



/**
 * @author wuwei
 * @create 2019/5/17 13:57
 * @desc
 **/
@Service("zbkService")
public class ZbkServiceImpl implements ZbkService {

    @Resource
    private BaseDao baseDao;

	@Override
	public Pages getZbk(Pages page,String xmlx, HttpSession session) {
		
		String sql=" from zftz_zbk where zfrq is null ";
		if (!StringUtil.isblank(xmlx)) {
			sql =sql+" and xmlx="+xmlx;
		}
		List list =baseDao.selectMapsBySQL("select * "+sql, null, page.getOffset(), page.getLimit());
        Long count =baseDao.getCountBySQL("select count(*) "+sql,null);
        page.setTotal(count.intValue());
        page.setRows(list);
		return page;
	}

	@Override
	public Result saveZbk(String guid,String xmlx, String yjzb, String ejzb, String sjzb, String dwtze, String jldw, String bz,
			HttpSession session) {
		Result result=new Result();
		YhglYwYhyy yhglywyhyy=((YhglYwYhyy)session.getAttribute(StaticData.LOGINUSER));
		String[] guids=guid.split(",");
		String[] xmlxs=xmlx.split(",");
		String[] yjzbs=yjzb.split(",");
		String[] ejzbs=ejzb.split(",");
		String[] sjzbs=sjzb.split(",");
		String[] dwtzes=dwtze.split(",");
		String[] jldws=jldw.split(",");
		String[] bzs=bz.split(",");

			for (int i = 0; i < xmlxs.length; i++) {
				if (!"-1".equals(guids[i])) {
					ZftzZbk zftzzbk=(ZftzZbk)baseDao.load(ZftzZbk.class, Long.parseLong(guids[i]));
//					List list=baseDao.selectMapsBySQL("select * from zftz_zbk where XMLX="+xmlxs[i]+" and yjzb='"+yjzbs[i]+"' and ejzb='"+ejzbs[i]+"' and sjzb='"+sjzbs[i]+"'");
//					if (list.size()>1) {
//						
//					}else{
						zftzzbk.setXmlx(Integer.parseInt(xmlxs[i]));
						if (!"EMPTY".equals(yjzbs[i])) {
							zftzzbk.setYjzb(yjzbs[i]);
						}
						if (!"EMPTY".equals(ejzbs[i])) {
							zftzzbk.setEjzb(ejzbs[i]);					
						}
						if (!"EMPTY".equals(sjzbs[i])) {
							zftzzbk.setSjzb(sjzbs[i]);
						}
						if (!"EMPTY".equals(dwtzes[i])) {
							zftzzbk.setDwtze(new BigDecimal(dwtzes[i]));
						}
						if (!"EMPTY".equals(bzs[i])) {
							zftzzbk.setBz(bzs[i]);	
						}
						if (!"EMPTY".equals(jldws[i])) {
							zftzzbk.setJldw(jldws[i]);
						}
						zftzzbk.setCzsj(new Date());
						zftzzbk.setCzr(yhglywyhyy.getGuid().toString());
						baseDao.update(zftzzbk);
//					}	
				}else{
				
//					List list=baseDao.selectMapsBySQL("select * from zftz_zbk where XMLX="+xmlxs[i]+" and yjzb='"+yjzbs[i]+"' and ejzb='"+ejzbs[i]+"' and sjzb='"+sjzbs[i]+"'");
//					if (list.size()>0) {
//						
//					}else{
						ZftzZbk zftzzbk=new ZftzZbk();		
						zftzzbk.setXmlx(Integer.parseInt(xmlxs[i]));
						if (!"EMPTY".equals(yjzbs[i])) {
							zftzzbk.setYjzb(yjzbs[i]);
						}
						if (!"EMPTY".equals(ejzbs[i])) {
							zftzzbk.setEjzb(ejzbs[i]);					
						}
						if (!"EMPTY".equals(sjzbs[i])) {
							zftzzbk.setSjzb(sjzbs[i]);
						}
						if (!"EMPTY".equals(dwtzes[i])) {
							zftzzbk.setDwtze(new BigDecimal(dwtzes[i]));
						}
						if (!"EMPTY".equals(bzs[i])) {
							zftzzbk.setBz(bzs[i]);	
						}
						if (!"EMPTY".equals(jldws[i])) {
							zftzzbk.setJldw(jldws[i]);
						}
						zftzzbk.setCzsj(new Date());
						zftzzbk.setCzr(yhglywyhyy.getGuid().toString());
						baseDao.save(zftzzbk);
						
//					}

				}
					
				
			}
			result.setContent("保存成功");
			result.setSuccess(true);
			return result;
	}

	@Override
	public Result delZbk(String guid, HttpSession session) {
		Result result=new Result();
		YhglYwYhyy yhglywyhyy=((YhglYwYhyy)session.getAttribute(StaticData.LOGINUSER));
		String[] guids=guid.split(",");
		for (int i = 0; i < guids.length; i++) {
			if ("-1".equals(guids[i])) {
				
			}else{
				baseDao.executeBySql("update  zfTZ_zbk set zfrq=sysdate  where id="+guids[i]+"");
			}
		}
		result.setSuccess(true);
		result.setContent("删除成功");
		return result;
	}

	public void insertExcel(List<String[]>  list, HttpSession session){
        YhglYwYhyy userinfo= (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER );

        Date date = new Date();
        for(int i=3;i<list.size();i++){
            Object[] ss = list.get(i);
            ZftzZbk zftzzbk = new ZftzZbk();
            if ("".equals(ss[1].toString())) {
				
			}else if("学校".equals(ss[0].toString())){
				zftzzbk.setXmlx(1);
			}else if("医院".equals(ss[0].toString())){
				zftzzbk.setXmlx(2);
			}else if("办公用房".equals(ss[0].toString())){
				zftzzbk.setXmlx(3);
			}else if("市政道路".equals(ss[0].toString())){
				zftzzbk.setXmlx(4);
			}else if("高速公路".equals(ss[0].toString())){
				zftzzbk.setXmlx(5);
			}else if("其他交通道路".equals(ss[0].toString())){
				zftzzbk.setXmlx(6);
			}else if("广场".equals(ss[0].toString())){
				zftzzbk.setXmlx(7);
			}
            
            zftzzbk.setYjzb("".equals(ss[1].toString())?null:ss[1].toString());
            zftzzbk.setEjzb("".equals(ss[2].toString())?null:ss[2].toString());    
            zftzzbk.setSjzb("".equals(ss[3].toString())?null:ss[3].toString());
            zftzzbk.setDwtze("".equals(ss[4].toString())?null: BigDecimal.valueOf(Double.parseDouble(ss[4].toString())));
            zftzzbk.setJldw("".equals(ss[5].toString())?null:ss[5].toString());
            zftzzbk.setBz("".equals(ss[6].toString())?null:ss[6].toString());
            zftzzbk.setCzsj(new Date());
            zftzzbk.setCzr(userinfo.getGuid().toString());
            baseDao.save(zftzzbk);
        }
    }

	@Override
	public ExcelUtil exportExcelByLx(String xmlx, HttpServletResponse res) {
			String title="指标库维护";
		 	String[] rowName ={"项目类型","一级指标","二级指标","三级指标","单位投资额"
	                ,"计量单位","备注"};
	        StringBuffer sql = new StringBuffer();
	        sql.append("select (case z.xmlx when 1 then '学校' when 2 then '医院' when 3 then '办公用房' when 4 then '市政道路' when 5 then '高速公路' when 6 then '其他交通道路' when 7 then '广场'  end) as xmlx,");
	        sql.append(" z.yjzb,z.ejzb,z.sjzb,z.dwtze,z.jldw,z.bz from zftz_zbk z where z.zfrq is null ");
	        if (!StringUtil.isblank(xmlx)) {
	        	sql.append(" and z.xmlx="+xmlx);
			}
	        List<Object[]> dataList= baseDao.selectBySql(sql.toString());
	        return new ExcelUtil(title,rowName,dataList,res);
	 
	}		

    

}
