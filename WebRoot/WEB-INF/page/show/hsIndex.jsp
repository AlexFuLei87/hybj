<%--  <%@ page language="java" pageEncoding="UTF-8"%>  --%>
<%@ page language="java" import="cn.hybj.domain.HybjUser" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% HybjUser user = (HybjUser)request.getSession().getAttribute("globle_user");%>
<HTML>
	<HEAD>
		<title>管理</title>		
		<LINK href="${pageContext.request.contextPath }/css/Style.css"  type="text/css" rel="stylesheet">
		<LINK href="${pageContext.request.contextPath }/css/jquery-ui.min.css"  type="text/css" rel="stylesheet">
		<script language="javascript"  src="${pageContext.request.contextPath }/script/function.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/input_verify.js"></script>
		<script language="javascript">
		</script>
	</HEAD>
	<body>
	
<form name="Form1" method="post" action="name.aspx" id="Form1">
	<div>
		<input style="font-size:12px; color:black; height=30;width=120"  type="button" value="编辑上线申报" name="BT_Import"
				 onclick="openWindow('cp/hybjShowAction_reportAdd.do?cpName=${department}','700','400')">
		<input style="font-size:12px; color:black; height=30;width=120"  type="button" value="批量上线申报" name="BT_Import"
				 onclick="openWindow('cp/hybjShowAction_importpage.do?cpName=${department}','700','400')">
		<span>||</span>
		<input style="font-size:12px; color:black; height=30;width=120"  type="button" value="编辑下线申报" name="BT_Import"
			   onclick="openWindow('cp/hybjShowAction_reportOffAdd.do?cpName=${department}','700','500')">
		<input style="font-size:12px; color:black; height=30;width=120"  type="button" value="批量下线申报" name="BT_Import"
			   onclick="openWindow('cp/hybjShowAction_importOffpage.do?cpName=${department}','700','500')">
	</div>
	<table width="100%" border="0" height="88" border="1" background=${pageContext.request.contextPath }/images/back1.jpg>
		<%-- <tr>
			<td colspan=2 class="ta_01" align="center" background="${pageContext.request.contextPath }/images/b-info.gif"><strong>公告首页</strong></td>
		</tr>
		 --%>
		<tr>
			<td width="50%" height="84" align="left" valign="top" >
			
			<fieldset style="width: 95%; height: 630px; padding: 1 background:${pageContext.request.contextPath }/images/back1.JPG"><legend>
			<font color="#0000FF">
			<img border="0" src="${pageContext.request.contextPath }/images/zoom.gif" width="14" height="14"> 所有上报</font></legend>
			<IFRAME src="${pageContext.request.contextPath }/system/hybjMenuAction_alermSB.do?cpName=${department}"  name="alarmx"  frameBorder=0 width=100% scrolling=auto height=610></IFRAME>
		  	 
		   </fieldset>
			
			</td>
			<td width="50%" align="left" valign="top">
			
			<fieldset style="width: 95%; height: 200px; padding: 1 background:${pageContext.request.contextPath }/images/back1.JPG"><legend>
			<font color="#0000FF">
			<img border="0" src="${pageContext.request.contextPath }/images/zoom.gif" width="14" height="14"> 通过审核</font></legend>		
			<IFRAME src="${pageContext.request.contextPath }/system/hybjMenuAction_alermTG.do?cpName=${department}"  name="alarmx"  frameBorder=0 width=100% scrolling=auto height=170></IFRAME>
				
			</fieldset>
			
			<fieldset style="width: 95%; height: 200px; padding: 1 background:${pageContext.request.contextPath }/images/back1.JPG"><legend>
			<font color="#0000FF">
			<img border="0" src="${pageContext.request.contextPath }/images/zoom.gif" width="14" height="14"> 不通过审核</font></legend>
			<!-- encodeURI(encodeURI(url)); 	 -->
			<IFRAME src="${pageContext.request.contextPath }/system/hybjMenuAction_alermWTG.do?cpName=${department}"  name="alarmj"  frameBorder=0 width=100% scrolling=auto height=170></IFRAME>
				
			</fieldset>
			
			<fieldset style="width: 95%; height: 200px; padding: 1 background:${pageContext.request.contextPath }/images/back1.JPG"><legend>
			<font color="#0000FF">
			<img border="0" src="${pageContext.request.contextPath }/images/zoom.gif" width="14" height="14">草稿状态的节目</font></legend>
			
			<IFRAME src="${pageContext.request.contextPath }/system/hybjMenuAction_alermCG.do?cpName=${department}"  name="station"  frameBorder=0 width=100% scrolling=auto height=170></IFRAME>
				     
		   </fieldset>
			
			</td>
		</tr>
		<tr><td height=2></td></tr>
	</table>
	</form>
	

	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/datepicker_cn.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-ui.min.js"></script>
	
</HTML>
