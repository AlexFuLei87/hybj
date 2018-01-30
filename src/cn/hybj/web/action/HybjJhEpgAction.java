package cn.hybj.web.action;



import java.io.UnsupportedEncodingException;
import java.util.List;

import cn.hybj.domain.*;
import cn.hybj.service.*;
import cn.hybj.web.form.HybjOutlineForm;
import cn.hybj.web.form.HybjSystemDDlForm;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.hybj.container.ServiceProvider;
import cn.hybj.web.form.HybjReportForm;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class HybjJhEpgAction extends BaseAction implements ModelDriven<HybjOutlineForm>{
	
	private IHybjJhEpgService hybjJhEpgService = (IHybjJhEpgService)ServiceProvider.getService(IHybjJhEpgService.SERVICE_NAME);
	private IHybjReportService hybjReportService = (IHybjReportService)ServiceProvider.getService(IHybjReportService.SERVICE_NAME);
    private IHybjNoticeService hybjNoticeService = (IHybjNoticeService)ServiceProvider.getService(IHybjNoticeService.SERVICE_NAME);
	private IHybjDemandService hybjDemandService = (IHybjDemandService)ServiceProvider.getService(IHybjDemandService.SERVICE_NAME);
	private IHybjSystemDDlService hybjSystemDDlService = (IHybjSystemDDlService)ServiceProvider.getService(IHybjSystemDDlService.SERVICE_NAME);
	private IHybjSpecialService hybjSpecialService = (IHybjSpecialService)ServiceProvider.getService(IHybjSpecialService.SERVICE_NAME);

	//调用日志管理的业务层
	private IHybjLogService hybjLogService = (IHybjLogService)ServiceProvider.getService(IHybjLogService.SERVICE_NAME);
	private HybjReportForm hybjReportForm = new HybjReportForm();
	private HybjOutlineForm hybjOutlineForm = new HybjOutlineForm();
	private HybjDemand demand;
	private  HybjReport hybjReport;
	private HybjSpecial hybjSpecial;

	public HybjSpecial getHybjSpecial() {
		return hybjSpecial;
	}

	public void setHybjSpecial(HybjSpecial hybjSpecial) {
		this.hybjSpecial = hybjSpecial;
	}

	public HybjReport getHybjReport() {
		return hybjReport;
	}

	public void setHybjReport(HybjReport hybjReport) {
		this.hybjReport = hybjReport;
	}

	public HybjDemand getDemand() {
		return demand;
	}

	public void setDemand(HybjDemand demand) {
		this.demand = demand;
	}

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
		List<HybjSystemDDlForm> jctList = hybjSystemDDlService.findElecSystemDDlListByKeyword("所属单位");
		hybjReportForm.setReportStatus("online");
		hybjReportForm.setStatus("normal");
		List<HybjReport> list = hybjJhEpgService.findByCondition(hybjReportForm);
		request.setAttribute("jctList", jctList);
		request.setAttribute("report", list);
		return "jhhy";
	}
	public String showOffline(){
		List<HybjSystemDDlForm> jctList = hybjSystemDDlService.findElecSystemDDlListByKeyword("所属单位");
		hybjReportForm.setReportStatus("offline");
		List<HybjReport> list = hybjJhEpgService.findByCondition(hybjReportForm);
		request.setAttribute("jctList", jctList);
		request.setAttribute("report", list);
		return "showOffline";
	}
	public String handleZt(){
		List<HybjSystemDDlForm> jctList = hybjSystemDDlService.findElecSystemDDlListByKeyword("所属单位");
		HybjSpecial hybjSpecial = new HybjSpecial();
		hybjSpecial.setStatus("normal");
		List<HybjSpecial> list =  hybjSpecialService.findByCondition(hybjSpecial);
		request.setAttribute("jctList", jctList);
		request.setAttribute("special", list);
		return "handleZt";
	}
	public String changSpecialStatus(){
		hybjSpecialService.updateStatus(hybjSpecial);
		JSONObject json = getSuccessJsonTemplate();
		json.put("message", "操作成功");
		writeStream(json);
		return SUCCESS;
	}
	public String saveSpecialFeedback(){
		hybjSpecialService.updatefeedback(hybjSpecial);
		JSONObject json = getSuccessJsonTemplate();
		writeStream(json);
		return SUCCESS;
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

	public String batchSpecialFail(){
			String[] idss = ids.split(",");
			HybjSpecial hybjSpecial = new HybjSpecial();
			hybjSpecial.setStatus("fail");
			for (String string : idss) {
				int id = Integer.parseInt(string);
				hybjSpecial.setId(id);
				hybjSpecialService.updateStatus(hybjSpecial);
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

    public String handleTZ(){
		HybjOutline outline = new HybjOutline();
		outline.setStatus("1");
		List<HybjOutline> lists = hybjNoticeService.findByCondition(outline);
		request.setAttribute("tzList", lists);
        return "handleTZ";
    }
    public String tzResult(){
		HybjOutline outline = new HybjOutline();
		outline.setStatus("23");
		List<HybjOutline> lists = hybjNoticeService.findByCondition(outline);
		request.setAttribute("tzList", lists);
        return "completeTZ";
    }
	public String showXQ(){
		List<HybjSystemDDlForm> jctList = hybjSystemDDlService.findElecSystemDDlListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
		HybjDemand hybjDemand =  new HybjDemand();
		HybjUser user = (HybjUser)request.getSession().getAttribute("globle_user");
		if("电信".equals(user.getDepartment())){
			hybjDemand.setTowho("dx");
		}else if("华数TV".equals(user.getDepartment())) {
			hybjDemand.setTowho("hs");
		}else if("芒果".equals(user.getDepartment())) {
			hybjDemand.setTowho("mg");
		}else if("优酷".equals(user.getDepartment())) {
			hybjDemand.setTowho("yk");
		}else if("优朋".equals(user.getDepartment())) {
			hybjDemand.setTowho("yp");
		}else if("百视通".equals(user.getDepartment())) {
			hybjDemand.setTowho("bst");
		}else if("天翼视讯".equals(user.getDepartment())) {
			hybjDemand.setTowho("tysx");
		}else if("腾讯".equals(user.getDepartment())) {
			hybjDemand.setTowho("tx");
		}else if("爱奇艺".equals(user.getDepartment())) {
			hybjDemand.setTowho("aqy");
		}else if("聚合".equals(user.getDepartment())){
			hybjDemand.setTowho("jh");
		}
		List<HybjDemand> lists = hybjDemandService.findByFuzzy(hybjDemand);
		request.setAttribute("xqList", lists);
		return "showXQ";

		}

    public String changeStatus(){
		hybjNoticeService.changeStatus(hybjOutlineForm);
		JSONObject json = getSuccessJsonTemplate();
		json.put("message", "状态更改成功");
		writeStream(json);
		return SUCCESS;

    }
	public String findById(){
		HybjDemand hybjDemand = hybjDemandService.findById(demand.getId());
		JSONObject json = getSuccessJsonTemplate();
		json.put("hybjDemand", hybjDemand);
		writeStream(json);
		return SUCCESS;

	}
	public String findNoticeById(){
		HybjOutline hybjOutline = hybjNoticeService.findById(demand.getId());
		JSONObject json = getSuccessJsonTemplate();
		json.put("hybjOutline", hybjOutline);
		writeStream(json);
		return SUCCESS;

	}

	public String changeStatusById(){
		hybjDemandService.changeStatusById(demand.getId(),demand.getStatus());
		JSONObject json = getSuccessJsonTemplate();
		json.put("message", "状态修改成功");
		writeStream(json);
		return SUCCESS;

	}

	public String changeTowhoById(){
		hybjDemandService.changeTowhoById(demand.getId(),demand.getTowho());
		JSONObject json = getSuccessJsonTemplate();
		json.put("message", "已指派给"+demand.getCp());
		writeStream(json);
		return SUCCESS;

	}

	public String deleteOutlineById(){
		hybjNoticeService.deleteById(hybjOutlineForm.getId());
		JSONObject json = getSuccessJsonTemplate();
		json.put("message", "该公示已被删除");
		writeStream(json);
		return SUCCESS;

	}
	public String findByFuzzy() throws UnsupportedEncodingException {
        List<HybjSystemDDlForm> jctList = hybjSystemDDlService.findElecSystemDDlListByKeyword("所属单位");
        request.setAttribute("jctList", jctList);
		byte[] bytes =hybjReport.getCp().getBytes("iso-8859-1");
		String cp = new String(bytes, "utf-8");
		byte[] bytes1 =hybjReport.getItemName().getBytes("iso-8859-1");
		String itemName = new String(bytes1, "utf-8");
		hybjReport.setCp(cp);
		hybjReport.setItemName(itemName);
		if(!StringUtils.isBlank(hybjReport.getReportStatus())){
			List<HybjReport> report = hybjReportService.findByFuzzy(hybjReport);
			request.setAttribute("report", report);
			return "showOffline";
		}else {
		List<HybjReport> report = hybjReportService.findByFuzzy(hybjReport);
		request.setAttribute("report", report);
		return "jhhy";
		}

	}
	public String findSpecialByFuzzy() throws UnsupportedEncodingException {
			List<HybjSystemDDlForm> jctList = hybjSystemDDlService.findElecSystemDDlListByKeyword("所属单位");
			request.setAttribute("jctList", jctList);
			byte[] bytes =hybjSpecial.getCp().getBytes("iso-8859-1");
			String cp = new String(bytes, "utf-8");
			byte[] bytes1 =hybjSpecial.getSpecialName().getBytes("iso-8859-1");
			String special = new String(bytes1, "utf-8");
			hybjSpecial.setCp(cp);
			hybjSpecial.setSpecialName(special);
			hybjSpecial.setStatus("normal");
			List<HybjSpecial> list = hybjSpecialService.findByCondition(hybjSpecial);
			request.setAttribute("special", list);
			return "handleZt";
		}

	public String findXQByFuzzy() throws UnsupportedEncodingException {
        List<HybjSystemDDlForm> jctList = hybjSystemDDlService.findElecSystemDDlListByKeyword("所属单位");
        request.setAttribute("jctList", jctList);
		byte[] bytes1 =demand.getDemandName().getBytes("iso-8859-1");
		String demandName = new String(bytes1, "utf-8");
		HybjUser user = (HybjUser)request.getSession().getAttribute("globle_user");
		if("电信".equals(user.getDepartment())){
			demand.setTowho("dx");
		}else if("华数TV".equals(user.getDepartment())) {
			demand.setTowho("hs");
		}else if("芒果".equals(user.getDepartment())) {
			demand.setTowho("mg");
		}else if("优酷".equals(user.getDepartment())) {
			demand.setTowho("yk");
		}else if("优朋".equals(user.getDepartment())) {
			demand.setTowho("yp");
		}else if("百视通".equals(user.getDepartment())) {
			demand.setTowho("bst");
		}else if("天翼视讯".equals(user.getDepartment())) {
			demand.setTowho("tysx");
		}else if("腾讯".equals(user.getDepartment())) {
			demand.setTowho("tx");
		}else if("爱奇艺".equals(user.getDepartment())) {
			demand.setTowho("aqy");
		}else if("聚合".equals(user.getDepartment())){
			demand.setTowho("jh");
		}
		demand.setDemandName(demandName);
		List<HybjDemand> report = hybjDemandService.findByFuzzy(demand);
		request.setAttribute("xqList", report);

		return "showXQ";

	}

	public String onlineResult() throws UnsupportedEncodingException {
		List<HybjSystemDDlForm> jctList = hybjSystemDDlService.findElecSystemDDlListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
		if (hybjReport != null){
			byte[] bytes =hybjReport.getCp().getBytes("iso-8859-1");
			String cp = new String(bytes, "utf-8");
			byte[] bytes1 =hybjReport.getItemName().getBytes("iso-8859-1");
			String itemName = new String(bytes1, "utf-8");
			hybjReportForm.setCp(cp);
			hybjReportForm.setItemName(itemName);
		}
		hybjReportForm.setStatus("onlineAndPass");
		hybjReportForm.setReportStatus("online");
		List<HybjReport> list = hybjJhEpgService.findByCondition(hybjReportForm);
		request.setAttribute("report", list);
		return "onlineResult";

	}

	public String offlineResult() throws UnsupportedEncodingException {
		List<HybjSystemDDlForm> jctList = hybjSystemDDlService.findElecSystemDDlListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
		if (hybjReport != null){
			byte[] bytes =hybjReport.getCp().getBytes("iso-8859-1");
			String cp = new String(bytes, "utf-8");
			byte[] bytes1 =hybjReport.getItemName().getBytes("iso-8859-1");
			String itemName = new String(bytes1, "utf-8");
			hybjReportForm.setCp(cp);
			hybjReportForm.setItemName(itemName);
		}
		hybjReportForm.setStatus("onlineAndPass");
		hybjReportForm.setReportStatus("offline");
		List<HybjReport> list = hybjJhEpgService.findByCondition(hybjReportForm);
		request.setAttribute("report", list);
		return "offlineResult";

	}



}
