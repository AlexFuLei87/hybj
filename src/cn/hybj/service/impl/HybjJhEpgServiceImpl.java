package cn.hybj.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import cn.hybj.dao.IHybjNoticeDao;
import cn.hybj.domain.HybjOutline;
import cn.hybj.web.form.HybjOutlineForm;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.hybj.dao.IHybjJhEpgDao;
import cn.hybj.domain.HybjReport;
import cn.hybj.domain.HybjText;
import cn.hybj.service.IHybjJhEpgService;
import cn.hybj.web.form.HybjReportForm;

@Transactional(readOnly=true)
@Service(IHybjJhEpgService.SERVICE_NAME)
public class HybjJhEpgServiceImpl implements IHybjJhEpgService {
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveGG(HybjOutlineForm hybjOutlineForm) {
		HybjOutline outline = new HybjOutline();
		outline.setDetails(hybjOutlineForm.getDetails());
		outline.setGgName(hybjOutlineForm.getOutlineName());
		outline.setOutline(hybjOutlineForm.getOutline());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		outline.setCreateTime(format.format(new Date()).toString());
		outline.setStatus("1");
		hybjNoticeDao.save(outline);
	}

	@Resource(name=IHybjJhEpgDao.SERVICE_NAME)
	private IHybjJhEpgDao hybjJhEpgDao;

	@Resource(name= IHybjNoticeDao.SERVICE_NAME)
	private IHybjNoticeDao hybjNoticeDao;
	/**  
	* @Name: saveElecText
	* @Description: 保存ElecText的方法
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: ElecText elecText 对象
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public List<HybjReport> findByCondition(HybjReportForm hybjReportForm){
		return hybjJhEpgDao.findByCondition(hybjReportForm);
	}

	public void changeStatus(HybjReportForm hybjReportForm) {
		 hybjJhEpgDao.changeStatus(hybjReportForm);
		
	}

	public void saveFeedback(HybjReportForm hybjReportForm) {
		hybjJhEpgDao.saveFeedback(hybjReportForm);
		
	}

	
}
