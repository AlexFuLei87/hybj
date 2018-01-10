package cn.hybj.dao.impl;

import cn.hybj.dao.IHybjDemandDao;
import cn.hybj.dao.IHybjNoticeDao;
import cn.hybj.data.DataBaseUtil;
import cn.hybj.data.HibernateSessionFactory;
import cn.hybj.domain.HybjDemand;
import cn.hybj.domain.HybjOutline;
import cn.hybj.web.form.HybjOutlineForm;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(IHybjDemandDao.SERVICE_NAME)
public class HybjDemandDaoImpl extends CommonDaoImpl<HybjDemand> implements IHybjDemandDao {



}
