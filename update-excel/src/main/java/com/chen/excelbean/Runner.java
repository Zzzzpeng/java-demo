package com.chen.excelbean;

import com.alibaba.fastjson.JSON;
import com.chen.Main;
import org.springframework.util.StringUtils;
import us.codecraft.webmagic.Spider;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Runner {
    public void updateFile() throws Exception {
        //获取源文件和目标文件
        Properties properties = new Properties();
//        properties.load(new FileInputStream("../conf/config.properties"));
        String source = properties.getProperty("source");
        String target = properties.getProperty("target");
        if(StringUtils.isEmpty(source))
            source = Main.FROM_FILE_PATH;
        if(StringUtils.isEmpty(target))
            target = Main.TO_FILE_PATH;
//              target = "F:\\桌面\\pack\\finish.xlsx";
        List<Map<String,String>>[] list ;
        try {
            list = ExcelUtil.readAndReturn(source);
        } catch (Exception e) {
            e.printStackTrace();
            list = new List[2];
            list[0] = new ArrayList<>();
            list[1] = new ArrayList<>();
        }

        Date deadLine = null;
        try {
            Map<String, String> stringStringMap = list[0].get(list[0].size() - 1);
            String dateStr = stringStringMap.get("日期/区间");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/M/d");
            try {
                deadLine = dateFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                deadLine = dateFormat.parse(dateStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("指定文件无有效数据,开始抓取历史数据");
        }
        Worker worker = new Worker(deadLine);
        Spider spider = Spider.create(worker);
        String dateformat = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        String[] urls = (String[]) Arrays.asList(
                "http://www.shcpe.com.cn/index_132_lcid_1_date_" + dateformat + ".html",
                "http://www.shcpe.com.cn/index_132_lcid_2_date_" + dateformat + ".html").toArray();

                //开启2个线程抓取//启动爬虫
        spider.addUrl(urls).thread(2).run();
        Collections.reverse(worker.guoGuList);
        Collections.reverse(worker.chengshangList);
        list[0].addAll(worker.guoGuList);
        list[1].addAll(worker.chengshangList);
        List<Map<String, String>> guoGuList = list[0];
        List<TicketPropertiy> ggResList = new ArrayList<>();
        for (Map<String, String> mapItem : guoGuList) {
            String s = JSON.toJSONString(mapItem);
            TicketPropertiy ticketPropertiy = JSON.parseObject(s, TicketPropertiy.class);
            ggResList.add(ticketPropertiy);
        }
        ExcelUtil.writeWithoutModel(target,ReadMapListner.headMap,list);
//        ExcelUtil.writeWithoutModel(target,ggResList);
    }
}
