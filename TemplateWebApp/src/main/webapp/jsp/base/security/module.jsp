<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="title" value="Module" type="String" />
	<tiles:putAttribute name="body">
	
		<c:if test="${moduleForm.action=='update'}">
			<c:set var="url"
				value="/mvc/security/access/module/${moduleForm.module.moduleId}" />
				<c:set var="submitValue" value="update" />
		</c:if>
		<c:if test="${moduleForm.action=='add'}">
			<c:set var="url" value="/mvc/security/access/modules" />
			<c:set var="submitValue" value="add" />
		</c:if>
	
		<form action="${url}" method="POST">
			<sec:csrfInput />
			<table>
				<tr>
					<td>Module Name</td>
					<td>
					<c:if test="${submitValue=='update' }" >
						<input type="hidden" name="module.moduleId" value="${moduleForm.module.moduleId}" />
					</c:if>
					
					 
					<input type="text" name="module.moduleName"
						value="${moduleForm.module.moduleName}"  /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<input type="submit" value="${submitValue}"/>
					</td>
				</tr>
			</table>
		</form>

		<form action="/mvc/security/access/modules/delete" method="POST">
			<sec:csrfInput />
			<table border="1">
				<tr>
					<td><b><input type="submit" value="delete"
						/> </b></td>
					<td><b>module Name</b></td>
				</tr>

				<c:forEach varStatus="loopCounter" var="m" items="${moduleForm.moduleList}">
					<tr>
						<td><input type="checkbox"
							name="selectedModuleList[${loopCounter.count-1}]"
							value="${m.moduleId}"></s:checkbox>
						</td>
						<td>
						<a href="/mvc/security/access/module/${m.moduleId}">
							<c:out value="${m.moduleName}" />
						</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</form>

	</tiles:putAttribute>
</tiles:insertDefinition>
