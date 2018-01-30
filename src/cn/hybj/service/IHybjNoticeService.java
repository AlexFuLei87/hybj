package cn.hybj.service;


import cn.hybj.domain.HybjOutline;
import cn.hybj.web.form.HybjOutlineForm;

import java.util.List;

public interface IHybjNoticeService {
	public final static String SERVICE_NAME = "cn.hybj.service.impl.HybjNoticeServiceImpl";


	List<HybjOutline> findAll();

	List<HybjOutline> findGG();

	void changeStatus(HybjOutlineForm hybjOutlineForm);

    HybjOutline findById(Integer id);

    void deleteById(String id);

	List<HybjOutline> findByCondition(HybjOutline outline);
}
