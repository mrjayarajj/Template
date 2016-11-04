<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!-- 
  response.setHeader("Cache-Control","no-store, no-cache, must-revalidate"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");//HTTP 1.0
  response.setDateHeader ("Expires", -1);//prevent caching at a proxy server
   -->
<table border="0" width="100%">
	<tr>
		<td width="100%" align="right">
			<c:if test="${sessionScope.SPRING_SECURITY_CONTEXT!=null}" >
				Welcome , <sec:authentication property="principal.username"/>
			</c:if>
		</td>
	</tr>
	<tr>
		<td width="100%" align="right">
			<jsp:useBean id="date" class="java.util.Date" />
			<c:out value="${date}"/>
			<a href="//${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.servletContext.contextPath}/jsp/base/server.jsp" >
				.
			</a>
		</td>
	</tr>
	<tr>
		<td width="100%" align="right">
		<a href="${pageContext.servletContext.contextPath}/jsp/base/language.jsp">Change Language</a>
		</td>
	</tr>
	<c:if test="${not empty SPRING_SECURITY_CONTEXT}" var="securityContext" scope="session" >
		<tr>			
			<td width="100%" align="right"><a href="${pageContext.servletContext.contextPath}/logout"> Logout</a></td>
		</tr>
	</c:if>
</table>