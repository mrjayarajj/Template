<%@ page import="org.springframework.security.core.AuthenticationException"%>
<%@ page import="freemarker.ext.beans.CollectionModel"%>
<%@ page import="freemarker.ext.beans.StringModel"%>
<%@ page import="freemarker.ext.servlet.HttpRequestHashModel"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="title" value="User" type="String" />
	<tiles:putAttribute name="java_script">
		<script>
			alert("There are " + form.users.length + " Users in the system");
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<s:form namespace="/security/core/userdetails">
			<sec:csrfInput />
			<s:set name="user_editor" value="0" />
			<sec:authorize access="hasAuthority('BF_UPDATE_USER') and !hasAuthority('BF_ADD_USER')">
				<s:set name="user_editor" value="0" />
			</sec:authorize>
			<sec:authorize access="hasAuthority('BF_ADD_USER') and hasAuthority('BF_VIEW_USER')">
				<s:set name="user_editor" value="1" />
			</sec:authorize>
			
			
			
			<s:if test="%{#user_editor==1 || userForm.action=='update' }">
				<table>
					<tr>
						<td>User ID</td>
						<td><s:if test="%{userForm.action=='add'}">
								<s:textfield name="userForm.user.userId" />
							</s:if> <s:if test="%{userForm.action=='update'}">
								<s:hidden name="userForm.user.userId" />
								<s:property value="userForm.user.userId" />
							</s:if></td>
					</tr>
					<tr>
						<td><s:text name="user_name" /></td>
						<td><s:textfield name="userForm.user.userName" /></td>
					</tr>
					<tr>
						<td><s:text name="password" /></td>
						<td><s:password name="userForm.user.userPassword" /></td>
					</tr>
					<tr>
						<td><s:text name="role" /></td>
						<td><s:select name="userForm.user.role.roleId" list="userForm.roles" listKey="roleId" listValue="roleName" /></td>
					</tr>
					<tr>
						<td><s:text name="expire_date" /></td>
						<td><s:textfield name="userForm.user.expireDate" /> mm/dd/yyyy</td>
					</tr>
					<tr>
						<td>Status</td>
						<td><s:checkbox name="userForm.user.status" /></td>
					</tr>
					<tr>
						<td>Gender</td>
						<td><s:radio name="userForm.user.gender" list="#{'M':'Male','F':'Female'}" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>
							<!-- display add button only if user has BF_ADD_USER permission --> <sec:authorize access="hasAnyAuthority('BF_ADD_USER')">
								<!-- show all user information if user has BF_VIEW_USER permission after clicking add -->
								<sec:authorize access="hasAnyAuthority('BF_VIEW_USER')">
									<s:if test="%{userForm.action=='add'}">
										<s:hidden name="userForm.redirectName" value="redirect_onLoad" />
										<s:submit value="add" action="processUser" method="addUser" />
									</s:if>
								</sec:authorize>
								<!-- if user don't have BF_VIEW_USER permission but has add permission  -->
								<sec:authorize access="!hasAnyAuthority('BF_VIEW_USER')">
									<s:if test="%{userForm.action=='add'}">
										<s:hidden name="userForm.redirectName" value="redirect_showUser" />
										<s:submit value="add" action="processUser!addUser" />
									</s:if>
								</sec:authorize>
							</sec:authorize> <!-- show update button only if user has BF_UPDATE_USER permission  --> <sec:authorize access="hasAnyAuthority('BF_UPDATE_USER')">
								<s:if test="%{userForm.action=='update'}">
									<s:submit value="update" action="processUser!updateUser" />
									<s:submit value="cancel" action="processUser!cancel" />
								</s:if>
							</sec:authorize>
						</td>
					</tr>
				</table>
			</s:if>
			<!-- show the below user table only if user can perform the 3 operation (view,update and delete) 
			 which can be performed in this table -->
			<sec:authorize access="hasAnyAuthority('BF_VIEW_USER','BF_UPDATE_USER','BF_DELETE_USER')">
				<table border="1">
					<tr>
						<sec:authorize access="hasAnyAuthority('BF_DELETE_USER')">
							<td><s:submit value="delete" action="processUser!deleteUsers" /></td>
						</sec:authorize>
						<td><b>User Name</b></td>
						<td><b>Role</b></td>
						<td><b>Gender</b></td>
						<td><b>Status</b></td>
						<td><b>Expire Date</b></td>
					</tr>
					<s:iterator status="s" value="userForm.users">
						<tr>
							<!-- show delete check box only if user has BF_DELETE_USER permission   -->
							<sec:authorize access="hasAnyAuthority('BF_DELETE_USER')">
								<td><s:hidden name="userForm.selectedUserList[%{#s.index}].userId" value="%{userId}" /> <s:checkbox name="userForm.selectedUserList[%{#s.index}].selected" /></td>
							</sec:authorize>
							<td>
								<!-- show edit link only when user has BF_UPDATE_USER permission --> <sec:authorize access="hasAnyAuthority('BF_UPDATE_USER')">
									<s:url id="userIDURL" action="processUser" namespace="/security/core/userdetails" method="selectUser">
										<s:param name="userForm.user.userId" value="%{userId}"></s:param>
									</s:url>
									<s:a href="%{userIDURL}">
										<s:property value="userName" />
									</s:a>
								</sec:authorize> <!-- show user name without edit link --> <sec:authorize access="!hasAnyAuthority('BF_UPDATE_USER')">
									<s:property value="userName" />
								</sec:authorize>
							</td>
							<td><s:property value="role.roleName" /></td>
							<td><s:property value="gender" /></td>
							<td><s:if test="%{status==true}">
								Active <s:if test="%{locked==true}">,Locked</s:if>
									<s:else>,Not Locked</s:else>
								</s:if> <s:if test="%{status==false}">
								In Active <s:if test="%{locked==true}">,Locked</s:if>
									<s:else>,Not Locked</s:else>
								</s:if></td>
							<td><s:if test="%{expireDate!=null}">
									<s:date name="expireDate" />
								</s:if> <s:else>No Expire date</s:else></td>
						</tr>
					</s:iterator>
				</table>
			</sec:authorize>
		</s:form>
	</tiles:putAttribute>
</tiles:insertDefinition>