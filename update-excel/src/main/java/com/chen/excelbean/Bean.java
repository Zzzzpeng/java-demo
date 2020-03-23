package com.chen.excelbean;

import com.alibaba.excel.annotation.ExcelProperty;



public class Bean {
    @ExcelProperty("name")
    public String name;
    @ExcelProperty("age")
    public String age;
    @ExcelProperty("count")
    public String count;

}
