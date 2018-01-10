package cn.hybj.service;


import cn.hybj.web.form.HybjDemandForm;


public interface IHybjDemandService {
	public final static String SERVICE_NAME = "cn.hybj.service.impl.HybjDemandServiceImpl";


	int save(HybjDemandForm hybjDemandForm);
}
