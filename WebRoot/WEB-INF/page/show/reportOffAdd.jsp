<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
  <head>
   <title>下线报备</title>
   <LINK href="${pageContext.request.contextPath }/css/Style.css"  type="text/css" rel="stylesheet">
		<LINK href="${pageContext.request.contextPath }/css/jquery-ui.min.css"  type="text/css" rel="stylesheet">
		<script language="javascript"  src="${pageContext.request.contextPath }/script/function.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/input_verify.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/datepicker_cn.js"></script>
	   <script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-ui.min.js"></script>
		<script language="javascript">
		$(function() {
			initdatepicker_cn();
			$("#preOnlineTime").datepicker({
				dateFormat : "yy-mm-dd"
			});
			$("#onlineTime").datepicker({
				dateFormat : "yy-mm-dd"
			});
		});
		
		
		   	function checkAndSave(value){
		   		var itemName = $("#itemName").val();
		   		var programaName = $("#programaName option:selected").val();
		   		var type = $("#type option:selected").val();
		   		var cp = $("#cp").val();
		   		var onlineTime = $("#onlineTime").val();
		   		var preOnlineTime = $("#preOnlineTime").val();
		   		var isCharge = $("#isCharge").val()=='1'?true:false;
		   		var isjh = $("#isjh").val()=='1'?true:false;
		   		var remarks = $("#remarks").val();
		   		var reason = $("#reason").val();
		   		var now = new Date();
		   		var date = new Date(onlineTime);
		   		if (!date>now){
		   			alert("上映时间需大于或等于今天!!");
		   			return;
		   		}
		   		
		   		if(preOnlineTime<onlineTime){
		   			alert("预上线时间需大于等于上映时间");
		   			return;
		   		}
		   		
		   		if(!verifyStringNotEmpty(itemName, "节目名", 32,true)){
					return;
				}
				if(!verifyStringNotEmpty(programaName, "所属分类", 32,true)){
					return;
				}
				if(!verifyStringNotEmpty(type, "所属类型", 32,true)){
					return;
				}
				if(!verifyStringNotEmpty(cp, "内容方", 32,true)){
					return;
				}
				if(!verifyDate(onlineTime, "上线时间",true)){
					return;
				}
				if(!verifyDate(preOnlineTime, "预上线时间", true)){
					return
				}
				if(!verifyStringNotEmpty(isCharge, "是否收费", 32,true)){
					return;
				}if(!verifyStringNotEmpty(isjh, "是否聚合", 32,true)){
					return;
				}if(!verifyStringNotEmpty(reason, "下线原因", 32,true)){
					return;
				}
                $.ajax({
                    type : "POST",  //提交方式
                    url : "cp/jhReportAction_save.do",//路径
                    data : {
                        "hybjReportForm.itemName" : itemName,
                        "hybjReportForm.programaName" : programaName,
                        "hybjReportForm.cp" : cp,
                        "hybjReportForm.onlineTime" : onlineTime,
                        "hybjReportForm.preOnlineTime" : preOnlineTime,
                        "hybjReportForm.isjh" : isjh,
                        "hybjReportForm.isCharge" : isCharge,
                        "hybjReportForm.remarks" : remarks,
                        "hybjReportForm.type" : type,
                        "hybjReportForm.offlineReason" : reason,
                        "isSubmit" : value,
                        "status" : "offline"
                    },//数据，这里使用的是Json格式进行传输
                    dataType : "json",
                    async : true,
                    success : function(result) {//返回数据根据结果进行相应的处理
                        if(result.message){
                            alert(result.message);
                            $("#itemName").val('');
							$("#programaName").val('');
                            $("#type").children().remove();
                            $("#onlineTime").val('');
							$("#preOnlineTime").val('');
                            $("#isCharge").val('');
                            $("#isjh").val('');
                            $("#remarks").val('');
                            $("#reason").val('');
                        }
                    }
                });



                // document.Form1.action="cp/hybjReportAction_save.do?isSumbit="+value+"&where="+cp;
				// document.Form1.submit();
				// refreshOpener();
		   	}
		   	
		   	
		   	function checkItemName(){
		   	var itemName = $("#itemName").val();
			$.ajax({  
                    type : "POST",  //提交方式  
                    url : "cp/jhReportAction_checkItemName.do",//路径
                    data : {  
                        "hybjReportForm.itemName" : itemName  
                    },//数据，这里使用的是Json格式进行传输  
                    dataType : "json",
					async : true,
                    success : function(result) {//返回数据根据结果进行相应的处理  
                        if(result.message){
                         alert(result.message);
                        }
                    }  
                });  
		   	}
		   
		   	function change(value){
		   	var $this = $(value);
   			var choseValue = $this.val();
			$.ajax({  
                    type : "POST",  //提交方式  
                    url : "../cp/jhReportAction_changeType.do",//路径
                    data : {  
                        "hybjReportForm.programaName" : choseValue  
                    },//数据，这里使用的是Json格式进行传输  
                    dataType : "json",
					async : true,
                    success : function(result) {//返回数据根据结果进行相应的处理  
                       showType(result);
                    }  
                });  
		   	}
			
			
			function showType(data){
			$("#type").empty();
			$("#type").append(
				"<option value=''>请选择类型</option>");
			for (var i = 0; i < data.typeList.length; i++) {
				$("#type").append(
						"<option value='" + data.typeList[i].ddlName
								+ "'>" + data.typeList[i].ddlName
								+ "</option>");
			}
			
			}
			
			
		   
		</script>
