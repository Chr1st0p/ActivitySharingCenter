<div class="card-footer text-muted" id="content" width="100%">

    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ include file="./AjexPR.jsp"%>
    <div>
        <button class="btn btn-primary"
                onclick=" sendRequest1(${ActivityId});" value="returnpost">
            return</button>
    </div>
    <br>
    <div>
        <div class="msg even">
            <div class="col-md-3 author1">
                <h5 class="media-heading">
                    ${NicknameP}<small class="text-muted"> </small>
                </h5>
                <small class="text-muted"> <i class="fa fa-clock-o"></i>
                    ${PostTime}
                </small><br>
                <button class="btn btn-sm btn-primary" onclick="addRe(-1);"
                        value="Add">reply</button>
                <c:if test="${ sessionScope.userid == PostUserId}">
                    <button class="btn btn-sm btn-warning"
                            onclick=" sendRequest2(${ActivityId},${PostId});" value="deleteP">delete</button>
                </c:if>
            </div>
            <div class="col-md-9 author">${PostContent}</div>
            <div class="clearfix"></div>
        </div>
    </div>
    <div>
        <c:forEach items="${IdSet}" var="Id">
            <div class="msg even">
                <div class="col-md-3 author1">
                    <h4>Reply to ${NicknameR[Id]}</h4>
                    <h5 class="media-heading">
                        ${Nickname[Id]}<small class="text-muted"> </small>
                    </h5>
                    <small class="text-muted"> <i class="fa fa-clock-o"></i>
                        ${ReplyTime[Id]}
                    </small><br>
                    <button class="btn btn-sm btn-primary" onclick="addRe(${Id});"
                            value="Add">reply</button>
                    <c:if test="${ sessionScope.userid == ReplyUserId[Id]}">
                        <button class="btn btn-sm btn-warning"
                                onclick=" sendRequest3(${ActivityId},${Id});" value="deleteP">
                            delete</button>
                        </c:if>
                </div>
                <div class="col-md-9 author">${ReplyContent[Id]}</div>
                <div class="clearfix"></div>
            </div>
        </c:forEach>
    </div><br>
    <div id='addText' style="display: none;">
        <%@ include file="./addreply.jsp"%>
    </div>
</div>