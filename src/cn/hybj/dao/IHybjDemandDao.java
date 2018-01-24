package cn.hybj.dao;

import cn.hybj.domain.HybjDemand;

import java.util.List;


public interface IHybjDemandDao extends ICommonDao<HybjDemand> {
	public final static String SERVICE_NAME = "cn.hybj.dao.impl.HybjDemandDaoImpl";


	List<HybjDemand> findXQByCp(String department);

    List<HybjDemand> findAll();

    void changeStatusById(int id,String status);

    List<HybjDemand> findByFuzzy(HybjDemand demand);

    void changeTowhoById(Integer id, String towho);
}
