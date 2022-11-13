package com.chen.bean;


import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class ResultData  extends BaseRowModel {

    @ExcelProperty({"企业名称"})
    private String name;

    @ExcelProperty({"年份"})
    private String time;

    @ExcelProperty({"状态"})
    private String status;

    @ExcelProperty({"数值"})
    private String value;

    @ExcelProperty({"详情"})
    private String desc;



}