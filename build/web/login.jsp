<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
        <!-- Bootstrap -->
        <link href="${initParam.css}/bootstrap.min.css" rel="stylesheet">
        <link href="${initParam.css}/fonts.css" rel="stylesheet">        
        <link rel="stylesheet" href="${initParam.css}/font-awesome.min.css">
        <title>Login</title>
    </head>
    <body>
        <c:if test="%{result == 'failed'}">
            <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" id="myModal">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Login Failed!</h4>
                        </div>
                        <div class="modal-body">
                            <s:if test="%{error == 'email'}">
                                <p>There is no Admin account with the email entered.</p>
                            </s:if>
                            <s:if test="%{error == 'password'}">
                                <p>The email and password does not match.</p>
                            </s:if>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Dismiss</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
        </c:if>    
        <form id="login_form" name="login_form" method="POST" action="AdminLogin">
            <div class="container" align="center" style="margin-top: 20px;">
                <div class="well well-lg" style="font-size: 20px;">
                    <div>
                        <img src="${initParam.img}/logo.png" height="100" width="120"
                             id="imgLogo" />
                    </div>
                    <h1>Activity Sharing Center Administration Login</h1>
                    <div class="row" align="center" style="margin-left: 20%; margin-right: 20%;">
                        <label for="login_email" class="sr-only">E-mail</label>
                        <input type="text" class="form-control" id="login_email" name="login_email" placeholder="username">
                    </div>
                    <br />
                    <div class="row" align="center" style="margin-left: 20%; margin-right: 20%">
                        <label for="login_password" class="sr-only">Password</label>
                        <input type="password" class="form-control" id="login_password" name="login_password" placeholder="password">
                    </div>
                    <br />
                    <div class="panel-body" align="center">
                        <button type="submit" class="btn btn-primary">Login</button>
                    </div>
                </div>
            </div>
        </form>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="${initParam.js}/jquery-3.1.1.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="${initParam.js}/bootstrap.min.js"></script>
        <!-- Login -->
        <script src="${initParam.js}/jquery.validate.min.js"></script>
        <script src="${initParam.js}/login.js"></script>
        <c:if test="${result == 'failed'}">
            <script>
                $(document).ready(function () {
                    $('#myModal').modal('show');
                });
            </script>
        </c:if>
    </body>
</html>
