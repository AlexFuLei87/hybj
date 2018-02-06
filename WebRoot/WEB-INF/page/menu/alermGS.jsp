<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="cn.hybj.util.PageBean"%>
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
	  <script language="javascript" src="${pageContext.request.contextPath }/script/page.js?vs=1"></script>
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
			<s:form id="Form1" name="Form1" >
				<input type="hidden" name="initflag" id="initflag" value="1"/>
				<input type="hidden" name="pageNO" id="pageNO" value="1"/>
				<input type="hidden" name="pageSize" id="pageSize" value=""/>
			</s:form>
			<s:form id="Form2" name="Form2">
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
									${list.itemName }
							</td>
							<td>
									${list.reportStatus=='online'?'上线申报':'下线申报' }
							</td>
							<td>
									${list.verifyTime }
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
									${list.onlineTime }
							</td>
							<td>
									${list.preonlineTime}
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
					<s:if test="#request.gsList.size > 199">
					<tr>
						<td width="100%" height="1"  colspan="8">
							<table border="0" width="100%" cellspacing="0" cellpadding="0">
								<%PageBean pagebean=(PageBean)request.getAttribute("page");%>
								<tr>
									<td width="15%" align="left">总记录数：<%=pagebean.getTotalResult() %>条</td>
									<td width="14%" align="right"></td>
									<%if(pagebean.getFirstPage()){ %>
									<td width="8%" align="center">首页&nbsp;&nbsp;|</td>
									<td width="10%" align="center">上一页&nbsp;&nbsp;&nbsp;|</td>
									<%}else{ %>
									<td width="8%" align="center"><u><a href="#" onClick="gotopage('system/jhMenuAction_alermGS.do','start')">首页&nbsp;&nbsp;|</a></u></td>
									<td width="10%" align="center"><u><a href="#" onClick="gotopage('system/jhMenuAction_alermGS.do','prev')">上一页&nbsp;&nbsp;&nbsp;|</a></u></td>
									<%} %>
									<%if(pagebean.getLastPage()){ %>
									<td width="10%" align="center">下一页&nbsp;&nbsp;&nbsp;|</td>
									<td width="8%" align="center">末页</td>
									<%}else{ %>
									<td width="10%" align="center"><u><a href="#" onClick="gotopage('system/jhMenuAction_alermGS.do','next')">下一页&nbsp;&nbsp;&nbsp;|</a></u></td>
									<td width="8%" align="center"><u><a href="#" onClick="gotopage('system/jhMenuAction_alermGS.do','end')">末页</a></u></td>
									<%} %>
									<td width="6%" align="center">第<%=pagebean.getPageNo() %>页</td>
									<td width="6%" align="center">共<%=pagebean.getSumPage() %>页</td>

									<td><input type="hidden" id="pageNO2" name="pageNO" value="<%=pagebean.getPageNo()%>" ></td>
									<td><input type="hidden" id="prevpageNO2" name="prevpageNO" value="<%=(pagebean.getPageNo()-1)%>"></td>
									<td><input type="hidden" id="nextpageNO2" name="nextpageNO" value="<%=(pagebean.getPageNo()+1)%>"></td>
									<td><input type="hidden" id="sumPage2" name="sumPage" value="<%=pagebean.getSumPage() %>" ></td>
									<td><input type="hidden" id="pageSize2" name="pageSize" value="" ></td>
								</tr>
							</table>
						</td>
					</tr>
					</s:if>
				</table>
			</s:form>
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
