<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- The following line is essential for the "position: fixed" property to work correctly in IE -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <title>jquery.ui.chatbox</title>
    
    <link rel="stylesheet" href="/mvc/media/css/jquery-ui/jquery-ui.css" type="text/css" media="screen" />
    
    <script type="text/javascript" src="/mvc/media/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/mvc/media/js/jquery-ui/jquery-ui.js"></script>
    
    <link type="text/css" href="/static/jquery.ui.chatbox.css" rel="stylesheet" />
    <script type="text/javascript" src="/static/jquery.ui.chatbox.js"></script>
    
    <script type="text/javascript">
    
    	$(document).ready(function(){
          
    	  var user = new Object();
          
          $('li').on('click', function(event) {
        	  
        	  var userId =   $(this).attr('userId');
        	  var userName = $(this).attr('userName');
        	  
        	  console.log(userName);
        	  
              if(user[userId]!=null) {
            	  user[userId].chatbox("option", "boxManager").toggleBox();
              }
              else {
            	  user[userId] = $("#chat_div_"+userId).chatbox({id:"You", 
                                                user:{key : "value"+userId},
                                                title : userName ,
                                                messageSent : function(id, user, msg) {
                                                	console.log(msg);
                                                    $("#log").append(id + " said: " + msg + "<br/>");
                                                    $("#chat_div_"+userId).chatbox("option", "boxManager").addMsg(id, msg);
                                                }});
              }
          });
      });
    </script>
  </head>
  <body>
    	<p>It will not work untill necessary scripts and stylesheets are properly loaded, check out the code.</p>  
  		
  		<ul>
	    	<c:forEach varStatus="loopCounter" var="p" items="${principals}">
				<li  userId="${p.userId}" userName="${p.userName}" >${p.userName} <div id="chat_div_${p.userId}" />  </li>
			</c:forEach>
		</ul>
    	
    	<div id="log" />
  </body>
</html>