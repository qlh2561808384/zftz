package com.datanew.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author inRush
 * @date 2019/6/25
 **/
public interface AlkService {
    /**
     * 获取列定义列表
     *
     * @param bm 项目类型编码
     * @return {@link List<Map>}
     */
    List<Map> getColumnsDefine(String bm);

    /**
     * 保存列定义
     *
     * @param xmlx    项目编码
     * @param columns 列定义
     */
    void saveColumnsDefine(String xmlx, List<Map> columns);

    /**
     * 根据ID删除对应的列定义
     *
     * @param idList id列表
     */
    void deleteColumnsById(List<Integer> idList);

    /**
     * 获取业务表信息（具体就是获取已经占用了几列，一共有几列）
     *
     * @param bm 项目类型编码
     * @return
     */
    Map getBsTableInfo(String bm);

    /**
     * 解析Excel数据
     *
     * @param file excel文件
     * @param bm   项目编码
     * @return
     */
    List<Map> parseExcelData(MultipartFile file, String bm) throws IOException;

    /**
     * 导出excel表格
     *
     * @param response
     * @param bm
     */
    void exportExcelData(HttpServletResponse response, String bm, boolean getValue);

    /**
     * 根据项目类型获取数据
     *
     * @param bm 项目类型编码
     * @return
     */
    List<Map> getData(String bm);

    /**
     * 保存案例库数据
     */
    void save(ArrayList<Map<String, String>> map, String bm) throws ServerException;

    /**
     * 删除数据
     *
     * @param ids
     */
    void delete(List<Integer> ids);
}
