<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
        <!-- Bootstrap core CSS -->
        <link href="./resources/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap theme -->
        <link href="./resources/css/bootstrap-theme.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${initParam.css}/font-awesome.min.css">
        <link href="${initParam.css}/fonts.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="${initParam.css}/theme.css" rel="stylesheet">
        <title>showPost</title>
    </head>
    <body>
        <div>
            <h1 align="center">
                <form  action="GetPostServlet">
                    <input name="ActivityId"><br>
                    <input type="submit"  class="btn btn-lg btn-default" value="NiMaHi" method="POST"></input>
                </form>
            </h1>
        </div>

        <!-- Bootstrap core JavaScript
    ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${initParam.js}/jquery-3.1.1.min.js"></script>
        <script src="${initParam.js}/bootstrap.min.js"></script>
    </body>
</html>