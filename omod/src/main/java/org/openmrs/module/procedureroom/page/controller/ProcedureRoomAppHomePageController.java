package org.openmrs.module.procedureroom.page.controller;

import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.OpdTestOrder;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.module.procedureroom.model.SimplifiedProcedure;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;

import java.util.ArrayList;
import java.util.List;

@AppPage("procedureroom.procedure")
public class ProcedureRoomAppHomePageController {
	
	public void controller(UiUtils ui, PageModel model) {
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
		List<OpdTestOrder> allTestOrders = hospitalCoreService.getAllOpdOrdersByDateRange(true);
		List<SimplifiedProcedure> simplifiedProcedureList = new ArrayList<SimplifiedProcedure>();
		Concept procedureConcept = Context.getConceptService().getConceptByUuid("1651AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		for (OpdTestOrder opdTestOrder : allTestOrders) {
			SimplifiedProcedure simplifiedProcedure = new SimplifiedProcedure();
			if (opdTestOrder.getConcept().equals(procedureConcept)) {
				simplifiedProcedure.setPatientId(opdTestOrder.getPatient().getPatientId());
				simplifiedProcedure.setPatientIdentifier(opdTestOrder.getPatient().getPatientIdentifier().getIdentifier());
				simplifiedProcedure.setNames(opdTestOrder.getPatient().getPersonName().getFullName());
				simplifiedProcedure.setFrom(opdTestOrder.getFromDept());
				simplifiedProcedure.setProcedure(opdTestOrder.getValueCoded().getName().getName());
				simplifiedProcedure.setTimeOrdered(opdTestOrder.getCreatedOn());
				
				//add that on the list
				simplifiedProcedureList.add(simplifiedProcedure);
			}
		}
		model.addAttribute("procedures", simplifiedProcedureList);
		
	}
}
