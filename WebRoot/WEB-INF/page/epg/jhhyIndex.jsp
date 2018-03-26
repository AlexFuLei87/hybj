<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="cn.hybj.util.PageBean"%>
<HTML>
	<HEAD>
		<title>聚合EPG管理</title>
		<LINK href="${pageContext.request.contextPath }/css/Style.css"  type="text/css" rel="stylesheet">
		<LINK href="${pageContext.request.contextPath }/css/niceforms-default.css"  type="text/css" rel="stylesheet">
		<LINK href="${pageContext.request.contextPath }/css/form.css"  type="text/css" rel="stylesheet">
		<LINK href="${pageContext.request.contextPath }/css/frame.css"  type="text/css" rel="stylesheet">
		<LINK href="${pageContext.request.contextPath }/css/style_1.css"  type="text/css" rel="stylesheet">
		<script language="javascript"  src="${pageContext.request.contextPath }/script/function.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/script/page.js?vs=1"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery.min.js"></script>
		<style>
			table tbody {
				display:block;
				height:700px;
				overflow-y:scroll;
			}

			table thead, tbody tr {
				display:table;
				width:100%;
				table-layout:fixed;
			}

			/*table thead {*/
				/*width: calc( 100% - 1em )*/
			/*}*/
			/*table thead th{ background:#ccc;}*/
		</style>
		<script language="javascript">

            $(document).ready(function () {

                var $fixTable = $('#goodsList .fixTable');
                $('#goodsList').scroll(function() {
                    var id = '#' + this.id;
                    var scrollTop = $(id).scrollTop() || $(id).get(0).scrollTop,
                        style = {
                            'position': 'absolute',
                            'left': '0',
                            'right': '0',
                            'top': scrollTop + 'px'
                        };
                    if ($fixTable.length) {
                        (scrollTop === 0) ? $fixTable.addClass('hidden') : $fixTable.removeClass('hidden');
                        $fixTable.css(style);
                    } else {
                        var html = $(id + ' .scrollTable thead').get(0).innerHTML;
                        var table = $('<table class="table table-bordered fixTable"><thead>' + html + '</thead></table>');
                        table.css(style);
                        $(id).append(table);
                        $fixTable = $(this).find('.fixTable');
                    }
                });
            });

			function changeStatus(value,id){
				var $this = $(value);
   				var choseValue = $this.val();
   				if(choseValue == '0'){
   					$.ajax({  
                    type : "POST",  //提交方式  
                    url : "epg/jhJhEpgAction_checkStatus.do",//路径
                    data : {  
                        "hybjReportForm.status" : "pass",
                        "hybjReportForm.id" : id
                    },//数据，这里使用的是Json格式进行传输  
                    dataType : "json",
					async : true,
                    success : function(result) {//返回数据根据结果进行相应的处理  
                        if(result.message){
                         window.location.href= 'epg/jhJhEpgAction_jhhy.do';
                         alert(result.message);
                         
                        }
                    }  
                });  
   				}else if(choseValue == '1'){
   					$.ajax({  
                    type : "POST",  //提交方式  
                    url : "epg/jhJhEpgAction_checkStatus.do",//路径
                    data : {  
                        "hybjReportForm.status" : "fail",
                        "hybjReportForm.id" : id  
                    },//数据，这里使用的是Json格式进行传输  
                    dataType : "json",
					async : true,
                    success : function(result) {//返回数据根据结果进行相应的处理  
                        if(result.message){
                         alert(result.message);
                            window.location.href= 'epg/jhJhEpgAction_jhhy.do';
                           alert(result.message);
                        }
                    }  
                });  
   				}
			}
		function saveFeedback(value,id){
			var $this = $(value);
   			var feedback = $this.val();
   			if(feedback=="")return;
			$.ajax({  
                    type : "POST",  //提交方式  
                    url : "epg/jhJhEpgAction_saveFeedback.do",//路径
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


		function checkAll(){
			if ($("#all").is(':checked')){
				$("input[name='report_id']").attr("checked","true");
			}else{
				$("input[name='report_id']").removeAttr("checked");
			}
		}
		
		function batchPass() {
            var ids = "";
            var count = 0;
            var chk = $("input[name='report_id']");
            for (var i = 0; i < chk.length; i++) {
                if (chk[i].checked) {
                    if(count==0){ids = chk[i].value;
                        count++; }
                    else{
                        ids = ids + "," + chk[i].value;}
                }
            }
            $.ajax({
                type : "POST",  //提交方式
                url : "epg/jhJhEpgAction_batchPass.do",//路径
                data : {
                    "ids" : ids,
                    "hybjReportForm.status" : "pass"
                },//数据，这里使用的是Json格式进行传输
                dataType : "json",
                async : false,
                success : function(result) {//返回数据根据结果进行相应的处理
                    if(result.message){
                        alert(result.message);
                        var boxes = $("input[name='report_id']");
                        for(i=0;i<boxes.length;i++){
                            if(boxes[i].checked){
                                tr = boxes[i].parentNode.parentNode;
                                tr.parentNode.removeChild(tr);
                            }
                        }
                    }
                }
            });
        }


        function findByFuzzy() {
			var itemName = $("#demandName").val();
			var cpName = $("#cp").val();
            //"epg/hybjJhEpgAction_findByFuzzy.do"
			window.location.href = "epg/jhJhEpgAction_findByFuzzy.do?hybjReport.itemName="+itemName+"&hybjReport.cp="+cpName;

        }
		</script>
	</HEAD>
	<body>
	<div style='width: 100%'>
	<div id="goodsList" style='width:100%; float: left; height:100%; overflow:scroll;overflow-x:hidden'>
		<s:form id="Form1" name="Form1" disabled="true">
			<input type="hidden" name="initflag" id="initflag" value="1"/>
			<input type="hidden" name="pageNO" id="pageNO" value="1"/>
			<input type="hidden" name="pageSize" id="pageSize" value=""/>
		</s:form>
		<s:form id="Form2" name="Form2" >
			<table id="rounded-corner" style="margin: 0px; width: 100%; text-align: left; border-collapse: collapse;">
				<thead>
				<tr>
					<td style="width: 1%"></td>
					<td colspan="10" style="text-align: left;">
						节目名：
						<input type="text" size="25" name="demandName" id="demandName" value="" />
						cp:
						<s:select list="#request.jctList" name="cp" id="cp"
								  listKey="ddlName" listValue="ddlName"
								  headerKey="" headerValue=""
								  cssStyle="width:155px"
						>
						</s:select>
						<input onclick="findByFuzzy();" type="button" value="查询"/>
					</td>
					<td><input type="button" value="批量通过" onclick="batchPass();"/></td>
					<td><input type="button" value="已处理" onclick="window.location.href = 'epg/jhJhEpgAction_onlineResult.do'"/></td>
				</tr>
				<tr>
					<th scope="col" class="rounded" style="width: 1%;">
						<input type="checkbox" name="all" id="all" onClick="checkAll()" />
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						节目名称
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						申报时间
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						所属分类
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						所属类型
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						内容方
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						上线时间
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						预上线时间
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						是否收费
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						是否同步到聚合
					</th>

					<th scope="col" class="rounded" style="width: 9%;">
					    备注
					</th>

					<th scope="col" class="rounded" style="width: 9%;">
						反馈
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						操作
					</th>
				</tr>
				</thead>

				<c:forEach items="${report}" var="report" varStatus="vs">
					<tr>
						<td style="width: 5px">
							<input type="checkbox" id="report_id" name="report_id" value="${report.id}" />
						</td>
						<td >
							${report.itemName}
						</td>
						<td >
							${report.createTime}
						</td>
						<td>
							${report.programaName}
						</td>
						<td>
							${report.type}
						</td>
						<td>
							${report.cp}
						</td>
						<td>
							<c:if test="${fn:length(report.onlineTime)>10 }">
								${fn:substring(report.onlineTime, 0, 10)}
							</c:if>
							<c:if test="${fn:length(report.onlineTime)<=10 }">
								${report.onlineTime }
							</c:if>
						</td>
						<td>
							<c:if test="${fn:length(report.preonlineTime)>10 }">
								${fn:substring(report.preonlineTime, 0, 10)}
							</c:if>
							<c:if test="${fn:length(report.preonlineTime)<=10 }">
								${report.preonlineTime }
							</c:if>
						</td>
						<td>
							${report.isCharge==true?'是':'否'}
						</td>
						<td>
							${report.isJh==true?'是':'否'}
						</td>
						<td>
								${report.remarks}
						</td>
						<td>
							<input  type="text" value="${report.feedback}" onblur="saveFeedback(this,${report.id})" size="10"/>
						</td>
						<td>
						<%--  <a href="../programa/relateIntoRight.do?programaId=${report.id}" target="right_frame2" onclick="check('${vs.index}');" id="${vs.index}">关联内容</a> --%>
							<select id="status" class="status1" onchange="changeStatus(this,${report.id});" type="text">
				                <option value="">下拉操作</option>
				                <option value="0">审核通过</option>
				                <option value="1">审核不通过</option>
				             </select>
						</td>
					</tr>
				</c:forEach>
				<s:if test="#request.report.size > 199">
				<tr>
					<td width="100%" height="1"  colspan="12">
						<table border="0" width="100%" cellspacing="0" cellpadding="0">
							<%PageBean pagebean=(PageBean)request.getAttribute("page");%>
							<tr>
								<td width="15%" align="left">总记录数：<%=pagebean.getTotalResult() %>条</td>
								<td width="14%" align="right"></td>
								<%if(pagebean.getFirstPage()){ %>
								<td width="8%" align="center">首页&nbsp;&nbsp;|</td>
								<td width="10%" align="center">上一页&nbsp;&nbsp;&nbsp;|</td>
								<%}else{ %>
								<td width="8%" align="center"><u><a href="#" onClick="gotopage('epg/jhJhEpgAction_jhhy.do','start')">首页&nbsp;&nbsp;|</a></u></td>
								<td width="10%" align="center"><u><a href="#" onClick="gotopage('epg/jhJhEpgAction_jhhy.do','prev')">上一页&nbsp;&nbsp;&nbsp;|</a></u></td>
								<%} %>
								<%if(pagebean.getLastPage()){ %>
								<td width="10%" align="center">下一页&nbsp;&nbsp;&nbsp;|</td>
								<td width="8%" align="center">末页</td>
								<%}else{ %>
								<td width="10%" align="center"><u><a href="#" onClick="gotopage('epg/jhJhEpgAction_jhhy.do','next')">下一页&nbsp;&nbsp;&nbsp;|</a></u></td>
								<td width="8%" align="center"><u><a href="#" onClick="gotopage('epg/jhJhEpgAction_jhhy.do','end')">末页</a></u></td>
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
		</div>
		</div>
	</body>
</HTML>
