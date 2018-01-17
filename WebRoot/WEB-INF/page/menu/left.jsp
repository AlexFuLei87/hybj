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
                     
                        <%if(popedom.contains("a")){ %>
						<DIV class="parent" id="KB0Parent">
							<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
								<TBODY>
									<TR height=25 >
										<TD align="left" background="${pageContext.request.contextPath }/images/b-info.gif"  vAlign="middle">
											&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath }/images/add.gif" name="imgKB0" width="9" height="9" alt="" border="0" />
											&nbsp; <A class="cl" onclick="showOrHid('cps');" href="#">&nbsp;用户管理</A>
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
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/cp/hybjShowAction_hs.do" target="mainFrame">内容上报</A>
										</TD>
									</TR>
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
										<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/epg/hybjJhEpgAction_showXQ.do" target="mainFrame">需求处理</A>
									</TD>
								</TR>
								<%}if(popedom.contains("e")){ %>
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
											&nbsp; <A class="cl" onclick="showOrHid('jh')" href="#">&nbsp;聚合方管理</A>
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
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/epg/hybjJhEpgAction_jhhy.do" target="mainFrame">上线处理</A>
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
											<A class="cl_01" onclick="linkcolorchange(this)" href="${pageContext.request.contextPath }/epg/hybjJhEpgAction_showOffline.do" target="mainFrame">下线处理</A>
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