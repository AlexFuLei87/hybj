package cn.hybj.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import cn.hybj.domain.HybjUser;
import cn.hybj.util.PageInfo;


public interface ICommonDao<T> {
	public Serializable save(T entity);
	void update(T entity);
	T findObjectByID(Serializable id);
	void deleteObjectByIDs(Serializable ... ids);
	void deleteObjectByCollection(Collection<T> entities);
	List<T> findCollectionByConditionNoPage(String hqlWhere,
			Object[] params, LinkedHashMap<String, String> orderby);
	void saveObjectByCollection(Collection<T> entities);
	//添加分页功能
	List<T> findCollectionByConditionWithPage(String hqlWhere,
			Object[] params, LinkedHashMap<String, String> orderby,
			PageInfo pageInfo);
}
