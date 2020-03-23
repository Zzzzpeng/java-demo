package com.chen.excelbean;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSON;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtil {

    static List<Map<String, String>>[] readAndReturn(String path) {
        // 写法2：
        String fileName = path;
        ReadMapListner readMapListner = new ReadMapListner();
        ExcelReader excelReader = EasyExcel.read(fileName, readMapListner).build();
        ReadSheet readSheet0 = EasyExcel.readSheet(0).build();
        ReadSheet readSheet1 = EasyExcel.readSheet(1).build();
        excelReader.read(readSheet0, readSheet1);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
        return readMapListner.getMapList();
    }


    public static void writeWithoutModel(String path, Map<Integer, String> headMap, List<Map<String, String>>... dataList) throws Exception {
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/M/d");
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (CollectionUtils.isEmpty(headMap) || CollectionUtils.isEmpty(dataList[0])) {
            System.out.println("headMap或dataList为空");
            headMap = getDefaultHeadMap();
            ReadMapListner.headMap = headMap;
        }
        try (OutputStream out = new FileOutputStream(path)) {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, false);
            List<String> sheetNames = Worker.sheetNames;
            for (int j = 0; j < sheetNames.size() && j < dataList.length; j++) {
                List<List<String>> data = new ArrayList<>();
                List<String> headRow = new ArrayList<>();
                //组装表头list
                for (int i = 0; i < headMap.size(); i++) {
                    headRow.add(headMap.get(i));
                }
                data.add(headRow);
                //组装数据行
                for (int i = 0; i < dataList[j].size(); i++) {
                    Map<String, String> objMap = dataList[j].get(i);
                    List<String> dataRow = new ArrayList<>();
                    for (int k = 0; k < headRow.size(); k++) {
                        String attrName = headRow.get(k);
                        String value = objMap.get(attrName);
                        if (k != 0) {
                            if (StringUtils.isEmpty(value))
                                value = "";
                            value = value.length() >= 6 ? value.substring(0, 6) : value;
                        } else {
                            Date date = null;
                            try {
                                date = inputFormat.parse(value);
                            } catch (ParseException e) {
                                date = outputFormat.parse(value);
                            }
                            value = outputFormat.format(date);
                        }
                        dataRow.add(value);
                    }
                    data.add(dataRow);
                }
                Sheet sheet = new Sheet(j + 1, j);
                sheet.setSheetName(sheetNames.get(j));
                writer.write0(data, sheet);
            }
            writer.finish();
        }
    }

    public static Map<Integer, String> getDefaultHeadMap() {
        Map<String, String> map = JSON.parseObject("{\"11\":\"9M\",\"12\":\"10M\",\"13\":\"11M\",\"14\":\"1Y\",\"0\":\"日期/区间\",\"1\":\"1D\",\"2\":\"7D\",\"3\":\"1M\",\"4\":\"2M\",\"5\":\"3M\",\"6\":\"4M\",\"7\":\"5M\",\"8\":\"6M\",\"9\":\"7M\",\"10\":\"8M\"}", Map.class);
        Map<Integer, String> headmap = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            headmap.put(Integer.valueOf(entry.getKey()), entry.getValue());
        }
        System.out.println(JSON.toJSONString(headmap));
        return headmap;
    }

    public static void write(String path, List<TicketPropertiy>... data) throws Exception {
//        if (CollectionUtils.isEmpty(dataList[0])) {
//            System.out.println("dataList为空");
//        }
        for (List<TicketPropertiy> sheetData : data) {
            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
            // 背景设置为红色
            headWriteCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            WriteFont headWriteFont = new WriteFont();
            headWriteFont.setFontHeightInPoints((short) 12);
            headWriteCellStyle.setWriteFont(headWriteFont);
            // 内容的策略
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
            // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
            contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
            // 背景绿色
            contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            WriteFont contentWriteFont = new WriteFont();
            // 字体大小
            contentWriteFont.setFontHeightInPoints((short) 10);
            contentWriteCellStyle.setWriteFont(contentWriteFont);
            // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
            HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                    new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

            // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
            EasyExcel.write(path, TicketPropertiy.class).registerWriteHandler(horizontalCellStyleStrategy).sheet("模板")
                    .doWrite(sheetData);
        }

    }

}
