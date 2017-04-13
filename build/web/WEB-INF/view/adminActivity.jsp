<div class="panel panel-default">
    <div class="panel-heading">
        Activity Detail
    </div>
    <div class="panel-body">
        <div class="col-sm-9 col-md-10">
            <div class="msg-wrap">
                <p class="thread-title"> <c:out value="${activity.name}"></c:out></p>
                    <div class="msg odd">
                        <div class="col-md-3">
                            <h5 class="media-heading">${activity.creatorName} <small class="text-muted"> </small></h5>
                        <small class="text-muted"><i class=""></i>${activity.creatorNationlity}</small>
                    </div>
                    <div class="col-md-9 author">
                        <ul>
                            <li> <c:out value="${activity.name}"></c:out></li>
                                <li>Description:
                                <c:out value="${activity.description}"></c:out></li>
                                <li>
                                    Number Of people joined:
                                <c:out value="${activity.joined}"></c:out></li>
                                <li>
                                    Time:
                                <c:out value="${activity.time}"></c:out></li>
                                <li>
                                    Category:
                                <c:out value="${activity.categoryStr}"></c:out></li>
                                <li>Location:
                                <c:out value="${activity.location}"></c:out></li>
                                <li>Latitude:
                                <c:out value="${activity.latitude}"></c:out></li>
                                <li>Longitude:
                                <c:out value="${activity.longitude}"></c:out></li>
                            </ul>                            
                            <small class="text-muted"> </small>
                        </div>
                        <div class="clearfix"></div>
                        <form name="AdminActivityForm" id="AdminActivityForm" method="POST">
                            <div class="col-xs-12 col-sm-12 col-md-6">
                                <a class="btn btn-success" href="ApproveActivity?id=<c:out value="${activity.id}"></c:out>">Approve Activity</a>
                                <a class="btn btn-danger" href="RejectActivity?id=<c:out value="${activity.id}"></c:out>">Reject Activity</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!--  all end -->
        <script type="text/javascript">
            $(document).ready(function () {
            });
    </script>
</div>
</div>