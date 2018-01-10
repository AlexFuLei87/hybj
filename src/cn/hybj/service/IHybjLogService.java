package cn.hybj.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.hybj.web.form.HybjLogForm;




public interface IHybjLogService {
	public final static String SERVICE_NAME = "cn.hybj.service.impl.HybjLogServiceImpl";

	void saveElecLog(HttpServletRequest request, String details);

	List<HybjLogForm> findElecLogListByCondition(HybjLogForm elecLogForm);

	void deleteElecLogByLogIDs(HybjLogForm elecLogForm);

}
