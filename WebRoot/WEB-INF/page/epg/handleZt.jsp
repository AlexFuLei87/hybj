<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
	<HEAD>
		<title>专题处理</title>
		<LINK href="${pageContext.request.contextPath }/css/Style.css"  type="text/css" rel="stylesheet">
		<LINK href="${pageContext.request.contextPath }/css/niceforms-default.css"  type="text/css" rel="stylesheet">
		<LINK href="${pageContext.request.contextPath }/css/form.css"  type="text/css" rel="stylesheet">
		<LINK href="${pageContext.request.contextPath }/css/frame.css"  type="text/css" rel="stylesheet">
		<LINK href="${pageContext.request.contextPath }/css/style_1.css"  type="text/css" rel="stylesheet">
		<script language="javascript"  src="${pageContext.request.contextPath }/script/function.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery.min.js"></script>
		<script language="javascript">
			function changeStatus(obj,value,id){
				if(value){
				$.ajax({
					type : "POST",  //提交方式
					url : "epg/jhJhEpgAction_changSpecialStatus.do",//路径
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
                        url : "epg/jhJhEpgAction_changSpecialStatus.do",//路径
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


		function saveFeedback(value,id){
			var $this = $(value);
   			var feedback = $this.val();
   			if(feedback=="")return;
			$.ajax({  
                    type : "POST",  //提交方式  
                    url : "epg/jhJhEpgAction_saveSpecialFeedback.do",//路径
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


		function checkAll(){
			if ($("#all").is(':checked')){
				$("input[name='special_id']").attr("checked","true");
			}else{
				$("input[name='special_id']").removeAttr("checked");
			}
		}
		
		function batchFail() {
            var ids = "";
            var count = 0;
            var chk = $("input[name='special_id']");
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
                url : "epg/jhJhEpgAction_batchSpecialFail.do",//路径
                data : {
                    "ids" : ids
                },//数据，这里使用的是Json格式进行传输
                dataType : "json",
                async : false,
                success : function(result) {//返回数据根据结果进行相应的处理
                    if(result.message){
                        alert(result.message);
                        var boxes = $("input[name='special_id']");
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
			var specialName = $("#specialName").val();
			var cp = $("#cp").val();
            //"epg/hybjJhEpgAction_findByFuzzy.do"
			window.location.href = "epg/jhJhEpgAction_findSpecialByFuzzy.do?hybjSpecial.specialName="+specialName+"&hybjSpecial.cp="+cp;

        }
		</script>
	</HEAD>
	<body>
		<div style='width: 100%'>
	<div style='width:100%; float: left; height:100%; overflow:scroll;overflow-x:hidden'>
		<form id="form" name="form" >
			<table id="rounded-corner" style="margin: 0px; width: 100%; text-align: left; border-collapse: collapse;">
				<tr>
					<td colspan="5">
						专题名：
						<input type="text" size="25" name="specialName" id="specialName" value="" />
						cp:
						<s:select list="#request.jctList" name="cp" id="cp"
								  listKey="ddlName" listValue="ddlName"
								  headerKey="" headerValue=""
								  cssStyle="width:155px"
						>
						</s:select>
						<input onclick="findByFuzzy();" type="button" value="查询"/>
					</td>
					<td></td>
					<td><input type="button" value="批量不通过" onclick="batchFail();"/></td>
				</tr>
				<tr>
					<th scope="col" class="rounded" style="width: 1%;">
						<input type="checkbox" name="all" id="all" onClick="checkAll()" />
					</th>
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
						申报时间
					</th>
					<th scope="col" class="rounded" style="width: 12.5%;">
						内容方
					</th>
					<th scope="col" class="rounded" style="width: 12.5%;">
						反馈信息
					</th>
					<th scope="col" class="rounded" style="width: 12.5%;">
						操作
					</th>
					<th scope="col" class="rounded" style="width: 15%;">
						作图方
					</th>
				</tr>
				<c:forEach items="${special}" var="special" varStatus="vs">
					<tr>
						<td>
							<input type="checkbox" id="special_id" name="special_id" value="${special.id}" />
						</td>
						<td>
							${special.special_name}
						</td>
						<td>
							${special.item_name}
						</td>
						<td>
							${special.onDate}
						</td>
						<td>
							${special.create_time}
						</td>
						<td>
							${special.cp}
						</td>
						<td>
							<input  type="text" value="${report.feedback}" onblur="saveFeedback(this,${special.id})"/>
						</td>
						<td>
							<button onclick="changeStatus(this,true,${special.id});">审核不通过</button>
						</td>
						<td>
							<%--<s:select list="#request.jctList" name="cp" id="cp"--%>
									  <%--listKey="ddlName" listValue="ddlName"--%>
									  <%--headerKey="" headerValue=""--%>
									  <%--cssStyle="width:155px" onchange="changeStatus(false,${special.id})"--%>
							<%--></s:select>--%>
								<select id="cp" style="width:170px;height:25px" onchange="changeStatus(this,false,${special.id});">
									<option value=""></option>
									<option value="华数TV">华数TV</option>
									<option value="芒果TV">芒果TV</option>
									<option value="优酷">优酷</option>
									<option value="优朋">优朋</option>
									<option value="百视通">百视通</option>
									<option value="爱奇艺">爱奇艺</option>
									<option value="天翼视讯">天翼视讯</option>
									<option value="腾讯">腾讯</option>
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
