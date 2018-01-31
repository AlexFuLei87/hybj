<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<HTML>
	<HEAD>
		<title>聚合EPG管理</title>		
		<LINK href="${pageContext.request.contextPath }/css/Style.css"  type="text/css" rel="stylesheet">
		<LINK href="${pageContext.request.contextPath }/css/niceforms-default.css"  type="text/css" rel="stylesheet">
		<LINK href="${pageContext.request.contextPath }/css/form.css"  type="text/css" rel="stylesheet">
		<LINK href="${pageContext.request.contextPath }/css/frame.css"  type="text/css" rel="stylesheet">
		<LINK href="${pageContext.request.contextPath }/css/style_1.css"  type="text/css" rel="stylesheet">
		<script language="javascript"  src="${pageContext.request.contextPath }/script/function.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery.min.js"></script>
		<script language="javascript">
        function findByFuzzy() {
			var itemName = $("#demandName").val();
			var cpName = $("#cp").val();
            //"epg/hybjJhEpgAction_findByFuzzy.do"
			window.location.href = "epg/jhJhEpgAction_dxFindResultByFuzzy.do?hybjReport.itemName="+itemName+"&hybjReport.cp="+cpName;

        }
		</script>
	</HEAD>
	<body>
		<div style='width: 100%'>
	<div style='width:100%; float: left; height:100%; overflow:scroll;overflow-x:hidden'>
		<form id="form" name="form" >
			<table id="rounded-corner" style="margin: 0px; width: 100%; text-align: left; border-collapse: collapse;">
				<tr>
					<td colspan="6">
						节目名：
						<input type="text" size="25" name="demandName" id="demandName" value="" />
						cp:
						<s:select list="#request.jctList" name="cp" id="cp"
								  listKey="ddlName" listValue="ddlName"
								  headerKey="" headerValue=""
								  cssStyle="width:155px"
						>
						</s:select>
						<input onclick="findByFuzzy();" type="button" value="查询"/>
					</td>
					<td></td>
					<td></td>
					<td><input type="button" value="未处理" onclick="window.location.href='epg/jhJhEpgAction_dxOnline.do'"/></td>
				</tr>
				<tr>
					<th scope="col" class="rounded" style="width: 11%;">
						节目名称
					</th>
					<th scope="col" class="rounded" style="width: 11%;">
						申报时间
					</th>
					<th scope="col" class="rounded" style="width: 11%;">
						所属分类
					</th>
					<th scope="col" class="rounded" style="width: 11%;">
						所属类型
					</th>
					<th scope="col" class="rounded" style="width: 11%;">
						内容方
					</th>
					<th scope="col" class="rounded" style="width: 11%;">
						上线时间
					</th>
					<th scope="col" class="rounded" style="width: 11%;">
						预上线时间
					</th>
					<th scope="col" class="rounded" style="width: 11%;">
						是否收费
					</th>
					<th scope="col" class="rounded" style="width: 11%;">
						是否同步到聚合
					</th>
				</tr>
				<c:forEach items="${report}" var="report" varStatus="vs">
					<tr>
						<td>
							${report.item_name}
						</td>
						<td>
							${report.create_time}
						</td>
						<td>
							${report.programa_name}
						</td>
						<td>
							${report.report_type}
						</td>
						<td>
							${report.cp}
						</td>
						<td>
							<%--<s:if test="%{#report.online_time == 5}"></s:if>  ${fn:substring(c.description, 0, 12)}... --%>
								<c:if test="${fn:length(report.online_time)>10 }">
									${fn:substring(report.online_time, 0, 10)}
								</c:if>
								<c:if test="${fn:length(report.online_time)<=10 }">
									${report.online_time }
								</c:if>
						</td>
						<td>
							${report.preonline_time}
						</td>
						<td>
							${report.isCharge==true?'是':'否'}
						</td>
						<td>
							${report.is_jh==true?'是':'否'}
						</td>
					</tr>
				</c:forEach>
			</table>
		</form>
		</div>
		</div>
	</body>
</HTML>
