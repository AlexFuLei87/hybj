package cn.hybj.web.action;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.hybj.container.ServiceProvider;
import cn.hybj.domain.HybjReport;
import cn.hybj.service.IHybjLogService;
import cn.hybj.service.IHybjReportService;
import cn.hybj.service.IHybjSystemDDlService;
import cn.hybj.service.IHybjUserService;
import cn.hybj.web.form.HybjReportForm;
import cn.hybj.web.form.HybjSystemDDlForm;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class HybjReportAction extends BaseAction implements ModelDriven<HybjReportForm>{
	
	private IHybjUserService hybjUserService = (IHybjUserService)ServiceProvider.getService(IHybjUserService.SERVICE_NAME);
	
	private IHybjReportService hybjReportService = (IHybjReportService)ServiceProvider.getService(IHybjReportService.SERVICE_NAME);
	
	private IHybjSystemDDlService hybjSystemDDlService = (IHybjSystemDDlService)ServiceProvider.getService(IHybjSystemDDlService.SERVICE_NAME);
	private String isSubmit;
	private String ids;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public String getIsSubmit() {
		return isSubmit;
	}

	public void setIsSubmit(String isSubmit) {
		this.isSubmit = isSubmit;
	}

	//调用日志管理的业务层
	private IHybjLogService hybjLogService = (IHybjLogService)ServiceProvider.getService(IHybjLogService.SERVICE_NAME);
	private HybjReportForm hybjReportForm = new HybjReportForm();
	
	
	
	public HybjReportForm getHybjReportForm() {
		return hybjReportForm;
	}


	public void setHybjReportForm(HybjReportForm hybjReportForm) {
		this.hybjReportForm = hybjReportForm;
	}


	//使用log4j
	Log log = LogFactory.getLog(HybjReportAction.class);
	public HybjReportForm getModel() {
		return hybjReportForm;
	}

	
	public String save() throws Exception{
		if("online".equals(status)){
			hybjReportForm.setReportStatus("online");
		}else {
			hybjReportForm.setReportStatus("offline");
		}
		JSONObject json = getSuccessJsonTemplate();
		if("yes".equals(isSubmit)){
		//正常状态
		hybjReportForm.setStatus("normal");
		hybjReportService.saveReport(hybjReportForm);
		json.put("message", "提交成功");
		writeStream(json);
		}else{
		//草稿状态
		hybjReportForm.setStatus("draft");
		hybjReportService.saveReport(hybjReportForm);
		json.put("message", "已保存至草稿");
		writeStream(json);
		}
		return SUCCESS;
	}
	
	  public String checkItemName(){
		  	int count = hybjReportService.findReportByItemName(hybjReportForm.getItemName());
	        JSONObject json = getSuccessJsonTemplate();
	        if(count>0){
	        json.put("message", "该节目可能已被上报");
	        }
	        writeStream(json);
	        return SUCCESS;
	    }
	
	  public String importdata(){
		  List<HybjReport> list = hybjReportService.saveReportWithExcel(hybjReportForm);
		  request.setAttribute("cpList", list);
		 return "importResult";
		 
	  }
	  public String importOffdata(){
		  List<HybjReport> list = hybjReportService.saveReportWithExcel(hybjReportForm);
		  request.setAttribute("cpList", list);
		 return "importResult";

	  }
	  
	  public String changeType(){
		 List<HybjSystemDDlForm> typeList = hybjSystemDDlService.findElecSystemDDlListByKeyword(hybjReportForm.getProgramaName());
		 JSONObject json = getSuccessJsonTemplate();
		 request.setAttribute("typeList", typeList);
		 json.put("typeList", typeList);
		 writeStream(json);
		  return SUCCESS;
		  
	  }
	  public String batchUpdate(){
		  String[] idss = ids.split(",");
		  for (String string : idss) {
			int id = Integer.parseInt(string);
			hybjReportService.updateStatusById(id);
		 }
		  JSONObject json = getSuccessJsonTemplate();
		  json.put("message", "批量上传成功");
		  writeStream(json);
		  return SUCCESS;
		  
	  }

	  
	  public String update(){
		  int id = Integer.parseInt(ids);
		  hybjReportService.updateStatusById(id);
		  JSONObject json = getSuccessJsonTemplate();
		  json.put("message", "上传成功");
		  writeStream(json);
		  return SUCCESS;
		  
	  }
	  public String updateReport(){
		  JSONObject json = getSuccessJsonTemplate();
		  if("yes".equals(isSubmit)){
			  //正常状态
			  hybjReportForm.setStatus("normal");
			  hybjReportService.updateReport(hybjReportForm);
			  json.put("message", "提交成功");
			  writeStream(json);
		  }else{
			  //草稿状态
			  hybjReportForm.setStatus("draft");
			  hybjReportService.updateReport(hybjReportForm);
			  json.put("message", "已保存至草稿");
			  writeStream(json);
		  }
		  json.put("message", "提交成功");
		  writeStream(json);
		  return SUCCESS;

	  }



	  
}
