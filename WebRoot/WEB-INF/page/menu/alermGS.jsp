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

        function findByFuzzy() {
            var itemName = $("#itemName").val();
            var cpName = $("#cp").val();
            window.location.href = "../cp/jhReportAction_findByFuzzy.do?hybjReport.itemName="+itemName+"&hybjReport.cp="+cpName;

        }
        function saveFeedback(value,id){
            var $this = $(value);
            var feedback = $this.val();
            if(feedback=="")return;
            $.ajax({
                type : "POST",  //提交方式
                url : "../epg/jhJhEpgAction_saveFeedback.do",//路径
                data : {
                    "hybjReportForm.feedback" : feedback,
                    "hybjReportForm.id" : id
                },//数据，这里使用的是Json格式进行传输
                dataType : "json",
                async : true,
                success : function(result) {//返回数据根据结果进行相应的处理

                }
            });

        }
        function changeStatus(value,id){
            var $this = $(value);
            var choseValue = $this.val();
            if(choseValue == '0'){
                $.ajax({
                    type : "POST",  //提交方式
                    url : "../epg/jhJhEpgAction_checkStatus.do",//路径
                    data : {
                        "hybjReportForm.status" : "dxpass",
                        "hybjReportForm.id" : id
                    },//数据，这里使用的是Json格式进行传输
                    dataType : "json",
                    async : true,
                    success : function(result) {//返回数据根据结果进行相应的处理
                        if(result.message){
                            window.location.reload();
                            alert(result.message);

                        }
                    }
                });
            }else if(choseValue == '1'){
                $.ajax({
                    type : "POST",  //提交方式
                    url : "../epg/jhJhEpgAction_checkStatus.do",//路径
                    data : {
                        "hybjReportForm.status" : "dxfail",
                        "hybjReportForm.id" : id
                    },//数据，这里使用的是Json格式进行传输
                    dataType : "json",
                    async : true,
                    success : function(result) {//返回数据根据结果进行相应的处理
                        if(result.message){
                            alert(result.message);
                            window.location.reload();
                            alert(result.message);
                        }
                    }
                });
            }
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
				<s:if test="#request.permission=='dx'">
					<tr>
						<td colspan="4">
							节目名：
							<input type="text" size="25" name="itemName" id="itemName" value="" />
							内容方:
							<s:select list="#request.jctList" name="cp" id="cp"
									  listKey="ddlName" listValue="ddlName"
									  headerKey="" headerValue=""
									  cssStyle="width:155px"
							>
							</s:select>
							<input onclick="findByFuzzy();" type="button" value="查询"/>
						</td>
					</tr>
				</s:if>
					<tr>
						<th scope="col" class="rounded" style="width: 12.5%;">
							节目名称
						</th>
						<th scope="col" class="rounded" style="width: 15%;">
							申报类型
						</th>
						<th scope="col" class="rounded" style="width: 12.5%;">
							审核时间
						</th>
						<th scope="col" class="rounded" style="width: 12.5%;">
							申报状态
						</th>
						<th scope="col" class="rounded" style="width: 12.5%;">
							上线时间
						</th>
						<th scope="col" class="rounded" style="width: 12.5%;">
							预上线时间
						</th>
						<th scope="col" class="rounded" style="width: 12.5%;">
							所属CP
						</th>
						<th scope="col" class="rounded" style="width: 12.5%;">
							反馈信息
						</th>

					</tr>
					<s:if test="#request.gsList!=null">
					<s:iterator value="%{#request.gsList}" var="list">
						<tr>
							<td>
									${list.item_name }
							</td>
							<td>
									${list.report_status=='online'?'上线申报':'下线申报' }
							</td>
							<td>
									${list.verify_time }
							</td>

							<s:if test="#request.permission=='dx' ">
							<td>
								<select id="status" class="status1" onchange="changeStatus(this,${list.id});" >
									<option value="0" <s:if test="%{#list.status == 'dxpass'}"> selected="selected"</s:if>>审核通过</option>
									<option value="1" <s:if test="%{#list.status == 'dxfail'}"> selected="selected"</s:if> >审核不通过</option>
								</select>
							</td>
							</s:if>
							<s:if test="#request.permission =='cp'||#request.permission=='jh'">
								<td>${list.status=="dxpass"?"通过审核":"未通过审核" }</td>
							</s:if>

							<td>
									${list.online_time }
							</td>
							<td>
									${list.preonline_time}
							</td>
							<td>
									${list.cp }
							</td>
							<s:if test="#request.permission=='dx' ||#request.permission=='jh'">
							<td>
								<input  type="text" value="${list.feedback}" onblur="saveFeedback(this,${list.id})"/>
							</td>
							</s:if>
							<s:if test="#request.permission =='cp'">
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
							</s:if>

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
