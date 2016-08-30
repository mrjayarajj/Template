<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="title" value="Logger" type="String" />
	<tiles:putAttribute name="body">
		<form id="processLogger" name="processLogger" onsubmit="return true;" action="${pageContext.servletContext.contextPath}/security/access/processLogger.action?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
			<sec:csrfInput />
			<s:textarea name="loggerForm.loggerConfig" disabled="true" cssStyle="margin: 0px; height: 435px; width: 952px;" />
			</br>
			</br>
			<s:file name="loggerForm.upload" label="File" />
			</br>
			</br>
			<s:submit value="Import New Logger" action="processLogger!uploadLogger" />
		</form>
	</tiles:putAttribute>
</tiles:insertDefinition>