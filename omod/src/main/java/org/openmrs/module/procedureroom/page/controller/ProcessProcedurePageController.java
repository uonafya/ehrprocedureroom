package org.openmrs.module.procedureroom.page.controller;

import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.ehrconfigs.utils.EhrConfigsUtils;
import org.openmrs.module.kenyaemr.api.KenyaEmrService;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.module.procedureroom.model.SimplifiedConcept;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AppPage("procedureroom.procedure")
public class ProcessProcedurePageController {
	
	public void controller(UiUtils ui, PageModel model, @RequestParam("department") String department,
	        @RequestParam("patientId") Patient patient, @RequestParam("procedure") String procedure,
	        @RequestParam("procedureId") Integer procedureId) {
		
		List<Concept> actonList = new ArrayList<Concept>();
		List<SimplifiedConcept> conceptIdAndName = new ArrayList<SimplifiedConcept>();
		actonList.add(Context.getConceptService().getConceptByUuid("1267AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
		actonList.add(Context.getConceptService().getConceptByUuid("1118AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
		SimplifiedConcept simplifiedConcept = new SimplifiedConcept();
		for (Concept concept : actonList) {
			if (concept != null && concept.getName() != null) {
				simplifiedConcept.setConcept_id(concept.getConceptId());
				simplifiedConcept.setName(concept.getName().getName());
				conceptIdAndName.add(simplifiedConcept);
			}
		}
		
		model.addAttribute("procedure", procedure);
		model.addAttribute("action", conceptIdAndName);
	}
	
	public String post(HttpServletRequest request, PageModel model, UiUtils uiUtils,
	        @RequestParam("patientId") Patient patient, @RequestParam("action-taken") Integer action,
	        @RequestParam("procedureNotes") String notes, @RequestParam("procedureId") Integer procedureId) {
		KenyaEmrService kenyaEmrService = Context.getService(KenyaEmrService.class);
		Concept procedureConcept = Context.getConceptService().getConceptByUuid("1651AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		if (action != null && procedureId != null) {
			//create observations and te those to the encounter
			Obs procedure = new Obs();
			procedure.setLocation(kenyaEmrService.getDefaultLocation());
			procedure.setObsDatetime(new Date());
			procedure.setConcept(procedureConcept);
			procedure.setValueCoded(Context.getConceptService().getConcept(procedureId));
			procedure.setValueText(Context.getConceptService().getConcept(action).getName().getName());
			if (notes != null) {
				procedure.setComment(notes);
			}
			procedure.setPerson(patient);
			//create a new encounter and attach it to the obs
			Encounter encounter = new Encounter();
			encounter.setProvider(EhrConfigsUtils.getDefaultEncounterRole(),
			    EhrConfigsUtils.getProvider(Context.getAuthenticatedUser().getPerson()));
			encounter.setEncounterDatetime(new Date());
			encounter.setEncounterType(Context.getEncounterService().getEncounterTypeByUuid(
			    "ba45c278-f290-11ea-9666-1b3e6e848887"));
			encounter.setVisit(Context.getVisitService().getActiveVisitsByPatient(patient).get(0));
			encounter.setPatient(patient);
			
			//tie the obs to the encounter
			encounter.addObs(procedure);
			//save the encounter to the DB
			Context.getEncounterService().saveEncounter(encounter);
			
		}
		return "redirect:" + uiUtils.pageLink("procedureroom", "procedureRoomAppHome");
	}
}
