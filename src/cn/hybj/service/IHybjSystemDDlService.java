package cn.hybj.service;

import java.util.List;

import cn.hybj.web.form.HybjSystemDDlForm;



public interface IHybjSystemDDlService {
	public final static String SERVICE_NAME = "cn.hybj.service.impl.HybjSystemDDlServiceImpl";

	List<HybjSystemDDlForm> findKeyWord();

	List<HybjSystemDDlForm> findElecSystemDDlListByKeyword(String keyword);

	void saveElecSystemDDl(HybjSystemDDlForm hybjSystemDDlForm);

}
