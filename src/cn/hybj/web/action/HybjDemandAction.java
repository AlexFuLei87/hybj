package cn.hybj.web.action;


import cn.hybj.container.ServiceProvider;
import cn.hybj.service.*;
import cn.hybj.web.form.HybjDemandForm;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.UUID;

@SuppressWarnings("serial")
public class HybjDemandAction extends BaseAction implements ModelDriven<HybjDemandForm>{
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
	Log log = LogFactory.getLog(HybjDemandAction.class);
	public HybjDemandForm getModel() {
		return hybjDemandForm;
	}


	public String save() throws Exception {
		hybjDemandForm.setStatus("normal");
		File file = hybjDemandForm.getFile();
		InputStream  is = new FileInputStream(file);
		String newFilename = "";
		if (file != null) {
			String path = request.getSession().getServletContext().getRealPath("uploadFiles");
			File file1 = new File(path);
			if (!file1.exists()){
				//创建目录
				file1.mkdirs();
			}
			String saveFileName = System.currentTimeMillis()+ hybjDemandForm.getFileFileName().substring(hybjDemandForm.getFileFileName().indexOf("."));
			File realFile = new File(path,saveFileName);


			//保存文件到目指定目录中
			try {
				FileUtils.copyFile(file, realFile);
				hybjDemandForm.setAttachmentUrl(path+"/"+saveFileName);
				hybjDemandService.save(hybjDemandForm);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return "demandAdd";
	}


}
