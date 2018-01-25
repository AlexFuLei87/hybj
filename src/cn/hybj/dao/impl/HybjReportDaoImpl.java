package cn.hybj.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.hybj.dao.IHybjReportDao;
import cn.hybj.data.DataBaseUtil;
import cn.hybj.data.HibernateSessionFactory;
import cn.hybj.domain.HybjReport;

@Repository(IHybjReportDao.SERVICE_NAME)
public class HybjReportDaoImpl extends CommonDaoImpl<HybjReport> implements IHybjReportDao {

	public int findReportByItemName(final String itemName) {
		final String sql = "SELECT * FROM hybj_report h WHERE h.item_name = ?;";
	List<Object> list = (List<Object>)this.getHibernateTemplate().execute(new HibernateCallback(){

		public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			Query query = session.createSQLQuery(sql);
			query.setParameter(0, itemName);
			return query.list();
		}
	});
	return list.size();
	}

	public List<HybjReport> findPassOrFail() {
		Session session = null;
		List<HybjReport> list = null;
		try {
			session = HibernateSessionFactory.getSession();
			String sql = "SELECT * from hybj_report t ";
			String condition = "";
			Map<String, Object> parameter_map = new HashMap<String, Object>();
			condition += "where t.status IN ('pass','fail')";
			list= DataBaseUtil.getDataList(session, sql + condition + " order by t.create_time", parameter_map, 
					true);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public List<HybjReport> findByStatusAndCp(String status, String cp) {
		Session session = null;
		List<HybjReport> list = null;
		try {
			session = HibernateSessionFactory.getSession();
			String sql = "SELECT * from hybj_report t ";
			String condition = "";
			Map<String, Object> parameter_map = new HashMap<String, Object>();
			condition += "where t.status =:status and cp=:cp";
			parameter_map.put("status", status);
			parameter_map.put("cp", cp);
			list= DataBaseUtil.getDataList(session, sql + condition + " order by t.verify_time", parameter_map, 
					true);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public List<HybjReport> findOtherCpPsss(String name) {
		Session session = null;
		List<HybjReport> list = null;
		try {
			session = HibernateSessionFactory.getSession();
			String sql = "SELECT * from hybj_report t ";
			String condition = "";
			Map<String, Object> parameter_map = new HashMap<String, Object>();
			condition += "where t.status = 'pass' and cp=:cp";
			parameter_map.put("cp", name);
			list= DataBaseUtil.getDataList(session, sql + condition + " order by t.verify_time", parameter_map, 
					true);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public void updateStatusById(int id) {
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			String sql = "UPDATE  hybj_report t set t.status='normal' ";
			String condition = "";
			Map<String, Object> parameter_map = new HashMap<String, Object>();
			condition += "where id=:id";
			parameter_map.put("id", id);
			DataBaseUtil.executeUpdateByMap(session, sql+condition, parameter_map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void updateById(int id, String status) {
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			String sql = "UPDATE  hybj_report t ";
			String condition = "";
			Map<String, Object> parameter_map = new HashMap<String, Object>();
			condition += "set t.status=:status , t.verify_time = now() where id=:id";
			parameter_map.put("status", status);
			parameter_map.put("id", id);
			DataBaseUtil.executeUpdateByMap(session, sql+condition, parameter_map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<HybjReport> findByFuzzy(HybjReport hybjReport) {
		Session session = null;
		List<HybjReport> list = null;
		try {
			session = HibernateSessionFactory.getSession();
			String sql = "SELECT * from hybj_report t ";
			String condition = "";
			Map<String, Object> parameter_map = new HashMap<String, Object>();
			condition += "where 1=1 ";
			if(!StringUtils.isBlank(hybjReport.getCp())){
				condition += " and t.cp =:cp";
				parameter_map.put("cp", hybjReport.getCp());
			}
			if(!StringUtils.isBlank(hybjReport.getItemName())){
				condition += " and t.item_name like :itemName";
				parameter_map.put("itemName", "%"+hybjReport.getItemName()+"%");
			}
			if(!StringUtils.isBlank(hybjReport.getReportStatus())){
				condition += " and t.report_status =:reportStatus";
				parameter_map.put("reportStatus", hybjReport.getReportStatus());
			}
			if(!StringUtils.isBlank(hybjReport.getStatus())){
				if(hybjReport.getStatus().contains("And")){
					condition += " and t.status in ('fail','pass')";
				}else {
					condition += " and t.status =:status";
					parameter_map.put("status", hybjReport.getStatus());
				}
			}

			list= DataBaseUtil.getDataList(session, sql + condition, parameter_map,
					true);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
