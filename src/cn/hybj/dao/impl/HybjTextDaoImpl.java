package cn.hybj.dao.impl;

import org.springframework.stereotype.Repository;

import cn.hybj.dao.IHybjTextDao;
import cn.hybj.domain.HybjText;

@Repository(IHybjTextDao.SERVICE_NAME)
public class HybjTextDaoImpl extends CommonDaoImpl<HybjText> implements IHybjTextDao {
	
}
