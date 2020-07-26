package com.datanew.service.xmgcjjs;

import com.datanew.dto.Pages;
import com.datanew.model.ZftzXmgcjsba;
import com.datanew.model.ZftzXmgcjsbaFymx;
import com.datanew.model.ZftzXmgcjscc;
import com.datanew.model.ZftzXmgcjsccFymx;

import java.util.List;
import java.util.Map;

/**
 * 项目工程抽查服务
 *
 * @author inRush
 * @date 2019/5/29
 **/
public interface XmgcjsccService {
    /**
     * 获取中介机构
     *
     * @param jgBm 机构编码
     * @param jgzz 机构资质
     * @return
     */
    List<Map> getZjjg(String jgBm, String jgzz);

    /**
     * 根据条件获取工程信息
     *
     * @param condition 工程条件
     * @return
     */
    List<Map> getGc(Map condition);

    /**
     * 将source2随机合并到source1中
     *
     * @param source1
     * @param source2
     * @return
     */
    void randomMerge(List<Map> source1, List<Map> source2);


    void save(List<Map> bas) throws Exception;

    /**
     * 获取项目工程结算备案列表
     *
     * @return
     */
    Pages list(String yhzh, Integer status, Pages pages);

    /**
     * 合同列表
     *
     * @return
     */
    List<Map> htList();


    /**
     * 合同关联的信息
     *
     * @return
     */
    Map getHtInfo(Integer htId);

    /**
     * 获取备案详情
     *
     * @param baId
     * @return
     */
    Map getGcjsCc(Integer baId);

    /**
     * 保存或更新
     *
     * @param model
     * @param mxs
     */
    Long saveOrUpdate(ZftzXmgcjscc model, List<ZftzXmgcjsccFymx> mxs, List<Map> fjSaveModels);
    void submit(List<Integer> ccIds) ;
}
