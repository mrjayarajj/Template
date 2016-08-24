<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="title" value="Authendicate" type="String" />
	<tiles:putAttribute name="java_script" >
		<script>
		function changeModule(module){
			var formAction  = document.processAuthenticate.action;
			var submitAction = formAction.substring(0,formAction.lastIndexOf("/"));
			var submitAction = submitAction+"/processAuthenticate!onLoad.action?authenticateForm.selectedModule.moduleId="+module.value;
			document.processAuthenticate.action = submitAction;			
			document.processAuthenticate.submit();
		}
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<s:form namespace="/security/access">
			<sec:csrfInput />
			<table>
				<tr>
					<td>Module</td>
					<td><s:select name="authenticateForm.selectedModule.moduleId"
						list="authenticateForm.modules" listKey="moduleId"
						listValue="moduleName" onchange="changeModule(this)" /></td>
					<td><s:submit value="save"
						action="processAuthenticate!saveAuthenticates" /></td>
				</tr>
			</table>

			<table border="1">
				<tr>
					<td><b>Function Name</b></td>
					<s:iterator value="authenticateForm.roles">
						<td><b><s:property value="roleName" /></b></td>
					</s:iterator>
				</tr>
				<s:set name="idx" value="0" />
				<s:iterator value="authenticateForm.functions" status="f">
					<tr>
						<td><b><s:property value="functionName" /></b></td>
						<s:iterator value="authenticateForm.roles">
							<td><s:hidden
								name="authenticateForm.grantList[%{#idx}].functionId"
								value="%{functionId}" /> <s:hidden
								name="authenticateForm.grantList[%{#idx}].roleId"
								value="%{roleId}" /> <s:checkbox
								name="authenticateForm.grantList[%{#idx}].grant"
								value="%{authenticateForm.check(functionId,roleId)}" /> <s:set
								name="idx" value="#idx+1" /></td>
						</s:iterator>
					</tr>
				</s:iterator>
			</table>
		</s:form>
	</tiles:putAttribute>
</tiles:insertDefinition>