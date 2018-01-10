package cn.hybj.web.action;


import cn.hybj.container.ServiceProvider;
import cn.hybj.domain.HybjText;
import cn.hybj.service.IHybjTextService;
import cn.hybj.util.StringHelper;
import cn.hybj.web.form.HybjTextForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class HybjTextAction extends BaseAction implements ModelDriven<HybjTextForm>{

	private IHybjTextService elecTextService = (IHybjTextService)ServiceProvider.getService(IHybjTextService.SERVICE_NAME);
	
	private HybjTextForm elecTextForm = new HybjTextForm();
	
	public HybjTextForm getModel() {
		return elecTextForm;
	}
	/**  
	* @Name: save
	* @Description: 保存ElecText对象的方法
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: 跳转到system/textAdd.jsp
	*/
	public String save(){
		//System.out.println(elecTextForm.getTextName());
		//System.out.println(request.getParameter("textName"));
		//VO对象转换成PO对象
		//实例化PO对象
		HybjText elecText = new HybjText();
		elecText.setTextName(elecTextForm.getTextName());//测试名称
		elecText.setTextDate(StringHelper.stringConvertDate(elecTextForm.getTextDate()));//测试日期
		elecText.setTextRemark(elecTextForm.getTextRemark());//测试备注
		
		elecTextService.saveElecText(elecText);
		return "save";
	}

	
}
