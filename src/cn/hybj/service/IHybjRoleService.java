package cn.hybj.service;

import java.util.List;

import cn.hybj.util.XmlObject;
import cn.hybj.web.form.HybjRoleForm;
import cn.hybj.web.form.HybjUserForm;




public interface IHybjRoleService {
	public final static String SERVICE_NAME = "cn.hybj.service.impl.HybjRoleServiceImpl";

	List<XmlObject> readXml();

	List<XmlObject> readEditXml(String roleID);

	List<HybjUserForm> findElecUserListByRoleID(String roleID);

	void saveRole(HybjRoleForm elecRoleForm);

}
