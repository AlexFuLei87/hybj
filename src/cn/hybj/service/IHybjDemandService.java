package cn.hybj.service;


import cn.hybj.domain.HybjDemand;
import cn.hybj.web.form.HybjDemandForm;

import java.util.List;


public interface IHybjDemandService {
	public final static String SERVICE_NAME = "cn.hybj.service.impl.HybjDemandServiceImpl";


	int save(HybjDemandForm hybjDemandForm);

    List<HybjDemand> findXQByCp(String department);

	HybjDemand findById(int id);

	List<HybjDemand> findAll();

    void changeStatusById(Integer id, String status);
}
