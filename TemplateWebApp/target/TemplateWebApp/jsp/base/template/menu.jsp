<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%-- 
<c:if test="${fn:contains(sessionScope.SPRING_SECURITY_CONTEXT.authentication.authorities, 'BF_DELETE_USER')}">
    if user has BF_DELETE_USER permission
</c:if>
 --%>

<sec:authorize
	access="hasAnyAuthority('BF_UPDATE_USER','BF_DELETE_USER','BF_VIEW_USER')">
	<!-- if user has permission to update,delete and view then include onLoad  -->
	<a href="/mvc/security/core/userdetails/users">User</a>
</sec:authorize>
<sec:authorize access="hasAuthority('BF_ADD_USER')">
	<sec:authorize
		access="!hasAuthority('BF_UPDATE_USER') and !hasAuthority('BF_DELETE_USER') and !hasAuthority('BF_VIEW_USER')">
		<!-- if user can add but not update,delete and view then include showUser  -->
		<a href="/mvc/security/core/userdetails/users">User</a>
	</sec:authorize>
</sec:authorize>








<br>
<a href="/mvc/security/access/roles">Role</a>
<br>
<a href="/mvc/security/access/modules">Module</a>
<br>
<a href="/mvc/security/access/functions">Function</a>
<br>
<a href="/mvc/security/access/authenticate">Authenticate</a>
<br>
