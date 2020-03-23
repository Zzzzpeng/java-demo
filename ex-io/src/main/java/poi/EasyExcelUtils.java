package poi;

import bean.DemoData;
import bean.SourceBean;
import bean.TargetBean;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import listen.SourceBeanListener;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EasyExcelUtils {

    public static List<TargetBean> simpleReadSourceBean(String fileName ) {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        SourceBeanListener sourceBeanListener = new SourceBeanListener();
        System.out.println("开始读取" + fileName);
        EasyExcel.read(fileName, SourceBean.class, sourceBeanListener).sheet().doRead();
        return sourceBeanListener.getResList();
//        // 写法2：
//        fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
//        ExcelReader excelReader = EasyExcel.read(fileName, bean.DemoData.class, new DemoDataListener()).build();
//        ReadSheet readSheet = EasyExcel.readSheet(0).build();
//        excelReader.read(readSheet);
//        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
//        excelReader.finish();
    }


    //排除指定列
    public static void excludeOrIncludeWrite() {
        String fileName = TestFileUtil.getPath() + "excludeOrIncludeWrite" + System.currentTimeMillis() + ".xlsx";
        // 根据用户传入字段 假设我们要忽略 date
        Set<String> excludeColumnFiledNames = new HashSet<String>();
//        excludeColumnFiledNames.add("yourName");
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, DemoData.class).excludeColumnFiledNames(excludeColumnFiledNames).sheet("模板")
                .doWrite(data());

    }

    public static void simpleWrite() {
        // 写法1
        String fileName = TestFileUtil.getPath() + "simpleWrite"  + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());

//        // 写法2
//        fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
//        // 这里 需要指定写用哪个class去写
//        ExcelWriter excelWriter = EasyExcel.write(fileName, bean.DemoData.class).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
//        excelWriter.write(data(), writeSheet);
//        // 千万别忘记finish 会帮忙关闭流
//        excelWriter.finish()
    }

    public static void simpleWriteTarget(List<TargetBean> targetBeans) {
        // 写法1
        String fileName = TestFileUtil.getPath() + "target"  + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, TargetBean.class).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("审批明细模板").doWrite(targetBeans);

    }

    static Object getStyle() {
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为红色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)20);
        headWriteCellStyle.setWriteFont(headWriteFont);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);

//        contentWriteCellStyle.setDataFormat(DataFormat);
//        // 背景绿色
//        contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
//        WriteFont contentWriteFont = new WriteFont();
//        // 字体大小
//        contentWriteFont.setFontHeightInPoints((short)20);
//        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        return horizontalCellStyleStrategy;
    }

    public static class TestFileUtil {
        public static String getPath() {
            return "F:/temp/";
        }
    }
    static List<DemoData> data() {
        List<DemoData> dataList = new ArrayList<>();
        dataList.add(DemoData.builder().name("zep").yourName("yzep").age(6656.33).build());
        dataList.add(DemoData.builder().name("xixi").yourName("yzx").age(22223333.555).build());
        return dataList;
    }

//    @Data
//    @Builder
//    @NoArgsConstructor
//    static class DemoData {
//
//        @ExcelProperty(value = "姓名", index = 0)
//        String name;
//        @ExcelProperty(value = "价格", index = 2)
//        @NumberFormat("#,###.0000")
//        Double age;
//        @ExcelProperty(value = "小名", index = 1)
//        String yourName;
//    }

    static class Listener extends AnalysisEventListener<DemoData> {

        @Override
        public void invoke(DemoData data, AnalysisContext context) {
            System.out.println(data);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {

        }
    }
}
