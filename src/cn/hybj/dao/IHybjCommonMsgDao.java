package cn.hybj.dao;

import java.util.List;

import cn.hybj.domain.HybjCommonMsg;

public interface IHybjCommonMsgDao extends ICommonDao<HybjCommonMsg> {
	public final static String SERVICE_NAME = "cn.hybj.dao.impl.HybjCommonMsgDaoImpl";

	List<Object[]> findCommonMsgListByCurrentDate(String currentDate);

}
