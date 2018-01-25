<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
  <head>
    <title></title>

	  <LINK href="${pageContext.request.contextPath }/css/Style.css"  type="text/css" rel="stylesheet">
	  <LINK href="${pageContext.request.contextPath }/css/niceforms-default.css"  type="text/css" rel="stylesheet">
	  <LINK href="${pageContext.request.contextPath }/css/form.css"  type="text/css" rel="stylesheet">
	  <LINK href="${pageContext.request.contextPath }/css/frame.css"  type="text/css" rel="stylesheet">
	  <LINK href="${pageContext.request.contextPath }/css/style_1.css"  type="text/css" rel="stylesheet">
	  <script language="javascript"  src="${pageContext.request.contextPath }/script/function.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery.min.js"></script>
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
            if (len < 6){return}
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
        function showDetails(value) {
            var len = getBLen(value);
            if (len < 6){return}
			$("#details").html(value);
            $("#details1").show();

        }
        function closeWindow() {
            $("#details").val('');
            $("#details1").hide();
        }
    </SCRIPT>
	  <style>
		  .dialog_container1 {
			  position: fixed;
			  left: 50%;
			  top: 40%;
			  margin: -225px 0 0 -389px;
			  width: 588px;
			  height: 580px;
			  border-radius: 5px;
		  }
	  </style>
   </head>
  
  <body>
	<div style='width: 100%'>
		<%--<input style="font-size:12px; color:black; height=30;width=120"  type="button" value="公告区" name="BT_Import"--%>
			   <%--onclick="window.location.href='system/hybjMenuAction_alermGG.do'">--%>
		<div style='width:100%; float: left; height:100%; overflow:scroll;overflow-x:hidden'>
			<form id="form" name="form">
				<table id="rounded-corner" style="margin: 0px; width: 100%; text-align: left; border-collapse: collapse;">
					<tr>
						<th scope="col" class="rounded" style="width: 15%;">
							节目名称
						</th>
						<th scope="col" class="rounded" style="width: 14%;">
							审核时间
						</th>
						<th scope="col" class="rounded" style="width: 14%;">
							上报状态
						</th>
						<th scope="col" class="rounded" style="width: 14%;">
							上线时间
						</th>
						<th scope="col" class="rounded" style="width: 14%;">
							预上线时间
						</th>
						<th scope="col" class="rounded" style="width: 14%;">
							反馈信息
						</th>
						<th scope="col" class="rounded" style="width: 14%;">
							所属CP
						</th>
					</tr>
					<s:if test="#request.gsList!=null">
					<s:iterator value="%{#request.gsList}" var="list">
						<tr>
							<td>
									${list.item_name }
							</td>
							<td>
									${list.verify_time }
							</td>
							<td>
									${list.status=="pass"?"通过审核":"未通过审核" }
							</td>
							<td>
									${list.online_time }
							</td>
							<td>
									${list.preonline_time}
							</td>
							<td onmouseover="overShow('${list.feedback}')" onmouseout="outHide()" onclick="showDetails('${list.feedback}')">
								<c:choose>
									<c:when test="${fn:length(list.feedback)>6}">
										${fn:substring(list.feedback, 0, 6)}...
									</c:when>
									<c:otherwise>
										${list.feedback}
									</c:otherwise>
								</c:choose>
							</td>
							<td>
									${list.cp }
							</td>
						</tr>
					</s:iterator>
					</s:if>
				</table>
			</form>
			<div id="showDiv" style="position: absolute; background-color: white; border: 1px solid black;"></div>
		</div>
	</div>
	<div class="dialog_container1" id="details1" hidden  style="z-index: 999">
		<table cellSpacing="1" height="88" cellPadding="5" width="580" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">

			<tr>
				<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
					<font face="宋体" size="2"><strong>公示内容</strong></font>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_21">反&nbsp;&nbsp;馈&nbsp;&nbsp;详&nbsp;&nbsp;情：</td>
				<td class="ta_21" bgColor="#ffffff">
					<div  id="details" style="height: 400px;width: 650px;" rows="4" cols="52"></div>
				</td>

			</tr>
			<tr>

				<td class="ta_11" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
					<input type="button" name="BT_Submit" value="关闭"  style="font-size:12px; color:black; height=22;width=55"   onClick="closeWindow();">
					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>


				</td>


			</tr>

		</table>　
	</div>

  </body>
</html>
