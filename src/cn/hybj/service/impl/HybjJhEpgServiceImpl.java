package cn.hybj.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.hybj.dao.IHybjNoticeDao;
import cn.hybj.domain.HybjOutline;
import cn.hybj.util.PageInfo;
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

	@Override
	public List<HybjReport> findByConditionWithPage(HybjReportForm hybjReportForm, HttpServletRequest request) {
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
//		if(hybjReportForm.getReportStatus()!=null ){
//			hqlWhere += " and t.reportStatus = ?";
//			paramsList.add(hybjReportForm.getReportStatus());
//		}
		if (!org.apache.commons.lang3.StringUtils.isBlank(hybjReportForm.getStatus())) {
			if(Objects.equals("failAndPass",hybjReportForm.getStatus())){
				hqlWhere += " and t.status in ('fail','pass','dxfail','dxpass') ";
			}else if(Objects.equals("dxfailAnddxpass",hybjReportForm.getStatus())){
				hqlWhere += " and t.status in ('dxfail','dxpass') ";
			} else {
				hqlWhere += " and t.status = ? ";
				paramsList.add( hybjReportForm.getStatus());
			}
		}
		if (!org.apache.commons.lang3.StringUtils.isBlank(hybjReportForm.getCp())) {
			hqlWhere += " and t.cp = ? ";
			paramsList.add(hybjReportForm.getCp());
		}
		if (!org.apache.commons.lang3.StringUtils.isBlank(hybjReportForm.getItemName())) {
			hqlWhere += " and t.itemName like ? ";
			paramsList.add("%" + hybjReportForm.getItemName() + "%");
		}if(!org.apache.commons.lang3.StringUtils.isBlank(hybjReportForm.getReportStatus())) {
			hqlWhere += " and t.reportStatus = ? ";
			paramsList.add( hybjReportForm.getReportStatus());
		}
		Object [] params = paramsList.toArray();
		//组织排序
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("t.createTime", "asc");
		//修改，添加分页功能 begin
		PageInfo pageInfo = new PageInfo(request);
//      List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(hqlWhere, params, orderby);
		List<HybjReport> list = hybjJhEpgDao.findCollectionByConditionWithPage(hqlWhere, params, orderby ,pageInfo);
		request.setAttribute("page", pageInfo.getPageBean());
		//end
		//List<HybjReport> formList = this.elecUserPOListToVOList(list);
		return list;
	}
}
