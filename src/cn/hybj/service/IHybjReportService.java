package cn.hybj.service;

import java.util.List;

import cn.hybj.domain.HybjReport;
import cn.hybj.web.form.HybjReportForm;







public interface IHybjReportService {
	public final static String SERVICE_NAME = "cn.hybj.service.impl.HybjReportServiceImpl";

	void saveReport(HybjReportForm hybjReportForm);

	int findReportByItemName(String itemName);

	List<HybjReport> findPassOrFail();

	List<HybjReport> findReprotByStatusAndCp(String status,String cp);

	List<HybjReport> saveReportWithExcel(HybjReportForm hybjReportForm);

	List<HybjReport> findOtherCpPsss(String name);

	void updateStatusById(int id);


	void updateById(int id, String status);


	HybjReport getItemById(int i);

    void updateReport(HybjReportForm hybjReportForm);


	List<HybjReport> findByFuzzy(HybjReport hybjReport);
}
