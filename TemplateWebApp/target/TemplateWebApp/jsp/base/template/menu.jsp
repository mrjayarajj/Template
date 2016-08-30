<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%-- 
<c:if test="${fn:contains(sessionScope.SPRING_SECURITY_CONTEXT.authentication.authorities, 'BF_DELETE_USER')}">
    if user has BF_DELETE_USER permission
</c:if>
 --%>

<sec:authorize access="hasAnyAuthority('BF_UPDATE_USER','BF_DELETE_USER','BF_VIEW_USER')" >
<!-- if user has permission to update,delete and view then include onLoad  -->
	<s:url id="userURL" action="processUser" namespace="/security/core/userdetails" method="onLoad" includeParams="none" />
	<s:a href="%{userURL}">User</s:a>
</sec:authorize>
<sec:authorize access="hasAuthority('BF_ADD_USER')">
	<sec:authorize access="!hasAuthority('BF_UPDATE_USER') and !hasAuthority('BF_DELETE_USER') and !hasAuthority('BF_VIEW_USER')">	
	<!-- if user can add but not update,delete and view then include showUser  -->
		<s:url id="userURL_" action="processUser" namespace="/security/core/userdetails" method="showUser" includeParams="none" />
		<s:a href="%{userURL_}">User</s:a>			
	</sec:authorize>
</sec:authorize>

<br>
<s:url id="roleURL" action="processRole" namespace="/security/access" method="onLoad" includeParams="none" />
<s:a href="%{roleURL}">Role</s:a>
<br>
<s:url id="moduleURL" action="processModule" namespace="/security/access" method="onLoad" includeParams="none" />
<s:a href="%{moduleURL}">Module</s:a>
<br>
<s:url id="functionURL" action="processFunction" namespace="/security/access" method="onLoad" includeParams="none" />
<s:a href="%{functionURL}">Function</s:a> 
<br>
<s:url id="authenticateURL" action="processAuthenticate" namespace="/security/access" method="onLoad" includeParams="none" />
<s:a href="%{authenticateURL}">Authenticate</s:a>
<br>
<sec:authorize access="hasAuthority('BF_CONTROL_LOGGER')">
<s:url id="loggerURL" action="processLogger" namespace="/security/access" method="onLoad" includeParams="none" />
<s:a href="%{loggerURL}">Logger</s:a>
</sec:authorize>
<br>
