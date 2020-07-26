package com.datanew.service.xmgcjjs.impl;

import com.datanew.dao.BaseDao;
import com.datanew.dto.Pages;
import com.datanew.model.*;
import com.datanew.service.xmgcjjs.XmgcjsccService;
import com.datanew.util.JavaBeanUtil;
import com.datanew.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.*;

/**
 * @author inRush
 * @date 2019/5/29
 **/
@Service
public class XmgcjsccServiceImpl implements XmgcjsccService {
    private static final String SX_CODE = "10";

    @Autowired
    BaseDao baseDao;

    private String connectCondition(String baseSql, String[] condition) {
        StringBuilder sql = new StringBuilder(baseSql);
        boolean isFirst = true;
        for (String s : condition) {
            if (StringUtil.isblank(s)) {
                continue;
            }
            if (isFirst) {
                sql.append(" where ");
                isFirst = false;
            } else {
                sql.append(" and ");
            }
            sql.append(s);
        }
        return sql.toString();
    }

    public List<Map> getZjjg(String jgBm, String jgzz) {
        String[] bms = jgBm.split(",");
        StringBuilder bmStrs = new StringBuilder();
        boolean isFirst = true;
        for (String bm : bms) {
            if (StringUtil.isblank(bm)) {
                continue;
            }
            if (!isFirst) {
                bmStrs.append(",");
            }
            isFirst = false;
            bmStrs.append("'").append(bm).append("'");
        }
        String[] condition = new String[2];
        int index = 0;
        if (bms.length > 0 && !StringUtil.isblank(bmStrs.toString())) {
            condition[index++] = "JGFL in (" + bmStrs.toString() + ")";
        }
        if (!StringUtil.isblank(jgzz)) {
            condition[index] = "JGZZ like '%" + jgzz + "%'";
        }
        String sql = connectCondition("select * from ZFTZ_ZJJG", condition);
        return baseDao.selectMapsBySQL(sql);
    }

