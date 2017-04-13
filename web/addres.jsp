<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
	function delayURL( ) {
		var referrer = document.referrer;
		var delay = document.getElementById("time").innerHTML;
		if(delay>0){
			delay--;
			document.getElementById("time").innerHTML = delay;
		} else{
			window.top.location=referrer;
		}
		setTimeout("delayURL( )",1000);
	}
</script>
<body>
<div>
	<div align="center">
		<%String ref = request.getHeader("REFERER");%>   
		<h1>Add ${ori} <c:if test="${res=='1'}">success</c:if><c:if test="${res=='0'}">fail</c:if></h1>
		<h3><span id = "time" >3</span> seconds to trun to orginal page<br>If note please click
			<%String s=""; %>
			<c:if test="${ori=='Reply' }">
			
				<%s = "./GetReply?PostId="+Integer.parseInt(request.getAttribute("Id").toString());%>
			</c:if>
			<c:if test="${ori=='Post' }">
				
				<%s = "./GetPost?ActivityId="+Integer.parseInt(request.getAttribute("Id").toString());%>
			</c:if>
			<a href="javascript:window.location='<%=ref%>'">here</a>	
		</h3>
		
		<script type="text/javascript">
			delayURL( );
		</script>
	</div>
</div>