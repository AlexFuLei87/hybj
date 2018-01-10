package cn.hybj.dao;

import java.util.List;

import cn.hybj.domain.HybjSystemDDl;

public interface IHybjSystemDDlDao extends ICommonDao<HybjSystemDDl> {
	public final static String SERVICE_NAME = "cn.hybj.dao.impl.HybjSystemDDlDaoImpl";

	List<Object> findKeyWord();

	String findDDlName(String ddlCode, String keyword);

}
