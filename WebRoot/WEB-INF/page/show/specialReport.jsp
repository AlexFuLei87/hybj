<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
  <head>
    <title></title>

	  <LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
	  <LINK href="${pageContext.request.contextPath }/css/niceforms-default.css" type="text/css" rel="stylesheet">
	  <LINK href="${pageContext.request.contextPath }/css/form.css" type="text/css" rel="stylesheet">
	  <LINK href="${pageContext.request.contextPath }/css/frame.css" type="text/css" rel="stylesheet">
	  <LINK href="${pageContext.request.contextPath }/css/style_1.css" type="text/css" rel="stylesheet">
	  <script language="javascript"  src="${pageContext.request.contextPath }/script/function.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery.min.js"></script>
    <SCRIPT language="javascript">
		
		function showDetails(values) {
			
        }
    </SCRIPT>
   </head>
  
  <body>
	<div style='width: 100%;z-index: 1'>
        <input style="font-size:12px; color:black; height=30;width=120"  type="button" value="专题上传" name="BT_Import"
               onclick="openWindow('cp/hybjShowAction_specialAdd.do','600','300')">
		<input style="font-size:12px; color:black; height=30;width=120"  type="button" value="批量上传" name="BT_Import"
               onclick="openWindow('cp/hybjShowAction_specialImport.do','600','300')">
		<input style="font-size:12px; color:black; height=30;width=120"  type="button" value="模板下载" name="BT_Import"
			   onclick="window.location.href='cp/dowTemplate.do?tempType=zt'">
		<div style='width:100%; float: left; height:100%; overflow:scroll;overflow-x:hidden'>
				<table id="rounded-corner" style="margin: 0px; width: 100%; text-align: left; border-collapse: collapse;">
					<tr>
						<th scope="col" class="rounded" style="width: 15%;">
							专题名称
						</th>
						<th scope="col" class="rounded" style="width: 14%;">
							节目名称
						</th>
						<th scope="col" class="rounded" style="width: 14%;">
							上报状态
						</th>
						<th scope="col" class="rounded" style="width: 14%;">
							上报时间
						</th>
						<th scope="col" class="rounded" style="width: 14%;">
							审核时间
						</th>
					</tr>
					<s:if test="#request.ztList!=null">
					<s:iterator value="%{#request.ztList}" var="list" >
						<tr>
							<td>
									${list.special_name }
							</td>
							<td>
									${list.item_name }
							</td>
							<td>
								<s:if test="%{#list.status == 'normal'}">待审批</s:if>
								<s:if test="%{#list.status == 'pass'}">审批完成</s:if>
								<s:if test="%{#list.status == 'fail'}">驳回</s:if>
								<s:if test="%{#list.status == 'draft'}">草稿</s:if>
							</td>
							<td>
									${list.create_time }
							</td>
							<td>
									${list.verify_time }
							</td>
						</tr>
					</s:iterator>
					</s:if>
				</table>
		</div>
	</div>
	<div class="dialog_container" id="details1" hidden >
		<table cellSpacing="1" height="88" cellPadding="5" width="580" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">

			<tr>
				<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
					<font face="宋体" size="2"><strong>公示内容</strong></font>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_11">公&nbsp;&nbsp;示&nbsp;&nbsp;名&nbsp;&nbsp;称：<font color="#FF0000">*</font></td>
				<td class="ta_11" bgColor="#ffffff">
					<input  id="outlineName" maxlength="25" size="20" ></input>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_11">公&nbsp;&nbsp;示&nbsp;&nbsp;概&nbsp;&nbsp;要：</td>
				<td class="ta_11" bgColor="#ffffff">
					<input  id="outline" maxlength="25" size="20" ></input>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_21">公&nbsp;&nbsp;示&nbsp;&nbsp;详&nbsp;&nbsp;情：</td>
				<td class="ta_21" bgColor="#ffffff">
					<textarea  id="details" style="height: 400px" rows="4" cols="52"></textarea>
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
