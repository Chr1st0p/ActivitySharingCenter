<script type="text/javascript">
	function cancel(){
		var div = document.getElementById("addText");
    	div.style.display='none';
    }
</script>
<form action="AddReplyServlet" accept-charset="UTF-8" method="POST" role="form">
	<input  Id="addRe" name="ReId" value="1" type="hidden">
	
	<div class="form-group">
		<textarea class="form-control counted" rows="3"
			placeholder="Write a reply..." name="content"></textarea>
		
	</div>
	<div class="form-group">
		<h6 class="pull-right" id="counter">2500 characters remaining</h6>
		<input type="submit"  class="btn btn-sm btn-primary" value="Sure" method="POST"></input>&nbsp;
	    <input type="button" class="btn btn-sm btn-warning" name="Submit" onclick="cancel();" value="Back">
	</div>
</form>