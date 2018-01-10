package cn.hybj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.hybj.dao.IHybjCommonMsgDao;
import cn.hybj.domain.HybjCommonMsg;

@Repository(IHybjCommonMsgDao.SERVICE_NAME)
public class HybjCommonMsgDaoImpl extends CommonDaoImpl<HybjCommonMsg> implements IHybjCommonMsgDao {

	/**  
	* @Name: findElecCommonMsgListByCurrentDate
	* @Description: 通过当前日期查询代办事宜列表
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: String currentDate 当前日期
	* @Return: List<Object[]> 代办事宜列表
	*/
	public List<Object[]> findCommonMsgListByCurrentDate(String currentDate) {
		final String sql = "SELECT o.StationRun as stationRun,o.DevRun as devRun " +
				     "FROM Hybj_CommonMsg o " + 
                     "WHERE o.CreateDate = '" + currentDate + "'";
		List<Object[]> list = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql)
							  .addScalar("stationRun",Hibernate.STRING)
				              .addScalar("devRun",Hibernate.STRING);
				return query.list();
			}
		});
		return list;
	}
	
}
