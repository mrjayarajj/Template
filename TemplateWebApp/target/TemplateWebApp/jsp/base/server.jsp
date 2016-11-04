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
			<tr>
				<td><b>Request :</b></td>
				<td><div style="max-width: 300px">${requestScope}</div></td>
			</tr>
			
			<tr>
				<td><b>Session :</b></td>
				<td><div style="max-width: 300px">${sessionScope}</div></td>
			</tr>
			<%
				Cookie[] cookie = request.getCookies();
						for (int i = 0; i < cookie.length; i++) {
			%>
			<tr>
				<td><b>Cookie :</b></td>
				<td><div style="max-width: 300px"><%=cookie[i].getName()%>=<%=cookie[i].getValue()%>,<%=cookie[i].getDomain()%></div></td>
			</tr>
			<%
				}
			%>
			
			<!--x-debug-enabled = com.baseframework.web.filters.JSPIncludeFilter-DEBUG#com.baseframework.web.filters.RequestLoggerFilter$Cookie-DEBUG#com.baseframework.web.filters.RequestLoggerFilter$Header-DEBUG# -->
		</table>
	</tiles:putAttribute>
</tiles:insertDefinition>

<%
	ClassLoader cl = ClassLoader.getSystemClassLoader();

	URL[] urls = ((URLClassLoader) cl).getURLs();

	for (URL url : urls) {
		System.out.println(url.getFile());
	}
	//Properties props = System.getProperties();
	//props.list(System.out);
%>