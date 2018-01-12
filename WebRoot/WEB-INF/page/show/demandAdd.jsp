<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
  <head>
   <title></title>
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
			$("#completeTime").datepicker({
				dateFormat : "yy-mm-dd"
			});

		});
		function quit() {
			window.close();
        }

        function submitDemand() {
            $.ajax({
                type : "POST",  //提交方式
                url : "../cp/hybjDemandAction_save.do",//路径
                data :$("#Form1").serialize(),//数据，这里使用的是Json格式进行传输
                dataType : "json",
                async : true,
                success : function(result) {//返回数据根据结果进行相应的处理

                }
            });
        }

        function checkData() {
		    var demandName = $("#demandName").val();
		    var fenlei = $("#fenlei").val();
		    var completeTime = $("#completeTime").val();

            if(!verifyDate(completeTime, "完成时间",true)){
                return false;
            }
            if(!verifyStringNotEmpty(demandName, "需求名称", 32,true)){
                return false;
            }
            if(!verifyStringNotEmpty(fenlei, "需求分类", 32,true)){
                return false;
            }
			return true;
        }
		
		

		</script>
</head>
<body>
<s:form action="../cp/hybjDemandAction_save.do" onsubmit="return checkData()" name="Form1" method="post" id="Form1" enctype="multipart/form-data">
	<br>
	<table cellSpacing="1" height="88" cellPadding="5" width="630" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">

		<tr>
			<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
				<font face="宋体" size="2"><strong>需求上报</strong></font>
			</td>
		</tr>
		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_11">需&nbsp;&nbsp;求&nbsp;&nbsp;名&nbsp;&nbsp;称：<font color="#FF0000">*</font></td>
			<td class="ta_11" bgColor="#ffffff">
				<s:textfield name="demandName" id="demandName" maxlength="25" size="20"></s:textfield>
			</td>
			<td width="18%" align="center" bgColor="#f5fafe" class="ta_11">分类：<font color="#FF0000">*</font></td>
			<td class="ta_11" bgColor="#ffffff">
					<%-- <s:textfield name="programaName" id="programaName" maxlength="25" size="20"></s:textfield> --%>
				<select name="fenlei" id="fenlei" style="width:170px;height:25px" onchange="change(this);">
					<option>请选择分类</option>
					<option value="营销">营销</option>
					<option value="支撑">支撑</option>
					<option value="数据">数据</option>
					<option value="其他">其他</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_11">完&nbsp;&nbsp;成&nbsp;&nbsp;时&nbsp;&nbsp;间：</td>
			<td class="ta_11" bgColor="#ffffff">
				<s:textfield name="completeTime" id="completeTime" maxlength="50" size="20" ></s:textfield>
			</td>

			<td align="center" bgColor="#f5fafe" class="ta_11">内容方：<font color="#FF0000">*</font></td>
			<td class="ta_11" bgColor="#ffffff">
				<s:textfield name="cp" id="cp" maxlength="25" size="20" value="%{#request.department}" readOnly="true">
				</s:textfield>
			</td>

		</tr>

		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_11">附件上传：</td>
			<td class="ta_11" bgColor="#ffffff"  colSpan="3">
				<s:file name="file" id="file" cssStyle="width:365px"></s:file>
			</td>
		</tr>

		<TR>
			<TD class="ta_11" align="left" bgColor="#f5fafe">需&nbsp;&nbsp;求&nbsp;&nbsp;详&nbsp;&nbsp;情：</TD>
			<TD class="ta_11" bgColor="#ffffff" colSpan="3">
					<s:textarea name="xqDetails" id="xqDetails" cssStyle="WIDTH:95%" rows="4" cols="52"></s:textarea>
		</TR>
		<TR>
			<td  align="center"  colSpan="4"  class="sep1"></td>
		</TR>
		<tr>
			<td class="ta_11" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
				<s:submit name="import" value="提交" cssStyle="width: 60px; font-size:12px; color:black; height:22"></s:submit>
				<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
				<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="退出"  name="Reset1"  onClick="quit()">
				<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>


			</td>

		</tr>
	</table>　
</s:form>
</body>
</html>
