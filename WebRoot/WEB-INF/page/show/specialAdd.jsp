<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
  <head>
   <title>专题上报</title>
   <LINK href="${pageContext.request.contextPath }/css/Style.css"  type="text/css" rel="stylesheet">
		<LINK href="${pageContext.request.contextPath }/css/jquery-ui.min.css"  type="text/css" rel="stylesheet">
		<script language="javascript"  src="${pageContext.request.contextPath }/script/function.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/input_verify.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/datepicker_cn.js"></script>
	   <script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-ui.min.js"></script>
		<script language="javascript">
		function checkAndSave() {
			var specialName = $("#specialNama").val();
			var itemName = $("#itemName").val();
			var cp = $("#cp").val();
			var onDate = $("#onDate").val();
            if(!verifyStringNotEmpty(specialName, "专题名", 32,true)){
                return;
            }if(!verifyStringNotEmpty(itemName, "节目名", 32,true)){
                return;
            }if(!verifyStringNotEmpty(cp, "内容方", 32,true)){
                return;
            }
            $.ajax({
                type : "POST",  //提交方式
                url : "../cp/hybjSpecialAction_save.do",//路径
                data :{
                    "hybjSpecial.specialName":specialName,
                    "hybjSpecial.itemName":itemName,
                    "hybjSpecial.onDate":onDate,
                    "hybjSpecial.cp":cp
				},//数据，这里使用的是Json格式进行传输
                dataType : "json",
                async : true,
                success : function(result) {//返回数据根据结果进行相应的处理
					if(result.message){
					    alert(result.message);
					    $("#specialNama").val('');
                        $("#itemName").val('');
					}

                }
            });
        }
		

			
		   
		</script>
</head>
<body>
  <s:form name="Form1" method="post">
	 	<br>
    	<table cellSpacing="1" height="88" cellPadding="5" width="530" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">

	    <tr>
			<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
			 <font face="宋体" size="2"><strong>专题上报</strong></font>
			</td>
	    </tr>
	     <tr>
	         <td align="center" bgColor="#f5fafe" class="ta_11">专&nbsp;&nbsp;题&nbsp;&nbsp;名：<font color="#FF0000">*</font></td>
	         <td class="ta_11" bgColor="#ffffff" colspan="3">
	         	<s:textfield name="specialNama" id="specialNama" maxlength="25" size="20"></s:textfield>
	         </td>
	    </tr>
		<tr>
		    <td align="center" bgColor="#f5fafe" class="ta_11">节&nbsp;&nbsp;目&nbsp;&nbsp;名：</td>
			<td class="ta_11" bgColor="#ffffff">
				 <s:textfield name="itemName" id="itemName" maxlength="25" size="20" ></s:textfield>

			</td>
		</tr>
		<tr>
		    <td align="center" bgColor="#f5fafe" class="ta_11">申&nbsp;&nbsp;请&nbsp;&nbsp;时&nbsp;&nbsp;间：</td>
			<td class="ta_11" bgColor="#ffffff">
				<select name="onDate" id="onDate" style="width:170px;height:25px" onchange="change(this);">
					<option value="周一">周一</option>
					<option value="周五">周五</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_11">内容方：<font color="#FF0000">*</font></td>
			<td class="ta_11" bgColor="#ffffff">
				<s:textfield name="cp" id="cp" maxlength="25" size="20" value="%{#request.department}" readOnly="true">
				</s:textfield>
			</td>
		</tr>

			<TR>
				<td  align="center"  colSpan="4"  class="sep1"></td>
			</TR>
			<tr>
				<td class="ta_11" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
				<input type="button" name="BT_Submit" value="提交"  style="font-size:12px; color:black; height=22;width=55"   onClick="checkAndSave()">
				 <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
				<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="关闭"  name="Reset1"  onClick="window.close()">
				 <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
		
				</td>
			
					</tr>
				</table>　
			</s:form>		 
</body>
</html>
