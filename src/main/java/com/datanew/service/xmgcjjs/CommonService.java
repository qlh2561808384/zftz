package com.datanew.service.xmgcjjs;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author inRush
 * @date 2019/5/29
 **/
public interface CommonService {
    /**
     * 获取选项表选项数据
     *
     * @param lx 选项类型
     * @return
     */
    List<Map> getXxList(String lx);

    /**
     * 获取对应事项类型的附件类型
     *
     * @param sxbm 事项编码
     * @return
     */
    List<Map> getFjlx(String sxbm);

    /**
     * 获取中介机构列表
     *
     * @return
     */
    List<Map> getZjjg();

    /**
     * 获取主管单位
     *
     * @return
     */
    List<Map> getZgdw();

    /**
     * 获取按钮权限列表
     *
     * @param btnGroupId
     * @return
     */
    List<String> getButtonPermission(Integer btnGroupId, HttpSession session);

    /**
     * 获取用户类型
     *
     * @param session
     * @return
     */
    String getUserType(HttpSession session);
}
