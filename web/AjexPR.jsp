<script>
function initRequest() {
 	if (window.XMLHttpRequest) {
 		return new XMLHttpRequest();
 	} else if (window.ActiveXObject) {
 		isIE = true;
 		return new ActiveXObject("Microsoft.XMLHTTP");
 	}
}
function sendRequest(Id) {
 	var url = "./GetReply?PostId=" + Id;
 	var req = initRequest();
 	req.onreadystatechange = function() {
 		if (req.readyState == 4) {
 			if (req.status == 200) {
 				document.getElementById("content").innerHTML = req.responseText;
 			}
 		}
 	};
 	req.open("GET", url, true);
 	req.send(null);
}
function sendRequest1(Id) {
	
 	var url = "./GetPostServlet?ActivityId=" + Id;
 	var req = initRequest();
 	req.onreadystatechange = function() {
 		if (req.readyState == 4) {
 			if (req.status == 200) {
 				document.getElementById("content").innerHTML = req.responseText;
 			}
 		}
 	};
 	req.open("GET", url, true);
 	req.send(null);
}
function sendRequest2(Id1,Id2) {
	if(!confirm('Are you sure you want to delete?')){
		return false;
	}
 	var url ="./DelPosts?Id=" + Id2;
 	var req = initRequest();
 	req.onreadystatechange = function() {
 		if (req.readyState == 4) {
 			if (req.status == 200) {
 				document.getElementById("content").innerHTML = req.responseText;
 			}
 		}
 	};
 	req.open("GET", url, true);
 	req.send(null);
}
function sendRequest3(Id1,Id2) {
	if(!confirm('Are you sure you want to delete?')){
		return false;
	}
 	var url ="./DelReply?Id=" + Id2;
 	var req = initRequest();
 	req.onreadystatechange = function() {
 		if (req.readyState == 4) {
 			if (req.status == 200) {
 				document.getElementById("content").innerHTML = req.responseText;
 			}
 		}
 	};
 	req.open("GET", url, true);
 	req.send(null);
}
</script>