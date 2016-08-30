<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="title" value="Module" type="String" />
	<tiles:putAttribute name="body">
		<s:form namespace="/security/access">
			<sec:csrfInput />
			<table>
				<tr>
					<td><s:text name="module_name" /></td>
					<td><s:hidden name="moduleForm.module.moduleId" /> <s:textfield
						name="moduleForm.module.moduleName" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:if test="%{moduleForm.action=='add'}">
						<s:submit value="add" action="processModule!addModule" />
					</s:if> <s:if test="%{moduleForm.action=='update'}">
						<s:submit value="update" action="processModule!updateModule" />
					</s:if></td>
				</tr>
			</table>

			<table border="1">
				<tr>
					<td><b><s:submit value="delete"
						action="processModule!deleteModules" /> </b></td>
					<td><b>module Name</b></td>
				</tr>

				<s:iterator status="s" value="moduleForm.moduleList">
					<tr>
						<td><s:checkbox
							name="moduleForm.selectedModuleList[%{#s.index}]"
							fieldValue="%{moduleForm.moduleList[#s.index].moduleId}"></s:checkbox>
						</td>
						<td><s:url id="moduleIDURL" action="processModule"
							namespace="/security/access" method="selectModule">
							<s:param name="moduleForm.module.moduleId" value="%{moduleId}"></s:param>
						</s:url> <s:a href="%{moduleIDURL}">
							<s:property value="moduleName" />
						</s:a></td>
					</tr>
				</s:iterator>
			</table>
		</s:form>

	</tiles:putAttribute>
</tiles:insertDefinition>
