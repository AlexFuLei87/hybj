<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
  <head>
    <title>草稿状态</title>
    <link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet" /><script language="javascript"  src="${pageContext.request.contextPath }/script/function.js"></script>
  </head>
  <SCRIPT language="javascript">

  </SCRIPT>
  <body>
    <table width="100%" border="0" id="table8">
	    <tr>
		<td style="width: 20%;">节目名字</td>
		<td style="width: 20%;">创建时间</td>
		<td style="width: 20%;">所属栏目</td>
		<td style="width: 20%;">类型</td>
		<td style="width: 20%;">备注</td>
		</tr>
	<s:if test="#request.cgList!=null">
	<s:iterator value="%{#request.cgList}" var="list">
		<tr style="cursor:pointer"  onclick="openWindow('../cp/hybjShowAction_showCG.do?id=${list.id }','700','400')">
		<td style="width: 20%;">${list.item_name }</td>
		<td style="width: 20%;">${list.create_time }</td>
		<td style="width: 20%;">${list.programa_name }</td>
		<td style="width: 20%;">${list.report_type }</td>
		<td style="width: 20%;">${list.remarks }</td>
		</tr>
	</s:iterator>	
 	</s:if>
	</table>
  </body>
</html>