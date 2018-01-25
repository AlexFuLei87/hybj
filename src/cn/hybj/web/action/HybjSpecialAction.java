package cn.hybj.web.action;


import cn.hybj.container.ServiceProvider;
import cn.hybj.domain.HybjSpecial;
import cn.hybj.service.IHybjLogService;
import cn.hybj.service.IHybjSpecialService;
import cn.hybj.service.IHybjSystemDDlService;
import cn.hybj.web.form.HybjSpecialForm;
import cn.hybj.web.form.HybjSystemDDlForm;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class HybjSpecialAction extends BaseAction implements ModelDriven<HybjSpecialForm>{
	private IHybjSpecialService hybjSpecialService = (IHybjSpecialService)ServiceProvider.getService(IHybjSpecialService.SERVICE_NAME);

	//调用日志管理的业务层
	private IHybjLogService elecLogService = (IHybjLogService)ServiceProvider.getService(IHybjLogService.SERVICE_NAME);
	private IHybjSystemDDlService hybjSystemDDlService = (IHybjSystemDDlService)ServiceProvider.getService(IHybjSystemDDlService.SERVICE_NAME);

	private HybjSpecialForm hybjSpecialForm = new HybjSpecialForm();
	private HybjSpecial hybjSpecial;
	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public HybjSpecialForm getHybjSpecialForm() {
		return hybjSpecialForm;
	}

	public void setHybjSpecialForm(HybjSpecialForm hybjSpecialForm) {
		this.hybjSpecialForm = hybjSpecialForm;
	}

	public HybjSpecial getHybjSpecial() {
		return hybjSpecial;
	}

	public void setHybjSpecial(HybjSpecial hybjSpecial) {
		this.hybjSpecial = hybjSpecial;
	}

	//使用log4j
	Log log = LogFactory.getLog(HybjSpecialAction.class);
	public HybjSpecialForm getModel() {
		return hybjSpecialForm;
	}

   public String save(){
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String date = format.format(new Date());
	   hybjSpecial.setCreateTime(date);
	   //1 为提交后到聚合审批
	   hybjSpecial.setStatus("normal");
	   hybjSpecialService.save(hybjSpecial);

	   JSONObject json = getSuccessJsonTemplate();
	   json.put("message", "提交成功");
	   writeStream(json);
		return SUCCESS;
   }

	public String importSpecial(){
		List<HybjSpecial> list = hybjSpecialService.saveReportWithExcel(hybjSpecialForm);
		request.setAttribute("cpList", list);
		return "specialResult";

	}

	public String batchUpdate(){
		String[] idss = ids.split(",");
		HybjSpecial special = new HybjSpecial();
		for (String string : idss) {
			int id = Integer.parseInt(string);
			special.setId(id);
			special.setStatus("normal");
			hybjSpecialService.updateStatusById(special);
		}
		JSONObject json = getSuccessJsonTemplate();
		json.put("message", "批量上传成功");
		writeStream(json);
		return SUCCESS;

	}


	public String update(){
		int id = Integer.parseInt(ids);
		HybjSpecial special = new HybjSpecial();
		special.setStatus("normal");
		special.setId(id);
		hybjSpecialService.updateStatusById(special);
		JSONObject json = getSuccessJsonTemplate();
		json.put("message", "上传成功");
		writeStream(json);
		return SUCCESS;

	}

	public String findByFuzzy() throws UnsupportedEncodingException {
		List<HybjSystemDDlForm> jctList = hybjSystemDDlService.findElecSystemDDlListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
		byte[] bytes =hybjSpecial.getCp().getBytes("iso-8859-1");
		String cp = new String(bytes, "utf-8");
		byte[] bytes1 =hybjSpecial.getSpecialName().getBytes("iso-8859-1");
		String specialName = new String(bytes1, "utf-8");
		hybjSpecial.setCp(cp);
		hybjSpecial.setSpecialName(specialName);
		hybjSpecial.setStatus("PassAndFail");
		List<HybjSpecial> list = hybjSpecialService.findByCondition(hybjSpecial);
		request.setAttribute("ztList", list);
		return "alermZT";
	}


}
