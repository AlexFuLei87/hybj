package cn.hybj.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.hybj.dao.IHybjUserDao;
import cn.hybj.domain.HybjUser;

@Repository(IHybjUserDao.SERVICE_NAME)
public class HybjUserDaoImpl extends CommonDaoImpl<HybjUser> implements IHybjUserDao {

	/**  
	* @Name: findElecPopedomByLogonName
	* @Description: 使用登录名获取当前登录名所具有的权限，查询数据库表
	*                                                hybj_user
	*                                                hybj_user_role
	*                                                hybj_role_popedom
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: String name 登录名
	* @Return: List<Object> 存放该用户具有的权限集合
	*/
	public List<Object> findElecPopedomByLogonName(final String name) {
		final String sql = "SELECT a.popedomcode as popedom FROM hybj_role_popedom a " +
					 "LEFT OUTER JOIN hybj_user_role b ON a.RoleID = b.RoleID " +
					 "INNER JOIN hybj_user c ON b.UserID = c.UserID " +
					 "AND c.logonName = ? " +
					 "WHERE c.isDuty = '1'";
		List<Object> list = (List<Object>)this.getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql)
				                     .addScalar("popedom",Hibernate.STRING);
				query.setParameter(0, name);
				return query.list();
			}
			
		});
		return list;
	}
	/**  
	* @Name: findElecRoleByLogonName
	* @Description: 使用登录名获取当前登录名所具有的角色，查询数据库表
	*                                                hybj_user
	*                                                hybj_user_role
	*                                                hybj_systemddl
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: String name 登录名
	* @Return: List<Object[]> 存放该用户具有的角色集合
	*/
	public List<Object[]> findRoleByLogonName(final String name) {
		final String sql = "SELECT b.ddlCode as code,b.ddlName as name FROM hybj_user_role a " +
					 "LEFT OUTER JOIN hybj_systemddl b ON a.RoleID = b.ddlCode " +
					 "AND b.keyword = '角色类型' " +
					 "INNER JOIN hybj_user c ON a.UserID = c.UserID " +
					 "AND c.logonName = ? " +		
					 "WHERE c.isDuty = '1'";
		List<Object[]> list = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql)
				                     .addScalar("code",Hibernate.STRING)
				                     .addScalar("name",Hibernate.STRING);
				query.setParameter(0, name);
				return query.list();
			}
			
		});
		return list;
	}
	/**  
	* @Name: findUserByChart
	* @Description:使用柱状图按照所属单位统计用户数量
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: List<Object[]> 结果集对象
	*/
	public List<Object[]> findUserByChart() {
		final String sql = "SELECT b.DdlName AS jctname,COUNT(*) AS jctcount FROM hybj_user a " +
					 "LEFT OUTER JOIN hybj_systemddl b ON a.JctID = b.DdlCode " +
					 "AND b.Keyword = '所属单位' " +
					 "GROUP BY a.JctID";
		List<Object[]> list = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql)
				              .addScalar("jctname",Hibernate.STRING)
				              .addScalar("jctcount",Hibernate.STRING);	
				return query.list();
			}
			
		});
		return list;
	}

	
	
}
