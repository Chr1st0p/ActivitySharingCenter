<div class="card-footer text-muted" id="content" width="100%">
    <script type="text/javascript">
        function add() {
            var div = document.getElementById("addText");
            div.style.display = '';
            div.focus();
        }
    </script>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ include file="./WEB-INF/jspf/delAlarm.jspf"%>
    <!-- window.location.href = './addposts.jsp?AcId=${ActivityId}' -->
    <%@ include file="./AjexPR.jsp"%>
    <div>
        <button class="btn btn-primary" onclick="add();" value="Add"
                Id="addposts">Add Posts</button><br>
        <div id='addText' style="display: none;">
            <%@ include file="./addposts.jsp"%>
        </div>
    </div><br>
    <div>
        <c:forEach items="${IdSet}" var="Id">
            <div class="msg even">
                <div class="col-md-3 author1">
                    <h5 class="media-heading">
                        ${Nickname[Id]}<small class="text-muted"> 

                        </small>
                    </h5>
                    <small class="text-muted"> <i class="fa fa-clock-o"></i>
                        ${PostTime[Id]}
                    </small><br>
                    <c:if
                        test="${ sessionScope.userid == PostUserId[Id]}">
                        <button class="btn btn-sm btn-warning"
                                onclick=" sendRequest2(${ActivityId},${Id});" value="deleteP">
                            delete</button>
                        </c:if>
                </div>
                <div class="col-md-9 author">
                    <a href="javascript:void(0)"
                       onclick="sendRequest(${Id});"> <font size="5">
                        ${fn:substring(PostContent[Id],0,200)} </font>
                    </a>
                </div>
                <div class="clearfix"></div>
            </div>

        </c:forEach>
    </div>
</div>