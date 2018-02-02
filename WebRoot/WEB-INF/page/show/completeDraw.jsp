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
   </head>
  <body>
	<div style='width: 100%;z-index: 1'>
		<input style="font-size:12px; color:black; height=30;width=120"  type="button" value="未完成" name="BT_Import"
			   onclick="window.location.href='cp/jhShowAction_drawing.do'">
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
							附件名
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
								<a href="${pageContext.request.contextPath }/cp/dowZTFile.do?specialId=${list.id }">${list.attachment_name }</a>
							</td>
						</tr>
					</s:iterator>
					</s:if>
				</table>
		</div>
	</div>


  </body>
</html>
