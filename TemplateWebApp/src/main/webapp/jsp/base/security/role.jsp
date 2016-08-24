<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="title" value="Role" type="String" />
	<tiles:putAttribute name="body">
		<s:form namespace="/security/access">
			<sec:csrfInput />
			<table>
				<tr>
					<td><s:text name="role_name" /></td>
					<td><s:hidden name="roleForm.role.roleId" /> <s:textfield
						name="roleForm.role.roleName" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:if test="%{roleForm.action=='add'}">
						<s:submit value="add" action="processRole!addRole" />
					</s:if> <s:if test="%{roleForm.action=='update'}">
						<s:submit value="update" action="processRole!updateRole" />
					</s:if></td>
				</tr>
			</table>

			<table border="1">
				<tr>
					<td><b><s:submit value="delete"
						action="processRole!deleteRoles" /> </b></td>
					<td><b>Role Name</b></td>
				</tr>

				<s:iterator status="s" value="roleForm.roleList">
					<tr>
						<td><s:checkbox name="roleForm.selectedRoleList[%{#s.index}]"
							fieldValue="%{roleForm.roleList[#s.index].roleId}"></s:checkbox>
						</td>
						<td>
							
							<a href="${pageContext.servletContext.contextPath}/security/access/processRole!selectRole.action?roleForm.role.roleId=${roleId}" >
							 <s:property value="roleName" />
							</a>
							
						 </td>
					</tr>
				</s:iterator>
			</table>
		</s:form>
	</tiles:putAttribute>
</tiles:insertDefinition>