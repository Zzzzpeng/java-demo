package bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.Data;

@Data
public class SourceBean {
    //    票号	票面金额	出票日期	票据到期日	承兑人开户行名称	承兑人账号	承兑人开户行名称	承兑人开户行行号	贴现行名称	贴现行行号	 保证增信行名称（非必输项）	保证增信行行号（非必输项）	客户经理	前手背书人(资管计划购买需要录入)	是否有风险缓释物(资管计划购买需要录入)	是否同城（再贴现录入）	是否符合政策标准（再贴现录入）	任务类型(0-交易、1-竞价、2-撮合)131036100003820190830466919568	 1,800,000.00 	2019/8/30	2020/3/1	上海浦东发展银行股份有限公司合肥分行	0	上海浦东发展银行股份有限公司合肥分行	310361000011	平安银行股份有限公司广州分行	307581009005			huangxixi888					0
    //票号	 票面金额 	出票日	汇票到期日	出票人	承兑人	承兑行行号	贴现行行名	贴现行行号

    @ExcelProperty(value = "票号")
    String ticketId;

    @ExcelProperty(value = "票面金额")
    Double price;

    @DateTimeFormat("yyyy/MM/dd")
    @ExcelProperty(value = "出票日")
    String date;

    @DateTimeFormat("yyyy/MM/dd")
    @ExcelProperty(value = "汇票到期日")
    String dedaLine;

    @ExcelProperty(value = "出票人")
    String createrName;

    @ExcelProperty(value = "承兑人")
    String acceptor;

    @ExcelProperty(value = "承兑行行号")
    String applybankNo;

    @ExcelProperty(value = "贴现行行名")
    String cashBank;

    @ExcelProperty(value = "贴现行行号")
    String cashBankNo;
}
