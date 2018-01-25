<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<title>管理</title>		
		<LINK href="${pageContext.request.contextPath }/css/Style.css"  type="text/css" rel="stylesheet">
		<LINK href="${pageContext.request.contextPath }/css/jquery-ui.min.css"  type="text/css" rel="stylesheet">
		<script language="javascript"  src="${pageContext.request.contextPath }/script/function.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/input_verify.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/script/kindeditor-4.1.10/kindeditor-min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/script/kindeditor-4.1.10/lang/zh_CN.js"></script>

        <script language="javascript">
            var contentAddEditor ;
            $(function(){
                contentAddEditor = KindEditor.create("#Form1 [name=details]", {
                    items: ["source", "|", "undo", "redo", "|", "preview", "print", "template", "code", "cut", "copy", "paste", "plainpaste", "wordpaste",
                        "|", "justifyleft", "justifycenter", "justifyright", "justifyfull", "insertorderedlist", "insertunorderedlist", "indent", "outdent", "subscript", "superscript", "clearhtml", "quickformat"]
                })
            });
            
            function submitOutline() {
                contentAddEditor.sync();
                $.post("epg/jhJhEpgAction_saveOutline.do",$("#Form1").serialize(), function(data){
                    var data1 = eval("("+data+")");
                   alert(data1.message);
                   location.reload();
                });
            }


            function clearNotice() {
               location.reload();

            }

		</script>
	</HEAD>
	<body>
	
<form name="Form1" method="post" action="name.aspx" id="Form1">
	<table width="100%" border="0" height="88" border="1" background=${pageContext.request.contextPath }/images/back1.jpg>
		<%-- <tr>
			<td colspan=2 class="ta_01" align="center" background="${pageContext.request.contextPath }/images/b-info.gif"><strong>公告首页</strong></td>
		</tr>
		 --%>
		<tr>
			<td width="50%" height="84" align="left" valign="top" >
			
			<fieldset style="width: 95%; height: 630px; padding: 1 background:${pageContext.request.contextPath }/images/back1.JPG"><legend>
			<font color="#0000FF">
			<img border="0" src="${pageContext.request.contextPath }/images/zoom.gif" width="14" height="14"> 公示内容 </font></legend>
                    <br>
                    <table cellSpacing="1" height="88" cellPadding="5" width="580" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">

                        <tr>
                            <td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
                                <font face="宋体" size="2"><strong>公示内容</strong></font>
                            </td>
                        </tr>
                        <tr>
                            <td align="center" bgColor="#f5fafe" class="ta_11">节&nbsp;&nbsp;目&nbsp;&nbsp;名：<font color="#FF0000">*</font></td>
                            <td class="ta_11" bgColor="#ffffff">
                                <s:textfield name="outlineName" id="outlineName" maxlength="25" size="20" ></s:textfield>
                            </td>
                        </tr>
                        <tr>
                            <td align="center" bgColor="#f5fafe" class="ta_11">公&nbsp;&nbsp;示&nbsp;&nbsp;概&nbsp;&nbsp;要：</td>
                            <td class="ta_11" bgColor="#ffffff">
                                <s:textfield name="outline" id="outline" maxlength="25" size="20" ></s:textfield>
                            </td>
                        </tr>
                        <tr>
                            <td align="center" bgColor="#f5fafe" class="ta_21">公&nbsp;&nbsp;示&nbsp;&nbsp;详&nbsp;&nbsp;情：</td>
                            <td class="ta_21" bgColor="#ffffff">
                                <textarea name="details" id="details" style="height: 400px" rows="4" cols="52"></textarea>
                            </td>

                        </tr>

                        <tr>
                            <td class="ta_11" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
                                <input type="button" name="BT_Submit" value="提交"  style="font-size:12px; color:black; height=22;width=55"   onClick="submitOutline();">
                                <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
                                <input style="font-size:12px; color:black; height=22;width=55"  type="button" value="取消"  name="Reset1"  onClick="clearNotice()">
                                <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>

                            </td>

                        </tr>
                    </table>　
            </fieldset>
			
			</td>
			<td width="50%" align="left" valign="top">
			
			<fieldset style="width: 95%; height: 100%; padding: 1  background:${pageContext.request.contextPath }/images/back1.JPG"><legend>
			<font color="#0000FF">
			<img border="0" src="${pageContext.request.contextPath }/images/zoom.gif" width="14" height="14">以往公示内容</font></legend>
			
			<IFRAME src="${pageContext.request.contextPath }/epg/jhJhEpgAction_showYW.do"  name="station"  frameBorder=0 width=100% scrolling=auto height=100%></IFRAME>
				     
		   </fieldset>
			
			</td>
		</tr>
	</table>
	</form>
	

	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/datepicker_cn.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-ui.min.js"></script>

</HTML>
