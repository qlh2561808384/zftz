package com.datanew.service.xmgcjjs.impl;

import com.datanew.dao.BaseDao;
import com.datanew.model.ZftzXmjgjs;
import com.datanew.service.xmgcjjs.FlowService;
import com.datanew.service.xmgcjjs.XmgcjsbzService;
import com.datanew.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author inRush
 * @date 2019/6/3
 **/
@Service
public class XmgcjsbzServiceImpl implements XmgcjsbzService {
    private static final String SX_CODE = "11";
    @Autowired
    BaseDao baseDao;
    @Autowired
    FlowService flowService;

    public List getXmList(String yhId, boolean excludeExistItem) {
        String sql = "with xmTable as (select t.id, t.xmmc, t.jsdw\n" +
                "                 from zftz_xm t\n" +
                "                 where t.zt = 1\n" +
                "                   and JSDW in (select y.entid from v_zftz_yhtoenter y where y.guid = '" + yhId + "')\n" +
                "                   and t.id not in (select ID_ZFTZ_XM from ZFTZ_XMJGJS where ZT = 1)),\n" +
                "     dwTable as (select t.id, t.mc, FJBM\n" +
                "                 from v_zftz_jsdw t,\n" +
                "                      xmTable t1\n" +
                "                 where t.ID = t1.JSDW\n" +
                "                 group by t.ID, MC, BM, FJBM)\n" +
                "select distinct t.ID || '_dw' ID, t.MC, t.FJBM || '_dw' PID\n" +
                "from V_ZFTZ_JSDW t\n" +
                "start with ID in (select ID from dwTable)\n" +
                "connect by prior FJBM = ID\n" +
                "union all\n" +
                "select to_char(t.ID) id, t.XMMC MC, t.JSDW || '_dw' PID\n" +
                "from xmTable t";
//        if (!StringUtil.isblank(yhId)) {
//            sql += "  and t.JSDW in (select ENTID\n" +
//                    "from V_ZFTZ_YHTOENTER where GUID=" + yhId + ")\n";
//        }
//        if (excludeExistItem) {
//            sql += "  and t.ID not in (select ID_ZFTZ_XM\n" +
//                    "                    from ZFTZ_XMJGJS\n" +
//                    "                    where ZT = 1\n" +
//                    "                      and JSDW in (select ENTID\n" +
//                    "from V_ZFTZ_YHTOENTER where GUID=" + yhId + "))";
//        }
        return baseDao.selectMapsBySQL(sql);
    }

    @Override
    public Map getXmInfo(Integer xmId, boolean getDefaultData) {
        String baseSql = "select t1.JSDW,\n" +
                "       t2.MC           JSDWMC,\n" +
                "       t3.ID           ZGBM,\n" +
                "       t3.MC           ZGBMMC,\n" +
                "       nvl(t4.GSJE, 0) XMGSJE,\n" +
                "       t5.JSDZ         XMGHXZ\n" +
                "from ZFTZ_XM t1\n" +
                "         left join\n" +
                "     V_ZFTZ_JSDW t2 on t2.ID = t1.JSDW\n" +
                "         left join\n" +
                "     V_ZFTZ_ZGDW t3 on t3.ID = t1.ZGBM\n" +
                "         left join\n" +
                "     (select t.id_zftz_xm ID_XM, sum(t.gstzje) GSJE\n" +
                "      from v_zftz_gcfy t\n" +
                "      group by t.id_zftz_xm) t4 on t4.ID_XM = t1.ID\n" +
                "         left join\n" +
                "     ZFTZ_XMTZWH t5 on t5.ID_ZFTZ_XM = t1.ID\n" +
                "where t1.ZT = 1\n" +
                "  and t1.ID = " + xmId;
        List<Map> list = baseDao.selectMapsBySQL(baseSql);
        if (list.size() <= 0) {
            throw new IllegalArgumentException("对应项目未找到！");
        }
        Map xmInfo = list.get(0);
        String defaultDataSql = "select zjly, nvl(sum(xmjhje), 0) XMJHJE, nvl(sum(xmdwje), 0) XMDWJE, nvl(sum(xmjyje), 0) XMJYJE\n" +
                "from v_zftz_xmcwjs_xmzjly\n" +
                "where id_zftz_xm = " + xmId + "\n" +
                "group by zjly";
        if (getDefaultData) {
            List<Map> defaultData = baseDao.selectMapsBySQL(defaultDataSql);
            double jhHj = 0, sjHj = 0, jyHj = 0;
            for (Map data : defaultData) {
                String suffix = "";
                if ("1".equals(data.get("ZJLY").toString())) {
                    suffix = "CZZJ";
                } else if ("2".equals(data.get("ZJLY").toString())) {
                    suffix = "ZYPH";
                } else if ("3".equals(data.get("ZJLY").toString())) {
                    suffix = "QTZJ";
                }
                double jhJe = Double.parseDouble(data.get("XMJHJE").toString());
                double sjJe = Double.parseDouble(data.get("XMDWJE").toString());
                double jyJe = Double.parseDouble(data.get("XMJYJE").toString());
                jhHj += jhJe;
                sjHj += sjJe;
                jyHj += jyJe;
                xmInfo.put("JH_" + suffix, jhJe);
                xmInfo.put("SJ_" + suffix, sjJe);
                xmInfo.put("JY_" + suffix, jyJe);
            }
            xmInfo.put("JH_HJ", jhHj);
            xmInfo.put("SJ_HJ", sjHj);
            xmInfo.put("JY_HJ", jyHj);
        }
        return xmInfo;
    }

