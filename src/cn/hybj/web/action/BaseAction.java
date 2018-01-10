package cn.hybj.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.hybj.util.WhFastJsonUtil;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	@SuppressWarnings("unused")
	protected HttpServletRequest request = null;
	
	@SuppressWarnings("unused")
	protected HttpServletResponse response = null;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;	
	}
	
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * 正确返回的JSON格式模板
	 * 
	 * @return
	 */
	protected JSONObject getSuccessJsonTemplate() {
		JSONObject json = new JSONObject();
		json.put("returnCode", "SUCCESS");
		return json;
	}

	/**
	 * 异常返回的JSON格式模板
	 * 
	 * @return
	 */
	protected JSONObject getErrorJsonTemplate() {
		JSONObject json = new JSONObject();
		json.put("returnCode", "FAIL");
		return json;
	}

	/**
	 * 将数据写入inputStream流中，返回客户端
	 * 
	 * @param content
	 *            输入内容
	 */
	protected void writeStream(String content) {
		try {
			inputStream = new ByteArrayInputStream(content.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// 返回错误代码
			LOG.error("Invoke method getBytes(String charsetName), contains unsupported parameter charsetName.");
			inputStream = new ByteArrayInputStream(getErrorJsonTemplate().toString().getBytes());
		}
	}

	/**
	 * 将数据写入inputStream流中，返回客户端
	 * 
	 * @param content
	 *            输入内容
	 */
	protected void writeStream(JSONObject json) {
		try {
			inputStream = new ByteArrayInputStream(JSONObject.toJSONString(json, WhFastJsonUtil.getConfigDatetime())
					.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// 返回错误代码
			LOG.error("Invoke method getBytes(String charsetName), contains unsupported parameter charsetName.");
			inputStream = new ByteArrayInputStream(getErrorJsonTemplate().toString().getBytes());
		}
	}
}
