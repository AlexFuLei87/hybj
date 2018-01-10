package cn.hybj.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.hybj.dao.ICommonDao;
import cn.hybj.util.GenericSuperClass;
import cn.hybj.util.PageInfo;

public class CommonDaoImpl<T> extends HibernateDaoSupport implements ICommonDao<T> {

	//范型转换save
	@SuppressWarnings("unchecked")
	private Class entity = (Class)GenericSuperClass.getClass(this.getClass());
	/**  
	* @Name: save
	* @Description: 保存对象的方法
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: T entity 对象
	* @Return: 无
	*/
	public Serializable save(T entity) {
		Serializable id = this.getHibernateTemplate().save(entity);
		return id;
	}

	/**
	 *  <bean id="xxxxx" class="cn.hybj.dao.impl.CommonDaoImpl">
			<property name="sessionFactory" ref="sessionFactory"></property>
		</bean>
	 */
	@Resource(name="sessionFactory")
	public final void setSessionFactoryDi(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	/**  
	* @Name: update
	* @Description: 修改对象的方法
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: T entity 对象
	* @Return: 无
	*/
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	/**  
	* @Name: findObjectByID
	* @Description: 使用主键ID查询对象
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: Serializable id 主键ID
	* @Return: 返回对象
	*/
	@SuppressWarnings("unchecked")
	public T findObjectByID(Serializable id) {
		return (T)this.getHibernateTemplate().get(entity, id);
	}

	/**  
	* @Name: deleteObjectByIDs
	* @Description: 通过id的数组删除对象
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: Serializable [] id 主键ID的数组
	* @Return: 无
	*/
	public void deleteObjectByIDs(Serializable... ids) {
		for(int i=0;ids!=null && i<ids.length;i++){
			Serializable id = ids[i];
			Object object = (Object)this.getHibernateTemplate().get(entity, id);
			this.getHibernateTemplate().delete(object);
		}
	}
	/**  
	* @Name: deleteObjectByCollection
	* @Description: 通过集合的形式删除对象
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: SCollection<T> list 集合对象
	* @Return: 无
	*/
	public void deleteObjectByCollection(Collection<T> entities) {
		this.getHibernateTemplate().deleteAll(entities);
	}

	/**  
	* @Name: findCollectionByConditionNoPage
	* @Description: 使用查询条件查询列表的集合（不分页）
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: String hqlWhere hql语句的where条件
	*              Object[] params where条件的查询参数
	*              LinkedHashMap<String, String> orderby 排序条件
	* @Return: List<T> 结果集列表集合
	*/
	public List<T> findCollectionByConditionNoPage(String hqlWhere,
			final Object[] params, LinkedHashMap<String, String> orderby) {
		/**
		 * 组织HQL语句的Where条件
		 *      select * from hybj_text o where 1=1     放置DAO层
				and o.textName like ?              放置Service层
				and o.textRemark like ?
				order by o.textDate desc , o.textName asc 
		 */
		String hql = "from " + entity.getSimpleName() + " o where 1=1";
		//组织排序条件
		String hqlOrderBy = this.orderByCondition(orderby);
		hql = hql + hqlWhere + hqlOrderBy;
		final String finalHql = hql;
		List<T> list = (List<T>)this.getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(finalHql);
				setParams(query,params);
				return query.list();
			}
		});
		return list;
	}

	/**  
	* @Name: setParams
	* @Description: 对where条件中的参数设置参数值
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: Object[] params 参数值
	* @Return: 无
	*/
	private void setParams(Query query,Object[] params) {
		for(int i=0;params!=null && i<params.length;i++){
			query.setParameter(i, params[i]);
		}
	}

	/**  
	* @Name: orderByCondition
	* @Description: 组织排序条件
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: LinkedHashMap<String, String> orderby 排序条件
	* @Return: String 排序语句的字符串
	*/
	private String orderByCondition(LinkedHashMap<String, String> orderby) {
		StringBuffer buffer = new StringBuffer("");
		if(orderby!=null){
			buffer.append(" order by ");
			for(Map.Entry<String, String> map:orderby.entrySet()){
				buffer.append(" " + map.getKey() + " " + map.getValue() + ",");
			}
			buffer.deleteCharAt(buffer.length()-1);
		}
		return buffer.toString();
	}
	/**  
	* @Name: saveObjectByCollection
	* @Description: 使用集合的形式进行批量保存
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: Collection<T> entities 集合对象
	* @Return: 无
	*/
	public void saveObjectByCollection(Collection<T> entities) {
		this.getHibernateTemplate().saveOrUpdateAll(entities);
	}


	/**  
	* @Name: findCollectionByConditionWithPage
	* @Description: 使用查询条件查询列表的集合（分页）
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: String hqlWhere hql语句的where条件
	*              Object[] params where条件的查询参数
	*              LinkedHashMap<String, String> orderby 排序条件
	* @Return: List<T> 结果集列表集合
	*/
	public List<T> findCollectionByConditionWithPage(String hqlWhere,
			final Object[] params, LinkedHashMap<String, String> orderby,
			final PageInfo pageInfo) {
		String hql = "from " + entity.getSimpleName() + " o where 1=1";
		//组织排序条件
		String hqlOrderBy = this.orderByCondition(orderby);
		hql = hql + hqlWhere + hqlOrderBy;
		final String finalHql = hql;
		List<T> list = (List<T>)this.getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(finalHql);
				setParams(query,params);
				//添加分页功能
				pageInfo.setTotalResult(query.list().size());//通过pageInfo对象设置列表中的总记录数
				query.setFirstResult(pageInfo.getBeginResult());//当前页中的数据从第几条开始查询
				query.setMaxResults(pageInfo.getPageSize());//当前页显示几条记录
				return query.list();
			}
		});
		return list;
	}
	
}
