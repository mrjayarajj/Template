<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<!-- override favicon.ico location -->
	<link rel="icon" type="image/ico" href="//${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.servletContext.contextPath}/media/images/favicon.ico">
	<sec:csrfMetaTags />
	<title><tiles:insertAttribute name="title" ignore="true" /></title>	
	<jsp:include page="/jsp/base/json.jsp" />
	<script>
		function onLoadHandler(){
			
		}
	</script>
	<tiles:insertAttribute name="css_style" ignore="true" />
	<!--
	commented because minified script file exist only in targent not in source, need to configure jetty to look into target  
	<script src="//${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.servletContext.contextPath}/media/js/scripts.js"></script>
	-->
	<tiles:insertAttribute name="java_script" ignore="true" />
</head>
<body onload="onLoadHandler()">
	<table border="0" align="center" width="100%">
		<tr>
			<td colspan="2" width="100%"><tiles:insertAttribute name="header" />
			</td>
		</tr>
		<tr height="400">
			
			<td width="10%" align="left" valign="top">
				<tiles:insertAttribute	name="menu" />
			</td>
			<td width="90%" align="left" valign="top">
				<tiles:insertAttribute name="body" />
			</td>
		</tr>
		<tr>
			<td colspan="2" width="100%">
				<tiles:insertAttribute name="footer" />
			</td>
		</tr>
	</table>
</body>
</html>