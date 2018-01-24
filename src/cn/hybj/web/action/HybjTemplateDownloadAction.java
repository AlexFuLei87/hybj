package cn.hybj.web.action;


import cn.hybj.container.ServiceProvider;
import cn.hybj.domain.HybjDemand;
import cn.hybj.service.IHybjDemandService;
import cn.hybj.service.IHybjLogService;
import cn.hybj.web.form.HybjDemandForm;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SuppressWarnings("serial")
public class HybjTemplateDownloadAction extends BaseAction {

	private String tempName;

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	private String tempType;

	public String getTempType() {
		return tempType;
	}

	public void setTempType(String tempType) {
		this.tempType = tempType;
	}

	public InputStream getFileInputStream() throws Exception {

		String url = request.getSession().getServletContext().getRealPath("");
		//得到一个文件输E:\hybj_dx\out\artifacts\ah_jh_war_exploded\WEB-INF\classes\template\simple
		if("offline".equals(tempType)){
			url += "/WEB-INF/classes/template/simple/下线报备模板 .xls";
			tempName = "下线报备模板.xls";
		}else if("online".equals(tempType)){
			url += "/WEB-INF/classes/template/simple/上线报备模板.xls";
			tempName = "上线报备模板.xls";
		}else if("zt".equals(tempType)){
			url += "/WEB-INF/classes/template/simple/专题上报模板.xls";
			tempName = "专题上报模板.xls";
		}
		this.tempName = new String(tempName.getBytes(),"ISO8859-1");
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
