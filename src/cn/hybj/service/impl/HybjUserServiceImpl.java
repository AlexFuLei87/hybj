package cn.hybj.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.hybj.dao.IHybjSystemDDlDao;
import cn.hybj.dao.IHybjUserDao;
import cn.hybj.domain.HybjUser;
import cn.hybj.service.IHybjUserService;
import cn.hybj.util.GenerateSqlFromExcel;
import cn.hybj.util.MD5keyBean;
import cn.hybj.util.PageInfo;
import cn.hybj.util.StringHelper;
import cn.hybj.web.form.HybjUserForm;
@Transactional(readOnly=true)
@Service(IHybjUserService.SERVICE_NAME)
public class HybjUserServiceImpl implements IHybjUserService {
	
	@Resource(name=IHybjUserDao.SERVICE_NAME)
	private IHybjUserDao elecUserDao;

	@Resource(name=IHybjSystemDDlDao.SERVICE_NAME)
	private IHybjSystemDDlDao elecSystemDDlDao;
	/**  
	* @Name: findElecUserList
	* @Description: 查询用户列表信息，
	*               判断用户姓名是否为空，如果不为空按照用户姓名组织查询条件
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: ElecUserForm elecUserForm VO对象存放用户姓名
	* @Return: List<ElecUserForm> 用户信息结果集对象
	*/
	public List<HybjUserForm> findElecUserList(HybjUserForm elecUserForm,HttpServletRequest request) {
		//组织查询条件
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if(elecUserForm!=null && elecUserForm.getUserName()!=null && !elecUserForm.getUserName().equals("")){
			hqlWhere += " and t.userName like ?";
			paramsList.add("%" + elecUserForm.getUserName() + "%");
		}
		Object [] params = paramsList.toArray();
		//组织排序
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("t.onDutyDate", "desc");
		//修改，添加分页功能 begin
		PageInfo pageInfo = new PageInfo(request);
//		List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(hqlWhere, params, orderby);
		List<HybjUser> list = elecUserDao.findCollectionByConditionWithPage(hqlWhere, params, orderby ,pageInfo);
		request.setAttribute("page", pageInfo.getPageBean());
		//end
		List<HybjUserForm> formList = this.elecUserPOListToVOList(list);
		return formList;
	}
	/**  
	* @Name: elecUserPOListToVOList
	* @Description: 获取的用户列表中的值从PO对象转换成VO对象
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: List<ElecUser> list 存放PO对象
	* @Return: List<ElecUserForm> 存放VO对象
	*/
	private List<HybjUserForm> elecUserPOListToVOList(List<HybjUser> list) {
		List<HybjUserForm> formList = new ArrayList<HybjUserForm>();
		HybjUserForm elecUserForm = null;
		for(int i=0;list!=null && i<list.size();i++){
			HybjUser elecUser = list.get(i);
			elecUserForm = new HybjUserForm();
			elecUserForm.setUserID(elecUser.getUserID());
			elecUserForm.setLogonName(elecUser.getLogonName());
			elecUserForm.setUserName(elecUser.getUserName());
			elecUserForm.setSexID(elecUser.getSexID()!=null && !elecUser.getSexID().equals("")?elecSystemDDlDao.findDDlName(elecUser.getSexID(),"性别"):"");
			elecUserForm.setContactTel(elecUser.getContactTel());
			elecUserForm.setIsDuty(elecUser.getIsDuty()!=null && !elecUser.getIsDuty().equals("")?elecSystemDDlDao.findDDlName(elecUser.getIsDuty(),"是否在职"):"");
			formList.add(elecUserForm);
		}
		return formList;
	}
	/**  
	* @Name: saveElecUser
	* @Description: 保存用户信息
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: ElecUserForm elecUserForm VO对象用于存放用户信息
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecUser(HybjUserForm elecUserForm) {
		//VO对象转换成PO对象
		HybjUser elecUser = this.elecUserVOToPO(elecUserForm);
		if(elecUserForm.getUserID()!=null && !elecUserForm.getUserID().equals("")){
			elecUserDao.update(elecUser);
		}
		else{
			elecUserDao.save(elecUser);
		}
	}
	/**  
	* @Name: elecUserVOToPO
	* @Description: 将用户信息从VO对象转换PO对象
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: ElecUserForm elecUserForm VO对象用于存放用户信息
	* @Return: ElecUser PO对象，用于保存用户信息
	*/
	private HybjUser elecUserVOToPO(HybjUserForm elecUserForm) {
		HybjUser elecUser = new HybjUser();
		if(elecUserForm!=null){
			if(elecUserForm.getUserID()!=null && !elecUserForm.getUserID().equals("")){
				elecUser.setUserID(elecUserForm.getUserID());
				if(elecUserForm.getOffDutyDate()!=null && !elecUserForm.getOffDutyDate().equals("")){
					elecUser.setOffDutyDate(StringHelper.stringConvertDate(elecUserForm.getOffDutyDate()));
				}
			}
			elecUser.setJctID(elecUserForm.getJctID());
			elecUser.setUserName(elecUserForm.getUserName());
			elecUser.setLogonName(elecUserForm.getLogonName());
			//2011-12-29日修改，使用md5进行密码加密 begin
			String password = elecUserForm.getLogonPwd();
			String md5password = "";
			//初始化密码，密码为000000
			if(password==null || "".equals(password)){
				password = "000000";
			}
			String md5flag = elecUserForm.getMd5flag();
			//修改时2次密码一致，不需要进行密码加密
			if(md5flag!=null && md5flag.equals("1")){
				md5password = password;
			}
			//无论是新增，还是处理修改（修改了当前密码），都需要进行密码加密
			else{
				MD5keyBean md5 = new MD5keyBean();
				md5password = md5.getkeyBeanofStr(password);
			}
			elecUser.setLogonPwd(md5password);
			//end
			elecUser.setSexID(elecUserForm.getSexID());
			if(elecUserForm.getBirthday()!=null && !elecUserForm.getBirthday().equals("")){
				elecUser.setBirthday(StringHelper.stringConvertDate(elecUserForm.getBirthday()));
			}
			elecUser.setDepartment(elecUserForm.getDepartment());
			elecUser.setContactTel(elecUserForm.getContactTel());
			elecUser.setEmail(elecUserForm.getEmail());
			elecUser.setMobile(elecUserForm.getMobile());
			elecUser.setIsDuty(elecUserForm.getIsDuty());
			if(elecUserForm.getOnDutyDate()!=null && !elecUserForm.getOnDutyDate().equals("")){
				elecUser.setOnDutyDate(StringHelper.stringConvertDate(elecUserForm.getOnDutyDate()));
			}
			elecUser.setRemark(elecUserForm.getRemark());
		}
		return elecUser;
	}
	/**  
	* @Name: findElecUser
	* @Description: 使用用户ID进行查询，获取用户的详细信息
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: ElecUserForm elecUserForm VO对象用于存放用户ID
	* @Return: ElecUserForm VO对象存放用户的详细信息
	*/
	public HybjUserForm findElecUser(HybjUserForm elecUserForm) {
		String userID = elecUserForm.getUserID();
		HybjUser elecUser = elecUserDao.findObjectByID(userID);
		//PO对象转换成VO对象
		elecUserForm = this.elecUserPOToVO(elecUser,elecUserForm);
		return elecUserForm;
	}
	/**  
	* @Name: elecUserPOToVO
	* @Description: 获取用户的详细信息，从PO对象转换成VO对象
	* @Author: 付磊（作者）
	* @Parameters: ElecUser elecUser PO对象存放用户详细信息
	* @Return: ElecUserForm VO对象存放用户的详细信息
	*/
	private HybjUserForm elecUserPOToVO(HybjUser elecUser,HybjUserForm elecUserForm) {
		//ElecUserForm elecUserForm = new ElecUserForm();
		if(elecUser!=null){
			elecUserForm.setUserID(elecUser.getUserID());
			elecUserForm.setJctID(elecUser.getJctID());
			elecUserForm.setUserName(elecUser.getUserName());
			elecUserForm.setLogonName(elecUser.getLogonName());
			elecUserForm.setLogonPwd(elecUser.getLogonPwd());
			elecUserForm.setSexID(elecUser.getSexID());
			elecUserForm.setBirthday(String.valueOf(elecUser.getBirthday()!=null && !elecUser.getBirthday().equals("")?elecUser.getBirthday():""));
			elecUserForm.setDepartment(elecUser.getDepartment());
			elecUserForm.setContactTel(elecUser.getContactTel());
			elecUserForm.setEmail(elecUser.getEmail());
			elecUserForm.setMobile(elecUser.getMobile());
			elecUserForm.setIsDuty(elecUser.getIsDuty());
			elecUserForm.setOnDutyDate(String.valueOf(elecUser.getOnDutyDate()!=null && !elecUser.getOnDutyDate().equals("")?elecUser.getOnDutyDate():""));
			elecUserForm.setOffDutyDate(String.valueOf(elecUser.getOffDutyDate()!=null && !elecUser.getOffDutyDate().equals("")?elecUser.getOffDutyDate():""));
			elecUserForm.setRemark(elecUser.getRemark());
		}
		return elecUserForm;
	}
	/**  
	* @Name: deleteElecUser
	* @Description: 从页面中获取userID的值，通过userID删除用户信息
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: ElecUserForm elecUserForm VO对象存放用户ID
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteElecUser(HybjUserForm elecUserForm) {
		String userID = elecUserForm.getUserID();
		elecUserDao.deleteObjectByIDs(userID);
	}
	/**  
	* @Name: checkLogonName
	* @Description: 使用登录名作为条件查询数据库，判断当前登录名在数据库中是否存在
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: String logonName 登录名
	* @Return: String  checkflag=1：如果值为1，说明当前登录名在数据库中有重复记录，则不能进行保存
	*                  checkflag=2：如果值为2，说明当前登录名在数据库中没有重复值，可以进行保存
	*/
	public String checkLogonName(String logonName) {
		String hqlWhere = " and o.logonName = ?";
		Object [] params = {logonName};
		List<HybjUser> list = elecUserDao.findCollectionByConditionNoPage(hqlWhere, params, null);
		String checkflag = "";
		if(list!=null && list.size()>0){
			checkflag = "1";
		}
		else{
			checkflag = "2";
		}
		return checkflag;
	}
	/**  
	* @Name: findElecUserByLogonName
	* @Description: 使用登录名获取用户的详细信息，用于首页登录的校验
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: String name 登录名
	* @Return: ElecUser 存放用户详细信息
	*/
	public HybjUser findElecUserByLogonName(String name) {
		String hqlWhere = " and o.logonName = ?";
		Object [] params = {name};
		List<HybjUser> list = elecUserDao.findCollectionByConditionNoPage(hqlWhere, params, null);
		HybjUser elecUser = null;
		if(list!=null && list.size()>0){
			elecUser = list.get(0);
		}
		return elecUser;
	}
	/**  
	* @Name: findElecPopedomByLogonName
	* @Description: 使用登录名获取当前登录名所具有的权限
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: String name 登录名
	* @Return: String 用户存放该用户具有的权限
	*/
	public String findElecPopedomByLogonName(String name) {
		List<Object> list = elecUserDao.findElecPopedomByLogonName(name);
		StringBuffer buffer = new StringBuffer("");
		for(int i=0;list!=null && i<list.size();i++){
			Object object = list.get(i);
			buffer.append(object.toString());
		}
		return buffer.toString();
	}
	/**  
	* @Name: findElecRoleByLogonName
	* @Description: 使用登录名获取当前登录名所具有的角色
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: String name 登录名
	* @Return: Hashtable<String, String> 存放角色的集合
	*/
	public Hashtable<String, String> findElecRoleByLogonName(String name) {
		List<Object[]> list = elecUserDao.findRoleByLogonName(name);
		Hashtable<String, String> ht = null;
		if(list!=null && list.size()>0){
			ht = new Hashtable<String, String>();
			for(int i=0;i<list.size();i++){
				Object[] object = list.get(i);
				ht.put(object[0].toString(), object[1].toString());
			}
		}
		return ht;
	}
	/**  
	* @Name: getExcelFiledNameList
	* @Description: 获取excel的标题数据（登录名,用户姓名,性别	联系电话	是否在职）
	*               放到ArrayList集合中
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: ArrayList(Excel标题集数据)
	*/
	public ArrayList getExcelFiledNameList() {
		String [] titles = {"登录名","用户姓名","性别","联系电话","是否在职"};
		ArrayList filedName = new ArrayList();
		for(int i=0;i<titles.length;i++){
			String title = titles[i];
			filedName.add(title);
		}
		return filedName;
	}
	/**  
	* @Name: getExcelFiledDataList
	* @Description: 获取excel的数据内容
	* 	   获取数据,(zhugeliang	诸葛亮	男	88886666	是
	 	            liubei	    刘备  	男	12345678	是
	                )
	       将zhugeliang	诸葛亮	男	88886666	是值存放到ArrayList dataList集合中
	       再实例化一个ArrayList filedData集合 filedData.add(dataList);
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: ArrayList(Excel标题集数据)
	*/
	public ArrayList getExcelFiledDataList(HybjUserForm elecUserForm) {
		//组织查询条件
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if(elecUserForm!=null && elecUserForm.getUserName()!=null && !elecUserForm.getUserName().equals("")){
			hqlWhere += " and o.userName like ?";
			paramsList.add("%" + elecUserForm.getUserName() + "%");
		}
		Object [] params = paramsList.toArray();
		//组织排序
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.onDutyDate", "desc");
		List<HybjUser> list = elecUserDao.findCollectionByConditionNoPage(hqlWhere, params, orderby);
		List<HybjUserForm> formList = this.elecUserPOListToVOList(list);
		//构造报表导出数据
		ArrayList filedData = new ArrayList();
		for(int i=0;formList!=null && i<formList.size();i++){
			ArrayList dataList = new ArrayList();
			HybjUserForm userForm = formList.get(i);
			//zhugeliang	诸葛亮	男	88886666	是
			dataList.add(userForm.getLogonName());
			dataList.add(userForm.getUserName());
			dataList.add(userForm.getSexID());
			dataList.add(userForm.getContactTel());
			dataList.add(userForm.getIsDuty());
			filedData.add(dataList);
		}
		return filedData;
	}
	/**  
	* @Name: saveElecUserWithExcel
	* @Description: 从Excel中读取用户信息数据，保存到数据库的表Elec_User中
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: ElecUserForm elecUserForm 存放Excel文件的流对象
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecUserWithExcel(HybjUserForm elecUserForm) {
		try {
			File file = elecUserForm.getFile();
			GenerateSqlFromExcel generater = new GenerateSqlFromExcel();
			ArrayList<String[]> arrayList = generater.generateStationBugSql(file);
			MD5keyBean md5 = new MD5keyBean();
			for(int i=0;arrayList!=null && i<arrayList.size();i++){
				String[] data = arrayList.get(i);
				//实例化PO对象，用PO对象进行保存
				HybjUser elecUser = new HybjUser();
				//登录名	密码	用户姓名	性别	所属单位	联系地址	是否在职
				elecUser.setLogonName(data[0].toString());
				elecUser.setLogonPwd(md5.getkeyBeanofStr(data[1].toString()));
				elecUser.setUserName(data[2].toString());
				elecUser.setSexID(data[3].toString());
				elecUser.setJctID(data[4].toString());
				elecUser.setContactTel(data[5].toString());
				elecUser.setIsDuty(data[6].toString());
				elecUser.setBirthday(StringHelper.stringConvertDate(data[7].toString()));
				elecUserDao.save(elecUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**  
	* @Name: findUserByChart
	* @Description:使用柱状图按照所属单位统计用户数量
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: List<ElecUserForm> 结果集对象
	*/
	public List<HybjUserForm> findUserByChart() {
		List<Object[]> list = elecUserDao.findUserByChart();
		List<HybjUserForm> formList = this.userChartPOListToVOList(list);
		return formList;
	}
	/**  
	* @Name: userChartPOListToVOList
	* @Description:使用柱状图按照所属单位统计用户数量，将PO对象转换成VO对象
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: 无
	* @Return: List<ElecUserForm> 结果集对象
	*/
	private List<HybjUserForm> userChartPOListToVOList(List<Object[]> list) {
		List<HybjUserForm> fomrList = new ArrayList<HybjUserForm>();
		HybjUserForm elecUserForm = null;
		for(int i=0;list!=null && i<list.size();i++){
			Object [] object = list.get(i);
			elecUserForm = new HybjUserForm();
			elecUserForm.setJctname(object[0].toString());
			elecUserForm.setJctcount(object[1].toString());
			fomrList.add(elecUserForm);
		}
		return fomrList;
	}
	
}
