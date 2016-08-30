<%@ taglib prefix="s" uri="/struts-tags"%>
<form id="loginForm" action="j_security_check" method="POST">
<table>
	<tr>
		<td colspan="2">
			Login Page - Powered by Secure Filter
		</td>
	</tr>
	<tr>
		<td colspan="2"><s:fielderror>
			<s:param>loginForm.userInfo.userName</s:param>
		</s:fielderror></td>
	</tr>
	<tr>
		<td><s:text name="user_name" /></td>
		<td><input type="text" name="j_username" value="star"></td>
	</tr>
	<tr>
		<td colspan="2"><s:fielderror>
			<s:param>loginForm.userInfo.password</s:param>
		</s:fielderror></td>
	</tr>
	<tr>
		<td><s:text name="password" /></td>
		<td><input type="password" name="j_password" value="star">
		</td>
	</tr>
	<tr>
		<td></td>
		<td><input type="Submit"></td>
	</tr>
</table>
</form>