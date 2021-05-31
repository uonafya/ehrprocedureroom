<%
    ui.decorateWith("kenyaemr", "standardPage")
    ui.includeJavascript("ehrconfigs", "jquery.dataTables.min.js")
    ui.includeCss("ehrconfigs", "jquery.dataTables.min.css")
%>
<script type="text/javascript">
    jQuery(function() {
        var table =  jQuery("#tbl").DataTable();
    });
</script>
<style type="text/css">
.no-close .ui-dialog-titlebar-close {
    display: none;
}
body {
    font: 90%/1.45em "Helvetica Neue", HelveticaNeue, Verdana, Arial, Helvetica, sans-serif;
    margin: 0;
    padding: 0;
    color: #333;
}
table#summary.dataTable tbody tr:hover {
    background-color: #43fff8;
}
</style>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">Procedures Scheduled today</div>
    <div class="ke-page-content" style="background-color: #F3F9FF;">
        <table id="tbl" width="100%">
            <thead>
            <tr>
                <th>Patient ID</th>
                <th>Patient identifier</th>
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
                    No records found for this patirnt
                </td>
            </tr>
            <% } %>
            <% procedures.each {%>
            <tr>
                <td>${it.patientId}</td>
                <td>${it.patientIdentifier}</td>
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