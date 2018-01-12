<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery.min.js"></script>
		<script language="javascript">
			function changeStatus(value,id){
				var $this = $(value);
   				var choseValue = $this.val();
   				if(choseValue == '0'){
   					$.ajax({  
                    type : "POST",  //提交方式  
                    url : "epg/hybjJhEpgAction_checkStatus.do",//路径  
                    data : {  
                        "hybjReportForm.status" : "pass",
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
                    url : "epg/hybjJhEpgAction_checkStatus.do",//路径  
                    data : {  
                        "hybjReportForm.status" : "fail",
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
		function saveFeedback(value,id){
			var $this = $(value);
   			var feedback = $this.val();
   			if(feedback=="")return;
			$.ajax({  
                    type : "POST",  //提交方式  
                    url : "epg/hybjJhEpgAction_saveFeedback.do",//路径  
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
                url : "epg/hybjJhEpgAction_batchPass.do",//路径
                data : {
                    "ids" : ids
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
		
		</script>
	</HEAD>
	<body>
		<div style='width: 100%'>
	<div style='width:100%; float: left; height:100%; overflow:scroll;overflow-x:hidden'>
		<form id="form" name="form">
			<table id="rounded-corner" style="margin: 0px; width: 100%; text-align: left; border-collapse: collapse;">
				<tr>
					<td colspan="6">
						节目名：
						<input type="text" size="25" name="itemName" value="" />
						cp:
						<s:select list="#request.jctList" name="department" id="department"
								  listKey="ddlName" listValue="ddlName"
								  headerKey="" headerValue=""
								  cssStyle="width:155px"
						>
						</s:select>
						<input type="button" value="查询" />
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><input type="button" value="批量通过" onclick="batchPass();"/></td>
				</tr>
				<tr>
					<th scope="col" class="rounded" style="width: 1%;">
						<input type="checkbox" name="all" id="all" onClick="checkAll()" />
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						节目名称
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						上报时间
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
						操作
					</th>
				</tr>
				<c:forEach items="${report}" var="report" varStatus="vs">
					<tr>
						<td>
							<input type="checkbox" id="report_id" name="report_id" value="${report.id}" />
						</td>
						<td>
							${report.item_name}
						</td>
						<td>
							${report.create_time}
						</td>
						<td>
							${report.programa_name}
						</td>
						<td>
							${report.report_type}
						</td>
						<td>
							${report.cp}
						</td>
						<td>
							${report.online_time}
						</td>
						<td>
							${report.preonline_time}
						</td>
						<td>
							${report.isCharge==true?'是':'否'}
						</td>
						<td>
							${report.is_jh==true?'是':'否'}
						</td>
						<td>
							<input  type="text" value="${report.feedback}" onblur="saveFeedback(this,${report.id})"/>
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
			</table>
		</form>
		</div>
		</div>
	</body>
</HTML>
