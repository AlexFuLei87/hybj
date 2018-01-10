<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
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

    </SCRIPT>
   </head>
  
  <body>

	<%--<div class="list_lh">--%>
			<%-- <ul>
				<li>
				<s:if test="#request.gsList!=null">
				<s:iterator value="%{#request.gsList}" var="list">
				<p>${list.item_name } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<em>${list.status=="pass"?"通过审核":"未通过审核" }</em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${list.feedback }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${list.cp }</p>
				</s:iterator>	
		  		</s:if>
				</li>
			</ul> --%>
			<%--<table width="100%" border="0" id="table8">--%>
				<%--<tr>--%>
				<%--<td style="width: 20%;">节目名字</td>--%>
				<%--<td style="width: 20%;">审核时间</td>--%>
				<%--<td style="width: 20%;">上报状态</td>--%>
				<%--<td style="width: 20%;">反馈</td>--%>
				<%--<td style="width: 20%;">所属cp</td>--%>
				<%--</tr>--%>
				<%--<s:if test="#request.gsList!=null">--%>
				<%--<s:iterator value="%{#request.gsList}" var="list">--%>
				<%--<tr>--%>
				<%--<td style="width: 20%;">${list.item_name }</td>--%>
				<%--<td style="width: 20%;">${list.verify_time }</td>--%>
				<%--<td style="width: 20%;">${list.status=="pass"?"通过审核":"未通过审核" }</td>--%>
				<%--<td style="width: 20%;">${list.feedback }</td>--%>
				<%--<td style="width: 20%;">${list.cp }</td>--%>
				<%--</tr>--%>
				<%--</s:iterator>	--%>
		  		<%--</s:if>--%>
			<%--</table>--%>
		<%--</div>--%>
	<div style='width: 100%'>
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
							<td style="text-overflow: ellipsis">

									${list.feedback }

							</td>
							<td>
									${list.cp }
							</td>
						</tr>
					</s:iterator>
					</s:if>
				</table>
			</form>
		</div>
	</div>


  </body>
</html>
