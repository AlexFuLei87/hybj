package cn.hybj.web.action;



import java.util.List;

import cn.hybj.domain.HybjOutline;
import cn.hybj.service.IHybjNoticeService;
import cn.hybj.service.IHybjReportService;
import cn.hybj.web.form.HybjOutlineForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.hybj.container.ServiceProvider;
import cn.hybj.domain.HybjReport;
import cn.hybj.service.IHybjJhEpgService;
import cn.hybj.service.IHybjLogService;
import cn.hybj.web.form.HybjReportForm;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class HybjJhEpgAction extends BaseAction implements ModelDriven<HybjOutlineForm>{
	
	private IHybjJhEpgService hybjJhEpgService = (IHybjJhEpgService)ServiceProvider.getService(IHybjJhEpgService.SERVICE_NAME);
	private IHybjReportService hybjReportService = (IHybjReportService)ServiceProvider.getService(IHybjReportService.SERVICE_NAME);
    private IHybjNoticeService hybjNoticeService = (IHybjNoticeService)ServiceProvider.getService(IHybjNoticeService.SERVICE_NAME);

    //调用日志管理的业务层
	private IHybjLogService hybjLogService = (IHybjLogService)ServiceProvider.getService(IHybjLogService.SERVICE_NAME);
	private HybjReportForm hybjReportForm = new HybjReportForm();
	private HybjOutlineForm hybjOutlineForm = new HybjOutlineForm();

    public HybjOutlineForm getHybjOutlineForm() {
        return hybjOutlineForm;
    }

    public void setHybjOutlineForm(HybjOutlineForm hybjOutlineForm) {
        this.hybjOutlineForm = hybjOutlineForm;
    }

    //使用log4j
	Log log = LogFactory.getLog(HybjJhEpgAction.class);
	public HybjOutlineForm getModel() {
		return hybjOutlineForm;
	}

	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public HybjReportForm getHybjReportForm() {
		return hybjReportForm;
	}


	public void setHybjReportForm(HybjReportForm hybjReportForm) {
		this.hybjReportForm = hybjReportForm;
	}


	public String jhyp(){
		return "jhyp";
	}
	
	public String jhhy(){
		List<HybjReport> list = hybjJhEpgService.findByCondition(hybjReportForm);
		request.setAttribute("report", list);
		return "jhhy";
	}
	
	public String checkStatus(){
		hybjJhEpgService.changeStatus(hybjReportForm);
		
		JSONObject json = getSuccessJsonTemplate();
		json.put("message", "操作成功");
		writeStream(json);
		return SUCCESS;
	}
	
	public String saveFeedback(){
		hybjJhEpgService.saveFeedback(hybjReportForm);
		return SUCCESS;
	}
	public String batchPass(){
		String[] idss = ids.split(",");
		String status = "pass";
		for (String string : idss) {
			int id = Integer.parseInt(string);
			hybjReportService.updateById(id,status);
		}
		JSONObject json = getSuccessJsonTemplate();
		json.put("message", "批量审核成功");
		writeStream(json);
		return SUCCESS;

	}


	public String showGS(){
		return "showGS";
	}

    public String saveOutline(){
        hybjJhEpgService.saveGG(hybjOutlineForm);
        JSONObject json = getSuccessJsonTemplate();
        json.put("message", "公告添加成功");
        writeStream(json);
        return SUCCESS;

    }
    public String showYW(){
        List<HybjOutline> lists = hybjNoticeService.findAll();
        request.setAttribute("ggList", lists);
        return "showYW";

    }

    public String changeStatus(){
		hybjNoticeService.changeStatus(hybjOutlineForm);
		JSONObject json = getSuccessJsonTemplate();
		json.put("message", "状态更改成功");
		writeStream(json);
		return SUCCESS;

    }
}
