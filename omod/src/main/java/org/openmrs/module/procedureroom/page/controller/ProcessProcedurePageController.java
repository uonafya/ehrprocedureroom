package org.openmrs.module.procedureroom.page.controller;

import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@AppPage("procedureroom.procedure")
public class ProcessProcedurePageController {
	
	public void controller(UiUtils ui, PageModel model, @RequestParam("department") String department,
	        @RequestParam("patientId") Patient patient, @RequestParam("procedure") String procedure,
	        @RequestParam("procedureId") Integer procedureId) {
		
		List<Concept> actonList = new ArrayList<Concept>();
		Map<Integer, String> conceptIdAndName = new HashMap<Integer, String>();
		actonList.add(Context.getConceptService().getConceptByUuid("1267AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
		actonList.add(Context.getConceptService().getConceptByUuid("1118AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
		for (Concept concept : actonList) {
			conceptIdAndName.put(concept.getConceptId(), concept.getName(Locale.US, true).getName());
		}
		
		model.addAttribute("procedure", procedure);
		model.addAttribute("action", conceptIdAndName);
	}
	
	public String post(HttpServletRequest request, PageModel model, UiUtils uiUtils,
	        @RequestParam("action-taken") Integer action, @RequestParam("procedureNotes") String notes) {
		
		return "redirect:" + uiUtils.pageLink("procedureroom", "procedureRoomAppHome");
	}
}
