package cn.hybj.web.action;


import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.hybj.container.ServiceProvider;
import cn.hybj.domain.HybjUser;
import cn.hybj.service.IHybjLogService;
import cn.hybj.service.IHybjUserService;
import cn.hybj.util.LogonUtils;
import cn.hybj.util.MD5keyBean;
import cn.hybj.web.form.HybjCommonMsgForm;
import cn.hybj.web.form.HybjMenuForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class HybjYqAction extends BaseAction implements ModelDriven<HybjMenuForm>{
	
	
	
	//调用日志管理的业务层
	private IHybjLogService elecLogService = (IHybjLogService)ServiceProvider.getService(IHybjLogService.SERVICE_NAME);
	private HybjMenuForm elecMenuForm = new HybjMenuForm();
	
	//使用log4j
	Log log = LogFactory.getLog(HybjYqAction.class);
	public HybjMenuForm getModel() {
		return elecMenuForm;
	}

	
	public String dx(){
		return "dx";
	}
	
	
}
