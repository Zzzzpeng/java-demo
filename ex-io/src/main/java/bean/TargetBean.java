package bean;

import annotation.CellInfo;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.ToString;

import java.util.Random;

@Data
@ToString
public class TargetBean {
    //票号	 票面金额 	出票日	汇票到期日	出票人	承兑人	承兑行行号	贴现行行名	贴现行行号
//    票号	票面金额	   出票日期	票据到期日	承兑人开户行名称	承兑人账号	承兑人开户行名称	承兑人开户行行号	贴现行名称	贴现行行号	 保证增信行名称（非必输项）	保证增信行行号（非必输项）	客户经理	前手背书人(资管计划购买需要录入)	是否有风险缓释物(资管计划购买需要录入)	是否同城（再贴现录入）	是否符合政策标准（再贴现录入）	任务类型(0-交易、1-竞价、2-撮合)131036100003820190830466919568	 1,800,000.00 	2019/8/30	2020/3/1	上海浦东发展银行股份有限公司合肥分行	0	上海浦东发展银行股份有限公司合肥分行	310361000011	平安银行股份有限公司广州分行	307581009005			huangxixi888					0
    @ColumnWidth
    @ExcelProperty(value = "票号")
    @CellInfo(width = 30 * 256)
    String ticketId;

    @CellInfo(format = "#,##0.00", type = Double.class, width = 18 * 256)
    @NumberFormat("#,##0.00")
    @ExcelProperty(value = "票面金额")
    Double price;

    @DateTimeFormat("yyyy/M/dd")
    @ExcelProperty(value = "出票日期")
    @CellInfo(format = "yyyy/M/dd", width = 14 * 256)
    String date;

    @CellInfo(format = "yyyy/M/dd",width = 14 * 256)
    @DateTimeFormat("yyyy/M/dd")
    @ExcelProperty(value = "票据到期日")
    String dedaLine;

//    @ExcelProperty(value = "出票人")
//    String createrName;

    @CellInfo(width = 30 * 256)
    @ExcelProperty(value = "承兑人开户行名称")
    String acceptor;

    @ExcelProperty(value = "承兑人账号")
    String acceptorAccount = "0";

    @CellInfo(width = 30 * 256)
    @ExcelProperty(value = "承兑人开户行名称")
    String acceptorr;

    @CellInfo(width = 20 * 256)
    @ExcelProperty(value = "承兑人开户行行号")
    String applybankNo;

    @CellInfo(width = 30 * 256)
    @ExcelProperty(value = "贴现行名称")
    String cashBank;

    @CellInfo(width = 15 * 256)
    @ExcelProperty(value = "贴现行行号")
    String cashBankNo;

    @CellInfo(width = 25 * 256)
    @ExcelProperty(value = " 保证增信行名称（非必输项）")
    String desc0 = "";

    @CellInfo(width = 25 * 256)
    @ExcelProperty(value = "保证增信行行号（非必输项）")
    String desc1 = "";

    @CellInfo(width = 25 * 256)
    @ExcelProperty(value = "客户经理")
    String customManager = "tanmanbo961";

    @CellInfo(width = 25 * 256)
    @ExcelProperty(value = "前手背书人(资管计划购买需要录入)")
    String desc2 = "";

    @CellInfo(width = 25 * 256)
    @ExcelProperty(value = "是否有风险缓释物(资管计划购买需要录入)")
    String desc3 = "";

    @CellInfo(width = 25 * 256)
    @ExcelProperty(value = "是否同城(再贴现录入)")
    String desc4 = "";

    @CellInfo(width = 25 * 256)
    @ExcelProperty(value = "是否符合政策标准(再贴现录入)")
    String desc5 = "";

    @ExcelProperty(value = "任务类型(0-交易、1-竞价、2-撮合)")
    @CellInfo(type = int.class,width = 25 * 256)
    int desc6 = 0;


    public void setAcceptor(String acceptor) {
        this.acceptor = acceptor;
        setAcceptorr(acceptor);
        Random random = new Random();
        random.nextDouble();
    }

}
