<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${json!=null || json.length>0}">
	<script>
		var form = ${json};
	</script>
</c:if>