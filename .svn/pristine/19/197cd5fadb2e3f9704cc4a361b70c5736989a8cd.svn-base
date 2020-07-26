package com.datanew.service.xmgcjjs.impl;

import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.model.ZftzXmgcjsba;
import com.datanew.model.ZftzXmgcjsbaFymx;
import com.datanew.service.xmgcjjs.FlowService;
import com.datanew.service.xmgcjjs.XmgcjsbbService;
import com.datanew.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author inRush
 * @date 2019/5/21
 **/
@Service
public class XmgcjsbbServiceImpl implements XmgcjsbbService {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmgcjsbbServiceImpl.class);
    private static final String SX_CODE = "9";
    @Autowired
    BaseDao baseDao;
    @Autowired
    FlowService flowService;

    private String[] getSelectSql() {
        String selectCountSql = "select count(*) ";
        String selectDataSql = "select t1.ID,\n" +
                "       t1.ID_ZFTZ_HTBA," +
                "       t2.XMBH,\n" +
                "       t1.XMMC,\n" +
                "       t1.HTMC GCMC,\n" +
                "       t3.XMLXMC XMLX,\n" +
                "       t5.MC JSDW,\n" +
                "       t1.SHZJJG,\n" +
                "       (t1.SSS / 10000 + t1.HZJS / 10000) GCJSSDS,\n" +
                "       t1.JSSDSJ,\n" +
                "       t1.CZR,\n" +
                "       t1.CZSJ ,t1.LCHJ, t1.CZSPYJ ,t8.CZLX ";
        return new String[]{selectCountSql, selectDataSql};
    }

    private Pages getPages(String baseSql, Pages pages) {
        String[] selectSql = getSelectSql();
        List<Map> list = (List<Map>) baseDao.selectMapsBySQL(selectSql[1] + baseSql, null, pages.getOffset(), pages.getLimit());
        Long count = baseDao.getCountBySQL(selectSql[0] + baseSql, null);
        pages.setTotal(count.intValue());
        pages.setRows(list);
        return pages;
    }

    public Pages list(String yhId, Pages pages, Integer status) {
        String statusCondition = status == 0 ? "t1.LCHJ='-1'" : "t1.LCHJ<>'-1'";
        // 获取基本信息 项目编号-项目名称-工程名称-项目类型-建设单位-审核中介机构-结算审定价-结算审定时间-报备人-b报备时间
        String baseSql = "from ZFTZ_XMGCJSBA t1\n" +
                "         left join V_ZFTZ_JSDW t5 on t5.ID = t1.JSDW\n" +
                "         left join ZFTZ_HTBA t on t.ID = t1.ID_ZFTZ_HTBA\n" +
                "         left join\n" +
                "     ZFTZ_XMTZWH t2 on t2.ID_ZFTZ_XM = t.ID_ZFTZ_XM\n" +
                "         left join\n" +
                "     (select t1.*, t2.MC XMLXMC\n" +
                "      from ZFTZ_XM t1,\n" +
                "           ZFTZ_XXB t2\n" +
                "      where to_char(t1.XMLX) = t2.BM\n" +
                "        and t2.LX = 'xmlx') t3 on t3.ID = t.ID_ZFTZ_XM\n" +
                "         left join\n" +
                "     (select t.ID_ZFTZ_HTBA, sum(t.BCHTJE) HTJE from zftz_htbamx t group by t.ID_ZFTZ_HTBA) t4\n" +
                "     on t4.ID_ZFTZ_HTBA = t1.ID_ZFTZ_HTBA\n" +
                "left join (select XMID, CZLX\n" +
                "           from ZFTZ_SHJL t6\n" +
                "           where t6.SXLX='9' and t6.XMID in (select ID\n" +
                "                             from ZFTZ_XMGCJSBA t1\n" +
                "                             where ZT = 1\n" +
                "                               and " + statusCondition + "\n" +
                "                               and JSDW in (select ENTID\n" +
                "                                            from V_ZFTZ_YHTOENTER\n" +
                "                                            where GUID = " + yhId + "))\n" +
                "             and t6.GUID = (select max(t7.GUID) from ZFTZ_SHJL t7 where t7.XMID = t6.XMID)) t8\n" +
                "          on t8.XMID = t1.ID\n" +
                "where " + statusCondition + "\n" +
                "  and t3.ZT = 1\n" +
                "  and t.ZT = 1\n" +
                "  and t1.ZT = 1\n" +
                "  and t1.JSDW in (select ENTID\n" +
                "                  from V_ZFTZ_YHTOENTER\n" +
                "                  where GUID = " + yhId + ")\n" +
                "order by t1.ID";
        return getPages(baseSql, pages);
    }

    public List<Map> htList(String yhId, String htId) {
        String sql = "with htTable as (select ID, HTMC, ID_ZFTZ_XM\n" +
                "                 from ZFTZ_HTBA\n" +
                "                 where ZT = 1\n" +
                "                   and ID not in (select ID_ZFTZ_HTBA from ZFTZ_XMGCJSBA t1 where t1.ZT = 1)),\n" +
                "     xmTable as (select t.id, t.xmmc, t.jsdw\n" +
                "                 from zftz_xm t\n" +
                "                 where t.zt = 1\n" +
                "                   and JSDW in (select y.entid from v_zftz_yhtoenter y where y.guid = '" + yhId + "')),\n" +
                "     dwTable as (select t.id, t.mc, FJBM\n" +
                "                 from v_zftz_jsdw t,\n" +
                "                      xmTable t1,\n" +
                "                      htTable t2\n" +
                "                 where t1.ID = t2.ID_ZFTZ_XM\n" +
                "                   and t.ID = t1.JSDW\n" +
                "                 group by t.ID, MC, BM, FJBM)\n" +
                "select t.ID || '_dw'  ID, t.MC, t.FJBM || '_dw' PID\n" +
                "from V_ZFTZ_JSDW t\n" +
                "start with ID in (select ID from dwTable)\n" +
                "connect by prior FJBM = ID\n" +
                "union all\n" +
                "select to_char(t.ID) || '_xm' id, t.XMMC MC, t.JSDW || '_dw' PID\n" +
                "from xmTable t,\n" +
                "     htTable t1\n" +
                "where t.ID = t1.ID_ZFTZ_XM\n" +
                "group by t.ID, XMMC, JSDW\n" +
                "union all\n" +
                "select to_char(t.ID), t.HTMC MC, to_char(t.ID_ZFTZ_XM) || '_xm' PID\n" +
                "from htTable t,\n" +
                "     xmTable t2\n" +
                "where t.ID_ZFTZ_XM = t2.ID";
        return (List<Map>) baseDao.selectMapsBySQL(sql);
    }

    public Pages todoList(Long userId, Pages pages, Integer status) {
        // 检出是否有待办
        List<String> dbList;
        String lcCondition = "";
        if (status.equals(2)) {
            // 未处理
            dbList = flowService.check(userId, SX_CODE);
            // 未处理的需要排除掉初始流程和结束流程
            lcCondition = "  and t1.LCHJ <> '-1' and t1.LCHJ <> '0'";
        } else if (status.equals(3)) {
            // 经办
            dbList = flowService.checkReviewedProject(userId, SX_CODE);
        } else {
            throw new IllegalArgumentException("当前查询状态错误！");
        }
        if (dbList.size() <= 0) {
            pages.setRows(new ArrayList());
            return pages;
        }
        // 拼接待办业务id列表
        StringBuilder dbSb = new StringBuilder("(");
        for (int i = 0; i < dbList.size(); i++) {
            if (i != 0) {
                dbSb.append(",");
            }
            dbSb.append(dbList.get(i));
        }
        dbSb.append(")");
        String baseSql = "from ZFTZ_XMGCJSBA t1\n" +
                "         left join ZFTZ_HTBA t on t.ID = t1.ID_ZFTZ_HTBA\n" +
                "         left join V_ZFTZ_JSDW t5 on t5.ID = t1.JSDW\n" +
                "         left join ZFTZ_XMTZWH t2 on t2.ID_ZFTZ_XM = t.ID_ZFTZ_XM\n" +
                "         left join\n" +
                "     (select t1.*, t2.MC XMLXMC\n" +
                "      from ZFTZ_XM t1,\n" +
                "           ZFTZ_XXB t2\n" +
                "      where to_char(t1.XMLX) = t2.BM\n" +
                "        and t2.LX = 'xmlx') t3 on t3.ID = t.ID_ZFTZ_XM\n" +
                "         left join (select t.ID_ZFTZ_HTBA, sum(t.BCHTJE) HTJE from zftz_htbamx t group by t.ID_ZFTZ_HTBA) t4\n" +
                "                   on t4.ID_ZFTZ_HTBA = t.ID\n" +
                "           left join (select XMID, CZLX\n" +
                "                    from ZFTZ_SHJL t6\n" +
                "                    where t6.SXLX='9' and t6.XMID in " + dbSb.toString() + "\n" +
                "                      and t6.GUID = (select max(t7.GUID) from ZFTZ_SHJL t7 where t7.XMID = t6.XMID)) t8\n" +
                "                   on t8.XMID = t1.ID\n" +
                "where t.ZT = 1\n" + lcCondition +
                "  and t1.ZT = 1\n" +
                "  and t1.ID in " + dbSb.toString() + "\n" +
                "    order by t1.ID";
        return getPages(baseSql, pages);
    }

    public Map getHtInfo(Integer htId, boolean getDefaultData) {
        // 工程名称，工程内容，项目名称，项目总投资，建设单位,主管部门,已支付工程款,项目编号
        String baseSql = "select t1.ID ID_ZFTZ_HTBA," +
                "       HTMC,\n" +
                "       HTNR GCNR,\n" +
                "       XMMC,\n" +
                "       (nvl(t3.ZTZ_JZAZTZ, 0) + nvl(t3.ZTZ_DTTZ, 0) + nvl(t3.ZTZ_SBTZ, 0) + nvl(t3.ZTZ_QTTZ, 0)) XMZTZ,\n" +
                "       t2.JSDW,\n" +
                "       t2.ZGBM,\n" +
                "       t7.MC                                                                                     JSDWMC,\n" +
                "       t8.MC                                                                                     ZGBMMC,\n" +
                "       nvl(ZJE, 0)                                                                               GCBG_ZJE,\n" +
                "       nvl(-JSE, 0)                                                                               GCBG_JSE,\n" +
                "       nvl(YZFGCK, 0)                                                                            YZFGCK,\n" +
                "       t6.XMBH\n" +
                "from ZFTZ_HTBA t1\n" +
                "         left join ZFTZ_XM t2 on t2.ID = t1.ID_ZFTZ_XM\n" +
                "         left join ZFTZ_XMZJLYDJ t3 on t3.ID_ZFTZ_XM = t1.ID_ZFTZ_XM\n" +
                "         left join\n" +
                "     (select ID_ZFTZ_HTBA, sum(t1.je) YZFGCK\n" +
                "      from ZFTZ_XMZXDJ t,\n" +
                "           ZFTZ_XMZXDJ_KXMX t1\n" +
                "      where t.id = t1.id_zftz_xmzxdj\n" +
                "      group by ID_ZFTZ_HTBA) t4 on t4.ID_ZFTZ_HTBA = t1.ID\n" +
                "         left join\n" +
                "     (select t1.ID_ZFTZ_HTBA,\n" +
                "             sum(case when BCBGJE > 0 then BCBGJE else 0 end)  ZJE,\n" +
                "             sum(case when BCBGJE <= 0 then BCBGJE else 0 end) JSE\n" +
                "      from ZFTZ_XMBGDBA t1,\n" +
                "           ZFTZ_XMBGDBAMX t2\n" +
                "      where t2.ID_ZFTZ_XMBGDBA = t1.ID\n" +
                "      group by ID_ZFTZ_HTBA) t5 on t5.ID_ZFTZ_HTBA = t1.ID\n" +
                "         left join\n" +
                "     ZFTZ_XMTZWH t6 on t6.ID_ZFTZ_XM = t1.ID_ZFTZ_XM\n" +
                "         left join\n" +
                "     V_ZFTZ_JSDW t7 on t7.ID = t2.JSDW\n" +
                "         left join\n" +
                "     V_ZFTZ_ZGDW t8 on t8.ID = t2.ZGBM\n" +
                "where t1.ZT=1 and t1.ID =" + htId;
        String gcbgSql = "select bgdh,\n" +
                "       bgnr,\n" +
                "       bgqrsj,\n" +
                "       bcbgje / 10000 sjzjyszj,\n" +
                "       zhtbl LJZHTJBL,\n" +
                "       zjagsbl LJZJAGSBL\n" +
                "from v_zftz_xmbgqk where ID_ZFTZ_HTBA = " + htId;
        String gcfymx = "select GCFYID,\n" +
                "       GCFYMC,\n" +
                "       TZEFL,\n" +
                "       FL,\n" +
                "       nvl(GSTZJE / 10000, 0)      GSTZJE,\n" +
                "       nvl(YZFJE_HTJE / 10000, 0)  YZFJE_HTJE,\n" +
                "       nvl(YZFJE_WHTJE / 10000, 0) YZFJE_WHTJE,\n" +
                "       ID_ZFTZ_HTBA\n" +
                "from v_zftz_xmgcjs_gcfymx\n" +
                "where ID_ZFTZ_HTBA = " + htId;
        String xmzjlySql = "select nvl(XMZJLY_CZXZJ, 0) ZJLY_CZXZJ, nvl(XMZJLY_ZYPH, 0) ZJLY_ZYPH, nvl(XMZJLY_QT, 0) ZJLY_QT\n" +
                "from v_zftz_xmjbxx t1,\n" +
                "     ZFTZ_HTBA t2\n" +
                "where t1.ID_ZFTZ_XM = t2.ID_ZFTZ_XM\n" +
                "  and t2.ID = " + htId;
        List<Map> base = baseDao.selectMapsBySQL(baseSql);
        if (base.size() == 0) {
            throw new InvalidParameterException("合同ID无效");
        }
        Map htInfo = base.get(0);
        if (getDefaultData) {
            List<Map> xmzjlyList = baseDao.selectMapsBySQL(xmzjlySql);
            if (xmzjlyList.size() > 0) {
                Map xmzjly = xmzjlyList.get(0);
                BigDecimal hj = ((BigDecimal) xmzjly.get("ZJLY_CZXZJ")).add((BigDecimal) xmzjly.get("ZJLY_ZYPH")).add((BigDecimal) xmzjly.get("ZJLY_QT"));
                htInfo.putAll(xmzjly);
                htInfo.put("ZJLY_HJ", hj);
            }
        }
        List<Map> bgdList = baseDao.selectMapsBySQL(gcbgSql);
        List<Map> gcfymxList = baseDao.selectMapsBySQL(gcfymx);
        // 计算应付金额合计
        for (Map item : gcfymxList) {
            item.put("YFJE_XJ", (((BigDecimal) item.get("YZFJE_HTJE")).add((BigDecimal) item.get("YZFJE_WHTJE"))));
        }
        // 插入变更单记录和工程费用明细
        htInfo.put("GCBG", bgdList);
        htInfo.put("GCFYMX", gcfymxList);

        return htInfo;
    }

    public Map getGcjsBa(Integer baId) {
        // 基础数据
        String sql = "select * from ZFTZ_XMGCJSBA where ID=" + baId;
        // 明细数据
        String mxSql = "select * from ZFTZ_XMGCJSBA_FYMX where ID_ZFTZ_XMGCJSBA=" + baId;
        // 附件数据
        String fjSql = "select * from ZFTZ_FILE where FILEBSID='" + baId + "' and FILEBSTYPE='" + SX_CODE + "'";
        List<Map> baDatas = baseDao.selectMapsBySQL(sql);
        if (baDatas.size() <= 0) {
            throw new InvalidParameterException("对应备案未找到");
        }
        List<Map> mxDatas = baseDao.selectMapsBySQL(mxSql);
        List<Map> fjDatas = baseDao.selectMapsBySQL(fjSql);
        Map baData = baDatas.get(0);
        Map htData = getHtInfo(((BigDecimal) baData.get("ID_ZFTZ_HTBA")).intValue(), false);
        baData.putAll(htData);
        baData.put("FJ", fjDatas);
        for (Map item : (List<Map>) htData.get("GCFYMX")) {
            for (Map mx : mxDatas) {
                if (mx.get("GCFYID").equals(item.get("GCFYID")) && mx.get("TZEFL").equals(item.get("TZEFL"))) {
                    item.put("ID", mx.get("ID"));
                    item.put("HTJE", mx.get("HTJE"));
                    item.put("XYJE", mx.get("XYJE"));
                    break;
                }
            }
        }
        return baData;
    }

    public Long saveOrUpdate(ZftzXmgcjsba model, List<ZftzXmgcjsbaFymx> mxs, List<Map> fjSaveModels) {
        Long mainId = model.getId();
        if (model.getId() != null) {
            baseDao.update(model);
        } else {
            baseDao.save(model);
            mainId = model.getId();
        }
        baseDao.executeBySql("delete from ZFTZ_XMGCJSBA_FYMX where ID_ZFTZ_XMGCJSBA=" + mainId);
        for (ZftzXmgcjsbaFymx mx : mxs) {
            mx.setIdZftzXmgcjsba(mainId);
            mx.setId(null);
            baseDao.save(mx);
        }
        for (Map fj : fjSaveModels) {
            String sql = String.format(Locale.CHINA, "update ZFTZ_FILE set FILEBSID='%s', FILEDL='%s', FILEXL='%s' where GUID=%s",
                    mainId, fj.get("FILEDL"), fj.get("FILEXL"), fj.get("FILEID"));
            baseDao.executeBySql(sql);
        }
        return model.getId();
    }

    public void delete(List<Integer> ids) {
        for (Integer id : ids) {
            baseDao.executeBySql("update ZFTZ_XMGCJSBA set ZT=0 where ID=" + id);
        }
    }
}
