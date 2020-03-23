package com.chen.excelbean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;


@Data
public class TicketPropertiy {

    @JSONField(name = "日期/区间")
    @ExcelProperty("日期/区间")
    public String date;

    @JSONField(name = "1D")
    @ExcelProperty("1D")
    public String D1;


    @JSONField(name = "7D")
    @ExcelProperty("7D")
    public String D7;

    @JSONField(name = "1M")
    @ExcelProperty("1M")
    public String M1;

    @JSONField(name = "2M")
    @ExcelProperty("2M")
    public String M2;

    @JSONField(name = "3M")
    @ExcelProperty("3M")
    public String M3;

    @JSONField(name = "4M")
    @ExcelProperty("4M")
    public String M4;

    @JSONField(name = "5M")
    @ExcelProperty("5M")
    public String M5;

    @JSONField(name = "6M")
    @ExcelProperty("6M")
    public String M6;

    @JSONField(name = "7M")
    @ExcelProperty("7M")
    public String M7;

    @JSONField(name = "8M")
    @ExcelProperty("8M")
    public String M8;

    @JSONField(name = "9M")
    @ExcelProperty("9M")
    public String M9;

    @JSONField(name = "10M")
    @ExcelProperty("10M")
    public String M10;

    @JSONField(name = "11M")
    @ExcelProperty("11M")
    public String M11;

    @JSONField(name = "1Y")
    @ExcelProperty("Y1")
    public Double Y1;
}
