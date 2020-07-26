package com.datanew.util;

import javassist.NotFoundException;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author inRush
 * @date 2018/12/17
 **/
public class ExcelParser {

    /**
     * ExcelParser 类型
     */
    public enum Type {
        /**
         * xlsx类型
         */
        XLSX("xlsx"),
        /**
         * xls类型
         */
        XLS("xls");
        private String suffix;

        Type(String suffix) {
            this.suffix = suffix;
        }

        public static Type getType(String suffix) {
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

    private static final char[] LETTERS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private Workbook workbook;
    private String[][] excelTable;
    private String actitySheet;
    private Type type;


    public static ExcelParser getParser(Type type) {
        return new ExcelParser(type);
    }


    private ExcelParser(Type type) {
        this.type = type;
    }

    public ExcelParser load(InputStream inputStream) throws IOException {
        if (type.suffix.equals(Type.XLSX.suffix)) {
            workbook = new XSSFWorkbook(inputStream);
        } else {
            workbook = new HSSFWorkbook(inputStream);
        }
        return this;
    }

    public ExcelParser load(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        return load(inputStream);
    }

    public String[][] getTable() {
        return excelTable;
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

    /**
     * 获取表格边界（粗略，不精确）
     *
     * @param sheetName 表格名称
     * @return boundarys
     */
    public int[] parseBoundary(String sheetName) {
        int[] boundarys = new int[4];
        Sheet sheet = workbook.getSheet(sheetName);

        int startCol = 0;
        int endCol = getMaxColumn(sheet);
        boundarys[0] = 0;
        boundarys[1] = sheet.getLastRowNum();
        boundarys[2] = startCol;
        boundarys[3] = endCol;
        return boundarys;
    }


    private static String getValueByCellStyle(Cell cell, int rowCellType) {
        if (cell == null) {
            return "";
        }
        switch (rowCellType) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue().trim();
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    String dataFormat = cell.getCellStyle().getDataFormatString();
                    if (StringUtil.isblank(dataFormat)) {
                        dataFormat = "yyyy-MM-dd HH:mm:ss";
                    }
                    if (dataFormat.contains("hh")) {
                        dataFormat = "yyyy-MM-dd HH:mm:ss";
                    } else {
                        dataFormat = "yyyy-MM-dd";
                    }

                    return new SimpleDateFormat(dataFormat).format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(cell.getNumericCellValue()));
                } else {
                    DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
                    return decimalFormat.format(cell.getNumericCellValue());
                }
            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_BLANK:
                return "";
            case Cell.CELL_TYPE_FORMULA:
//                if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cell.getDateCellValue());
//                }
                return getValueByCellStyle(cell, cell.getCachedFormulaResultType());
            case Cell.CELL_TYPE_ERROR:
                return ErrorEval.getText(cell.getErrorCellValue());
        }
        return null;
    }

    public ExcelParser parse(int index) throws IOException {
        Sheet sheet = workbook.getSheetAt(index);
        return this.parse(sheet.getSheetName());
    }

    public ExcelParser parse(String sheetName)
            throws IOException {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            // 对应的数据表不存在
            throw new IOException(sheetName + " is not found");
        }
        actitySheet = sheetName;
        int rowSize = sheet.getLastRowNum();
        int rowIndex = 0;
        excelTable = new String[rowSize + 1][];
        int maxCol = getMaxColumn(sheet);
        while (rowIndex <= rowSize) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                // 空行
                excelTable[rowIndex] = new String[0];
                rowIndex++;
                continue;
            }
            short cellSize = row.getLastCellNum();
            int cellIndex = 0;
            excelTable[rowIndex] = new String[maxCol];
            while (cellIndex < maxCol) {
                // 每一行填充为等长，防止后面取范围产生数组越界异常
                if (cellIndex >= cellSize) {
                    excelTable[rowIndex][cellIndex] = "";
                    cellIndex++;
                    continue;
                }
                Cell cell = row.getCell(cellIndex);
                excelTable[rowIndex][cellIndex] = getValueByCellStyle(cell, cell.getCellType());
                cellIndex++;
            }
            rowIndex++;
        }
        return this;
    }

    public static ExcelParser parse(String path, String sheetName) throws IOException, NotFoundException, IllegalArgumentException {
        String suffix = path.substring(path.lastIndexOf('.') + 1);
        Type type = Type.getType(suffix);
        if (type == null) {
            throw new IllegalArgumentException("this type is not support: " + suffix);
        }
        ExcelParser excel = getParser(type);
        InputStream inputStream = new FileInputStream(path);
        return excel.load(inputStream).parse(sheetName);
    }


    /**
     * 过滤，取i-j行和n-m列,传入的参数从0开始
     *
     * @return filterTable
     */
    public ExcelParser filter(int i, int j, int n, int m)
            throws ArrayIndexOutOfBoundsException {
        if (i > j || n > m || i > excelTable.length) {
            throw new ArrayIndexOutOfBoundsException(actitySheet);
        }
        String[][] filterTable = new String[j - i + 1][m - n + 1];
        int index = 0;
        for (int k = i; k <= j; k++) {
            System.arraycopy(excelTable[k], n, filterTable[index++], 0, m - n + 1);
        }
        excelTable = filterTable;
        return this;
    }

    /**
     * 去除空行
     *
     * @return
     */
    public ExcelParser trim() {
        List<String[]> trimTable = new ArrayList();
        for (String[] row : excelTable) {
            boolean hasContent = false;
            for (String col : row) {
                if (!StringUtil.isblank(col)) {
                    hasContent = true;
                    break;
                }
            }
            if (hasContent) {
                trimTable.add(row);
            }
        }
        String[][] newTable = new String[trimTable.size()][];
        excelTable = trimTable.toArray(newTable);
        return this;
    }

    public static int convertColumnToInteger(String column) {
        char[] letters = column.toCharArray();
        int total = 0;
        int index;
        for (int i = letters.length - 1; i >= 0; i--) {
            index = getLetterIndex(letters[i]);
            if (i != letters.length - 1) {
                index += 1;
            }
            total += index * Math.pow(LETTERS.length, letters.length - i - 1);
        }
        return total;
    }

    public static String convertColumnToLetter(int column) {
        int length = LETTERS.length;
        StringBuilder sb = new StringBuilder();
        while (column != 0) {
            int index = column % length;
            column /= length;
            if (column == 0) {
                index--;
            }
            sb.append(LETTERS[index]);
        }
        return sb.reverse().toString();
    }

    private static int getLetterIndex(char letter) {
        for (int i = 0; i < LETTERS.length; i++) {
            if (LETTERS[i] == letter) {
                return i;
            }
        }
        return -1;
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
}
