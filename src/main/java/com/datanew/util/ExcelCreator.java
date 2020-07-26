package com.datanew.util;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellAlignment;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

/**
 * @author inRush
 * @date 2019/3/20
 **/
public class ExcelCreator {
    public enum Type {
        /**
         * xlsx类型
         */
        XLSX(".xlsx"),
        /**
         * xls类型
         */
        XLS(".xls");
        private String suffix;

        Type(String suffix) {
            this.suffix = suffix;
        }

        public static ExcelCreator.Type getType(String suffix) {
            if (XLSX.suffix.equals(suffix)) {
                return XLSX;
            }
            if (XLS.suffix.equals(suffix)) {
                return XLS;
            }
            return null;
        }

        @Override
        public String toString() {
            return this.suffix;
        }
    }

    private ExcelCreator.Type type;
    private XSSFWorkbook workbook;

    private ExcelCreator(ExcelCreator.Type type) {
        this.type = type;
        this.workbook = new XSSFWorkbook();
    }

    public static ExcelCreator create() {
        return new ExcelCreator(Type.XLSX);
    }

    /**
     * 解析数据表名称
     *
     * @return names
     */
    public String[] parseNames() {
        String[] names = new String[workbook.getNumberOfSheets()];
        for (int i = 0; i < names.length; i++) {
            names[i] = workbook.getSheetName(i);
        }
        return names;
    }

    public TableRange parseBoundary(String sheetName) {
        TableRange range = new TableRange();
        Sheet sheet = workbook.getSheet(sheetName);

        int startCol = 0;
        int endCol = getMaxColumn(sheet);
        range.firstRow = 0;
        range.lastRow = sheet.getLastRowNum();
        range.firstCol = startCol;
        range.lastCol = endCol - 1;
        return range;
    }

    private int getMaxColumn(Sheet sheet) {
        int lastRowNum = sheet.getLastRowNum();
        if (lastRowNum != 0) {
            int maxCol = 0;
            // 获取最长的一行是多长
            for (int i = 0; i < lastRowNum; i++) {
                if (sheet.getRow(i) == null) {
                    continue;
                }
                if (sheet.getRow(i).getLastCellNum() > maxCol) {
                    maxCol = sheet.getRow(i).getLastCellNum();
                }
            }
            return maxCol;
        } else {
            return 0;
        }
    }

    private TableRange[] duplicateRang(TableRange source, int count) {
        TableRange[] ranges = new TableRange[count];
        for (int i = 0; i < ranges.length; i++) {
            ranges[i] = source;
        }
        return ranges;
    }

    private void putDataToCell(Cell cell, Object data) {
        if (data instanceof Date) {
            cell.setCellValue((Date) data);
        } else if (data instanceof String) {
            cell.setCellValue((String) data);
        } else if (data instanceof Double) {
            cell.setCellValue((Double) data);
        } else if (data instanceof RichTextString) {
            cell.setCellValue((RichTextString) data);
        } else if (data instanceof Boolean) {
            cell.setCellValue((Boolean) data);
        } else if (data instanceof Calendar) {
            cell.setCellValue((Calendar) data);
        } else if (data == null) {
            cell.setCellValue("");
        } else {
            cell.setCellErrorValue(new Byte("type not support"));
        }
    }

    public ExcelCreator addSheet(String sheetName, Object[][] table) {
        XSSFSheet sheet = workbook.createSheet(sheetName);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        for (int i = 0; i < table.length; i++) {
            Object[] rows = table[i];
            XSSFRow row = sheet.createRow(i);
            for (int j = 0; j < rows.length; j++) {
                XSSFCell cell = row.createCell(j);
                putDataToCell(cell, rows[j]);
                cell.setCellStyle(style);
            }
        }
        return this;
    }

    public ExcelCreator setAlignment(Sheet sheet, TableRange[] ranges, short align) {
        for (TableRange range : ranges) {
            for (int i = range.firstRow; i <= range.lastRow; i++) {
                Row row = sheet.getRow(i);
                for (int j = range.firstCol; j <= range.lastCol; j++) {
                    Cell cell = row.getCell(j);
                    CellStyle cs = workbook.createCellStyle();
                    cs.cloneStyleFrom(cell.getCellStyle());
                    cs.setAlignment(align);
                }
            }
        }
        return this;
    }

    public ExcelCreator setAlignment(String sheetName, TableRange[] ranges, short align) {
        Sheet sheet = workbook.getSheet(sheetName);
        return setAlignment(sheet, ranges, align);
    }

