package cn.hybj.web.action;


import java.util.List;

import cn.hybj.container.ServiceProvider;
import cn.hybj.service.IHybjLogService;
import cn.hybj.web.form.HybjLogForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class HybjLogAction extends BaseAction implements ModelDriven<HybjLogForm>{

	private IHybjLogService elecLogService = (IHybjLogService)ServiceProvider.getService(IHybjLogService.SERVICE_NAME);
	
	private HybjLogForm elecLogForm = new HybjLogForm();
	
	public HybjLogForm getModel() {
		return elecLogForm;
	}
	
	/**  
	* @Name: home
	* @Description: 查询日志列表信息
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: String home 跳转到logIndex.jsp
	*/
	public String home(){
		List<HybjLogForm> list = elecLogService.findElecLogListByCondition(elecLogForm);
		request.setAttribute("logList", list);
		return "home";
	}
	/**  
	* @Name: delete
	* @Description: 删除查询得到的日志列表信息
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: String delete 重定向到logIndex.jsp
	*/
	public String delete(){
		//第三种方式
		String logid [] = request.getParameterValues("logID");
		elecLogService.deleteElecLogByLogIDs(elecLogForm);
		return "delete";
	}
}
