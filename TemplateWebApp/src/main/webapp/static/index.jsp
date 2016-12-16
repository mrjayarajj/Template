<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="title" value="Hello WebSocket" type="String" />
	<tiles:putAttribute name="java_script" type="String">
		<script src="sockjs-0.3.4.js"></script>
		<script src="stomp.js"></script>
		<script type="text/javascript" src="/mvc/media/js/jquery/jquery.js"></script>

		<script type="text/javascript">
		
			var contextPath = "${pageContext.servletContext.contextPath}";
			
			var source = new EventSource(contextPath+"/wst/sse-questions");

			source.addEventListener('spring', function(event) {
				$('#questions').prepend("<br>");
				$('#questions').prepend("SSE: "+event.data);
			});
			
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");

			$(document).ready(function(){
				$( "#postQuestion" ).click(function() {
					postQuestionThroughSSE();
				});
			});
			
			function postQuestionThroughSSE() {

				var dataVal = $("#newQuestionForm").serialize();
				
				$.ajax({
					url : contextPath+'/wst/sse-new-questions',
					type : 'post',
					data : dataVal,
					contentType : 'application/xml;charset=UTF-8',
					beforeSend : function(xhr) {
						xhr.setRequestHeader(header, token);
					}
				});
			}
		</script>

		<script type="text/javascript">
		
			var host = "${pageContext.request.serverName}";
			var port = "${pageContext.request.serverPort}";
			var contextPath = "${pageContext.servletContext.contextPath}";
		
			var ws = new WebSocket("ws://"+host+":"+port+""+contextPath+"/wst/ws-questions");
						
			ws.onopen = function(){
				ws.send("i am opened..");				
			}
			
			ws.onmessage = function(event){
				console.log("message="+event.data);
				$('#questions').prepend("<br>");
				$('#questions').prepend("WS: "+event.data);
			}
			
			ws.onlcose = function(event){
				console.log("closed="+event.data);
			}
			
			$(document).ready(function(){
				$( "#postQuestion" ).click(function() {
					ws.send($('#newQuestionText').val());
				});
			});
			
		</script>
		
		<script type="text/javascript">
		
			var host = "${pageContext.request.serverName}";
			var port = "${pageContext.request.serverPort}";
			var contextPath = "${pageContext.servletContext.contextPath}";
		
			var sjs = new SockJS(contextPath+"/wst/sjs-questions");
						
			sjs.onopen = function(){
				sjs.send("i am opened..");				
			}
			
			sjs.onmessage = function(event){
				console.log("message="+event.data);
				$('#questions').prepend("<br>");
				$('#questions').prepend("SJS: "+event.data);
			}
			
			sjs.onlcose = function(event){
				console.log("closed="+event.data);
			}
			
			$(document).ready(function(){
				$( "#postQuestion" ).click(function() {
					sjs.send($('#newQuestionText').val());
				});
			});
			
		</script>

		<script type="text/javascript">
			
			var stompClient = null;

			function setConnected(connected) {
				document.getElementById('connect').disabled = connected;
				document.getElementById('disconnect').disabled = !connected;
			}
			
			var contextPath = "${pageContext.servletContext.contextPath}";

			connect();
			
			function connect() {
				//Start a webscoket
				var socket = new SockJS(contextPath+'/wst/stom-questions');
				stompClient = Stomp.over(socket);
				stompClient
						.connect(
								{},
								function(frame) {
									setConnected(true);
									console.log('Connected: ' + frame);
									stompClient
											.subscribe(
													'/topic/stom-sub-questions',
													function(greeting) {
														
														var msg = JSON.parse(greeting.body);
														
														if(msg.content!=undefined){
															showGreeting("STOM application destination: "+msg.content);
														}else{
															showGreeting("STOM subscription destination: "+msg.name);
														}
													});
									
									
									stompClient
									.subscribe(
											'/user/queue/private',
											function(message) {
												
												showGreeting("STOM user destination: "+message.body);
												
											});
								});
			}

			function disconnect() {
				if (stompClient != null) {
					stompClient.disconnect();
				}
				setConnected(false);
				console.log("Disconnected");
			}
			
			$(document).ready(function(){
				$( "#postQuestion" ).click(function() {
					sendName();
				});
			});

			function sendName() {
				var name = document.getElementById('newQuestionText').value;
				
				stompClient.send("/app/stom-questions", {}, JSON.stringify({
					'name' : name
				}));
				
				stompClient.send("/topic/stom-sub-questions", {}, JSON.stringify({
					'name' : name
				}));
			}

			function showGreeting(message) {
				$('#questions').prepend("<br>");
				$('#questions').prepend(message);
			}
			
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="menu" value="" type="String" />
	<tiles:putAttribute name="body">
		<div>
			<form id="newQuestionForm" onsubmit="return false;">
				Question : <input type="text" id="newQuestionText"
					name="newQuestionText" />
				<button id="postQuestion">Post Question</button>
				<div id="questions"></div>
			</form>
		</div>

		<div>
			<div>
				<button id="connect" onclick="connect();">Sjs Connect</button>
				<button id="disconnect" disabled="disabled" onclick="disconnect();">Sjs Disconnect</button>
			</div>
			<br>
		</div>
		
		<a href="http://corp.apple.com:8080/wst/stom-post-questions?question=how%20is%20it%20done%20?" target="_blank" >post a question to a topic from a different tab</a>
		
		<br>
		
		stompClient.send("/user/mrjayarajj/queue/private",{},"hi ");
		
	</tiles:putAttribute>
</tiles:insertDefinition>



</html>