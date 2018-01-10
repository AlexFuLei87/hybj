package cn.hybj.web.action;


import java.util.List;

import cn.hybj.container.ServiceProvider;
import cn.hybj.service.IHybjCommonMsgService;
import cn.hybj.web.form.HybjCommonMsgForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class HybjCommonMsgAction extends BaseAction implements ModelDriven<HybjCommonMsgForm>{

	private IHybjCommonMsgService hybjCommonMsgService = (IHybjCommonMsgService)ServiceProvider.getService(IHybjCommonMsgService.SERVICE_NAME);
	
	private HybjCommonMsgForm hybjCommonMsgForm = new HybjCommonMsgForm();
	
	public HybjCommonMsgForm getModel() {
		return hybjCommonMsgForm;
	}
	
	/**  
	* @Name: home
	* @Description: 查询代办事宜列表
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: String home 跳转到actingIndex.jsp
	*/
	public String home(){
		List<HybjCommonMsgForm> list = hybjCommonMsgService.findCommonMsgList();
		request.setAttribute("commonList", list);
		return "home";
	}
	/**  
	* @Name: save
	* @Description: 保存代办事宜信息
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: String save 重定向到actingIndex.jsp
	*/
	public String save(){
		hybjCommonMsgService.saveCommonMsg(hybjCommonMsgForm);
		return "save";
	}
	
}
