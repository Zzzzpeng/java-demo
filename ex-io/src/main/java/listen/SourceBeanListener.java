package listen;

import bean.SourceBean;
import bean.TargetBean;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.apache.commons.beanutils.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public  class SourceBeanListener extends AnalysisEventListener<SourceBean> {
    List<TargetBean> resList = new ArrayList<>();
    @Override
    public void invoke(SourceBean data, AnalysisContext context) {
        TargetBean targetBean = new TargetBean();
        try {
            BeanUtils.copyProperties(targetBean,data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resList.add(targetBean);
    }
    public List<TargetBean> getResList(){
        return resList;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("读取完成");
    }
}
