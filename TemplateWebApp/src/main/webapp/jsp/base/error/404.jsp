<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="title" value="File Not Found" type="String" />
	<tiles:putAttribute name="java_script" type="String" >
		<script>
			function goBack() {
				window.history.back();
			}
		</script>	
	</tiles:putAttribute>
	<tiles:putAttribute name="menu" value="" type="String" />
	<tiles:putAttribute name="body">
		<h1>404-File Not Found</h1> <a href="#" onclick="goBack()">Go Back</a>
	</tiles:putAttribute>
</tiles:insertDefinition>