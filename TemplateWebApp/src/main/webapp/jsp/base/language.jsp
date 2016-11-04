<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>Language</title>
</head>
<body>
	<form
		action="${pageContext.servletContext.contextPath}/mvc/security/core/userdetails/users"
		method="GET">
		<sec:csrfInput />
		<table width="100%">
			<tr>
				<td align="right"><select
					value="${sessionScope.WW_TRANS_I18N_LOCALE}" name="request_locale">
						<option value="en">english</option>
						<option value="ta">tamil</option>
				</select> <input type="submit" value="change" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
