package com.datanew.service.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.datanew.model.YhglYwYh;
import com.datanew.model.YhglYwYhyy;
import com.datanew.dto.Result;
import org.eclipse.jdt.core.Flags;
import org.springframework.stereotype.Service;

import com.datanew.dao.BaseDao;
import com.datanew.util.ConfigureParser;
import com.datanew.util.StaticData;
import com.datanew.util.StringUtil;
import com.datanew.service.XmzjglService;

@Service("xmzjglService")
public class XmzjglServiceImpl implements XmzjglService {

    @Resource
    private BaseDao baseDao;

    @Override
    public List getNdjhglInfo(String nd,String xmmc,String jszt,HttpSession session) {
        try {
            YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
            Long userid = yhglywyhyy.getGuid();
//            xmmc = new String(xmmc.getBytes("ISO8859-1"), "UTF-8");

          /*  String sql = "select t1.szdwid from yhgl_yw_yhyy t1 where t1.guid = ?";
            List param1 = new ArrayList();
            param1.add(userid);
            List list1 = baseDao.selectMapsBySQL(sql, param1);
            Map map1 = (Map) list1.get(0);
            String yhszdw = map1.get("SZDWID").toString();*/

           /* String sql1 = "select t3.id sid,t1.id,t1.xmmc,t3.nd,round(nvl(t3.ndztz,0)/10000,0) ndztz,"
                    + "(select round(nvl(t2.gsztz,t2.ztz_zjlydj)/10000,2) xmztz from v_zftz_xmjbxx t2 where t2.id_zftz_xm =t1.id) xmztz,"
                    + " round((nvl(t3.yyggys,0)+nvl(t3.phzy,0)+nvl(t3.phzc,0)+nvl(t3.qxfd,0)+nvl(t3.pppshzb,0)+nvl(t3.qt,0))/10000,2) ZJLY1,"
                    + " round(nvl(t3.yyggys,0)/10000,2) ZJLY2,round(nvl(t3.phzy,0)/10000,2) ZJLY3,round(nvl(t3.phzc,0)/10000,2) ZJLY4,round(nvl(t3.qxfd,0)/10000,2) ZJLY5,round(nvl(t3.pppshzb,0)/10000,2) ZJLY6,round(nvl(t3.qt,0)/10000,2) ZJLY7"
                    + " from ZFTZ_XM t1,ZFTZ_NDJHGL T3 where "
                    + " t3.id_ZFTZ_XM = t1.id and t1.zt = 1 and (sffb = 0 or sffb is null) ";*/
            String sql1 = "select t3.id sid,\n" +
                    "       t1.jsdw,\n" +
                    "       w.bm DWBM,\n" +
                    "       w.mc DWMC,\n" +
                    "       t1.id,\n" +
                    "       t1.xmmc,\n" +
                    "       t3.nd,\n" +
                    "       round(nvl(t3.ndztz, 0) / 10000, 0) ndztz,\n" +
                    "       (select round(nvl(t2.gsztz, t2.ztz_zjlydj) / 10000, 2) xmztz\n" +
                    "          from v_zftz_xmjbxx t2\n" +
                    "         where t2.id_zftz_xm = t1.id) xmztz,\n" +
                    "       round((nvl(t3.yyggys, 0) + nvl(t3.phzy, 0) + nvl(t3.phzc, 0) +\n" +
                    "             nvl(t3.qxfd, 0) + nvl(t3.pppshzb, 0) + nvl(t3.qt, 0)) / 10000,\n" +
                    "             2) ZJLY1,\n" +
                    "       round(nvl(t3.yyggys, 0) / 10000, 2) ZJLY2,\n" +
                    "       round(nvl(t3.phzy, 0) / 10000, 2) ZJLY3,\n" +
                    "       round(nvl(t3.phzc, 0) / 10000, 2) ZJLY4,\n" +
                    "       round(nvl(t3.qxfd, 0) / 10000, 2) ZJLY5,\n" +
                    "       round(nvl(t3.pppshzb, 0) / 10000, 2) ZJLY6,\n" +
                    "       round(nvl(t3.qt, 0) / 10000, 2) ZJLY7\n" +
                    "  from ZFTZ_XM t1\n" +
                    "  left join v_zftz_jsdw w\n" +
                    "    on t1.jsdw = w.id, ZFTZ_NDJHGL T3\n" +
                    " where t3.id_ZFTZ_XM = t1.id\n" +
                    "   and t1.zt = 1\n" +
                    "   and (sffb = 0 or sffb is null)";
//                    + " and t1.id not in (select t.id_zftz_xm from zftz_xmjgjs t where t.zt = '1' and t.jsdw = ?)";
            List param = new ArrayList();
//            param.add(yhszdw);
//            param.add(yhszdw);

            if (!nd.equals("")) {
//                sql1 = sql1 + " and t3.nd="+nd;
                sql1 = sql1 + " and t3.nd=?";
                param.add(nd);
            }
            if (!xmmc.equals("")) {
//                sql1 = sql1 + " and t1.xmmc="+xmmc;
                sql1 = sql1 + " and t1.xmmc like '%"+xmmc+"%'";
//                param.add(xmmc);
            }
            if (jszt.equals("2")) {
//                sql1 = sql1 + " and t1.xmmc="+xmmc;
//                sql1 = sql1 + " and t1.id in (select t.id_zftz_xm from zftz_xmjgjs t where t.zt = '1' and t.jsdw = ?)";
                sql1 = sql1 + " and t1.id in (select t.id_zftz_xm from zftz_xmjgjs t where t.zt = '1')";
//                param.add(yhszdw);
            }else if(jszt.equals("1")) {
//                sql1 = sql1 + " and t1.id not in (select t.id_zftz_xm from zftz_xmjgjs t where t.zt = '1' and t.jsdw = ?)";
                sql1 = sql1 + " and t1.id not in (select t.id_zftz_xm from zftz_xmjgjs t where t.zt = '1')";
//                param.add(yhszdw);
            }

            sql1 = sql1 + " order by t1.xmmc";

            List list = baseDao.selectMapsBySQL(sql1, param);
            return list;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List getXmmc(String jszt,HttpSession session) {

        if (jszt == null){
            jszt = "";
        }
//        String sql1="select t1.id,t1.xmmc from ZFTZ_XM t1 where t1.zt = '1' and (sffb = 0 or sffb is null)  \n";
            List dataList = new ArrayList();

            YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
            Long userid = yhglywyhyy.getGuid();
            StringBuffer sb = new StringBuffer();
            sb.append("select t.xmmc as id,\n" +
                    "       t.xmmc as name,\n" +
                    "       'jsdw' || t.jsdw as pid,\n" +
                    "       t.id as type,"+
                    "       1 as isleaf\n" +
                    "  from ZFTZ_XM t\n" +
                    " where t.zt = 1\n" +
                    "   and t.jsdw is not null\n" +
                    "    and (t.sffb = 0 or t.sffb is null)");
            if (jszt.equals("2")) {
                sb.append("and t.id in\n" +
                        "       (select to_char(s.id_zftz_xm) from zftz_xmjgjs s where s.zt = 1)");
//                sql1 = sql1 + " and t1.id in (select t.id_zftz_xm from zftz_xmjgjs t where t.zt = '1')";
            }else {
                sb.append("and t.id not in\n" +
                        "       (select to_char(s.id_zftz_xm) from zftz_xmjgjs s where s.zt = 1)");
//                sql1 = sql1 + " and t1.id not in (select t.id_zftz_xm from zftz_xmjgjs t where t.zt = '1')";
            }
            sb.append("union all\n" +
                    "select 'jsdw' || w.id as id, w.mc as name, to_char(0) as pid,w.id as type, 0 as isleaf\n" +
                    "  from v_zftz_jsdw w\n" +
                    " where w.id in (select x.jsdw\n" +
                    "                  from zftz_xm x\n" +
                    "                 where x.zt = '1'\n" +
                    "                   and x.jsdw is not null\n" +
                    "                    and (x.sffb = 0 or x.sffb is null)");

            if (jszt.equals("2")) {
                sb.append("and x.id in (select to_char(s.id_zftz_xm)\n" +
                        "                                      from zftz_xmjgjs s\n" +
                        "                                     where s.zt = 1))");
            }else {
                sb.append("and x.id not in (select to_char(s.id_zftz_xm)\n" +
                        "                                      from zftz_xmjgjs s\n" +
                        "                                     where s.zt = 1))");
            }
//            List<Map> list = baseDao.selectMapsBySQL(sb.toString());

         /*   for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                String id = map.get("ID").toString();
                String name = map.get("XMMC").toString();
                map.clear();
                map.put("id", name);
                map.put("name", name);
                map.put("pId","0");
                map.put("type",id);
                dataList.add(map);
//                list2.add(map);
//            map.put("",);
            }*/

            List list = baseDao.selectBySql(sb.toString());//得到List集合
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    Map map = new HashMap();
                    map.put("id", obj[0]);
                    map.put("name", obj[1]);
                    map.put("pId", obj[2]);
                    map.put("type",obj[3]);
                    dataList.add(map);
                }
            }
            return dataList;
    }

    @Override
    public String getXmztz(String xmid){
        String sql1 = "select round(nvl(t2.gsztz,t2.ztz_zjlydj)/10000,2) xmztz from v_zftz_xmjbxx t2 where t2.id_zftz_xm = "+xmid;
        try {
            List list = baseDao.selectMapsBySQL(sql1);

            Map map = (Map) list.get(0);
            String xmztz = map.get("XMZTZ").toString();

            return xmztz;
        }catch (Exception e){
            return "";
        }
    }

