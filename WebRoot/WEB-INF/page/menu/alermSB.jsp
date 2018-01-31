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
		申报时间
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
		审核状态
		</th>
		<th scope="col" class="rounded"  style="word-break: break-all">
		申报类型
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
						<s:if test="%{#other.status == 'normal'}">待聚合审核</s:if>
						<s:if test="%{#other.status == 'pass'}">待电信审核</s:if>
						<s:if test="%{#other.status == 'fail'}">聚合审核不通过</s:if>
						<s:if test="%{#other.status == 'dxpass'}">审核通过</s:if>
						<s:if test="%{#other.status == 'dxfail'}">电信审核不通过</s:if>
					</td>
					<td align="left" valign="middle"  style="word-break: break-all">
					${other.report_status == 'online'?'上线申报':'下线申报' }
					</td>
				</tr>	
			</s:iterator>	
	    </s:if>
	    
	</table>
  </body>
</html>