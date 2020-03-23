package com.chen.excelbean;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.*;

public class ReadMapListner extends AnalysisEventListener<Map<Integer, String>> {
    public List<Map<Integer, String>> guoGuList = new ArrayList<>();
    public List<Map<Integer, String>> chengShangList = new ArrayList<>();
    public static Map<Integer, String> headMap;

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
//        context.readSheetHolder()
//        System.out.println(JSON.toJSONString(data));
        Integer sheetNo = context.readSheetHolder().getSheetNo();
        if(sheetNo == 0)
            guoGuList.add(data);
        else if(sheetNo == 1)
            chengShangList.add(data);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println(headMap);
        this.headMap = headMap;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
    public List<Map<String,String>>[] getMapList(){
        List<Map<String, String>> guoGuObjList = new ArrayList<>();
        List<Map<String, String>> chengShangObjList = new ArrayList<>();
        for (Map<Integer, String> seqMapItem : guoGuList) {
            Map<String,String> obj = new HashMap<>();
            //遍历键值对,序号键换成属性键
            for (Map.Entry<Integer, String> entry : seqMapItem.entrySet()) {
                obj.put(headMap.get(entry.getKey()),entry.getValue());
            }
            guoGuObjList.add(obj);
        }
        for (Map<Integer, String> seqMapItem : chengShangList) {
            Map<String,String> obj = new HashMap<>();
            //遍历键值对,序号键换成属性键
            for (Map.Entry<Integer, String> entry : seqMapItem.entrySet()) {
                obj.put(headMap.get(entry.getKey()),entry.getValue());
            }
            chengShangObjList.add(obj);
        }
        return (List<Map<String, String>>[]) Arrays.asList(guoGuObjList,chengShangObjList).toArray();

    }
}
