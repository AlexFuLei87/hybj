<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
  <head>
    <title></title>

	  <LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
	  <LINK href="${pageContext.request.contextPath }/css/niceforms-default.css" type="text/css" rel="stylesheet">
	  <LINK href="${pageContext.request.contextPath }/css/form.css" type="text/css" rel="stylesheet">
	  <LINK href="${pageContext.request.contextPath }/css/frame.css" type="text/css" rel="stylesheet">
	  <LINK href="${pageContext.request.contextPath }/css/style_1.css" type="text/css" rel="stylesheet">
	  <script language="javascript"  src="${pageContext.request.contextPath }/script/function.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery.min.js"></script>
    <SCRIPT language="javascript">
		function uploadFile(value) {
            $("#id").val(value);
            $("#details1").show();
        }

        function closeWindow() {
            $("#details1").hide();
        }
        function checkFile(value) {
            var $this = $(value);
            var fileName = $this.val();
            if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG|rar|zip)$/.test(fileName)){
                alert("选择的文件必须是图片或者压缩文件");
                $this.val("");
            }
        }
    </SCRIPT>
	  <style>
		  .dialog_container1 {
			  position: fixed;
			  left: 50%;
			  top: 40%;
			  margin: -225px 0 0 -389px;
			  width: 588px;
			  height: 580px;
			  border-radius: 5px;
		  }
	  </style>
   </head>
  <body>
	<div style='width: 100%;z-index: 1'>
		<input style="font-size:12px; color:black; height=30;width=120"  type="button" value="已完成" name="BT_Import"
			   onclick="window.location.href='cp/jhSpecialAction_completeDraw.do'">
		<div style='width:100%; float: left; height:100%; overflow:scroll;overflow-x:hidden'>
				<table id="rounded-corner" style="margin: 0px; width: 100%; text-align: left; border-collapse: collapse;">
					<tr>
						<th scope="col" class="rounded" style="width: 12.5%;">
							专题名
						</th>
						<th scope="col" class="rounded" style="width: 12.5%;">
							节目名
						</th>
						<th scope="col" class="rounded" style="width: 12.5%;">
							专题内容方
						</th>
						<th scope="col" class="rounded" style="width: 12.5%;">
							申请完成时间
						</th>
						<th scope="col" class="rounded" style="width: 12.5%;">
							创建时间
						</th>
						<th scope="col" class="rounded" style="width: 12.5%;">
							审核时间
						</th>
						<th scope="col" class="rounded" style="width: 12.5%;">
							附件上传
						</th>
					</tr>
					<s:if test="#request.ztList!=null">
					<s:iterator value="%{#request.ztList}" var="list">
						<tr>
							<td>
									${list.special_name }
							</td>
							<td>
									${list.item_name }
							</td>
							<td>
									${list.cp }
							</td>
							<td>
									${list.onDate }
							</td>
							<td>
									${list.create_time }
							</td>
							<td>
									${list.verify_time }
							</td>
							<td>
								<button onclick="uploadFile(${list.id })">附件上传</button>
							</td>
						</tr>
					</s:iterator>
					</s:if>
				</table>
		</div>
	</div>
	<div class="dialog_container1" id="details1" hidden >
		<s:form action="/cp/jhSpecialAction_uploadImages.do" method="post" enctype="multipart/form-data">
			<br>
			<table  cellSpacing="1" height="88" cellPadding="5" width="580" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0" >
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
						<input name = "id" id="id" hidden>
					</td>
					<td width="1%"></td>
				</tr>
				<tr height=50><td colspan=4 ></td></tr>
				<tr height=2><td colspan=4 background="${pageContext.request.contextPath }/images/b-info.gif"></td></tr>
				<tr height=10><td colspan=4 ></td></tr>
				<tr>
					<td align="center" colspan=4>
						<s:submit name="import" value="上传" cssStyle="width: 60px; font-size:12px; color:black; height:22"></s:submit>
						<INPUT type="button"  name="Reset1" id="save"  value="关闭"  onClick="closeWindow();" style="width: 60px; font-size:12px; color:black; height=22">
					</td>
				</tr>
			</table>
		</s:form>
	</div>

  </body>
</html>
