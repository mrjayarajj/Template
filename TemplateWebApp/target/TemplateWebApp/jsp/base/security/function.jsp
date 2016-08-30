<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="title" value="Function" type="String" />
	<tiles:putAttribute name="body">
		<s:form namespace="/security/access">
			<sec:csrfInput />
			<table>
				<tr>
					<td><s:text name="function_name" /></td>
					<td><s:hidden name="functionForm.function.functionId" /> <s:textfield
						name="functionForm.function.functionName" /></td>
				</tr>
				<tr>
					<td>Function Mnemonics</td>
					<td><s:textfield name="functionForm.function.functionMnemonic" />
					</td>
				</tr>
				<tr>
					<td>Module</td>
					<td><s:select name="functionForm.function.module.moduleId"
						list="functionForm.moduleList" listKey="moduleId"
						listValue="moduleName" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:if test="%{functionForm.action=='add'}">
						<s:submit value="add" action="processFunction!addFunction" />
					</s:if> <s:if test="%{functionForm.action=='update'}">
						<s:submit value="update" action="processFunction!updateFunction" />
					</s:if></td>
				</tr>
			</table>

			<table border="1">
				<tr>
					<td><b><s:submit value="delete"
						action="processFunction!deleteFunctions" /> </b></td>
					<td><b>Function Name</b></td>
					<td><b>Module Name</b></td>
				</tr>

				<s:iterator status="s" value="functionForm.functionList">
					<tr>
						<td><s:checkbox
							name="functionForm.selectedFunctionList[%{#s.index}]"
							fieldValue="%{functionForm.functionList[#s.index].functionId}"></s:checkbox>
						</td>
						<td><s:url id="functionIDURL" action="processFunction"
							namespace="/security/access" method="selectFunction">
							<s:param name="functionForm.function.functionId"
								value="%{functionId}"></s:param>
						</s:url> <s:a href="%{functionIDURL}">
							<s:property value="functionName" />
						</s:a></td>
						<td><s:property value="module.moduleName" /></td>
					</tr>
				</s:iterator>
			</table>
		</s:form>

	</tiles:putAttribute>
</tiles:insertDefinition>