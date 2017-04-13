    <c:if test="${result == 'failed'}">
        <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" id="myModal">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Login Failed!</h4>
                    </div>
                    <div class="modal-body">
                        <c:if test="${error == 'email'}">
                            <p>There is no account with the email entered.</p>
                        </c:if>
                        <c:if test="${error == 'password'}">
                            <p>The email and password does not match.</p>
                        </c:if>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Dismiss</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
    </c:if>
        <div class="text-center" style="padding:50px 0">
            <div class="logo">login</div>
            <div class="login-form-1">
                <form role="form" id="login-form" name="login-form" method="POST" method="POST" action="Login" class="text-left">
                    <div class="login-form-main-message"></div>
                    <div class="main-login-form">
                        <div class="login-group">
                            <div class="form-group">
                                <label for="login_email" class="sr-only">E-mail</label>
                                <input type="text" class="form-control" id="login_email" name="login_email" placeholder="username">
                            </div>
                            <div class="form-group">
                                <label for="login_password" class="sr-only">Password</label>
                                <input type="password" class="form-control" id="login_password" name="login_password" placeholder="password">
                            </div>
                        </div>
                        <button type="submit" class="login-button"><i class="fa fa-chevron-right"></i></button>
                    </div>
                    <div class="etc-login-form">
                        <p>new user? <a href="./signup.jsp">create new account</a></p>
                    </div>
                    <div class="etc-login-form">
                        <p>administrator? <a href="./login.jsp">login here</a></p>
                    </div>
                </form>
            </div>
        </div>
    </div>