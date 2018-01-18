package cn.hybj.dao.impl;

import cn.hybj.dao.IHybjDemandDao;
import cn.hybj.dao.IHybjSpecialDao;
import cn.hybj.data.DataBaseUtil;
import cn.hybj.data.HibernateSessionFactory;
import cn.hybj.domain.HybjDemand;
import cn.hybj.domain.HybjSpecial;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(IHybjSpecialDao.SERVICE_NAME)
public class HybjSpecialDaoImpl extends CommonDaoImpl<HybjSpecial> implements IHybjSpecialDao {

    @Override
    public List<HybjSpecial> findBycondition(HybjSpecial hybjSpecial) {
        Session session = null;
        List<HybjSpecial> list = null;
        try {
            session = HibernateSessionFactory.getSession();
            String sql = "SELECT * from hybj_special t ";
            String condition = "";
            Map<String, Object> parameter_map = new HashMap<String, Object>();
            condition += "where 1=1 ";
            if(!StringUtils.isBlank(hybjSpecial.getCp())){
                condition += " and t.cp =:cp";
                parameter_map.put("cp", hybjSpecial.getCp());
            }
            if(!StringUtils.isBlank(hybjSpecial.getItemName())){
                condition += " and t.item_name =:itemName";
                parameter_map.put("itemName", hybjSpecial.getItemName());
            }
            if(!StringUtils.isBlank(hybjSpecial.getSpecialName())){
                condition += " and t.special_name =:specialName";
                parameter_map.put("specialName", hybjSpecial.getSpecialName());
            }
            if(!StringUtils.isBlank(hybjSpecial.getStatus())){
                condition += " and t.status =:status";
                parameter_map.put("status", hybjSpecial.getStatus());
            }

            list= DataBaseUtil.getDataList(session, sql + condition+" order by t.verify_time,t.create_time", parameter_map,
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
    public void updateStatus(HybjSpecial hybjSpecial) {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSession();
            String sql = "UPDATE  hybj_special t set t.status=:status ";
            String condition = "";
            Map<String, Object> parameter_map = new HashMap<String, Object>();
            condition += "where id=:id";
            parameter_map.put("id", hybjSpecial.getId());
            parameter_map.put("status", hybjSpecial.getStatus());
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
    public void updataFeedback(HybjSpecial hybjSpecial) {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSession();
            String sql = "UPDATE  hybj_special t set t.feedback=:feedback ";
            String condition = "";
            Map<String, Object> parameter_map = new HashMap<String, Object>();
            condition += "where id=:id";
            parameter_map.put("id", hybjSpecial.getId());
            parameter_map.put("feedback", hybjSpecial.getFeedback());
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