    @Override
    public void updateNdjhglInfo(String sdate, HttpSession session, Result result) {
        try {
            YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
            Long userid = yhglywyhyy.getGuid();
            List param = new ArrayList();
            List param1 = new ArrayList();
            List list = new ArrayList();

            JSONArray jsonArray1 = JSONArray.parseArray(sdate);
            for (int j=0;j<jsonArray1.size();j++) {
                JSONObject jsonObject = (JSONObject) jsonArray1.get(j);
                if (jsonObject.containsKey("SID")){
                    String sid = jsonObject.get("SID").toString();

                    String xmid = jsonObject.get("ID").toString();
                    String nd = jsonObject.get("ND").toString();
                    String sql = "select id from ZFTZ_NDJHGL where id_zftz_xm = ? and nd = ?";
                    List param2 = new ArrayList();
                    param2.add(xmid);
                    param2.add(nd);

                    List list1 = baseDao.selectMapsBySQL(sql,param2);

                    if (list1.size() > 0) {
                        Map map = (Map) list1.get(0);
                        String id = map.get("ID").toString();
                        if (!sid.equals(id)) {
                            result.setSuccess(false);
                            result.setContent("第" + (j + 1) + "行数据重复");
                            return;
                        }
                    }
                }else {
                    String xmid = jsonObject.get("ID").toString();
                    String nd = jsonObject.get("ND").toString();
                    String sql = "select id from ZFTZ_NDJHGL where id_zftz_xm = ? and nd = ?";
                    List param2 = new ArrayList();
                    param2.add(xmid);
                    param2.add(nd);

                    List list1 = baseDao.selectMapsBySQL(sql,param2);
                    if (list1.size()>0){
                        result.setSuccess(false);
                        result.setContent("第"+(j+1)+"行数据重复");
                        return;
                    }
                }
            }

//            JSONObject jsonObject = JSON.parseObject(sdate);
            JSONArray jsonArray = JSONArray.parseArray(sdate);


            for (int j=0;j<jsonArray.size();j++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(j);
//                Boolean b = jsonObject.getBoolean("ID");
                String sid = "";
//                String xmid = "";
                if (jsonObject.containsKey("SID")){
                    sid = jsonObject.get("SID").toString();
                }
                String xmid = jsonObject.get("ID").toString();
                String xmmc = jsonObject.get("XMMC").toString();
                String nd = jsonObject.get("ND").toString();
                if (("").equals(nd)){
                    continue;
                }
                String ndztz = ("Na").equals(jsonObject.get("NDZTZ").toString())?"":jsonObject.get("NDZTZ").toString();
                String yyggys = ("Na").equals(jsonObject.get("ZJLY2").toString())?"":jsonObject.get("ZJLY2").toString();
                String phzy = ("Na").equals(jsonObject.get("ZJLY3").toString())?"":jsonObject.get("ZJLY3").toString();
                String phzc = ("Na").equals(jsonObject.get("ZJLY4").toString())?"":jsonObject.get("ZJLY4").toString();
                String qxfd = ("Na").equals(jsonObject.get("ZJLY5").toString())?"":jsonObject.get("ZJLY5").toString();
                String pppshzb = ("Na").equals(jsonObject.get("ZJLY6").toString())?"":jsonObject.get("ZJLY6").toString();
                String qt = ("Na").equals(jsonObject.get("ZJLY7").toString())?"":jsonObject.get("ZJLY7").toString();

                if (!sid.equals("")){
                    //原本就存在的数据,进行更新
                    String sql = "select id from ZFTZ_NDJHGL where id_zftz_xm = ? and nd = ?";
                    List param2 = new ArrayList();
                    param2.add(xmid);
                    param2.add(nd);
                    List list2 = baseDao.selectMapsBySQL(sql,param2);
                    String oid;
                    //1.存在id，是对已存在数据的修改
                        // 判断当前项目当前年份已存在
                        // 1.本条数据修改了项目或年份  --删除本条数据对应的表中数据，然后更新已存在的数据
                        //2.本条数据修改了项目和年份但未与数据库中已有数据冲突  --直接更新本条数据到数据库
                        //3.本条数据未修改项目和年份  --更新本条数据到数据库
                    if (list2.size() !=0) {
                        Map map = (Map) list2.get(0);
                        oid = map.get("ID").toString();
                    }else {
                        oid = "";
                    }
                    if (list2.size() == 1&&!sid.equals(oid)) {
                        sql = "delete from ZFTZ_NDJHGL where id = " + sid;
                        baseDao.executeBySql(sql);

                        sql = "update ZFTZ_NDJHGL \n" +
                                "set yyggys = ?*10000,phzy = ?*10000,phzc = ?*10000,qxfd = ?*10000,pppshzb = ?*10000,qt = ?*10000,czsj=sysdate,czr=?,ndztz = ?*10000 \n" +
                                " where id_zftz_xm = ? , nd = ? ";

                        param2.clear();
                        param2.add(yyggys);
                        param2.add(phzy);
                        param2.add(phzc);
                        param2.add(qxfd);
                        param2.add(pppshzb);
                        param2.add(qt);
                        param2.add(userid);
                        param2.add(ndztz);
                        param2.add(xmid);
                        param2.add(nd);
                        baseDao.executeBySql(sql,param2);
                    }else {

                        String sql1 = "update ZFTZ_NDJHGL \n" +
                                "set yyggys = ?*10000,phzy = ?*10000,phzc = ?*10000,qxfd = ?*10000,pppshzb = ?*10000,qt = ?*10000,czsj=sysdate,czr=?, \n" +
                                " id_zftz_xm = ? , nd = ?,ndztz = ?*10000  where id = ?";
                        param1.clear();
                        param1.add(yyggys);
                        param1.add(phzy);
                        param1.add(phzc);
                        param1.add(qxfd);
                        param1.add(pppshzb);
                        param1.add(qt);
                        param1.add(userid);
                        param1.add(xmid);
                        param1.add(nd);
                        param1.add(ndztz);
                        param1.add(sid);

                        baseDao.executeBySql(sql1, param1);
                    }
                }else {
                    String sql1="select * from ZFTZ_NDJHGL WHERE id_zftz_xm = ? and nd = ? ";
                    param.clear();
                    param.add(xmid);
                    param.add(nd);
                    list = baseDao.selectMapsBySQL(sql1, param);
                    if (list.size() == 0) {
                        //当前年度下，项目不存在，新增
                        sql1 = "insert into ZFTZ_NDJHGL\n" +
                                "values(SEQ_ZFTZ_NDJHGL.nextval,?,?,?*10000,?*10000,?*10000,?*10000,?*10000,?*10000,?*10000,sysdate,?)";
                        param1.clear();
                        param1.add(xmid);
                        param1.add(nd);
                        param1.add(ndztz);
                        param1.add(yyggys);
                        param1.add(phzy);
                        param1.add(phzc);
                        param1.add(qxfd);
                        param1.add(pppshzb);
                        param1.add(qt);
                        param1.add(userid);

                        baseDao.executeBySql(sql1, param1);
                    } else {
                        result.setSuccess(false);
                        result.setContent("第"+(j+1)+"行数据重复");
                        return;
                    }
//                        //当前年度下，项目存在，更新 原数据加现数据
//                        sql1 = "update ZFTZ_NDJHGL \n" +
//                                "set yyggys = yyggys + ?,phzy = phzy +?,phzc = phzc +?,qxfd = phzc+?,pppshzb = phzc+?,qt = phzc +?,czsj=sysdate,czr=? \n" +
//                                "where id_zftz_xm = ? and nd = ? ";
//                        param1.clear();
//                        param1.add(yyggys);
//                        param1.add(phzy);
//                        param1.add(phzc);
//                        param1.add(qxfd);
//                        param1.add(pppshzb);
//                        param1.add(qt);
//                        param1.add(userid);
//                        param1.add(xmid);
//                        param1.add(nd);
//
//                        baseDao.executeBySql(sql1, param1);
//                    }
                }
            }
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
        }
    }

    @Override
    public void deleteNdjhglInfo(String sdate,Result result) {
        try {
            JSONArray jsonArray1 = JSONArray.parseArray(sdate);
            for (int j=0;j<jsonArray1.size();j++) {
                JSONObject jsonObject = (JSONObject) jsonArray1.get(j);
                if (jsonObject.containsKey("SID")) {
                    String sql1 = "delete from ZFTZ_NDJHGL where id = ? ";
                    String sid = jsonObject.get("SID").toString();

                    List param = new ArrayList();
                    param.add(sid);
                    baseDao.executeBySql(sql1,param);
                }

            }
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
        }
    }

