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
    });
</script>
<style type="text/css">
.no-close .ui-dialog-titlebar-close {
    display: none;
}
table#tbl.dataTable tbody tr:hover {
    background-color: #43fff8;
}
#inline-tabs {
    background: #f9f9f9 none repeat scroll 0 0;
}
</style>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">Procedures Scheduled today</div>
    <div class="ke-page-content" id="tabs" style="margin-top: 40px!important;">
        <ul id="inline-tabs">
            <li><a href="#tabs-1">Procedure Queue</a></li>
            <li><a href="#tabs-2">Completed Procedure</a></li>
            <li><a href="#tabs-3">Declined Procedure</a></li>
        </ul>
        <div id="tabs-1">
                <table>
                    <tr>
                        <td width="30%">&nbsp;</td>
                        <td style="background-color: #F3F9FF;" width="70%">
                            <table id="tbl" width="100%">
                                <thead>
                                <tr>
                                    <th>Patient ID</th>
                                    <th>Procedure ID</th>
                                    <th>Patient Names</th>
                                    <th>Departiment</th>
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

                        </td>
                    </tr>
                </table>
        </div>
        <div id="tabs-2"></div>
        <div id="tabs-3"></div>
    </div>
</div>