    public Long saveOrUpdate(ZftzXmjgjs model, ArrayList<Map> fjs) {
        Long mainId = model.getId();
        if (model.getId() != null) {
            baseDao.update(model);
        } else {
            baseDao.save(model);
            mainId = model.getId();
        }
        for (Map fj : fjs) {
            String sql = String.format(Locale.CHINA, "update ZFTZ_FILE set FILEBSID='%s', FILEDL='%s', FILEXL='%s' where GUID=%s",
                    mainId, fj.get("FILEDL"), fj.get("FILEXL"), fj.get("FILEID"));
            baseDao.executeBySql(sql);
        }
        return mainId;
    }

    public List<Map> list(String yhId, Integer status) {
        String statusCondition = status == 0 ? " and LCHJ<>'0' " : " and LCHJ='0' ";
        String sql = "select t1.ID,\n" +
                "       t2.XMBH,\n" +
                "       t3.XMMC,\n" +
                "       t3.XMLX,\n" +
                "       t5.MC   JSDW,\n" +
                "       t6.JGMC SHZJJG,\n" +
                "       t1.LCHJ ZT,\n" +
                "       t1.CZR,\n" +
                "       t1.CZSJ,\n" +
                "       t1.LCHJ,\n" +
                "       t8.CZLX,\n" +
                "       t1.CZSPYJ\n" +
                "from ZFTZ_XMJGJS t1\n" +
                "         left join ZFTZ_XMTZWH t2 on t1.ID_ZFTZ_XM = t2.ID_ZFTZ_XM\n" +
                "         left join (select t1.ID, t1.XMMC, t2.MC XMLX\n" +
                "                    from ZFTZ_XM t1,\n" +
                "                         ZFTZ_XXB t2\n" +
                "                    where t1.XMLX = t2.BM\n" +
                "                      and t1.ZT = 1\n" +
                "                      and t2.LX = 'xmlx') t3 on t3.ID = t1.ID_ZFTZ_XM\n" +
                "         left join V_ZFTZ_JSDW t5 on t5.ID = t1.JSDW\n" +
                "         left join ZFTZ_ZJJG t6 on t6.JGBM = t1.SHZJJG\n" +
                "         left join (select XMID YWID, CZLX\n" +
                "                    from ZFTZ_SHJL t6\n" +
                "                    where t6.SXLX = '11' and t6.XMID in (select ID\n" +
                "                                      from ZFTZ_XMJGJS\n" +
                "                                      where ZT = 1\n" + statusCondition +
                "                                        and JSDW in (select ENTID\n" +
                "                                                     from V_ZFTZ_YHTOENTER\n" +
                "                                                     where GUID = " + yhId + "))\n" +
                "                      and t6.GUID = (select max(t7.GUID) from ZFTZ_SHJL t7 where t7.XMID = t6.XMID)) t8\n" +
                "                   on t8.YWID = t1.ID\n" +
                "where t1.ZT = 1\n" +
                "  and t1.JSDW in (select ENTID\n" +
                "                  from V_ZFTZ_YHTOENTER\n" +
                "                  where GUID = " + yhId + ")\n" + statusCondition +
                "order by t1.ID";
        return baseDao.selectMapsBySQL(sql);
    }