</head>
<body>
  <s:form name="Form1" method="post">
	 	<br>
    	<table cellSpacing="1" height="88" cellPadding="5" width="630" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">

	    <tr>
			<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
			 <font face="宋体" size="2"><strong>上报条目</strong></font>
			</td>
	    </tr>
	     <tr>
	         <td align="center" bgColor="#f5fafe" class="ta_11">节&nbsp;&nbsp;目&nbsp;&nbsp;名：<font color="#FF0000">*</font></td>
	         <td class="ta_11" bgColor="#ffffff">
	         	<s:textfield name="itemName" id="itemName" maxlength="25" size="20" onblur="checkItemName()"></s:textfield>
	         </td>
	         <td width="18%" align="center" bgColor="#f5fafe" class="ta_11">所属分类：<font color="#FF0000">*</font></td>
	         <td class="ta_11" bgColor="#ffffff">
	         	<%-- <s:textfield name="programaName" id="programaName" maxlength="25" size="20"></s:textfield> --%>
	         <select name="programaName" id="programaName" style="width:170px;height:25px" onchange="change(this);">
	         	<option>请选择分类</option>
	         	<option value="电影">电影</option>
	         	<option value="电视剧">电视剧</option>
	         	<option value="综艺">综艺</option>
	         	<option value="纪实">纪实</option>
	         	<option value="动画">动画</option>
	         </select>
	         </td>
	    </tr>
		<tr>
		    <td align="center" bgColor="#f5fafe" class="ta_11">类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：</td>
			<td class="ta_11" bgColor="#ffffff">
				<%-- <s:textfield name="type" id="type" maxlength="25" size="20" ></s:textfield> --%>
			 <select name="type" id="type" style="width:170px;height:25px" >
	         </select>
			</td>
			
			<td align="center" bgColor="#f5fafe" class="ta_11">内容方：<font color="#FF0000">*</font></td>
			<td class="ta_11" bgColor="#ffffff">
				<s:textfield name="cp" id="cp" maxlength="25" size="20" value="%{#request.department}" readOnly="true">
				</s:textfield>
			</td>
			
		</tr>
		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_11">上&nbsp;&nbsp;映&nbsp;&nbsp;时&nbsp;&nbsp;间：</td>
			<td class="ta_11" bgColor="#ffffff">
				<s:textfield name="onlineTime" id="onlineTime" maxlength="50" size="20" ></s:textfield>
			</td>
			<td align="center" bgColor="#f5fafe" class="ta_11">预上映时间：</td>
			<td class="ta_11" bgColor="#ffffff">
				<s:textfield name="PreOnlineTime" id="preOnlineTime" maxlength="50" size="20"></s:textfield>
			</td>
		</tr>

		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_11">是否付费：</td>
			<td class="ta_11" bgColor="#ffffff">
				<s:select list="#request.isChargeList" name="isCharge" id="isCharge"
				          listKey="ddlCode" listValue="ddlName"
				          headerKey="" headerValue=""
				          cssStyle="width:155px"
				          >
				</s:select>
			</td>
			<td align="center" bgColor="#f5fafe" class="ta_11">是否同步聚合：</td>
			<td class="ta_11" bgColor="#ffffff">
				<s:select list="#request.isjhList" name="isjh" id="isjh"
				          listKey="ddlCode" listValue="ddlName"
				          headerKey="" headerValue=""
				          cssStyle="width:155px"
				          >
				</s:select>
			</td>
		</tr>
		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_11">下线原因：</td>
			<td class="ta_11" bgColor="#ffffff" colspan="3">
				<s:textfield name="reason" id="reason" maxlength="90" size="65"></s:textfield>
			</td>

		</tr>

			<TR>
			<TD class="ta_11" align="left" bgColor="#f5fafe">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</TD>
			<TD class="ta_11" bgColor="#ffffff" colSpan="3">
				<s:textarea name="remarks" id="remarks" cssStyle="WIDTH:95%" rows="4" cols="52"></s:textarea>
			</TR>
			<TR>
				<td  align="center"  colSpan="4"  class="sep1"></td>
			</TR>
			<tr>
				<td class="ta_11" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
				<input type="button" name="BT_Submit" value="提交"  style="font-size:12px; color:black; height=22;width=55"   onClick="checkAndSave('yes')">
				 <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
				<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="保存草稿"  name="Reset1"  onClick="checkAndSave('no')">
				 <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
		
				</td>
			
					</tr>
				</table>　
			</s:form>		 
</body>
</html>
