package com.datanew.service.xmgcjjs;

import java.util.List;
import java.util.Map;

/**
 * @author inRush
 * @date 2019/6/6
 **/
public interface XmgcjjsFlowService {

    /**
     * 获取流程审核记录
     *
     * @param type 流程类型（事项编码）
     * @param ywId 业务ID
     * @return
     */
    List<Map> getShjl(String type, Integer ywId);

    /**
     * 提交流程
     *
     * @param type 流程类型（事项编码）
     * @param yhId 用户ID
     * @param ids  业务ID列表
     */
    void submit(String type, Long yhId, List<Integer> ids) throws Exception;

    /**
     * 跳转到下一个流程
     *
     * @param type    流程类型 （事项编码）
     * @param yhId    用户ID
     * @param ids     业务ID列表
     * @param lchjs   业务流程环节列表
     * @param comments 审批意见
     * @throws Exception
     */
    void nextFlow(String type, Long yhId, List<Integer> ids, List<String> lchjs, List<String> comments) throws Exception;

    /**
     * 退回到上一个流程
     *
     * @param type    流程类型（事项编码）
     * @param yhId    yonghuID
     * @param ids     业务ID列表
     * @param lchjs   流程环节列表
     * @param comments 审批意见
     * @throws Exception
     */
    void backFlow(String type, Long yhId, List<Integer> ids, List<String> lchjs, List<String> comments) throws Exception;


}
