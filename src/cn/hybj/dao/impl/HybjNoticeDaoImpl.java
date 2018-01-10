package cn.hybj.dao.impl;

import cn.hybj.dao.IHybjNoticeDao;
import cn.hybj.data.DataBaseUtil;
import cn.hybj.data.HibernateSessionFactory;
import cn.hybj.domain.HybjOutline;
import cn.hybj.web.form.HybjOutlineForm;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(IHybjNoticeDao.SERVICE_NAME)
public class HybjNoticeDaoImpl extends CommonDaoImpl<HybjOutline> implements IHybjNoticeDao {


    @Override
    public List<HybjOutline> findAll() {
        Session session = null;
        List<HybjOutline> list = null;
        try {
            session = HibernateSessionFactory.getSession();
            String sql = "SELECT * from hybj_outline t ";
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
    public List<HybjOutline> findGG() {
        Session session = null;
        List<HybjOutline> list = null;
        try {
            session = HibernateSessionFactory.getSession();
            String sql = "SELECT * from hybj_outline t ";
            String condition = "where t.status = '1'";
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
    public void changeStatus(HybjOutlineForm hybjOutlineForm) {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSession();
            String sql = "UPDATE  hybj_outline t set t.status=:status ";
            String condition = "";
            Map<String, Object> parameter_map = new HashMap<String, Object>();
            condition += "where t.id=:id";
            parameter_map.put("id", hybjOutlineForm.getId());
            parameter_map.put("status", hybjOutlineForm.getStatus());
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
