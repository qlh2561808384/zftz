package com.datanew.service.impl;

import com.datanew.dao.BaseDao;
import com.datanew.service.ProFlowService;
import com.datanew.service.xmgcjjs.FlowService;
import com.datanew.service.xmgcjjs.XmgcjjsFlowService;
import com.datanew.service.xmgcjjs.impl.FlowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author inRush
 * @date 2019/6/6
 **/
@Service
public class ProFlowServiceImpl implements ProFlowService {
    @Autowired
    BaseDao baseDao;
    @Autowired
    FlowService flowService;

    private static final Map<String, String> TYPE_MAP = new HashMap<String, String>(3) {
        {
            put("2", "ZFTZ_XMGSYJZX");
            put("3", "ZFTZ_XMGS");
            put("12", "ZFTZ_XMGS_TZ");
        }
    };

    private Long updateSubmitModel(String type, Integer id, String lcbm, String comment) {
        String updateSql = "";
        if("ZFTZ_XMGSYJZX".equals(TYPE_MAP.get(type))){
            updateSql = "update " + TYPE_MAP.get(type) + " set LCHJ='" + lcbm + "', fkyj='" + comment + " 'where ID=" + id;
        }else {
            updateSql = "update " + TYPE_MAP.get(type) + " set LCHJ='" + lcbm + "'where ID=" + id;
        }
        baseDao.executeBySql(updateSql);
        // 返回业务ID
        return Long.valueOf(id.toString());
    }

    private String getDqLchj(String type, Integer ywId) {
        String sql = "select LCHJ from " + TYPE_MAP.get(type) + " where ID=" + ywId;
        List<Map> list = baseDao.selectMapsBySQL(sql);
        return list.get(0).get("LCHJ").toString();
    }

    private FlowService.SubmitEvent getSubmitEvent(final String type, final List<String> lchjs) {
        return new FlowService.SubmitEvent() {
            public String onPrepare(Integer ywId, int index) {
                // 返回当前环节编码
                String realLchj = getDqLchj(type, ywId);
                // 校验流程编码，判断是否已经被处理
                if (!realLchj.equals(lchjs.get(index))) {
                    throw new IllegalArgumentException("流程已被处理！请刷新页面");
                }
                return realLchj;
            }

            public Long onSubmit(Integer id, String nextFlowCode,String comment) {
                // 更新环节编码并且返回业务ID
                return updateSubmitModel(type, id, nextFlowCode, comment);
            }
        };
    }

    public List<Map> getShjl(String type, Integer ywId) {
        /*String sql = "select nvl(z.dqhjmc,'提交') DQHJBM,\n" +
                "       t2.XM,\n" +
                "       t1.DDRQ,\n" +
                "       t1.CLSJ,\n" +
                "       t1.CLYJ,\n" +
                "       case\n" +
                "         when t1.CZLX = '1' then\n" +
                "          '提交'\n" +
                "         else\n" +
                "          '退回'\n" +
                "       end CZLX\n" +
                "  from ZFTZ_SHJL t1\n" +
                "  left join zftz_splc z\n" +
                "    on t1.dqhjbm = z.dqhjbm\n" +
                "   and z.sxbm = 2, YHGL_YW_YH t2\n" +
                " where t2.GUID = t1.CLYHID\n" +
                "   and t1.XMID = " + ywId +
                "  and t1.SXLX = '" + type + "'\n" +
                " order by t1.GUID";*/
        String sql = "select nvl(z.dqhjmc, '提交') DQHJBM,\n" +
                "       t2.XM,\n" +
                "       t1.DDRQ,\n" +
                "       t1.CLSJ,\n" +
                "       t1.CLYJ,\n" +
                "       case\n" +
                "         when t1.CZLX = '1' then\n" +
                "          '提交'\n" +
                "         else\n" +
                "          '退回'\n" +
                "       end CZLX\n" +
                "  from ZFTZ_SHJL t1\n" +
                "  left join zftz_splc z\n" +
                "    on t1.dqhjbm = z.dqhjbm\n" +
                "   and z.sxbm = 2, YHGL_YW_YH t2, yhgl_yw_yhyy yy\n" +
                " where yy.GUID = t1.CLYHID\n" +
                "   and t2.guid = yy.yhid\n" +
                "   and t1.XMID = " + ywId +
                "  and t1.SXLX = '" + type + "'\n" +
                " order by t1.GUID\n";
        List list = baseDao.selectMapsBySQL(sql);
        return list;
    }


    public void submit(String type, Long yhId, List<Integer> ids) throws Exception {
        List<String> lchjs = new ArrayList<String>();
        for (Integer id : ids) {
            lchjs.add("-1");
        }
        this.nextFlow(type, yhId, ids, lchjs, null);
    }

    public void nextFlow(String type, Long yhId, List<Integer> ids, List<String> lchjs, List<String> comments) throws Exception {
        flowService.submit(yhId, ids, type,
                FlowServiceImpl.OperateCode.NEXT, comments, getSubmitEvent(type, lchjs));
    }

    public void backFlow(String type, Long yhId, List<Integer> ids, List<String> lchjs, List<String> comments) throws Exception {
        flowService.submit(yhId, ids, type,
                FlowServiceImpl.OperateCode.BACK, comments, getSubmitEvent(type, lchjs));
    }
}
