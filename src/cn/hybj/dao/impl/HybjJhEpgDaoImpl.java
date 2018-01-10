package cn.hybj.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.hybj.dao.IHybjJhEpgDao;
import cn.hybj.data.DataBaseUtil;
import cn.hybj.data.HibernateSessionFactory;
import cn.hybj.data.Page;
import cn.hybj.domain.HybjReport;
import cn.hybj.web.form.HybjReportForm;

@Repository(IHybjJhEpgDao.SERVICE_NAME)
public class HybjJhEpgDaoImpl extends CommonDaoImpl<HybjReport> implements IHybjJhEpgDao {

	public List<HybjReport> findByCondition(HybjReportForm hybjReportForm) {
		Session session = null;
		List<HybjReport> list = null;
		try {
			session = HibernateSessionFactory.getSession();
			String sql = "SELECT * from hybj_report t ";
			String condition = "";
			Map<String, Object> parameter_map = new HashMap<String, Object>();
			condition += "where t.status =:status";
			parameter_map.put("status", "normal");
			if (!StringUtils.isBlank(hybjReportForm.getCp())) {
				condition += "and t.cp =:cp";
				parameter_map.put("cp", hybjReportForm.getCp());
			}
			if (!StringUtils.isBlank(hybjReportForm.getItemName())) {
				condition += " and t.item_name like:itemName ";
				parameter_map.put("itemName", "%" + hybjReportForm.getItemName() + "%");
			}
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

	public void changeStatus(HybjReportForm hybjReportForm) {
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			String sql = "Update hybj_report ";
			String condition = "";
			Map<String, Object> parameter_map = new HashMap<String, Object>();
			condition += "SET `status` =:status , verify_time =:verifyTime WHERE id=:id";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String verifyTime = format.format(new Date()).toString();
			parameter_map.put("status", hybjReportForm.getStatus());
			parameter_map.put("verifyTime", verifyTime);
			parameter_map.put("id", hybjReportForm.getId());
			DataBaseUtil.executeUpdateByMap(session, sql + condition, parameter_map);
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

	public void saveFeedback(HybjReportForm hybjReportForm) {
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			String sql = "Update hybj_report ";
			String condition = "";
			Map<String, Object> parameter_map = new HashMap<String, Object>();
			condition += "SET `feedback` =:feedback WHERE id=:id";
			parameter_map.put("feedback", hybjReportForm.getFeedback());
			parameter_map.put("id", hybjReportForm.getId());
			DataBaseUtil.executeUpdateByMap(session, sql + condition, parameter_map);
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
	
}
