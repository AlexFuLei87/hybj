package cn.hybj.dao.impl;

import cn.hybj.dao.IHybjDemandDao;
import cn.hybj.dao.IHybjNoticeDao;
import cn.hybj.data.DataBaseUtil;
import cn.hybj.data.HibernateSessionFactory;
import cn.hybj.domain.HybjDemand;
import cn.hybj.domain.HybjOutline;
import cn.hybj.domain.HybjReport;
import cn.hybj.web.form.HybjOutlineForm;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(IHybjDemandDao.SERVICE_NAME)
public class HybjDemandDaoImpl extends CommonDaoImpl<HybjDemand> implements IHybjDemandDao {

    @Override
    public List<HybjDemand> findXQByCp(String department) {
        Session session = null;
        List<HybjDemand> list = null;
        try {
            session = HibernateSessionFactory.getSession();
            String sql = "SELECT * from hybj_demand t ";
            String condition = "where t.cp=:cp";
            Map<String, Object> parameter_map = new HashMap<String, Object>();
            parameter_map.put("cp",department);
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


    @Override
    public List<HybjDemand> findAll() {
        Session session = null;
        List<HybjDemand> list = null;
        try {
            session = HibernateSessionFactory.getSession();
            String sql = "SELECT * from hybj_demand t ";
            String condition = "";
            Map<String, Object> parameter_map = new HashMap<String, Object>();
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


    @Override
    public void changeStatusById(int id, String status) {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSession();
            String sql = "UPDATE  hybj_demand t set t.status=:status ";
            String condition = "";
            Map<String, Object> parameter_map = new HashMap<String, Object>();
            condition += "where t.id=:id";
            parameter_map.put("id", id);
            parameter_map.put("status", status);
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
    public List<HybjDemand> findByFuzzy(HybjDemand demand) {
        Session session = null;
        List<HybjDemand> list = null;
        try {
            session = HibernateSessionFactory.getSession();
            String sql = "SELECT * from hybj_demand t ";
            String condition = "";
            Map<String, Object> parameter_map = new HashMap<String, Object>();
            condition += "where 1=1 ";
            if(!StringUtils.isBlank(demand.getCp())){
                condition += " and t.cp =:cp";
                parameter_map.put("cp", demand.getCp());
            }
            if(!StringUtils.isBlank(demand.getDemandName())){
                condition += " and t.demand_name =:demandName";
                parameter_map.put("demandName", demand.getDemandName());
            }
            if(!StringUtils.isBlank(demand.getTowho())){
                condition += " and t.towho =:towho";
                parameter_map.put("towho", demand.getTowho());
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


    @Override
    public void changeTowhoById(Integer id, String towho) {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSession();
            String sql = "UPDATE  hybj_demand t set t.towho=:towho ";
            String condition = "";
            Map<String, Object> parameter_map = new HashMap<String, Object>();
            condition += "where t.id=:id";
            parameter_map.put("id", id);
            parameter_map.put("towho", towho);
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
}
