package cn.hybj.service.impl;

import cn.hybj.dao.IHybjDemandDao;
import cn.hybj.dao.IHybjSpecialDao;
import cn.hybj.domain.HybjDemand;
import cn.hybj.domain.HybjSpecial;
import cn.hybj.service.IHybjDemandService;
import cn.hybj.service.IHybjSpecialService;
import cn.hybj.util.GenerateSqlFromExcel;
import cn.hybj.web.form.HybjDemandForm;
import cn.hybj.web.form.HybjSpecialForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional(readOnly=true)
@Service(IHybjSpecialService.SERVICE_NAME)
public class HybjSpecialServiceImpl implements IHybjSpecialService {

	@Resource(name= IHybjSpecialDao.SERVICE_NAME)
	private IHybjSpecialDao hybjSpecialDao;

	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void save(HybjSpecial hybjSpecial) {
		hybjSpecialDao.save(hybjSpecial);
	}

	@Override
	public List<HybjSpecial> findByCondition(HybjSpecial hybjSpecial) {
		return hybjSpecialDao.findBycondition(hybjSpecial);
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void updateStatus(HybjSpecial hybjSpecial) {
		hybjSpecialDao.updateStatus(hybjSpecial);
	}

	@Override
	public void updatefeedback(HybjSpecial hybjSpecial) {
		hybjSpecialDao.updataFeedback(hybjSpecial);
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public List<HybjSpecial> saveReportWithExcel(HybjSpecialForm hybjSpecialForm) {
			try {
				File file = hybjSpecialForm.getFile();
				GenerateSqlFromExcel generater = new GenerateSqlFromExcel();

				List<String> cplist = new ArrayList<>();
				cplist.add("华数TV");
				cplist.add("芒果TV");
				cplist.add("爱奇艺");
				cplist.add("优酷");
				cplist.add("优朋");
				cplist.add("百视通");
				cplist.add("天翼视讯");
				cplist.add("腾讯");

				ArrayList<String[]> arrayList = generater.generateStationBugSql(file);
				List<HybjSpecial> list = new ArrayList<HybjSpecial>();;
				List<Serializable> idList = new ArrayList<Serializable>();
				for(int i=0;arrayList!=null && i<arrayList.size();i++){
					String[] data = arrayList.get(i);
					//实例化PO对象，用PO对象进行保存
					HybjSpecial hybjSpecial = new HybjSpecial();
					hybjSpecial.setSpecialName(data[1].toString());
					hybjSpecial.setItemName(data[2].toString());
					if(!cplist.contains(data[3].toString()) || !hybjSpecialForm.getCp().equals(data[3].toString())){ continue; }
					hybjSpecial.setCp(data[3].toString());
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					hybjSpecial.setCreateTime(format.format(new Date()));
					hybjSpecial.setStatus("draft");
					Serializable id = hybjSpecialDao.save(hybjSpecial);
					idList.add(id);
				}
				for (Serializable serializable : idList) {
					HybjSpecial special = hybjSpecialDao.findObjectByID(serializable);
					list.add(special);
				}


				return list;


			} catch (Exception e) {
				e.printStackTrace();

			}
			return null;
	}


	@Override
	public void updateStatusById(HybjSpecial special) {
		hybjSpecialDao.updateStatus(special);
	}
}
