<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			window.location.href = "epg/jhJhEpgAction_offlineResult.do?hybjReport.itemName="+itemName+"&hybjReport.cp="+cpName+"&hybjReport.reportStatus=offline";

        }
		</script>
	</HEAD>
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
	<body>
		<div style='width: 100%'>
	<div style='width:100%; float: left; height:100%; overflow:scroll;overflow-x:hidden'>
		<form id="form" name="form" >
			<table id="rounded-corner" style="margin: 0px; width: 100%; text-align: left; border-collapse: collapse;">
				<thead>
				<tr>
					<td colspan="6" style="text-align: left;">
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
					<td></td>
					<td><input type="button" value="未处理" onclick="window.location.href='epg/jhJhEpgAction_showOffline.do'"/></td>
				</tr>
				<tr>
					<th scope="col" class="rounded" style="width: 9%;">
						节目名称
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						申报时间
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						所属分类
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						所属类型
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						内容方
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						上线时间
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						预上线时间
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						是否收费
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						是否同步到聚合
					</th>
					<th scope="col" class="rounded" style="width: 9%;">
						下线原因
					</th>
				</tr>
				</thead>
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
							${report.online_time}
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
						<td>
							${report.offline_reason}
						</td>
					</tr>
				</c:forEach>
			</table>
		</form>
		</div>
		</div>
	</body>
</HTML>
