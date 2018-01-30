package cn.hybj.dao;

import cn.hybj.domain.HybjOutline;
import cn.hybj.web.form.HybjOutlineForm;

import java.util.List;


public interface IHybjNoticeDao extends ICommonDao<HybjOutline> {
	public final static String SERVICE_NAME = "cn.hybj.dao.impl.HybjNoticeDaoImpl";
	List<HybjOutline> findAll();

	List<HybjOutline> findGG();

	void changeStatus(HybjOutlineForm hybjOutlineForm);

    List<HybjOutline> findByCondition(HybjOutline outline);
}
