package com.datanew.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.persister.entity.Loadable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.dto.Result;
import com.datanew.dto.SSOLoginDTO;
import com.datanew.model.XtglGgMenu;
import com.datanew.model.XtglYygwTomenu;
import com.datanew.model.YhglYwYh;
import com.datanew.model.YhglYwYhyy;
import com.datanew.model.ZftzGwtogk;
import com.datanew.service.UserService;
import com.datanew.sso.client.SSOClientSession;
import com.datanew.sso.client.model.UserDataDTO;
import com.datanew.util.ConfigureParser;
import com.datanew.util.StaticData;
import com.datanew.util.StringUtil;


@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    BaseDao baseDao;



	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Result login(String username, String password, HttpSession session) {
		Result result = new Result();
		String hql ="select yy,y from YhglYwYh y,YhglYwYhyy yy,XtglDmYy xt " +
				" where yy.yhid = y.guid and yy.yyid = xt.guid " +
				" and xt.zt ='1'   and   y.zt='1'  and   yy.zt ='1'" +
				" and xt.bm ='"+ConfigureParser.getPropert("YYBM")+"' " +
				" and   yy.yhzh =? and '123456'=? " ;
		List values = new ArrayList();
		values.add(username);
		values.add(password);
		Object[] Object = (Object[] ) baseDao.loadByHql(hql, values);

		if(Object==null){
			result.setSuccess(false);
			result.setContent("帐号密码错误");
		}else{
			YhglYwYhyy userinfo =(YhglYwYhyy) Object[0];
			YhglYwYh yhinfo =(YhglYwYh) Object[1];

			result.setSuccess(true);
			result.setContent("登录成功");
			session.setAttribute(StaticData.LOGINUSER, userinfo);
			session.setAttribute(StaticData.LOGINUSERINFO, yhinfo);
			setUserMenu(session,userinfo);
			//selYhtoQy(session);
			session.setAttribute(StaticData.XZQH, "3306,330601,33060101,330602,330603,330604,330624,330681,330683");



		}

		return result;
	}
    //设置用户菜单
	private void setUserMenu(HttpSession session, YhglYwYhyy userinfo) {
		String menusql="select distinct m.* " +
				" from YHGL_YW_YHYY y, XTGL_YYGW_TOMENU t, XTGL_GG_MENU m,xtgl_dm_yy yy " +
				"  where  yy.bm ='"+ConfigureParser.getPropert("CDYYBM")+"'" +
				"    and yy.bm = m.YYbm " +
				"    and instr(','||y.gwid||',',','||t.gwid||',')>0  " +    //用户可能对应多个岗位
				"    and t.menyid = m.menuid " +
				"    and m.enable=1 and m.menutype=1 " +
				"    and y.yhid = '"+userinfo.getYhid()+"'" +
				"      order by m.ordernum, m.menuid ";
		List<Map> menulist = baseDao.selectMapsBySQL(menusql);
		Map indexmenu = new HashMap();
		indexmenu.put("ENABLE", 1);
		indexmenu.put("MENUNAME", "首页");
		indexmenu.put("MENUID", "11");
		indexmenu.put("MENUICON", "");
		indexmenu.put("MENUURL", "indexcontent.jsp");
		menulist.add(0, indexmenu);
		session.setAttribute(StaticData.USERMENUS, menulist);

		StringBuffer menustr=new StringBuffer(",");
		for (int i=0;i<menulist.size();i++){
			menustr.append(menulist.get(i).get("MENUID")+",");
		}
		session.setAttribute(StaticData.USERMENUSSTR, menustr.toString());


		String buttonssql="select distinct m.* " +
				" from YHGL_YW_YHYY y, XTGL_YYGW_TOMENU t, XTGL_GG_MENU m,xtgl_dm_yy yy " +
				"  where  yy.bm ='"+ConfigureParser.getPropert("CDYYBM")+"'" +
				"    and yy.bm = m.YYbm " +
				"    and instr(','||y.gwid||',',','||t.gwid||',')>0  " +    //用户可能对应多个岗位
				"    and t.menyid = m.menuid " +
				"    and m.enable=1 and m.menutype=2 " +
				"    and y.yhid = '"+userinfo.getYhid()+"'" +
				"      order by m.ordernum, m.menuid ";
		List<Map> buttonslist = baseDao.selectMapsBySQL(buttonssql);
		session.setAttribute(StaticData.USERBUTTONS, buttonslist);

		StringBuffer buttonstr=new StringBuffer(",");
		for (int i=0;i<buttonslist.size();i++){
			buttonstr.append(buttonslist.get(i).get("MENUID")+",");
		}
		session.setAttribute(StaticData.USERBUTTONSSTR, buttonstr.toString());
	}


	public Result updatePassword(String oldpassword, String newpassword, HttpSession session) {
		Result result = new Result();
		/*BaseOperator operator = (BaseOperator) session.getAttribute(StaticData.LOGINUSER);

		BaseOperator o = (BaseOperator) baseDao.load(BaseOperator.class, operator.getUserid());
		if(o.getPassword().equals(oldpassword)){
			o.setPassword(newpassword);
			baseDao.update(o);
			result.setSuccess(true);
			result.setContent("修改成功");
		}else{
			result.setSuccess(false);
			result.setContent("修改失败，原密码错误");
		}*/

		return result;
	}







	public void saveDesktop(String html, HttpServletRequest request, Result result) {
		/*BaseOperator operator = (BaseOperator) request.getSession().getAttribute(StaticData.LOGINUSER);
		BaseOperatorSupp baseOperatorSupp = (BaseOperatorSupp) baseDao.load(BaseOperatorSupp.class,operator.getUserid());
        if(baseOperatorSupp==null){
			baseOperatorSupp = new BaseOperatorSupp();
			baseOperatorSupp.setIndexhtml(html);
			baseOperatorSupp.setUserid(operator.getUserid());
			baseDao.save(baseOperatorSupp);
		}else{
			baseOperatorSupp.setIndexhtml(html);
			baseDao.update(baseOperatorSupp);
		}*/
        result.setSuccess(true);
        result.setContent("成功");


	}

	public Result getDesktop(HttpServletRequest request) {
		Result result = new Result();
		/*BaseOperator operator = (BaseOperator) request.getSession().getAttribute(StaticData.LOGINUSER);
		BaseOperatorSupp baseOperatorSupp = (BaseOperatorSupp) baseDao.load(BaseOperatorSupp.class,operator.getUserid());
		if(baseOperatorSupp==null){
			result.setSuccess(false);

		}else{
			result.setSuccess(true);
			result.setContent(baseOperatorSupp.getIndexhtml());
		}*/
		return result;
	}

