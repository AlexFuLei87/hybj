package cn.hybj.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hybj.container.ServiceProvider;
import cn.hybj.service.IHybjUserService;

public class CheckLoginName extends HttpServlet {

	IHybjUserService elecUserService = (IHybjUserService)ServiceProvider.getService(IHybjUserService.SERVICE_NAME);
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String logonName = request.getParameter("logonName");
		/**
		 * checkflag：判断当前登录名在数据库中是否存在的标识
		 * checkflag=1：如果值为1，说明当前登录名在数据库中有重复记录，则不能进行保存
		 * checkflag=2：如果值为2，说明当前登录名在数据库中没有重复值，可以进行保存
		 */
		String checkflag = elecUserService.checkLogonName(logonName);
		out.print(checkflag);
		out.flush();
		out.close();
	}

}
