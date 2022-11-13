package com.chen.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordBean extends BaseRowModel {


    @ExcelProperty("企业名称")
    public String name;


    @ExcelProperty("详细地址")
    public String  addr;

 }