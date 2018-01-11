package cn.hybj.web.action;


import java.io.UnsupportedEncodingException;
import java.util.List;

import cn.hybj.domain.HybjDemand;
import cn.hybj.domain.HybjReport;
import cn.hybj.domain.HybjUser;
import cn.hybj.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.hybj.container.ServiceProvider;
import cn.hybj.web.form.HybjMenuForm;
import cn.hybj.web.form.HybjSystemDDlForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class HybjShowAction extends BaseAction implements ModelDriven<HybjMenuForm>{
	

	
	private IHybjSystemDDlService hybjSystemDDlService = (IHybjSystemDDlService)ServiceProvider.getService(IHybjSystemDDlService.SERVICE_NAME);
	private IHybjDemandService hybjDemandService = (IHybjDemandService)ServiceProvider.getService(IHybjDemandService.SERVICE_NAME);


	private IHybjReportService hybjReportService = (IHybjReportService)ServiceProvider.getService(IHybjReportService.SERVICE_NAME);

	//调用日志管理的业务层
	private IHybjLogService elecLogService = (IHybjLogService)ServiceProvider.getService(IHybjLogService.SERVICE_NAME);
	private HybjMenuForm elecMenuForm = new HybjMenuForm();
	
	//使用log4j
	Log log = LogFactory.getLog(HybjShowAction.class);
	public HybjMenuForm getModel() {
		return elecMenuForm;
	}

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void initSelect(){
		List<HybjSystemDDlForm> isChargeList = hybjSystemDDlService.findElecSystemDDlListByKeyword("是否收费");
		List<HybjSystemDDlForm> jctList = hybjSystemDDlService.findElecSystemDDlListByKeyword("所属单位");
		List<HybjSystemDDlForm> isjhList = hybjSystemDDlService.findElecSystemDDlListByKeyword("是否同步聚合");
		request.setAttribute("isChargeList", isChargeList);
		request.setAttribute("jctList", jctList);
		request.setAttribute("isjhList", isjhList);
	}
	public String hs(){
		initSelect();
		request.setAttribute("department", "华数TV");
		return "hs";
	}

	public String mg(){
		initSelect();
		request.setAttribute("department", "芒果TV");
		return "mg";
	}

	public String aqy(){
		initSelect();
		return "aqy";
	}

	public String bst(){
		initSelect();
		request.setAttribute("department", "百视通");
		return "bst";
	}

	public String yt(){
		initSelect();
		request.setAttribute("department", "优酷");
		return "yt";
	}

	public String yp(){
		initSelect();
		request.setAttribute("department", "优朋");
		return "yp";
	}

	public String tx(){
		initSelect();
		request.setAttribute("department", "腾讯");
		return "tx";
	}
	public String tysx(){
		initSelect();
		request.setAttribute("department", "天翼视讯");
		return "tysx";
	}
	public String xq(){
		HybjUser user = (HybjUser)request.getSession().getAttribute("globle_user");
		System.out.println(user.getDepartment());
		List<HybjDemand> lists = hybjDemandService.findXQByCp(user.getDepartment());
		request.setAttribute("xqList",lists);
		return "xq";
		}
	public String demandAdd(){
			return "demandAdd";
		}


	public String importpage() throws Exception {
		String name=request.getParameter("cpName");
		byte[] bytes =name.getBytes("iso-8859-1");
		name = new String(bytes, "utf-8");
		request.setAttribute("department", name);
		return "fileImport";
	}
	public String reportAdd() throws Exception{
		String name=request.getParameter("cpName");
		byte[] bytes =name.getBytes("iso-8859-1");
		name = new String(bytes, "utf-8");
		initSelect();
		request.setAttribute("department", name);
		return "reportAdd";
	}

	public String showCG() throws Exception{

		 HybjReport report = hybjReportService.getItemById(Integer.parseInt(id));
		request.setAttribute("report", report);
		initSelect();
		return "showCG";
	}


}