    public Map getDetail(Integer id) {
        List<Map> list = baseDao.selectMapsBySQL("select t1.*, t4.XMMC, t2.MC JSDWMC, t5.XMBH, t3.MC ZGBMMC\n" +
                "from ZFTZ_XMJGJS t1\n" +
                "         left join V_ZFTZ_JSDW t2 on t2.ID = t1.JSDW\n" +
                "         left join V_ZFTZ_ZGDW t3 on t3.ID = t1.ZGBM\n" +
                "         left join ZFTZ_XM t4 on t4.ID = t1.ID_ZFTZ_XM\n" +
                "         left join ZFTZ_XMTZWH t5 on t5.ID_ZFTZ_XM=t1.ID_ZFTZ_XM\n" +
                "where t1.ID =" + id);
        if (list.size() <= 0) {
            throw new IllegalArgumentException("对应工程竣工结算详情未找到");
        }
        Map detail = list.get(0);
        String fjSql = "select * from ZFTZ_FILE where FILEBSID='" + id + "'  and FILEBSTYPE='" + SX_CODE + "'";
        List<Map> fjDatas = baseDao.selectMapsBySQL(fjSql);
        detail.put("FJ", fjDatas);
        return detail;
    }

    public void delete(List<Integer> ids) {
        for (Integer id : ids) {
            baseDao.executeBySql("update ZFTZ_XMJGJS\n" +
                    "set ZT=0\n" +
                    "where ID = " + id);
        }
    }

    public List<Map> todoList(Long userId, Integer status) {
        // 检出是否有待办
        List<String> dbList;
        String lcCondition = "";
        if (status.equals(0)) {
            // 未核实
            dbList = flowService.check(userId, SX_CODE);
            lcCondition = "  and t1.LCHJ <> '-1' and t1.LCHJ <> '0'";
        } else if (status.equals(1)) {
            // 已核实
            dbList = flowService.checkReviewedProject(userId, SX_CODE);
        } else {
            throw new IllegalArgumentException("查询状态错误");
        }
        if (dbList.size() <= 0) {
            return new ArrayList<Map>();
        }
        // 拼接待办业务id列表
        StringBuilder dbSb = new StringBuilder();
        for (int i = 0; i < dbList.size(); i++) {
            if (i != 0) {
                dbSb.append(",");
            }
            dbSb.append(dbList.get(i));
        }
        String sql = "select t1.ID,\n" +
                "       t2.XMBH,\n" +
                "       t3.XMMC,\n" +
                "       t3.XMLX,\n" +
                "       t5.MC   JSDW,\n" +
                "       t6.JGMC SHZJJG,\n" +
                "       t1.LCHJ ZT,\n" +
                "       t1.CZR,\n" +
                "       t1.CZSJ,\n" +
                "       t1.LCHJ,\n" +
                "       t1.CZSPYJ\n" +
                "from ZFTZ_XMJGJS t1\n" +
                "         left join ZFTZ_XMTZWH t2 on t1.ID_ZFTZ_XM = t2.ID_ZFTZ_XM\n" +
                "         left join (select t1.ID, t1.XMMC, t2.MC XMLX\n" +
                "                    from ZFTZ_XM t1,\n" +
                "                         ZFTZ_XXB t2\n" +
                "                    where t1.XMLX = t2.BM\n" +
                "                      and t1.ZT = 1\n" +
                "                      and t2.LX = 'xmlx') t3 on t3.ID = t1.ID_ZFTZ_XM\n" +
                "         left join V_ZFTZ_JSDW t5 on t5.ID = t1.JSDW\n" +
                "         left join ZFTZ_ZJJG t6 on t6.JGBM = t1.SHZJJG\n" +
                "where t1.ID in (" + dbSb.toString() + ")" + lcCondition;
        return baseDao.selectMapsBySQL(sql);
    }

    @Override
    public void updateComment(List<Integer> ids, String comment) {
        for (Integer id : ids) {
            String sql = "update ZFTZ_XMJGJS set CZSPYJ='" + comment + "' where ID=" + id;
            baseDao.executeBySql(sql);
        }
    }
}
