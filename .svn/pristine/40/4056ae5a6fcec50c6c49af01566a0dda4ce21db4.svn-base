package com.datanew.service.impl;

import com.datanew.dao.BaseDao;
import com.datanew.model.ZftzAlkdy;
import com.datanew.service.AlkService;
import com.datanew.util.ExcelCreator;
import com.datanew.util.ExcelParser;
import com.datanew.util.JavaBeanUtil;
import com.datanew.util.StaticData;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author inRush
 * @date 2019/6/25
 **/
@Service
public class AlkServiceImpl implements AlkService {
    @Autowired
    BaseDao baseDao;

    @Override
    public List<Map> getColumnsDefine(String bm) {
        String sql = "select *\n" +
                "from ZFTZ_ALKDY where XMLX='" + bm + "' order by ID";
        return baseDao.selectMapsBySQL(sql);
    }

    @Override
    public void saveColumnsDefine(String xmlx, List<Map> columns) {
        try {
            baseDao.executeBySql("delete ZFTZ_ALKDY\n" +
                    "where XMLX = '" + xmlx + "'");
            for (Map column : columns) {
                ZftzAlkdy alkdy = JavaBeanUtil.convertMap(ZftzAlkdy.class, column, new JavaBeanUtil.CamelCaseConverter());
                alkdy.setXmlx(xmlx);
                baseDao.save(alkdy);
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("上传参数异常");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("上传参数异常");
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("上传参数异常");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("上传参数异常");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("上传参数异常");
        }
    }

    @Override
    public void deleteColumnsById(List<Integer> idList) {
        for (Integer id : idList) {
            String sql = "delete ZFTZ_ALKDY\n" +
                    "where ID = " + id;
            baseDao.executeBySql(sql);
        }
    }

    @Override
    public Map getBsTableInfo(String bm) {
        String sql = "select MAX_COUNT, CURRENT_COUNT\n" +
                "from (select count(*) - 3 MAX_COUNT\n" +
                "      from user_tab_cols utc\n" +
                "      where utc.table_name = 'ZFTZ_ALK') t1,\n" +
                "     (select count(*) CURRENT_COUNT\n" +
                "      from ZFTZ_ALKDY\n" +
                "      where XMLX = '" + bm + "')";
        return (Map) baseDao.selectMapsBySQL(sql).get(0);
    }

    @Override
    public List<Map> parseExcelData(MultipartFile file, String bm) throws IOException {
        String[][] table = ExcelParser.getParser(ExcelParser.Type.XLSX)
                .load(file.getInputStream())
                .parse("sheet1")
                .getTable();
        List<Map> columns = getEnableColumnsDefine(bm);
        List<Map> tableList = new ArrayList<Map>();
        for (int i = 0; i < table.length; i++) {
            if (i == 0) {
                continue;
            }
            String[] row = table[i];
            Map column = new HashMap();
            for (int j = 0; j < columns.size(); j++) {
                column.put(columns.get(j).get("ZDM"), row[j]);
            }
            tableList.add(column);
        }
        return tableList;
    }

    @Override
    public void exportExcelData(HttpServletResponse response, String bm, boolean getValue) {
        String xmlxSql = "select MC from ZFTZ_XXB where BM='" + bm + "' and LX='xmlx'";
        // 获取表格的列定义
        List<Map> columns = getEnableColumnsDefine(bm);
        List<Map> xmlxs = baseDao.selectMapsBySQL(xmlxSql);
        String fileName = xmlxs.get(0).get("MC").toString().concat("模板");
        List<Map> data = new ArrayList<Map>();
        if (getValue) {
            data = getData(bm);
        }
        String[][] table = new String[data.size() + 1][columns.size()];
        for (int i = 0; i < columns.size(); i++) {
            table[0][i] = (String) columns.get(i).get("XSMC");
        }
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < columns.size(); j++) {
                String zdm = (String) columns.get(j).get("ZDM");
                table[i + 1][j] = (String) data.get(i).get(zdm);
            }
        }
        try {
            ExcelCreator creator = ExcelCreator.create()
                    .addSheet("Sheet1", table);
            for (int i = 0; i < columns.size(); i++) {
                XSSFDataFormat format = creator.getWorkbook().createDataFormat();
                if (columns.get(i).get("ZDLX").equals("2")) {
                    creator.setColumnStyle("sheet1", i, format.getFormat((String) columns.get(i).get("ZDGS")));
                } else if (columns.get(i).get("ZDLX").equals("3")) {
                    StringBuilder formatStr = new StringBuilder("0");
                    int accuracy = Integer.parseInt((String) columns.get(i).get("ZDGS"));
                    boolean first = true;
                    for (int j = 0; j < accuracy; j++) {
                        if (first) {
                            formatStr.append(".");
                            first = false;
                        }
                        formatStr.append("0");
                    }
                    creator.setColumnStyle("sheet1", i, format.getFormat(formatStr.toString()));
                }
            }
            creator.bold(new ExcelCreator.TableRange[]{
                    new ExcelCreator.TableRange(0, 0, 0, columns.size() - 1)
            }).exportToFontEnd(response, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Map> getEnableColumnsDefine(String bm) {
        String sql = "select *\n" +
                "from ZFTZ_ALKDY where XMLX='" + bm + "' and ZT = 1 order by ID";
        return baseDao.selectMapsBySQL(sql);
    }

    private String buildSaveSql(List<Map> columnsDefine, String bm) {
        StringBuilder columnSql = new StringBuilder("(ID,XMLX,ZT");
        StringBuilder valueSql = new StringBuilder(" values(seq_zftz_alk.nextval," + bm + ",1");
        for (Map map : columnsDefine) {
            columnSql.append(",");
            columnSql.append(map.get("ZDM"));
            valueSql.append(",");
            valueSql.append("?");
        }
        columnSql.append(")");
        valueSql.append(")");
        return "insert into ZFTZ_ALK ".concat(columnSql.toString()).concat(valueSql.toString());
    }

    @Override
    public List<Map> getData(String bm) {
        List<Map> columns = getEnableColumnsDefine(bm);
        StringBuilder selectDataStr = new StringBuilder("ID");
        for (Map column : columns) {
            selectDataStr.append(",").append(column.get("ZDM"));
        }
        String sql = "select " + selectDataStr.toString() + " from ZFTZ_ALK where XMLX='" + bm + "' and ZT=1";
        return baseDao.selectMapsBySQL(sql);
    }

    @Override
    public void save(ArrayList<Map<String, String>> map, String bm) throws ServerException {
        if (map.size() <= 0) {
            return;
        }
        StaticData.signs.acquireUninterruptibly(2);
        Connection connection = baseDao.getConnection();
        if (connection == null) {
            throw new ServerException("获取数据库连接失败");
        }
        PreparedStatement insertSt = null;
        try {
            baseDao.executeBySql("delete ZFTZ_ALK where XMLX='" + bm + "'");
            connection.setAutoCommit(false);
            // 获取启用的列
            List<Map> columnsDefine = getEnableColumnsDefine(bm);
            // 根据定义的列创建SQL
            String sql = buildSaveSql(columnsDefine, bm);
            insertSt = connection.prepareStatement(sql);
            // 根据定义的列，取出上传数据中的列数据，设置进ST中
            for (Map<String, String> row : map) {
                for (int i = 0; i < columnsDefine.size(); i++) {
                    Map column = columnsDefine.get(i);
                    insertSt.setString(i + 1, row.get(column.get("ZDM")));
                }
                insertSt.addBatch();
            }
            insertSt.executeBatch();
            insertSt.clearBatch();
            connection.commit();
        } catch (ClassCastException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new IllegalArgumentException("上传数据格式异常！");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new ServerException("数据库保存异常！");
        } finally {
            try {
                if (insertSt != null) {
                    insertSt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            StaticData.signs.release(2);
        }
    }


    @Override
    public void delete(List<Integer> ids) {
        for (Integer id : ids) {
            baseDao.executeBySql("update ZFTZ_ALK set ZT=0 where ID=" + id);
        }
    }
}
