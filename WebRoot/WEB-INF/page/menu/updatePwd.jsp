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
	<script language="javascript">
		
		
  </script>
  </head>
  
 <body>
 	<s:form name="Form1" method="post">
	 <!--修改密码弹窗-->
<div id="dialog" class="div_mask">
    <div class="dialog_container">
        <!--标题-->
        <div class="title_dialog">
            <div>
                修<br/>改<br/>密<br/>码
            </div>
        </div>
        <!--详细信息-->
        <div class="div_info_dialog">
            <!--原始密码-->
            <div class="div_line_info_dialog">
                <div class="type_info_dialog">请输入原始密码：</div>
                <input id="txtOriginal" class="txt_info_dialog" type="password">
            </div>
            <!--新密码-->
            <div class="div_line_info_dialog">
                <div class="type_info_dialog">请输入新密码：</div>
                <input id="txtNew" type="password" class="txt_info_dialog" onkeyup="value=value.replace(/[^\d.]/g,'')">
            </div>
            <!--确认新密码-->
            <div class="div_line_info_dialog">
                <div class="type_info_dialog">请确认新密码：</div>
                <input id="txtConfirm" class="txt_info_dialog" type="password">
            </div>
        </div>
        <div class="dialog_div_btn">
            <!--保存按钮-->
            <button class="btn_save" type="button" onclick="btnSavePassword()">保存</button>
            <!--取消按钮-->
            <button class="btn_cancel" type="button" onclick="closeDialog()">取消</button>
        </div>
    </div>
</div>
	</s:form>		 

</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/datepicker_cn.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-ui.min.js"></script>
</html>
