package cn.hybj.web.action;


import cn.hybj.container.ServiceProvider;
import cn.hybj.domain.HybjDemand;
import cn.hybj.domain.HybjSpecial;
import cn.hybj.service.IHybjDemandService;
import cn.hybj.service.IHybjLogService;
import cn.hybj.service.IHybjSpecialService;
import cn.hybj.web.form.HybjDemandForm;
import cn.hybj.web.form.HybjSpecialForm;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SuppressWarnings("serial")
public class HybjDownloadZTAction extends BaseAction implements ModelDriven<HybjSpecialForm>{
	private IHybjSpecialService hybjSpecialService = (IHybjSpecialService)ServiceProvider.getService(IHybjSpecialService.SERVICE_NAME);

	//调用日志管理的业务层
	private IHybjLogService elecLogService = (IHybjLogService)ServiceProvider.getService(IHybjLogService.SERVICE_NAME);
	private HybjSpecialForm hybjSpecialForm = new HybjSpecialForm();

	public HybjSpecialForm getHybjSpecialForm() {
		return hybjSpecialForm;
	}

	public void setHybjSpecialForm(HybjSpecialForm hybjSpecialForm) {
		this.hybjSpecialForm = hybjSpecialForm;
	}

	//使用log4j
	Log log = LogFactory.getLog(HybjDownloadZTAction.class);
	public HybjSpecialForm getModel() {
		return hybjSpecialForm;
	}

	private int specialId;
	private String dowFileName;

	public String getDowFileName() {
		return dowFileName;
	}

	public void setDowFileName(String dowFileName) {
		this.dowFileName = dowFileName;
	}

	public int getSpecialId() {
		return specialId;
	}

	public void setSpecialId(int specialId) {
		this.specialId = specialId;
	}

	public InputStream getFileInputStream() throws FileNotFoundException {
		HybjSpecial special = hybjSpecialService.findById(specialId);
		String url = special.getAttachmnetUrl();
		dowFileName = special.getAttachmentName();



		try {
			//读取服务器上指定目录下的文件
			FileInputStream fis = new FileInputStream(url);

			return fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
