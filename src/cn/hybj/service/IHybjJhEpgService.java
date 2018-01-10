package cn.hybj.service;

import java.util.List;

import cn.hybj.domain.HybjReport;
import cn.hybj.domain.HybjText;
import cn.hybj.web.form.HybjOutlineForm;
import cn.hybj.web.form.HybjReportForm;
import cn.hybj.web.form.HybjTextForm;

public interface IHybjJhEpgService {
	public final static String SERVICE_NAME = "cn.hybj.service.impl.HybjJhEpgServiceImpl";
	List<HybjReport> findByCondition(HybjReportForm hybjReportForm);
	void changeStatus(HybjReportForm hybjReportForm);
	void saveFeedback(HybjReportForm hybjReportForm);

    void saveGG(HybjOutlineForm hybjOutlineForm);
}
