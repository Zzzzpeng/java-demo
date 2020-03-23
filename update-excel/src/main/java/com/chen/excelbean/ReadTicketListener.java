package com.chen.excelbean;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;

public class ReadTicketListener extends AnalysisEventListener<TicketPropertiy> {
    @Override
    public void invoke(TicketPropertiy data, AnalysisContext context) {
        System.out.println(JSON.toJSON(data));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
