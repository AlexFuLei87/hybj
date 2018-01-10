package cn.hybj.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.hybj.dao.IHybjSystemDDlDao;
import cn.hybj.domain.HybjSystemDDl;
import cn.hybj.service.IHybjSystemDDlService;
import cn.hybj.web.form.HybjSystemDDlForm;
@Transactional(readOnly=true)
@Service(IHybjSystemDDlService.SERVICE_NAME)
public class HybjSystemDDlServiceImpl implements IHybjSystemDDlService {
	
	@Resource(name=IHybjSystemDDlDao.SERVICE_NAME)
	private IHybjSystemDDlDao elecSystemDDlDao;

	/**  
	* @Name: findKeyWord
	* @Description: 查询数据类型关键字，且去掉重复值
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: List<ElecSystemDDlForm> 数据类型列表
	*/
	public List<HybjSystemDDlForm> findKeyWord() {
		List<Object> list = elecSystemDDlDao.findKeyWord();
		List<HybjSystemDDlForm> formList = this.elecSystemDDlObjectToVO(list);
		return formList;
	}
	/**  
	* @Name: elecSystemDDlObjectToVO
	* @Description: 将查询的数据类型列表由Object对象转换成VO对象
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: List<Object> list Object对象
	* @Return: List<ElecSystemDDlForm> VO对象
	*/
	private List<HybjSystemDDlForm> elecSystemDDlObjectToVO(List<Object> list) {
		List<HybjSystemDDlForm> formList = new ArrayList<HybjSystemDDlForm>();
		HybjSystemDDlForm elecSystemDDlForm = null;
		for(int i=0;list!=null && i<list.size();i++){
			Object object = list.get(i);
			elecSystemDDlForm = new HybjSystemDDlForm();
			elecSystemDDlForm.setKeyword(object.toString());
			formList.add(elecSystemDDlForm);
		}
		return formList;
	}
	/**  
	* @Name: findElecSystemDDlListByKeyword
	* @Description: 根据选中数据类型，查询对应的数据项
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: List<ElecSystemDDlForm> 对应数据项的VO集合
	*/
	public List<HybjSystemDDlForm> findElecSystemDDlListByKeyword(String keyword) {
		List<HybjSystemDDl> list = this.findSystemDDlListByCondition(keyword);
		List<HybjSystemDDlForm> formList = this.elecSystemDDlPOListToVOList(list);
		return formList;
	}
	
	/**  
	* @Name: findSystemDDlListByCondition
	* @Description: 根据选中数据类型，查询对应的数据项，获取数据项的集合列表
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: String keyword 数据类型
	* @Return: List<ElecSystemDDlForm> 对应数据项的VO集合
	*/
	private List<HybjSystemDDl> findSystemDDlListByCondition(String keyword) {
		String hqlWhere = " and o.keyword = ?";
		Object [] params = {keyword};
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.ddlCode", "asc");
		List<HybjSystemDDl> list = elecSystemDDlDao.findCollectionByConditionNoPage(hqlWhere, params, orderby);
		return list;
	}
	/**  
	* @Name: elecSystemDDlPOListToVOList
	* @Description: 数据项的集合列表从PO对象转化成VO对象
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: List<ElecSystemDDl> list 存放PO集合
	* @Return: List<ElecSystemDDlForm> 对应数据项的VO集合
	*/
	private List<HybjSystemDDlForm> elecSystemDDlPOListToVOList(
			List<HybjSystemDDl> list) {
		List<HybjSystemDDlForm> formList = new ArrayList<HybjSystemDDlForm>();
		HybjSystemDDlForm elecSystemDDlForm = null;
		for(int i=0;list!=null && i<list.size();i++){
			HybjSystemDDl elecSystemDDl = list.get(i);
			elecSystemDDlForm = new HybjSystemDDlForm();
			elecSystemDDlForm.setSeqID(String.valueOf(elecSystemDDl.getSeqID()));
			elecSystemDDlForm.setKeyword(elecSystemDDl.getKeyword());
			elecSystemDDlForm.setDdlCode(String.valueOf(elecSystemDDl.getDdlCode()));
			elecSystemDDlForm.setDdlName(elecSystemDDl.getDdlName());
			formList.add(elecSystemDDlForm);
		}
		return formList;
	}
	/**  
	* @Name: saveElecSystemDDl
	* @Description: 保存数据字典，保存数据类型、数据项编号、数据项名称
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: ElecSystemDDlForm elecSystemDDlForm 存放页面传递的表单值
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecSystemDDl(HybjSystemDDlForm elecSystemDDlForm) {
		//获取页面传递的表单值
		//获取数据类型
		String keyword = elecSystemDDlForm.getKeywordname();
		//获取判断是新增数据类型还是在原有的数据类型中编辑的标识
		String typeflag = elecSystemDDlForm.getTypeflag();
		//获取需要保存的数据项的名称
		String [] itemname = elecSystemDDlForm.getItemname();
		//如果是新增数据类型的操作，此时typeflag==new
		if(typeflag!=null && typeflag.equals("new")){
			//保存数据字典
			this.saveSystemDDlWithParams(keyword,itemname);
		}
		//否则是表示在原有的数据类型中进行修改和编辑，此时typeflag==add
		else{
			List<HybjSystemDDl> list = findSystemDDlListByCondition(keyword);
			elecSystemDDlDao.deleteObjectByCollection(list);
			//保存数据字典
			this.saveSystemDDlWithParams(keyword,itemname);
		}
		
	}
	/**  
	* @Name: saveSystemDDlWithParams
	* @Description: 通过页面传递的参数保存数据字典
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: String keyword      数据类型
	*              String[] itemname   数据项名称
	* @Return: 无
	*/
	private void saveSystemDDlWithParams(String keyword, String[] itemname) {
		List<HybjSystemDDl> list = new ArrayList<HybjSystemDDl>();
		for(int i=0;itemname!=null && i<itemname.length;i++){
			HybjSystemDDl elecSystemDDl = new HybjSystemDDl();
			elecSystemDDl.setKeyword(keyword);
			elecSystemDDl.setDdlName(itemname[i]);
			elecSystemDDl.setDdlCode(new Integer(i+1));
			list.add(elecSystemDDl);
			//elecSystemDDlDao.save(elecSystemDDl);
		}
		elecSystemDDlDao.saveObjectByCollection(list);
	}
}
