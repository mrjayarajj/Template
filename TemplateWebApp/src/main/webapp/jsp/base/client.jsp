<%@ page import="java.net.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<tiles:insertDefinition name="loginLayout">
	<tiles:putAttribute name="title" value="Client" type="String" />
	<tiles:putAttribute name="css_style" type="String">

	</tiles:putAttribute>

	<tiles:putAttribute name="menu" value="" />
	<tiles:putAttribute name="body">

		<table>
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