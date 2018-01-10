package cn.hybj.service;

import java.util.List;

import cn.hybj.web.form.HybjCommonMsgForm;


public interface IHybjCommonMsgService {
	public final static String SERVICE_NAME = "cn.hybj.service.impl.HybjCommonMsgServiceImpl";

	List<HybjCommonMsgForm> findCommonMsgList();

	void saveCommonMsg(HybjCommonMsgForm hybjCommonMsgForm);

	List<HybjCommonMsgForm> findCommonMsgListByCurrentDate();
}
