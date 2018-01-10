package cn.hybj.dao;

import java.util.List;

import cn.hybj.domain.HybjUserRole;

public interface IHybjUserRoleDao extends ICommonDao<HybjUserRole> {
	public final static String SERVICE_NAME = "cn.hybj.dao.impl.ElecUserRoleDaoImpl";

	List<Object[]> findUserListByRoleID(String roleID);
}
