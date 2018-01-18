package cn.hybj.web.action;


import cn.hybj.container.ServiceProvider;
import cn.hybj.domain.HybjSpecial;
import cn.hybj.service.IHybjLogService;
import cn.hybj.service.IHybjSpecialService;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class HybjSpecialAction extends BaseAction implements ModelDriven<HybjSpecial>{
	private IHybjSpecialService hybjSpecialService = (IHybjSpecialService)ServiceProvider.getService(IHybjSpecialService.SERVICE_NAME);

	//调用日志管理的业务层
	private IHybjLogService elecLogService = (IHybjLogService)ServiceProvider.getService(IHybjLogService.SERVICE_NAME);


	private HybjSpecial hybjSpecial;

	public HybjSpecial getHybjSpecial() {
		return hybjSpecial;
	}

	public void setHybjSpecial(HybjSpecial hybjSpecial) {
		this.hybjSpecial = hybjSpecial;
	}

	//使用log4j
	Log log = LogFactory.getLog(HybjSpecialAction.class);
	public HybjSpecial getModel() {
		return hybjSpecial;
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



}
