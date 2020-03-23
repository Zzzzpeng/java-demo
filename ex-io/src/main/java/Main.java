import bean.TargetBean;
import com.alibaba.excel.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.POIXMLProperties;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import poi.EasyExcelUtils;
import poi.PoiUtil;
import poi.PoiUtilGraph;

import java.awt.geom.Rectangle2D;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    private static final String UNIT_STRING_WAN = "万";
    private static final String UNIT_STRING_YI = "亿";
    public static void main(String[] args) throws Exception {
        run(args);
//        filePathTest();
//        testQues();
    }
    static void run(String[] args){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("../conf/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
//            throw new RuntimeException("配置文未发现");
        }
        Object sourceDir = properties.get("from");
        Object targetDir = properties.get("to");
        String from,to;
        if(StringUtils.isEmpty(sourceDir)){
            from = "../source";
            from = EasyExcelUtils.TestFileUtil.getPath() + "source" ;
        }else {
            from = sourceDir.toString();
        }
        if(StringUtils.isEmpty(targetDir)){
//            to = "../target";
            to = EasyExcelUtils.TestFileUtil.getPath() + "target" ;
        }else {
            to = targetDir.toString();
        }
        File file = new File(from);
        List<FilePath> filePathList = new ArrayList<>();
        if (file.isDirectory()) {
            String[] list = file.list();
            for (String fileName : list) {
                if(fileName.endsWith(".xlsx") || fileName.endsWith(".xls")){
                    String path = from + "/" + fileName;
                    String name = fileName.replaceAll("\\.xlsx|\\.xls","");
                    String targetPath = to + "/" + "卖断" + name +"%s" + ".xls" ;
                    FilePath filePath = new FilePath(path, targetPath, name);
                    filePathList.add(filePath);
                }
            }
        }

        for (FilePath filePath : filePathList) {
            List<TargetBean> targetBeans = EasyExcelUtils.simpleReadSourceBean(filePath.getFileName());
            if(targetBeans == null || targetBeans.size() ==0){
                System.out.println(filePath.getFileName()+"文件中没有读取到数据....");
                return;
            }
            //price求和
            BigDecimal sum = new BigDecimal("0");
            for (TargetBean targetBean : targetBeans) {
                Double price = targetBean.getPrice();
                sum = sum.add(new BigDecimal(price!=null?price.toString():""));
            }
            try {
                System.out.println("**********************");
                System.out.println(filePath.getTargetName());
                String finalTargetName = String.format(filePath.getTargetName(),formatNumber(sum));
//                PoiUtil.poiWrite();
                PoiUtilGraph<TargetBean> targetBeanPoiUtilGraph = new PoiUtilGraph<>(TargetBean.class, finalTargetName);
                targetBeanPoiUtilGraph.poiWrite(targetBeans,"模板1");
                targetBeanPoiUtilGraph.poiWrite(targetBeans,"模板2");
                targetBeanPoiUtilGraph.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FilePath{
        private String fileName; //源地址
        private String targetName; //目标地址
        private String name;  //文件名(不含拓展名)
    }
    static void filePathTest(){
        File file = new File("F:/桌面/pack");
        if (file.isDirectory()) {
            String[] list = file.list();
            for (String s : list) {
                System.out.println(s.replaceAll("\\.xlsx|\\.xls",""));
            }
        }
    }
    static void testQues(){
        Properties properties = new Properties();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("F:\\testjar\\conf\\config.properties"));
            properties.load(inputStreamReader);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("配置文未发现");
        }
        System.out.println(properties);
    }

    public static String formatNumber(BigDecimal amount) {
        if (amount == null) {
            return "0";
        }
        if (amount.compareTo(new BigDecimal(10000)) < 0) {
            //如果小于1万
            return amount.stripTrailingZeros().toPlainString();
        }
        if (amount.compareTo(new BigDecimal(10000000)) < 0) {
            //如果大于1万小于1亿
            return amount.divide(new BigDecimal(10000), 2, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString() + UNIT_STRING_WAN;
        }
        return amount.divide(new BigDecimal(100000000), 2, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString() + UNIT_STRING_YI;
    }


}
