<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:insertDefinition name="loginLayout">
	<tiles:putAttribute name="title" value="Login" type="String" />
	<tiles:putAttribute name="java_script">
		<script type="text/javascript" src="/media/js/login/login-1.js"></script>		
	</tiles:putAttribute>	
	<tiles:putAttribute name="menu" value="" />
	<tiles:putAttribute name="body">
		<fmt:setLocale value="${sessionScope.WW_TRANS_I18N_LOCALE}" scope="session" />
		<fmt:bundle basename="jsp.base.security.Login">
			<body>
				<h1><fmt:message key="login" /></h1>
				<c:if test="${not empty param.login_error}">
					<font color="#FF0000"> <fmt:message key="login_error"/> <br /> </font>
					<!--  
				    	<font color="#D0D0D0"> Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" /></font>
					-->
				</c:if>
				<form name="f" action="${pageContext.servletContext.contextPath}/login"  method="POST">
					<sec:csrfInput />
					<table>
						<tr>
							<td><fmt:message key="user_name" /> :</td>
							<td><c:if test="${not empty param.login_error}">
									<input type='text' name='username' value='<c:out value="${SPRING_SECURITY_LAST_USERNAME}"/>' />
								</c:if> <c:if test="${empty param.login_error}">
									<input type='text' name='username'/>
								</c:if></td>
						</tr>
						<tr>
							<td><fmt:message key="password" />:</td>
							<td><input type='password' name='password'></td>
						</tr>
						<tr>
							<td><input type="checkbox" name="_spring_security_remember_me"></td>
							<td><fmt:message key="remember_me_desc"/></td>
						</tr>
						<tr>
							<td colspan='2'>
							 	<input name="submit" type="submit" value="<fmt:message key="submit" />" >
							 	<input name="reset" type="reset" value="<fmt:message key="reset" /> ">
							 </td>
						</tr>
					</table>
				</form>
				`
			</body>
		</fmt:bundle>
	</tiles:putAttribute>
</tiles:insertDefinition>