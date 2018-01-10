package cn.hybj.dao;

import java.util.List;

import cn.hybj.domain.HybjUser;

public interface IHybjUserDao extends ICommonDao<HybjUser> {
	public final static String SERVICE_NAME = "cn.hybj.dao.impl.ElecUserDaoImpl";

	List<Object> findElecPopedomByLogonName(String name);

	List<Object[]> findRoleByLogonName(String name);

	List<Object[]> findUserByChart();

}
