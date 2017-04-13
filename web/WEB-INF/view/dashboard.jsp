<div class="container body-content col-sm-12">
    <div class="container-fluid">
        <div class="row clearfix">
            <div class="header">
                <h2>
                    ${type} Activities
                </h2>
            </div>
            <table class="table table-bordered table-striped table-hover js-basic-datatable dataTable" id="DataTables_Table" role="grid" aria-describedby="DataTables_Table_info">
                <thead>
                    <tr role="row">
                        <th class="sorting_asc" tabindex="0" aria-controls="DataTables_Table" rowspan="1" colspan="1" aria-sort="ascending" style="width: 50px;">Activity ID</th>
                        <th class="sorting" tabindex="0" aria-controls="DataTables_Table" rowspan="1" colspan="1" style="width: 120px;">Activity Time</th>
                        <th class="sorting" tabindex="0" aria-controls="DataTables_Table" rowspan="1" colspan="1" style="width: 100px;">Created By</th>
                        <th class="sorting" tabindex="0" aria-controls="DataTables_Table" rowspan="1" colspan="1" style="width: 100px;">Category</th>
                        <th class="sorting" tabindex="0" aria-controls="DataTables_Table" rowspan="1" colspan="1" style="width: 150px;">Location</th>
                        <c:if test="${type == 'All'}"><th class="sorting" tabindex="0" aria-controls="DataTables_Table" rowspan="1" colspan="1" style="width: 80px;">Status</th></c:if>
                        <th aria-controls="DataTables_Table" rowspan="1" colspan="1" style="width: 100px;">Action</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th rowspan="1" colspan="1">Activity ID</th>
                        <th rowspan="1" colspan="1">Activity Time</th>
                        <th rowspan="1" colspan="1">Created By</th>
                        <th rowspan="1" colspan="1">Category</th>
                        <th rowspan="1" colspan="1">Location</th>
                        <c:if test="${type == 'All'}"><th rowspan="1" colspan="1">Status</th></c:if>
                        <th rowspan="1" colspan="1">Action</th>
                    </tr>
                </tfoot>
                <tbody>
                    <c:forEach items="${Activities}" var="Activity">
                        <tr role="row">
                            <td class="sorting_1" style="vertical-align: middle;">${Activity.getActivityID()}</td>
                            <td style="vertical-align: middle;"><fmt:formatDate value="${Activity.getActivityTime()}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                            <td style="vertical-align: middle;">${Activity.getCreatedBy()}</td>
                            <td style="vertical-align: middle;">${Activity.getCategory()}</td>
                            <td style="vertical-align: middle;">${Activity.getLocation()}</td>
                            <c:if test="${type == 'All'}"><td style="vertical-align: middle;">${Activity.getStatus()}</td></c:if>
                            <td><a class="btn btn-success" href="./AdminActivity?id=${Activity.getActivityID()}" role="button">View</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>       
<script>
    $(function () {
        $('.js-basic-datatable').DataTable();
    });
</script> 
</body>
</html>
