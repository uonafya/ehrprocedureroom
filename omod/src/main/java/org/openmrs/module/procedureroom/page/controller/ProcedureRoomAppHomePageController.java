package org.openmrs.module.procedureroom.page.controller;

import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.OpdTestOrder;
import org.openmrs.module.kenyaemr.api.KenyaEmrService;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.module.procedureroom.model.CompletedProceduresSimplifier;
import org.openmrs.module.procedureroom.model.SimplifiedProcedure;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@AppPage("procedureroom.procedure")
public class ProcedureRoomAppHomePageController {
	
	public void controller(UiUtils ui, PageModel model) {
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
		List<OpdTestOrder> allTestOrders = hospitalCoreService.getAllOpdOrdersByDateRange(true);
		List<SimplifiedProcedure> simplifiedProcedureList = new ArrayList<SimplifiedProcedure>();
		Concept procedureConcept = Context.getConceptService().getConceptByUuid("1651AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		KenyaEmrService kenyaEmrService = Context.getService(KenyaEmrService.class);
		for (OpdTestOrder opdTestOrder : allTestOrders) {
			SimplifiedProcedure simplifiedProcedure = new SimplifiedProcedure();
			if (opdTestOrder.getConcept().equals(procedureConcept)) {
				simplifiedProcedure.setPatientId(opdTestOrder.getPatient().getPatientId());
				simplifiedProcedure.setProcedureId(opdTestOrder.getValueCoded().getConceptId());
				simplifiedProcedure.setNames(opdTestOrder.getPatient().getPersonName().getFullName());
				simplifiedProcedure.setFrom(opdTestOrder.getFromDept());
				simplifiedProcedure.setProcedure(opdTestOrder.getValueCoded().getName().getName());
				simplifiedProcedure.setTimeOrdered(opdTestOrder.getCreatedOn());
				
				//add that on the list
				simplifiedProcedureList.add(simplifiedProcedure);
			}
		}
		//check all the procedures processed for the patient and exclude them
		List<Obs> procedureObs = Context.getObsService().getObservations(null, null, Arrays.asList(procedureConcept), null,
		    null, Arrays.asList(kenyaEmrService.getDefaultLocation()), null, null, null, getPreviousDateBasedOnMonths(),
		    new Date(), false);
		List<CompletedProceduresSimplifier> completedProceduresSimplifierList = new ArrayList<CompletedProceduresSimplifier>();
		for (Obs obs : procedureObs) {
			CompletedProceduresSimplifier completedProceduresSimplifier = new CompletedProceduresSimplifier();
			if (obs != null && obs.getValueCoded() != null && obs.getValueText() != null) {
				completedProceduresSimplifier.setPatientName(obs.getPerson().getPersonName().getFullName());
				completedProceduresSimplifier.setProcedureOrdered(obs.getValueCoded().getName().getName());
				completedProceduresSimplifier.setDatePerformed(obs.getObsDatetime());
				completedProceduresSimplifier.setAction(obs.getValueText());
				completedProceduresSimplifier.setComments(obs.getComment());
				completedProceduresSimplifier.setPatientId(obs.getPersonId());
				
				//add it to a list
				completedProceduresSimplifierList.add(completedProceduresSimplifier);
			}
		}
		//loop through what is suppossed to be done today and what is already done
		//Difference is what we need to remian schduled for today
		List<SimplifiedProcedure> filteredFinal = new ArrayList<SimplifiedProcedure>();
		List<SimplifiedProcedure> servicedProcedures = new ArrayList<SimplifiedProcedure>();
		for (SimplifiedProcedure simplifiedProcedure : simplifiedProcedureList) {
			for (CompletedProceduresSimplifier completedProceduresSimplifier : completedProceduresSimplifierList) {
				if (formatDate(completedProceduresSimplifier.getDatePerformed()).equals(
				    formatDate(simplifiedProcedure.getTimeOrdered()))
				        && simplifiedProcedure.getPatientId().equals(completedProceduresSimplifier.getPatientId())
				        && simplifiedProcedure.getProcedure().equals(completedProceduresSimplifier.getProcedureOrdered())
				        && completedProceduresSimplifier.getAction() != null) {
					//do nothing those procedures have already been serviced,
					servicedProcedures.add(simplifiedProcedure);
					
				} else {
					filteredFinal.add(simplifiedProcedure);
				}
			}
		}
		model.addAttribute("procedures", filteredFinal);
		model.addAttribute("done", completedProceduresSimplifierList);
		
	}
	
	private Date getPreviousDateBasedOnMonths() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -14);
		return calendar.getTime();
	}
	
	private static String formatDate(Date date) {
		
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		return formatter.format(date);
	}
}
