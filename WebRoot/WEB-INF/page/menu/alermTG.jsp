<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
  <head>
    <title>通过审核</title>
    
    <link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet" />
	<LINK href="${pageContext.request.contextPath }/css/style_1.css" type="text/css" rel="stylesheet">
    <script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
	  <SCRIPT language="javascript">
          getBLen = function(str) {
              if (str == null) return 0;
              if (typeof str != "string"){
                  str += "";
              }
              return str.replace(/[^\x00-\xff]/g,"01").length;
          }
          function overShow(value) {
              var len = getBLen(value);
              if (len < 10){return}
              var showDiv = document.getElementById('showDiv');
              showDiv.style.left = event.clientX;
              showDiv.style.top = event.clientY;
              showDiv.style.display = 'block';
              showDiv.innerHTML = value;
          }
          function outHide() {
              var showDiv = document.getElementById('showDiv');
              showDiv.style.display = 'none';
              showDiv.innerHTML = '';
          }
	  </SCRIPT>
   </head>
  
  <body>
    <table id="rounded-corner" style="margin: 0px; width: 100%; text-align: left; border-collapse: collapse;">
    	<tr>
	<th scope="col" class="rounded" style="width: 20%;">节目名字</th>
	<th scope="col" class="rounded" style="width: 20%;">审核时间</th>
	<th scope="col" class="rounded" style="width: 20%;">申报状态</th>
	<th scope="col" class="rounded" style="width: 20%;">反馈</th>
	<th scope="col" class="rounded" style="width: 20%;">申报类型</th>
	</tr>
	<s:if test="#request.reportList!=null">
	<s:iterator value="%{#request.reportList}" var="list">
		<tr>
		<td >${list.item_name }</td>
		<td >${list.verify_time }</td>
		<td >${list.status=="pass"?"通过审核":"未通过审核" }</td>
		<td onmouseover="overShow('${list.feedback}')" onmouseout="outHide()">
		<c:choose>
			<c:when test="${fn:length(list.feedback)>10}">
				${fn:substring(list.feedback, 0, 10)}...
			</c:when>
			<c:otherwise>
				${list.feedback}
			</c:otherwise>
		</c:choose>
	</td>
		<td style="width: 20%;">${list.report_status == 'online'?'上线申报':'下线申报' }</td>
		</tr>
	</s:iterator>	
 	</s:if>
	</table>
	<div id="showDiv" style="position: absolute; background-color: white; border: 1px solid black;"></div>
  </body>
</html>
