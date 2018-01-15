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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
		hybjDemand.setAttachmentName(hybjDemandForm.getFileFileName());
		hybjDemand.setAttachmentUrl(hybjDemandForm.getAttachmentUrl());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		hybjDemand.setCreateTime(format.format(new Date()).toString());
		hybjDemand.setTowho(hybjDemandForm.getTowho());
		Serializable id = hybjDemandDao.save(hybjDemand);
		return (int)id;
	}


	@Override
	public List<HybjDemand> findXQByCp(String department) {
		return hybjDemandDao.findXQByCp(department);
	}

	@Override
	public HybjDemand findById(int id) {
		return hybjDemandDao.findObjectByID(id);
	}

	@Override
	public List<HybjDemand> findAll() {
		return hybjDemandDao.findAll();
	}


	@Override
	public void changeStatusById(Integer id, String status) {
		hybjDemandDao.changeStatusById(id,status);
	}

	@Override
	public List<HybjDemand> findByFuzzy(HybjDemand demand) {
		return hybjDemandDao.findByFuzzy(demand);
	}

}
