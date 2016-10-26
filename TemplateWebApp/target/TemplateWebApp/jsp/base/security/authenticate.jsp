<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="title" value="Authendicate" type="String" />
	<tiles:putAttribute name="java_script">
		<script>
			function changeModule(module) {
				var formAction = document.getAuthenticateForm.action;
				var submitAction = formAction.substring(0, formAction
						.lastIndexOf("/"));
				var submitAction = submitAction
						+ "/authenticate/"
						+ module.value;
				document.getAuthenticateForm.action = submitAction;
				document.getAuthenticateForm.submit();
			}
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
	
		<form name="getAuthenticateForm" action="/mvc/security/access/authenticate" method="GET">
		</form>
	
		<form name="authenticateForm" action="/mvc/security/access/authenticate" method="POST">
			<sec:csrfInput />
			<table>
				<tr>
					<td>Module</td>
					<td><select name="selectedModule.moduleId"
						onchange="changeModule(this)">
							<c:forEach var="m" items="${authenticateForm.modules}">

								<c:if
									test="${authenticateForm.selectedModule.moduleId==m.moduleId}">
									<option selected value="${m.moduleId}">${m.moduleName}</option>
								</c:if>
								<c:if
									test="${authenticateForm.selectedModule.moduleId!=m.moduleId}">
									<option value="${m.moduleId}">${m.moduleName}</option>
								</c:if>

							</c:forEach>
					</select></td>
					<td><input type="submit" value="save" /></td>
				</tr>
			</table>


			<table border="1">
				<tr>
					<td><b>Function Name</b></td>
					<c:forEach var="r" items="${authenticateForm.roles}">
						<td><b><c:out value="${r.roleName}" /></b></td>
					</c:forEach>
				</tr>

				<c:set var="idx" value="0" />
				<c:forEach var="f" items="${authenticateForm.functions}">
					<tr>
						<td><b><c:out value="${f.functionName}" /></b></td>

						<c:forEach var="r" items="${authenticateForm.roles}">
							<td><input type="hidden" name="grantList[${idx}].functionId"
								value="${f.functionId}" /> <input type="hidden"
								name="grantList[${idx}].roleId" value="${r.roleId}" /> <c:set
									var="grant" value="false" /> <c:forEach var="a"
									items="${authenticateForm.authenticatedList}">

									<c:if
										test="${a.roleId==r.roleId && a.functionId==f.functionId }">
										<c:set var="grant" value="true" />
									</c:if>

								</c:forEach> <input type="checkbox" name="grantList[${idx}].grant"
								${ grant==true? 'checked=checked' : '' } /> <c:set var="idx"
									value="${idx+1}" /></td>
						</c:forEach>

					</tr>
				</c:forEach>


			</table>

		</form>
	</tiles:putAttribute>
</tiles:insertDefinition>