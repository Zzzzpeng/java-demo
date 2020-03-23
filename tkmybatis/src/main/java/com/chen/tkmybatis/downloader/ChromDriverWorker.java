//package com.chen.tkmybatis.downloader;
//
//import com.chen.tkmybatis.dao.FileUrlMapper;
//import com.chen.tkmybatis.po.FileUrl;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//import tk.mybatis.mapper.entity.Example;
//import tk.mybatis.mapper.util.Sqls;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Service
//public class ChromDriverWorker {
//    @Autowired
//    FileUrlMapper fileUrlMapper;
//
//
//    public void download() {
//        String downloadFilepath = "D:\\classfile\\8down";
//        Map<String, Object> prefs = new HashMap<String, Object>();
//        prefs.put("download.default_directory", downloadFilepath);
//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("prefs", prefs);
////        ChromeDriver driver = new ChromeDriver(options);
//
//        List<String> catlogList = Arrays.asList("八上", "八下", "九上", "九下", "七上", "七下");
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HASEE\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
//        System.out.println(System.getProperty("webdriver.chrome.driver"));
//        WebDriver driver = null;
//        int size = 30;
//        int sleepSec = 1;
//        try {
//            driver = new ChromeDriver(options);
//            driver.get("https://passport.21cnjy.com/login?jump_url=http%3A%2F%2Fwww.21cnjy.com/H/9/49091/6031845.shtml");
//            int count = 0;
//            for (int i = 0; i < catlogList.size(); i++) {  //561
//                String catlog = catlogList.get(i);
//                Example example = Example.builder(FileUrl.class).select("id","url", "fileSize").
//                        where(Sqls.custom().andEqualTo("catalog", catlog).andGreaterThan("id",18820)).build();
//                List<FileUrl> fileUrls = fileUrlMapper.selectByExample(example);
//                fileUrls = fileUrls.stream().filter(item -> {
//                    if (!item.getFileSize().contains("MB"))
//                        return true;
//
//                    String[] split = item.getFileSize().split("\\.");
//                    try {
//                        if (split.length == 2) {
//                            int mbSize = Integer.valueOf(split[0]);
//                            if (mbSize > size)
//                                return false;
//                        } else if (split.length == 1) {
//                            split = item.getFileSize().split(" ");
//                            int mbSize = Integer.valueOf(split[0]);
//                            if (mbSize > size)
//                                return false;
//                        }
//                    } catch (NumberFormatException e) {
//                        System.err.println("提取文件大小异常，参数如下");
//                        System.err.println(item.toString());
//                    }
//                    return true;
//                }).collect(Collectors.toList());
//                List<String> urlList = fileUrls.stream().map(FileUrl::getUrl).distinct().collect(Collectors.toList());
//                if (CollectionUtils.isEmpty(fileUrls)) {
//                    System.err.println("fileUrls列表为空,参数calog:" + catlog);
//                    continue;
//                }
//                for (int j = 0; j < urlList.size(); j++) {
//                    String url = urlList.get(j);
//                    if (count % 3 == 0) {
//                        Thread.sleep(sleepSec * 1000);
//                    }
//                    String number = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
//                    url = "https://www.21cnjy.com/" + "xwt/index?assetId=" + number;
//                    if (StringUtils.isEmpty(number)) {
//                        System.err.println("截取下载链接参数失败,number:  " + number);
//                        continue;
//                    }
//                    driver.get(url);
//                    count++;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            driver.close();
//        }
//    }
//
//    public static void main(String[] args) {
////        String str = "182 MB";
////        String[] split = str.split("\\.");
////        if(split.length==2){
////            int mbSize = Integer.valueOf(split[0]);
////            if(mbSize>55){}
////        }
////        else if(split.length==1){
////            split = str.split(" ");
////            Arrays.toString(split);
////            System.out.println(split[0]);
////        }
////        System.out.println(Arrays.toString(split));
//    }
//}
