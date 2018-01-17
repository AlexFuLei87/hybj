<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
  <head>
    <title>未通过审核</title>
    <link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet" />
	  <LINK href="${pageContext.request.contextPath }/css/style_1.css" type="text/css" rel="stylesheet">
    <script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
   </head>
  
  <body>
    <table id="rounded-corner" style="margin: 0px; width: 100%; text-align: left; border-collapse: collapse;">
	<%-- <tr>
		<td align="left" valign="middle"  style="color: #000000">
		<span class="style1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:;" onClick="openWindow('infoDeviceJ.do?devId=ee1cf4ceb3fa440a9699fd8c6dad7d30&seqId=&isPage=no',800,450,'设备检修周期添加');" class="cl_01">测试经济界</a></span></td>
		<td>${reportList }</td>
	</tr> --%>
	<tr>
	<th scope="col" class="rounded" style="width: 20%;">节目名字</th>
	<th scope="col" class="rounded" style="width: 20%;">审核时间</th>
	<th scope="col" class="rounded" style="width: 20%;">上报状态</th>
	<th scope="col" class="rounded" style="width: 20%;">反馈</th>
	<th scope="col" class="rounded" style="width: 20%;">上报类型</th>
	</tr>
	<s:if test="#request.reportList!=null">
	<s:iterator value="%{#request.reportList}" var="list">
		<tr>
		<td style="width: 20%;">${list.item_name }</td>
		<td style="width: 20%;">${list.verify_time }</td>
		<td style="width: 20%;">${list.status=="pass"?"通过审核":"未通过审核" }</td>
		<td style="width: 20%;">${list.feedback }</td>
		<td style="width: 20%;">${list.report_status == 'online'?'上线上报':'下线上报' }</td>
		</tr>
	</s:iterator>	
 	</s:if>
	</table>
  </body>
</html>