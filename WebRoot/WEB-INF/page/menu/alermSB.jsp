<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
  <head>
    <title>load</title>
    <link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet" />
	  <LINK href="${pageContext.request.contextPath }/css/style_1.css" type="text/css" rel="stylesheet">
   </head>
  
  <body>
    <table id="rounded-corner" style="margin: 0px; width: 100%; text-align: left; border-collapse: collapse;">
    <tr>
		<th scope="col" class="rounded"  style="word-break: break-all">
		节目名字
		</th>
		<th scope="col" class="rounded"  style="word-break: break-all">
		上报时间
		</th>
		<th scope="col" class="rounded"  style="word-break: break-all">
		所属分类
		</th>
		<th scope="col" class="rounded"  style="word-break: break-all">
		类型
		</th>
		<th scope="col" class="rounded"  style="word-break: break-all">
		上映时间
		</th>
		<th scope="col" class="rounded"  style="word-break: break-all">
		上报类型
		</th>
	</tr>	
    	   <s:if test="#request.others!=null">
			<s:iterator value="%{#request.others}" var="other">
				<tr>
					<td align="left" valign="middle"  style="word-break: break-all">
					${other.item_name }
					</td>
					<td align="left" valign="middle"  style="word-break: break-all">
					${other.create_time }
					</td><td align="left" valign="middle"  style="word-break: break-all">
					${other.programa_name }
					</td>
					<td align="left" valign="middle"  style="word-break: break-all">
					${other.report_type }
					</td>
					<td align="left" valign="middle"  style="word-break: break-all">
					${other.online_time }
					</td>
					<td align="left" valign="middle"  style="word-break: break-all">
					${other.report_status == 'online'?'上线上报':'下线上报' }
					</td>
				</tr>	
			</s:iterator>	
	    </s:if>
	    
	</table>
  </body>
</html>