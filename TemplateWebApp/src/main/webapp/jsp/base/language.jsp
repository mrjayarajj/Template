<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>Language</title>
</head>
<body>
	<form action="${pageContext.servletContext.contextPath}/action/processForward!forward.action?forwardTo=/jsp/base/welcome.jsp" method="post">
		<sec:csrfInput />
		<table width="100%">
			<tr>
				<td align="right"><s:select value="%{#session.WW_TRANS_I18N_LOCALE}" name="request_locale" list="#{'en':'english','ta':'tamil'}" /> <s:submit value="change" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
