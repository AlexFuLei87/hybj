<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
  <head>
    <title></title>

	  <LINK href="${pageContext.request.contextPath }/css/Style.css"  type="text/css" rel="stylesheet">
	  <LINK href="${pageContext.request.contextPath }/css/niceforms-default.css"  type="text/css" rel="stylesheet">
	  <LINK href="${pageContext.request.contextPath }/css/form.css"  type="text/css" rel="stylesheet">
	  <LINK href="${pageContext.request.contextPath }/css/frame.css"  type="text/css" rel="stylesheet">
	  <LINK href="${pageContext.request.contextPath }/css/style_1.css"  type="text/css" rel="stylesheet">
	  <script language="javascript"  src="${pageContext.request.contextPath }/script/function.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery.min.js"></script>
    <SCRIPT language="javascript">
		function changeStatus(values,id) {
			var $this = $(values);
			var chos = $this.val();
			if(chos!=""){
			   if(chos==0){
                   updataStatus(id,"1");
			   }else if(chos==1){
                   updataStatus(id,"2");
			   }
			}
        }

        function updataStatus(id,status) {
            $.ajax({
                type : "POST",  //提交方式
                url : "epg/hybjJhEpgAction_changeStatus.do",//路径
                data : {
                    "hybjOutlineForm.id" : id,
                    "hybjOutlineForm.status" : status
                },//数据，这里使用的是Json格式进行传输
                dataType : "json",
                async : false,
                success : function(result) {//返回数据根据结果进行相应的处理
                    if(result.message){
                        alert(result.message);


                    }
                }
            });
        }
    </SCRIPT>
   </head>
  
  <body>
	<div style='width: 100%;z-index: 1'>
		<div style='width:100%; float: left; height:100%; overflow:scroll;overflow-x:hidden'>
				<table id="rounded-corner" style="margin: 0px; width: 100%; text-align: left; border-collapse: collapse;">
					<tr>
						<th scope="col" class="rounded" style="width: 15%;" hidden>

						</th>
						<th scope="col" class="rounded" style="width: 15%;">
							公告名称
						</th>
						<th scope="col" class="rounded" style="width: 14%;">
							公告概括
						</th>
						<th scope="col" class="rounded" style="width: 14%;">
							公告时间
						</th>
						<th scope="col" class="rounded" style="width: 14%;">
							公告状态
						</th>
						<th scope="col" class="rounded" style="width: 14%;">
							操作
						</th>
					</tr>
					<s:if test="#request.ggList!=null">
					<s:iterator value="%{#request.ggList}" var="list" status="status">
						<tr>
							<td hidden>
									${list.details }
							</td>
							<td>
									${list.gg_name }
							</td>
							<td>
									${list.outline }
							</td>
							<td>
									${list.create_time }
							</td>
							<td>
									${list.status=="1"?"正在公示":"未公示" }
							</td>
							<td>
								<select id="sele" class="status1" onchange="changeStatus(this,${list.id});" type="text">
									<option value="">选择操作</option>
									<option value="0">公示</option>
									<option value="1">撤销公示</option>
								</select>
							</td>
						</tr>
					</s:iterator>
					</s:if>
				</table>
		</div>
	</div>
  </body>
</html>
