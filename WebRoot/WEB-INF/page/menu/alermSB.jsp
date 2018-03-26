<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page import="cn.hybj.util.PageBean"%>
<html>
  <head>
    <title>load</title>
    <link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet" />
	  <LINK href="${pageContext.request.contextPath }/css/style_1.css" type="text/css" rel="stylesheet">
	  <script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery.min.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
	  <script language="javascript" src="${pageContext.request.contextPath }/script/page.js?vs=1"></script>
	  <style>
		  table tbody {
			  display:block;
			  height:700px;
			  overflow-y:scroll;
		  }

		  table thead, tbody tr {
			  display:table;
			  width:100%;
			  table-layout:fixed;
		  }

		  table thead {
			  width: calc( 100% - 1em )
		  }
		  table thead th{ background:#ccc;}
	  </style>
   </head>

  
  <body>
  <s:form id="Form1" name="Form1" >
	  <input type="hidden" name="initflag" id="initflag" value="1"/>
	  <input type="hidden" name="pageNO" id="pageNO" value="1"/>
	  <input type="hidden" name="pageSize" id="pageSize" value=""/>
  </s:form>
  <s:form id="Form2" name="Form2" >
    <table id="rounded-corner" style="margin: 0px; width: 100%; text-align: left; border-collapse: collapse;">
		<thead>
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
		</thead>
    	   <s:if test="#request.others!=null">
			<s:iterator value="%{#request.others}" var="other">
				<tr>
					<td align="left" valign="middle"  style="word-break: break-all">
					${other.itemName }
					</td>
					<td align="left" valign="middle"  style="word-break: break-all">
					${other.createTime }
					</td><td align="left" valign="middle"  style="word-break: break-all">
					${other.programaName }
					</td>
					<td align="left" valign="middle"  style="word-break: break-all">
					${other.type }
					</td>
					<td align="left" valign="middle"  style="word-break: break-all">
					${other.onlineTime }
					</td>
					<td align="left" valign="middle"  style="word-break: break-all">
						<s:if test="%{#other.status == 'normal'}">待聚合审核</s:if>
						<s:if test="%{#other.status == 'pass'}">待电信审核</s:if>
						<s:if test="%{#other.status == 'fail'}">聚合审核不通过</s:if>
						<s:if test="%{#other.status == 'dxpass'}">审核通过</s:if>
						<s:if test="%{#other.status == 'dxfail'}">电信审核不通过</s:if>
					</td>
					<td align="left" valign="middle"  style="word-break: break-all">
					${other.reportStatus == 'online'?'上线申报':'下线申报' }
					</td>
				</tr>	
			</s:iterator>	
	    </s:if>
		<s:if test="#request.others.size > 199">
		<tr>
			<td width="100%" height="1"  colspan="7">
				<table border="0" width="100%" cellspacing="0" cellpadding="0">
					<%PageBean pagebean=(PageBean)request.getAttribute("page");%>
					<tr>
						<td width="15%" align="left">总记录数：<%=pagebean.getTotalResult() %>条</td>
						<td width="14%" align="right"></td>
						<%if(pagebean.getFirstPage()){ %>
						<td width="8%" align="center">首页&nbsp;&nbsp;|</td>
						<td width="10%" align="center">上一页&nbsp;&nbsp;&nbsp;|</td>
						<%}else{ %>
						<td width="8%" align="center"><u><a href="#" onClick="gotopage('system/jhMenuAction_alermSB.do?cpName=${department}','start')">首页&nbsp;&nbsp;|</a></u></td>
						<td width="10%" align="center"><u><a href="#" onClick="gotopage('system/jhMenuAction_alermSB.do?cpName=${department}','prev')">上一页&nbsp;&nbsp;&nbsp;|</a></u></td>
						<%} %>
						<%if(pagebean.getLastPage()){ %>
						<td width="10%" align="center">下一页&nbsp;&nbsp;&nbsp;|</td>
						<td width="8%" align="center">末页</td>
						<%}else{ %>
						<td width="10%" align="center"><u><a href="#" onClick="gotopage('system/jhMenuAction_alermSB.do?cpName=${department}','next')">下一页&nbsp;&nbsp;&nbsp;|</a></u></td>
						<td width="8%" align="center"><u><a href="#" onClick="gotopage('system/jhMenuAction_alermSB.do?cpName=${department}','end')">末页</a></u></td>
						<%} %>
						<td width="6%" align="center">第<%=pagebean.getPageNo() %>页</td>
						<td width="6%" align="center">共<%=pagebean.getSumPage() %>页</td>

						<td><input type="hidden" id="pageNO2" name="pageNO" value="<%=pagebean.getPageNo()%>" ></td>
						<td><input type="hidden" id="prevpageNO2" name="prevpageNO" value="<%=(pagebean.getPageNo()-1)%>"></td>
						<td><input type="hidden" id="nextpageNO2" name="nextpageNO" value="<%=(pagebean.getPageNo()+1)%>"></td>
						<td><input type="hidden" id="sumPage2" name="sumPage" value="<%=pagebean.getSumPage() %>" ></td>
						<td><input type="hidden" id="pageSize2" name="pageSize" value="" ></td>
					</tr>
				</table>
			</td>
		</tr>
	</s:if>
	</table>
  </s:form>
  </body>
</html>