package cn.hybj.dao.impl;

import org.springframework.stereotype.Repository;

import cn.hybj.dao.IHybjLogDao;
import cn.hybj.domain.HybjLog;

@Repository(IHybjLogDao.SERVICE_NAME)
public class HybjLogDaoImpl extends CommonDaoImpl<HybjLog> implements IHybjLogDao {
	
}
