<div class="panel panel-default">
    <div class="panel-heading">Activity List</div>

    <div class="panel-body">
        <table id="myTable" class="table table-bordered table-striped table-hover js-basic-datatable dataTable" id="DataTables_Table" role="grid" aria-describedby="DataTables_Table_info" cellspacing="0" width="100%">
            <thead> 
                <tr><td>Name</td><td>Location</td><td>Joined</td><td>Action</td></tr>						
            </thead> 
            <tbody>

                <c:forEach items="${requestScope.activityList}" var="emp">

                    <tr><td>
                            <c:out value="${emp.name}"></c:out>


                            </td>

                            <td>
                            <c:out value="${emp.location}"></c:out></td>
                            <td>
                            <c:out value="${emp.joined}"></c:out></td>
                            <td>
                                <a href='<c:out value="ViewActivity?id=${emp.id}"></c:out>'>View</a></td>
                        </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>
    <script>
        $(document).ready(function () {
            $('.js-basic-datatable').DataTable();
            $('#datetimepicker1').datetimepicker({
                defaultDate: new Date(),
                format: 'YYYY-MM-DD HH:mm:ss'
            });

        });



    </script>


</body>
</html>