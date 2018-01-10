package cn.hybj.web.action;


import java.util.List;

import cn.hybj.container.ServiceProvider;
import cn.hybj.service.IHybjSystemDDlService;
import cn.hybj.web.form.HybjSystemDDlForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class HybjSystemDDlAction extends BaseAction implements ModelDriven<HybjSystemDDlForm>{

	private IHybjSystemDDlService elecSystemDDlService = (IHybjSystemDDlService)ServiceProvider.getService(IHybjSystemDDlService.SERVICE_NAME);
	
	private HybjSystemDDlForm elecSystemDDlForm  = new HybjSystemDDlForm();
	
	public HybjSystemDDlForm getModel() {
		return elecSystemDDlForm;
	}
	
	/**  
	* @Name: home
	* @Description: 查询数据类型，且去掉重复值
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: String home 跳转到dictionaryIndex.jsp
	*/
	public String home(){
		List<HybjSystemDDlForm> list = elecSystemDDlService.findKeyWord();
		request.setAttribute("systemList", list);
		return "home";
	}
	
	/**  
	* @Name: edit
	* @Description: 根据选中数据类型，跳转到编辑此数据类型的页面
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2011-12-25 （创建日期）
	* @Parameters: 无
	* @Return: String edit 跳转到dictionaryEdit.jsp
	*/
	public String edit(){
		//获取数据类型
		String keyword = elecSystemDDlForm.getKeyword();
		List<HybjSystemDDlForm> list = elecSystemDDlService.findElecSystemDDlListByKeyword(keyword);
		request.setAttribute("systemList", list);
		return "edit";
	}
	/**  
	* @Name: save
	* @Description: 保存数据字典
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: String save 重定向到dictionaryIndex.jsp
	*/
	public String save(){
		elecSystemDDlService.saveElecSystemDDl(elecSystemDDlForm);
		return "save";
	}
}
