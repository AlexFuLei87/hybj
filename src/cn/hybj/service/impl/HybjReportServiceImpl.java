package cn.hybj.service.impl;



import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.hybj.dao.IHybjReportDao;
import cn.hybj.domain.HybjReport;
import cn.hybj.domain.HybjUser;
import cn.hybj.service.IHybjReportService;
import cn.hybj.util.GenerateSqlFromExcel;
import cn.hybj.util.MD5keyBean;
import cn.hybj.util.StringHelper;
import cn.hybj.web.form.HybjReportForm;
@Transactional(readOnly=true)
@Service(IHybjReportService.SERVICE_NAME)
public class HybjReportServiceImpl implements IHybjReportService {
	
	@Resource(name=IHybjReportDao.SERVICE_NAME)
	private IHybjReportDao hybjReportDao;

	
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveReport(HybjReportForm hybjReportForm) {
		HybjReport hybjReport = new HybjReport();
		hybjReport.setIsCharge(hybjReportForm.isCharge());
		hybjReport.setIsJh(hybjReportForm.isIsjh());
		hybjReport.setCp(hybjReportForm.getCp());
		hybjReport.setItemName(hybjReportForm.getItemName());
		hybjReport.setProgramaName(hybjReportForm.getProgramaName());
		hybjReport.setOnlineTime(hybjReportForm.getOnlineTime());
		hybjReport.setPreonlineTime(hybjReportForm.getPreOnlineTime());
		hybjReport.setRemarks(hybjReportForm.getRemarks());
		hybjReport.setType(hybjReportForm.getType());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		hybjReport.setCreateTime(format.format(new Date()).toString());
		hybjReport.setStatus(hybjReportForm.getStatus());
		hybjReport.setReportStatus(hybjReportForm.getReportStatus());
		hybjReport.setOfflineReason(hybjReportForm.getOfflineReason());
		hybjReportDao.save(hybjReport);
	}




	public int findReportByItemName(String itemName) {
		return hybjReportDao.findReportByItemName(itemName);
		
	}


	public List<HybjReport> findPassOrFail() {
		return hybjReportDao.findPassOrFail();
	}


	public List<HybjReport> findReprotByStatusAndCp(String status, String cp) {
		return hybjReportDao.findByStatusAndCp(status,cp);
	}

	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public List<HybjReport> saveReportWithExcel(HybjReportForm hybjReportForm) {
		try {
			File file = hybjReportForm.getFile();
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
			List<HybjReport> list = new ArrayList<HybjReport>();;
			List<Serializable> idList = new ArrayList<Serializable>();
			for(int i=0;arrayList!=null && i<arrayList.size();i++){
				String[] data = arrayList.get(i);
				//实例化PO对象，用PO对象进行保存
				HybjReport hybjReport = new HybjReport();
				//
				hybjReport.setPreonlineTime(data[1].toString());
				hybjReport.setOnlineTime(data[2].toString());
				if (data[3].toString() == "" || data[3].toString().isEmpty() ||data[3].toString().equals("")) {
					continue;
				}
				hybjReport.setStatus("draft");
				hybjReport.setItemName(data[3].toString());
				hybjReport.setType(data[4].toString());
				hybjReport.setIsCharge(data[5].toString()=="N"?false:true);
				//如果不是cp中的就 不保存
				if(!cplist.contains(data[6].toString())&& !hybjReportForm.getCp().equals(data[6].toString())){ continue; }
				hybjReport.setCp(data[6].toString());
				hybjReport.setIsJh(data[7].toString()=="未同步"?false:true);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				hybjReport.setCreateTime(format.format(new Date()).toString());
				hybjReport.setReportStatus("online");
				Serializable id = hybjReportDao.save(hybjReport);
				idList.add(id);
			}
			for (Serializable serializable : idList) {
				HybjReport report = hybjReportDao.findObjectByID(serializable);
				list.add(report);
			}
			
			
			return list;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		return null;
		
		
	}


	public List<HybjReport> findOtherCpPsss(String name) {
		return hybjReportDao.findOtherCpPsss(name);
	}


	public void updateStatusById(int id) {
		hybjReportDao.updateStatusById(id);
		
	}

	@Override
	public void updateById(int id, String status) {
		hybjReportDao.updateById(id,status);
	}

	@Override
	public HybjReport getItemById(int i) {
		return hybjReportDao.findObjectByID(i);
	}

	@Override
    @Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void updateReport(HybjReportForm hybjReportForm) {
		HybjReport hybjReport = new HybjReport();
		hybjReport.setId(hybjReportForm.getId());
		hybjReport.setPreonlineTime(hybjReportForm.getPreOnlineTime());
		hybjReport.setOnlineTime(hybjReportForm.getOnlineTime());
		hybjReport.setStatus(hybjReportForm.getStatus());
		hybjReport.setItemName(hybjReportForm.getItemName());
		hybjReport.setType(hybjReportForm.getType());
		hybjReport.setIsCharge(hybjReportForm.isCharge());
		hybjReport.setCp(hybjReportForm.getCp());
		hybjReport.setIsJh(hybjReportForm.isIsjh());
		hybjReport.setProgramaName(hybjReportForm.getProgramaName());
		hybjReport.setRemarks(hybjReportForm.getRemarks());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		hybjReport.setCreateTime(format.format(new Date()).toString());
		hybjReportDao.update(hybjReport);
	}


	@Override
	public List<HybjReport> findByFuzzy(HybjReport hybjReport) {
		return hybjReportDao.findByFuzzy(hybjReport);
	}
}
