package cn.hybj.web.action;


import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import cn.hybj.container.ServiceProvider;
import cn.hybj.domain.HybjSystemDDl;
import cn.hybj.service.IHybjLogService;
import cn.hybj.service.IHybjSystemDDlService;
import cn.hybj.service.IHybjUserService;
import cn.hybj.util.ChartUtils;
import cn.hybj.util.ExcelFileGenerator;
import cn.hybj.web.form.HybjSystemDDlForm;
import cn.hybj.web.form.HybjUserForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class HybjUserAction extends BaseAction implements ModelDriven<HybjUserForm>{

	private IHybjUserService elecUserService = (IHybjUserService)ServiceProvider.getService(IHybjUserService.SERVICE_NAME);
	
	private IHybjSystemDDlService elecSystemDDlService = (IHybjSystemDDlService)ServiceProvider.getService(IHybjSystemDDlService.SERVICE_NAME);
	
	private IHybjLogService elecLogService = (IHybjLogService)ServiceProvider.getService(IHybjLogService.SERVICE_NAME);
	
	private HybjUserForm elecUserForm = new HybjUserForm();
	
	public HybjUserForm getModel() {
		return elecUserForm;
	}
	
	/**  
	* @Name: home
	* @Description: 查询用户列表信息
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: String home 跳转到userIndex.jsp
	*/
	public String home(){
		//，添加分页功能，分页操作需要一个Request对象
		List<HybjUserForm> list = elecUserService.findElecUserList(elecUserForm,request);
		request.setAttribute("userList", list);
		//使用initflag标识，判断当前跳转的userIndex.jsp还是userList.jsp
		String initflag = request.getParameter("initflag");
		if(initflag!=null && initflag.equals("1")){
			return "userlist";
		}
		//end
		return "home";
	}
	/**  
	* @Name: add
	* @Description: 跳转到添加用户的页面
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: String add 跳转到userAdd.jsp
	*/
	public String add(){
		List<HybjSystemDDlForm> sexList = elecSystemDDlService.findElecSystemDDlListByKeyword("性别");
		List<HybjSystemDDlForm> jctList = elecSystemDDlService.findElecSystemDDlListByKeyword("所属单位");
		HybjSystemDDlForm form = new HybjSystemDDlForm();
		HybjSystemDDlForm form1 = new HybjSystemDDlForm();
		form.setDdlName("电信");
		form1.setDdlName("聚合");
		jctList.add(form);
		jctList.add(form1);
		List<HybjSystemDDlForm> isdutyList = elecSystemDDlService.findElecSystemDDlListByKeyword("是否在职");
		request.setAttribute("sexList", sexList);
		request.setAttribute("jctList", jctList);
		request.setAttribute("isdutyList", isdutyList);
		return "add";
	}
	
	/**  
	* @Name: initSystemDDl
	* @Description:初始化新增和编辑用户页面中使用的数据字典项
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: 无
	*/
	private void initSystemDDl() {
		/**
		 * 使用数据类型进行查询，获取对应数据类型下的数据项编号和数据项名称
		 * 查询性别、所属单位、是否在职
		 */
		List<HybjSystemDDlForm> sexList = elecSystemDDlService.findElecSystemDDlListByKeyword("性别");
		List<HybjSystemDDlForm> jctList = elecSystemDDlService.findElecSystemDDlListByKeyword("所属单位");
		List<HybjSystemDDlForm> isdutyList = elecSystemDDlService.findElecSystemDDlListByKeyword("是否在职");
		request.setAttribute("sexList", sexList);
		request.setAttribute("jctList", jctList);
		request.setAttribute("isdutyList", isdutyList);		
	}

	/**  
	* @Name: save
	* @Description: 保存用户信息
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2011-12-26 （创建日期）
	* @Parameters: 无
	* @Return: String save 重定向到userIndex.jsp
	*/
	public String save(){
		elecUserService.saveElecUser(elecUserForm);
		//修改，将新增和修改的信息添加到日志表中 begin
		if(elecUserForm.getUserID()!=null && !elecUserForm.getUserID().equals("")){
			//修改保存
			elecLogService.saveElecLog(request, "用户管理：修改用户【"+elecUserForm.getUserName()+"】的信息");
		}
		else{
			//新增保存
			elecLogService.saveElecLog(request, "用户管理：新增用户【"+elecUserForm.getUserName()+"】的信息");
		}
		//end
		//2011-12-29日修改，添加跳转的标识
		//如果roleflag==1，需要跳转到userEdit.jsp
		//如果roleflag==“”，需要跳转到userIndex.jsp
		String roleflag = request.getParameter("roleflag");
		if(roleflag!=null && roleflag.equals("1")){
			return edit();
		}
		return "list";
	}
	
	/**  
	* @Name: edit
	* @Description: 跳转到编辑的页面
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2011-12-26 （创建日期）
	* @Parameters: 无
	* @Return: String edit 跳转到userEdit.jsp
	*/
	public String edit(){
		elecUserForm = elecUserService.findElecUser(elecUserForm);
		//使用值栈的形式传递elecUserForm对象
		//ActionContext.getContext().getValueStack().push(elecUserForm);
		this.initSystemDDl();
		/**
		 *  使用viewflag字段
		 *  判断当前用户跳转的是编辑页面还是明细页面
		 *    * 如果viewflag==null：说明当前操作的是编辑页面
		 *    * 如果viewflag==1:说明当前操作的是明细页面
		 */
		String viewflag = elecUserForm.getViewflag();
		request.setAttribute("viewflag", viewflag);
		/**
		 * 2011-12-29日修改，判断点击左侧”用户管理”的连接
		 *    * 如果当前操作人是系统管理员或者是高级管理员的时候，则点击“用户管理”的时候
	     *      跳转到userIndex.jsp，可以查看用户列表信息
	     *    * 如果当前操作人不是系统管理员或者是高级管理员的时候，则点击“用户管理”的时候
	     *      需要跳转到userEdit.jsp，可以对当前登录人进行编辑并保存
		 */
		String roleflag = elecUserForm.getRoleflag();
		request.setAttribute("roleflag", roleflag);
		return "edit";
	}
	/**  
	* @Name: delete
	* @Description: 删除用户信息
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: String delete 跳转到userIndex.jsp
	*/
	public String delete(){
		elecUserService.deleteElecUser(elecUserForm);
		return "list";
	}
	/**  
	* @Name: export
	* @Description:导出excel的报表数据
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: 无
	*/
	public String export(){
		//获取导出的表头和数据
		//获取表头,存放到ArrayList filedName对象中(登录名	用户姓名	性别	联系电话	是否在职)
		ArrayList filedName = elecUserService.getExcelFiledNameList(); 
		/**获取数据,(zhugeliang	诸葛亮	男	88886666	是
	 	            liubei	    刘备  	男	12345678	是
	                )
	       将zhugeliang	诸葛亮	男	88886666	是值存放到ArrayList dataList集合中
	       再实例化一个ArrayList filedData集合 filedData.add(dataList);
        */
		ArrayList filedData = elecUserService.getExcelFiledDataList(elecUserForm);
		try {
			//获取输出流
			OutputStream out = response.getOutputStream();
			//重置输出流
			response.reset();
			//设置导出Excel报表的导出形式
			response.setContentType("application/vnd.ms-excel");
			ExcelFileGenerator generator = new ExcelFileGenerator(filedName,filedData);
			generator.expordExcel(out);
			//设置输出形式
			System.setOut(new PrintStream(out));
			//刷新输出流
			out.flush();
			//关闭输出流
			if(out!=null){
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**  
	* @Name: importpage
	* @Description:跳转到导入excel的页面
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: String 跳转到userImport.jsp
	*/
	public String importpage(){
		return "importpage";
	}
	/**  
	* @Name: importdata
	* @Description:从excel中读取数据，存放到数据库中
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: String 跳转到userImport.jsp
	*/
	public String importdata(){
		elecUserService.saveElecUserWithExcel(elecUserForm);
		return "importdata";
	}
	/**  
	* @Name: chart
	* @Description:使用柱状图按照所属单位统计用户
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: String 跳转到userReport.jsp
	*/
	public String chart(){
		List<HybjUserForm> list = elecUserService.findUserByChart();
		String filename = ChartUtils.getUserBarChart(list);
		request.setAttribute("filename", filename);
		return "chart";
	}
}
