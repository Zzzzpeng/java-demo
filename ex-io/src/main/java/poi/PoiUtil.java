package poi;

import annotation.CellInfo;
import bean.TargetBean;
import com.alibaba.excel.util.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PoiUtil {
    static List<CellStyle> contentCellstyles;
    static Field[] targetBeanFields = TargetBean.class.getDeclaredFields();

    public static void poiWrite(List<TargetBean> targetBeanList, String fileName) throws IOException {
        SXSSFWorkbook wb = new SXSSFWorkbook();
        Sheet sheet = wb.createSheet("审批明细模板");//创建Excel工作表对象
        //创建样式列表
        createCellStyleList(wb);
        //根据样式列表创建表头行
        createHeader(sheet);
        //第一行是表头

        for (int i = 0; targetBeanList != null && i < targetBeanList.size(); i++) {
            Row row = sheet.createRow(i + 1); //创建Excel工作表的行
            writeObj(wb, row, targetBeanList.get(i));
        }
        wb.write(new FileOutputStream(fileName));
    }

    static void writeObj(SXSSFWorkbook wb, Row row, TargetBean targetBean) {
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

    static void setCellStyleAndValue(Cell cell, int styleIndex) {
        cell.setCellStyle(getContentCellStyles().get(styleIndex)); //创建Excel工作表指定行的单元格
    }

    static List<CellStyle> getContentCellStyles() {
        return contentCellstyles;
    }


    static CellStyle getDefaultStyle(Workbook wb) {
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

    static List<CellStyle> createCellStyleList(Workbook wb) {
        List<CellStyle> cellStyles = new ArrayList<>();
        Class<TargetBean> targetBeanClass = TargetBean.class;
        Field[] declaredFields = targetBeanClass.getDeclaredFields();
        CellStyle defaultStyle = getDefaultStyle(wb);
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
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
                int width = annotation.width();
                if (width > 0) {
                    wb.getSheetAt(0).setColumnWidth(i,width);
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

    static void createHeader(Sheet sheet) {
        String[] headArr = ("票号,票面金额,出票日期,票据到期日,承兑人开户行名称,承兑人账号,承兑人开户行名称,承兑人开户行行号," +
                "贴现行名称,贴现行行号,保证增信行名称（非必输项）,保证增信行行号（非必输项）,客户经理,前手背书人(资管计划购买需要录入)," +
                "是否有风险缓释物(资管计划购买需要录入),是否同城（再贴现录入）,是否符合政策标准（再贴现录入）" +
                ",任务类型(0-交易、1-竞价、2-撮合)").split(",");
        Row row = sheet.createRow(0);
        List<CellStyle> headStyles = getContentCellStyles();
        if (headStyles.size() != headArr.length)
            throw new RuntimeException("xxxxxxxxxxx!");
        for (int i = 0; i < headStyles.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(headArr[i]);
//            cell.setCellStyle(headStyles.get(i));
        }
    }

    public static void main(String[] args) throws IOException, IllegalAccessException {
        String from = EasyExcelUtils.TestFileUtil.getPath() + "source" + ".xlsx";
        String to = EasyExcelUtils.TestFileUtil.getPath() + "target_poi" + ".xls";
        List<TargetBean> targetBeans = EasyExcelUtils.simpleReadSourceBean(from);
        poiWrite(targetBeans,to);
    }
}
