<%@ page import="java.net.*"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<tiles:insertDefinition name="loginLayout">
	<tiles:putAttribute name="title" value="Server" type="String" />
	<tiles:putAttribute name="css_style" type="String">
		<style>
.wrap {
	width: 352px;
}

.wrap table {
	width: 550px;
	table-layout: fixed;
}

table tr td {
	padding: 0px;
	border: 0px solid #eee;
	word-wrap: break-word;
}
</style>
	</tiles:putAttribute>
	<tiles:putAttribute name="java_script" type="String">
		<script>
			function goBack() {
				window.history.back();
			}
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="body">

		<div class="wrap">
			<table class="head">
				<tr>
					<td><b> <a
							href="//${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.servletContext.contextPath}/jsp/base/webservice.jsp">
								WebService </a>
					</b></td>
					<td><a href="#" onclick="goBack()">Go Back</a></td>
				</tr>
				<tr>
					<td><b>Java Version :</b></td>
					<td><%=System.getProperty("java.vm.version")%></td>
				</tr>
				<tr>
					<td><b>App Server :</b></td>
					<td><%=(InetAddress.getLocalHost()).getHostName()%></td>
				</tr>
				<tr>
					<td><b>Server OS :</b></td>
					<td><%=System.getProperty("os.name")%></td>
				</tr>
				<tr>
					<td><b>Web Server :</b></td>
					<td>${pageContext.request.serverName}</td>
				</tr>
				<tr>
					<td><b>Server Path :</b></td>
					<td><%=this.getClass().getResource("/")%></td>
				</tr>
				<s:iterator status="s" value="#request">
					<tr>
						<td><b>REQ:</b> <s:property value="key" /></td>
						<td><s:property value="value" /></td>
					</tr>
				</s:iterator>
				<s:iterator status="s" value="#response">
					<tr>
						<td><b>RES:</b> <s:property value="key" /></td>
						<td><s:property value="value" /></td>
					</tr>
				</s:iterator>
				<s:iterator status="s" value="#page">
					<tr>
						<td><b>PGE:</b> <s:property value="key" /></td>
						<td><s:property value="value" /></td>
					</tr>
				</s:iterator>
				<s:iterator status="s" value="#session">
					<tr>
						<td><b>SES:</b> <s:property value="key" /></td>
						<td><s:property value="value" /></td>
					</tr>
				</s:iterator>
				<!--  
				<c:forEach varStatus="i" var="cookie"
					items="${pageContext.request.cookies}">
					<tr>
						<td><b>COOKIE:</b></td>
						<td><c:out value="${cookie}" /></td>
					</tr>
				</c:forEach>
				-->
				<%
					Cookie[] cookie = request.getCookies();
							for (int i = 0; i < cookie.length; i++) {
				%>
				<tr>
					<td><b>COOKIE:</b></td>
					<td><%=cookie[i].getName()%>=<%=cookie[i].getValue()%>,<%=cookie[i].getDomain()%></td>
				</tr>
				<%
					}
				%>
				<!--x-debug-enabled = com.baseframework.web.filters.JSPIncludeFilter-DEBUG#com.baseframework.web.filters.RequestLoggerFilter$Cookie-DEBUG#com.baseframework.web.filters.RequestLoggerFilter$Header-DEBUG# -->
			</table>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>