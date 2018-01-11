package cn.hybj.dao.impl;

import cn.hybj.dao.IHybjDemandDao;
import cn.hybj.dao.IHybjNoticeDao;
import cn.hybj.data.DataBaseUtil;
import cn.hybj.data.HibernateSessionFactory;
import cn.hybj.domain.HybjDemand;
import cn.hybj.domain.HybjOutline;
import cn.hybj.web.form.HybjOutlineForm;
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
}
