package cn.hybj.web.action;


import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;

import cn.hybj.domain.HybjSpecial;
import cn.hybj.domain.HybjUser;
import cn.hybj.service.*;
import cn.hybj.web.form.HybjSpecialForm;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.hybj.container.ServiceProvider;
import cn.hybj.domain.HybjReport;
import cn.hybj.web.form.HybjReportForm;
import cn.hybj.web.form.HybjSystemDDlForm;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class HybjReportAction extends BaseAction implements ModelDriven<HybjReportForm>{
	
	private IHybjUserService hybjUserService = (IHybjUserService)ServiceProvider.getService(IHybjUserService.SERVICE_NAME);
	private IHybjSpecialService hybjSpecialService = (IHybjSpecialService)ServiceProvider.getService(IHybjSpecialService.SERVICE_NAME);
	private IHybjReportService hybjReportService = (IHybjReportService)ServiceProvider.getService(IHybjReportService.SERVICE_NAME);
	
	private IHybjSystemDDlService hybjSystemDDlService = (IHybjSystemDDlService)ServiceProvider.getService(IHybjSystemDDlService.SERVICE_NAME);
	private String isSubmit;
	private String ids;
	private String submitStatus;
	private HybjReport hybjReport;

	public HybjReport getHybjReport() {
		return hybjReport;
	}

	public void setHybjReport(HybjReport hybjReport) {
		this.hybjReport = hybjReport;
	}

	public String getSubmitStatus() {
		return submitStatus;
	}

	public void setSubmitStatus(String submitStatus) {
		this.submitStatus = submitStatus;
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


	private HybjSpecialForm hybjSpecialForm;

	public HybjSpecialForm getHybjSpecialForm() {
		return hybjSpecialForm;
	}

	public void setHybjSpecialForm(HybjSpecialForm hybjSpecialForm) {
		this.hybjSpecialForm = hybjSpecialForm;
	}

	//使用log4j
	Log log = LogFactory.getLog(HybjReportAction.class);
	public HybjReportForm getModel() {
		return hybjReportForm;
	}

	
	public String save() throws Exception{
		if("online".equals(submitStatus)){
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
	        json.put("message", "该节目可能已被申报");
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

	  public String findByFuzzy() throws UnsupportedEncodingException {
		  List<HybjSystemDDlForm> jctList = hybjSystemDDlService.findElecSystemDDlListByKeyword("所属单位");
		  request.setAttribute("jctList", jctList);
		  byte[] bytes =hybjReport.getCp().getBytes("iso-8859-1");
		  String cp = new String(bytes, "utf-8");
		  byte[] bytes1 =hybjReport.getItemName().getBytes("iso-8859-1");
		  String itemName = new String(bytes1, "utf-8");
		  byte[] bytes2 =hybjReport.getProgramaName().getBytes("iso-8859-1");
		  String programName = new String(bytes2, "utf-8");

		  HybjReport hybjReport1 = new HybjReport();
		  hybjReport1.setCp(cp);
		  hybjReport1.setItemName(itemName);
		  hybjReport1.setProgramaName(programName);
		  hybjReport1.setIsCharge(hybjReport.getIsCharge());
		  hybjReport1.setStatus(hybjReport.getStatus());
		  if(StringUtils.isBlank(hybjReport1.getStatus())){
			  hybjReport1.setStatus("dxPassAnddxFail");
		  }
		  showPermission();
		  List<HybjReport> list = hybjReportService.findByFuzzyWithPage(hybjReport1,request);
		  request.setAttribute("gsList", list);
		return "alermGS";
	  }

	public void showPermission(){
		HybjUser user = (HybjUser)request.getSession().getAttribute("globle_user");
		if(Objects.equals("电信",user.getDepartment())){
			request.setAttribute("permission", "dx");
		}else if(Objects.equals("聚合",user.getDepartment())){
			request.setAttribute("permission", "jh");
		}else {
			request.setAttribute("permission", "cp");
		}
	}
	  
}
