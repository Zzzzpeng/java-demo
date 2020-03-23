package bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoData {

    @ExcelProperty(value = "姓名", index = 0)
    String name;
    @ExcelProperty(value = "价格", index = 2)
    @NumberFormat("#,###.0000")
    Double age;
    @ExcelProperty(value = "小名", index = 1)
    String yourName;


}