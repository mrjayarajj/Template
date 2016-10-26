<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="title" value="Role" type="String" />
	<tiles:putAttribute name="body">

		<c:if test="${roleForm.action=='update'}">
			<c:set var="url"
				value="/mvc/security/access/role/${roleForm.role.roleId}" />
				<c:set var="submitValue" value="update" />
		</c:if>
		<c:if test="${roleForm.action=='add'}">
			<c:set var="url" value="/mvc/security/access/roles" />
			<c:set var="submitValue" value="add" />
		</c:if>


		<form action="${url}" method="POST">
			<sec:csrfInput />
			<table>
				<tr>
					<td>Role Name:</td>
					<td> <input type="hidden"
							name="role.roleId" value="${roleForm.role.roleId}" />
							
							<input type="text"
							name="role.roleName" value="${roleForm.role.roleName}" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					
					<input type="submit" value="${submitValue}" /> 
					
				</tr>
			</table>
		</form>

		<form action="/mvc/security/access/roles/delete" method="POST">
			<sec:csrfInput />
			<table border="1">
				<tr>
					<td><b><input type="submit" value="delete" /> </b></td>
					<td><b>Role Name</b></td>
				</tr>

				<c:forEach varStatus="loopCounter" var="r"
					items="${roleForm.roleList}">
					<tr>
						<td><input type="checkbox"
							name="selectedRoleList[${loopCounter.count-1}]"
							value="${r.roleId}" /></td>
						<td><a
							href="${pageContext.servletContext.contextPath}/mvc/security/access/role/${r.roleId}">
								<c:out value="${r.roleName}" />
						</a></td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</tiles:putAttribute>
</tiles:insertDefinition>