package cn.hybj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.hybj.dao.IHybjCommonMsgDao;
import cn.hybj.domain.HybjCommonMsg;
import cn.hybj.service.IHybjCommonMsgService;
import cn.hybj.web.form.HybjCommonMsgForm;
@Transactional(readOnly=true)
@Service(IHybjCommonMsgService.SERVICE_NAME)
public class HybjCommonMsgServiceImpl implements IHybjCommonMsgService {
	
	@Resource(name=IHybjCommonMsgDao.SERVICE_NAME)
	private IHybjCommonMsgDao elecCommonMsgDao;

	/**  
	* @Name: home
	* @Description: 查询所有的代办事宜信息
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: List<ElecCommonMsgForm> 代办事宜结果集列表
	*/
	public List<HybjCommonMsgForm> findCommonMsgList() {
		String hqlWhere = "";
		Object [] params = null;
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put(" o.createDate", "desc");
		List<HybjCommonMsg> list = elecCommonMsgDao.findCollectionByConditionNoPage(hqlWhere, params, orderby);
		List<HybjCommonMsgForm> formList = this.elecCommonMsgPOListToVOList(list);
		return formList;
	}

	/**  
	* @Name: elecCommonMsgPOListToVOList
	* @Description: 将代办事宜的结果信息从PO对象转换成VO对象
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: List<ElecCommonMsg> list PO对象结果集
	* @Return: List<ElecCommonMsgForm> VO对象结果集
	*/
	private List<HybjCommonMsgForm> elecCommonMsgPOListToVOList(
			List<HybjCommonMsg> list) {
		List<HybjCommonMsgForm> formList = new ArrayList<HybjCommonMsgForm>();
		HybjCommonMsgForm elecCommonMsgForm = null;
		for(int i=0;list!=null && i<list.size();i++){
			HybjCommonMsg elecCommonMsg = list.get(i);
			elecCommonMsgForm = new HybjCommonMsgForm();
			elecCommonMsgForm.setComID(elecCommonMsg.getComID());
			elecCommonMsgForm.setDevRun(elecCommonMsg.getDevRun());
			elecCommonMsgForm.setStationRun(elecCommonMsg.getStationRun());
			elecCommonMsgForm.setCreateDate(String.valueOf(elecCommonMsg.getCreateDate()!=null?elecCommonMsg.getCreateDate():""));
			formList.add(elecCommonMsgForm);
		}
		return formList;
	}

	/**  
	* @Name: saveElecCommonMsg
	* @Description: 保存代办事宜信息
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: ElecCommonMsgForm elecCommonMsgForm VO对象，页面传递的参数值
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveCommonMsg(HybjCommonMsgForm elecCommonMsgForm) {
		//VO对象转成PO对象
		HybjCommonMsg elecCommonMsg = this.elecCommonMsgVOToPO(elecCommonMsgForm);
		elecCommonMsgDao.save(elecCommonMsg);
	}
	/**  
	* @Name: elecCommonMsgVOToPO
	* @Description: 页面传递的代办事宜信息从VO对象转换成PO对象
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: ElecCommonMsgForm elecCommonMsgForm VO对象，页面传递的参数值
	* @Return: ElecCommonMsg PO对象
	*/
	private HybjCommonMsg elecCommonMsgVOToPO(
			HybjCommonMsgForm elecCommonMsgForm) {
		HybjCommonMsg elecCommonMsg = new HybjCommonMsg();
		if(elecCommonMsgForm!=null){
			elecCommonMsg.setStationRun(elecCommonMsgForm.getStationRun());
			elecCommonMsg.setDevRun(elecCommonMsgForm.getDevRun());
			elecCommonMsg.setCreateDate(new Date());
		}
		return elecCommonMsg;
	}

	/**  
	* @Name: findElecCommonMsgListByCurrentDate
	* @Description: 通过当前日期查询代办事宜列表
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: List<ElecCommonMsgForm> 代办事宜列表
	*/
	public List<HybjCommonMsgForm> findCommonMsgListByCurrentDate() {
		//获取当前日期YYYY-MM-DD
		java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
		String currentDate = date.toString();
		List<Object[]> list = elecCommonMsgDao.findCommonMsgListByCurrentDate(currentDate);
		List<HybjCommonMsgForm> formList = this.elecCommonMsgObjectListToVOList(list);
		return formList;
	}
	/**  
	* @Name: elecCommonMsgObjectListToVOList
	* @Description: 将代办事宜查询的结果由Object[]转换成VO对象
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: List<Object[]> list 存放Object[]数组对象
	* @Return: List<ElecCommonMsgForm> VO对象集合
	*/
	private List<HybjCommonMsgForm> elecCommonMsgObjectListToVOList(
			List<Object[]> list) {
		List<HybjCommonMsgForm> formList = new ArrayList<HybjCommonMsgForm>();
		HybjCommonMsgForm elecCommonMsgForm = null;
		for(int i=0;list!=null && i<list.size();i++){
			Object[] object = list.get(i);
			elecCommonMsgForm = new HybjCommonMsgForm();
			elecCommonMsgForm.setStationRun(object[0].toString());
			elecCommonMsgForm.setDevRun(object[1].toString());
			formList.add(elecCommonMsgForm);
		}
		return formList;
	}
	
}
