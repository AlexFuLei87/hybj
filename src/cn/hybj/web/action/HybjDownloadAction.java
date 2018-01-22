package cn.hybj.web.action;


import cn.hybj.container.ServiceProvider;
import cn.hybj.domain.HybjDemand;
import cn.hybj.service.IHybjDemandService;
import cn.hybj.service.IHybjLogService;
import cn.hybj.web.form.HybjDemandForm;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

@SuppressWarnings("serial")
public class HybjDownloadAction extends BaseAction implements ModelDriven<HybjDemandForm>{
	private IHybjDemandService hybjDemandService = (IHybjDemandService)ServiceProvider.getService(IHybjDemandService.SERVICE_NAME);

	//调用日志管理的业务层
	private IHybjLogService elecLogService = (IHybjLogService)ServiceProvider.getService(IHybjLogService.SERVICE_NAME);
	private HybjDemandForm hybjDemandForm = new HybjDemandForm();

    public HybjDemandForm getHybjDemandForm() {
        return hybjDemandForm;
    }

    public void setHybjDemandForm(HybjDemandForm hybjDemandForm) {
        this.hybjDemandForm = hybjDemandForm;
    }

    //使用log4j
	Log log = LogFactory.getLog(HybjDownloadAction.class);
	public HybjDemandForm getModel() {
		return hybjDemandForm;
	}

	private String demandId;
	private String dowFileName;

	public String getDowFileName() {
		return dowFileName;
	}

	public void setDowFileName(String dowFileName) {
		this.dowFileName = dowFileName;
	}

	public String getDemandId() {
		return demandId;
	}

	public void setDemandId(String demandId) {
		this.demandId = demandId;
	}

	public InputStream getFileInputStream() throws FileNotFoundException {
		HybjDemand hybjDemand = hybjDemandService.findById(Integer.parseInt(demandId));
		String url = hybjDemand.getAttachmentUrl();
		dowFileName = hybjDemand.getAttachmentName();
		//String realPath = request.getSession().getServletContext().getRealPath("uploadFiles");
		//得到一个文件输


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
