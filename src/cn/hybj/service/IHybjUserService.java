package cn.hybj.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.hybj.domain.HybjUser;
import cn.hybj.web.form.HybjUserForm;



public interface IHybjUserService {
	public final static String SERVICE_NAME = "cn.hybj.service.impl.HybjUserServiceImpl";

	List<HybjUserForm> findElecUserList(HybjUserForm hybjUserForm,HttpServletRequest request);

	void saveElecUser(HybjUserForm hybjUserForm);

	HybjUserForm findElecUser(HybjUserForm hybjUserForm);

	void deleteElecUser(HybjUserForm hybjUserForm);

	String checkLogonName(String logonName);

	HybjUser findElecUserByLogonName(String name);

	String findElecPopedomByLogonName(String name);

	Hashtable<String, String> findElecRoleByLogonName(String name);

	ArrayList getExcelFiledNameList();

	ArrayList getExcelFiledDataList(HybjUserForm hybjUserForm);

	void saveElecUserWithExcel(HybjUserForm hybjUserForm);

	List<HybjUserForm> findUserByChart();

}
