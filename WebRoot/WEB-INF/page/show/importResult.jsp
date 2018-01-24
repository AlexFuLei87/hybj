<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title></title>
    <link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath }/css/niceforms-default.css" type="text/css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath }/css/style_1.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery.min.js"></script>
   <SCRIPT language="javascript">
	document.onkeydown = function(){
		var k = event.keyCode;
		if((event.ctrlKey == true && k == 82) || (k == 116) || (event.ctrlKey == true && k == 116))
		{
			event.keyCode = 0;
			event.returnValue = false;
			event.cancelBubble = true;
		}
	}
   
    	function checkAll(){
		 if ($("#all").is(':checked')){
		 	$("input[name='report_id']").attr("checked","true"); 
		 }else{
		   $("input[name='report_id']").removeAttr("checked"); 
		 }
		}
     function batchSubmit() {
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
             url : "cp/hybjReportAction_batchUpdate.do",//路径  
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
		
		function updateSingle(value,id){
		 var $this = $(value);
		 var choseValue = $this.val();
		 if(choseValue=="normal"){
		 $.ajax({  
             type : "POST",  //提交方式  
             url : "cp/hybjReportAction_update.do",//路径  
             data : {  
                 "ids" : id  
             },//数据，这里使用的是Json格式进行传输  
             dataType : "json",
 			 async : false,
             success : function(result) {//返回数据根据结果进行相应的处理 
             if(result.message){ 
             	alert(result.message);
	            tr = value.parentNode.parentNode.parentNode;
	            tr.parentNode.removeChild(tr);
	           }
             }  
            });  
		 
		 
		 }
		}


		function showDetails(value) {
			if(value == 'yes'){
                var showDiv = document.getElementById('showDiv');
                showDiv.style.left = event.clientX;
                showDiv.style.top = event.clientY;
                showDiv.style.display = 'block';
                showDiv.innerHTML = '该节目已经被其他内容方上传过';
			}else {
			    return
			}
        }

		function outHide() {
			var showDiv = document.getElementById('showDiv');
			showDiv.style.display = 'none';
			showDiv.innerHTML = '';
		}
   </SCRIPT>
   </head>
  <body>

		<form id="myform" name="form"  method="post">
			<table id="rounded-corner">
				<tr style="text-align: left">
					<th scope="col" class="rounded" width="10px">
						<input type="checkbox" name="all" id="all" onClick="checkAll()" />
					</th>
					<th scope="col" class="rounded" width="130px">
						预上映时间
					</th>
					<th scope="col" class="rounded" width="130px">
						上映时间
					</th>
					<th scope="col" class="rounded" width="130px">
						节目名字
					</th>
					<th scope="col" class="rounded" width="130px">
						所属分类
					</th>
					<th scope="col" class="rounded" width="130px">
						类型
					</th>
					<th scope="col" class="rounded" width="130px">
						状态
					</th>
					<th scope="col" class="rounded" width="130px">
						操作
					</th>
				</tr>
				<c:forEach items="${cpList}" var="list" varStatus="vs">
					<tr id="${vs.index }"  >
						<td>
							<input type="checkbox" id="report_id" name="report_id" value="${list.id}" />
						</td>
						<td>
							<span>${list.preonlineTime.substring(0,10) } </span>
						</td>
						<td>
							<span>${list.onlineTime.substring(0,10) }</span>
						</td>
						<td onmouseover="showDetails('${list.isRepeat }')" onmouseout="outHide()">
							<c:if test="${list.isRepeat == 'yes' }"><span style="color:#F00"> ${list.itemName }</span></c:if>
							<c:if test="${list.isRepeat == null }"><span> ${list.itemName }</span></c:if>

						</td>
						<td>
							<span>${list.programaName }</span>
						</td>
						<td>
							<span>${list.type }</span>
						</td>
						<td>
							<span>待提交</span>
						</td>
						<td>
							<span>
							<select onchange="updateSingle(this,${list.id})">
								<option></option>
								<option value="normal">提交</option>
							</select>
							</span>
						</td>
					</tr>	
				</c:forEach>
					<tr>
						<td colspan="8" align="center" valign="middle" border="1px">
						<input  style="font-size:12px; color:black; height=22;width=55;right:100px"  type="button" value="批量提交"  name="Reset1"  onClick="batchSubmit()">
						</td>
					</tr>
			</table>
		</form>
		<div id="showDiv" style="position: absolute; background-color: white; border: 1px solid black;"></div>
  </body>
</html>