package com.datanew.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.service.ApplyPostService;
import com.datanew.util.StaticData;
import com.datanew.util.StringUtil;
import com.sun.org.apache.bcel.internal.generic.Select;
import com.sun.org.apache.xpath.internal.operations.And;
import com.datanew.model.XtglDmYy;
import com.datanew.model.YhglDmYygw;
import com.datanew.model.YhglYwYhyy;
@Service("ApplyPostService")
public  class  ApplyPostServiceImpl implements ApplyPostService {

    @Resource
    private BaseDao baseDao;

	@Override
	public Pages getAppylToPostInfo(Pages page, String id) {
	
		String hql=" from YhglDmYygw y where y.ssyyid= "+Long.parseLong(id)+" and y.zt!='2' order by y.bm";
		List list =baseDao.selectByHql(hql, null, page.getOffset(), page.getLimit());
        Long count =baseDao.getCountByHQL("select count(*) "+hql,null);
        page.setTotal(count.intValue());
        page.setRows(list);
		
		return page;
	}
	
	@Override
	public List getApplyTree() {
		String sql="select guid as id,mc as name, 0 as isleaf,0 as pId from XTGL_DM_YY y where y.zt='1'  order by y.bm";
		List list =baseDao.selectMapsBySQL(sql);
		return list;
	}

	@Override
	public Result saveAppylToPost(YhglDmYygw yhgldmyygw, String id, Result result,HttpSession session) {
		
		if(yhgldmyygw.getGuid()!=null){
			baseDao.update(yhgldmyygw);
			result.setContent("修改成功");
			result.setSuccess(true);
		}else{
			XtglDmYy xtgldmyy=(XtglDmYy)baseDao.load(XtglDmYy.class, Long.parseLong(id));
			Date date=new Date();
			yhgldmyygw.setSsyyid(xtgldmyy.getGuid());
			yhgldmyygw.setCjsj(date);
			yhgldmyygw.setSsyybm(xtgldmyy.getBm());
			yhgldmyygw.setCjyhid(((YhglYwYhyy)session.getAttribute(StaticData.LOGINUSER)).getGuid());
			baseDao.save(yhgldmyygw);
			result.setContent("新增成功");
			result.setSuccess(true);
		}
		return result;
		
	}

	@Override
	public Result delAppylToPost(Long id, Result result) {
		String sql="update YHGL_DM_YYGW set zt='2' where guid="+id;
		baseDao.executeBySql(sql);
		result.setContent("删除成功");
		result.setSuccess(true);
		return result;
	}

	

   

}
