<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
  <head>     	
	<title>Redirecting....</title> 
	<meta http-equiv="refresh" content="2;url=http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.servletContext.contextPath}/jsp/base/welcome.jsp">
   	
   </head>
  
  <body>
    Loading index please wait... 
  </body>
</html>
