package cn.hybj.service.impl;

import cn.hybj.dao.IHybjDemandDao;
import cn.hybj.domain.HybjDemand;
import cn.hybj.service.IHybjDemandService;
import cn.hybj.web.form.HybjDemandForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;

@Transactional(readOnly=true)
@Service(IHybjDemandService.SERVICE_NAME)
public class HybjDemandServiceImpl implements IHybjDemandService {

	@Resource(name= IHybjDemandDao.SERVICE_NAME)
	private IHybjDemandDao hybjDemandDao;


	@Override
	@Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,readOnly=false)
	public int save(HybjDemandForm hybjDemandForm) {
		HybjDemand hybjDemand = new HybjDemand();
		hybjDemand.setCp(hybjDemandForm.getCp());
		hybjDemand.setDemandName(hybjDemandForm.getDemandName());
		hybjDemand.setFenlei(hybjDemandForm.getFenlei());
		hybjDemand.setCompleteTime(hybjDemandForm.getCompleteTime());
		hybjDemand.setXqDetails(hybjDemandForm.getXqDetails());
		hybjDemand.setStatus(hybjDemandForm.getStatus());
		Serializable id = hybjDemandDao.save(hybjDemand);
		return (int)id;
	}
}
