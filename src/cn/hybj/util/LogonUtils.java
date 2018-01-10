package cn.hybj.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

public class LogonUtils {

	/**  
	* @Name: checkNumber
	* @Description: 首页登录中添加验证码的功能
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: HttpServletRequest request对象
	* @Return: boolean true 验证成功
	*                  false 验证失败
	*/
	public static boolean checkNumber(HttpServletRequest request) {
		//从session中获取验证码的数值
		HttpSession session = request.getSession(false);
		if(session==null){
			return false;
		}
		String check_number_key = (String)session.getAttribute("CHECK_NUMBER_KEY");
		if(StringUtils.isBlank(check_number_key)){
			return false;
		}
		//从登录页面获取验证码的数值
		String checkNumber = request.getParameter("checkNumber");
		if(StringUtils.isBlank(checkNumber)){
			return false;
		}
		return check_number_key.equalsIgnoreCase(checkNumber);
	}

	/**  
	* @throws UnsupportedEncodingException 
	* @Name: remeberMeByCookie
	* @Description: 首页登录中添加记住我的功能
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: HttpServletRequest request对象
	*              HttpServletResponse response对象
	* @Return: 无
	*/
	public static void remeberMeByCookie(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		//获取页面中的登录名和密码
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		//创建2个Cookie，分别用来存放登录名和密码
		//处理Cookie中存在的中文字符
		String codeName = URLEncoder.encode(name, "UTF-8");
		Cookie nameCookie = new Cookie("name",codeName);
		Cookie passwordCookie = new Cookie("password",password);
		//设置Cookie的有效路径，有效路径定义为项目的根路径
		//System.out.println("path="+request.getContextPath());
		nameCookie.setPath(request.getContextPath()+"/");
		passwordCookie.setPath(request.getContextPath()+"/");
		/**
		 * 从页面中获取记住我的复选框的值，
		 *    * 如果有值，设置Cookie的有效时长
		 *    * 如果没有值，清空Cookie的有效时长
		 * <input type="checkbox" name="remeberMe" id="remeberMe" value="yes">
		 */
		String remeberMe = request.getParameter("remeberMe");
		//设置Cookie的有效时长
		if(remeberMe!=null && remeberMe.equals("yes")){
			nameCookie.setMaxAge(7*24*60*60);
			passwordCookie.setMaxAge(7*24*60*60);
		}
		//清空Cookie的有效时长
		else{
			nameCookie.setMaxAge(0);
			passwordCookie.setMaxAge(0);
		}
		//将2个Cookie的对象存放到response对象
		response.addCookie(nameCookie);
		response.addCookie(passwordCookie);
	}

}
