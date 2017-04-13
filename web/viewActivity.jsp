<c:choose>
    <c:when test="${activity.rating!=0}">
        <c:set var="dsiabled" value="disabled" />
    </c:when>
</c:choose>
<nav>
    <ul class="pager">
        <li class="previous"><a href="DisplayActivity"><span
                    aria-hidden="true">&larr;</span>Back</a></li>
    </ul>
</nav>
<div class="panel panel-default">
    <div class="panel-heading">Activity Detail</div>
    <div class="panel-body">
        <div class="col-sm-9 col-md-10">
            <div class="msg-wrap">
                <p class="thread-title">
                    <c:out value="${activity.name}"></c:out>
                    </p>
                    <div class="msg odd">
                        <div class="col-md-3">
                            <h5 class="media-heading">${activity.creatorName}
                            <small class="text-muted"> </small>
                        </h5>
                        <small class="text-muted"> <i class=""></i>${activity.creatorNationlity}</small>
                    </div>
                    <div class="col-md-9 author">
                        <ul>
                            <li><c:out value="${activity.name}"></c:out></li>
                            <li>Description: <c:out value="${activity.description}"></c:out></li>
                            <li>Number Of people joined: <c:out
                                    value="${activity.joined}"></c:out></li>
                            <li>Time: <c:out value="${activity.time}"></c:out></li>
                            <li>Category: <c:out value="${activity.categoryStr}"></c:out></li>
                            <li>Location: <c:out value="${activity.location}"></c:out></li>
                            <li>Latitude: <c:out value="${activity.latitude}"></c:out></li>
                            <li>Longitude: <c:out value="${activity.longitude}"></c:out></li>
                            </ul>
                        <c:choose>
                            <c:when test="${activity.participant==true}">
                                <a class="btn btn-primary"
                                   href="CancelActivity?id=<c:out value="${activity.id}"></c:out>"
                                       onclick="return confirm('Are you sure you want to cancel this activity?');">
                                       Cancel</a>
                                </c:when>
                                <c:otherwise>
                                <a class="btn btn-primary"
                                   href="JoinActivity?id=<c:out value="${activity.id}"></c:out>"
                                       onclick="return confirm('Are you sure you want to join this activity?');">
                                       Join</a>
                                </c:otherwise>
                            </c:choose>
                        <small class="text-muted"> </small>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="msg even">
                    <div class="clearfix"></div>
                </div>
            </div>
            <script type="text/javascript">
                function addRe(reId) {
                    var div = document.getElementById("addText");
                    //alert(reId);
                    document.getElementById("addRe").value = reId;

                    div.style.display = "";
                }
                function add() {
                    var div = document.getElementById("addText");
                    div.style.display = '';
                    div.focus();
                }
            </script>
            <!--  <div class="send-wrap ">
                    <form accept-charset="UTF-8" action="" method="POST" role="form"
                            class="">
                            <div class="form-group">
                                    <textarea class="form-control counted" rows="3"
                                            placeholder="Write a reply..."></textarea>
                            </div>
                            <div class="form-group">
                                    <h6 class="pull-right" id="counter">2500 characters remaining</h6>
                                    <button class="btn btn-info" type="submit">Send</button>
                            </div>
                    </form>
            </div>-->
            <%@ include file="./posts.jsp"%>
        </div>
        <!--  all end -->
    </div>
</div>
</div>
<script>
    $(document).ready(function () {
        $('#datetimepicker1').datetimepicker({
            defaultDate: new Date(),
            format: 'YYYY-MM-DD HH:mm:ss'
        });
    });
</script>