package com.chen;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSON;
import com.chen.bean.RecordBean;
import com.chen.bean.ResultData;
import okhttp3.*;
import org.apache.poi.util.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.*;

public class Runner {

    public static final MediaType TYPE_JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static final String DOMAIN = "http://www.shcpe.com.cn";

    public static void ma1in(String[] args) throws IOException {

        //        String keyWord = properties.getProperty("keyWord");
//        while (keyWord == null || keyWord.isEmpty()) {
//            Scanner sc = new Scanner(System.in);
//            System.out.println("请输入搜索关键字keyWord");
//            keyWord = sc.nextLine();  //读取字符串型输入
//        }
//        List<String> keys = Arrays.asList(keyWord.split(","));



        InputStream inputStream = new FileInputStream("../input.config");
        InputStreamReader reader = new InputStreamReader(inputStream, "GBK");
        Properties properties = new Properties();
        properties.load(reader);
        String year = properties.getProperty("year");
        year = StringUtils.isEmpty(year) ? "2020" : year;

        List<String> keys = readObj("../input.xls");
        List<ResultData> resultDataList = new ArrayList<>();
        for (String key : keys) {
            OkHttpClient client = new OkHttpClient();
            FormBody.Builder params = new FormBody.Builder();
            params.add("form['CODE_KIND']", "");
            params.add("form['QUERY_STATE']", "");
            params.add("form['ENTER_TYPE']", "");
            params.add("form['START_DATE']", "");
            params.add("form['END_DATE']", "");
            params.add("form['REGIONNAME']", "");
            params.add("form['REGIONCODE']", "440310");
            params.add("pointType:", "REGION");
            params.add("form['ENTER_NAME']", key);
            params.add("pages:", "1");
            params.add("pageNum", "1");
            params.add("pageSize", "50");
            params.add("orderBy", "FILL_TIME DESC,e.CREATE_TIME DESC,ENTER_ID ASC ");
            Headers header = new Headers.Builder()
                    .add("Cookie", "JSESSIONID=" + properties.getProperty("JSESSIONID"))
                    .add("Host", "www-app.gdeei.cn")
                    .add("Origin", "https://www-app.gdeei.cn")
                    .add("Referer", "https://www-app.gdeei.cn/nvocs/wysb/list.vm?type=2").build();
            FormBody build = params.build();
            Request request = new Request.Builder()
                    .url("https://www-app.gdeei.cn/nvocs/wysb/list.vm?type=2")
                    .post(build)
                    .headers(header)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isRedirect()) {
                String location = response.header("Location");
                request = new Request.Builder()
                        .url(DOMAIN + location)
                        .post(RequestBody.create(TYPE_JSON, com.alibaba.fastjson.JSON.toJSONString(params)))
                        .build();
                response = client.newCall(request).execute();
            }
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String body = response.body().string();
//            System.out.println(body);
            Document document = Jsoup.parse(body);
            Element tableElement = document.getElementById("listTable");
            Elements tbody = tableElement.getElementsByTag("tbody");
            Elements elements = tbody.get(0).getElementsByTag("tr");
            boolean match = false;
            for (Element element : elements) {
                String[] text = element.text().split(" ");
                if(text.length<2){
                    break;
                }
                String s = text[1];
                if (year.equals(s)) {
                    System.out.print(key + "   ");
                    System.out.println(s + "   " + text[text.length - 2] + "......详情:" + element.text());
                    match = true;
                    ResultData resultData = new ResultData();
                    resultData.setName(key);
                    resultData.setTime(year);
                    resultData.setStatus(text[text.length - 2]);
                    resultData.setValue(text[text.length - 1]);
                    resultData.setDesc(element.text());
                    resultDataList.add(resultData);
                    break;
                }
            }
            if (!match) {
                ResultData resultData = new ResultData();
                resultData.setName(key);
                resultData.setStatus("无记录");
                resultDataList.add(resultData);
                System.out.println(key + "..无记录");
            }
        }
        writeData(resultDataList);
        System.out.println("\n\n检索完毕......,结果详output.xlsx");


    }

    public static List<String> readObj(String path) throws IOException {
        String readPath = "C:\\Users\\HASEE\\Downloads\\广东省VOCs系统坪山区企业填报情况.xls";
        readPath = !StringUtils.isEmpty(path) ? path : readPath;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(readPath);
            // sheetNo --> 读取哪一个 表单
            // headLineMun --> 从哪一行开始读取( 不包括定义的这一行，比如 headLineMun为2 ，那么取出来的数据是从 第三行的数据开始读取 )
            // clazz --> 将读取的数据，转化成对应的实体，需要 extends BaseRowModel
            Sheet sheet = new Sheet(1, 2, RecordBean.class);

            // 这里 取出来的是 ExcelModel实体 的集合
            List<Object> readList = EasyExcelFactory.read(inputStream, sheet);
            // 存 ExcelMode 实体的 集合
            List<String> companyNames = new ArrayList<>();
            for (Object obj : readList) {
                String name = ((RecordBean) obj).getName();
                name = name.replaceAll("（.*", "");
                name = name.replaceAll("\\(.*", "");
                companyNames.add(name);
            }
            return companyNames;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new ArrayList<>();
    }

    public static void writeData(List<ResultData> list) throws IOException {
        if(list == null || list.size() == 0){
            return;
        }
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("../output.xlsx");
            EasyExcelFactory.write(outputStream, ResultData.class).sheet("sheet1").doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    public static void main(String[] args) {
        
    }


}