    public ExcelCreator setVerticalAlignment(Sheet sheet, TableRange[] ranges, short align) {
        for (TableRange range : ranges) {
            for (int i = range.firstRow; i <= range.lastRow; i++) {
                Row row = sheet.getRow(i);
                for (int j = range.firstCol; j <= range.lastCol; j++) {
                    Cell cell = row.getCell(j);
                    CellStyle cs = workbook.createCellStyle();
                    cs.cloneStyleFrom(cell.getCellStyle());
                    cs.setVerticalAlignment(align);
                }
            }
        }
        return this;
    }

    public ExcelCreator setColumnStyle(String sheetName, int index, short format) {
        Sheet sheet = workbook.getSheet(sheetName);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(format);
        sheet.setDefaultColumnStyle(index, style);
        return this;
    }

    public ExcelCreator setVerticalAlignment(String sheetName, TableRange[] ranges, short align) {
        Sheet sheet = workbook.getSheet(sheetName);
        return setVerticalAlignment(sheet, ranges, align);
    }


    /**
     * 合并单元格
     *
     * @param sheetNames 需要合并的的数据表
     * @return {@link ExcelCreator}
     */
    public ExcelCreator mergedCell(String[] sheetNames, TableRange[] ranges) {
        for (int i = 0; i < sheetNames.length; i++) {
            String sheetName = sheetNames[i];
            TableRange range = ranges[i];
            Sheet sheet = workbook.getSheet(sheetName);
            CellRangeAddress region = new CellRangeAddress(range.firstRow, range.lastRow, range.firstCol, range.lastCol);
            sheet.addMergedRegion(region);
        }
        return this;
    }

    public ExcelCreator mergedCell(String sheetName, TableRange[] ranges) {
        for (TableRange range : ranges) {
            Sheet sheet = workbook.getSheet(sheetName);
            CellRangeAddress region = new CellRangeAddress(range.firstRow, range.lastRow, range.firstCol, range.lastCol);
            sheet.addMergedRegion(region);
        }
        return this;
    }

