<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<tiles:insertDefinition name="loginLayout">
	<tiles:putAttribute name="title" value="Webservices" type="String" />
	<tiles:putAttribute name="java_script" type="String">
		<script type="text/javascript" src="/media/js/jquery.js"></script>
		<script>		
			$(document).ready( function() {
				console.log('jquery');
			});		
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
		
		Webservice
		
		<br>

		<a
			href="//${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.servletContext.contextPath}/ws/userdetails/userdetails.wsdl">
			User Details </a>

		<br>
				
		REST Webservice
		
		<br>

		<a
			href="//${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.servletContext.contextPath}/rws/brand/ecommerce/1.5/users/dummy">
			User Details </a>

	</tiles:putAttribute>
</tiles:insertDefinition>
