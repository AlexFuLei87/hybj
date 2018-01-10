package cn.hybj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.hybj.dao.IHybjUserRoleDao;
import cn.hybj.domain.HybjUserRole;

@Repository(IHybjUserRoleDao.SERVICE_NAME)
public class HybjUserRoleDaoImpl extends CommonDaoImpl<HybjUserRole> implements IHybjUserRoleDao {

	/**  
	* @Name: findUserListByRoleID
	* @Description: 查询用户列表集合，获取系统中所有的用户，并与该角色拥有的用户进行匹配
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: String roleID 角色ID
	* @Return: List<Object[]> 用户集合（匹配完成）
	*/
	public List<Object[]> findUserListByRoleID(final String roleID) {
		final String sql = "SELECT DISTINCT CASE hybj_user_role.RoleID " +
					 "WHEN ? THEN '1' ELSE '0' END AS flag, " +
					 "hybj_user.UserID as userID, " +
					 "hybj_user.UserName as userName, " +
					 "hybj_user.LogonName as logonName " +
					 "FROM hybj_user " + 
					 "LEFT OUTER JOIN hybj_user_role " + 
					 "ON hybj_user.UserID = hybj_user_role.UserID " + 
					 "AND hybj_user_role.RoleID = ? " +
					 "WHERE hybj_user.IsDuty='1'";
		List<Object []> list = (List<Object []>) this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql)
				              .addScalar("flag", Hibernate.STRING)
				              .addScalar("userID", Hibernate.STRING)
				              .addScalar("userName", Hibernate.STRING)
				              .addScalar("logonName", Hibernate.STRING);
				query.setString(0, roleID);
				query.setString(1, roleID);
				return query.list();
			}
		});
		return list;
	}

	
	
}
