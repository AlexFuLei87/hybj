package cn.hybj.web.action;


import cn.hybj.container.ServiceProvider;
import cn.hybj.domain.HybjSpecial;
import cn.hybj.domain.HybjUser;
import cn.hybj.service.IHybjLogService;
import cn.hybj.service.IHybjSpecialService;
import cn.hybj.service.IHybjSystemDDlService;
import cn.hybj.web.form.HybjSpecialForm;
import cn.hybj.web.form.HybjSystemDDlForm;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
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


	public String uploadImages() throws Exception{
	 	File file = hybjSpecialForm.getFile();
	 	if(file != null){
			InputStream in = new FileInputStream(file);
			String path = request.getSession().getServletContext().getRealPath("ztImages");
			File file1 = new File(path);
			if(!file1.exists()){
				file1.mkdirs();
			}
			String saveFileName = System.currentTimeMillis() + hybjSpecialForm.getFileFileName().substring(hybjSpecialForm.getFileFileName().indexOf("."));
			File realFile = new File(path,saveFileName);
			try {
				FileUtils.copyFile(file, realFile);
				HybjSpecial special = new HybjSpecial();
				special.setAttachmnetUrl(path+"/"+saveFileName);
				special.setAttachmentName(hybjSpecialForm.getFileFileName());
				special.setId(hybjSpecialForm.getId());
				hybjSpecialService.updateById(special);
			} catch (IOException e) {
				e.printStackTrace();
			}

	 	}
		return "drawing";
	}


	public String completeDraw(){
		HybjUser user = (HybjUser)request.getSession().getAttribute("globle_user");
		HybjSpecial special = new HybjSpecial();
		special.setDrawPart(user.getDepartment());
		special.setAttachmentName("isNotNull");
		special.setStatus("pass");
		List<HybjSpecial> list = hybjSpecialService.findDrawPart(special);
		request.setAttribute("ztList",list);
		return "completeDraw";
	}
}
