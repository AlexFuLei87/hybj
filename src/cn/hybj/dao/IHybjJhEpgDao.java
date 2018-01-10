package cn.hybj.dao;

import java.util.List;

import cn.hybj.domain.HybjReport;
import cn.hybj.web.form.HybjReportForm;

public interface IHybjJhEpgDao extends ICommonDao<HybjReport> {
	public final static String SERVICE_NAME = "cn.hybj.dao.impl.HybjJhEpgDaoImpl";

	List<HybjReport> findByCondition(HybjReportForm hybjReportForm);

	void changeStatus(HybjReportForm hybjReportForm);

	void saveFeedback(HybjReportForm hybjReportForm);

}
