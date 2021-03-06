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
import java.util.Objects;

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
                condition += " and t.item_name like :itemName";
                parameter_map.put("itemName", "%"+hybjSpecial.getItemName()+"%");
            }
            if(!StringUtils.isBlank(hybjSpecial.getSpecialName())){
                condition += " and t.special_name like :specialName";
                parameter_map.put("specialName", "%"+hybjSpecial.getSpecialName()+"%");
            }
            if(!StringUtils.isBlank(hybjSpecial.getStatus())){
                if(hybjSpecial.getStatus().contains("And")){
                    condition += " and t.status in ('fail' , 'pass') ";
                } else {
                    condition += " and t.status =:status";
                    parameter_map.put("status", hybjSpecial.getStatus());
                }
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
            String sql = "UPDATE  hybj_special t set t.status=:status , t.verify_time = now() ";
            String condition = "";
            Map<String, Object> parameter_map = new HashMap<String, Object>();
            if("pass".equals(hybjSpecial.getStatus()) && !StringUtils.isBlank(hybjSpecial.getCp())){
                sql += " , t.draw_part =:drawPart ";
                parameter_map.put("drawPart",hybjSpecial.getCp());
            }
            condition += " where id=:id";
            parameter_map.put("id", hybjSpecial.getId());
            parameter_map.put("status", hybjSpecial.getStatus());
            DataBaseUtil.executeUpdateByMap(session, sql+condition , parameter_map);
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

    @Override
    public List<HybjSpecial> findByPassAndFail() {
        Session session = null;
        List<HybjSpecial> list = null;
        try {
            session = HibernateSessionFactory.getSession();
            String sql = "SELECT * from hybj_special t ";
            String condition = " where t.status = 'pass' or t.status = 'fail' ";
            Map<String, Object> parameter_map = new HashMap<String, Object>();
            list = DataBaseUtil.getDataList(session, sql + condition + " order by t.verify_time,t.create_time", parameter_map,
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
    public List<HybjSpecial> findDrawPart(HybjSpecial special) {
        Session session = null;
        List<HybjSpecial> list = null;
        try {
            session = HibernateSessionFactory.getSession();
            String sql = "SELECT * from hybj_special t ";
            String condition = " where 1=1 ";
            Map<String, Object> parameter_map = new HashMap<String, Object>();
            if(!StringUtils.isBlank(special.getDrawPart())){
                condition += " and t.draw_part =:drawPart ";
                parameter_map.put("drawPart",special.getDrawPart());
            }if(!StringUtils.isBlank(special.getStatus())){
                condition += " and t.status =:status ";
                parameter_map.put("status",special.getStatus());
            }if(!StringUtils.isBlank(special.getAttachmentName())){
                if(Objects.equals("isNull",special.getAttachmentName())){
                    condition += " and t.attachment_name  is null";
                }if(Objects.equals("isNotNull",special.getAttachmentName())){
                    condition += " and t.attachment_name  is not null";
                }
            }

            list = DataBaseUtil.getDataList(session, sql + condition + " order by t.create_time", parameter_map,
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
    public void updateById(HybjSpecial hybjSpecial) {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSession();
            String sql = "UPDATE  hybj_special t set t.attachment_url=:url,t.attachment_name=:name ";
            String condition = "";
            Map<String, Object> parameter_map = new HashMap<String, Object>();
            condition += "where id=:id";
            parameter_map.put("id", hybjSpecial.getId());
            parameter_map.put("url", hybjSpecial.getAttachmnetUrl());
            parameter_map.put("name", hybjSpecial.getAttachmentName());
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