//	@Override
//	public Result caslogin(HttpServletRequest request, HttpSession session) {
//		Result result = new Result();
//		try {
//			YhglYwYhyy  userinfo= (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER );
//
//			if (userinfo == null) {
//				result.setSuccess(false);
//				result.setContent("无此对应的用户");
//				return result;
//			}else{
//				setUserMenu(session,userinfo);
//				result.setSuccess(true);
//				result.setContent("登录成功");
//				return result;
//			}
//		}catch (Exception e){
//			request.getSession().invalidate();
//			e.printStackTrace();
//			result.setSuccess(false);
//			result.setContent("无此对应的用户");
//			return result;
//		}
//	}
	public Result caslogin(HttpServletRequest request, HttpSession session) {
		Result result = new Result();
		SSOLoginDTO ssoLoginDTO = new SSOLoginDTO();
		result.setContent(ssoLoginDTO);
		try {
			YhglYwYhyy  userinfo= (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER );

			if (userinfo == null) {
				result.setSuccess(false);
				ssoLoginDTO.setMsg("无此对应的用户");
				return result;
			}else{
				setUserMenu(session,userinfo);
				result.setSuccess(true);
				String redirecttokey = ConfigureParser.getPropert("sso.mode.redirectto.key");
				if (!StringUtil.isblank(redirecttokey)){
					String redirectto = request.getParameter(redirecttokey);
					if (!StringUtil.isblank(redirectto)){
						ssoLoginDTO.setRedirect_to(URLDecoder.decode(redirectto, "UTF-8"));
					}
				}
				ssoLoginDTO.setMsg("登录成功");
				return result;
			}
		}catch (Exception e){
			request.getSession().invalidate();
			e.printStackTrace();
			result.setSuccess(false);
			ssoLoginDTO.setMsg("无此对应的用户");
			return result;
		}
	}

	public Result orglogin(HttpServletRequest request){
		Result result = new Result();
		SSOLoginDTO ssoLoginDTO = new SSOLoginDTO();
		result.setContent(ssoLoginDTO);
		UserDataDTO userDataDTO = SSOClientSession.getInstance().getUser();
		if(userDataDTO == null){
			result.setSuccess(false);
			ssoLoginDTO.setMsg("无此对应的用户");
			return result;
		}else{
			String hql ="select yy from YhglYwYh y,YhglYwYhyy yy,XtglDmYy xt " +
                    " where yy.yhid = y.guid and yy.yyid = xt.guid " +
                    " and xt.zt ='1'   and   y.zt='1'  and   yy.zt ='1'" +
                    " and xt.bm ='"+ ConfigureParser.getPropert("YYBM")+"' " +
                    " and   yy.yhzh ='"+userDataDTO.getUserName()+"'  " ;
            List list=baseDao.selectByHql(hql);
            YhglYwYhyy userinfo = (YhglYwYhyy) list.get(0);
            if(userinfo!=null){
            	request.getSession().setAttribute(StaticData.LOGINUSER, userinfo);
            	setUserMenu(request.getSession(),userinfo);
            	result.setSuccess(true);
				String redirecttokey = ConfigureParser.getPropert("sso.mode.redirectto.key");
				if (!StringUtil.isblank(redirecttokey.trim())){
					String redirectto = request.getParameter(redirecttokey);
					if (!StringUtil.isblank(redirectto)){
						try {
							ssoLoginDTO.setRedirect_to(URLDecoder.decode(redirectto, "UTF-8"));
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				ssoLoginDTO.setMsg("登录成功");
				return result;
            }else{
            	result.setSuccess(false);
    			ssoLoginDTO.setMsg("无此对应的用户");
    			return result;
            }
		}
	}

	public List getPostTree() {
		String sql = "select o.mc as name, o.guid as id, 0 as pId from YHGL_DM_YYGW o where o.ssyybm='"+ConfigureParser.getPropert("CDYYBM")+"' and o.zt!='2'";
        return baseDao.selectMapsBySQL(sql);
	}

	public List getMenuTree() {
		 String sql = "select  o.menuname as name, o.menuid as id, parentid as pId from XTGL_GG_MENU o where  o.yybm='"+ConfigureParser.getPropert("CDYYBM")+"' order by o.ordernum, o.menuid";
	     return baseDao.selectMapsBySQL(sql);
	}

	public void getPostMenu(String postId, Result result) {
		String sql = "select menyid from XTGL_YYGW_TOMENU  m where m.GWID = " + postId +"  ";
		List list = baseDao.selectMapsBySQL(sql);
		StringBuffer menus = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			Map map=(Map)list.get(i);
			menus.append(map.get("MENYID"));
			menus.append(",");
		}
		result.setSuccess(true);
		result.setContent(menus);

	}

	public void savePostMenu(String postId, String menus, Result result) {
		String delSql = "delete from XTGL_YYGW_TOMENU t where t.gwid = '" + postId + "'";
		baseDao.executeBySql(delSql);

		String[] menuIds = menus.split(",");
		for (String str : menuIds) {
			XtglYygwTomenu xtglyygwtomenu = new XtglYygwTomenu();
			xtglyygwtomenu.setGwid(postId);;
			xtglyygwtomenu.setMenyid(str);;
			baseDao.save(xtglyygwtomenu);
		}
		result.setSuccess(true);
		result.setContent("保存成功");

	}

	public String getUserName(HttpSession session){
		YhglYwYhyy userinfo = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
		String hql = " from YhglYwYh where guid = "+userinfo.getYhid();
		List list0 = baseDao.selectByHql(hql);
		YhglYwYh user = (YhglYwYh) list0.get(0);
		return user.getXm();
	}


	public Pages getMenuInformation(Pages page) {
		String sql = "select new map(t.menuid as MENUID,t.menuname as MENUNAME,t.menutype as MENUTYPE,t.menuurl as 	MENUURL,t.menuicon as MENUICON,t.enable as ENABLE,t.parentid as PARENTID) from XtglGgMenu t where 1=1 and yybm='"+ConfigureParser.getPropert("CDYYBM")+"'";
		String countsql = "select count(1) from XTGL_GG_MENU t where 1=1 and yybm='"+ConfigureParser.getPropert("YYBM")+"'";
		List list= baseDao.selectByHql(sql,null,page.getOffset(),page.getLimit());
		List listcount=baseDao.selectBySql(countsql);
		if(list.size()>0){
			page.setRows(list);
			page.setTotal(Integer.parseInt(listcount.get(0).toString()));
		}
		return page;
	}

	public Result saveMenu(XtglGgMenu menu, Result result, int stuts) {
		if(stuts==-1){
			menu.setYybm(ConfigureParser.getPropert("CDYYBM"));
			menu.setIsdel("2");
			menu.setMenutype("1");
			baseDao.save(menu);
			if(menu.getParentid()==null){
				menu.setParentid(menu.getMenuid());
				baseDao.update(menu);
			}
			result.setSuccess(true);
			result.setContent("保存成功");
		}else{
			XtglGgMenu menu2=(XtglGgMenu)baseDao.load(XtglGgMenu.class, menu.getMenuid());
			menu2.setMenuname(menu.getMenuname());
			menu2.setEnable(menu.getEnable());
			menu2.setMenuurl(menu.getMenuurl());
			menu2.setParentid(menu.getParentid());
			baseDao.update(menu2);
			result.setSuccess(true);
			result.setContent("修改成功");
		}
		return result;
	}

	public Result delMenu(String menuid, Result result) {
		XtglGgMenu menu=(XtglGgMenu)baseDao.load(XtglGgMenu.class, Long.parseLong(menuid));
		if("2".equals(menu.getIsdel())){
			baseDao.executeBySql("delete XTGL_GG_MENU where menuid="+menuid);
			result.setSuccess(true);
			result.setContent("删除成功");

		}else{
			result.setSuccess(false);
			result.setContent("该菜单为系统菜单不允许删除！");

		}
		return result;
	}
	@Override
	public List getGkTree() {
		String sql = "select  o.mc as name, o.deptid as id, 0 as pId,1 as isleaf from v_zftz_division o where  1=1 ";
	    return baseDao.selectMapsBySQL(sql);
	}
	@Override
	public void getPostGk(String postId, Result result) {
		String sql = "select gkid from zftz_gwtogk  m where m.gwid = " + postId +"  ";
		List list = baseDao.selectMapsBySQL(sql);
		StringBuffer menus = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			Map map=(Map)list.get(i);
			menus.append(map.get("GKID"));
			menus.append(",");
		}
		result.setSuccess(true);
		result.setContent(menus);

	}
	@Override
	public void savePostToGk(String postId, String gks, Result result) {
		String delSql = "delete from zftz_gwtogk t where t.gwid = '" + postId + "'";
		baseDao.executeBySql(delSql);

		String[] gksIds = gks.split(",");
		for (String str : gksIds) {
			ZftzGwtogk zftzgwtogk = new ZftzGwtogk();
			zftzgwtogk.setGwid(Long.parseLong(postId));
			zftzgwtogk.setGkid(Long.parseLong(str));
			baseDao.save(zftzgwtogk);
		}
		result.setSuccess(true);
		result.setContent("保存成功");

	}

	@Override
	public List getUserDaiban(HttpServletRequest request,HttpSession session) {
		List list = new ArrayList();
		YhglYwYhyy userinfo = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
		String btnstr = ""+ request.getSession().getAttribute(StaticData.USERBUTTONSSTR);
		String menustr = ""+ request.getSession().getAttribute(StaticData.USERMENUSSTR);

        Map map ;
//		if(menustr.indexOf(",70021,")>-1){
//			map = new HashMap();
//			map.put("id",70021);
//			map.put("url","base/xmqqch.jsp");
//			map.put("name","项目前期策划申请");
//			String sql = "from zftz_xmqqch x1 " +
//	                "left join zftz_xm x2 on  x1.id_zftz_xm=x2.id " +
//	                "left join yhgl_yw_yhyy y1 on x1.czr=to_char(y1.guid) " +
//	                "left join yhgl_yw_yh y2 on y1.yhid=y2.guid "+
//	                " where x1.sldh is not null and x1.zt=1 and x2.jsdw='"+userinfo.getSzdwid()+"' and x1.lchj='-1'";
//			Long count = baseDao.getCountBySQL("select count(*) "+sql,null);
//			map.put("count",count);
//			list.add(map);
//		}
		if(menustr.indexOf(",70022,")>-1){
            map = new HashMap();
			map.put("id",70022);
			map.put("url","base/xmqqsh.jsp");
            map.put("name","项目前期策划审核");
            String sql = "from zftz_xmqqch x1 " +
                    "left join zftz_xm x2 on  x1.id_zftz_xm=x2.id " +
                    "left join yhgl_yw_yhyy y1 on x1.czr=to_char(y1.guid) " +
                    "left join yhgl_yw_yh y2 on y1.yhid=y2.guid "+
                    " where x1.sldh is not null and x1.zt=1 ";
            sql =sql+" and x1.lchj not in(0,-1) and x1.id in (select ywid from zftz_gg_ryqzyh where yhid='"+userinfo.getGuid()+"' and sxlx='1')";
            Long count = baseDao.getCountBySQL("select count(*) " + sql,null);
            map.put("count",count);
            list.add(map);
		}

		if(menustr.indexOf(",70024,")>-1){
			map = new HashMap();
			map.put("id",70024);
			map.put("url","");
			map.put("name","项目概算编制");
			Long count = baseDao.getCountBySQL("select 1 from dual ", null);
			map.put("count",count);
			list.add(map);
		}
		if(menustr.indexOf(",70025,")>-1){
			map = new HashMap();
			map.put("id",70025);
			map.put("url","");
			map.put("name","项目概算审核");
			Long count = baseDao.getCountBySQL("select 1 from dual ",null);
			map.put("count",count);
			list.add(map);
		}
		if(menustr.indexOf(",70026,")>-1){
			map = new HashMap();
			map.put("id",70026);
			map.put("url","pro/xmqqchgl/Xmgsyjfk.jsp");
			map.put("name","项目概算意见反馈");
			String sql = "(select e.id yjid,\n" +
					"       a.ID mc,\n" +
					"       (select b.xmbh\n" +
					"          from ZFTZ_XMTZWH b\n" +
					"         where b.id_zftz_xm = e.id_zftz_xm\n" +
					"           and b.zt = 1) as xmbh,\n" +
					"       a.XMMC,\n" +
					"       a.ZGBM,\n" +
					"       a.XMLX,\n" +
					"       a.JSDW,\n" +
					"       e.ID xmgsyjid,\n" +
					"       e.jsnr,\n" +
					"       e.fkyj,\n" +
					"       e.lchj,\n" +
					"       (select c.ydmj\n" +
					"          from ZFTZ_XMZJLYDJ c\n" +
					"         where c.id_zftz_xm = e.id_zftz_xm\n" +
					"           and c.zt = 1) as ydmj,\n" +
					"       (select c.tzxe / 10000\n" +
					"          from ZFTZ_XMZJLYDJ c\n" +
					"         where c.id_zftz_xm = e.id_zftz_xm\n" +
					"           and c.zt = 1) as tzxe,\n" +
					"       (select (nvl(c.ztz_jzaztz, 0) + nvl(c.ztz_sbtz, 0) +\n" +
					"               nvl(c.ztz_dttz, 0) + nvl(c.ztz_qttz, 0)) / 10000\n" +
					"          from ZFTZ_XMZJLYDJ c\n" +
					"         where c.id_zftz_xm = e.id_zftz_xm\n" +
					"           and c.zt = 1) as xmztz,\n" +
					"       (select c.xmghxz\n" +
					"          from ZFTZ_XMZJLYDJ c\n" +
					"         where c.id_zftz_xm = e.id_zftz_xm\n" +
					"           and c.zt = 1) as xmghxz,\n" +
					"       (select c.id\n" +
					"          from ZFTZ_XMZJLYDJ c\n" +
					"         where c.id_zftz_xm = e.id_zftz_xm\n" +
					"           and c.zt = 1) as zjlydjid,\n" +
					"       (select d.id as qqchid\n" +
					"          from zftz_xmqqch d\n" +
					"         where d.id_zftz_xm = e.id_zftz_xm\n" +
					"           and d.zt = 1) as qqchid\n" +
					"  from zftz_xmgsyjzx e\n" +
					"  left join ZFTZ_XM a\n" +
					"    on e.id_zftz_xm = a.id\n" +
					" where e.zt = 1\n" +
					"   and (a.jsdw = 56151224 or\n" +
					"       e.id in (select T.YWID\n" +
					"                   from zftz_gg_ryqzyh t\n" +
					"                  where t.yhid = 14330\n" +
					"                    and t.sxlx = 2\n" +
					"                    and t.ssyybm = 'ZFTZ'))\n" +
					"   and e.lchj not in (0, -1)\n" +
					" order by e.czsj)\n";
			Long count = baseDao.getCountBySQL("select count(*) from " + sql, null);
			map.put("count",count);
			list.add(map);
		}
		if(menustr.indexOf(",70027,")>-1){
			map = new HashMap();
			map.put("id",70027);
			map.put("url","");
			map.put("name","项目概算调整审核");
			Long count = baseDao.getCountBySQL("select 1 from dual ",null);
			map.put("count",0);
			list.add(map);
		}

		if(menustr.indexOf(",72001,")>-1){
			map = new HashMap();
			map.put("id",72001);
			map.put("url","xmgcjjs/xmgcjsbb.html");
			map.put("name","项目工程结算报备");
			Long count = baseDao.getCountBySQL("select count(*)\n" +
					"from ZFTZ_GG_RYQZYH t1,\n" +
					"     ZFTZ_XMGCJSBA t2\n" +
					"where t2.ID = t1.YWID\n" +
					"  and t2.LCHJ <> '-1'\n" +
					"  and t2.LCHJ <> '0'\n" +
					"  and t2.ZT = 1\n" +
					"  and YHID = '"+userinfo.getGuid()+"'\n" +
					"  and SXLX = '9'",null);
			map.put("count",count);
			list.add(map);
		}

		if(menustr.indexOf(",72003,")>-1){
			map = new HashMap();
			map.put("id",72003);
			map.put("url","xmgcjjs/xmgcjspfdj.html");
			map.put("name","项目工程决算批复登记");
			Long count = baseDao.getCountBySQL("select count(*)\n" +
                    "from ZFTZ_GG_RYQZYH t1,\n" +
                    "     ZFTZ_XMJGJS t2\n" +
                    "where t2.ID = t1.YWID\n" +
                    "  and t2.LCHJ <> '-1'\n" +
                    "  and t2.LCHJ <> '0'\n" +
                    "  and t2.ZT = 1\n" +
                    "  and YHID = '"+userinfo.getGuid()+"'\n" +
                    "  and SXLX = '11'",null);
			map.put("count",count);
			list.add(map);
		}

		return list;


	}
	@Override
	public Object getButtons(HttpSession session) {
		YhglYwYh yhglYwYh = (YhglYwYh) session.getAttribute(StaticData.LOGINUSERINFO);

		Map<Object,Object> map = new HashMap<Object,Object>();
		String buttons = String.valueOf(session.getAttribute(StaticData.USERBUTTONSSTR));
		map.put("buttons", buttons);
		map.put("yhlx", yhglYwYh.getYhlx());
		return map;
	}
	

/*	@Override
	public void savepicture(String guid ,  String picinfo) {


	}*/


}
