package org.openmrs.module.procedureroom.page.controller;

import org.openmrs.Patient;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

@AppPage("procedureroom.procedure")
public class ProcessProcedurePageController {
	
	public void controller(UiUtils ui, PageModel model, @RequestParam("department") String department,
	        @RequestParam("patientId") Patient patient, @RequestParam("procedure") Integer procedure,
	        @RequestParam("procedureId") Integer procedureId) {
		
	}
}