    public List<Map> getGc(Map mapCondition) {
        try {
            GcfwCondition gcfwCondition = JavaBeanUtil.convertMap(GcfwCondition.class, mapCondition, new JavaBeanUtil.UppercaseConverter());
            String[] condition = new String[7];
            int index = 0;
            if (!StringUtil.isblank(gcfwCondition.getBbqsrq())) {
                condition[index++] = "CZSJ >= to_date('" + gcfwCondition.getBbqsrq() + " 00:00:00', 'yyyy-MM-dd hh24:mi:ss')";
            }
            if (!StringUtil.isblank(gcfwCondition.getBbjzrq())) {
                condition[index++] = "CZSJ <= to_date('" + gcfwCondition.getBbjzrq() + " 23:59:59', 'yyyy-MM-dd hh24:mi:ss')";
            }
            if (!StringUtil.isblank(gcfwCondition.getXmlx())) {
                condition[index++] = "XMLX in (" + gcfwCondition.getXmlx() + ")";
            }
            if (gcfwCondition.getStart_je() != null) {
                condition[index++] = "HTJE >= " + (gcfwCondition.getStart_je() * 10000);
            }
            if (gcfwCondition.getEnd_je() != null) {
                condition[index++] = "HTJE <= " + (gcfwCondition.getEnd_je() * 10000);
            }
            if (gcfwCondition.getLjbgje() != null) {
                condition[index++] = "BGJE > " + (gcfwCondition.getLjbgje() * 10000);
            }
            if (!StringUtil.isblank(gcfwCondition.getSfcgs())) {
                condition[index] = "SFCGS in (" + gcfwCondition.getSfcgs() + ")";
            }
            String sql = connectCondition("select * from V_ZFTZ_XMGCJSCC", condition);
            return baseDao.selectMapsBySQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public void randomMerge(List<Map> source1, List<Map> source2) {
        Random random = new Random(System.currentTimeMillis());
        for (Map item : source1) {
            int randomNumber = random.nextInt(source2.size());
            item.putAll(source2.get(randomNumber));
        }
    }

    public void save(List<Map> bas) throws Exception {
        if (bas.size() == 0) {
            return;
        }
        Map<String, String> zjjgMap = new HashMap();
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (Map ba : bas) {
            zjjgMap.put(String.valueOf(ba.get("id")), String.valueOf(ba.get("jgbm")));
            if (!isFirst) {
                sb.append(",");
            }
            sb.append(ba.get("id"));
            isFirst = false;
        }

        List<Map> list = baseDao.selectMapsBySQL("select *\n" +
                "from ZFTZ_XMGCJSBA\n" +
                "where ID in (" + sb.toString() + ")");
        for (Map item : list) {
            ZftzXmgcjscc cc = JavaBeanUtil.convertMap(ZftzXmgcjscc.class, item, new JavaBeanUtil.CamelCaseConverter());
            Long oldId = cc.getId();
            cc.setShzjjg(zjjgMap.get(cc.getId().toString()));
            cc.setId(null);
            baseDao.save(cc);
            List<Map> mxList = baseDao.selectMapsBySQL("select * from ZFTZ_XMGCJSBA_FYMX where ID_ZFTZ_XMGCJSBA=" + oldId);
            for (Map mxItem : mxList) {
                ZftzXmgcjsccFymx ccMx = JavaBeanUtil.convertMap(ZftzXmgcjsccFymx.class, mxItem, new JavaBeanUtil.CamelCaseConverter());
                ccMx.setIdZftzXmgcjscc(cc.getId());
                ccMx.setId(null);
                baseDao.save(ccMx);
            }
            /*List<Map> fjList = baseDao.selectMapsBySQL("select * from ZFTZ_FILE where FILEBSID='" + oldId + "'");
            for (Map fjItem : fjList) {
                ZftzFile file = JavaBeanUtil.convertMap(ZftzFile.class, fjItem, new JavaBeanUtil.CamelCaseConverter());
                file.setFilebsid(cc.getId().toString());
                file.setGuid(null);
                baseDao.save(file);
            }*/
        }
    }

    private String[] getSelectSql() {
        String selectCountSql = "select count(*) ";
        String selectDataSql = "select t1.ID,\n" +
                "       t2.XMBH,\n" +
                "       t1.XMMC,\n" +
                "       t1.HTMC GCMC,\n" +
                "       t3.XMLXMC XMLX,\n" +
                "       t5.MC JSDW,\n" +
                "       t1.SHZJJG,\n" +
                "       (t1.SSS + t1.HZJS) GCJSSDS,\n" +
                "       t1.JSSDSJ,\n" +
                "       t1.CZR,\n" +
                "       t1.CZSJ ";
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

    public Pages list(String yhzh, Integer status, Pages pages) {
        // 获取基本信息 项目编号-项目名称-工程名称-项目类型-建设单位-审核中介机构-结算审定价-结算审定时间-报备人-报备时间
        String baseSql = "from ZFTZ_XMGCJSCC t1\n" +
                "     left join V_ZFTZ_JSDW t5 on t5.ID = t1.JSDW\n" +
                "     left join ZFTZ_HTBA t on t.ID=t1.ID_ZFTZ_HTBA\n" +
                "     left join\n" +
                "     ZFTZ_XMTZWH t2 on t2.ID_ZFTZ_XM=t.ID_ZFTZ_XM\n" +
                "     left join\n" +
                "     (select t1.*,t2.MC XMLXMC from ZFTZ_XM t1, ZFTZ_XXB t2 where to_char(t1.XMLX)=t2.BM and t2.LX='xmlx') t3 on t3.ID=t.ID_ZFTZ_XM\n" +
                "         left join\n" +
                "     (select t.ID_ZFTZ_HTBA, sum(t.BCHTJE) HTJE from zftz_htbamx t group by t.ID_ZFTZ_HTBA) t4 on t4.ID_ZFTZ_HTBA=t1.ID_ZFTZ_HTBA\n" +
                "where t1.ZT=" + status +
                " order by t1.ID";
        return getPages(baseSql, pages);
    }

    public List<Map> htList() {
        String sql = "select ID, HTMC from ZFTZ_HTBA";
        List<Map> list = baseDao.selectMapsBySQL(sql);
        return list;
    }


    public Map getHtInfo(Integer htId) {
        // 工程名称，工程内容，项目名称，项目总投资，建设单位,主管部门,已支付工程款,项目编号
        String baseSql = "select HTMC,\n" +
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
                "where t1.ID =" + htId;
        String gcbgSql = "select t.bgdh,\n" +
                "       t.bgnr,\n" +
                "       t.bgqrsj,\n" +
                "       (t.sjzjyszj/10000) sjzjyszj,\n" +
                "       (t1.htje/10000) htje,\n" +
                "       (t2.jatzje/10000) jatzje,\n" +
                "       (t2.ztzje/10000) ztzje\n" +
                "from ZFTZ_XMBGDBA t,\n" +
                "     (select t.id_zftz_htba, sum(t.bchtje) htje from zftz_htbamx t group by t.id_zftz_htba) t1,\n" +
                "     (select id_zftz_xm, sum(jatzje) jatzje, sum(ztzje) ztzje\n" +
                "      from (\n" +
                "               select t.id_zftz_xm,\n" +
                "                      nvl(t1.tze_jztz, 0) + nvl(t1.tze_aztz, 0) jatzje,\n" +
                "                      nvl(t1.tze_jztz, 0) + nvl(t1.tze_aztz, 0) + nvl(t1.tze_sbtz, 0) +\n" +
                "                      nvl(t1.tze_qttz, 0)                       ztzje\n" +
                "               from zftz_xmgs t,\n" +
                "                    zftz_xmgsmx t1\n" +
                "               where t.id = t1.id_zftz_xmgs)\n" +
                "      group by id_zftz_xm) t2\n" +
                "where t.id_zftz_htba = t1.id_zftz_htba\n" +
                "  and t.id_zftz_xm = t2.id_zftz_xm\n" +
                "  and t.ID_ZFTZ_HTBA = " + htId;
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
        List<Map> base = baseDao.selectMapsBySQL(baseSql);
        if (base.size() == 0) {
            throw new InvalidParameterException("合同ID无效");
        }
        Map htInfo = base.get(0);
        List<Map> bgdList = baseDao.selectMapsBySQL(gcbgSql);
        // 计算变更单比例（累计占合同比例，累计占建安概算比例）
        for (Map item : bgdList) {
            item.put("LJZHTJBL", ((BigDecimal) item.get("SJZJYSZJ")).divide(((BigDecimal) item.get("HTJE")), 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
            item.put("LJZJAGSBL", ((BigDecimal) item.get("SJZJYSZJ")).divide(((BigDecimal) item.get("JATZJE")), 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
        }
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

    public Map getGcjsCc(Integer baId) {
        // 基础数据
        String sql = "select * from ZFTZ_XMGCJSCC where ID=" + baId;
        // 明细数据
        String mxSql = "select * from ZFTZ_XMGCJSCC_FYMX where ID_ZFTZ_XMGCJSCC=" + baId;
        // 附件数据
        String fjSql = "select * from ZFTZ_FILE where FILEBSID='" + baId + "'  and FILEBSTYPE='" + SX_CODE + "'";
        List<Map> baDatas = baseDao.selectMapsBySQL(sql);
        if (baDatas.size() <= 0) {
            throw new InvalidParameterException("对应备案未找到");
        }
        List<Map> mxDatas = baseDao.selectMapsBySQL(mxSql);
        List<Map> fjDatas = baseDao.selectMapsBySQL(fjSql);
        Map baData = baDatas.get(0);
        Map htData = getHtInfo(((BigDecimal) baData.get("ID_ZFTZ_HTBA")).intValue());
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

    public Long saveOrUpdate(ZftzXmgcjscc model, List<ZftzXmgcjsccFymx> mxs, List<Map> fjSaveModels) {
        Long mainId = model.getId();
        if (model.getId() != null) {
            baseDao.update(model);
        } else {
            baseDao.save(model);
            mainId = model.getId();
        }
        for (ZftzXmgcjsccFymx mx : mxs) {
            mx.setIdZftzXmgcjscc(mainId);
            if (mx.getId() != null) {
                baseDao.update(mx);
            } else {
                baseDao.save(mx);
            }
        }
        for (Map fj : fjSaveModels) {
            String sql = String.format(Locale.CHINA, "update ZFTZ_FILE set FILEBSID='%s', FILEDL='%s', FILEXL='%s' where GUID=%s",
                    mainId, fj.get("FILEDL"), fj.get("FILEXL"), fj.get("FILEID"));
            baseDao.executeBySql(sql);
        }
        return model.getId();
    }

    public void submit(List<Integer> ccIds) {
        for (Integer ccId : ccIds) {
            baseDao.executeBySql("update ZFTZ_XMGCJSCC set ZT=2 where ID=" + ccId);
        }
    }
}
