package cn.hybj.service.impl;

import cn.hybj.dao.IHybjJhEpgDao;
import cn.hybj.dao.IHybjNoticeDao;
import cn.hybj.domain.HybjOutline;
import cn.hybj.domain.HybjReport;
import cn.hybj.service.IHybjJhEpgService;
import cn.hybj.service.IHybjNoticeService;
import cn.hybj.web.form.HybjOutlineForm;
import cn.hybj.web.form.HybjReportForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional(readOnly=true)
@Service(IHybjNoticeService.SERVICE_NAME)
public class HybjNoticeServiceImpl implements IHybjNoticeService {

	@Resource(name= IHybjNoticeDao.SERVICE_NAME)
	private IHybjNoticeDao hybjNoticeDao;


	@Override
	public List<HybjOutline> findAll() {
		return hybjNoticeDao.findAll();
	}

	@Override
	public List<HybjOutline> findGG() {
		return hybjNoticeDao.findGG();
	}


	@Override
	public void changeStatus(HybjOutlineForm hybjOutlineForm) {
		hybjNoticeDao.changeStatus(hybjOutlineForm);
	}

	@Override
	public HybjOutline findById(Integer id) {
		return hybjNoticeDao.findObjectByID(id);
	}
}