    @Override
    public void insertExcel(List<String[]>  list, HttpSession session,Map<String,Object> resultMap) {
        YhglYwYhyy userinfo = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
        Long userid = userinfo.getGuid();
        String sql1;
        List param = new ArrayList();
        List param1 = new ArrayList();

        Object[] ss = list.get(1);
        String title1 = "".equals(ss[2].toString())?null:ss[2].toString();   //项目编码
        String title2 = "".equals(ss[3].toString())?null:ss[3].toString();   //项目名称
        String title3 = "".equals(ss[4].toString())?null:ss[4].toString();    //总投资(不用存到数据库)
        String title4 = "".equals(ss[5].toString())?null:ss[5].toString();    //年度
        String title5 = "".equals(ss[6].toString())?null:ss[6].toString();    //年度总投资
        if (!( ("项目编码").equals(title1)&&("项目名称").equals(title2)&&("总投资").equals(title3)&&("年度").equals(title4)&&("年度总投资").equals(title5))){
            resultMap.put("success",false);
            resultMap.put("error","上传失败");
            return;
        }

        for(int i=3;i<list.size();i++) {
            ss = list.get(i);
            String xmid = "".equals(ss[2].toString())?null:ss[2].toString();   //项目代码
            String nd = "".equals(ss[5].toString())?null:ss[5].toString();    //年度
            String sql = "select * from ZFTZ_NDJHGL where id_ZFTZ_XM = ? and nd = ?";
            param.clear();
            param.add(xmid);
            param.add(nd);
            List list1 = baseDao.selectMapsBySQL(sql,param);

            if (list1.size()>0){
                resultMap.put("success",false);
                resultMap.put("error","数据重复");
                return;
            }
        }

        for (int i = 3; i < list.size(); i++) {
            ss = list.get(i);
            String xmid = "".equals(ss[2].toString())?null:ss[2].toString();   //项目代码
            String sql = " select count(*) a from zftz_xm where id = ? and zt = 1";
            param.clear();
            param.add(xmid);
            List list1 = baseDao.selectMapsBySQL(sql,param);
            if (list1.size() > 0) {
                String a = ((Map) list1.get(0)).get("A").toString();
                if ("0".equals(a)) {
                    resultMap.put("success",false);
                    resultMap.put("error","第"+(i-2)+"数据中没有该项目");
                    return;
                }
            }
        }
        for(int i=3;i<list.size();i++) {
            ss = list.get(i);

            String xmid = "".equals(ss[2].toString())?null:ss[2].toString();   //项目代码
            String xmmc = "".equals(ss[3].toString())?null:ss[3].toString();   //项目名称
            String ztz = "".equals(ss[4].toString())?null:ss[4].toString();    //总投资(不用存到数据库)
            String nd = "".equals(ss[5].toString())?null:ss[5].toString();    //年度
            String ndztz = "".equals(ss[6].toString())?null:ss[6].toString();    //年度总投资
            String hj = "".equals(ss[7].toString())?null:ss[7].toString();    //合计(不用存到数据库)
            String yyggys = "".equals(ss[8].toString())?null:ss[8].toString();    //一般公共预算
            String phzy = "".equals(ss[9].toString())?null:ss[9].toString();    //盘活资源/政府性基金
            String phzc = "".equals(ss[10].toString())?null:ss[10].toString();    //盘活资产
            String qxfd = "".equals(ss[11].toString())?null:ss[11].toString();    //区县分担
            String pppshzb = "".equals(ss[12].toString())?null:ss[12].toString();    //PPP社会资本
            String qt = "".equals(ss[13].toString())?null:ss[13].toString();    //其他(自筹)

            String sql = "select * from ZFTZ_NDJHGL where id_ZFTZ_XM = ? and nd = ?";
            param.clear();
            param.add(xmid);
            param.add(nd);
            List list1 = baseDao.selectMapsBySQL(sql,param);
            if (list1.size()>0){
                resultMap.put("success",false);
                resultMap.put("error","数据重复");
                return;
//                sql1 = "update ZFTZ_NDJHGL \n" +
//                        "set yyggys = yyggys + ?,phzy = phzy +?,phzc = phzc +?,qxfd = phzc+?,pppshzb = phzc+?,qt = phzc +?,czsj=sysdate,czr=? \n" +
//                        "where id_zftz_xm = ? and nd = ? ";
//                param1.clear();
//                param1.add(yyggys);
//                param1.add(phzy);
//                param1.add(phzc);
//                param1.add(qxfd);
//                param1.add(pppshzb);
//                param1.add(qt);
//                param1.add(userid);
//                param1.add(xmid);
//                param1.add(nd);
//
//                baseDao.executeBySql(sql1, param1);
            }else {
                sql1 = "insert into ZFTZ_NDJHGL\n" +
                        "values(SEQ_ZFTZ_NDJHGL.nextval,?,?,?*10000,?*10000,?*10000,?*10000,?*10000,?*10000,?*10000,sysdate,?)";
                param1.clear();
                param1.add(xmid);
                param1.add(nd);
                param1.add(ndztz);
                param1.add(yyggys);
                param1.add(phzy);
                param1.add(phzc);
                param1.add(qxfd);
                param1.add(pppshzb);
                param1.add(qt);
                param1.add(userid);

                baseDao.executeBySql(sql1, param1);
            }
        }
    }

    @Override
    public String[][] exportexcel(String nd,HttpSession session){
        YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
        Long userid = yhglywyhyy.getGuid();

        String sql1 = "select t1.szdwid from yhgl_yw_yhyy t1 where t1.guid = ?";
        List param1 = new ArrayList();
        param1.add(userid);
        List list1 = baseDao.selectMapsBySQL(sql1,param1);
        Map map1 = (Map) list1.get(0);
        String yhszdw = map1.get("SZDWID").toString();

       /* String sql="select t3.id sid,t1.id,t1.xmmc,t3.nd,round(nvl(t3.ndztz,0)/10000,2) ndztz,\n" +
                "(select round(nvl(t2.gsztz,t2.ztz_zjlydj)/10000,2) xmztz from v_zftz_xmjbxx t2 where t2.id_zftz_xm =t1.id) xmztz,"+
                " round((nvl(t3.yyggys,0)+nvl(t3.phzy,0)+nvl(t3.phzc,0)+nvl(t3.qxfd,0)+nvl(t3.pppshzb,0)+nvl(t3.qt,0))/10000,2) ZJLY1,\n" +
                " round(nvl(t3.yyggys,0)/10000,2) ZJLY2,round(nvl(t3.phzy,0)/10000,2) ZJLY3,round(nvl(t3.phzc,0)/10000,2) ZJLY4,round(nvl(t3.qxfd,0)/10000,2) ZJLY5,round(nvl(t3.pppshzb,0)/10000,2) ZJLY6,round(nvl(t3.qt,0)/10000,2) ZJLY7\n" +
                " from ZFTZ_XM t1,ZFTZ_NDJHGL T3 where \n" +
                " t3.id_ZFTZ_XM = t1.id and t1.zt = 1 and (sffb = 0 or sffb is null) and t1.jsdw = ? ";*/
        String sql = "select t3.id sid,\n" +
                "       w.bm DWBM,\n" +
                "       w.mc DWMC,\n" +
                "       t1.id,\n" +
                "       t1.xmmc,\n" +
                "       t3.nd,\n" +
                "       round(nvl(t3.ndztz, 0) / 10000, 2) ndztz,\n" +
                "       (select round(nvl(t2.gsztz, t2.ztz_zjlydj) / 10000, 2) xmztz\n" +
                "          from v_zftz_xmjbxx t2\n" +
                "         where t2.id_zftz_xm = t1.id) xmztz,\n" +
                "       round((nvl(t3.yyggys, 0) + nvl(t3.phzy, 0) + nvl(t3.phzc, 0) +\n" +
                "             nvl(t3.qxfd, 0) + nvl(t3.pppshzb, 0) + nvl(t3.qt, 0)) / 10000,\n" +
                "             2) ZJLY1,\n" +
                "       round(nvl(t3.yyggys, 0) / 10000, 2) ZJLY2,\n" +
                "       round(nvl(t3.phzy, 0) / 10000, 2) ZJLY3,\n" +
                "       round(nvl(t3.phzc, 0) / 10000, 2) ZJLY4,\n" +
                "       round(nvl(t3.qxfd, 0) / 10000, 2) ZJLY5,\n" +
                "       round(nvl(t3.pppshzb, 0) / 10000, 2) ZJLY6,\n" +
                "       round(nvl(t3.qt, 0) / 10000, 2) ZJLY7\n" +
                "  from ZFTZ_XM t1 left join v_zftz_jsdw w\n" +
                "    on t1.jsdw = w.id, ZFTZ_NDJHGL T3\n" +
                " where t3.id_ZFTZ_XM = t1.id\n" +
                "   and t1.zt = 1\n" +
                "   and (sffb = 0 or sffb is null)\n" ;
        List param = new ArrayList();
//        param.add(yhszdw);
        if (!nd.equals("")){
//                sql1 = sql1 + " and t3.nd="+nd;
            sql = sql + " and t3.nd=?";
            param.add(nd);
        };


        List<Map> list = baseDao.selectMapsBySQL(sql,param);

        String[][] a1 = new String[list.size()+5][14];

        a1[0][0] = "年度计划管理(万元)";
        a1[1][0] = "单位编码";
        a1[1][1] = "单位名称";
        a1[1][2] = "项目编码";
        a1[1][3] = "项目名称";
        a1[1][4] = "总投资";
        a1[1][5] = "年度";
        a1[1][6] = "年度总投资";
        a1[1][7] = "资金来源";
        a1[2][7] = "合计";
        a1[2][8] = "一般公共预算";
        a1[2][9] = "盘活资源/政府性基金";
        a1[2][10] = "盘活资产";
        a1[2][11] = "区县分担";
        a1[2][12] = "PPP社会资本";
        a1[2][13] = "其他(自筹)";

        for (int j=0;j<list.size();j++) {
            Map map = (Map) list.get(j);
            a1[j + 3][0] = (map.get("DWBM"))==null?"":map.get("DWBM").toString();
            a1[j + 3][1] = (map.get("DWMC"))==null?"":map.get("DWMC").toString();
            a1[j + 3][2] = (map.get("ID"))==null?"":map.get("ID").toString();
            a1[j + 3][3] = (map.get("XMMC"))==null?"":map.get("XMMC").toString();
            a1[j + 3][4] = (map.get("XMZTZ"))==null?"":map.get("XMZTZ").toString();
            a1[j + 3][5] = (map.get("ND"))==null?"":map.get("ND").toString();
            a1[j + 3][6] = (map.get("NDZTZ"))==null?"":map.get("NDZTZ").toString();
            a1[j + 3][7] = (map.get("ZJLY1"))==null?"":map.get("ZJLY1").toString();
            a1[j + 3][8] = (map.get("ZJLY2"))==null?"":map.get("ZJLY2").toString();
            a1[j + 3][9] = (map.get("ZJLY3"))==null?"":map.get("ZJLY3").toString();
            a1[j + 3][10] = (map.get("ZJLY4"))==null?"":map.get("ZJLY4").toString();
            a1[j + 3][11] = (map.get("ZJLY5"))==null?"":map.get("ZJLY5").toString();
            a1[j + 3][12] = (map.get("ZJLY6"))==null?"":map.get("ZJLY6").toString();
            a1[j + 3][13] = (map.get("ZJLY7"))==null?"":map.get("ZJLY7").toString();
        }

        return a1;
    }

