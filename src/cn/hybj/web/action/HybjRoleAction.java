package cn.hybj.web.action;


import java.util.List;

import cn.hybj.container.ServiceProvider;
import cn.hybj.service.IHybjRoleService;
import cn.hybj.service.IHybjSystemDDlService;
import cn.hybj.util.XmlObject;
import cn.hybj.web.form.HybjRoleForm;
import cn.hybj.web.form.HybjSystemDDlForm;
import cn.hybj.web.form.HybjUserForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class HybjRoleAction extends BaseAction implements ModelDriven<HybjRoleForm>{

	private IHybjRoleService elecRoleService = (IHybjRoleService)ServiceProvider.getService(IHybjRoleService.SERVICE_NAME);
	
	private IHybjSystemDDlService elecSystemDDlService = (IHybjSystemDDlService)ServiceProvider.getService(IHybjSystemDDlService.SERVICE_NAME);
	
	private HybjRoleForm elecRoleForm = new HybjRoleForm();
	
	public HybjRoleForm getModel() {
		return elecRoleForm;
	}
	
	/**  
	* @Name: home
	* @Description: 查询所有的角色类型（在数据字典表中获取）
	*               从Function.xml文件中查询系统所有的功能权限
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2011-12-27 （创建日期）
	* @Parameters: 无
	* @Return: String home 跳转到roleIndex.jsp
	*/
	public String home(){
		//获取所有的角色类型
		List<HybjSystemDDlForm> systemList = elecSystemDDlService.findElecSystemDDlListByKeyword("角色类型");
		request.setAttribute("systemList", systemList);
		//从Function.xml配置文件中获取权限的集合
		List<XmlObject> xmlList = elecRoleService.readXml();
		request.setAttribute("xmlList", xmlList);
		return "home";
	}
	
	/**  
	* @Name: edit
	* @Description: 1、使用角色ID查询该角色下具有的权限，并与系统中所有的权限进行匹配
	*               2、使用角色ID查询该角色所拥有的用户
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: String edit 跳转到roleEdit.jsp
	*/
	public String edit(){
		String roleID = elecRoleForm.getRole();
		//查询权限集合
		List<XmlObject> xmlList = elecRoleService.readEditXml(roleID);
		request.setAttribute("xmlList", xmlList);
		//查询用户集合
		List<HybjUserForm> userList = elecRoleService.findElecUserListByRoleID(roleID);
		request.setAttribute("userList", userList);
		return "edit";
	}
	/**  
	* @Name: save
	* @Description: 执行保存，保存角色和权限的关联表
	*                        保存用户和角色的关联表
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: String save 重定向到roleIndex.jsp
	*/
	public String save(){
		elecRoleService.saveRole(elecRoleForm);
		return "save";
	}
}
