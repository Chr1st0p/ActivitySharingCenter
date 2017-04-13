<!DOCTYPE html>
<html>
    <head>
        <title>Action Result</title>
    </head>
    <body>
        <script>
            var message = "${message}";
            var msg = "Redirecting to Dashbaord...";
            if (message.length !== 0) {
                msg = message + "  " + msg;
            }
            alert(msg);
            window.location.href = 'Dashboard';
        </script>
    </body>
</html>