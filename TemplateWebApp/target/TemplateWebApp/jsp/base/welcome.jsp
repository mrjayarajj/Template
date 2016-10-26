<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="title" value="Welcome" type="String" />
	<tiles:putAttribute name="body">
		Welcome
	</tiles:putAttribute>
</tiles:insertDefinition>