<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.Hashtable"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String popedom = (String)request.getSession().getAttribute("globle_popedom"); 
Hashtable ht = (Hashtable)request.getSession().getAttribute("globle_role");
%>
<HTML>
	<HEAD>
		<TITLE>Left</TITLE>
		<STYLE type="text/css">BODY {
	MARGIN: 0px; BACKGROUND-COLOR: #8ba7e3
}
BODY {
	COLOR: #000000
}
TD {
	COLOR: #000000
}
TH {
	COLOR: #000000
} 
		</STYLE>
		<LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
		<SCRIPT language="JavaScript">
		a="";
		NS4 = (document.layers) ? 1 : 0;
		IE4 = (document.all) ? 1 : 0;
		ver4 = (NS4 || IE4) ? 1 : 0;
		if (ver4) {
			with (document) {
				write("<STYLE TYPE='text/css'>");
				if (NS4) {
					write(".parent {position:absolute; visibility:visible}");
					write(".child {position:absolute; visibility:visible}");
					write(".regular {position:absolute; visibility:visible}")
				}
				else {
					write(".child {display:none}")
				}
				write("</STYLE>");
			}
		}
		function getIndex(el) {
			ind = null;
			for (i=0; i<document.layers.length; i++) {
				whichEl = document.layers[i];
				if (whichEl.id == el) {
					ind = i;
					break;
				}
			}
			return ind;
		}
		function arrange() {
			nextY = document.layers[firstInd].pageY +document.layers[firstInd].document.height;
			for (i=firstInd+1; i<document.layers.length; i++) {
				whichEl = document.layers[i];
				if (whichEl.visibility != "hide") {
					whichEl.pageY = nextY;
					nextY += whichEl.document.height;
				}
			}
		}

		function initIt(){
			if (!ver4) return;
			if (NS4) {
				for (i=0; i<document.layers.length; i++) {
					whichEl = document.layers[i];
					if (whichEl.id.indexOf("Child") != -1) whichEl.visibility = "hide";
			}
				arrange();
			}
			else {
				divColl = document.all.tags("DIV");
				for (i=0; i<divColl.length; i++) {
					whichEl = divColl(i);
					if (whichEl.className == "child") whichEl.style.display = "none";
				}
			}
		}
		function expand(bb)
		{
			
		if(a!="")
		{
		expandIt(a);
		}

		expandIt(bb);

//		a=bb;

		}
		
		function expandIt(el) {
		
			//debugger ;
			if (!ver4) return;
			if (IE4) {
				whichEl = eval(el + "Child");
				whichimg = eval("img" + el)
				if (whichEl.style.display == "none") {
					whichEl.style.display = "block";
					whichimg.src="${pageContext.request.contextPath }/images/open.gif";
		            
				}
				else {
					whichEl.style.display = "none";
					whichimg.src="${pageContext.request.contextPath }/images/add.gif";
				}
			}
			else {
				whichEl = eval("document." + el + "Child");
				if (whichEl.visibility == "hide") {
					whichEl.visibility = "show";
				}
				else {
					whichEl.visibility = "hide";
				}
				arrange();
			}
		}
		
		
		function linkcolorchange(objLink)
		{
			for(var i=0;i<document.links.length;i++)
			{
				document.links[i].style.color = "" ;
			}
				objLink.style.color = "red" ;
		}
		function backgroundColorChange(objLink,strColor)
		{
			objLink.style.backgroundColor = strColor ;
		}

		onload = initIt;
		</SCRIPT>
	</HEAD>
	<BODY scroll="no" MS_POSITIONING="GridLayout" scroll="auto" class="bodyscroll">
		<TABLE height="100%" cellSpacing="0" cellPadding="0" width="143" border="0">
			<TBODY>
				<TR>
					<TD vAlign="top" bgColor="#F6F6F6" height="100%">
						<DIV class="parent" id="KB0Parent">
							<%--<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">--%>
								<%--<TBODY>--%>
								<%--<TR height=25 >--%>
									<%--<TD align="left" background="${pageContext.request.contextPath }/images/b-info.gif"  vAlign="middle">--%>
										<%--&nbsp;&nbsp;&nbsp;--%>
										<%--&nbsp; <A class="cl"  href="${pageContext.request.contextPath }/system/hybjMenuAction_alermGG.do" target="mainFrame">&nbsp;公告展示</A>--%>
									<%--</TD>--%>
								<%--</TR>--%>
								<%--</TBODY>--%>
							<%--</TABLE>--%>
								<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
									<TBODY>
									<TR height=50 >
										<TD align="left" background="${pageContext.request.contextPath }/images/b-info.gif"  vAlign="middle">
											&nbsp;&nbsp;&nbsp;<img src="" name="imgKB0" width="9" height="9" alt="" border="0" />
											&nbsp; <A class="cl" onclick="expand('KB0') " href="${pageContext.request.contextPath }/system/hybjMenuAction_alermGG.do" target="mainFrame">&nbsp;公告展示</A>
										</TD>
									</TR>
									</TBODY>
								</TABLE>
						</DIV>
                     
                        <%if(popedom.contains("a") || popedom.contains("b") ){ %>
						<DIV class="parent" id="KB0Parent">
							<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
								<TBODY>
									<TR height=25 >
										<TD align="left" background="${pageContext.request.contextPath }/images/b-info.gif"  vAlign="middle">
											&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath }/images/add.gif" name="imgKB0" width="9" height="9" alt="" border="0" />
											&nbsp; <A class="cl" onclick="expand('KB0'); return false" href="#">&nbsp;CP用户管理</A>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</DIV>
						<DIV class="child" id="KB0Child">
							<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
								<TBODY>
								    <%if(popedom.contains("a")){ %>
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/cp/hybjShowAction_hs.do" target="mainFrame">内容上报</A>
										</TD>
									</TR>
                                    <%}if(popedom.contains("b")){ %>
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/cp/hybjShowAction_xq.do" target="mainFrame">需求上报</A>
										</TD>
									</TR>
									<%} %>

									
								</TBODY>
							</TABLE>
						</DIV>
                       <%} %>

                      
                      <%if(popedom.contains("g") || popedom.contains("h")|| popedom.contains("i")){ %>
						<DIV class="parent" id="KB2Parent">
							<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
								<TBODY>
									<TR>
									<TR height=25>
										<TD background="${pageContext.request.contextPath }/images/b-info.gif" align="left" vAlign="middle">
											&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath }/images/add.gif" name="imgKB2" width="9" height="9" alt="" border="0" />
											&nbsp; <A class="cl" onclick="expand('KB2'); return false" href="#">&nbsp;聚合EPG方管理</A>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</DIV>
						<DIV class="child" id="KB2Child">
							<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
								<TBODY>
									<%if(popedom.contains("g")){ %>
									
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/epg/hybjJhEpgAction_jhhy.do" target="mainFrame">明云聚合</A>
										</TD>
									</TR>
									<%}if(popedom.contains("h")){ %>
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/epg/hybjJhEpgAction_showGS.do" target="mainFrame">公示管理</A>
										</TD>
									</TR>
									<%}if(popedom.contains("i")){ %>
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/epg/hybjJhEpgAction_showXQ.do" target="mainFrame">需求管理</A>
										</TD>
									</TR>
									<%}%>
								</TBODY>
							</TABLE>
						</DIV>
                       <%} %>
 
                       
                   
						<DIV class="parent" id="KB4Parent">
							<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
								<TBODY>
									<TR>
									<TR height=25>
										<TD background="${pageContext.request.contextPath }/images/b-info.gif" align="left" vAlign="middle">
											&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath }/images/add.gif" name="imgKB4" width="9" height="9" alt="" border="0" />
											&nbsp; <A class="cl" onclick="expand('KB4'); return false" href="#">&nbsp;系统管理</A>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</DIV>
						<DIV class="child" id="KB4Child">
							<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
								<TBODY>
								
								    <%if(popedom.contains("t")){ %>
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/hybjUserAction_home.do" target="mainFrame">用户管理</A>
										</TD>
									</TR>
                                    <%} %>
                                    <%if(popedom.contains("p")){ %>
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/hybjUserAction_edit.do?userID=${globle_user.userID }&roleflag=1" target="mainFrame">个人管理</A>
										</TD>
									</TR>

									<%} %>
									<%if(popedom.contains("q")){ %>
									
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/hybjRoleAction_home.do" target="mainFrame">权限管理</A>
										</TD>
									</TR>
									<%}if(popedom.contains("r")){ %>
									
									
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/hybjCommonMsgAction_home.do" target="mainFrame">待办事宜</A>
										</TD>
									</TR>
									<%}if(popedom.contains("s")){ %>
									
									
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/hybjSystemDDlAction_home.do" target="mainFrame">数据字典维护</A>
										</TD>
									</TR>
									<%} %>
									
									<TR>
										<TD class="box06" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/hybjLogAction_home.do" target="mainFrame">日志管理</A>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</DIV>
					
						
	</BODY>