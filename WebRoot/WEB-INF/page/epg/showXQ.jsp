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
            $.ajax({
                type : "POST",  //提交方式
                url : "epg/hybjJhEpgAction_findById.do",//路径
                data : {
                    "demand.id" : values
                },//数据，这里使用的是Json格式进行传输
                dataType : "json",
                async : true,
                success : function(result) {//返回数据根据结果进行相应的处理
                   showResult(result.hybjDemand);
                }
            });
        }

        function showResult(data) {
            $("#demandName").val(data.demandName);
            $("#demandType").val(data.fenlei);
            $("#details").val(data.xqDetails);
            $("#details1").show();
        }

        function closeWindow() {
		    $("#demandName").val("");
		    $("#demandType").val("");
		    $("#details").val("");
			$("#details1").hide();
        }

        function changeStatus(values,id) {
			var $this = $(values);
			var chosVal = $this.val();
			if ("" == chosVal){return}
			if (chosVal == "0"){ajaxChangeStatus(id,1);}
			if (chosVal == "1"){ajaxChangeStatus(id,2);}
			if (chosVal == "2"){ajaxChangeStatus(id,3);}
			if (chosVal == "3"){ajaxChangeStatus(id,4);}
        }


        function ajaxChangeStatus(id,status) {
            $.ajax({
                type : "POST",  //提交方式
                url : "epg/hybjJhEpgAction_changeStatusById.do",//路径
                data : {
                    "demand.id" : id,
                    "demand.status" : status
                },//数据，这里使用的是Json格式进行传输
                dataType : "json",
                async : true,
                success : function(result) {//返回数据根据结果进行相应的处理
					alert(result.message);
					location.reload();
                }
            });


        }
        function findXQByFuzzy() {
            var itemName = $("#itemName1").val();
            var cp = $("#cp").val();
            window.location.href = "epg/hybjJhEpgAction_findXQByFuzzy.do?demand.demandName="+itemName+"&demand.cp="+cp;
        }
    </SCRIPT>
   </head>
  
  <body>
	<div style='width: 100%;z-index: 1'>
		<div style='width:100%; float: left; height:100%; overflow:scroll;overflow-x:hidden'>
				<table id="rounded-corner" style="margin: 0px; width: 100%; text-align: left; border-collapse: collapse;">
					<tr>
						<td colspan="6">
							节目名：
							<input type="text" size="25" name="itemName1" id="itemName1" value="" />
							cp:
							<s:select list="#request.jctList" name="cp" id="cp"
									  listKey="ddlName" listValue="ddlName"
									  headerKey="" headerValue=""
									  cssStyle="width:155px"
							>
							</s:select>
							<input onclick="findXQByFuzzy();" type="button" value="查询"/>
						</td>
					</tr>
					<tr>
						<th scope="col" class="rounded" style="width: 12%;">
							需求名称
						</th>
						<th scope="col" class="rounded" style="width: 12%;">
							所属内容方
						</th>
						<th scope="col" class="rounded" style="width: 12%;">
							需求分类
						</th>
						<th scope="col" class="rounded" style="width: 12%;">
							需求状态
						</th>
						<th scope="col" class="rounded" style="width: 12%;">
							完成时间
						</th>
						<th scope="col" class="rounded" style="width: 12%;">
							创建时间
						</th>
						<th scope="col" class="rounded" style="width: 12%;">
							附件下载
						</th>
						<th scope="col" class="rounded" style="width: 12%;">
							详情
						</th>
						<th scope="col" class="rounded" style="width: 12%;">
							需求处理
						</th>
					</tr>
					<s:if test="#request.xqList!=null">
					<s:iterator value="%{#request.xqList}" var="list" status="statusa">
						<tr>
							<td>
									${list.demand_name }
							</td>
							<td>
									${list.cp }
							</td>
							<td>
									${list.fenlei }
							</td>
							<td>
								<s:if test="%{#list.status == 1}">待审批</s:if>
								<s:if test="%{#list.status == 2}">审批完成</s:if>
								<s:if test="%{#list.status == 3}">驳回</s:if>
								<s:if test="%{#list.status == 4}">需求完结</s:if>
							</td>
							<td>
									${list.complete_time }
							</td>
							<td>
									${list.create_time }
							</td>

							<td>
								<a href="${pageContext.request.contextPath }/cp/dowFile.do?demandId=${list.id }">${list.attachment_name}</a>
							</td>
							<td>
								<button onclick="showDetails(${list.id })">查看详情</button>
							</td>
							<td>
								<select id="status" class="status1" onchange="changeStatus(this,${list.id});"
										<s:if test="%{#list.status == 4}">disabled="disabled"</s:if>>
									<option value="">下拉操作</option>
									<s:if test="%{#list.status == 1}"><option value="0">待审批</option></s:if>
									<s:if test="%{#list.status <= 2}"><option value="1">审批完成</option></s:if>
									<s:if test="%{#list.status <= 3}"><option value="2">驳回</option></s:if>
									<s:if test="%{#list.status <= 4}"><option value="3" <s:if test="%{#list.status == 4}">selected="selected"</s:if>>需求已完结</option></s:if>
								</select>
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
					<font face="宋体" size="2"><strong>需求详情</strong></font>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_11">需&nbsp;&nbsp;求&nbsp;&nbsp;名&nbsp;&nbsp;称：<font color="#FF0000">*</font></td>
				<td class="ta_11" bgColor="#ffffff">
					<input  id="demandName" maxlength="25" size="20" ></input>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_11">需&nbsp;&nbsp;求&nbsp;&nbsp;分&nbsp;&nbsp;类：</td>
				<td class="ta_11" bgColor="#ffffff">
					<input  id="demandType" maxlength="25" size="20" ></input>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_21">需&nbsp;&nbsp;求&nbsp;&nbsp;详&nbsp;&nbsp;情：</td>
				<td class="ta_21" bgColor="#ffffff">
					<textarea  id="details" style="height: 400px;width:700px" rows="4" cols="52"></textarea>
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
