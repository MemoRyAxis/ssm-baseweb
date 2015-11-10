package com.memory.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * utils4xls&xlsx
 *
 * @author memoryaxis@gmail.com
 * @date Jul 7, 2015
 */
public class ExcelUtil {

    public static final String EXCEL2003_DOC_SUBHEADER = "D0CF11E0A1B11AE1";

    public static final String EXCEL2007_DOC_SUBHEADER = "504B030414000600";

    /**
     * generate Excel by template file
     */
    public static void generateExcelFileByTemplate(String template, OutputStream os, String[][] data, int startRow, int startColumn) {

        InputStream in = ExcelUtil.class.getResourceAsStream(template);

        try {
            Workbook wb = new HSSFWorkbook(in);
            Sheet sheet = wb.getSheetAt(0);
            for (int i = startRow, rowCursor = 0; i < data.length; i++, rowCursor++) {
                Row row = sheet.createRow(i);
                for (int j = startColumn, colCursor = 0; j < data[i].length; j++, colCursor++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(data[rowCursor][colCursor]);
                }
            }
            wb.write(os);
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * read excel file to string array
     */
    @SuppressWarnings("resource")
    public static String[][] getExcelData(MultipartFile file, int rowStart, int columnNumber) throws IOException {
        String[][] data = null;
        Workbook wb = null;
        try {
            wb = new HSSFWorkbook(file.getInputStream());
        } catch (Exception e) {
            wb = new XSSFWorkbook(file.getInputStream());
        }
        int sheetNum = wb.getNumberOfSheets();
        for (int i = 0; i < sheetNum; i++) {
            Sheet sheet = wb.getSheetAt(i);
            int rowNum = sheet.getPhysicalNumberOfRows();
            for (int j = rowStart, rowCursor = 0; j < rowNum; j++, rowCursor++) {
                Row row = sheet.getRow(j);
                if (j == rowStart) {
                    data = new String[rowNum][columnNumber];
                }
                for (int k = 0; k < columnNumber; k++) {
                    if (null == row) {
                        data[rowCursor][k] = "";
                    } else {
                        Cell cell = row.getCell(k);
                        data[rowCursor][k] = parseExcel(cell);
                    }
                }
            }
        }
        return data;
    }

    private static DecimalFormat DECIMALFORMAT = new DecimalFormat("#.##");

    public static String parseExcel(Cell cell) {
        if (cell == null) {
            return "";
        }
        String result = new String();
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = null;
                    sdf = new SimpleDateFormat("yyyy/MM/dd");
                    Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                } else {
                    double va = cell.getNumericCellValue();
                    if (va == (int) va) {
                        result = String.valueOf((int) va);
                    } else {
                        result = DECIMALFORMAT.format(va);
                    }
                }
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                try {
                    result = String.valueOf(cell.getNumericCellValue());
                } catch (IllegalStateException e) {
                    result = String.valueOf(cell.getRichStringCellValue());
                }
                break;
            case HSSFCell.CELL_TYPE_STRING:
                result = cell.getRichStringCellValue().toString();
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                result = "";
            default:
                result = "";
                break;
        }

        return result;
    }

    /**
     * verify file is Excel file or not
     */
    public static boolean verifyExcelFile(MultipartFile file) throws IOException {
        boolean isExcelFile = false;
        byte[] b = new byte[28];
        InputStream inputStream = file.getInputStream();
        inputStream.read(b, 0, 28);
        if (inputStream != null) {
            inputStream.close();
        }
        // xls
        if (bytesToHexString(b).substring(0, 16).toUpperCase().equals(EXCEL2003_DOC_SUBHEADER)) {
            isExcelFile = true;
        }
        // xlsx
        if (bytesToHexString(b).substring(0, 16).toUpperCase().equals(EXCEL2007_DOC_SUBHEADER)) {
            isExcelFile = true;
        }
        return isExcelFile;
    }

    /**
     * get Excel type
     */
    public static String getExcelSuffix(MultipartFile file) {
        String suffix = "";
        Workbook wb = null;
        try {
            wb = new HSSFWorkbook(file.getInputStream());
            suffix = ".xls";
        } catch (Exception e) {
            try {
                wb = new XSSFWorkbook(file.getInputStream());
            } catch (IOException e1) {
                e.printStackTrace();
            }
            suffix = ".xlsx";
        }
        return suffix;
    }

    /**
     * read file's subheader
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

}
