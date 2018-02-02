package cn.hybj.web.action;


import java.io.UnsupportedEncodingException;
import java.util.List;

import cn.hybj.domain.HybjDemand;
import cn.hybj.domain.HybjReport;
import cn.hybj.domain.HybjSpecial;
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
	private IHybjSpecialService hybjSpecialService = (IHybjSpecialService)ServiceProvider.getService(IHybjSpecialService.SERVICE_NAME);

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
		HybjUser user = (HybjUser)request.getSession().getAttribute("globle_user");
		request.setAttribute("department", user.getDepartment());
		return "hs";
	}
	public String zt(){
		initSelect();
		HybjUser user = (HybjUser)request.getSession().getAttribute("globle_user");
		request.setAttribute("department", user.getDepartment());
		HybjSpecial hybjSpecial = new HybjSpecial();
		hybjSpecial.setCp(user.getDepartment());
		List<HybjSpecial> ztList = hybjSpecialService.findByCondition(hybjSpecial);
		request.setAttribute("ztList", ztList);
		return "zt";
	}


	public String xq(){
		HybjUser user = (HybjUser)request.getSession().getAttribute("globle_user");
		System.out.println(user.getDepartment());
		List<HybjDemand> lists = hybjDemandService.findXQByCp(user.getDepartment());
		request.setAttribute("xqList",lists);
		return "xq";
		}
	public String demandAdd(){
		HybjUser user = (HybjUser)request.getSession().getAttribute("globle_user");
		request.setAttribute("department",user.getDepartment());
		return "demandAdd";
		}


	public String importpage() throws Exception {
		String name=request.getParameter("cpName");
		byte[] bytes =name.getBytes("iso-8859-1");
		name = new String(bytes, "utf-8");
		request.setAttribute("department", name);
		return "fileImport";
		}
	public String specialImport() throws Exception {
		HybjUser user = (HybjUser)request.getSession().getAttribute("globle_user");
		request.setAttribute("department",user.getDepartment());
		return "specialImport";
		}

	public String importOffpage() throws Exception {
		String name=request.getParameter("cpName");
		byte[] bytes =name.getBytes("iso-8859-1");
		name = new String(bytes, "utf-8");
		request.setAttribute("department", name);
		return "fileImportOff";
	}
	public String reportAdd() throws Exception{
		String name=request.getParameter("cpName");
		byte[] bytes =name.getBytes("iso-8859-1");
		name = new String(bytes, "utf-8");
		initSelect();
		request.setAttribute("department", name);
		return "reportAdd";
	}
	public String specialAdd() {
		HybjUser user = (HybjUser)request.getSession().getAttribute("globle_user");
		request.setAttribute("department",user.getDepartment());
		return "specialAdd";
	}
	public String reportOffAdd() throws Exception{
		String name=request.getParameter("cpName");
		byte[] bytes =name.getBytes("iso-8859-1");
		name = new String(bytes, "utf-8");
		initSelect();
		request.setAttribute("department", name);
		return "reportOffAdd";
	}

	public String showCG() throws Exception{

		 HybjReport report = hybjReportService.getItemById(Integer.parseInt(id));
		request.setAttribute("report", report);
		initSelect();
		return "showCG";
	}
	public String drawing() throws Exception{
		HybjUser user = (HybjUser)request.getSession().getAttribute("globle_user");
		HybjSpecial special = new HybjSpecial();
		special.setDrawPart(user.getDepartment());
		special.setAttachmentName("isNull");
		special.setStatus("pass");
		List<HybjSpecial> list = hybjSpecialService.findDrawPart(special);
		request.setAttribute("ztList",list);
		return "drawing";
		}


}
