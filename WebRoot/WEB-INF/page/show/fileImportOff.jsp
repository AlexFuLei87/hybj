<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
  <head> 
    <title>导入文件</title> 
    <link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery.min.js"></script>
    <SCRIPT language="javascript">
    	$(function() {
			var message = "${message}";
			if(message){
			alert(message);}
		});
    
       function checkFile(value){
       var $this = $(value);
   	   var fileName = $this.val();
   	   
       if(!/\.(xlsx|xls)$/.test(fileName)){
        alert("选择的文件必须是Excel文件");
        $this.val("");
      }
       if(/\.(xlsx)$/.test(fileName)){
        alert("请使用word 2003格式Excel文件（以xls结尾的文件）");
        $this.val("");
      }
  
       }
     
    </SCRIPT>
    
  </head>
  
  <body>
    <s:form action="/cp/hybjReportAction_importOffdata.do" method="post" enctype="multipart/form-data">
      <br>
      <table border="0" width="100%" cellspacing="0" cellpadding="0">
      	<tr>
      		<td class="ta_01" align="center" background="${pageContext.request.contextPath }/images/b-info.gif" colspan="4">
				<font face="宋体" size="2"><strong>Excel文件数据导入</strong></font>
			</td>
		</tr>
		<tr>
          <td width="1%" height=30></td>
          <td width="20%"></td>
          <td width="78%"></td>
          <td width="1%"></td>
        </tr>
        <tr>
          <td width="1%"></td>
          <td width="15%" align="center">请选择文件:</td>
          <td width="83%" align="left">
          <s:file onchange="checkFile(this);" name="file" cssStyle="width:365px"></s:file>
          <input name = "cp" id="cp" value="${department }" hidden>
          <input name = "reportStatus" id="reportStatus" value="offline" hidden>

          </td>
          <td width="1%"></td>
        </tr>
        <tr height=50><td colspan=4 ></td></tr>
	    <tr height=2><td colspan=4 background="${pageContext.request.contextPath }/images/b-info.gif"></td></tr>
	    <tr height=10><td colspan=4 ></td></tr>
        <tr>
          <td align="center" colspan=4>
          	  <s:submit name="import" value="导入" cssStyle="width: 60px; font-size:12px; color:black; height:22"></s:submit>
	          <INPUT type="button"  name="Reset1" id="save"  value="关闭"  onClick="window.close();" style="width: 60px; font-size:12px; color:black; height=22">
          </td>
        </tr>
      </table>
    </s:form>
  </body>
</html>
