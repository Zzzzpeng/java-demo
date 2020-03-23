package poi;

import annotation.CellInfo;
import bean.TargetBean;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.util.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

//支持多sheet共用同一样式(即不同sheet展示同一对象列表),暂不支持不同sheet展示不同样式
public class PoiUtilGraph<T> {
    List<CellStyle> contentCellstyles;
    Field[] targetBeanFields;
    Class type;
    SXSSFWorkbook wb = new SXSSFWorkbook();
    String fileName;

    public PoiUtilGraph(Class type, String fileName) {
        this.type = type;
        this.fileName = fileName;
        targetBeanFields = type.getDeclaredFields();

    }

    public void poiWrite(List<T> targetBeanList, String sheetName) throws IOException {
        Sheet sheet = wb.createSheet(sheetName);//创建Excel工作表对象

        //创建样式列表
        createCellStyleList(wb);
        //根据样式列表创建表头行
        createHeader(sheet);
        //第一行是表头

        for (int i = 0; targetBeanList != null && i < targetBeanList.size(); i++) {
            Row row = sheet.createRow(i + 1); //创建Excel工作表的行
            writeObj(wb, row, targetBeanList.get(i));
        }
    }

    public void close() throws Exception {
        wb.write(new FileOutputStream(fileName));
    }

    void writeObj(SXSSFWorkbook wb, Row row, T targetBean) {
        for (int i = 0; i < targetBeanFields.length; i++) {
            Cell cell = row.createCell(i);
            setCellStyleAndValue(cell, i);
            Field targetBeanField = targetBeanFields[i];
            //设置为可访问
            targetBeanField.setAccessible(true);
            try {
                Object value = targetBeanField.get(targetBean);
                if (value instanceof String)
                    cell.setCellValue((String) value);
                else if (value instanceof Double)
                    cell.setCellValue((double) value);
                else if (value instanceof Integer)
                    cell.setCellValue((int) value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    }

    void setCellStyleAndValue(Cell cell, int styleIndex) {
        cell.setCellStyle(getContentCellStyles().get(styleIndex)); //创建Excel工作表指定行的单元格
    }

    List<CellStyle> getContentCellStyles() {
        return contentCellstyles;
    }


    CellStyle getDefaultStyle(Workbook wb) {
        CellStyle defaultStyle = wb.createCellStyle();//创建单元格样式
        defaultStyle.setBorderBottom(BorderStyle.THIN);
        defaultStyle.setBorderTop(BorderStyle.THIN);
        defaultStyle.setBorderLeft(BorderStyle.THIN);
        defaultStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        defaultStyle.setAlignment(HorizontalAlignment.CENTER);
        defaultStyle.setFont(font);
        return defaultStyle;
    }

    List<CellStyle> createCellStyleList(Workbook wb) {
        if (contentCellstyles != null && !contentCellstyles.isEmpty()) {
            return contentCellstyles;
        }
        List<CellStyle> cellStyles = new ArrayList<>();
        CellStyle defaultStyle = getDefaultStyle(wb);
        for (int i = 0; i < targetBeanFields.length; i++) {
            Field declaredField = targetBeanFields[i];
            if (declaredField.isAnnotationPresent(CellInfo.class)) {
                CellInfo annotation = declaredField.getAnnotation(CellInfo.class);
                //创建默认单元格样式
                CellStyle cellStyle = getDefaultStyle(wb);
                String format = annotation.format();
                //如果不为空则创建
                if (!StringUtils.isEmpty(format)) {
                    DataFormat dataFormat = wb.createDataFormat();
                    cellStyle.setDataFormat(dataFormat.getFormat(format));//保留1位小数点
                }
                cellStyles.add(cellStyle);
                continue;
            }
            //走到这里说明只需要空样式
            cellStyles.add(defaultStyle);
        }
        contentCellstyles = cellStyles;
        return cellStyles;
    }

    void createHeader(Sheet sheet) {
        Field[] declaredField = type.getDeclaredFields();
        Row row = sheet.createRow(0);
        List<CellStyle> headStyles = getContentCellStyles();
        if (headStyles.size() != declaredField.length)
            throw new RuntimeException("列数与字段数不同!!!");
        for (int i = 0; i < headStyles.size(); i++) {
            Cell cell = row.createCell(i);
            String lieName;
            //从@ExcelProperty注解中获取列名,获取不到则去变量名
            if (declaredField[i].isAnnotationPresent(ExcelProperty.class)) {
                lieName = declaredField[i].getAnnotation(ExcelProperty.class).value()[0];
            } else {
                lieName = declaredField[i].getName();
            }
            if (declaredField[i].isAnnotationPresent(CellInfo.class)) {
                CellInfo annotation = declaredField[i].getAnnotation(CellInfo.class);
                int width = annotation.width();
                if (width > 0) {
                    sheet.setColumnWidth(i, width);
                }
            }
            cell.setCellValue(lieName);
            cell.setCellStyle(headStyles.get(i));
        }
    }

    public static void main(String[] args) throws IOException, IllegalAccessException {
        String from = EasyExcelUtils.TestFileUtil.getPath() + "source" + ".xlsx";
        String to = EasyExcelUtils.TestFileUtil.getPath() + "target_poi" + ".xls";
        List<TargetBean> targetBeans = EasyExcelUtils.simpleReadSourceBean(from);
//        new PoiUtilGraph<>(TargetBean.class).poiWrite("", to);
    }
}
