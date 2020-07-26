package com.datanew.action;

import com.datanew.util.ExcelCreator;
import com.datanew.util.ExcelParser;
import com.datanew.util.ExcelCreator.TableRange;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author inRush
 * @date 2019/5/23
 **/
@Controller
@ResponseBody
@RequestMapping(value = "excel")
public class ExcelController {
    public void parseExcel(MultipartFile file) {
        try {
            String suffix = file.getOriginalFilename().split(".")[1];
            ExcelParser.Type type = ExcelParser.Type.getType(suffix);
            String[][] table1 = ExcelParser.getParser(type).load(file.getInputStream()).parse(0).getTable();
            String[][] table2 = ExcelParser.getParser(type).load(file.getInputStream()).parse("Sheet1").getTable();
            ExcelCreator creator = ExcelCreator.create();

            creator.addSheet("sheet1", table1);
            // 导出到文件
            creator.exportToFile("./", "excel");

            // 导出OutputStream
            OutputStream os = new ByteArrayOutputStream();
            creator.getWorkbook().write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "download")
    public void download(HttpServletResponse response) {
        String[][] array = new String[4][4];
        array[0][0] = "A";
        array[0][2] = "B";
        array[2][0] = "C";
        array[2][2] = "D";
        TableRange[] ranges = new TableRange[4];
        ranges[0] = new TableRange(0, 1, 0, 1);
        ranges[1] = new TableRange(2, 3, 0, 1);
        ranges[2] = new TableRange(0, 1, 2, 3);
        ranges[3] = new TableRange(2, 3, 2, 3);
        try {
            ExcelCreator.create()
                    .addSheet("sheet1", array)
                    .mergedCell(0, ranges)
                    .exportToFontEnd(response, "excel");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