    public ExcelCreator mergedCell(int sheetIndex, TableRange[] ranges) {
        for (TableRange range : ranges) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            CellRangeAddress region = new CellRangeAddress(range.firstRow, range.lastRow, range.firstCol, range.lastCol);
            sheet.addMergedRegion(region);
        }
        return this;
    }

    public ExcelCreator mergedCell(String[] sheetNames, TableRange range) {
        TableRange[] ranges = duplicateRang(range, sheetNames.length);
        return mergedCell(sheetNames, ranges);
    }

    public ExcelCreator mergedCell(TableRange[] ranges) {
        return mergedCell(parseNames(), ranges);
    }

    public ExcelCreator mergedCell(TableRange rang) {
        String[] sheetNames = parseNames();
        TableRange[] ranges = duplicateRang(rang, sheetNames.length);
        return mergedCell(sheetNames, ranges);
    }

    /**
     * 加粗对应单元格的字体
     *
     * @param sheetNames 需要加粗的数据表
     * @return {@link ExcelCreator}
     */
    public ExcelCreator bold(String[] sheetNames, TableRange[] rangs) {
        for (int k = 0; k < sheetNames.length; k++) {
            String sheetName = sheetNames[k];
            TableRange range = rangs[k];
            Sheet sheet = workbook.getSheet(sheetName);
            for (int i = range.firstRow; i <= range.lastRow; i++) {
                for (int j = range.firstCol; j <= range.lastCol; j++) {
                    Cell cell = sheet.getRow(i).getCell(j);
                    XSSFFont font = workbook.createFont();
                    font.setBold(true);
                    XSSFCellStyle style = workbook.createCellStyle();
                    style.cloneStyleFrom(cell.getCellStyle());
                    style.setFont(font);
                    cell.setCellStyle(style);
                }
            }
        }
        return this;
    }

    public ExcelCreator bold(String[] sheetNames, TableRange range) {
        TableRange[] ranges = duplicateRang(range, sheetNames.length);
        return bold(sheetNames, ranges);
    }


    public ExcelCreator bold(TableRange[] rangs) {
        return bold(parseNames(), rangs);
    }

    public ExcelCreator bold(TableRange rang) {
        String[] sheetNames = parseNames();
        TableRange[] ranges = duplicateRang(rang, sheetNames.length);
        return bold(sheetNames, ranges);
    }

    public static class BorderStyle {
        private short lineStyle;
        private short lineColor;

        public static BorderStyle defaultStyle() {
            return new BorderStyle(XSSFCellStyle.BORDER_THIN, HSSFColor.BLACK.index);
        }

        public BorderStyle(short lineStyle, short lineColor) {
            this.lineStyle = lineStyle;
            this.lineColor = lineColor;
        }

        public short getLineStyle() {
            return lineStyle;
        }

        public void setLineStyle(short lineStyle) {
            this.lineStyle = lineStyle;
        }

        public short getLineColor() {
            return lineColor;
        }

        public void setLineColor(short lineColor) {
            this.lineColor = lineColor;
        }
    }


    private void setBorderStyle(boolean top, boolean right, boolean bottom, boolean left, Cell cell, BorderStyle style) {
        XSSFCellStyle newStyle = workbook.createCellStyle();
        newStyle.cloneStyleFrom(cell.getCellStyle());
        if (top) {
            newStyle.setBorderTop(style.lineStyle);
            newStyle.setTopBorderColor(style.lineColor);
        }
        if (right) {
            newStyle.setBorderRight(style.lineStyle);
            newStyle.setRightBorderColor(style.lineColor);
        }
        if (bottom) {
            newStyle.setBorderBottom(style.lineStyle);
            newStyle.setBottomBorderColor(style.lineColor);
        }
        if (left) {
            newStyle.setBorderLeft(style.lineStyle);
            newStyle.setLeftBorderColor(style.lineColor);
        }
        cell.setCellStyle(newStyle);
    }

    public ExcelCreator border(String[] sheetNames, TableRange[] ranges,
                               BorderStyle borderStyle, boolean... position) {
        if (position.length <= 0 || position.length > 5) {
            throw new IllegalArgumentException("position must be less than or equal to 4 and greater than 0");
        }
        for (int k = 0; k < sheetNames.length; k++) {
            String sheetName = sheetNames[k];
            TableRange range = ranges[k];
            boolean top, right, left, bottom, allBorder = false;
            if (position.length == 1) {
                top = right = left = bottom = position[0];
            } else if (position.length == 2) {
                top = bottom = position[0];
                right = left = position[1];
            } else if (position.length == 3) {
                top = position[0];
                right = left = position[1];
                bottom = position[2];
            } else {
                top = position[0];
                right = position[1];
                bottom = position[2];
                left = position[3];
                if (position.length == 5) {
                    allBorder = position[4];
                }
            }
            Sheet sheet = workbook.getSheet(sheetName);
            for (int i = range.firstRow; i <= range.lastRow; i++) {
                for (int j = range.firstCol; j <= range.lastCol; j++) {
                    Cell cell = sheet.getRow(i).getCell(j);
                    if (allBorder) {
                        setBorderStyle(top, right, bottom, left, cell, borderStyle);
                    } else {
                        if (top && i == range.firstRow) {
                            setBorderStyle(true, false, false, false, cell, borderStyle);
                        }
                        if (right && j == range.lastCol) {
                            setBorderStyle(false, true, false, false, cell, borderStyle);
                        }
                        if (bottom && i == range.lastRow) {
                            setBorderStyle(false, false, true, false, cell, borderStyle);
                        }
                        if (left && j == range.firstCol) {
                            setBorderStyle(false, false, false, true, cell, borderStyle);
                        }
                    }

                }
            }
        }
        return this;
    }

    public ExcelCreator border(String[] sheetNames, TableRange range, BorderStyle borderStyle, boolean... position) {
        TableRange[] ranges = duplicateRang(range, sheetNames.length);
        return border(sheetNames, ranges, borderStyle, position);
    }

    public ExcelCreator border(TableRange[] rangs, BorderStyle borderStyle, boolean... position) {
        return border(parseNames(), rangs, borderStyle, position);
    }

    public ExcelCreator border(String[] sheetNames, BorderStyle borderStyle, boolean... position) {
        TableRange[] rangs = new TableRange[sheetNames.length];
        for (int i = 0; i < sheetNames.length; i++) {
            String sheetName = sheetNames[i];
            TableRange range = parseBoundary(sheetName);
            rangs[i] = range;
        }
        return border(sheetNames, rangs, borderStyle, position);
    }

    public ExcelCreator border(BorderStyle borderStyle, boolean... position) {
        return border(parseNames(), borderStyle, position);
    }

    /**
     * 对sheetNames中每个指定数据表进行自动宽度，每个表的指定列都是columns
     *
     * @param sheetNames 需要设置的数据表
     * @param columns    需要设置自动宽度的列
     * @return {@link ExcelCreator}
     */
    public ExcelCreator autoWidth(String[] sheetNames, int[] columns) {
        for (String sheetName : sheetNames) {
            Sheet sheet = workbook.getSheet(sheetName);
            for (int column : columns) {
                sheet.autoSizeColumn(column);
                sheet.setColumnWidth(column, sheet.getColumnWidth(column) * 15 / 10);
            }
        }

        return this;
    }

    /**
     * 配置指定数据表指定列进行自动宽度
     *
     * @param sheetName
     * @param columns
     * @return
     */
    public ExcelCreator autoWidth(String sheetName, int[] columns) {
        Sheet sheet = workbook.getSheet(sheetName);
        for (int column : columns) {
            sheet.autoSizeColumn(column);
            sheet.setColumnWidth(column, sheet.getColumnWidth(column) * 15 / 10);
        }
        return this;
    }

    /**
     * 每个数据表都会设置一遍columns定义的列进行自动宽度
     *
     * @param columns
     * @return
     */
    public ExcelCreator autoWidth(int[] columns) {
        return autoWidth(parseNames(), columns);
    }

    public ExcelCreator setWidth(String sheetName, String column, int width) {
        int colIndex = ExcelParser.convertColumnToInteger(column);
        return setWidth(sheetName, colIndex, width);
    }

    public ExcelCreator setWidth(String sheetName, int column, int width) {
        Sheet sheet = workbook.getSheet(sheetName);
        sheet.setColumnWidth(column, width);
        return this;
    }

    /**
     * 默认设置第一个数据表宽度
     *
     * @param column 需要设置的列
     * @param width  宽度
     * @return
     */
    public ExcelCreator setWidth(String column, int width) {
        int colIndex = ExcelParser.convertColumnToInteger(column);
        workbook.getSheetAt(0).setColumnWidth(colIndex, width);
        return this;
    }

    public ExcelCreator setHeight(int row, short height) {
        workbook.getSheetAt(0).getRow(row).setHeight(height);
        return this;
    }

    public ExcelCreator setHeight(String sheetName, int row, short height) {
        workbook.getSheet(sheetName).getRow(row).setHeight(height);
        return this;
    }

    public ExcelCreator setHeight(int sheetIndex, int row, short height) {
        workbook.getSheetAt(sheetIndex).getRow(row).setHeight(height);
        return this;
    }


    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    /**
     * 导出excel到文件中
     *
     * @param path     保存路径
     * @param fileName 需要导出的文件名称，不包括后缀
     * @throws IOException 文件创建异常
     */
    public void exportToFile(String path, String fileName) throws IOException {
        String target = path.concat(fileName).concat(type.suffix);
        File file = new File(target);
        if (!file.exists()) {
            boolean newFile = file.createNewFile();
            if (!newFile) {
                throw new IOException("create file " + fileName.concat(type.suffix) + " unsuccessful");
            }
        }
        FileOutputStream op = new FileOutputStream(file);
        workbook.write(op);
    }

    public void exportToFontEnd(HttpServletResponse response, String fileName) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8").concat(".xlsx") + "\"");
//        response.addHeader("Content-Length", String.valueOf(byteArrayOutputStream.size()));
        response.setContentType("application/octet-stream;charset=UTF-8");
        getWorkbook().write(response.getOutputStream());
    }

    public static class TableRange {
        private Integer firstRow;
        private Integer lastRow;
        private Integer firstCol;
        private Integer lastCol;

        public TableRange(Integer firstRow, Integer lastRow, Integer firstCol, Integer lastCol) {
            this.firstRow = firstRow;
            this.lastRow = lastRow;
            this.firstCol = firstCol;
            this.lastCol = lastCol;
        }

        public TableRange() {
        }

        public Integer getFirstRow() {
            return firstRow;
        }

        public void setFirstRow(Integer firstRow) {
            this.firstRow = firstRow;
        }

        public Integer getLastRow() {
            return lastRow;
        }

        public void setLastRow(Integer lastRow) {
            this.lastRow = lastRow;
        }

        public Integer getFirstCol() {
            return firstCol;
        }

        public void setFirstCol(Integer firstCol) {
            this.firstCol = firstCol;
        }

        public Integer getLastCol() {
            return lastCol;
        }

        public void setLastCol(Integer lastCol) {
            this.lastCol = lastCol;
        }
    }
}
