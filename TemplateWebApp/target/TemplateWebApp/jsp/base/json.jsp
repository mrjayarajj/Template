<%@ taglib prefix="s" uri="/struts-tags" %> <s:bean name="com.baseframework.biz.util.JSONConvertor" id="jsonUtil"> <s:param name="javaValue" value="%{javaValue}"/> </s:bean> 
<script>
	var form = ${jsonUtil.json} ;	
</script>
