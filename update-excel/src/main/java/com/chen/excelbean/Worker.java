package com.chen.excelbean;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.SimpleDateFormat;
import java.util.*;

public class Worker implements PageProcessor {
    public Worker(Date deadLine) {
        this.deadLine = deadLine;
    }

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    Date deadLine;
    //业务相关属性
    static List<String> sheetNames = Arrays.asList("国股曲线","城商曲线");
    TicketType countryTicket = new TicketType(sheetNames.get(0),"1");
    TicketType cityTicket = new TicketType(sheetNames.get(1),"2");
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private String[] filedArr = "日期/区间,1D,7D,1M,2M,3M,4M,5M,6M,7M,8M,9M,10M,11M,1Y".split(",");
    List<Map<String, String>> guoGuList = new Vector<>();
    List<Map<String, String>> chengshangList = new Vector<>();
    @Override
    public void process(Page page) {
        TicketType ticketType = page.getUrl().get().contains("lcid_1")? countryTicket : cityTicket;
        List<Map<String, String>> dataList = page.getUrl().get().contains("lcid_1") ? guoGuList : chengshangList;

        Elements select = page.getHtml().getDocument().select("table>tbody>tr");
        //如果连续N次获取不到数据,不继续爬历史数据
        if(!ticketType.goonOrNot(select.size())){
            System.out.println("日期已衔接或连续无数据,停止爬取...");
            return;
        }
        if(select.size()>1){
            String pageStr = page.getHtml().get();
            int left = pageStr.indexOf("showValueStr = \",\" + \"");
            int right = pageStr.indexOf("\" + \",\";//获取y轴要显");
            String dataArrayStr = pageStr.substring(left+22,right);
            String[] valueArr = dataArrayStr.split(",");
            Map<String, String> dataObj = new HashMap<>(16);
            dataObj.put(filedArr[0],ticketType.getDateFormat());
            for (int i = 1; i < filedArr.length; i++) {
                dataObj.put(filedArr[i],valueArr[i-1]);
            }
            dataList.add(dataObj);
            System.out.println(ticketType.name+"\n"+JSON.toJSONString(dataObj));
            //下次要抓取的页面url
        }
        page.addTargetRequests(ticketType.getBackDateFormat());

    }

    @Override
    public Site getSite() {
        return site;
    }

    class TicketType {
        private String name;
        String urlLastFix;

        public TicketType(String name, String urlLastFix) {
            this.name = name;
            this.urlLastFix = urlLastFix;
        }

        private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        private SimpleDateFormat excelDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        private Calendar targetCalendar = Calendar.getInstance();
        private int count = 2;
        private final int initCount = 10;

        boolean goonOrNot(int size){
            if(deadLine != null && DateUtils.isSameDay(deadLine, targetCalendar.getTime()))
                return false;
            if(size == 8){
                count = initCount;
            }
            else {
                if(--count<0) return false;
            }
            return true;
        }

        public String getDateFormat() {
            return excelDateFormat.format(targetCalendar.getTime());
        }

        public List<String> getBackDateFormat() {
            targetCalendar.add(Calendar.DAY_OF_MONTH, -1);
            return  Arrays.asList("http://www.shcpe.com.cn/index_132_lcid_"+urlLastFix+"_date_"
                    + simpleDateFormat.format(targetCalendar.getTime()) + ".html");

        }
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println();
    }
}