<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<tiles:insertDefinition name="loginLayout">
	<tiles:putAttribute name="title" value="Webservices" type="String" />
	<tiles:putAttribute name="java_script" type="String">
		<script type="text/javascript" src="/mvc/media/js/jquery.js"></script>
		<script>
			$(document).ready(function() {
				console.log('jquery');
			});
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="menu" value="" />
	<tiles:putAttribute name="body">

		<c:set var="baseUrl"
			value="${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.servletContext.contextPath}" />
		
		Webservice
			<br>

		<a
			href="//${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.servletContext.contextPath}/ws/userdetails/userdetails.wsdl">
			User Details </a>

		<br>
				
		REST Webservice
		
		<br>
		<b>Users</b>
		<br>
		<a href="//${baseUrl}/rws/v1.1/security/core/userdetails/users">Users</a><br>
		<a href="//${baseUrl}/rws/v1.1/security/core/userdetails/users.jsd">Users Schema</a><br>
		<a href="//${baseUrl}/rws/v1.1/security/core/userdetails/users?mediaType=json">Users As JSON in Query Param</a><br>
		<a href="//${baseUrl}/rws/v1.1/security/core/userdetails/users?mediaType=xml">Users As XML in Query Param</a><br>
		<a href="//${baseUrl}/rws/v1.1/security/core/userdetails/users.json">Users As JSON in Path Extension</a><br>
		<a href="//${baseUrl}/rws/v1.1/security/core/userdetails/users.xml">Users As XML in Path Extension</a><br>
		<a href="//${baseUrl}/rws/v1.1/security/core/userdetails/users?userId=226475">User by id</a><br>
		<a href="//${baseUrl}/rws/v1.1/security/core/userdetails/users?userName=mrjayarajj">User by name</a><br>
		<a href="//${baseUrl}/rws/v1.1/security/core/userdetails/users.json?userId=226475">User by id As JSON in Path Extension</a><br>
		<a href="//${baseUrl}/rws/v1.1/security/core/userdetails/users.xml?userName=mrjayarajj">User by name AS XML in Path Extension</a><br>
		<a href="//${baseUrl}/rws/v1.1/security/core/userdetails/users?userId=226475&mediaType=json">User by id As JSON in Query Param</a><br>
		<a href="//${baseUrl}/rws/v1.1/security/core/userdetails/users?userName=mrjayarajj&mediaType=xml">User by name AS XML in Query Param</a><br>
		<br>
		<b>User</b>
		<br>
		<a href="//${baseUrl}/rws/v1.1/security/core/userdetails/user/226475">User</a><br>

	</tiles:putAttribute>
</tiles:insertDefinition>
