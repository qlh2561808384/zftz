package com.datanew.service.xmgcjjs.impl;

import com.datanew.dao.BaseDao;
import com.datanew.model.YhglYwYh;
import com.datanew.service.xmgcjjs.CommonService;
import com.datanew.util.StaticData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author inRush
 * @date 2019/5/29
 **/
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    BaseDao baseDao;

    public List<Map> getXxList(String lx) {
        String sql = "select BM, MC, FJBM from ZFTZ_XXB where LX='" + lx + "' and YXBZ='Y'";
        return baseDao.selectMapsBySQL(sql);
    }

    public List<Map> getFjlx(String sxbm) {
        String sql = "select case when BM = '" + sxbm + "' then 1 else 0 end TYPE, BM, MC\n" +
                "from ZFTZ_XXB\n" +
                "where (FJBM = '" + sxbm + "' and LX = 'fjlx' or BM = '" + sxbm + "' and LX = 'sxlx')\n" +
                "  and YXBZ = 'Y'";
        return baseDao.selectMapsBySQL(sql);
    }

    public List<Map> getZjjg() {
        return baseDao.selectMapsBySQL("select * from ZFTZ_ZJJG");
    }

    public List<Map> getZgdw() {
        return baseDao.selectMapsBySQL("select ID, MC \n" +
                "from V_ZFTZ_ZGDW");
    }

    public List<String> getButtonPermission(Integer btnGroupId, HttpSession session) {
        String btnStr = (String) session.getAttribute(StaticData.USERBUTTONSSTR);
        String[] btns = btnStr.split(",");
        List<String> btnList = new ArrayList<String>();
        for (String btn : btns) {
            if (btn.startsWith(btnGroupId.toString())) {
                btnList.add(btn);
            }
        }
        return btnList;
    }

    @Override
    public String getUserType(HttpSession session) {
        YhglYwYh yhinfo = (YhglYwYh) session.getAttribute(StaticData.LOGINUSERINFO);
        return yhinfo.getYhlx();
    }
}
