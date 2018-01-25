package cn.hybj.web.action;


import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.List;

import cn.hybj.domain.*;
import cn.hybj.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.hybj.container.ServiceProvider;
import cn.hybj.util.LogonUtils;
import cn.hybj.util.MD5keyBean;
import cn.hybj.web.form.HybjCommonMsgForm;
import cn.hybj.web.form.HybjMenuForm;
import cn.hybj.web.form.HybjSystemDDlForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class HybjMenuAction extends BaseAction implements ModelDriven<HybjMenuForm>{
	private IHybjCommonMsgService elecCommonMsgService = (IHybjCommonMsgService)ServiceProvider.getService(IHybjCommonMsgService.SERVICE_NAME);
	
	private IHybjUserService elecUserService = (IHybjUserService)ServiceProvider.getService(IHybjUserService.SERVICE_NAME);
	
	private IHybjReportService hybjReportService = (IHybjReportService)ServiceProvider.getService(IHybjReportService.SERVICE_NAME);

	private IHybjNoticeService hybjNoticeService = (IHybjNoticeService)ServiceProvider.getService(IHybjNoticeService.SERVICE_NAME);

	private IHybjSpecialService hybjSpecialService = (IHybjSpecialService)ServiceProvider.getService(IHybjSpecialService.SERVICE_NAME);

	private IHybjSystemDDlService hybjSystemDDlService = (IHybjSystemDDlService)ServiceProvider.getService(IHybjSystemDDlService.SERVICE_NAME);
	//调用日志管理的业务层
	private IHybjLogService elecLogService = (IHybjLogService)ServiceProvider.getService(IHybjLogService.SERVICE_NAME);
	private HybjMenuForm elecMenuForm = new HybjMenuForm();
	
	//使用log4j
	Log log = LogFactory.getLog(HybjMenuAction.class);
	public HybjMenuForm getModel() {
		return elecMenuForm;
	}
	
	private String cpName;
	
	
	
	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

	/**  
	* @throws UnsupportedEncodingException 
	* @Name: home
	* @Description: 从登陆页面获取登陆名和密码，验证是否合法
	*               如果合法：则验证成功，跳转到home.jsp
	*               如果不合法：则验证失败，回退到index.jsp
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: String home 
	*               验证成功，跳转到home.jsp
	*               验证失败，回退到index.jsp
	*/
	public String home() throws UnsupportedEncodingException{
		//，添加验证码的校验功能 begin
		boolean flag = LogonUtils.checkNumber(request);
		if(!flag){
			this.addFieldError("error", "验证码为空或者有误");
			return "error";
		}
		//end
		//获取当前登录名和密码
		String name = elecMenuForm.getName();
		String password = elecMenuForm.getPassword();
		MD5keyBean md5 = new MD5keyBean();
		String md5password = md5.getkeyBeanofStr(password);
		//使用登录名查询数据库，获取用户的详细信息
		HybjUser elecUser = elecUserService.findElecUserByLogonName(name);
		if(elecUser==null){
			this.addFieldError("error", "您当前输入的登录名不存在");
			return "error";
		}
		if(password==null || password.equals("") || !elecUser.getLogonPwd().equals(md5password)){
			this.addFieldError("error", "您当前输入的密码有误或不存在");
			return "error";
		}
		request.getSession().setAttribute("globle_user", elecUser);
		//获取当前登录名所具有的权限
		String popedom = elecUserService.findElecPopedomByLogonName(name);
		if(popedom==null || "".equals(popedom)){
			this.addFieldError("error", "当前登录名没有分配权限，请与管理员联系");
			return "error";
		}
		request.getSession().setAttribute("globle_popedom", popedom);
		//获取当前登录名所具有的角色
		Hashtable<String, String> ht = elecUserService.findElecRoleByLogonName(name);
		if(ht==null){
			this.addFieldError("error", "当前登录名没有分配角色，请与管理员联系");
			return "error";
		}
		request.getSession().setAttribute("globle_role", ht);
		//添加记住我的功能，记住当前操作的用户名和密码 begin
		LogonUtils.remeberMeByCookie(request,response);
		//end
		
		//添加日志管理模块维护系统的性能安全 begin
		//使用log4j
//		java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
//		String d = date.toString();
//		log.info("用户名:【"+elecUser.getUserName()+"】登录系统！时间是:"+d);
		//使用数据库
		elecLogService.saveElecLog(request,"登录模块：当前用户【"+elecUser.getUserName()+"】登录系统");
		//end
		return "home";
	}
	
	public String updatePasswd(){
		return "updatePasswd";
	}
	public String hs(){
		return "hs";
	}
	
	public String title(){
		return "title";
	}
	
	public String left(){
        HybjUser user = (HybjUser)request.getSession().getAttribute("globle_user");
        request.setAttribute("department",user.getDepartment());
	    return "left";
	}
	
	public String change1(){
		return "change1";
	}
	
	public String loading(){
		return "loading";
	}

	/**
	 * cp为通过审核的内容
	 * @return
	 * @throws Exception
	 */
	public String alermWTG() throws Exception{
		String name=request.getParameter("cpName"); 
		byte[] bytes =name.getBytes("iso-8859-1");//退回错误的解码，让字符串通过iso-8859-1返回到字节数据，即还原字节数据
		name = new String(bytes, "utf-8");//重新使用正确的utf-8来解码。
		String status = "fail"; 
		List<HybjReport> list = hybjReportService.findReprotByStatusAndCp(status,name);
		request.setAttribute("reportList", list);
		return "alermWTG";
	}
	
	
	/**
	 * cp通过审核的内容
	 * @return
	 * @throws Exception
	 */
	public String alermTG() throws Exception{
		String name=request.getParameter("cpName"); 
		byte[] bytes =name.getBytes("iso-8859-1");
		name = new String(bytes, "utf-8");
		String status = "pass"; 
		List<HybjReport> list = hybjReportService.findReprotByStatusAndCp(status,name);
		request.setAttribute("reportList", list);
		return "alermTG";
	}
	/**
	 * 公示区的内容
	 * @return
	 */
	public String alermGS(){
		//List<HybjCommonMsgForm> findCommonMsgListByCurrentDate();
		List<HybjSystemDDlForm> jctList = hybjSystemDDlService.findElecSystemDDlListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
		List<HybjReport> gsList = hybjReportService.findPassOrFail();
		request.setAttribute("gsList", gsList);
		return "alermGS";
	}
	/**
	 * 其余cp的上报情况
	 * @return
	 * @throws Exception 
	 */
	public String alermSB() throws Exception{
		String name=request.getParameter("cpName"); 
		byte[] bytes =name.getBytes("iso-8859-1");
		name = new String(bytes, "utf-8");
		List<HybjReport> others = hybjReportService.findOtherCpPsss(name);
		request.setAttribute("others", others);
		return "alermSB";
	}
	
	/**
	 * 草稿状态的上报内容
	 * @return
	 * @throws Exception
	 */
	public String alermCG() throws Exception{
		String name=request.getParameter("cpName"); 
		byte[] bytes =name.getBytes("iso-8859-1");//退回错误的解码，让字符串通过iso-8859-1返回到字节数据，即还原字节数据
		name = new String(bytes, "utf-8");//重新使用正确的utf-8来解码。
		String status = "draft"; 
		List<HybjReport> list = hybjReportService.findReprotByStatusAndCp(status,name);
		request.setAttribute("cgList", list);
		return "alermCG";
	}

	/**
	 * 公示区展示
	 * @return
	 */
	public String alermGG() {
		List<HybjOutline> lists = hybjNoticeService.findGG();
		request.setAttribute("ggList", lists);
		return "alermGG";
	}
	/**  
	* @Name: logout
	* @Description: 重新回退到登录页面
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: String logout 跳转到index.jsp
	*/
	public String logout(){
		//清空session
		request.getSession().invalidate();
		return "logout";
	}
	public String alermZT(){
		List<HybjSystemDDlForm> jctList = hybjSystemDDlService.findElecSystemDDlListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
		List<HybjSpecial> list = hybjSpecialService.findPassAndFail();
		request.setAttribute("ztList", list);
		return "alermZT";
	}
}
