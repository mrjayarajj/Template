<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="title" value="Function" type="String" />
	<tiles:putAttribute name="body">

		<c:if test="${functionForm.action=='update'}">
			<c:set var="url"
				value="/mvc/security/access/function/${functionForm.function.functionId}" />
			<c:set var="submitValue" value="update" />
		</c:if>
		<c:if test="${functionForm.action=='add'}">
			<c:set var="url" value="/mvc/security/access/functions" />
			<c:set var="submitValue" value="add" />
		</c:if>


		<form action="${url}" method="POST">
			<sec:csrfInput />
			<table>
				<tr>
					<td>Function Name</td>
					<td><input type="hidden" name="function.functionId"
						value="${functionForm.function.functionId }" /> <input
						type="text" name="function.functionName"
						value="${functionForm.function.functionName}" /></td>
				</tr>
				<tr>
					<td>Function Mnemonics</td>
					<td><input type="text" name="function.functionMnemonic"
						value="${functionForm.function.functionMnemonic}" /></td>
				</tr>
				<tr>
					<td>Module</td>
					<td><select name="function.module.moduleId">
							<c:forEach var="m" items="${functionForm.moduleList}">
								<c:if
									test="${functionForm.function.module.moduleId==m.moduleId}">
									<option selected value="${m.moduleId}">${m.moduleName}</option>
								</c:if>
								<c:if
									test="${functionForm.function.module.moduleId!=m.moduleId}">
									<option value="${m.moduleId}">${m.moduleName}</option>
								</c:if>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="${submitValue}" /></td>
				</tr>
			</table>
		</form>

		<form action="/mvc/security/access/functions/delete" method="POST">
			<sec:csrfInput />
			<table border="1">
				<tr>
					<td><b><input type="submit" value="delete" /> </b></td>
					<td><b>Function Name</b></td>
					<td><b>Module Name</b></td>
				</tr>

				<c:forEach varStatus="loopCounter" var="f"
					items="${functionForm.functionList}">
					<tr>
						<td><input type="checkbox"
							name="selectedFunctionList[${loopCounter.count-1}]"
							value="${f.functionId}" /></td>
						<td><a
							href="${pageContext.servletContext.contextPath}/mvc/security/access/function/${f.functionId}">
								<c:out value="${f.functionName}" />
						</a></td>
						<td><c:out value="${f.module.moduleName}" /></td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</tiles:putAttribute>
</tiles:insertDefinition>