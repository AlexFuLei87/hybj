package cn.hybj.web.action;


import cn.hybj.container.ServiceProvider;
import cn.hybj.service.*;
import cn.hybj.web.form.HybjDemandForm;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class HybjDemandAction extends BaseAction implements ModelDriven<HybjDemandForm>{
	private IHybjDemandService hybjDemandService = (IHybjDemandService)ServiceProvider.getService(IHybjDemandService.SERVICE_NAME);

	//调用日志管理的业务层
	private IHybjLogService elecLogService = (IHybjLogService)ServiceProvider.getService(IHybjLogService.SERVICE_NAME);
	private HybjDemandForm hybjDemandForm = new HybjDemandForm();

    public HybjDemandForm getHybjDemandForm() {
        return hybjDemandForm;
    }

    public void setHybjDemandForm(HybjDemandForm hybjDemandForm) {
        this.hybjDemandForm = hybjDemandForm;
    }

    //使用log4j
	Log log = LogFactory.getLog(HybjDemandAction.class);
	public HybjDemandForm getModel() {
		return hybjDemandForm;
	}


	public String save(){
		hybjDemandForm.setStatus("normal");
		int id = hybjDemandService.save(hybjDemandForm);

		return "demandAdd";
	}


}
