<%
    ui.decorateWith("kenyaemr", "standardPage", [ patient: currentPatient])
    ui.includeCss("ehrconfigs", "referenceapplication.css")

%>
<script>
    PAGE = {
        /** SUBMIT */
        submit: function () {
            jq("#procedureSummaryForm").submit();
        }
    };
</script>

<div class="ke-panel-frame">
    <div class="ke-panel-heading">Scheduled procedure is : ${procedure}</div>
    <div class="ke-page-content">
        <table cellpadding="0" cellspacing="0" width="100%">
            <tr>
                <td width="30%">&nbsp;</td>
                <td width="40%">
                    <form form id="procedureSummaryForm" method="post">
                        <div class="onerow">
                            <h2>Action taken<span>*</span></h2>
                            <div class="col4">
                                <p>
                                    <select id="action-taken" name="action-taken">
                                        <option value="">-Please select-</option>
                                            <option value="1267">Completed</option>
                                            <option value="1118">Declined</option>
                                    </select>
                                    <span id="error123" class="field-error" style="display: none"></span>
                                </p>
                            </div>
                            <h2>Notes</h2>
                            <div class="col4">
                                <p>
                                    <textarea id="procedureNotes" name="procedureNotes" rows="4" cols="50">&nbsp;</textarea>
                                </p>
                            </div>
                        </div>
                        <div class="onerow" style="margin-top: 100px">

                            <a class="button confirm" onclick="PAGE.submit();"
                               style="float:right; display:inline-block; margin-left: 5px;">
                                <span>FINISH</span>
                            </a>

                            <a class="button cancel" onclick="window.location.href = window.location.href"
                               style="float:right; display:inline-block;"/>
                            <span>RESET</span>
                        </a>
                        </div>
                    </form>
                </td>
                <td width="30%"></td>
            </tr>
        </table>
    </div>
</div>