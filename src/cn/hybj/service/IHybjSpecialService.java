package cn.hybj.service;


import cn.hybj.domain.HybjDemand;
import cn.hybj.domain.HybjSpecial;
import cn.hybj.web.form.HybjDemandForm;
import cn.hybj.web.form.HybjSpecialForm;

import java.util.List;


public interface IHybjSpecialService {
	public final static String SERVICE_NAME = "cn.hybj.service.impl.HybjSpecialServiceImpl";


    void save(HybjSpecial hybjSpecial);

    List<HybjSpecial> findByCondition(HybjSpecial hybjSpecial);

    void updateStatus(HybjSpecial hybjSpecial);

    void updatefeedback(HybjSpecial hybjSpecial);

    List<HybjSpecial> saveReportWithExcel(HybjSpecialForm hybjSpecial);

    void updateStatusById(HybjSpecial special);

    List<HybjSpecial> findPassAndFail();
}
