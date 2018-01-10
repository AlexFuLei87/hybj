package cn.hybj.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;


public class DataBaseUtil {

	@SuppressWarnings("unchecked")
	public static List getDataList(Session session, String sql, Map<String, Object> parameter_map, boolean resultToMap) {
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		if (parameter_map != null) {
			sqlQuery.setProperties(parameter_map);
		}
		if (resultToMap) {
			sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		}
		return sqlQuery.list();
	}

	@SuppressWarnings("unchecked")
	public static List getDataList(Session session, String sql, Map<String, Object> parameter_map) {
		return getDataList(session, sql, parameter_map, false);
	}

	@SuppressWarnings("unchecked")
	public static List getDataList(Session session, String sql) throws Exception {
		return getDataList(session, sql, null, false);
	}

	public static Object getUniqueResult(Session session, String sql, Map<String, Object> parameter_map, boolean resultToMap) {
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		if (parameter_map != null) {
			sqlQuery.setProperties(parameter_map);
		}
		if (resultToMap) {
			sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		}

		return sqlQuery.uniqueResult();
	}

	public static Object getUniqueResult(Session session, String sql, Map<String, Object> parameter_map) {
		return getUniqueResult(session, sql, parameter_map, false);
	}

	public static Object getUniqueResult(Session session, String sql) {
		return getUniqueResult(session, sql, null, false);
	}

	@SuppressWarnings("unchecked")
	public static Page getDataPage(Session session, String countSql, String sql, Map<String, Object> parameter_map, int now, int maxResults, boolean resultToMap) {
		int rowSum = 0;
		Object ob = getUniqueResult(session, countSql, parameter_map);
		if (ob != null) {
			rowSum = Integer.parseInt(ob.toString());
		}
		Page page = new Page(now, maxResults, rowSum);
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		if (parameter_map != null) {
			sqlQuery.setProperties(parameter_map);
		}
		if (resultToMap) {
			sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		}
		List dataList = sqlQuery.setFirstResult((rowSum == 0 ? 0 : (page.getNow() - 1)) * maxResults).setMaxResults(maxResults).list();
		page.setData(dataList);
		return page;
	}
	@SuppressWarnings("unchecked")
	public static int executeUpdateByMap(Session session, String sql, Map<String, Object> parameter_map) {

		SQLQuery sqlQuery = session.createSQLQuery(sql);
		if (parameter_map != null) {
			sqlQuery.setProperties(parameter_map);
		}
		
		return sqlQuery.executeUpdate();
	}
	public static Page getDataPage(Session session, String countSql, String sql, Map<String, Object> parameter_map, int present, int maxResults) {
		return getDataPage(session, countSql, sql, parameter_map, present, maxResults, false);
	}

	public static Page getDataPage(Session session, String countSql, String sql, int now, int maxResults) {
		return getDataPage(session, countSql, sql, null, now, maxResults, false);
	}

	@SuppressWarnings("unchecked")
	public static int executeSql(final Session session, final String sql, final List parameter_list) {
		final List result_list = new ArrayList();
		Work work = new Work() {
			public void execute(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql);
				if (parameter_list != null) {
					int i = 1;
					Iterator it = parameter_list.iterator();
					while (it.hasNext()) {
						Object ob = it.next();
						ps.setObject(i, ob);
						++i;
					}
				}
				result_list.add(ps.executeUpdate());
			}
		};
		try {
			session.getTransaction().setTimeout(6);
			session.doWork(work);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result_list.size() > 0) {
			return (Integer) result_list.get(0);
		} else {
			return -1;
		}
	}

	public static int executeSql(final Session session, final String sql) throws Exception {
		return executeSql(session, sql, null);
	}

}
