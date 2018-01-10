<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
  <head>
   <title>添加用户</title>
   <LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
   <script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
   <script type="text/javascript" language="JavaScript" src="${pageContext.request.contextPath }/script/calendar.js" charset="gb2312"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath }/script/validate.js"></script>
<script language="javascript">

	function check_null(){
	    var theForm=document.Form1;
	    
	    if(Trim(theForm.logonName.value)=="")
		{
			alert("登录名不能为空");
			theForm.logonName.focus();
			return false;
		}
	    if(Trim(theForm.userName.value)=="")
		{
			alert("用户姓名不能为空");
			theForm.userName.focus();
			return false;
		}
	    if(theForm.jctID.value=="")
		{
			alert("请选择所属单位");
			theForm.jctId.focus();
			return false;
		}
	
        if(theForm.logonPwd.value!=theForm.passwordconfirm.value){
		
		  alert("两次输入密码不一致，请重新输入");
		  return;
		}
		if(checkNull(theForm.contactTel)){
         if(!checkPhone(theForm.contactTel.value))
		  {
			alert("请输入正确的电话号码");
			theForm.contactTel.focus();
			return false;
		  }
		}
		
	    if(checkNull(theForm.mobile)){
         if(!checkMobilPhone(theForm.mobile.value))
		  {
			alert("请输入正确的手机号码");
			theForm.mobile.focus();
			return false;
		  }
		}
		
	   if(checkNull(theForm.email))	{
         if(!checkEmail(theForm.email.value))
		 {
			alert("请输入正确的EMail");
			theForm.email.focus();
			return false;
		 }
	   }
		
	   if(theForm.remark.value.length>250){
     
        	alert("备注字符长度不能超过250");
			theForm.remark.focus();
			return false; 
        }
		 document.Form1.action="system/hybjUserAction_save.do";
		 document.Form1.submit();	 		 
		 refreshOpener();
	}
	
	//使用ajax进行异步校验，校验登录名在数据库是否存在
	//创建ajax引擎
	 function checkLogonName(){
	 	
	 	//第三步：与后台服务器建立一个连接
	 	//xmlHttp.open("post","../../checkLogonName",true);
	 	//第四步：发送请求的参数
	 	//xmlHttp.send("logonName="+logonName);
	 }
   </script>
  </head>
  
 <body>
 
<s:form name="Form1" method="post">
 <br>
    <table cellSpacing="1" cellPadding="5" width="580" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">

	    <tr>
			<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
			 <font face="宋体" size="2"><strong>添加用户</strong></font>
			</td>
	    </tr>
	     <tr>
	         <td align="center" bgColor="#f5fafe" class="ta_01">登&nbsp;&nbsp;录&nbsp;&nbsp;名：<font color="#FF0000">*</font></td>
	         <td class="ta_01" bgColor="#ffffff">
	         	<s:textfield name="logonName" id="logonName" maxlength="25" size="20" onblur="checkLogonName()"></s:textfield>
	         </td>
	         <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">用户姓名：<font color="#FF0000">*</font></td>
	         <td class="ta_01" bgColor="#ffffff">
	         	<s:textfield name="userName" id="userName" maxlength="25" size="20"></s:textfield>
	         </td>
	    </tr>
		<tr>
		    <td align="center" bgColor="#f5fafe" class="ta_01">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:select list="#request.sexList" name="sexID" id="sexID"
				          listKey="ddlCode" listValue="ddlName"
				          headerKey="" headerValue=""
				          cssStyle="width:155px"
				          >
				</s:select>
			</td>
			
			<td align="center" bgColor="#f5fafe" class="ta_01">所属单位：<font color="#FF0000">*</font></td>
			<td class="ta_01" bgColor="#ffffff">
				<s:select list="#request.jctList" name="jctID" id="jctID"
				          listKey="ddlCode" listValue="ddlName"
				          headerKey="" headerValue=""
				          cssStyle="width:155px"
				          >
				</s:select>
			</td>
			
		</tr>
		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_01">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:password name="logonPwd" id="logonPwd" maxlength="25" size="22"></s:password>
			</td>
			<td align="center" bgColor="#f5fafe" class="ta_01">确认密码：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:password name="passwordconfirm" id="passwordconfirm" maxlength="25" size="22"></s:password>
			</td>
		</tr>

		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_01">出生日期：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield name="birthday" maxlength="50"  size="20" onclick="JavaScript:calendar(document.Form1.birthday)" ></s:textfield>
			</td>
			<td align="center" bgColor="#f5fafe" class="ta_01">联系地址：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield name="address" maxlength="50"  size="20"></s:textfield>
			</td>
		</tr>

		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_01">联系电话：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield name="contactTel" maxlength="25"  size="20"></s:textfield>
			</td>
			<td align="center" bgColor="#f5fafe" class="ta_01">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield name="mobile" maxlength="25"  size="20"></s:textfield>
			</td>
		</tr>

		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_01">电子邮箱：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield name="email" maxlength="50"  size="20"></s:textfield>
			</td>
			<td align="center" bgColor="#f5fafe" class="ta_01">是否在职：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:select list="#request.isdutyList" name="isDuty" id="isDuty"
				          listKey="ddlCode" listValue="ddlName"
				          value="1"
				          cssStyle="width:155px"
				          >
				</s:select>
			</td>
		</tr>

		<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">入职日期：</td>
		<td class="ta_01" bgColor="#ffffff">
			<s:textfield name="onDutyDate" id="onDutyDate" maxlength="50" size="20" onclick="JavaScript:calendar(document.Form1.onDutyDate)"></s:textfield>
		</td>
		<td align="center" bgColor="#ffffff" class="ta_01"></td>
		<td class="ta_01" bgColor="#ffffff"></td>
		</tr>

		<TR>
		<TD class="ta_01" align="center" bgColor="#f5fafe">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</TD>
		<TD class="ta_01" bgColor="#ffffff" colSpan="3">
			<s:textarea name="remark" id="remark" cssStyle="WIDTH:95%" rows="4" cols="52"></s:textarea>
		</TR>
		<TR>
			<td  align="center"  colSpan="4"  class="sep1"></td>
		</TR>
		<tr>
			<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
			<input type="button" name="BT_Submit" value="保存"  style="font-size:12px; color:black; height=22;width=55"   onClick="check_null()">
			 <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
			<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="关闭"  name="Reset1"  onClick="window.close()">
				
			</td>
		</tr>
	</table>　
</s:form>
</body>
</html>
