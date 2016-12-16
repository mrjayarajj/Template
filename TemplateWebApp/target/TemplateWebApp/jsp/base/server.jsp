<%@ page import="java.net.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<tiles:insertDefinition name="loginLayout">
	<tiles:putAttribute name="title" value="Server" type="String" />
	<tiles:putAttribute name="css_style" type="String">

	</tiles:putAttribute>
	<tiles:putAttribute name="java_script" type="String">
		<script>
			function goBack() {
				window.history.back();
			}
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="menu" value="" />
	<tiles:putAttribute name="body">

		<table>
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
		</table>
	</tiles:putAttribute>
</tiles:insertDefinition>