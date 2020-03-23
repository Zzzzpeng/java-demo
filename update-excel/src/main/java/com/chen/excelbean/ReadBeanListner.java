package com.chen.excelbean;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;

public class ReadBeanListner extends AnalysisEventListener<Bean> {
    @Override
    public void invoke(Bean data, AnalysisContext context) {
        System.out.println(JSON.toJSONString(data));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
