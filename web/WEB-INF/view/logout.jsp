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
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="${initParam.js}/jquery-3.1.1.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="${initParam.js}/bootstrap.js"></script>
        <title>Logout</title>
    </head>
    <body>
        <div class="container">
            <div class="header clearfix">
                <h3 class="text-danger"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> You're logged out. Goodbye and see you again!</h3>
            </div>

            <div class="jumbotron">
                <h2>To login again...</h2>
                <p><a class="btn btn-lg btn-success" href="./index.jsp" role="button">Login</a></p>
            </div>
        </div> <!-- /container -->
    </body>
</html>