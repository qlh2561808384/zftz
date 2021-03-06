package com.datanew.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.datanew.dao.BaseDao;
import com.datanew.dto.Result;
import com.datanew.model.*;
import com.datanew.service.ProService;
import com.datanew.service.xmgcjjs.FlowService;
import com.datanew.service.xmgcjjs.impl.XmgcjsbbServiceImpl;
import com.datanew.util.JavaBeanUtil;
import com.datanew.util.StaticData;
import com.datanew.util.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("ProService")
public class ProServiceImpl implements ProService {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmgcjsbbServiceImpl.class);
    private static final String SX_CODE = "2";
    @Autowired
    private BaseDao baseDao;
    @Autowired
    FlowService flowService;

    @Override
    public void save(HttpServletRequest request, String jsonData, Result result, String id, String dataArr, String fileId, String zcdjFileId) {
        String ywid = "";
        //获取用户信息
        YhglYwYhyy operator = (YhglYwYhyy) request.getSession().getAttribute(StaticData.LOGINUSER);
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        //项目名称
        String xmmc = jsonObject.get("XMMC") == null ? "" : jsonObject.get("XMMC").toString();
        //项目编号
        String xmbh = jsonObject.get("XMBH") == null ? "" : jsonObject.get("XMBH").toString();
        //项目赋码
        String xmfm = jsonObject.get("XMFM") == null ? "" : jsonObject.get("XMFM").toString();
        //项目阶段
//        Object xmjd = jsonObject.get("XMJD");
        //项目类型
        Object xmlx = jsonObject.get("XMLX");
        //项目实施类型
        Object xmsslx = jsonObject.get("XMSSLX");
        //主管部门
        Object zgbm = jsonObject.get("ZGBM");
        //立项年度
        Object lxnd = jsonObject.get("LXND");
        //总投资 取数的
        //建设单位
        Object jsdw = jsonObject.get("JSDW");

        ///计划开工日期
        Object jhkgrq = jsonObject.get("JHKGRQ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        Date parse = null;
        //估算投资 取数
        //建设地址
        String jsdz = jsonObject.get("JSDZ") == null ? "" : jsonObject.get("JSDZ").toString();

        //计划竣工日期
        Object jhjgrq = jsonObject.get("JHJGRQ");

        //概算投资 取数的
        //项目建议文书号
        String xmjywsh = jsonObject.get("XMJYWSH") == null ? "" : jsonObject.get("XMJYWSH").toString();
        //形象进度
        Object xxjd = jsonObject.get("XXJD");
        //建安概算 取数的
        // 项目可研批复文号
        String xmkypfwh = jsonObject.get("XMKYPFWH") == null ? "" : jsonObject.get("XMKYPFWH").toString();
        //受托审核事务所
        String stshsws = jsonObject.get("STSHSWS") == null ? "" : jsonObject.get("STSHSWS").toString();
        //审定投资数
        Object sdtzs = jsonObject.get("SDTZS");
        // 是否封闭
        Object sffb = jsonObject.get("SFFB");
        //项目初步设计批复文号
        String xmcbsjpfwh = jsonObject.get("XMCBSJPFWH") == null ? "" : jsonObject.get("XMCBSJPFWH").toString();
        //项目绩效 暂时不做
        //建设内容
//        String jsnr = jsonObject.get("JSNR") == null ? "" : jsonObject.get("JSNR").toString();
        //概算批复文号
        String gspfwh = jsonObject.get("GSPFWH") == null ? "" : jsonObject.get("GSPFWH").toString();
        //项目联系人
        String xmlxr = jsonObject.get("XMLXR") == null ? "" : jsonObject.get("XMLXR").toString();
        //项目联系电话
        String xmlxdh = jsonObject.get("XMLXDH") == null ? "" : jsonObject.get("XMLXDH").toString();

        //实际开工日期
        Object sjkgrq = jsonObject.get("SJKGRQ");

        //实际竣工日期
        Object sjjgrq = jsonObject.get("SJJGRQ");

        //所用到的实体类 ZFTZXMTZWH 、ZFTZXM、ZFTZXMGS
        //先保存项目这张表
        if ("".equals(id)) {
            //项目表
            ZftzXm zftzxm = new ZftzXm();
            zftzxm.setXmmc(xmmc);
           /* if (!"".equals(xmjd)) {
                zftzxm.setXmjd(Integer.parseInt(xmjd.toString()));
            }*/
            if (!"".equals(xmlx)) {
                zftzxm.setXmlx(Integer.parseInt(xmlx.toString()));
            }
            if (!"".equals(jsdw)) {
                zftzxm.setJsdw(Integer.parseInt(jsdw.toString()));
            }
            if (!"".equals(zgbm)) {
                zftzxm.setZgbm(Integer.parseInt(zgbm.toString()));
            }
            zftzxm.setXmfm(xmfm);
            if (!"".equals(sffb)) {
                zftzxm.setSffb(Integer.parseInt(sffb.toString()));
            }
            zftzxm.setZt(1);
            baseDao.save(zftzxm);
            //取项目id
            Long xmid = zftzxm.getId();
            ywid = String.valueOf(xmid);
            //项目台账维护表
            ZftzXmtzwh zftzXmtzwh = new ZftzXmtzwh();
            //关联的项目表里面的id
            zftzXmtzwh.setIdZftzXm(xmid);

            zftzXmtzwh.setXmbh(xmbh);
            zftzXmtzwh.setXmfm(xmfm);
            if (!"".equals(xmsslx)) {
                zftzXmtzwh.setXmsslx(Integer.parseInt(xmsslx.toString()));
            }
            if (!"".equals(lxnd)) {
                zftzXmtzwh.setLxnd(Integer.parseInt(lxnd.toString()));
            }
            zftzXmtzwh.setJsdz(jsdz);
            if (!"".equals(jhkgrq)) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    //将string类型的日期转换为Date类型
                    parse = sdf.parse(jhkgrq.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                sd = new SimpleDateFormat("yyyyMMdd");
                //将Date类型格式化为想要的类型
                String format = sd.format(parse);
                Integer Jhkgrq = Integer.parseInt(format);
                zftzXmtzwh.setJhkgrq(Jhkgrq);
            }
            if (!"".equals(jhjgrq)) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    //将string类型的日期转换为Date类型
                    parse = sdf.parse(jhjgrq.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                sd = new SimpleDateFormat("yyyyMMdd");
                //将Date类型格式化为想要的类型
                String format = sd.format(parse);
                Integer Jhjgrq = Integer.parseInt(format);
                zftzXmtzwh.setJhjgrq(Jhjgrq);
            }
            if (!"".equals(xxjd)) {
                zftzXmtzwh.setXxjd(new BigDecimal(xxjd.toString()));
            }
            zftzXmtzwh.setXmjyswh(xmjywsh);
            zftzXmtzwh.setXmkypfwh(xmkypfwh);
            zftzXmtzwh.setGspfwh(gspfwh);
            zftzXmtzwh.setXmcbsjpfwh(xmcbsjpfwh);
            zftzXmtzwh.setStshsws(stshsws);
            if (!"".equals(sdtzs)) {
                zftzXmtzwh.setSdtze((new BigDecimal(sdtzs.toString())).multiply(new BigDecimal("10000")));
            }
            zftzXmtzwh.setXmlxr(xmlxr);
            zftzXmtzwh.setXmlxdh(xmlxdh);
            zftzXmtzwh.setZt(1);
            zftzXmtzwh.setCzsj(new Date());
            //获取操作人
            zftzXmtzwh.setCzr(operator.getGuid().toString());
            if (!"".equals(sjkgrq)) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    //将string类型的日期转换为Date类型
                    parse = sdf.parse(sjkgrq.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                sd = new SimpleDateFormat("yyyyMMdd");
                //将Date类型格式化为想要的类型
                String format = sd.format(parse);
                Integer Sjkgrq = Integer.parseInt(format);
                zftzXmtzwh.setSjkgrq(Sjkgrq);
            }
            if (!"".equals(sjjgrq)) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    //将string类型的日期转换为Date类型
                    parse = sdf.parse(sjjgrq.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                sd = new SimpleDateFormat("yyyyMMdd");
                //将Date类型格式化为想要的类型
                String format = sd.format(parse);
                Integer Sjjgrq = Integer.parseInt(format);
                zftzXmtzwh.setSjjgrq(Sjjgrq);
            }
            baseDao.save(zftzXmtzwh);
            Long zftzXmtzwhId = zftzXmtzwh.getId();
//            ywid = String.valueOf(zftzXmtzwhId);
            /*************************************更新项目信息登记 台账维护 上传文件的文件表里面的filebsid*****************************************************/
            JSONArray fileID = JSONArray.parseArray(fileId);
            if (null != fileID) {
                for (int i = 0; i < fileID.size(); i++) {
                    JSONObject file = (JSONObject) fileID.get(i);
                    String fid = file.get("FILEID") == null ? "" : file.get("FILEID").toString();
                    String sql = " select filexl from zftz_file where guid = " + fid;
                    List<Map> mapsBySQL = baseDao.selectMapsBySQL(sql);
                    String updateSql = "";
//                    int
                    if (mapsBySQL.size() > 0 && null != mapsBySQL.get(0).get("FILEXL")) {
                        baseDao.executeBySql("delete from zftz_file a where a.filebstype = 14 and a.filexl = 99 and a.filebsid = " + xmid);
                        updateSql = "update zftz_file a set a.filebsid = " + xmid + " where guid = " + fid;
                    } else {
                        updateSql = "update zftz_file a set a.filebsid = " + zftzXmtzwhId + " where guid = " + fid;
                    }
                    baseDao.executeBySql(updateSql);
                }
            }
            /*************************************更新台账维护 资产登记 上传文件的文件表里面的filebsid*****************************************************/
            JSONArray zcdjfileid = JSONArray.parseArray(zcdjFileId);
            if (null != zcdjfileid) {
                for (int i = 0; i < zcdjfileid.size(); i++) {
                    JSONObject zcdjID = (JSONObject) zcdjfileid.get(i);
                    String zid = zcdjID.get("FILEID") == null ? "" : zcdjID.get("FILEID").toString();
                    String updateSql = "update zftz_file a set a.filebsid = " + zftzXmtzwhId + " where guid = " + zid;
                    baseDao.executeBySql(updateSql);
                }
            }
            /************************************************************项目资产登记数据*******************************************************************/

            JSONArray jsonArray = JSONArray.parseArray(dataArr);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jo = (JSONObject) jsonArray.get(i);
                String zcbh = jo.get("ZCBH") == null ? "" : jo.get("ZCBH").toString();
                String zcmc = jo.get("ZCMC") == null ? "" : jo.get("ZCMC").toString();
                String zclb = jo.get("ZCLB") == null ? "" : jo.get("ZCLB").toString();
                Object zcjz = jo.get("ZCJZ").toString();
                Object mj = jo.get("MJ").toString();
                //处理Iteger
                Object rzfs = jo.get("RZFS");

                //处理时间类型
                Object rzsj = jo.get("RZSJ");

                String bz = jo.get("BZ") == null ? "" : jo.get("BZ").toString();

                //资产登记
                ZftzXmzcdj zcdj = new ZftzXmzcdj();
                zcdj.setIdZftzXm(xmid);
                zcdj.setZcbh(zcbh);
                zcdj.setZcmc(zcmc);
                zcdj.setZclb(zclb);
                if (!"".equals(zcjz)) {
                    zcdj.setZcjz((new BigDecimal(zcjz.toString())).multiply(new BigDecimal("10000")));
                }
                if (!"".equals(mj)) {
                    zcdj.setMj(new BigDecimal(mj.toString()));
                }
                if (!"".equals(rzfs)) {
                    zcdj.setRzfs(Integer.parseInt(rzfs.toString()));
                }
                if (!"".equals(rzsj)) {
                    SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
                    Date parse4 = null;
                    try {
                        //将string类型的日期转换为Date类型
                        parse4 = sdf.parse(rzsj.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat sd3 = new SimpleDateFormat("yyyyMMdd");
                    //将Date类型格式化为想要的类型
                    String format4 = sd3.format(parse4);
                    Integer Rzsj = Integer.parseInt(format4);
                    zcdj.setRzsj(Rzsj);
                }
                zcdj.setBz(bz);
                zcdj.setZt(1);
                zcdj.setCzsj(new Date());
                zcdj.setCzr(operator.getGuid().toString());
                baseDao.save(zcdj);
            }
        } else {
            ywid = String.valueOf(id);
            //项目表
            ZftzXm zftzxm = (ZftzXm) baseDao.load(ZftzXm.class, Long.parseLong(id));
            zftzxm.setXmmc(xmmc);
           /* if (!"".equals(xmjd)) {
                zftzxm.setXmjd(Integer.parseInt(xmjd.toString()));
            }*/
            if (!"".equals(xmlx)) {
                zftzxm.setXmlx(Integer.parseInt(xmlx.toString()));
            }
            if (!"".equals(jsdw)) {
                zftzxm.setJsdw(Integer.parseInt(jsdw.toString()));
            }
            if (!"".equals(zgbm)) {
                zftzxm.setZgbm(Integer.parseInt(zgbm.toString()));
            }
            zftzxm.setXmfm(xmfm);
            if (!"".equals(sffb)) {
                zftzxm.setSffb(Integer.parseInt(sffb.toString()));
            }
            zftzxm.setZt(1);
            baseDao.saveOrUpdate(zftzxm);
            //取项目id
            String sql = "select id from zftz_xmtzwh where id_zftz_xm = " + Long.parseLong(id);
            List list = baseDao.selectBySql(sql);
            Long xmtzid = null;
            if (0 < list.size()) {
                xmtzid = ((BigDecimal) list.get(0)).longValue();
                ZftzXmtzwh zftzXmtzwh = (ZftzXmtzwh) baseDao.load(ZftzXmtzwh.class, xmtzid);
                //先保存项目台账维护历史表
                ZftzXmtzwhHis zftzxmtzwhhis = new ZftzXmtzwhHis();
//          ZftzXmtzwh zftzXmtzwh = new ZftzXmtzwh();
                //关联的项目表里面的id
                zftzxmtzwhhis.setId(zftzXmtzwh.getId());
                zftzxmtzwhhis.setIdZftzXm(Long.parseLong(id));
                zftzxmtzwhhis.setXmbh(zftzXmtzwh.getXmbh());
                zftzxmtzwhhis.setXmfm(zftzXmtzwh.getXmfm());
                zftzxmtzwhhis.setXmsslx(zftzXmtzwh.getXmsslx());
                zftzxmtzwhhis.setLxnd(zftzXmtzwh.getLxnd());
                zftzxmtzwhhis.setJsdz(zftzXmtzwh.getJsdz());
                zftzxmtzwhhis.setJhkgrq(zftzXmtzwh.getJhkgrq());
                zftzxmtzwhhis.setJhjgrq(zftzXmtzwh.getJhjgrq());
                zftzxmtzwhhis.setXxjd(zftzXmtzwh.getXxjd());
                zftzxmtzwhhis.setXmjyswh(zftzXmtzwh.getXmjyswh());
                zftzxmtzwhhis.setXmkypfwh(zftzXmtzwh.getXmkypfwh());
                zftzxmtzwhhis.setGspfwh(zftzXmtzwh.getGspfwh());
                zftzxmtzwhhis.setXmcbsjpfwh(zftzXmtzwh.getXmcbsjpfwh());
                zftzxmtzwhhis.setStshsws(zftzXmtzwh.getStshsws());
                zftzxmtzwhhis.setSdtze(zftzXmtzwh.getSdtze());
                zftzxmtzwhhis.setXmlxr(zftzXmtzwh.getXmlxr());
                zftzxmtzwhhis.setXmlxdh(zftzXmtzwh.getXmlxdh());
                zftzxmtzwhhis.setZt(zftzXmtzwh.getZt());
                zftzxmtzwhhis.setCzsj(zftzXmtzwh.getCzsj());
                //获取操作人
                zftzxmtzwhhis.setCzr(zftzXmtzwh.getCzr());
                zftzxmtzwhhis.setSjkgrq(zftzXmtzwh.getSjkgrq());
                zftzxmtzwhhis.setSjjgrq(zftzXmtzwh.getSjjgrq());
                zftzxmtzwhhis.setXgsj(new Date());
                zftzxmtzwhhis.setXgr(operator.getGuid().toString());
                baseDao.save(zftzxmtzwhhis);
                //关联的项目表里面的id
                zftzXmtzwh.setIdZftzXm(Long.parseLong(id));
                zftzXmtzwh.setXmbh(xmbh);
                zftzXmtzwh.setXmfm(xmfm);
                if (!"".equals(xmsslx)) {
                    zftzXmtzwh.setXmsslx(Integer.parseInt(xmsslx.toString()));
                }
                if (!"".equals(lxnd)) {
                    zftzXmtzwh.setLxnd(Integer.parseInt(lxnd.toString()));
                }
                zftzXmtzwh.setJsdz(jsdz);
                if (!"".equals(jhkgrq)) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        //将string类型的日期转换为Date类型
                        parse = sdf.parse(jhkgrq.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    sd = new SimpleDateFormat("yyyyMMdd");
                    //将Date类型格式化为想要的类型
                    String format = sd.format(parse);
                    Integer Jhkgrq = Integer.parseInt(format);
                    zftzXmtzwh.setJhkgrq(Jhkgrq);
                }
                if (!"".equals(jhjgrq)) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        //将string类型的日期转换为Date类型
                        parse = sdf.parse(jhjgrq.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    sd = new SimpleDateFormat("yyyyMMdd");
                    //将Date类型格式化为想要的类型
                    String format = sd.format(parse);
                    Integer Jhjgrq = Integer.parseInt(format);
                    zftzXmtzwh.setJhjgrq(Jhjgrq);
                }
                if (!"".equals(xxjd)) {
                    zftzXmtzwh.setXxjd(new BigDecimal(xxjd.toString()));
                }
                zftzXmtzwh.setXmjyswh(xmjywsh);
                zftzXmtzwh.setXmkypfwh(xmkypfwh);
                zftzXmtzwh.setGspfwh(gspfwh);
                zftzXmtzwh.setXmcbsjpfwh(xmcbsjpfwh);
                zftzXmtzwh.setStshsws(stshsws);
                if (!"".equals(sdtzs)) {
                    zftzXmtzwh.setSdtze((new BigDecimal(sdtzs.toString())).multiply(new BigDecimal("10000")));
                }
                zftzXmtzwh.setXmlxr(xmlxr);
                zftzXmtzwh.setXmlxdh(xmlxdh);
                zftzXmtzwh.setZt(1);
                zftzXmtzwh.setCzsj(new Date());
                //获取操作人
                zftzXmtzwh.setCzr(operator.getGuid().toString());
                if (!"".equals(sjkgrq)) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        //将string类型的日期转换为Date类型
                        parse = sdf.parse(sjkgrq.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    sd = new SimpleDateFormat("yyyyMMdd");
                    //将Date类型格式化为想要的类型
                    String format = sd.format(parse);
                    Integer Sjkgrq = Integer.parseInt(format);
                    zftzXmtzwh.setSjkgrq(Sjkgrq);
                }
                if (!"".equals(sjjgrq)) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        //将string类型的日期转换为Date类型
                        parse = sdf.parse(sjjgrq.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    sd = new SimpleDateFormat("yyyyMMdd");
                    //将Date类型格式化为想要的类型
                    String format = sd.format(parse);
                    Integer Sjjgrq = Integer.parseInt(format);
                    zftzXmtzwh.setSjjgrq(Sjjgrq);
                }
                baseDao.saveOrUpdate(zftzXmtzwh);

            } else {
                ZftzXmtzwh zftzXmtzwh = new ZftzXmtzwh();
                //关联的项目表里面的id
                zftzXmtzwh.setIdZftzXm(Long.parseLong(id));
                zftzXmtzwh.setXmbh(xmbh);
                zftzXmtzwh.setXmfm(xmfm);
                if (!"".equals(xmsslx)) {
                    zftzXmtzwh.setXmsslx(Integer.parseInt(xmsslx.toString()));
                }
                if (!"".equals(lxnd)) {
                    zftzXmtzwh.setLxnd(Integer.parseInt(lxnd.toString()));
                }
                zftzXmtzwh.setJsdz(jsdz);
                if (!"".equals(jhkgrq)) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        //将string类型的日期转换为Date类型
                        parse = sdf.parse(jhkgrq.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    sd = new SimpleDateFormat("yyyyMMdd");
                    //将Date类型格式化为想要的类型
                    String format = sd.format(parse);
                    Integer Jhkgrq = Integer.parseInt(format);
                    zftzXmtzwh.setJhkgrq(Jhkgrq);
                }
                if (!"".equals(jhjgrq)) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        //将string类型的日期转换为Date类型
                        parse = sdf.parse(jhjgrq.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    sd = new SimpleDateFormat("yyyyMMdd");
                    //将Date类型格式化为想要的类型
                    String format = sd.format(parse);
                    Integer Jhjgrq = Integer.parseInt(format);
                    zftzXmtzwh.setJhjgrq(Jhjgrq);
                }
                if (!"".equals(xxjd)) {
                    zftzXmtzwh.setXxjd(new BigDecimal(xxjd.toString()));
                }
                zftzXmtzwh.setXmjyswh(xmjywsh);
                zftzXmtzwh.setXmkypfwh(xmkypfwh);
                zftzXmtzwh.setGspfwh(gspfwh);
                zftzXmtzwh.setXmcbsjpfwh(xmcbsjpfwh);
                zftzXmtzwh.setStshsws(stshsws);
                if (!"".equals(sdtzs)) {
                    zftzXmtzwh.setSdtze((new BigDecimal(sdtzs.toString())).multiply(new BigDecimal("10000")));
                }
                zftzXmtzwh.setXmlxr(xmlxr);
                zftzXmtzwh.setXmlxdh(xmlxdh);
                zftzXmtzwh.setZt(1);
                zftzXmtzwh.setCzsj(new Date());
                //获取操作人
                zftzXmtzwh.setCzr(operator.getGuid().toString());
                if (!"".equals(sjkgrq)) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        //将string类型的日期转换为Date类型
                        parse = sdf.parse(sjkgrq.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    sd = new SimpleDateFormat("yyyyMMdd");
                    //将Date类型格式化为想要的类型
                    String format = sd.format(parse);
                    Integer Sjkgrq = Integer.parseInt(format);
                    zftzXmtzwh.setSjkgrq(Sjkgrq);
                }
                if (!"".equals(sjjgrq)) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        //将string类型的日期转换为Date类型
                        parse = sdf.parse(sjjgrq.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    sd = new SimpleDateFormat("yyyyMMdd");
                    //将Date类型格式化为想要的类型
                    String format = sd.format(parse);
                    Integer Sjjgrq = Integer.parseInt(format);
                    zftzXmtzwh.setSjjgrq(Sjjgrq);
                }
                baseDao.save(zftzXmtzwh);
                xmtzid = zftzXmtzwh.getId();
            }


            //项目台账维护表


/*******************************上传文件第一个tab页 更新filebsid************************************************/

            JSONArray fileID = JSONArray.parseArray(fileId);
            if (null != fileID) {
                for (int i = 0; i < fileID.size(); i++) {
                    JSONObject file = (JSONObject) fileID.get(i);
                    String fid = file.get("FILEID") == null ? "" : file.get("FILEID").toString();
                    String selsql = " select filexl from zftz_file where guid = " + fid;
                    List<Map> mapsBySQL = baseDao.selectMapsBySQL(selsql);
                    String updateSql = "";

                    if (mapsBySQL.size() > 0 && null != mapsBySQL.get(0).get("FILEXL")) {
//                        baseDao.executeBySql("delete from zftz_file a where a.filebstype = 14 and a.filexl = 99 and a.filebsid = " + id);
                        updateSql = "update zftz_file a set a.filebsid = " + id + " where guid = " + fid;
                    } else {
                        updateSql = "update zftz_file a set a.filebsid = " + xmtzid + " where guid = " + fid;
                    }
                    baseDao.executeBySql(updateSql);
                }
            }
            /*************************************更新台账维护 资产登记 上传文件的文件表里面的filebsid*****************************************************/
            JSONArray zcdjfileid = JSONArray.parseArray(zcdjFileId);
            if (null != zcdjfileid) {
                for (int i = 0; i < zcdjfileid.size(); i++) {
                    JSONObject zcdjID = (JSONObject) zcdjfileid.get(i);
                    String zid = zcdjID.get("FILEID") == null ? "" : zcdjID.get("FILEID").toString();
                    String updateSql = "update zftz_file a set a.filebsid = " + xmtzid + " where guid = " + zid;
                    baseDao.executeBySql(updateSql);
                }
            }
            /************************************************************项目资产登记数据*******************************************************************/
            String bz = "";
            JSONArray jsonArray = JSONArray.parseArray(dataArr);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jo = (JSONObject) jsonArray.get(i);
                String zcbh = jo.get("ZCBH") == null ? "" : jo.get("ZCBH").toString();
                String zcmc = jo.get("ZCMC") == null ? "" : jo.get("ZCMC").toString();
                String zclb = jo.get("ZCLB") == null ? "" : jo.get("ZCLB").toString();
                Object zcjz = jo.get("ZCJZ").toString();
                Object mj = jo.get("MJ").toString();
                //处理Iteger
                Object rzfs = jo.get("RZFS");

                //处理时间类型
                Object rzsj = jo.get("RZSJ");
                bz = jo.get("BZ") == null ? "" : jo.get("BZ").toString();

                //取资产登记id  去判断  是保存 还是更新

                Object zcdjID = jo.get("ID");
                ZftzXmzcdj zftzxmzcdj;
                if (null != zcdjID) {
                    zftzxmzcdj = (ZftzXmzcdj) baseDao.load(ZftzXmzcdj.class, Long.parseLong(zcdjID.toString()));
                } else {
                    zftzxmzcdj = new ZftzXmzcdj();
                }
                zftzxmzcdj.setIdZftzXm(Long.parseLong(id));
                zftzxmzcdj.setZcbh(zcbh);
                zftzxmzcdj.setZcmc(zcmc);
                zftzxmzcdj.setZclb(zclb);
                if (!"".equals(zcjz)) {
                    zftzxmzcdj.setZcjz((new BigDecimal(zcjz.toString())).multiply(new BigDecimal("10000")));
                }
                if (!"".equals(mj)) {
                    zftzxmzcdj.setMj(new BigDecimal(mj.toString()));
                }
                if (!"".equals(rzfs)) {
                    zftzxmzcdj.setRzfs(Integer.parseInt(rzfs.toString()));
                }
                if (!"".equals(rzsj)) {
                    SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
                    Date parse4 = null;
                    try {
                        //将string类型的日期转换为Date类型
                        parse4 = sdf.parse(rzsj.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat sd3 = new SimpleDateFormat("yyyyMMdd");
                    //将Date类型格式化为想要的类型
                    String format4 = sd3.format(parse4);
                    Integer Rzsj = Integer.parseInt(format4);
                    zftzxmzcdj.setRzsj(Rzsj);
                }
                zftzxmzcdj.setBz(bz);
                zftzxmzcdj.setZt(1);
                zftzxmzcdj.setCzsj(new Date());
                zftzxmzcdj.setCzr(operator.getGuid().toString());
                baseDao.saveOrUpdate(zftzxmzcdj);
            }
        }
        result.setContent(ywid);
        result.setSuccess(true);
    }

    @Override
    public List getXMData(String xmmc, String XMLX, String XMSSLX, HttpSession session) {
        String mc = "";
        try {
            mc = new String(xmmc.getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        YhglYwYhyy userinfo = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
        StringBuffer sb = new StringBuffer("select b.XMBH,\n" +
                "       a.XMMC,\n" +
                "       a.XMLX,\n" +
                "case\n" +
                "         when substr(nvl(b.sjkgrq, '99991231'), 0, 4) >\n" +
                "              to_char(sysdate, 'yyyy') then\n" +
                "          1\n" +
                "         when substr(nvl(b.sjkgrq, '99991231'), 0, 4) =\n" +
                "              to_char(sysdate, 'yyyy') then\n" +
                "          2\n" +
                "         when substr(nvl(b.sjjgrq, '99991231'), 0, 4) >=\n" +
                "              to_char(sysdate, 'yyyy') then\n" +
                "          3\n" +
                "         else\n" +
                "          4\n" +
                "       end xmjd," +
                "       b.XMSSLX,\n" +
                "       b.LXND,\n" +
                "       a.JSDW,\n" +
                "       a.ID,\n" +
                "       (select mc from v_zftz_jsdw v where a.jsdw = v.id) mc,\n" +
                "       \n" +
                "       (select (nvl(c.ZTZ_JZAZTZ, 0) + nvl(c.ZTZ_DTTZ, 0) +\n" +
                "               nvl(c.ZTZ_QTTZ, 0) + nvl(c.ZTZ_SBTZ, 0)) / 10000 ZTZ\n" +
                "          from ZFTZ_XMZJLYDJ c\n" +
                "         where c.id_zftz_xm = a.id\n" +
                "           and c.zt = 1) ZTZ,\n" +
                "       \n" +
                "       b.XMFM,\n" +
                "       a.ZGBM,\n" +
                "       to_char(to_date(b.JHKGRQ, 'yyyyMMdd'), 'yyyy-mm-dd') JHKGRQ,\n" +
                "       (select (nvl(d.ZTZ_JZAZTZ, 0) + nvl(d.ZTZ_DTTZ, 0) +\n" +
                "               nvl(d.ZTZ_SBTZ, 0) + nvl(d.ZTZ_QTTZ, 0)) / 10000\n" +
                "          from ZFTZ_XMQQCH d\n" +
                "         where d.id_zftz_xm = a.id\n" +
                "           and d.zt = 1) TZGS,\n" +
                "       \n" +
                "       b.JSDZ,\n" +
                "       to_char(to_date(b.JHJGRQ, 'yyyyMMdd'), 'yyyy-mm-dd') JHJGRQ,\n" +
                "       (select vvv.GSTZ\n" +
                "          from (select v.id_zftz_xm, sum(v.GSTZ) GSTZ, sum(v.JAGSTZ) JAGSTZ\n" +
                "                  from (select y.id_zftz_xm,\n" +
                "                               sum(y.gstzje) GSTZ,\n" +
                "                               case\n" +
                "                                 when y.tzefl in (1, 2) then\n" +
                "                                  sum(y.gstzje)\n" +
                "                                 else\n" +
                "                                  0\n" +
                "                               end JAGSTZ\n" +
                "                          from v_zftz_gcfy y\n" +
                "                         group by y.id_zftz_xm, y.tzefl) v\n" +
                "                 group by v.id_zftz_xm) vvv\n" +
                "         where vvv.id_zftz_xm = a.id)/10000 GSTZ,\n" +
                "       \n" +
                "       b.XMJYSWH XMJYWSH,\n" +
                "       b.XXJD,\n" +
                "       (select vvv.JAGSTZ\n" +
                "          from (select v.id_zftz_xm, sum(v.GSTZ) GSTZ, sum(v.JAGSTZ) JAGSTZ\n" +
                "                  from (select y.id_zftz_xm,\n" +
                "                               sum(y.gstzje) GSTZ,\n" +
                "                               case\n" +
                "                                 when y.tzefl in (1, 2) then\n" +
                "                                  sum(y.gstzje)\n" +
                "                                 else\n" +
                "                                  0\n" +
                "                               end JAGSTZ\n" +
                "                          from v_zftz_gcfy y\n" +
                "                         group by y.id_zftz_xm, y.tzefl) v\n" +
                "                 group by v.id_zftz_xm) vvv\n" +
                "         where vvv.id_zftz_xm = a.id)/10000 JAGSTZ,\n" +
                "       \n" +
                "       b.XMKYPFWH,\n" +
                "       b.STSHSWS,\n" +
                "       b.SDTZE / 10000 SDTZS,\n" +
                "       b.GSPFWH,\n" +
                "       b.XMCBSJPFWH,\n" +
                "       a.SFFB,\n" +
                "       b.XMLXR,\n" +
                "       to_char(to_date(b.SJKGRQ, 'yyyyMMdd'), 'yyyy-mm-dd') SJKGRQ,\n" +
                "       b.XMLXDH,\n" +
                "       to_char(to_date(b.SJJGRQ, 'yyyyMMdd'), 'yyyy-mm-dd') SJJGRQ,\n" +
                "       (select t1.jsnr\n" +
                "          from zftz_xmgs t1\n" +
                "         where t1.id_zftz_xm = a.id\n" +
                "           and t1.zt = 1) as jsnr,\n" +
                "       b.id tzwhid\n" +
                "\n" +
                "  from ZFTZ_XMTZWH b, ZFTZ_XM a\n" +
                " where a.ID = b.ID_ZFTZ_XM\n" +
                "   and b.zt = 1\n" +
                /*"   and a.jsdw = " + userinfo.getSzdwid() +*/
                "and a.jsdw in (select entid from v_zftz_yhtoenter where guid = " + userinfo.getGuid() + ")\n");
        if (!"".equals(mc)) {
            sb.append(" and a.xmmc like '%" + mc + "%'");
        }
        if (!"".equals(XMLX)) {
            sb.append(" and a.xmlx = " + XMLX);
        }
        if (!"".equals(XMSSLX)) {
            sb.append(" and b.xmsslx = " + XMSSLX);
        }
        sb.append("   order by b.czsj");
        List list = baseDao.selectMapsBySQL(sb.toString());
        return list;
    }

    @Override
    public List getZGBMTree(HttpSession session) {
        YhglYwYhyy userinfo = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
        List dataList = new ArrayList();
//        String sql = "select mc as name,id as ID,bm as BM ,fjbm as PID from v_zftz_zgdw ";
        String sql = "select d.mc as name,d.id as ID,d.bm as BM ,d.fjbm as PID from v_zftz_zgdw d where "
                + " d.id in (select p_entid from v_zftz_yhtoenter where guid="+userinfo.getGuid()+")";
        List list =  baseDao.selectBySql(sql);//得到List集合
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                Map map = new HashMap();
                map.put("id", obj[1]);
                map.put("name", obj[0]);
                map.put("pId", obj[3]);
                dataList.add(map);
            }
        }
        return dataList;
    }

    @Override
    public List getZGBMNOTree() {
        List dataList = new ArrayList();
        String sql = "select mc as name,id as ID,bm as BM ,fjbm as PID from v_zftz_zgdw ";
/*        String sql = "select d.mc as name,d.id as ID,d.bm as BM ,d.fjbm as PID from v_zftz_zgdw d where "
                + " d.id in (select p_entid from v_zftz_yhtoenter where guid="+userinfo.getGuid()+")";*/
        List list =  baseDao.selectBySql(sql);//得到List集合
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                Map map = new HashMap();
                map.put("id", obj[1]);
                map.put("name", obj[0]);
                map.put("pId", obj[3]);
                dataList.add(map);
            }
        }
        return dataList;
    }

    @Override
    public List getJSDWTree(HttpSession session) {
        YhglYwYhyy userinfo = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
        YhglYwYh operator = (YhglYwYh) session.getAttribute(StaticData.LOGINUSERINFO);
        String yhlx = operator.getYhlx();
        String sql = "";
        List dataList = new ArrayList();
        if ("1".equals(yhlx)) {
            sql = "select mc as name,id as ID,bm as BM ,fjbm as PID from v_zftz_jsdw where  id  in (select entid from v_zftz_yhtoenter where guid=" + userinfo.getGuid()+")";
        } else {
            sql = "select mc as name,id as ID,bm as BM ,fjbm as PID from v_zftz_jsdw where id in (select entid from v_zftz_yhtoenter where guid=" + userinfo.getGuid()+")";
        }
        List list = baseDao.selectBySql(sql);//得到List集合
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                Map map = new HashMap();
                map.put("id", obj[1]);
                map.put("name", obj[0]);
                map.put("pId", obj[3]);
                dataList.add(map);
            }
        }
        return dataList;
    }

    @Override
    public List getJSDWNoTree() {
        List dataList = new ArrayList();
        String sql = "select mc as name,id as ID,bm as BM ,fjbm as PID from v_zftz_jsdw";
        List list = baseDao.selectBySql(sql);//得到List集合
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                Map map = new HashMap();
                map.put("id", obj[1]);
                map.put("name", obj[0]);
                map.put("pId", obj[3]);
                dataList.add(map);
            }
        }
        return dataList;
    }

    @Override
    public List query(String xmmc, String xmlx, String xmsslx, Result result) {
        StringBuffer sb = new StringBuffer();
        String sql = "select b.XMBH,\n" +
                "       a.XMMC,\n" +
                "       a.XMLX,\n" +
                "       a.XMJD,\n" +
                "       b.XMSSLX,\n" +
                "       b.LXND,\n" +
                "       a.JSDW,\n" +
                "       a.ID,\n" +
                "       v.mc,\n" +
                "       nvl(c.ZTZ_JZAZTZ, 0) + nvl(c.ZTZ_DTTZ, 0) + nvl(c.ZTZ_QTTZ, 0) +\n" +
                "       nvl(c.ZTZ_SBTZ, 0) ZTZ\n" +
                "  from ZFTZ_XM a, \n" +
                "  ZFTZ_XMTZWH b, \n" +
                "  ZFTZ_XMZJLYDJ c, \n" +
                "  v_zftz_jsdw v\n" +
                " where a.ID = b.ID_ZFTZ_XM\n" +
                "   and a.ID = c.ID_ZFTZ_XM\n" +
                "   and a.jsdw = v.id";
        sb.append(sql);
        if (!"".equals(xmmc)) {
            sb.append(" and a.xmmc like '%" + xmmc + "%'");
        }
        if (!"".equals(xmlx)) {
            sb.append(" and a.xmlx = " + xmlx);
        }
        if (!"".equals(xmsslx)) {
            sb.append(" and b.xmsslx = " + xmsslx);
        }
        List list = baseDao.selectMapsBySQL(sb.toString());
        return list;
    }

    @Override
    public void delete(List<Integer> ids, Result result) {
        if (ids.size() > 0) {
            for (int m = 0; m < ids.size(); m++) {
                int id = ids.get(m);
                //项目
               /* String xmSql = "update zftz_xm set zt = 0 where id = " + id;
                baseDao.executeBySql(xmSql);*/

                //项目台账
                String sql = "select id from zftz_xmtzwh where id_zftz_xm = " + id;
                List list = baseDao.selectBySql(sql);
                Long xmtzid = null;
                if (null != list && 0 < list.size()) {
                    xmtzid = ((BigDecimal) list.get(0)).longValue();
                }
                String xmtzSql = "update zftz_xmtzwh set zt = 0 where id = " + xmtzid;
                baseDao.executeBySql(xmtzSql);
                //资产登记
                String sqll = "select id from zftz_xmzcdj where id_zftz_xm = " + id;
                List list1 = baseDao.selectBySql(sqll);
                Long xmzcdj = null;
                if (null != list1 && 0 < list1.size()) {
                    for (int i = 0; i < list1.size(); i++) {
                        xmzcdj = ((BigDecimal) list1.get(i)).longValue();
                        String xmZcdjSql = "update zftz_xmzcdj set zt = 0 where id = " + xmzcdj;
                        baseDao.executeBySql(xmZcdjSql);
                    }
                }
            }
        }
        result.setSuccess(true);
        result.setContent("删除成功");
    }

    @Override
    public void deleteGSMX(List<Integer> ids, Result result) {
        if (ids.size() > 0) {
            for (int m = 0; m < ids.size(); m++) {
                String id = ids.get(m).toString();
                ZftzXmgsmx zftzXmgsmx = (ZftzXmgsmx) baseDao.load(ZftzXmgsmx.class, Long.parseLong(id));
                baseDao.delete(zftzXmgsmx);
            }
        }
    }

    @Override
    public List getJclbTreeData() {
        List dataList = new ArrayList();
        String sql = "select mc as name,id as ID,bm as BM ,fjbm as PID from zftz_xxb t where t.lx='zclb'";
        List list = baseDao.selectBySql(sql);//得到List集合
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                Map map = new HashMap();
                map.put("id", obj[2]);
                map.put("name", obj[0]);
                map.put("pId", obj[3]);
                dataList.add(map);
            }
        }
        return dataList;
    }

    @Override
    public List getRzfsTreeData() {
        List dataList = new ArrayList();
        String sql = "select mc as name,id as ID,bm as BM ,fjbm as PID from zftz_xxb t where t.lx='rzfs'";
        List list = baseDao.selectBySql(sql);//得到List集合
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                Map map = new HashMap();
                map.put("id", obj[2]);
                map.put("name", obj[0]);
                map.put("pId", obj[3]);
                dataList.add(map);
            }
        }
        return dataList;
    }

    @Override
    public List getZcdjData(String id) {
        List<Map> list = new ArrayList();
        if (null != id) {
            String sql = "select a.id,\n" +
                    "       a.zcbh,\n" +
                    "       a.zcmc,\n" +
                    "       a.zclb,\n" +
                    "       a.zcjz/10000 zcjz,\n" +
                    "       a.mj,\n" +
                    "       a.rzfs,\n" +
                    "       to_char(to_date(a.rzsj, 'yyyyMMdd'), 'yyyy-mm-dd') rzsj,\n" +
                    "       a.bz,\n" +
                    "       a.zt,\n" +
                    "       a.czsj,\n" +
                    "       a.czr ,\n" +
                    "       b.mc zclbmc,\n" +
                    "       c.mc rzfsmc\n" +
                    "  from zftz_xmzcdj a\n" +
                    "  left join (select bm, mc from zftz_xxb t where t.lx = 'zclb') b\n" +
                    "    on a.zclb = b.bm\n" +
                    "  left join (select bm, mc from zftz_xxb t where t.lx = 'rzfs') c\n" +
                    "    on a.rzfs = c.bm\n" +
                    " where a.id_zftz_xm = " + Long.parseLong(id) +
                    "   and a.zt = 1\n";
            list = baseDao.selectMapsBySQL(sql);
        } else {

        }
        return list;
    }

    @Override
    public void deleteZcdj(String id, Result result) {
        String[] guids = id.split(",");
        for (int i = 0; i < guids.length; i++) {
            if ("-1".equals(guids[i])) {

            } else {
                String sql = "update zftz_xmzcdj set zt = 0 where id = " + guids[i];
                baseDao.executeBySql(sql);
            }
        }
        result.setSuccess(true);
        result.setContent("删除成功");
    }

    @Override
    public List getXMJDTree() {
        List dataList = new ArrayList();
        /*String hql = "from Xxb x where x.lx='xmjd'";
        return baseDao.selectByHql(hql);*/
        String sql = "select mc as name,id as ID,bm as BM ,fjbm as PID from zftz_xxb t where t.lx='xmjd'";
        List list = baseDao.selectBySql(sql);//得到List集合
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                Map map = new HashMap();
                map.put("id", obj[2]);
                map.put("name", obj[0]);
                map.put("pId", obj[3]);
                dataList.add(map);
            }
        }
        return dataList;
    }

    @Override
    public List getXMJD(String id) {
        String sql = "";
        if ("".equals(id)) {

        } else {
            sql = "select case\n" +
                    "         when substr(nvl(t.sjkgrq, '99991231'), 0, 4) >\n" +
                    "              to_char(sysdate, 'yyyy') then\n" +
                    "          1\n" +
                    "         when substr(nvl(t.sjkgrq, '99991231'), 0, 4) =\n" +
                    "              to_char(sysdate, 'yyyy') then\n" +
                    "          2\n" +
                    "         when substr(nvl(t.sjjgrq, '99991231'), 0, 4) >=\n" +
                    "              to_char(sysdate, 'yyyy') then\n" +
                    "          3\n" +
                    "         else\n" +
                    "          4\n" +
                    "       end xmjd\n" +
                    "  from zftz_xmtzwh t\n" +
                    " where t.zt = 1\n" +
                    "   and t.id_zftz_xm = " + id;
        }
        List list = baseDao.selectMapsBySQL(sql);
        return list;
    }

    @Override
    public List getXMLXTree() {
        List dataList = new ArrayList();
        String sql = "select mc as name,id as ID,bm as BM ,fjbm as PID from zftz_xxb t where t.lx='xmlx'";
        List list = baseDao.selectBySql(sql);//得到List集合
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                Map map = new HashMap();
                map.put("id", obj[2]);
                map.put("name", obj[0]);
                map.put("pId", obj[3]);
                dataList.add(map);
            }
        }
        return dataList;
    }

    @Override
    public List getXmsslxTree() {
        List dataList = new ArrayList();
        String sql = "select mc as name,id as ID,bm as BM ,fjbm as PID from zftz_xxb t where t.lx='xmsslx'";
        List list = baseDao.selectBySql(sql);//得到List集合
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                Map map = new HashMap();
                map.put("id", obj[2]);
                map.put("name", obj[0]);
                map.put("pId", obj[3]);
                dataList.add(map);
            }
        }
        return dataList;
    }

    @Override
    public List selectXmlx() {
        String sql = "select * from zftz_xxb t where t.lx='xmlx'";
        return baseDao.selectMapsBySQL(sql);
    }

    @Override
    public List selectXmmc() {
        String sql = "select id,xmmc name,0 pid from zftz_xm where zt = 1";
        List list = baseDao.selectMapsBySQL(sql);
        return list;
    }

    @Override
    public List selectXmsslx() {
        String sql = "select * from zftz_xxb t where t.lx='xmsslx'";
        return baseDao.selectMapsBySQL(sql);
    }

    @Override
    public List selectXmjd() {
        String sql = "select * from zftz_xxb t where t.lx='xmjd'";
        return baseDao.selectMapsBySQL(sql);
    }

    @Override
    public List getXmmcTree(String id, HttpSession session) {
        YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
        List dataList = new ArrayList();
        if (null == id) {
           /* String sql = "select to_char(t.id) as id,\n" +
                    "       t.xmmc as name,\n" +
                    "       'jsdw' || t.jsdw as pid,\n" +
                    "       1 as isleaf\n" +
                    "  from ZFTZ_XM t\n" +
                    " where t.zt = 1\n" +
                    "   and t.jsdw is not null\n" +
                    "   and t.id not in\n" +
                    "       (select to_char(b.id_zftz_xm) from zftz_xmgsyjzx b where b.zt = 1)\n" +
                    "   and t.id not in\n" +
                    "       (select to_char(s.id_zftz_xm) from zftz_xmjgjs s where s.zt = 1)\n" +
                    "and t.jsdw in(select entid from v_zftz_yhtoenter where guid = " + yhglywyhyy.getGuid() + ")" +
                    "union all\n" +
                    "select 'jsdw' || w.id as id, w.mc as name, to_char('jsdw' || fjbm) as pid, 0 as isleaf\n" +
                    "  from v_zftz_jsdw w\n" +
                    " where w.id in (select x.jsdw\n" +
                    "                  from zftz_xm x\n" +
                    "                 where x.zt = '1'\n" +
                    "                   and x.jsdw is not null\n" +
                    "                   and x.id not in (select to_char(b.id_zftz_xm)\n" +
                    "                                      from zftz_xmgsyjzx b\n" +
                    "                                     where b.zt = 1)\n" +
                    "                   and x.id not in (select to_char(s.id_zftz_xm)\n" +
                    "                                      from zftz_xmjgjs s\n" +
                    "                                     where s.zt = 1))" +
                    "and w.id in(select entid from v_zftz_yhtoenter where guid = " + yhglywyhyy.getGuid() + ")";*/
            String sql = "with xmTable as\n" +
                    " (select t.id, t.xmmc, t.jsdw\n" +
                    "    from zftz_xm t\n" +
                    "   where t.zt = 1\n" +
                    "      and t.id not in\n" +
                    "       (select to_char(b.id_zftz_xm) from zftz_xmgsyjzx b where b.zt = 1)\n" +
                    "   and t.id not in\n" +
                    "       (select to_char(s.id_zftz_xm) from zftz_xmjgjs s where s.zt = 1)\n" +
                    "   and t.jsdw in (select entid from v_zftz_yhtoenter where guid = " + yhglywyhyy.getGuid() + ")),\n" +
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
            List list = baseDao.selectBySql(sql);//得到List集合
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
        } else if ("0".equals(id)) {
            String sql = "select t.id,t.xmmc as name,0 as pid from ZFTZ_XM t where t.zt = 1 nd t.jsdw  = " + yhglywyhyy.getSzdwid();
            List list = baseDao.selectBySql(sql);//得到List集合
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
        }
        return dataList;
    }

    @Override
    public List getXmtzwhTree(String id, HttpSession session) {
        List dataList = new ArrayList();
        YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
       /* String sql = "select to_char(t.id) as id,\n" +
                "       t.xmmc as name,\n" +
                "       'jsdw' || t.jsdw as pid,\n" +
                "       1 as isleaf\n" +
                "  from ZFTZ_XM t\n" +
                " where t.zt = 1\n" +
                "   and t.jsdw in (select entid from v_zftz_yhtoenter where guid = " + yhglywyhyy.getGuid() + ")\n" +
                "   and t.jsdw is not null\n" +
                "   and t.id not in\n" +
                "       (select to_char(b.id_zftz_xm) from zftz_xmtzwh b where b.zt = 1)\n" +
                "   and t.id not in\n" +
                "       (select to_char(s.id_zftz_xm) from zftz_xmjgjs s where s.zt = 1)\n" +
                "union all\n" +
                "select 'jsdw' || w.id as id, w.mc as name, to_char('jsdw' || fjbm) as pid, 0 as isleaf\n" +
                "  from v_zftz_jsdw w\n" +
                " where w.id in (select x.jsdw\n" +
                "                  from zftz_xm x\n" +
                "                 where x.zt = '1'\n" +
                "                   and x.jsdw in\n" +
                "                       (select entid from v_zftz_yhtoenter where guid = " + yhglywyhyy.getGuid() + ")\n" +
                "                   and x.jsdw is not null\n" +
                "                   and x.id not in (select to_char(b.id_zftz_xm)\n" +
                "                                      from zftz_xmtzwh b\n" +
                "                                     where b.zt = 1)\n" +
                "                   and x.id not in (select to_char(s.id_zftz_xm)\n" +
                "                                      from zftz_xmjgjs s\n" +
                "                                     where s.zt = 1))";*/
        String sql = "with xmTable as\n" +
                " (select t.id, t.xmmc, t.jsdw\n" +
                "    from zftz_xm t\n" +
                "   where t.zt = 1\n" +
                "    and t.jsdw in (select entid from v_zftz_yhtoenter where guid = " + yhglywyhyy.getGuid() + ")\n" +
                "   and t.jsdw is not null\n" +
                "   and t.id not in\n" +
                "       (select to_char(b.id_zftz_xm) from zftz_xmtzwh b where b.zt = 1)\n" +
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
        List list = baseDao.selectBySql(sql);//得到List集合
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
    }

    @Override
        public List loadxmData(String id, HttpSession session) {
        YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
        String sql = "select x.xmbh,\n" +
                "       x.xmjd,\n" +
                "       x.xmlx,\n" +
                "       x.zgbm,\n" +
                "       x.lxnd,\n" +
                "       x.ztz_zjlydj/10000 ztz,\n" +
                "       x.jsdw,\n" +
                "       x.jhkgrq,\n" +
                "       x.ztz_qqch/10000 TZGS,\n" +
                "       x.jhjgrq,\n" +
                "       x.gsztz/10000 GSTZ,\n" +
                "       x.xmjyswh,\n" +
                "       x.xxjd,\n" +
                "       x.gsjaztz/10000 JAGSTZ ,\n" +
                "       x.xmkypfwh,\n" +
                "       x.stshsws,\n" +
                "       x.tzxe/10000 sdtzs,\n" +
                "       x.gspfwh,\n" +
                "       x.xmcbsjpfwh,\n" +
                "       nvl(x.sffb,0) sffb,\n" +
                "       x.xmlxr,\n" +
                "       x.sjkgrq,\n" +
                "       x.xmlxdh,\n" +
                "       x.sjjgrq,\n" +
                "       x.xmghxz jsdz,\n" +
                "       x.jsnr\n" +
                "  from v_zftz_xmjbxx x\n" +
                " where id_zftz_xm = " + id;
        return baseDao.selectMapsBySQL(sql);
    }

    @Override
    public List loadXMData(String id) {
//        String sql = "select b.*,a.XMGHXZ from ZFTZ_XMZJLYDJ a ,ZFTZ_XM b where a.ID_ZFTZ_XM = b.ID and a.zt = 1 and b.ID = " + id;
//        String sql = "select * from v_zftz_xmjbxx a where a.id_zftz_xm = " + id;
        /*String sql = "select b.*,\n" +
                "       (select m.xmghxz\n" +
                "          from ZFTZ_XMZJLYDJ m, zftz_xm n\n" +
                "         where m.id_zftz_xm = n.id\n" +
                "           and m.zt = 1\n" +
                "           and n.id = " + id + ") xmghxz\n" +
                "  from ZFTZ_XM b\n" +
                " where b.ID = " + id;*/
        String sql = "select x.tzxe/10000 sdtzs,x.* from v_zftz_xmjbxx x where x.id_zftz_xm = " + id;
        return baseDao.selectMapsBySQL(sql);
    }

    @Override
    public void saveXmgs(HttpServletRequest request, String json, String xmgsyjid, Result result, String xmgszxd) {
        String zxywid = "";
        //获取用户信息
        YhglYwYhyy operator = (YhglYwYhyy) request.getSession().getAttribute(StaticData.LOGINUSER);
        JSONObject jsonObject = JSONObject.parseObject(json);
        String xmid = jsonObject.get("MC") == null ? "" : jsonObject.get("MC").toString();
        Long id = null;
        if (!"".equals(xmid)) {
            id = Long.parseLong(xmid);
        }
        String jsnr = jsonObject.get("JSNR") == null ? "" : jsonObject.get("JSNR").toString();
        String fkyj = jsonObject.get("FKYJ") == null ? "" : jsonObject.get("FKYJ").toString();
        if ("".equals(xmgsyjid)) {
            ZftzXmgsyjzx zftzXmgsyjzx = new ZftzXmgsyjzx();
            zftzXmgsyjzx.setIdZftzXm(id);
            zftzXmgsyjzx.setJsnr(jsnr);
            zftzXmgsyjzx.setFkyj(fkyj);
            zftzXmgsyjzx.setLchj(-1);
            zftzXmgsyjzx.setZt(1);
            zftzXmgsyjzx.setCzsj(new Date());
            zftzXmgsyjzx.setCzr(operator.getGuid().toString());
            baseDao.save(zftzXmgsyjzx);
            Long ID = zftzXmgsyjzx.getId();
            zxywid = ID.toString();
            /*************************************更新项目概算征询单  上传文件的文件表里面的filebsid*****************************************************/
            JSONArray fileID = JSONArray.parseArray(xmgszxd);
            if (null != fileID) {
                for (int i = 0; i < fileID.size(); i++) {
                    JSONObject file = (JSONObject) fileID.get(i);
                    String fid = file.get("FILEID") == null ? "" : file.get("FILEID").toString();
                    String updateSql = "update zftz_file a set a.filebsid = " + ID + " where guid = " + fid;
                    baseDao.executeBySql(updateSql);
                }
            }
        } else {
            zxywid = xmgsyjid;
            long parseLong = Long.parseLong(xmgsyjid);
            String sql = "select lchj from ZFTZ_XMGSYJZX where id = " + xmgsyjid;
            List list = baseDao.selectBySql(sql);
            Integer integer = null;
            if (0 < list.size()) {
                String str = list.get(0).toString();
                if (!"".equals(str)) {
                    integer = Integer.parseInt(str);
                }
            }
            ZftzXmgsyjzx zftzxmgsyjzx = (ZftzXmgsyjzx) baseDao.load(ZftzXmgsyjzx.class, parseLong);
            zftzxmgsyjzx.setIdZftzXm(id);
            zftzxmgsyjzx.setJsnr(jsnr);
            zftzxmgsyjzx.setFkyj(fkyj);
            zftzxmgsyjzx.setLchj(integer);
            zftzxmgsyjzx.setZt(1);
            zftzxmgsyjzx.setCzsj(new Date());
            zftzxmgsyjzx.setCzr(operator.getGuid().toString());
            baseDao.saveOrUpdate(zftzxmgsyjzx);
            /*************************************更新项目概算征询单  上传文件的文件表里面的filebsid*****************************************************/
            JSONArray fileID = JSONArray.parseArray(xmgszxd);
            if (null != fileID) {
                for (int i = 0; i < fileID.size(); i++) {
                    JSONObject file = (JSONObject) fileID.get(i);
                    String fid = file.get("FILEID") == null ? "" : file.get("FILEID").toString();
                    String updateSql = "update zftz_file a set a.filebsid = " + xmgsyjid + " where guid = " + fid;
                    baseDao.executeBySql(updateSql);
                }
            }
        }
        result.setSuccess(true);
        result.setContent(zxywid);
    }

    @Override
    public List getXmgsyjData(String xmmc, String zt, HttpSession session) {
        String mc = "";
        try {
            mc = new String(xmmc.getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
        String checkSQL = "select xmid from zftz_shjl where sxlx = 2 and clyhid = " + yhglywyhyy.getGuid();
        StringBuffer sql = new StringBuffer();
        /*sql.append("select e.id yjid,\n" +
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
                "       (select (nvl(c.ztz_jzaztz,0)+nvl(c.ztz_sbtz,0)+nvl(c.ztz_dttz,0)+nvl(c.ztz_qttz,0))/10000\n" +
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
                "   and (a.jsdw = " + yhglywyhyy.getSzdwid() +
                " or\n" +
                "       e.id in (select T.YWID\n" +
                "                   from zftz_gg_ryqzyh t\n" +
                "                  where t.yhid = " + yhglywyhyy.getGuid() +
                "                    and t.sxlx = 2\n" +
                "                    and t.ssyybm = 'ZFTZ'))");*/
        sql.append("select e.id yjid,\n" +
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
                "   and a.jsdw in (select entid from v_zftz_yhtoenter where guid = " + yhglywyhyy.getGuid() + ")");
        if (!"".equals(zt)) {
            Long ll = Long.parseLong(zt);
            if (ll != 2) {
                sql.append("   and e.id in (select T.YWID\n" +
                        "                                   from zftz_gg_ryqzyh t\n" +
                        "                                  where t.yhid = " + yhglywyhyy.getGuid() +
                        "                                    and t.sxlx = 2\n" +
                        "                                    and t.ssyybm = 'ZFTZ')");
            }
        }
//               );
        Long Zt = null;
        if ("".equals(zt)) {
            sql.append("and e.lchj not in (0,-1)");
        } else {
            Zt = Long.parseLong(zt);
            if (Zt == 0 || Zt == -1) {
                sql.append("and e.lchj = " + Zt);
            }
            if (Zt != 0 && Zt != -1 && Zt != 2) {
                sql.append("and e.lchj not in(0,-1)");
            }
            if (Zt == 2) {
                sql.append("and e.id in (" + checkSQL + ")");
            }
        }
        if (!"".equals(mc)) {
            sql.append(" and a.xmmc like " + "'%" + mc + "%'");
        }
        sql.append("    order by e.czsj");
        List list = baseDao.selectMapsBySQL(sql.toString());
        return list;
    }

    @Override
    public List getXmGSYJData(String xmmc, String zt, HttpSession session) {
        String mc = "";
        try {
            mc = new String(xmmc.getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
        StringBuffer sql = new StringBuffer();
        sql.append("select e.id yjid,\n" +
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
                        "       (select (nvl(c.ztz_jzaztz,0)+nvl(c.ztz_sbtz,0)+nvl(c.ztz_dttz,0)+nvl(c.ztz_qttz,0))/10000\n" +
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
                        "           and d.zt = 1) as qqchid,\n" +
                        "case when e.lchj=0 then '已审核'\n" +
                        "when e.lchj=-1 and (select count(*) from zftz_shjl t where t.xmid=e.id and t.sxlx=2)>=1 then '退回' \n" +
                        "  when  e.lchj=-1 and (select count(*) from zftz_shjl t where t.xmid=e.id and t.sxlx=2)=0 then '未审核'\n" +
                        "    else '流程中' end zt1"+
                        "  from zftz_xmgsyjzx e\n" +
                        "  left join ZFTZ_XM a\n" +
                        "    on e.id_zftz_xm = a.id\n" +
                        " where e.zt = 1\n"+
                /*"   and (a.jsdw = " + yhglywyhyy.getSzdwid() +
                " or\n" +
                "       e.id in (select T.YWID\n" +
                "                   from zftz_gg_ryqzyh t\n" +
                "                  where t.yhid = " + yhglywyhyy.getGuid() +
                "                    and t.sxlx = 2\n" +
                "                    and t.ssyybm = 'ZFTZ'))"*/
                "   and a.jsdw in (select entid from v_zftz_yhtoenter where guid = " + yhglywyhyy.getGuid() + ")");
        Long Zt = null;
        if ("".equals(zt)) {
            sql.append("and e.lchj != 0");
        } else {
            Zt = Long.parseLong(zt);
            if (Zt == 0 || Zt == -1) {
                sql.append("and e.lchj = " + Zt);
            } else {
                sql.append("and e.lchj not in(0,-1)");
            }
        }
        if (!"".equals(mc)) {
            sql.append(" and a.xmmc like " + "'%" + mc + "%'");
        }
        sql.append("    order by e.czsj");
        List list = baseDao.selectMapsBySQL(sql.toString());
        return list;
    }

    @Override
    public List getXmgsfkData(String xmmc, HttpSession session) {
        YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
        String sql = "select e.id yjid,\n" +
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
                "       (select c.xmztz / 10000\n" +
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
                "   and e.id in (select t.ywid\n" +
                "                  from zftz_gg_ryqzyh t\n" +
                "                 where t.yhid = \n" + yhglywyhyy.getGuid() +
                "                   and t.sxlx = 2)\n" +
                "   and e.lchj not in (0,-1)";
        List list = baseDao.selectMapsBySQL(sql);
        return list;
    }

    @Override
    public List selectZgbm() {
        String sql = "select * from v_zftz_zgdw";
        return baseDao.selectMapsBySQL(sql);
    }

    @Override
    public List selectJsdw(HttpSession session) {
        YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
        String sql = "select * from v_zftz_jsdw  where id = " + yhglywyhyy.getSzdwid();
        return baseDao.selectMapsBySQL(sql);
    }

    @Override
    public List selectNoJsdw() {
        String sql = "select * from v_zftz_jsdw";
        return baseDao.selectMapsBySQL(sql);
    }

    @Override
    public void deleteXmgsyj(List<Integer> ids, Result result) {
        if (ids.size() > 0) {
            for (int m = 0; m < ids.size(); m++) {
                int id = ids.get(m);
                String sql = "update zftz_xmgsyjzx set zt = 0 where id = " + id;
                baseDao.executeBySql(sql);
            }
        }
        /* Long xmgsid = null;
            if (!"".equals(id)) {
                xmgsid = Long.parseLong(id);
            }*/
        result.setSuccess(true);
        result.setContent("删除成功");
    }

    @Override
    public List getZBKData(String id) {
        Long xmid = null;
        if (!"".equals(id)) {
            xmid = Long.parseLong(id);
        }
        String sql = "select a.yjzb,\n" +
                "        a.ejzb,\n" +
                "        a.sjzb,\n" +
                "        a.dwtze,\n" +
                "        a.jldw,\n" +
                "        a.zbly,\n" +
                "        b.sl,\n" +
                "        b.id_zftz_zbk zbkid,\n" +
                "        a.dwtze * b.sl xj\n" +
                "   from zftz_zbk a\n" +
                "   left join zftz_xmbzcs b\n" +
                "     on a.id = b.id_zftz_zbk\n" +
                "  where b.id_zftz_xm = " + xmid;
        List list = baseDao.selectMapsBySQL(sql);
        return list;
    }

    @Override
    public List getZjlydj(String id) {
        /*String sql = "select a.xmmc,\n" +
                "       a.jsdw,\n" +
                "       a.xmlx,\n" +
                "       b.rjl,\n" +
                "       b.xmghxz,\n" +
                "       b.xmztz,\n" +
                "       b.ydmj,\n" +
                "       b.jzzmj,\n" +
                "       b.dsjzmj,\n" +
                "       b.xmlc,\n" +
                "       b.dlkd,   \n" +
                "       b.jldw,\n" +
                "       b.tzxe, \n" +
                "       b.jykzzjbz,\n" +
                "       b.xmdwzj,\n" +
                "       b.jsnr,\n" +
                "       nvl(b.ztz_jzaztz,0) ZTZJZAZTZ,\n" +
                "       nvl(b.ztz_sbtz,0) ZTZSBTZ, \n" +
                "       nvl(b.ztz_dttz,0) ZTZDTTZ, \n" +
                "       nvl(b.ztz_qttz,0) ZTZQTTZ, \n" +
                "       nvl(b.ztz_jzaztz, 0) + nvl(b.ztz_sbtz, 0) + nvl(b.ztz_dttz, 0) + nvl(b.ztz_qttz, 0) ZTZHJ,\n" +
                "       nvl(b.xmzjly_czxzj,0) as XMZJLYCZXZJ,\n" +
                "       nvl(b.xmzjly_zyph,0) as XMZJLYZYPH, \n" +
                "       nvl(b.xmzjly_qt,0) as XMZJLYQT, \n" +
                "       nvl(b.xmzjly_czxzj, 0) + nvl(b.xmzjly_zyph, 0) + nvl(b.xmzjly_qt, 0) XMZJLYHJ\n" +
                "  from zftz_xm a     \n" +
                "  left join zftz_xmzjlydj b\n" +
                "    on a.id = b.id_zftz_xm\n" +
                "    where a.id = " + id +
                "    and b.zt = 1";*/
        String sql = "select a.xmmc,\n" +
                "       a.jsdw,\n" +
                "       a.xmlx,\n" +
                "       b.rjl,\n" +
                "       b.id,\n" +
                "       b.xmghxz,\n" +
                "       nvl(b.xmztz,0)/10000 xmztz,\n" +
                "       b.ydmj,\n" +
                "       b.jzzmj,\n" +
                "       b.dsjzmj,\n" +
                "       b.xmlc,\n" +
                "       b.dlkd,\n" +
                "       b.jldw,\n" +
                "       nvl(b.tzxe,0)/10000 tzxe,\n" +
                "       b.jykzzjbz,\n" +
                "       b.xmdwzj,\n" +
                "       b.jsnr,\n" +
                "       nvl(b.ztz_jzaztz, 0)/10000 ZTZJZAZTZ,\n" +
                "       nvl(b.ztz_sbtz, 0)/10000 ZTZSBTZ,\n" +
                "       nvl(b.ztz_dttz, 0)/10000 ZTZDTTZ,\n" +
                "       nvl(b.ztz_qttz, 0)/10000 ZTZQTTZ,\n" +
                "       (nvl(b.ztz_jzaztz, 0) + nvl(b.ztz_sbtz, 0) + nvl(b.ztz_dttz, 0) +\n" +
                "       nvl(b.ztz_qttz, 0))/10000 ZTZHJ,\n" +
                "       nvl(b.xmzjly_czxzj, 0)/10000 as XMZJLYCZXZJ,\n" +
                "       nvl(b.xmzjly_zyph, 0)/10000 as XMZJLYZYPH,\n" +
                "       nvl(b.xmzjly_qt, 0)/10000 as XMZJLYQT,\n" +
                "       (nvl(b.xmzjly_czxzj, 0) + nvl(b.xmzjly_zyph, 0) + nvl(b.xmzjly_qt, 0))/10000 XMZJLYHJ\n" +
                "  from zftz_xm a\n" +
                "  left join zftz_xmzjlydj b\n" +
                "    on a.id = b.id_zftz_xm\n" +
                " where a.id = " + id +
                "   and b.zt = 1\n";
        List list = baseDao.selectMapsBySQL(sql);
        return list;
    }

    @Override
    public List getXmqqch(String id) {
        /*String sql = "select a.xmmc,\n" +
                "       a.jsdw,\n" +
                "       a.zgbm,\n" +
                "       b.xmghxz,\n" +
                "       a.xmlx,\n" +
                "       b.rjl,\n" +
                "       b.ydmj,\n" +
                "       b.jzzmj,\n" +
                "       b.dsjzmj,\n" +
                "       b.xmlc,\n" +
                "       b.dlkd,\n" +
                "       b.lxdh,\n" +
                "       b.xmdwzj,\n" +
                "       b.jldw,\n" +
                "       b.tlxzjbz,\n" +
                "       b.lxr,\n" +
                "       b.jsnr,\n" +
                "       b.wjzjbz,\n"+
                "       nvl(b.ztz_jzaztz, 0) ZTZJZAZTZ1,\n" +
                "       nvl(b.ztz_sbtz, 0) ZTZSBTZ1,\n" +
                "       nvl(b.ztz_dttz, 0) ZTZDTTZ1,\n" +
                "       nvl(b.ztz_qttz, 0) ZTZQTTZ1,\n" +
                "       nvl(b.ztz_jzaztz, 0) + nvl(b.ztz_sbtz, 0) + nvl(b.ztz_dttz, 0) +\n" +
                "       nvl(b.ztz_qttz, 0) ZTZHJ1,\n" +
                "       nvl(b.xmzjly_czxzj, 0) as XMZJLYCZXZJ1,\n" +
                "       nvl(b.xmzjly_zyph, 0) as XMZJLYZYPH1,\n" +
                "       nvl(b.xmzjly_qt, 0) as XMZJLYQT1,\n" +
                "       nvl(b.xmzjly_czxzj, 0) + nvl(b.xmzjly_zyph, 0) + nvl(b.xmzjly_qt, 0) XMZJLYHJ1,\n" +
                "       b.xmphjy,\n" +
                "       nvl(b.tzxe, 0) tzxe,\n" +
                "       nvl(b.jykzzjbz, 0) jykzzjbz,\n" +
                "       nvl(b.ztz_jzaztz, 0) + nvl(b.ztz_sbtz, 0) + nvl(b.ztz_dttz, 0) +\n" +
                "       nvl(b.ztz_qttz, 0) - nvl(b.tzxe, 0) hjje\n" +
                "  from zftz_xm a\n" +
                "  left join zftz_xmqqch b\n" +
                "    on a.id = b.id_zftz_xm\n" +
                "    where a.id = " + id +
                "   and b.zt = 1";*/
        String sql = "select a.xmmc,\n" +
                "       a.jsdw,\n" +
                "       a.zgbm,\n" +
                "       b.xmghxz,\n" +
                "       a.xmlx,\n" +
                "       b.rjl,\n" +
                "       b.id,\n" +
                "       b.ydmj,\n" +
                "       b.jzzmj,\n" +
                "       b.dsjzmj,\n" +
                "       b.xmlc,\n" +
                "       b.dlkd,\n" +
                "       b.lxdh,\n" +
                "       b.xmdwzj,\n" +
                "       b.jldw,\n" +
                "       b.tlxzjbz,\n" +
                "       b.lxr,\n" +
                "       b.jsnr,\n" +
                "       b.wjzjbz,\n" +
                "       nvl(b.ztz_jzaztz, 0)/10000 ZTZJZAZTZ1,\n" +
                "       nvl(b.ztz_sbtz, 0)/10000 ZTZSBTZ1,\n" +
                "       nvl(b.ztz_dttz, 0)/10000 ZTZDTTZ1,\n" +
                "       nvl(b.ztz_qttz, 0)/10000 ZTZQTTZ1,\n" +
                "       (nvl(b.ztz_jzaztz, 0) + nvl(b.ztz_sbtz, 0) + nvl(b.ztz_dttz, 0) +\n" +
                "       nvl(b.ztz_qttz, 0))/10000 ZTZHJ1,\n" +
                "       nvl(b.xmzjly_czxzj, 0)/10000 as XMZJLYCZXZJ1,\n" +
                "       nvl(b.xmzjly_zyph, 0)/10000 as XMZJLYZYPH1,\n" +
                "       nvl(b.xmzjly_qt, 0)/10000 as XMZJLYQT1,\n" +
                "       (nvl(b.xmzjly_czxzj, 0) + nvl(b.xmzjly_zyph, 0) + nvl(b.xmzjly_qt, 0))/10000 XMZJLYHJ1,\n" +
                "       b.xmphjy,\n" +
                "       nvl(b.tzxe, 0)/10000 tzxe,\n" +
                "       nvl(b.jykzzjbz, 0) jykzzjbz,\n" +
                "       (nvl(b.ztz_jzaztz, 0) + nvl(b.ztz_sbtz, 0) + nvl(b.ztz_dttz, 0) +\n" +
                "       nvl(b.ztz_qttz, 0) - nvl(b.tzxe, 0))/10000 hjje\n" +
                "  from zftz_xm a\n" +
                "  left join zftz_xmqqch b\n" +
                "    on a.id = b.id_zftz_xm\n" +
                " where a.id = " + id +
                "   and b.zt = 1\n";
        List list = baseDao.selectMapsBySQL(sql);
        return list;
    }


    @Override
    public List getShyj(String id) {
        String sql = "";
        List<Map> list = new ArrayList<Map>();
        if (id != null) {
            sql = "select a.*,i.yyguid,i.xm FKR\n" +
                    "  from zftz_shjl a\n" +
                    "  left join (select m.guid yyguid, n.*\n" +
                    "               from yhgl_yw_yhyy m, yhgl_yw_yh n\n" +
                    "              where m.yhid = n.guid) i\n" +
                    "    on a.clyhid = i.yyguid\n" +
                    "    where a.xmid =  " + id;
            list = baseDao.selectMapsBySQL(sql);
        } else {
        }
        return list;
    }

    @Override
    public List getXmgsmcTree(String id, HttpSession session) {
        YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
        List dataList = new ArrayList();
        if (null == id) {
           /* String sql = "select to_char(t.id) as id,\n" +
                    "       t.xmmc as name,\n" +
                    "       'jsdw' || t.jsdw as pid,\n" +
                    "       1 as isleaf\n" +
                    "  from ZFTZ_XM t\n" +
                    " where t.zt = 1\n" +
                    "   and t.jsdw in (select entid from v_zftz_yhtoenter where guid = " + yhglywyhyy.getGuid() + ")\n" +
                    "   and t.jsdw is not null\n" +
                    "   and t.id not in\n" +
                    "       (select to_char(b.id_zftz_xm) from zftz_xmgs b where b.zt = 1)\n" +
                    "   and t.id not in\n" +
                    "       (select to_char(s.id_zftz_xm) from zftz_xmjgjs s where s.zt = 1)\n" +
                    "union all\n" +
                    "select 'jsdw' || w.id as id, w.mc as name, to_char('jsdw' || fjbm) as pid, 0 as isleaf\n" +
                    "  from v_zftz_jsdw w\n" +
                    " where w.id in\n" +
                    "       (select x.jsdw\n" +
                    "          from zftz_xm x\n" +
                    "         where x.zt = '1'\n" +
                    "           and x.jsdw in (select entid from v_zftz_yhtoenter where guid = " + yhglywyhyy.getGuid() + ")\n" +
                    "           and x.jsdw is not null\n" +
                    "           and x.id not in\n" +
                    "               (select to_char(b.id_zftz_xm) from zftz_xmgs b where b.zt = 1)\n" +
                    "           and x.id not in (select to_char(s.id_zftz_xm)\n" +
                    "                              from zftz_xmjgjs s\n" +
                    "                             where s.zt = 1))\n";*/
            String sql = "with xmTable as\n" +
                    " (select t.id, t.xmmc, t.jsdw\n" +
                    "    from zftz_xm t\n" +
                    "   where t.zt = 1\n" +
                    "     and t.jsdw in (select entid from v_zftz_yhtoenter where guid = " + yhglywyhyy.getGuid() + ")\n" +
                    "   and t.jsdw is not null\n" +
                    "   and t.id not in\n" +
                    "       (select to_char(b.id_zftz_xm) from zftz_xmgs b where b.zt = 1)\n" +
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
            List list = baseDao.selectBySql(sql);//得到List集合
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
        } else if ("0".equals(id)) {
            String sql = "select t.id,t.xmmc as name,0 as pid from ZFTZ_XM t where t.zt = 1 and t.jsdw  in (select entid from v_zftz_yhtoenter where guid = " + yhglywyhyy.getGuid() + ")";
            List list = baseDao.selectBySql(sql);//得到List集合
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
        }
        return dataList;
    }

    @Override
    public void saveGs(HttpServletRequest request, Result result, String xmgsid, Map xmgs, List<Map> xmgsmx, List<Map> gsfileid) {
        String ywgsid = "";
        //获取用户信息
        YhglYwYhyy operator = (YhglYwYhyy) request.getSession().getAttribute(StaticData.LOGINUSER);
        //项目id
        String xmid = xmgs.get("MC") == null ? "" : xmgs.get("MC").toString();
        Long id = null;
        if (!"".equals(xmid)) {
            id = Long.parseLong(xmid);
        }
        //建设单位
        Object jsdw = xmgs.get("JSDW");

        //主管部门
        Object zgbm = xmgs.get("ZGBM");

        //项目规划选址
        String xmghxz = xmgs.get("XMGHXZ") == null ? "" : xmgs.get("XMGHXZ").toString();

        //项目类型
        Object xmlx = xmgs.get("XMLX");

        //容积率
        Object rjl = xmgs.get("RJL");

        //用地平方面积
        Object ydmj = xmgs.get("YDMJ");

        //建筑总面积
        Object jzzmj = xmgs.get("JZZMJ");

        //地上建筑面积
        Object dsjzmj = xmgs.get("DSJZMJ");

        //项目里程
        Object xmlc = xmgs.get("XMLC");

        //道路宽度
        Object dlkd = xmgs.get("DLKD");

        //投资限额
        Object tzxe = xmgs.get("TZXE");

        //建议控制造价标准
        Object jykzzjbz = xmgs.get("JYKZZJBZ");

        //项目单位造价标准
        Object xmdwzjbz = xmgs.get("XMDWZJ");

        String jsnr = xmgs.get("JSNR") == null ? "" : xmgs.get("JSNR").toString();
/***************************************项目概算明细******************************************************/


        if ("".equals(xmgsid)) {
            ZftzXmgs zftzXmgs = new ZftzXmgs();
            zftzXmgs.setIdZftzXm(id);
            zftzXmgs.setXmghxz(xmghxz);
            if (!"".equals(rjl)) {
                zftzXmgs.setRjl(new BigDecimal(rjl.toString()));
            }
            if (!"".equals(ydmj)) {
                zftzXmgs.setYdmj(new BigDecimal(ydmj.toString()));
            }
            if (!"".equals(jzzmj)) {
                zftzXmgs.setJzzmj(new BigDecimal(jzzmj.toString()));
            }
            if (!"".equals(dsjzmj)) {
                zftzXmgs.setDsjzmj(new BigDecimal(dsjzmj.toString()));
            }
            if (!"".equals(xmlc)) {
                zftzXmgs.setXmlc(new BigDecimal(xmlc.toString()));
            }
            if (!"".equals(dlkd)) {
                zftzXmgs.setDlkd(new BigDecimal(dlkd.toString()));
            }
            if (!"".equals(tzxe)) {
                zftzXmgs.setTzxe((new BigDecimal(tzxe.toString())).multiply(new BigDecimal("10000")));
            }
            if (!"".equals(jykzzjbz)) {
                zftzXmgs.setJykzzjbz(new BigDecimal(jykzzjbz.toString()));
            }
            if (!"".equals(xmdwzjbz)) {
                zftzXmgs.setXmdwzj(new BigDecimal(xmdwzjbz.toString()));
            }
            zftzXmgs.setJsnr(jsnr);
            zftzXmgs.setLchj(-1);
            zftzXmgs.setZt(1);
            zftzXmgs.setCzsj(new Date());
            zftzXmgs.setCzr(operator.getGuid().toString());
            baseDao.save(zftzXmgs);
            Long xmgsFileId = zftzXmgs.getId();
            ywgsid = xmgsFileId.toString();
            /*************************************跟新项目备案登记  上传文件的文件表里面的filebsid*****************************************************/
            if (null != gsfileid && 0 < gsfileid.size()) {
                for (int j = 0; j < gsfileid.size(); j++) {
                    Map mapID = gsfileid.get(j);
                    String ID = mapID.get("FILEID") == null ? "" : mapID.get("FILEID").toString();
                    String updateSql = "update zftz_file a set a.filebsid = " + xmgsFileId + " where guid = " + ID;
                    baseDao.executeBySql(updateSql);
                }
            }
            if (null != xmgsmx && 0 < xmgsmx.size()) {
                for (int i = 0; i < xmgsmx.size(); i++) {
                    Map xmgsmxMap = xmgsmx.get(i);
                    //工程费用名称
                    String gcfymc = xmgsmxMap.get("GCFYMC") == null ? "" : xmgsmxMap.get("GCFYMC").toString();

                    //计量单位
                    String jldw = xmgsmxMap.get("JLDW") == null ? "" : xmgsmxMap.get("JLDW").toString();

                    //工程量
                    Object gcl = xmgsmxMap.get("GCL");
//                    BigDecimal gcl = new BigDecimal(xmgsmxMap.get("GCL").toString());

                    //费用类型
                    Object fylx = xmgsmxMap.get("FYLX");


                    //单价或费率
                    Object djhfl = xmgsmxMap.get("DJHFL");

                    //建筑投资
                    Object jztz = xmgsmxMap.get("JZTZ");

                    //安装投资
                    Object aztz = xmgsmxMap.get("AZTZ");

                    //设备投资
                    Object sbtz = xmgsmxMap.get("SBTZ");

                    //其他投资
                    Object xj = xmgsmxMap.get("XJ");
//                    BigDecimal qttz = new BigDecimal(xmgsmxMap.get("QTTZ").toString());
//                    BigDecimal xj = new BigDecimal(xmgsmxMap.get("XJ").toString());

                    //土地征迁费用
                    Object tdzqfy = xmgsmxMap.get("TDZQFY");
//                    BigDecimal tdz
                    //利息费用
                    Object lxfy = xmgsmxMap.get("LXFY");
                    Long xmgsId = zftzXmgs.getId();
                    ZftzXmgsmx zftzXmgsmx = new ZftzXmgsmx();
                    zftzXmgsmx.setIdZftzXmgs(xmgsId);
                    zftzXmgsmx.setGcfymc(gcfymc);
                    zftzXmgsmx.setJldw(jldw);
                    if (!"".equals(gcl)) {
                        zftzXmgsmx.setGcl(new BigDecimal(gcl.toString()));
                    }
                    if (!"".equals(fylx)) {
                        zftzXmgsmx.setFylx(Integer.parseInt(fylx.toString()));
                    }
                    if (!"".equals(djhfl)) {
                        zftzXmgsmx.setDjhfl(new BigDecimal(djhfl.toString()));
                    }
                    if (!"".equals(jztz)) {
                        zftzXmgsmx.setTzeJztz((new BigDecimal(jztz.toString())).multiply(new BigDecimal("10000")));
                    }
                    if (!"".equals(aztz)) {
                        zftzXmgsmx.setTzeAztz((new BigDecimal(aztz.toString())).multiply(new BigDecimal("10000")));
                    }
                    if (!"".equals(sbtz)) {
                        zftzXmgsmx.setTzeSbtz((new BigDecimal(sbtz.toString())).multiply(new BigDecimal("10000")));
                    }
                    if (!"".equals(xj)) {
                        zftzXmgsmx.setTzeQttz((new BigDecimal(xj.toString())).multiply(new BigDecimal("10000")));
                    }
                    if (!"".equals(tdzqfy)) {
                        zftzXmgsmx.setTzeQtTdzqfy((new BigDecimal(tdzqfy.toString())).multiply(new BigDecimal("10000")));
                    }
                    if (!"".equals(lxfy)) {
                        zftzXmgsmx.setTzeQtLx((new BigDecimal(lxfy.toString())).multiply(new BigDecimal("10000")));
                    }
                    baseDao.save(zftzXmgsmx);
                }
            }
        } else {
            ywgsid = xmgsid;
            long gsid = Long.parseLong(xmgsid);
            String sql = "select lchj from ZFTZ_XMGS where id = " + xmgsid;
            List list = baseDao.selectBySql(sql);
            Integer integer = null;
            if (0 < list.size()) {
                String str = list.get(0).toString();
                if (!"".equals(str)) {
                    integer = Integer.parseInt(str);
                }
            }
            ZftzXmgs zftzXmgs = (ZftzXmgs) baseDao.load(ZftzXmgs.class, gsid);
            zftzXmgs.setIdZftzXm(id);
            zftzXmgs.setXmghxz(xmghxz);
            if (!"".equals(rjl)) {
                zftzXmgs.setRjl(new BigDecimal(rjl.toString()));
            }
            if (!"".equals(ydmj)) {
                zftzXmgs.setYdmj(new BigDecimal(ydmj.toString()));
            }
            if (!"".equals(jzzmj)) {
                zftzXmgs.setJzzmj(new BigDecimal(jzzmj.toString()));
            }
            if (!"".equals(dsjzmj)) {
                zftzXmgs.setDsjzmj(new BigDecimal(dsjzmj.toString()));
            }
            if (!"".equals(xmlc)) {
                zftzXmgs.setXmlc(new BigDecimal(xmlc.toString()));
            }
            if (!"".equals(dlkd)) {
                zftzXmgs.setDlkd(new BigDecimal(dlkd.toString()));
            }
            if (!"".equals(tzxe)) {
                zftzXmgs.setTzxe((new BigDecimal(tzxe.toString())).multiply(new BigDecimal("10000")));
            }
            if (!"".equals(jykzzjbz)) {
                zftzXmgs.setJykzzjbz(new BigDecimal(jykzzjbz.toString()));
            }
            if (!"".equals(xmdwzjbz)) {
                zftzXmgs.setXmdwzj(new BigDecimal(xmdwzjbz.toString()));
            }
            zftzXmgs.setJsnr(jsnr);
            zftzXmgs.setLchj(integer);
            zftzXmgs.setZt(1);
            zftzXmgs.setCzsj(new Date());
            zftzXmgs.setCzr(operator.getGuid().toString());
            baseDao.saveOrUpdate(zftzXmgs);

            /*************************************跟新项目备案登记  上传文件的文件表里面的filebsid*****************************************************/
            if (null != gsfileid && 0 < gsfileid.size()) {
                for (int j = 0; j < gsfileid.size(); j++) {
                    Map mapID = gsfileid.get(j);
                    String ID = mapID.get("FILEID") == null ? "" : mapID.get("FILEID").toString();
                    String updateSql = "update zftz_file a set a.filebsid = " + xmgsid + " where guid = " + ID;
                    baseDao.executeBySql(updateSql);
                }
            }

           /* String selectSql = "select id from zftz_xmgsmx a where a.id_zftz_xmgs = " + xmgsid;
            List list1 = baseDao.selectBySql(selectSql);
            Long xmgsmxid = null;
            if (null != list1 && 0 < list1.size()) {
                for (int i = 0; i < list1.size(); i++) {
                    String o = list1.get(i) == null ? "" : list1.get(i).toString();
                    if (!"".equals(o)) {
                        xmgsmxid = Long.parseLong(o);
                    }
                    ZftzXmgsmx zftzXmgsmx = zftzXmgsmx = (ZftzXmgsmx) baseDao.load(ZftzXmgsmx.class, xmgsmxid);
                    baseDao.delete(zftzXmgsmx);
                }
            }*/
            ZftzXmgsmx zftzXmgsmx;
            if (null != xmgsmx && 0 < xmgsmx.size()) {
                for (int i = 0; i < xmgsmx.size(); i++) {
                    Map xmgsmxMap = xmgsmx.get(i);
                    Object XMGSMXID = xmgsmxMap.get("XMGSMXID");
                    if (XMGSMXID != null) {
                        zftzXmgsmx = (ZftzXmgsmx) baseDao.load(ZftzXmgsmx.class, Long.parseLong(XMGSMXID.toString()));
                    } else {
                        zftzXmgsmx = new ZftzXmgsmx();
                    }

                    //工程费用名称
                    String gcfymc = xmgsmxMap.get("GCFYMC") == null ? "" : xmgsmxMap.get("GCFYMC").toString();

                    //计量单位
                    String jldw = xmgsmxMap.get("JLDW") == null ? "" : xmgsmxMap.get("JLDW").toString();

                    //工程量
                    Object gcl = xmgsmxMap.get("GCL");
//                    BigDecimal gcl = new BigDecimal(xmgsmxMap.get("GCL").toString());

                    //费用类型
                    Object fylx = xmgsmxMap.get("FYLX");
                    /*Integer fylx = null;
                    String fylxlx = xmgsmxMap.get("FYLX") == null ? "" : xmgsmxMap.get("FYLX").toString();
                    if (!"".equals(fylxlx)) {
                        fylx = Integer.parseInt(fylxlx);
                    }*/

                    //单价或费率
                    Object djhfl = xmgsmxMap.get("DJHFL");
//                    BigDecimal djhfl = new BigDecimal(xmgsmxMap.get("DJHFL").toString());

                    //建筑投资
                    Object jztz = xmgsmxMap.get("JZTZ");
//                    BigDecimal jztz = new BigDecimal(xmgsmxMap.get("JZTZ").toString());

                    //安装投资
                    Object aztz = xmgsmxMap.get("AZTZ");
//                    BigDecimal aztz = new BigDecimal(xmgsmxMap.get("AZTZ").toString());

                    //设备投资
                    Object sbtz = xmgsmxMap.get("SBTZ");
//                    BigDecimal sbtz = new BigDecimal(xmgsmxMap.get("SBTZ").toString());

                    //其他投资
                    Object xj = xmgsmxMap.get("XJ");
//                    BigDecimal qttz = new BigDecimal(xmgsmxMap.get("QTTZ").toString());
//                    BigDecimal xj = new BigDecimal(xmgsmxMap.get("XJ").toString());

                    //土地征迁费用
                    Object tdzqfy = xmgsmxMap.get("TDZQFY");
//                    BigDecimal tdzqfy = new BigDecimal(xmgsmxMap.get("TDZQFY").toString());

                    //利息费用
                    Object lxfy = xmgsmxMap.get("LXFY");
//                    BigDecimal lxfy = new BigDecimal(xmgsmxMap.get("LXFY").toString());

                    zftzXmgsmx.setIdZftzXmgs(Long.parseLong(xmgsid));
                    zftzXmgsmx.setGcfymc(gcfymc);
                    zftzXmgsmx.setJldw(jldw);
                    if (null != gcl && !"".equals(gcl)) {
                        zftzXmgsmx.setGcl(new BigDecimal(gcl.toString()));
                    }
                    if (null != fylx && !"".equals(fylx)) {
                        zftzXmgsmx.setFylx(Integer.parseInt(fylx.toString()));
                    }
                    if (null != djhfl && !"".equals(djhfl)) {
                        zftzXmgsmx.setDjhfl(new BigDecimal(djhfl.toString()));
                    }
                    if (null != jztz && !"".equals(jztz)) {
                        zftzXmgsmx.setTzeJztz((new BigDecimal(jztz.toString())).multiply(new BigDecimal("10000")));
                    }
                    if (null != aztz && !"".equals(aztz)) {
                        zftzXmgsmx.setTzeAztz((new BigDecimal(aztz.toString())).multiply(new BigDecimal("10000")));
                    }
                    if (null != sbtz && !"".equals(sbtz)) {
                        zftzXmgsmx.setTzeSbtz((new BigDecimal(sbtz.toString())).multiply(new BigDecimal("10000")));
                    }
                    if (null != xj && !"".equals(xj)) {
                        zftzXmgsmx.setTzeQttz((new BigDecimal(xj.toString())).multiply(new BigDecimal("10000")));
                    }
                    if (null != tdzqfy && !"".equals(tdzqfy)) {
                        zftzXmgsmx.setTzeQtTdzqfy((new BigDecimal(tdzqfy.toString())).multiply(new BigDecimal("10000")));
                    }
                    if (null != lxfy && !"".equals(lxfy)) {
                        zftzXmgsmx.setTzeQtLx((new BigDecimal(lxfy.toString())).multiply(new BigDecimal("10000")));
                    }
                    baseDao.saveOrUpdate(zftzXmgsmx);
                }
            }
        }
        result.setSuccess(true);
        result.setContent(ywgsid);
    }

    @Override
    public List getFylx() {
        /*String sql = "select mc as NAME,id as ID,bm as BM ,fjbm as PID from zftz_xxb t where t.lx='fylx' ";
        List list = baseDao.selectMapsBySQL(sql);//得到List集合
        return list;*/
        List dataList = new ArrayList();
        String sql = "select mc as NAME,id as ID,bm as BM ,fjbm as PID from zftz_xxb t where t.lx='fylx'";
        List list = baseDao.selectBySql(sql);//得到List集合
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                Map map = new HashMap();
                map.put("id", obj[2]);
                map.put("name", obj[0]);
                map.put("pId", obj[3]);
                dataList.add(map);
            }
        }
        return dataList;
    }

    @Override
    public List getXmgsData(String xmmc, String zt, HttpSession session) {
        StringBuffer sb = new StringBuffer();
        YhglYwYh operator = (YhglYwYh) session.getAttribute(StaticData.LOGINUSERINFO);
        String yhlx = operator.getYhlx();
        YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
        String sql = "select xmid from zftz_shjl where sxlx = 3 and clyhid = " + yhglywyhyy.getGuid();
        sb.append("select a.id gsid,\n" +
                "       a.id_zftz_xm xmid,\n" +
                "       a.xmghxz,\n" +
                "       a.rjl,\n" +
                "       a.ydmj,\n" +
                "       a.jzzmj,\n" +
                "       a.dsjzmj,\n" +
                "       a.xmlc,\n" +
                "       a.dlkd,\n" +
                "       a.tzxe / 10000 tzxe,\n" +
                "       a.jykzzjbz,\n" +
                "       a.xmdwzj,\n" +
                "       a.jsnr,\n" +
                "       a.lchj,\n" +
                "       a.zt,\n" +
                "       a.czsj,\n" +
                "       yhyy.xm czr,\n" +
                "       (select f.xmbh\n" +
                "          from zftz_xmtzwh f\n" +
                "         where f.id_zftz_xm = a.id_zftz_xm\n" +
                "           and f.zt = 1) as xmbh,\n" +
                "       b.id mc,\n" +
                "       b.xmmc,\n" +
                "       b.zgbm,\n" +
                "       b.jsdw,\n" +
                "       b.xmlx,\n" +
                "       (select nvl(d.ZTZ_JZAZTZ, 0) + nvl(d.ZTZ_DTTZ, 0) +\n" +
                "               nvl(d.ZTZ_QTTZ, 0) + nvl(d.ZTZ_SBTZ, 0)\n" +
                "          from zftz_xmzjlydj d\n" +
                "         where d.id_zftz_xm = a.id_zftz_xm\n" +
                "           and d.zt = 1) / 10000 xmztz,\n" +
                "       e.gsxmztz,\n" +
                "       (select d.id\n" +
                "          from zftz_xmzjlydj d\n" +
                "         where d.id_zftz_xm = a.id_zftz_xm\n" +
                "           and d.zt = 1) as zjlydjid,\n" +
                "       (select h.id from zftz_xmqqch h where a.id_zftz_xm = h.id_zftz_xm) as qqchid,\n" +
                "       case\n" +
                "         when a.lchj = 0 then\n" +
                "          '已完成'\n" +
                "         when a.lchj != 0 and\n" +
                "              (select czlx\n" +
                "                 from zftz_shjl sh\n" +
                "                where sh.guid = (select max(guid)\n" +
                "                                   from zftz_shjl l\n" +
                "                                  where l.sxlx = '3'\n" +
                "                                    and l.xmid = a.id)) = 2 then\n" +
                "          '退回'\n" +
                "         when a.lchj != -1 and a.lchj != 0 and\n" +
                "              (select czlx\n" +
                "                 from zftz_shjl sh\n" +
                "                where sh.guid = (select max(guid)\n" +
                "                                   from zftz_shjl l\n" +
                "                                  where l.sxlx = '3'\n" +
                "                                    and l.xmid = a.id)) = 1 then\n" +
                "          '已备案'\n" +
                "         else\n" +
                "          '未备案'\n" +
                "       end zt1\n" +
                "  from zftz_xmgs a\n" +
                "  left join zftz_xm b\n" +
                "    on a.id_zftz_xm = b.id\n" +
                "  left join (select c.id_zftz_xmgs,\n" +
                "                    sum(nvl(c.tze_jztz, 0) + nvl(c.tze_aztz, 0) +\n" +
                "                        nvl(c.tze_sbtz, 0) + nvl(c.tze_qttz, 0)) / 10000 gsxmztz\n" +
                "               from zftz_xmgsmx c\n" +
                "              group by c.id_zftz_xmgs) e\n" +
                "    on a.id = e.id_zftz_xmgs\n" +
                "  left join (select yh.xm, yy.guid\n" +
                "               from yhgl_yw_yh yh, yhgl_yw_yhyy yy\n" +
                "              where yh.guid = yy.yhid) yhyy\n" +
                "    on yhyy.guid = a.czr\n" +
                " where a.zt = 1");
        if (!"".equals(xmmc)) {
            sb.append(" and b.xmmc like " + "'%" + xmmc + "%'");
        }
        Long Zt = null;
        if (!"".equals(zt)) {
            Zt = Long.parseLong(zt);
            if (Zt == -1) {
                sb.append("and a.lchj = " + Zt);
            } else if (Zt != 2 && Zt != -1) {
                sb.append("and a.lchj not in(-1)");
            }
        }
        if (Zt == 2) {
            sb.append(" and a.id in(" + sql + ")");
            sb.append(" and b.jsdw in (select entid from v_zftz_yhtoenter where guid = " + yhglywyhyy.getGuid() + ")");
        }else {
            sb.append(" and b.jsdw in (select entid from v_zftz_yhtoenter where guid = " + yhglywyhyy.getGuid() + ")");
        }
        if("1".equals(yhlx)){

        }else {
            if(Zt!=2){
                sb.append(" and a.id in (select T.YWID\n" +
                        "                  from zftz_gg_ryqzyh t\n" +
                        "                 where t.yhid = " + yhglywyhyy.getGuid() +
                        "                   and t.sxlx = 3\n" +
                        "                   and t.ssyybm = 'ZFTZ')");
            }
        }
        sb.append(" order by a.czsj");
        List list = baseDao.selectMapsBySQL(sb.toString());
        return list;
    }

    @Override
    public List getXmgsmxData(String id) {
        /*String sql = "select a.id xmgsmxid,\n" +
                "       a.id_zftz_xmgs,\n" +
                "       a.gcfymc,\n" +
                "       a.jldw,\n" +
                "       a.gcl,\n" +
                "       a.fylx,\n" +
                "       a.djhfl,\n" +
                "       a.tze_jztz jztz,\n" +
                "       a.tze_aztz aztz,\n" +
                "       a.tze_sbtz sbtz,\n" +
                "       a.tze_qttz xj,\n" +
                "       a.tze_qt_tdzqfy tdzqfy,\n" +
                "       a.tze_qt_lx lxfy,\n" +
                "       nvl(a.tze_jztz, 0) + nvl(a.tze_aztz, 0) + nvl(a.tze_sbtz, 0) +\n" +
                "       nvl(a.tze_qttz, 0) hj\n" +
                "  from zftz_xmgsmx a\n" +
                " where a.id_zftz_xmgs = " + id;*/
        String sql = "select a.id xmgsmxid,\n" +
                "       a.id_zftz_xmgs,\n" +
                "       a.gcfymc,\n" +
                "       a.jldw,\n" +
                "       a.gcl,\n" +
                "       a.fylx,\n" +
                "       a.djhfl,\n" +
                "       a.tze_jztz/10000 jztz,\n" +
                "       a.tze_aztz/10000 aztz,\n" +
                "       a.tze_sbtz/10000 sbtz,\n" +
                "       a.tze_qttz/10000 xj,\n" +
                "       a.tze_qt_tdzqfy/10000 tdzqfy,\n" +
                "       a.tze_qt_lx/10000 lxfy,\n" +
                "       (nvl(a.tze_jztz, 0) + nvl(a.tze_aztz, 0) + nvl(a.tze_sbtz, 0) +\n" +
                "       nvl(a.tze_qttz, 0))/10000 hj\n" +
                "  from zftz_xmgsmx a\n" +
                " where a.id_zftz_xmgs = " + id;
        List list = baseDao.selectMapsBySQL(sql);
        return list;
    }

    @Override
    public void delXmgs(List<Integer> ids) {
        if (ids.size() > 0) {
            for (int m = 0; m < ids.size(); m++) {
                int id = ids.get(m);
                String delSql = "update zftz_xmgs set zt = 0 where id = " + id;
                baseDao.executeBySql(delSql);
            }
        }
    }

    @Override
    public List getFile(String id) {
        String sql = " select guid fileid,z.filename,z.filesize,z.filebstype filedl from zftz_file z where z.filebstype = 14 and  z.filebsid = " + id;
        List list = baseDao.selectMapsBySQL(sql);
        return list;
    }

    @Override
    public List getZcdjFile(String id) {
        String sql = " select guid fileid,z.filename,z.filesize,z.filebstype filedl from zftz_file z where z.filebstype = 15 and  z.filebsid = " + id;
        List list = baseDao.selectMapsBySQL(sql);
        return list;
    }

    @Override
    public List getXmgszxdFile(String id) {
        String sql = " select guid fileid,z.filename,z.filesize,z.filebstype filedl from zftz_file z where z.filebstype = 2 and  z.filebsid = " + id;
        List list = baseDao.selectMapsBySQL(sql);
        return list;
    }

    @Override
    public List getZjlyFile(String id) {
        List list = null;
        if (!"".equals(id)) {
            String sql = " select guid fileid,z.filename,z.filesize,z.filebstype filedl from zftz_file z where z.filebstype = 13 and  z.filebsid = " + id;
            list = baseDao.selectMapsBySQL(sql);
        }
        return list;
    }

    @Override
    public List getQqchFile(String id) {
        List list = null;
        if (!"".equals(id)) {
            String sql = " select guid fileid,z.filename,z.filesize,z.filebstype filedl from zftz_file z where z.filebstype = 1 and  z.filebsid = " + id;
            list = baseDao.selectMapsBySQL(sql);
        }
        return list;
    }

    @Override
    public List getXmgsFile(String id) {
        String sql = " select guid fileid,z.filename,z.filesize,z.filebstype filedl from zftz_file z where z.filebstype = 3 and  z.filebsid = " + id;
        List list = baseDao.selectMapsBySQL(sql);
        return list;
    }

    @Override
    public void checkData(String id, Result result) {
       /* String ids = "";
        for (int i = 0; i < id.size(); i++) {
            Integer integer = id.get(i);
            if (i != id.size() - 1) {
                ids += integer + ",";
            }

        }*/
        String obj = null;
        String sql = "select count(*) a\n" +
                "      from ZFTZ_SGTBA z\n" +
                "      where z.ZT = 1\n" +
                "        and z.ID_ZFTZ_XM in (" + id + ")" +
                "      union\n" +
                "      select count(*)\n" +
                "      from zftz_xmgs_tz\n" +
                "      where ZT = 1\n" +
                "        and id_zftz_xm in (" + id + ")" +
                "order by a desc";
        List list = baseDao.selectBySql(sql);
        if (list.size() > 0) {
            obj = list.get(0).toString();
        }
        result.setSuccess(true);
        result.setContent(obj);
    }

    @Override
    public void insertExcel(List<Object[]> list, HttpSession session, Map<String, Object> resultMap, String id) {
        Object[] obj = null;
        String sql = "select m.ID from zftz_xmgs m where m.zt = 1 and  m.id_zftz_xm = " + id;
        List li = baseDao.selectBySql(sql);
        Long xmgsid = null;
        if (0 < li.size()) {
            xmgsid = Long.parseLong(li.get(0).toString());
        }
        if (null != list && 0 < list.size()) {
            for (int i = 3; i < list.size(); i++) {
                obj = list.get(i);
                ZftzXmgsmx zftzXmgsmx = new ZftzXmgsmx();
                if ("".equals(obj[4].toString())) {

                } else if ("工程费用".equals(obj[4].toString())) {
                    zftzXmgsmx.setFylx(1);
                } else if ("其他费用".equals(obj[4].toString())) {
                    zftzXmgsmx.setFylx(2);
                } else if ("建设管理费".equals(obj[4].toString())) {
                    zftzXmgsmx.setFylx(201);
                } else if ("建设单位管理费".equals(obj[4].toString())) {
                    zftzXmgsmx.setFylx(20101);
                } else if ("建设管理其他费".equals(obj[4].toString())) {
                    zftzXmgsmx.setFylx(20102);
                } else if ("代建管理费".equals(obj[4].toString())) {
                    zftzXmgsmx.setFylx(20103);
                } else if ("工程监理费".equals(obj[4].toString())) {
                    zftzXmgsmx.setFylx(20104);
                } else if ("勘察设计费".equals(obj[4].toString())) {
                    zftzXmgsmx.setFylx(202);
                } else if ("工程勘察费".equals(obj[4].toString())) {
                    zftzXmgsmx.setFylx(20201);
                } else if ("工程设计费".equals(obj[4].toString())) {
                    zftzXmgsmx.setFylx(20202);
                } else if ("其他".equals(obj[4].toString())) {
                    zftzXmgsmx.setFylx(203);
                } else if ("预备费".equals(obj[4].toString())) {
                    zftzXmgsmx.setFylx(3);
                }
                zftzXmgsmx.setIdZftzXmgs(xmgsid);
                zftzXmgsmx.setGcfymc("".equals(obj[1].toString()) ? null : obj[1].toString());
                zftzXmgsmx.setJldw("".equals(obj[2].toString()) ? null : obj[2].toString());
                zftzXmgsmx.setGcl("".equals(obj[3].toString()) ? null : BigDecimal.valueOf(Double.parseDouble(obj[3].toString())));
                zftzXmgsmx.setDjhfl("".equals(obj[5].toString()) ? null : BigDecimal.valueOf(Double.parseDouble(obj[5].toString())));
                zftzXmgsmx.setTzeJztz("".equals(obj[6].toString()) ? null : (BigDecimal.valueOf(Double.parseDouble(obj[6].toString()))).multiply(new BigDecimal("10000")));
                zftzXmgsmx.setTzeAztz("".equals(obj[7].toString()) ? null : (BigDecimal.valueOf(Double.parseDouble(obj[7].toString()))).multiply(new BigDecimal("10000")));
                zftzXmgsmx.setTzeSbtz("".equals(obj[8].toString()) ? null : (BigDecimal.valueOf(Double.parseDouble(obj[8].toString()))).multiply(new BigDecimal("10000")));
                zftzXmgsmx.setTzeQttz("".equals(obj[9].toString()) ? null : (BigDecimal.valueOf(Double.parseDouble(obj[9].toString()))).multiply(new BigDecimal("10000")));
                zftzXmgsmx.setTzeQtTdzqfy("".equals(obj[10].toString()) ? null : (BigDecimal.valueOf(Double.parseDouble(obj[10].toString()))).multiply(new BigDecimal("10000")));
                zftzXmgsmx.setTzeQtLx("".equals(obj[11].toString()) ? null : (BigDecimal.valueOf(Double.parseDouble(obj[11].toString()))).multiply(new BigDecimal("10000")));
                baseDao.save(zftzXmgsmx);
                resultMap.put("success", true);
            }
        }
    }

    @Override
    public void saveExcel(List<String[]> list, HttpSession session, Map<String, Object> resultMap, String id) {
        YhglYwYhyy operator = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
        Map hash = new HashMap();
        String sql = "select mc as name, bm as BM\n" +
                "  from zftz_xxb t\n" +
                " where t.lx = 'zclb'";
        List<Map> maps = baseDao.selectMapsBySQL(sql);
        for (int j = 0; j < maps.size(); j++) {
            Map map = maps.get(j);
            Object bm = map.get("BM");
            Object name = map.get("NAME");
            hash.put(name, bm);
        }
        Map zrfsmap = new HashMap();
        String rzfs = "select mc as name,bm as BM from zftz_xxb t where t.lx='rzfs'";
        List<Map> zrfs = baseDao.selectMapsBySQL(rzfs);
        for (int h = 0; h < zrfs.size(); h++) {
            Map map = zrfs.get(h);
            Object bm = map.get("BM");
            Object name = map.get("NAME");
            zrfsmap.put(name, bm);
        }
        String[] keyArray = new String[]{
                "zcbh", "zcmc", "zclb", "zcjz", "mj", "rzfs", "rzsj", "bz"
        };
//        ObjectMapper objectMapper = new ObjectMapper();
        try {
//            Map<String,Integer> readValue = objectMapper.readValue(json, Map.class);
            if (0 < list.size()) {
                for (int i = 2; i < list.size(); i++) {
                    Map result = new HashMap();
                    for (int j = 0; j < keyArray.length; j++) {
                        String value = list.get(i)[j];
                        if (j == 2) {
                            value = (String) hash.get(value);
                        }
                        if (j == 5) {
                            if ("暂估入账".equals(value)) {
                                value = "2";
                            } else if ("实入账".equals(value)) {
                                value = "1";
                            }
                        }
                        result.put(keyArray[j], value);
                    }
                    ZftzXmzcdj zftzXmzcdj = JavaBeanUtil.convertMap(ZftzXmzcdj.class, result, new JavaBeanUtil.DefaultConverter());
                    zftzXmzcdj.setIdZftzXm(Long.parseLong(id));
                    zftzXmzcdj.setCzr(operator.getGuid().toString());
                    zftzXmzcdj.setCzsj(new Date());
                    zftzXmzcdj.setZt(1);
                    baseDao.save(zftzXmzcdj);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        resultMap.put("success", true);
    }

    @Override
    public List getTzbadj(String xmmc, String lchj, HttpSession session) {
        try {
            YhglYwYh operator = (YhglYwYh) session.getAttribute(StaticData.LOGINUSERINFO);
            String yhlx = operator.getYhlx();
            YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
            String sqlss = "select xmid from zftz_shjl where sxlx = 12 and clyhid = " + yhglywyhyy.getGuid();
           /* String sql = "select t5.id,\n" +
                    "       rownum xh,\n" +
                    "       t5.sid,\n" +
                    "       t5.gsid,\n" +
                    "       t5.xmmc,\n" +
                    "       (select mc from v_zftz_zgdw where id = t5.zgbm) zgbm,\n" +
                    "       (select mc\n" +
                    "          from zftz_xxb\n" +
                    "         where lx = 'xmlx'\n" +
                    "           and bm = t5.xmlx) xmlx,\n" +
                    "       (select mc from v_zftz_jsdw where id = t5.jsdw) jsdw,\n" +
                    "       t5.ydmj,\n" +
                    "       t4.gsxmztz,\n" +
                    "       round(nvl(t5.tzxe, 0) / 10000, 2) tzxe,\n" +
                    "       (select round((nvl(t2.ztz_jzaztz, 0) + nvl(t2.ztz_sbtz, 0) +\n" +
                    "                     nvl(t2.ztz_dttz, 0) + nvl(t2.ztz_qttz, 0)) / 10000,\n" +
                    "                     2) xmztz\n" +
                    "          from ZFTZ_XMZJLYDJ t2\n" +
                    "         where t2.id_zftz_xm = t5.id\n" +
                    "           and t2.zt = 1) xmztz,\n" +
                    "       t5.lchj,\n" +
                    "       case\n" +
                    "         when t5.lchj = -1 then\n" +
                    "          '未备案'\n" +
                    "         else\n" +
                    "          '备案'\n" +
                    "       end lcmc,\n" +
                    "      (select count(*)\n" +
                    "                          from zftz_xmjgjs t\n" +
                    "                         where t.zt = '1'\n" +
                    "                           and t.id_zftz_xm = t5.id )  jszt,\n" +
                    "       (select t7.xm\n" +
                    "          from yhgl_yw_yhyy t6, yhgl_yw_yh t7\n" +
                    "         where t6.yhid = t7.guid\n" +
                    "           and t6.guid = t5.czr) czr,\n" +
                    "       t5.czsj,\n" +
                    "       \n" +
                    "       case\n" +
                    "         when t5.lchj = 0 then\n" +
                    "          '已审核'\n" +
                    "         when t5.lchj = -1 and (select count(*)\n" +
                    "                                  from zftz_shjl t\n" +
                    "                                 where t.xmid = t5.id\n" +
                    "                                   and t.sxlx = 12) >= 1 then\n" +
                    "          '退回'\n" +
                    "         when t5.lchj = -1 and (select count(*)\n" +
                    "                                  from zftz_shjl t\n" +
                    "                                 where t.xmid = t5.id\n" +
                    "                                   and t.sxlx = 12) = 0 then\n" +
                    "          '未审核'\n" +
                    "         else\n" +
                    "          '流程中'\n" +
                    "       end zt1\n" +
                    "  from (select tt.id           sid,\n" +
                    "               tt.lchj,\n" +
                    "               tt.id_zftz_xmgs gsid,\n" +
                    "               tt.rjl,\n" +
                    "               tt.ydmj,\n" +
                    "               tt.jzzmj,\n" +
                    "               tt.dsjzmj,\n" +
                    "               tt.tzxe,\n" +
                    "               tt.jykzzjbz,\n" +
                    "               tt.xmdwzj,\n" +
                    "               tt.jsnr,\n" +
                    "               tt.czsj,\n" +
                    "               tt.czr,\n" +
                    "               t1.*\n" +
                    "          from ZFTZ_XMGS_TZ tt, zftz_xm t1\n" +
                    "         where tt.zt = '1'\n" +
                    "           and tt.id_zftz_xm = t1.id\n" +
                    "           and t1.zt = '1'\n" +
                    "           and t1.jsdw in (select entid from v_zftz_yhtoenter where guid = " + yhglywyhyy.getGuid() + ")) t5\n" +
                    "  left join (select id_zftz_xm,\n" +
                    "                    round(nvl(sum(gstzje), 0) / 10000, 2) gsxmztz\n" +
                    "               from v_zftz_gcfy\n" +
                    "              group by id_zftz_xm) t4\n" +
                    "    on t5.id = t4.id_zftz_xm\n" +
                    " where 1 = 1";*/
            String sql = "select t5.id,\n" +
                    "       rownum xh,\n" +
                    "       t5.sid,\n" +
                    "       t5.gsid,\n" +
                    "       t5.xmmc,\n" +
                    "       (select mc from v_zftz_zgdw where id = t5.zgbm) zgbm,\n" +
                    "       (select mc\n" +
                    "          from zftz_xxb\n" +
                    "         where lx = 'xmlx'\n" +
                    "           and bm = t5.xmlx) xmlx,\n" +
                    "       (select mc from v_zftz_jsdw where id = t5.jsdw) jsdw,\n" +
                    "       t5.ydmj,\n" +
                    "       t4.gsxmztz,\n" +
                    "       round(nvl(t5.tzxe, 0) / 10000, 2) tzxe,\n" +
                    "       (select round((nvl(t2.ztz_jzaztz, 0) + nvl(t2.ztz_sbtz, 0) +\n" +
                    "                     nvl(t2.ztz_dttz, 0) + nvl(t2.ztz_qttz, 0)) / 10000,\n" +
                    "                     2) xmztz\n" +
                    "          from ZFTZ_XMZJLYDJ t2\n" +
                    "         where t2.id_zftz_xm = t5.id\n" +
                    "           and t2.zt = 1) xmztz,\n" +
                    "       t5.lchj,\n" +
                    "       case\n" +
                    "         when t5.lchj = -1 then\n" +
                    "          '未备案'\n" +
                    "         else\n" +
                    "          '备案'\n" +
                    "       end lcmc,\n" +
                    "      (select count(*)\n" +
                    "                          from zftz_xmjgjs t\n" +
                    "                         where t.zt = '1'\n" +
                    "                           and t.id_zftz_xm = t5.id )  jszt,\n" +
                    "       (select t7.xm\n" +
                    "          from yhgl_yw_yhyy t6, yhgl_yw_yh t7\n" +
                    "         where t6.yhid = t7.guid\n" +
                    "           and t6.guid = t5.czr) czr,\n" +
                    "       t5.czsj,\n" +
                    "       \n" +
                   " case\n" +
                    "         when t5.lchj = 0 then\n" +
                    "          '已完成'\n" +
                    "         when t5.lchj != 0 and\n" +
                    "              (select czlx\n" +
                    "                 from zftz_shjl sh\n" +
                    "                where sh.guid = (select max(guid)\n" +
                    "                                   from zftz_shjl l\n" +
                    "                                  where l.sxlx = '12'\n" +
                    "                                    and l.xmid = t5.sid)) = 2 then\n" +
                    "          '退回'\n" +
                    "         when t5.lchj != -1 and t5.lchj != 0 and\n" +
                    "              (select czlx\n" +
                    "                 from zftz_shjl sh\n" +
                    "                where sh.guid = (select max(guid)\n" +
                    "                                   from zftz_shjl l\n" +
                    "                                  where l.sxlx = '12'\n" +
                    "                                    and l.xmid = t5.sid)) = 1 then\n" +
                    "          '已备案'\n" +
                    "         else\n" +
                    "          '未备案'\n" +
                    "       end zt1"+
                    "  from (select tt.id           sid,\n" +
                    "               tt.lchj,\n" +
                    "               tt.id_zftz_xmgs gsid,\n" +
                    "               tt.rjl,\n" +
                    "               tt.ydmj,\n" +
                    "               tt.jzzmj,\n" +
                    "               tt.dsjzmj,\n" +
                    "               tt.tzxe,\n" +
                    "               tt.jykzzjbz,\n" +
                    "               tt.xmdwzj,\n" +
                    "               tt.jsnr,\n" +
                    "               tt.czsj,\n" +
                    "               tt.czr,\n" +
                    "               t1.*\n" +
                    "          from ZFTZ_XMGS_TZ tt, zftz_xm t1\n" +
                    "         where tt.zt = '1'\n" +
                    "           and tt.id_zftz_xm = t1.id\n" +
                    "           and t1.zt = '1'";
            if(!("").equals(lchj)){
                if("5".equals(lchj)){
                    sql += " and tt.id in (select xmid\n" +
                            "                  from zftz_shjl\n" +
                            "                 where sxlx = 12\n" +
                            "                   and clyhid = " + yhglywyhyy.getGuid() + ")";
                }
            }
            sql += "and t1.jsdw in (select entid from v_zftz_yhtoenter where guid = " + yhglywyhyy.getGuid() + ")";
            if ("1".equals(yhlx)) {

            }else {
                if(!("").equals(lchj)){
                    if(!"5".equals(lchj)){
                        sql += "  and tt.id in (select T.YWID\n" +
                                "                  from zftz_gg_ryqzyh t\n" +
                                "                 where t.yhid = " + yhglywyhyy.getGuid() +
                                "                   and t.sxlx = 12\n" +
                                "                   and t.ssyybm = 'ZFTZ')";
                    }
                }
            }
            sql += ") t5\n" +
                    "  left join (select id_zftz_xm,\n" +
                    "                    round(nvl(sum(gstzje), 0) / 10000, 2) gsxmztz\n" +
                    "               from v_zftz_gcfy\n" +
                    "              group by id_zftz_xm) t4\n" +
                    "    on t5.id = t4.id_zftz_xm\n" +
                    " where 1 = 1\n";
            if (!("").equals(xmmc)) {
                sql = sql + " and t5.xmmc like '%" + xmmc + "%'";
            }
            if (!("").equals(lchj)) {
                if (("1").equals(lchj)) {
                    sql = sql + " and t5.lchj = '-1'";
                } else if (!"3".equals(lchj) && !"1".equals(lchj) && !"5".equals(lchj)) {
                    sql = sql + " and t5.lchj <> '-1' ";/*and t5.lchj <> '0'*/
                }
                /*if("4".equals(lchj)){
                    sql = sql + " and t5.lchj <> '-1' and t5.lchj <> '0'";
                } else*/
               /* if ("5".equals(lchj)) {
                    sql += " and t5.sid in(" + sqlss + ")";
                }*/

            }
            List list = baseDao.selectMapsBySQL(sql);
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List getXmmc(HttpSession session) {
        String sql1 = "select t1.id,t1.xmmc \n" +
                "from ZFTZ_XM t1,ZFTZ_XMGS t2 \n" +
                "where t1.id = t2.id_zftz_xm and t1.zt = '1' \n" +
                "and t2.zt = '1' and t2.lchj = '-1' and t1.jsdw = ?";
        try {
            YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
            Long userid = yhglywyhyy.getGuid();

            String sql = "select t1.szdwid from yhgl_yw_yhyy t1 where t1.guid = ?";
            List param1 = new ArrayList();
            param1.add(userid);
            List list1 = baseDao.selectMapsBySQL(sql, param1);
            Map map1 = (Map) list1.get(0);
            String yhszdw = map1.get("SZDWID").toString();

            param1.clear();
            param1.add(yhszdw);
            List<Map> list = baseDao.selectMapsBySQL(sql1, param1);


//            List list2 = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                String id = map.get("ID").toString();
                String name = map.get("XMMC").toString();
                map.clear();
                map.put("id", id);
                map.put("text", name);
//                map.put("type",id);
//                list2.add(map);
//            map.put("",);
            }

            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List getXmmc2(HttpSession session) {
        try {
            List dataList = new ArrayList();

            YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
            Long userid = yhglywyhyy.getGuid();
            /*String sql1 = "select to_char(t.id) as id,\n" +
                    "       t.xmmc as name,\n" +
                    "       'jsdw' || t.jsdw as pid,\n" +
                    "       1 as isleaf\n" +
                    "  from ZFTZ_XM t\n" +
                    " where t.zt = 1\n" +
                    "   and t.jsdw in (select entid from v_zftz_yhtoenter where guid = " + userid + ")\n" +
                    "   and t.jsdw is not null\n" +
                    "   and t.id not in (select t3.id_zftz_xm\n" +
                    "                      from ZFTZ_XMGS_TZ t3\n" +
                    "                     where t3.zt <> '0'\n" +
                    "                       and t3.lchj = '-1')\n" +
                    "union all\n" +
                    "select 'jsdw' || w.id as id,\n" +
                    "       w.mc as name,\n" +
                    "       to_char('jsdw' || fjbm) as pid,\n" +
                    "       0 as isleaf\n" +
                    "  from v_zftz_jsdw w\n" +
                    " where w.id in (select x.jsdw\n" +
                    "                  from zftz_xm x\n" +
                    "                 where x.zt = '1'\n" +
                    "                   and x.jsdw in\n" +
                    "                       (select entid from v_zftz_yhtoenter where guid = " + userid + ")\n" +
                    "                   and x.jsdw is not null\n" +
                    "                   and x.id not in (select t3.id_zftz_xm\n" +
                    "                                      from ZFTZ_XMGS_TZ t3\n" +
                    "                                     where t3.zt <> '0'\n" +
                    "                                       and t3.lchj = '-1'))";*/
           /* String sql1 = "with xmTable as\n" +
                    " (select t.id, t.xmmc, t.jsdw\n" +
                    "    from zftz_xm t\n" +
                    "   where t.zt = 1\n" +
                    "     and t.jsdw in (select entid from v_zftz_yhtoenter where guid = " + yhglywyhyy.getGuid() + ")\n" +
                    "   and t.jsdw is not null\n" +
                    "   and t.id not in (select t3.id_zftz_xm\n" +
                    "                      from ZFTZ_XMGS_TZ t3\n" +
                    "                     where t3.zt <> '0'\n" +
                    "                       and t3.lchj = '-1')),\n" +
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
                    "select to_char(t.ID) id, t.XMMC MC, t.JSDW || '_dw' PID from xmTable t\n";*/
            String sql1 = "with xmTable as\n" +
                    " (select t.id, t.xmmc, t.jsdw\n" +
                    "    from zftz_xm t\n" +
                    "   where t.zt = 1\n" +
                    "     and t.jsdw in (select entid from v_zftz_yhtoenter where guid = " + userid + ")\n" +
                    "     and t.jsdw is not null\n" +
                    "     and t.id not in\n" +
                    "         (select to_char(s.id_zftz_xm) from zftz_xmjgjs s where s.zt = 1)\n" +
                    "     and t.id not in (select t3.id_zftz_xm\n" +
                    "                        from ZFTZ_XMGS_TZ t3\n" +
                    "                       where t3.zt <> '0'\n" +
                    "                         and t3.lchj = '-1')\n" +
                    "     and t.id not in (select g.id_zftz_xm\n" +
                    "                        from zftz_xmgs g\n" +
                    "                       where g.zt = 1\n" +
                    "                         and g.lchj = '-1')),\n" +
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
            List list = baseDao.selectBySql(sql1);
//            List list = baseDao.selectBySql(sql);//得到List集合
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

//            List list2 = new ArrayList();
          /*  for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                String id = map.get("ID").toString();
                String name = map.get("XMMC").toString();
                map.clear();
                map.put("id", id);
                map.put("name", name);
                map.put("pId", "0");
                dataList.add(map);
//                map.put("type",id);
//                list2.add(map);
//            map.put("",);
            }*/

//            return dataList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List getJsdw() {
        try {
            String sql = "select * from v_zftz_jsdw";
            List list = baseDao.selectMapsBySQL(sql);

            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                String id = map.get("ID").toString();
                String name = map.get("MC").toString();
                map.clear();
                map.put("id", id);
                map.put("text", name);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List getZgbm() {
        try {
            String sql = "select * from v_zftz_zgdw";
            List list = baseDao.selectMapsBySQL(sql);

            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                String id = map.get("ID").toString();
                String name = map.get("MC").toString();
                map.clear();
                map.put("id", id);
                map.put("text", name);
//                map.put("type",id);
//                list2.add(map);
//            map.put("",);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List getXmlx() {
        try {
            String sql = "select * from zftz_xxb where lx = 'xmlx'";
            List list = baseDao.selectMapsBySQL(sql);

            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                String id = map.get("ID").toString();
                String name = map.get("MC").toString();
                map.clear();
                map.put("id", id);
                map.put("text", name);
//                map.put("type",id);
//                list2.add(map);
//            map.put("",);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Object getXmgsinfo(String xmid) {
        try {
            String sql1 = "select t1.id,t1.id_zftz_xm xmid,t2.jsdw,t2.zgbm,t2.xmlx,t1.xmghxz,t1.rjl,t1.ydmj,t1.jzzmj,\n" +
                    "t1.dsjzmj,t1.xmlc,t1.dlkd,round(nvl(t1.tzxe,0)/10000,2) tzxe,t1.jykzzjbz,t1.xmdwzj,t1.jsnr\n" +
                    " from ZFTZ_XMGS t1,ZFTZ_XM t2 where t1.id_zftz_xm = t2.id and t1.id_zftz_xm = ? and t1.zt = '1'";
            List param = new ArrayList();
            param.add(xmid);

            List list = baseDao.selectMapsBySQL(sql1, param);

            Map map = (Map) list.get(0);
            return map;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Object getXmgsinfobyid(String tzid) {
        try {
            /*String sql = "select t1.id,t1.id_zftz_xm xmid,t1.id_zftz_xmgs gsid,\n" +
                    "t3.zgbm,t3.jsdw,t2.xmghxz,t3.xmlx,t2.rjl,t2.ydmj,\n" +
                    "t2.jzzmj,t2.dsjzmj,round(nvl(t2.tzxe,0)/10000,2) tzxe,t2.jykzzjbz,t2.xmdwzj,t2.jsnr \n" +
                    "from ZFTZ_XMGS_TZ t1,ZFTZ_XMGS t2,ZFTZ_XM t3\n" +
                    "where t1.id_zftz_xmgs = t2.id and t3.id = t1.id_zftz_xm\n" +
                    "and t1.id = ? and t1.zt = '1'";*/
            String sql = "select t1.id,\n" +
                    "       t1.id_zftz_xm xmid,\n" +
                    "       t1.id_zftz_xmgs gsid,\n" +
                    "       t3.zgbm,\n" +
                    "       t3.jsdw,\n" +
                    "       t2.xmghxz,\n" +
                    "       t3.xmlx,\n" +
                    "       t1.rjl,\n" +
                    "       t1.ydmj,\n" +
                    "       t1.jzzmj,\n" +
                    "       t1.dsjzmj,\n" +
                    "       round(nvl(t1.tzxe, 0) / 10000, 2) tzxe,\n" +
                    "       t1.jykzzjbz,\n" +
                    "       t1.xmdwzj,\n" +
                    "       t1.jsnr\n" +
                    "  from ZFTZ_XMGS_TZ t1, ZFTZ_XMGS t2, ZFTZ_XM t3\n" +
                    " where t1.id_zftz_xmgs = t2.id\n" +
                    "   and t3.id = t1.id_zftz_xm\n" +
                    "   and t1.id = ?\n" +
                    "   and t1.zt = '1'";
            List param = new ArrayList();
            param.add(tzid);

            List list = baseDao.selectMapsBySQL(sql, param);

            Map map = (Map) list.get(0);
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public List getGcfymc(String xmid) {
        try {
            if (xmid == null) {
                String sql = "select distinct gcfyid,gcfymc from v_zftz_gcfy";//t1.id_zftz_xm = ? and
                List list = baseDao.selectMapsBySQL(sql);
                for (int i = 0; i < list.size(); i++) {
                    Map map = (Map) list.get(i);
                    String id = map.get("GCFYID").toString();
                    String name = map.get("GCFYMC").toString();
                    map.clear();
                    map.put("id", id);
                    map.put("text", name);
                }
                return list;
            } else {
                String sql = "select distinct gcfyid,gcfymc from v_zftz_gcfy where id_zftz_xm = ?";
                List param = new ArrayList();
                param.add(xmid);
                List list = baseDao.selectMapsBySQL(sql, param);
                for (int i = 0; i < list.size(); i++) {
                    Map map = (Map) list.get(i);
                    String id = map.get("GCFYID").toString();
                    String name = map.get("GCFYMC").toString();
                    map.clear();
                    map.put("id", id);
                    map.put("text", name);
                }
                return list;
            }
//            List list = baseDao.selectMapsBySQL(sql);
//            List list = baseDao.selectMapsBySQL(sql,param);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List getGcfymctree(String xmid) {
        try {
            List dataList = new ArrayList();
            if (xmid == null || ("").equals(xmid)) {
                String sql = "select distinct gcfyid,gcfymc,0 as pid from v_zftz_gcfy" +
                        "where gcfyid in (select t1.id from ZFTZ_XMGSMX t1,ZFTZ_XMGS t2 where t1.id_zftz_xmgs = t2.id and t2.zt <> '0') order by gcfymc";//t1.id_zftz_xm = ? and
                List list = baseDao.selectBySql(sql);
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    Map map = new HashMap();
                    map.put("id", obj[0]);
                    map.put("name", obj[1]);
                    map.put("pId", obj[2]);
                    dataList.add(map);
                }
                return dataList;
            } else {
                String sql = "select distinct gcfyid,gcfymc,0 as pid from v_zftz_gcfy where id_zftz_xm = " + xmid +
                        " and gcfyid in (select t1.id from ZFTZ_XMGSMX t1,ZFTZ_XMGS t2 where t1.id_zftz_xmgs = t2.id and t2.zt <> '0') order by gcfymc";
//                List param = new ArrayList();
//                param.add(xmid);
                List list = baseDao.selectBySql(sql);
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    Map map = new HashMap();
                    map.put("id", obj[0]);
                    map.put("name", obj[1]);
                    map.put("pId", obj[2]);
                    dataList.add(map);
                }
                return dataList;
            }
//            List list = baseDao.selectMapsBySQL(sql);
//            List list = baseDao.selectMapsBySQL(sql,param);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Map getGsmxinfo(String gsid, String tzid) {
        try {
            if (!StringUtil.isblank(gsid)) {
                String sql = "select * from ZFTZ_XMGSMX where id = ?";//t1.id_zftz_xm = ? and
                List param = new ArrayList();
                param.add(gsid);
                List list = baseDao.selectMapsBySQL(sql, param);
                Map map = (Map) list.get(0);
                String jldw = map.get("JLDW").toString();
                String gcl = map.get("GCL").toString();
                String djhfl = map.get("DJHFL").toString();
                map.clear();
                map.put("jldw", jldw);
                map.put("gcl", gcl);
                map.put("djhfl", djhfl);
//            }
                return map;
            } else if (!StringUtil.isblank(tzid)) {
                String sql = "select * from ZFTZ_XMGS_TZMX where id = ?";//t1.id_zftz_xm = ? and
                List param = new ArrayList();
                param.add(tzid);
                List list = baseDao.selectMapsBySQL(sql, param);
                Map map = (Map) list.get(0);
                String jldw = map.get("JLDW").toString();
                String gcl = map.get("GCL").toString();
                String djhfl = map.get("DJHFL").toString();
                map.clear();
                map.put("jldw", jldw);
                map.put("gcl", gcl);
                map.put("djhfl", djhfl);
//            }
                return map;
            } else {
                return null;
            }
//            for (int i = 0; i < list.size(); i++) {

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List getFylx1() {
        try {
            String sql = "select * from zftz_xxb t1 where t1.lx = 'fylx'";
            List list = baseDao.selectMapsBySQL(sql);

            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                String id = map.get("BM").toString();
                String name = map.get("MC").toString();
                map.clear();
                map.put("id", id);
                map.put("text", name);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getFylxbygsmxid(String gsmxid) {
        try {
            String sql = "select fylx from ZFTZ_XMGSMX t where t.id = ?";
            List param = new ArrayList();
            param.add(gsmxid);
            List list = baseDao.selectMapsBySQL(sql, param);

            if (list.size() > 0) {
                Map map = (Map) list.get(0);
                String fylx = map.get("FYLX").toString();
                return fylx;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List getFylxtree1() {
        try {
            List dataList = new ArrayList();
            String sql = "select bm,mc,0 as pid from zftz_xxb t1 where t1.lx = 'fylx'";
            List list = baseDao.selectBySql(sql);

            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                Map map = new HashMap();
                map.put("id", obj[0]);
                map.put("name", obj[1]);
                map.put("pId", obj[2]);
                dataList.add(map);
            }
            return dataList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List getTzefl() {
        try {
            String sql = "select * from zftz_xxb t1 where t1.lx = 'tzefl' order by to_number(bm)";
            List list = baseDao.selectMapsBySQL(sql);

            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                String id = map.get("BM").toString();
                String name = map.get("MC").toString();
                map.clear();
                map.put("id", id);
                map.put("text", name);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Map getGstzje(String xmid, String gc, String fylx, String tzefl) {
        try {
            if (!("0").equals(gc)) {
                String sql = "select round(nvl(gstzje,0)/10000,2) gstzje from v_zftz_gcfy where id_zftz_xm = ? and gcfyid = ? and fylx = ? and tzefl = ?";
                List param = new ArrayList();
                param.add(xmid);
                param.add(gc);
                param.add(fylx);
                param.add(tzefl);

                List list = baseDao.selectMapsBySQL(sql, param);
                Map map = (Map) list.get(0);
                return map;
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void insertGstzba(String sdata, String uploadTableData,String from, String xmid, String gsid, HttpSession session, Result result) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
            Long userid = yhglywyhyy.getGuid();
            List param = new ArrayList();

            conn = baseDao.getConnection();
            conn.setAutoCommit(false);

            //from
            JSONObject object = JSONObject.parseObject(from);
            String rjl;
            Object Rjl = object.get("RJL");
            if (Rjl != null) {
                rjl = Rjl.toString();
            }else {
                rjl = "";
            }
            String ydmj;
            Object Ydmj = object.get("YDMJ");
            if (Ydmj != null) {
                ydmj = Ydmj.toString();
            }else {
                ydmj = "";
            }

            String jzzmj;
            Object Jzzmj = object.get("JZZMJ");
            if (Jzzmj != null) {
                jzzmj = Jzzmj.toString();
            }else {
                jzzmj = "";
            }

            String dsjzmj;
            Object Dsjzmj = object.get("DSJZMJ");
            if (Dsjzmj != null) {
                dsjzmj = Dsjzmj.toString();
            }else {
                dsjzmj = "";
            }
            String tzxe;
            Object Tzxe = object.get("TZXE");
            if (Tzxe != null) {
                tzxe = Tzxe.toString();
            }else {
                tzxe = "0";
            }
            String jykzzjbz;
            Object Jykzzjbz = object.get("JYKZZJBZ");
            if (Jykzzjbz != null) {
                jykzzjbz = Jykzzjbz.toString();
            }else {
                jykzzjbz = "";
            }
            String xmdwzj;
            Object Xmdwzj = object.get("XMDWZJ");
            if (Xmdwzj != null) {
                xmdwzj = Xmdwzj.toString();
            }else {
                xmdwzj = "";
            }
            String jsnr = "";
            Object Jsnr = object.get("JSNR");
            if (Jsnr != null) {
                jsnr = Jsnr.toString();
            }
            String s = "select SEQ_ZFTZ_XMGS_TZ.nextval as a from dual";
            List list = baseDao.selectMapsBySQL(s);
            Map map1 = (Map) list.get(0);
            String id = map1.get("A").toString();

            String sql = "insert into ZFTZ_XMGS_TZ values(?,?,?,'',-1,1,sysdate,?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.valueOf(id));
            ps.setInt(2, Integer.valueOf(xmid));
            ps.setInt(3, Integer.valueOf(gsid));
            ps.setString(4, userid.toString());

            ps.setString(5, rjl);
            ps.setString(6, ydmj);
            ps.setString(7, jzzmj);
            ps.setString(8, dsjzmj);
            ps.setString(9, tzxe);
            ps.setString(10, jykzzjbz);
            ps.setString(11, xmdwzj);
            ps.setString(12, jsnr);
            ps.executeUpdate();

            JSONArray jsonArray = JSONArray.parseArray(sdata);
            for (int j = 0; j < jsonArray.size(); j++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(j);

                String gcmc = jsonObject.get("GCFYMC").toString();
                String fylx = jsonObject.get("FYLX").toString();
                String tzefl = jsonObject.get("TZEFL").toString();
                String ytzje;
                String tzsm;
                if (jsonObject.get("YTZJE") != null) {
                    ytzje = ("").equals(jsonObject.get("YTZJE").toString()) ? "0" : jsonObject.get("YTZJE").toString();
                } else {
                    ytzje = "0";
                }
                String tzhje = jsonObject.get("TZHJE").toString();
                if (jsonObject.get("TZSM") != null) {
                    tzsm = jsonObject.get("TZSM").toString();
                } else {
                    tzsm = "";
                }

//                String sql2 = "select gcfymc from ZFTZ_XMGSMX where id = ?";
//                param.clear();
//                param.add(gcid);
//                List list2 = baseDao.selectMapsBySQL(sql2, param);
//                Map map = (Map) list2.get(0);
//                String gcmc = map.get("GCFYMC").toString();

                Float tzje = Float.parseFloat(tzhje) - Float.parseFloat(ytzje);

                if (!("").equals(gcmc) && !("").equals(fylx) && !("").equals(tzefl) && !("").equals(tzhje)) {
                    if (jsonObject.containsKey("ISNEW")) {
                        String sql1 = "insert into ZFTZ_XMGS_TZMX values(SEQ_ZFTZ_XMGS_TZMX.nextval,?,SEQ_ZFTZ_XMGSMX.nextval,?,?,?,?,?,?,?*10000,?)";
                        ps = conn.prepareStatement(sql1);
                        ps.setInt(1, Integer.valueOf(id));
//                        ps.setInt(2, Integer.valueOf(0));
                        ps.setString(2, gcmc);
                        ps.setString(3, "");
                        ps.setInt(4, Integer.valueOf("0"));
                        ps.setInt(5, Integer.valueOf(fylx));
                        ps.setInt(6, Integer.valueOf("0"));
                        ps.setInt(7, Integer.valueOf(tzefl));
                        ps.setFloat(8, Float.valueOf(tzje));
                        ps.setString(9, tzsm);
                        ps.executeUpdate();
                    } else {
                        String gcid = jsonObject.get("GCFYID").toString();
                        String sql2 = "select gcfymc,jldw,gcl,djhfl from ZFTZ_XMGSMX where id = ?";
                        param.clear();
                        param.add(gcid);
                        List list2 = baseDao.selectMapsBySQL(sql2, param);
                        Map map = (Map) list2.get(0);
//                        String gcmc = map.get("GCFYMC").toString();
                        String jldw = (map.get("JLDW") == null) ? "0" : map.get("JLDW").toString();
                        String gcl = (map.get("GCL") == null) ? "0" : map.get("GCL").toString();
                        String djhfl = (map.get("DJHFL") == null) ? "0" : map.get("DJHFL").toString();

                        String sql1 = "insert into ZFTZ_XMGS_TZMX values(SEQ_ZFTZ_XMGS_TZMX.nextval,?,?,?,?,?,?,?,?,?*10000,?)";
                        ps = conn.prepareStatement(sql1);
                        ps.setInt(1, Integer.valueOf(id));
                        ps.setInt(2, Integer.valueOf(gcid));
                        ps.setString(3, gcmc);
                        ps.setString(4, jldw);
                        ps.setInt(5, Integer.valueOf(gcl));
                        ps.setInt(6, Integer.valueOf(fylx));
                        ps.setInt(7, Integer.valueOf(djhfl));
                        ps.setInt(8, Integer.valueOf(tzefl));
                        ps.setFloat(9, Float.valueOf(tzje));
                        ps.setString(10, tzsm);
                        ps.executeUpdate();
                    }
                }
            }

            JSONArray jsonArray2 = JSONArray.parseArray(uploadTableData);
            for (int j = 0; j < jsonArray2.size(); j++) {
                JSONObject jsonObject2 = (JSONObject) jsonArray2.get(j);

//                String filexl = jsonObject2.get("filexl").toString();
                String guid = jsonObject2.get("FILEID").toString();

                String sql1 = " update zftz_file set filedl = 12,filebsid = ? where guid = ? ";
                ps = conn.prepareStatement(sql1);
//                ps.setString(1, filexl);
//                ps.setString(2,id);
                ps.setInt(1, Integer.valueOf(id));
                ps.setInt(2, Integer.valueOf(guid));
                ps.executeUpdate();
            }

            conn.commit();
            conn.setAutoCommit(true);
            result.setSuccess(true);
            result.setContent(id);
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e1) {

            }
            result.setSuccess(false);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e2) {
                result.setSuccess(false);
            }
        }
    }

    @Override
    public void deleteGstzbadj(String sdata, HttpSession session, Result result) {
        try {
            JSONArray jsonArray1 = JSONArray.parseArray(sdata);
            List param = new ArrayList();
            for (int j = 0; j < jsonArray1.size(); j++) {
                JSONObject jsonObject = (JSONObject) jsonArray1.get(j);
                if (jsonObject.containsKey("SID")) {
                    String id = jsonObject.get("SID").toString();

                    String sql = "update ZFTZ_XMGS_TZ set zt = '0' where id = ?";
                    param.clear();
                    param.add(id);
                    baseDao.executeBySql(sql, param);

//                    sql = "delete from ZFTZ_XMGS_TZMX where id_ZFTZ_XMGS_TZ = ?";
//                    param.clear();
//                    param.add(id);
//                    baseDao.executeBySql(sql, param);
                }
            }
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
        }
    }

    @Override
    public void deleteGstzba(String sdata, String xmid, String gsid, HttpSession session, Result result) {
        try {
            String id;
            String sql1 = "select id from ZFTZ_XMGS_TZ where id_ZFTZ_XM = ? and id_ZFTZ_XMGS = ?";
            List param1 = new ArrayList();
            param1.add(xmid);
            param1.add(gsid);
            List list = baseDao.selectMapsBySQL(sql1, param1);
            if (list.size() > 0) {
                Map map = (Map) list.get(0);
                id = map.get("ID").toString();
            } else {
                result.setSuccess(true);
                return;
            }


            JSONArray jsonArray1 = JSONArray.parseArray(sdata);
            List param = new ArrayList();
            for (int j = 0; j < jsonArray1.size(); j++) {
                JSONObject jsonObject = (JSONObject) jsonArray1.get(j);
                if (jsonObject.containsKey("GCFYID")) {
//                    String sql1 = "update ZFTZ_XMGS_TZ set zt = '0' where id = ? ";
                    String gcid = jsonObject.get("GCFYID").toString();
                    String fylx = jsonObject.get("FYLX").toString();
                    String tzefl = jsonObject.get("TZEFL").toString();

//                    param.clear();
//                    param.add(sid);
//                    baseDao.executeBySql(sql1,param);

                    String sql = "delete from ZFTZ_XMGS_TZMX where id_ZFTZ_XMGS_TZ = ? and gcfyid = ? and fylx = ? and tzefl = ?";
                    param.clear();
                    param.add(id);
                    param.add(gcid);
                    param.add(fylx);
                    param.add(tzefl);
                    baseDao.executeBySql(sql, param);
                }

            }

            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
        }
    }

    @Override
    public void savePro(HttpServletRequest request, ZftzXm zftzxm, Result result) {
        zftzxm.setSffb(0);
        zftzxm.setZt(1);
        baseDao.save(zftzxm);
        result.setSuccess(true);
        result.setContent(zftzxm);
    }

    @Override
    public List getBatzmx(String tzid, String xmid) {
        try {
            String sql = "select t1.id,t1.gcfyid,t1.gcfymc,t1.fylx,t1.tzefl,round(nvl(t4.gstzje-t1.tzje,0)/10000,2) ytzje,\n" +
                    " round(nvl(t4.gstzje,0)/10000,2) tzhje,\n" +
                    "case when nvl(t1.tzje,0) >= 0 then round(nvl(t1.tzje,0)/10000,2) else 0 end tzje,\n" +
                    "case when nvl(t1.tzje,0) < 0 then round(nvl(t1.tzje*-1,0)/10000,2) else 0 end tjje,\n" +
                    "  t1.tzsm\n" +
                    "from ZFTZ_XMGS_TZMX t1\n" +
                    "left join (select sum(t2.gstzje) gstzje,t2.gcfyid,t2.fylx,t2.tzefl from v_zftz_gcfy t2,ZFTZ_XMGS_TZMX t3 \n" +
                    "where t2.fylx = t3.fylx and t2.tzefl = t3.tzefl and t2.id_zftz_xm = ? and t3.id_zftz_xmgs_tz = ? and t2.gcfyid = t3.gcfyid group by t2.gcfyid,t2.fylx,t2.tzefl) t4\n" +
                    "on t1.gcfyid = t4.gcfyid and t1.fylx = t4.fylx and t1.tzefl = t4.tzefl\n" +
                    "where id_ZFTZ_XMGS_TZ =? order by t1.gcfymc";
            List param = new ArrayList();
            param.add(xmid);
            param.add(tzid);
            param.add(tzid);
            List list = baseDao.selectMapsBySQL(sql, param);
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void updateGstzbamx(String sdata, String uploadTableData, String tzid, HttpSession session, Result result,String formData) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = baseDao.getConnection();
            conn.setAutoCommit(false);
            JSONObject object = JSONObject.parseObject(formData);
            String rjl;
            Object Rjl = object.get("RJL");
            if (Rjl != null) {
                rjl = Rjl.toString();
            }else {
                rjl = "";
            }
            String ydmj;
            Object Ydmj = object.get("YDMJ");
            if (Ydmj != null) {
                ydmj = Ydmj.toString();
            }else {
                ydmj = "";
            }

            String jzzmj;
            Object Jzzmj = object.get("JZZMJ");
            if (Jzzmj != null) {
                jzzmj = Jzzmj.toString();
            }else {
                jzzmj = "";
            }

            String dsjzmj;
            Object Dsjzmj = object.get("DSJZMJ");
            if (Dsjzmj != null) {
                dsjzmj = Dsjzmj.toString();
            }else {
                dsjzmj = "";
            }
            String tzxe;
            Object Tzxe = object.get("TZXE");
            if (Tzxe != null) {
                tzxe = Tzxe.toString();
            }else {
                tzxe = "";
            }
            String jykzzjbz;
            Object Jykzzjbz = object.get("JYKZZJBZ");
            if (Jykzzjbz != null) {
                jykzzjbz = Jykzzjbz.toString();
            }else {
                jykzzjbz = "";
            }
            String xmdwzj;
            Object Xmdwzj = object.get("XMDWZJ");
            if (Xmdwzj != null) {
                xmdwzj = Xmdwzj.toString();
            }else {
                xmdwzj = "";
            }
            String jsnr = "";
            Object Jsnr = object.get("JSNR");
            if (Jsnr != null) {
                jsnr = Jsnr.toString();
            }
            String updateSql = "update zftz_xmgs_tz z\n" +
                    "   set z.rjl      = ?,\n" +
                    "       z.ydmj     = ?,\n" +
                    "       z.jzzmj    = ?,\n" +
                    "       z.dsjzmj   = ?,\n" +
                    "       z.tzxe     = ?*10000,\n" +
                    "       z.jykzzjbz = ?,\n" +
                    "       z.xmdwzj   = ?,\n" +
                    "       z.jsnr     = ?\n" +
                    " where z.id = ? ";
            List list = new ArrayList();
            list.add(rjl);
            list.add(ydmj);
            list.add(jzzmj);
            list.add(dsjzmj);
            list.add(tzxe);
            list.add(jykzzjbz);
            list.add(xmdwzj);
            list.add(jsnr);
            list.add(tzid);
            baseDao.executeBySql(updateSql, list);
            List param = new ArrayList();
            JSONArray jsonArray = JSONArray.parseArray(sdata);
            for (int j = 0; j < jsonArray.size(); j++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(j);
                if (jsonObject.containsKey("ID")) {
                    String gcfyid = jsonObject.get("GCFYID").toString();
                    String gcfymc = jsonObject.get("GCFYMC").toString();
                    String fylx = jsonObject.get("FYLX").toString();
                    String tzefl = jsonObject.get("TZEFL").toString();
                    String id = jsonObject.get("ID").toString();
                    String tzhje = jsonObject.get("TZHJE").toString();
                    String ytzje;
                    if (jsonObject.get("YTZJE") != null) {
                        ytzje = ("").equals(jsonObject.get("YTZJE").toString()) ? "0" : jsonObject.get("YTZJE").toString();
                    } else {
                        ytzje = "0";
                    }
                    String tzsm = (jsonObject.get("TZSM") == null) ? "" : jsonObject.get("TZSM").toString();
                    tzhje = ("").equals(tzhje) ? "0" : tzhje;

                    Float tzje = Float.parseFloat(tzhje) - Float.parseFloat(ytzje);

                    String sql = "update ZFTZ_XMGS_TZMX set tzje = ?*10000,tzsm = ?,gcfyid = ?,gcfymc = ?,fylx = ?,tzefl = ? where id = ? ";
                    ps = conn.prepareStatement(sql);
                    ps.setFloat(1, Float.valueOf(tzje));
                    ps.setString(2, tzsm);
                    ps.setInt(3, Integer.valueOf(gcfyid));
                    ps.setString(4, gcfymc);
                    ps.setInt(5, Integer.valueOf(fylx));
                    ps.setInt(6, Integer.valueOf(tzefl));
                    ps.setInt(7, Integer.valueOf(id));
                    ps.executeUpdate();
                } else {
                    if (!jsonObject.containsKey("GCFYID")) {
                        continue;
                    }
                    String gcfyid = jsonObject.get("GCFYID").toString();
                    String gcfymc = jsonObject.get("GCFYMC").toString();
                    String fylx = jsonObject.get("FYLX").toString();
                    String tzefl = jsonObject.get("TZEFL").toString();
                    String tzhje = jsonObject.get("TZHJE").toString();
                    String tzsm = (jsonObject.get("TZSM") == null) ? "" : jsonObject.get("TZSM").toString();
                    String ytzje;
                    if (jsonObject.get("YTZJE") != null) {
                        ytzje = ("").equals(jsonObject.get("YTZJE").toString()) ? "0" : jsonObject.get("YTZJE").toString();
                    } else {
                        ytzje = "0";
                    }
                    tzhje = ("").equals(tzhje) ? "0" : tzhje;

                    Float tzje = Float.parseFloat(tzhje) - Float.parseFloat(ytzje);

                    if (!("").equals(gcfyid) && !("").equals(fylx) && !("").equals(tzefl) && !("").equals(tzhje)) {
                        if (jsonObject.containsKey("ISNEW")) {
                            String sql1 = "insert into ZFTZ_XMGS_TZMX values(SEQ_ZFTZ_XMGS_TZMX.nextval,?,SEQ_ZFTZ_XMGSMX.nextval,?,?,?,?,?,?,?*10000,?)";
                            ps = conn.prepareStatement(sql1);
                            ps.setInt(1, Integer.valueOf(tzid));
//                        ps.setInt(2, Integer.valueOf(0));
                            ps.setString(2, gcfymc);
                            ps.setString(3, "");
                            ps.setInt(4, Integer.valueOf("0"));
                            ps.setInt(5, Integer.valueOf(fylx));
                            ps.setInt(6, Integer.valueOf("0"));
                            ps.setInt(7, Integer.valueOf(tzefl));
                            ps.setFloat(8, Float.valueOf(tzje));
                            ps.setString(9, tzsm);
                            ps.executeUpdate();
                        } else {
                            String sql2 = "select gcfymc,jldw,gcl,djhfl from ZFTZ_XMGSMX where id = ?";
                            param.clear();
                            param.add(gcfyid);
                            List list2 = baseDao.selectMapsBySQL(sql2, param);
                            Map map = (Map) list2.get(0);
                            String gcmc = map.get("GCFYMC").toString();
                            String jldw = (map.get("JLDW") == null) ? "0" : map.get("JLDW").toString();
                            String gcl = (map.get("GCL") == null) ? "0" : map.get("GCL").toString();
                            String djhfl = (map.get("DJHFL") == null) ? "0" : map.get("DJHFL").toString();

                            String sql1 = "insert into ZFTZ_XMGS_TZMX values(SEQ_ZFTZ_XMGS_TZMX.nextval,?,?,?,?,?,?,?,?,?*10000,?)";
                            ps = conn.prepareStatement(sql1);
                            ps.setInt(1, Integer.valueOf(tzid));
                            ps.setInt(2, Integer.valueOf(gcfyid));
                            ps.setString(3, gcmc);
                            ps.setString(4, jldw);
                            ps.setInt(5, Integer.valueOf(gcl));
                            ps.setInt(6, Integer.valueOf(fylx));
                            ps.setInt(7, Integer.valueOf(djhfl));
                            ps.setInt(8, Integer.valueOf(tzefl));
                            ps.setFloat(9, Float.valueOf(tzje));
                            ps.setString(10, tzsm);
                            ps.executeUpdate();
                        }
                    }
                }
            }

            JSONArray jsonArray2 = JSONArray.parseArray(uploadTableData);
            for (int j = 0; j < jsonArray2.size(); j++) {
                JSONObject jsonObject2 = (JSONObject) jsonArray2.get(j);

//                String filexl = jsonObject2.get("filexl").toString();
                String guid = jsonObject2.get("FILEID").toString();

                String sql1 = " update zftz_file set filedl = 12,filebsid = ? where guid = ? ";
                ps = conn.prepareStatement(sql1);
//                ps.setString(1,filexl);
                ps.setString(1, tzid);
                ps.setInt(2, Integer.valueOf(guid));
                ps.executeUpdate();
            }
            conn.commit();
            conn.setAutoCommit(true);
            result.setSuccess(true);
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e1) {

            }
            result.setSuccess(false);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e2) {
                result.setSuccess(false);
            }
        }
    }

    @Override
    public void deleteGstzbamx(String sdata, HttpSession session, Result result) {
        try {
            JSONArray jsonArray = JSONArray.parseArray(sdata);
            List param = new ArrayList();
            for (int j = 0; j < jsonArray.size(); j++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(j);
                if (jsonObject.get("ID") != null) {
                    String id = jsonObject.get("ID").toString();

                    String sql = "delete from ZFTZ_XMGS_TZMX where id = ?";
                    param.add(id);
                    baseDao.executeBySql(sql, param);
                }
            }

            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
        }
    }

    @Override
    public List getfile(String bsid) {
        try {
            String sql = "select guid fileid,filename,filedl,filesize from zftz_file where filedl = 12 and filebsid = ?";
            List param = new ArrayList();
            param.add(bsid);
            List list = baseDao.selectMapsBySQL(sql, param);
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void saveGsmx(String sdata, HttpSession session, Result result) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(sdata);

            String gcmc = jsonObject.get("GCMC").toString();
            String jldw = jsonObject.get("JLDW").toString();
            String gcl = jsonObject.get("GCL").toString();
            String fylx = jsonObject.get("FYLX").toString();
            String djhfl = jsonObject.get("DJHFL").toString();

            String sql = "insert into ZFTZ_XMGSMX values(SEQ_ZFTZ_XMGSMX.nextval,0,?,?,?,?,?,0,0,0,0,0,0)";
            List param = new ArrayList();
            param.add(gcmc);
            param.add(jldw);
            param.add(gcl);
            param.add(fylx);
            param.add(djhfl);

            baseDao.executeBySql(sql, param);


            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
        }
    }

    @Override
    public List getXmZjlymcTree(String id, HttpSession session) {
        List dataList = new ArrayList();
        YhglYwYhyy yhglywyhyy = ((YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER));
//        String sql = "select t.id, t.name  as name, t.pid as pid,t.isleaf as isleaf\n" +
//                "  from v_zftz_jsdw_xm t\n" +
//                " where  " +
//                "    t.id not in\n" +
//                "       (select to_char(b.id_zftz_xm) from ZFTZ_XMZJLYDJ b where b.zt = 1)"+
//		        "   and t.id not in\n" +
//		        "       (select to_char(s.id_zftz_xm) from zftz_xmjgjs s where s.zt = 1)";
        String sql = "with xmTable as\n" +
                " (select t.id, t.xmmc, t.jsdw\n" +
                "    from zftz_xm t\n" +
                "   where t.zt = 1\n" +
                "    and t.jsdw in (select entid from v_zftz_yhtoenter where guid = "+yhglywyhyy.getGuid()+")\n" +
                "   and t.jsdw is not null\n" +
                "   and t.id not in\n" +
                "       (select to_char(b.id_zftz_xm) from zftz_xmzjlydj b where b.zt = 1)\n" +
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
        List list = baseDao.selectBySql(sql);//得到List集合
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
    }

    @Override
    public List getXmgsYj(String id) {
        String sql = "select a.id XMGSYJID,a.fkyj,a.lchj  from zftz_xmgsyjzx a where a.zt = 1 and id = " + id;
        List list = baseDao.selectMapsBySQL(sql);
        return list;
    }

    @Override
    public Result selThyj(String id,String type) {
        Result result = new Result();
        String sql = "select l.clyj from zftz_shjl l where sxlx=" + type + " and czlx=2 " +
                "and xmid=" + id + " order by l.clsj desc";
        List l = baseDao.selectBySql(sql);
        if(l.size()>0){
            result.setContent(l.get(0).toString());
        }else{
            result.setContent("无退回意见");
        }
        return result;
    }

    @Override
    public List getAllZGBMTree(HttpSession session) {
        YhglYwYhyy userinfo = (YhglYwYhyy) session.getAttribute(StaticData.LOGINUSER);
        List dataList = new ArrayList();
//        String sql = "select mc as name,id as ID,bm as BM ,fjbm as PID from v_zftz_zgdw ";
        String sql = "select d.mc as name,d.id as ID,d.bm as BM ,d.fjbm as PID from v_zftz_zgdw d ";
        List list =  baseDao.selectBySql(sql);//得到List集合
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                Map map = new HashMap();
                map.put("id", obj[1]);
                map.put("name", obj[0]);
                map.put("pId", obj[3]);
                dataList.add(map);
            }
        }
        return dataList;
    }

}
