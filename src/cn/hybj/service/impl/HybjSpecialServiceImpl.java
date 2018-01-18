package cn.hybj.service.impl;

import cn.hybj.dao.IHybjDemandDao;
import cn.hybj.dao.IHybjSpecialDao;
import cn.hybj.domain.HybjDemand;
import cn.hybj.domain.HybjSpecial;
import cn.hybj.service.IHybjDemandService;
import cn.hybj.service.IHybjSpecialService;
import cn.hybj.web.form.HybjDemandForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional(readOnly=true)
@Service(IHybjSpecialService.SERVICE_NAME)
public class HybjSpecialServiceImpl implements IHybjSpecialService {

	@Resource(name= IHybjSpecialDao.SERVICE_NAME)
	private IHybjSpecialDao hybjSpecialDao;

	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void save(HybjSpecial hybjSpecial) {
		hybjSpecialDao.save(hybjSpecial);
	}

	@Override
	public List<HybjSpecial> findByCondition(HybjSpecial hybjSpecial) {
		return hybjSpecialDao.findBycondition(hybjSpecial);
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void updateStatus(HybjSpecial hybjSpecial) {
		hybjSpecialDao.updateStatus(hybjSpecial);
	}

	@Override
	public void updatefeedback(HybjSpecial hybjSpecial) {
		hybjSpecialDao.updataFeedback(hybjSpecial);
	}
}
