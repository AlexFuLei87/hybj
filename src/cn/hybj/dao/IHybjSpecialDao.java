package cn.hybj.dao;

import cn.hybj.domain.HybjDemand;
import cn.hybj.domain.HybjSpecial;

import java.util.List;


public interface IHybjSpecialDao extends ICommonDao<HybjSpecial> {
	public final static String SERVICE_NAME = "cn.hybj.dao.impl.HybjSpecialDaoImpl";


    List<HybjSpecial> findBycondition(HybjSpecial hybjSpecial);

    void updateStatus(HybjSpecial hybjSpecial);

    void updataFeedback(HybjSpecial hybjSpecial);


    List<HybjSpecial> findByPassAndFail();

    List<HybjSpecial> findDrawPart(HybjSpecial special);

    void updateById(HybjSpecial hybjSpecial);
}
