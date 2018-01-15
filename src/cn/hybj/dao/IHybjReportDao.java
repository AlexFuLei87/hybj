package cn.hybj.dao;

import java.util.List;

import cn.hybj.domain.HybjReport;

public interface IHybjReportDao extends ICommonDao<HybjReport> {
	public final static String SERVICE_NAME = "cn.hybj.dao.impl.HybjReportDaoImpl";

	int findReportByItemName(String itemName);

	List<HybjReport> findPassOrFail();

	List<HybjReport> findByStatusAndCp(String status, String cp);

	List<HybjReport> findOtherCpPsss(String name);

	void updateStatusById(int id);

	void updateById(int id, String status);

    List<HybjReport> findByFuzzy(HybjReport hybjReport);
}
