<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="title" value="User" type="String" />
	<tiles:putAttribute name="java_script">
		<script>
			alert("There are " + form.users.length + " Users in the system");
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
	
		<c:if test="${userForm.action=='update'}" >
			<c:set var="url" value="/mvc/security/core/userdetails/user/${userForm.user.userId}" />
		</c:if>
		
		<c:if test="${userForm.action=='add'}" >
			<c:set var="url" value="/mvc/security/core/userdetails/users" />
		</c:if>
	
		<form method="POST"
			action="${url}">

			<sec:csrfInput />
			<c:set var="user_editor" value="0" />
			<sec:authorize
				access="hasAuthority('BF_UPDATE_USER') and !hasAuthority('BF_ADD_USER')">
				<c:set var="user_editor" value="0" />
			</sec:authorize>
			<sec:authorize
				access="hasAuthority('BF_ADD_USER') and hasAuthority('BF_VIEW_USER')">
				<c:set var="user_editor" value="1" />
			</sec:authorize>

			<c:if test="${user_editor==1 || userForm.action=='update' }">
				<table>
					<tr>
						<td>User ID:</td>
						<td><c:if test="${userForm.action=='add'}">
								<input type="text" name="user.userId" />
							</c:if> <c:if test="${userForm.action=='update'}">
								<input type="hidden" name="user.userId"
									value="${userForm.user.userId}" />
								<c:out value="${userForm.user.userId}" />
							</c:if></td>
					</tr>
					<tr>
						<td>User Name:</td>
						<td><input type="text" name="user.userName"
							value="${userForm.user.userName}" /></td>
					</tr>

					<tr>
						<td>Password:</td>
						<td><input type="text" name="user.userPassword"
							name="${userForm.user.userPassword}" /></td>
					</tr>

					<tr>
						<td>Role:</td>
						<td><select name="user.role.roleId">
								<c:forEach var="r" items="${userForm.roles}">
									<c:if test="${userForm.user.role.roleId==r.roleId}">
										<option selected value="${r.roleId}">${r.roleName}</option>
									</c:if>
									<c:if test="${userForm.user.role.roleId!=r.roleId}">
										<option value="${r.roleId}">${r.roleName}</option>
									</c:if>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td>Expire Date:</td>
						<td><input type="text" name="user.expireDateAsString"
							value="${userForm.user.expireDateAsString}" /> MM/dd/yyyy</td>
					</tr>

					<tr>
						<td>Status</td>
						<td><input type="checkbox" name="user.status"
							${userForm.user.status==true? 'checked=checked' : '' } /></td>
					</tr>

					<tr>
						<td>Gender</td>
						<td><input type="radio" name="user.gender"
							${userForm.user.gender eq 'M'.charAt(0)  ? 'checked=checked' : '' }
							value="M" /> Male <input type="radio" name="user.gender"
							${userForm.user.gender eq 'F'.charAt(0)  ? 'checked=checked' : '' }
							value="F" /> Female</td>
					</tr>

					<tr>
						<td>&nbsp;</td>
						<td>
							<!-- display add button only if user has BF_ADD_USER permission -->
							<sec:authorize access="hasAnyAuthority('BF_ADD_USER')">
								<!-- show all user information if user has BF_VIEW_USER permission after clicking add -->
								<sec:authorize access="hasAnyAuthority('BF_VIEW_USER')">
									<c:if test="${userForm.action=='add'}">
										<input type="hidden" name="redirectName"
											value="redirect_onLoad" />
										<input type="submit" value="add" action="processUser"
											method="addUser" />
									</c:if>
								</sec:authorize>
								<!-- if user don't have BF_VIEW_USER permission but has add permission  -->
								<sec:authorize access="!hasAnyAuthority('BF_VIEW_USER')">
									<c:if test="${userForm.action=='add'}">
										<input type="hidden" name="redirectName"
											value="redirect_showUser" />
										<input type="submit" value="add" action="processUser!addUser" />
									</c:if>
								</sec:authorize>
							</sec:authorize> <!-- show update button only if user has BF_UPDATE_USER permission  -->
							<sec:authorize access="hasAnyAuthority('BF_UPDATE_USER')">
								<c:if test="${userForm.action=='update'}">
									<input type="submit" value="update" />
									<input type="submit" value="cancel" action="processUser!cancel" />
								</c:if>
							</sec:authorize>
						</td>
					</tr>
				</table>
			</c:if>
		</form>
		<form id="userForm" method="POST"
			action="/mvc/security/core/userdetails/users/delete">
			<sec:csrfInput />
			<!-- show the below user table only if user can perform the 3 operation (view,update and delete) 
			 which can be performed in this table -->
			<sec:authorize
				access="hasAnyAuthority('BF_VIEW_USER','BF_UPDATE_USER','BF_DELETE_USER')">
				<table border="1">
					<tr>
						<sec:authorize access="hasAnyAuthority('BF_DELETE_USER')">
							<td><input type="submit" value="delete"
								action="processUser!deleteUsers" /></td>
						</sec:authorize>
						<td><b>User Name</b></td>
						<td><b>Role</b></td>
						<td><b>Gender</b></td>
						<td><b>Status</b></td>
						<td><b>Expire Date</b></td>
					</tr>
					<c:forEach varStatus="loopCounter" var="u"
						items="${userForm.users}">

						<tr>
							<!-- show delete check box only if user has BF_DELETE_USER permission   -->
							<sec:authorize access="hasAnyAuthority('BF_DELETE_USER')">
								<td><input type="hidden"
									name="selectedUserList[${loopCounter.count}].userId"
									value="${u.userId}" /> <input type="checkbox"
									name="selectedUserList[${loopCounter.count}].selected" /></td>
							</sec:authorize>
							<td>
								<!-- show edit link only when user has BF_UPDATE_USER permission -->
								<sec:authorize access="hasAnyAuthority('BF_UPDATE_USER')">
									<a href="/mvc/security/core/userdetails/user/${u.userId}"> <c:out
											value="${u.userName}" />
									</a>
								</sec:authorize> <!-- show user name without edit link --> <sec:authorize
									access="!hasAnyAuthority('BF_UPDATE_USER')">
									<c:out value="${u.userName}" />
								</sec:authorize>
							</td>

							<td><c:out value="${u.role.roleName}" /></td>
							<td><c:out value="${u.gender}" /></td>
							<td><c:if test="${u.status==true}">
									Active 
									<c:if test="${u.locked==true}">,Locked</c:if>
									<c:if test="${u.locked==false}">,Not Locked</c:if>
								</c:if> <c:if test="${u.status==false}">
									In Active 
									<c:if test="${u.locked==true}">,Locked</c:if>
									<c:if test="${u.locked==false}">,Not Locked</c:if>
								</c:if></td>
							<td><c:if test="${u.expireDate!=null}">
									<c:out value="${u.expireDateAsString}" />
								</c:if> <c:if test="${u.expireDate==null}">No Expire date</c:if></td>
						</tr>
					</c:forEach>
				</table>
			</sec:authorize>
		</form>
	</tiles:putAttribute>
</tiles:insertDefinition>