    @Override
    public List getXmglInfo(String nd,String xmmc,HttpSession session){
        try{
            YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
            Long userid = yhglywyhyy.getGuid();
//            xmmc = new String(xmmc.getBytes("ISO8859-1"), "UTF-8");

            String sql1 = "select t1.szdwid from yhgl_yw_yhyy t1 where t1.guid = ?";
            List param1 = new ArrayList();
            param1.add(userid);
            List list1 = baseDao.selectMapsBySQL(sql1,param1);
            Map map = (Map) list1.get(0);
            String yhszdw = map.get("SZDWID").toString();

            String sql = "select a.id,a.xmmc,(select round(nvl(t4.gsztz,t4.ztz_zjlydj)/10000,2) xmztz from v_zftz_xmjbxx t4 where t4.id_zftz_xm =a.id) xmztz,round(nvl(b.YSAP1,0)/10000,2) YSAP1,round(nvl(b.YSAP2,0)/10000,2) YSAP2,round(nvl(b.YSAP3,0)/10000,2) YSAP3,\n" +
                    "round(nvl(b.ZJDW1,0)/10000,2) ZJDW1,round(nvl(b.ZJDW2,0)/10000,2) ZJDW2,round(nvl(b.ZJDW3,0)/10000,2) ZJDW3,round(nvl(b.YKJH1,0)/10000,2) YKJH1,round(nvl(b.YKJH2,0)/10000,2) YKJH2,round(nvl(b.YKJH31,0)/10000,2) YKJH31,round(nvl(b.YKJH32,0)/10000,2) YKJH32,round(nvl(b.ye,0)/10000,2) ye,round(b.zxl,2) zxl from zftz_xm a left join ( \n" +
                    "select t2.id,t2.xmmc,\n" +
                    "t1.ysap_je_ln+t1.ysap_je_dn YSAP1,t1.ysap_je_ln YSAP2,t1.ysap_je_dn YSAP3,\n" +
                    "t1.zjdw_je_ln+t1.zjdw_je_dn ZJDW1,t1.zjdw_je_ln ZJDW2,t1.zjdw_je_dn ZJDW3,\n" +
                    "t1.zjzx_je_ln+zjzx_je_dn1+zjzx_je_dn YKJH1,t1.zjzx_je_ln YKJH2,zjzx_je_dn1 YKJH31,zjzx_je_dn YKJH32,\n" +
                    "zjdw_je_dn-zjzx_je_dn YE,\n" +
                    "case when nvl(ysap_je_dn,0) = 0 then 0 else zjzx_je_dn/ysap_je_dn end ZXL\n" +
                    " from \n" +
                    "(select id_zftz_xm,\n" +
                    "sum(ysap_je_ln) ysap_je_ln,\n" +
                    "sum(ysap_je_dn) ysap_je_dn,\n" +
                    "sum(zjdw_je_ln) zjdw_je_ln,\n" +
                    "sum(zjdw_je_dn) zjdw_je_dn,\n" +
                    "sum(zjzx_je_ln) zjzx_je_ln,\n" +
                    "case when sum(zjdw_je_ln)-sum(zjzx_je_ln)-sum(zjzx_je_dn)>=0 then sum(zjzx_je_dn)\n" +
                    "  when sum(zjdw_je_ln)-sum(zjzx_je_ln)-sum(zjzx_je_dn)<0 then sum(zjdw_je_ln)-sum(zjzx_je_ln) end zjzx_je_dn1,\n" +
                    "case when sum(zjdw_je_ln)-sum(zjzx_je_ln)-sum(zjzx_je_dn)>=0 then 0\n" +
                    "  when sum(zjdw_je_ln)-sum(zjzx_je_ln)-sum(zjzx_je_dn)<0 then -sum(zjdw_je_ln)+sum(zjzx_je_ln)+sum(zjzx_je_dn) end zjzx_je_dn\n" +
                    " from \n" +
                    "(select t.id_zftz_xm,\n" +
                    "case when t.year<? then t.ysap_je else 0 end ysap_je_ln, \n" +
                    "case when t.year=? then t.ysap_je else 0 end ysap_je_dn,\n" +
                    "case when t.year<? then t.zjdw_je else 0 end zjdw_je_ln,\n" +
                    "case when t.year=? then t.zjdw_je else 0 end zjdw_je_dn,\n" +
                    "case when t.year<? then t.zjzx_je else 0 end zjzx_je_ln,\n" +
                    "case when t.year=? then t.zjzx_je else 0 end zjzx_je_dn\n" +
                    "from V_ZFTZ_XMYSGL_ZJQK t \n" +
                    "where t.year<=?) group by id_zftz_xm) t1,zftz_xm t2\n" +
                    "where t1.id_zftz_xm = t2.id\n"+
                    ") b on a.id=b.id where a.zt=1 and a.jsdw= ? \n"+
                    "and a.id not in (select t3.id_zftz_xm from zftz_xmjgjs t3 where t3.zt = '1' and t3.jsdw = ?)";
            List param = new ArrayList();
            param.add(nd);
            param.add(nd);
            param.add(nd);
            param.add(nd);
            param.add(nd);
            param.add(nd);
            param.add(nd);
            param.add(yhszdw);
            param.add(yhszdw);
//            if (!jsdw.equals("")){
////                sql1 = sql1 + " and t1.jsdw="+jsdw;
//                sql = sql + " and t2.jsdw=?";
//                param.add(jsdw);
//            };
            if (!xmmc.equals("")){
//                sql1 = sql1 + " and t1.xmmc="+xmmc;
                sql = sql + " and a.xmmc like '%"+xmmc+"%'";
//                param.add(xmmc);
            };

            sql = sql + " order by a.xmmc";
            List list = baseDao.selectMapsBySQL(sql,param);
            return list;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List getZjapmxInfo(String nd,String xmid){
        try {
            String sql = "select a.mc ZJLY,round((nvl(b.ysap_je,0)+nvl(c.ysap_je,0))/10000,2) ysap1,round(nvl(b.ysap_je,0)/10000,2) ysap3,round(nvl(c.ysap_je,0)/10000,2) ysap2,\n" +
                    "a.bm, f.DNZJJH,round((nvl(d.money,0)+nvl(e.money,0))/10000,2) zjdw1,round(nvl(d.money,0)/10000,2) zjdw3,round(nvl(e.money,0)/10000,2) zjdw2 from zftz_xxb a left join\n" +
                    "(select t1.ysap_je,t1.zjly from v_zftz_xmysgl_ysap t1\n" +
                    "where t1.id_zftz_xm = ? and t1.nd = ? \n" +
                    "group by t1.zjly,t1.ysap_je) b\n" +
                    "on b.zjly = a.bm \n" +
                    "left join\n" +
                    "(select sum(ysap_je) ysap_je,zjly from (select t1.ysap_je,t1.zjly from v_zftz_xmysgl_ysap t1\n" +
                    "where t1.id_zftz_xm = ? and t1.nd < ? \n" +
                    "group by t1.zjly,t1.ysap_je) group by zjly) c\n" +
                    "on c.zjly = a.bm \n" +
                    "left join\n" +
                    "(select 1 bm,nvl(yyggys,0)/10000 DNZJJH from zftz_ndjhgl where id_zftz_xm = ? and nd = ?\n" +
                    "union select 2 bm,nvl(phzy,0)/10000 DNZJJH from zftz_ndjhgl where id_zftz_xm = ? and nd = ?\n" +
                    "union select 3 bm,nvl(phzc,0)/10000 DNZJJH from zftz_ndjhgl where id_zftz_xm = ? and nd = ?\n" +
                    "union select 4 bm,nvl(qxfd,0)/10000 DNZJJH from zftz_ndjhgl where id_zftz_xm = ? and nd = ?\n" +
                    "union select 5 bm,nvl(pppshzb,0)/10000 DNZJJH from zftz_ndjhgl where id_zftz_xm = ? and nd = ?\n" +
                    "union select 6 bm,nvl(qt,0)/10000 DNZJJH from zftz_ndjhgl where id_zftz_xm = ? and nd = ?) f\n" +
                    "on a.bm = f.bm\n"+
                    "left join\n" +
                    "(select t2.money,t2.zjlybm from V_ZFTZ_XMYSGL_ZJDW t2\n" +
                    "where t2.id_zftz_xm = ? and t2.year = ? \n" +
                    "group by t2.zjlybm,t2.money) d\n" +
                    "on a.bm = d.zjlybm\n" +
                    "left join\n" +
                    "(select sum(money) money,zjlybm from (select t2.money,t2.zjlybm from V_ZFTZ_XMYSGL_ZJDW t2\n" +
                    "where t2.id_zftz_xm = ? and t2.year < ? \n" +
                    "group by t2.zjlybm,t2.money) group by zjlybm) e\n" +
                    "on a.bm = e.zjlybm\n" +
                    "where a.lx = 'zjly'\n" +
                    "order by a.bm";
            List param = new ArrayList();
            param.add(xmid);
            param.add(nd);
            param.add(xmid);
            param.add(nd);
            param.add(xmid);
            param.add(nd);
            param.add(xmid);
            param.add(nd);
            param.add(xmid);
            param.add(nd);
            param.add(xmid);
            param.add(nd);
            param.add(xmid);
            param.add(nd);
            param.add(xmid);
            param.add(nd);
            param.add(xmid);
            param.add(nd);
            param.add(xmid);
            param.add(nd);

            List list = baseDao.selectMapsBySQL(sql,param);
            return list;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List getXMzbmx(String nd,String xmid,String bm){
        //对单位进行限制
        try {
            String sql1 = "select jsdw from ZFTZ_XM where id = ?";
            List param1 = new ArrayList();
            param1.add(xmid);
            List list1 = baseDao.selectMapsBySQL(sql1,param1);

            Map map = (Map) list1.get(0);
            String jsdw = map.get("JSDW").toString();

//            String sql = "select rownum xh,zjly,budgetid,in_code,gnkmmc,je from V_ZFTZ_JC_DWZB t where jsdw = ?";
            String sql = "select (select count(*)\n" +
                    "from ZFTZ_XMYSDYZBMX b\n" +
                    "where b.zbid = a.budgetid\n" +
                    "and b.zjlybm = a.in_code) flag,(select id\n" +
                    "from ZFTZ_XMYSDYZBMX b\n" +
                    "where b.zbid = a.budgetid\n" +
                    "and b.zjlybm = a.in_code) id, rownum xh,a.zjly,a.budgetid,a.in_code,a.gnkmmc,a.je \n" +
                    "from v_zftz_jc_dwzb a where a.jsdw=? and a.year=?\n" +
                    "and not exists(\n" +
                    "select * from zftz_xmysdyzbmx t,zftz_xmysgl t1 \n" +
                    "    where t.id_zftz_xmysgl=t1.id and ((t1.id_zftz_xm <> ? and t1.nd = ?) \n" +
                    "    or (t1.id_zftz_xm=? and t1.nd = ? and t1.zjly <> ?))  \n" +
                    "    and a.budgetid=t.zbid and a.in_code=t.zjlybm\n" +
                    ")";

            List param = new ArrayList();
            param.add(jsdw);
            param.add(nd);
            param.add(xmid);
            param.add(nd);
            param.add(xmid);
            param.add(nd);
            param.add(bm);
//            sql = sql + " order by budgetid";

            List list = baseDao.selectMapsBySQL(sql,param);
            return list;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void updateZjap(String sdata,String xmid,String nd,HttpSession session,Result result){
        try {
            YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
            Long userid = yhglywyhyy.getGuid();
            String sql;
            String sql1;
            List param = new ArrayList();
            List param1 = new ArrayList();
            JSONArray jsonArray = JSONArray.parseArray(sdata);
            for (int j=0;j<jsonArray.size();j++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(j);

                String zjly = jsonObject.get("BM").toString();

                sql1 = "select * from ZFTZ_XMYSGL where id_ZFTZ_XM = ? and nd = ? and zjly = ?";
                param1.clear();
                param1.add(xmid);
                param1.add(nd);
                param1.add(zjly);
                List list1 = baseDao.selectMapsBySQL(sql1,param1);
                if (list1.size() == 0){
                    sql = "insert into ZFTZ_XMYSGL values(SEQ_ZFTZ_XMYSGL.nextval,?,?,?,0,sysdate,?)";
                    param.clear();
                    param.add(xmid);
                    param.add(nd);
                    param.add(zjly);
                    param.add(userid);

                    baseDao.executeBySql(sql,param);
                }
            }
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
        }
    }

    @Override
    public void updateDnje(String sdata,String xmid,String nd,HttpSession session,Result result){
        try {
            YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
            Long userid = yhglywyhyy.getGuid();
            String sql;
            String sql1;
            List param = new ArrayList();
            List param1 = new ArrayList();
            JSONArray jsonArray = JSONArray.parseArray(sdata);
            for (int j=0;j<jsonArray.size();j++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(j);

                String zjly = jsonObject.get("BM").toString();
                String je = jsonObject.get("YSAP3").toString();

                sql1 = "select * from ZFTZ_XMYSGL where id_ZFTZ_XM = ? and nd = ? and zjly = ?";
                param1.clear();
                param1.add(xmid);
                param1.add(nd);
                param1.add(zjly);
                List list1 = baseDao.selectMapsBySQL(sql1,param1);
                if (list1.size() == 0){
                    sql = "insert into ZFTZ_XMYSGL values(SEQ_ZFTZ_XMYSGL.nextval,?,?,?,0,sysdate,?)";
                    param.clear();
                    param.add(xmid);
                    param.add(nd);
                    param.add(zjly);
                    param.add(userid);

                    baseDao.executeBySql(sql,param);
                }else {
                    if (zjly.equals("3")||zjly.equals("5")||zjly.equals("6")) {
                        sql = "update ZFTZ_XMYSGL set je = ?*10000,czsj = sysdate,czr = ? where id_ZFTZ_XM = ? and nd = ? and zjly = ?";
                        param.clear();
                        param.add(je);
                        param.add(userid);
                        param.add(xmid);
                        param.add(nd);
                        param.add(zjly);

                        baseDao.executeBySql(sql, param);
                    }
                }
            }
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
        }
    }

    @Override
    public void updateXMzbmx(String sdata,String xmid,String nd,String zjly,Result result){
        try {
            String sql;
            List param = new ArrayList();
            JSONArray jsonArray = JSONArray.parseArray(sdata);
            String sql1 = "select * from ZFTZ_XMYSGL where id_ZFTZ_XM = ? and nd = ? and zjly = ?";
            List param1 = new ArrayList();
            param1.add(xmid);
            param1.add(nd);
            param1.add(zjly);
            List list1 = baseDao.selectMapsBySQL(sql1,param1);

            Map map = (Map) list1.get(0);
            String id = map.get("ID").toString();
            String flag;
            for (int j=0;j<jsonArray.size();j++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(j);
                if (jsonObject.containsKey("FLAG")){
                     flag = jsonObject.get("FLAG").toString();
                }else{
                     flag = "";
                }
                String sid = (jsonObject.get("ID") == null)?"":jsonObject.get("ID").toString();

                //ZFTZ_XMYSGL(项目预算管理)的id,需要另传
                String budgetid = jsonObject.get("BUDGETID").toString();
                String in_code = jsonObject.get("IN_CODE").toString();

                if (!(flag.equals("0")||flag.equals("false"))){
                    if (sid.equals("")){
                        sql = "insert into ZFTZ_XMYSDYZBMX values(SEQ_ZFTZ_XMYSDYZBMX.nextval,?,?,?)";
                        param.clear();
                        param.add(id);
                        param.add(budgetid);
                        param.add(in_code);
                        baseDao.executeBySql(sql,param);
                    }
                }else {
                    if (!sid.equals("")){
                        sql = "delete from ZFTZ_XMYSDYZBMX where id = ?";
                        param.clear();
                        param.add(sid);
                        baseDao.executeBySql(sql,param);
                    }
                }
            }
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
        }
    }

    @Override
    public List getZjdwmxInfo(String xmid,String nd,String zjly){
        try {
            String sql1 = "select * from ZFTZ_XMYSGL where id_ZFTZ_XM = ? and nd = ? and zjly = ?";
            List param1 = new ArrayList();
            param1.add(xmid);
            param1.add(nd);
            param1.add(zjly);
            List list1 = baseDao.selectMapsBySQL(sql1,param1);
            Map map = (Map) list1.get(0);
            String id = map.get("ID").toString();

            String sql = "select t2.id,to_date(t2.dzrq,'yyyy-mm-dd') dzrq,t2.dzje from ZFTZ_XMYSGL t1,ZFTZ_XMZJDW t2 where t1.id = t2.id_zftz_xmysgl\n" +
                    " and t1.id = ?";
            List param = new ArrayList();
            param.add(id);

            List list = baseDao.selectMapsBySQL(sql,param);
            return list;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void deleteZjdwmx(String sid,Result result){
        try {
            String sql = "delete from ZFTZ_XMZJDW where id = ?";
            List param = new ArrayList();
            param.add(sid);
            baseDao.executeBySql(sql,param);

            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
        }
    }

    @Override
    public void updateZjdwmx(String sdata,String xmid,String nd,String zjly,Result result){
        try {
            JSONArray jsonArray = JSONArray.parseArray(sdata);
            String sql;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            Date d = null;
            List param = new ArrayList();
            for (int j = 0; j < jsonArray.size(); j++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(j);

                if (jsonObject.containsKey("ID")) {
                    sql = "update ZFTZ_XMZJDW set dzrq = ?,dzje = ? where id = ?";
                    String id = jsonObject.get("ID").toString();
                    String dzrq = jsonObject.get("DZRQ").toString();
                    String dzje = jsonObject.get("DZJE").toString();
                    d = sdf.parse(dzrq);
                    dzrq = sdf1.format(d);
                    param.clear();
                    param.add(dzrq);
                    param.add(dzje);
                    param.add(id);

                    baseDao.executeBySql(sql, param);
                } else {
                    String sql1 = "select * from ZFTZ_XMYSGL where id_ZFTZ_XM = ? and nd = ? and zjly = ?";
                    List param1 = new ArrayList();
                    param1.add(xmid);
                    param1.add(nd);
                    param1.add(zjly);

                    List list = baseDao.selectMapsBySQL(sql1, param1);
                    Map map = (Map) list.get(0);
                    String id = map.get("ID").toString();

                    sql = "insert into ZFTZ_XMZJDW values(SEQ_ZFTZ_XMZJDW.nextval,?,?,?)";
                    String dzrq = jsonObject.get("DZRQ").toString();
                    String dzje = jsonObject.get("DZJE").toString();
                    d = sdf.parse(dzrq);
                    dzrq = sdf1.format(d);
                    param.clear();
                    param.add(id);
                    param.add(dzrq);
                    param.add(dzje);

                    baseDao.executeBySql(sql, param);
                }
            }
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
        }
    }

    @Override
    public List getXmzxgl(String pch,String zt,HttpSession session){
        try {
            YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
            Long userid = yhglywyhyy.getGuid();
            String sql1 = "select t1.szdwid from yhgl_yw_yhyy t1 where t1.guid = ?";
            List param1 = new ArrayList();
            param1.add(userid);
            List list1 = baseDao.selectMapsBySQL(sql1,param1);
            Map map1 = (Map) list1.get(0);
            String yhszdw = map1.get("SZDWID").toString();
            /*String sql = "select t1.id sid,\n" +
                    "       f.id_zftz_htba htid,\n" +
                    "       t4.id,\n" +
                    "       t1.pch,\n" +
                    "       t4.xmmc,\n" +
                    "       t5.htmc,\n" +
                    "       (select round(sum(t3.je) / 10000, 2) je\n" +
                    "          from ZFTZ_XMZXDJ_ZFMX t3\n" +
                    "         where t3.id_zftz_xmzxdj = t1.id) je,\n" +
                    "       t1.zfrq,\n" +
                    "       t1.xxjd,\n" +
                    "       (select t7.xm\n" +
                    "          from yhgl_yw_yhyy t6, yhgl_yw_yh t7\n" +
                    "         where t6.yhid = t7.guid\n" +
                    "           and t6.guid = t1.czr) czr,\n" +
                    "       t1.czsj,\n" +
                    "       t1.zt,\n" +
                    "       case\n" +
                    "         when t4.id in (select id_zftz_xm\n" +
                    "                          from zftz_xmjgjs t\n" +
                    "                         where t.zt = '1'\n" +
                    "                           and t.jsdw = ?) then\n" +
                    "          '1'\n" +
                    "         else\n" +
                    "          '0'\n" +
                    "       end jszt\n" +
                    "  from ZFTZ_XMZXDJ t1, ZFTZ_XM t4, ZFTZ_HTBA t5, zftz_xmzxdj_zfmx f\n" +
                    " where t1.id_zftz_xm = t4.id\n" +
                    "   and t5.id = f.id_zftz_htba\n" +
                    "   and t4.zt = '1'\n" +
                    "   and t4.jsdw = ?";*/
          /*  String sql = "select d.pch,\n" +
                    "       xm.id,\n" +
                    "       xm.xmmc,\n" +
                    "       d.zfrq,\n" +
                    "       d.xxjd,\n" +
                    "       d.czr,\n" +
                    "       d.czsj,\n" +
                    "       d.zt,\n" +
                    "       (select sum(nvl(kx.je, 0)) / 10000\n" +
                    "          from zftz_xmzxdj_kxmx kx\n" +
                    "         where kx.id_zftz_xmzxdj = d.id) zfje\n" +
                    "  from zftz_xmzxdj d, zftz_xm xm, v_zftz_yhtoenter yt\n" +
                    " where d.id_zftz_xm = xm.id\n" +
                    "   and xm.jsdw = yt.entid\n" +
                    "   and yt.guid = " + userid +
                    "   and d.zt != 0";*/
            String sql = "\n" +
                    "select d.pch,\n" +
                    "       xm.id,\n" +
                    "       d.id sid,"+
                    "       xm.xmmc,\n" +
                    "       d.zfrq,\n" +
                    "       d.xxjd,\n" +
                    "   /*    d.czr,*/\n" +
                    "       d.czsj,\n" +
                    "       d.zt,\n" +
                    "       case\n" +
                    "         when xm.id in (select id_zftz_xm\n" +
                    "                          from zftz_xmjgjs t\n" +
                    "                         where t.zt = '1'\n" +
                    "                           and t.jsdw = '56150586') then\n" +
                    "          '1'\n" +
                    "         else\n" +
                    "          '0'\n" +
                    "       end jszt,\n" +
                    "       (select sum(nvl(kx.je, 0)) / 10000\n" +
                    "          from zftz_xmzxdj_kxmx kx\n" +
                    "         where kx.id_zftz_xmzxdj = d.id) je,\n" +
                    "          (select t7.xm\n" +
                    "          from yhgl_yw_yhyy t6, yhgl_yw_yh t7\n" +
                    "         where t6.yhid = t7.guid\n" +
                    "           and t6.guid = d.czr) czr\n" +
                    "  from zftz_xmzxdj d, zftz_xm xm, v_zftz_yhtoenter yt\n" +
                    " where d.id_zftz_xm = xm.id\n" +
                    "   and xm.jsdw = yt.entid\n" +
                    "   and yt.guid =" + userid +
                    "   and d.zt != 0";
           /* List param = new ArrayList();
            param.add(yhszdw);
            param.add(yhszdw);*/
            if (zt.equals("2")){
                sql = sql + " and d.zt = '2' ";
            }else {
                sql = sql + " and d.zt = '1' ";
            }
            if (pch !=null&&!pch.equals("")){
                sql = sql + " and d.pch like '%"+pch+"%'";
//                param.add(pch);
            }
            sql = sql + " order by d.id";
            List list = baseDao.selectMapsBySQL(sql);
            for (int i = 0; i < list.size(); i++) {
                String msg = "";
                Map a=(Map) list.get(i);
                String selectSql = " SELECT x.id_zftz_htba from zftz_xmzxdj_zfmx x where x.id_zftz_xmzxdj = " + a.get("SID");
                List list2 = baseDao.selectMapsBySQL(selectSql);
                if(list2.size()>0){
                    for (int j = 0; j < list2.size(); j++) {
                        msg += ((Map) list2.get(j)).get("ID_ZFTZ_HTBA") + ",";
                    }
                    if (msg.length() > 0) {
                        msg = msg.substring(0, msg.length() - 1);
                    }
                }
                a.put("HETONGID",msg);
            }
            return list;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void  deleteXmzxgl(String sdata,Result result){
        try {
            String sql = "update ZFTZ_XMZXDJ set zt = '0' where id = ?";
            List param = new ArrayList();

            JSONArray jsonArray = JSONArray.parseArray(sdata);
            for (int j = 0; j < jsonArray.size(); j++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(j);
                String id = jsonObject.get("SID").toString();
                param.clear();
                param.add(id);
                baseDao.executeBySql(sql,param);
            }
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
        }
    }

    @Override
    public List getHtzfmx(String htid,String sid){
        try {
           /* String sql = "select t1.id,t2.id sid,t1.id_zftz_htba,t1.yjzfsj,t1.yjzfje,(nvl(t1.je,0)-nvl(t2.je,0)) yfje,nvl(t2.je,0) je \n" +
                    "from  v_zftz_zfjdmx t1 left join (select * from ZFTZ_XMZXDJ_ZFMX where id_zftz_xmzxdj = ?) t2\n" +
                    "on t1.id = t2.id_zftz_htyjzfmx \n" +
                    "where t1.id_zftz_htba = ? order by t1.id";*/
            String sql = "\n" +
                    "select ht.id, ht.htmc HTXY, nvl(h.yfje,0) yfje,hh.je\n" +
                    "  from zftz_htba ht\n" +
                    "  left join (select id_zftz_htba, sum(nvl(x.je, 0)) yfje\n" +
                    "               from zftz_xmzxdj_zfmx x\n" +
                    "              where x.id_zftz_htba in (" + htid + ")\n" +
                    "              group by id_zftz_htba) h\n" +
                    "    on ht.id = h.id_zftz_htba\n" +
                    "  left join (select xx.id_zftz_htba, xx.je\n" +
                    "               from zftz_xmzxdj_zfmx xx\n" +
                    "              where xx.id_zftz_xmzxdj = ?) hh\n" +
                    "    on ht.id = hh.id_zftz_htba\n" +
                    " where ht.id in (" + htid + ")\n" +
                    " and ht.zt = 1";
            List param = new ArrayList();
//            param.add(htid);
            param.add(sid);
//            param.add(htid);
//            if (!sid.equals("")){
//                sql = sql + " and t2.id_ZFTZ_XMZXDJ = ?";
//                param.add(sid);
//            }
            List list = baseDao.selectMapsBySQL(sql,param);
            return list;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List getHTZFmx(String sid) {
        String sql = "SELECT w.id_zftz_htba id,\n" +
                "                  w.je,\n" +
                "                  (select ht.htmc htxy\n" +
                "                     from zftz_htba ht\n" +
                "                    where ht.id = w.id_zftz_htba) htxy,\n" +
                "                  nvl(j.yfje,0) yfje\n" +
                "             from zftz_xmzxdj_zfmx w\n" +
                "             left join (select id_zftz_htba, sum(nvl(x.je, 0)) yfje\n" +
                "                          from zftz_xmzxdj_zfmx x\n" +
               /* "                         where x.id_zftz_xmzxdj= " + sid +*/
                "                         group by id_zftz_htba) j\n" +
                "               on w.id_zftz_htba = j.id_zftz_htba\n" +
                "            where w.id_zftz_xmzxdj= " + sid;
        List list = baseDao.selectMapsBySQL(sql);
        return list;
    }

    @Override
    public List getHtmc(String xmid,HttpSession session){
        try {
            String sql;

            YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
            Long userid = yhglywyhyy.getGuid();

            String sql1 = "select t1.szdwid from yhgl_yw_yhyy t1 where t1.guid = ?";
            List param1 = new ArrayList();
            param1.add(userid);
            List list1 = baseDao.selectMapsBySQL(sql1,param1);
            Map map1 = (Map) list1.get(0);
            String yhszdw = map1.get("SZDWID").toString();

            if (xmid!=null&&!("").equals(xmid)) {
                List dataList = new ArrayList();
                sql = "select t2.id,t2.htmc from ZFTZ_XM t1,ZFTZ_HTBA t2 where t1.id = t2.id_zftz_xm and t1.id = ? "+
                        " and t2.id not in (select t3.id_zftz_htba from zftz_xmgcjsba t3 where t3.zt = '1' and t3.jsdw = ?) order by t2.htmc";
                List param = new ArrayList();
                param.add(xmid);
                param.add(yhszdw);
                List list = baseDao.selectMapsBySQL(sql,param);

                for (int i = 0; i < list.size(); i++) {
                    Map map = (Map) list.get(i);
                    String id = map.get("ID").toString();
                    String name = map.get("HTMC").toString();
                    map.clear();
                    map.put("id", id);
                    map.put("name", name);
                    map.put("pId","0");
                    dataList.add(map);
//                map.put("type",id);
//                list2.add(map);
//            map.put("",);
                }

                return dataList;
            }else {
                sql = "select t2.id,t2.htmc from ZFTZ_XM t1,ZFTZ_HTBA t2 where t1.id = t2.id_zftz_xm order by t2.htmc";
                List list = baseDao.selectMapsBySQL(sql);

                for (int i = 0; i < list.size(); i++) {
                    Map map = (Map) list.get(i);
                    String id = map.get("ID").toString();
                    String name = map.get("HTMC").toString();
                    map.clear();
                    map.put("id", id);
                    map.put("text", name);
//                map.put("type",id);
//                list2.add(map);
//            map.put("",);
                }

                return list;
            }

        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List getHTMC(String xmid, HttpSession session) {
        List dataList = new ArrayList();

        String sql = "select w.id_zftz_htba id,\n" +
                "       (select ht.htmc htxy from zftz_htba ht where ht.id = w.id_zftz_htba) name\n" +
                "  from zftz_xmzxdj_zfmx w\n" +
                " where w.id_zftz_xmzxdj = " + xmid;
        List list = baseDao.selectMapsBySQL(sql);
        for (int i = 0; i < list.size(); i++) {
            Map map = (Map) list.get(i);
            String id = map.get("ID").toString();
            String name = map.get("NAME").toString();
            map.clear();
            map.put("id", id);
            map.put("text", name);
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    public Object getXmzxInfo(String pch){
        try {
            String sql = "select t1.zfrq,t1.zy,t1.xxjd,nvl(sum(t2.je),0) je from ZFTZ_XMZXDJ t1\n" +
                    "left join ZFTZ_XMZXDJ_KXMX t2 \n" +
                    " on t1.id = t2.id_ZFTZ_XMZXDJ \n" +
                    " where t1.pch = ? \n" +
                    " group by t1.zfrq,t1.zy,t1.xxjd";
            List param = new ArrayList();
            param.add(pch);

            List list = baseDao.selectMapsBySQL(sql,param);

            Map map = (Map) list.get(0);
            return map;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List getKxmx(String zxdjid){
        try {
            String sql = "select t1.bm,t1.mc,nvl(t2.je,0) je from ZFTZ_xxb t1\n" +
                    "left join \n";
            String sql1 = " on t1.bm = t2.kxlx\n" +
                    "where t1.lx = 'zfkxlx' ";
            List param = new ArrayList();
//            if (htid != ""&&htid !=null){
                sql = sql + " (select je,kxlx from ZFTZ_XMZXDJ_KXMX where id_ZFTZ_XMZXDJ = ?) t2 ";
                param.add(zxdjid);
//            }else {
//                sql = sql + " (select je,kxlx from ZFTZ_XMZXDJ_KXMX) t2 ";
//            }

            sql = sql + sql1 + "order by to_number(t1.bm)";

            List list = baseDao.selectMapsBySQL(sql,param);
            return list;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List getXmmc2(HttpSession session){
        try {
            List dataList = new ArrayList();

            YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
            Long userid = yhglywyhyy.getGuid();

           /* String sql = "select t1.szdwid from yhgl_yw_yhyy t1 where t1.guid = ?";
            List param1 = new ArrayList();
            param1.add(userid);
            List list1 = baseDao.selectMapsBySQL(sql,param1);
            Map map1 = (Map) list1.get(0);
            String yhszdw = map1.get("SZDWID").toString();*/

            /*String sql1 = "select t1.id,t1.xmmc from ZFTZ_XM t1 where t1.zt = '1' and t1.jsdw = ? "+
                        " and t1.id not in (select t.id_zftz_xm from zftz_xmjgjs t where t.zt = '1' and t.jsdw = ?) order by t1.xmmc";*/
            String sql1 = "with xmTable as\n" +
                    " (select t.id, t.xmmc, t.jsdw\n" +
                    "    from zftz_xm t\n" +
                    "   where t.zt = 1\n" +
                    "    and t.jsdw in (select entid from v_zftz_yhtoenter where guid = " + userid + ")\n" +
                    "   and t.jsdw is not null\n" +
                    "  /* and t.id not in\n" +
                    "       (select to_char(b.id_zftz_xm) from zftz_xmtzwh b where b.zt = 1)*/\n" +
                    "   and t.id not in\n" +
                    "       (select to_char(s.id_zftz_xm) from zftz_xmjgjs s where s.zt = 1)),\n" +
                    "dwTable as\n" +
                    " (select t.id, t.mc, FJBM\n" +
                    "    from v_zftz_jsdw t, xmTable t1\n" +
                    "   where t.ID = t1.JSDW\n" +
                    "   group by t.ID, MC, BM, FJBM)\n" +
                    "select distinct t.ID || '_dw' ID, t.MC, t.FJBM || '_dw' PID\n" +
                    "  from V_ZFTZ_JSDW t\n" +
                    " start with ID in (select ID from dwTable)\n" +
                    "connect by prior FJBM = ID\n" +
                    "union all\n" +
                    "select to_char(t.ID) id, t.XMMC MC, t.JSDW || '_dw' PID from xmTable t\n";
          /*  List param = new ArrayList();
            param.add(yhszdw);
            param.add(yhszdw);*/
            List list = baseDao.selectBySql(sql1);

           /* for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                String id = map.get("ID").toString();
                String name = map.get("XMMC").toString();
                map.clear();
                map.put("id", id);
                map.put("name", name);
                map.put("pId","0");
                dataList.add(map);
//                list2.add(map);
//            map.put("",);
            }*/
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    Map map = new HashMap();
                    map.put("id", obj[0]);
                    map.put("name", obj[1]);
                    map.put("pId", obj[2]);
                    dataList.add(map);
                }
            }
            return dataList;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List getXmmcall(HttpSession session){
        try {
            List dataList = new ArrayList();

            YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
            Long userid = yhglywyhyy.getGuid();

            String sql = "select t1.szdwid from yhgl_yw_yhyy t1 where t1.guid = ?";
            List param1 = new ArrayList();
            param1.add(userid);
            List list1 = baseDao.selectMapsBySQL(sql,param1);
            Map map1 = (Map) list1.get(0);
            String yhszdw = map1.get("SZDWID").toString();
            YhglYwYh operator = (YhglYwYh) session.getAttribute(StaticData.LOGINUSERINFO);
            String yhlx = operator.getYhlx();
            String sql1 = "";
            if("1".equals(yhlx)){
                sql1 = "select t1.id,t1.xmmc from ZFTZ_XM t1 where t1.zt = '1' and t1.jsdw = " + yhszdw + " order by t1.xmmc";
            }else {
                sql1 = "select t1.id,t1.xmmc from ZFTZ_XM t1 where t1.zt = '1'  order by t1.xmmc";
            }
//            String sql1 = "select t1.id,t1.xmmc from ZFTZ_XM t1 where t1.zt = '1' and t1.jsdw = ? order by t1.xmmc";
//                    " and t1.id not in (select t.id_zftz_xm from zftz_xmjgjs t where t.zt = '1' and t.jsdw = ?)";
//            List param = new ArrayList();
//            param.add(yhszdw);
//            param.add(yhszdw);
            List list = baseDao.selectMapsBySQL(sql1);

            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                String id = map.get("ID").toString();
                String name = map.get("XMMC").toString();
                map.clear();
                map.put("id", id);
                map.put("name", name);
                map.put("pId","0");
                dataList.add(map);
//                list2.add(map);
//            map.put("",);
            }
            return dataList;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void updateXmzxdjzt(String sdata,Result result){
        try {
            String sql = "update ZFTZ_XMZXDJ set zt = '2' where id = ?";
            List param = new ArrayList();
            JSONArray jsonArray = JSONArray.parseArray(sdata);
            for (int j = 0; j < jsonArray.size(); j++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(j);
                String id = jsonObject.get("SID").toString();
                param.clear();
                param.add(id);
                baseDao.executeBySql(sql,param);
            }
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
        }
    }

    @Override
    public void insertXmzxdj(String xmid,String htid,String zfrq,String zy,String xxjd,String sdata,String mxdata,String uploadTableData,HttpSession session,Result result){
        Connection conn = null;
//        ResultSet rs = null;
        PreparedStatement ps = null;
        YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
        Long userid = yhglywyhyy.getGuid();
        try {
            String s = "select SEQ_ZFTZ_XMZXDJ.nextval as a from dual";
            List list = baseDao.selectMapsBySQL(s);
            Map map1 = (Map) list.get(0);
            String zxdjid = map1.get("A").toString();

            conn = baseDao.getConnection();
            conn.setAutoCommit(false);

            zfrq = ("").equals(zfrq)?"0":zfrq;
            xxjd = ("").equals(xxjd)?"0":xxjd;

            String sql = "insert into ZFTZ_XMZXDJ values(?,to_char(sysdate,'yyyymmdd')||?,?,?,?,?,?,1,sysdate,?)";
            ps = conn.prepareStatement(sql);
//            ps.setString(1,"00");
            ps.setInt(1,Integer.valueOf(zxdjid));
            ps.setString(2,zxdjid);
            ps.setInt(3,Integer.valueOf(xmid));
            ps.setString(4,"");
            ps.setInt(5,Integer.valueOf(zfrq));
            ps.setString(6,zy);
            ps.setFloat(7,Float.valueOf(xxjd));
            ps.setString(8,userid.toString());
            ps.executeUpdate();

//            String ssql = "select SEQ_ZFTZ_XMZXDJ.currval a from dual";
//            List list1 = baseDao.selectBySql(ssql);
//            Map map1 = (Map)list1.get(0);
//            String currval = map1.get("A").toString();

//            String ssql = "select id from ZFTZ_HTYJZFMX where id_ZFTZ_HTBA = ?";
//            List param1 = new ArrayList();
//            param1.add(htid);
//            List list1 = baseDao.selectMapsBySQL(ssql,param1);
//            if(list1.size() == 0){
//                result.setSuccess(false);
//                result.setContent("合同预计支付明细获取失败");
//                return;
//            }
//            Map map2 = (Map)list1.get(0);
//            String zfmxid = map2.get("ID").toString();

            JSONArray jsonArray = JSONArray.parseArray(sdata);
            for (int j = 0; j < jsonArray.size(); j++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(j);
                if (jsonObject.get("JE") != null) {
                    String je = ("").equals(jsonObject.get("JE").toString())?"0":jsonObject.get("JE").toString();
                    if (!("").equals(je)) {
                        String bm = jsonObject.get("BM").toString();
                        String sql1 = " insert into ZFTZ_XMZXDJ_KXMX values(SEQ_ZFTZ_XMZXDJ_KXMX.nextval,?,?,?)";
                        ps = conn.prepareStatement(sql1);
//                    ps.setInt(1, Integer.valueOf(currval) + 1);
                        ps.setInt(1,Integer.valueOf(zxdjid));
                        ps.setInt(2, Integer.valueOf(bm));
                        ps.setFloat(3, Float.valueOf(je));
                        ps.executeUpdate();
                    }
                }
            }

            JSONArray jsonArray1 = JSONArray.parseArray(mxdata);
            for (int j = 0; j < jsonArray1.size(); j++) {
                JSONObject jsonObject1 = (JSONObject) jsonArray1.get(j);

                String je = ("").equals(jsonObject1.get("JE").toString())?"0":jsonObject1.get("JE").toString();
                String mxid = jsonObject1.get("ID").toString();
                if (!("").equals(je)) {
                    String sql1 = " insert into ZFTZ_XMZXDJ_ZFMX values(SEQ_ZFTZ_XMZXDJ_ZFMX.nextval,?,?,?) ";
                    ps = conn.prepareStatement(sql1);
//                ps.setInt(1,Integer.valueOf(currval) + 1);
                    ps.setInt(1,Integer.valueOf(zxdjid));
                    ps.setInt(2, Integer.valueOf(mxid));
                    ps.setFloat(3, Float.valueOf(je));
                    ps.executeUpdate();
                }
            }

            JSONArray jsonArray2 = JSONArray.parseArray(uploadTableData);
            for (int j = 0; j < jsonArray2.size(); j++) {
                JSONObject jsonObject2 = (JSONObject) jsonArray2.get(j);

//                String filexl = jsonObject2.get("filexl").toString();
                String guid = jsonObject2.get("FILEID").toString();

                String sql1 = " update zftz_file set filedl = 16,filebsid = ? where guid = ? ";
                ps = conn.prepareStatement(sql1);
//                ps.setString(1,filexl);
//                ps.setString(2,id);
                ps.setInt(1,Integer.valueOf(zxdjid));
                ps.setInt(2,Integer.valueOf(guid));
                ps.executeUpdate();
            }

            conn.commit();
            conn.setAutoCommit(true);
            result.setSuccess(true);
            result.setContent(zxdjid);
        }catch (Exception e){
            try{
                e.printStackTrace();
                conn.rollback();
            }catch (Exception e1){

            }
            result.setSuccess(false);
        }finally {
            try{
                if (conn != null){conn.close();}
                if (ps != null){ps.close();}
            }catch (Exception e2){
                result.setSuccess(false);
            }
        }
    }

    @Override
    public void updateXmzxdj(String id,String xmid,String htid,String zfrq,String zy,String xxjd,String sdata,String mxdata,String uploadTableData,HttpSession session,Result result){
        Connection conn = null;
//        ResultSet rs = null;
        PreparedStatement ps = null;
        YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
        Long userid = yhglywyhyy.getGuid();
        try {
            conn = baseDao.getConnection();
            conn.setAutoCommit(false);

            zfrq = ("").equals(zfrq)?"0":zfrq;
            xxjd = ("").equals(xxjd)?"0":xxjd;

//            String ssql = "select id from ZFTZ_HTYJZFMX where id_ZFTZ_HTBA = ?";
//            List param1 = new ArrayList();
//            param1.add(htid);
//            List list1 = baseDao.selectMapsBySQL(ssql,param1);
//            Map map1 = (Map)list1.get(0);
//            String zfmxid = map1.get("ID").toString();

            String sql = "update ZFTZ_XMZXDJ set zfrq = ?, zy = ?, xxjd = ?,czsj = sysdate,czr = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.valueOf(zfrq));
            ps.setString(2,zy);
            ps.setFloat(3,Float.valueOf(xxjd));
            ps.setString(4,userid.toString());
            ps.setInt(5,Integer.valueOf(id));
            ps.executeUpdate();

            JSONArray jsonArray = JSONArray.parseArray(sdata);
            for (int j = 0; j < jsonArray.size(); j++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(j);
                if (jsonObject.get("JE") != null) {
                    String je = jsonObject.get("JE").toString();
                    String bm = jsonObject.get("BM").toString();
                    if(!("").equals(je)) {
                        String sql1 = " update ZFTZ_XMZXDJ_KXMX set je = ? where id_ZFTZ_XMZXDJ = ? and kxlx = ?";
                        ps = conn.prepareStatement(sql1);
                        ps.setFloat(1, Float.valueOf(je));
                        ps.setInt(2, Integer.valueOf(id));
                        ps.setInt(3, Integer.valueOf(bm));

                        ps.executeUpdate();
                    }
                }
            }

            JSONArray jsonArray1 = JSONArray.parseArray(mxdata);
            for (int j = 0; j < jsonArray1.size(); j++) {
                JSONObject jsonObject1 = (JSONObject) jsonArray1.get(j);
                if (jsonObject1.get("JE") != null) {
                    String je = jsonObject1.get("JE").toString();
//                    String mxid = jsonObject1.get("SID").toString();
                    if (!("").equals(je)) {
                        if (jsonObject1.get("SID") != null) {
                            String mxid = jsonObject1.get("SID").toString();
                            String sql1 = " update ZFTZ_XMZXDJ_ZFMX set je = ? where id = ?";
                            ps = conn.prepareStatement(sql1);
                            ps.setFloat(1, Float.valueOf(je));
                            ps.setInt(2, Integer.valueOf(mxid));
                            ps.executeUpdate();
                        }
//                        else {
//                            String mxid = jsonObject1.get("ID").toString();
//                            String sql1 = " insert into ZFTZ_XMZXDJ_ZFMX values(SEQ_ZFTZ_XMZXDJ_ZFMX.nextval,SEQ_ZFTZ_XMZXDJ.currval,?,?) ";
//                            ps = conn.prepareStatement(sql1);
//                            ps.setFloat(1, Float.valueOf(je));
//                            ps.setInt(2, Integer.valueOf(mxid));
//
//                        }
                    }
                }
            }

            JSONArray jsonArray2 = JSONArray.parseArray(uploadTableData);
            for (int j = 0; j < jsonArray2.size(); j++) {
                JSONObject jsonObject2 = (JSONObject) jsonArray2.get(j);

//                String filexl = jsonObject2.get("filexl").toString();
                String guid = jsonObject2.get("FILEID").toString();

                String sql1 = " update zftz_file set filedl = 16,filebsid = ? where guid = ? ";
                ps = conn.prepareStatement(sql1);
//                ps.setString(1,filexl);
                ps.setString(1,id);
                ps.setInt(2,Integer.valueOf(guid));
                ps.executeUpdate();
            }

            conn.commit();
            conn.setAutoCommit(true);
            result.setSuccess(true);
        }catch (Exception e){
            try{
                conn.rollback();
            }catch (Exception e1){

            }
            result.setSuccess(false);
        }finally {
            try{
                if (conn != null){conn.close();}
                if (ps != null){ps.close();}
            }catch (Exception e2){
                result.setSuccess(false);
            }
        }
    }

    @Override
    public List getfile(String bsid){
        try {
            String sql = "select guid fileid,filename,filedl,filesize from zftz_file where filedl = 16 and filebsid = ?";
            List param = new ArrayList();
            param.add(bsid);
            List list = baseDao.selectMapsBySQL(sql,param);
            return list;
        }catch (Exception e){
            return null;
        }
    }
}
