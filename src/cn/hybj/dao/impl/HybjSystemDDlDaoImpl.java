package cn.hybj.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.hybj.dao.IHybjSystemDDlDao;
import cn.hybj.domain.HybjSystemDDl;

@Repository(IHybjSystemDDlDao.SERVICE_NAME)
public class HybjSystemDDlDaoImpl extends CommonDaoImpl<HybjSystemDDl> implements IHybjSystemDDlDao {

	/**  
	* @Name: findKeyWord
	* @Description: 查询数据类型关键字，且去掉重复值
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: List<ElecSystemDDl> 数据类型列表
	*/
	public List<Object> findKeyWord() {
		String hql = "select distinct o.keyword from HybjSystemDDl o";
		List<Object> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	/**  
	* @Name: findDDlName
	* @Description: 用数据类型和数据项的编号获取数据项的名称
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: String ddlCode 数据项编号
	*              String keyword 数据类型
	* @Return: String  数据项名称
	*/
	public String findDDlName(String ddlCode, String keyword) {
		String hql = "from HybjSystemDDl where keyword = '" + keyword + "'" +
		             " and ddlCode=" + ddlCode;
		List<HybjSystemDDl> list = this.getHibernateTemplate().find(hql);
		String ddlName = "";
		if(list!=null && list.size()>0){
			HybjSystemDDl elecSystemDDl = list.get(0);
			ddlName = elecSystemDDl.getDdlName();
		}
		return ddlName;
	}

	
	
}
