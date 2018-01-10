package cn.hybj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.hybj.dao.IHybjLogDao;
import cn.hybj.domain.HybjLog;
import cn.hybj.domain.HybjUser;
import cn.hybj.service.IHybjLogService;
import cn.hybj.web.form.HybjLogForm;
@Transactional(readOnly=true)
@Service(IHybjLogService.SERVICE_NAME)
public class HybjLogServiceImpl implements IHybjLogService {
	
	@Resource(name=IHybjLogDao.SERVICE_NAME)
	private IHybjLogDao elecLogDao;

	/**  
	* @Name: saveElecLog
	* @Description: 保存日志信息
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: HttpServletRequest request，用于存放用户等相关信息
	*              String details，操作明细
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecLog(HttpServletRequest request, String details) {
		HybjLog elecLog = new HybjLog();
		elecLog.setIpAddress(request.getRemoteAddr());//IP地址
		HybjUser elecUser = (HybjUser)request.getSession().getAttribute("globle_user");
		elecLog.setOpeName(elecUser.getUserName());//操作人
		elecLog.setOpeTime(new Date());//操作时间
		elecLog.setDetails(details);
		elecLogDao.save(elecLog);
	}

	/**  
	* @Name: findElecLogListByCondition
	* @Description: 使用查询条件，查询日志信息列表
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: ElecLogForm elecLogForm，用于存放操作人信息
	* @Return: List<ElecLogForm> 日志信息列表
	*/
	public List<HybjLogForm> findElecLogListByCondition(HybjLogForm elecLogForm) {
		//组织查询和排序的条件
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if(elecLogForm!=null && elecLogForm.getOpeName()!=null && !elecLogForm.getOpeName().equals("")){
			hqlWhere += " and o.opeName like ?";
			paramsList.add("%"+elecLogForm.getOpeName()+"%");
		}
		Object [] params = paramsList.toArray();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.opeTime", "desc");
		List<HybjLog> list = elecLogDao.findCollectionByConditionNoPage(hqlWhere, params, orderby);
		//PO对象的结果集转换成VO对象的结果集
		List<HybjLogForm> formList = this.elecLogPOListToVOList(list);
		return formList;
	}
	/**  
	* @Name: elecLogPOListToVOList
	* @Description: 日志信息列表中，PO对象的结果集转换成VO对象的结果集
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: List<ElecLog> list，PO对象的结果集
	* @Return: List<ElecLogForm> VO对象的结果集
	*/
	private List<HybjLogForm> elecLogPOListToVOList(List<HybjLog> list) {
		List<HybjLogForm> formList = new ArrayList<HybjLogForm>();
		HybjLogForm elecLogForm = null;
		for(int i=0;list!=null && i<list.size();i++){
			HybjLog elecLog = list.get(i);
			elecLogForm = new HybjLogForm();
			elecLogForm.setLogID(elecLog.getLogID());
			elecLogForm.setIpAddress(elecLog.getIpAddress());
			elecLogForm.setOpeName(elecLog.getOpeName());
			elecLogForm.setOpeTime(String.valueOf(elecLog.getOpeTime()!=null?elecLog.getOpeTime():""));
			elecLogForm.setDetails(elecLog.getDetails());
			formList.add(elecLogForm);
		}
		return formList;
	}

	/**  
	* @Name: deleteElecLogByLogIDs
	* @Description: 使用日志ID，删除日志列表信息
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: ElecLogForm elecLogForm 用于存放日志ID的数组
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteElecLogByLogIDs(HybjLogForm elecLogForm) {
		//第一种方式
//		String [] logids = elecLogForm.getLogid();
//		elecLogDao.deleteObjectByIDs(logids);
		//第二种方式
		String logID = elecLogForm.getLogID();
		String [] logids = logID.split(",");
		String [] ids = new String[logids.length];
		for(int i=0;logids!=null && i<logids.length;i++){
			String logid = logids[i];
			ids[i] = logid.trim();
		}
		elecLogDao.deleteObjectByIDs(ids);
	}	
}
