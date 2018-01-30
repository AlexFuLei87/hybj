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
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/tabletool.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery.min.js"></script>
		<SCRIPT language="JavaScript">
			function showOrHid(value) {
			    if($("TBODY[name="+value+"]").is(':hidden')){
                    $("TBODY[name="+value+"]").show();
				}else{
                    $("TBODY[name="+value+"]").hide();
				}

            }

		</SCRIPT>
	</HEAD>
	<BODY scroll="no" MS_POSITIONING="GridLayout" scroll="auto" class="bodyscroll">

				<%--<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">--%>
					<%--<TBODY>--%>
					<%--<TR height=50 >--%>
						<%--<TD align="left" background="${pageContext.request.contextPath }/images/b-info.gif"  vAlign="middle">--%>
							<%--&nbsp;&nbsp;&nbsp;<img src="" name="imgKB0" width="9" height="9" alt="" border="0" />--%>
							<%--&nbsp; <A class="cl" onclick="expand('KB0') " href="${pageContext.request.contextPath }/system/hybjMenuAction_alermGG.do" target="mainFrame">&nbsp;公告公示</A>--%>
						<%--</TD>--%>
					<%--</TR>--%>
					<%--</TBODY>--%>
				<%--</TABLE>--%>
					<%if(popedom.contains("w")||popedom.contains("x")||popedom.contains("y")){ %>
					<DIV class="parent" id="KB0Parent">
						<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
							<TBODY>
							<TR height=25 >
								<TD align="left" background="${pageContext.request.contextPath }/images/b-info.gif"  vAlign="middle">
									&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath }/images/add.gif" name="imgKB0" width="9" height="9" alt="" border="0" />
									&nbsp; <A class="cl" onclick="showOrHid('gs');" href="#">&nbsp;公示管理</A>
								</TD>
							</TR>
							</TBODY>
						</TABLE>
					</DIV>
					<DIV class="child" id="KB0Child">
						<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
							<TBODY name="gs">
							<%if(popedom.contains("w")){ %>
							<TR>
								<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
									<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/jhMenuAction_alermGS.do" target="mainFrame">内容公示</A>
								</TD>
							</TR>
							<%}if(popedom.contains("x")){ %>
							<TR>
								<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
									<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/jhMenuAction_alermZT.do" target="mainFrame">专题公示</A>
								</TD>
							</TR>
							<%}if(popedom.contains("y")){ %>
							<TR>
								<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
									<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/jhMenuAction_alermGG.do" target="mainFrame">通知</A>
								</TD>
							</TR>
							<%}%>


							</TBODY>
						</TABLE>
					</DIV>
					<%} %>
                     
                        <%if(popedom.contains("a")||popedom.contains("b")){ %>
						<DIV class="parent" id="KB0Parent">
							<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
								<TBODY>
									<TR height=25 >
										<TD align="left" background="${pageContext.request.contextPath }/images/b-info.gif"  vAlign="middle">
											&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath }/images/add.gif" name="imgKB0" width="9" height="9" alt="" border="0" />
											&nbsp; <A class="cl" onclick="showOrHid('cps');" href="#">&nbsp;内容管理</A>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</DIV>
						<DIV class="child" id="KB0Child">
							<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
								<TBODY name="cps">
								    <%if(popedom.contains("a")){ %>
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/cp/jhShowAction_hs.do" target="mainFrame">内容申报</A>
										</TD>
									</TR>
									<%}if(popedom.contains("b")){ %>
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/cp/jhShowAction_zt.do" target="mainFrame">专题申报</A>
										</TD>
									</TR>
									<%--<%}if(popedom.contains("b")){ %>--%>
									<%--<TR>--%>
										<%--<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">--%>
											<%--<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/cp/jhShowAction_drawing.do" target="mainFrame">作图任务</A>--%>
										<%--</TD>--%>
									<%--</TR>--%>
                                    <%}%>

									
								</TBODY>
							</TABLE>
						</DIV>
                       <%} %>


						<%if(popedom.contains("d") || popedom.contains("e") ){ %>
						<DIV class="parent" id="KB0Parent">
							<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
								<TBODY>
								<TR height=25 >
									<TD align="left" background="${pageContext.request.contextPath }/images/b-info.gif"  vAlign="middle">
										&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath }/images/add.gif" name="imgKB0" width="9" height="9" alt="" border="0" />
										&nbsp; <A class="cl" onclick="showOrHid('xq')" href="#">&nbsp;需求管理</A>
									</TD>
								</TR>
								</TBODY>
							</TABLE>
						</DIV>
						<DIV class="child" id="KB0Child">
							<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
								<TBODY name="xq">
								<%if(popedom.contains("d")){ %>
								<TR>
									<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
										<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/epg/jhJhEpgAction_showXQ.do" target="mainFrame">需求处理</A>
									</TD>
								</TR>
								<%}if(popedom.contains("e")){ %>
								<TR>
									<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
										<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/cp/jhShowAction_xq.do" target="mainFrame">需求报备</A>
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
											&nbsp; <A class="cl" onclick="showOrHid('jh')" href="#">&nbsp;聚合管理</A>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</DIV>
						<DIV class="child" id="KB2Child">
							<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
								<TBODY name="jh">
									<%if(popedom.contains("g")){ %>
									
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/epg/jhJhEpgAction_jhhy.do" target="mainFrame">上线处理</A>
										</TD>
									</TR>
									<%}if(popedom.contains("i")){ %>
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/epg/jhJhEpgAction_showOffline.do" target="mainFrame">下线处理</A>
										</TD>
									</TR>
									<%}if(popedom.contains("j")){ %>
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/epg/jhJhEpgAction_handleZt.do" target="mainFrame">专题处理</A>
										</TD>
									</TR>
									<%}if(popedom.contains("h")){ %>
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/epg/jhJhEpgAction_showGS.do" target="mainFrame">通知</A>
										</TD>
									</TR>
									<%}%>
								</TBODY>
							</TABLE>
						</DIV>
                       <%} %>


				<%if(popedom.contains("g") ){ %>
				<DIV class="parent" id="KB2Parent">
					<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
						<TBODY>
						<TR>
						<TR height=25>
							<TD background="${pageContext.request.contextPath }/images/b-info.gif" align="left" vAlign="middle">
								&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath }/images/add.gif" name="imgKB2" width="9" height="9" alt="" border="0" />
								&nbsp; <A class="cl" onclick="showOrHid('dx')" href="#">&nbsp;电信管理</A>
							</TD>
						</TR>
						</TBODY>
					</TABLE>
				</DIV>
				<DIV class="child" id="KB2Child">
					<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
						<TBODY name="dx">
						<%if(popedom.contains("g")){ %>
						<TR>
							<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
								<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/epg/jhJhEpgAction_handleTZ.do" target="mainFrame">通知处理</A>
							</TD>
						</TR>
						<%}%>
						</TBODY>
					</TABLE>
				</DIV>
				<%} %>


					<%if(popedom.contains("t") || popedom.contains("p")|| popedom.contains("q")|| popedom.contains("r")|| popedom.contains("s")|| popedom.contains("z")){ %>
						<DIV class="parent" id="KB4Parent">
							<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
								<TBODY>
									<TR>
									<TR height=25>
										<TD background="${pageContext.request.contextPath }/images/b-info.gif" align="left" vAlign="middle">
											&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath }/images/add.gif" name="imgKB4" width="9" height="9" alt="" border="0" />
											&nbsp; <A class="cl" onclick="showOrHid('xt'); return false" href="#">&nbsp;系统管理</A>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</DIV>
						<DIV class="child" id="KB4Child">
							<TABLE cellSpacing="0" cellPadding="0" width="99%" border="0">
								<TBODY name="xt">
								
								    <%if(popedom.contains("t")){ %>
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/jhUserAction_home.do" target="mainFrame">用户管理</A>
										</TD>
									</TR>
                                    <%} %>
                                    <%if(popedom.contains("p")){ %>
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/jhUserAction_edit.do?userID=${globle_user.userID }&roleflag=1" target="mainFrame">个人管理</A>
										</TD>
									</TR>

									<%} %>
									<%if(popedom.contains("q")){ %>
									
									<TR>
										<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/jhRoleAction_home.do" target="mainFrame">权限管理</A>
										</TD>
									</TR>
									<%--<%}if(popedom.contains("r")){ %>--%>
									<%----%>
									<%----%>
									<%--<TR>--%>
										<%--<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">--%>
											<%--<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/jhCommonMsgAction_home.do" target="mainFrame">待办事宜</A>--%>
										<%--</TD>--%>
									<%--</TR>--%>
									<%--<%}if(popedom.contains("s")){ %>--%>
									<%----%>
									<%----%>
									<%--<TR>--%>
										<%--<TD class="box05" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">--%>
											<%--<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/jhSystemDDlAction_home.do" target="mainFrame">数据字典维护</A>--%>
										<%--</TD>--%>
									<%--</TR>--%>

									<%}if(popedom.contains("z")){ %>
									<TR>
										<TD class="box06" onmouseover="backgroundColorChange(this,'white');" onmouseout="backgroundColorChange(this,'');">
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/system/jhLogAction_home.do" target="mainFrame">日志管理</A>
										</TD>
									</TR>
									<%} %>
								</TBODY>
							</TABLE>
						</DIV>
						<%} %>

					
						
	</BODY>