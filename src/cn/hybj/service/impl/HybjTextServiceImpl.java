package cn.hybj.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.hybj.dao.IHybjTextDao;
import cn.hybj.domain.HybjText;
import cn.hybj.service.IHybjTextService;
import cn.hybj.web.form.HybjTextForm;
@Transactional(readOnly=true)
@Service(IHybjTextService.SERVICE_NAME)
public class HybjTextServiceImpl implements IHybjTextService {
	
	@Resource(name=IHybjTextDao.SERVICE_NAME)
	private IHybjTextDao elecTextDao;
	
	/**  
	* @Name: saveElecText
	* @Description: 保存ElecText的方法
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: ElecText elecText 对象
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecText(HybjText elecText){
		elecTextDao.save(elecText);
	}

	/**  
	* @Name: findCollectionByConditionNoPage
	* @Description: 使用查询条件查询列表的集合（不分页）
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: ElecTextForm elecTextForm VO对象
	* @Return: List<ElecText> 列表集合
	*/
	public List<HybjText> findCollectionByConditionNoPage(
			HybjTextForm elecTextForm) {
		/**
		 * 组织HQL语句的Where条件
		 *      select * from hybj_text o where 1=1     放置DAO层
				and o.textName like '%张%'              放置Service层
				and o.textRemark like '%李%'
				order by o.textDate desc , o.textName asc 
		 */
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if(elecTextForm!=null && StringUtils.isNotBlank(elecTextForm.getTextName())){
			hqlWhere += " and o.textName like ?";
			paramsList.add("%"+elecTextForm.getTextName()+"%");
		}
		if(elecTextForm!=null && StringUtils.isNotBlank(elecTextForm.getTextRemark())){
			hqlWhere += " and o.textRemark like ?";
			paramsList.add("%"+elecTextForm.getTextRemark()+"%");
		}
		Object [] params = paramsList.toArray();
		/**
		 * 组织排序语句
		 *     order by o.textDate desc , o.textName asc 
		 */
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.textDate", "desc");
		orderby.put("o.textName", "asc");
		//查询列表
		List<HybjText> list = elecTextDao.findCollectionByConditionNoPage(hqlWhere,params,orderby);
		for(int i=0;list!=null && i<list.size();i++){
			HybjText elecText = list.get(i);
			System.out.println(elecText.getTextName() + "　" + elecText.getTextRemark());
		}
		return null;
	}
}
