<%
    ui.decorateWith("kenyaemr", "standardPage")
    ui.includeJavascript("ehrconfigs", "jquery.dataTables.min.js")
    ui.includeCss("ehrconfigs", "jquery.dataTables.min.css")
%>
<script type="text/javascript">
    jQuery(function() {
        jQuery("#tabs").tabs();
        var table =  jQuery("#tbl").DataTable();
        jQuery('#tbl tbody').on( 'click', 'tr', function () {
            var results = table.row( this ).data();
            ui.navigate('procedureroom', 'processProcedure', {patientId: results[0], procedureId: results[1], department: results[3], procedure: results[4]});
        } );
        jQuery("#tbl1").DataTable();
        jQuery("#tbl2").DataTable();
    });
</script>
<style type="text/css">
.no-close .ui-dialog-titlebar-close {
    display: none;
}
table#tbl.dataTable tbody tr:hover {
    background-color: #43fff8;
}
table#tbl1.dataTable tbody tr:hover {
    background-color: #43fff8;
}
table#tbl2.dataTable tbody tr:hover {
    background-color: #43fff8;
}
#inline-tabs {
    background: #f9f9f9 none repeat scroll 0 0;
}
</style>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">Procedures Scheduled today</div>
    <br />
    <div class="ke-page-content">


        <div style="background-color: #F3F9FF;" width="100%" id="tabs" style="margin-top: 40px!important;">
            <ul id="inline-tabs">
                <li><a href="#tabs-1">Procedure Queue</a></li>
                <li><a href="#tabs-2">Completed Procedure</a></li>
                <li><a href="#tabs-3">Declined Procedure</a></li>
            </ul>
        <div id="tabs-1">
            <table id="tbl" width="100%">
                <thead>
                <tr>
                    <th>Patient ID</th>
                    <th>Procedure ID</th>
                    <th>Patient Names</th>
                    <th>Department</th>
                    <th>Ordered Procedure</th>
                    <th>Date Ordered</th>
                </tr>
                </thead>
                <tbody>
                <% if (procedures.empty) { %>
                <tr>
                    <td colspan="6">
                        No records found.
                    </td>
                </tr>
                <% } %>
                <% procedures.each {%>
                <tr>
                    <td>${it.patientId}</td>
                    <td>${it.procedureId}</td>
                    <td>${it.names}</td>
                    <td>${it.from}</td>
                    <td>${it.procedure}</td>
                    <td>${it.timeOrdered}</td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
        <div id="tabs-2">
            <table id="tbl1" width="100%">
                <thead>
                <tr>
                    <th>Patient Names</th>
                    <th>Departiment</th>
                    <th>Ordered Procedure</th>
                    <th>Date Ordered</th>
                    <td>Status</td>
                    <td>Notes</td>
                </tr>
                </thead>
                <tbody>
                <% if (done.empty) { %>
                <tr>
                    <td colspan="6">
                        No records found.
                    </td>
                </tr>
                <% } %>
                <% done.each {%>
                <tr>
                    <td>${it.patientId}</td>
                    <td>${it.procedureId}</td>
                    <td>${it.names}</td>
                    <td>${it.from}</td>
                    <td>${it.procedure}</td>
                    <td>${it.timeOrdered}</td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
        <div id="tabs-3">
            <table id="tbl2" width="100%">
                <thead>
                <tr>
                    <th>Patient Names</th>
                    <th>Departiment</th>
                    <th>Ordered Procedure</th>
                    <th>Date Ordered</th>
                    <td>Status</td>
                    <td>Notes</td>
                </tr>
                </thead>
                <tbody>
                <% if (declined.empty) { %>
                <tr>
                    <td colspan="6">
                        No records found.
                    </td>
                </tr>
                <% } %>
                <% declined.each {%>
                <tr>
                    <td>${it.patientId}</td>
                    <td>${it.procedureId}</td>
                    <td>${it.names}</td>
                    <td>${it.from}</td>
                    <td>${it.procedure}</td>
                    <td>${it.timeOrdered}</td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
        </div>
    </div>
</div>