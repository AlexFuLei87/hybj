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
            var specialName = $("#specialName").val();
            var cpName = $("#cp").val();
            //"epg/hybjJhEpgAction_findByFuzzy.do"
            window.location.href = "../cp/jhSpecialAction_findByFuzzy.do?hybjSpecial.specialName="+specialName+"&hybjSpecial.cp="+cpName;

        }
        function saveFeedback(value,id){
            var $this = $(value);
            var feedback = $this.val();
            if(feedback=="")return;
            $.ajax({
                type : "POST",  //提交方式
                url : "../epg/jhJhEpgAction_saveSpecialFeedback.do",//路径
                data : {
                    "hybjSpecial.feedback" : feedback,
                    "hybjSpecial.id" : id
                },//数据，这里使用的是Json格式进行传输
                dataType : "json",
                async : true,
                success : function(result) {//返回数据根据结果进行相应的处理

                }
            });

        }

        function changeStatus(obj,value,id){
            if(value){
                $.ajax({
                    type : "POST",  //提交方式
                    url : "../epg/jhJhEpgAction_changSpecialStatus.do",//路径
                    data : {
                        "hybjSpecial.status" : "fail",
                        "hybjSpecial.id" : id
                    },//数据，这里使用的是Json格式进行传输
                    dataType : "json",
                    async : true,
                    success : function(result) {//返回数据根据结果进行相应的处理
                        if(result.message){
                            alert(result.message);
                            window.location.reload();
                        }
                    }
                });
            }else{
                var $this = $(obj);
                var cp = $this.val();
                $.ajax({
                    type : "POST",  //提交方式
                    url : "../epg/jhJhEpgAction_changSpecialStatus.do",//路径
                    data : {
                        "hybjSpecial.status" : "pass",
                        "hybjSpecial.cp" : cp,
                        "hybjSpecial.id" : id
                    },//数据，这里使用的是Json格式进行传输
                    dataType : "json",
                    async : true,
                    success : function(result) {//返回数据根据结果进行相应的处理
                        if(result.message){
                            alert(result.message);
                            window.location.reload();
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
					<tr>
						<td colspan="4">
							专题名：
							<input type="text" size="25" name="specialName" id="specialName" value="" />
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
					<tr>
						<th scope="col" class="rounded" style="width: 12.5%;">
							专题名
						</th>
						<th scope="col" class="rounded" style="width: 12.5%;">
							节目名
						</th>
						<th scope="col" class="rounded" style="width: 12.5%;">
							处理时间
						</th>
						<th scope="col" class="rounded" style="width: 12.5%;">
							专题状态
						</th>
						<th scope="col" class="rounded" style="width: 12.5%;">
							申报时间
						</th>
						<th scope="col" class="rounded" style="width: 12.5%;">
							内容方
						</th>
						<th scope="col" class="rounded" style="width: 12.5%;">
							作图方
						</th>
						<th scope="col" class="rounded" style="width: 12.5%;">
							反馈信息
						</th>

					</tr>
					<s:if test="#request.ztList!=null">
					<s:iterator value="%{#request.ztList}" var="list">
						<tr>
							<td>
									${list.special_name }
							</td>
							<td>
									${list.item_name }
							</td>
							<td>
									${list.onDate }
							</td>
							<td>
								${list.status=="pass"?"通过审核":"未通过审核" }
							<td>
								${list.create_time }
							</td>
							<td>
									${list.cp}
							</td>
							<s:if test="#request.permission=='dx' ||#request.permission=='jh'">
								<td>
									<select id="cp1" style="width:170px;height:25px" onchange="changeStatus(this,false,${list.id});">
										<option value=""></option>
										<option value="华数TV" <s:if test="%{#list.draw_part == '华数TV'}"> selected="selected"</s:if>>华数TV</option>
										<option value="芒果TV" <s:if test="%{#list.draw_part == '芒果TV'}"> selected="selected"</s:if>>芒果TV</option>
										<option value="优酷" <s:if test="%{#list.draw_part == '优酷'}"> selected="selected"</s:if>>优酷</option>
										<option value="优朋" <s:if test="%{#list.draw_part == '优朋'}"> selected="selected"</s:if>>优朋</option>
										<option value="百视通" <s:if test="%{#list.draw_part == '百视通'}"> selected="selected"</s:if>>百视通</option>
										<option value="爱奇艺" <s:if test="%{#list.draw_part == '爱奇艺'}"> selected="selected"</s:if>>爱奇艺</option>
										<option value="天翼视讯" <s:if test="%{#list.draw_part == '天翼视讯'}"> selected="selected"</s:if>>天翼视讯</option>
										<option value="腾讯" <s:if test="%{#list.draw_part == '腾讯'}"> selected="selected"</s:if>>腾讯</option>
									</select>
								</td>
							</s:if>
							<s:if test="#request.permission =='cp'">
								<td>${list.draw_part}</td>

							</s:if>
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
					<font face="宋体" size="2"><strong>专题公示</strong></font>
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
