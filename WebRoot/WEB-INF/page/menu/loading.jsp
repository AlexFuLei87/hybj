<%@ page language="java"  pageEncoding="UTF-8"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>     
    <title></title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache"><link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet" />
<style type="text/css">
<!--
body {
	background-color: #FFFFFF;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
body,td,th {
	color: #000000;
}
-->
    </style>
<style>
BODY {SCROLLBAR-FACE-COLOR: #cccccc; SCROLLBAR-HIGHLIGHT-COLOR: #ffffFF; SCROLLBAR-SHADOW-COLOR: #ffffff; SCROLLBAR-3DLIGHT-COLOR: #cccccc; SCROLLBAR-ARROW-COLOR:  #ffffff; SCROLLBAR-TRACK-COLOR: #ffffFF; SCROLLBAR-DARKSHADOW-COLOR: #cccccc; }
</style>
<%-- <link href="${pageContext.request.contextPath }/css/login.css" rel="stylesheet" type="text/css"> --%>
</head>

<body>

<form name="Form1" method="post" action="name.aspx" id="Form1">

	<table width="100%" border="0" height="88" border="1" background=${pageContext.request.contextPath }/images/back1.jpg>
		<tr>
			<td  class="ta_01" align="center" background="${pageContext.request.contextPath }/images/b-info.gif"><strong>公示首页</strong></td>
			<td  class="ta_01" align="center" background="${pageContext.request.contextPath }/images/b-info.gif"><strong>公告首页</strong></td>
		</tr>

		<tr>
			<td width="50%" height="84" align="left" valign="top" >
			<fieldset style="width: 99.6%; height: 700%; padding: 1"><legend>
			<font color="#0000FF">
			<img border="0" src="${pageContext.request.contextPath }/images/zoom.gif" width="14" height="14"> 公示区展示</font></legend>
			<IFRAME src="${pageContext.request.contextPath }/system/jhMenuAction_alermGS.do"  name="project" id="project" frameBorder=0 width=100% scrolling=auto height=100%></IFRAME>
			</fieldset>
			</td>
			<td width="50%" height="84" align="left" valign="top" >
				<fieldset style="width: 100%; height: 700%; padding: 1"><legend>
					<font color="#0000FF">
						<img border="0" src="${pageContext.request.contextPath }/images/zoom.gif" width="14" height="14"> 公告展示</font></legend>
					<IFRAME src="${pageContext.request.contextPath }/system/jhMenuAction_alermGG.do"  name="project"  frameBorder=0 width=100% scrolling=auto height=100%></IFRAME>
				</fieldset>
			</td>
		</tr>
		<tr><td height=2></td></tr>
	</table>
	</form>
</body>
</html>
