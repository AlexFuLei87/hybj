package cn.hybj.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.hybj.dao.IHybjRolePopedomDao;
import cn.hybj.dao.IHybjUserRoleDao;
import cn.hybj.domain.HybjRolePopedom;
import cn.hybj.domain.HybjUserRole;
import cn.hybj.service.IHybjRoleService;
import cn.hybj.util.XmlObject;
import cn.hybj.web.form.HybjRoleForm;
import cn.hybj.web.form.HybjUserForm;
@Transactional(readOnly=true)
@Service(IHybjRoleService.SERVICE_NAME)
public class HybjRoleServiceImpl implements IHybjRoleService {
	
	@Resource(name=IHybjUserRoleDao.SERVICE_NAME)
	private IHybjUserRoleDao elecUserRoleDao;

	@Resource(name=IHybjRolePopedomDao.SERVICE_NAME)
	private IHybjRolePopedomDao elecRolePopedomDao;

	/**  
	* @Name: readXml
	* @Description: 从Function.xml文件中查询系统所有的功能权限
	*               存放到XmlObject对象中
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: List<XmlObject> 权限的集合
	*/
	public List<XmlObject> readXml() {
		
		ServletContext servletContext = ServletActionContext.getServletContext();
		String realPath = servletContext.getRealPath("/WEB-INF/classes/Function.xml");
		File f = new File(realPath);
		List<XmlObject> xmlList = new ArrayList<XmlObject>();
		//使用dom4j读取配置文件
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(f);
			Element element = document.getRootElement();
			XmlObject xmlObject = null;
			/**
			 * Function:对应配置文件中的Function元素节点
			 * FunctionCode：对应配置文件中Function元素节点下的FunctionCode元素节点
			 * FunctionName：对应配置文件中Function元素节点下的FunctionName元素节点
			 * ParentCode：对应配置文件中Function元素节点下的ParentCode元素节点
			 * ParentName：对应配置文件中Function元素节点下的ParentName元素节点
			 */
			for(Iterator<Element> iter = element.elementIterator("Function");iter.hasNext();){
				Element xmlElement = iter.next();
				xmlObject = new XmlObject();
				xmlObject.setCode(xmlElement.elementText("FunctionCode"));
				xmlObject.setName(xmlElement.elementText("FunctionName"));
				xmlObject.setParentCode(xmlElement.elementText("ParentCode"));
				xmlObject.setParentName(xmlElement.elementText("ParentName"));
				xmlList.add(xmlObject);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return xmlList;
	}

	/**  
	* @Name: readEditXml
	* @Description: 使用角色ID查询该角色下具有的权限，并与系统中所有的权限进行匹配
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: String roleID 角色ID
	* @Return: List<XmlObject> 权限集合（匹配完成）
	*/
	public List<XmlObject> readEditXml(String roleID) {
		//使用roleID查询该角色下具有的权限
		HybjRolePopedom elecRolePopedom = elecRolePopedomDao.findObjectByID(roleID);
		String popedom = "";
		if(elecRolePopedom!=null){
			popedom = elecRolePopedom.getPopedomcode();
		}
		//与系统中所有的权限进行匹配
		List<XmlObject> list = this.readXmlByPopedom(popedom);
		return list;
	}
	/**  
	* @Name: readXmlByPopedom
	* @Description: 读取配置文件，获取系统中所有的权限，与当前角色具有的权限进行匹配
	*               * 如果匹配不成功，设置flag = 0，表示该角色不具有的权限，则页面中权限复选框不被选中
	*               * 如果匹配成功，设置flag = 1，表示该角色具有此权限，则页面中的权限复选框被选中
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: String popedom 权限集合
	* @Return: List<XmlObject> 权限集合（匹配完成）
	*/
	private List<XmlObject> readXmlByPopedom(String popedom) {
		List<XmlObject> list = new ArrayList<XmlObject>();
		List<XmlObject> xmlList = this.readXml();
		for(int i=0;xmlList!=null && i<xmlList.size();i++){
			XmlObject object = xmlList.get(i);
			//表示当前权限被选中
			if(popedom.contains(object.getCode())){
				object.setFlag("1");
			}
			else{
				object.setFlag("0");
			}
			list.add(object);
		}
		return list;
	}
	/**  
	* @Name: findElecUserListByRoleID
	* @Description: 查询用户列表集合，获取系统中所有的用户，并与该角色具有的用户进行匹配
	*               * 如果匹配不成功，设置flag = 0，表示该角色不拥有的用户，则页面中用户复选框不被选中
	*               * 如果匹配成功，设置flag = 1，表示该角色拥有此用户，则页面中的用户复选框被选中
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: String roleID 角色ID
	* @Return: List<ElecUserForm> 用户集合（匹配完成）
	*/
	public List<HybjUserForm> findElecUserListByRoleID(String roleID) {
		List<Object []> list = elecUserRoleDao.findUserListByRoleID(roleID);
		List<HybjUserForm> formList = this.elecUserRoleObjectListToVOList(list);
		return formList;
	}
	/**  
	* @Name: elecUserRoleObjectListToVOList
	* @Description:将获取到的用户列表信息从Object对象转换成VO对象
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: List<Object[]> list Ojbect集合对象
	* @Return: List<ElecUserForm> VO集合对象
	*/
	private List<HybjUserForm> elecUserRoleObjectListToVOList(
			List<Object[]> list) {
		List<HybjUserForm> formList = new ArrayList<HybjUserForm>();
		HybjUserForm elecUserForm = null;
		for(int i=0;list!=null && i<list.size();i++){
			Object[] objects = list.get(i);
			elecUserForm = new HybjUserForm();
			elecUserForm.setFlag(objects[0].toString());
			elecUserForm.setUserID(objects[1].toString());
			elecUserForm.setUserName(objects[2].toString());
			elecUserForm.setLogonName(objects[3].toString());
			formList.add(elecUserForm);
		}
		return formList;
	}
	/**  
	* @Name: saveRole
	* @Description:保存角色和权限的关联表
	*              保存用户和角色的关联表
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: ElecRoleForm elecRoleForm VO对象，存放角色ID、权限code数组、用户ID数组
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveRole(HybjRoleForm elecRoleForm) {
		//保存角色和权限的关联表
		this.saveRolePopedom(elecRoleForm);
		//保存用户和角色的关联表
		this.saveUserRole(elecRoleForm);
	}

	/**  
	* @Name: saveRole
	* @Description:保存角色和权限的关联表
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: ElecRoleForm elecRoleForm VO对象，存放角色ID、权限code数组
	* @Return: 无
	*/
	private void saveRolePopedom(HybjRoleForm elecRoleForm) {
		//角色ID
		String roleid = elecRoleForm.getRoleid();
		//权限code结合
		String [] selectoper = elecRoleForm.getSelectoper();
		StringBuffer popedom = new StringBuffer("");
		for(int i=0;selectoper!=null && i<selectoper.length;i++){
			popedom.append(selectoper[i]);
		}
		//使用角色ID查询角色和权限的关联表
		HybjRolePopedom elecRolePopedom = elecRolePopedomDao.findObjectByID(roleid);
		//说明角色和权限关联表中存在该角色的记录，此时执行update的操作
		if(elecRolePopedom!=null){
			elecRolePopedom.setPopedomcode(popedom.toString());
			elecRolePopedomDao.update(elecRolePopedom);
		}
		//说明角色和权限关联表中不存在该角色的记录，此时执行save的操作
		else{
			elecRolePopedom = new HybjRolePopedom();
			elecRolePopedom.setRoleID(roleid);
			elecRolePopedom.setPopedomcode(popedom.toString());
			elecRolePopedomDao.save(elecRolePopedom);
		}
	}
	/**  
	* @Name: saveUserRole
	* @Description:保存用户和角色的关联表
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: ElecRoleForm elecRoleForm VO对象，存放角色ID、用户ID数组
	* @Return: 无
	*/
	private void saveUserRole(HybjRoleForm elecRoleForm) {
		//角色ID
		String roleid = elecRoleForm.getRoleid();
		//用户ID数组
		String [] selectuser = elecRoleForm.getSelectuser();
		/**
		 * 以roleID作为条件，查询用户和角色的关联表，获取用户和角色关联的集合对象
		 */
		String hqlWhere = " and o.roleID = ?";
		Object [] params = {roleid};
		List<HybjUserRole> entities = elecUserRoleDao.findCollectionByConditionNoPage(hqlWhere, params, null);
		/**
		 * 以roleID作为条件，删除用户和角色的关联表
		 */
		elecUserRoleDao.deleteObjectByCollection(entities);
		//新增用户和角色的关联表
		List<HybjUserRole> list = new ArrayList<HybjUserRole>();
		for(int i=0;selectuser!=null && i<selectuser.length;i++){
			String userID = selectuser[i];
			HybjUserRole elecUserRole = new HybjUserRole();
			elecUserRole.setUserID(userID);
			elecUserRole.setRoleID(roleid);
			list.add(elecUserRole);
			//elecUserRoleDao.save(elecUserRole);
		}
		elecUserRoleDao.saveObjectByCollection(list);
	}
}
