<%-- 
    Document   : signup
    Created on : 10-May-2016, 23:03:58
    Author     : FENG0
--%>
<title>Signup</title>
</head>
<body>
    <c:if test="${result == 'failed'}">
        <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" id="myModal">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Register Failed!</h4>
                    </div>
                    <div class="modal-body">
                        <p>There is already an account with the email...</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Dismiss</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
    </c:if>

    <div class="container">
        <div class="text-center" style="padding:50px 0">
            <div class="logo">register</div>
            <!-- Main Form -->
            <div class="login-form-1">
                <form role="form" id="register-form" name="register-form" method="POST" action="Signup" class="text-left">
                    <div class="login-form-main-message"></div>
                    <div class="main-login-form">
                        <div class="login-group">
                            <div class="form-group">
                                <label for="reg_email" class="sr-only">Email (as Username)</label>
                                <input type="text" class="form-control" id="reg_email" name="reg_email" placeholder="email (as username)">
                            </div>
                            <div class="form-group">
                                <label for="reg_password" class="sr-only">Password</label>
                                <input type="password" class="form-control" id="reg_password" name="reg_password" placeholder="password">
                            </div>
                            <div class="form-group">
                                <label for="reg_password_confirm" class="sr-only">Password Confirm</label>
                                <input type="password" class="form-control" id="reg_password_confirm" name="reg_password_confirm" placeholder="confirm password">
                            </div>
                            <div class="form-group">
                                <label for="reg_fullname" class="sr-only">Full Name</label>
                                <input type="text" class="form-control" id="reg_fullname" name="reg_fullname" placeholder="full name">
                            </div>
                            <div class="form-group">
                                <label for="reg_phone" class="sr-only">Phone</label>
                                <input type="text" class="form-control" id="reg_phone" name="reg_phone" placeholder="phone">
                            </div>
                            <div class="form-group login-group-checkbox">
                                <input type="checkbox" class="" id="reg_agree" name="reg_agree" required="true">
                                <label for="reg_agree">i agree with <a href="#" data-toggle="modal" data-target="#t_and_c_m">terms</a></label>
                            </div>
                        </div>
                        <button type="submit" class="login-button"><i class="fa fa-chevron-right"></i></button>
                    </div>
                    <div class="etc-login-form">
                        <p>already have an account? <a href="./index.jsp">login here</a></p>
                    </div>
                </form>
            </div>
            <!-- end:Main Form -->
        </div>
        <!-- Modal -->
        <div class="modal fade" id="t_and_c_m" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title" id="myModalLabel">Terms & Conditions</h4>
                    </div>
                    <div class="modal-body">
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">I Agree</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
    </div>
