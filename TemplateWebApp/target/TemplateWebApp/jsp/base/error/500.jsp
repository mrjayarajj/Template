<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="title" value="Internal Server Error" type="String" />
	<tiles:putAttribute name="java_script" type="String" >
		<script>
			function goBack() {
				window.history.back();
			}
		</script>	
	</tiles:putAttribute>
	<tiles:putAttribute name="menu" value="" type="String" />
	<tiles:putAttribute name="body">
		<h1>500-Internal Server Error</h1><a href="#" onclick="goBack()">Go Back</a>
		<p>Something is broken. Please e-mail us and let us know what you were doing when this error occurred. We will fix it as soon as possible. Sorry for any inconvenience caused. </p>
		<!--  
			${requestScope.EXCEPTION}
		-->
	</tiles:putAttribute>
</tiles:insertDefinition>