package com.datanew.service.xmgcjjs;

import com.datanew.dto.Pages;
import com.datanew.model.ZftzFile;
import com.datanew.model.ZftzXmgcjsba;
import com.datanew.model.ZftzXmgcjsbaFymx;
import javassist.NotFoundException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author inRush
 * @date 2019/5/21
 **/
public interface XmgcjsbbService {
    /**
     * 获取项目工程结算备案列表
     *
     * @return
     */
    Pages list(String yhzh, Pages pages, Integer status);

    /**
     * 合同列表
     *
     * @return
     */
    List<Map> htList(String dwId,String htId);

    /**
     * 合同关联的信息
     *
     * @return
     */
    Map getHtInfo(Integer htId, boolean getDefaultData);

    /**
     * 获取备案详情
     *
     * @param baId
     * @return
     */
    Map getGcjsBa(Integer baId);

    /**
     * 保存或更新
     *
     * @param model
     * @param mxs
     */
    Long saveOrUpdate(ZftzXmgcjsba model, List<ZftzXmgcjsbaFymx> mxs, List<Map> fjSaveModels);


    /**
     * 删除
     *
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 待办列表
     *
     * @return
     */
    Pages todoList(Long userId, Pages pages, Integer status);
}
